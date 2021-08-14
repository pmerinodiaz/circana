/**
 * @(#)PanelConfiguracionOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario
 * las opciones de configuraci�n para el modelo de operaci�n del recurso en
 * estudio.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
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
	
	/** Bot�n que restaura la configuraci�n por defecto. */
	private JButton botonRestaurar;
	
	/** Bot�n que establece la configuraci�n del algoritmo gen�tico. */
	private JButton botonEstablecer;
	
	/**
	 * Panel que contiene la configuraci�n del algoritmo gen�tico de transporte.
	 */
	public SubPanelConfiguracionAGT subpanelConfiguracionAGT;
	
	/** Panel que contiene la configuraci�n del algoritmo gen�tico de ruteo. */
	public SubPanelConfiguracionAGR subpanelConfiguracionAGR;
	
	/**
	 * M�todo constructor en donde se crean e inicializan los componentes GUI
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
	 * M�todo en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este m�todo corresponden a los atributos
	 * que se derivan de la clase JPanel.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * M�todo que configura el panel configuraci�n del modelo operativo y todos
     * sus componentes GUI.
     */
	public void configurarComponentes()
	{
		JLabel informacion =
			new JLabel("Se aconseja seguir la recomendaci�n dada para cada item.",
			new ImageIcon("../img/recomendacion.gif"),JLabel.LEFT);
		
		// paneles de configuraci�n de los algoritmos gen�ticos
		subpanelConfiguracionAGT =
			new SubPanelConfiguracionAGT(this,10,15,800,275);
		subpanelConfiguracionAGR =
			new SubPanelConfiguracionAGR(this,10,300,800,220);
		
		// botones del panel de configuraci�n
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
			"Establecer los cambios en la configuraci�n de la operaci�n");
		botonRestaurar.setToolTipText(
			"Restaurar la configuraci�n por defecto de la operaci�n");
		
		// agregando al panel
		add(subpanelConfiguracionAGT);
		add(subpanelConfiguracionAGR);
		add(informacion);
		add(botonRestaurar);
		add(botonEstablecer);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los botones que tiene el 
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
     * M�todo que carga una configuraci�n de los algoritmos gen�ticos asociados
     * a un proyecto. Cuando se carga la configuraci�n, esta se puede visualizar
     * en el panel.
     */
	public void cargarConfiguracion()
	{
	}
	
	/**
     * M�todo que restaura los valores por defecto de los algoritmos gen�tico.
     */
	public void restaurarConfiguracion()
	{
		int opcion = JOptionPane.showConfirmDialog(this,
					"Se borrar�n todos los resultados operativos generados "+
					"anteriormente para esta configuraci�n.\nDesea continuar?",
					"CIRCANA Pro 2.0",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// Cuando el usuario acepta el cambio de configuraci�n.
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
     * M�todo que establece los valores de configuraci�n del algoritmo
     * gen�tico.
     */	
	public void establecerConfiguracion()
	{
		int opcion = JOptionPane.showConfirmDialog(this,
					"Se borrar�n todos los resultados operativos generados "+
					"anteriormente para esta configuraci�n.\nDesea continuar?",
					"CIRCANA Pro 2.0",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// Cuando el usuario acepta el cambio de configuraci�n.
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
     * M�todo que valida la configuraci�n de los algoritmos gen�ticos. La
     * configuraci�n ha cambiar es personalizada por el usuario. En particular,
     * este m�todo no se implementa, ya que lo maneja el sub-panel respectivo
     * del algoritmo gen�tico.
     */
    public String validarConfiguracion()
    {
    	return "";
    }
	
	/**
	 * Metodo que maneja la habilitacion/deshabilitacion de los botones del 
	 * panel de configuraci�n. Generalmente se bloquean cuando los algoritmos
	 * gen�ticos se encuentran en ejecuci�n.
	 * 
	 * @param habilitado Indica si el boton se habilita o deshabilita.
	 */
	public void habilitarBotones(boolean habilitado)
	{
		botonRestaurar.setEnabled(habilitado);
		botonEstablecer.setEnabled(habilitado);
	}
    
	/**
     * M�todo que retorna el boton restaurar.
     *
     * @return botonRestaurar Boton restaurar del panel Configuraci�n.
     */
	public JButton obtenerBotonRestaurar()
	{
		return botonRestaurar;
	}	

	/**
     * M�todo que retorna el boton establecer.
     *
     * @return botonEstablecer Boton establecer del panel Configuraci�n.
     */
	public JButton obtenerBotonEstablecer()
	{
		return botonEstablecer;
	}
}