package com.engineering.reverse.cumulativesum.imperative;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CumulativeSumUsingLoop {
  public static List<Long> compute(List<Long> inputList) {
    Objects.requireNonNull(inputList, "Input list can not be null");

    List<Long> result = new ArrayList<>(inputList.size());
    Long prevSum = 0L;
    for (Long element : inputList) {
      prevSum += element;
      result.add(prevSum);
    }
    return result;
  }
}
