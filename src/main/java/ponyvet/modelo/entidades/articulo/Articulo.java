package ponyvet.modelo.entidades.articulo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "articulo")
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="art_id")
    private int id;
    private String clave;
    private String descripcion;
    @OneToOne
    @JoinColumn(name = "cat_id")
    private Categoria categoria;
   
    private float precioCom; //PRECIO COMPRA
    private float precioSal; //PRECIO VENTA
    private float existencia;
    private float inventarioMin;
    private float inventarioMax;
    private boolean lote;
    private boolean granel;
    private String localizacion;
    @OneToOne
    @JoinColumn(name = "unidadCompra")
    private Unidad unidadCompra;
    @OneToOne
    @JoinColumn(name = "unidadSalida")
    private Unidad unidadSalida;
    private float factor;
    private String caracteristicas;
    
    private String imagen;
    
    @OneToMany(mappedBy = "articulo", fetch = FetchType.EAGER)
    private Set<Lote> lotes = new HashSet<>();
    
    private LocalDateTime fechaMod;
    

    public Articulo() {   
    }

    public Articulo(String clave, String descripcion, float precioCom, float precioSal) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.precioCom = precioCom;
        this.precioSal = precioSal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public float getPrecioCom() {
        return precioCom;
    }

    public void setPrecioCom(float precioCom) {
        this.precioCom = precioCom;
    }

    public float getPrecioSal() {
        return precioSal;
    }

    public void setPrecioSal(float precioSal) {
        this.precioSal = precioSal;
    }

    public float getExistencia() {
        return existencia;
    }

    public void setExistencia(float existencia) {
        this.existencia = existencia;
    }

    public float getInventarioMin() {
        return inventarioMin;
    }

    public void setInventarioMin(float inventarioMin) {
        this.inventarioMin = inventarioMin;
    }

    public float getInventarioMax() {
        return inventarioMax;
    }

    public void setInventarioMax(float inventarioMax) {
        this.inventarioMax = inventarioMax;
    }

    public boolean isLote() {
        return lote;
    }

    public void setLote(boolean lote) {
        this.lote = lote;
    }

    public boolean isGranel() {
        return granel;
    }

    public void setGranel(boolean granel) {
        this.granel = granel;
    }

    public Unidad getUnidadCompra() {
        return unidadCompra;
    }

    public void setUnidadCompra(Unidad unidadCompra) {
        this.unidadCompra = unidadCompra;
    }

    public Unidad getUnidadSalida() {
        return unidadSalida;
    }

    public void setUnidadSalida(Unidad unidadSalida) {
        this.unidadSalida = unidadSalida;
    }
    

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Set<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(Set<Lote> lotes) {
        this.lotes = lotes;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public LocalDateTime getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(LocalDateTime fechaMod) {
        this.fechaMod = fechaMod;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Articulo{id=").append(id).append(", clave=").append(clave).append(", descripcion=").append(descripcion).append(", localizacion=").append(localizacion).append(", categoria=").append(categoria).append(", precioCom=").append(precioCom).append(", precioSal=").append(precioSal).append(", existencia=").append(existencia).append(", inventarioMin=").append(inventarioMin).append(", inventarioMax=").append(inventarioMax).append(", lote=").append(lote).append(", granel=").append(granel).append(", unidadCompra=").append(unidadCompra).append(", unidadVenta=").append(unidadSalida).append(", factor=").append(factor).append(", imagen=").append(imagen).append(", caracteristicas=").append(", fechamod=").append(fechaMod).append(caracteristicas).append('}');    
        lotes.forEach(lote -> {
            sb.append("\n*").append(lote.toString());
        });
        return sb.toString();
    }

}
