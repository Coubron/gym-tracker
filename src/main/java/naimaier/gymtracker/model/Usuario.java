package naimaier.gymtracker.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Usuario implements Serializable{
    private int id;
    private String nome;
    private String senha;
    private String permissao;
    private Sexo sexo;
    private Date nascimento;
    private float altura;

    @Enumerated(EnumType.STRING)
    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Deixa a numeracao por conta do auto_increment do DB
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String aNome) {
        nome = aNome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String aSenha) {
        senha = aSenha;
    }
    
    public int calculaIdade() {
        Calendar dataAtual = Calendar.getInstance();
        Calendar dataNasc = Calendar.getInstance();
        dataNasc.setTime(this.getNascimento());
        long diaMilissegundos = 86400000;
        return (int) (((dataAtual.getTimeInMillis() - dataNasc.getTimeInMillis()) / diaMilissegundos) / 365);
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
