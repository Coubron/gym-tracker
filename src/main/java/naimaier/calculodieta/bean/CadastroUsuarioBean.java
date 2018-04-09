package naimaier.calculodieta.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.persistence.PersistenceException;
import naimaier.calculodieta.infra.UsuarioJPA;
import naimaier.calculodieta.model.Sexo;
import naimaier.calculodieta.model.Usuario;
import naimaier.calculodieta.util.FacesUtil;
import naimaier.calculodieta.util.MD5;

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

        if (new UsuarioJPA().porNome(this.usuario.getNome()) == null) {
            try {
                new UsuarioJPA().salvar(usuario);
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
