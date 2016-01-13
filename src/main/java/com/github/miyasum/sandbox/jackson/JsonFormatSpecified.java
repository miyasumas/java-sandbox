package com.github.miyasum.sandbox.jackson;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonFormatSpecified {

	static class Data {
		@JsonFormat(shape = Shape.STRING)
		public int no;

		public String name;

		@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmm")
		public LocalDateTime date;

		@Override
		public String toString() {
			return String.format("Data [no=%s, name=%s, date=%s]", no, name, date);
		}
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
				.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

		JsonFormatSpecified.Data data = new JsonFormatSpecified.Data();
		data.no = 1;
		data.name = "aaa";
		data.date = LocalDateTime.now();
		System.out.println(mapper.writeValueAsString(data));

		String json = "{\"no\": \"1\",\"name\": \"aaa\", \"date\": \"201201010000\"}";
		JsonFormatSpecified.Data obj = mapper.readValue(json, JsonFormatSpecified.Data.class);
		System.out.println(obj);
	}
}
