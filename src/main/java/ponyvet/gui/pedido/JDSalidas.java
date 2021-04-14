package ponyvet.gui.pedido;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ponyvet.controller.SalidaController;
import ponyvet.controller.articulo.ArticuloController;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.modelo.entidades.salida.Salida;
import ponyvet.modelo.entidades.salida.SalidaDetalle;
import ponyvet.utilities.FormatearValores;

public class JDSalidas extends javax.swing.JDialog {

    private List<SalidaDetalle> salida_lista = new ArrayList<>();

    public JDSalidas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        dpFechaFinal.setDate(LocalDate.now());
        dpFechaInicial.setDate(LocalDate.now().minus(1, ChronoUnit.WEEKS));
    }

    private void actualizarTabla(List<SalidaDetalle> articulos) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        int rowCount = modelo.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        for (SalidaDetalle detalleArticulo : articulos) {
            Articulo articulo = detalleArticulo.getArticulo();
            String aux[] = new String[5];
            aux[0] = articulo.getClave();
            aux[1] = articulo.getDescripcion();
            if (detalleArticulo.getCantidad() % 1 != 0) {
                float cant = Math.round(detalleArticulo.getCantidad());
                detalleArticulo.setCantidad(cant);
            }
            aux[2] = String.valueOf(detalleArticulo.getCantidad());
            
            if (articulo.getUnidadCompra() != articulo.getUnidadSalida()) {
                aux[3] = FormatearValores.formatearPrecioArticulo(articulo.getPrecioCom(), articulo.getUnidadCompra().getNombre());
            } else {
                aux[3] = FormatearValores.formatearPrecio(articulo.getPrecioCom());
            }

            float importe = detalleArticulo.getCantidad() * detalleArticulo.getArticulo().getPrecioCom();
            aux[4] = String.valueOf(importe);

            if (detalleArticulo.getCantidad() > 0) {
                modelo.addRow(aux);
            }
        }

        salida_lista = articulos;
        jTable1.setModel(modelo);
    }

    private int indexOfArticulo(List<SalidaDetalle> articulos, int id) {
        int index = -1;

        for (int i = 0; i < articulos.size(); i++) {
            SalidaDetalle aux = articulos.get(i);
            if (aux.getArticulo().getId() == id) {
                index = i;
            }
        }

        return index;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dpFechaInicial = new com.github.lgooddatepicker.components.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        dpFechaFinal = new com.github.lgooddatepicker.components.DatePicker();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new ponyvet.gui.utilities.JTableCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ultimas salidas de artículos");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-checked-22.png"))); // NOI18N
        jButton4.setText("Agregar al pedido");
        jButton4.setBackground(new Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Fecha Inicial:");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(dpFechaInicial, gridBagConstraints);

        jLabel2.setText("Fecha Final:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(dpFechaFinal, gridBagConstraints);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-search-22.png"))); // NOI18N
        jButton3.setText("Buscar");
        jButton3.setBackground(new Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jButton3, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(60);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(2).setMinWidth(60);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(3).setMinWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(4).setMinWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (dpFechaInicial.getDate() != null && dpFechaFinal.getDate() != null) {
            LocalDate dateInicial = dpFechaInicial.getDate();
            LocalDate dateFinal = dpFechaFinal.getDate();

            LocalDateTime fechaInicial = dateInicial.atStartOfDay();
            LocalDateTime fechaFinal = dateFinal.atTime(LocalTime.MAX);

            SalidaController controller = new SalidaController();
            List<Salida> salidas = controller.readByDateRange(fechaInicial, fechaFinal);

            List<SalidaDetalle> articulos = new ArrayList<>();

            salidas.forEach(salida -> {
                salida.getSalidas_detalle().forEach(salida_detalle -> {
                    float cantiadad = salida_detalle.getCantidad() / salida_detalle.getArticulo().getFactor();
                    salida_detalle.setCantidad(cantiadad);
                    int index = indexOfArticulo(articulos, salida_detalle.getArticulo().getId());
                    if (index == -1) {

                        articulos.add(salida_detalle);
                    } else {
                        SalidaDetalle aux = articulos.get(index);

                        float cantidad = aux.getCantidad() + salida_detalle.getCantidad();
                        aux.setCantidad(cantidad);

                        articulos.set(index, aux);
                    }
                });
            });
            
            actualizarTabla(articulos);
            
            if(jTable1.getRowCount() <= 0){
                JOptionPane.showMessageDialog(null, String.format("No se encontraron salidas para el perido de %s\nal %s", 
                        FormatearValores.formatearFecha(dateInicial), FormatearValores.formatearFecha(dateFinal)));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fecha inicial y final.", "Rango de fechas no especificado.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (jTable1.getRowCount() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Deseas agregar las salidas al pedido?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (resp == 0) {
                JFPedido.vaciarRetiro();
                salida_lista.forEach(detalleArticulo -> {
                    if(detalleArticulo.getCantidad() > 0){
                        JFPedido.agregarArticulo(detalleArticulo.getArticulo().getClave(), detalleArticulo.getCantidad());
                    } 
                });
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay articulos para agregar", "La tabla está vacía", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker dpFechaFinal;
    private com.github.lgooddatepicker.components.DatePicker dpFechaInicial;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    public static ponyvet.gui.utilities.JTableCustom jTable1;
    // End of variables declaration//GEN-END:variables
}
