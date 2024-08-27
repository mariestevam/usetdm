package br.com.usetdm.model;
import br.com.usetdm.enums.Funcao;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class Funcionario extends Pessoa{

    private Funcao funcao;
    private Double salario;
}