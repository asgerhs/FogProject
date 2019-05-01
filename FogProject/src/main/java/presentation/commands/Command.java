/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.commands;

import javax.servlet.http.HttpServletRequest;
import logic.facades.MaterialFacade;

/**
 *
 * @author Martin Frederiksen
 */
public interface Command {
    String execute(HttpServletRequest request, MaterialFacade mf);
}
