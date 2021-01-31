package com.gridnine.testing;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilterRulesTest {
    static Flight twoHoursDuration;
    static Flight normalMultiSegment;
    static Flight departingInThePast;
    static Flight departsBeforeItArrives;
    static  Flight twoHoursGroundTime;

    @BeforeClass
    public static void setUp(){
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        List<Segment> segmentListTwoHoursDuration = new ArrayList<>(1);
        segmentListTwoHoursDuration.add(new Segment(threeDaysFromNow.plusHours(1), threeDaysFromNow.plusHours(3)));
        twoHoursDuration = new Flight(segmentListTwoHoursDuration);
        List<Segment> multiSegmentList = new ArrayList<>(2);
        multiSegmentList.add(new Segment(threeDaysFromNow.plusHours(1), threeDaysFromNow.plusHours(3)));
        multiSegmentList.add(new Segment(threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(6)));
        normalMultiSegment = new Flight(multiSegmentList);
        List<Segment> segmentListDepartingInThePast = new ArrayList<>(1);
        segmentListDepartingInThePast.add(new Segment(threeDaysFromNow.minusDays(4), threeDaysFromNow));
        departingInThePast = new Flight(segmentListDepartingInThePast);
        List<Segment> segmentListDepartsBeforeItArrives = new ArrayList<>(1);
        segmentListDepartsBeforeItArrives.add(new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(4)));
        departsBeforeItArrives = new Flight(segmentListDepartsBeforeItArrives);
        List<Segment> segmentListMoreThanTwoHoursGroundTime = new ArrayList<>(3);
        segmentListMoreThanTwoHoursGroundTime.add(new Segment(threeDaysFromNow.plusHours(2), threeDaysFromNow.plusHours(3)));
        segmentListMoreThanTwoHoursGroundTime.add(new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(9)));
        segmentListMoreThanTwoHoursGroundTime.add(new Segment(threeDaysFromNow.plusHours(10), threeDaysFromNow.plusHours(15)));
        twoHoursGroundTime = new Flight(segmentListMoreThanTwoHoursGroundTime);
    }

    @Test
    public void departureTimeAfterTheCurrentTimeRulePositiveTest() {
       assertTrue(FilterRules.DEPARTURE_AFTER_CURRENT_TIME.test(twoHoursDuration));
    }

    @Test
    public void departureTimeAfterTheCurrentTimeRuleNegativeTest() {
        assertFalse(FilterRules.DEPARTURE_AFTER_CURRENT_TIME.test(departingInThePast));
    }

    @Test
    public void arrivalTimeAfterDepartureTimeRulePositiveTest() {
        assertTrue(FilterRules.ARRIVAL_AFTER_DEPARTURE.test(twoHoursDuration));
    }

    @Test
    public void arrivalTimeAfterDepartureTimeRuleNegativeTest() {
        assertFalse(FilterRules.ARRIVAL_AFTER_DEPARTURE.test(departsBeforeItArrives));
    }

    @Test
    public void waitingTimeBetweenSegmentsLessThanTwoHoursRulePositiveTest() {
        assertTrue(FilterRules.WAITING_TIME_BETWEEN_SEGMENTS_LESS_THAN_2_HOURS.test(normalMultiSegment));
    }

    @Test
    public void waitingTimeBetweenSegmentsLessThanTwoHoursRuleNegativeTest() {
        assertFalse(FilterRules.WAITING_TIME_BETWEEN_SEGMENTS_LESS_THAN_2_HOURS.test(twoHoursGroundTime));
    }
}
