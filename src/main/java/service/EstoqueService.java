package service;

import dao.EstoqueDAO;
import dao.ProdutoDAO;
import domain.Estoque;
import domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class EstoqueService {

    private EstoqueDAO estoqueDAO;
    private ProdutoDAO produtoDAO;

    public EstoqueService(){
        estoqueDAO = new EstoqueDAO();
        produtoDAO = new ProdutoDAO();
    }

    public void save(Estoque estoque){
        estoqueDAO.save(estoque);
    }


    public void persist(Estoque estoque){
        estoqueDAO.persist(estoque);
    }
}
