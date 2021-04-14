package ponyvet.controller;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import ponyvet.modelo.dao.VeterinarioDAO;
import ponyvet.modelo.entidades.Veterinario;

public class VeterinarioController {

    private static final VeterinarioDAO dao = new VeterinarioDAO();

    public static DefaultTableModel getModelVeterinarios(DefaultTableModel model, String name) {

        List<Veterinario> articulos = dao.findByName(name);

        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for (Veterinario veterinario : articulos) {
            String row[] = new String[4];
            row[0] = String.valueOf(veterinario.getId());
            row[1] = veterinario.getNombre();
            row[2] = veterinario.getDireccion();
            row[3] = veterinario.getTelefono();

            model.addRow(row);
        }

        return model;
    }

    public static void create(Veterinario vet) {
        boolean result = dao.create(vet);
        if (result) {
            JOptionPane.showMessageDialog(null,
                    "El veterinario se almacenó correactamente.",
                    "Veterinario almacenado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "El veterinario no se puedo almacenar.\nVerifique si la informacion no contiene simbolos que no sean alfanumericos",
                    "Error al almacenar veterinario", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void update(Veterinario vet){
        boolean result = dao.update(vet);
        if (result) {
            JOptionPane.showMessageDialog(null,
                    "El veterinario se actualizo correactamente.",
                    "Veterinario almacenado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "El veterinario no se pudo actualizo.\nVerifique si la informacion no contiene simbolos que no sean alfanumericos",
                    "Error al actualizar veterinario", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void delete(Veterinario vet){
        boolean result = dao.delete(vet.getId());
        if (result) {
            JOptionPane.showMessageDialog(null, 
                    "El veterinario se eliminó correactamente.", 
                    "Veterinario eliminado", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, 
                    "No se puede eliminar el veterinario.\nTiene registros de salida, lote, pedio o compra relacionados", 
                    "Error al borrar veterinario", JOptionPane.ERROR_MESSAGE);
        }
    }

    public HashMap<Integer, String> getHashMap() {
        HashMap<Integer, String> map = new HashMap<>();

        List<Veterinario> veterinarios = dao.read();

        veterinarios.forEach(veterinario -> {
            map.put(veterinario.getId(), veterinario.getNombre());
        });

        return map;
    }

    public Veterinario findById(int id) {
        Veterinario veterinario = dao.searchById(id);
        return veterinario;
    }
}
