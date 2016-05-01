/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mack.controllers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mack.controllers.AbstractController;
import mack.dao.usuario.UsuarioDAO;
import mack.dao.usuario.UsuarioDAOFactory;
import mack.entities.Usuario_antig;

/**
 *
 * @
 */
public class BuscaController extends AbstractController {

    public void execute() {
        try {

            List usuarios = new ArrayList<Usuario_antig>();
            UsuarioDAO dao = UsuarioDAOFactory.getUsuarioDAO();
            usuarios = (List) dao.buscaUsuarioPorNome(this.getRequest().getParameter("nome"));
            this.setReturnPage("/index.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);

        } catch (Exception ex) {
            Logger.getLogger(BuscaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
