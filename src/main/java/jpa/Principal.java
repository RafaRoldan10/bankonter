package jpa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jpa.controllers.ControladorContrato;
import jpa.controllers.ControladorTipoContrato;
import jpa.controllers.ControladorUsuario;
import jpa.model.Contrato;
import jpa.model.Tipocontrato;
import jpa.model.Usuario;
import jpa.utils.Utils;
import jpa.vista.PanelTipoContrato;
import jpa.vista.PanelUsuario;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToolBar;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;



import javax.swing.event.ChangeEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtfContrato;
	private JTextField jtfUsuario;
	private JTextField jtfDescripcion;
	private JSpinner spinner;
	private JSlider slider;
	private JLabel lblSaldo;
	private JFormattedTextField jtfFecha ;
	private SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
	private Contrato actual = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muestraContrato((Contrato)ControladorContrato.getInstance().findFirst());
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/tutorialJava/capitulo9_AWT_SWING/res/gotostart.png")));
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muestraContrato((Contrato)ControladorContrato.getInstance().findPrevious(actual.getId()));
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Principal.class.getResource("/tutorialJava/capitulo9_AWT_SWING/res/previous.png")));
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muestraContrato((Contrato)ControladorContrato.getInstance().findNext(actual.getId()));
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(Principal.class.getResource("/tutorialJava/capitulo9_AWT_SWING/res/next.png")));
		toolBar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				muestraContrato((Contrato)ControladorContrato.getInstance().findLast());
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(Principal.class.getResource("/tutorialJava/capitulo9_AWT_SWING/res/gotoend.png")));
		toolBar.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		btnNewButton_6.setIcon(new ImageIcon(Principal.class.getResource("/tutorialJava/capitulo9_AWT_SWING/res/nuevo.png")));
		toolBar.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnNewButton_7.setIcon(new ImageIcon(Principal.class.getResource("/tutorialJava/capitulo9_AWT_SWING/res/guardar.png")));
		toolBar.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnNewButton_8.setIcon(new ImageIcon(Principal.class.getResource("/tutorialJava/capitulo9_AWT_SWING/res/eliminar.png")));
		toolBar.add(btnNewButton_8);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Contrato");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 11;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfDescripcion = new JTextField();
		GridBagConstraints gbc_jtfDescripcion = new GridBagConstraints();
		gbc_jtfDescripcion.gridwidth = 9;
		gbc_jtfDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_jtfDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDescripcion.gridx = 2;
		gbc_jtfDescripcion.gridy = 1;
		panel.add(jtfDescripcion, gbc_jtfDescripcion);
		jtfDescripcion.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jtfFecha = getJFormattedTextFieldDatePersonalizado();
		GridBagConstraints gbc_jtfFecha = new GridBagConstraints();
		gbc_jtfFecha.gridwidth = 9;
		gbc_jtfFecha.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFecha.gridx = 2;
		gbc_jtfFecha.gridy = 2;
		panel.add(jtfFecha, gbc_jtfFecha);
		
		JLabel lblNewLabel_3 = new JLabel("Limite");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				slider.setMaximum((int)spinner.getValue());
			}
		});
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 3;
		panel.add(spinner, gbc_spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Saldo");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSaldo.setText(slider.getValue() + " euros");
			}
		});
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 9;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 2;
		gbc_slider.gridy = 4;
		panel.add(slider, gbc_slider);
		
		lblSaldo = new JLabel("");
		GridBagConstraints gbc_lblSaldo = new GridBagConstraints();
		gbc_lblSaldo.insets = new Insets(0, 0, 5, 0);
		gbc_lblSaldo.gridx = 11;
		gbc_lblSaldo.gridy = 4;
		panel.add(lblSaldo, gbc_lblSaldo);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo Contrato");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 5;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		jtfContrato = new JTextField();
		jtfContrato.setEditable(false);
		GridBagConstraints gbc_jtfContrato = new GridBagConstraints();
		gbc_jtfContrato.gridwidth = 9;
		gbc_jtfContrato.insets = new Insets(0, 0, 5, 5);
		gbc_jtfContrato.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfContrato.gridx = 2;
		gbc_jtfContrato.gridy = 5;
		panel.add(jtfContrato, gbc_jtfContrato);
		jtfContrato.setColumns(10);
		
		JButton btnTipoContrato = new JButton("Ver tipo Contrato");
		btnTipoContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaTipoContrato();
			}
		});
		GridBagConstraints gbc_btnTipoContrato = new GridBagConstraints();
		gbc_btnTipoContrato.insets = new Insets(0, 0, 5, 0);
		gbc_btnTipoContrato.gridx = 11;
		gbc_btnTipoContrato.gridy = 5;
		panel.add(btnTipoContrato, gbc_btnTipoContrato);
		
		JLabel lblNewLabel_6 = new JLabel("Usuario");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 6;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		jtfUsuario = new JTextField();
		jtfUsuario.setEditable(false);
		GridBagConstraints gbc_jtfUsuario = new GridBagConstraints();
		gbc_jtfUsuario.gridwidth = 9;
		gbc_jtfUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_jtfUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfUsuario.gridx = 2;
		gbc_jtfUsuario.gridy = 6;
		panel.add(jtfUsuario, gbc_jtfUsuario);
		jtfUsuario.setColumns(10);
		
		JButton btnUsuario = new JButton("Ver Usuario");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaUsuario();
			}
		});
		
			
		
		GridBagConstraints gbc_btnUsuario = new GridBagConstraints();
		gbc_btnUsuario.gridx = 11;
		gbc_btnUsuario.gridy = 6;
		panel.add(btnUsuario, gbc_btnUsuario);
		
		muestraContrato((Contrato)ControladorContrato.getInstance().findFirst());
	}
	
	private void muestraContrato(Contrato c) {
		if(c != null) {
			this.actual = c;
			this.jtfContrato.setText(c.getTipocontrato().getDescripcion());
			this.jtfUsuario.setText(c.getUsuario().getNombreUsuario());
			this.jtfDescripcion.setText(c.getDescripcion());
			this.jtfFecha.setText(Utils.getFormattedStringFromDate("dd/MM/yyyy HH:mm:ss", this.actual.getFechaFirma()));
			this.spinner.setValue((int)c.getLimite());
			this.slider.setMaximum((int)c.getLimite());
			this.slider.setValue((int)c.getSaldo());
			this.lblSaldo.setText(c.getSaldo() + " euros");
		}
		
	}
	
	private void nuevo() {
		this.actual = new Contrato();
		
		this.jtfDescripcion.setText("");
		this.jtfFecha.setText("");
		this.slider.setValue(0);
		this.spinner.setValue(0);
		this.actual.setTipocontrato((Tipocontrato) ControladorTipoContrato.getInstance().obtencionUnaSolaEntidad(1));
		this.actual.setUsuario((Usuario)ControladorUsuario.getInstance().obtencionUnaSolaEntidad(1));
		
		muestraContrato(actual);
	}
	
	private void eliminar() {
		try {
			String respuestas[] = new String[] {"Sí", "No"};
			int opcionElegida = JOptionPane.showOptionDialog(
					null, 
					"¿Realmente desea eliminar el registro?", 
					"Eliminación de fabricante", 
			        JOptionPane.DEFAULT_OPTION, 
			        JOptionPane.WARNING_MESSAGE, 
			        null, respuestas, 
			        respuestas[1]);
		    
			if(opcionElegida == 0) {
				int id = actual.getId();
				ControladorContrato.getInstance().delete(actual);
		    	  
		    	  // Decido qué registro voy a mostrar en pantalla.
		    	  // Voy a comprobar si existe un anterior, si existe lo muestro
		    	  // Si no existe anterior compruebo si existe siguiente, 
		    	  // si existe lo muestro. En caso contrario, no quedan registros
		    	  // así que muestro en blanco la pantalla
		    	  this.actual = (Contrato)ControladorContrato.getInstance().findPrevious(id);
		    	  if (this.actual != null) { // Existe un anterior, lo muestro
		    		  muestraContrato(actual);
		    	  }
		    	  else {
		    		  this.actual = (Contrato)ControladorContrato.getInstance().findNext(id);
			    	  if (this.actual != null) { // Existe un anterior, lo muestro
			    		  muestraContrato(actual);
			    	  }
		    		  else { // No quedan registros en la tabla
		    			  nuevo();
		    		  }
		    	  }
		      }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	
	private void guardar() {
		this.actual.setDescripcion(this.jtfDescripcion.getText());
		this.actual.setFechaFirma(Utils.getDateFromFormattedString("dd/MM/yyyy", this.jtfFecha.getText()));
		this.actual.setLimite( ((Number) this.spinner.getValue()).intValue() );
		this.actual.setSaldo(this.slider.getValue());
		
		try {
			ControladorContrato.getInstance().save(actual);
			JOptionPane.showMessageDialog(null, "Contrato guardado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "NO se ha guardado el contrato. ERROR");
		}
	}

	
	private JFormattedTextField getJFormattedTextFieldDatePersonalizado() {
		JFormattedTextField jftf = new JFormattedTextField(
				new JFormattedTextField.AbstractFormatter() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null && value instanceof Date) {
					return sdf.format(((Date) value));
				}
				return "";
			}

			@Override
			public Object stringToValue(String text) throws ParseException {
				try {
					jtfFecha.setBackground(Color.WHITE);
					return sdf.parse(text);
				} catch (Exception e) {
					jtfFecha.setBackground(Color.RED);
					return null;
				}
			}
		});
		jftf.setColumns(20);
		jftf.setValue(new Date());
		return jftf;
	}
	
	private void seleccionaUsuario() {
		Utils.dialogo(new PanelUsuario(actual.getUsuario(),this));
	}
	/**
	 * 
	 */
	private void seleccionaTipoContrato() {
		Utils.dialogo(new PanelTipoContrato(actual.getTipocontrato(),this));
	}
	
	/**
	 * 
	 * @param t
	 */
	public void setTipoContrato (Tipocontrato t) {
		if (t != null) {
			this.actual.setTipocontrato(t);
			this.jtfContrato.setText(t.toString());
		}
	}
	
	
	/**
	 * 
	 * @param t
	 */
	public void setUsuario (Usuario u) {
		if (u != null) {
			this.actual.setUsuario(u);
			this.jtfUsuario.setText(u.toString());
		}
	}
	
	



}
