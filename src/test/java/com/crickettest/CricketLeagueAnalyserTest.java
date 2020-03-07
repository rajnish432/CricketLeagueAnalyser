package com.crickettest;

import com.cricketanalyser.CricketLeagueAnalyser;
import com.cricketanalyser.IplMostRunsCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
    private static final String IPL_MOST_RUNS_CSV_PATH="./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenMostRunsData_WhenSorted_ReturnsTopAverages() {
        cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_PATH);
        String result=cricketLeagueAnalyser.getTopBattingAverage();
        IplMostRunsCSV[] iplMostRunsCSVS=new Gson().fromJson(result,IplMostRunsCSV[].class);
        Assert.assertEquals(83.2,iplMostRunsCSVS[0].average,0.0);
    }
}
