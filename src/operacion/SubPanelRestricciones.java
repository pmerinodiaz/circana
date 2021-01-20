/**
 * @(#)SubPanelRestricciones.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.CardLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se permite configurar
 * las restricciones de oferta, demanda y capacidad de transporte.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Color
 * @see CardLayout
 * @see JPanel 
 * @see JTabbedPane
 * @see JComboBox
 * @see JLabel
 * @see Border
 * @see LineBorder
 * @see TitleBorder
 * @see SubPanelOfertaOperacion
 * @see SubPanelDemandaOperacion
 * @see SubPanelMedioOperacion
 * @see PanelTransportarOperacion
 * @see EventoComboBox
 * @see InterfacePanel
 */
public class SubPanelRestricciones extends JPanel implements InterfacePanel
{
	/** Panel que hace referencia al creador de este objeto. */
	public PanelTransportarOperacion panelTransportarOperacion;
	
	/** La paleta que contiene la lista de paneles de restricción. */
	private JPanel paleta;
	
	/** Combobox para la selección del panel de restricción. */
	private JComboBox comboRestricciones;
	
	/** Texto para la fecha de la lista desplegable. */
	private JLabel textoFecha;
	
	/** Combobox para la selección de la fecha. */
	private JComboBox comboFecha;
	
	/** Vector de la lista de selección de paneles de restricción. */
	private String[] lista;
	
	/** Panel para asignar cantidad ofrecida. */
	private SubPanelOfertaOperacion subpanelOferta;
	
	/** Panel para asignar cantidad demandada. */
	private SubPanelDemandaOperacion subpanelDemanda;
	
	/** Panel para asignar medio de transporte. */
	private SubPanelMedioOperacion subpanelMedio;
	
