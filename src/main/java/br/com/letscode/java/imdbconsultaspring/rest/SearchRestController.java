package br.com.letscode.java.imdbconsultaspring.rest;

import br.com.letscode.java.imdbconsultaspring.archivecsv.ControlCache;
import br.com.letscode.java.imdbconsultaspring.omdbclient.MovieMinimalRestRepository;
import br.com.letscode.java.imdbconsultaspring.omdbclient.ResultID;
import br.com.letscode.java.imdbconsultaspring.omdbclient.ResultSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class SearchRestController {
    private final MovieMinimalRestRepository restRepository;
    private final MovieIDRestRepository idRestRepository;

    public SearchRestController(MovieMinimalRestRepository restRepository, MovieIDRestRepository idRestRepository){
        this.restRepository = restRepository;
        this.idRestRepository = idRestRepository;
    }

    @GetMapping("/search")
    public ResultSearch search(@RequestParam String title) throws URISyntaxException, IOException {
        ControlCache cache = new ControlCache();

        if (cache.getMovies(title).getResultList().size() != 0){
                return  cache.getMovies(title);
        }
        cache.writeLine(this.restRepository.search(title));
        return this.restRepository.search(title);
    }

    @GetMapping("/movies/{id}")
    public ResultID searchId(@RequestParam@PathVariable String id){
        return this.restRepository.searchId(id);
    }
}