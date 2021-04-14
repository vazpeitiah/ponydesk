package ponyvet.modelo.entidades;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import ponyvet.modelo.entidades.pedido.Pedido;

@Entity(name = "proveedor")
public class Proveedor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private int id;
    //GENERAL
    private String nombre;
    private String alias;
    private String representante;
    private String rfc;
    private String curp;
    private Blob foto;
    //CONTACTO
    private String telefono;
    private String celular;
    private String email;
    //DIRECCION
    private String pais;
    private String estado;
    private String municipio;
    private String colonia;
    private String localidad;
    private String domicilio;
    private String numExt;
    private String numInt;
    private String codigoPostal;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "proveedor")
    private List<Pedido> pedidos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Proveedor{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", alias=").append(alias);
        sb.append(", representante=").append(representante);
        sb.append(", rfc=").append(rfc);
        sb.append(", curp=").append(curp);
        sb.append(", foto=").append(foto);
        sb.append(", telefono=").append(telefono);
        sb.append(", celular=").append(celular);
        sb.append(", email=").append(email);
        sb.append(", pais=").append(pais);
        sb.append(", estado=").append(estado);
        sb.append(", municipio=").append(municipio);
        sb.append(", colonia=").append(colonia);
        sb.append(", localidad=").append(localidad);
        sb.append(", domicilio=").append(domicilio);
        sb.append(", numExt=").append(numExt);
        sb.append(", numInt=").append(numInt);
        sb.append(", codigoPostal=").append(codigoPostal).append('}');
        
        pedidos.forEach( pedido -> { sb.append("\n*").append(pedido.toString()); });
        
        return sb.toString();
    }

    
    
}
