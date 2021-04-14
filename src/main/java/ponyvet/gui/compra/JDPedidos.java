package ponyvet.gui.compra;

import javax.swing.table.DefaultTableModel;
import ponyvet.controller.PedidoController;
import java.awt.Color;
import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ponyvet.controller.ProveedorController;
import ponyvet.modelo.entidades.Proveedor;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.pedido.ListaPedido;
import ponyvet.modelo.entidades.pedido.Pedido;

public class JDPedidos extends javax.swing.JDialog {
    
    private final HashMap<Integer, String> map;

    public JDPedidos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
         map = ProveedorController.getHashMap();

        map.entrySet().forEach(veterinario -> {
            jcbProveedores.addItem(veterinario);
        });
        mostrarTodo();
        
    }
    
    public static void mostrarTodo(){
        DefaultTableModel model = PedidoController.getModelFromList(
                (DefaultTableModel)tableArticulos.getModel(), 
                PedidoController.findByEstado(Pedido.NORECIBIDO));
        tableArticulos.setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableArticulos = new ponyvet.gui.utilities.JTableCustom();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jcbProveedores = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos");

        tableArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Estado", "Fecha realizado", "Costo total", "Proveedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableArticulos.setFocusable(false);
        tableArticulos.getTableHeader().setReorderingAllowed(false);
        tableArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableArticulosMouseClicked(evt);
            }
        });
        tableArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableArticulosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableArticulos);
        if (tableArticulos.getColumnModel().getColumnCount() > 0) {
            tableArticulos.getColumnModel().getColumn(0).setMinWidth(60);
            tableArticulos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableArticulos.getColumnModel().getColumn(0).setMaxWidth(100);
            tableArticulos.getColumnModel().getColumn(3).setMinWidth(60);
            tableArticulos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableArticulos.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jButton1.setBackground(Color.white);
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Ver detalles del pedido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(Color.white);
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-check-all-22.png"))); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jcbProveedores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbProveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los proveedores" }));
        jcbProveedores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbProveedoresItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jcbProveedores, gridBagConstraints);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-search-22.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnBuscar, gridBagConstraints);

        btnMostrarTodo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMostrarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-select-all-24.png"))); // NOI18N
        btnMostrarTodo.setText("Mostrar todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnMostrarTodo, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Proveedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableArticulosMouseClicked
        // TODO add your handling code here
        if (evt.getClickCount() == 2 && tableArticulos.getSelectedRowCount() > 0) {
            Point point = evt.getPoint();
            int row = tableArticulos.rowAtPoint(point);
            String id = (String) tableArticulos.getValueAt(row, 0);
            Pedido pedido = PedidoController.findById(id);
            
            JDArticulosPedido articulos = new JDArticulosPedido(new JFrame(), true, pedido);
            articulos.setVisible(true);
        }
    }//GEN-LAST:event_tableArticulosMouseClicked

    private void tableArticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableArticulosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableArticulosKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (tableArticulos.getRowCount() > 0) {
            if (tableArticulos.getSelectedRowCount() > 0) {
                int row = tableArticulos.getSelectedRow();
                String id = (String) tableArticulos.getValueAt(row, 0);

                Pedido pedido = PedidoController.findById(id);

                JDArticulosPedido articulos = new JDArticulosPedido(new JFrame(), true, pedido);
                articulos.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (tableArticulos.getRowCount() > 0) {
            if (tableArticulos.getSelectedRowCount() > 0) {
                int row = tableArticulos.getSelectedRow();
                String id = (String) tableArticulos.getValueAt(row, 0);
                Pedido pedido = PedidoController.findById(id);
                
                int resp = JOptionPane.showConfirmDialog(null,
                        String.format("¿Deseas agregar los artículos del pedido con ID %d del provedor %s?\n"
                                + "Los artículos que tengas en el carrito se eliminaran y se sustituiran por estos.", pedido.getId(), pedido.getProveedor().getNombre()),
                        "Agregar artículos al carrito", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (resp == 0) {
                    JFCompra.vaciarRetiro();
                    Set<ListaPedido> detalles = pedido.getListaPedidos();
                    for (ListaPedido detalle : detalles) {
                        Articulo articulo = detalle.getArticulo();
                        JFCompra.agregarArticulo(articulo.getClave(), detalle.getCantidad());
                    }
                    JFCompra.pedido = pedido;
                    JFCompra.txtAvisos.setText(
                            String.format("Estás recibiendo el pedido con clave %d de %s. Para cancelar da clic en el botón de \"Cancelar Ingreso\"", 
                                    pedido.getId(),
                                    pedido.getProveedor().getNombre()));
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        // TODO add your handling code here:
        mostrarTodo();
        jcbProveedores.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, "Se actualizó la tabla de manera correcta.");
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:   
        if (jcbProveedores.getSelectedIndex() == 0) {
            mostrarTodo();
        } else {
            Proveedor prov = ProveedorController.findById(((HashMap.Entry<Integer,String>)jcbProveedores.getSelectedItem()).getKey());
            List<Pedido> pedidos = PedidoController.findByProvAndEsta(prov.getId(), Pedido.NORECIBIDO);
            if (pedidos.size() > 0) {
                DefaultTableModel prevModel = (DefaultTableModel) tableArticulos.getModel();
                DefaultTableModel model = PedidoController.getModelFromList(prevModel, pedidos);
                tableArticulos.setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron pedidos pendientes del proveedor " + prov.getNombre());
            }
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jcbProveedoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbProveedoresItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbProveedoresItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JToggleButton btnMostrarTodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<Object> jcbProveedores;
    public static ponyvet.gui.utilities.JTableCustom tableArticulos;
    // End of variables declaration//GEN-END:variables
}
