package com.cricketanalyser;

public class CricketLeagueExceptions extends RuntimeException {

    enum ExceptionType
    {
        NO_CENSUS_DATA,CSV_FILE_PROBLEM;
    }
    ExceptionType eType;

    public CricketLeagueExceptions(String message,ExceptionType eType) {
        super(message);
        this.eType=eType;
    }
}
