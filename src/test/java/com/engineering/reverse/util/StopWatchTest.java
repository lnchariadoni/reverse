package com.engineering.reverse.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StopWatchTest {
  @Test
  void validStartAndEnd() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
    }

    stopWatch.end();
    String computed = stopWatch.computeElapsedTime();
    Assertions.assertTrue(computed.startsWith("0h 0m 1s"));
  }

  @Test
  void invalidEnd() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Assertions.assertThrows(RuntimeException.class, stopWatch::computeElapsedTime);
  }

  @Test
  void invalidStart() {
    StopWatch stopWatch = new StopWatch();
    Assertions.assertThrows(RuntimeException.class, stopWatch::computeElapsedTime);
  }

  @Test
  void validStartConstructorAndEnd() {
    StopWatch stopWatch = new StopWatch(System.currentTimeMillis());
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
    }

    stopWatch.end();
    String computed = stopWatch.computeElapsedTime();
    Assertions.assertTrue(computed.startsWith("0h 0m 1s"));
  }
}
