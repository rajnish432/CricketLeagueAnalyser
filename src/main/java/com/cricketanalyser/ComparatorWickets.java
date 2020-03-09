package com.cricketanalyser;

import java.util.Comparator;

public class ComparatorWickets implements Comparator<IplDTO> {

    @Override
    public int compare(IplDTO p1, IplDTO p2) {
        int i=(p1.fourWickets+p1.fiveWickets)-(p2.fourWickets+p2.fiveWickets);
        return i;
    }
}
