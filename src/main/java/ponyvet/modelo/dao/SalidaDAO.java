package ponyvet.modelo.dao;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.config.ConfiguracionesGenerales;
import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.salida.Salida;
import ponyvet.modelo.entidades.salida.SalidaDetalle;

public class SalidaDAO {

    private EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    private static int MAX_RESULTS = ConfiguracionesGenerales.MAXIMA_CANTIDAD_REGISTROS;
    
    public void create(Salida sal) {
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();

        //VALORES INICIALES
        if (sal.getFecha() == null) {
            sal.setFecha(LocalDateTime.now());
        }

        manager.persist(sal);
        manager.getTransaction().commit();

        manager.close();
    }

    public List<Salida> read() {
        EntityManager manager = emf.createEntityManager();

        List<Salida> salidas = (List<Salida>) manager.createQuery("FROM salida ORDER BY fecha DESC").setMaxResults(MAX_RESULTS).getResultList();
        manager.close();
        return salidas;
    }

    public boolean update(Salida sal) {
        boolean result = false;
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();

        //VALORES INICIALES
        if (sal.getFecha() == null) {
            sal.setFecha(LocalDateTime.now());
        }
        
        Salida salida = manager.find(Salida.class, sal.getId());

        sal.setSalidas_detalle(salida.getSalidas_detalle());

        try{
            manager.merge(sal);
            manager.getTransaction().commit();
            result = true;
        } catch(Exception e){
            System.out.println("Error al actulizar salida");
            e.printStackTrace();
        }
        
        manager.close();
       
        
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();
        Salida sal = manager.find(Salida.class, id);
        
        try{          
            manager.remove(sal);
            manager.getTransaction().commit();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            manager.close();
        }

        return result;
    }

    public Salida searchById(int id) {
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();

        Salida sal = manager.find(Salida.class, id);
        manager.getTransaction().commit();

        manager.close();
        return sal;
    }

    public List<Salida> readByDateRange(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        EntityManager manager = emf.createEntityManager();

        List<Salida> salidas = (List<Salida>) manager.createQuery("FROM salida WHERE fecha BETWEEN :inicial AND :final ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("inicial", fechaInicial).setParameter("final", fechaFinal).getResultList();
        manager.close();
        return salidas;

    }
    
    public List<Salida> readByDateRangeAndVeterinario(LocalDateTime fechaInicial, LocalDateTime fechaFinal, int idVet) {
        EntityManager manager = emf.createEntityManager();

        List<Salida> salidas = (List<Salida>) manager.createQuery("FROM salida WHERE vet_id = :idVet AND fecha BETWEEN :inicial AND :final ORDER BY fecha DESC").setMaxResults(MAX_RESULTS)
                .setParameter("idVet", idVet).setParameter("inicial", fechaInicial).setParameter("final", fechaFinal).getResultList();
        manager.close();
        return salidas;

    }

    public void createDetalle(SalidaDetalle detalle) {
        EntityManager manager = emf.createEntityManager();

        manager.getTransaction().begin();

        Articulo art = manager.find(Articulo.class, detalle.getArticulo().getId());
        detalle.setArticulo(art);

        Salida sal = manager.find(Salida.class, detalle.getSalida().getId());
        detalle.setSalida(sal);

        manager.persist(detalle);
        manager.getTransaction().commit();

        manager.close();
    }

}
