package br.com.qualitatec.api.purchasing.dto;

import br.com.qualitatec.api.purchasing.Model.Compra;
import lombok.Data;

import java.util.List;
@Data
public class CompraDto {
    private List<Compra> compras;
}
