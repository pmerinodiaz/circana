/**
 * @(#)PanelPlanificarIntegracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario
 * las opciones para realizar la planificaci�n de ventas de la integraci�n final
 * de todos los modelos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see Color
 * @see Font
 * @see KeyEvent 
 * @see JPanel
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see SwingConstants
 * @see JComboBox
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see CampoNumerico
 * @see FrameIntegracion
 * @see EventoButton
 * @see ConfiguracionHeuristica
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class PanelPlanificarIntegracion extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** Puntero al frame que contiene a este panel. */
	public FrameIntegracion frameIntegracion;
	
	/** Texto que indica si el modelo ecosit�mico tiene resultados. */
	private JLabel textoEstadoEcosistema;
	
	/** Texto que indica si el modelo econ�mico tiene resultados. */
	private JLabel textoEstadoEconomia;
	
	/** Texto que indica si el modelo operativo tiene resultados. */
	private JLabel textoEstadoOperacion;
	
	/** Combo box que contiene los tipos de heur�sticas. */
	private JComboBox comboTipoHeuristica;
	
	/** Combo box que contiene los tipos redes neuronales artificiales. */
	private JComboBox comboTipoRedNeuronal;
	
	/** Cuadro de texto que contiene el stock acumulado. */
	private CampoNumerico campoStockAcumulado;
	
	/** Bot�n para iniciar la planificaci�n de ventas. */
	private JButton botonPlanificar;
	
	/**
	 * M�todo constructor en donde se crean e inicializan los componentes GUI
	 * que contiene este panel.
	 *
	 * @param frameIntegracion Un objeto que hace referencia al
	 *                         FrameIntegracion que contiene a este panel.
	 */
	public PanelPlanificarIntegracion(FrameIntegracion frameIntegracion)
	{
		// Inicializar el puntero.
		this.frameIntegracion = frameIntegracion;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * M�todo en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este m�todo corresponden a los atributos
	 * que se derivan de la clase JPanel. En particular, este panel no configura
	 * sus atributos derivados.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * M�todo que configura los componentes GUI que tiene este panel. Este panel
     * contiene dos paneles de configuraci�n: los resultados de los modelos y la
     * planificaci�n de ventas. A su vez, cada panel contiene diferentes
     * componentes GUI.
     */
	public void configurarComponentes()
	{
		// Crear el bot�n.
		botonPlanificar = new JButton("Planificar Ventas",
			new ImageIcon("../img/planificar_ventas.gif"));
		
		// Posicionar y dimensionar.
		botonPlanificar.setBounds(315, 510, 170, 30);
		
		// Asignar key-event.
		botonPlanificar.setMnemonic(KeyEvent.VK_P);
		
		// Asignar tool tips.
		botonPlanificar.setToolTipText("Ejecutar la planificaci�n de ventas " +
									   "de la integraci�n");
		
		// Agregar los componentes.
		add(botonPlanificar);
		
		// Configurar los paneles.
		configurarResultados();
		configurarPlanificacion();
	}
	
	/**
     * M�todo que configura los componentes GUI del panel de resultados de los
     * modelos. En este panel se muestran los textos de los modelos. Los textos
     * indican si los modelos tiene o no resultados.
     */
	private void configurarResultados()
	{
		// Crear el panel.
		JPanel resultados = new JPanel(null);
		
		// Crear el borde del panel.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo del panel.
		TitledBorder titulo = new TitledBorder(borde,
			" Resultados de los Modelos ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// Crear los textos.
		JLabel[] textoExplicacion = new JLabel[4];
		textoExplicacion[0] =
			new JLabel(new ImageIcon("../img/integracion_resultados_modelos" +
			".gif"));
		textoExplicacion[1] =
			new JLabel("Para realizar la integraci�n es necesario que Ud. " +
			"haya guardado los resultados generados por los modelos " +
			"ecosist�mico,");
		textoExplicacion[2] =
			new JLabel("econ�mico y operativo. A continuaci�n se muestran " +
			"los estados de los modelos. Para aquellos modelos en los que no " +
			"se");
		textoExplicacion[3] =
			new JLabel("tienen resultados, deber� ejecutarlos y luego guardar " +
			"sus resultados.");
		JLabel textoEcosistema = new JLabel("Ecosistema:");
		JLabel textoEconomia = new JLabel("Econom�a:");
		JLabel textoOperacion = new JLabel("Operaci�n:");
		textoEstadoEcosistema = new JLabel();
		textoEstadoEconomia = new JLabel();
		textoEstadoOperacion = new JLabel();
		
		// Cargar los componentes.
		textoEstadoEcosistema.setHorizontalAlignment(JLabel.LEFT);
		textoEstadoEconomia.setHorizontalAlignment(JLabel.LEFT);
		textoEstadoOperacion.setHorizontalAlignment(JLabel.LEFT);
		
		// Configurar el panel.
		resultados.setBounds(10, 15, 800, 210);
		resultados.setBorder(titulo);
		
		// Posicionar los componentes.
		textoExplicacion[0].setBounds(20, 25, 50, 50);
		textoExplicacion[1].setBounds(80, 25, 760, 15);
		textoExplicacion[2].setBounds(80, 40, 760, 15);
		textoExplicacion[3].setBounds(80, 55, 760, 15);
		textoEcosistema.setBounds(80, 90, 90, 20);
		textoEstadoEcosistema.setBounds(170, 90, 200, 20);
		textoEconomia.setBounds(80, 120, 90, 20);
		textoEstadoEconomia.setBounds(170, 120, 200, 20);
		textoOperacion.setBounds(80, 150, 90, 20);
		textoEstadoOperacion.setBounds(170, 150, 200, 20);
		
		// Agregar al panel.
		resultados.add(textoExplicacion[0]);
		resultados.add(textoExplicacion[1]);
		resultados.add(textoExplicacion[2]);
		resultados.add(textoExplicacion[3]);
		resultados.add(textoEcosistema);
		resultados.add(textoEstadoEcosistema);
		resultados.add(textoEconomia);
		resultados.add(textoEstadoEconomia);
		resultados.add(textoOperacion);
		resultados.add(textoEstadoOperacion);
		
		// Agregando este panel.
		add(resultados);
	}
	
	/**
     * M�todo que configura los componentes GUI del panel de planificaci�n de
     * ventas. En este panel el usuario debe ingresar el stock acumulado y el
     * tipo de red neuronal artificial de predicci�n econ�mica.
     */
	private void configurarPlanificacion()
	{
		// Crear el panel.
		JPanel planificacion = new JPanel(null);
		
		// Crear el borde del panel.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo del panel.
		TitledBorder titulo = new TitledBorder(borde,
			" Planificaci�n de Ventas ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// Crear los textos.
		JLabel[] textoExplicacion = new JLabel[4];
		textoExplicacion[0] =
			new JLabel(new ImageIcon("../img/integracion_planificacion_ventas" +
			".gif"));
		textoExplicacion[1] =
			new JLabel("Puede ingresar los datos b�sicos para realizar " +
			"una planificaci�n de ventas. Seleccione el tipo de heur�stica " +
			"de venta, el tipo");
		textoExplicacion[2] =
			new JLabel("de red neuronal artificial que desea utilizar para " +
			"predecir la demanda y el precio e ingrese la cantidad de stock " +
			"acumulado en");
		textoExplicacion[3] =
			new JLabel("bodega del recurso en estudio.");
		JLabel textoTipoHeuristica = new JLabel("Tipo de Heur�stica:");
		JLabel textoTipoRedNeuronal = new JLabel("Tipo de Red Neuronal:");
		JLabel textoStockAcumulado = new JLabel("Stock Acumulado:");
		JLabel textoRecomendacion = new JLabel("Se aconseja seguir la "+
			"recomendaci�n dada para cada item.",
			new ImageIcon("../img/recomendacion.gif"), JLabel.LEFT);
		JLabel recomendacionTipoHeuristica = new JLabel(
			"Vender a Mayor Precio",
			new ImageIcon("../img/recomendacion.gif"),
			SwingConstants.LEFT);
		JLabel recomendacionTipoRedNeuronal = new JLabel(
			"Back-Propagation (BP)",
			new ImageIcon("../img/recomendacion.gif"),
			SwingConstants.LEFT);
		JLabel recomendacionStockAcumulado = new JLabel(
			"Toneladas",
			new ImageIcon("../img/recomendacion.gif"),
			SwingConstants.LEFT);
		
		// Crear los combo box.
		comboTipoHeuristica = new JComboBox();
		comboTipoRedNeuronal = new JComboBox();
		
		// Crear el campo de texto.
		campoStockAcumulado = new CampoNumerico(0.0);
		
		// Crear la fuente para la recomendaci�n.
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// Cargar los combo box.
		cargarComboTipoHeuristica();
		cargarComboTipoRedNeuronal();
		
		// Asignar la fuente a la recomendaci�n.
		recomendacionTipoHeuristica.setFont(fuenteRecomendacion);
		recomendacionTipoRedNeuronal.setFont(fuenteRecomendacion);
		recomendacionStockAcumulado.setFont(fuenteRecomendacion);
		
		// Configurar el panel.
		planificacion.setBounds(10, 240, 800, 250);
		planificacion.setBorder(titulo);
		
		// Posicionar los componentes.
		textoExplicacion[0].setBounds(20, 25, 50, 70);
		textoExplicacion[1].setBounds(80, 25, 760, 15);
		textoExplicacion[2].setBounds(80, 40, 760, 15);
		textoExplicacion[3].setBounds(80, 55, 760, 15);
		textoTipoHeuristica.setBounds(80, 95, 150, 20);
		comboTipoHeuristica.setBounds(230, 95, 190, 20);
		recomendacionTipoHeuristica.setBounds(440, 95, 150, 20);
		textoTipoRedNeuronal.setBounds(80, 125, 150, 20);
		comboTipoRedNeuronal.setBounds(230, 125, 190, 20);
		recomendacionTipoRedNeuronal.setBounds(440, 125, 150, 20);
		textoStockAcumulado.setBounds(80, 155, 150, 20);
		campoStockAcumulado.setBounds(230, 155, 190, 20);
		recomendacionStockAcumulado.setBounds(440, 155, 150, 20);
		textoRecomendacion.setBounds(10, 220, 400, 30);
		
		// Asignar el tool tips.
		comboTipoHeuristica.setToolTipText(
			"Seleccione el tipo de heur�stica que desea utilizar");
		comboTipoRedNeuronal.setToolTipText(
			"Seleccione el tipo de red neuronal artificial que desea utilizar");
		campoStockAcumulado.setToolTipText(
			"Ingrese la cantidad de stock acumulado en bodega del recurso");
		
		// Agregar al panel.
		planificacion.add(textoExplicacion[0]);
		planificacion.add(textoExplicacion[1]);
		planificacion.add(textoExplicacion[2]);
		planificacion.add(textoExplicacion[3]);
		planificacion.add(textoTipoHeuristica);
		planificacion.add(comboTipoHeuristica);
		planificacion.add(recomendacionTipoHeuristica);
		planificacion.add(textoTipoRedNeuronal);
		planificacion.add(comboTipoRedNeuronal);
		planificacion.add(recomendacionTipoRedNeuronal);
		planificacion.add(textoStockAcumulado);
		planificacion.add(campoStockAcumulado);
		planificacion.add(recomendacionStockAcumulado);
		planificacion.add(textoRecomendacion);
		
		// Agregando a este panel.
		add(planificacion);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los botones que tiene este
	 * panel. En particular se incorpora el escuchador de eventos del tipo
	 * EventoButton a los JButton que tiene este panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos al bot�n.
		botonPlanificar.addActionListener(eventoButton);
	}
	
	/**
     * M�todo que carga el texto del estado del modelo ecosist�mico. Se
     * verifica si el modelo ecosist�mico tiene o no resultados. En caso
     * afirmativo, el texto se establece en "con resultados". En caso contrario,
     * el texto se establece en "sin resultados".
     */
	public void cargarTextoEcosistema()
	{
		// Cuando hay resultados ecosist�micos.
		if (Proyecto.hayResultados("dato_ecosistemico_resultado"))
		{
			textoEstadoEcosistema.setText("Con resultados.");
			textoEstadoEcosistema.setIcon(new ImageIcon("../img/establecer.gif"));
		}
		
		// Cuando no hay resultados ecosist�micos.
		else
		{
			textoEstadoEcosistema.setText("Sin resultados.");
			textoEstadoEcosistema.setIcon(new ImageIcon("../img/cancelar.gif"));
		}
	}
	
	/**
     * M�todo que carga el texto del estado del modelo econ�mico. Se verifica si
     * el modelo econ�mico tiene o no resultados. En caso afirmativo, el texto
     * se establece en "con resultados". En caso contrario, el texto se
     * establece en "sin resultados".
     */
	public void cargarTextoEconomia()
	{
		// Cuando hay resultados econ�micos.
		if (Proyecto.hayResultados("dato_economico_resultado"))
		{
			textoEstadoEconomia.setText("Con resultados.");
			textoEstadoEconomia.setIcon(new ImageIcon("../img/establecer.gif"));
		}
		
		// Cuando no hay resultados econ�micos.
		else
		{
			textoEstadoEconomia.setText("Sin resultados.");
			textoEstadoEconomia.setIcon(new ImageIcon("../img/cancelar.gif"));
		}
	}
	
	/**
     * M�todo que carga el texto del estado del modelo operativo. Se verifica si
     * el modelo operativo tiene o no resultados. En caso afirmativo, el texto
     * se establece en "con resultados". En caso contrario, el texto se
     * establece en "sin resultados".
     */
	public void cargarTextoOperacion()
	{
		// Cuando hay resultados operativos.
		if (Proyecto.hayResultados("dato_operativo_resultado"))
		{
			textoEstadoOperacion.setText("Con resultados.");
			textoEstadoOperacion.setIcon(new ImageIcon("../img/establecer.gif"));
		}
		
		// Cuando no hay resultados operativos.
		else
		{
			textoEstadoOperacion.setText("Sin resultados.");
			textoEstadoOperacion.setIcon(new ImageIcon("../img/cancelar.gif"));
		}
	}
	
	/**
     * M�todo que carga el combo del tipo de heur�stica del panel planificar de
     * integraci�n.
     */
	private void cargarComboTipoHeuristica()
	{
		BaseDatoTablaBasica tablaTipoHeuristica =
			new BaseDatoTablaBasica(Proyecto.CONEXION, "tipo_heuristica");
		
		Proyecto.CONEXION.conectar();
		ResultSet resultado = tablaTipoHeuristica.buscarCondicion("");
		
		try
		{
			comboTipoHeuristica.removeAllItems();
			resultado.first();
			do
			{
				comboTipoHeuristica.addItem(
					resultado.getString("descripcion_tipo_heuristica"));
				resultado.next();
			}
			while (!resultado.isAfterLast());
			
			comboTipoHeuristica.setSelectedIndex(
				ConfiguracionHeuristica.obtenerHeuristica());
		}
		catch (SQLException ex)
		{
			System.out.println("Error al cargar la tabla tipo_heuristica.");
		}
		
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * M�todo que carga el combo del tipo de red neuronal del panel
     * planificar de integraci�n.
     */
	private void cargarComboTipoRedNeuronal()
	{
		BaseDatoTablaBasica tablaTipoRedNeuronal =
			new BaseDatoTablaBasica(Proyecto.CONEXION, "tipo_red_neuronal");
		
		Proyecto.CONEXION.conectar();
		ResultSet resultado = tablaTipoRedNeuronal.buscarCondicion("");
		
		try
		{
			comboTipoRedNeuronal.removeAllItems();
			resultado.first();
			do
			{
				comboTipoRedNeuronal.addItem(
					resultado.getString("descripcion_tipo_red_neuronal"));
				resultado.next();
			}
			while (!resultado.isAfterLast());
			
			comboTipoRedNeuronal.setSelectedIndex(
				ConfiguracionHeuristica.obtenerRedNeuronal());
		}
		catch (SQLException ex)
		{
			System.out.println("Error al cargar la tabla tipo_red_neuronal.");
		}
		
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo que carga la configuraci�n en el panel. Se obtiene los datos de la
	 * configuraci�n desde la clase est�tica ConfiguracionHeuristica.
	 */
	public void cargarConfiguracion()
	{
		// Establecer la configuraci�n del aut�mata celular.
		comboTipoHeuristica.setSelectedIndex(
			ConfiguracionHeuristica.obtenerHeuristica());
		comboTipoRedNeuronal.setSelectedIndex(
			ConfiguracionHeuristica.obtenerRedNeuronal());
		campoStockAcumulado.setValue(
			ConfiguracionHeuristica.obtenerStockAcumulado());
	}
	
	/**
	 * M�todo donde se establece la configuraci�n por defecto. Se eligen y
	 * setean los valores de los componentes GUI que tiene el panel de
	 * planificar con los valores por defecto. Finalmente, se debe notificar
	 * los cambios efectuados en el proyecto. Este m�todo no se implementa,
	 * porque el panel no tiene la opci�n de restaurar la configuraci�n.
	 */
	public void restaurarConfiguracion()
	{
	}
	
	/**
	 * M�todo que establece los valores de la configuraci�n aplicada por el
	 * usuario. En este m�todo se cambian los valores de los atributos de la
	 * clase est�tica ConfiguracionHeuristica. Luego se notifican los cambios
	 * efectuados en el proyecto.
	 */
	public void establecerConfiguracion()
	{
		// Establecer la configuraci�n de la heur�stica.
		ConfiguracionHeuristica.establecerHeuristica(
			comboTipoHeuristica.getSelectedIndex());
		ConfiguracionHeuristica.establecerRedNeuronal(
			comboTipoRedNeuronal.getSelectedIndex());
		ConfiguracionHeuristica.establecerStockAcumulado(
			campoStockAcumulado.getValueDouble());
		
		// Cambiar el proyecto.
		Proyecto.establecerReporteIntegracion(null);
		Proyecto.establecerModificado(true);
	}
	
	/**
     * M�todo que verifica si los datos ingresados en la planificaci�n de ventas
     * tienen una sintaxis correcta.
     *
     * @return error Retorna un string con el error cometido.
     */
	public String validarConfiguracion()
	{
		String error = "";
		
		// Validar el tipo de red neuronal BP.
		if (comboTipoRedNeuronal.getSelectedIndex() == 0 &&
			!Proyecto.hayResultadosEconomicos(0))
			error += "- La red neuronal BP no tiene resultados.\n";
		
		// Validar el tipo de red neuronal RBF.
		if (comboTipoRedNeuronal.getSelectedIndex() == 1 &&
			!Proyecto.hayResultadosEconomicos(1))
			error += "- La red neuronal RBF no tiene resultados.\n";
		
		// Validar el campo stock acumulado.
		if (!Servicio.esNumero(campoStockAcumulado.getText()) ||
			campoStockAcumulado.getValueDouble() < 0)
			error += "- En el campo stock acumulado.\n";
		
		return error;
	}
	
	/**
	 * M�todo que habilitar o deshabilita los botones del panel. Espec�ficamente
	 * se establece la propiedad de habilitaci�n de los botones, dependiendo del
	 * estado actual en que se encuentra la simulaci�n.
	 */
	public void habilitarBotones()
	{
		// Dependiendo del estado de la planificaci�n, deshabilitar los botones.
		switch (frameIntegracion.obtenerEstado())
		{
			// Proceso en segundo plano iniciado.
			case ProcesoSegundoPlano.INICIADO:
			habilitarBotones(false);
			break;
			
			// Proceso en segundo plano detenido.
			case ProcesoSegundoPlano.DETENIDO:
			habilitarBotones(true);
			break;
		}
	}
	
	/**
     * M�todo donde se maneja la habilitaci�n o deshabilitaci�n del bot�n de
     * planificar las ventas.
     *
     * @param habilitado Inidica si el bot�n se habilita o deshabilita.
     */
	public void habilitarBotones(boolean habilitado)
	{
		botonPlanificar.setEnabled(habilitado);
	}
	
	/**
	 * M�todo que obtiene el objeto botonPlanificar.
	 *
	 * @return botonPlanificar El bot�n planificar del panel planificar.
	 */
	public JButton obtenerBotonPlanificar()
	{
		return botonPlanificar;
	}
	
	/**
	 * M�todo que obtiene el valor como double del campo de texto de stock
	 * acumulado en bodega.
	 *
	 * @return campoStock El valor que contiene el campo de texto de stock
	 *                    acumulado en bodega.
	 */
	public double obtenerStockAcumulado()
	{
		return campoStockAcumulado.getValueDouble();
	}
}