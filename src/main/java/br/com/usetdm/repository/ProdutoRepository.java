package br.com.usetdm.repository;

import br.com.usetdm.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCodigoContainingOrNomeContainingOrNumeracaoContaining(String codigo, String nome, String numeracao);
}
