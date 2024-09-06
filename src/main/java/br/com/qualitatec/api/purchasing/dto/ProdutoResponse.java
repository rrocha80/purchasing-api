package br.com.qualitatec.api.purchasing.dto;


import br.com.qualitatec.api.purchasing.Model.Produto;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoResponse {
    private List<Produto> produtos;
}
