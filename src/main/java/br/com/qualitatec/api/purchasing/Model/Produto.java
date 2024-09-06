package br.com.qualitatec.api.purchasing.Model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Produto")
public class Produto implements Serializable {

    @Id
    private Long codigo;

    @SerializedName("tipo_vinho")
    private String tipoVinho;

    private Double preco;

    private int safra;

    @SerializedName("ano_compra")
    @Column(name = "ANO_COMPRA")
    private int anoCompra;
}
