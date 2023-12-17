package tech.chilo.sa.controler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.chilo.sa.entites.Client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "client")
public class ClientController {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping( consumes = APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Client client) {

    }
}
