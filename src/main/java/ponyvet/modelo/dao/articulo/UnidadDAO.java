package ponyvet.modelo.dao.articulo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.modelo.dao.JPAUtil;

import ponyvet.modelo.entidades.articulo.Unidad;

public class UnidadDAO {
    
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //CARGAR EL ARCHIVO DE PESISTENCIA
    
    public void create(Unidad uni){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(uni);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public List<Unidad> read(){
        EntityManager manager = emf.createEntityManager();
        List<Unidad> unidades = (List<Unidad>) manager.createQuery("FROM unidad").getResultList();
        manager.close();
        return unidades;
    }
    
    public void update(Unidad uni){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(uni);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public void delete(int id){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        Unidad uni = manager.find(Unidad.class, id);
        manager.remove(uni);
        manager.getTransaction().commit();
        manager.close();
    } 
    
    public Unidad findById(int id){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        Unidad uni = manager.find(Unidad.class, id);
        manager.getTransaction().commit();
        manager.close();
        return uni;
    }
    
    public List<Unidad> findByNombre(String keywords){
        EntityManager manager = emf.createEntityManager();
        List<Unidad> unidades = (List<Unidad>) manager.createQuery("FROM unidad WHERE nombre LIKE :keywords").setParameter("keywords", '%' + keywords + '%').getResultList();
        manager.close();
        return unidades;
    }
}
