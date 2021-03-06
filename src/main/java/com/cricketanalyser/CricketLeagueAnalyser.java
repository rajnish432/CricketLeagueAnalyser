package com.cricketanalyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {
    List<IplDTO> iplDTOList;
    Map<String,IplDTO> iplDTOMap=null;
    Map<SortField,Comparator<IplDTO>> fieldComparatorMap;
    public IplAdapter iplAdapter;

    public void setIPLAdapter(IplMostRunsAdapter iplMostRunsAdapter) {
        this.iplAdapter=iplMostRunsAdapter;
    }

    public enum IplRecords{
        IPL_MOST_RUNS,IPL_MOST_WICKETS;
    }

    public CricketLeagueAnalyser() {
        iplDTOList=new ArrayList<>();
        fieldComparatorMap=new HashMap<>();
        iplDTOMap=new HashMap<>();
        this.fieldComparatorMap.put(SortField.AVERAGE,Comparator.comparing(ipldata-> ipldata.average));
        Comparator<IplDTO> averagecomp=Comparator.comparing(ipldata-> ipldata.average);
        this.fieldComparatorMap.put(SortField.AVERAGEWITHSTRIKERATE,averagecomp.thenComparing(ipldata->ipldata.strikeRate));
        this.fieldComparatorMap.put(SortField.STRIKE_RATE,Comparator.comparing(ipldata->ipldata.strikeRate));
        this.fieldComparatorMap.put(SortField.MAXIMUM_BOUNDARIES,Comparator.comparing(ipldata-> ipldata.sixes+ipldata.fours));
        this.fieldComparatorMap.put(SortField.MAXIMUM_BOUNDARIES_WITH_STRIKE,new ComparatorBoundaries().thenComparing(ipldata->ipldata.strikeRate));
        this.fieldComparatorMap.put(SortField.MAX_RUNS,Comparator.comparing(ipldata-> ipldata.runs));
        Comparator<IplDTO> runs=Comparator.comparing(ipldata-> ipldata.runs);
        this.fieldComparatorMap.put(SortField.RUNS_WITH_AVERAGE,runs.thenComparing(ipldata-> ipldata.average));
        this.fieldComparatorMap.put(SortField.ECONOMY,Comparator.comparing(ipldata-> ipldata.economy));
        this.fieldComparatorMap.put(SortField.STRIKE_RATE_WITH_WICKETS,new ComparatorWickets().thenComparing(ipldata-> ipldata.strikeRate));
        Comparator<IplDTO> wickets=Comparator.comparing(ipldata-> ipldata.wickets);
        this.fieldComparatorMap.put(SortField.WICKETS_WITH_AVERAGE,wickets.thenComparing(ipldata-> ipldata.average));
        this.fieldComparatorMap.put(SortField.BEST_BATTING_BOWLING_AVERAGE,new ComparatorAverage());
        this.fieldComparatorMap.put(SortField.ALL_ROUNDER,new ComparatorAllRounder());
        //this.fieldComparatorMap.put(SortField.ALL_ROUNDER,runs.thenComparing(ipldata->ipldata.wickets));
        }

    public int loadIplData(IplRecords iplRecords,String... csvFilePath)
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
