package br.com.usetdm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "O valor deve ser informado")
    private Double valor;

    @NotEmpty(message = "A descrição deve ser informado")
    @Lob
    @Column( length = 7500)
    private String descricao;

    @NotEmpty(message = "As numerações devem ser informadas")
    private String numeracao;

    //@NotEmpty(message = "Coloque uma imagem do produto")
    private String imagem;



    //@JsonManagedReference
}
