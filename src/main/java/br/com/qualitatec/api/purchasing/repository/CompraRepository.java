package br.com.qualitatec.api.purchasing.repository;

import br.com.qualitatec.api.purchasing.Model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
