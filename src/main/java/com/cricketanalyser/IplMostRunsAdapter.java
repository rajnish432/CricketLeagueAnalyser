package com.cricketanalyser;

import com.censusjar.CsvBuilderFactory;
import com.censusjar.ICsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplMostRunsAdapter extends IplAdapter {
    @Override
    public Map<String, IplDTO> loadIplData(String... csvFilePath) {
        Map<String,IplDTO> iplDTOMap=null;
        iplDTOMap=super.loadIplData(IplMostRunsCSV.class,csvFilePath[0]);
        if (csvFilePath.length>1) {
            this.loadBowlingData(iplDTOMap, csvFilePath[1]);
        }
        return iplDTOMap;
    }

    private void loadBowlingData(Map<String, IplDTO> iplDTOMap, String csvVFilePath) {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvVFilePath))) {
            ICsvBuilder iCsvBuilder= CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<IplMostWicketsCSV> iterator=iCsvBuilder.getCsvFileIterator(reader,IplMostWicketsCSV.class);
            Iterable<IplMostWicketsCSV> iterable=() ->iterator;
            StreamSupport.stream(iterable.spliterator(),false)
                    .filter(iplMostWicketsAdapter -> iplDTOMap.get(iplMostWicketsAdapter.playerName)!=null )
                    .forEach(mergedData->{iplDTOMap.get(mergedData.playerName).bowlerAverage=mergedData.average;
                        iplDTOMap.get(mergedData.playerName).maxwickets=mergedData.wickets;});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
