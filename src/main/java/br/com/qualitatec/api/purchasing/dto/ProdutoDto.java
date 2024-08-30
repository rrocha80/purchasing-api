package br.com.qualitatec.api.purchasing.dto;


import br.com.qualitatec.api.purchasing.Model.Produto;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoDto {
    private List<Produto> produtos;
}
