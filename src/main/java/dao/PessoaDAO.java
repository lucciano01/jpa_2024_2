package dao;

import domain.Pessoa;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import persistence.JPAUtil;

@RequiredArgsConstructor
public class PessoaDAO {

    private final JPAUtil jpaUtil;

    public void save(Pessoa pessoa){
        jpaUtil.getEntityManager().getTransaction().begin();
        jpaUtil.getEntityManager().persist(pessoa);
        jpaUtil.getEntityManager().getTransaction().commit();
        jpaUtil.getEntityManager().close();
    }

    public Pessoa findById(Long id){
        jpaUtil.getEntityManager().getTransaction().begin();
        return jpaUtil.getEntityManager().find(Pessoa.class, id);
    }

    public void closeEntityManager(){
        jpaUtil.getEntityManager().close();
    }
}
