package ponyvet.controller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.modelo.dao.SalidaDAO;
import ponyvet.modelo.entidades.Veterinario;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.salida.Salida;
import ponyvet.modelo.entidades.salida.SalidaDetalle;

public class SalidaController {
    
    private static final SalidaDAO dao = new SalidaDAO();
    
    public static DefaultTableModel getModelFromList(DefaultTableModel model, List<Salida> salidas){
        
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        for (Salida salida : salidas) {
            String row[] = new String[4];
            row[0] = String.valueOf(salida.getId());
            row[1] = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).format(salida.getFecha());
            row[2] = String.valueOf(salida.getTotal());
            row[3] = salida.getVeterinario().getNombre();
            
            model.addRow(row);
        }
        
        return model;
    }
    
    public static DefaultTableModel getModelArticulos(DefaultTableModel model, Salida salida){
        Set<SalidaDetalle> detallesArticulo = salida.getSalidas_detalle();
        
        for (SalidaDetalle salidaDetalle : detallesArticulo) {
            Articulo art = salidaDetalle.getArticulo();
            
            String row[] = new String[5];
            row[0] = art.getClave();
            row[1] = art.getDescripcion();
            row[2] = String.valueOf(salidaDetalle.getCantidad());
            row[3] = String.valueOf(art.getPrecioSal());
            DecimalFormat df = new DecimalFormat("0.000");
            float total = salidaDetalle.getCantidad() * art.getPrecioCom();
            row[4] = String.valueOf(total);
            
            model.addRow(row);
        }
        return model;
    }
    
    public static void create(Salida salida){
        dao.create(salida);
    }
    
    public static void update(Salida salida){
        boolean result = dao.update(salida);
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
    
    public static void addArticulo(SalidaDetalle detalle){
        dao.createDetalle(detalle);
    }
    
    public static List<Salida> read(){
        List<Salida> salidas = dao.read();
        return salidas;
    }
    
    public static void delete(String id){
        boolean result = dao.delete(Integer.valueOf(id));
        if (result) {
            JOptionPane.showMessageDialog(null, 
                    "El registro se eliminó correactamente.", 
                    "Registro eliminado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "No se puede eliminar el registro.\nTiene otros registros de salida, lote, pedio o compra relacionados", 
                    "Error al borrar registro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Salida findById(String id){
        return dao.searchById(Integer.parseInt(id));
    }
    
    public static List<Salida> readByDateRange(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
        List<Salida> salidas = dao.readByDateRange(fechaInicial, fechaFinal);
        return salidas;
    }
    
    public static List<Salida> readByDateRangeAndVet(LocalDateTime fechaInicial, LocalDateTime fechaFinal, Veterinario vet){
        List<Salida> salidas = dao.readByDateRangeAndVeterinario(fechaInicial, fechaFinal, vet.getId());
        return salidas;
    }
    
}
