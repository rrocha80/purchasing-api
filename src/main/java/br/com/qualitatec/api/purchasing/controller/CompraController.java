package br.com.qualitatec.api.purchasing.controller;

import br.com.qualitatec.api.purchasing.Model.Compra;
import br.com.qualitatec.api.purchasing.dto.ClienteFielDto;
import br.com.qualitatec.api.purchasing.dto.ClienteRecomendacaoVinhoDto;
import br.com.qualitatec.api.purchasing.dto.CompraResponse;
import br.com.qualitatec.api.purchasing.repository.CompraRepository;
import br.com.qualitatec.api.purchasing.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class CompraController {
    @Autowired
    CompraRepository repository;

    @Autowired
    CompraService compraService;

    @GetMapping("/search-all")
    public ResponseEntity<List<Compra>> findAll() {
        var compras = repository.findAll();
        if (Objects.isNull(compras)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(compras);
    }

    @GetMapping("/compras")
    public ResponseEntity<CompraResponse> findAllCompras() {
        var compras = compraService.findAllCompras();
        if (compras.getCompras().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(compras);
    }

    @GetMapping("/maior-compra/{ano}")
    public ResponseEntity<CompraResponse> findByMaiorCompraAno(@PathVariable("ano") int ano) {
        var compras = compraService.findByMaiorCompraAno(ano);
        if (compras.getCompras().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(compras);
    }

    @GetMapping("/clientes-fieis")
    public ResponseEntity<List<ClienteFielDto>> findByClienteFiel() {
        var clientes = compraService.findByClienteFiel();
        if (Objects.isNull(clientes)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/recomendacao/cliente/tipo")
    public ResponseEntity<List<ClienteRecomendacaoVinhoDto>> findByClienteVinhoRecomenda() {
        var clientes = compraService.findByClienteVinhoRecomenda();
        if (Objects.isNull(clientes)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(clientes);
    }
}
