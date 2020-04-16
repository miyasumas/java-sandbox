package com.github.miyasum.sandbox.guava;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * MapDifference
 */
public class MapDifferenceSample {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Map<String, Integer> left = ImmutableMap.of("aaa", 1);
    Map<String, Integer> right = ImmutableMap.of("bbb", 2, "aaa", 10);
    MapDifference<String, Integer> diff = Maps.difference(left, right);

    System.out.println(diff.entriesDiffering().keySet());
  }
}
