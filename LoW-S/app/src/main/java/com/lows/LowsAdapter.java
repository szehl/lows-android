package com.lows;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * LoWS ListView Adapter
 * This class is used for the ListView in the ResultListFragment class for displaying the LoWS
 * found in current vicinity
 * 
 * @author Sven Zehl
 *
 */
public class LowsAdapter extends ArrayAdapter<String> {
	  private final Context context;
	  private final String[] values;
	  private final String[] serviceText;
	  private final String[] iconName;
	  private final double[] rssiIndicator;
	  private List<LoWS> lows;

	  public LowsAdapter(Context context, List<LoWS> lows, String[] values, String[] serviceText, String[] iconName, double[] rssiIndicator) {
	    super(context, R.layout.low_list_entry, values);
	    this.lows=lows;
	    this.context = context;
	    this.values = values;
	    this.serviceText = serviceText;
	    this.iconName = iconName;
		  this.rssiIndicator = rssiIndicator;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    View rowView = inflater.inflate(R.layout.low_list_entry, parent, false);
	    TextView textViewTop = (TextView) rowView.findViewById(R.id.top);
	   TextView textViewBottom = (TextView) rowView.findViewById(R.id.bottom);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		  ImageView rssiImg = (ImageView) rowView.findViewById(R.id.rssi);
		  double rssiData = rssiIndicator[position];
 		
	    textViewTop.setText(values[position]);
	    if(serviceText[position].length()>70)
	    {
	    	textViewBottom.setText(serviceText[position].substring(0, 70)+"...");
	    }
	    else
	    {
	    	textViewBottom.setText(serviceText[position]);
	    }
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

	    String s = values[position];;
	    imageView.setImageResource(getImageId(getContext(), iconName[position]));
	    return rowView;
	  }
	  public static int getImageId(Context context, String imageName) {
		    return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
		}
	} 

