package ponyvet.modelo.entidades.pedido;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import ponyvet.modelo.entidades.articulo.Articulo;

@Embeddable
public class ListaPedidoID implements Serializable{
    @ManyToOne(cascade = CascadeType.ALL)
    private Pedido pedido;
    @ManyToOne(cascade = CascadeType.ALL)
    private Articulo articulo;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
}
