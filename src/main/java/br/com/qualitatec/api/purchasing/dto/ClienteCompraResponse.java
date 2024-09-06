package br.com.qualitatec.api.purchasing.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteCompraResponse {
    private String nome;
    private String cpf;
    private List<CompraDto> compras;

}
