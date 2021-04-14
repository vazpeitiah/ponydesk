package ponyvet.modelo.entidades.pedido;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import ponyvet.modelo.entidades.articulo.Articulo;

@Entity(name = "pedido_lista")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.pedido",
        joinColumns = @JoinColumn(name = "ped_id")),
    @AssociationOverride(name = "primaryKey.articulo",
        joinColumns = @JoinColumn(name = "art_id")) })

public class ListaPedido implements Serializable{
    @EmbeddedId
    private ListaPedidoID primaryKey = new ListaPedidoID();
    private float cantidad;

    public void setPrimaryKey(ListaPedidoID primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    @Transient
    public Pedido getPedido(){
        return primaryKey.getPedido();
    }
    
    public void setPedido(Pedido pedido){
        primaryKey.setPedido(pedido);
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
        return "Lista pedido{" + "aticulo=" + getArticulo() + ", cantidad=" + cantidad + "}}";
    }
    
}
