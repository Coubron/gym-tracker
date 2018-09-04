package naimaier.gymtracker.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.persistence.PersistenceException;
import naimaier.gymtracker.dao.UsuarioDAO;
import naimaier.gymtracker.model.Sexo;
import naimaier.gymtracker.model.Usuario;
import naimaier.gymtracker.util.FacesUtil;
import naimaier.gymtracker.util.MD5;

@ManagedBean
public class CadastroUsuarioBean implements Serializable {

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sexo[] getSexo() {
        return Sexo.values();
    }

    public String salvar() {
        this.usuario.setPermissao("user");
        this.usuario.setSenha(MD5.convert(this.usuario.getSenha()));

        if (new UsuarioDAO().porNome(this.usuario.getNome()) == null) {
            try {
                new UsuarioDAO().salvar(usuario);
            } catch (PersistenceException e) {
                FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao criar usuario");
                return null;
            }
        } else {
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Nome de usuario ja existe. Escolha outro nome.");
            return null;
        }

        return "CadastroUsuarioSucesso";
    }
}
