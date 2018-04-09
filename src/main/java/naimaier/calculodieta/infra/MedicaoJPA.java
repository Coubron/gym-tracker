package naimaier.calculodieta.infra;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import naimaier.calculodieta.model.Medicao;
import naimaier.calculodieta.util.JPAUtil;
import naimaier.calculodieta.repository.Medicoes;

public class MedicaoJPA implements Medicoes {

    @Override
    public List<Medicao> todas(int usuario) {

        try {
            EntityManager em = new JPAUtil().getEntityManager();

            Query query = em.createQuery("select m from Medicao m where m.usuario = '" + usuario + "' ORDER BY m.dia ASC");

            List<Medicao> medicoes = query.getResultList();

            em.close();
            return medicoes;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Medicao porCodigo(int codigo) {

        EntityManager em = new JPAUtil().getEntityManager();

        Medicao medicao = em.find(Medicao.class, codigo);

        em.close();

        return medicao;
    }

    @Override
    public void salvar(Medicao medicao) throws PersistenceException{
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        em.merge(medicao);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remover(Medicao medicao) throws PersistenceException{
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        em.remove(em.find(Medicao.class, medicao.getId()));

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Medicao ultima(int usuario) {

        try {
            EntityManager em = new JPAUtil().getEntityManager();

            Query query = em.createQuery("select m from Medicao m where m.usuario = '" + usuario + "' ORDER BY m.dia DESC");

            Medicao ultimaMedicao = (Medicao) query.setMaxResults(1).getSingleResult();

            em.close();
            return ultimaMedicao;
        } catch (NoResultException e) {
            return null;
        }
    }
}
