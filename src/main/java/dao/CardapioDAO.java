package dao;

import domain.Cardapio;
import persistence.JPAUtil;

public class CardapioDAO {

    private JPAUtil jpaUtil;

    public CardapioDAO(){
        this.jpaUtil = new JPAUtil();
    }

    public void save(Cardapio cardapio){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().persist(cardapio);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }
    public void update(Cardapio cardapio){
       // jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().merge(cardapio);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public Cardapio cardapio(long id){
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Cardapio.class, id);
     }
}
