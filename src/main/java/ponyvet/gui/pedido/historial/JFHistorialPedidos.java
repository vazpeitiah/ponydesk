package ponyvet.gui.pedido.historial;

import java.awt.Color;
import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.controller.PedidoController;
import ponyvet.controller.ProveedorController;
import ponyvet.gui.utilities.JInternalFrameCustom;
import ponyvet.modelo.entidades.Proveedor;
import ponyvet.modelo.entidades.pedido.Pedido;

public class JFHistorialPedidos extends JInternalFrameCustom{
    
     private final HashMap<Integer, String> map;
    
    public JFHistorialPedidos() {
        initComponents();
        
        map = ProveedorController.getHashMap();

        map.entrySet().forEach(veterinario -> {
            jComboBox1.addItem(veterinario);
        });

        dpFechaFinal.setDate(LocalDate.now());
        dpFechaInicial.setDate(LocalDate.now().minus(1, ChronoUnit.WEEKS));
        
        actualizarTabla(PedidoController.read());
    }
    
    public static void actualizarTabla(List<Pedido> pedidos){
        DefaultTableModel prevModel = (DefaultTableModel) tableSalidas.getModel();
        DefaultTableModel model = PedidoController.getModelFromList(prevModel, pedidos);
        tableSalidas.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        dpFechaInicial = new com.github.lgooddatepicker.components.DatePicker();
        dpFechaFinal = new com.github.lgooddatepicker.components.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnMostrarTodo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSalidas = new ponyvet.gui.utilities.JTableCustom();

        setClosable(true);
        setTitle("Historial de pedidos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Fecha Inicial");
        jLabel1.setFocusable(false);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 2);
        jPanel4.add(jLabel1, gridBagConstraints);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-search-22.png"))); // NOI18N
        btnBuscar.setText("Buscar(Enter)");
        btnBuscar.setFocusable(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 5, 5);
        jPanel4.add(btnBuscar, gridBagConstraints);

        dpFechaInicial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(dpFechaInicial, gridBagConstraints);

