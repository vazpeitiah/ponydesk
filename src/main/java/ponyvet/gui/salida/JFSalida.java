package ponyvet.gui.salida;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.controller.articulo.ArticuloController;
import ponyvet.gui.utilities.JInternalFrameCustom;
import ponyvet.modelo.entidades.articulo.Articulo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import ponyvet.modelo.entidades.salida.SalidaDetalle;

public class JFSalida extends JInternalFrameCustom{
    
    private static int cantidadArticulos = 0;
    private static float costoTotal = 0;
    
    public JFSalida() {
        initComponents();
    }
    
    private static boolean add(Articulo articulo, float cantidad){
        boolean resultado = false;
        if(cantidad <= articulo.getExistencia()){
            
            String[] row = new String[5];
            row[0] = articulo.getClave();
            row[1] = articulo.getDescripcion();
            row[2] = String.valueOf(cantidad);
            row[3] = String.valueOf(articulo.getPrecioSal());
            float importe = articulo.getPrecioSal() * cantidad;
            row[4] = String.valueOf(importe);
            
            ((DefaultTableModel) tableListaArticulos.getModel()).addRow(row); 
            resultado = true;
        }else{
            JOptionPane.showMessageDialog(null, "No hay existencias suficientes del articulo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    public static boolean agregarArticuloConClave(String clave, float cantidad) {
        boolean resultado = false;
        ArticuloController controller = new ArticuloController();
        Articulo art = controller.findByClave(clave);
        
        if (art != null) {
            int index = buscarIndexArticulo(art.getClave());
            if (index == -1) { //No se ha agredado el articulo
                resultado = add(art, cantidad);
            } else {
                resultado = sumarArticulo(art, index, cantidad);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el art??culo");
        }
        return resultado;
    }
    
    public static boolean sumarArticulo(Articulo art, int index, float cantidad){
        boolean resultado = false;
        if (tableListaArticulos.getRowCount() > 0) {
            Float cantidadAnterior = Float.parseFloat((String) tableListaArticulos.getValueAt(index, 2));
            Float ncantidad = cantidadAnterior + cantidad;
            if (ncantidad <= art.getExistencia()) {
                tableListaArticulos.setValueAt(String.valueOf(ncantidad), index, 2);
                Float precio = Float.parseFloat((String)tableListaArticulos.getValueAt(index, 3));
                Float importe = precio * ncantidad;
                tableListaArticulos.setValueAt(String.valueOf(importe), index, 4);
                resultado = true;
            }else{
                JOptionPane.showMessageDialog(null, "No hay existencias suficientes del articulo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
    }

    private void restarUnArticulo(Articulo art, int index){
        if (tableListaArticulos.getRowCount() > 0) {
            Float cantidadAnterior = Float.parseFloat((String) tableListaArticulos.getValueAt(index, 2));
            Float cantidad = cantidadAnterior - 1;
            if (cantidad > 0) {
                tableListaArticulos.setValueAt(String.valueOf(cantidad), index, 2);
                Float precio = Float.parseFloat((String)tableListaArticulos.getValueAt(index, 3));
                Float importe = precio * cantidad;
                tableListaArticulos.setValueAt(String.valueOf(importe), index, 4);
            }
        }
    }
    
    private static int buscarIndexArticulo(String clave) {
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
    
    public void setFocusCampoBuscar(){
        txtBuscar.requestFocus();
    }
    
    public static void actualizarDatos(){
        int cantArticulos = 0;
        float costo = 0;
        if(tableListaArticulos.getRowCount() > 0){
            for (int i = 0; i < tableListaArticulos.getRowCount(); i++) {
                float cantidad = Float.parseFloat((String) tableListaArticulos.getValueAt(i, 2));
                float importe = Float.parseFloat((String) tableListaArticulos.getValueAt(i, 4));
                cantArticulos += cantidad;
                costo += importe;
            }
        }
         //Actualizamos variables globales
        cantidadArticulos = cantArticulos;
        costoTotal = costo;
        //Ponemos los valores en las etiquetas correspondientes
        lblCantidadArticulos.setText(String.valueOf(cantidadArticulos));
        lblCostoTotal.setText(String.valueOf(costoTotal));
    }
    
    

    public static void vaciarRetiro() {
        DefaultTableModel model = (DefaultTableModel) tableListaArticulos.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        tableListaArticulos.setModel(model);
        actualizarDatos();
    }
    
    public static boolean actualizarCantidad(Articulo art, int index, float cantidad){
        boolean result = false;
        if (tableListaArticulos.getRowCount() > 0) {
            if (cantidad <= art.getExistencia()) {
                tableListaArticulos.setValueAt(String.valueOf(cantidad), index, 2);
                Float precio = Float.parseFloat((String)tableListaArticulos.getValueAt(index, 3));
                Float importe = precio * cantidad;
                tableListaArticulos.setValueAt(String.valueOf(importe), index, 4);
                result = true;
            }else{
                JOptionPane.showMessageDialog(null, "No hay existencias suficientes del articulo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return result;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

        setClosable(true);
        setTitle("Retiro de medicamentos");

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
        if (tableListaArticulos.getColumnModel().getColumnCount() > 0) {
            tableListaArticulos.getColumnModel().getColumn(0).setMinWidth(100);
            tableListaArticulos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableListaArticulos.getColumnModel().getColumn(1).setMinWidth(300);
            tableListaArticulos.getColumnModel().getColumn(1).setPreferredWidth(300);
            tableListaArticulos.getColumnModel().getColumn(2).setMinWidth(50);
            tableListaArticulos.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableListaArticulos.getColumnModel().getColumn(3).setMinWidth(50);
            tableListaArticulos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableListaArticulos.getColumnModel().getColumn(4).setMinWidth(50);
            tableListaArticulos.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-add-24.png"))); // NOI18N
        jButton3.setText("Agregar");
        jButton3.setBackground(new Color(255, 255, 255));
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton5, gridBagConstraints);

        jButton6.setBackground(new Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-trash-can-24.png"))); // NOI18N
        jButton6.setText("Quitar art??culo");
        jButton6.setFocusPainted(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 4);
        jPanel2.add(jButton6, gridBagConstraints);

        jButton9.setBackground(new java.awt.Color(255, 204, 204));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-delete-document-24.png"))); // NOI18N
        jButton9.setText("Cancelar retiro");
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Realizar retiro de medicamentos");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-cash-register-32 (1).png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblCantidadArticulos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCantidadArticulos.setForeground(new java.awt.Color(0, 102, 153));
        lblCantidadArticulos.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Art??culos del retiro actual");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Costo total:");

        lblCostoTotal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCostoTotal.setForeground(new java.awt.Color(0, 102, 153));
        lblCostoTotal.setText("0.00");

        jButton2.setBackground(new Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-check-all-22.png"))); // NOI18N
        jButton2.setText("Finalizar salida");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
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

    private void tableListaArticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableListaArticulosKeyPressed
        // TODO add your handling code here:
        if(tableListaArticulos.getSelectedRowCount() > 0){
            if(evt.getKeyCode() == KeyEvent.VK_ADD || evt.getKeyCode() == KeyEvent.VK_PLUS){
                int row = tableListaArticulos.getSelectedRow();
                String clave = (String) tableListaArticulos.getValueAt(row, 0);
                ArticuloController controller = new ArticuloController();
                Articulo articulo = controller.findByClave(clave);
                
                sumarArticulo(articulo, row, 1);
                actualizarDatos();
            }else if(evt.getKeyCode() == KeyEvent.VK_MINUS || evt.getKeyCode() == 109){
                int row = tableListaArticulos.getSelectedRow();
                String clave = (String) tableListaArticulos.getValueAt(row, 0);
                ArticuloController controller = new ArticuloController();
                Articulo articulo = controller.findByClave(clave);
                
                restarUnArticulo(articulo, row);
                actualizarDatos();
            }
        }
    }//GEN-LAST:event_tableListaArticulosKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER && !txtBuscar.getText().equals("")){
           agregarArticuloConClave(txtBuscar.getText(), 1);
           txtBuscar.setText("");
           actualizarDatos();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JDBuscarArticulo buscar = new JDBuscarArticulo(new JFrame(), true);
        buscar.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (tableListaArticulos.getRowCount() > 0) {
            if (tableListaArticulos.getSelectedRowCount() > 0) {
                int resp = JOptionPane.showConfirmDialog(null, "??Deseas quitar el art??culo del retiro actual?", "Borrar art??culo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (resp == 0) {
                    int row = tableListaArticulos.getSelectedRow();
                    ((DefaultTableModel) tableListaArticulos.getModel()).removeRow(row);
                    actualizarDatos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un registro de la tabla", "Registro no seleccionado", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay art??culos agregados al carrito todav??a. Para agregar art??culos:\nPulse en el bot??n \"Agregar\" o escane?? el c??digo de barras del art??culo.");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (tableListaArticulos.getRowCount() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "??Desesas cancelar el retiro? Se eliminaran todos los articulos que agregaste", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                vaciarRetiro();
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay art??culos agregados al carrito todav??a. Para agregar art??culos:\nPulse en el bot??n \"Agregar\" o escane?? el c??digo de barras del art??culo.");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(tableListaArticulos.getRowCount() > 0){
            List<SalidaDetalle> lista_articulo = new ArrayList<>();
            for (int i = 0; i < tableListaArticulos.getRowCount(); i++) {
                SalidaDetalle aux = new SalidaDetalle();
                
                aux.setArticulo(new ArticuloController().findByClave((String) tableListaArticulos.getValueAt(i, 0)));  
                aux.setCantidad(Float.parseFloat((String) tableListaArticulos.getValueAt(i, 2)));
                
                lista_articulo.add(aux);
            }
            JDFinalizarSalida finalizar = new JDFinalizarSalida(new JFrame(), true, lista_articulo, costoTotal);
            finalizar.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "No hay art??culos agregados al carrito todav??a. Para agregar art??culos:\nPulse en el bot??n \"Agregar\" o escane?? el c??digo de barras del art??culo.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(tableListaArticulos.getRowCount() > 0){
            if(tableListaArticulos.getSelectedRowCount() > 0){
                int row = tableListaArticulos.getSelectedRow();
                String clave = (String) tableListaArticulos.getValueAt(row, 0);
                JDAjustarCantidad ajustarCantidad = new JDAjustarCantidad(new JFrame(), true, clave, row);
                ajustarCantidad.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Selecciona un registro de la tabla", "Fila no seleccionada", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay art??culos agregados al carrito todav??a. Para agregar art??culos:\nPulse en el bot??n \"Agregar\" o escane?? el c??digo de barras del art??culo.");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tableListaArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListaArticulosMouseClicked
        // TODO add your handling code here

    }//GEN-LAST:event_tableListaArticulosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!txtBuscar.getText().equals("")) {
            agregarArticuloConClave(txtBuscar.getText(), 1);
            txtBuscar.setText("");
            actualizarDatos();
        }else{
            JOptionPane.showMessageDialog(null, "Desde de escribir la clave  del art??culo en el cuadro de b??squeda, o en caso de contar\n"
                                              + "con un lector de c??digos de barras, simplemente escane?? el c??digo del art??culo y este\n"
                                              + "se a??adira autom??ticamente al carrito.\n");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private static ponyvet.gui.utilities.JTableCustom tableListaArticulos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
