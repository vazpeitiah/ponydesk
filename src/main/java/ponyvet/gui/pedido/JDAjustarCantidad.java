package ponyvet.gui.pedido;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import ponyvet.config.ConfiguracionesGenerales;
import ponyvet.utilities.Validar;

public class JDAjustarCantidad extends javax.swing.JDialog {

    private final int index;
    private final boolean isGranel;
    
    public JDAjustarCantidad(java.awt.Frame parent, boolean modal, int index, float cantidadActual, boolean isGranel) {
        super(parent, modal);
        initComponents();

        this.index = index;
        this.isGranel = isGranel;
        txtCantidad.setText(String.valueOf(cantidadActual));
    }

    public void actualizarCantidadArticulo(String cantidadStr) {
        if (Validar.isFloat(cantidadStr)) {
            float cantidad = Float.parseFloat(cantidadStr);
            if (cantidad > 0 && cantidad < ConfiguracionesGenerales.MAXIMA_CANTIDAD_ARTICULOS) {
                if(isGranel){
                    JFPedido.actualizarCantidadArticulo(index, cantidad);
                    this.dispose();
                }else{
                    if(cantidad % 1 == 0){ //Si es un numero entero
                        JFPedido.actualizarCantidadArticulo(index, cantidad);
                        this.dispose();
                    }else{ 
                        JOptionPane.showMessageDialog(null, 
                                "Este artículo no se puede pedir a granel. Ingresa un número entero sin decimales", 
                                "Cantidad inválidad", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cantidad incorrecta. Debe ingresar un valor mayor a cero y menor a " + ConfiguracionesGenerales.MAXIMA_CANTIDAD_ARTICULOS,
                        "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Cadena no válida. Escriba un número positivo con decimales o un entero.", 
                    "Error al ingresar la cantidad", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajustar la cantidad del artículo");
        setFocusable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Escriba la cantidad:");
        jLabel1.setFocusable(false);

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-check-all-22.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtCantidad)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 206, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        actualizarCantidadArticulo(txtCantidad.getText());
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_formKeyPressed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            actualizarCantidadArticulo(txtCantidad.getText());
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables
}
