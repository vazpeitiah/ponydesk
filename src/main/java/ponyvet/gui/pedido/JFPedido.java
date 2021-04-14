package ponyvet.gui.pedido;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.controller.articulo.ArticuloController;
import ponyvet.gui.utilities.JInternalFrameCustom;
import ponyvet.modelo.entidades.articulo.Articulo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import ponyvet.modelo.entidades.pedido.ListaPedido;
import ponyvet.utilities.FormatearValores;
import java.awt.Color;

public final class JFPedido extends JInternalFrameCustom {
    
    public JFPedido() {
        initComponents();
        setFocusCampoBuscar();
    }

    public static void agregarArticulo(String clave, float cantidad) {
        Articulo articulo = ArticuloController.findByClave(clave);

        if (articulo != null) {
            String descripcion = articulo.getDescripcion();
            float precioCompra = articulo.getPrecioCom();
            float importe = cantidad * precioCompra;

            String row[] = new String[5];
            row[0] = clave;
            row[1] = descripcion;
            row[2] = FormatearValores.formatearCantidad(cantidad);
            row[3] = FormatearValores.formatearPrecio(precioCompra);
            row[4] = FormatearValores.formatearPrecio(importe);

            ((DefaultTableModel) tablePedidos.getModel()).addRow(row);
            actualizarEtiquetasTotal();
        }
    }

    public static void actualizarCantidadArticulo(int index, float cantidad) {
        String clave = (String) tablePedidos.getValueAt(index, 0);
        Articulo articulo = ArticuloController.findByClave(clave);

        if (articulo != null) {
            float precioCompra = articulo.getPrecioCom();
            if (cantidad > 0) {
                float importe = precioCompra * cantidad;

                tablePedidos.setValueAt(FormatearValores.formatearCantidad(cantidad), index, 2);
                tablePedidos.setValueAt(FormatearValores.formatearPrecio(importe), index, 4);

                actualizarEtiquetasTotal();
            }
        }
    }

    public static void vaciarRetiro() {
        DefaultTableModel model = (DefaultTableModel) tablePedidos.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        tablePedidos.setModel(model);
        actualizarEtiquetasTotal();
    }

    public static int buscarIndexArticulo(String clave) {
        int index = -1;
        if (tablePedidos.getRowCount() > 0) {
            for (int i = 0; i < tablePedidos.getRowCount() && index == -1; i++) {
                String aux = (String) tablePedidos.getValueAt(i, 0);
                if (clave.equals(aux)) {
                    index = i;
                }
            }
        }
        return index;
    }

    public static void elimiarArticulo(int row) {
        ((DefaultTableModel) tablePedidos.getModel()).removeRow(row);
        actualizarEtiquetasTotal();
    }

    public static void actualizarEtiquetasTotal() {
        int cantidadTotal = 0;
        float importeTotal = 0.0f;

        if (tablePedidos.getRowCount() > 0) {
            for (int i = 0; i < tablePedidos.getRowCount(); i++) {
                Articulo art = ArticuloController.findByClave((String)tablePedidos.getValueAt(i, 0));
                float cantidad = Float.parseFloat((String) tablePedidos.getValueAt(i, 2));
                float importe = cantidad * art.getPrecioCom();
                if (cantidad % 1 == 0) {
                    cantidadTotal = (int) (cantidadTotal + cantidad); //Sumamos los articulos a granel como uno solo
                } else {
                    cantidadTotal++;
                }
                importeTotal = importeTotal + importe;
            }
        }

        lblCantidadArticulos.setText(String.valueOf(cantidadTotal));
        lblCostoTotal.setText(String.valueOf(importeTotal));
    }
    
    public void setFocusCampoBuscar(){
        txtBuscar.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAgregarArticulo = new javax.swing.JButton();
        btnAjustarCantidad = new javax.swing.JButton();
        btnAgregarDesdeRetiros = new javax.swing.JButton();
        btnRemoverArticulo = new javax.swing.JButton();
        btnVaciarPedido = new javax.swing.JButton();
        btnRealizarPedido = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblCostoTotal = new javax.swing.JLabel();
        lbl$ = new javax.swing.JLabel();
        lblCantidadArticulos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePedidos = new ponyvet.gui.utilities.JTableCustom();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Realizar pedido nuevo");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnAgregarArticulo.setBackground(Color.white);
        btnAgregarArticulo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAgregarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-add-24.png"))); // NOI18N
        btnAgregarArticulo.setText("Agregar artículo");
        btnAgregarArticulo.setFocusPainted(false);
        btnAgregarArticulo.setFocusable(false);
        btnAgregarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarArticuloActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnAgregarArticulo, gridBagConstraints);

