import dao.EstoqueDAO;
import dao.ProdutoDAO;
import domain.Estoque;
import domain.Produto;
import service.EstoqueService;

import java.math.BigDecimal;

public class App2 {

    public static void main(String[] args) {
//        var produtoDAO = new ProdutoDAO();
//        var estoqueDAO = new EstoqueDAO();

        var estoqueService = new EstoqueService();

        var estoque = Estoque.builder()
                .produto(Produto.builder().preco(new BigDecimal(25.7))
                        .descricao("Importado")
                        .disponivel(true)
                        .categoria("Alemã")
                        .nome("Tora Alemã")
                        .build())
                .quantidade(15)
                .build();
        estoqueService.persist(estoque);
    }
}
