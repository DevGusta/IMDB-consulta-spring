package br.com.letscode.java.imdbconsultaspring.omdbclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Arrays;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieMinimal {

    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Title")
    private String title;
    private Integer year;

    @JsonProperty("Year")
    public void setYear(String year) {
        this.year = convertYear(year);
    }

    private int convertYear(final String year) {
        if (year.matches("\\d+")) {
            return Integer.parseInt(year);
        }
        return Arrays.stream(year.split("\\D"))
                .map(Integer::parseInt)
                .findFirst()
                .orElseThrow();
    }

    public static MovieMinimal fromLine(String[] line) {
        return new MovieMinimal(
                line[0],
                line[1],
                Integer.parseInt(line[2])
        );
    }
}
