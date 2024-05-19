package jpa.utils;

import java.awt.Component;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JPanel;



public class Utils {
	
	public static void dialogo(JPanel panel) {
		JDialog dialogo = new JDialog();
		// El usuario no puede redimensionar el diálogo
		dialogo.setResizable(true);
		// título del díalogo
		dialogo.setTitle("Título");
		// Introducimos el panel creado sobre el diálogo
		dialogo.setContentPane(panel);
		// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que deben y el lugar adecuado
		dialogo.pack();
		// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
		dialogo.setModal(true);
		// Centro el di�logo en pantalla
		dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
		
		
		// Muestro el di�logo en pantalla
		dialogo.setVisible(true);
	}
	
	public static void closeDialogFromInnerJPanel(JPanel pane) {
		JDialog dialog = getParentDialog(pane);
        if (dialog != null) {
            dialog.dispose(); // Cerrar el JDialog
        }
	}
	
	/**
     * Método para encontrar el JDialog ancestro
     * @param component
     * @return
     */
    private static JDialog getParentDialog(Component component) {
        if (component == null) {
            return null;
        }
        if (component instanceof JDialog) {
            return (JDialog) component;
        }
        return getParentDialog(component.getParent());
    }
    
    public static String getFormattedStringFromDate (String dateFormat, Date date) {
		try {
			return new SimpleDateFormat(dateFormat).format(date);
		}
		catch (Exception ex) {
			System.out.println("Unable to format date: " + date + " with format: " + dateFormat);
			return "";
		}
	}
    
    /**
	 * 
	 * @param dateFormat
	 * @param date
	 * @return
	 */
	public static Date getDateFromFormattedString (String dateFormat, String strDate) {
		try {
			return new SimpleDateFormat(dateFormat).parse(strDate);
		}
		catch (Exception ex) {
			System.out.println("Unable to parse string: " + strDate + " to java.util.Date with format: " + dateFormat);
			return null;
		}
	}




	
	
	
}


