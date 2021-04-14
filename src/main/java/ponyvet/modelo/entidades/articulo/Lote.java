package ponyvet.modelo.entidades.articulo;

import ponyvet.modelo.entidades.articulo.Articulo;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "lote")
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private int id; 
    private String numLote;
    private LocalDate fechaCad;
    private float exisInicial;
    private float exisActual;
    private boolean estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Articulo articulo;

    public Lote() {
    }

    public Lote(String numLote, LocalDate fechaCad, float exisInicial, float exisActual, boolean estado, Articulo articulo) {
        this.numLote = numLote;
        this.fechaCad = fechaCad;
        this.exisInicial = exisInicial;
        this.exisActual = exisActual;
        this.estado = estado;
        this.articulo = articulo;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumLote() {
        return numLote;
    }

    public void setNumLote(String numLote) {
        this.numLote = numLote;
    }

    public LocalDate getFechaCad() {
        return fechaCad;
    }

    public void setFechaCad(LocalDate fechaCad) {
        this.fechaCad = fechaCad;
    }

    public float getExisInicial() {
        return exisInicial;
    }

    public void setExisInicial(float exisInicial) {
        this.exisInicial = exisInicial;
    }

    public float getExisActual() {
        return exisActual;
    }

    public void setExisActual(float exisActual) {
        this.exisActual = exisActual;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Lote{" + "id=" + id + ", numLote=" + numLote + ", fechaCad=" + fechaCad + ", exisInicial=" + exisInicial + ", exisActual=" + exisActual + ", estado=" + estado + '}';
    }
    
}
