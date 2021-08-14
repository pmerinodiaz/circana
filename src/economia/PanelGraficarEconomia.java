/**
 * @(#)PanelGraficarEconomia.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
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
 * precios y demanda en un año determinado. En grafica esta basada en la red
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
	/** Frame creador de la lista de panel de economía. */
	public FrameEconomia frameEconomia;
	
	/** Panel en cual se grafican las curvas. */
	public SubPanelGraficarEconomia subPanelGraficarEconomia;
	
	/** Botón que atrasa un año en la predicción. */
	private  JButton botonAnterior;
	
	/** Botón que adelanta un año en la predicción. */
	private  JButton botonSiguiente;
	
	/** Texto que muestra el tiempo de predicción. */
	private JLabel textoPrediccion;
	
	/**
	 * Constructor de la clase PanelGraficarEconomia. Invoca a los métodos que
	 * configuran el panel de estado, sus componentes y los eventos asociados.
	 *
	 * @param frameEconomia Objeto que hace referencia al frame economía.
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
	 * Método en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este método corresponden a los atributos
	 * que se derivan de la clase JPanel.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * Método que configura los componentes GUI del panel.
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
			"Gráfico de la Demanda y el Precio");
		textoPrediccion = new JLabel(
			"Año "+(Proyecto.obtenerFechaInicial())[2]);
		
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
		botonAnterior.setToolTipText("Ver curvas del año anterior");
		botonSiguiente.setToolTipText("Ver curvas del año siguiente");
		
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
	 * Método que adjunta los escuchadores eventos a los botones de acceso
	 * rápido que tiene el panel de graficar. En particular se incorpora el 
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
     * Método que actualiza el panel graficar economía cuando se ha actualizado
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
     * Método que cambia el año de predicción de la curva ha graficar. Además
     * actualiza los botones que seleccionan los años de predicción para
     * deshabiltarlos si llegaron a un borde de la predicción.
     * 
     * @param anoPrediccion Contiene el año actual que se esta predicciendo.
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
		
		textoPrediccion.setText("Año " + anoPrediccion);
		subPanelGraficarEconomia.repaint();
	}
	
	/**
	 * Método que obtiene el objeto botonAnterior.
	 *
	 * @return botonAnterior El botón anterior del panel graficar.
	 */
	public JButton obtenerBotonAnterior()
	{
		return botonAnterior;
	}
	
	/**
	 * Método que obtiene el objeto botonSiguiente.
	 *
	 * @return botonSiguiente El botón siguiente del panel graficar.
	 */
	public JButton obtenerBotonSiguiente()
	{
		return botonSiguiente;
	}
}