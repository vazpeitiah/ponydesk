package ponyvet.utilities;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import ponyvet.modelo.entidades.pedido.Pedido;

public class FormatearValores {
    
    private static final DecimalFormat dfPrecio = new DecimalFormat("##,##0.00");
    private static final DecimalFormat dfCantidad = new DecimalFormat("0.00");
    private static final DecimalFormat dfCantidadEntera = new DecimalFormat("0");
    private static final DecimalFormat dfClavePedido = new DecimalFormat("000000000000");
    
    public static String formatearPrecio(float precio) {
        return String.format("$ %s", dfPrecio.format(precio));
    }
    
    public static String formatearPrecioArticulo(float precio, String unidad) {
        return String.format("$ %s x%s", dfPrecio.format(precio), unidad);
    }
    
    public static String formatearCantidad(float cantidad) {
        return dfCantidad.format(cantidad);
    }
    
    public static String formatearCantidadEntera(float cantidad) {
        return dfCantidadEntera.format(cantidad);
    }
    
    public static String formatearFechaTiempo(LocalDateTime fecha){
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).format(fecha);
    }
    
    public static String formatearFecha(LocalDate fecha){
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(fecha);
    }
    
    public static String formatearHora(LocalTime hora){
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(hora);
    }
    
    public static String formatearEstadoPedido(int estado){
        String strEstado = null;
        switch(estado){
            case Pedido.RECIBIDO:
                strEstado = "Recibido";
                break;
            case Pedido.NORECIBIDO:
                strEstado = "En proceso de entrega";
                break;
            case Pedido.CANCELADO:
                strEstado = "Cancelado";
                break;
        }
        return strEstado;
    }
    
    public static String formatearNombreProveedor(String nombre, String alias){
        if(alias.isEmpty() || alias == null){
            return nombre;
        }else{
            return String.format("%s (%s)", nombre, alias);
        }
    }
    
    public static String formatearClavePedido(long id){
        return dfClavePedido.format((float)id);
    }
}
