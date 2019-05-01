/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.commands;

import data.exceptions.CommandExceptions;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Martin Frederiksen
 */
public interface Command {
    String execute(HttpServletRequest request) throws CommandExceptions;
}
