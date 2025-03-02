package com.engineering.reverse.util;

import java.time.Duration;
import java.time.Instant;

public class StopWatch {
  private long startTime;
  private long endTime;

  public StopWatch() {
    reset();
  }

  public StopWatch(long startTime) {
    this.startTime = startTime;
  }

  public StopWatch(long startTime, long endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public void reset() {
    startTime = 0;
    endTime = 0;
  }

  public void start() {
    startTime = System.currentTimeMillis();
  }

  public void end() {
    endTime = System.currentTimeMillis();
  }

  public String computeElapsedTime() {
    if ((startTime <= 0) || (endTime <= 0)) {
      throw new RuntimeException("Start or end is not initialized.");
    }

    Duration duration =
        Duration.between(Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime));

    long hours = duration.toHours();
    long minutes = duration.toMinutesPart();
    long seconds = duration.toSecondsPart();
    long milliseconds = duration.toMillisPart();

    reset();
    return String.format("%dh %dm %ds %dms", hours, minutes, seconds, milliseconds);
  }
}
