package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;

/**
 *Functional interface for filtering a list of elements by rule.
 * @param <T> type of filtered elements.
 */
@FunctionalInterface
public interface Filter<T> {
     /**
      *Filtration method
      * @param list List with the elements which must be filtered.
      * @param filterRule Predicate with the rule by which filtering must be done.
      * @return Filtered list of elements.
      */
     List<T> filtration(List<T> list, Predicate<T> filterRule);
}
