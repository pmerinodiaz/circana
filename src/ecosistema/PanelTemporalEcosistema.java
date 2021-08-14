/**
 * @(#)PanelTemporalEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JPanel. Se especializa en contener un panel
 * que permite al usuario ver la din�mica temporal del recurso estudiado y
 * contener cuatro botones que permiten al usuario moverse en los a�os del
 * gr�fico.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see KeyEvent
 * @see JPanel
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see FrameEcosistema
 * @see SubPanelTemporalEcosistema
 * @see EventoButton
 * @see InterfacePanel
 */
public class PanelTemporalEcosistema extends JPanel implements InterfacePanel
{
	/** El puntero al frame que contiene a este panel. */
	public FrameEcosistema frameEcosistema;
	
	/** El panel en donde se muestra la gr�fica temporal del ecosistema. */
	public SubPanelTemporalEcosistema subpanelTemporalEcosistema;
	
	/** Bot�n que permite mover las gr�ficas al a�o anterior. */
	private JButton botonAnterior;
	
	/** Bot�n que permite mover las gr�ficas al a�o siguiente. */
	private JButton botonSiguiente;
	
	/** Texto que muestra el tiempo de simulaci�n. */
	private JLabel textoSimulacion;
	
	/** El a�o que se est� graficando en el panel. */
	private int anio;
	
	/**
	 * M�todo constructor en donde se crean e inicializan los componentes GUI
	 * que contiene este panel. Primero se inicializa el puntero al frame que
	 * contiene a este panel. Luego se llama a los m�todos que configurar este
	 * panel y despu�s se configuran los eventos que contiene este panel.
	 *
	 * @param frameEcosistema Un objeto que hace referencia al FrameEcosistema
	 *                        que contiene a este panel.
	 */
	public PanelTemporalEcosistema(FrameEcosistema frameEcosistema)
	{
		// Apuntar al frame contenedor de este panel.
		this.frameEcosistema = frameEcosistema;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * M�todo en donde se inicializan los atributos que se usan en todo este
	 * panel.
	 */
	private void configurarElementosEspeciales()
	{
		// Establecer el a�o.
		anio = Proyecto.obtenerFechaInicialFormatoInt()[2];
	}
	
	/**
	 * M�todo en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran correnponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este m�todo.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
	 * M�todo en donde se configuran los componentes que contiene este panel.
	 * Este panel contiene un panel y cuatro botones: uno que permite al usuario
	 * retroceder los gr�ficos y otro que permite al usuario avanzar los
	 * gr�ficos.
	 */
	public void configurarComponentes()
	{
		// Obtener la mitad del frame.
		int mitadFrame = frameEcosistema.getWidth() / 2;
		
		// Crear los componentes que tiene este panel.
		textoSimulacion = new JLabel("A�o " + anio);
		subpanelTemporalEcosistema =
						new SubPanelTemporalEcosistema(this, 0, 0, 823, 520);
		botonAnterior = new JButton("Anterior",
						new ImageIcon("../img/anterior.gif"));
		botonSiguiente = new JButton("Siguiente",
						 new ImageIcon("../img/siguiente.gif"));
		
		// Posicionar y dimensionar.
		textoSimulacion.setBounds(mitadFrame - 25, 530, 60, 20);
		botonAnterior.setBounds(mitadFrame - 180, 525, 140, 30);
		botonSiguiente.setBounds(mitadFrame + 40, 525, 140, 30);
		
		// Asignar los eventos de tecla.
		botonAnterior.setMnemonic(KeyEvent.VK_A);
		botonSiguiente.setMnemonic(KeyEvent.VK_S);
		
		// Asignar los tips.
		botonAnterior.setToolTipText("Ver curvas del a�o anterior");
		botonSiguiente.setToolTipText("Ver curvas del a�o siguiente");
		
		// configurando elementos
		botonAnterior.setHorizontalTextPosition(JButton.RIGHT);
		botonSiguiente.setHorizontalTextPosition(JButton.LEFT);
		textoSimulacion.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		habilitarControles();
		
		// Agregar los componentes.
		add(subpanelTemporalEcosistema);
		add(botonAnterior);
		add(botonSiguiente);
		add(textoSimulacion);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los componentes que tiene
	 * este panel de configuraci�n. En particular se incorpora el escuchador de
	 * eventos del tipo EventoButton a los JButton que tiene el panel.
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
	 * M�todo que configura los botones de navegaci�n. Espec�ficamente se
	 * establece la propiedad de habilitaci�n de los botones de navegaci�n,
	 * dependiendo del estado actual en que se encuentra la simulaci�n.
	 */
	private void habilitarControles()
	{
		// Cuando el a�o es mayor que el a�o inicial.
		if (Proyecto.obtenerFechaInicialFormatoInt()[2] < anio)
			botonAnterior.setEnabled(true);
		
		// Cuando el a�o es menor o igual que el a�o inicial.
		else botonAnterior.setEnabled(false);
		
		// Cuando el a�o es menor que el a�o final.
		if (anio < Proyecto.obtenerFechaFinalFormatoInt()[2])
			botonSiguiente.setEnabled(true);
		
		// Cuando el a�o es mayor o igual que el a�o final.
		else botonSiguiente.setEnabled(false);
	}
	
	/**
	 * M�todo que cambia el a�o del gr�fico al a�o anterior.
	 */
	public void cambiarAnioAnterior()
	{
		// Cambiar el a�o.
		if (Proyecto.obtenerFechaInicialFormatoInt()[2] < anio)
			anio--;
		
		// Cambiar el texto del a�o.
		textoSimulacion.setText("A�o " + anio);
		
		// Habilitar los controles.
		habilitarControles();
		
		// Repintar el subpanel.
		subpanelTemporalEcosistema.repaint();
	}
	
	/**
	 * M�todo que cambia el a�o del gr�fico al a�o siguiente.
	 */
	public void cambiarAnioSiguiente()
	{
		// Cambiar el a�o.
		if (anio < Proyecto.obtenerFechaFinalFormatoInt()[2])
			anio++;
		
		// Cambiar el texto del a�o.
		textoSimulacion.setText("A�o " + anio);
		
		// Habilitar los controles.
		habilitarControles();
		
		// Repintar el subpanel.
		subpanelTemporalEcosistema.repaint();
	}
	
	/**
	 * M�todo que obtiene el valor del atributo botonAnterior.
	 *
	 * @return botonAnterior El valor del atributo botonAnterior.
	 */
	public JButton obtenerBotonAnterior()
	{
		return botonAnterior;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo botonSiguiente.
	 *
	 * @return botonSiguiente El valor del atributo botonSiguiente.
	 */
	public JButton obtenerBotonSiguiente()
	{
		return botonSiguiente;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo anio.
	 *
	 * @return anio El valor del atributo anio.
	 */
	public int obtenerAnio()
	{
		return anio;
	}
}