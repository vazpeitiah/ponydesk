package ponyvet.modelo.entidades.compra;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import ponyvet.modelo.entidades.articulo.Articulo;

@Embeddable
public class CompraDetalleID implements Serializable{
    @ManyToOne(cascade = CascadeType.ALL)
    private Compra compra;
    @ManyToOne(cascade = CascadeType.ALL)
    private Articulo articulo;

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
}
