package com.lows;

/**
 * Created by zehl on 21/08/17.
 */
public class MobicomDemoCodebook extends PreinstalledCodebook {

    public MobicomDemoCodebook() {
        //BEPS
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:ce:21:db", "0x21", "0x5a", "0x30", "Mobicom BEPS System, No Emergency"));
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:ce:21:db", "0x21", "0x52", "0x30", "Please use red door to evacuate!"));
        //PSA
        addNewEntry(new PreInstalledCodebookEntry("64:66:b3:ce:21:db", "0x3f", "0x54", "0x30", "Please pee into the office of Mr. Gawlowicz."));
    }
}
