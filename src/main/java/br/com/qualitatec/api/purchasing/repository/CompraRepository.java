package br.com.qualitatec.api.purchasing.repository;

import br.com.qualitatec.api.purchasing.Model.Compra;
import br.com.qualitatec.api.purchasing.dto.ClienteFielDto;
import br.com.qualitatec.api.purchasing.dto.ClienteRecomendacaoVinhoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query(value = "SELECT * FROM Compra WHERE clienteId = :clienteId", nativeQuery = true)
    List<Compra> findByCliente(@Param("clienteId") Long clienteId);

    @Query(value = "SELECT c.* FROM Compra c INNER JOIN Produto p ON p.codigo = c.codigo WHERE p.ano_compra = :ano", nativeQuery = true)
    List<Compra> findByMaiorCompraAno(@Param("ano") int ano);

    @Query(value = "SELECT TOP 3 A.NOME, A.CPF, A.QTD AS QTDVENDAS,(\n" +
            "SELECT SUM(CO.TOTAL)\n" +
            "FROM COMPRA CO\n" +
            " INNER JOIN CLIENTE CL ON CL.CLIENTE_ID = CO.CLIENTEID\n" +
            " INNER JOIN PRODUTO P ON P.CODIGO = CO.CODIGO\n" +
            "WHERE CO.CLIENTEID = A.CLIENTEID\n" +
            ") AS TOTAL\n" +
            "FROM \n" +
            "(\n" +
            "SELECT CO.CLIENTEID, CL.NOME, CL.CPF, COUNT(CL.CPF) AS QTD\n" +
            "FROM COMPRA CO\n" +
            " INNER JOIN CLIENTE CL ON CL.CLIENTE_ID = CO.CLIENTEID\n" +
            " INNER JOIN PRODUTO P ON P.CODIGO = CO.CODIGO\n" +
            "GROUP BY CO.CLIENTEID, NOME, CPF\n" +
            ") A\n" +
            "ORDER BY TOTAL DESC, QTD DESC", nativeQuery = true)
    List<ClienteFielDto> findByClienteFiel();

    @Query(value = "SELECT CL.NOME, CL.CPF, P.TIPO_VINHO AS VINHORECOMENDADO, COUNT(P.TIPO_VINHO) AS QTD\n" +
            "FROM COMPRA CO\n" +
            " INNER JOIN CLIENTE CL ON CL.CLIENTE_ID = CO.CLIENTEID\n" +
            " INNER JOIN PRODUTO P ON P.CODIGO = CO.CODIGO\n" +
            "GROUP BY CL.NOME, CL.CPF, P.TIPO_VINHO\n" +
            "HAVING QTD > 1\n" +
            "ORDER BY CL.NOME, COUNT(P.TIPO_VINHO) DESC", nativeQuery = true)
    List<ClienteRecomendacaoVinhoDto> findByClienteVinhoRecomenda();


}
