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
import mack.entities.Usuario;

/*

author DENIS NASCIMENTO
author ANDRE RAMOS

 */
public class BuscaPorIdController extends AbstractController {

    @Override
    public void execute() {
        try {

            List usuarios = new ArrayList<Usuario>();
            UsuarioDAO dao = UsuarioDAOFactory.getUsuarioDAO();
            
            int aux = Integer.parseInt(this.getRequest().getParameter("usuario_id"));
            
            usuarios = (List) dao.buscaUsuarioPorId(aux);
            this.setReturnPage("/index.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);

        } catch (Exception ex) {
            Logger.getLogger(BuscaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