        btnAjustarCantidad.setBackground(Color.white);
        btnAjustarCantidad.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAjustarCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-adjust-24.png"))); // NOI18N
        btnAjustarCantidad.setText("Cantidad");
        btnAjustarCantidad.setFocusPainted(false);
        btnAjustarCantidad.setFocusable(false);
        btnAjustarCantidad.setPreferredSize(new java.awt.Dimension(170, 30));
        btnAjustarCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjustarCantidadActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnAjustarCantidad, gridBagConstraints);

        btnAgregarDesdeRetiros.setBackground(Color.white);
        btnAgregarDesdeRetiros.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAgregarDesdeRetiros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sorting-arrows.png"))); // NOI18N
        btnAgregarDesdeRetiros.setText("Retiros");
        btnAgregarDesdeRetiros.setFocusPainted(false);
        btnAgregarDesdeRetiros.setFocusable(false);
        btnAgregarDesdeRetiros.setPreferredSize(new java.awt.Dimension(170, 30));
        btnAgregarDesdeRetiros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDesdeRetirosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnAgregarDesdeRetiros, gridBagConstraints);

        btnRemoverArticulo.setBackground(new java.awt.Color(255, 204, 204));
        btnRemoverArticulo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRemoverArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-delete-bin-22.png"))); // NOI18N
        btnRemoverArticulo.setText("Remover");
        btnRemoverArticulo.setFocusPainted(false);
        btnRemoverArticulo.setFocusable(false);
        btnRemoverArticulo.setPreferredSize(new java.awt.Dimension(170, 30));
        btnRemoverArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverArticuloActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnRemoverArticulo, gridBagConstraints);

        btnVaciarPedido.setBackground(new java.awt.Color(255, 204, 204));
        btnVaciarPedido.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnVaciarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-delete-document-24.png"))); // NOI18N
        btnVaciarPedido.setText("Vaciar pedido");
        btnVaciarPedido.setFocusPainted(false);
        btnVaciarPedido.setFocusable(false);
        btnVaciarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaciarPedidoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnVaciarPedido, gridBagConstraints);

        btnRealizarPedido.setBackground(Color.white);
        btnRealizarPedido.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRealizarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-check-all-22.png"))); // NOI18N
        btnRealizarPedido.setText("Realizar pedido");
        btnRealizarPedido.setFocusPainted(false);
        btnRealizarPedido.setFocusable(false);
        btnRealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPedidoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Costo total:");
        jLabel4.setFocusable(false);

        lblCostoTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCostoTotal.setForeground(new java.awt.Color(0, 102, 153));
        lblCostoTotal.setText("0.00");
        lblCostoTotal.setFocusable(false);

        lbl$.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl$.setForeground(new java.awt.Color(0, 102, 153));
        lbl$.setText("$");

        lblCantidadArticulos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCantidadArticulos.setForeground(new java.awt.Color(0, 102, 153));
        lblCantidadArticulos.setText("0");
        lblCantidadArticulos.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Artículos del retiro actual");
        jLabel3.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Realizar pedido nuevo");
        jLabel5.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-create-order-32.png"))); // NOI18N
        jLabel6.setFocusable(false);

        tablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Descripción", "Cantidad", "Precio", "Importe"
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
        tablePedidos.setFocusable(false);
        tablePedidos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablePedidos);
        if (tablePedidos.getColumnModel().getColumnCount() > 0) {
            tablePedidos.getColumnModel().getColumn(0).setMinWidth(150);
            tablePedidos.getColumnModel().getColumn(0).setPreferredWidth(150);
            tablePedidos.getColumnModel().getColumn(0).setMaxWidth(150);
            tablePedidos.getColumnModel().getColumn(2).setMinWidth(100);
            tablePedidos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablePedidos.getColumnModel().getColumn(2).setMaxWidth(100);
            tablePedidos.getColumnModel().getColumn(3).setMinWidth(100);
            tablePedidos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tablePedidos.getColumnModel().getColumn(3).setMaxWidth(100);
            tablePedidos.getColumnModel().getColumn(4).setMinWidth(100);
            tablePedidos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tablePedidos.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Buscar arículo por clave:");
        jLabel1.setFocusable(false);

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-search-22.png"))); // NOI18N
        btnBuscar.setText("Buscar(Enter)");
        btnBuscar.setFocusPainted(false);
        btnBuscar.setFocusable(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblCantidadArticulos)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lbl$)
                        .addGap(18, 18, 18)
                        .addComponent(lblCostoTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRealizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRealizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCostoTotal)
                    .addComponent(jLabel4)
                    .addComponent(lbl$)
                    .addComponent(jLabel3)
                    .addComponent(lblCantidadArticulos))
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

    private void btnAgregarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarArticuloActionPerformed
        // TODO add your handling code here:
        JDAgregarArticulo agregarArticulo = new JDAgregarArticulo(new JFrame(), true);
        agregarArticulo.setVisible(true);
    }//GEN-LAST:event_btnAgregarArticuloActionPerformed

    private void btnRemoverArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverArticuloActionPerformed
        // TODO add your handling code here:
        if (tablePedidos.getRowCount() > 0) {
            if (tablePedidos.getSelectedRowCount() > 0) {
                int row = tablePedidos.getSelectedRow();
                int resp = JOptionPane.showConfirmDialog(null, "¿Deseas quitar el artículo del retiro actual?", "Borrar artículo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (resp == 0) {
                    elimiarArticulo(row);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, 
                    "No hay artículos agregados al pedido todavía. Para agregar artículos:\n"
                  + "Pulse en el botón \"Agregar\" o escaneé el código de barras del artículo.");
        }
    }//GEN-LAST:event_btnRemoverArticuloActionPerformed

    private void btnVaciarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarPedidoActionPerformed
        // TODO add your handling code here:
        if (tablePedidos.getRowCount() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desesas cancelar el retiro? Se eliminaran todos los articulos que agregaste", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (resp == 0) {
                vaciarRetiro();
            }
        }else{
            JOptionPane.showMessageDialog(null, 
                    "El pedido ya está vacío.");
        }
    }//GEN-LAST:event_btnVaciarPedidoActionPerformed

    private void btnAjustarCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjustarCantidadActionPerformed
        // TODO add your handling code here:
        if (tablePedidos.getRowCount() > 0) {
            if (tablePedidos.getSelectedRowCount() > 0) {
                int row = tablePedidos.getSelectedRow();
                Articulo art = ArticuloController.findByClave((String)tablePedidos.getValueAt(row, 0));
                JDAjustarCantidad ajustarCantidad = new JDAjustarCantidad(new JFrame(), true, row, Float.parseFloat((String)tablePedidos.getValueAt(row, 2)), art.isGranel());
                ajustarCantidad.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, 
                    "No hay artículos agregados al pedido todavía. Para agregar artículos:\n"
                  + "Pulse en el botón \"Agregar\" o escaneé el código de barras del artículo.");
        }
    }//GEN-LAST:event_btnAjustarCantidadActionPerformed

    private void btnAgregarDesdeRetirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDesdeRetirosActionPerformed
        // TODO add your handling code here:
        JDSalidas salidas = new JDSalidas(new JFrame(), true);
        salidas.setVisible(true);
    }//GEN-LAST:event_btnAgregarDesdeRetirosActionPerformed

    private void btnRealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPedidoActionPerformed
        // TODO add your handling code here:
        if (tablePedidos.getRowCount() > 0) {

            List<ListaPedido> listaPedido = new ArrayList<>();
            for (int i = 0; i < tablePedidos.getRowCount(); i++) {
                ListaPedido aux = new ListaPedido();

                aux.setArticulo(ArticuloController.findByClave((String) tablePedidos.getValueAt(i, 0)));
                aux.setCantidad(Float.parseFloat((String) tablePedidos.getValueAt(i, 2)));

                listaPedido.add(aux);
            }
            JDFinalizarPedido finalizar = new JDFinalizarPedido(new JFrame(), true, listaPedido, Float.parseFloat(lblCostoTotal.getText()));
            finalizar.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos un artículo", "Pedido vacío", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRealizarPedidoActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER && !txtBuscar.getText().equals("") && txtBuscar.getText().length() >= 10){
            int index = buscarIndexArticulo(txtBuscar.getText());
            if(index == -1){
                agregarArticulo(txtBuscar.getText(), 1);
            }else{
                float nf = Float.valueOf((String)tablePedidos.getValueAt(index, 2)) + 1;
                actualizarCantidadArticulo(index, nf);
            }
            
            txtBuscar.setText("");
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (!txtBuscar.getText().equals("") && txtBuscar.getText().length() >= 10) {
           //bsucar
        }else{
            JOptionPane.showMessageDialog(null, "Debe de escribir la clave (Mayor a 10 digitos) del articulo O en caso\n"
                + "de contar con un lector de códigos de barras, simplemente escaneá el código y el \n"
                + "artículo se añadira automáticamente a la lista de artículos.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarArticulo;
    private javax.swing.JButton btnAgregarDesdeRetiros;
    private javax.swing.JButton btnAjustarCantidad;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.JButton btnRemoverArticulo;
    private javax.swing.JButton btnVaciarPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl$;
    private static javax.swing.JLabel lblCantidadArticulos;
    private static javax.swing.JLabel lblCostoTotal;
    public static ponyvet.gui.utilities.JTableCustom tablePedidos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
