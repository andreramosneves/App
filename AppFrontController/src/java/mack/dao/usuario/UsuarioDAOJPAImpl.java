package mack.dao.usuario;

import mack.entities.Usuario_antig;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.logging.*;

public class UsuarioDAOJPAImpl implements UsuarioDAO {

    static final private Log log = LogFactory.getLog(UsuarioDAOJPAImpl.class);
    private boolean bIsClosed = false;

    public UsuarioDAOJPAImpl() {
    }

    @Override
    public List buscaUsuarioPorId(final int id)
            throws UsuarioNaoEncontradoException {
        List<Usuario_antig> u = new ArrayList<Usuario_antig>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Usuario_antig usuario = em.find(Usuario_antig.class, id);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("usuario não encontrado");
        }else {
            u.add(usuario);
        }
        em.clear();
        em.close();
        emf.close();
        return u;
    }

    @Override
    public Collection buscaUsuarioPorNome(final String nome) {
        Collection result = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u where u.nome = :nome");
        q.setParameter("nome", nome);
        result = q.getResultList();
        em.clear();
        em.close();
        emf.close();

        return result;
    }

    @Override
    public void removeUsuario(final int id)
            throws UsuarioNaoEncontradoException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();

        Usuario_antig encontrada = em.find(Usuario_antig.class, id);

        em.getTransaction().begin();
        em.remove(encontrada);
        em.getTransaction().commit();
        
        em.clear();
        em.close();
        emf.close();

    }

    @Override
    public Usuario_antig criaUsuario(
            final String nome,
            final String sobrenome) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();

        Usuario_antig u = new Usuario_antig();
        u.setNome(nome);
        u.setSobrenome(sobrenome);
        u.getId();
        
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
         
        
        em.clear();
        em.close();
        emf.close();

        return u;
    }

    @Override
    public void updateUsuario(final int id,
            final String nome,
            final String sobrenome) throws UsuarioNaoEncontradoException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();

        Usuario_antig u = new Usuario_antig(id, nome, sobrenome);
        u.setNome(nome);
        u.setSobrenome(sobrenome);

        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        
        em.clear();
        em.close();
        emf.close();

    }

    @Override
    public void close() {
        log.info("close() called");
        bIsClosed = true;
    }

    @Override
    public boolean isClosed() {
        return bIsClosed;
    }

    @Override
    public Collection buscaTodosUsuarios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u");
        Collection result = null;
        result = q.getResultList();
        em.clear();
        em.close();
        emf.close();
        return result;
    }
}
