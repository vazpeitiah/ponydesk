package ponyvet.gui.principal;

import java.beans.PropertyVetoException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import ponyvet.controller.CompraController;
import ponyvet.controller.PedidoController;
import ponyvet.controller.SalidaController;
import ponyvet.gui.articulo.JDFormularioArticulo;
import ponyvet.gui.articulo.JFArticulo;
import ponyvet.gui.compra.JFCompra;
import ponyvet.gui.compra.historial.JFHistorialCompra;
import ponyvet.gui.pedido.JFPedido;
import ponyvet.gui.pedido.historial.JFHistorialPedidos;
import ponyvet.gui.proveedor.JDFormularioProveedor;
import ponyvet.gui.proveedor.JFProveedor;
import ponyvet.gui.salida.JFSalida;
import ponyvet.gui.salida.historial.JFHistorialSalida;
import ponyvet.gui.veterinario.JDFormularioVeterinario;
import ponyvet.gui.veterinario.JFVeterinario;

public class JFMenuPrincipal extends javax.swing.JFrame {
    // Menu principal (de la parte superior)
    private JFConfiguracion configuracion = null;
    private JFInicio inicio = null;
    
    // Menu Operaciones
    private JFArticulo articulo = null;
    private JFSalida salidas = null;
    private JFPedido pedidos = null;
    private JFVeterinario veterinario = null;
    private JFProveedor proveedor = null;
    private JFCompra entradas = null;
    
    // Menu Historial y Consultas
    private JFHistorialSalida salidasHistorial = null;
    private JFHistorialCompra comprasHistorial = null;
    private JFHistorialPedidos pedidosHistorial = null;
    
    public JFMenuPrincipal() {
        initComponents();
        
        URL iconURL = getClass().getResource("/IMG/dog.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
    }
    
    private static boolean estacerrado(Object jiFrame) {
        JInternalFrame[] activos = jDesktopPane1.getAllFrames();
        boolean cerrado = true;
        for (int i = 0; i < activos.length && cerrado; i++) {
            if(activos[i] == jiFrame)
                cerrado = false;
        }
        return cerrado;
    }
    
    // MENU DE OPERACIONES
    public void abrirArticulos(){
        if(estacerrado(articulo)){
            articulo = new JFArticulo();
            jDesktopPane1.add(articulo).setVisible(true);
            try {
                articulo.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JFArticulo.actualizarTablaArticulos("");
            articulo.moveToFront();
        }
    }
    
    public void abrirSalidas(){
        if(estacerrado(salidas)){
            salidas = new JFSalida();
            jDesktopPane1.add(salidas).setVisible(true);
            try {
                salidas.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            salidas.moveToFront();
            salidas.setFocusCampoBuscar();
        }
    }
    
    public void abrirPedidos(){
        if(estacerrado(pedidos)){
            pedidos = new JFPedido();
            jDesktopPane1.add(pedidos).setVisible(true);
            try {
                pedidos.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            pedidos.moveToFront();
            pedidos.setFocusCampoBuscar();
        }
        
    }
    
    public void abrirVeterinarios(){
        if(estacerrado(veterinario)){
            veterinario = new JFVeterinario();
            jDesktopPane1.add(veterinario).setVisible(true);
            try {
                veterinario.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JFVeterinario.actualizarTablaVeterinarios("");
            veterinario.moveToFront();
        }
    }
    
    public void abrirProveedores(){
        if(estacerrado(proveedor)){
            proveedor = new JFProveedor();
            jDesktopPane1.add(proveedor).setVisible(true);
            try {
                proveedor.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JFProveedor.actualizarTabla("");
            proveedor.moveToFront();
        }
    }
    
    public void abrirEntradas() {
        if (estacerrado(entradas)) {
            entradas = new JFCompra();
            jDesktopPane1.add(entradas).setVisible(true);
            try {
                entradas.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            entradas.moveToFront();
            entradas.setFocusCampoBuscar();
        }
    }
     
    // MENU DE HISTORIAL Y CONSULTAS
    public void abrirSalidasHistorial() {
        if (estacerrado(salidasHistorial)) {
            salidasHistorial = new JFHistorialSalida();
            jDesktopPane1.add(salidasHistorial).setVisible(true);
            try {
                salidasHistorial.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JFHistorialSalida.actualizarTabla(SalidaController.read());
            salidasHistorial.moveToFront();
        }
    }
    
    public void abrirComprasHistorial() {
        if (estacerrado(comprasHistorial)) {
            comprasHistorial = new JFHistorialCompra();
            jDesktopPane1.add(comprasHistorial).setVisible(true);
            try {
                comprasHistorial.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JFHistorialCompra.actualizarTabla(CompraController.read());
            comprasHistorial.moveToFront();
        }
    }
    
    public void abrirPedidosHistorial() {
        if (estacerrado(pedidosHistorial)) {
            pedidosHistorial = new JFHistorialPedidos();
            jDesktopPane1.add(pedidosHistorial).setVisible(true);
            try {
                pedidosHistorial.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JFHistorialPedidos.actualizarTabla(PedidoController.read());
            pedidosHistorial.moveToFront();
        }
    }
    
    public void abrirConfiguracion() {
        if (estacerrado(pedidosHistorial)) {
            configuracion = new JFConfiguracion();
            jDesktopPane1.add(configuracion).setVisible(true);
            try {
                configuracion.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(JFMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            configuracion.moveToFront();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpPrincipal = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Veterinaria Pony");
        setExtendedState(6);
        setFocusCycleRoot(false);

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jpPrincipal.setLayout(new java.awt.GridBagLayout());

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jpPrincipal.add(jDesktopPane1, gridBagConstraints);

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setText("Artículos");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenu1.setMargin(new java.awt.Insets(2, 8, 2, 20));

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem1.setText("Agregar artículo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem2.setText("Inventario general");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Veterinarios");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenu2.setMargin(new java.awt.Insets(2, 8, 2, 20));

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem5.setText("Agregar nuevo veterinario");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem6.setText("Lista de veterinarios");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Proveedores");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenu3.setMargin(new java.awt.Insets(2, 8, 2, 20));

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem3.setText("Agregar nuevo proveedor");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem4.setText("Lista de proveedores");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Salidas");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenu4.setMargin(new java.awt.Insets(2, 8, 2, 20));

        jMenuItem7.setText("Registrar salida");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Historial de salidas");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Compras");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenu5.setMargin(new java.awt.Insets(2, 8, 2, 20));

        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem9.setText("Registrar compra");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem10.setText("Historial de compras");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Pedidos");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenu6.setMargin(new java.awt.Insets(2, 8, 2, 20));

        jMenuItem11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem11.setText("Agregar nuevo pedido");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuItem13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem13.setText("Historial de pedidos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Configuración");

        jMenuItem12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem12.setText("Información de la empresa");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JDFormularioArticulo art = new JDFormularioArticulo(null, true, null);
        art.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        abrirArticulos();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        JDFormularioVeterinario vet = new JDFormularioVeterinario(null, true, null);
        vet.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        abrirSalidas();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        abrirComprasHistorial();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        abrirPedidosHistorial();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        JDFormularioProveedor prov = new JDFormularioProveedor(null, true, null);
        prov.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        abrirVeterinarios();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        abrirProveedores();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        abrirSalidasHistorial();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        abrirEntradas();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        abrirPedidos();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        abrirConfiguracion();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jpPrincipal;
    // End of variables declaration//GEN-END:variables
}
