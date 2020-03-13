package com.cricketanalyser;

import java.util.Comparator;

public class ComparatorAverage implements Comparator<IplDTO> {
    @Override
    public int compare(IplDTO p1, IplDTO p2) {
        int i= (int) ((int)(p1.battingAverage+p1.bowlerAverage)-(p2.battingAverage+p2.bowlerAverage));
        return i;
    }
}
