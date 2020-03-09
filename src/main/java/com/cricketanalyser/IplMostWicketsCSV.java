package com.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplMostWicketsCSV {
    @CsvBindByName(column ="Avg",required = true)
    public double average;
    @CsvBindByName(column ="PLAYER",required = true)
    public String playerName;
    @CsvBindByName(column ="SR",required = true)
    public double strikeRate;
}
