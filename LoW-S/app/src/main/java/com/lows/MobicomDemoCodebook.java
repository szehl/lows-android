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

    public MobicomDemoCodebook() {
        //BEPS
        //Code Zero - No Emergency
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency")); //^!Z0^
        //Code Red - Do not move!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x52", "0x44", "DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed about your presence")); //^!RD^

        //Code Red - Evacuate immediately!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x52", "0x45", "EVACUATE IMMEDIATELY using closest door, meet outside at the parking lot!")); //^!RE^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, located between drug-store and kiosk")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, located between drug-store and kiosk")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, located between drug-store and kiosk")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, located between drug-store and kiosk")); //^?ED^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x45", "0x44", "Disabled-access elevator, fully operational, capacity: up to 750 pounds, located between drug-store and kiosk")); //^?ED^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x31", "Line U2 Wittenbergplatz, Departing in 1min")); //^?U1^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x32", "Line U2 Wittenbergplatz, Departing in 2min")); //^?U2^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x33", "Line U2 Wittenbergplatz, Departing in 3min")); //^?U3^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x34", "Line U2 Wittenbergplatz, Departing in 4min")); //^?U4^

        //Elevator!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x35", "Line U2 Wittenbergplatz, Departing in 5min")); //^?U5^

        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x55", "0x30", "Line U2 Wittenbergplatz, Now Departing!")); //^?U5^


        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x73", "0x57", "Walmart, Coupon! Take a screenshot and get a 10 percent discount voucher for your Walmart purchase! Only today!")); //^?sW^

        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x3f", "0x46", "0x4d", "McDonalds!, show your screen and get one cheesburger for free if you buy a McDonald's Menu")); //^?FM^

    }
}
