package com.github.miyasum.sandbox.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class GetterOnly {

  public static void main(String[] args)
    throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();

    Setter data = new Setter();
    data.setName("aaa");
    System.out.println(mapper.writeValueAsString(data));

    String json = "{\"name\": \"aaa\"}";
    Getter obj = mapper.readValue(json, Getter.class);
    System.out.println(obj);
  }

  static class Getter {

    private String name;

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return String.format("Getter [name=%s]", name);
    }
  }

  static class Setter {

    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return String.format("Setter [name=%s]", name);
    }
  }

}
