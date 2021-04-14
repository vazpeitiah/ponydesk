package ponyvet.gui.proveedor;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.controller.ProveedorController;
import ponyvet.gui.utilities.JInternalFrameCustom;
import ponyvet.modelo.entidades.Proveedor;

public class JFProveedor extends JInternalFrameCustom {

    public JFProveedor() {
        initComponents();
        actualizarTabla("");
    }

    public static void actualizarTabla(String keywords) {
        DefaultTableModel model = ProveedorController.getFormattedModel((DefaultTableModel) tableProveedores.getModel(), keywords);
        tableProveedores.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProveedores = new ponyvet.gui.utilities.JTableCustom();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Lista de proveedores");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Clave", "Nombre", "Representante", "RFC", "Dirección", "Teléfono", "Celular", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProveedores.setFocusable(false);
        tableProveedores.getTableHeader().setReorderingAllowed(false);
        tableProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProveedoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableProveedores);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Buscar:");
        jLabel1.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jLabel1, gridBagConstraints);

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(txtBuscar, gridBagConstraints);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-search-22.png"))); // NOI18N
        btnBuscar.setText("Buscar(Enter)");
        btnBuscar.setFocusable(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(btnBuscar, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(800, 64));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnAgregar.setBackground(new Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-add-24.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFocusable(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 1);
        jPanel2.add(btnAgregar, gridBagConstraints);

        btnEditar.setBackground(new Color(255, 255, 255));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-edit-24.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setFocusPainted(false);
        btnEditar.setFocusable(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel2.add(btnEditar, gridBagConstraints);

        btnEliminar.setBackground(new Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-trash-can-24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel2.add(btnEliminar, gridBagConstraints);

        btnMostrarTodo.setBackground(new Color(255, 255, 255));
        btnMostrarTodo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-select-all-24.png"))); // NOI18N
        btnMostrarTodo.setText("Actualizar Tabla");
        btnMostrarTodo.setFocusPainted(false);
        btnMostrarTodo.setFocusable(false);
        btnMostrarTodo.setOpaque(true);
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 0);
        jPanel2.add(btnMostrarTodo, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Proveedores");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-supplier-32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedoresMouseClicked
        // TODO add your handling code here:
         if (evt.getClickCount() == 2 && tableProveedores.getSelectedRowCount() > 0) {
            Point point = evt.getPoint();
            int row = tableProveedores.rowAtPoint(point);
            String id = (String) tableProveedores.getValueAt(row, 0);
            Proveedor prov = ProveedorController.findById(Integer.parseInt(id));
            JDFormularioProveedor editForm = new JDFormularioProveedor(null, true, prov);
            editForm.setVisible(true);
        }
    }//GEN-LAST:event_tableProveedoresMouseClicked

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        //actualizarTabla(txtBuscar.getText());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtBuscar.getText().equals("")) {
                actualizarTabla(txtBuscar.getText());
                if (tableProveedores.getRowCount() <= 0) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados con ese nombre");
                    actualizarTabla("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes escribir el nombre del proveedor en el cuadro de búsqueda.");
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (!txtBuscar.getText().equals("")) {
            actualizarTabla(txtBuscar.getText());
            if (tableProveedores.getRowCount() <= 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados con ese nombre");
                actualizarTabla("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes escribir el nombre del proveedor en el cuadro de búsqueda.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        JDFormularioProveedor formulario = new JDFormularioProveedor(new JFrame(), true, null);
        formulario.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (tableProveedores.getSelectedRowCount() > 0) {
            int row = tableProveedores.getSelectedRow();
            String id = (String) tableProveedores.getValueAt(row, 0);
            Proveedor prov = new ProveedorController().findById(Integer.parseInt(id));
            JDFormularioProveedor editForm = new JDFormularioProveedor(null, true, prov);
            editForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla", "Registro no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tableProveedores.getSelectedRowCount() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Deseas borrar el registro?", "Eliminar registro", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

            if (resp == 0) {
                int row = tableProveedores.getSelectedRow();
                String id = (String) tableProveedores.getValueAt(row, 0);
                Proveedor prov = new ProveedorController().findById(Integer.parseInt(id));

                ProveedorController.delete(prov);

                actualizarTabla("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla", "Veterinario no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        actualizarTabla("");
        JOptionPane.showMessageDialog(null, "Se actualizó la tabla correctamente.");
    }//GEN-LAST:event_btnMostrarTodoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAgregar;
    public static javax.swing.JButton btnBuscar;
    public static javax.swing.JButton btnEditar;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnMostrarTodo;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JSeparator jSeparator1;
    public static ponyvet.gui.utilities.JTableCustom tableProveedores;
    public static javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
