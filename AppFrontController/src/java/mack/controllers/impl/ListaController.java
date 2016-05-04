
package mack.controllers.impl;

import java.util.List;
import javax.ejb.EJB;
import mack.controllers.AbstractController;
import ejb.beans.UsuarioBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ListaController extends AbstractController {

 @EJB
 UsuarioBean userBean;
    public void execute() {
        
     try {
         Context cont = new InitialContext();
          UsuarioBean user = (UsuarioBean) cont.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            this.setReturnPage("/index.jsp");
            this.getRequest().setAttribute("usuarios", user.list());
     } catch (NamingException ex) {
         this.setReturnPage("/error.jsp");
         ex.printStackTrace();
     }
       
        
       
//            this.setReturnPage("/index.jsp");
//            this.getRequest().setAttribute("usuarios", userBean.list());

       

    }

}

















