package ponyvet.modelo.entidades.salida;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import ponyvet.modelo.entidades.articulo.Articulo;

@Embeddable
public class SalidaDetalleId implements Serializable{
    @ManyToOne(cascade = CascadeType.ALL)
    private Salida salida;
    @ManyToOne(cascade = CascadeType.ALL)
    private Articulo articulo;

    public Salida getSalida() {
        return salida;
    }

    public void setSalida(Salida salida) {
        this.salida = salida;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
}
