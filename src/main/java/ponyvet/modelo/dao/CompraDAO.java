package ponyvet.modelo.dao;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.config.ConfiguracionesGenerales;
import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.compra.Compra;
import ponyvet.modelo.entidades.compra.CompraDetalle;

public class CompraDAO {
    
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    private static int MAX_RESULTS = ConfiguracionesGenerales.MAXIMA_CANTIDAD_REGISTROS;
    
    public void create(Compra com) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //VALORES POR DEFECTO
        if (com.getFecha() == null) {
            com.setFecha(LocalDateTime.now());
        }
        
        manager.persist(com);
        manager.getTransaction().commit();
        
        manager.close();
    }
    
    public List<Compra> read() {
        EntityManager manager = emf.createEntityManager();
        
        List<Compra> compras = (List<Compra>) manager.createQuery("FROM compra ORDER BY fecha DESC").setMaxResults(MAX_RESULTS).getResultList();
        
        manager.close();
        return compras;
    }
    
    public boolean update(Compra com) {
        boolean result = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //VALORES POR DEFECTO
        if (com.getFecha() == null) {
            com.setFecha(LocalDateTime.now());
        }
        
        Compra compra = manager.find(Compra.class, com.getId());

        com.setCompraDetalles(compra.getCompraDetalles());
        
        try{
            manager.merge(com);
            manager.getTransaction().commit();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        manager.close();
        
        return result;
    }
    
    public boolean delete(long id) {
        boolean result = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Compra com = manager.find(Compra.class, id);
        
        try{
            manager.remove(com);
            manager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        manager.close();
        return result;
    }
    
    public Compra searchById(long id) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
        Compra com = manager.find(Compra.class, id);
        
        manager.close();
        return com;
    }
    
    public List<Compra> readByDateRange(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        EntityManager manager = emf.createEntityManager();

        List<Compra> compras = (List<Compra>) manager.createQuery("FROM compra WHERE fecha BETWEEN :inicial AND :final ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("inicial", fechaInicial).setParameter("final", fechaFinal).getResultList();
        manager.close();
        return compras;
    }
    
    public List<Compra> readByFolio(String folio) {
       EntityManager manager = emf.createEntityManager();

        List<Compra> compras = (List<Compra>) manager.createQuery("FROM compra WHERE folio = :folio ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("folio", folio).getResultList();
        manager.close();
        return compras;
    }
    
    public void createDetalle(CompraDetalle detalle) {
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();

        Articulo art = manager.find(Articulo.class, detalle.getArticulo().getId());
        detalle.setArticulo(art);

        Compra com = manager.find(Compra.class, detalle.getCompra().getId());
        detalle.setCompra(com);

        manager.persist(detalle);
        manager.getTransaction().commit();

        manager.close();
    }
}