        dpFechaFinal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dpFechaFinal.setPreferredSize(new java.awt.Dimension(174, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 5, 2);
        jPanel4.add(dpFechaFinal, gridBagConstraints);

        jLabel2.setText("Fecha Final");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 2);
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel5.setText("Filtrar búsqueda por:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jPanel4.add(jLabel5, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los proveedores" }));
        jComboBox1.setBackground(Color.white);
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setLightWeightPopupEnabled(false);
        jComboBox1.setRequestFocusEnabled(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 5, 2);
        jPanel4.add(jComboBox1, gridBagConstraints);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los estados", "No recíbido", "Recíbido", "Cancelado" }));
        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jComboBox2, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(800, 64));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-select-all-24.png"))); // NOI18N
        btnMostrarTodo.setText("Actualizar tabla");
        btnMostrarTodo.setBackground(new Color(255, 255, 255));
        btnMostrarTodo.setFocusPainted(false);
        btnMostrarTodo.setFocusable(false);
        btnMostrarTodo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMostrarTodo.setOpaque(true);
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 0);
        jPanel2.add(btnMostrarTodo, gridBagConstraints);

        jButton1.setText("jButton1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jButton1, gridBagConstraints);

        jButton2.setText("Mostrar detalles");
        jButton2.setBackground(Color.white);
        jButton2.setFocusPainted(false);
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 0);
        jPanel2.add(jButton2, gridBagConstraints);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-delete-bin-22.png"))); // NOI18N
        jButton3.setText("Eliminar");
        jButton3.setBackground(Color.white);
        jButton3.setFocusPainted(false);
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 0);
        jPanel2.add(jButton3, gridBagConstraints);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-edit-24.png"))); // NOI18N
        jButton4.setText("Editar");
        jButton4.setBackground(Color.white);
        jButton4.setFocusPainted(false);
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 0);
        jPanel2.add(jButton4, gridBagConstraints);

        jLabel4.setText("CONSULTAR PEDIDOS");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-purchase-order-32.png"))); // NOI18N
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        tableSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Estado", "Fecha realizado", "Costo Total", "Proveedor", "Fecha Recibido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSalidasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSalidas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
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
                .addGap(8, 8, 8)
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if(!dpFechaInicial.getDateStringOrEmptyString().equals("") && !dpFechaFinal.getDateStringOrEmptyString().equals("")){

            LocalDate dateInicial = dpFechaInicial.getDate();
            LocalDate dateFinal = dpFechaFinal.getDate();

            LocalDateTime fechaInicial = dateInicial.atStartOfDay();
            LocalDateTime fechaFinal = dateFinal.atTime(LocalTime.MAX);

            if(jComboBox1.getSelectedIndex() == 0){ //Buscar con todos los proveedores
                if(jComboBox2.getSelectedIndex() == 0){ // Buscar con todos los proveedores y todos los estados
                    actualizarTabla(PedidoController.findByDateRange(fechaInicial, fechaFinal));
                }else{
                    switch(jComboBox2.getSelectedIndex()){
                        case 1:
                            actualizarTabla(PedidoController.findByDateRangeAndEstado(fechaInicial, fechaFinal, Pedido.NORECIBIDO));
                            break;
                        case 2:
                            actualizarTabla(PedidoController.findByDateRangeAndEstado(fechaInicial, fechaFinal, Pedido.RECIBIDO));
                            break;
                        case 3:
                            actualizarTabla(PedidoController.findByDateRangeAndEstado(fechaInicial, fechaFinal, Pedido.CANCELADO));
                            break;
                        default:
                            JOptionPane.showConfirmDialog(null, "Error con el estado del pedido");
                            break;
                    }
                }
            }else{
                Proveedor prov = ProveedorController.findById(((HashMap.Entry<Integer,String>)jComboBox1.getSelectedItem()).getKey());
                if(jComboBox2.getSelectedIndex() == 0){ // Buscar con un proveedor en particular y todos los estados
                    actualizarTabla(PedidoController.findByDateRangeAndProv(fechaInicial, fechaFinal, prov));
                }else{
                    switch(jComboBox2.getSelectedIndex()){
                        case 1:
                            actualizarTabla(PedidoController.findByDateRangeAndProvAndEstado(fechaInicial, fechaFinal, prov, Pedido.NORECIBIDO));
                            break;
                        case 2:
                            actualizarTabla(PedidoController.findByDateRangeAndProvAndEstado(fechaInicial, fechaFinal, prov, Pedido.RECIBIDO));
                            break;
                        case 3:
                            actualizarTabla(PedidoController.findByDateRangeAndProvAndEstado(fechaInicial, fechaFinal, prov, Pedido.CANCELADO));
                            break;
                        default:
                            JOptionPane.showConfirmDialog(null, "Error con el estado del pedido");
                            break;
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debes ingresar las fechas de manera correcta.", "Fechas incorrectas", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        actualizarTabla(PedidoController.read());
        jComboBox1.setSelectedIndex(0);
        dpFechaFinal.setDate(LocalDate.now());
        dpFechaInicial.setDate(LocalDate.now().minus(1, ChronoUnit.WEEKS));
        JOptionPane.showMessageDialog(null, "Se actualizó la tabla correctamente.");
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (tableSalidas.getSelectedRowCount() > 0) {
            int row = tableSalidas.getSelectedRow();
            String id = (String) tableSalidas.getValueAt(row, 0);

            Pedido pedido= PedidoController.findById(id);

            JDDetallePedido pedidoDetalle = new JDDetallePedido(new JFrame(), true, pedido);
            pedidoDetalle.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (tableSalidas.getSelectedRowCount() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Deseas quitar el artículo del retiro actual?", "Borrar artículo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                int row = tableSalidas.getSelectedRow();
                String id = (String) tableSalidas.getValueAt(row, 0);
                PedidoController.delete(id);
                actualizarTabla(PedidoController.read());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un registro de la tabla", "Registro no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (tableSalidas.getSelectedRowCount() > 0) {
            int row = tableSalidas.getSelectedRow();
            String id = (String) tableSalidas.getValueAt(row, 0);

            Pedido pedido= PedidoController.findById(id);

            JDEditarPedido editarPedido = new JDEditarPedido(new JFrame(), true, pedido);
            editarPedido.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tableSalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSalidasMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && tableSalidas.getSelectedRowCount() > 0) {
            Point point = evt.getPoint();
            int row = tableSalidas.rowAtPoint(point);
            String id = (String) tableSalidas.getValueAt(row, 0);
            Pedido pedido = PedidoController.findById(id);

            JDDetallePedido pedidoDetalle = new JDDetallePedido(new JFrame(), true, pedido);
            pedidoDetalle.setVisible(true);
        }
    }//GEN-LAST:event_tableSalidasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnMostrarTodo;
    private com.github.lgooddatepicker.components.DatePicker dpFechaFinal;
    private com.github.lgooddatepicker.components.DatePicker dpFechaInicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<Object> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public static ponyvet.gui.utilities.JTableCustom tableSalidas;
    // End of variables declaration//GEN-END:variables
}
