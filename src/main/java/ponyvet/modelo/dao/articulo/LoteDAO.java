package ponyvet.modelo.dao.articulo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.articulo.Lote;

public class LoteDAO {

    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    
    public List<Lote> read(Articulo art) {
        EntityManager manager = emf.createEntityManager();
        
        List<Lote> lotes = null;
        
        lotes = (List<Lote>) manager.createQuery("FROM lote WHERE estado = 0 AND art_id = :id").setParameter("id", art.getId()).getResultList();

        manager.close();
        return lotes;
    }

    public boolean create(Lote lote) {
        boolean resultado = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //VALORES INICIALES
        if (lote.getExisInicial() > 0 && lote.getExisActual() == 0) {
            lote.setExisActual(lote.getExisInicial());
        }

        Articulo art = manager.find(Articulo.class, lote.getArticulo().getId());

        lote.setArticulo(art);

        try {
            manager.persist(lote);
            manager.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println("error al crear lote");
        }

        manager.close();
        return resultado;
    }

    public boolean update(Lote lote) {
        boolean resultado = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        try {
            manager.merge(lote);
            manager.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println("error al actualizar lote");
        }

        manager.close();
        return resultado;
    }

    public boolean delete(int id) {
        boolean resultado = false;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        Lote lote = manager.find(Lote.class, id);

        try {
            manager.remove(lote);
            manager.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println("error al eliminar lote");
            e.getCause().getMessage();
        }

        manager.close();
        return resultado;
    }

    public Lote findById(int id) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        
    
        Lote lote = manager.find(Lote.class, id);
        manager.getTransaction().commit();

        manager.close();
        return lote;
    }
    
}
