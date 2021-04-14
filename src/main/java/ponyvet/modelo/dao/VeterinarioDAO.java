package ponyvet.modelo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.Veterinario;

public class VeterinarioDAO {
    
    private EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    
    public boolean create(Veterinario vet){
        boolean resutlt = false;
        EntityManager manager = emf.createEntityManager();
        
        manager.getTransaction().begin();
        
        try {
            manager.persist(vet);
            manager.getTransaction().commit();
            resutlt = true;
        } catch (Exception e) {
            System.out.println("error al crear artículo");
            e.printStackTrace();
        } finally {
            manager.close();
        }
        
        return resutlt;
    }
    
    public List<Veterinario> read(){
        EntityManager manager = emf.createEntityManager();
        
        List<Veterinario> veterinarios = (List<Veterinario>) manager.createQuery("FROM veterinario").getResultList();
        manager.close();
        return veterinarios;
    }
    
    public boolean update(Veterinario vet){
        boolean result = false;
        EntityManager manager = emf.createEntityManager();
        
        manager.getTransaction().begin();
        
        try {
            manager.merge(vet);
            manager.getTransaction().commit();
            result = true;
        }catch (Exception e) {
            System.out.println("error al actualizar el veterinario");
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
        
        Veterinario vet = manager.find(Veterinario.class, id);
        
        try{
            manager.remove(vet);
            manager.getTransaction().commit();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }
        
        return result;
    }
    
   public Veterinario searchById(int id){
        EntityManager manager = emf.createEntityManager();
        
        manager.getTransaction().begin();
        
        Veterinario vet = manager.find(Veterinario.class, id);
        manager.getTransaction().commit();
        
        manager.close();
        return vet;
    }
   
   public List<Veterinario> findByName(String name) {
        List<Veterinario> veterinarios = null;
        EntityManager manager = emf.createEntityManager();
        
        try{
            veterinarios = (List<Veterinario>) manager.createQuery("FROM veterinario WHERE nombre LIKE :name ORDER BY nombre ASC")
                    .setParameter("name", '%' + name + '%').getResultList();
        }catch(Exception e){
            System.out.println("error al buscar los veterinarios");
            //e.printStackTrace();
        }
        
        manager.close();
        return veterinarios;
    }
    
}
