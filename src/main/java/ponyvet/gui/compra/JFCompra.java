package ponyvet.gui.compra;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.controller.articulo.ArticuloController;
import ponyvet.gui.utilities.JInternalFrameCustom;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.compra.CompraDetalle;
import ponyvet.modelo.entidades.pedido.Pedido;

public class JFCompra extends JInternalFrameCustom {
    
    public static Pedido pedido = null;

    public JFCompra() {
        initComponents();
    }
    
    public void setFocusCampoBuscar(){
        txtBuscar.requestFocus();
    }

    public static boolean agregarArticulo(String clave, float cantidad) {
        boolean resp = false;
        Articulo articulo = ArticuloController.findByClave(clave);

        if (articulo != null) {
            String descripcion = articulo.getDescripcion();
            float precioCompra = articulo.getPrecioCom();
            float importe = cantidad * precioCompra;

            String row[] = new String[5];
            row[0] = clave;
            row[1] = descripcion;
            row[2] = String.valueOf(cantidad);
            row[3] = String.valueOf(precioCompra);
            row[4] = String.valueOf(importe);
            //row[5] = String.valueOf(articulo.getExistencia());

            ((DefaultTableModel) tableListaArticulos.getModel()).addRow(row);
            actualizarEtiquetasTotal();

            resp = true;
        }
        return resp;
    }

    public static boolean actualizarCantidadArticulo(int index, float cantidad) {
        boolean result = false;

        String clave = (String) tableListaArticulos.getValueAt(index, 0);

        Articulo articulo = new ArticuloController().findByClave(clave);

        if (articulo != null) {
            float precioCompra = Float.parseFloat((String) tableListaArticulos.getValueAt(index, 3));
            if (cantidad > 0) {
                float importe = precioCompra * cantidad;

                tableListaArticulos.setValueAt(String.valueOf(cantidad), index, 2);
                tableListaArticulos.setValueAt(String.valueOf(importe), index, 4);

                actualizarEtiquetasTotal();
                result = true;
            }
        }

        return result;
    }

    public static boolean actualizarPrecioArticulo(int index, float precio) {
        boolean result = false;

        String clave = (String) tableListaArticulos.getValueAt(index, 0);

        Articulo articulo = new ArticuloController().findByClave(clave);

        if (articulo != null) {
            tableListaArticulos.setValueAt(articulo.getPrecioCom(), index, 3);
        }

        return result;
    }

    public static void vaciarRetiro() {
        DefaultTableModel model = (DefaultTableModel) tableListaArticulos.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        tableListaArticulos.setModel(model);
        actualizarEtiquetasTotal();
    }

    public static int buscarIndexArticulo(String clave) {
        int index = -1;
        if (tableListaArticulos.getRowCount() > 0) {
            for (int i = 0; i < tableListaArticulos.getRowCount() && index == -1; i++) {
                String aux = (String) tableListaArticulos.getValueAt(i, 0);
                if (clave.equals(aux)) {
                    index = i;
                }
            }
        }
        return index;
    }

    public static void elimiarArticulo(int row) {
        ((DefaultTableModel) tableListaArticulos.getModel()).removeRow(row);
        actualizarEtiquetasTotal();
    }

