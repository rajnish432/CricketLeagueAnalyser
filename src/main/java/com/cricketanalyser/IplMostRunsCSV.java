package com.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplMostRunsCSV {
    @CsvBindByName(column = "Avg",required = true)
    public double average;
    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;
    @CsvBindByName(column = "4s",required = true)
    public int fours;
    @CsvBindByName(column = "6s",required = true)
    public int sixes;
}
