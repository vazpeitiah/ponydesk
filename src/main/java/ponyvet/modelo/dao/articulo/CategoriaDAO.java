package ponyvet.modelo.dao.articulo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.modelo.dao.JPAUtil;

import ponyvet.modelo.entidades.articulo.Categoria;

public class CategoriaDAO {
    
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //CARGAR EL ARCHIVO DE PESISTENCIA
    
    public void create(Categoria cat){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(cat);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public List<Categoria> read(){
        EntityManager manager = emf.createEntityManager();
        List<Categoria> categorias = (List<Categoria>) manager.createQuery("FROM categoria").getResultList();
        manager.close();
        return categorias;
    }
    
    public void update(Categoria cat){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(cat);
        manager.getTransaction().commit();
        manager.close();
    }
    
    public void delete(int id){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        Categoria cat = manager.find(Categoria.class, id);
        manager.remove(cat);
        manager.getTransaction().commit();
        manager.close();
    } 
    
    public Categoria searchById(int id){
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        Categoria cat = manager.find(Categoria.class, id);
        manager.getTransaction().commit();
        manager.close();
        return cat;
    }
    
    public List<Categoria> findByDescripcion(String keywords){
        EntityManager manager = emf.createEntityManager();
        List<Categoria> categorias = (List<Categoria>) manager.createQuery("FROM categoria WHERE nombre LIKE :keywords AND id != 1", Categoria.class).setParameter("keywords", '%' + keywords + '%').getResultList();
        manager.close();
        return categorias;
    }
}