    public static void actualizarEtiquetasTotal() {
        int cantidadTotal = 0;
        float importeTotal = 0.0f;

        if (tableListaArticulos.getRowCount() > 0) {
            for (int i = 0; i < tableListaArticulos.getRowCount(); i++) {
                float cantidad = Float.parseFloat((String) tableListaArticulos.getValueAt(i, 2));
                float importe = Float.parseFloat((String) tableListaArticulos.getValueAt(i, 4));
                if (cantidad % 1 == 0) {
                    cantidadTotal = (int) (cantidadTotal + cantidad);
                } else {
                    cantidadTotal++;
                }
                importeTotal = importeTotal + importe;
            }
        }

        lblCantidadArticulos.setText(String.valueOf(cantidadTotal));
        lblCostoTotal.setText(String.valueOf(importeTotal));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListaArticulos = new ponyvet.gui.utilities.JTableCustom();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblCantidadArticulos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCostoTotal = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lbl$ = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtAvisos = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Registrar compra");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tableListaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Descripcion", "Cantidad", "Precio", "Importe"
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
        tableListaArticulos.setFocusable(false);
        tableListaArticulos.getTableHeader().setReorderingAllowed(false);
        tableListaArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListaArticulosMouseClicked(evt);
            }
        });
        tableListaArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableListaArticulosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableListaArticulos);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton3.setBackground(new Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-add-24.png"))); // NOI18N
        jButton3.setText("Agregar");
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.setPreferredSize(new java.awt.Dimension(187, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton3, gridBagConstraints);

        jButton5.setBackground(new Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-adjust-24.png"))); // NOI18N
        jButton5.setText("Modificar cantidad");
        jButton5.setFocusPainted(false);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton5, gridBagConstraints);

        jButton6.setBackground(new Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-trash-can-24.png"))); // NOI18N
        jButton6.setText("Quitar artículo");
        jButton6.setFocusPainted(false);
        jButton6.setFocusable(false);
        jButton6.setPreferredSize(new java.awt.Dimension(187, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton6, gridBagConstraints);

        jButton9.setBackground(new java.awt.Color(255, 204, 204));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-delete-document-24.png"))); // NOI18N
        jButton9.setText("Cancelar Ingreso");
        jButton9.setFocusPainted(false);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton9, gridBagConstraints);

        jButton7.setBackground(new Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-delivery-24.png"))); // NOI18N
        jButton7.setText("Pedidos");
        jButton7.setFocusPainted(false);
        jButton7.setFocusable(false);
        jButton7.setPreferredSize(new java.awt.Dimension(187, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton7, gridBagConstraints);

        jButton4.setBackground(Color.white);
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-update-tag-22.png"))); // NOI18N
        jButton4.setText("Actualizar precio");
        jButton4.setFocusable(false);
        jButton4.setPreferredSize(new java.awt.Dimension(187, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Registrar entrada de medicamentos");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-shopping-basket-add-32.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblCantidadArticulos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCantidadArticulos.setForeground(new java.awt.Color(0, 102, 153));
        lblCantidadArticulos.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Artículos en la entrada actual");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Costo total:");

        lblCostoTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCostoTotal.setForeground(new java.awt.Color(0, 102, 153));
        lblCostoTotal.setText("0.00");

        jButton2.setBackground(new Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-check-all-22.png"))); // NOI18N
        jButton2.setText("Finalizar entrada");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbl$.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl$.setForeground(new java.awt.Color(0, 102, 153));
        lbl$.setText("$");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantidadArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl$)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCostoTotal)
                .addGap(58, 58, 58)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCostoTotal)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(lblCantidadArticulos)
                    .addComponent(lbl$))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Buscar para agregar:");
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

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-search-22.png"))); // NOI18N
        jButton1.setText("Buscar(Enter)");
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtAvisos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAvisos.setForeground(new java.awt.Color(204, 0, 0));
        txtAvisos.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtAvisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAvisos))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableListaArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListaArticulosMouseClicked
        // TODO add your handling code here
    }//GEN-LAST:event_tableListaArticulosMouseClicked

    private void tableListaArticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableListaArticulosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableListaArticulosKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JDAgregarArticulo buscar = new JDAgregarArticulo(new JFrame(), true);
        buscar.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (tableListaArticulos.getRowCount() > 0) {
            if (tableListaArticulos.getSelectedRowCount() > 0) {
                int row = tableListaArticulos.getSelectedRow();
                float cantidad = Float.parseFloat((String)tableListaArticulos.getValueAt(row, 2));
                JDAjustarCantidad ajustarCantidad = new JDAjustarCantidad(new JFrame(), true, row, cantidad);
                ajustarCantidad.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay artículos agregados al carrito todavía. Para agregar artículos:\nPulse en el botón \"Agregar\" o escaneé el código de barras del artículo.");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (tableListaArticulos.getRowCount() > 0) {
            if (tableListaArticulos.getSelectedRowCount() > 0) {
                int row = tableListaArticulos.getSelectedRow();
                int resp = JOptionPane.showConfirmDialog(null, "¿Deseas quitar el artículo del retiro actual?", "Borrar artículo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (resp == 0) {
                    elimiarArticulo(row);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay artículos agregados al carrito todavía. Para agregar artículos:\nPulse en el botón \"Agregar\" o escaneé el código de barras del artículo.");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (tableListaArticulos.getRowCount() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desesas cancelar el retiro? Se eliminaran todos los articulos que agregaste", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (resp == 0) {
                JFCompra.pedido = null;
                txtAvisos.setText("");
                vaciarRetiro();
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay artículos agregados al carrito todavía. Para agregar artículos:\nPulse en el botón \"Agregar\" o escaneé el código de barras del artículo.");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if (tableListaArticulos.getRowCount() > 0) {
            List<CompraDetalle> listaCompra = new ArrayList<>();
            for (int i = 0; i < tableListaArticulos.getRowCount(); i++) {
                CompraDetalle aux = new CompraDetalle();

                aux.setArticulo(ArticuloController.findByClave((String) tableListaArticulos.getValueAt(i, 0)));
                aux.setCantidad(Float.parseFloat((String) tableListaArticulos.getValueAt(i, 2)));

                listaCompra.add(aux);
            }
            JDFinalizarCompra finalizar = new JDFinalizarCompra(new JFrame(), true, listaCompra, Float.parseFloat(lblCostoTotal.getText()), pedido);
            finalizar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos un artículo", "Pedido vacío", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
                float nf = Float.valueOf((String)tableListaArticulos.getValueAt(index, 2)) + 1;
                actualizarCantidadArticulo(index, nf);
            }
            
            txtBuscar.setText("");
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!txtBuscar.getText().equals("") && txtBuscar.getText().length() >= 10) {
            //bsucar
        }else{
            JOptionPane.showMessageDialog(null, "Debe de escribir la clave (Mayor a 10 digitos) del articulo O en caso\n"
                + "de contar con un lector de códigos de barras, simplemente escaneá el código y el \n"
                + "artículo se añadira automáticamente a la lista de artículos.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JDPedidos pedidos = new JDPedidos(new JFrame(), true);
        pedidos.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         if (tableListaArticulos.getRowCount() > 0) {
            if (tableListaArticulos.getSelectedRowCount() > 0) {
                int row = tableListaArticulos.getSelectedRow();
                String clave = (String) tableListaArticulos.getValueAt(row, 0);
                Articulo art = ArticuloController.findByClave(clave);
                JDActualizarPrecio actualizarPrecio = new JDActualizarPrecio(new JFrame(), true, art, row);
                actualizarPrecio.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo en la tabla.", "Artículo no seleccionado", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay artículos agregados al carrito todavía. Para agregar artículos:\nPulse en el botón \"Agregar\" o escaneé el código de barras del artículo.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl$;
    private static javax.swing.JLabel lblCantidadArticulos;
    private static javax.swing.JLabel lblCostoTotal;
    public static ponyvet.gui.utilities.JTableCustom tableListaArticulos;
    public static javax.swing.JLabel txtAvisos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
