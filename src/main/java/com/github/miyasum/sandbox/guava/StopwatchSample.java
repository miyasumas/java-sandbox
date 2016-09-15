package com.github.miyasum.sandbox.guava;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.google.common.base.Stopwatch;

/**
 * Stopwatch
 */
public class StopwatchSample {

	public static void main(String[] args) {
		Stopwatch stopwatch = Stopwatch.createStarted();

		IntStream.range(0, 1000).parallel().forEach(i -> {
			try {
				TimeUnit.MILLISECONDS.sleep(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "[msec]");
	}
}
