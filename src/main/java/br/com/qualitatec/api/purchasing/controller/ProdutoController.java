package br.com.qualitatec.api.purchasing.controller;

import br.com.qualitatec.api.purchasing.Model.Produto;
import br.com.qualitatec.api.purchasing.dto.ProdutoDto;
import br.com.qualitatec.api.purchasing.repository.ProdutoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/search-all")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }
}
