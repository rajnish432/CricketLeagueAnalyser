package com.cricketanalyser;

import java.util.Map;

public class IplMostRunsAdapter extends IplAdapter {
    @Override
    public Map<String, IplDTO> loadIplData(String... csvFilePath) {
        Map<String,IplDTO> stringIplDTOMap=super.loadIplData(IplMostRunsCSV.class,csvFilePath[0]);
        return stringIplDTOMap;
    }
}
