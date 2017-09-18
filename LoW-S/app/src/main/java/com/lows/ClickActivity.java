package com.lows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.lows.contentprovider.MyCodeBookContentProvider;
import com.lows.database.CodeBookTable;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import android.widget.Switch;
import android.widget.TextView;
/**
 * This Activity is started when a user taps on a single item of the ListView displayed 
 * via the ResultListFragment, this activity lists details about the specifc LoWS
 * and allows the setting of search strings. If a dichotomous code LoWS is used, this Activity
 * tries to decode the ldc, if the ldc is not available the Codebook Updater is started.
 * 
 *TODO: -make the variable CB_CHECK_INTERVAL adjustable via the settings menu and combine the
 *		 variable with the CB_CHECK_INTERVAL variable of the AlarmClickActivity
 *		-Currently only reduced LoWS together with the dichotomous code are supported,
 *		 this should be extended to also support dichotomous codes within a extended (flexible) LoWS message.
 *		-Display the ldc after the codebook was updated immediately, currently the string
 * 		 "Currently no location specific data available (no codebook entry found)" is displayed if no codebook
 * 		 was available but this string is not automatically replaced after the codebook was updated, it is 
 * 		 only replaced after the ClickActivity is reloaded manually by the user by returning and tapping again
 * 		 on the same LoWS entry in the ListView.
 * 
 * @author Sven Zehl
 *
 *
 */
public class ClickActivity extends Activity {
	int position;
	ProgressDialog dialog;
	boolean alarmSwitchState = false;
	EditText searchEditText;
	Button saveButton;
	private static final String TAG = "com.lows.ClickActivity";
	private static final int CB_CHECK_INTERVAL = 10; //check for new codebook if entry is older than CB_CHECK_INTERVAL 
													//You should also check the constant of AlarmClickActivity!

