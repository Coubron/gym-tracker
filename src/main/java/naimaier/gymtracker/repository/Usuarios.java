package naimaier.gymtracker.repository;

import naimaier.gymtracker.model.Usuario;

public interface Usuarios {
    public Usuario porCodigo(int codigo);
    public Usuario porNome(String nome);
    public Usuario ativo();
    
    public void salvar(Usuario usuario);
}
