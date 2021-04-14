package ponyvet.modelo.entidades.salida;

import java.io.Serializable;
import ponyvet.modelo.entidades.Veterinario;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "salida")
public class Salida implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sal_id")
    private int id;
    private LocalDateTime fecha;
    private float total;
    
    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Veterinario veterinario;
    
    @OneToMany(mappedBy = "primaryKey.salida", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SalidaDetalle> salidas_detalle = new HashSet<SalidaDetalle>();
    
    public Salida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Set<SalidaDetalle> getSalidas_detalle() {
        return salidas_detalle;
    }

    public void setSalidas_detalle(Set<SalidaDetalle> salidas_detalle) {
        this.salidas_detalle = salidas_detalle;
    }
    
    public void addArticulo(SalidaDetalle articulo){
       this. salidas_detalle.add(articulo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Salida{id=").append(id).append(", fecha=").append(fecha).append(", total=").append(total).append('}');
        salidas_detalle.forEach( detalle -> {
            sb.append("\n*").append(detalle.toString());
        });
        return sb.toString();
    }
    
}
