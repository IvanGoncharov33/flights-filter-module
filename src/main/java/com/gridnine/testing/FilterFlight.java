package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *Flight filtering class.
 */
public class FilterFlight implements Filter<Flight> {
    /**
     * {@inheritDoc}
     */
    public List<Flight> filtration(List<Flight> flightsList, Predicate<Flight> filterRule) {
        return flightsList.stream().filter(filterRule).collect(Collectors.toList());
    }
}
