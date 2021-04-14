package ponyvet.controller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.modelo.dao.CompraDAO;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.compra.Compra;
import ponyvet.modelo.entidades.compra.CompraDetalle;

public class CompraController {
    
    private static final CompraDAO dao = new CompraDAO();
    
    public static DefaultTableModel getModelFromList(DefaultTableModel model, List<Compra> compras){
        //Eliminar los registros anteriores del modelo de la tabla
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        for (Compra compra : compras) {
            String row[] = new String[4];
            row[0] = String.valueOf(compra.getId());
            row[1] = compra.getFolio();
            row[2] = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).format(compra.getFecha());
            row[3] = String.valueOf(compra.getTotal());
            
            model.addRow(row);
        }
        
        return model;
    }
    
    public static DefaultTableModel getModelArticulos(DefaultTableModel model, Compra compra){
        Set<CompraDetalle> detalles = compra.getCompraDetalles();
        
        for (CompraDetalle detalle : detalles) {
            Articulo art = detalle.getArticulo();
            
            String row[] = new String[5];
            row[0] = art.getClave();
            row[1] = art.getDescripcion();
            row[2] = String.valueOf(detalle.getCantidad());
            row[3] = String.valueOf(art.getPrecioSal());
            DecimalFormat df = new DecimalFormat("0.000");
            float total = detalle.getCantidad() * art.getPrecioCom();
            row[4] = String.valueOf(total);
            
            model.addRow(row);
        }
        return model;
    }
    
    public static List<Compra> read(){
        return dao.read();
    }
    
    public static void create(Compra compra){
        dao.create(compra);
    }
    
    public static void update(Compra compra){
        boolean result = dao.update(compra);
         if(result){
            JOptionPane.showMessageDialog(null, 
                    "El registro se actualizó correactamente.", 
                    "Registro actualizado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El registro no se puedo actualizar.\nPuede que haya ingresado la fecha o un lote de manera incorrecta.", 
                    "Error al actualizar registro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void delete(String id){
        Compra compra = findById(id);
        boolean result = dao.delete(Long.parseLong(id));
        JOptionPane.showMessageDialog(null, 
                    "El registro se eliminó correactamente.", 
                    "Registro eliminado", JOptionPane.INFORMATION_MESSAGE);
        /*if (result) {
            JOptionPane.showMessageDialog(null, 
                    "El registro se eliminó correactamente.", 
                    "Registro eliminado", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, 
                    "No se puede eliminar el registro.\nTiene otros registros de salida, lote, pedio o compra relacionados", 
                    "Error al borrar registro", JOptionPane.ERROR_MESSAGE);
        }*/
    }
    
    public static void addArticulo(CompraDetalle detalle){
        dao.createDetalle(detalle);
    }
    
    public static void createListaPedido(CompraDetalle detalle){
        dao.createDetalle(detalle);
    }
    
    public static Compra findById(String id){
        return dao.searchById(Long.parseLong(id));
    }
    
    public static List<Compra> readByDateRange(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        List<Compra> compras = dao.readByDateRange(fechaInicial, fechaFinal);
        return compras;
    }
    
    public static  List<Compra> readByFolio(String folio){
        List<Compra> compras = dao.readByFolio(folio);
        return  compras;
    }
}
