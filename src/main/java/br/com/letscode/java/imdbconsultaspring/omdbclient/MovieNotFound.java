package br.com.letscode.java.imdbconsultaspring.omdbclient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFound extends RuntimeException{
    public MovieNotFound(String id) {
        super("Filme com id " + id + " não encontado.");
    }
}
