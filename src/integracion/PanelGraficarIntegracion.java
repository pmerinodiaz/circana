/**
 * @(#)PanelGraficaIntegracion.java 2.0 01/03/05
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
	/** Frame creador de la lista de panel de integración. */
	public FrameIntegracion frameIntegracion;
	
	/** Panel en cual se grafican las curvas. */
	public SubPanelGraficarIntegracion subPanelGraficarIntegracion;
	
	/** Botón que atrasa un año en la predicción. */
	private  JButton botonAnterior;
	
	/** Botón que adelanta un año en la predicción. */
	private  JButton botonSiguiente;
	
	/** Texto que muestra el tiempo de planificación. */
	private JLabel textoPlanificacion;
	
	/**
	 * Constructor de la clase PanelGraficarIntegracion. Invoca a los métodos
	 * que configuran el panel de estado, sus componentes y los eventos
	 * asociados.
	 *
	 * @param frameIntegracion Objeto que hace referencia al frame integración.
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
		// Mitad del frame.
		int mitadFrame = frameIntegracion.getWidth() / 2;
		
		// Crear el sub-panel.
		subPanelGraficarIntegracion =
			new SubPanelGraficarIntegracion(this,0,40,823,480);
		
		// Crear los textos.
		JLabel textoTitulo =
			new JLabel("Gráfico de la Planificación de Ventas");
		textoPlanificacion =
			new JLabel("Año "+(Proyecto.obtenerFechaInicial())[2]);
		
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
		botonAnterior.setToolTipText("Ver curvas del año anterior");
		botonSiguiente.setToolTipText("Ver curvas del año siguiente");
		
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
     * Método que actualiza el panel graficar de integración cuando se ha
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
     * Método que cambia el año de planificación de las curvas ha graficar.
     * Además actualiza los botones que seleccionan los años de planificación
     * para deshabiltarlos si llegaron a un borde de la planificación.
     * 
     * @param anioPlanoficacion Contiene el año actual que se está planificando.
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
		
		textoPlanificacion.setText("Año " + anioPlanificacion);
		subPanelGraficarIntegracion.repaint();
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