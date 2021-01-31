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
    public List<Flight> filtration(List<Flight> flightsList, Predicate<Flight> filteringRule) {
        return flightsList.stream().filter(filteringRule).collect(Collectors.toList());
    }
}
