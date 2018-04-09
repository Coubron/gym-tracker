package naimaier.calculodieta.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Medicao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dia;
    private String descricao;
    private float pescoco;
    @Column(name = "biceps_e")
    private float bicepsE;
    @Column(name = "biceps_d")
    private float bicepsD;
    @Column(name = "antebraco_e")
    private float antebracoE;
    @Column(name = "antebraco_d")
    private float antebracoD;
    private float peito;
    private float cintura;
    private float quadris;
    @Column(name = "coxa_e")
    private float coxaE;
    @Column(name = "coxa_d")
    private float coxaD;
    @Column(name = "panturrilha_e")
    private float panturrilhaE;
    @Column(name = "panturrilha_d")
    private float panturrilhaD;
    private float ombro;
    private float bf;
    private float peso;
    @ManyToOne
    private Usuario usuario;
    
    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medicao other = (Medicao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPescoco() {
        return pescoco;
    }

    public void setPescoco(float pescoco) {
        this.pescoco = pescoco;
    }

    public float getBicepsE() {
        return bicepsE;
    }

    public void setBicepsE(float bicepsE) {
        this.bicepsE = bicepsE;
    }

    public float getBicepsD() {
        return bicepsD;
    }

    public void setBicepsD(float bicepsD) {
        this.bicepsD = bicepsD;
    }

    public float getAntebracoE() {
        return antebracoE;
    }

    public void setAntebracoE(float antebracoE) {
        this.antebracoE = antebracoE;
    }

    public float getAntebracoD() {
        return antebracoD;
    }

    public void setAntebracoD(float antebracoD) {
        this.antebracoD = antebracoD;
    }

    public float getPeito() {
        return peito;
    }

    public void setPeito(float peito) {
        this.peito = peito;
    }

    public float getCintura() {
        return cintura;
    }

    public void setCintura(float cintura) {
        this.cintura = cintura;
    }

    public float getQuadris() {
        return quadris;
    }

    public void setQuadris(float quadris) {
        this.quadris = quadris;
    }

    public float getCoxaE() {
        return coxaE;
    }

    public void setCoxaE(float coxaE) {
        this.coxaE = coxaE;
    }

    public float getCoxaD() {
        return coxaD;
    }

    public void setCoxaD(float coxaD) {
        this.coxaD = coxaD;
    }

    public float getPanturrilhaE() {
        return panturrilhaE;
    }

    public void setPanturrilhaE(float panturrilhaE) {
        this.panturrilhaE = panturrilhaE;
    }

    public float getPanturrilhaD() {
        return panturrilhaD;
    }

    public void setPanturrilhaD(float panturrilhaD) {
        this.panturrilhaD = panturrilhaD;
    }

    public float getOmbro() {
        return ombro;
    }

    public void setOmbro(float ombro) {
        this.ombro = ombro;
    }

    public float getBf() {
        return bf;
    }

    public void setBf(float bf) {
        this.bf = bf;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
