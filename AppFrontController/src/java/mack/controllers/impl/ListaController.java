
package mack.controllers.impl;

import java.util.List;
import javax.ejb.EJB;
import mack.controllers.AbstractController;
import ejb.beans.UsuarioBean;


public class ListaController extends AbstractController {

 @EJB
 UsuarioBean userBean;
    public void execute() {
       
            this.setReturnPage("/index.jsp");
            this.getRequest().setAttribute("usuarios", userBean.list());

       

    }

}
