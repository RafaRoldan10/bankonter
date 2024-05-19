package jpa.vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jpa.Principal;
import jpa.controllers.ControladorTipoContrato;
import jpa.model.Contrato;
import jpa.model.Tipocontrato;
import jpa.utils.Utils;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelTipoContrato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltrar;
	private List<Tipocontrato> lista = (List<Tipocontrato>)ControladorTipoContrato.getInstance().findAll();;
	private Tipocontrato actual;
	private Principal p;
	private JScrollPane jScrollPane;
	

	/**
	 * Create the panel.
	 */
	public PanelTipoContrato(Tipocontrato c, Principal p) {
		this.p = p;
		this.actual = c;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtfFiltrar = new JTextField();
		GridBagConstraints gbc_jtfFiltrar = new GridBagConstraints();
		gbc_jtfFiltrar.gridwidth = 10;
		gbc_jtfFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltrar.gridx = 1;
		gbc_jtfFiltrar.gridy = 1;
		add(jtfFiltrar, gbc_jtfFiltrar);
		jtfFiltrar.setColumns(10);
		
		JButton btnFiltrar = new JButton("Búsqueda Rápida");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtra();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltrar.gridx = 11;
		gbc_btnFiltrar.gridy = 1;
		add(btnFiltrar, gbc_btnFiltrar);
		
		jScrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		add(jScrollPane, gbc_scrollPane);
		jScrollPane.setPreferredSize(new Dimension(300, 400));
		
		JButton btnAceptar = new JButton("Ok");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 5;
		gbc_btnAceptar.gridy = 9;
		add(btnAceptar, gbc_btnAceptar);

	}
	
	@SuppressWarnings("unchecked")
	private void filtra() {
		List<Tipocontrato> lista = (List<Tipocontrato>)ControladorTipoContrato.getInstance().buscaConLikeYDescripcion(this.jtfFiltrar.getText());
				Object m[][] = new Object[lista.size()][2];
				for (int i = 0; i < m.length; i++) {
					m[i][0] = lista.get(i).getId();
					m[i][1] = lista.get(i).getDescripcion();
				}
				
				DefaultTableModel dtm = new DefaultTableModel(m, new String[] {"Identificador", "Descripción"}) {			
					/**
					 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
					 */
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				JTable table = new JTable(dtm);
				
				// Selecciono el tipo de contrato
				if (actual != null) {
					for (int i = 0; i < lista.size(); i++) {
						if (lista.get(i).getId() == this.actual.getId()) {
							table.setRowSelectionInterval(i, i);
						}
					}
				}
				
				// Determino el mecanismo de doble clic de seleccion del tipo de contrato sobre la tabla
				PanelTipoContrato thisPanel = this; // Necesito un puntero a "this" antes de entrar en la clase anónima de MouseAdapter
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						
						// Compruebo el doble clic
						if (e.getClickCount() > 1) {
							if (table.getSelectedRow() > -1) {
								p.setTipoContrato(lista.get(table.getSelectedRow()));
								Utils.closeDialogFromInnerJPanel(thisPanel);
							}
						}
					}
					
				});
				
				// Muestro la JTable construida
				jScrollPane.setViewportView(table);
			}



	}


