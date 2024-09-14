package dao;

import domain.Estoque;
import domain.Produto;
import persistence.JPAUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Objects;

public class EstoqueDAO {

    private JPAUtil jpaUtil;
    private ArrayList<Produto> estoqueInsuficente;


    public EstoqueDAO(){
       this.jpaUtil = new JPAUtil();
    }

    public void save(Estoque estoque){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().merge(estoque);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public void persist(Estoque estoque){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().persist(estoque);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public void updateProdutoId(Long idProduto, int quantidade){
       try{

           Query query = jpaUtil.getEntityManager()
                   .createNamedQuery("produtoEstoqueId");
           query.setParameter("idProduto", idProduto);
           var estoque = (Estoque) query.getSingleResult();
           if(Objects.nonNull(estoque)){
               if(estoque.getQuantidade() < quantidade){
                   this.getEstoqueInsuficente().add(estoque.getProduto());
               }else{
                   estoque.setQuantidade(estoque.getQuantidade()
                           - quantidade);
                   jpaUtil.getEntityManager().merge(estoque);
                   jpaUtil.getEntityManager().getTransaction().commit();

               }

           }

       }catch (Exception ex){
           System.out.println("Erro: " +ex.getMessage());
       }

    }

    public ArrayList<Produto> getEstoqueInsuficente() {
        if(estoqueInsuficente == null){
            estoqueInsuficente = new ArrayList<>();
        }
        return  estoqueInsuficente;
    }

    public void setEstoqueInsuficente() {
        this.estoqueInsuficente = new ArrayList();
    }
}
