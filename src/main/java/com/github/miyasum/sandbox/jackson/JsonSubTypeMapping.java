package com.github.miyasum.sandbox.jackson;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonSubTypeMapping {

	public static class Zoo {
		public Animal animal;

		@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
		@JsonSubTypes({
				@JsonSubTypes.Type(value = Dog.class, name = "dog"),
				@JsonSubTypes.Type(value = Cat.class, name = "cat")
		})
		public static abstract class Animal {
			public String name;

			@Override
			public String toString() {
				return String.format("Animal [name=%s]", name);
			}
		}

		public static class Dog extends Animal {
			public double barkVolume;

			@Override
			public String toString() {
				return String.format("Dog [barkVolume=%s, name=%s]", barkVolume, name);
			}
		}

		@JsonTypeName("cat")
		public static class Cat extends Animal {
			boolean likesCream;
			public int lives;

			@Override
			public String toString() {
				return String.format("Cat [likesCream=%s, lives=%s, name=%s]", likesCream, lives, name);
			}
		}

		@Override
		public String toString() {
			return String.format("Zoo [animal=%s]", animal);
		}
	}

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper()
				.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

		{
			Zoo zoo = new Zoo();
			zoo.animal = new Zoo.Dog();
			zoo.animal.name = "aaa";
			String json = mapper.writeValueAsString(zoo);
			System.out.println(json);
		}
		{
			String json = "{\"dog\":{\"name\":\"aaa\"}}";
			Zoo zoo = mapper.readValue(json, Zoo.class);
			System.out.println(zoo);
		}
	}
}
