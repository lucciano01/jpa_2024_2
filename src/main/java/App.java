import dao.ProdutoDAO;
import domain.Produto;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {

        var produto = Produto.builder()
                .categoria("Entrada")
                .descricao("Batata Frita com queijo ralado")
                //.id(1L)
                .disponivel(true)
                .nome("Batata Frita")
                .preco(BigDecimal.valueOf(20))
                .build();

        var produto2 = Produto.builder()
                .categoria("Prato Principal")
                .descricao("Churrasco a moda da casa")
                //.id(1L)
                .disponivel(true)
                .nome("Churrasco")
                .preco(BigDecimal.valueOf(50))
                .build();

        var produtoDAO = new ProdutoDAO();
        //produtoDAO.save(produto);
       // produtoDAO.save(produto2);
       // System.out.println(produtoDAO.produtoById(1L));
        //System.out.println(produtoDAO.getAllProdutos());
        System.out.println(produtoDAO.getAllProdutosByPreco(BigDecimal.valueOf(30)));

    }
}
