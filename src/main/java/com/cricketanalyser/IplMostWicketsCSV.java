package com.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplMostWicketsCSV {
    @CsvBindByName(column ="Avg",required = true)
    public double average;
    @CsvBindByName(column ="PLAYER",required = true)
    public String playerName;
    @CsvBindByName(column ="SR",required = true)
    public double strikeRate;
    @CsvBindByName(column ="Econ",required = true)
    public double economy;
    @CsvBindByName(column ="5w",required = true)
    public int fiveWickets;
    @CsvBindByName(column ="4w",required = true)
    public int fourWickets;
    @CsvBindByName(column ="Wkts",required = true)
    public int wickets;
}
