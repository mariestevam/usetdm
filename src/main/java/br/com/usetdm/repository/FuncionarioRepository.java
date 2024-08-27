package br.com.usetdm.repository;

import br.com.usetdm.model.Funcionario;
import br.com.usetdm.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long>{
}