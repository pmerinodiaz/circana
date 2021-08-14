/*
 * @(#)SubPanelControlEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestran al usuario
 * los componentes GUI que permiten controlar la simulación espacial. Contiene
 * los botones para iniciar, pausar y detener la simulación. También contiene
 * las opciones para mover la simulación en diferentes direcciones: norte, sur,
 * oeste, este, noroeste, noreste, suroeste, sureste y centro. Además contiene
 * los botones para acercar, alejar y restablecer el zoom con el cual se
 * visualiza el foco de la simulación.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Color
 * @see Font
 * @see JPanel
 * @see JButton
 * @see ImageIcon
 * @see JLabel
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see PanelEspacialEcosistema
 * @see EventoButton
 * @see InterfacePanel
 */
public class SubPanelControlEcosistema extends JPanel implements InterfacePanel
{
	/** El puntero al panel que contiene a este panel. */
	public PanelEspacialEcosistema panelEspacialEcosistema;
	
	/** El botón para iniciar la simulación. */
	private JButton botonSimular;
	
	/** El botón para pausar la simulación. */
	private JButton botonPausar;
	
	/** El botón para detener la simulación. */
	private JButton botonDetener;
	
	/** El botón para mover la simulación en dirección noroeste. */
	private JButton botonNoroeste;
	
	/** El botón para mover la simulación en dirección norte. */
	private JButton botonNorte;
	
	/** El botón para mover la simulación en dirección noreste. */
	private JButton botonNoreste;
	
	/** El botón para mover la simulación en dirección oeste. */
	private JButton botonOeste;
	
	/** El botón para mover la simulación en dirección centro. */
	private JButton botonCentrar;
	
	/** El botón para mover la simulación en dirección este. */
	private JButton botonEste;
	
	/** El botón para mover la simulación en dirección suroeste. */
	private JButton botonSuroeste;
	
	/** El botón para mover la simulación en dirección sur. */
	private JButton botonSur;
	
	/** El botón para mover la simulación en dirección sureste. */
	private JButton botonSureste;
	
	/** El botón para acercar la simulación. */
	private JButton botonAcercar;
	
	/** El botón para alejar la simulación. */
	private JButton botonAlejar;
	
	/** El botón para restablecer la simulación. */
	private JButton botonRestablecer;
	
	/** La etiqueta de la fecha actual. */
	private JLabel etiquetaFecha;
	
	/** La etiqueta de la hora actual. */
	private JLabel etiquetaHora;
	
	/** La etiqueta de la longitud en el mapa. */
	private JLabel etiquetaLongitud;
	
	/** La etiqueta de la latitud en el mapa. */
	private JLabel etiquetaLatitud;
	
	/** La etiqueta de la altitud en el mapa. */
	private JLabel etiquetaAltitud;
	
