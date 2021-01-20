/**
 * @(#)PanelGraficaIntegracion.java 2.0 01/03/05
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
 * Clase que extiende de la clase JPanel. En esta clase se dibujan las curvas de 
 * oferta, demanda, precio y venta en un periodo de tiempo determinado.
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
 * @see FrameIntegracion
 * @see SubPanelGraficarIntegracion
 * @see EventoBoton
 * @see InterfacePanel
 */
public class PanelGraficarIntegracion extends JPanel implements InterfacePanel
{
	/** Frame creador de la lista de panel de integraci�n. */
	public FrameIntegracion frameIntegracion;
	
	/** Panel en cual se grafican las curvas. */
	public SubPanelGraficarIntegracion subPanelGraficarIntegracion;
	
	/** Bot�n que atrasa un a�o en la predicci�n. */
	private  JButton botonAnterior;
	
	/** Bot�n que adelanta un a�o en la predicci�n. */
	private  JButton botonSiguiente;
	
	/** Texto que muestra el tiempo de planificaci�n. */
	private JLabel textoPlanificacion;
	
	/**
	 * Constructor de la clase PanelGraficarIntegracion. Invoca a los m�todos
	 * que configuran el panel de estado, sus componentes y los eventos
	 * asociados.
	 *
	 * @param frameIntegracion Objeto que hace referencia al frame integraci�n.
	 */
	public PanelGraficarIntegracion(FrameIntegracion frameIntegracion)
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
		// Mitad del frame.
		int mitadFrame = frameIntegracion.getWidth() / 2;
		
		// Crear el sub-panel.
		subPanelGraficarIntegracion =
			new SubPanelGraficarIntegracion(this,0,40,823,480);
		
		// Crear los textos.
		JLabel textoTitulo =
			new JLabel("Gr�fico de la Planificaci�n de Ventas");
		textoPlanificacion =
			new JLabel("A�o "+(Proyecto.obtenerFechaInicial())[2]);
		
		// Crear los botones.
		botonAnterior = new JButton("Anterior",
			new ImageIcon("../img/anterior.gif"));
		botonSiguiente = new JButton("Siguiente",
			new ImageIcon("../img/siguiente.gif"));
		
		// Posicionado los componentes.
		textoTitulo.setBounds(mitadFrame - 110, 10, 220, 20);
		textoPlanificacion.setBounds(mitadFrame - 25, 530, 60, 20);
		botonAnterior.setBounds(mitadFrame - 180, 525, 140, 30);
		botonSiguiente.setBounds(mitadFrame + 40, 525, 140, 30);
		
		// Asignando eventos de teclas a los botones.
		botonAnterior.setMnemonic(KeyEvent.VK_A);
		botonSiguiente.setMnemonic(KeyEvent.VK_S);
		
		// Asignando tool tips.
		botonAnterior.setToolTipText("Ver curvas del a�o anterior");
		botonSiguiente.setToolTipText("Ver curvas del a�o siguiente");
		
		// Configurando elementos.
		botonAnterior.setHorizontalTextPosition(JButton.RIGHT);
		botonSiguiente.setHorizontalTextPosition(JButton.LEFT);
		textoPlanificacion.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		botonAnterior.setEnabled(false);
		
		// Agregando los elementos al panel.
		add(textoTitulo);
		add(subPanelGraficarIntegracion);
		add(botonAnterior);
		add(botonSiguiente);
		add(textoPlanificacion);
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
     * M�todo que actualiza el panel graficar de integraci�n cuando se ha
     * actualizado la informacion del proyecto.
     */
	public void actualizarInformacionProyecto()
	{
		int anioPlanificacion = frameIntegracion.obtenerAnioPlanificacion();
		int anioInicial = Integer.parseInt((Proyecto.obtenerFechaInicial())[2]);
		int anioFinal = Integer.parseInt((Proyecto.obtenerFechaFinal())[2]);
		
		if (anioPlanificacion < anioInicial)
			anioPlanificacion = anioInicial;
		
		if (anioPlanificacion > anioFinal)
			anioPlanificacion = anioFinal;
		
		cambiarAnioPlanificacion(anioPlanificacion);
	}
	
	/**
     * M�todo que cambia el a�o de planificaci�n de las curvas ha graficar.
     * Adem�s actualiza los botones que seleccionan los a�os de planificaci�n
     * para deshabiltarlos si llegaron a un borde de la planificaci�n.
     * 
     * @param anioPlanoficacion Contiene el a�o actual que se est� planificando.
     */
	public void cambiarAnioPlanificacion(int anioPlanificacion)
	{
		int anioInicial = Integer.parseInt((Proyecto.obtenerFechaInicial())[2]);
		int anioFinal = Integer.parseInt((Proyecto.obtenerFechaFinal())[2]);
		
		if (anioInicial == anioPlanificacion)
			botonAnterior.setEnabled(false);
		else
			botonAnterior.setEnabled(true);
		
		if	(anioFinal == anioPlanificacion)
			botonSiguiente.setEnabled(false);
		else
			botonSiguiente.setEnabled(true);
		
		textoPlanificacion.setText("A�o " + anioPlanificacion);
		subPanelGraficarIntegracion.repaint();
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