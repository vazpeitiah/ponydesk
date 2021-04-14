package ponyvet.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.Empresa;

public class EmpresaDAO {
    
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //CARGAR EL ARCHIVO DE PESISTENCIA
    
    public void create(Empresa emp){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
       //ESTABLECIENDO VALORES POR DEFECTO
        if (emp.getPais() == null) emp.setPais("MÉXICO");
        if (emp.getEstado() == null) emp.setEstado("HIDALGO");
        if (emp.getNumExt() == null) emp.setNumExt("S/N");
        if (emp.getNumInt() == null) emp.setNumInt("S/N");
        
        manager.persist(emp);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public List<Empresa> read(){
        EntityManager manager = emf.createEntityManager();
        List<Empresa> empresas = (List<Empresa>) manager.createQuery("FROM empresa").getResultList();
        manager.close();
        return empresas;
    }
    
    public boolean update(Empresa emp) {
        boolean result = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //ESTABLECIENDO VALORES POR DEFECTO
        if (emp.getPais() == null) {
            emp.setPais("MÉXICO");
        }
        if (emp.getEstado() == null) {
            emp.setEstado("HIDALGO");
        }
        if (emp.getNumExt() == null) {
            emp.setNumExt("S/N");
        }
        if (emp.getNumInt() == null) {
            emp.setNumInt("S/N");
        }

        try{
            manager.merge(emp);
            manager.getTransaction().commit();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        manager.close();
        return result;
    }
    
    public void delete(int id){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Empresa emp = manager.find(Empresa.class, id);
        
        manager.remove(emp);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public Empresa searchById(int id){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Empresa emp = manager.find(Empresa.class, id);
        
        manager.getTransaction().commit();
        manager.close();
        return emp;
    }
 
}
