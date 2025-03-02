package com.engineering.reverse.cumulativesum.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CumulativeSumUsingList {
  public static List<Long> compute(List<Long> inputList) {
    Objects.requireNonNull(inputList, "Input list can not be null");

    return inputList.stream()
        //                .parallel()
        .collect(
            () -> new ArrayList<Long>(),
            (cumm, element) -> {
              if (cumm.isEmpty()) {
                cumm.add(element);
              } else {
                cumm.add(cumm.getLast() + element);
              }
            },
            (left, right) -> left.addAll(right.stream().map(e -> e + left.getLast()).toList()));
  }
}
