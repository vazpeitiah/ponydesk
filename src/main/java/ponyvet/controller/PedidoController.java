package ponyvet.controller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.modelo.dao.PedidoDAO;
import ponyvet.modelo.entidades.Proveedor;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.pedido.ListaPedido;
import ponyvet.modelo.entidades.pedido.Pedido;
import ponyvet.utilities.FormatearValores;

public class PedidoController {
    private static final PedidoDAO dao = new PedidoDAO();
    
    public static DefaultTableModel getModelFromList(DefaultTableModel model, List<Pedido> pedidos){
        
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        for (Pedido pedido : pedidos) {
            String row[] = new String[6];
            row[0] = String.valueOf(pedido.getId());
            row[1] = FormatearValores.formatearEstadoPedido(pedido.getEstado());
            row[2] = FormatearValores.formatearFechaTiempo(pedido.getFecha());
            row[3] = FormatearValores.formatearPrecio(pedido.getTotal());
            row[4] = pedido.getProveedor().getNombre();
            if(pedido.getFechaRecibido() != null){
                row[5] = FormatearValores.formatearFechaTiempo(pedido.getFechaRecibido());
            }else{
                row[5] = "-";
            }
            model.addRow(row);
        }
        
        return model;
    }
    
    public static DefaultTableModel getModel(DefaultTableModel model, String keywords) {
        List<Pedido> pedidos = dao.read(); 
        
        for (Pedido pedido : pedidos) {
            String row[] = new String[4];
            row[0] = String.valueOf(pedido.getId());
            row[1] = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).format(pedido.getFecha());
            row[2] = String.valueOf(pedido.getTotal());
            String nombreProveedor = pedido.getProveedor().getNombre();
            String aliasProveedor = pedido.getProveedor().getAlias();
            row[3] = String.format("%s - %S", nombreProveedor, aliasProveedor);
            
            model.addRow(row);
        }
        return model;
    }
    
    public static DefaultTableModel getModelCompra(DefaultTableModel model, LocalDateTime fecha, int proveedor) {
        List<Pedido> pedidos = dao.readByDateRangeAndProvAndEsta(fecha, fecha, proveedor, Pedido.RECIBIDO);
        
        for (Pedido pedido : pedidos) {
            String row[] = new String[4];
            row[0] = String.valueOf(pedido.getId());
            row[1] = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).format(pedido.getFecha());
            row[2] = String.valueOf(pedido.getTotal());
            String nombreProveedor = pedido.getProveedor().getNombre();
            String aliasProveedor = pedido.getProveedor().getAlias();
            row[3] = String.format("%s - %S", nombreProveedor, aliasProveedor);
            
            model.addRow(row);
        }
        return model;
    }
    
    
    public static DefaultTableModel getModelArticulos(DefaultTableModel model, Pedido pedido) {

        Set<ListaPedido> articulos = pedido.getListaPedidos();
        
        for (ListaPedido detalleArticulo : articulos) {
            Articulo articulo = detalleArticulo.getArticulo();
                    
            String row[] = new String[5];

            row[0] = articulo.getClave();
            row[1] = articulo.getDescripcion();
            row[2] = String.valueOf(detalleArticulo.getCantidad());
            row[3] = String.valueOf(articulo.getPrecioCom());
            DecimalFormat df = new DecimalFormat("0.00");
            float total = detalleArticulo.getCantidad() * articulo.getPrecioCom();
            row[4] = String.format("$ %s", df.format(total));
            model.addRow(row);
        }
        
        return model;
    }
    
    public static void create(Pedido pedido){
        dao.create(pedido);
    }
    public static void update(Pedido pedido){
        boolean result = dao.update(pedido);
        if(result){
            JOptionPane.showMessageDialog(null, 
                    "El pedido se actualizó correactamente.", 
                    "Pedido actualizado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El pedido no se puedo actualizar.", 
                    "Error al actualizar registro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public static void updateNoNotify(Pedido pedido){
        dao.update(pedido);
    }
    
    public static void delete(String id){
        boolean result = dao.delete(Long.parseLong(id));
         if(result){
            JOptionPane.showMessageDialog(null, 
                    "El pedido se eliminado correactamente.", 
                    "Pedido elimnado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El pedido no se puedo actualizar.", 
                    "Error al intentar eliminar el pedido", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static List<Pedido> read(){
        List<Pedido> pedidos = dao.read();
        return pedidos;
    }
    
    public static void createListaPedido(ListaPedido listaPedido){
        dao.createListaPedido(listaPedido);
    }
    
    public static Pedido findById(String id){
        return dao.searchById(Integer.valueOf(id));
    }

    public static List<Pedido> findByDateRange(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
        return dao.readByDateRange(fechaInicial, fechaFinal);
    }
    
    public static List<Pedido> findByEstado(int estado){
        return dao.readByEstado(estado);
    }
    
    public static List<Pedido> findByDateRangeAndProv(LocalDateTime fechaInicial, LocalDateTime fechaFinal, Proveedor prov){
        return dao.readByDateRangeAndProv(fechaInicial, fechaFinal, prov.getId());
    }
    
    public static List<Pedido> findByDateRangeAndProvAndEstado(LocalDateTime fechaInicial, LocalDateTime fechaFinal, Proveedor prov, int estado){
        return dao.readByDateRangeAndProvAndEsta(fechaInicial, fechaFinal, prov.getId(), estado);
    }
    
    public static List<Pedido> findByDateRangeAndEstado(LocalDateTime fechaInicial, LocalDateTime fechaFinal, int estado){
        return dao.readByDateRangeAndEsta(fechaInicial, fechaFinal, estado);
    }
    
    public static List<Pedido> findByProvAndEsta(int proveedor, int estado){
        return dao.readByProvAndEsta(proveedor, estado);
    }
}
