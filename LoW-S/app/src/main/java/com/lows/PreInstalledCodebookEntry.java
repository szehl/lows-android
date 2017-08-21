package com.lows;

/**
 * Created by zehl on 21/08/17.
 */
public class PreInstalledCodebookEntry {
    String apMAC = "";// = "64:66:b3:ce:21:db";
    String serviceType = "";// = "0x21";
    String hardcodeValue = "";// = "0x5a";
    String codebookValue = "";// = "0x30";
    String data = "";// = "Crazy Motherfucker";
    int version = 1;

    public PreInstalledCodebookEntry(String extApMac, String extServiceType, String extHardcodeValue, String extCodebookValue, String extData) {
        apMAC = extApMac;// = "64:66:b3:ce:21:db";
        serviceType = extServiceType;// = "0x21";
        hardcodeValue = extHardcodeValue;// = "0x5a";
        codebookValue = extCodebookValue;// = "0x30";
        data = extData;// = "Crazy Motherfucker";
        version = 2;
    }
}
