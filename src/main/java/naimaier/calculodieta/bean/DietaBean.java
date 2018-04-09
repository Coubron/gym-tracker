package naimaier.calculodieta.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import naimaier.calculodieta.model.Dieta;
import naimaier.calculodieta.util.FacesUtil;

@ManagedBean
@ViewScoped
public class DietaBean implements Serializable {

    private Dieta dieta = new Dieta();
    private String dietaSelecionada;
    private boolean calculationEnabled;

    @PostConstruct
    public void init() {

        this.dieta.init();
        
        this.setCalculationEnabled(false);
        
        this.verificaUltimaMedicao();
    }

    public void eventListener() {
        this.verificaUltimaMedicao();
        
        if (this.dietaSelecionada != null && this.isCalculationEnabled() && this.dieta.getActivity() != 0) {
                this.calculaDieta();
        }
    }
    
    private void verificaUltimaMedicao () {
        if(this.dieta.getUltimaMedicao() != null) {
            if(this.dieta.getUltimaMedicao().getPeso() > 0) {
                this.setCalculationEnabled(true);
            } else {
                FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Para calcular a dieta e necessario que seu peso corporal esteja corretamente cadastrado na medicao mais atual");
            }
        } else {
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Para calcular a dieta e necessario uma medicao cadastrada.");
        }
    }

    public void calculaDieta() {
        switch (this.getDietaSelecionada()) {
            case "bulking":
                this.dieta.calculoBulking();
                break;
            case "cutting":
                this.dieta.calculoCutting();
                break;
            case "manutencao":
                this.dieta.calculoManutencao();
                break;
            default:
                break;
        }
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public String getDietaSelecionada() {
        return dietaSelecionada;
    }

    public void setDietaSelecionada(String dietaSelecionada) {
        this.dietaSelecionada = dietaSelecionada;
    }

    public boolean isCalculationEnabled() {
        return calculationEnabled;
    }

    public void setCalculationEnabled(boolean CalculationEnabled) {
        this.calculationEnabled = CalculationEnabled;
    }
}
