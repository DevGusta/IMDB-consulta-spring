package br.com.letscode.java.imdbconsultaspring.rest;

import br.com.letscode.java.imdbconsultaspring.archivecsv.Cache;
import br.com.letscode.java.imdbconsultaspring.omdbclient.MovieMinimalRestRepository;
import br.com.letscode.java.imdbconsultaspring.omdbclient.MovieNotFound;
import br.com.letscode.java.imdbconsultaspring.omdbclient.ResultID;
import br.com.letscode.java.imdbconsultaspring.omdbclient.ResultSearch;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class SearchRestController {
    private final MovieMinimalRestRepository restRepository;

    public SearchRestController(MovieMinimalRestRepository restRepository) {
        this.restRepository = restRepository;
    }

    @GetMapping("/search")
    public ResultSearch search(@RequestParam String title) throws URISyntaxException, IOException {
        Cache cache = new Cache();

        if (cache.getMovies(title).getResultList().size() != 0) {
            return cache.getMovies(title);
        }
        cache.writeLine(this.restRepository.search(title));
        return this.restRepository.search(title);
    }

    //não está funcionado
    @GetMapping("/movies/{id}")
    public ResultID searchId(@PathVariable String id) {

        return this.restRepository.searchId(id);
    }
}