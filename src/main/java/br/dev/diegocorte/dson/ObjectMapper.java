package br.dev.diegocorte.dson;

import java.lang.reflect.Field;
import java.util.Objects;

import br.dev.diegocorte.dson.annotations.JsonProperty;
import br.dev.diegocorte.dson.annotations.JsonSerializable;

public class ObjectMapper {

	public String writeValueAsString(Object object) throws IllegalArgumentException, IllegalAccessException {

		validadeObject(object);
		Class<?> clazz = object.getClass();
		isSerializable(clazz);

		StringBuilder builder = new StringBuilder();
		builder.append("{");

		for (Field field : clazz.getDeclaredFields()) {

			if (isJsonProperty(field)) {

				field.setAccessible(true);
				String customName = field.getAnnotation(JsonProperty.class).value();
				customName = customName.isEmpty() ? field.getName() : customName;

				builder.append("\"")
						.append(customName)
						.append("\"")
						.append(":");

				if (field.getType().equals(String.class)) {

					builder.append("\"")
							.append((String) field.get(object))
							.append(("\""));

				} else {
					builder.append(String.valueOf(field.get(object)));
				}
				
				builder.append(",");
				
			}

		}

		builder.setLength(builder.length() - 1);
		builder.append("}");
		return builder.toString();

	}

	private void validadeObject(Object object) {
		if (Objects.isNull(object)) {
			throw new JsonSerializationException("Object is null.");
		}
	}

	private boolean isSerializable(Class<?> clazz) {
		if (clazz.isAnnotationPresent(JsonSerializable.class)) {
			return true;
		}

		throw new JsonSerializationException(clazz + " is not serializable.");
	}

	private boolean isJsonProperty(Field field) {
		return field.isAnnotationPresent(JsonProperty.class);
	}

}
