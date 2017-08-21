package com.lows;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zehl on 21/08/17.
 */
public class PreinstalledCodebook {
    public List<PreInstalledCodebookEntry> cb = new ArrayList<PreInstalledCodebookEntry>();

    public int addNewEntry(PreInstalledCodebookEntry newEntry)
    {
        cb.add(newEntry);
        return 0;
    }
}
