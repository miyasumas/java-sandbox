package com.github.miyasum.sandbox.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MIYASAKA Yasumasa
 * @since 2017/05/18
 */
public class JsonIncludeTest {

  public static void main(String[] args) throws JsonProcessingException {
    {
      ObjectMapper mapper = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
      System.out.println(mapper.writeValueAsString(new Root()));
    }
    {
      ObjectMapper mapper = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
      System.out.println(mapper.writeValueAsString(new Root()));
    }
    {
      ObjectMapper mapper = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
      System.out.println(mapper.writeValueAsString(new Root()));
    }
    {
      ObjectMapper mapper = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
      System.out.println(mapper.writeValueAsString(new Root()));
    }
  }

  static class Data {

    public int no;

    public String name = "";

    public Map<String, String> map = new HashMap<>();

    public List<String> list = new ArrayList<>();
  }

  static class Root {

    public Data data = new Data();
  }
}
