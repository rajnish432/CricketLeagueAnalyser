package com.cricketanalyser;


import java.util.Map;

public class IplAdapterFactory {
    public static Map<String,IplDTO> getCricketData(CricketLeagueAnalyser.IplRecords iplRecords,String... csvFilePath)
    {
        if(iplRecords.equals(CricketLeagueAnalyser.IplRecords.IPL_MOST_RUNS))
            return new IplMostRunsAdapter().loadIplData(csvFilePath);
        if (iplRecords.equals(CricketLeagueAnalyser.IplRecords.IPL_MOST_WICKETS))
            return new IplMostWicketsAdapter().loadIplData(csvFilePath);
        if (iplRecords.equals(CricketLeagueAnalyser.IplRecords.ALLROUNDER))
            return new AllRounder().loadIplData(csvFilePath);
        if (iplRecords.equals(CricketLeagueAnalyser.IplRecords.ALLROUNDER_MAIN))
            return new AllRounderMain().loadIplData(csvFilePath);
        else
            throw new CricketLeagueExceptions("No Such Record",CricketLeagueExceptions.ExceptionType.NO_RECORDS_FOUND);
    }
}
