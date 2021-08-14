/*
 * @(#)SubPanelControlEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
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
 * los componentes GUI que permiten controlar la simulaci�n espacial. Contiene
 * los botones para iniciar, pausar y detener la simulaci�n. Tambi�n contiene
 * las opciones para mover la simulaci�n en diferentes direcciones: norte, sur,
 * oeste, este, noroeste, noreste, suroeste, sureste y centro. Adem�s contiene
 * los botones para acercar, alejar y restablecer el zoom con el cual se
 * visualiza el foco de la simulaci�n.
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
	
	/** El bot�n para iniciar la simulaci�n. */
	private JButton botonSimular;
	
	/** El bot�n para pausar la simulaci�n. */
	private JButton botonPausar;
	
	/** El bot�n para detener la simulaci�n. */
	private JButton botonDetener;
	
	/** El bot�n para mover la simulaci�n en direcci�n noroeste. */
	private JButton botonNoroeste;
	
	/** El bot�n para mover la simulaci�n en direcci�n norte. */
	private JButton botonNorte;
	
	/** El bot�n para mover la simulaci�n en direcci�n noreste. */
	private JButton botonNoreste;
	
	/** El bot�n para mover la simulaci�n en direcci�n oeste. */
	private JButton botonOeste;
	
	/** El bot�n para mover la simulaci�n en direcci�n centro. */
	private JButton botonCentrar;
	
	/** El bot�n para mover la simulaci�n en direcci�n este. */
	private JButton botonEste;
	
	/** El bot�n para mover la simulaci�n en direcci�n suroeste. */
	private JButton botonSuroeste;
	
	/** El bot�n para mover la simulaci�n en direcci�n sur. */
	private JButton botonSur;
	
	/** El bot�n para mover la simulaci�n en direcci�n sureste. */
	private JButton botonSureste;
	
	/** El bot�n para acercar la simulaci�n. */
	private JButton botonAcercar;
	
	/** El bot�n para alejar la simulaci�n. */
	private JButton botonAlejar;
	
	/** El bot�n para restablecer la simulaci�n. */
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
     * M�todo constructor en donde se configuran los componentes GUI que tiene
     * este panel.
     *
     * @param panelEspacialEcosistema Un objeto que hace referencia al panel
     *                                padre de este sub-panel.
     * @param x1 La posici�n inicial en el eje X para este panel.
     * @param y1 La posici�n inicial en el eje Y para este panel.
     * @param x2 La posici�n final en el eje X para este panel.
     * @param y2 La posici�n final en el eje Y para este panel.
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
	 * M�todo que configura el tipo de borde usado por este panel. Se crea el
	 * borde y luego se incorpora al panel.
	 *
     * @param x1 La posici�n inicial en el eje X para este panel.
     * @param y1 La posici�n inicial en el eje Y para este panel.
     * @param x2 La posici�n final en el eje X para este panel.
     * @param y2 La posici�n final en el eje Y para este panel.
	 */
	private void configurarPanel(int x1, int y1, int x2, int y2)
	{
		// Asignar el tama�o y la posici�n al panel.
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
	 * M�todo en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran en este panel correnponden a los
	 * atributos derivados de la clase JPanel. En particular este m�todo no se
	 * se implementa.
	 */
	public void configurarPanel()
	{
	}
	
	/**
	 * M�todo que configura los componentes GUI que tiene este panel. Se crean
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
		
		// Crear los t�tulos para los paneles.
		TitledBorder tituloControles = new TitledBorder(borde,
						" Controles ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloMovimientos = new TitledBorder(borde,
						" Movimientos ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloZoom = new TitledBorder(borde,
						" Zoom ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloFecha = new TitledBorder(borde,
						" Fecha y Hora ", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloPosicion = new TitledBorder(borde,
						" Posici�n ", TitledBorder.LEFT, TitledBorder.TOP);
		
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
		
		// Asignar la posici�n y tama�o a los componentes.
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
		botonSimular.setToolTipText("Iniciar la simulaci�n");
		botonPausar.setToolTipText("Pausar la simulaci�n");
		botonDetener.setToolTipText("Detener la simulaci�n");
		botonNoroeste.setToolTipText("Mover al Noroeste");
		botonNorte.setToolTipText("Mover al Norte");
		botonNoreste.setToolTipText("Mover al Noreste");
		botonOeste.setToolTipText("Mover al Oeste");
		botonCentrar.setToolTipText("Mover al Centro");
		botonEste.setToolTipText("Mover al Este");
		botonSuroeste.setToolTipText("Mover al Suroeste");
		botonSur.setToolTipText("Mover al Sur");
		botonSureste.setToolTipText("Mover al Sureste");
		botonAcercar.setToolTipText("Acercar la simulaci�n");
		botonAlejar.setToolTipText("Alejar la simulaci�n");
		botonRestablecer.setToolTipText("Restablecer la simulaci�n");
		
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
	 * M�todo que adjunta los escuchadores eventos a los botones de control de
	 * la simulaci�n espacial. En particular se incorpora el escuchador de
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
	 * M�todo que configura los botones de control. Espec�ficamente se establece
	 * la propiedad de habilitaci�n de los botones de control, dependiendo del
	 * estado actual en que se encuentra la simulaci�n.
	 */
	public void habilitarControles()
	{
		// Dependiendo del estado de la simulaci�n, deshabilitar los botones.
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
	 * M�todo que configura los botones de movimientos. Espec�ficamente se
	 * establece la propiedad del �cono de los botones de movimientos,
	 * dependiendo de si se puede o no mover en alguna direcci�n del mapa.
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
	 * M�todo que configura los botones de zooms. Espec�ficamente se establece
	 * la propiedad de habilitaci�n de los botones de zooms, dependiendo de si
	 * se puede o no acercar o alejar la simulaci�n.
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
	 * M�todo en donde se cambia el texto que tiene el atributo etiquetaFecha.
	 *
	 * @param texto El texto que se establece al atributo etiquetaFecha.
	 */
	public void cambiarEtiquetaFecha(String texto)
	{
		etiquetaFecha.setText("Fecha: "+texto);
	}
	
	/**
	 * M�todo en donde se cambia el texto que tiene el atributo etiquetaHora.
	 *
	 * @param texto El texto que se establece al atributo etiquetaHora.
	 */
	public void cambiarEtiquetaHora(String texto)
	{
		etiquetaHora.setText("Hora: "+texto);
	}
	
	/**
	 * M�todo en donde se cambia el texto que tiene el atributo
	 * etiquetaLongitud.
	 *
	 * @param texto El texto que se establece al atributo etiquetaLongitud.
	 */
	public void cambiarEtiquetaLongitud(String texto)
	{
		etiquetaLongitud.setText("Longitud: "+texto);
	}
	
	/**
	 * M�todo en donde se cambia el texto que tiene el atributo etiquetaLatitud.
	 *
	 * @param texto El texto que se establece al atributo etiquetaLatitud.
	 */
	public void cambiarEtiquetaLatitud(String texto)
	{
		etiquetaLatitud.setText("Latitud: "+texto);
	}
	
	/**
	 * M�todo en donde se cambia el texto que tiene el atributo etiquetaAltitud.
	 *
	 * @param texto El texto que se establece al atributo etiquetaAltitud.
	 */
	public void cambiarEtiquetaAltitud(String texto)
	{
		etiquetaAltitud.setText("Altitud: "+texto);
	}
	
	/**
	 * M�todo que retorna el valor del atributo panelEspacialEcosistema.
	 *
	 * @return panelEspacialEcosistema El valor actual del atributo
	 *                                 panelEspacialEcosistema.
	 */
	public PanelEspacialEcosistema obtenerPanelEspacialEcosistema()
	{
		return panelEspacialEcosistema;
	}
	
	/**
	 * M�todo que obtiene el bot�n Simular.
	 *
	 * @return botonSimular El bot�n que permite iniciar la simulaci�n.
	 */
	public JButton obtenerBotonSimular()
	{
		return botonSimular;
	}
	
	/**
	 * M�todo que obtiene el bot�n Pausar.
	 *
	 * @return botonPausar El bot�n que permite pausar la simulaci�n.
	 */
	public JButton obtenerBotonPausar()
	{
		return botonPausar;
	}
	
	/**
	 * M�todo que obtiene el bot�n Detener.
	 *
	 * @return botonDetener El bot�n que permite detener la simulaci�n.
	 */
	public JButton obtenerBotonDetener()
	{
		return botonDetener;
	}
	
	/**
	 * M�todo que obtiene el bot�n Noroeste.
	 *
	 * @return botonNoroeste El bot�n que permite mover el mapa en direcci�n
	 *                       hacia el Noroeste.
	 */
	public JButton obtenerBotonNoroeste()
	{
		return botonNoroeste;
	}
	
	/**
	 * M�todo que obtiene el bot�n Norte.
	 *
	 * @return botonNorte El bot�n que permite mover el mapa en direcci�n hacia
	 *                    el Norte.
	 */
	public JButton obtenerBotonNorte()
	{
		return botonNorte;
	}
	
	/**
	 * M�todo que obtiene el bot�n Noreste.
	 *
	 * @return botonNoreste El bot�n que permite mover el mapa en direcci�n
	 *                      hacia el Noreste.
	 */
	public JButton obtenerBotonNoreste()
	{
		return botonNoreste;
	}
	
	/**
	 * M�todo que obtiene el bot�n Oeste.
	 *
	 * @return botonNoroeste El bot�n que permite mover el mapa en direcci�n
	 *                       hacia el Oeste.
	 */
	public JButton obtenerBotonOeste()
	{
		return botonOeste;
	}
	
	/**
	 * M�todo que obtiene el bot�n Centrar.
	 *
	 * @return botonNoroeste El bot�n que permite mover el mapa en direcci�n
	 *                       hacia el Centro.
	 */
	public JButton obtenerBotonCentrar()
	{
		return botonCentrar;
	}
	
	/**
	 * M�todo que obtiene el bot�n Este.
	 *
	 * @return botonNoroeste El bot�n que permite mover el mapa en direcci�n
	 *                       hacia el Este.
	 */
	public JButton obtenerBotonEste()
	{
		return botonEste;
	}
	
	/**
	 * M�todo que obtiene el bot�n Suroeste.
	 *
	 * @return botonNoroeste El bot�n que permite mover el mapa en direcci�n
	 *                       hacia el Suroeste.
	 */
	public JButton obtenerBotonSuroeste()
	{
		return botonSuroeste;
	}
	
	/**
	 * M�todo que obtiene el bot�n Sur.
	 *
	 * @return botonSur El bot�n que permite mover el mapa en direcci�n hacia el
	 *                  Sur.
	 */
	public JButton obtenerBotonSur()
	{
		return botonSur;
	}
	
	/**
	 * M�todo que obtiene el bot�n Sureste.
	 *
	 * @return botonSureste El bot�n que permite mover el mapa en direcci�n
	 *                      hacia el Sureste.
	 */
	public JButton obtenerBotonSureste()
	{
		return botonSureste;
	}
	
	/**
	 * M�todo que obtiene el bot�n Acercar.
	 *
	 * @return botonAcercar El bot�n que permite acercar la simulaci�n.
	 */
	public JButton obtenerBotonAcercar()
	{
		return botonAcercar;
	}
	
	/**
	 * M�todo que obtiene el bot�n Alejar.
	 *
	 * @return botonAlejar el bot�n que permite alejar la simulaci�n.
	 */
	public JButton obtenerBotonAlejar()
	{
		return botonAlejar;
	}
	
	/**
	 * M�todo que obtiene el bot�n Restablecer.
	 *
	 * @return botonRestablecer el bot�n que permite restablecer la simulaci�n.
	 */
	public JButton obtenerBotonRestablecer()
	{
		return botonRestablecer;
	}
}