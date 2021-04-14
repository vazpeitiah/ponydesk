package ponyvet.controller;

import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.modelo.dao.ProveedorDAO;
import ponyvet.modelo.entidades.Proveedor;
import ponyvet.modelo.entidades.Veterinario;
import ponyvet.utilities.FormatearValores;

public class ProveedorController {

    private static final ProveedorDAO dao = new ProveedorDAO();

    public static DefaultTableModel getFormattedModel(DefaultTableModel prevModel, String keywords) {
        List<Proveedor> proveedores = dao.findByDescription(keywords);
        
        int rowCount = prevModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            prevModel.removeRow(i);
        }
        
        for (Proveedor proveedor : proveedores) {
            String row[] = new String[8];
            row[0] = String.valueOf(proveedor.getId());
            row[1] = FormatearValores.formatearNombreProveedor(proveedor.getNombre(), proveedor.getAlias());
            row[2] = proveedor.getRepresentante();
            row[3] = proveedor.getRfc();
            row[4] = String.format("%s, %s", proveedor.getMunicipio(), proveedor.getEstado());
            row[5] = proveedor.getTelefono();
            row[6] = proveedor.getCelular();
            row[7] = proveedor.getEmail();

            prevModel.addRow(row);
        }
        return prevModel;
    }
    
    public static void create(Proveedor prov) {
        boolean result = dao.create(prov);
        if (result) {
            JOptionPane.showMessageDialog(null,
                    "El registro se almacenó correactamente.",
                    "Registro almacenado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "El registro no se puedo almacenar.\nVerifique si la informacion no contiene simbolos que no sean alfanumericos",
                    "Error al almacenar registro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void update(Proveedor prov){
        boolean result = dao.update(prov);
        if (result) {
            JOptionPane.showMessageDialog(null,
                    "El registro se actualizo correactamente.",
                    "registro almacenado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "El registro no se pudo actualizo.\nVerifique si la informacion no contiene simbolos que no sean alfanumericos",
                    "Error al actualizar registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void delete(Proveedor prov) {
        boolean result = dao.delete(prov.getId());
        if (result) {
            JOptionPane.showMessageDialog(null,
                    "El registro se eliminó correactamente.",
                    "Registro eliminado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se puede eliminar el registro.\nTiene registros de salida, lote, pedio o compra relacionados",
                    "Error al borrar registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Proveedor findById(int id) {
        Proveedor proveedor = dao.searchById(id);
        return proveedor;
    }
    
    public static HashMap<Integer, String> getHashMap() {
        HashMap<Integer, String> map = new HashMap<>();

        List<Proveedor> proveedores = dao.read();

        proveedores.forEach(proveedor -> {
            map.put(proveedor.getId(), FormatearValores.formatearNombreProveedor(proveedor.getNombre(), proveedor.getAlias()) );
        });

        return map;
    }

}
