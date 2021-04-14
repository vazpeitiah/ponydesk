package ponyvet.gui.compra;

import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import ponyvet.config.ConfiguracionesGenerales;
import ponyvet.controller.articulo.ArticuloController;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.utilities.Validar;

public class JDActualizarPrecio extends javax.swing.JDialog {
    
    private final Articulo articulo;
    private final int row;
    
    
    public JDActualizarPrecio(java.awt.Frame parent, boolean modal, Articulo art, int row) {
        super(parent, modal);
        initComponents();
        articulo = art;
        this.row = row;
        
        txtPrecioCom.setText(String.valueOf(art.getPrecioCom()));
        txtPrecioVenta.setText(String.valueOf(art.getPrecioSal()));
        txtFactor.setText(String.valueOf(art.getFactor()));
        
        txtUnidadCompra.setText("x"+art.getUnidadCompra().getNombre());
        txtUnidadSalida.setText("x"+art.getUnidadSalida().getNombre());
        
        DecimalFormat df = new DecimalFormat("####################.###");
        Float precioSalida = art.getPrecioCom() / art.getFactor();
        Float precioSalidaFormateado = Float.parseFloat(df.format(precioSalida));
        if (art.getPrecioSal() != precioSalidaFormateado) {
            jCheckBox1.setSelected(true);
            txtPrecioVenta.setEditable(true);
        }
    }
    
    public void calularPrecioSalida() {

        if (!txtPrecioCom.getText().equals("") && !txtFactor.getText().equals("")) {
            float factor = Float.parseFloat(txtFactor.getText());
            float precioCompra = Float.parseFloat(txtPrecioCom.getText());

            if (factor > 0) {
                float precioSalida = precioCompra / factor;
                txtPrecioVenta.setText(Float.toString(precioSalida));
            }
        }

    }
    
    private boolean validarCampos() {
        boolean resultado = true;

        //CAMPO FACTOR
        txtFactor.setBorder(new JTextField().getBorder());
        if (!Validar.isFloat(txtFactor.getText())) {
            JOptionPane.showMessageDialog(null, "Factor incorrecto. Debe escribir un número entero o decimales.");
            txtFactor.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        if (Float.parseFloat(txtFactor.getText()) <= 0.0f) {
            JOptionPane.showMessageDialog(null, "El factor no puede ser menor o igual 0");
            txtFactor.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }

        //CAMPO PRECIO COMPRA
        txtPrecioCom.setBorder(new JTextField().getBorder());

        if (!Validar.isFloat(txtPrecioCom.getText())) {
            JOptionPane.showMessageDialog(null, "Precio de salida incorrecto. Debe escribir un número entero o decimales.");
            txtPrecioCom.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        if (Float.parseFloat(txtPrecioCom.getText()) <= 0.0f || Float.parseFloat(txtPrecioCom.getText()) >= ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO) {
            JOptionPane.showMessageDialog(null, "El precio de compra debe ser mayor a 0 y menor a "+ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO);
            txtPrecioCom.setBorder(new LineBorder(Color.RED));
            resultado = false;
        
        }
        
        //CAMPO PRECIO SALIDA
        txtPrecioVenta.setBorder(new JTextField().getBorder());

        if (!Validar.isFloat(txtPrecioVenta.getText())) {
            JOptionPane.showMessageDialog(null, "Precio de compra incorrecto. Debe escribir un número entero o decimales.");
            txtPrecioVenta.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        if (Float.parseFloat(txtPrecioVenta.getText()) <= 0.0f || Float.parseFloat(txtPrecioVenta.getText()) >= ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO) {
            JOptionPane.showMessageDialog(null, "El precio de salida  debe ser mayor a 0 y menor a "+ ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO);
            txtPrecioVenta.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        return resultado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPrecioCom = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFactor = new javax.swing.JTextField();
        txtUnidadCompra = new javax.swing.JLabel();
        txtUnidadSalida = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Actualizar precio del artículo");
        setResizable(false);

        txtPrecioCom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPrecioCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioComActionPerformed(evt);
            }
        });
        txtPrecioCom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioComKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioComKeyReleased(evt);
            }
        });

        txtPrecioVenta.setEditable(false);
        txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Precio de compra");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Precio de venta");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Factor");

        txtFactor.setEditable(false);
        txtFactor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFactor.setText("1.0");

        txtUnidadCompra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUnidadCompra.setText("xPZA");

        txtUnidadSalida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUnidadSalida.setText("xPZA");

        jLabel6.setText("(Si quieres registrar un precio de venta)");

        jCheckBox1.setText("Precio venta");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-check-all-22.png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecioCom))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(txtPrecioVenta)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUnidadSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(txtUnidadCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jCheckBox1))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(txtFactor))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtUnidadCompra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtUnidadSalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioComActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioComActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(validarCampos()){
            articulo.setPrecioCom(Float.parseFloat(txtPrecioCom.getText()));
            articulo.setPrecioSal(Float.parseFloat(txtPrecioVenta.getText()));
            articulo.setFactor(Float.parseFloat(txtFactor.getText()));
            this.dispose();
            ArticuloController.update(articulo);
            JFCompra.actualizarPrecioArticulo(row, articulo.getPrecioCom());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
         if (evt.getStateChange() == 1) {
            txtPrecioVenta.setEditable(true);
        } else {
             txtPrecioCom.setText(String.valueOf(articulo.getPrecioCom()));
            calularPrecioSalida();
            txtPrecioVenta.setEditable(false);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void txtPrecioComKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioComKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioComKeyPressed

    private void txtPrecioComKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioComKeyReleased
        // TODO add your handling code here:
        calularPrecioSalida();
    }//GEN-LAST:event_txtPrecioComKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtFactor;
    private javax.swing.JTextField txtPrecioCom;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JLabel txtUnidadCompra;
    private javax.swing.JLabel txtUnidadSalida;
    // End of variables declaration//GEN-END:variables
}
