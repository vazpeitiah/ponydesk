package ponyvet.controller.articulo;

import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.modelo.dao.articulo.LoteDAO;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.articulo.Lote;
import ponyvet.utilities.FormatearValores;

public class LoteController {
    
    public static final LoteDAO dao = new LoteDAO();
    
    public static DefaultTableModel getModelLotes(DefaultTableModel model, String clave){
        Articulo art = ArticuloController.findByClave(clave);
        
        List<Lote> lotes = dao.read(art);
        
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        for (Lote lote : lotes) {
            String[] aux = new String[5];
            aux[0] = String.valueOf(lote.getId());
            aux[1] = lote.getNumLote();
            aux[2] = FormatearValores.formatearFecha(lote.getFechaCad());
            if(art.isGranel()){
                aux[3] = FormatearValores.formatearCantidad(lote.getExisInicial());
                aux[4] = FormatearValores.formatearCantidad(lote.getExisActual());
            }else{
                aux[3] = FormatearValores.formatearCantidadEntera(lote.getExisInicial());
                aux[4] = FormatearValores.formatearCantidadEntera(lote.getExisActual());
            }
            
            model.addRow(aux);
        }
        
        return model;
    }
    
    public static void create(Lote lote){
        boolean resultado = dao.create(lote);
        
        if(resultado){
            JOptionPane.showMessageDialog(null, 
                    "El lote se almacenó correactamente.", 
                    "Lote almacenado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El lote no se puedo almacenar.\nVerifique si el lote ya se ha dado de alta previamente", 
                    "Error al almacenar lote", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void update(Lote lote){
        boolean resultado = dao.update(lote);
        
        if(resultado){
            JOptionPane.showMessageDialog(null, 
                    "El lote se actualizó correactamente.", 
                    "Lote actualizado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El lote no se puedo actualizar", 
                    "Error al actualizar lote", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void delete(int id){
        boolean resultado = dao.delete(id);
        
        if(resultado){
            JOptionPane.showMessageDialog(null, 
                    "El lote se eliminó correactamente.", 
                    "Lote eliminado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El lote no se puedo eliminar.", 
                    "Error al eliminar lote", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static List<Lote> readLotesActivos(String clave){
        
        Articulo art = ArticuloController.findByClave(clave);
        
        List<Lote> lotes = dao.read(art);
        
        return lotes;
    }
    
    public static boolean deleteAll(String clave){
        boolean resultado = true;
        Articulo articulo = ArticuloController.findByClave(clave);
        Set<Lote> lotes = articulo.getLotes();
        if(lotes != null && lotes.size() > 0){
            for (Lote lote : lotes) {
                resultado = dao.delete(lote.getId());
            }

            if(resultado){
                JOptionPane.showMessageDialog(null, 
                        "Se eliminaron los lotes correactamente.", 
                        "Lotes eliminados", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, 
                        "No se pudieron eliminar los lotes", 
                        "Error al eliminar lotes", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
    }

}
