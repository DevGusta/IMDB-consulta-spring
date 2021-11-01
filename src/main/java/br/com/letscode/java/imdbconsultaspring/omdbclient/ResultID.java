package br.com.letscode.java.imdbconsultaspring.omdbclient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Arrays;

@Value
public class ResultID {
    String title;
    String imdbId;
    int year;

    public ResultID(String title, String imdbId, int year) {
        this.title = title;
        this.imdbId = imdbId;
        this.year = year;
    }



    @JsonCreator
    public ResultID(String title, String imdbId, String year) {
        this.title = title;
        this.imdbId = imdbId;
        this.year =  convertYear(year);
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
}

