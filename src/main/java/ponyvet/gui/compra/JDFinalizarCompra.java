package ponyvet.gui.compra;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import ponyvet.controller.CompraController;
import ponyvet.controller.PedidoController;
import ponyvet.controller.articulo.ArticuloController;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.compra.Compra;
import ponyvet.modelo.entidades.compra.CompraDetalle;
import ponyvet.modelo.entidades.pedido.Pedido;
import ponyvet.utilities.CodigoBarras;
import ponyvet.utilities.FormatearValores;

public class JDFinalizarCompra extends javax.swing.JDialog {
    
    private final List<CompraDetalle> listaArticulos; // Lista de articulos de la compra
    private float total = 0.0f; // Costo total de la lista de articulos
    private final Pedido pedido;

    public JDFinalizarCompra(java.awt.Frame parent, boolean modal, List<CompraDetalle> listaArticulos, float total, Pedido pedido) {
        super(parent, modal);
        initComponents();
        this.total = total; //Recibimos la lista de articulos desde JFCompra
        this.listaArticulos = listaArticulos; // Recibimos la lista de articulos desde JFCompra
        this.pedido = pedido;
        dateTimePicker1.setDateTimePermissive(LocalDateTime.now());
        if(this.pedido != null){
            txtFolio.setText(String.format("PED%s", FormatearValores.formatearClavePedido(pedido.getId())));
        }else{
            String folio = CodigoBarras.generarCodigoGenerico(12);
            while (!CompraController.readByFolio(folio).isEmpty()) {
                folio = CodigoBarras.generarCodigoGenerico(12);
            }
            txtFolio.setText(String.format("COM%s", folio));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtFolio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realizar compra");
        setResizable(false);

        jLabel2.setText("Finalizar ingreso de medicamentos");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLabel3.setText("Número de folio de la compra:");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jLabel4.setText("Fecha");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jButton2.setText("Cancelar");
        jButton2.setBackground(new Color(255, 255, 255));
        jButton2.setFocusPainted(false);
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-checked-22.png"))); // NOI18N
        jButton1.setText("Finalizar");
        jButton1.setBackground(new Color(255, 255, 255));
        jButton1.setFocusPainted(false);
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtFolio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFolio)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(0, 124, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Compra compra = new Compra();
        compra.setFolio(txtFolio.getText());
        compra.setTotal(total);
        compra.setFecha(dateTimePicker1.getDateTimePermissive());

        if(dateTimePicker1.getDateTimePermissive() != null){
            CompraController.create(compra);

            listaArticulos.forEach((CompraDetalle detalle) -> {
                detalle.setCompra(compra);
                //System.out.println(lista.toString());
                CompraController.createListaPedido(detalle);
                
                Articulo art = detalle.getArticulo();
                float existenciaActual = art.getExistencia();
                art.setExistencia(existenciaActual + detalle.getCantidad());
                ArticuloController.updateWhitoutAlert(art);
            });

            this.dispose();
            if(pedido == null){
                JOptionPane.showMessageDialog(null, "La salida se registro correctamento en el sistema", "Salida registrada", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JFCompra.txtAvisos.setText("");
                JOptionPane.showMessageDialog(null, "Los artículos del pedido se actualizaron en el inventario de manera correcta");
                pedido.setEstado(Pedido.RECIBIDO);
                pedido.setFechaRecibido(LocalDateTime.now());
                PedidoController.updateNoNotify(pedido);
            }
            JFCompra.vaciarRetiro();
        }else{
            JOptionPane.showMessageDialog(null, "Fecha inválida. Escribió mal la fecha", "Error fecha", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
}
