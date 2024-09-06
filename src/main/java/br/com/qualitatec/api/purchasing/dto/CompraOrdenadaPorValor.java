package br.com.qualitatec.api.purchasing.dto;

import br.com.qualitatec.api.purchasing.Model.Produto;
import lombok.Data;

import java.util.List;

@Data
public class CompraOrdenadaPorValor {
    private String nome;
    private String cpf;
    private List<Produto> produtos;
    private int quantidade;

}
