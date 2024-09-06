package br.com.qualitatec.api.purchasing.controller;

import br.com.qualitatec.api.purchasing.Model.Produto;
import br.com.qualitatec.api.purchasing.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/search-all")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/search/{codigo}")
    public ResponseEntity<Optional<Produto>> findById(@PathVariable("codigo") Long codigo) {
        return ResponseEntity.ok().body(repository.findById(codigo));
    }
}
