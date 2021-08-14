/**
 * @(#)PanelTemporalEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JPanel. Se especializa en contener un panel
 * que permite al usuario ver la dinámica temporal del recurso estudiado y
 * contener cuatro botones que permiten al usuario moverse en los años del
 * gráfico.
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
	
	/** El panel en donde se muestra la gráfica temporal del ecosistema. */
	public SubPanelTemporalEcosistema subpanelTemporalEcosistema;
	
	/** Botón que permite mover las gráficas al año anterior. */
	private JButton botonAnterior;
	
	/** Botón que permite mover las gráficas al año siguiente. */
	private JButton botonSiguiente;
	
	/** Texto que muestra el tiempo de simulación. */
	private JLabel textoSimulacion;
	
	/** El año que se está graficando en el panel. */
	private int anio;
	
	/**
	 * Método constructor en donde se crean e inicializan los componentes GUI
	 * que contiene este panel. Primero se inicializa el puntero al frame que
	 * contiene a este panel. Luego se llama a los métodos que configurar este
	 * panel y después se configuran los eventos que contiene este panel.
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
	 * Método en donde se inicializan los atributos que se usan en todo este
	 * panel.
	 */
	private void configurarElementosEspeciales()
	{
		// Establecer el año.
		anio = Proyecto.obtenerFechaInicialFormatoInt()[2];
	}
	
	/**
	 * Método en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran correnponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este método.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
	 * Método en donde se configuran los componentes que contiene este panel.
	 * Este panel contiene un panel y cuatro botones: uno que permite al usuario
	 * retroceder los gráficos y otro que permite al usuario avanzar los
	 * gráficos.
	 */
	public void configurarComponentes()
	{
		// Obtener la mitad del frame.
		int mitadFrame = frameEcosistema.getWidth() / 2;
		
		// Crear los componentes que tiene este panel.
		textoSimulacion = new JLabel("Año " + anio);
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
		botonAnterior.setToolTipText("Ver curvas del año anterior");
		botonSiguiente.setToolTipText("Ver curvas del año siguiente");
		
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
	 * Método que adjunta los escuchadores eventos a los componentes que tiene
	 * este panel de configuración. En particular se incorpora el escuchador de
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
	 * Método que configura los botones de navegación. Específicamente se
	 * establece la propiedad de habilitación de los botones de navegación,
	 * dependiendo del estado actual en que se encuentra la simulación.
	 */
	private void habilitarControles()
	{
		// Cuando el año es mayor que el año inicial.
		if (Proyecto.obtenerFechaInicialFormatoInt()[2] < anio)
			botonAnterior.setEnabled(true);
		
		// Cuando el año es menor o igual que el año inicial.
		else botonAnterior.setEnabled(false);
		
		// Cuando el año es menor que el año final.
		if (anio < Proyecto.obtenerFechaFinalFormatoInt()[2])
			botonSiguiente.setEnabled(true);
		
		// Cuando el año es mayor o igual que el año final.
		else botonSiguiente.setEnabled(false);
	}
	
	/**
	 * Método que cambia el año del gráfico al año anterior.
	 */
	public void cambiarAnioAnterior()
	{
		// Cambiar el año.
		if (Proyecto.obtenerFechaInicialFormatoInt()[2] < anio)
			anio--;
		
		// Cambiar el texto del año.
		textoSimulacion.setText("Año " + anio);
		
		// Habilitar los controles.
		habilitarControles();
		
		// Repintar el subpanel.
		subpanelTemporalEcosistema.repaint();
	}
	
	/**
	 * Método que cambia el año del gráfico al año siguiente.
	 */
	public void cambiarAnioSiguiente()
	{
		// Cambiar el año.
		if (anio < Proyecto.obtenerFechaFinalFormatoInt()[2])
			anio++;
		
		// Cambiar el texto del año.
		textoSimulacion.setText("Año " + anio);
		
		// Habilitar los controles.
		habilitarControles();
		
		// Repintar el subpanel.
		subpanelTemporalEcosistema.repaint();
	}
	
	/**
	 * Método que obtiene el valor del atributo botonAnterior.
	 *
	 * @return botonAnterior El valor del atributo botonAnterior.
	 */
	public JButton obtenerBotonAnterior()
	{
		return botonAnterior;
	}
	
	/**
	 * Método que obtiene el valor del atributo botonSiguiente.
	 *
	 * @return botonSiguiente El valor del atributo botonSiguiente.
	 */
	public JButton obtenerBotonSiguiente()
	{
		return botonSiguiente;
	}
	
	/**
	 * Método que obtiene el valor del atributo anio.
	 *
	 * @return anio El valor del atributo anio.
	 */
	public int obtenerAnio()
	{
		return anio;
	}
}