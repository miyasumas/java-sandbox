package com.github.miyasum.sandbox.jackson;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;

public class Unwrapped {

  public static void main(String[] args)
    throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper()
      .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

    Data data = new Data();
    data.name = "aaa";
    data.common.type = "bbb";
    data.common.no = 1;
    System.out.println(mapper.writeValueAsString(data));

    String json = "{\"name\": \"aaa\", \"type\": \"bbb\", \"no\":1}";
    Data obj = mapper.readValue(json, Data.class);
    System.out.println(obj);
  }
}

class Data {

  public String name;

  @JsonUnwrapped
  public Common common = new Common();

  @Override
  public String toString() {
    return String.format("Data [name=%s, common=%s]", name, common);
  }
}

class Common {

  public String type;

  public int no;

  @Override
  public String toString() {
    return String.format("Common [type=%s, no=%s]", type, no);
  }
}
