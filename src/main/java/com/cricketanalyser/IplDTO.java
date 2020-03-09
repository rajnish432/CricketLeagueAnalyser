package com.cricketanalyser;

public class IplDTO {
    public String playerName;
    public double average;
    public double strikeRate;
    public int sixes;
    public int fours;
    public int runs;
    public double economy;
    public int fourWickets;
    public int fiveWickets;
    public int wickets;

    public IplDTO(IplMostRunsCSV iplMostRunsCSV) {
        runs=iplMostRunsCSV.runs;
        sixes=iplMostRunsCSV.sixes;
        fours=iplMostRunsCSV.fours;
        strikeRate=iplMostRunsCSV.strikeRate;
        average=iplMostRunsCSV.average;
        playerName=iplMostRunsCSV.playerName;
    }

    public IplDTO(IplMostWicketsCSV iplMostWicketsCSV) {
        average=iplMostWicketsCSV.average;
        playerName=iplMostWicketsCSV.playerName;
        strikeRate=iplMostWicketsCSV.strikeRate;
        economy=iplMostWicketsCSV.economy;
        fiveWickets=iplMostWicketsCSV.fiveWickets;
        fourWickets=iplMostWicketsCSV.fourWickets;
        wickets=iplMostWicketsCSV.wickets;
    }
}
