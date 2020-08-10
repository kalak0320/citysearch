package com.mastercard.assessment.citysearch.graph;


import java.util.*;

/**
 * Graph data structure implementation for searching the cities
 */
public class CityRoutesGraph {

    private HashMap<String, Set<String>> routesMap = new HashMap<>();

    public CityRoutesGraph(List<Route> routes){
        routes.forEach(route -> {
            routesMap.putIfAbsent(route.getOriginCity(), new HashSet<>());
            routesMap.get(route.getOriginCity()).add(route.getDestinationCity());
            routesMap.putIfAbsent(route.getDestinationCity(), new HashSet<>());
            routesMap.get(route.getDestinationCity()).add(route.getOriginCity());
        });
    }


    public boolean isRouteAvailable(Route route){
        return isRouteAvailable(route, new HashSet<>());
    }


    /**
     * BFS implementation to search through the graph if the cities are connected or not
     * @param route
     * @param visited
     * @return
     */
    private boolean isRouteAvailable(Route route, HashSet<String> visited){

        if(visited.contains(route.getOriginCity())){
            return false;
        }
        visited.add(route.getOriginCity());
        if(route.getOriginCity().equals(route.getDestinationCity())){
            return true;
        }

        Set<String> destinations = routesMap.computeIfAbsent(route.getOriginCity(), (key) -> new HashSet<>());

        for(String currCity: destinations){
            Route newRoute = new Route.Builder()
                    .withOriginCity(currCity)
                    .withDestinationCity(route.getDestinationCity())
                    .build();
            if(isRouteAvailable(newRoute, visited)) {
                return true;
            }
        }

        return false;
    }

}
