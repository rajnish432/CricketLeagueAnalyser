package com.crickettest;

import com.cricketanalyser.CricketLeagueAnalyser;
import com.cricketanalyser.IplMostRunsCSV;
import com.cricketanalyser.IplMostWicketsCSV;
import com.cricketanalyser.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
    private static final String IPL_MOST_RUNS_CSV_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_WICKETS_CSV_PATH="./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopAverages() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String result=cricketLeagueAnalyser.getSortedData(SortField.AVERAGE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(result,IplMostRunsCSV[].class);
        Assert.assertEquals(83.2,iplMostRunsCSVS[0].average,0.0);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopStrikeRate() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String topStrikeRate=cricketLeagueAnalyser.getSortedData(SortField.STRIKE_RATE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(topStrikeRate,IplMostRunsCSV[].class);
        Assert.assertEquals(333.33,iplMostRunsCSVS[0].strikeRate,0.0);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsMaxBoundaries() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String maxBoundary=cricketLeagueAnalyser.getSortedData(SortField.MAXIMUM_BOUNDARIES);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxBoundary,IplMostRunsCSV[].class);
        Assert.assertEquals(83,iplMostRunsCSVS[0].sixes+iplMostRunsCSVS[0].fours);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopStrikeRateWithMaxBoundaries() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String maxRecord=cricketLeagueAnalyser.getSortedData(SortField.MAXIMUM_BOUNDARIES);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxRecord,IplMostRunsCSV[].class);
        Assert.assertEquals(204.81,iplMostRunsCSVS[0].strikeRate,0.0);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopAveragesWithStrikeRates() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String maxAverageWithStrikes=cricketLeagueAnalyser.getSortedData(SortField.AVERAGE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxAverageWithStrikes,IplMostRunsCSV[].class);
        Assert.assertEquals(134.62,iplMostRunsCSVS[0].strikeRate,0.0);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopHitsWithAverage() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String maxHitWithAverage=cricketLeagueAnalyser.getSortedData(SortField.MAX_RUNS);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxHitWithAverage,IplMostRunsCSV[].class);
        Assert.assertEquals(69.2,iplMostRunsCSVS[0].average,0.0f);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ReturnsTopBowlingAverage() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS,IPL_MOST_WICKETS_CSV_PATH);
        String maxAverage=cricketLeagueAnalyser.getSortedData(SortField.AVERAGE);
        IplMostWicketsCSV[] iplMostWicketsCSVS=new Gson().fromJson(maxAverage,IplMostWicketsCSV[].class);
        Assert.assertEquals(166.0,iplMostWicketsCSVS[0].average,0.0);
    }
}
