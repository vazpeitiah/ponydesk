package ponyvet.modelo.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import ponyvet.controller.EmpresaController;
import ponyvet.modelo.entidades.Empresa;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        try {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: No se puede acceder a la base de datos del sistema. Intente reiniciar la computadora\n"
                    + "o contactese con el administrador del sistema", "Error con la base de datos", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
