package br.dev.diegocorte.dson;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.dev.diegocorte.dson.models.SerializableObject;

public class SerializationTest {

  @Test
  public void objectToJsonString() throws IllegalArgumentException, IllegalAccessException {

    SerializableObject object;
    object = new SerializableObject(2023L, "Hello World!!", 14, true, "Shouldn't be here.");
    ObjectMapper mapper = new ObjectMapper();

    String json = mapper.writeValueAsString(object);
    String expectedJson = "{\"longField\":2023,\"stringField\":\"Hello World!!\",\"intField\":14,\"booleanField\":true}";

    assertEquals(expectedJson, json);
  }

  @Test
  public void writeNonSerializableObject() {
    record NonSerializableObject(String someText) {
    }

    var object = new NonSerializableObject("Hello World!");
    ObjectMapper mapper = new ObjectMapper();

    assertThrows(RuntimeException.class, () -> {
      mapper.writeValueAsString(object);
    });

  }

}