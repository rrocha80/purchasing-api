package br.com.qualitatec.api.purchasing.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Compra")
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long compraId;

    @ManyToOne
    @JoinColumn(name="CLIENTEID", nullable=false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="codigo", nullable=false)
    private Produto produto;

    private int quantidade;

    private Double Total;
}
