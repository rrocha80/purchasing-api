package br.com.qualitatec.api.purchasing.dto;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<ClienteCompraResponse> compras;
}
