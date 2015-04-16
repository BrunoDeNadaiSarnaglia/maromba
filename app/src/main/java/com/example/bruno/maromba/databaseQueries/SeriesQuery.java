package com.example.bruno.maromba.databaseQueries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bruno on 4/16/2015.
 */
public class SeriesQuery {

    HashMap<String, ArrayList<String>> seriesName = new HashMap<String, ArrayList<String>>();

    /**
     * Mock class to simulate the search for series
     */
    public SeriesQuery() {
        seriesName.put("a@b", new ArrayList<String>(Arrays.asList("serie A", "serie B", "serie C")));
        seriesName.put("c@d", new ArrayList<String>(Arrays.asList("serie D", "serie E", "serie F")));
    }

    public List<String> getSeriesNames(String email){
        if(!seriesName.containsKey(email))
            return null;
        else
            return seriesName.get(email);
    }

    public void deleteAccount(String email){
        seriesName.remove(email);
    }
}
