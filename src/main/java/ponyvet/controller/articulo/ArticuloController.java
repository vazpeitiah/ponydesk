package ponyvet.controller.articulo;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.modelo.dao.articulo.ArticuloDAO;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.articulo.Categoria;
import ponyvet.utilities.FormatearValores;

public class ArticuloController {

    private static final ArticuloDAO dao = new ArticuloDAO();
    public static final int PEDIDOS = 2;
    public static final int COMPRAS = 1;
    public static final int SALIDAS = 0;
    
    public static DefaultTableModel getModelArticulos(DefaultTableModel model, String keywords) {

        List<Articulo> articulos = dao.findByDescripcion(keywords);

        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for (Articulo articulo : articulos) {
            String row[] = new String[10];

            row[0] = articulo.getClave();
            row[1] = articulo.getDescripcion();
            if (articulo.isGranel()) {
                row[2] = FormatearValores.formatearCantidad(articulo.getExistencia());
            } else {
                row[2] = FormatearValores.formatearCantidadEntera(articulo.getExistencia());
            }
            if (articulo.getUnidadCompra() != articulo.getUnidadSalida()) {
                row[3] = FormatearValores.formatearPrecio(articulo.getPrecioCom());
                row[4] = FormatearValores.formatearPrecioArticulo(articulo.getPrecioSal(), articulo.getUnidadSalida().getNombre());
            } else {
                row[3] = FormatearValores.formatearPrecio(articulo.getPrecioCom());
                row[4] = FormatearValores.formatearPrecio(articulo.getPrecioSal());
            }
            row[5] = (articulo.isLote()) ? "L" : "";
            row[6] = (articulo.isGranel()) ? "G" : "";
            Categoria cat = articulo.getCategoria();
            row[7] = cat.getNombre();
            row[8] = String.valueOf(articulo.getInventarioMin());
            row[9] = String.valueOf(articulo.getInventarioMax());
            model.addRow(row);
        }

        return model;
    }
    
    public static DefaultTableModel getModelArticulosCorto(DefaultTableModel model, String keywords, int type) {

        List<Articulo> articulos = dao.findByDescripcion(keywords);

        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for (Articulo articulo : articulos) {
            String row[] = new String[6];

            row[0] = articulo.getClave();
            row[1] = articulo.getDescripcion();
            if (articulo.isGranel()) {
                row[2] = FormatearValores.formatearCantidad(articulo.getExistencia());
            } else {
                row[2] = FormatearValores.formatearCantidadEntera(articulo.getExistencia());
            }
            if (articulo.getUnidadCompra() != articulo.getUnidadSalida()) {
                if(type == SALIDAS){
                    row[3] = FormatearValores.formatearPrecioArticulo(articulo.getPrecioSal(), articulo.getUnidadSalida().getNombre());
                }else if(type == COMPRAS || type == PEDIDOS){
                    row[3] = FormatearValores.formatearPrecioArticulo(articulo.getPrecioCom(), articulo.getUnidadCompra().getNombre());
                }
            } else {
                if(type == SALIDAS){
                    row[3] = FormatearValores.formatearPrecio(articulo.getPrecioSal());
                }else if(type == COMPRAS || type == PEDIDOS){
                    row[3] = FormatearValores.formatearPrecio(articulo.getPrecioCom());
                }
                
            }
            row[4] = (articulo.isLote()) ? "L" : "";
            row[5] = (articulo.isGranel()) ? "G" : "";
            model.addRow(row);
        }

        return model;
    }

    public static void create(Articulo art) {
        boolean result = dao.create(art);
        if(result){
            JOptionPane.showMessageDialog(null, 
                    "El artículo se almacenó correactamente.", 
                    "Artículo almacenado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El artículo no se puedo almacenar.\nVerifique si el artículon ya se ha dado de alta previamente", 
                    "Error al almacenar artículo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void update(Articulo art) {
       boolean result = dao.update(art);
       if(result){
            JOptionPane.showMessageDialog(null, 
                    "El artículo se actualizó correactamente.", 
                    "Artículo almacenado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El artículo no se puedo actualizar.\nPuede que haya intentado usar una clave ya útilizada", 
                    "Error al actualizar artículo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void updateWhitoutAlert(Articulo art) {
       dao.update(art);
    }
    
    public static void delete(Articulo art){
        boolean result = dao.delete(art.getId());
        if (result) {
            JOptionPane.showMessageDialog(null, 
                    "El artículo se eliminó correactamente.", 
                    "Artículo eliminado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "No se puede eliminar el artículo.\nTiene registros de salida, lote, pedio o compra relacionados", 
                    "Error al borrar artículo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Articulo findByClave(String clave) {
        Articulo art = dao.findByClave(clave);
        return art;
    }
    
    public static Articulo findById(int id){
        Articulo art = dao.findById(id);
        return art;
    }
    
    public static void ajustarExistencia(String clave, float existencia){
        Articulo articulo = dao.findByClave(clave);
        
        articulo.setExistencia(existencia);
        
        boolean result = dao.update(articulo);
        
        if (result) {
            JOptionPane.showMessageDialog(null, 
                    "La existencia se actuliazó correctamente", 
                    "Existencia actualizada", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "Error. La existencia no pudo actulizarze", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