	/**
     * Método constructor del panel de restricciones.
     *
     * @param panelTransportarOperacion El panel creador de este objeto.
     * @param x Inicio X del sub-panel.
     * @param y Inicio Y del sub-panel.
     * @param ancho Ancho del sub-panel.
     * @param largo Largo del sub-panel.
     */
	public SubPanelRestricciones(
							PanelTransportarOperacion panelTransportarOperacion,
							int x, int y, int ancho, int largo)
	{
		// Inicializar el puntero.
		this.panelTransportarOperacion = panelTransportarOperacion;
		
		setBounds(x,y,ancho,largo);
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este método corresponden a los atributos
	 * que se derivan de la clase JPanel.
	 */
	public void configurarPanel()
	{
		// borde del panel
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// titulos
		TitledBorder titulo = new TitledBorder(borde,
			" Restricciones del Problema de Transporte ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// agrega el titulo
		setBorder(titulo);
		setLayout(null);
	}
	
	/**
	 * Método que configura los componentes GUI contenidos en este panel.
	 */
	public void configurarComponentes()
	{
		lista = new String[3];
		lista[0] = "Oferta";
		lista[1] = "Demanda";
		lista[2] = "Medio de Transporte";
		
		// borde del panel
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// crear paneles
		subpanelOferta = new SubPanelOfertaOperacion();
		subpanelDemanda = new SubPanelDemandaOperacion();
		subpanelMedio = new SubPanelMedioOperacion();
		
		// Crear la paleta para los paneles.
		paleta = new JPanel(new CardLayout());
		
		// Incorporar los paneles al contenedor.
		paleta.add(lista[0],subpanelOferta);
		paleta.add(lista[1],subpanelDemanda);
		paleta.add(lista[2],subpanelMedio);
		
		// agregar la paleta
		add(paleta);
		
		// creando textos
		JLabel textoExplicacion =
			new JLabel("Ingrese las restricciones de oferta, demanda y medio " +
			"de transporte del problema de transporte.");
		JLabel textoRestricciones = new JLabel("Tipo de Restricción:");
		textoFecha = new JLabel("Fecha:");
		
		// crear el combo box
		comboFecha = new JComboBox();
		
		// cargar los combo box
		comboRestricciones = new JComboBox(lista);
		cargarComboFecha();
		
		// establecer tips
		comboRestricciones.setToolTipText("Seleccione el tipo de restricción");
		
		// posicionar dentro del panel
		textoExplicacion.setBounds(20,25,760,15);
		textoRestricciones.setBounds(20,50,120,20);
		comboRestricciones.setBounds(180,50,150,20);
		textoFecha.setBounds(20,80,120,20);
		comboFecha.setBounds(180,80,150,20);
		paleta.setBounds(20,110,760,120);
		
		// incorporar al panel principal
		add(textoExplicacion);
		add(textoRestricciones);
		add(comboRestricciones);
		add(textoFecha);
		add(comboFecha);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los combobox que tiene el 
	 * panel de transporte. En particular se incorpora el escuchador de eventos
	 * del tipo EventoComboBox a los JComboBox que tiene el panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoComboBox eventoCombo = new EventoComboBox(this);
		
		// Incorporar el escuchador de eventos a los combobox.
		comboRestricciones.addActionListener(eventoCombo);
		comboFecha.addActionListener(eventoCombo);
	}
	
	/**
     * Método que carga el combo fecha del panel restricciones.
     */
	private void cargarComboFecha()
	{
		int[] fechaInicial = Proyecto.obtenerFechaInicialFormatoInt();
		int[] fechaFinal = Proyecto.obtenerFechaFinalFormatoInt();
		int[] diaMes = new int[2];
		int[] fecha = new int[3];
		int diaInicial;
		int diaFinal;
		
		diaInicial = Servicio.obtenerDia(fechaInicial[0], fechaInicial[1]);
		diaFinal = Servicio.obtenerDia(fechaFinal[0], fechaFinal[1]);
		
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		
		String sql = "select codigo_dia, codigo_anio " +
			"from caladero_resultado " +
			"where ((codigo_dia >= " + diaInicial + " and " +
					"codigo_anio = " + fechaInicial[2] + ") or " +
				   "(codigo_dia <= " + diaFinal + " and " +
				    "codigo_anio = " + fechaFinal[2] + ")) and " +
				    "codigo_proyecto = " + Proyecto.obtenerCodigo() + " and " +
				    "codigo_tipo_caladero_resultado = 1 " +
			"group by codigo_dia order by codigo_anio, codigo_dia";
		
		comboFecha.removeAllItems();
		
		conexion.conectar();
		
		resultado = conexion.ejecutarConsulta(sql);
		
		try
		{
			resultado.first();
			if(resultado.getRow() != 0)
			{
				do
				{
					diaMes = Servicio.obtenerMesDia(
						resultado.getInt("codigo_dia"));
					fecha[0] = diaMes[1];
					fecha[1] = diaMes[0];
					fecha[2] = resultado.getInt("codigo_anio");
					
					comboFecha.addItem(Servicio.obtenerFecha(fecha));
					resultado.next();
				}
				while(!resultado.isAfterLast());
				comboFecha.setToolTipText(
					"Seleccione una fecha para cargar los caladeros");
				comboFecha.setEnabled(true);
			}
			else
			{
				comboFecha.addItem("Inicie el Ecosistema");
				comboFecha.setToolTipText(
					"Debe iniciar la simulación del ecosistema");
				comboFecha.setEnabled(false);
			}
		}
		catch(SQLException ex)
		{
			System.out.println("Error al cargar el combo fecha.");
		}
		
		conexion.desconectar();
	}
	
	/**
	 * Método que permite establecer las restricciones de oferta, demanda y 
	 * medio de transporte del problema de operativo de acuerdo a los valores
	 * entregados por el usuario.
	 */
	public void establecerRestricciones()
	{
		subpanelOferta.establecerRestricciones();
		subpanelDemanda.establecerRestricciones();
		subpanelMedio.establecerRestricciones();
	}
	
	/**
	 * Método que actualiza el subpanel visualizado entre oferta, demanda y
	 * medios de transporte en el panel de restricciones. Además, si el tipo de
	 * restricción no es de oferta, entonces no se muestra el combo de fecha.
	 */
	public void establecerPaleta()
	{
		// Cambia al subpanel selecionado en el combobox
		((CardLayout) paleta.getLayout()).show(paleta,
			(String) comboRestricciones.getSelectedItem());
		
		// Cuando no es la restricción de oferta.
		if (comboRestricciones.getSelectedIndex() != 0)
		{
			textoFecha.setVisible(false);
			comboFecha.setVisible(false);
		}
		
		// Cuando es la restricción de oferta.
		else
		{
			textoFecha.setVisible(true);
			comboFecha.setVisible(true);
		}
	}
	
	/**
	 * Método que obtiene la fecha seleccionada el combobox.
	 *
	 * @return fecha Cadena con la fecha seleccionada en el combobox.
	 */
	public String obtenerFecha()
	{
		return "" + comboFecha.getSelectedItem();
	}
	
	/**
	 * Método que actualiza el panel que contiene los puntos de oferta
	 * (caladeros). El panel muestra los caladeros disponibles de acuerdo a la
	 * fecha seleccionada en el combobox.
	 */
	public void actualizarPaneles()
	{
		subpanelOferta = new SubPanelOfertaOperacion();
		paleta.remove(0);
		paleta.add(subpanelOferta, lista[0], 0);
		establecerPaleta();
		ConfiguracionAGR.establecerTablaDistancia();
		this.updateUI();
	}
	
	/**
	 * Método que obtiene el objeto comboRestricciones.
	 *
	 * @return ComboRestricciones El Combobox con la lista de los paneles de
	 *		    				  restricciones.
	 */
	public JComboBox obtenerComboRestricciones()
	{
		return comboRestricciones;
	}
	
	/**
	 * Método que obtiene el objeto comboFecha.
	 *
	 * @return ComboFecha El Combobox con el listado de fechas en las cuales
	 *  				  existen caladeros disponibles.
	 */
	public JComboBox obtenerComboFecha()
	{
		return comboFecha;
	}
}