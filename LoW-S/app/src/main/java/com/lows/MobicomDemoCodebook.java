package com.lows;

/**
 * Created by zehl on 21/08/17.
 */
public class MobicomDemoCodebook extends PreinstalledCodebook {
    /*
    AP1 - 192.168.68.10
    64:66:b3:54:bf:7b

    AP2 - 192.168.68.20
    30:b5:c2:56:66:b2

    AP3 - 192.168.68.30
    14:cc:20:b4:ed:08

    AP4 - 192.168.68.40
    c4:e9:84:7d:c6:f4

    AP5 - 192.168.68.50
    64:66:b3:54:cb:c3

    */

    private static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return "0x"+hex.toString();
    }

    private void autoAddEntry(String code, String displayText)
    {
        String[] accessPoints = {"64:66:b3:54:bf:7b", "30:b5:c2:56:66:b2", "14:cc:20:b4:ed:08", "c4:e9:84:7d:c6:f4", "c4:e9:84:7d:c6:f5", "64:66:b3:54:cb:c3"};
        String first = asciiToHex(Character.toString(code.charAt(0)));
        String second = asciiToHex(Character.toString(code.charAt(1)));
        String third = asciiToHex(Character.toString(code.charAt(2)));
        String text = displayText;
        int size = accessPoints.length;
        for (int i=0; i<size; i++)
        {
            addNewEntry(new PreInstalledCodebookEntry(accessPoints[i], first, second, third, text));
        }
    }

    public MobicomDemoCodebook() {
        //BEPS
        //Code Zero - No Emergency
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        //Code Red - Do not move!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence")); //^!RD^

        //Code Red - Evacuate immediately!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, Call 1 (888) 441-6847 if not working")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, Call 1 (888) 441-6847 if not working")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, Call 1 (888) 441-6847 if not working")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, Call 1 (888) 441-6847 if not working")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, Call 1 (888) 441-6847 if not working")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, Call 1 (888) 441-6847 if not working")); //^?ED^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^

        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^


        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^

        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f5", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^




        //CONFERENCE PROGRAMM
        //
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Mobicom Registration and Breakfast, 07:30 - 08:40

        autoAddEntry("CR1", "Mobicom Registration and Breakfast, 07:30 - 08:40");
        autoAddEntry("C[W", "Welcome & Awards, 08:40 - 09:05");
        autoAddEntry("C]W", "Welcome & Awards, 08:40 - 09:05");
        autoAddEntry("C}W", "Welcome & Awards, 08:40 - 09:05");
        autoAddEntry("CXW", "Welcome & Awards, 08:40 - 09:05");

        autoAddEntry("C[K", "Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");
        autoAddEntry("C]K", "Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");
        autoAddEntry("C}K", "Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");
        autoAddEntry("CK1", "Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");



        autoAddEntry("CS1", "Paper session I: Wireless High Jinks");
        autoAddEntry("Ct1", "WEBee: Physical-Layer Cross-Technology Communication via Emulation\n" +
                "Zhijun Li (University of Minnesota & Harbin Institute of Technology)\n" +
                "Tian He (University of Minnesota)\n");


    }
}
