package br.com.qualitatec.api.purchasing.repository;

import br.com.qualitatec.api.purchasing.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
