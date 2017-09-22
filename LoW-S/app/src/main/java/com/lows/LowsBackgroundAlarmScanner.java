package com.lows;



import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

//import com.stericson.RootTools.RootTools;
//import com.stericson.RootTools.exceptions.RootDeniedException;
//import com.stericson.RootTools.execution.Command;

import android.Manifest;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
//import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.lows.contentprovider.MyCodeBookContentProvider;
import com.lows.database.CodeBookTable;


/**
 * This class implements the LoWS Background Scanner Service, this IntentService is executed periodically 
 * by the AlarmManager which is set within the LowsActivity class.
 *
 * TODO: 	-find reason why the background scanner crashes sometimes
 * 			-move display and search string storage to sqlite
 * 			-Try to find a way to prevent parsing the Scan Results twice, currently we parse them within the 
 * 			 LowsActivity and within this class.
 * 			-Same as in the LowsActivity, the extended (flexible) LoWS parsing is not complete,
 * 			 currently fragmentation, encryption, signature validation and extended type extraction
 * 			 is not supported, when a way has found to only parse the data once for the BackgroundScanner
 * 			 and the Main LowsActivity, then this should be done.
 * 
 * @author Sven Zehl
 *
 * 
 */
public class LowsBackgroundAlarmScanner extends IntentService {

	private static int backgroundScannerInterval = 5;

	private static final String TAG = "com.lows.LowsBackgroundAlarmScanner";
	//search strings
	private String[] searchNCompareData;
	private int searchNCompareDataLength;
	//display strings if a match occured
	private String[] alarmMessagesData;
	private int alarmMessagesDataLength;
	//Handler to access the Wifi API
	WifiManager mainWifiObj;
	//Broadcast Receiver for new Wifi Scan Results
	WifiBackgroundScanReceiver wifiRecieverBackground;
	private boolean scanFinished = false;
	//AccessPoint List
	private List<AccessPoint> aps;
	private AccessPoint tempAp;
	//LoWS List
	private List<LoWS> lows;
	//Notification Killer Map
	//Map killIDs = new HashMap();
	Map<Integer, Handler> killIDs = new HashMap<Integer, Handler>();


	Intent BackgroundScannerIntent;
	PendingIntent BackgroundScannerPendingIntent;
	AlarmManager alarm;



