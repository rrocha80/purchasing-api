package br.com.qualitatec.api.purchasing.service;

import br.com.qualitatec.api.purchasing.Model.Cliente;
import br.com.qualitatec.api.purchasing.Model.Compra;
import br.com.qualitatec.api.purchasing.Model.Produto;
import br.com.qualitatec.api.purchasing.dto.Response;
import br.com.qualitatec.api.purchasing.repository.ClienteRepository;
import br.com.qualitatec.api.purchasing.repository.CompraRepository;
import br.com.qualitatec.api.purchasing.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public void alimentarDadosCompras(Response response) {

        response.getCompras().forEach(e -> {
            Cliente cliente = Cliente.builder()
                    .nome(e.getNome())
                    .cpf(e.getCpf())
                    .build();
            clienteRepository.save(cliente);

            Produto produto = produtoRepository.getReferenceById(e.getCompras().get(0).getCodigo());

            Compra compra = Compra.builder()
                    .cliente(cliente)
                    .produto(produto)
                    .quantidade(e.getCompras().get(0).getQuantidade())
                    .build();
        });

    }

}
