package com.cricketanalyser;

import com.censusjar.CsvBuilderFactory;
import com.censusjar.ICsvBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IplAdapter extends RuntimeException {

    public abstract Map<String,IplDTO> loadIplData(String... csvFilePath);

    public <E> Map<String,IplDTO> loadIplData(Class<E> csvClass,String csvFilePath) {
        Map<String,IplDTO> iplDTOMap=new HashMap<>();
        try(Reader reader= Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder openCsvBuilder = CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<E> iteratorload = openCsvBuilder.getCsvFileIterator(reader,csvClass);
            Iterable<E> iterable=() -> iteratorload;
            if (csvClass.getName().equals("com.cricketanalyser.IplMostRunsCSV"))
            {
                StreamSupport.stream(iterable.spliterator(),false)
                        .map(IplMostRunsCSV.class::cast)
                        .forEach(csvipl-> iplDTOMap.put(csvipl.playerName,new IplDTO(csvipl)));
            }
            else if (csvClass.getName().equals("com.cricketanalyser.IplMostWicketsCSV"))
            {
                StreamSupport.stream(iterable.spliterator(),false)
                        .map(IplMostWicketsCSV.class::cast)
                        .forEach(csvipl-> iplDTOMap.put(csvipl.playerName,new IplDTO(csvipl)));
            }
            return iplDTOMap;
        } catch (IOException e) {
            throw new CricketLeagueExceptions("Wrong File Path",CricketLeagueExceptions.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}
