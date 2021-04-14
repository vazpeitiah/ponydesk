package ponyvet.modelo.entidades.pedido;

import ponyvet.modelo.entidades.Proveedor;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "pedido")
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private long id;
    private LocalDateTime fecha;
    private LocalDateTime fechaRecibido;
    private float total;
    private int estado;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Proveedor proveedor;
    
    @OneToMany(mappedBy = "primaryKey.pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ListaPedido> listaPedidos = new HashSet<ListaPedido>();

    public static final int RECIBIDO = 1;
    public static final int NORECIBIDO = 0;
    public static final int CANCELADO = 2;
    
    public Set<ListaPedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(Set<ListaPedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
    public Pedido() {
    } 

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDateTime getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(LocalDateTime fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Pedido: {id =").append(String.valueOf(id))
                .append(", fecha =").append(fecha.toLocalDate().toString())
                .append(", total =").append(String.valueOf(total))
                .append(", estado =").append(String.valueOf(estado)).append("}");
        
        getListaPedidos().forEach(articulo -> {
            sb.append("\n*").append(articulo.toString());
        });
        
        return sb.toString();
    }
   
 }
