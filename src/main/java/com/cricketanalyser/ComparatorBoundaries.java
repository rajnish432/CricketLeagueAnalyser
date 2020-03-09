package com.cricketanalyser;

import java.util.Comparator;

public class ComparatorBoundaries implements Comparator<IplDTO> {
    @Override
    public int compare(IplDTO p1, IplDTO p2) {
        int i=(p1.sixes+p1.fours)-(p2.fours+p2.sixes);
        return i;
    }
}
