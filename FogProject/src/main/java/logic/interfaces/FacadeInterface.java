/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.interfaces;

import data.exceptions.MapperExceptions;
import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface FacadeInterface <T>{
    //Stadig MapperException??
    List<T> getMaterials() throws MapperExceptions;
    T getMaterialById(int id) throws MapperExceptions;
}
