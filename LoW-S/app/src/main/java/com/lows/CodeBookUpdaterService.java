package com.lows;




import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lows.contentprovider.MyCodeBookContentProvider;
import com.lows.database.CodeBookTable;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * This is the codebook update service
 * 
 * TODO: -Make the global codebook address server IP address (CODEBOOK_ADDRESS_SERVER) adjustable 
 * 		 via the settings menu.
 * 
 * @author Sven Zehl
 *
 */
public class CodeBookUpdaterService extends IntentService {

	private static final String TAG = "com.lows.CodeBookUpdaterService";
	public static final String MAC_IN_MSG = "mac";
	//IP address of the global codebook address server
	public static final String CODEBOOK_ADDRESS_SERVER = "http://ns.tkn.tu-berlin.de/gcbas/address_handler.php";
	Handler mHandler;
	String toastString;

	
	@Override
	public void onCreate() {
	    super.onCreate();
	    //Handler is only needed to send toast messages to the user
	    mHandler = new Handler();
	}
	
	
	@Override
	protected void onHandleIntent(Intent intent) {		
		mHandler.post(new Runnable() {            
	        @Override
	        public void run() {
	            Toast.makeText(CodeBookUpdaterService.this, "Codebook Updater Service started", Toast.LENGTH_LONG).show();                
	        }
	    });
		
		Log.i(TAG, "Intent Service started");
		//Get the MAC to search for
		String mac = intent.getStringExtra(MAC_IN_MSG);
		Log.i(TAG, "MAC received: "+mac);

		String result = "";
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("mac",mac));
		InputStream is=null;
		//http post
		toastString = "Contacting Codebook Address Server "+CODEBOOK_ADDRESS_SERVER + "quering for codebook server address for: "+mac;
		mHandler.post(new Runnable() {            
	        @Override
	        public void run() {
	            Toast.makeText(CodeBookUpdaterService.this, toastString, Toast.LENGTH_LONG).show();                
	        }
	    });
		//Contact global codebook address server
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost(CODEBOOK_ADDRESS_SERVER);
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		}catch(Exception e){
				
		        Log.e(TAG, "Error in http connection "+e.toString());
		}
		//convert response to string
		try{
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        StringBuilder sb = new StringBuilder();
		        String line = null;
		        while ((line = reader.readLine()) != null) {
		                sb.append(line + "\n");
		        }
		        is.close();
		 
		        result=sb.toString();
		}catch(Exception e){
		        Log.e(TAG, "Error converting result "+e.toString());
		}
		String codebookServer = null;
		//parse json data
		try{
		        JSONArray jArray = new JSONArray(result);
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                Log.i("log_tag","mac: "+json_data.getString("mac")+
		                        ", ip: "+json_data.getString("ip")
		                );
		                codebookServer = json_data.getString("ip");
		                
		        }
		}
		catch(JSONException e){
		        Log.e(TAG, "Error parsing data "+e.toString());
		}
		
		if(codebookServer==null)
		{
			
			toastString ="No codebook server found for mac" +mac;
			mHandler.post(new Runnable() {            
		        @Override
		        public void run() {
		            Toast.makeText(CodeBookUpdaterService.this, toastString, Toast.LENGTH_LONG).show();                
		        }
		    });
			Log.e(TAG, "Error, no Codebook Server found for MAC address");
			return;
		}
		toastString ="Codebook Address Server returned address: "+codebookServer;
		mHandler.post(new Runnable() {            
	        @Override
	        public void run() {
	            Toast.makeText(CodeBookUpdaterService.this, toastString, Toast.LENGTH_LONG).show();                
	        }
	    });
		result = "";

		is = null;
		//http post
		toastString = "Contacting "+codebookServer;
		mHandler.post(new Runnable() {            
	        @Override
	        public void run() {
	            Toast.makeText(CodeBookUpdaterService.this, toastString, Toast.LENGTH_LONG).show();                
	        }
	    });
		//Contact local Codebook Server
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://"+codebookServer+"/codebookserver/cb-request-handler.php");
		        HttpResponse response = httpclient.execute(httppost);
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		}catch(Exception e){
				
		        Log.e(TAG, "Error in http connection "+e.toString());
		}
		//convert response to string
		try{
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        StringBuilder sb = new StringBuilder();
		        String line = null;
		        while ((line = reader.readLine()) != null) {
		                sb.append(line + "\n");
		        }
		        is.close();
		 
		        result=sb.toString();
		}catch(Exception e){
		        Log.e(TAG, "Error converting result "+e.toString());
		}
		 
		//parse json data
		try{
		        JSONArray jArray = new JSONArray(result);
		        toastString = "Received Codebook with size: "+jArray.length();
		        mHandler.post(new Runnable() {            
			        @Override
			        public void run() {
			            Toast.makeText(CodeBookUpdaterService.this, toastString, Toast.LENGTH_LONG).show();                
			        }
			    });
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                Log.i("log_tag","id: "+json_data.getInt("id")+
		                        ", mac: "+json_data.getString("access_point_mac")+
		                        ", service_type: "+json_data.getString("service_hexcode")+
		                        ", hardcoded_value: "+json_data.getString("lic")+
		                        ", codebook_value: "+json_data.getString("ldc")+
		                        ", data: "+json_data.getString("data")+
		                        ", version: "+json_data.getInt("version")
		                );
		                String tempMac = json_data.getString("access_point_mac");
		                String tempServiceType = json_data.getString("service_hexcode");
		                String tempHardcodeValue = json_data.getString("lic");
		                String tempCodebookValue = json_data.getString("ldc");
		                String tempData = json_data.getString("data");
		                int tempVersion = json_data.getInt("version");
		                Calendar c = Calendar.getInstance();
		                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		                String currentDate = sdf.format(c.getTime());
		                
		                Cursor cursor = getContentResolver().query(
		    					MyCodeBookContentProvider.CONTENT_URI,
		    					null,
		    					"mac LIKE '" + tempMac + "' AND servicetype LIKE '"
		    							+ tempServiceType + "' AND hardcodedvalue LIKE '"
		    							+ tempHardcodeValue + "' AND codebookvalue LIKE '"
		    							+ tempCodebookValue + "'", null, null);
		    			if (cursor != null) {
		    				
		    				if (cursor.getCount() > 0) {
		    					//update or do nothing
		    					cursor.moveToFirst();
		    					//String dataValue = cursor.getString(cursor.getColumnIndexOrThrow(CodeBookTable.COLUMN_DATA));
		    					int versionCompare = cursor.getInt(cursor.getColumnIndexOrThrow(CodeBookTable.COLUMN_VERSION));
		    					if(tempVersion==versionCompare)
		    					{
		    						//do nothing the data is still the same
		    					}
		    					else
		    					{
		    						//update this entry, the data has changed
		    						ContentValues values = new ContentValues();
			    					values.put(CodeBookTable.COLUMN_MAC, tempMac);
			    					values.put(CodeBookTable.COLUMN_SERVICE_TYPE, tempServiceType);
			    					values.put(CodeBookTable.COLUMN_HARDCODED_VALUE, tempHardcodeValue);
			    					values.put(CodeBookTable.COLUMN_CODEBOOK_VALUE, tempCodebookValue);
			    					values.put(CodeBookTable.COLUMN_DATA,tempData);
			    					values.put(CodeBookTable.COLUMN_LASTCHANGED, currentDate);
			    					values.put(CodeBookTable.COLUMN_VERSION, tempVersion);
			    					getContentResolver().update(MyCodeBookContentProvider.CONTENT_URI, values,
			    							"mac LIKE '" + tempMac + "' AND servicetype LIKE '"
					    							+ tempServiceType + "' AND hardcodedvalue LIKE '"
					    							+ tempHardcodeValue + "' AND codebookvalue LIKE '"
					    							+ tempCodebookValue + "'", null);		
		    					}
		    				} else {
		    					//add new entry
		    					ContentValues values = new ContentValues();
		    					values.put(CodeBookTable.COLUMN_MAC, tempMac);
		    					values.put(CodeBookTable.COLUMN_SERVICE_TYPE, tempServiceType);
		    					values.put(CodeBookTable.COLUMN_HARDCODED_VALUE, tempHardcodeValue);
		    					values.put(CodeBookTable.COLUMN_CODEBOOK_VALUE, tempCodebookValue);
		    					values.put(CodeBookTable.COLUMN_DATA,tempData);
		    					values.put(CodeBookTable.COLUMN_LASTCHANGED, currentDate);
		    					values.put(CodeBookTable.COLUMN_VERSION, tempVersion);
		    					Uri savedUri = getContentResolver().insert(MyCodeBookContentProvider.CONTENT_URI, values);		
		    				}
		    			} // always close the cursor

		    			cursor.close();
		                
		                
		        }
		}
		catch(JSONException e){
		        Log.e(TAG, "Error parsing data "+e.toString());
		}
		
		
		
		
		

	}

	public CodeBookUpdaterService() {
	 	   super("CodeBookUpdaterService");
	}
	


	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
	
}