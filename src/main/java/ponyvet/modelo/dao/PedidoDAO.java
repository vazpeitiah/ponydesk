package ponyvet.modelo.dao;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.config.ConfiguracionesGenerales;
import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.pedido.ListaPedido;
import ponyvet.modelo.entidades.pedido.Pedido;

public class PedidoDAO {

    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    private static int MAX_RESULTS = ConfiguracionesGenerales.MAXIMA_CANTIDAD_REGISTROS;
    
    public void create(Pedido pedido) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //VALORES POR DEFECTO
        if (pedido.getFecha() == null) {
            pedido.setFecha(LocalDateTime.now());
        }

        manager.persist(pedido);
        manager.getTransaction().commit();

        manager.close();
    }

    public List<Pedido> read() {
        EntityManager manager = emf.createEntityManager();

        List<Pedido> pedidos = (List<Pedido>) manager.createQuery("FROM pedido ORDER BY fecha DESC").setMaxResults(MAX_RESULTS).getResultList();

        manager.close();
        return pedidos;
    }

    public boolean update(Pedido ped) {
        boolean result = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //VALORES POR DEFECTO
        if (ped.getFecha() == null) {
            ped.setFecha(LocalDateTime.now());
        }
        
        Pedido pedido = manager.find(Pedido.class, ped.getId());

        ped.setListaPedidos(pedido.getListaPedidos());

        try{
            manager.merge(ped);
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

        Pedido pedido = manager.find(Pedido.class, id);
        
        try{
            manager.remove(pedido);
            manager.getTransaction().commit();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }

        manager.close();
        
        return result;
    }

    public Pedido searchById(long id) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        Pedido pedido = manager.find(Pedido.class, id);
        manager.getTransaction().commit();

        manager.close();
        return pedido;
    }
    
    
     public void createListaPedido(ListaPedido listaPedido) {
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();
       
        Articulo art = manager.find(Articulo.class, listaPedido.getArticulo().getId());
        listaPedido.setArticulo(art);
        
        Pedido ped = manager.find(Pedido.class, listaPedido.getPedido().getId());
        listaPedido.setPedido(ped);       
        
        manager.persist(listaPedido);
        manager.getTransaction().commit();

        manager.close();
    }
     
    public List<Pedido> readByDateRange(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        EntityManager manager = emf.createEntityManager();

        List<Pedido> pedidos = (List<Pedido>) 
                manager.createQuery("FROM pedido WHERE fecha BETWEEN :inicial AND :final ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("inicial", fechaInicial).setParameter("final", fechaFinal).getResultList();
        manager.close();
        return pedidos;

    }
    
    public List<Pedido> readByEstado(int estado) {
        EntityManager manager = emf.createEntityManager();

        List<Pedido> pedidos = (List<Pedido>) 
                manager.createQuery("FROM pedido WHERE estado = :estado ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("estado", estado).getResultList();
        manager.close();
        return pedidos;

    }
    
    public List<Pedido> readByDateRangeAndProv(LocalDateTime fechaInicial, LocalDateTime fechaFinal, long id) {
        EntityManager manager = emf.createEntityManager();

        List<Pedido> pedidos = (List<Pedido>) 
                manager.createQuery("FROM pedido WHERE pro_id = :proveedor AND fecha BETWEEN :inicial AND :final ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("proveedor", id).setParameter("inicial", fechaInicial).setParameter("final", fechaFinal).getResultList();
        manager.close();
        return pedidos;

    }
    
    public List<Pedido> readByDateRangeAndProvAndEsta(LocalDateTime fechaInicial, LocalDateTime fechaFinal, int proveedor, int estado) {
        EntityManager manager = emf.createEntityManager();

        List<Pedido> pedidos = (List<Pedido>) 
                manager.createQuery("FROM pedido WHERE estado = :estado AND pro_id = :proveedor AND fecha BETWEEN :inicial AND :final ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("estado", estado).setParameter("proveedor", proveedor).setParameter("inicial", fechaInicial).setParameter("final", fechaFinal).getResultList();
        manager.close();
        return pedidos;
    }
    
    public List<Pedido> readByDateRangeAndEsta(LocalDateTime fechaInicial, LocalDateTime fechaFinal, int estado) {
        EntityManager manager = emf.createEntityManager();

        List<Pedido> pedidos = (List<Pedido>) 
                manager.createQuery("FROM pedido WHERE estado = :estado AND fecha BETWEEN :inicial AND :final ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("estado", estado).setParameter("inicial", fechaInicial).setParameter("final", fechaFinal).getResultList();
        manager.close();
        return pedidos;
    }
    
     public List<Pedido> readByProvAndEsta(int proveedor, int estado) {
        EntityManager manager = emf.createEntityManager();

        List<Pedido> pedidos = (List<Pedido>) 
                manager.createQuery("FROM pedido WHERE estado = :estado AND pro_id = :proveedor ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("estado", estado).setParameter("proveedor", proveedor).getResultList();
        manager.close();
        return pedidos;
    }
}
