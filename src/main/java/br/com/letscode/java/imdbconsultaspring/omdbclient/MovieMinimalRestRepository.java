package br.com.letscode.java.imdbconsultaspring.omdbclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "movieMinimalRest", url = "${omdb.url}")
public interface MovieMinimalRestRepository {
    @GetMapping
    ResultSearch search(@RequestParam("s") String title);

    @GetMapping
    ResultID searchId(@RequestParam("i") String id);

}
