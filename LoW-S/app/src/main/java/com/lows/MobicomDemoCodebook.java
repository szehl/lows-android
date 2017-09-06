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
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x52", "0x44", "Huge Fire, no possibility to evacuate without risk, DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed and will rescue as soon as possible!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x52", "0x44", "Huge Fire, no possibility to evacuate without risk, DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed and will rescue as soon as possible!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x52", "0x44", "Huge Fire, no possibility to evacuate without risk, DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed and will rescue as soon as possible!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x52", "0x44", "Huge Fire, no possibility to evacuate without risk, DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed and will rescue as soon as possible!")); //^!RD^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x52", "0x44", "Huge Fire, no possibility to evacuate without risk, DO NOT MOVE AND WAIT FOR HELP OR FURTHER INSTRUCTIONS! Emergency Services are informed and will rescue as soon as possible!")); //^!RD^

        //Code Red - Evacuate immediately!
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:bf:7b", "0x21", "0x52", "0x45", "Huge Fire, evacuate immediately using closest door, meet outside at the parking lot! Check for further instructions if you are lost on the way to the outside!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("30:b5:c2:56:66:b2", "0x21", "0x52", "0x45", "Huge Fire, evacuate immediately using closest door, meet outside at the parking lot! Check for further instructions if you are lost on the way to the outside!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("14:cc:20:b4:ed:08", "0x21", "0x52", "0x45", "Huge Fire, evacuate immediately using closest door, meet outside at the parking lot! Check for further instructions if you are lost on the way to the outside!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("c4:e9:84:7d:c6:f4", "0x21", "0x52", "0x45", "Huge Fire, evacuate immediately using closest door, meet outside at the parking lot! Check for further instructions if you are lost on the way to the outside!")); //^!RE^
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:54:cb:c3", "0x21", "0x52", "0x45", "Huge Fire, evacuate immediately using closest door, meet outside at the parking lot! Check for further instructions if you are lost on the way to the outside!")); //^!RE^

        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:ce:21:db", "0x21", "0x52", "0x30", "Please use red door to evacuate!"));
        //PSA
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:ce:21:db", "0x3f", "0x54", "0x30", "Please pee into the office of Mr. Gawlowicz."));
    }
}
