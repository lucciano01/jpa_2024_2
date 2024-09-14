package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@NamedQueries({
        @NamedQuery(name = "produtoEstoque", query = "select e.produto.nome from Estoque e where e.quantidade > 1 and  e.produto.id = :id "),
        @NamedQuery(name = "produtoEstoqueId", query = "select e from Estoque e where e.produto.id = :idProduto ")
})
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   // @OneToOne(cascade = CascadeType.ALL)
    Produto produto;

    @Column(columnDefinition="default '0'")
    private Integer quantidade;
}
