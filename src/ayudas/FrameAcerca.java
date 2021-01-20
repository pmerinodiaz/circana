/**
 * @(#)FrameAcerca.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que deriva de JDialog y que contiene el frame que se muestra al
 * usuario cuando se pulsa el botón "Acerca de CIRCANA Pro". Este frame tiene
 * por objetivo mostrar al usuario la versión del programa, los derechos de
 * de copia y los autores del programa.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Container
 * @see Font
 * @see Toolkit
 * @see Color
 * @see GridLayout
 * @see KeyEvent
 * @see JDialog
 * @see JPanel
 * @see JLabel
 * @see JButton
 * @see JFrame
 * @see ImageIcon
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameCircanaPro
 * @see InterfaceFrame
 */
public class FrameAcerca extends JDialog implements InterfaceFrame
{
	/** Puntero al frame que contiene a este diálogo. */
	public FrameCircanaPro frameCircanaPro;
	
	/** El botón "Aceptar" para cerrar este frame. */
	private JButton botonAceptar;
	
	/*
	 * Método constructor en donde se crea y configura este frame.
	 *
	 * @param frameCircanaPro El frame que contiene a este frame.
	 */
	public FrameAcerca(FrameCircanaPro frameCircanaPro)
	{
		super(frameCircanaPro);
		
		// Inicializar el puntero.
		this.frameCircanaPro = frameCircanaPro;
		
		// Configurar.
		configurarFrame();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se inicializan los objetos que se usan en todo este
	 * frame. En particular, este frame no maneja elementos especiales.
	 */
	public void configurarElementosEspeciales()
	{
	}
	
	/**
	 * Método que configura las características de este frame.
	 */
	public void configurarFrame()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		setTitle("Acerca de CIRCANA Pro");
		setSize(300, 360);
		setLocation(tk.getScreenSize().width/2-150,
					tk.getScreenSize().height/2-180);
		setResizable(false);
		setModal(true);
	}
	
	/**
	 * Método que configura los paneles de este frame y sus componentes GUI.
	 */
	public void configurarComponentes()
	{
		// Capturar el container de este frame.
		Container contenedor = getContentPane();
		
		// El tipo de borde de los paneles.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los bordes de los paneles.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		// Crear los paneles.
		JPanel panelLogo = new JPanel();
		JPanel panelInfo = new JPanel();
		
		// Setear el borde de los paneles.
		panelInfo.setBorder(titulo);
		
		// Setear el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Setear el layout de los paneles.
		panelLogo.setLayout(null);
		panelInfo.setLayout(new GridLayout(8, 1));
		
		// Valores de ancho y alto de los paneles.
		int ancho = 300;
		int alto = 150;
		
		// Cambiar el tamaño y posición de los paneles.
		panelLogo.setBounds(0, 0, ancho, alto);
		panelInfo.setBounds(5, alto+5, ancho-15, alto-10);
		
		// Crear los botones.
		JLabel fotoLogo =
			new JLabel(new ImageIcon("../img/creditos.gif"),JLabel.CENTER);
		
		botonAceptar = new JButton("Aceptar",
			new ImageIcon("../img/establecer.gif"));
		botonAceptar.setToolTipText("Cerrar la ventana");
		botonAceptar.setMnemonic(KeyEvent.VK_A);
		
		// Crear las etiquetas.
		JLabel labelCircana =
			new JLabel("CIRCANA Pro 2.0", 0);
		JLabel labelNombre =
			new JLabel("Captura Inteligente del Recurso Camarón Nailon", 0);
		JLabel labelCopyright =
			new JLabel("Copyright © 2003-2005", 0);
		JLabel labelDerechos =
			new JLabel("Todos los derechos reservados", 0);
		JLabel labelAutores =
			new JLabel("");
		JLabel labelAutor1 =
			new JLabel("Héctor Díaz Díaz - hectordiazuls@hotmail.com", 0);
		JLabel labelAutor2 =
			new JLabel("Paul Leger Morales - pleger@gmail.com", 0);
		JLabel labelAutor3 =
			new JLabel("Patricio Merino Díaz - pmerinodiaz@hotmail.com", 0);
		
		// Crear las fuentes.
		Font fuente1 = new Font("Helvetica", Font.PLAIN, 12);
		Font fuente2 = new Font("Helvetica", Font.PLAIN, 11);
		
		// Cambiar el tamaño y posición de los componentes.
		fotoLogo.setBounds(0, 0, 300, 150);
		botonAceptar.setBounds(90, 2*alto, 120, 25);
		
		// Cambiar la fuente de los label.
		labelNombre.setFont(fuente1);
		labelCopyright.setFont(fuente2);
		labelDerechos.setFont(fuente2);
		labelAutores.setFont(fuente2);
		labelAutor1.setFont(fuente2);
		labelAutor2.setFont(fuente2);
		labelAutor3.setFont(fuente2);
		
		// Incorporar los componentes a los paneles.
		panelLogo.add(fotoLogo);
		panelInfo.add(labelCircana);
		panelInfo.add(labelNombre);
		panelInfo.add(labelCopyright);
		panelInfo.add(labelDerechos);
		panelInfo.add(labelAutores);
		panelInfo.add(labelAutor1);
		panelInfo.add(labelAutor2);
		panelInfo.add(labelAutor3);
		
		// Incorporar los paneles al contenedor del frame.
		contenedor.add(panelLogo);
		contenedor.add(panelInfo);
		contenedor.add(botonAceptar);
	}
	
	/**
	 * Método para configura los eventos de los componentes GUI de este frame.
	 */
	public void configurarEventos()
	{
		// Crear el escuhcador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el evento al botón.
		botonAceptar.addActionListener(eventoButton);
	}
	
	/**
	 * Método que obtiene el botón "Aceptar".
	 *
	 * @return botonAceptar el botón "Aceptar" que tiene este frame.
	 */
	public JButton obtenerBotonAceptar()
	{
		return botonAceptar;
	}
}