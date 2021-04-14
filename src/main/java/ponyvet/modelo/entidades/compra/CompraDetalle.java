package ponyvet.modelo.entidades.compra;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import ponyvet.modelo.entidades.articulo.Articulo;

@Entity(name = "compra_lista")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.compra",
        joinColumns = @JoinColumn(name = "com_id")),
    @AssociationOverride(name = "primaryKey.articulo",
        joinColumns = @JoinColumn(name = "art_id")) })

public class CompraDetalle implements Serializable{
    @EmbeddedId
    private CompraDetalleID primaryKey = new CompraDetalleID();
    private float cantidad;

    public void setPrimaryKey(CompraDetalleID primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    @Transient
    public Compra getCompra(){
        return primaryKey.getCompra();
    }
    
    public void setCompra(Compra compra){
        primaryKey.setCompra(compra);
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
        return "Compra Detalle{" + "aticulo=" + getArticulo() + ", cantidad=" + cantidad + "}}";
    }
    
}
