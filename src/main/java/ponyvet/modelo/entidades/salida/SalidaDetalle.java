package ponyvet.modelo.entidades.salida;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import ponyvet.modelo.entidades.articulo.Articulo;

@Entity(name = "salida_lista")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.salida",
        joinColumns = @JoinColumn(name = "sal_id")),
    @AssociationOverride(name = "primaryKey.articulo",
        joinColumns = @JoinColumn(name = "art_id")) })

public class SalidaDetalle implements Serializable{
    
    @EmbeddedId
    private SalidaDetalleId primaryKey = new SalidaDetalleId();
    private float cantidad;

    public SalidaDetalleId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(SalidaDetalleId primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    @Transient
    public Salida getSalida(){
        return primaryKey.getSalida();
    }
    
    public void setSalida(Salida salida){
        primaryKey.setSalida(salida);
    }
    
    @Transient
    public Articulo getArticulo(){
        return primaryKey.getArticulo();
    }
    
    public void setArticulo(Articulo articulo){
        primaryKey.setArticulo(articulo);
    }


    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "SalidaDetalle{" + "aticulo=" + getArticulo() + ", cantidad=" + cantidad + '}';
    }
    
}
