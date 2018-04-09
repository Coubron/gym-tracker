package naimaier.calculodieta.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;
import naimaier.calculodieta.infra.MedicaoJPA;
import naimaier.calculodieta.infra.UsuarioJPA;
import naimaier.calculodieta.model.Medicao;
import naimaier.calculodieta.repository.Medicoes;
import naimaier.calculodieta.util.FacesUtil;

@ManagedBean
@ViewScoped
public class MedidasBean implements Serializable {

    private Medicao medicao;
    private List<Medicao> listaMedicoes = new ArrayList<>();
    private boolean editing;


    @PostConstruct
    public void init() {
        //Busca todas as medicoes do usuario atual
        Medicoes medicoes = new MedicaoJPA();
        this.setListaMedicoes(medicoes.todas(new UsuarioJPA().ativo().getId()));

        this.novaMedicao();
    }

    public void setListaMedicoes(List<Medicao> listaMedicoes) {
        this.listaMedicoes = listaMedicoes;
    }

    public List<Medicao> getListaMedicoes() {
        return listaMedicoes;
    }

    public Medicao getMedicao() {
        return medicao;
    }

    public void setMedicao(Medicao medicao) {
        this.medicao = medicao;
    }
    
    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public void novaMedicao() {
        this.setEditing(false);
        this.setMedicao(new Medicao());
        this.medicao.setDia(new Date());
    }

    public void selectMedicao(int id) {
        this.setEditing(true);
        this.setMedicao(new MedicaoJPA().porCodigo(id));
    }

    public String salvar() {
        if (this.medicao.getUsuario() == null) {
            this.medicao.setUsuario(new UsuarioJPA().porCodigo(new UsuarioJPA().ativo().getId()));
        }

        try {
            new MedicaoJPA().salvar(this.medicao);
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Medidas salvas com sucesso!");
        } catch (PersistenceException e){
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar medidas!");
        }

        //Reload na pagina
        return "Medidas";
    }

    public String remover() {
        if (this.getMedicao().getUsuario() != null) { //TODO gambiarra para descobrir se eh novo ou nao
            try {
                new MedicaoJPA().remover(medicao);
                FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Medicao removida com sucesso!");
            } catch (PersistenceException e) {
                FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao remover medidas!");
            }
        }

        //Reload na pagina
        return "Medidas";
    }
}
