package ejb.beans;
import static ejb.beans.Hash.generateStrongPasswordHash;
import ejb.entities.Usuario;
import ejb.interceptor.AcessoItensInterceptor;
import ejb.interceptor.LogInterceptor;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class UsuarioBean {
    

@PersistenceContext(unitName = "ModuloEJBPU")
 private EntityManager em;

 public void save(Usuario u) {
 
 
 em.persist(u);
 }
  
 
 public List<Usuario> list() {
 Query query = em.createQuery("FROM Usuario u");
 List<Usuario> list = query.getResultList();
 return list;
 }


public boolean buscaPorNomeSenha(final String nome, String senha) {
        try {
            Query query = em.createQuery("FROM Usuario u where u.nome = :username");
            query.setParameter("username", nome);
            return Hash.validaSenha(senha, ((Usuario) query.getResultList().get(0)).getSenha());
        } catch (IndexOutOfBoundsException | NoSuchAlgorithmException | InvalidKeySpecException ex ) {
            return false;
        } catch(Exception ecc){
            ecc.printStackTrace();
            return false;
        }
    }
  
public void sucesso(){
    
}
public void falha(){
    
}
}