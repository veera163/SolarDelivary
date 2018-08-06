package com.example.home.solardelivary.Modules;

import java.util.List;


public interface DirectionFinderListener {

    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
