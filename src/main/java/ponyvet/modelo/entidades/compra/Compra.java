package ponyvet.modelo.entidades.compra;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "compra")
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private long id;
    private String folio;
    private LocalDateTime fecha;
    private float total;
    
    @OneToMany(mappedBy = "primaryKey.compra", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CompraDetalle> compraDetalles = new HashSet<CompraDetalle>();

    public Set<CompraDetalle> getCompraDetalles() {
        return compraDetalles;
    }

    public void setCompraDetalles(Set<CompraDetalle> compraDetalles) {
        this.compraDetalles = compraDetalles;
    }

    public Compra() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", folio=" + folio + ", fecha=" + fecha + ", total=" + total + '}';
    }
    
}
