package br.com.qualitatec.api.purchasing.integration;

import br.com.qualitatec.api.purchasing.Model.Cliente;
import br.com.qualitatec.api.purchasing.Model.Compra;
import br.com.qualitatec.api.purchasing.Model.Produto;
import br.com.qualitatec.api.purchasing.dto.Response;
import br.com.qualitatec.api.purchasing.repository.ClienteRepository;
import br.com.qualitatec.api.purchasing.repository.CompraRepository;
import br.com.qualitatec.api.purchasing.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IntegrationServiceImpl implements IntegrationService {

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

            e.getCompras().forEach(c -> {
                Optional<Produto> produto = produtoRepository.findById(c.getCodigo());
                Compra compra = Compra.builder()
                        .cliente(cliente)
                        .produto(produto.get())
                        .quantidade(c.getQuantidade())
                        .Total(produto.get().getPreco() * c.getQuantidade())
                        .build();
                compraRepository.save(compra);
            });
        });
    }

}
