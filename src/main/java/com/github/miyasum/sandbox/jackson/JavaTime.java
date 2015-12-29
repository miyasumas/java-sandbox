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

public class JavaTime {

	static class Data {
		public String name;

		@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmm")
		public LocalDateTime date;

		@Override
		public String toString() {
			return String.format("Data [name=%s, date=%s]", name, date);
		}
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
				.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

		JavaTime.Data data = new JavaTime.Data();
		data.name = "aaa";
		data.date = LocalDateTime.now();
		System.out.println(mapper.writeValueAsString(data));

		String json = "{\"name\": \"aaa\", \"date\": \"201201010000\"}";
		JavaTime.Data obj = mapper.readValue(json, JavaTime.Data.class);
		System.out.println(obj);
	}
}
