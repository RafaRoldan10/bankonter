package jpa.vista;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import jpa.Principal;
import jpa.controllers.ControladorUsuario;
import jpa.model.Contrato;
import jpa.model.Usuario;
import jpa.utils.Utils;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<Usuario> listaUsuario = (List<Usuario>)ControladorUsuario.getInstance().findAll();
	private DefaultListModel<Usuario> listModelUsuarios = null;
	private JTextField jtfFiltra;
	private JList jlistUsuarios;
	private JRadioButton rdbtnEmail;
	private JRadioButton rdbtnNombre;
	private Usuario actual;
	private Principal p;
	
	
	/**
	 * Create the panel.
	 */
	public PanelUsuario(Usuario c, Principal p) {
		this.actual = c;
		this.p = p;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtfFiltra = new JTextField();
		GridBagConstraints gbc_jtfFiltra = new GridBagConstraints();
		gbc_jtfFiltra.gridwidth = 10;
		gbc_jtfFiltra.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltra.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltra.gridx = 1;
		gbc_jtfFiltra.gridy = 1;
		add(jtfFiltra, gbc_jtfFiltra);
		jtfFiltra.setColumns(10);
		
		JButton btnFiltra = new JButton("Filtra");
		btnFiltra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtra();
			}
		});
		GridBagConstraints gbc_btnFiltra = new GridBagConstraints();
		gbc_btnFiltra.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltra.gridx = 11;
		gbc_btnFiltra.gridy = 1;
		add(btnFiltra, gbc_btnFiltra);
		
		rdbtnEmail = new JRadioButton("Email");
		GridBagConstraints gbc_rdbtnEmail = new GridBagConstraints();
		gbc_rdbtnEmail.gridwidth = 3;
		gbc_rdbtnEmail.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnEmail.gridx = 1;
		gbc_rdbtnEmail.gridy = 2;
		add(rdbtnEmail, gbc_rdbtnEmail);
		
		JCheckBox caseSensitive = new JCheckBox("Case Sensitive");
		GridBagConstraints gbc_caseSensitive = new GridBagConstraints();
		gbc_caseSensitive.insets = new Insets(0, 0, 5, 5);
		gbc_caseSensitive.gridx = 5;
		gbc_caseSensitive.gridy = 2;
		add(caseSensitive, gbc_caseSensitive);
		
		rdbtnNombre = new JRadioButton("Nombre");
		GridBagConstraints gbc_rdbtnNombre = new GridBagConstraints();
		gbc_rdbtnNombre.gridwidth = 3;
		gbc_rdbtnNombre.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNombre.gridx = 1;
		gbc_rdbtnNombre.gridy = 3;
		add(rdbtnNombre, gbc_rdbtnNombre);
		jlistUsuarios = new JList(this.getDefaultListModel());
		this.jlistUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(jlistUsuarios);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 12;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectUser();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 9;
		add(btnNewButton, gbc_btnNewButton);
		
		addControlCustomizableBehaviours();

	}
	
	private DefaultListModel getDefaultListModel () {
		this.listModelUsuarios = new DefaultListModel<Usuario>();
		return this.listModelUsuarios;
	}
	
	private void filtra() {
		if(this.jtfFiltra.getText().equals("")) {
			for(Usuario u : listaUsuario) {
				this.listModelUsuarios.addElement(u);
			}
			return;
		}
		
		if(rdbtnNombre.isSelected()) {
			for(Usuario u : listaUsuario) {
				if(u.getNombreUsuario().toLowerCase().contains(this.jtfFiltra.getText().toLowerCase())) {
					this.listModelUsuarios.addElement(u);
				}
			}
			return;
		}
		if(rdbtnEmail.isSelected()) {
			for(Usuario u : listaUsuario) {
				if(u.getNombreUsuario().toLowerCase().contains(this.jtfFiltra.getText().toLowerCase())) {
					this.listModelUsuarios.addElement(u);
				}
			}
			return;
		}
	}
	
	
	
	private void addControlCustomizableBehaviours() {
		// Comportamiento excluyente en los JRadioButton
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNombre);
		bg.add(rdbtnEmail);
		rdbtnNombre.setSelected(true);		
	}
	
	/**
	 * 
	 */
	private void selectUser() {
		if (this.jlistUsuarios != null) {
			this.p.setUsuario((Usuario)jlistUsuarios.getSelectedValue());
			Utils.closeDialogFromInnerJPanel(this);
		}
	}



}
