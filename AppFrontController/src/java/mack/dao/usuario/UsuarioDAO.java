package mack.dao.usuario;

import mack.entities.Usuario_antig;
import java.util.Collection;
import java.util.List;

public interface UsuarioDAO {
    public List buscaUsuarioPorId(int id) throws UsuarioNaoEncontradoException;
    public Collection buscaUsuarioPorNome(String nome);
    public Collection buscaTodosUsuarios();
    public void removeUsuario(int id) throws UsuarioNaoEncontradoException;
    public Usuario_antig criaUsuario(String nome, String sobrenome);
    public void updateUsuario(int id, String nome, String sobrenome) throws UsuarioNaoEncontradoException;
    public void close();
    public boolean isClosed();
}
