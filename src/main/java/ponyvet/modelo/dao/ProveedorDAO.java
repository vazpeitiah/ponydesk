package ponyvet.modelo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.Proveedor;

public class ProveedorDAO {

    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    
    public List<Proveedor> read() {
        EntityManager manager = emf.createEntityManager();
        
        List<Proveedor> proveedores = (List<Proveedor>) manager.createQuery("FROM proveedor").getResultList();
        
        manager.close();
        return proveedores;
    }
    
    public boolean create(Proveedor proveedor) {
        boolean result = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //VALORES POR DEFECTO
        if (proveedor.getPais() == null) {
            proveedor.setPais("MÉXICO");
        }
        if (proveedor.getEstado() == null) {
            proveedor.setEstado("HIDALGO");
        }
        if (proveedor.getNumExt() == null) {
            proveedor.setNumExt("S/N");
        }
        if (proveedor.getNumInt() == null) {
            proveedor.setNumInt("S/N");
        }

        try{
            manager.persist(proveedor);
            manager.getTransaction().commit();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }
        
        return result;
    }
    
    public boolean update(Proveedor proveedor) {
        boolean result = false;
        
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //VALORES POR DEFECTO
        if (proveedor.getPais() == null) {
            proveedor.setPais("MÉXICO");
        }
        if (proveedor.getEstado() == null) {
            proveedor.setEstado("HIDALGO");
        }
        if (proveedor.getNumExt() == null) {
            proveedor.setNumExt("S/N");
        }
        if (proveedor.getNumInt() == null) {
            proveedor.setNumInt("S/N");
        }
        
        try{
            manager.merge(proveedor);
            manager.getTransaction().commit();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }
        
        return result;
    }
    
    public boolean delete(int id){
        boolean result = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Proveedor proveedor = manager.find(Proveedor.class, id);
        
        try{
            manager.remove(proveedor);
            manager.getTransaction().commit();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }
        
        return result;
    }
    
    public Proveedor searchById(int id){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Proveedor proveedor = manager.find(Proveedor.class, id);
        manager.getTransaction().commit();
        
        manager.close();
        return proveedor;
    }
    
    public List<Proveedor> findByDescription(String keywords) {
        List<Proveedor> proveedores = null;
        EntityManager manager = emf.createEntityManager();
        
        try{
            proveedores = (List<Proveedor>) manager.createQuery("FROM proveedor WHERE nombre LIKE :keywords "
                            + "OR representante LIKE :keywords OR alias LIKE :keywords ORDER BY nombre ASC")
                    .setParameter("keywords", '%' + keywords + '%').getResultList();
        }catch(Exception e){
            System.out.println("error al buscar los registros");
            e.printStackTrace();
        }
        
        manager.close();
        return proveedores;
    }

}
