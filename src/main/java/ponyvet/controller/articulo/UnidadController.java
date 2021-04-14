package ponyvet.controller.articulo;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import ponyvet.gui.articulo.JDUnidad;
import ponyvet.modelo.dao.articulo.UnidadDAO;
import ponyvet.modelo.entidades.articulo.Unidad;

public class UnidadController {
    
    private static final UnidadDAO dao = new UnidadDAO();
    
    public static DefaultTableModel modelFindByNombre(String keywords){
        
        DefaultTableModel model = (DefaultTableModel) JDUnidad.tableUnidades.getModel();
        
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        List<Unidad> unidades = dao.findByNombre(keywords);
        
        unidades.stream().map(unidad -> {
            String row [] = new String[2];
            row[0] = String.valueOf(unidad.getId());
            row[1] = unidad.getNombre();
            return row;            
        }).forEachOrdered(row -> {
            model.addRow(row);
        });
        
        return model;
    }
    
    public static DefaultTableModel modelFindById(int id){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Clave");
        model.addColumn("Nombre");
        
        Unidad unidad = dao.findById(id);

        String row [] = new String[2];
        row[0] = String.valueOf(unidad.getId());
        row[1] = unidad.getNombre();
        
        model.addRow(row);
        return model;
    }
    
    public static void create(Unidad unidad){
        dao.create(unidad);
    }
    
    public static Unidad findById(int id){
        Unidad unidad = dao.findById(id);
        return unidad;
    }
    
}
