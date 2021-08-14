/*
 * @(#)PanelConfiguracionEconomia.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
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
	
	/** Bot�n que cambia la configuracion de la red. */
	private JButton botonEstablecer;
	
	/** Bot�n que vuelve la configuraci�n por defecto. */
	private JButton botonRestaurar;
	
	/** Panel que contiene la configuraci�n de la red RBF. */
	public SubPanelConfiguracionRBF subPanelConfiguracionRBF;
	
	/** Panel que contiene la configuraci�n de la red BP. */
	public SubPanelConfiguracionBP subPanelConfiguracionBP;
	
	/** 
	 * Constructor de la clase PanelEntrenarEconomia. Donde se configura los 
	 * botones, los eventos. Recibe como par�metro el creador del objeto.
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
	 * M�todo en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este m�todo corresponden a los atributos
	 * que se derivan de la clase JPanel.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * M�todo que cconfigura los botones que se encuentra en panel de entrenar.
     * Consta de 2 tipos: Uno para la red BP y otra para la red RBF.
     */
	public void configurarComponentes()
	{
		JLabel informacion =
			new JLabel("Se aconseja seguir la recomendaci�n dada para cada item.",
			new ImageIcon("../img/recomendacion.gif"),JLabel.LEFT);
		
		// paneles que contiene la configuraci�n
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
			"Establecer los cambios en la configuraci�n de la econom�a");
		botonRestaurar.setToolTipText(
			"Restaurar la configuraci�n por defecto de la econom�a");
		
		// agregando al panel
		add(subPanelConfiguracionBP);
		add(subPanelConfiguracionRBF);
		add(informacion);
		add(botonRestaurar);
		add(botonEstablecer);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los botones de acceso
	 * r�pido que tiene el panel de configuraci�n. En particular se incorpora el 
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
     * M�todo que carga el panel con la configuraci�n de la red neuronal elegida
     * en el panel entrennar de econom�a.
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
     * M�todo que carga una configuraci�n de una red neuronal asociada a un
     * proyecto. Cuando se carga la configuraci�n, esta se puede visualizar en
     * el panel.
     */
	public void cargarConfiguracion()
	{
		// cargar la configuraci�n
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
     * M�todo que carga una configuraci�n de una red neuronal asociada a un tipo
     * de red neuronal y un proyecto. Cuando se carga la configuraci�n, esta se
     * puede visualizar en el panel.
     *
     * @param eleccionRed El tipo de red neuronal.
     */
	public void cargarConfiguracion(int eleccionRed)
	{
		// cargar la configuraci�n
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
     * M�todo que configura de la red elegida a una configuraci�n por defecto
     * del proyecto. Cuando se carga la configuraci�n, esta se puede visualizar
     * en el panel.  Antes de restaurar la configuraci�n se pregunta al usuario
     * si realmente desea realizar la operaci�n.
     */
	public void restaurarConfiguracion()
	{
		// restaurar la configuraci�n
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
     * M�todo que cambia la configuraci�n de una red neuronal. La configuraci�n
     * ha cambiar es personalizada por el usuario.
     */	
    public void establecerConfiguracion()
    {
		// establecer la configuraci�n
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
     * M�todo que valida la configuraci�n de una red neuronal. La configuraci�n
     * ha cambiar es personalizada por el usuario. En particular, este m�todo no
     * se implementa, ya que lo maneja el sub-panel respectivo de la red.
     */	
    public String validarConfiguracion()
    {
    	return "";
    }
    
    /**
     * M�todo que maneja la habilitaci�n de los botones principales de panel de
     * configuraci�n ("Restaurar" y "Establecer"). M�todo ocupado generalmente
     * cuando la red est� entrenando.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
    public void habilitarBotones(boolean habilitado)
	{
		botonEstablecer.setEnabled(habilitado);
		botonRestaurar.setEnabled(habilitado);
	}
	
	/**
     * M�todo que retorna el bot�n restaurar.
     *
     * @return botonRestaurar Bot�n restaurar del panel configuraci�n.
     */
	public JButton obtenerBotonRestaurar()
	{
		return botonRestaurar;
	}
	
	/**
     * M�todo que retorna el bot�n establecer.
     *
     * @return botonRestaurar Bot�n establecer del panel configuraci�n.
     */
	public JButton obtenerbotonEstablecer()
	{
		return botonEstablecer;
	}
}