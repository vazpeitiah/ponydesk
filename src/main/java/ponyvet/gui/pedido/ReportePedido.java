package ponyvet.gui.pedido;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import ponyvet.modelo.dao.ConexionDB;
import ponyvet.utilities.FileTypeFilter;

public class ReportePedido {
    
    private long id;
    private static String BASE_NAME = "Pedido_ID";
    
    public ReportePedido(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void verReportePedido() {
        ConexionDB con = new ConexionDB();
        Connection conexion = con.conectar();
        
        Map<String, Object> map = new HashMap<>();
        map.put("PEDIDO_ID", id);
        JasperPrint jasperPrintWindow;
        
        try {
            jasperPrintWindow = JasperFillManager.fillReport("JasperReports/report.jasper", map, conexion);
            JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con.cerrar();
    }
    
    public boolean generarReportePedido(String path) {
        boolean result = false;
        ConexionDB con = new ConexionDB();
        Connection conexion = con.conectar();
        
        Map<String, Object> map = new HashMap<>();
        map.put("PEDIDO_ID", id);
        
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport("JasperReports/report.jasper", map, conexion);
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
            result = true;
        } catch (JRException ex) {
            Logger.getLogger(ReportePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con.cerrar();
        return result;
    }
    
    public static void guardarReportePedido(long pedido_id) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "PDF Documents (*.pdf)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".pdf");
                }
            }
        });
        fileChooser.setAcceptAllFileFilterUsed(false);

        FileFilter pdfFilter = new FileTypeFilter(".pdf", "PDF Documents");
        fileChooser.addChoosableFileFilter(pdfFilter);
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd_MM_yyyy_H_mm_ss");
        String defpath = System.getProperty("user.home").concat("\\Documentos").concat("\\PedidoClave").concat(String.valueOf(pedido_id)).concat("_").concat(DATE_FORMAT.format(new Date())).concat(".pdf");
        fileChooser.setSelectedFile(new File(defpath));

        int option = fileChooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            boolean result = new ReportePedido(pedido_id).generarReportePedido(file.getAbsolutePath());
            if (result) {
                JOptionPane.showMessageDialog(null, "Se almacenó correctamente el archivo en " + file.getAbsolutePath(), "Reporta almacenadó correctamente.", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pude guardar el reporte", "Error al generar reporte", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
