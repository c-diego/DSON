package br.dev.diegocorte.dson.models;

import br.dev.diegocorte.dson.annotations.JsonProperty;
import br.dev.diegocorte.dson.annotations.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerializable
public class SerializableObject {

  @JsonProperty
  private long longField;

  @JsonProperty
  private String stringField;

  @JsonProperty
  private int intField;

  @JsonProperty
  private boolean booleanField;

  private String nonAnnotedField;

}