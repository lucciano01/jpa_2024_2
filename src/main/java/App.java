import dao.*;
import domain.*;
import persistence.JPAUtil;
import service.EstoqueService;
import service.PedidoService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

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
                .categoria("Sobremesa")
                .descricao("Sorvete com calda")
                //.id(1L)
                .disponivel(true)
                .nome("Sorvete de Maracujá")
                .preco(BigDecimal.valueOf(13.5))
                .build();

        var produtoDAO = new ProdutoDAO();

        var produtoEstoque = produtoDAO.findById(4L);

        var estoque = Estoque.builder()
                .produto(produtoEstoque)
                .quantidade(2)
                .build();

        var estoqueDAO = new EstoqueDAO();
        //estoqueDAO.save(estoque);


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

//        var cardapio = Cardapio.builder()
////                .produtos(List.of(Produto.builder()
////                                .categoria("Entrada")
////                                .descricao("Oriental")
////                                .disponivel(true)
////                                .preco(new BigDecimal("50.5"))
////                                .nome("Sushi")
//                        .build())
//                .produtos(produtos)
//                .build();
       // cardapioDAO.save(cardapio);

        var cardapioExistente = cardapioDAO.cardapio(7L);
        cardapioExistente.setProdutos(produtos);

        //cardapioDAO.update(cardapioExistente);

        var cliente  = pessoaDAO.findById(4L);

        var itensPedidos =
                new ArrayList<ItemCardapio>();
        var item1 = new ItemCardapio();
        item1.setQuantidade(2);
        item1.setProduto(produtos.get(0));
        item1.setSubTotal(item1.calculaSubtotal().doubleValue());

        var item2 = new ItemCardapio();
        item2.setQuantidade(6);
        item2.setProduto(produtos.get(1));
        item2.setSubTotal(item2.calculaSubtotal().doubleValue());

        var item3 = new ItemCardapio();
        item3.setQuantidade(4);
        item3.setProduto(produtos.get(3));
        item3.setSubTotal(item3.calculaSubtotal().doubleValue());

        itensPedidos.add(item1);
        itensPedidos.add(item2);
        itensPedidos.add(item3);

        var pedidoService = new PedidoService();

        pedidoService.setCliente(cliente);
        pedidoService.setItens(itensPedidos);

        System.out.println("Cliente:"+ cliente.getNome());
//        itensPedidos.forEach(
//                i -> System.out.println(
//                        "Item: " +i.getProduto().getNome()+
//                        " - Quantidade: " +i.getQuantidade()+
//                        " - Preco: " +i.getProduto().getPreco()+
//                        "- Subtotal: "+ i.calculaSubtotal()+
//                        " - " +pedidoService.updateEstoque(i)
//                        );
//        );
        for(ItemCardapio i : itensPedidos){
            System.out.println( "Item: " +i.getProduto().getNome());
            System.out.println( "Quantidade: " +i.getQuantidade());
            System.out.println( "Preço: " +i.getProduto().getPreco());
            System.out.println( "Subtotal: " +i.calculaSubtotal());
            pedidoService.updateEstoque(i);
        }
        System.out.println("Produtos com estoque insuficiente para o pedido:");
        pedidoService.produtosSemEstoque().forEach(
                p -> System.out.print(p.getNome()+" | ")
        );
        System.out.println();
        pedidoService.resetProdutosSemEstoque();
        var total = new BigDecimal(BigInteger.ZERO);
        for(ItemCardapio i : itensPedidos){
            total = total.add(pedidoService.subtotal(i));
        }
        System.out.println("Valor Total: " +total);

//        var pedido = Pedido.builder()
//                .pessoa(cliente)
//                .itensPedido(itensPedidos)
//                .total(total)
//                .build();
//        var pedidoDAO = new PedidoDAO();
//        pedidoDAO.save(pedido);




    }
}
