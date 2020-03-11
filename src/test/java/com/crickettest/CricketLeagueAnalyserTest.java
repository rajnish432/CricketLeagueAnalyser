package com.crickettest;

import com.cricketanalyser.*;
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
        Assert.assertEquals("MS Dhoni",iplMostRunsCSVS[0].playerName);
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
        String maxRecord=cricketLeagueAnalyser.getSortedData(SortField.MAXIMUM_BOUNDARIES_WITH_STRIKE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxRecord,IplMostRunsCSV[].class);
        Assert.assertEquals("Andre Russell",iplMostRunsCSVS[0].playerName);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopAveragesWithStrikeRates() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String maxAverageWithStrikes=cricketLeagueAnalyser.getSortedData(SortField.AVERAGEWITHSTRIKERATE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxAverageWithStrikes,IplMostRunsCSV[].class);
        Assert.assertEquals("MS Dhoni",iplMostRunsCSVS[0].playerName);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopHitsWithAverage() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS,IPL_MOST_RUNS_CSV_PATH);
        String maxHitWithAverage=cricketLeagueAnalyser.getSortedData(SortField.RUNS_WITH_AVERAGE);
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(maxHitWithAverage,IplMostRunsCSV[].class);
        Assert.assertEquals("David Warner ",iplMostRunsCSVS[0].playerName);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ReturnsTopBowlingAverage() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS,IPL_MOST_WICKETS_CSV_PATH);
        String maxAverage=cricketLeagueAnalyser.getSortedData(SortField.AVERAGE);
        IplMostWicketsCSV[] iplMostWicketsCSVS=new Gson().fromJson(maxAverage,IplMostWicketsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham",iplMostWicketsCSVS[0].playerName);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ReturnsTopBowlingStrikeRate() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS,IPL_MOST_WICKETS_CSV_PATH);
        String maxStrikeRate=cricketLeagueAnalyser.getSortedData(SortField.STRIKE_RATE);
        IplMostWicketsCSV[] iplMostWicketsCSVS=new Gson().fromJson(maxStrikeRate,IplMostWicketsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham",iplMostWicketsCSVS[0].playerName);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ReturnsTopEconomyRate() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS,IPL_MOST_WICKETS_CSV_PATH);
        String maxEcconomy=cricketLeagueAnalyser.getSortedData(SortField.ECONOMY);
        IplMostWicketsCSV[] iplMostWicketsCSVS=new Gson().fromJson(maxEcconomy,IplMostWicketsCSV[].class);
        Assert.assertEquals("Ben Cutting",iplMostWicketsCSVS[0].playerName);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ReturnsTopStrikeRateWithWickets() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS,IPL_MOST_WICKETS_CSV_PATH);
        String maxStrikeWithWickets=cricketLeagueAnalyser.getSortedData(SortField.STRIKE_RATE_WITH_WICKETS);
        IplMostWicketsCSV[] iplMostWicketsCSVS=new Gson().fromJson(maxStrikeWithWickets,IplMostWicketsCSV[].class);
        Assert.assertEquals("Lasith Malinga",iplMostWicketsCSVS[0].playerName);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ReturnsTopAverageWithStrikeRate() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS,IPL_MOST_WICKETS_CSV_PATH);
        String maxAverageWithStrikeRate=cricketLeagueAnalyser.getSortedData(SortField.AVERAGEWITHSTRIKERATE);
        IplMostWicketsCSV[] iplMostWicketsCSVS=new Gson().fromJson(maxAverageWithStrikeRate,IplMostWicketsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham",iplMostWicketsCSVS[0].playerName);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ReturnsMaxWicketsWithAverage() {
        cricketLeagueAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS,IPL_MOST_WICKETS_CSV_PATH);
        String maxWicketsWithAverage=cricketLeagueAnalyser.getSortedData(SortField.WICKETS_WITH_AVERAGE);
        IplMostWicketsCSV[] iplMostWicketsCSVS=new Gson().fromJson(maxWicketsWithAverage,IplMostWicketsCSV[].class);
        Assert.assertEquals("Imran Tahir",iplMostWicketsCSVS[0].playerName);
    }

    @Test
    public void givenMostWicketsandRunsData_WhenSorted_ReturnsBestBattingAndBowlingAverage() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        iplAnalyser.setIPLAdapter(new IplMostRunsAdapter());
        try {
            iplAnalyser.loadIplData(CricketLeagueAnalyser.IplRecords.ALLROUNDER, IPL_MOST_RUNS_CSV_PATH, IPL_MOST_WICKETS_CSV_PATH);
            String sortedData = iplAnalyser.getSortedData(SortField.BEST_BATTING_BOWLING_AVERAGE);
            System.out.println(sortedData);
            IplMostRunsCSV[] iplCSVData = new Gson().fromJson(sortedData, IplMostRunsCSV[].class);
            Assert.assertEquals("Marcus Stoinis", iplCSVData[0].playerName);
        } catch (CricketLeagueExceptions e) {
            e.printStackTrace();
        }
    }
}

