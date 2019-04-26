/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.Interfaces;

import data.MapperError;
import data.models.Material;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface IMaterialMapper {
    List<Material> getMaterials() throws SQLException;
    Material getMaterialById(int id) throws MapperError;   
}
