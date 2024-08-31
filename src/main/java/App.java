import dao.CardapioDAO;
import dao.PessoaDAO;
import dao.ProdutoDAO;
import domain.Cardapio;
import domain.Endereco;
import domain.Pessoa;
import domain.Produto;
import persistence.JPAUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

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
                .descricao("Doces")
                //.id(1L)
                .disponivel(true)
                .nome("Churros")
                .preco(BigDecimal.valueOf(50))
                .build();

        var produtoDAO = new ProdutoDAO();
        //produtoDAO.save(produto);
      // produtoDAO.save(produto2);
       // System.out.println(produtoDAO.produtoById(1L));
        //System.out.println(produtoDAO.getAllProdutos());
        //System.out.println(produtoDAO.getAllProdutosByPreco(BigDecimal.valueOf(30)));
       // System.out.println(produtoDAO.findByName("Churrasc"));
        //System.out.println(produtoDAO.findByNameLike("Bat"));
        //System.out.println(produtoDAO.delete(1L));
        //System.out.println(produtoDAO.update(4L, "Churrasco a Grega"));

        var jpaUtil = new JPAUtil();

        var pessoaDAO = new PessoaDAO(jpaUtil);

        var pessoa = Pessoa.builder()
                .endereco(Endereco.builder()
                        .numero("950")
                        .rua("Av. Principal")
                        .bairro("Centro")
                        .cidade("Cajazeiras")
                        .build())
                .cpf("1234567889004545")
                .nome("Maria")
                .email("maria@email.com")
                .build();

       // pessoaDAO.save(pessoa);

        var cardapioDAO = new CardapioDAO();
        var produtos = produtoDAO.getAllProdutos();

        var cardapio = Cardapio.builder()
                .produtos(List.of(Produto.builder()
                                .categoria("Entrada")
                                .descricao("Oriental")
                                .disponivel(true)
                                .preco(new BigDecimal("50.5"))
                                .nome("Sushi")
                        .build()))
                //.produtos(produtos)
                .build();
        //cardapioDAO.save(cardapio);

        var cardapioExistente = cardapioDAO.cardapio(7L);
        cardapioExistente.setProdutos(produtos);

        cardapioDAO.update(cardapioExistente);

    }
}
