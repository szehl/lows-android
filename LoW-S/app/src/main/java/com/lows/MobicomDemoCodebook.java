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

        autoAddEntry("C[C", "Coffee Break");
        autoAddEntry("C]C", "Coffee Break");
        autoAddEntry("C}C", "Coffee Break");
        autoAddEntry("CC1", "Coffee Break 09:55 - 10:20");

        autoAddEntry("C[S", "Paper session I: Wireless High Jinks  10:20 - 11:40");
        autoAddEntry("C]S", "Paper session I: Wireless High Jinks  10:20 - 11:40");
        autoAddEntry("C}S", "Paper session I: Wireless High Jinks  10:20 - 11:40");
        autoAddEntry("CS1", "Paper session I: Wireless High Jinks  10:20 - 11:40");

        autoAddEntry("CT1", "WEBee: Physical-Layer Cross-Technology Communication via Emulation\n" +
                "Zhijun Li (University of Minnesota & Harbin Institute of Technology)\n" +
                "Tian He (University of Minnesota)\n");
        autoAddEntry("CT2", "Stateful Inter-Packet Signal Processing for Wireless Networking\n" +
                "Shangqing Zhao (University of South Florida)\n" +
                "Zhengping Luo (University of South Florida)\n" +
                "Zhuo Lu (University of South Florida)\n" +
                "Xiang Lu (Chinese Academy of Sciences & University of Chinese Academy of Sciences)\n" +
                "Yao Liu (University of South Florida)\n");
        autoAddEntry("CT3", "WWiFi-Assisted 60 GHz Wireless Networks\t\n" +
                "Sanjib Sur (University of Wisconsin-Madison & Hewlett Packard Labs)\n" +
                "Ioannis Pefkianakis (Hewlett Packard Labs)\n" +
                "Xinyu Zhang (University of California, San Diego)\n" +
                "Kyu-Han Kim (Hewlett Packard Labs)");
        autoAddEntry("CT4", "Pose Information Assisted 60 GHz Networks: Towards Seamless Coverage and Mobility Support\n" +
                "Teng Wei (University of Wisconsin - Madison)\n" +
                "Xinyu Zhang (University of California San Diego) ");

        autoAddEntry("C[L", "Lunch");
        autoAddEntry("C]L", "Lunch");
        autoAddEntry("C}L", "Lunch");
        autoAddEntry("CL1", "Lunch 11:40 - 13:10");

        autoAddEntry("C[N", "N2Women Event 12:00 - 13:00");
        autoAddEntry("C]N", "N2Women Event 12:00 - 13:00");
        autoAddEntry("C}N", "N2Women Event 12:00 - 13:00");
        autoAddEntry("CN1", "N2Women Event 12:00 - 13:00");

        autoAddEntry("C[T", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");
        autoAddEntry("C]T", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");
        autoAddEntry("C}T", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");
        autoAddEntry("CS2", "Paper session II: Can You Hear Me Now? 13:10 - 14:30");

        autoAddEntry("CT5", "A Control-Plane Perspective on Reducing Data Access Latency in LTE Networks\n" +
                "Yuanjie Li (University of California, Los Angeles)\n" +
                "Zengwen Yuan (University of California, Los Angeles)\n" +
                "Chunyi Peng (Purdue University)");

        autoAddEntry("CT6", "Experience: An Open Platform for Experimentation with Commercial Mobile Broadband Networks\n" +
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

        autoAddEntry("CT7", "Experience: Automating Diagnosis of Cellular Radio Access Network Problems\n" +
                "Anand Padmanabha Iyer (University of California, Berkeley)\n" +
                "Li Erran Li (Uber Technologies)\n" +
                "Ion Stoica (University of California, Berkeley)\n");

        autoAddEntry("CT8", "Adding the Next Nine: an Investigation of Mobile Broadband Networks Availability\t\n" +
                "Ahmed Elmokashfi (Simula Research Laboratory)\n" +
                "DongZhou (Simula Research Laboratory)\n" +
                "Džiugas Baltrunas (Simula Research Laboratory)");

        autoAddEntry("CC2", "Coffee Break 14:30 - 14:55");

        autoAddEntry("C[U", "Paper session III: Invisible Cobwebs 14:55 - 16:35");
        autoAddEntry("C]U", "Paper session III: Invisible Cobwebs 14:55 - 16:35");
        autoAddEntry("C}U", "Paper session III: Invisible Cobwebs 14:55 - 16:35");
        autoAddEntry("CS3", "Paper session III: Invisible Cobwebs 14:55 - 16:35");

        autoAddEntry("CT9", "The Tick Programmable Low-Latency SDR System\n" +
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

        autoAddEntry("CTA", "BiPass: Enabling End-to-End Full Duplex\t\n" +
                "Lu Chen (Ohio State University)\n" +
                "Fei Wu (Ohio State University)\n" +
                "Jiaqi Xu (Ohio State University)\n" +
                "Kannan Srinivasan (Ohio State University)\n" +
                "Ness Shroff (Ohio State University) ");

        autoAddEntry("CTB", "Orion: RAN Slicing for a Flexible and Cost-Effective Multi-Service Mobile Network Architecture\t\n" +
                "Xenofon Foukas (University of Edinburgh)\n" +
                "Mahesh K. Marina (University of Edinburgh)\n" +
                "Kimon Kontovasilis (NCSR Demokritos)");

        autoAddEntry("CTC", "Accelerating Multipath Transport Through Balanced Subflow Completion\n" +
                "Yihua Ethan Guo (University of Michigan)\n" +
                "Ashkan Nikravesh (University of Michigan)\n" +
                "Z. Morley Mao (University of Michigan)\n" +
                "Feng Qian (Indiana University)\n" +
                "Subhabrata Sen (AT&T Labs – Research)");

        autoAddEntry("CTD", "FSONet: A Wireless Backhaul for Multi-Gigabit Picocells Using Steerable Free Space Optics\n" +
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

    }
}
