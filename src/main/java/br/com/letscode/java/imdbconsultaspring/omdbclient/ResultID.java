package br.com.letscode.java.imdbconsultaspring.omdbclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultID {
    @JsonProperty("Movie")
    private MovieMinimal movieMinimal;
}
