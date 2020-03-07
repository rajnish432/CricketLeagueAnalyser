package com.crickettest;

import com.cricketanalyser.CricketLeagueAnalyser;
import com.cricketanalyser.IplMostRunsCSV;
import com.cricketanalyser.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
    private static final String IPL_MOST_RUNS_CSV_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopAverages() {
        cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_PATH);
        String result=cricketLeagueAnalyser.getSortedData(SortField.AVERAGE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(result,IplMostRunsCSV[].class);
        Assert.assertEquals(83.2,iplMostRunsCSVS[0].average,0.0);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopStrikeRate() {
        cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_PATH);
        String topStrikeRate=cricketLeagueAnalyser.getSortedData(SortField.STRIKE_RATE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(topStrikeRate,IplMostRunsCSV[].class);
        Assert.assertEquals(333.33,iplMostRunsCSVS[0].strikeRate,0.0);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsMaxBoundaries() {
        cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_PATH);
        String maxBoundary=cricketLeagueAnalyser.getSortedData(SortField.MAXIMUM_BOUNDARIES);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxBoundary,IplMostRunsCSV[].class);
        Assert.assertEquals(83,iplMostRunsCSVS[0].sixes+iplMostRunsCSVS[0].fours);
    }
}