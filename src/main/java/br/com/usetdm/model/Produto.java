package br.com.usetdm.model;

import br.com.usetdm.enums.Tipoproduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true)
    private Long id;

    @NotEmpty(message = "O codigo deve ser informado")
    private String codigo;

    @NotEmpty(message = "O nome deve ser informado")
    private String nome;

    @NotEmpty(message = "O valor deve ser informado")
    private Double valor;

    @NotEmpty(message = "A descrição deve ser informado")
    private String descricao;

    @NotEmpty(message = "Coloque uma imagem do produto")
    private String imagem;

    private Tipoproduto tipoproduto;

    //@JsonManagedReference
}
