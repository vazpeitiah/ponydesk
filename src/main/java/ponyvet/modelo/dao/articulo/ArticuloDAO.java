package ponyvet.modelo.dao.articulo;

import java.util.List;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ponyvet.config.ConfiguracionesGenerales;
import ponyvet.modelo.dao.JPAUtil;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.articulo.Categoria;
import ponyvet.modelo.entidades.articulo.Unidad;
import ponyvet.utilities.CodigoBarras;

public class ArticuloDAO {

    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //CARGAR EL ARCHIVO DE PESISTENCIA
    private static int MAX_RESULTS = ConfiguracionesGenerales.MAXIMA_CANTIDAD_REGISTROS;
    
    public List<Articulo> read() {
        List<Articulo> articulos = null;
        EntityManager manager = emf.createEntityManager();

        try {
            articulos = (List<Articulo>) manager.createQuery("FROM articulo ORDER BY fechaMod DESC").setMaxResults(MAX_RESULTS).getResultList();
        } catch (Exception e) {
            System.out.println("error al leer la lista de articulos");
            //e.printStackTrace();
        }

        manager.close();
        return articulos;
    }

    public boolean create(Articulo art) {
        boolean resultado = false;

        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //ESTABLECIENDO VALORES POR DEFECTO
        if (art.getCategoria() == null) {
            Categoria cat = manager.find(Categoria.class, 1);
            art.setCategoria(cat);
        }
        if (art.getUnidadCompra() == null) {
            Unidad unic = manager.find(Unidad.class, 1);
            art.setUnidadCompra(unic);
        }
        if (art.getUnidadSalida() == null) {
            Unidad unis = manager.find(Unidad.class, 1);
            art.setUnidadSalida(unis);
        }
        if (art.getFactor() == 0.0f) {
            art.setFactor(1.0f);
        }
        if(art.getLocalizacion() == null){
            art.setLocalizacion("Almacén");
        }
        art.setFechaMod(LocalDateTime.now());

        try {
            manager.persist(art);
            manager.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println("error al crear artículo");
            e.printStackTrace();
        }

        manager.close();

        return resultado;
    }

    public boolean update(Articulo art) {
        boolean resultado = false;

        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        //ESTABLECIENDO VALORES POR DEFECTO
        if (art.getCategoria() == null) {
            Categoria cat = manager.find(Categoria.class, 1);
            art.setCategoria(cat);
        }
        if (art.getUnidadCompra() == null) {
            Unidad unic = manager.find(Unidad.class, 1);
            art.setUnidadCompra(unic);
        }
        if (art.getUnidadSalida() == null) {
            Unidad unis = manager.find(Unidad.class, 1);
            art.setUnidadSalida(unis);
        }
        if (art.getFactor() == 0.0f) {
            art.setFactor(1.0f);
        }
        if(art.getLocalizacion() == null){
            art.setLocalizacion("Almacén");
        }
        art.setFechaMod(LocalDateTime.now());
        
        Articulo articulo = manager.find(Articulo.class, art.getId());

        art.setLotes(articulo.getLotes());
        
        try {
            manager.merge(art);
            manager.getTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println("error al actualizar artículo");
            e.printStackTrace();
        }

        
        manager.close();

        return resultado;
    }

    public boolean delete(int id) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        Articulo art = manager.find(Articulo.class, id);

        boolean state = false;
        try {
            manager.remove(art);
            manager.getTransaction().commit();
            state = true;
        } catch (Exception e) {
            System.out.println("error al eliminar artículo");
        }

        manager.close();
        return state;
    }

    public Articulo findById(int id) {
        Articulo art = null;
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        try{
            art = manager.find(Articulo.class, id);
            manager.getTransaction().commit();
        }catch(Exception e){
            System.out.println("error al buscar articulo por id");
            //e.printStackTrace();
        }

        manager.close();
        return art;
    }

    public Articulo findByClave(String clave) {
        EntityManager manager = emf.createEntityManager();
        Articulo articulo = null;
        
        try {
            articulo = (Articulo) manager.createQuery("FROM articulo WHERE clave = :clave").setMaxResults(MAX_RESULTS).setParameter("clave", clave).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            System.out.println("error al buscar articulo por id");
            //e.printStackTrace();
        }
        
        manager.close();
        return articulo;
    }

    public List<Articulo> findByDescripcion(String keywords) {
        List<Articulo> articulos = null;
        EntityManager manager = emf.createEntityManager();
        
        try{
            articulos = (List<Articulo>) manager.createQuery("FROM articulo WHERE clave LIKE :keywords OR descripcion LIKE :keywords ORDER BY fechaMod DESC").setMaxResults(MAX_RESULTS)
                    .setParameter("keywords", '%' + keywords + '%').getResultList();
        }catch(Exception e){
            System.out.println("error al buscar articulos por desccripcion");
            //e.printStackTrace();
        }
        
        manager.close();
        return articulos;
    }
//    
//    public static void main(String[] args) {
//        ArticuloDAO dao = new ArticuloDAO();
//        for (int i = 0; i < 1000; i++) {
//            Articulo articulo = new Articulo();
//            articulo.setClave(CodigoBarras.generarCodigoGenerico(13));
//            articulo.setDescripcion("ARTICULO PARA PRUEBA RÁPIDA " + (i + 1001));
//            articulo.setPrecioCom((1000 + i)/(i+1));
//            articulo.setPrecioSal((1000 + i)/(i+1));
//            articulo.setExistencia(100);
//            dao.create(articulo);
//            System.out.println(articulo.toString());
//        }
//    }

}
