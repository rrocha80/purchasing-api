package br.com.qualitatec.api.purchasing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraListDto {
    private String nome;
    private String cpf;
    private String tipoVinho;
    private Double preco;
    private int safra;
    private int anoCompra;
    private int quantidate;
    private String total;
}
