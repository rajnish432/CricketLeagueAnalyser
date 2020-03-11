package com.cricketanalyser;

import java.util.Comparator;

public class ComparatorAllRounder implements Comparator<IplDTO> {

    @Override
    public int compare(IplDTO p1, IplDTO p2) {
            int i=0;
        if(p1.maxwickets!=0) {
            i = (p1.maxwickets * p1.runs)-(p2.maxwickets*p2.runs);
        }return i;
    }
}
