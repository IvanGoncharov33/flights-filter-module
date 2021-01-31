package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.*;

public class FilterFlightTest {
    List<Flight> flightList = new ArrayList<>(2);
    Predicate<Flight> flightPredicate;

    @Before
    public void setUp(){
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        List<Segment> segmentListTwoHoursDuration = new ArrayList<>(1);
        segmentListTwoHoursDuration.add(new Segment(threeDaysFromNow.plusHours(1), threeDaysFromNow.plusHours(3)));
        flightList.add(new Flight(segmentListTwoHoursDuration));
        List<Segment> multiSegmentList = new ArrayList<>(2);
        multiSegmentList.add(new Segment(threeDaysFromNow.plusHours(1), threeDaysFromNow.plusHours(3)));
        multiSegmentList.add(new Segment(threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(6)));
        flightList.add(new Flight(multiSegmentList));
        flightPredicate = flight -> flight.getSegments().size() > 1;
    }

    @Test
    public void shouldFilter() {
        FilterFlight filteringFlight = new FilterFlight();
        List<Flight> flightListAfterFilter = filteringFlight.filtration(flightList,flightPredicate);
        Assert.assertThat(flightListAfterFilter.size(), is(1));
    }
}
