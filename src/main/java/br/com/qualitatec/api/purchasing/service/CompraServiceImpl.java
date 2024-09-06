package br.com.qualitatec.api.purchasing.service;

import br.com.qualitatec.api.purchasing.Model.Cliente;
import br.com.qualitatec.api.purchasing.Model.Compra;
import br.com.qualitatec.api.purchasing.Model.Produto;
import br.com.qualitatec.api.purchasing.dto.ClienteFielDto;
import br.com.qualitatec.api.purchasing.dto.ClienteRecomendacaoVinhoDto;
import br.com.qualitatec.api.purchasing.dto.CompraListDto;
import br.com.qualitatec.api.purchasing.dto.CompraResponse;
import br.com.qualitatec.api.purchasing.repository.ClienteRepository;
import br.com.qualitatec.api.purchasing.repository.CompraRepository;
import br.com.qualitatec.api.purchasing.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    CompraListDto dto;

    List<CompraListDto> compraListDto;

    @Override
    public CompraResponse findAllCompras() {
        CompraResponse compraResponse = new CompraResponse();
        List<CompraListDto> compraListDto = new ArrayList<>();

        List<Compra> compras = compraRepository.findAll();

        compras.forEach(c -> {
            dto = new CompraListDto();

            Optional<Cliente> cliente = clienteRepository.findById(c.getCliente().getClienteId());
            dto.setNome(cliente.get().getNome());
            dto.setCpf(cliente.get().getCpf());

            Optional<Produto> produto = produtoRepository.findById(c.getProduto().getCodigo());
            dto.setTipoVinho(produto.get().getTipoVinho());
            dto.setPreco(produto.get().getPreco());
            dto.setSafra(produto.get().getSafra());
            dto.setAnoCompra(produto.get().getAnoCompra());
            dto.setQuantidate(c.getQuantidade());
            dto.setTotal(arredondar(c.getTotal()));
            compraListDto.add(dto);
            compraResponse.setCompras(compraListDto);
        });
        if (Objects.nonNull(compraResponse.getCompras())) {
            Collections.sort(compraResponse.getCompras(), Comparator.comparing(CompraListDto::getTotal));
        }

        return compraResponse;
    }

    @Override
    public CompraResponse findByMaiorCompraAno(int ano) {
        CompraResponse compraResponse = new CompraResponse();
        compraListDto = new ArrayList<>();

        List<Compra> compras = compraRepository.findByMaiorCompraAno(ano);

        compras.forEach(e -> {
            Optional<Cliente> cliente = clienteRepository.findById(e.getCliente().getClienteId());
            Optional<Produto> produto = produtoRepository.findById(e.getProduto().getCodigo());

            compraListDto.add(CompraListDto.builder()
                    .nome(cliente.get().getNome())
                    .cpf(cliente.get().getCpf())
                    .tipoVinho(produto.get().getTipoVinho())
                    .preco(produto.get().getPreco())
                    .safra(produto.get().getSafra())
                    .anoCompra(produto.get().getAnoCompra())
                    .quantidate(e.getQuantidade())
                    .total(arredondar(e.getTotal())).build());

            compraResponse.setCompras(compraListDto);
        });

        if (Objects.nonNull(compraResponse.getCompras())) {
            Optional<CompraListDto> reultMax = compraResponse.getCompras().stream().max(Comparator.comparing(CompraListDto::getTotal));
            compraListDto = new ArrayList<>();
            compraListDto.add(reultMax.get());
        }

        return CompraResponse.builder().compras(compraListDto).build();
    }

    @Override
    public List<ClienteFielDto> findByClienteFiel() {
        return compraRepository.findByClienteFiel();
    }

    @Override
    public List<ClienteRecomendacaoVinhoDto> findByClienteVinhoRecomenda() {
        return compraRepository.findByClienteVinhoRecomenda();
    }

    private static String arredondar(Double valor) {
        return new DecimalFormat("#,##0.00").format(valor);
    }

}