	//Access to the Android Wifi Service
	WifiManager mainWifiObj;
	//Broadcast Receiver Object
	WifiScanReceiver wifiReciever;
	String macData = "";
	double rssiData = -100.0;
	int serviceType = 0;
	String serviceData = "";
	Switch alarmSwitch;





	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.click_activity);
		final TextView typeTextView = (TextView) findViewById(R.id.type);
		final TextView dataTextView = (TextView) findViewById(R.id.data);
		final TextView locationTextView = (TextView) findViewById(R.id.locdata);
		final TextView cbDescTextView = (TextView) findViewById(R.id.codebook_desc);
		final TextView rssiTextView = (TextView) findViewById(R.id.rssi);
		//rssiImg= (ImageView) findViewById(R.id.rssiImage);
		//rssiImg.setImageResource(R.drawable.wifi4);

		final ImageView rssiImg= (ImageView) findViewById(R.id.rssiImage);
		searchEditText = (EditText) findViewById(R.id.searchText);
		final TextView searchTextView = (TextView) findViewById(R.id.alarmIf);
		alarmSwitch = (Switch) findViewById(R.id.alarm_switch);
		saveButton = (Button) findViewById(R.id.save_button);

		Bundle bundle = getIntent().getExtras();
		position = bundle.getInt("position");
		String bundleType = bundle.getString("type");
		String bundleData = bundle.getString("data");
		String bundleSearchText = bundle.getString("searchText");
		serviceData = bundle.getString("serviceData");
		serviceType = bundle.getInt("serviceType");
		int formatType = bundle.getInt("formatType");
		macData = bundle.getString("mac");
		rssiData = bundle.getDouble("rssi");
		// Fields for database lookup
		String hardcodedValue = "0x00";
		String codebookValue = "0x00";
		String typeValue = "0x" + Integer.toHexString(serviceType);

		if(rssiData > -40.0)
		{
			rssiImg.setImageResource(R.drawable.wifi4);
		}
		else if(rssiData > -60.0 && rssiData < -40.0)
		{
			rssiImg.setImageResource(R.drawable.wifi3);
		}
		else if(rssiData > -80.0 && rssiData < -60.0)
		{
			rssiImg.setImageResource(R.drawable.wifi2);
		}
		else
		{
			rssiImg.setImageResource(R.drawable.wifi0);
		}
		if(serviceType==33)
		{
			rssiImg.setImageResource(R.drawable.beps);
			if(serviceData.equals("5245"))
			{
				rssiImg.setImageResource(R.drawable.evaplan);
			}
		}

		if(serviceType==63)
		{
			if(serviceData.equals("464d"))
			{
				rssiImg.setImageResource(R.drawable.donald);
			}
		}

		//get Wifi System Service
		mainWifiObj = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		//Register Broadcast Receiver to IntentFilter
		wifiReciever = new WifiScanReceiver();
		registerReceiver(wifiReciever, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));


		//Start the IEEE 802.11 scan via the Wifi System Service APO
		mainWifiObj.startScan();

		//TODO: Here also the case when a dichotomous code is embedded within a extended (flexible) type should
		// 		be supported somehow.
		if (formatType == 1) {
			// Codebook format (reduced format)
			hardcodedValue = "0x" + serviceData.substring(0, 2);
			codebookValue = "0x" + serviceData.substring(2, 4);
			// Database Stuff
			// Get correct row
			Cursor cursor = getContentResolver().query(
					MyCodeBookContentProvider.CONTENT_URI,
					null,
					"mac LIKE '" + macData + "' AND servicetype LIKE '"
							+ typeValue + "' AND hardcodedvalue LIKE '"
							+ hardcodedValue + "' AND codebookvalue LIKE '"
							+ codebookValue + "'", null, null);

			String dataValue = "Currently no location specific data available (no codebook entry found)";
			//Check if an entry was found
			if (cursor != null) {
				//If an entry was found for the ldc
				if(cursor.getCount()>0)
		    	{
		    		cursor.moveToFirst();
		    		dataValue = cursor.getString(cursor.getColumnIndexOrThrow(CodeBookTable.COLUMN_DATA));
		    		String entryDate = cursor.getString(cursor.getColumnIndexOrThrow(CodeBookTable.COLUMN_LASTCHANGED));
		    		
		    		/*Check if the codebook updater should be started because the stored codebook
		    		 * is older than CB_CHECK_INTERVAL.
		    		 */
		    		Calendar c = Calendar.getInstance();
		    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		    		try {
		    			//Get current date and time
						Date currentDateDate = sdf.parse(sdf.format(c.getTime()));
						//Set Calendar to time of the entry
						c.setTime(sdf.parse(entryDate));
						//Increment the Calendar date (Date of the entry) to CB_CHECK_INTERVAL
						c.add(Calendar.DATE, CB_CHECK_INTERVAL); 
						//Get the time and date of the entry plus CB_CHECK_INTERVAL from calendar and format it into Date
						Date entryDateDate = sdf.parse(sdf.format(c.getTime()));
						//Compare if entryDateDate now is older than current date, if yes start codebook updater
						//Toast.makeText(getApplicationContext(), "LoWSActivity: entryDatDate:"+entryDateDate.toString()+" currentDatDate:"+currentDateDate.toString(), Toast.LENGTH_SHORT).show();
						if(entryDateDate.before(currentDateDate)){
							Log.w(TAG, "entryDatDate:"+entryDateDate.toString()+" currentDatDate:"+currentDateDate.toString());
				    		Intent cbusIntent = new Intent(this, CodeBookUpdaterService.class);
							cbusIntent.putExtra(CodeBookUpdaterService.MAC_IN_MSG, macData);
							startService(cbusIntent);
			        	}	
					} catch (ParseException e) {
						Log.e(TAG, "Error while processing date comparison "+e.toString());
					}
		    	}
				//No entry was found for the ldc
				else {
					//Start Codebook Updater
					Intent cbusIntent = new Intent(this, CodeBookUpdaterService.class);
					//Send the codebook updater the mac
					cbusIntent.putExtra(CodeBookUpdaterService.MAC_IN_MSG, macData);
					startService(cbusIntent);
				}
			} 
			//close the cursor
			cursor.close();
			locationTextView.setText(dataValue);
			//End database operations

		} else {
			cbDescTextView.setVisibility(View.INVISIBLE);
			locationTextView.setVisibility(View.INVISIBLE);
		}
		boolean showAlarmSwitch = bundle.getBoolean("showAlarmSwitch");
		boolean initialAlarmSwitchState = bundle
				.getBoolean("AlarmInitialState");
		boolean showAlarmSearchField = bundle
				.getBoolean("showAlarmSearchField");
		typeTextView.setText(bundleType);
		dataTextView.setText(bundleData);
		searchTextView.setText(bundleSearchText);
		rssiTextView.setText("Distance (RSSI): "+rssiData+" dBm");

		// Database Stuff should be deleted, only for testing purposes....

		// Add Rows
		/*
		ContentValues values = new ContentValues();
		values.put(CodeBookTable.COLUMN_MAC, "a0:cf:5b:9f:93:c1");
		values.put(CodeBookTable.COLUMN_SERVICE_TYPE, "0x21");
		values.put(CodeBookTable.COLUMN_HARDCODED_VALUE, "0x52");
		values.put(CodeBookTable.COLUMN_CODEBOOK_VALUE, "0x77");
		values.put(CodeBookTable.COLUMN_DATA,
				"Use the white door on the end of the floor to escape");
		*/
		// Uri savedUri =
		// getContentResolver().insert(MyCodeBookContentProvider.CONTENT_URI,
		// values);

		// String[] projection = { CodeBookTable.COLUMN_ID,
		// CodeBookTable.COLUMN_MAC, CodeBookTable.COLUMN_DATA };
		// CursorLoader cursorLoader = new CursorLoader(this,
		// MyCodeBookContentProvider.CONTENT_URI, projection, null, null, null);
		// Cursor cursor = cursorLoader.loadInBackground();

		if (!showAlarmSwitch) {
			alarmSwitch.setVisibility(View.INVISIBLE);
			searchTextView.setVisibility(View.INVISIBLE);
			searchEditText.setVisibility(View.INVISIBLE);
			saveButton.setVisibility(View.INVISIBLE);
		}
		if (!showAlarmSearchField) {
			searchTextView.setVisibility(View.INVISIBLE);
			searchEditText.setVisibility(View.INVISIBLE);
		}
		if (showAlarmSwitch) {
			if (initialAlarmSwitchState) {
				alarmSwitch.toggle();
				alarmSwitchState = true;
				//rssiImg.setVisibility(View.INVISIBLE);
			}

		}

		alarmSwitch
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
												 boolean isChecked) {
						if (isChecked) {
							alarmSwitchState = true;
						} else {
							alarmSwitchState = false;
						}

					}

				});

		saveButton.setOnClickListener(new View.OnClickListener() {
			// Setting the action to perform when the start button is pressed.
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("alarmSet", alarmSwitchState);
				intent.putExtra("position", position);
				String searchTextReturn = searchEditText.getEditableText()
						.toString();
				intent.putExtra("searchTextReturn", searchTextReturn);
				setResult(-1, intent);
				onBackPressed();

			}

		});



	}




	/**
	 * WifiScanReceiver class, the BroadcastReceiver class that handles what should happen if new Wifi Scan
	 * Results are available, holds the method onReceive, which is called when new scan results were found.
	 * OnReceive then calls the nlscanner binary to get all IEEE 802.11 IE(s)
	 *
	 * @author Sven Zehl
	 */
	class WifiScanReceiver extends BroadcastReceiver {

		/**
		 * This method is called when new wifi scan results are available, we simply start the nlscanner
		 * to get all scan results together with all IEEE 802.11 IEs from the driver.
		 */

		private void getWifi() {
			if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
					requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0x12345);
				} else {
					doGetWifi(); // the actual wifi scanning
				}
			}
			else
			{
				doGetWifi(); // the actual wifi scanning
			}
		}

		private String asciiToHex(String asciiValue) {
			char[] chars = asciiValue.toCharArray();
			StringBuffer hex = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
				hex.append(Integer.toHexString((int) chars[i]));
			}
			return hex.toString();
		}

		@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
		private void doGetWifi() {
			final ImageView rssiImg= (ImageView) findViewById(R.id.rssiImage);
			final TextView rssiTextView = (TextView) findViewById(R.id.rssi);
			final TextView locationTextView = (TextView) findViewById(R.id.locdata);
			//aps = new ArrayList<AccessPoint>();
			List<ScanResult> wifiList;
			StringBuilder sb = new StringBuilder();
			sb = new StringBuilder();
			wifiList = mainWifiObj.getScanResults();
			Log.i(TAG, Integer.toString((wifiList.size())));
			for (int i = 0; i < wifiList.size(); i++) {
				//sb.append(new Integer(i + 1).toString() + ".");
				//Log.i(TAG, (wifiList.get(i)).toString());
				sb.append((wifiList.get(i)).SSID.toString());
				//sb.append((wifiList.get(i)).toString());
				//sb.append("OFN: " + wifiList.get(i).operatorFriendlyName.toString());
				sb.append("\n");


				String delimiter = new String();
				int pos;
				//Recognition of the first and all new access point entries

				AccessPoint tempApx = new AccessPoint();

				if(macData.equals(wifiList.get(i).BSSID.toString())) {
					rssiData = wifiList.get(i).level;
					String tempSSID = ((wifiList.get(i)).SSID.toString());
					if (rssiData > -40.0) {
						rssiImg.setImageResource(R.drawable.wifi4);
					} else if (rssiData > -60.0 && rssiData < -40.0) {
						rssiImg.setImageResource(R.drawable.wifi3);
					} else if (rssiData > -80.0 && rssiData < -60.0) {
						rssiImg.setImageResource(R.drawable.wifi2);
					} else {
						rssiImg.setImageResource(R.drawable.wifi0);
					}
					rssiTextView.setText("Distance (RSSI): " + rssiData + " dBm");
					if (serviceType == 33) {
						rssiImg.setImageResource(R.drawable.beps);
						if (serviceData.equals("5245")) {
							rssiImg.setImageResource(R.drawable.evaplan);
						}
					}
					if(serviceType==63)
					{
						if(serviceData.equals("464d"))
						{
							rssiImg.setImageResource(R.drawable.donald);
						}
					}
					//tempSSID = tempReadAp.getSsid();
					int numLows = (tempSSID.length() - tempSSID.replace("^", "").length()) / 2;
					if (numLows > 0)//We have a LoW-S encoding
					{
						Log.i(TAG, "Found " + Integer.toString(numLows) + " LoWS Embeddings in SSID: " + tempSSID);
						for (int x = 1; x <= numLows; x++) {
							int posLows = tempSSID.indexOf("^");
							if (tempSSID.charAt(posLows + 4) == '^') {
								//Yes we have a lows embedding, now put it into our lows array list, together with the ap data
								//First extract data out of hostname
								String tempString = tempSSID.substring(tempSSID.indexOf("^") + 1, tempSSID.indexOf("^", posLows + 1));
								if (tempString.length() != 3) {
									Log.i(TAG, "ERROR Embedding is not a LoWS Embedding: " + tempString + "posLows: " + Integer.toString(posLows));
								} else {
									String tempHex = asciiToHex(tempString);
									String tempLowsFormatType = tempHex.subSequence(0, 2).toString();
									int tempLowsFormatTypeInt = Integer.parseInt(tempLowsFormatType, 16);
									String tempServiceData = tempHex.subSequence(2, 6).toString();
									//Log.i(TAG, "FOUND LoWS UPDATE!!!!: " + tempString + "posLows: " + Integer.toString(posLows) + "tempHex: " + tempHex);
									Log.i(TAG, "FOUND LoWS UPDATE2!!!: " + "type: " + Integer.toString(tempLowsFormatTypeInt) + "tempServiceData: " + tempServiceData);
									//Log.i(TAG, "FOUND LoWS UPDATE3!!!: " + "type: " + Integer.toString(tempLowsFormatTypeInt)+ "(type: " + Integer.toString(serviceType) + ") tempServiceData: " + tempHex.subSequence(4, 6).toString()+ " (ServiceData: " + serviceData.subSequence(0, 2).toString() + ")");
									if(tempLowsFormatTypeInt == serviceType && tempHex.subSequence(2, 4).toString().equals(serviceData.subSequence(0, 2).toString()))
									{

										Log.i(TAG, "FOUND LoWS UPDATE3!!!: " + "type: " + Integer.toString(tempLowsFormatTypeInt)+ "(type: " + Integer.toString(serviceType) + ") tempServiceData: " + tempServiceData+ " (ServiceData: " + serviceData.subSequence(0, 2).toString() + ")");

											// Codebook format (reduced format)
										    String typeValue = "0x" + tempHex.subSequence(0, 2).toString();
											String hardcodedValue = "0x" + tempHex.subSequence(2, 4).toString();
											String codebookValue = "0x" + tempHex.subSequence(4, 6).toString();
											// Database Stuff
											// Get correct row
											Cursor cursor = getContentResolver().query(
													MyCodeBookContentProvider.CONTENT_URI,
													null,
													"mac LIKE '" + macData + "' AND servicetype LIKE '"
															+ typeValue + "' AND hardcodedvalue LIKE '"
															+ hardcodedValue + "' AND codebookvalue LIKE '"
															+ codebookValue + "'", null, null);

											String dataValue = "Currently no location specific data available (no codebook entry found)";
											//Check if an entry was found
											if (cursor != null) {
												//If an entry was found for the ldc
												if(cursor.getCount()>0)
												{
													cursor.moveToFirst();
													dataValue = cursor.getString(cursor.getColumnIndexOrThrow(CodeBookTable.COLUMN_DATA));
													locationTextView.setText(dataValue);

												}
												//No entry was found for the ldc
												else {
													Log.i(TAG, "No Codebookentry found for update " + "type: " + Integer.toString(tempLowsFormatTypeInt) + "tempServiceData: " + tempServiceData);
													//Start Codebook Updater
													//Intent cbusIntent = new Intent(this, CodeBookUpdaterService.class);
													//Send the codebook updater the mac
													//cbusIntent.putExtra(CodeBookUpdaterService.MAC_IN_MSG, macData);
													//startService(cbusIntent);
												}
											}
											//close the cursor
											cursor.close();
											//End database operations


									}
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

			}
			mainWifiObj.startScan();

		}

		public void onReceive(Context c, Intent intent) {
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

	}




	@Override
	public void onBackPressed() {
		finish();
	}

}
