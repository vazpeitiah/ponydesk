package ponyvet.gui.articulo;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import ponyvet.config.ConfiguracionesGenerales;
import ponyvet.controller.articulo.ArticuloController;
import ponyvet.controller.articulo.CategoriaController;
import ponyvet.controller.articulo.LoteController;
import ponyvet.controller.articulo.UnidadController;
import ponyvet.modelo.entidades.articulo.Articulo;
import ponyvet.utilities.CodigoBarras;
import ponyvet.utilities.ImageUtilities;
import ponyvet.utilities.Validar;

public class JDFormularioArticulo extends javax.swing.JDialog {

    private static Articulo articulo;
    private final boolean isupdate;

    public JDFormularioArticulo(java.awt.Frame parent, boolean modal, Articulo art) {
        super(parent, modal);
        initComponents();

        txtClave.requestFocus();

        if (art != null) {
            articulo = art; //ASIGNAR ARTICULO A ACTULIZAR
            //ASIGNAR VALORES A SUS RESPECTIVOS CAMPOS
            txtClave.setText(art.getClave());
            txtDescripcion.setText(art.getDescripcion());

            if (!verificarIgualdadUnidad()) {
                txtFactor.setEditable(true);
            }

            txtFactor.setText(String.valueOf(art.getFactor()));
            txtInventarioMin.setText(String.valueOf(art.getInventarioMin()));
            txtInventarioMax.setText(String.valueOf(art.getInventarioMax()));
            txtLocalizacion.setText(art.getLocalizacion());
            txtPrecioCompra.setText(String.valueOf(art.getPrecioCom()));
            txtPrecioSalida.setText(String.valueOf(art.getPrecioSal()));
            txtCaracteristicas.setText(art.getCaracteristicas());

            if (art.isLote()) {
                jcbLote.setSelected(true);
            }
            if (art.isGranel()) {
                jcbGranel.setSelected(true);
            }

            DecimalFormat df = new DecimalFormat("####################.###");
            Float precioSalida = art.getPrecioCom() / art.getFactor();
            Float precioSalidaFormateado = Float.parseFloat(df.format(precioSalida));
            if (art.getPrecioSal() != precioSalidaFormateado) {
                jcbPrecioSalida.setSelected(true);
                txtPrecioSalida.setEditable(true);
            }

            if (art.getImagen() != null) {
                colocarImagen(art.getImagen());
            }

            actualizarCategoria(art.getCategoria());
            actualizarUnidadCompra(art.getUnidadCompra());
            actualizarUnidadSalida(art.getUnidadSalida());
            isupdate = true;
        } else {
            articulo = new Articulo(); //CREAR ARTICULO NUEVO

            //VALORES POR DEFECTO
            articulo.setLote(false);
            articulo.setGranel(false);

            actualizarCategoria(new CategoriaController().findById(1));
            actualizarUnidadCompra(new UnidadController().findById(1));
            actualizarUnidadSalida(new UnidadController().findById(1));
            isupdate = false;
        }
    }

    //METODOS AUXILIARES DEFINIDOS POR EL USUARIO
    public static void actualizarCategoria(ponyvet.modelo.entidades.articulo.Categoria categoria) {
        articulo.setCategoria(categoria);
        txtCategoria.setText(categoria.getNombre());

    }

    public static void actualizarUnidadCompra(ponyvet.modelo.entidades.articulo.Unidad unidadCompra) {
        articulo.setUnidadCompra(unidadCompra);
        txtUnidadCom.setText(unidadCompra.getNombre());
        lblUnidadCompra.setText('x' + unidadCompra.getNombre());
    }

    public static void actualizarUnidadSalida(ponyvet.modelo.entidades.articulo.Unidad unidadSalida) {
        articulo.setUnidadSalida(unidadSalida);
        txtUnidadSal.setText(unidadSalida.getNombre());
        lblUnidadSalida.setText('x' + unidadSalida.getNombre());
    }

