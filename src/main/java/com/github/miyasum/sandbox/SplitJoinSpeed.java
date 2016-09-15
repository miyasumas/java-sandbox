package com.github.miyasum.sandbox;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;

/**
 * 文字列分割の速度計測
 */
public class SplitJoinSpeed {
	
	private static final int MAX_TRIAL = 1000_0000;

	public static void main(String[] args) {
		{
			System.out.println("--- split ---");

			final Pattern pattern = Pattern.compile(",");
			split("Pattern#split()", t -> pattern.split(t));

			split("String#split()", t -> t.split(","));

			final Splitter splitter = Splitter.on(",");
			split("Splitter#on()", t -> splitter.split(t));

			final Splitter splitter2 = Splitter.onPattern(",");
			split("Splitter#onPattern()", t -> splitter2.split(t));
		}

		{
			System.out.println("--- join ---");

			join("String#join()", t -> String.join(",", t));

			join("Stream#collect()", t -> t.stream().collect(Collectors.joining(",")));

			final Joiner joiner = Joiner.on(",");
			join("Joiner#on()", t -> joiner.join(t));
		}
	}

	private static void split(String name, Consumer<String> splitter) {
		String text = "a,b,c,d,e,f,g,h,i";
		Stopwatch stopwatch = Stopwatch.createStarted();
		IntStream.range(0, MAX_TRIAL).mapToObj(i -> text).forEach(splitter);
		System.out.println(name + ": " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "[msec]");
	}

	private static void join(String name, Consumer<List<String>> splitter) {
		List<String> text = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
		Stopwatch stopwatch = Stopwatch.createStarted();
		IntStream.range(0, MAX_TRIAL).mapToObj(i -> text).forEach(splitter);
		System.out.println(name + ": " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "[msec]");
	}
}
