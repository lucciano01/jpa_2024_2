import dao.CardapioDAO;
import dao.PedidoDAO;
import dao.PessoaDAO;
import dao.ProdutoDAO;
import domain.*;
import persistence.JPAUtil;
import service.PedidoService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
//                .produtos(List.of(Produto.builder()
//                                .categoria("Entrada")
//                                .descricao("Oriental")
//                                .disponivel(true)
//                                .preco(new BigDecimal("50.5"))
//                                .nome("Sushi")
//                        .build()))
                .produtos(produtos)
                .build();
       // cardapioDAO.save(cardapio);

        var cardapioExistente = cardapioDAO.cardapio(7L);
        cardapioExistente.setProdutos(produtos);

        //cardapioDAO.update(cardapioExistente);

        var cliente  = pessoaDAO.findById(4L);

        var itensPedidos =
                new ArrayList<ItemCardapio>();
        var item1 = new ItemCardapio();
        item1.setQuantidade(1);
        item1.setProduto(produtos.get(0));
        item1.setSubTotal(item1.calculaSubtotal().doubleValue());

        var item2 = new ItemCardapio();
        item2.setQuantidade(3);
        item2.setProduto(produtos.get(1));
        item2.setSubTotal(item2.calculaSubtotal().doubleValue());

        var item3 = new ItemCardapio();
        item3.setQuantidade(5);
        item3.setProduto(produtos.get(3));
        item3.setSubTotal(item3.calculaSubtotal().doubleValue());

        itensPedidos.add(item1);
        itensPedidos.add(item2);
        itensPedidos.add(item3);

        var pedidoService = new PedidoService();

        pedidoService.setCliente(cliente);
        pedidoService.setItens(itensPedidos);

        System.out.println("Cliente:"+ cliente.getNome());
        itensPedidos.forEach(
                i -> System.out.println(
                        "Item: " +i.getProduto().getNome()+
                        " - Quantidade: " +i.getQuantidade()+
                        " - Preco: " +i.getProduto().getPreco()
                        +"- Subtotal: "+ i.calculaSubtotal())

        );
        var total = new BigDecimal(BigInteger.ZERO);
        for(ItemCardapio i : itensPedidos){
            total = total.add(pedidoService.subtotal(i));
        }
        System.out.println("Valor Total: " +total);

        var pedido = Pedido.builder()
                .pessoa(cliente)
                .itensPedido(itensPedidos)
                .total(total)
                .build();
        var pedidoDAO = new PedidoDAO();
        pedidoDAO.save(pedido);




    }
}