	@Override
	public void onCreate() {
		super.onCreate();

		// this gets called properly
		Log.d("myTag", "Service onCreate()");
		mainWifiObj = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		//New Broadcast Receiver
		wifiRecieverBackground = new WifiBackgroundScanReceiver();
		registerReceiver(wifiRecieverBackground, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		Log.i(TAG, "Background Scanner Intent Service started");


		//Get the searchStrings and the DisplayStrings
		searchNCompareData=arg0.getStringArrayExtra("searchNCompareData");
		alarmMessagesData=arg0.getStringArrayExtra("alarmMessagesData");
		searchNCompareDataLength = searchNCompareData.length;
		alarmMessagesDataLength = alarmMessagesData.length;
		if(searchNCompareDataLength!=alarmMessagesDataLength)
		{
			Log.i(TAG, "Intent Service Error != ");
		}
		else if(searchNCompareDataLength==0)
		{
			Log.i(TAG, "Intent Service Error ==0");
		}
		else
		{
			//what else?
		}
		//if (android.os.Build.VERSION.SDK_INT >= 23){
		startChangeBackgroundScanService();
		//}
		//Start the Background Scanner 
		scanNsearch();

	}



	public void startChangeBackgroundScanService()
	{
		if((searchNCompareData == null || alarmMessagesData == null))
		{
			searchNCompareData = new String[0];
			alarmMessagesData = new String[0];
		}
		BackgroundScannerIntent = new Intent(this, LowsBackgroundAlarmScanner.class);
		alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Calendar cal = Calendar.getInstance();
		BackgroundScannerIntent.removeExtra("searchNCompareData");
		BackgroundScannerIntent.removeExtra("alarmMessagesData");
		BackgroundScannerIntent.putExtra("searchNCompareData", searchNCompareData);
		BackgroundScannerIntent.putExtra("alarmMessagesData", alarmMessagesData);

		BackgroundScannerPendingIntent = PendingIntent.getService(this, 0, BackgroundScannerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// Start every 15 seconds
		//alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), backgroundScannerInterval*1000, BackgroundScannerPendingIntent);
		//if (android.os.Build.VERSION.SDK_INT >= 23){
		//	alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() + backgroundScannerInterval * 1000, BackgroundScannerPendingIntent);
			//alarm.setExact(AlarmManager.RTC_WAKEUP, backgroundScannerInterval * 1000, intent);
		//}
		if (Build.VERSION.SDK_INT >= 23) {
			alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()+ backgroundScannerInterval * 1000, BackgroundScannerPendingIntent);
		} else if (Build.VERSION.SDK_INT >= 19) {
			alarm.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()+ backgroundScannerInterval * 1000, BackgroundScannerPendingIntent);
		} else {
			alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()+ backgroundScannerInterval * 1000, BackgroundScannerPendingIntent);
		}
		//alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, backgroundScannerInterval * 1000, BackgroundScannerPendingIntent);
	}



	public LowsBackgroundAlarmScanner() {

		super("LowsBackgroundAlarmScanner");
	}
	
	/**
	 * This function starts the IEEE 802.11 scanning process via the standard Android Wifi API
	 */
	void scanNsearch()
	{
		//Get the Wifi System Service
				//Start the IEEE 802.11 scan via the Wifi System Service APO
		mainWifiObj.startScan(); 
		/*Only exit this function when the scan was completed, if this is not done, the BackgroundScanner 
		 * is terminated before the scan was complete!
		 */
		waitForScanFinished();
		
	}

	/**
	 * WifiScanReceiver, the BroadcastReceiver class for the IEEE 802.11 scan
	 * 
	 * @author Sven Zehl
	 *
	 */












	class WifiBackgroundScanReceiver extends BroadcastReceiver {


		private void getWifi() {
			//if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			//	requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0x12345);
			//} else {
				doGetWifi(); // the actual wifi scanning
			//}
		}

		private void doGetWifi() {
			aps = new ArrayList<AccessPoint>();
			List<ScanResult> wifiList;
			StringBuilder sb = new StringBuilder();
			sb = new StringBuilder();
			wifiList = mainWifiObj.getScanResults();
			Log.i(TAG, Integer.toString((wifiList.size())));
			for(int i = 0; i < wifiList.size(); i++){
				//sb.append(new Integer(i + 1).toString() + ".");
				//Log.i(TAG, (wifiList.get(i)).toString());
				sb.append((wifiList.get(i)).SSID.toString());
				//sb.append((wifiList.get(i)).toString());
				//sb.append("OFN: " + wifiList.get(i).operatorFriendlyName.toString());
				sb.append("\n");


				String delimiter = new String();
				int pos;
				//Recognition of the first and all new access point entries

				tempAp = new AccessPoint();

				tempAp.setBssid(wifiList.get(i).BSSID.toString());

				tempAp.setFreq(wifiList.get(i).frequency);

				tempAp.setBeaconInterval(0);

				tempAp.setSignal(wifiList.get(i).level);

				tempAp.setLastSeen((int) wifiList.get(i).timestamp);

				tempAp.setSsid(wifiList.get(i).SSID.toString());

				aps.add(tempAp);

			}
			Log.i(TAG, "Background Scanner Received an WIFI Alert!" + sb);
			scanFinished();
			ieParser();
		}

		public void onReceive(Context c, Intent intent)
		{
			getWifi();
			//start nlscanner binary to get all the ScanResults from the driver
			//startNLscanner();
			//wifimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			//List<ScanResult> results = mainWifiObj.getScanResults();
			//for (ScanResult result : results) {
			//    Log.d("WifiScanReceiver",
			//            String.format("\n%s (%s) %dMHz %ddBm", result.SSID, result.capabilities,
			//                    result.frequency, result.level));
			//}
			//lowsParser();
		}
/*
		public void onReceive(Context c, Intent intent) 
		{	
		    //start nlscanner binary to get all the ScanResults from the driver
			//Log.i(TAG, "Background Scanner Received an WIFI Alert!");
			//wifimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			//List<ScanResult> results = mainWifiObj.getScanResults();
			//for (ScanResult result : results) {
			//	Log.d(TAG, String.format("\n%s (%s) %dMHz %ddBm", result.SSID, result.capabilities,
			//					result.frequency, result.level));
			//}




			List<ScanResult> wifiList;
			StringBuilder sb = new StringBuilder();
			sb = new StringBuilder();
			wifiList = mainWifiObj.getScanResults();
			Log.i(TAG, Integer.toString((wifiList.size())));
			for(int i = 0; i < wifiList.size(); i++){
				sb.append(new Integer(i+1).toString() + ".");
				Log.i(TAG, (wifiList.get(i)).toString());
				sb.append((wifiList.get(i)).toString());
				//wifiList.get(i).operatorFriendlyName;
				sb.append("\\n");
			}
			Log.i(TAG, "Background Scanner Received an WIFI Alert!"+sb);
			//start the nlscanner binary
			//startNLscanner();
			//notify that the scan was completed
			scanFinished();
		}
*/
	}


	//private void getWifi() {
	//	if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
	//		ContextCompat.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0x12345);
	//	} else {
	//		doGetWifi(); // the actual wifi scanning
	//	}
	//}


	@Override
	public void onDestroy()
	{
		unregisterReceiver(wifiRecieverBackground);
		super.onDestroy();
	}
	
	/**
	 * This function waits till the IEEE 802.11 scan was finished,
	 * this is needed to keep the BackgroundScanner alive
	 */
    public void waitForScanFinished(){
        synchronized(this){
            while(!scanFinished){
                try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }
    }
    
    /**
     * This function is called when the full IEEE 802.11 scan was completed (incl. nlscanner binary call)
     * the function simply sets the variable scanFinished to true and therefore ends the waitForScanFinsihed()
     * function.
     */
    public void scanFinished(){
        synchronized(this){
             this.scanFinished = true;
             notifyAll();
        }
    }
    
    /**
     * Start the nlscanner binary and parse the data
     */

/*
    void startNLscanner()
    {
    	//initialize the AccessPoint List
		aps = new ArrayList<AccessPoint>();
		//Set the RootTools Command
		Command command = new Command(0, "/data/data/com.lows/files/nlscanner"){
			@Override
			//Start ieParser() if the nlscanner succeeded
			public void commandCompleted(int id, int exitCode) {
				Log.i(TAG, "nlscanner sucessfully executed!");
				ieParser();
			}

			@Override
			//Parse the output of the nlscanner binary and fill the AccessPoint array
			public void commandOutput(int id, String line) {
				//String modifyText = outputText.getText().toString();
				String delimiter = new String();
				int pos;
				//Recognition of the first and all new access point entries
				if(line.indexOf("*")==0||line.indexOf("#")==0)
				{
					if(!(line.indexOf("#")==0))
					{
						aps.add(tempAp);
					}
						
					tempAp = new AccessPoint();
				}
				else if(line.indexOf(delimiter = "BSS ")!=-1)
				{
					pos = line.indexOf(delimiter);
				    tempAp.setBssid(line.substring( pos + delimiter.length() ));
				}
				else if(line.indexOf(delimiter = "freq: ")!=-1)
				{
					pos = line.indexOf(delimiter);
				    tempAp.setFreq(  Integer.parseInt(line.substring(pos + delimiter.length())) );
				}
				else if(line.indexOf(delimiter = "interval: ")!=-1)
				{
					pos = line.indexOf(delimiter);
				    tempAp.setBeaconInterval(  Integer.parseInt(line.substring(pos + delimiter.length())) );	
				}	
				else if(line.indexOf(delimiter = "(dBm): ")!=-1)
				{
					pos = line.indexOf(delimiter);
				    tempAp.setSignal(  Double.parseDouble(line.substring(pos + delimiter.length())) );	
				}
				else if(line.indexOf(delimiter = "(ms ago): ")!=-1)
				{
					pos = line.indexOf(delimiter);
				    tempAp.setLastSeen(  Integer.parseInt(line.substring(pos + delimiter.length())) );	
				}
				else if(line.indexOf(delimiter = "SSID: ")!=-1)
				{
					pos = line.indexOf(delimiter);
				    tempAp.setSsid(line.substring( pos + delimiter.length() ));
				}
				else if(line.indexOf(delimiter = "IE data (hex): ")!=-1)
				{
					pos = line.indexOf(delimiter);
				    tempAp.addIE(line.substring( pos + delimiter.length() ));		    
				}	
			}

			@Override
			public void commandTerminated(int id, String reason) {
				Log.i(TAG, "nlscanner terminated!, reason: " + reason);
			}
			
		};
		
		//Execute nlscanner
        try {
			RootTools.getShell(false).add(command);
		} catch (IOException e) {
			Log.i(TAG, "nlscanner IO exception!");
		} catch (TimeoutException e) {
			Log.i(TAG, "nlscanner Timeout Exception");
			
		} catch (RootDeniedException e) {
			Log.i(TAG, "nlscanner root denied Exception!");
		}
    
    }

*/
	private static String asciiToHex(String asciiValue)
	{
		char[] chars = asciiValue.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++)
		{
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}
    
    /**
     * Parse the Information Elements stored in the AccessPoint array
     */
    void ieParser()
	{
		if(aps==null)
		{
			Log.i(TAG, "aps array == null, ieParser() cant do anything....");;
		}
		else
		{
			lows = new ArrayList<LoWS>();
			int numberAps = aps.size();
			int i,j;
			int numberIEs;
			String tempSSID;
			AccessPoint tempReadAp;
			for(i=0; i<numberAps; i++)
			{
				tempReadAp = aps.get(i);
				numberIEs = tempReadAp.getIESize();
				//We have SSID Embedding
				if(numberIEs==0) {
					tempSSID = tempReadAp.getSsid();
					int numLows = (tempSSID.length() - tempSSID.replace("^", "").length())/2;
					if(numLows>0)//We have a LoW-S encoding
					{
						Log.i(TAG, "Found "+Integer.toString(numLows)+" LoWS Embeddings in SSID: "+tempSSID);
						for(int x=1; x<=numLows; x++) {
							int posLows = tempSSID.indexOf("^");
							if (tempSSID.charAt(posLows + 4) == '^') {
								//Yes we have a lows embedding, now put it into our lows array list, together with the ap data
								//First extract data out of hostname
								String tempString = tempSSID.substring(tempSSID.indexOf("^") + 1, tempSSID.indexOf("^", posLows + 1));
								if (tempString.length() != 3) {
									Log.i(TAG, "ERROR Embedding is not a LoWS Embedding: " + tempString + "posLows: " + Integer.toString(posLows));
								} else {
									String tempHex = asciiToHex(tempString);
									LoWS tempLows = new LoWS(tempReadAp, tempHex, 3); //Lows SSID Embedding is always 3 Byte
									lows.add(tempLows);
									Log.i(TAG, "added SSID embedded LoWS: " + tempString + "posLows: " + Integer.toString(posLows) + "tempHex: " + tempHex);
									tempSSID = tempSSID.substring(posLows + 5);
								}
								//tempSSID = tempSSID.subSequence(posLows + 4, posLows).toString();
								//LoWS tempLows = new LoWS(tempReadAp, tempSSID, 3); //Lows Cisco Embedding is always 3 Byte
								//lows.add(tempLows);
								//Log.i(TAG, "added SSID embedded LoWS: " + tempString + "posLows: "+Integer.toString(posLows));
								//debugText = debugText + "\n-" + "added SSID embedded LoWS: " +tempSSID;
							}
						}
					}
				}
				for(j=0; j<numberIEs; j++)
				{
					String tempIE = tempReadAp.getIE(j);
					//Look for Cisco CCX Hostname Embedding
					
					if(tempIE.charAt(0)=='8' && tempIE.charAt(1) == '5')
					{
						//We have a Cisco CCX IE
						//First extract hostname out of IE
						tempIE = tempIE.subSequence(36, 81).toString();
						//Remove spaces
						tempIE = tempIE.replaceAll(" ", "");
						//Now convert to ascii
						StringBuilder output = new StringBuilder();
						for(int p = 0; p < tempIE.length(); p+=2)
						{
							String str = tempIE.substring(p, p+2);
							output.append((char)Integer.parseInt(str, 16));
						}
						//Now look if there is something embedded
						if(output.charAt(10)=='^' && output.charAt(14)=='^')//We have a LoW-S encoding
						{
							//Yes we have a lows embedding, now put it into our lows array list, together with the ap data
							//First extract data out of hostname
							tempIE = tempIE.subSequence((tempIE.length()-8), (tempIE.length()-2)).toString();
							LoWS tempLows = new LoWS(tempReadAp, tempIE, 3); //Lows Cisco Embedding is always 3 Byte
							lows.add(tempLows);
							//debugText = debugText + "\n-" + "added IE Cisco-Embedding: " +tempIE;
						}
						
					}
					
					if(tempIE.charAt(0)=='d' && tempIE.charAt(1) == 'd' && tempIE.charAt(6) == 'a' 
							&& tempIE.charAt(7) == 'a' && tempIE.charAt(9) == 'a' && tempIE.charAt(10) == 'a' 
							&& tempIE.charAt(12) == 'a' && tempIE.charAt(13) == 'a') //Vendor specific element + OUI aaaaaa
					{
						//Yes we have a lows embedding inside a Vendor Specific IE
						//Get the length of the IE
						String tempIELength = tempIE.subSequence(3, 5).toString();
						int tempIELengthInt=Integer.parseInt(tempIELength, 16); //Length is Data + OUI + type
						//Remove all the whitespaces
						tempIE = tempIE.replaceAll(" ", "");
						//Extract the data block
						
						tempIE = tempIE.subSequence(10, (14+((tempIELengthInt-5)*2))).toString();
						
						LoWS tempLows = new LoWS(tempReadAp, tempIE, (tempIELengthInt-3)); //Data_Length = IELength-OUI
						
						lows.add(tempLows);
					
						//debugText = debugText + "\n-" + "added IE Vendor Specific: " +tempIE;
						
					}
					
				}
			}
			Log.i(TAG, "ieParser() Finished");
			//start the LoWS Parser
			lowsParser();
			//Remove Double LoWS
			removeDoubleLows();
			//start to compare the found LoWS with the search strings
			compareResultWithSearchStrings();
		}
	}
    
    /**
     * This function parses the LoWS and extracts the LoWS raw data
     * Beside, this function starts the Broadcast Intent to notify other applications
     * that new LoWS messages have been found. Further the service data is sent within the Intent
     */
    public void lowsParser()
	{
		Log.i(TAG, "lowsParser() started ");
		if(lows==null)
		{
			Log.i(TAG, "lowsParser() finished, No LoWS found :-( ");
			//debugText = debugText + "\n-" + "No LoWS found in your current area, lowsParser terminated.";
			return;
		}
		else
		{
			int numberLows = lows.size();
			int i;
			LoWS tempReadLows;
			Log.i(TAG, "lowsParser() lows size: "+ Integer.toString(lows.size()));
			for(i=0; i<numberLows; i++)
			{
				tempReadLows = lows.get(i);
				String tempLowsData = tempReadLows.getLowsData();
				
				//Send Broadcast to enable other applications to use the lows data
				Intent broadcastIntent = new Intent();
				broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
				broadcastIntent.setAction("com.lows.newlows");
				broadcastIntent.putExtra("lows", tempLowsData);
				sendBroadcast(broadcastIntent);
				Log.i(TAG, "lowsParser() processing LoWS: "+tempLowsData);
				String tempLowsFormatType = tempLowsData.subSequence(0, 2).toString();
				int tempLowsFormatTypeInt=Integer.parseInt(tempLowsFormatType, 16);
				
				if(tempLowsFormatTypeInt<128)
				{
					//We have a lows in reduced Format
					parseReducedLows(i, tempLowsFormatTypeInt); 
					//In the reduced format, the format type is also the real type
				}
				else if(tempLowsFormatTypeInt >127)
				{
					//We have a lows in extended (flexible) Format
					parseExtendedLows(i);
				}

				
			}
		}
	
	}


	/**
	 * Delete double LoWS entries from LoWS List,
	 * E.g. Cisco APs often broadcast multiple BSSIDs and include the LoWS message
	 * in all beacons from all this BSSs, to not display the same LoWS in the ScanResults
	 * for the user, the double LoWS have to be removed.
	 */
	public void removeDoubleLows() {

		if (lows == null) {
			return;
		} else {
			String bestBepsBssid = findBestBEPS();
			Log.i(TAG, "Best BEPS BSSID: " + bestBepsBssid);

			LoWS tempReadLows;
			LoWS tempCompareLows;
			int toRemove = -1;
			for (int l = 0; l < lows.size(); l++) {
				tempReadLows = lows.get(l);
				//Remove double BEPS, we only need one with the highest RSSI
				String tempLowsData = tempReadLows.getLowsServiceData();
				int tempType = tempReadLows.getType();
				if (tempType == 33 && bestBepsBssid != "") //If it is BEPS
				{
					AccessPoint tempAccessPoint = tempReadLows.getApData();
					String tempBSSID = tempAccessPoint.getBssid();
					Log.i(TAG, "Checking BSSID: " + tempBSSID);
					if (!tempBSSID.equals(bestBepsBssid)) {
						toRemove = l;
						Log.i(TAG, "Remove LowS with BSSID: " + tempBSSID + " and index: " + l);
						break;
					}
				}

				int tempReadType = tempReadLows.getType();
				for (int k = 0; k < lows.size(); k++) {
					if (k != l) {
						tempCompareLows = lows.get(k);
						String tempCompareData = tempCompareLows.getLowsServiceData();
						int tempCompareType = tempCompareLows.getType();
						if (tempLowsData.equals(tempCompareData) && (tempReadType == tempCompareType)) {
							Log.i(TAG, "Double LoWS found: " + tempLowsData + "==" + tempCompareData);
							toRemove = k;
							break;
						}
					}
				}
				if (toRemove > 0) {
					break;
				}



			}
			if (toRemove > -1) {
				lows.remove(toRemove);
				Log.i(TAG, "LowS with index " + toRemove + " deleted");
				removeDoubleLows();
			}

		}
	}

	/**
	 * Describe THIS !!
	 */
	public String findBestBEPS() {
		LoWS tempReadLows;
		LoWS tempCompareLows;
		int toRemove = 0;
		int tempType;
		String bssidBestBeps = "";
		double bepsHighestRSSI = -100.0;
		for (int l = 0; l < lows.size(); l++) {
			tempReadLows = lows.get(l);
			//Remove double BEPS, we only need one with the highest RSSI

			tempType = tempReadLows.getType();
			Log.i(TAG, "LoWS Type: " + tempType);
			if (tempType == 33) //If it is BEPS
			{
				AccessPoint tempAccessPoint = tempReadLows.getApData();
				double tempSignal = tempAccessPoint.getSignal();
				String tempBSSID = tempAccessPoint.getBssid();
				Log.i(TAG, "BEPS found: " + tempBSSID + "Signal: "+Double.toString(tempSignal));
				//Find the beps with highest RSSI!!!!!!
				if (bepsHighestRSSI < tempSignal) {
					bepsHighestRSSI = tempSignal;
					bssidBestBeps = tempBSSID;
				}
			}

		}
		return bssidBestBeps;

	}


	/**
     * Parse service data of the reduced LoWS message
     * @param i, index within the LoWS List
     * @param type, LoWS type, e.g. BEPS
     */
	public void parseReducedLows(int i, int type)
	{
		LoWS tempReducedLows = lows.get(i);
		tempReducedLows.setFormatType(1);
		tempReducedLows.setType(type);
		tempReducedLows.setLowsServiceData(tempReducedLows.getLowsData().subSequence(2, 6).toString());
		lows.set(i, tempReducedLows);
		
	}
	
	/**
	 * Parse service data of extended (flexible) LoWS message
	 * @param i, index within the LoWS List
	 */
	public void parseExtendedLows(int i)
	{
		LoWS tempExtendedLows = lows.get(i);
		tempExtendedLows.setFormatType(2);
		lows.set(i, tempExtendedLows);
		parseExtendedOptionByte(i); 
		//underlying function will delete lows from array if it is a fragment and if it is not fully reassembled yet
		//Now we know that current LoWS is not a fragment or it is already fully reassembled
		parseExtendedType(i);
		parseExtendedSecurityByte(i);
		encryptExtendedData(i);
		verifyExtendedSignature(i);
		parseExtendedData(i);
		
		//debugText = debugText + "\n-" + "parseExtendedLows: " +tempExtendedLows.getLowsData();
	}
	
	public void parseExtendedData(int i) {
		LoWS tempExtendedLows = lows.get(i);
		int extendedTypeDataLength = tempExtendedLows.getLowsData().length();
		int startOfServiceData = tempExtendedLows.getBeginOfServiceData();
		String extendedLowsServiceData = tempExtendedLows.getLowsData().subSequence(startOfServiceData, extendedTypeDataLength).toString();
		tempExtendedLows.setLowsServiceData(extendedLowsServiceData);
		lows.set(i, tempExtendedLows);
	}

	public void verifyExtendedSignature(int i) {
		// TODO Auto-generated method stub
		
	}

	public void encryptExtendedData(int i) {
		// TODO Auto-generated method stub
		
	}

	public void parseExtendedSecurityByte(int i)
	{
		LoWS tempExtendedLows = lows.get(i);
		//If security is present, the parseExtendedOptionByte() func. should recognized this
		if(tempExtendedLows.isEncryptionIsPresent())
		{
			//process Encryption stuff
		}
		if(tempExtendedLows.isSignatureIsPresent())
		{
			//process Signature stuff
		}
	}
	
	public void parseExtendedType(int i)
	{
		LoWS tempExtendedLows = lows.get(i);
		String tempLowsData = tempExtendedLows.getLowsData();
		if(tempExtendedLows.isSeqNumberIsPresent())
		{
			//Length of Seq# must be determined, because Type follows after the Seq#
		}
		else
		{
			//We have no Seq# the type starts with the second byte of the LoWS data.
			String tempLowsType = tempLowsData.subSequence(2, 4).toString();
			int tempLowsTypeInt=Integer.parseInt(tempLowsType, 16);
			if(tempLowsTypeInt<128)
			{
				//Next Byte does not belong to the type, tempLowsTypeInt is the type of the LoWS
				tempExtendedLows.setType(tempLowsTypeInt);
				tempExtendedLows.setEndOfType(4); //Till the next byte isn't also a type field, the end of type is at byte position 2
				//Now that we know the Type, we can set the DisplayString which will be displayed in the Scan Result fragment.
				//tempExtendedLows.setLowsDisplayString(getDisplayStringFromType(2,tempLowsTypeInt));
				if(!tempExtendedLows.isEncryptionIsPresent() && !tempExtendedLows.isSignatureIsPresent())
				{
					tempExtendedLows.setBeginOfServiceData(4);
				}
				lows.set(i, tempExtendedLows);
			}
			else
			{
				//Next Byte belongs also to the Type
				//Parsing of the lows must be continued till the byte is smaller than 128.
			}
		}
	}
	
	
	
	public void parseExtendedOptionByte(int i)
	{
		//LoWS tempExtendedLows = lows.get(i);
		//parse the options
		//parse SequenceNumber if Fragmented here by calling the Fragmented Function!!
		//If fragmented, LoWS must be deleted from lows array and stored somewhere else till LoWS is completed
		//(if all fragments were received) afterwards the complete LoWS should be stored in lows array.
		//If fragmented and not fully reassembeld, function should exit processing here!
	}
	
    
    
    
    
    /**
     * This function is called after all LoWS messages have been parsed.
     * The function now compares all the stored LoWS service data with the search strings that have been
     * submitted before. If a match occured a notification is started via the function sendNotification()
     */
    void compareResultWithSearchStrings()
    {
    	int i=0;
    	int j=0;
    	LoWS searchLow;
    	for(i=0; i< lows.size(); i++)
    	{
    		searchLow=lows.get(i);
    		for(j=0;j<searchNCompareDataLength; j++)
    		{
    			if(searchNCompareData[j].equals(searchLow.getLowsData()))
    			{
    				Log.i(TAG, "MATCH FOUND! " + searchNCompareData[j] + "==" + searchLow.getLowsData());
    				Log.i(TAG, "String to display: " + alarmMessagesData[j]);
    				sendNotification(alarmMessagesData[j], searchNCompareData[j], searchLow);
    			}
    		}
    		if(searchLow.getType()==67){
				//if(Integer.parseInt(searchLow.getLowsData().substring(0,2), 16) < 96) // Notify only big letter ascii messages
				//{
				sendNotificationCIS(searchLow);
				//}
			}
    	}
    }
    
    
    /**
     * This function is called from the compareResultWithSearchStrings() function if a match with the
     * search strings occured.
     * 
     * @param displayMessage, The message the should be displayed within the notification
     * @param matchString, the string that caused the match
     * @param matchLows, the LoWS that corresponds to the match
     */
    void sendNotification(String displayMessage, String matchString, LoWS matchLows)
	{
		int mID=2;
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
	    .setSmallIcon(R.drawable.ic_launcher)
	    .setContentTitle("LoWS Alarm")
	    .setContentText(displayMessage);
		
		//Prepare the Intent that starts the AlarmClickActivity when the user taps on the notification
		Intent resultIntent = new Intent(this, AlarmClickActivity.class);
		resultIntent.putExtra("matchString", matchString);
		resultIntent.putExtra("displayMessage", displayMessage);
		resultIntent.putExtra("formatType", matchLows.getFormatType());
		resultIntent.putExtra("BSSID", matchLows.getBssid());
		resultIntent.putExtra("SSID", matchLows.getSsid());
		resultIntent.putExtra("signalStrength", matchLows.getSignalStrength());
		resultIntent.putExtra("frequency", matchLows.getFrequency());
		resultIntent.putExtra("type", matchLows.getType());
		resultIntent.putExtra("serviceData", matchLows.getLowsServiceData());
		resultIntent.putExtra("alarmMessagesData", alarmMessagesData);
		resultIntent.putExtra("searchNCompareData", searchNCompareData);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		//Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(AlarmClickActivity.class);
		//Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		
		NotificationCompat.InboxStyle inboxStyle =
	        new NotificationCompat.InboxStyle();
		// Sets a title for the Inbox in expanded layout
		inboxStyle.setBigContentTitle("LoWS Alarm");
	    inboxStyle.addLine(displayMessage);
		mBuilder.setStyle(inboxStyle);
		//mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
		Notification note = mBuilder.build();
	    note.defaults |= Notification.DEFAULT_VIBRATE;
	    note.defaults |= Notification.DEFAULT_LIGHTS;
	    note.defaults |= Notification.DEFAULT_SOUND;
	    note.defaults |= Notification.PRIORITY_MAX;
	    note.flags |= Notification.FLAG_AUTO_CANCEL;
		Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(500);
	    //mId would allow to update the notification later on, we do not need it at the moment
	    mNotificationManager.notify(mID, note);
	}
	void sendNotificationCIS(LoWS matchLows)
	{

		CISLoWSReducedType cisTypeTemp = new CISLoWSReducedType();
		String serviceData = matchLows.getLowsServiceData();
		String hardDataString = cisTypeTemp.decodeData(serviceData);
		String hardcodedValue = "0x"+serviceData.substring(0,2);
		SharedPreferences pref10 = getApplicationContext().getSharedPreferences("PREF_TAG_TIMESTAMP10", Context.MODE_PRIVATE);
		SharedPreferences pref20 = getApplicationContext().getSharedPreferences("PREF_TAG_TIMESTAMP20", Context.MODE_PRIVATE);
		SharedPreferences pref30 = getApplicationContext().getSharedPreferences("PREF_TAG_TIMESTAMP30", Context.MODE_PRIVATE);
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		int mID = Integer.parseInt(serviceData, 16);
		if((Integer.parseInt(serviceData.substring(0,2), 16)>90 && Integer.parseInt(serviceData.substring(0,2), 16)<97)||(Integer.parseInt(serviceData.substring(0,2), 16)>122))
		{
			mID = 10;
			pref10.edit().putLong("PREF_TAG_TIMESTMAP10", System.currentTimeMillis()).commit();
			//pref10.edit().clear().apply();
		}
		else if((Integer.parseInt(serviceData.substring(0,2), 16)>97 && Integer.parseInt(serviceData.substring(0,2), 16)<123))
		{
			mID = 20;
			pref20.edit().putLong("PREF_TAG_TIMESTMAP20", System.currentTimeMillis()).commit();
			//pref20.edit().clear().apply();
		}
		else
		{
			mID = 30;
			pref30.edit().putLong("PREF_TAG_TIMESTMAP30", System.currentTimeMillis()).commit();
			//pref30.edit().clear().apply();
		}

		//long ts10 = pref10.getLong("PREF_TAG_TIMESTMAP10", 0);
		//ts10 = ts10 + 1;
		//pref10.edit().putLong("PREF_TAG_TIMESTMAP10", ts10).commit();
		//pref10.edit().clear().commit();
		//Log.i(TAG, "ts10 :"+ (ts10) );


		long ts10 = pref10.getLong("PREF_TAG_TIMESTMAP10", 0);
		long ts20 = pref20.getLong("PREF_TAG_TIMESTMAP20", 0);
		long ts30 = pref30.getLong("PREF_TAG_TIMESTMAP30", 0);
		Log.i(TAG, "System.currentTimeMillis()-ts10 :"+ (System.currentTimeMillis()-ts10) );
		if(System.currentTimeMillis()-ts10 > 1000*25){
			Log.i(TAG, "Killing mID 10");
			notificationManager.cancel(10);
		}
		Log.i(TAG, "System.currentTimeMillis()-ts20 :"+ (System.currentTimeMillis()-ts20) );
		if(System.currentTimeMillis()-ts20 > 1000*25){
			Log.i(TAG, "Killing mID 20");
			notificationManager.cancel(20);
		}
		Log.i(TAG, "System.currentTimeMillis()-ts30 :"+ (System.currentTimeMillis()-ts30) );
		if(System.currentTimeMillis()-ts30 > 1000*25){
			Log.i(TAG, "Killing mID 30");
			notificationManager.cancel(30);
		}

		String codebookValue = "0x"+serviceData.substring(2,4);
		String typeValue = "0x"+Integer.toHexString(matchLows.getType());
		String macData = matchLows.getBssid();
		String  typeString = cisTypeTemp.getDisplayString();
		//Database Stuff
		//Get correct row
		Cursor cursor = getContentResolver().query(MyCodeBookContentProvider.CONTENT_URI, null,
				"mac LIKE '"+macData+"' AND servicetype LIKE '"+ typeValue+
						"' AND hardcodedvalue LIKE '"+hardcodedValue+
						"' AND codebookvalue LIKE '"+codebookValue+"'",
				null,null);

		String dataValue="Currently no location specific data available (no codebook entry found)";
		if (cursor != null)
		{
			if(cursor.getCount()>0)
			{
				cursor.moveToFirst();
				dataValue = cursor.getString(cursor.getColumnIndexOrThrow(CodeBookTable.COLUMN_DATA));
			}
		}    // always close the cursor
		cursor.close();

		//End database




		//int mID=2;


		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.mobi))
				.setContentTitle(hardDataString)
				.setContentText(dataValue);


		NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
		bigText.bigText(dataValue);
		bigText.setBigContentTitle(hardDataString);
		bigText.setSummaryText(typeString);
		mBuilder.setStyle(bigText);
		mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);




		//Prepare the Intent that starts the AlarmClickActivity when the user taps on the notification
		Intent resultIntent = new Intent(this, LowsActivity.class);
		/*
		resultIntent.putExtra("matchString", matchString);
		resultIntent.putExtra("displayMessage", displayMessage);
		resultIntent.putExtra("formatType", matchLows.getFormatType());
		resultIntent.putExtra("BSSID", matchLows.getBssid());
		resultIntent.putExtra("SSID", matchLows.getSsid());
		resultIntent.putExtra("signalStrength", matchLows.getSignalStrength());
		resultIntent.putExtra("frequency", matchLows.getFrequency());
		resultIntent.putExtra("type", matchLows.getType());
		resultIntent.putExtra("serviceData", matchLows.getLowsServiceData());
		resultIntent.putExtra("alarmMessagesData", alarmMessagesData);
		resultIntent.putExtra("searchNCompareData", searchNCompareData);
		*/

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		//Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(AlarmClickActivity.class);
		//Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
				stackBuilder.getPendingIntent(
						0,
						PendingIntent.FLAG_UPDATE_CURRENT
				);
		mBuilder.setContentIntent(resultPendingIntent);

		/*
		NotificationCompat.InboxStyle inboxStyle =
				new NotificationCompat.InboxStyle();
		// Sets a title for the Inbox in expanded layout
		inboxStyle.setBigContentTitle(hardDataString);
		inboxStyle.addLine(dataValue);
		mBuilder.setStyle(inboxStyle);
		*/
		//mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
		Notification note = mBuilder.build();
		//note.defaults |= Notification.DEFAULT_VIBRATE;
		//note.defaults |= Notification.DEFAULT_LIGHTS;
		//note.defaults |= Notification.DEFAULT_SOUND;
		//note.defaults |= Notification.PRIORITY_MAX;
		note.flags |= Notification.FLAG_AUTO_CANCEL;
		//Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		//v.vibrate(500);
		//mId would allow to update the notification later on, we do not need it at the moment
		mNotificationManager.notify(mID, note);
/*
		if (killIDs.get(mID) == null) // doesn't exist
		{
			killIDs.put(mID, new Handler());
			Log.i(TAG, "Adding new Handler!!!! mID: "+mID);

		}

		// exists
		// do stuff with arr
		final int killID = mID;
		Handler handler = killIDs.get(mID);
		handler.removeCallbacksAndMessages(null);
		Log.i(TAG, "Starting killer runnable");
		handler.postDelayed(new Runnable(){
			@Override
			public void run(){
				NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.cancel(killID);
			}
		}, 1000*60*1); //Delete Notification Automatically after 1min if not refrehsed
		*/
	}

}