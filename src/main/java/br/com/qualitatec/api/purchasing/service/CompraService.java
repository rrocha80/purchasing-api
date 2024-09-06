package br.com.qualitatec.api.purchasing.service;

import br.com.qualitatec.api.purchasing.dto.ClienteFielDto;
import br.com.qualitatec.api.purchasing.dto.ClienteRecomendacaoVinhoDto;
import br.com.qualitatec.api.purchasing.dto.CompraResponse;

import java.util.List;

public interface CompraService {
    CompraResponse findAllCompras();

    CompraResponse findByMaiorCompraAno(int ano);

    List<ClienteFielDto> findByClienteFiel();

    List<ClienteRecomendacaoVinhoDto> findByClienteVinhoRecomenda();
}
