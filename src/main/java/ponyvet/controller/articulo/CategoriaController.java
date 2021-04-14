package ponyvet.controller.articulo;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import ponyvet.gui.articulo.JDCategoria;
import ponyvet.modelo.dao.articulo.CategoriaDAO;
import ponyvet.modelo.entidades.articulo.Categoria;

public class CategoriaController {
    
    private static final CategoriaDAO catDAO = new CategoriaDAO();
    
    public static DefaultTableModel modelFindByDescripcion(String keywords){
        
        DefaultTableModel model = (DefaultTableModel) JDCategoria.tableCategorias.getModel();
        
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        List<Categoria> categorias = catDAO.findByDescripcion(keywords);
        
        categorias.stream().map(categoria -> {
            String row[] = new String[2];
            row[0] = String.valueOf(categoria.getId());
            row[1] = categoria.getNombre();
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
        
        Categoria categoria = catDAO.searchById(id);
        
        String aux[] = new String[2];
        
        aux[0] = String.valueOf(categoria.getId());
        aux[1] = categoria.getNombre();
        
       model.addRow(aux);
        
        return model;     
    }
    
    public static Categoria findById(int cat_id){
        Categoria cat = catDAO.searchById(cat_id);
        return cat;
    }
    
    public static void create(Categoria cat){
        catDAO.create(cat);
    }
    
}
