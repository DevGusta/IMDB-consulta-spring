package br.com.letscode.java.imdbconsultaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@SpringBootApplication
public class ImdbConsultaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImdbConsultaSpringApplication.class, args);
    }

}
