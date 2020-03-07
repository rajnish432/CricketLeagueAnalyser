package com.cricketanalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CricketLeagueAnalyser {
    List<IplMostRunsCSV> mostRunsCSVList;
    Map<SortField,Comparator<IplMostRunsCSV>> fieldComparatorMap;

    public CricketLeagueAnalyser() {
        mostRunsCSVList=new ArrayList<>();
        fieldComparatorMap=new HashMap<>();
        this.fieldComparatorMap.put(SortField.AVERAGE,Comparator.comparing(census-> census.average));
        this.fieldComparatorMap.put(SortField.STRIKE_RATE,Comparator.comparing(census->census.strikeRate));
    }

    public int loadIplData(String csvFilePath) {
        int noOfPlayers=0;
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBeanBuilder<IplMostRunsCSV> csvCsvToBeanBuilder=new CsvToBeanBuilder<>(reader);
            csvCsvToBeanBuilder.withType(IplMostRunsCSV.class);
            csvCsvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IplMostRunsCSV> csvToBean = csvCsvToBeanBuilder.build();
            Iterator<IplMostRunsCSV> iteratorload=csvToBean.iterator();
            while (iteratorload.hasNext())
            {
                IplMostRunsCSV iplMostRunsCSV=iteratorload.next();
                mostRunsCSVList.add(iplMostRunsCSV);
                noOfPlayers++;
            }
            return noOfPlayers;
        } catch (IOException e) {
            throw new CricketLeagueExceptions("Wrong File Path",CricketLeagueExceptions.ExceptionType.CSV_FILE_PROBLEM);
        }

    }

    public String getSortedData(SortField sortField) {
        if (mostRunsCSVList==null || mostRunsCSVList.size()==0)
        {
            throw new CricketLeagueExceptions("No Records",CricketLeagueExceptions.ExceptionType.NO_RECORDS_FOUND);
        }
        sort(this.fieldComparatorMap.get(sortField));
        Collections.reverse(mostRunsCSVList);
        String sortedAverage=new Gson().toJson(mostRunsCSVList);
        return sortedAverage;
    }

    private void sort(Comparator<IplMostRunsCSV> mostRunsCSVComparator) {
        for (int i = 0; i< this.mostRunsCSVList.size()-1; i++)
        {
            for (int j = 0; j< this.mostRunsCSVList.size()-i-1; j++)
            {
                IplMostRunsCSV census1= this.mostRunsCSVList.get(j);
                IplMostRunsCSV census2= this.mostRunsCSVList.get(j+1);
                if (mostRunsCSVComparator.compare(census1,census2)>0) {
                    this.mostRunsCSVList.set(j, census2);
                    this.mostRunsCSVList.set(j + 1, census1);
                }
            }
        }
    }
}