    public static boolean verificarIgualdadUnidad() {
        boolean estado = false;
        if (articulo.getUnidadCompra().getId() == articulo.getUnidadSalida().getId()) {
            estado = true;
        }
        return estado;
    }

    public static void calularPrecioSalida() {

        if (!txtPrecioCompra.getText().equals("") && !txtFactor.getText().equals("")) {
            float factor = Float.parseFloat(txtFactor.getText());
            float precioCompra = Float.parseFloat(txtPrecioCompra.getText());

            if (factor > 0) {
                float precioSalida = precioCompra / factor;
                txtPrecioSalida.setText(Float.toString(precioSalida));
            }
        }

    }

    public void colocarImagen(String path) {
        ImageIcon icon = new ImageIcon(path);
        ImageIcon imageIcon = new ImageIcon(icon.getImage()
                .getScaledInstance(lblImagen.getWidth(),
                        lblImagen.getHeight(),
                        Image.SCALE_SMOOTH));
        lblImagen.setIcon(imageIcon);
    }

    private boolean validarCampos() {
        boolean resultado = true;

        //CAMPO DE LA CLAVE 
        txtClave.setBorder(new JTextField().getBorder());

        if (txtClave.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la clave. Puede generar una automáticamente"
                    + "\ncon el botón 'Generar Clave', en la parte inferior.");
            txtClave.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        if (!Validar.isAlphaNumericKey(txtClave.getText())) {
            JOptionPane.showMessageDialog(null, "Clave invalida. Solo puedes usar letras de la A a las Z y números.\nNo puede usar la letra ñ ni signos de puntuación.");
            txtClave.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }

        //CAMPO DESCRIPCION
        txtDescripcion.setBorder(new JTextField().getBorder());

        if (txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripcion del artículo.");
            txtDescripcion.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }

        //CAMPO FACTOR
        txtFactor.setBorder(new JTextField().getBorder());

        if (!Validar.isFloat(txtFactor.getText())) {
            JOptionPane.showMessageDialog(null, "Factor incorrecto. Debe escribir un número entero o decimales.");
            txtFactor.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        if (Float.parseFloat(txtFactor.getText()) == 0.0f) {
            JOptionPane.showMessageDialog(null, "El factor no puede ser igual a 0");
            txtFactor.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }

        //CAMPO INVENTARIO MIN
        txtInventarioMin.setBorder(new JTextField().getBorder());

        if (!Validar.isFloat(txtInventarioMin.getText())) {
            txtInventarioMin.setBorder(new LineBorder(Color.RED));
            resultado = false;
            JOptionPane.showMessageDialog(null, "Inventario mínimo incorrecto. Debe escribir un número entero o decimales."
                    + "\nTambién puede escribir 0");
        }
        
        if(!articulo.isGranel() && Float.parseFloat(txtInventarioMin.getText()) % 1 != 0){
            txtInventarioMin.setBorder(new LineBorder(Color.RED));
            resultado = false;
            JOptionPane.showMessageDialog(null, "Este artículo no se puede manejar por fracciones. Puedes marcar la opción \"Granel\"\n"
                    + "dentro de la pestaña \"Adicional\" o también puede escribir 0");
        }

        //CAMPO INVENTARIO MAX
        txtInventarioMax.setBorder(new JTextField().getBorder());

        if (!Validar.isFloat(txtInventarioMax.getText())) {
            JOptionPane.showMessageDialog(null, "Inventario máximo incorrecto. Debe escribir un número entero o decimales."
                    + "\nTambién puede escribir 0");
            txtInventarioMax.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        
         if(!articulo.isGranel() && Float.parseFloat(txtInventarioMax.getText()) % 1 != 0){
            txtInventarioMax.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }

        //CAMPO PRECIO COMPRA
        txtPrecioCompra.setBorder(new JTextField().getBorder());

        if (!Validar.isFloat(txtPrecioCompra.getText())) {
            JOptionPane.showMessageDialog(null, "Precio de salida incorrecto. Debe escribir un número entero o decimales.");
            txtPrecioCompra.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        if (Float.parseFloat(txtPrecioCompra.getText()) <= 0.0f || Float.parseFloat(txtPrecioCompra.getText()) >= ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO) {
            JOptionPane.showMessageDialog(null, "El precio de compra debe ser mayor a 0 y menor a "+ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO);
            txtPrecioCompra.setBorder(new LineBorder(Color.RED));
            resultado = false;
        
        }
        //CAMPO PRECIO SALIDA
        txtPrecioSalida.setBorder(new JTextField().getBorder());

        if (!Validar.isFloat(txtPrecioSalida.getText())) {
            JOptionPane.showMessageDialog(null, "Precio de compra incorrecto. Debe escribir un número entero o decimales.");
            txtPrecioSalida.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        if (Float.parseFloat(txtPrecioSalida.getText()) <= 0.0f || Float.parseFloat(txtPrecioCompra.getText()) >= ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO) {
            JOptionPane.showMessageDialog(null, "El precio de salida  debe ser mayor a 0 y menor a "+ ConfiguracionesGenerales.MAXIMA_CANTIDAD_PRECIO);
            txtPrecioSalida.setBorder(new LineBorder(Color.RED));
            resultado = false;
        }
        return resultado;
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        btnCambiarCategoria = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtUnidadCom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUnidadSal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFactor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtInventarioMin = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtInventarioMax = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLocalizacion = new javax.swing.JTextField();
        btnUnidadSal = new javax.swing.JButton();
        btnCambiarUnidadCom = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblUnidadCompra = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        txtPrecioSalida = new javax.swing.JTextField();
        lblUnidadSalida = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCaracteristicas = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnAgregarImg = new javax.swing.JButton();
        btnEliminarImg = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jcbLote = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jcbGranel = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jcbPrecioSalida = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        txtAviso1 = new javax.swing.JLabel();
        txtAviso2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnGenerarClave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario Artículo");
        setResizable(false);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Clave:");

        txtClave.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Descripción:");

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 51));
        jLabel3.setText("Categoria:");

        txtCategoria.setEditable(false);
        txtCategoria.setBackground(new java.awt.Color(255, 255, 255));
        txtCategoria.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCategoria.setText("SIN ASIGNAR");
        txtCategoria.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCategoria.setSelectionColor(new java.awt.Color(0, 102, 153));

        btnCambiarCategoria.setBackground(new Color(255, 255, 255));
        btnCambiarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-add-24.png"))); // NOI18N
        btnCambiarCategoria.setFocusPainted(false);
        btnCambiarCategoria.setOpaque(true);
        btnCambiarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCambiarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDescripcion)
                    .addComponent(txtClave))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 51));
        jLabel4.setText("Unidad de compra:");

        txtUnidadCom.setEditable(false);
        txtUnidadCom.setBackground(new java.awt.Color(255, 255, 255));
        txtUnidadCom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUnidadCom.setText("PZA");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 51));
        jLabel5.setText("Unidad de salida:");

        txtUnidadSal.setEditable(false);
        txtUnidadSal.setBackground(new java.awt.Color(255, 255, 255));
        txtUnidadSal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUnidadSal.setText("PZA");
        txtUnidadSal.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Factor:");

        txtFactor.setEditable(false);
        txtFactor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtFactor.setText("1.0");
        txtFactor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFactorKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Inventario mínimo:");

        txtInventarioMin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtInventarioMin.setText("0.0");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Inventario máximo:");

        txtInventarioMax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtInventarioMax.setText("0.0");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Localizacación");

        txtLocalizacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtLocalizacion.setText("Almacén");

        btnUnidadSal.setBackground(new Color(255, 255, 255));
        btnUnidadSal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-add-24.png"))); // NOI18N
        btnUnidadSal.setFocusPainted(false);
        btnUnidadSal.setOpaque(true);
        btnUnidadSal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnidadSalActionPerformed(evt);
            }
        });

        btnCambiarUnidadCom.setBackground(new Color(255, 255, 255));
        btnCambiarUnidadCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-add-24.png"))); // NOI18N
        btnCambiarUnidadCom.setFocusPainted(false);
        btnCambiarUnidadCom.setOpaque(true);
        btnCambiarUnidadCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarUnidadComActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtUnidadSal))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUnidadCom)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCambiarUnidadCom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUnidadSal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventarioMin, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInventarioMax, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLocalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCambiarUnidadCom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtUnidadCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnUnidadSal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtUnidadSal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtInventarioMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(txtInventarioMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Precio de compra:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Precio de salida:");

        lblUnidadCompra.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUnidadCompra.setText("xPZA");

        txtPrecioCompra.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPrecioCompra.setText("0.0");
        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyReleased(evt);
            }
        });

        txtPrecioSalida.setEditable(false);
        txtPrecioSalida.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPrecioSalida.setText("0.0");

        lblUnidadSalida.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUnidadSalida.setText("xPZA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrecioSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(txtPrecioCompra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUnidadCompra)
                    .addComponent(lblUnidadSalida))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidadCompra))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPrecioSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidadSalida))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Características");

        txtCaracteristicas.setColumns(15);
        txtCaracteristicas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCaracteristicas.setLineWrap(true);
        txtCaracteristicas.setRows(5);
        jScrollPane1.setViewportView(txtCaracteristicas);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setText("Imágen:");

        jPanel8.setLayout(new java.awt.GridBagLayout());

        lblImagen.setBackground(new java.awt.Color(204, 204, 204));
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 14.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(lblImagen, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridLayout(1, 3));

        btnAgregarImg.setText("+");
        btnAgregarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImgActionPerformed(evt);
            }
        });
        jPanel9.add(btnAgregarImg);

        btnEliminarImg.setText("-");
        btnEliminarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarImgActionPerformed(evt);
            }
        });
        jPanel9.add(btnEliminarImg);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel8.add(jPanel9, gridBagConstraints);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Características", jPanel3);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel18.setText("Algunas configuraciones adicionales:");

        jcbLote.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbLote.setText("Lote");
        jcbLote.setEnabled(false);
        jcbLote.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbLoteItemStateChanged(evt);
            }
        });

        jcbGranel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbGranel.setText("Granel");
        jcbGranel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbGranelItemStateChanged(evt);
            }
        });
        jcbGranel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbGranelMouseClicked(evt);
            }
        });
        jcbGranel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbGranelActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("(Indica si manejará lotes y fechas de caducidad para este producto )");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("(Indica si quieres registrar el producto con un precio de salida especifico)");

        jcbPrecioSalida.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbPrecioSalida.setText("Precio Salida");
        jcbPrecioSalida.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbPrecioSalidaItemStateChanged(evt);
            }
        });
        jcbPrecioSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPrecioSalidaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("(Indica si el artículo se puede vender en partes fraccionarias)");

        txtAviso1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtAviso1.setForeground(new java.awt.Color(255, 51, 51));

        txtAviso2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtAviso2.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAviso1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jcbLote)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jcbGranel)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jcbPrecioSalida)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)))
                        .addGap(0, 246, Short.MAX_VALUE))
                    .addComponent(txtAviso2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbLote)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbGranel)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbPrecioSalida)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(txtAviso1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAviso2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Adicional", jPanel10);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-save-22.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 92;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 157, 2, 0);
        jPanel4.add(btnGuardar, gridBagConstraints);

        btnGenerarClave.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGenerarClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icons8-key-22.png"))); // NOI18N
        btnGenerarClave.setText("GENERAR CLAVE");
        btnGenerarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarClaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 58, 2, 162);
        jPanel4.add(btnGenerarClave, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnCambiarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarCategoriaActionPerformed
        // TODO add your handling code here:
        JDCategoria categoria = new JDCategoria(new JFrame(), true);
        categoria.setVisible(true);
    }//GEN-LAST:event_btnCambiarCategoriaActionPerformed

    private void btnCambiarUnidadComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarUnidadComActionPerformed
        // TODO add your handling code here:
        JDUnidad unidad = new JDUnidad(new JFrame(), true, true);
        unidad.setVisible(true);
    }//GEN-LAST:event_btnCambiarUnidadComActionPerformed

    private void btnUnidadSalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnidadSalActionPerformed
        // TODO add your handling code here:
        JDUnidad unidad = new JDUnidad(new JFrame(), true, false);
        unidad.setVisible(true);
    }//GEN-LAST:event_btnUnidadSalActionPerformed

    private void txtPrecioCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyReleased
        JDFormularioArticulo.calularPrecioSalida();
    }//GEN-LAST:event_txtPrecioCompraKeyReleased

    private void txtFactorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFactorKeyReleased
        // TODO add your handling code here:
        JDFormularioArticulo.calularPrecioSalida();
    }//GEN-LAST:event_txtFactorKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        //Dar valores de los campos al articulo

        if (validarCampos()) {
            articulo.setClave(txtClave.getText());
            articulo.setDescripcion(txtDescripcion.getText());
            articulo.setPrecioCom(Float.parseFloat(txtPrecioCompra.getText()));
            articulo.setPrecioSal(Float.parseFloat(txtPrecioSalida.getText()));
            articulo.setFactor(Float.parseFloat(txtFactor.getText()));
            articulo.setInventarioMax(Float.parseFloat(txtInventarioMax.getText()));
            articulo.setInventarioMin(Float.parseFloat(txtInventarioMin.getText()));
            //Atributos que pueden ser nulos
            if (!txtCaracteristicas.getText().equals("")) {
                articulo.setCaracteristicas(txtCaracteristicas.getText());
            }
            if (!txtLocalizacion.getText().equals("")) {
                articulo.setLocalizacion(txtLocalizacion.getText());
            }              
            
          
            if(articulo.isGranel() != jcbGranel.isSelected()){
                if(jcbGranel.isSelected() == false){
                    articulo.setExistencia(0.0f);
                }
            }
            
            if(articulo.isLote() != jcbLote.isSelected()){
                if(jcbLote.isSelected() == false){
                    LoteController.deleteAll(articulo.getClave());
                }else{
                    articulo.setExistencia(0.0f);
                }
            }
            
            articulo.setGranel(jcbGranel.isSelected());
            articulo.setLote(jcbLote.isSelected());
            
            this.dispose();
            if (isupdate) {
                ArticuloController.update(articulo);
            } else {
                ArticuloController.create(articulo);
            }
            JFArticulo.actualizarTablaArticulos("");
        }

    }//GEN-LAST:event_btnGuardarActionPerformed


    private void jcbGranelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbGranelItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            txtUnidadSal.setText(articulo.getUnidadCompra().getNombre());
            articulo.setUnidadSalida(articulo.getUnidadCompra());
            txtUnidadSal.setEditable(false);
            btnUnidadSal.setEnabled(false);
        }else{
            txtUnidadSal.setEditable(true);
            btnUnidadSal.setEnabled(true);
        }
        
        if (isupdate) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                if (articulo.isGranel()) {
                    txtAviso1.setText("");
                }
            } else {
                if (articulo.isGranel()) {
                    txtAviso1.setText("GRANEL: SE ACTUALIZARA LA EXISTENCIA A 0");
                } else {
                    txtAviso1.setText("");
                }
            }
        }
    }//GEN-LAST:event_jcbGranelItemStateChanged

    private void jcbLoteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbLoteItemStateChanged
        // TODO add your handling code here:
        if (isupdate) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                if (articulo.isLote()) {
                    txtAviso2.setText("");
                } else {
                    txtAviso2.setText("LOTE: SE ACTUALIZARA LA EXISTENCIA A 0");
                }
            } else {
                if (articulo.isLote()) {
                    txtAviso2.setText("LOTE: SE ELIMINARAN LOS LOTES RELACIONADOS CON EL ARTÍCULO");
                } else {
                    txtAviso2.setText("");
                }
            }
        }
    }//GEN-LAST:event_jcbLoteItemStateChanged

    private void jcbPrecioSalidaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbPrecioSalidaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            txtPrecioSalida.setEditable(true);
        } else {
            calularPrecioSalida();
            txtPrecioSalida.setEditable(false);
        }
    }//GEN-LAST:event_jcbPrecioSalidaItemStateChanged

    private void jcbPrecioSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPrecioSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPrecioSalidaActionPerformed

    private void btnAgregarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImgActionPerformed
        // TODO add your handling code here:
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        jFileChooser1.setFileFilter(imageFilter);
        int returnVal = jFileChooser1.showOpenDialog(JDFormularioArticulo.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) { //SI SELECCIONO UN ARCHIVO
            File file = jFileChooser1.getSelectedFile();
            //byte[] fileContent = ImageUtilities.getBytesImage(file);
            //colocarImagen(new ImageIcon(fileContent));
            articulo.setImagen(file.getAbsolutePath());
            colocarImagen(articulo.getImagen());

        }
    }//GEN-LAST:event_btnAgregarImgActionPerformed

    private void btnEliminarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarImgActionPerformed
        // TODO add your handling code here:
        if (lblImagen.getIcon() != null) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Realmente deseas borrar la imagen?", "Eliminar imagen",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (resp == 0) {
                lblImagen.setIcon(null);
                articulo.setImagen(null);
            }
        }
    }//GEN-LAST:event_btnEliminarImgActionPerformed

    private void btnGenerarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarClaveActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(null, 
                "¿Deseas generar clave aleatoria?", "Generar código de barras",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (resp == 0) {
            String key = CodigoBarras.generarCodigoGenerico(CodigoBarras.EAN13);
            while (ArticuloController.findByClave(key) != null) {
                key = CodigoBarras.generarCodigoGenerico(CodigoBarras.EAN13);
            }
            txtClave.setText(key);
        }
    }//GEN-LAST:event_btnGenerarClaveActionPerformed

    private void txtClaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveKeyTyped

    private void jcbGranelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbGranelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbGranelMouseClicked

    private void jcbGranelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbGranelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbGranelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarImg;
    private javax.swing.JButton btnCambiarCategoria;
    private javax.swing.JButton btnCambiarUnidadCom;
    private javax.swing.JButton btnEliminarImg;
    private javax.swing.JButton btnGenerarClave;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnUnidadSal;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox jcbGranel;
    private javax.swing.JCheckBox jcbLote;
    private javax.swing.JCheckBox jcbPrecioSalida;
    private javax.swing.JLabel lblImagen;
    private static javax.swing.JLabel lblUnidadCompra;
    private static javax.swing.JLabel lblUnidadSalida;
    private javax.swing.JLabel txtAviso1;
    private javax.swing.JLabel txtAviso2;
    private javax.swing.JTextArea txtCaracteristicas;
    private static javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextField txtFactor;
    private javax.swing.JTextField txtInventarioMax;
    private javax.swing.JTextField txtInventarioMin;
    private javax.swing.JTextField txtLocalizacion;
    private static javax.swing.JTextField txtPrecioCompra;
    private static javax.swing.JTextField txtPrecioSalida;
    private static javax.swing.JTextField txtUnidadCom;
    private static javax.swing.JTextField txtUnidadSal;
    // End of variables declaration//GEN-END:variables
}
