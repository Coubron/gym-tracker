package naimaier.gymtracker.repository;

import java.util.List;
import naimaier.gymtracker.model.Medicao;

public interface Medicoes {
    public List<Medicao> todas(int usuario);
    public Medicao ultima(int usuario);
    public Medicao porCodigo(int codigo);
    
    public void salvar(Medicao medicao);
    public void remover(Medicao medicao);
}
