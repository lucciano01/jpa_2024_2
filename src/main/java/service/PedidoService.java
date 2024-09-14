package service;

import dao.CardapioDAO;
import dao.EstoqueDAO;
import domain.ItemCardapio;
import domain.Pessoa;
import domain.Produto;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoService {

    private Pessoa cliente;
    private CardapioDAO cardapioDAO;
    private EstoqueDAO estoqueDAO;

    private List<ItemCardapio> itens;

    public BigDecimal subtotal(ItemCardapio i){
       var subTotal = new BigDecimal(BigInteger.ZERO);
             subTotal = subTotal.add(
                   i.getProduto().getPreco()
                   .multiply(new BigDecimal(i.getQuantidade())));
        return subTotal;
    }

    public void updateEstoque(ItemCardapio ic){
        if(estoqueDAO == null ){
            estoqueDAO = new EstoqueDAO();
        }
        estoqueDAO.updateProdutoId (
                ic.getProduto().getId(),
                ic.getQuantidade()
        );

    }

    public ArrayList<Produto> produtosSemEstoque(){
        return estoqueDAO.getEstoqueInsuficente();
    }

    public void resetProdutosSemEstoque(){
        estoqueDAO.setEstoqueInsuficente();
    }

}
