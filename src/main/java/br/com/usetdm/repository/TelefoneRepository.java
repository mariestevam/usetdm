package br.com.usetdm.repository;

import br.com.usetdm.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    List<Telefone> findByPessoaId(Long idPessoa);
    //List<Telefone> findByPessoaId(Long idPessoa);
}
