package com.engineering.reverse.cumulativesum.imperative;

import com.engineering.reverse.cumulativesum.functional.CumulativeSumUsingArray;
import com.engineering.reverse.cumulativesum.functional.CumulativeSumUsingList;
import com.engineering.reverse.util.StopWatch;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CumulativeSumTest {
  public record InputOutputData(List<Long> input, List<Long> output) {}

  static InputOutputData getSmallInputData() {
    return new InputOutputData(
        Stream.of(1, 3, 5, 8, 10).mapToLong(Integer::longValue).boxed().toList(),
        Stream.of(1, 4, 9, 17, 27).mapToLong(Integer::longValue).boxed().toList());
  }

  static InputOutputData getLargeInputData() {
    return new InputOutputData(
        IntStream.rangeClosed(1, 5000_000)
            .mapToLong(index -> new Random().nextLong())
            .boxed()
            .toList(),
        Collections.emptyList());
  }

  @Test
  void smallCumulativeSumLoopTest() {
    InputOutputData smallInputData = CumulativeSumTest.getSmallInputData();
    List<Long> inputList = smallInputData.input();
    List<Long> expected = smallInputData.output();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingLoop.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using loop for small input list:" + elapsedTime);
    Assertions.assertEquals(expected, computedList);
  }

  @Test
  void smallCumulativeSumListTest() {
    InputOutputData smallInputData = CumulativeSumTest.getSmallInputData();
    List<Long> inputList = smallInputData.input();
    List<Long> expected = smallInputData.output();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingList.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using stream list for small input list:" + elapsedTime);
    Assertions.assertEquals(expected, computedList);
  }

  @Test
  void smallCumulativeSumArrayTest() {
    InputOutputData smallInputData = CumulativeSumTest.getSmallInputData();
    List<Long> inputList = smallInputData.input();
    List<Long> expected = smallInputData.output();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingArray.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using stream array for small input list:" + elapsedTime);
    Assertions.assertEquals(expected, computedList);
  }

  @Test
  void smallCumulativeSumExecutorServiceTest() {
    InputOutputData smallInputData = CumulativeSumTest.getSmallInputData();
    List<Long> inputList = smallInputData.input();
    List<Long> expected = smallInputData.output();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingMultiThreading.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using executor service for small input list:" + elapsedTime);
    Assertions.assertEquals(expected, computedList);
  }

  @Test
  void millionCumulativeSumLoopTest() {
    InputOutputData largeInputData = CumulativeSumTest.getLargeInputData();
    List<Long> inputList = largeInputData.input();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingLoop.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using loop for 5 million input list:" + elapsedTime);
  }

  @Test
  void millionCumulativeSumListTest() {
    InputOutputData largeInputData = CumulativeSumTest.getLargeInputData();
    List<Long> inputList = largeInputData.input();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingList.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using stream list for 5 million input list:" + elapsedTime);
  }

  @Test
  @Disabled
  void millionCumulativeSumArrayTest() {
    InputOutputData largeInputData = CumulativeSumTest.getLargeInputData();
    List<Long> inputList = largeInputData.input();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingArray.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using stream array for 5 million input list:" + elapsedTime);
  }

  @Test
  void millionCumulativeSumExecutorServiceTest() {
    InputOutputData largeInputData = CumulativeSumTest.getLargeInputData();
    List<Long> inputList = largeInputData.input();

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    List<Long> computedList = CumulativeSumUsingMultiThreading.compute(inputList);
    stopWatch.end();
    String elapsedTime = stopWatch.computeElapsedTime();
    System.out.println("Elapsed using executor service for 5 million input list:" + elapsedTime);
  }
}
