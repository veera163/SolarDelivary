package com.example.home.solardelivary.Modules;

import java.util.List;

/**
 * Created by veera on 10/7/2017.
 */

public interface DistanceFindListener {
    void onDistanceFinderStart();
    void onDistanceFinderSuccess(List<Route> route);
}
