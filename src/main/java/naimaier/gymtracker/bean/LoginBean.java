package naimaier.gymtracker.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import naimaier.gymtracker.infra.UsuarioJPA;
import naimaier.gymtracker.model.Usuario;
import naimaier.gymtracker.repository.Usuarios;
import naimaier.gymtracker.util.FacesUtil;

@ManagedBean
public class LoginBean implements Serializable {

    private String nomeUsuario;
    private String senha;
    private Usuario usuario = new Usuario();

    @PostConstruct
    public void init() {
        Usuarios usuarios = new UsuarioJPA();
        usuario = usuarios.ativo();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public String logar(){
        try {
            this.getRequest().login(this.nomeUsuario, this.senha);
            return "Home?faces-redirect=true";
        } catch (ServletException ex) {
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Usuario e/ou senha invalidos");
            return null;
        }
    }
    
    public String sair() throws ServletException{
        this.getRequest().logout();
        return "Login?faces-redirect=true";
    }
    
    private HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
