package naimaier.gymtracker.dao;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import naimaier.gymtracker.model.Usuario;
import naimaier.gymtracker.repository.Usuarios;
import naimaier.gymtracker.util.JPAUtil;

public class UsuarioDAO implements Usuarios {

    @Override
    public Usuario porCodigo(int codigo) {
        return (new JPAUtil().getEntityManager().find(Usuario.class, codigo));
    }

    /**
    * Busca usuarios pelo nome. Retorna o objeto Usuario, se nao encontrar retorna nulo.
    */
    @Override
    public Usuario porNome(String nome) {
        Usuario user = new Usuario();
        try {
            user = (Usuario) new JPAUtil().getEntityManager().createQuery("select u from Usuario u where u.nome='"+ nome +"'").getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }
    
    @Override
    public Usuario ativo() {
        Usuario user = new Usuario();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        user = this.porNome(request.getRemoteUser());
        
        return user;
    }

    @Override
    public void salvar(Usuario usuario) throws PersistenceException{
            EntityManager em = new JPAUtil().getEntityManager();
            em.getTransaction().begin();

            em.merge(usuario);

            em.getTransaction().commit();
            em.close();
    }
}
