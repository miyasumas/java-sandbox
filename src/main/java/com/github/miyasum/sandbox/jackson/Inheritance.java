package com.github.miyasum.sandbox.jackson;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author MIYASAKA Yasumasa
 */
public class Inheritance {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
				.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

		String json = "{\"name\": \"aaa\", \"type\": \"bbb\", \"no\":1}";
		B obj = mapper.readValue(json, B.class);
		System.out.println(obj);
	}

}

class A {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("A [name=%s]", name);
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class B extends A {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("B [type=%s, toString()=%s]", type, super.toString());
	}
}
