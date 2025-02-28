package com.engineering.reverse.cumulativesum.imperative;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class CumulativeSumUsingMultiThreading {
    private static final int THRESHOLD = 500_000;

    private static List<Long> splitAndCompute(List<Long> list) {
        List<Long> result;

        if(list.size() <= THRESHOLD) {
            result = CumulativeSumUsingLoop.compute(list);
        } else {
            List<Long> sublist1 = list.subList(0, THRESHOLD);
            List<Long> sublist2 = list.subList(THRESHOLD, list.size());

            try(ExecutorService executorService = Executors.newFixedThreadPool(2)) {
                Future<List<Long>> futureResult1 = executorService.submit(() -> splitAndCompute(sublist1));
                Future<List<Long>> futureResult2 = executorService.submit(() -> splitAndCompute(sublist2));

                try {
                    List<Long> result1 = futureResult1.get();
                    List<Long> result2 = futureResult2.get();

                    Long prevLastValue = result1.getLast();
                    result1.addAll(result2.stream().map(e -> e + prevLastValue).toList());
                    result = result1;
                } catch(Exception e) {
                    throw new RuntimeException("Caught exception while computing cumulative list recursively", e);
                }
            }
        }
        return result;
    }

    public static List<Long> compute(List<Long> inputList) {
        Objects.requireNonNull(inputList, "Input list can not be null");
        return CumulativeSumUsingMultiThreading.splitAndCompute(inputList);
    }
}
