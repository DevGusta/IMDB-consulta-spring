package br.com.letscode.java.imdbconsultaspring.archivecsv;

import br.com.letscode.java.imdbconsultaspring.omdbclient.MovieMinimal;
import br.com.letscode.java.imdbconsultaspring.omdbclient.ResultSearch;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControlCache {
    private final String filePath;
    private final String fileName = "cache.csv";
    private final ResultSearch resultSearchCache;

    public ControlCache() throws URISyntaxException {
        this.filePath = getFileFromResources();
        this.resultSearchCache = new ResultSearch();
    }

    private String getFileFromResources() throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource(fileName);
        File file = Paths.get(url.toURI()).toFile();
        return file.getAbsolutePath();
    }

    public void writeLine(ResultSearch resultSearch) throws IOException {
        StringBuilder str = new StringBuilder();
        for (MovieMinimal movie: resultSearch.getResultList()) {
            str.append(movie.getImdbId()).append("; ").append(movie.getTitle()).append("; ").append(movie.getYear()).append("\n");
        }
        Files.write(Path.of(this.filePath), str.toString().getBytes(), StandardOpenOption.APPEND);
    }

    public ResultSearch getMovies(String movieTitle) {
        try (Stream<String> stream = Files.lines(Path.of(this.filePath))) {
            List <MovieMinimal> movies;
            movies =stream
                    .map(l -> l.split("; "))
                    .filter(l -> l[1].toLowerCase().contains(movieTitle.toLowerCase()))
                    .map(MovieMinimal::fromLine)
                    .collect(Collectors.toList());

            this.resultSearchCache.setResultList(movies);
            return this.resultSearchCache;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
