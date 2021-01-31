package com.gridnine.testing;

import static com.gridnine.testing.FilterRules.*;
import static com.gridnine.testing.FlightBuilder.*;

public class Main {
    public static void main(String[] args){
        FilterFlight filteringFlight = new FilterFlight();
        System.out.println("Исключены полеты с вылетом до текущего момента времени:\n" +
                filteringFlight.filtration(createFlights(), DEPARTURE_AFTER_CURRENT_TIME));
        System.out.println("Исключены полеты в которых имеются сегменты с датой прилёта раньше даты вылета:\n"
                + filteringFlight.filtration(createFlights(), ARRIVAL_AFTER_DEPARTURE));
        System.out.println("Исключены полеты в которых общее время, проведённое на земле превышает два часа:\n"
                + filteringFlight.filtration(createFlights(), WAITING_TIME_BETWEEN_SEGMENTS_LESS_THAN_2_HOURS));
    }
}
