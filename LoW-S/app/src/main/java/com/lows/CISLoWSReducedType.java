package com.lows;

/**
 * This class implements the Conference Information Service
 * LoWS type via the LoWSReducedType interface class
 * 
 * TODO:
 * 
 * @author Sven Zehl
 *
 */
public class CISLoWSReducedType implements LoWSReducedType{

	@Override
	public int getTypeNumber() {
		// TODO Auto-generated method stub
		return 67; //0x43
	}

	@Override
	public String getDisplayString() {
		// TODO Auto-generated method stub
		return "Mobicom 2017 Information Service";
	}

	@Override
	public String decodeData(String dataInHex) {

		String returnText;
		StringBuilder bepsID = new StringBuilder();
		for(int p = 0; p < dataInHex.length(); p+=2)
		{
			String str = dataInHex.substring(p, p+2);
			bepsID.append((char)Integer.parseInt(str, 16));
		}

		if(bepsID.charAt(0)=='S')
		{
			returnText = "Current session";
		}
		else if(bepsID.charAt(0)=='A')
		{
			returnText = "Awards";
		}
		else if(bepsID.charAt(0)=='C')
		{
			returnText = "Coffee break";
		}
		else if(bepsID.charAt(0)=='D')
		{
			returnText = "Demo Session";
		}
		else if(bepsID.charAt(0)=='L')
		{
			returnText = "Lunch";
		}
		else if(bepsID.charAt(0)=='K')
		{
			returnText = "Keynote Talk";
		}
		else if(bepsID.charAt(0)=='P')
		{
			returnText = "Panel Session";
		}
		else if(bepsID.charAt(0)=='O')
		{
			returnText = "Poster Session";
		}
		else if(bepsID.charAt(0)=='R')
		{
			returnText = "Registration";
		}
		else if(bepsID.charAt(0)=='X')
		{
			returnText = "Current Event";
		}
		else if(bepsID.charAt(0)=='[')
		{
			returnText = "Event starting in 5min";
		}
		else if(bepsID.charAt(0)==']')
		{
			returnText = "Event starting in 3min";
		}
		else if(bepsID.charAt(0)=='}')
		{
			returnText = "Event starting in 1min";
		}
		else if(bepsID.charAt(0)=='t')
		{
			returnText = "Current Talk";
		}
		else
		{
			returnText = "General Conference Information Service Announcement (CIS)";
		}
		return returnText;
	}

	@Override
	public String getIconName() {
		return "cis";
	}

	@Override
	public boolean showAlarmSwitch() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean getAlarmStartState() {
		// TODO Auto-generated method stub
		return false;
	}



	public String[] getBackgroundScannerSearchStrings() {
		//auto generated with python script
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumberOfBackgroundScannerItems()
	{
		return 0;
	}

	@Override
	public String getAlarmClickStandardText(String serviceData) {
		return null;
	}

	@Override
	public boolean showSearchFieldSwitch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSearchFieldDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
