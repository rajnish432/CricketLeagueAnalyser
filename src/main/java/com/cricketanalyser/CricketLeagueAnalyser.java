package com.cricketanalyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {
    List<IplDTO> iplDTOList;
    Map<String,IplDTO> iplDTOMap=null;
    Map<SortField,Comparator<IplDTO>> fieldComparatorMap;

    public enum IplRecords{
        IPL_MOST_RUNS,IPL_MOST_WICKETS;
    }

    public CricketLeagueAnalyser() {
        iplDTOList=new ArrayList<>();
        fieldComparatorMap=new HashMap<>();
        iplDTOMap=new HashMap<>();
        this.fieldComparatorMap.put(SortField.AVERAGE,Comparator.comparing(census-> census.average));
        this.fieldComparatorMap.put(SortField.STRIKE_RATE,Comparator.comparing(census->census.strikeRate));
        this.fieldComparatorMap.put(SortField.MAXIMUM_BOUNDARIES,Comparator.comparing(ipldata-> ipldata.sixes+ipldata.fours));
        this.fieldComparatorMap.put(SortField.MAX_RUNS,Comparator.comparing(ipldata-> ipldata.runs));
    }

    public int loadIplData(IplRecords iplRecords,String csvFilePath)
    {
        iplDTOMap=IplAdapterFactory.getCricketData(iplRecords,csvFilePath);
        return iplDTOMap.size();
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
