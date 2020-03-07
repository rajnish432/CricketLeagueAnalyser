package com.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplMostRunsCSV {
    @CsvBindByName(column = "Avg",required = true)
    public double average;
}
