package br.com.qualitatec.api.purchasing.controller;

import br.com.qualitatec.api.purchasing.Model.Cliente;
import br.com.qualitatec.api.purchasing.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @GetMapping("/search-all")
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }
}
