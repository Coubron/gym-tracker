package naimaier.gymtracker.model;

public class Macronutrientes {
    private double kcal;
    private double proteinas;
    private double carboidratos;
    private double gorduras;

    public Macronutrientes(double kcal, double proteinas, double carboidratos, double gorduras) {
        this.kcal = kcal;
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.gorduras = gorduras;
    }

    public Macronutrientes() {
    }
    
    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(double carboidratos) {
        this.carboidratos = carboidratos;
    }

    public double getGorduras() {
        return gorduras;
    }

    public void setGorduras(double gorduras) {
        this.gorduras = gorduras;
    }
}
