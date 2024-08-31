package dao;

import domain.Produto;
import persistence.JPAUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {

    private JPAUtil jpaUtil;

    public ProdutoDAO (){
        jpaUtil = new JPAUtil();
    }


    public void save(Produto produto){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().persist(produto);
        jpaUtil.getEntityManager().getTransaction().commit();
       // jpaUtil.getEntityManager().close();
    }

    public Produto produtoById(Long id){
        jpaUtil.getEntityManager().getTransaction().begin();
        var produto = jpaUtil.getEntityManager()
                .find(Produto.class, id);
        return produto;
    }

    public List<Produto> getAllProdutos(){

        //utilizando jpql
        jpaUtil.getEntityManager().getTransaction().begin();
       // String jpql = "select p from Produto p";
        var query = jpaUtil.getEntityManager()
                .createNamedQuery("produto.getAll");
        return query.getResultList();
    }

    public List<Produto> getAllProdutosByPreco(BigDecimal valor){
        //utilizando jpql

        // String jpql = "select p from Produto p";
        var query = jpaUtil.getEntityManager()
                .createNamedQuery("produto.byPrice");
        query.setParameter("preco", valor);
        return query.getResultList();
    }

    public Produto findByName(String nome){
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager()
                .createNamedQuery("produto.byName");
        query.setParameter("nome", nome);
           return (Produto)query.getSingleResult();
    }

    public List<Produto> findByNameLike(String nome){
        jpaUtil.getEntityManager().getTransaction().begin();
        var query = jpaUtil.getEntityManager()
                .createNamedQuery("produto.byNameLike");
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

    public String delete(Long id){
        var produtoParaExcluir = produtoById(id);
        jpaUtil.getEntityManager().remove(produtoParaExcluir);
        jpaUtil.getEntityManager().getTransaction().commit();
        return produtoParaExcluir.getNome().concat(" excluído com sucesso!");
    }
    public String update(Long id, String nome){
        var produtoParaAlterar = produtoById(id);
        produtoParaAlterar.setNome(nome);
        jpaUtil.getEntityManager().merge(produtoParaAlterar);
        jpaUtil.getEntityManager().getTransaction().commit();
        return produtoParaAlterar.getNome().concat(" alterado com sucesso!");
    }
}
