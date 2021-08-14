/**
 * @(#)PanelConfiguracionOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario
 * las opciones de configuración para el modelo de operación del recurso en
 * estudio.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see KeyEvent
 * @see JPanel
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see JOptionPane
 * @see FrameOperacion
 * @see SubPanelConfiguracionAGT
 * @see SubPanelConfiguracionAGR
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class PanelConfiguracionOperacion extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{	
	/** Frame que se hace referncia al creador de este objeto. */
	public FrameOperacion frameOperacion;
	
	/** Botón que restaura la configuración por defecto. */
	private JButton botonRestaurar;
	
	/** Botón que establece la configuración del algoritmo genético. */
	private JButton botonEstablecer;
	
	/**
	 * Panel que contiene la configuración del algoritmo genético de transporte.
	 */
	public SubPanelConfiguracionAGT subpanelConfiguracionAGT;
	
	/** Panel que contiene la configuración del algoritmo genético de ruteo. */
	public SubPanelConfiguracionAGR subpanelConfiguracionAGR;
	
	/**
	 * Método constructor en donde se crean e inicializan los componentes GUI
	 * que contiene este panel.
	 *
	 * @param frameOperacion Un objeto que hace referencia al FrameOperacion que
	 *                       contiene a este panel.
	 */
	public PanelConfiguracionOperacion(FrameOperacion frameOperacion)
	{
		// Inicializar el puntero.
		this.frameOperacion = frameOperacion;
		
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
		setLayout(null);
	}
	
	/**
     * Método que configura el panel configuración del modelo operativo y todos
     * sus componentes GUI.
     */
	public void configurarComponentes()
	{
		JLabel informacion =
			new JLabel("Se aconseja seguir la recomendación dada para cada item.",
			new ImageIcon("../img/recomendacion.gif"),JLabel.LEFT);
		
		// paneles de configuración de los algoritmos genéticos
		subpanelConfiguracionAGT =
			new SubPanelConfiguracionAGT(this,10,15,800,275);
		subpanelConfiguracionAGR =
			new SubPanelConfiguracionAGR(this,10,300,800,220);
		
		// botones del panel de configuración
		botonRestaurar = new JButton("Restaurar",
			new ImageIcon("../img/restaurar.gif"));
		botonEstablecer = new JButton("Establecer",
			new ImageIcon("../img/establecer.gif"));
		
		// posicionando
		informacion.setBounds(10, 525, 400, 30);
		botonRestaurar.setBounds(530, 525, 135, 30);
		botonEstablecer.setBounds(675, 525, 135, 30);
		
		// asignando eventos de teclas a los botones
		botonEstablecer.setMnemonic(KeyEvent.VK_E);
		botonRestaurar.setMnemonic(KeyEvent.VK_R);
		
		// asignado tips
		botonEstablecer.setToolTipText(
			"Establecer los cambios en la configuración de la operación");
		botonRestaurar.setToolTipText(
			"Restaurar la configuración por defecto de la operación");
		
		// agregando al panel
		add(subpanelConfiguracionAGT);
		add(subpanelConfiguracionAGR);
		add(informacion);
		add(botonRestaurar);
		add(botonEstablecer);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones que tiene el 
	 * panel de configuracion. En particular se incorpora el escuchador de
	 * eventos del tipo EventoButton a los JButton que tiene el panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonRestaurar.addActionListener(eventoButton);
		botonEstablecer.addActionListener(eventoButton);
	}
	
	/**
     * Método que carga una configuración de los algoritmos genéticos asociados
     * a un proyecto. Cuando se carga la configuración, esta se puede visualizar
     * en el panel.
     */
	public void cargarConfiguracion()
	{
	}
	
	/**
     * Método que restaura los valores por defecto de los algoritmos genético.
     */
	public void restaurarConfiguracion()
	{
		int opcion = JOptionPane.showConfirmDialog(this,
					"Se borrarán todos los resultados operativos generados "+
					"anteriormente para esta configuración.\nDesea continuar?",
					"CIRCANA Pro 2.0",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// Cuando el usuario acepta el cambio de configuración.
		if (opcion == JOptionPane.YES_OPTION)
		{
			// Restaurar las configuraciones.
			subpanelConfiguracionAGT.restaurarConfiguracion();
			subpanelConfiguracionAGR.restaurarConfiguracion();
			
			// Cambiar el proyecto.
			Proyecto.limpiarResultados(Proyecto.OPERACION, -1);
			Proyecto.establecerReporteOperacion(null);
			Proyecto.establecerModificado(true);
			
			// Limpiar los componentes.
			frameOperacion.iniciarAlgoritmoGenetico();
			frameOperacion.panelReporte.limpiarReporte();
			frameOperacion.panelEscritorio.frameIntegracion.
				panelPlanificarIntegracion.cargarTextoOperacion();
		}
	}
	
	/**
     * Método que establece los valores de configuración del algoritmo
     * genético.
     */	
	public void establecerConfiguracion()
	{
		int opcion = JOptionPane.showConfirmDialog(this,
					"Se borrarán todos los resultados operativos generados "+
					"anteriormente para esta configuración.\nDesea continuar?",
					"CIRCANA Pro 2.0",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// Cuando el usuario acepta el cambio de configuración.
		if (opcion == JOptionPane.YES_OPTION)
		{
			// Establecer las configuraciones.
			subpanelConfiguracionAGT.establecerConfiguracion();
			subpanelConfiguracionAGR.establecerConfiguracion();
			
			// Cambiar el proyecto.
			Proyecto.limpiarResultados(Proyecto.OPERACION, -1);
			Proyecto.establecerReporteOperacion(null);
			Proyecto.establecerModificado(true);
			
			// Limpiar los componentes.
			frameOperacion.iniciarAlgoritmoGenetico();
			frameOperacion.panelReporte.limpiarReporte();
			frameOperacion.panelEscritorio.frameIntegracion.
				panelPlanificarIntegracion.cargarTextoOperacion();
		}
	}
	
	/**
     * Método que valida la configuración de los algoritmos genéticos. La
     * configuración ha cambiar es personalizada por el usuario. En particular,
     * este método no se implementa, ya que lo maneja el sub-panel respectivo
     * del algoritmo genético.
     */
    public String validarConfiguracion()
    {
    	return "";
    }
	
	/**
	 * Metodo que maneja la habilitacion/deshabilitacion de los botones del 
	 * panel de configuración. Generalmente se bloquean cuando los algoritmos
	 * genéticos se encuentran en ejecución.
	 * 
	 * @param habilitado Indica si el boton se habilita o deshabilita.
	 */
	public void habilitarBotones(boolean habilitado)
	{
		botonRestaurar.setEnabled(habilitado);
		botonEstablecer.setEnabled(habilitado);
	}
    
	/**
     * Método que retorna el boton restaurar.
     *
     * @return botonRestaurar Boton restaurar del panel Configuración.
     */
	public JButton obtenerBotonRestaurar()
	{
		return botonRestaurar;
	}	

	/**
     * Método que retorna el boton establecer.
     *
     * @return botonEstablecer Boton establecer del panel Configuración.
     */
	public JButton obtenerBotonEstablecer()
	{
		return botonEstablecer;
	}
}