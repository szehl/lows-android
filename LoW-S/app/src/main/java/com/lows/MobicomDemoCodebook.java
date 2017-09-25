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

        autoAddEntry("C[K", "Keynote Talk 1: Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");
        autoAddEntry("C]K", "Keynote Talk 1: Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");
        autoAddEntry("C}K", "Keynote Talk 1: Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");
        autoAddEntry("CK1", "Keynote Talk 1: Making Roads Safer by Making Drivers Better\n" +
                "Hari Balakrishnan (M.I.T. and Cambridge Mobile Telematics) ,  09:05 - 09:55");

        autoAddEntry("C[C", "Coffee Break");
        autoAddEntry("C]C", "Coffee Break");
        autoAddEntry("C}C", "Coffee Break");
        autoAddEntry("CC1", "Coffee Break 09:55 - 10:20");

        autoAddEntry("C[S", "Paper session I: Wireless High Jinks  10:20 - 11:40");
        autoAddEntry("C]S", "Paper session I: Wireless High Jinks  10:20 - 11:40");
        autoAddEntry("C}S", "Paper session I: Wireless High Jinks  10:20 - 11:40");
        autoAddEntry("CS1", "Paper session I: Wireless High Jinks  10:20 - 11:40");

        autoAddEntry("Ct1", "WEBee: Physical-Layer Cross-Technology Communication via Emulation\n" +
                "Zhijun Li (University of Minnesota & Harbin Institute of Technology)\n" +
                "Tian He (University of Minnesota)");
        autoAddEntry("Ct2", "Stateful Inter-Packet Signal Processing for Wireless Networking\n" +
                "Shangqing Zhao (University of South Florida)\n" +
                "Zhengping Luo (University of South Florida)\n" +
                "Zhuo Lu (University of South Florida)\n" +
                "Xiang Lu (Chinese Academy of Sciences & University of Chinese Academy of Sciences)\n" +
                "Yao Liu (University of South Florida)");
        autoAddEntry("Ct3", "WiFi-Assisted 60 GHz Wireless Networks\t\n" +
                "Sanjib Sur (University of Wisconsin-Madison & Hewlett Packard Labs)\n" +
                "Ioannis Pefkianakis (Hewlett Packard Labs)\n" +
                "Xinyu Zhang (University of California, San Diego)\n" +
                "Kyu-Han Kim (Hewlett Packard Labs)");
        autoAddEntry("Ct4", "Pose Information Assisted 60 GHz Networks: Towards Seamless Coverage and Mobility Support\n" +
                "Teng Wei (University of Wisconsin - Madison)\n" +
                "Xinyu Zhang (University of California San Diego) ");

        autoAddEntry("C[L", "Lunch");
        autoAddEntry("C]L", "Lunch");
        autoAddEntry("C}L", "Lunch");
        autoAddEntry("CL1", "Lunch 11:40 - 13:10");

        autoAddEntry("C[N", "N2Women Event 12:00 - 13:00");
        autoAddEntry("C]N", "N2Women Event 12:00 - 13:00");
        autoAddEntry("C}N", "N2Women Event 12:00 - 13:00");
        autoAddEntry("CXN", "N2Women Event 12:00 - 13:00 / Lunch 11:40 - 13:10");

        autoAddEntry("C[T", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");
        autoAddEntry("C]T", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");
        autoAddEntry("C}T", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");
        autoAddEntry("CS2", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");

        autoAddEntry("Ct5", "A Control-Plane Perspective on Reducing Data Access Latency in LTE Networks\n" +
                "Yuanjie Li (University of California, Los Angeles)\n" +
                "Zengwen Yuan (University of California, Los Angeles)\n" +
                "Chunyi Peng (Purdue University)");

        autoAddEntry("Ct6", "Experience: An Open Platform for Experimentation with Commercial Mobile Broadband Networks\n" +
                "Özgü Alay (Simula Research Laboratory)\n" +
                "Andra Lutu (Simula Research Laboratory)\n" +
                "Miguel Peón-Quirós (IMDEA Networks Institute)\n" +
                "Vincenzo Mancuso (IMDEA Networks Institute)\n" +
                "Thomas Hirsch (Celerway Communications)\n" +
                "Kristian Evensen (Celerway Communications)\n" +
                "Audun Hansen (Celerway Communications)\n" +
                "Stefan Alfredsson (Karlstad University)\n" +
                "Jonas Karlsson (Karlstad University)\n" +
                "Anna Brunstrom (Karlstad University)\n" +
                "Ali Safari Khatouni (Politecnico di Torino)\n" +
                "Marco Mellia (Politecnico di Torino)\n" +
                "Marco Ajmone Marsan (IMDEA Networks Institute & Politecnico di Torino)\n");

        autoAddEntry("Ct7", "Experience: Automating Diagnosis of Cellular Radio Access Network Problems\n" +
                "Anand Padmanabha Iyer (University of California, Berkeley)\n" +
                "Li Erran Li (Uber Technologies)\n" +
                "Ion Stoica (University of California, Berkeley)\n");

        autoAddEntry("Ct8", "Adding the Next Nine: an Investigation of Mobile Broadband Networks Availability\t\n" +
                "Ahmed Elmokashfi (Simula Research Laboratory)\n" +
                "DongZhou (Simula Research Laboratory)\n" +
                "Džiugas Baltrunas (Simula Research Laboratory)");

        autoAddEntry("CC2", "Coffee Break 14:30 - 14:55");

        autoAddEntry("C[U", "Paper session III: Invisible Cobwebs 14:55 - 16:35");
        autoAddEntry("C]U", "Paper session III: Invisible Cobwebs 14:55 - 16:35");
        autoAddEntry("C}U", "Paper session III: Invisible Cobwebs 14:55 - 16:35");
        autoAddEntry("CS3", "Paper session III: Invisible Cobwebs 14:55 - 16:35");

        autoAddEntry("Ct9", "The Tick Programmable Low-Latency SDR System\n" +
                "Haoyang Wu (Peking University)\n" +
                "Tao Wang (Peking University)\n" +
                "Zengwen Yuan (University of California, Los Angeles)\n" +
                "Chunyi Peng (Purdue University)\n" +
                "Zhiwei Li (Peking University)\n" +
                "Zhaowei Tan (University of California, Los Angeles)\n" +
                "Boyan Ding (Peking University)\n" +
                "Xiaoguang Li (Peking University)\n" +
                "Yuanjie Li (University of California, Los Angeles)\n" +
                "Jun Liu (Peking University)\n" +
                "Songwu Lu (University of California, Los Angeles)");

        autoAddEntry("CtA", "BiPass: Enabling End-to-End Full Duplex\t\n" +
                "Lu Chen (Ohio State University)\n" +
                "Fei Wu (Ohio State University)\n" +
                "Jiaqi Xu (Ohio State University)\n" +
                "Kannan Srinivasan (Ohio State University)\n" +
                "Ness Shroff (Ohio State University) ");

        autoAddEntry("CtB", "Orion: RAN Slicing for a Flexible and Cost-Effective Multi-Service Mobile Network Architecture\t\n" +
                "Xenofon Foukas (University of Edinburgh)\n" +
                "Mahesh K. Marina (University of Edinburgh)\n" +
                "Kimon Kontovasilis (NCSR Demokritos)");

        autoAddEntry("CtC", "Accelerating Multipath Transport Through Balanced Subflow Completion\n" +
                "Yihua Ethan Guo (University of Michigan)\n" +
                "Ashkan Nikravesh (University of Michigan)\n" +
                "Z. Morley Mao (University of Michigan)\n" +
                "Feng Qian (Indiana University)\n" +
                "Subhabrata Sen (AT&T Labs – Research)");

        autoAddEntry("CtD", "FSONet: A Wireless Backhaul for Multi-Gigabit Picocells Using Steerable Free Space Optics\n" +
                "Max Curran (Stony Brook University)\n" +
                "Md. Shaifur Rahman (Stony Brook University)\n" +
                "Himanshu Gupta (Stony Brook University)\n" +
                "Kai Zheng (Stony Brook University)\n" +
                "Jon Longtin (Stony Brook University)\n" +
                "Samir R Das (Stony Brook University)\n" +
                "Thanvir Mohamed (Stony Brook University)");

        autoAddEntry("C[A", "App Contest 16:35-17:35");
        autoAddEntry("C]A", "App Contest 16:35-17:35");
        autoAddEntry("C}A", "App Contest 16:35-17:35");
        autoAddEntry("CX2", "App Contest 16:35-17:35");

        autoAddEntry("C[D", "Demo/poster session and Student Research Competition (SRC) with reception 17:35 - 19:15");
        autoAddEntry("C]D", "Demo/poster session and Student Research Competition (SRC) with reception 17:35 - 19:15");
        autoAddEntry("C}D", "Demo/poster session and Student Research Competition (SRC) with reception 17:35 - 19:15");
        autoAddEntry("CD1", "Demo/poster session and Student Research Competition (SRC) with reception 17:35 - 19:15");

        autoAddEntry("C[J", "MobiJob dinner 19:20 - 21:20");
        autoAddEntry("C]J", "MobiJob dinner 19:20 - 21:20");
        autoAddEntry("C}J", "MobiJob dinner 19:20 - 21:20");
        autoAddEntry("CX3", "MobiJob dinner 19:20 - 21:20");



        ///Zweiter Tag 18.10.17

        autoAddEntry("CR2", "Mobicom Registration and Breakfast, 07:30 - 08:45");

        autoAddEntry("C[V", "Paper session IV: Aurora Borealis 08:45 - 09:45");
        autoAddEntry("C]V", "Paper session IV: Aurora Borealis 08:45 - 09:45");
        autoAddEntry("C}V", "Paper session IV: Aurora Borealis 08:45 - 09:45");
        autoAddEntry("CS4", "Paper session IV: Aurora Borealis 08:45 - 09:45");

        autoAddEntry("CtE", "MagneComm: Magnetometer-based Near-Field Communication\n" +
                "Hao Pan (Shanghai Jiao Tong University)\n" +
                "Yi-Chao Chen (Shanghai Jiao Tong University)\n" +
                "Guangtao Xue (Shanghai Jiao Tong University)\n" +
                "Xiaoyu Ji (Zhejiang University) ");

        autoAddEntry("CtF", "PassiveVLC: Enabling Practical Visible Light Backscatter Communication for Battery-free IoT Applications\t\n" +
                "Xieyang Xu (Peking University)\n" +
                "Yang Shen (Peking University)\n" +
                "Junrui Yang (Stanford University)\n" +
                "Chenren Xu (Peking University)\n" +
                "Guobin Shen (Zepp Labs, Inc.)\n" +
                "Guojun Chen (Peking University)\n" +
                "Yunzhe Ni (Peking University) ");

        autoAddEntry("CtG", "ReflexCode: Coding with Superposed Reflection Light for LED-Camera Communication\n" +
                "Yanbing Yang (Nanyang Technological University)\n" +
                "Jiangtian Nie (Nanyang Technological University)\n" +
                "Jun Luo (Nanyang Technological University) ");

        autoAddEntry("C[R", "Rock star presentation:\n" +
                "Shyam Gollakota (University of Washington): 09:45 - 10:35");
        autoAddEntry("C]R", "Rock star presentation:\n" +
                "Shyam Gollakota (University of Washington): 09:45 - 10:35");
        autoAddEntry("C}R", "Rock star presentation:\n" +
                "Shyam Gollakota (University of Washington): 09:45 - 10:35");
        autoAddEntry("CX4", "Rock star presentation:\n" +
                "Shyam Gollakota (University of Washington): 09:45 - 10:35");

        autoAddEntry("CC3", "Coffee Break 10:35 - 11:00");

        autoAddEntry("C[Z", "Second round of SRC  (65 min) 11:00 - 12:05");
        autoAddEntry("C]Z", "Second round of SRC  (65 min) 11:00 - 12:05");
        autoAddEntry("C}Z", "Second round of SRC  (65 min) 11:00 - 12:05");
        autoAddEntry("CX5", "Second round of SRC  (65 min) 11:00 - 12:05");

        autoAddEntry("CL2", "Lunch 12:05 - 13:35");

        autoAddEntry("C[a", "Paper session V: Location! Location! Location!  13:35 - 14:55");
        autoAddEntry("C]a", "Paper session V: Location! Location! Location!  13:35 - 14:55");
        autoAddEntry("C}a", "Paper session V: Location! Location! Location!  13:35 - 14:55");
        autoAddEntry("CS5", "Paper session V: Location! Location! Location!  13:35 - 14:55");

        autoAddEntry("CtH", "Pulsar: Towards Ubiquitous Visible Light Localization\n" +
                "Chi Zhang (University of Wisconsin-Madison)\n" +
                "Xinyu Zhang (University of California San Diego) ");

        autoAddEntry("CtI", "RF-Echo: A Non-Line-of-Sight Indoor Localization System Using a Low-Power Active RF Reflector ASIC Tag\n" +
                "Li-Xuan Chuo (University of Michigan)\n" +
                "Zhihong Luo (University of Michigan)\n" +
                "Dennis Sylvester (University of Michigan)\n" +
                "David Blaauw (University of Michigan)\n" +
                "Hun-Seok Kim (University of Michigan) ");

        autoAddEntry("CtJ", "Simultaneous Power-Based Localization of Transmitters for Crowdsourced Spectrum Monitoring\n" +
                "Mojgan Khaledi (University of Utah)\n" +
                "Mehrdad Khaledi (Rensselaer Polytechnic Institute)\n" +
                "Shamik Sarkar (University of Utah)\n" +
                "Sneha Kasera (University of Utah)\n" +
                "Neal Patwari (University of Utah)\n" +
                "Kurt Derr (Idaho National Labs)\n" +
                "Samuel Ramirez (Idaho National Labs) ");

        autoAddEntry("CtK", "Minding the Billions: Ultra-wideband Localization for Deployed RFIDs\n" +
                "Yunfei Ma (Massachusetts Institute of Technology)\n" +
                "Nicholas Selby (Massachusetts Institute of Technology)\n" +
                "Fadel Adib (Massachusetts Institute of Technology) ");

        autoAddEntry("CC4", "Coffee Break 14:55 - 15:15");

        autoAddEntry("C[b", "Paper session VI: Tag, You’re It! 15:15 - 16:35");
        autoAddEntry("C]b", "Paper session VI: Tag, You’re It! 15:15 - 16:35");
        autoAddEntry("C}b", "Paper session VI: Tag, You’re It! 15:15 - 16:35");
        autoAddEntry("CS6", "Paper session VI: Tag, You’re It! 15:15 - 16:35");

        autoAddEntry("CtL", "RIO: A Pervasive RFID-based Touch Gesture Interface\n" +
                "Swadhin Pradhan (University of Texas at Austin)\n" +
                "Eugene Chai (NEC Laboratories America)\n" +
                "Karthikeyan Sundaresan (NEC Laboratories America)\n" +
                "Lili Qiu (University of Texas at Austin)\n" +
                "Mohammad A. Khojastepour (NEC Laboratories America)\n" +
                "Sampath Rangarajan (NEC Laboratories America) ");

        autoAddEntry("CtM", "FlipTracer: Practical Parallel Decoding for Backscatter Communication\t\n" +
                "Meng Jin (Northwest University)\n" +
                "Yuan He (Tsinghua University)\n" +
                "Xin Meng (Northwest University)\n" +
                "Yilun Zheng (Tsinghua University)\n" +
                "Dingyi Fang (Northwest University)\n" +
                "Xiaojiang Chen (Northwest University) ");

        autoAddEntry("CtN", "TagScan: Simultaneous Target Imaging and Material Identification with Commodity RFID Devices\n" +
                "Ju Wang (Northwest University)\n" +
                "Jie Xiong (Singapore Management University)\n" +
                "Xiaojiang Chen (Northwest University)\n" +
                "Hongbo Jiang (Huazhong University of Science and Technology)\n" +
                "Rajesh Krishna Balan (Singapore Management University)\n" +
                "Dingyi Fang (Northwest University)\n");

        autoAddEntry("CtO", "Analog On-Tag Hashing: Towards Selective Reading as Hash Primitives in Gen2 RFID Systems\n" +
                "Lei Yang (Hong Kong Polytechnic University)\n" +
                "Qiongzheng Lin (Hong Kong Polytechnic University)\n" +
                "Chunhui Duan (Tsinghua University & Hong Kong Polytechnic University)\n" +
                "Zhenlin An (Hong Kong Polytechnic University) ");

        autoAddEntry("C[c", "Tram rides to Hidden Peak 16:45 - 18:00");
        autoAddEntry("C]c", "Tram rides to Hidden Peak 16:45 - 18:00");
        autoAddEntry("C}c", "Tram rides to Hidden Peak 16:45 - 18:00");
        autoAddEntry("CX6", "Tram rides to Hidden Peak 16:45 - 18:00");
        autoAddEntry("CX7", "Banquet 18:00 - 21:00");


        //Day 3

        autoAddEntry("C[d", "SIGMobile business meeting 08:45 - 09:45");
        autoAddEntry("C]d", "SIGMobile business meeting 08:45 - 09:45");
        autoAddEntry("C}d", "SIGMobile business meeting 08:45 - 09:45");
        autoAddEntry("CX8", "SIGMobile business meeting 08:45 - 09:45");

        autoAddEntry("C[e", "Paper session VII: Leaks, Plugs, Alice and Bob 09:45 - 11:05");
        autoAddEntry("C]e", "Paper session VII: Leaks, Plugs, Alice and Bob 09:45 - 11:05");
        autoAddEntry("C}e", "Paper session VII: Leaks, Plugs, Alice and Bob 09:45 - 11:05");
        autoAddEntry("CS7", "Paper session VII: Leaks, Plugs, Alice and Bob 09:45 - 11:05");

        autoAddEntry("CtP", "Cardiac Scan: A Non-contact and Continuous Heart-based User Authentication System\n" +
                "Feng Lin (University at Buffalo (SUNY))\n" +
                "Chen Song (University at Buffalo (SUNY))\n" +
                "Yan Zhuang (University at Buffalo (SUNY))\n" +
                "Wenyao Xu (University at Buffalo (SUNY))\n" +
                "Changzhi Li (Texas Tech University)\n" +
                "Kui Ren (University at Buffalo (SUNY))");

        autoAddEntry("CtQ", "Automating Visual Privacy Protection Using a Smart LED\t\n" +
                "Shilin Zhu (University of California-San Diego)\n" +
                "Chi Zhang (University of Wisconsin-Madison)\n" +
                "Xinyu Zhang (University of California-San Diego)");

        autoAddEntry("CtR", "Continuous Authentication for Voice Assistants\n" +
                "Huan Feng (University of Michigan)\n" +
                "Kassem Fawaz (University of Michigan)\n" +
                "Kang G. Shin (University of Michigan) ");

        autoAddEntry("CtS", "NICScatter: Backscatter as a Covert Channel in Mobile Devices\n" +
                "Zhice Yang (ShanghaiTech University & Hong Kong University of Science and Technology)\n" +
                "Qianyi Huang (Hong Kong University of Science and Technology)\n" +
                "Qian Zhang (Hong Kong University of Science and Technology) ");

        autoAddEntry("CC5", "Coffee Break 11:05 - 11:30");

        autoAddEntry("C[B", "Keynote Talk 2: Spurring Mobile Systems Research Into The Next Decade\n" +
                "Thyagarajan Nandagopal (National Science Foundation) 11:30 - 12:20");
        autoAddEntry("C]B", "MKeynote Talk 2: Spurring Mobile Systems Research Into The Next Decade\n" +
                "Thyagarajan Nandagopal (National Science Foundation) 11:30 - 12:20");
        autoAddEntry("C}B", "Keynote Talk 2: Spurring Mobile Systems Research Into The Next Decade\n" +
                "Thyagarajan Nandagopal (National Science Foundation) 11:30 - 12:20");
        autoAddEntry("CK2", "Keynote Talk 2: Spurring Mobile Systems Research Into The Next Decade\n" +
                "Thyagarajan Nandagopal (National Science Foundation) 11:30 - 12:20");

        autoAddEntry("CL3", "Lunch  12:20 - 13:50");

        autoAddEntry("C[f", "Paper session VIII: Frameworks and Such 13:50 - 15:10");
        autoAddEntry("C]f", "Paper session VIII: Frameworks and Such 13:50 - 15:10");
        autoAddEntry("C}f", "Paper session VIII: Frameworks and Such 13:50 - 15:10");
        autoAddEntry("CS8", "Paper session VIII: Frameworks and Such 13:50 - 15:10");

        autoAddEntry("CtT", "UIWear: Easily Adapting User Interfaces for Wearable Devices\n" +
                "Jian Xu (Stony Brook University)\n" +
                "Qingqing Cao (Stony Brook University)\n" +
                "Aditya Prakash (Stony Brook University)\n" +
                "Aruna Balasubramanian (Stony Brook University)\n" +
                "Donald E. Porter (University of North Carolina at Chapel Hill) ");

        autoAddEntry("CtU", "TinyLink: A Holistic System for Rapid Development of IoT Applications\t\n" +
                "Gaoyang Guan (Zhejiang University)\n" +
                "Wei Dong (Zhejiang University)\n" +
                "Yi Gao (Zhejiang University)\n" +
                "Kaibo Fu (Zhejiang University)\n" +
                "Zhihao Cheng (Zhejiang University) ");

        autoAddEntry("CtV", "BlueMountain: An Architecture for Customized Data Management on Mobile Systems\t\n" +
                "Sharath Chandrashekhara (University at Buffalo, The State University of New York)\n" +
                "Taeyeon Ki (University at Buffalo, The State University of New York)\n" +
                "Kyungho Jeon (University at Buffalo, The State University of New York)\n" +
                "Karthik Dantu (University at Buffalo, The State University of New York)\n" +
                "Steven Y. Ko (University at Buffalo, The State University of New York)\n");

        autoAddEntry("CtW", "Furion: Engineering High-Quality Immersive Virtual Reality on Today's Mobile Devices\n" +
                "Zeqi Lai (Tsinghua University)\n" +
                "Y. Charlie Hu (Purdue University)\n" +
                "Yong Cui (Tsinghua University)\n" +
                "Linhui Sun (Tsinghua University)\n" +
                "Ningwei Dai (Tsinghua University) ");

        autoAddEntry("C[g", "Panel session\n" + "What everyone ought to know about the Macro Trends in Wireless Research and Experimentation\n" +"Organizer: Abhimanyu Gosain (PAWR Project Office) 15:10 - 16:10");
        autoAddEntry("C]g", "Panel session\n" + "What everyone ought to know about the Macro Trends in Wireless Research and Experimentation\n" + "Organizer: Abhimanyu Gosain (PAWR Project Office) 15:10 - 16:10");
        autoAddEntry("C}g", "Panel session\n" + "What everyone ought to know about the Macro Trends in Wireless Research and Experimentation\n" + "Organizer: Abhimanyu Gosain (PAWR Project Office) 15:10 - 16:10");
        autoAddEntry("CP1", "Panel session\n" + "What everyone ought to know about the Macro Trends in Wireless Research and Experimentation\n" + "Organizer: Abhimanyu Gosain (PAWR Project Office) 15:10 - 16:10");

        autoAddEntry("CC6", "Coffee Break 16:10 - 16:35");

        autoAddEntry("C[h", "Paper session IX: Better, Faster Apps and Web  16:35 - 17:35");
        autoAddEntry("C]h", "Paper session IX: Better, Faster Apps and Web  16:35 - 17:35");
        autoAddEntry("C}h", "Paper session IX: Better, Faster Apps and Web  16:35 - 17:35");
        autoAddEntry("CS9", "Paper session IX: Better, Faster Apps and Web  16:35 - 17:35");

        autoAddEntry("CtX", "RAVEN : Perception-aware Optimization of Power Consumption for Mobile Games\n" +
                "Chanyou Hwang (Korea Advanced Institute of Science and Technology)\n" +
                "Saumay Pushp (Korea Advanced Institute of Science and Technology)\n" +
                "Changyoung Koh (Korea Advanced Institute of Science and Technology)\n" +
                "Jungpil Yoon (Korea Advanced Institute of Science and Technology)\n" +
                "Yunxin Liu (Microsoft Research)\n" +
                "Seungpyo Choi (Korea Advanced Institute of Science and Technology)\n" +
                "Junehwa Song (Korea Advanced Institute of Science and Technology) ");

        autoAddEntry("CtY", "Advertising-based Measurement: A Platform of 7 Billion Mobile Devices\n" +
                "Mark D Corner (University of Massachusetts Amherst)\n" +
                "Brian N Levine (University of Massachusetts Amherst)\n" +
                "Omar Ismail (University of Massachusetts Amherst)\n" +
                "Angela Upreti (University of Massachusetts Amherst) ");

        autoAddEntry("CtZ", "NutShell: Scalable Whittled Proxy Execution for Low-Latency Web over Cellular Networks\n" +
                "Ashiwan Sivakumar (Purdue University)\n" +
                "Chuan Jiang (Purdue University)\n" +
                "Yun Seong Nam (Purdue University)\n" +
                "Shankaranarayanan Puzhavakath Narayanan (AT&T Labs - Research)\n" +
                "Vijay Gopalakrishnan (AT&T Labs - Research)\n" +
                "Sanjay G. Rao (Purdue University)\n" +
                "Subhabrata Sen (AT&T Labs - Research)\n" +
                "Mithuna Thottethodi (Purdue University)\n" +
                "T.N. Vijaykumar (Purdue University)");

        autoAddEntry("C[i", "Closing remarks 17:35 - 17:40");
        autoAddEntry("C]i", "Closing remarks 17:35 - 17:40");
        autoAddEntry("C}i", "Closing remarks 17:35 - 17:40");
        autoAddEntry("CX9", "Closing remarks 17:35 - 17:40");

    }
}
