package com.cricketanalyser;

import java.util.Map;

public class IplMostWicketsAdapter extends IplAdapter {
    @Override
    public Map<String, IplDTO> loadIplData(String... csvFilePath) {
       Map<String,IplDTO> stringIplDTOMap=super.loadIplData(IplMostWicketsCSV.class,csvFilePath[0]);
       return stringIplDTOMap;
    }
}
