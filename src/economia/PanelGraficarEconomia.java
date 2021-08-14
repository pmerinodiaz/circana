/**
 * @(#)PanelGraficarEconomia.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se dibuja las curvas de 
 * precios y demanda en un a�o determinado. En grafica esta basada en la red
 * neuronal elegida, aunque no este entrenada
 * 
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Color
 * @see KeyEvent
 * @see JPanel
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameEconomia
 * @see SubPanelGraficarEconomia
 * @see EventoButton
 * @see InterfacePanel
 */
public class PanelGraficarEconomia extends JPanel implements InterfacePanel
{
	/** Frame creador de la lista de panel de econom�a. */
	public FrameEconomia frameEconomia;
	
	/** Panel en cual se grafican las curvas. */
	public SubPanelGraficarEconomia subPanelGraficarEconomia;
	
	/** Bot�n que atrasa un a�o en la predicci�n. */
	private  JButton botonAnterior;
	
	/** Bot�n que adelanta un a�o en la predicci�n. */
	private  JButton botonSiguiente;
	
	/** Texto que muestra el tiempo de predicci�n. */
	private JLabel textoPrediccion;
	
	/**
	 * Constructor de la clase PanelGraficarEconomia. Invoca a los m�todos que
	 * configuran el panel de estado, sus componentes y los eventos asociados.
	 *
	 * @param frameEconomia Objeto que hace referencia al frame econom�a.
	 */
	public PanelGraficarEconomia(FrameEconomia frameEconomia)
	{
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
     * M�todo que configura los componentes GUI del panel.
	 */
	public void configurarComponentes()
	{
		// mitad del frame
		int mitadFrame = frameEconomia.getWidth() / 2;
		
		// crear el sub-panel
		subPanelGraficarEconomia =
			new SubPanelGraficarEconomia(this,0,40,823,480);
		
		// crear textos
		JLabel textoTitulo = new JLabel(
			"Gr�fico de la Demanda y el Precio");
		textoPrediccion = new JLabel(
			"A�o "+(Proyecto.obtenerFechaInicial())[2]);
		
		// botones
		botonAnterior = new JButton("Anterior",
			new ImageIcon("../img/anterior.gif"));
		botonSiguiente = new JButton("Siguiente",
			new ImageIcon("../img/siguiente.gif"));
		
		// posicionado
		textoTitulo.setBounds(mitadFrame-110,10,220,20);
		textoPrediccion.setBounds(mitadFrame-25,530,60,20);
		botonAnterior.setBounds(mitadFrame-180,525,140,30);
		botonSiguiente.setBounds(mitadFrame+40,525,140,30);
		
		// asignando eventos de tipo teclas a los botones
		botonAnterior.setMnemonic(KeyEvent.VK_A);
		botonSiguiente.setMnemonic(KeyEvent.VK_S);
		
		// asignando tips
		botonAnterior.setToolTipText("Ver curvas del a�o anterior");
		botonSiguiente.setToolTipText("Ver curvas del a�o siguiente");
		
		// configurando elementos
		textoPrediccion.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		botonAnterior.setHorizontalTextPosition(JButton.RIGHT);
		botonSiguiente.setHorizontalTextPosition(JButton.LEFT);
		botonAnterior.setEnabled(false);
		
		// agreando elementos al panel
		add(textoTitulo);
		add(subPanelGraficarEconomia);
		add(botonAnterior);
		add(botonSiguiente);
		add(textoPrediccion);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los botones de acceso
	 * r�pido que tiene el panel de graficar. En particular se incorpora el 
	 * escuchador de eventos del tipo EventoButton a los JButton que tiene el
	 * panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonAnterior.addActionListener(eventoButton);
		botonSiguiente.addActionListener(eventoButton);
	}
	
	/**
     * M�todo que actualiza el panel graficar econom�a cuando se ha actualizado
     * la infomacion del proyecto.
     */
	public void actualizarInformacionProyecto()
	{
		int anoPrediccion = frameEconomia.obtenerAnoPrediccion();
		int anoInicial = Integer.parseInt((Proyecto.obtenerFechaInicial())[2]);
		int anoFinal = Integer.parseInt((Proyecto.obtenerFechaFinal())[2]);
		
		if (anoPrediccion < anoInicial)
			anoPrediccion = anoInicial;
		
		if (anoPrediccion > anoFinal)
			anoPrediccion = anoFinal;
		
		cambiarAnoPrediccion(anoPrediccion);
	}
	
	/**
     * M�todo que cambia el a�o de predicci�n de la curva ha graficar. Adem�s
     * actualiza los botones que seleccionan los a�os de predicci�n para
     * deshabiltarlos si llegaron a un borde de la predicci�n.
     * 
     * @param anoPrediccion Contiene el a�o actual que se esta predicciendo.
     */
	public void cambiarAnoPrediccion(int anoPrediccion)
	{
		int anoInicial = Integer.parseInt((Proyecto.obtenerFechaInicial())[2]);
		int anoFinal = Integer.parseInt((Proyecto.obtenerFechaFinal())[2]);
		
		if (anoInicial == anoPrediccion)
			botonAnterior.setEnabled(false);
		else
			botonAnterior.setEnabled(true);
		
		if	(anoFinal == anoPrediccion)
			botonSiguiente.setEnabled(false);
		else
			botonSiguiente.setEnabled(true);
		
		textoPrediccion.setText("A�o " + anoPrediccion);
		subPanelGraficarEconomia.repaint();
	}
	
	/**
	 * M�todo que obtiene el objeto botonAnterior.
	 *
	 * @return botonAnterior El bot�n anterior del panel graficar.
	 */
	public JButton obtenerBotonAnterior()
	{
		return botonAnterior;
	}
	
	/**
	 * M�todo que obtiene el objeto botonSiguiente.
	 *
	 * @return botonSiguiente El bot�n siguiente del panel graficar.
	 */
	public JButton obtenerBotonSiguiente()
	{
		return botonSiguiente;
	}
}