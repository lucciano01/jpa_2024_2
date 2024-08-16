package domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "produto.getAll", query = "select p from Produto p"),
        @NamedQuery(name = "produto.byPrice", query = "select p from Produto p where p.preco > :preco")
})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // TODO -> estrategia de geracao de id
    // @SequenceGenerator(name = "produto_sequence", initialValue = 1, allocationSize = 50)
    private Long id;
    private String nome;
    private BigDecimal preco;
    @Column(name = "descricao_produto")
    private String descricao;
    private String categoria;
    private Boolean disponivel;

}
