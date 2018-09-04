package naimaier.gymtracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import naimaier.gymtracker.dao.MedicaoDAO;
import naimaier.gymtracker.dao.UsuarioDAO;

public class Dieta implements Serializable{

    Usuario usuario = new Usuario();
    private Medicao ultimaMedicao = new Medicao();
    private Macronutrientes macrosManutencao = new Macronutrientes();
    private Macronutrientes macrosBulking = new Macronutrientes();
    private List<Macronutrientes> macrosCutting = new ArrayList<>();

    private static final double BULKING = 1.2; //Bulking = +20% calorias
    private static final double CUTTING = 0.8; //Cutting = -20% calorias

    //Dados
    private float peso; //em Kgs
    private float altura; //em cms
    private int idade;
    private float bf;
    private int sexFactor;//(homem= 5, mulher = -151)
    double activity;

    public void init() {
        //Busca o usuario atual
        this.setUsuario(new UsuarioDAO().ativo());
        //Busca as ultimas medidas cadastradas
        this.setUltimaMedicao(new MedicaoDAO().ultima(usuario.getId()));//TODO Erro se nao encontrar ou nao encontrar o peso

        if (this.getUltimaMedicao() != null) {
            //Inicializa os dados
            this.setPeso(ultimaMedicao.getPeso());
            this.setAltura(usuario.getAltura());
            this.setIdade(usuario.calculaIdade());
            this.setBf(ultimaMedicao.getBf());
        }

        switch (usuario.getSexo()) {
            case M:
                this.setSexFactor(5);
                break;
            case F:
                this.setSexFactor(-151);
                break;
            default:
                break;
        }
    }

    private double calculaBMR() {

        double bmr;//basal metabolic rate
        double lbm;//lean body mass
        if (this.getBf() > 0) {
            /*
            * (INCLUI BF) Calculo da BMR usando Katch-McArdle Equation
            * Katch = 370 + (21.6 * LBM)
            * where LBM is lean body mass
             */
            lbm = this.getPeso() - (this.getPeso() * (this.getBf() / 100));
            bmr = 370 + (21.6 * lbm);
        } else {
            /*
            * (NAO INCLUI BF) Calculo da BMR usando Mifflin-St Jeor Equation
            * Mifflin = (10.m + 6.25h - 5.0a) + s
            * m is mass in kg, h is height in cm, a is age in years, s is +5 for males and -151 for females
             */
            bmr = (10 * this.getPeso() + 6.25 * this.getAltura() - 5 * this.getIdade()) + this.getSexFactor();
        }
        return bmr;
    }

    /*
    * TDEE - Activity
    * Sedentary (little or no exercise): BMR x 1,2
    * Lightly active (training/sports 2-3 days/week): BMR x 1,375
    * Moderately active (training/sports 4-5 days/week): BMR x 1,55
    * Very active (training/sports 6-7 days a week): BMR x 1,725
    * Extremely active (twice per day, extra heavy workouts): BMR x 1,9
     */
    private double calculaTDEE() {
        return this.calculaBMR() * this.getActivity();
    }

    public void calculoBulking() {
        this.macrosBulking.setKcal(this.calculaTDEE() * Dieta.BULKING);
        this.macrosBulking.setProteinas(2 * this.getPeso());
        this.macrosBulking.setGorduras(this.getPeso());
        this.macrosBulking.setCarboidratos((this.macrosBulking.getKcal() - (this.macrosBulking.getProteinas() * 4 + this.macrosBulking.getGorduras() * 9)) / 4);
    }

    public void calculoManutencao() {
        this.macrosManutencao.setKcal(this.calculaTDEE());
        this.macrosManutencao.setProteinas(2 * this.getPeso());
        this.macrosManutencao.setGorduras(this.getPeso());
        this.macrosManutencao.setCarboidratos((this.macrosManutencao.getKcal() - (this.macrosManutencao.getProteinas() * 4 + this.macrosManutencao.getGorduras() * 9)) / 4);
    }

    /*
    * Cutting (-20%)
    * Diminuir 0,2 g/k de carbo por semana ateh chegar em 0,2/0,4
     */
    public void calculoCutting() {
        this.macrosCutting.clear();
        Macronutrientes macros = new Macronutrientes();
        macros.setKcal(this.calculaTDEE() * Dieta.CUTTING);
        macros.setProteinas(2 * this.getPeso());
        macros.setGorduras(this.getPeso());
        macros.setCarboidratos((macros.getKcal() - (macros.getProteinas() * 4 + macros.getGorduras() * 9)) / 4);

        while (macros.getCarboidratos() > 0.2 * this.getPeso()) {
            Macronutrientes tmp = new Macronutrientes(macros.getKcal(), macros.getProteinas(), macros.getCarboidratos(), macros.getGorduras());
            this.macrosCutting.add(tmp);
            macros.setCarboidratos(macros.getCarboidratos() - (0.2 * this.getPeso()));
            macros.setKcal(macros.getProteinas() * 4 + macros.getCarboidratos() * 4 + macros.getGorduras() * 9);
        }
    }

    public Macronutrientes getMacrosBulking() {
        return macrosBulking;
    }

    public void setMacrosBulking(Macronutrientes macrosBulking) {
        this.macrosBulking = macrosBulking;
    }

    public List<Macronutrientes> getMacrosCutting() {
        return macrosCutting;
    }

    public void setMacrosCutting(List<Macronutrientes> macrosCutting) {
        this.macrosCutting = macrosCutting;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Medicao getUltimaMedicao() {
        return ultimaMedicao;
    }

    public void setUltimaMedicao(Medicao ultimaMedicao) {
        this.ultimaMedicao = ultimaMedicao;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getBf() {
        return bf;
    }

    public void setBf(float bf) {
        this.bf = bf;
    }

    public int getSexFactor() {
        return sexFactor;
    }

    public void setSexFactor(int sexFactor) {
        this.sexFactor = sexFactor;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public Macronutrientes getMacrosManutencao() {
        return macrosManutencao;
    }

    public void setMacrosManutencao(Macronutrientes macrosManutencao) {
        this.macrosManutencao = macrosManutencao;
    }

}
