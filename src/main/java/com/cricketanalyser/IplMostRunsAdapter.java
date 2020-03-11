package com.cricketanalyser;

import java.util.HashMap;
import java.util.Map;

public class IplMostRunsAdapter extends IplAdapter {
    Map<String,IplDTO> iplDTOMap=new HashMap<>();
    @Override
    public Map<String, IplDTO> loadIplData(String... csvFilePath) {
        iplDTOMap=super.loadIplData(IplMostRunsCSV.class,csvFilePath[0]);
        return iplDTOMap;
    }
}
