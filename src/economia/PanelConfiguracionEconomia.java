/*
 * @(#)PanelConfiguracionEconomia.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JPanel. En esta clase se permite configurar
 * la red elegida en el panel entrenar. La clase carga sub-paneles para cada
 * tipo de red. 
 *  
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see KeyEvent
 * @see JPanel
 * @see JLabel
 * @see JButton
 * @see ImageIcon
 * @see FrameEconomia
 * @see SubPanelConfiguracionBP
 * @see SubPanelConfiguracionRBF
 * @see EventoButton
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class PanelConfiguracionEconomia extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** Frame que se hace referencia al creador de este objeto. */
	public FrameEconomia frameEconomia; 
	
	/** Botón que cambia la configuracion de la red. */
	private JButton botonEstablecer;
	
	/** Botón que vuelve la configuración por defecto. */
	private JButton botonRestaurar;
	
	/** Panel que contiene la configuración de la red RBF. */
	public SubPanelConfiguracionRBF subPanelConfiguracionRBF;
	
	/** Panel que contiene la configuración de la red BP. */
	public SubPanelConfiguracionBP subPanelConfiguracionBP;
	
	/** 
	 * Constructor de la clase PanelEntrenarEconomia. Donde se configura los 
	 * botones, los eventos. Recibe como parámetro el creador del objeto.
	 * 
	 * @param frameEconomia Creador del Panel List que contiene Entrenar.
	 */
	public PanelConfiguracionEconomia(FrameEconomia frameEconomia)
	{
		super(null);
		
		// Inicializar el puntero.
		this.frameEconomia = frameEconomia;
		
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
     * Método que cconfigura los botones que se encuentra en panel de entrenar.
     * Consta de 2 tipos: Uno para la red BP y otra para la red RBF.
     */
	public void configurarComponentes()
	{
		JLabel informacion =
			new JLabel("Se aconseja seguir la recomendación dada para cada item.",
			new ImageIcon("../img/recomendacion.gif"),JLabel.LEFT);
		
		// paneles que contiene la configuración
		subPanelConfiguracionBP = new SubPanelConfiguracionBP(10,15,800,505);
		subPanelConfiguracionRBF = new SubPanelConfiguracionRBF(10,15,800,505);
		
		// botones
		botonEstablecer = new JButton("Establecer",
			new ImageIcon("../img/establecer.gif"));
		botonRestaurar = new JButton("Restaurar",
			new ImageIcon("../img/restaurar.gif"));
		
		// posicionando
		informacion.setBounds(10, 525, 400, 30);
		botonRestaurar.setBounds(530, 525, 135, 30);
		botonEstablecer.setBounds(675, 525, 135, 30);
		
		// asignando eventos de teclas a los botones
		botonEstablecer.setMnemonic(KeyEvent.VK_E);
		botonRestaurar.setMnemonic(KeyEvent.VK_R);
		
	    // asignando cualidades especiales a los componetes
		subPanelConfiguracionRBF.setVisible(false);
		
		// asignado tips
		botonEstablecer.setToolTipText(
			"Establecer los cambios en la configuración de la economía");
		botonRestaurar.setToolTipText(
			"Restaurar la configuración por defecto de la economía");
		
		// agregando al panel
		add(subPanelConfiguracionBP);
		add(subPanelConfiguracionRBF);
		add(informacion);
		add(botonRestaurar);
		add(botonEstablecer);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones de acceso
	 * rápido que tiene el panel de configuración. En particular se incorpora el 
	 * escuchador de eventos del tipo EventoButton a los JButton que tiene el
	 * panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonEstablecer.addActionListener(eventoButton);
		botonRestaurar.addActionListener(eventoButton);
	}
	
	/**
     * Método que carga el panel con la configuración de la red neuronal elegida
     * en el panel entrennar de economía.
     */
	public void cambiarPanel()
	{
		switch (frameEconomia.obtenerEleccionRed())
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    	{
	    	 	subPanelConfiguracionRBF.setVisible(false);
	    	 	subPanelConfiguracionBP.setVisible(true);
	    	 	break;
	    	}
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    	{
	    		subPanelConfiguracionBP.setVisible(false);
	    		subPanelConfiguracionRBF.setVisible(true);
	    	 	break;
	    	}
	    }
	}
	
	/**
     * Método que carga una configuración de una red neuronal asociada a un
     * proyecto. Cuando se carga la configuración, esta se puede visualizar en
     * el panel.
     */
	public void cargarConfiguracion()
	{
		// cargar la configuración
		switch (frameEconomia.obtenerEleccionRed())
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    	{
	    		subPanelConfiguracionBP.cargarConfiguracion();
	    	   	break;
	    	}
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    	{
	    		subPanelConfiguracionRBF.cargarConfiguracion();
	    	    break;
	    	}
	    }
	}
	
	/**
     * Método que carga una configuración de una red neuronal asociada a un tipo
     * de red neuronal y un proyecto. Cuando se carga la configuración, esta se
     * puede visualizar en el panel.
     *
     * @param eleccionRed El tipo de red neuronal.
     */
	public void cargarConfiguracion(int eleccionRed)
	{
		// cargar la configuración
		switch (eleccionRed)
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    	{
	    		subPanelConfiguracionBP.cargarConfiguracion();
	    	   	break;
	    	}
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    	{
	    		subPanelConfiguracionRBF.cargarConfiguracion();
	    	    break;
	    	}
	    }
	}
	
	/**
     * Método que configura de la red elegida a una configuración por defecto
     * del proyecto. Cuando se carga la configuración, esta se puede visualizar
     * en el panel.  Antes de restaurar la configuración se pregunta al usuario
     * si realmente desea realizar la operación.
     */
	public void restaurarConfiguracion()
	{
		// restaurar la configuración
		switch (frameEconomia.obtenerEleccionRed())
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    	{
	    		subPanelConfiguracionBP.restaurarConfiguracion();
	    		break;
	    	}
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    	{
	    		subPanelConfiguracionRBF.restaurarConfiguracion();
	    		break;
	    	}
	    }
	}
	
	/**
     * Método que cambia la configuración de una red neuronal. La configuración
     * ha cambiar es personalizada por el usuario.
     */	
    public void establecerConfiguracion()
    {
		// establecer la configuración
    	switch (frameEconomia.obtenerEleccionRed())
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    	{
	    		subPanelConfiguracionBP.establecerConfiguracion();
	    	    break;
	    	}
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    	{
	    		subPanelConfiguracionRBF.establecerConfiguracion();
	    	    break;
	    	}
		}
    }
    
	/**
     * Método que valida la configuración de una red neuronal. La configuración
     * ha cambiar es personalizada por el usuario. En particular, este método no
     * se implementa, ya que lo maneja el sub-panel respectivo de la red.
     */	
    public String validarConfiguracion()
    {
    	return "";
    }
    
    /**
     * Método que maneja la habilitación de los botones principales de panel de
     * configuración ("Restaurar" y "Establecer"). Método ocupado generalmente
     * cuando la red está entrenando.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
    public void habilitarBotones(boolean habilitado)
	{
		botonEstablecer.setEnabled(habilitado);
		botonRestaurar.setEnabled(habilitado);
	}
	
	/**
     * Método que retorna el botón restaurar.
     *
     * @return botonRestaurar Botón restaurar del panel configuración.
     */
	public JButton obtenerBotonRestaurar()
	{
		return botonRestaurar;
	}
	
	/**
     * Método que retorna el botón establecer.
     *
     * @return botonRestaurar Botón establecer del panel configuración.
     */
	public JButton obtenerbotonEstablecer()
	{
		return botonEstablecer;
	}
}