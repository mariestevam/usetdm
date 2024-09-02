package br.com.usetdm.model;

import br.com.usetdm.enums.Tipoproduto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipoProduto",
        length = 1,
        discriminatorType = DiscriminatorType.STRING
)
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
    private String descrição;

    @NotEmpty(message = "Coloque uma imagem do produto")
    private String imagem;

    private Tipoproduto tipoproduto;





    //@JsonManagedReference
}