	/**
     * Método constructor en donde se configuran los componentes GUI que tiene
     * este panel.
     *
     * @param panelEspacialEcosistema Un objeto que hace referencia al panel
     *                                padre de este sub-panel.
     * @param x1 La posición inicial en el eje X para este panel.
     * @param y1 La posición inicial en el eje Y para este panel.
     * @param x2 La posición final en el eje X para este panel.
     * @param y2 La posición final en el eje Y para este panel.
     */
    public SubPanelControlEcosistema(
    							PanelEspacialEcosistema panelEspacialEcosistema,
    							int x1, int y1, int x2, int y2)
	{
		// Inicializar el panel padre.
		this.panelEspacialEcosistema = panelEspacialEcosistema;
		
		// Configurar.
		configurarPanel(x1, y1, x2, y2);
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método que configura el tipo de borde usado por este panel. Se crea el
	 * borde y luego se incorpora al panel.
	 *
     * @param x1 La posición inicial en el eje X para este panel.
     * @param y1 La posición inicial en el eje Y para este panel.
     * @param x2 La posición final en el eje X para este panel.
     * @param y2 La posición final en el eje Y para este panel.
	 */
	private void configurarPanel(int x1, int y1, int x2, int y2)
	{
		// Asignar el tamaño y la posición al panel.
		setBounds(x1, y1, x2, y2);
		
		// El tipo de borde del panel.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el borde del panel.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Setear el borde del panel.
		setBorder(titulo);
		
		// Eliminar el layout del panel.
		setLayout(null);
	}
	
	/**
	 * Método en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran en este panel correnponden a los
	 * atributos derivados de la clase JPanel. En particular este método no se
	 * se implementa.
	 */
	public void configurarPanel()
	{
	}
	
	/**
	 * Método que configura los componentes GUI que tiene este panel. Se crean
	 * y configuran los JButton, un JLabel y un JSlider.
	 */
	public void configurarComponentes()
	{
		// Crear los paneles.
		JPanel panelControles = new JPanel();
		JPanel panelMovimientos = new JPanel();
		JPanel panelZoom = new JPanel();
		JPanel panelFecha = new JPanel();
		JPanel panelPosicion = new JPanel();
		
		// Crear el borde para los paneles.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los títulos para los paneles.
		TitledBorder tituloControles = new TitledBorder(borde,
						" Controles ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloMovimientos = new TitledBorder(borde,
						" Movimientos ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloZoom = new TitledBorder(borde,
						" Zoom ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloFecha = new TitledBorder(borde,
						" Fecha y Hora ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloPosicion = new TitledBorder(borde,
						" Posición ", TitledBorder.LEFT, TitledBorder.TOP);
		
		// Establecer las propiedades de los paneles.
		panelControles.setBorder(tituloControles);
		panelMovimientos.setBorder(tituloMovimientos);
		panelZoom.setBorder(tituloZoom);
		panelFecha.setBorder(tituloFecha);
		panelPosicion.setBorder(tituloPosicion);
		
		// Eliminar el layout de los paneles.
		panelControles.setLayout(null);
		panelMovimientos.setLayout(null);
		panelZoom.setLayout(null);
		panelFecha.setLayout(null);
		panelPosicion.setLayout(null);
		
		// Dimensionar los paneles.
		panelControles.setBounds(2, 2, 100, 115);
		panelMovimientos.setBounds(2, 125, 100, 105);
		panelZoom.setBounds(2, 240, 100, 115);
		panelFecha.setBounds(2, 365, 100, 80);
		panelPosicion.setBounds(2, 455, 100, 95);
		
		// Crear los componentes.
		botonSimular = new JButton(new ImageIcon("../img/iniciar.gif"));
		botonPausar = new JButton(new ImageIcon("../img/pausar.gif"));
		botonDetener = new JButton(new ImageIcon("../img/detener.gif"));
		botonNoroeste = new JButton();
		botonNorte = new JButton();
		botonNoreste = new JButton();
		botonOeste = new JButton();
		botonCentrar = new JButton();
		botonEste = new JButton();
		botonSuroeste = new JButton();
		botonSur = new JButton();
		botonSureste = new JButton();
		botonAcercar = new JButton(new ImageIcon("../img/acercar.gif"));
		botonAlejar = new JButton(new ImageIcon("../img/alejar.gif"));
		botonRestablecer = new JButton(new ImageIcon("../img/restablecer.gif"));
		etiquetaFecha = new JLabel();
		etiquetaHora = new JLabel();
		etiquetaLongitud = new JLabel();
		etiquetaLatitud = new JLabel();
		etiquetaAltitud = new JLabel();
		
		// La fuente para los componentes.
		Font fuenteEtiqueta = new Font("Arial", Font.PLAIN, 9);
		
		// Establecer la fuente a los componentes.
		etiquetaFecha.setFont(fuenteEtiqueta);
		etiquetaHora.setFont(fuenteEtiqueta);
		etiquetaLongitud.setFont(fuenteEtiqueta);
		etiquetaLatitud.setFont(fuenteEtiqueta);
		etiquetaAltitud.setFont(fuenteEtiqueta);
		
		// Inicializar los componentes.
		cambiarEtiquetaFecha("");
		cambiarEtiquetaHora("");
		cambiarEtiquetaLongitud("");
		cambiarEtiquetaLatitud("");
		cambiarEtiquetaAltitud("");
		
		// Asignar la posición y tamaño a los componentes.
		botonSimular.setBounds(10, 20, 80, 25);
		botonPausar.setBounds(10, 50, 80, 25);
		botonDetener.setBounds(10, 80, 80, 25);
		botonNoroeste.setBounds(10, 20, 25, 26);
		botonNorte.setBounds(35, 20, 20, 26);
		botonNoreste.setBounds(55, 20, 26, 26);
		botonOeste.setBounds(10, 46, 25, 20);
		botonCentrar.setBounds(35, 46, 20, 20);
		botonEste.setBounds(55, 46, 26, 20);
		botonSuroeste.setBounds(10, 66, 25, 26);
		botonSur.setBounds(35, 66, 20, 26);
		botonSureste.setBounds(55, 66, 26, 26);
		botonAcercar.setBounds(10, 20, 80, 25);
		botonAlejar.setBounds(10, 50, 80, 25);
		botonRestablecer.setBounds(10, 80, 80, 25);
		etiquetaFecha.setBounds(10, 20, 80, 20);
		etiquetaHora.setBounds(10, 40, 80, 20);
		etiquetaLongitud.setBounds(10, 20, 80, 20);
		etiquetaLatitud.setBounds(10, 40, 80, 20);
		etiquetaAltitud.setBounds(10, 60, 80, 20);
		
		// Asignar el "tip" a los componentes.
		botonSimular.setToolTipText("Iniciar la simulación");
		botonPausar.setToolTipText("Pausar la simulación");
		botonDetener.setToolTipText("Detener la simulación");
		botonNoroeste.setToolTipText("Mover al Noroeste");
		botonNorte.setToolTipText("Mover al Norte");
		botonNoreste.setToolTipText("Mover al Noreste");
		botonOeste.setToolTipText("Mover al Oeste");
		botonCentrar.setToolTipText("Mover al Centro");
		botonEste.setToolTipText("Mover al Este");
		botonSuroeste.setToolTipText("Mover al Suroeste");
		botonSur.setToolTipText("Mover al Sur");
		botonSureste.setToolTipText("Mover al Sureste");
		botonAcercar.setToolTipText("Acercar la simulación");
		botonAlejar.setToolTipText("Alejar la simulación");
		botonRestablecer.setToolTipText("Restablecer la simulación");
		
		// Incorporar los componentes al panel.
		panelControles.add(botonSimular);
		panelControles.add(botonPausar);
		panelControles.add(botonDetener);
		panelMovimientos.add(botonNoroeste);
		panelMovimientos.add(botonNorte);
		panelMovimientos.add(botonNoreste);
		panelMovimientos.add(botonOeste);
		panelMovimientos.add(botonCentrar);
		panelMovimientos.add(botonEste);
		panelMovimientos.add(botonSuroeste);
		panelMovimientos.add(botonSur);
		panelMovimientos.add(botonSureste);
		panelZoom.add(botonAcercar);
		panelZoom.add(botonAlejar);
		panelZoom.add(botonRestablecer);
		panelFecha.add(etiquetaFecha);
		panelFecha.add(etiquetaHora);
		panelPosicion.add(etiquetaLongitud);
		panelPosicion.add(etiquetaLatitud);
		panelPosicion.add(etiquetaAltitud);
		
		// Agregar los paneles.
		add(panelControles);
		add(panelMovimientos);
		add(panelZoom);
		add(panelFecha);
		add(panelPosicion);
		
		// Habilitar los controles.
		habilitarControles();
		
		// Habilitar los movimientos.
		habilitarMovimientos();
		
		// Habilitar los zooms.
		habilitarZooms();
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones de control de
	 * la simulación espacial. En particular se incorpora el escuchador de
	 * eventos del tipo EventoButton a los JButton que tiene este JPanel.
	 */
	public void configurarEventos()
	{
		// Crear los escuchadores de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonSimular.addActionListener(eventoButton);
		botonPausar.addActionListener(eventoButton);
		botonDetener.addActionListener(eventoButton);
		botonNoroeste.addActionListener(eventoButton);
		botonNorte.addActionListener(eventoButton);
		botonNoreste.addActionListener(eventoButton);
		botonOeste.addActionListener(eventoButton);
		botonCentrar.addActionListener(eventoButton);
		botonEste.addActionListener(eventoButton);
		botonSuroeste.addActionListener(eventoButton);
		botonSur.addActionListener(eventoButton);
		botonSureste.addActionListener(eventoButton);
		botonAcercar.addActionListener(eventoButton);
		botonAlejar.addActionListener(eventoButton);
		botonRestablecer.addActionListener(eventoButton);
	}
	
	/**
	 * Método que configura los botones de control. Específicamente se establece
	 * la propiedad de habilitación de los botones de control, dependiendo del
	 * estado actual en que se encuentra la simulación.
	 */
	public void habilitarControles()
	{
		// Dependiendo del estado de la simulación, deshabilitar los botones.
		switch (panelEspacialEcosistema.frameEcosistema.obtenerEstado())
		{
			// Proceso en segundo plano iniciado.
			case ProcesoSegundoPlano.INICIADO:
			botonSimular.setEnabled(false);
			botonPausar.setEnabled(true);
			botonDetener.setEnabled(true);
			break;
			
			// Proceso en segundo plano pausado.
			case ProcesoSegundoPlano.PAUSADO:
			botonSimular.setEnabled(true);
			botonPausar.setEnabled(false);
			botonDetener.setEnabled(true);
			break;
			
			// Proceso en segundo plano detenido.
			case ProcesoSegundoPlano.DETENIDO:
			botonSimular.setEnabled(true);
			botonPausar.setEnabled(false);
			botonDetener.setEnabled(false);
			break;
		}
	}
	
	/**
	 * Método que configura los botones de movimientos. Específicamente se
	 * establece la propiedad del ícono de los botones de movimientos,
	 * dependiendo de si se puede o no mover en alguna dirección del mapa.
	 */
	public void habilitarMovimientos()
	{
		// Obtener el subpanel del mapa.
		SubPanelMapaEcosistema subpanelMapaEcosistema =
		panelEspacialEcosistema.subpanelMapaEcosistema;
		
		// Cuando se puede mover al Norte y Oeste.
		if (subpanelMapaEcosistema.poderMoverNorte() &&
			subpanelMapaEcosistema.poderMoverOeste())
			botonNoroeste.setIcon(new ImageIcon("../img/noroeste_on.gif"));
		
		// Cuando no se puede mover al Norte u Oeste.
		else botonNoroeste.setIcon(new ImageIcon("../img/noroeste_off.gif"));
		
		// Cuando se puede mover al Norte.
		if (subpanelMapaEcosistema.poderMoverNorte())
			botonNorte.setIcon(new ImageIcon("../img/norte_on.gif"));
		
		// Cuando no se puede mover al Norte.
		else botonNorte.setIcon(new ImageIcon("../img/norte_off.gif"));
		
		// Cuando se puede mover al Norte y Este.
		if (subpanelMapaEcosistema.poderMoverNorte() &&
			subpanelMapaEcosistema.poderMoverEste())
			botonNoreste.setIcon(new ImageIcon("../img/noreste_on.gif"));
		
		// Cuando no se puede mover al Norte u Este.
		else botonNoreste.setIcon(new ImageIcon("../img/noreste_off.gif"));
		
		// Cuando se puede mover al Oeste.
		if (subpanelMapaEcosistema.poderMoverOeste())
			botonOeste.setIcon(new ImageIcon("../img/oeste_on.gif"));
		
		// Cuando no se puede mover al Oeste.
		else botonOeste.setIcon(new ImageIcon("../img/oeste_off.gif"));
		
		// Cuando se puede mover al Centro.
		if (subpanelMapaEcosistema.poderMoverCentro())
			botonCentrar.setIcon(new ImageIcon("../img/centrar_on.gif"));
		
		// Cuando no se puede mover al Centro.
		else botonCentrar.setIcon(new ImageIcon("../img/centrar_off.gif"));
		
		// Cuando se puede mover al Este.
		if (subpanelMapaEcosistema.poderMoverEste())
			botonEste.setIcon(new ImageIcon("../img/este_on.gif"));
		
		// Cuando no se puede mover al Este.
		else botonEste.setIcon(new ImageIcon("../img/este_off.gif"));
		
		// Cuando se puede mover al Sur y Oeste.
		if (subpanelMapaEcosistema.poderMoverSur() &&
			subpanelMapaEcosistema.poderMoverOeste())
			botonSuroeste.setIcon(new ImageIcon("../img/suroeste_on.gif"));
		
		// Cuando no se puede mover al Sur u Oeste.
		else botonSuroeste.setIcon(new ImageIcon("../img/suroeste_off.gif"));
		
		// Cuando se puede mover al Sur.
		if (subpanelMapaEcosistema.poderMoverSur())
			botonSur.setIcon(new ImageIcon("../img/sur_on.gif"));
		
		// Cuando no se puede mover al Sur.
		else botonSur.setIcon(new ImageIcon("../img/sur_off.gif"));
		
		// Cuando se puede mover al Sur y Este.
		if (subpanelMapaEcosistema.poderMoverSur() &&
			subpanelMapaEcosistema.poderMoverEste())
			botonSureste.setIcon(new ImageIcon("../img/sureste_on.gif"));
		
		// Cuando no se puede mover al Sur u Este.
		else botonSureste.setIcon(new ImageIcon("../img/sureste_off.gif"));
	}
	
	/**
	 * Método que configura los botones de zooms. Específicamente se establece
	 * la propiedad de habilitación de los botones de zooms, dependiendo de si
	 * se puede o no acercar o alejar la simulación.
	 */
	public void habilitarZooms()
	{
		// Obtener el subpanel del mapa.
		SubPanelMapaEcosistema subpanelMapaEcosistema =
		panelEspacialEcosistema.subpanelMapaEcosistema;
		
		// Cuando se puede acercar.
		if (subpanelMapaEcosistema.poderAcercar())
			botonAcercar.setEnabled(true);
		
		// Cuando no se puede acercar.
		else botonAcercar.setEnabled(false);
		
		// Cuando se puede alejar.
		if (subpanelMapaEcosistema.poderAlejar())
			botonAlejar.setEnabled(true);
		
		// Cuando no se puede alejar.
		else botonAlejar.setEnabled(false);
	}
	
	/**
	 * Método en donde se cambia el texto que tiene el atributo etiquetaFecha.
	 *
	 * @param texto El texto que se establece al atributo etiquetaFecha.
	 */
	public void cambiarEtiquetaFecha(String texto)
	{
		etiquetaFecha.setText("Fecha: "+texto);
	}
	
	/**
	 * Método en donde se cambia el texto que tiene el atributo etiquetaHora.
	 *
	 * @param texto El texto que se establece al atributo etiquetaHora.
	 */
	public void cambiarEtiquetaHora(String texto)
	{
		etiquetaHora.setText("Hora: "+texto);
	}
	
	/**
	 * Método en donde se cambia el texto que tiene el atributo
	 * etiquetaLongitud.
	 *
	 * @param texto El texto que se establece al atributo etiquetaLongitud.
	 */
	public void cambiarEtiquetaLongitud(String texto)
	{
		etiquetaLongitud.setText("Longitud: "+texto);
	}
	
	/**
	 * Método en donde se cambia el texto que tiene el atributo etiquetaLatitud.
	 *
	 * @param texto El texto que se establece al atributo etiquetaLatitud.
	 */
	public void cambiarEtiquetaLatitud(String texto)
	{
		etiquetaLatitud.setText("Latitud: "+texto);
	}
	
	/**
	 * Método en donde se cambia el texto que tiene el atributo etiquetaAltitud.
	 *
	 * @param texto El texto que se establece al atributo etiquetaAltitud.
	 */
	public void cambiarEtiquetaAltitud(String texto)
	{
		etiquetaAltitud.setText("Altitud: "+texto);
	}
	
	/**
	 * Método que retorna el valor del atributo panelEspacialEcosistema.
	 *
	 * @return panelEspacialEcosistema El valor actual del atributo
	 *                                 panelEspacialEcosistema.
	 */
	public PanelEspacialEcosistema obtenerPanelEspacialEcosistema()
	{
		return panelEspacialEcosistema;
	}
	
	/**
	 * Método que obtiene el botón Simular.
	 *
	 * @return botonSimular El botón que permite iniciar la simulación.
	 */
	public JButton obtenerBotonSimular()
	{
		return botonSimular;
	}
	
	/**
	 * Método que obtiene el botón Pausar.
	 *
	 * @return botonPausar El botón que permite pausar la simulación.
	 */
	public JButton obtenerBotonPausar()
	{
		return botonPausar;
	}
	
	/**
	 * Método que obtiene el botón Detener.
	 *
	 * @return botonDetener El botón que permite detener la simulación.
	 */
	public JButton obtenerBotonDetener()
	{
		return botonDetener;
	}
	
	/**
	 * Método que obtiene el botón Noroeste.
	 *
	 * @return botonNoroeste El botón que permite mover el mapa en dirección
	 *                       hacia el Noroeste.
	 */
	public JButton obtenerBotonNoroeste()
	{
		return botonNoroeste;
	}
	
	/**
	 * Método que obtiene el botón Norte.
	 *
	 * @return botonNorte El botón que permite mover el mapa en dirección hacia
	 *                    el Norte.
	 */
	public JButton obtenerBotonNorte()
	{
		return botonNorte;
	}
	
	/**
	 * Método que obtiene el botón Noreste.
	 *
	 * @return botonNoreste El botón que permite mover el mapa en dirección
	 *                      hacia el Noreste.
	 */
	public JButton obtenerBotonNoreste()
	{
		return botonNoreste;
	}
	
	/**
	 * Método que obtiene el botón Oeste.
	 *
	 * @return botonNoroeste El botón que permite mover el mapa en dirección
	 *                       hacia el Oeste.
	 */
	public JButton obtenerBotonOeste()
	{
		return botonOeste;
	}
	
	/**
	 * Método que obtiene el botón Centrar.
	 *
	 * @return botonNoroeste El botón que permite mover el mapa en dirección
	 *                       hacia el Centro.
	 */
	public JButton obtenerBotonCentrar()
	{
		return botonCentrar;
	}
	
	/**
	 * Método que obtiene el botón Este.
	 *
	 * @return botonNoroeste El botón que permite mover el mapa en dirección
	 *                       hacia el Este.
	 */
	public JButton obtenerBotonEste()
	{
		return botonEste;
	}
	
	/**
	 * Método que obtiene el botón Suroeste.
	 *
	 * @return botonNoroeste El botón que permite mover el mapa en dirección
	 *                       hacia el Suroeste.
	 */
	public JButton obtenerBotonSuroeste()
	{
		return botonSuroeste;
	}
	
	/**
	 * Método que obtiene el botón Sur.
	 *
	 * @return botonSur El botón que permite mover el mapa en dirección hacia el
	 *                  Sur.
	 */
	public JButton obtenerBotonSur()
	{
		return botonSur;
	}
	
	/**
	 * Método que obtiene el botón Sureste.
	 *
	 * @return botonSureste El botón que permite mover el mapa en dirección
	 *                      hacia el Sureste.
	 */
	public JButton obtenerBotonSureste()
	{
		return botonSureste;
	}
	
	/**
	 * Método que obtiene el botón Acercar.
	 *
	 * @return botonAcercar El botón que permite acercar la simulación.
	 */
	public JButton obtenerBotonAcercar()
	{
		return botonAcercar;
	}
	
	/**
	 * Método que obtiene el botón Alejar.
	 *
	 * @return botonAlejar el botón que permite alejar la simulación.
	 */
	public JButton obtenerBotonAlejar()
	{
		return botonAlejar;
	}
	
	/**
	 * Método que obtiene el botón Restablecer.
	 *
	 * @return botonRestablecer el botón que permite restablecer la simulación.
	 */
	public JButton obtenerBotonRestablecer()
	{
		return botonRestablecer;
	}
}