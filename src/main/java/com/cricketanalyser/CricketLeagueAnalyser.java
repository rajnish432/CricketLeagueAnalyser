package com.cricketanalyser;

import com.censusjar.CsvBuilderFactory;
import com.censusjar.ICsvBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {
    List<IplDTO> iplDTOList;
    Map<String,IplDTO> iplDTOMap;
    Map<SortField,Comparator<IplDTO>> fieldComparatorMap;

    public CricketLeagueAnalyser() {
        iplDTOList=new ArrayList<>();
        fieldComparatorMap=new HashMap<>();
        iplDTOMap=new HashMap<>();
        this.fieldComparatorMap.put(SortField.AVERAGE,Comparator.comparing(census-> census.average));
        this.fieldComparatorMap.put(SortField.STRIKE_RATE,Comparator.comparing(census->census.strikeRate));
        this.fieldComparatorMap.put(SortField.MAXIMUM_BOUNDARIES,Comparator.comparing(ipldata-> ipldata.sixes+ipldata.fours));
        this.fieldComparatorMap.put(SortField.MAX_RUNS,Comparator.comparing(ipldata-> ipldata.runs));
    }

    public int loadIplData(String csvFilePath) {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder openCsvBuilder = CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<IplMostRunsCSV> iteratorload = openCsvBuilder.getCsvFileIterator(reader,IplMostRunsCSV.class);
            while (iteratorload.hasNext())
            {
                IplMostRunsCSV iplMostRunsCSV=iteratorload.next();
                this.iplDTOMap.put(iplMostRunsCSV.playerName,new IplDTO(iplMostRunsCSV));
           }
            return iplDTOList.size();
        } catch (IOException e) {
            throw new CricketLeagueExceptions("Wrong File Path",CricketLeagueExceptions.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
    public int loadIplWicketsData(String iplMostWicketsCsvPath) {
        try(Reader reader= Files.newBufferedReader(Paths.get(iplMostWicketsCsvPath))) {
            ICsvBuilder openCsvBuilder = CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<IplMostWicketsCSV> iteratorload = openCsvBuilder.getCsvFileIterator(reader,IplMostWicketsCSV.class);
            while (iteratorload.hasNext())
            {
                IplMostWicketsCSV iplMostWicketsCSV=iteratorload.next();
                this.iplDTOMap.put(iplMostWicketsCSV.playerName,new IplDTO(iplMostWicketsCSV));
            }
            return iplDTOList.size();
        } catch (IOException e) {
            throw new CricketLeagueExceptions("Wrong File Path",CricketLeagueExceptions.ExceptionType.CSV_FILE_PROBLEM);
        }
    }

    public String getSortedData(SortField sortField) {
        iplDTOList = iplDTOMap.values().stream().collect(Collectors.toList());
        if (iplDTOList==null || iplDTOList.size()==0)
        {
            throw new CricketLeagueExceptions("No Records",CricketLeagueExceptions.ExceptionType.NO_RECORDS_FOUND);
        }
            sort(this.fieldComparatorMap.get(sortField));
        Collections.reverse(iplDTOList);
        String sortedAverage=new Gson().toJson(iplDTOList);
        return sortedAverage;
    }

    private void sort(Comparator<IplDTO> mostRunsCSVComparator) {
        for (int i = 0; i< this.iplDTOList.size()-1; i++)
        {
            for (int j = 0; j< this.iplDTOList.size()-i-1; j++)
            {
                IplDTO census1= this.iplDTOList.get(j);
                IplDTO census2= this.iplDTOList.get(j+1);
                if (mostRunsCSVComparator.compare(census1,census2)>0) {
                    this.iplDTOList.set(j, census2);
                    this.iplDTOList.set(j + 1, census1);
                }
            }
        }
    }
}
