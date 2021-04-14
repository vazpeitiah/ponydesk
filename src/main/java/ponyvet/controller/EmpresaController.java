package ponyvet.controller;

import java.util.List;
import javax.swing.JOptionPane;
import ponyvet.modelo.dao.EmpresaDAO;
import ponyvet.modelo.entidades.Empresa;

public class EmpresaController {

    /**
     * Data access object for entity empresa
     */
    public static EmpresaDAO dao = new EmpresaDAO();
    
    public static List<Empresa> read(){
        return dao.read();
    }
    
    public static Empresa findById(String id){
        return dao.searchById(Integer.parseInt(id));
    }
    
    public static void create(Empresa emp){
        dao.create(emp);
    }
    
    public static void update(Empresa empresa){
        boolean result = dao.update(empresa);
         if(result){
            JOptionPane.showMessageDialog(null, 
                    "El registro se actualizó correactamente.", 
                    "Registro actualizado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El registro no se puedo actualizar.\nPuede que haya ingresado una fecha erronea.", 
                    "Error al actualizar registro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
