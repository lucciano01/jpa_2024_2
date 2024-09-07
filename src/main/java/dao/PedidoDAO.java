package dao;

import domain.Pedido;
import persistence.JPAUtil;

public class PedidoDAO {
    private JPAUtil jpaUtil;

    public PedidoDAO(){
        jpaUtil = new JPAUtil();
    }

    public void save(Pedido pedido){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().merge(pedido);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }
}
