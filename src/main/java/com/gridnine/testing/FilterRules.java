package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.function.Predicate;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 *Class containing filtering rules.
 */
public class FilterRules {
    /**
     *Filtering rule:
     * excludes flights departing before the current time.
     */
    public static final Predicate<Flight> DEPARTURE_AFTER_CURRENT_TIME = flight ->
           flight.getSegments().stream().allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()));
    /**
     *Filtering rule:
     * excludes flights in which there are segments
     * with an arrival date earlier than the departure date.
     */
    public static final Predicate<Flight> ARRIVAL_AFTER_DEPARTURE = flight -> flight.getSegments().stream()
            .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()));
    /**
     *Filtering rule:
     * excludes flights in which the waiting time
     * between segments is more than 2 hours
     */
   public static final Predicate<Flight> WAITING_TIME_BETWEEN_SEGMENTS_LESS_THAN_2_HOURS = flight -> {
       long accumulator = 0;
       for (int i = 0; i < flight.getSegments().size() - 1; i++) {
           accumulator += SECONDS.between(flight.getSegments().get(i).getArrivalDate(), flight.getSegments().get(i + 1).getDepartureDate());
       }
      return accumulator < 2 * 3600;
    };
}
