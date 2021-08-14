/**
 * @(#)PanelEvolucionOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector; 
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

/**
 * Clase que extiende de la clase JPanel. En esta clase permite al usuario ver
 * la evolución de la función de costos del algoritmo genético que resuelve el
 * problema operativo (transporte y distribución).
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Vector
 * @see JPanel
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see KeyEvent
 * @see FrameOperacion
 * @see SubPanelGraficarEvolucion
 * @see EventoButton
 * @see InterfacePanel
 */
public class PanelEvolucionOperacion extends JPanel implements InterfacePanel
{
	/** Frame que se hace referncia al creador de este objeto. */
	public FrameOperacion frameOperacion;
	
	/** Panel en cual se grafica la evolución del algoritmo genético. */
	public SubPanelGraficarEvolucion subPanelGraficarEvolucion;	
	
	/** Botón que permite atrasar la ejecución del algoritmo genético. */
	private  JButton botonAnterior;
	
	/** Botón que permite continuar la ejecución del algoritmo genético. */
	private JButton botonSiguiente;
	
	/** Vector que contiene los costos mínimos de cada generación. */
	private Vector evolucion;
	
	/** Sub-vector del vector de evolución. */
	private Vector subEvolucion;
	
	/** Indica la etapa analizada. */
	private int etapa;
	
	/**
	 * Método constructor en donde se crean e inicializan los componentes GUI
	 * que contiene este panel.
	 *
	 * @param frameOperacion Un objeto que hace referencia al FrameOperacion que
	 *                       contiene a este panel.
	 */
	public PanelEvolucionOperacion(FrameOperacion frameOperacion)
	{
		// Inicializar el puntero.
		this.frameOperacion = frameOperacion;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
     * Método que configura los elementos especiales que hay en el panel. Los
     * elementos especiales son: la etapa y los vectores de evolución y
     * sub-evolución.
     */
	private void configurarElementosEspeciales()
	{
		etapa = 0;
		evolucion = new Vector();
		subEvolucion = new Vector();
	}
	
	/**
	 * Método en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran corresponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este método.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * Método que configura los componentes que se encuentra en el panel de
     * evolución del modelo operativo.
     */
	public void configurarComponentes()
	{
		// mitad del frame
		int mitadFrame = frameOperacion.getWidth() / 2;
		
		// Crear titulo
		JLabel titulo = new JLabel(
			"Gráfico de la Evolución de la Función de Costos");
		
		// crear panel de gráfico
		subPanelGraficarEvolucion =
			new SubPanelGraficarEvolucion(this,0,40,823,480);
		
		// botones del panel de ejecución
		botonAnterior = new JButton("Anterior",
			new ImageIcon("../img/anterior.gif"));
		botonSiguiente = new JButton("Siguiente",
			new ImageIcon("../img/siguiente.gif"));
		
		// posicionar
		titulo.setBounds(mitadFrame-135,10,270,20);
		botonAnterior.setBounds(mitadFrame-180,525,140,30);
		botonSiguiente.setBounds(mitadFrame+40,525,140,30);
		
		// asignando eventos de tipo teclas a los botones
		botonAnterior.setMnemonic(KeyEvent.VK_A);
		botonSiguiente.setMnemonic(KeyEvent.VK_S);
		
		// asignando tips
		botonAnterior.setToolTipText(
			"Ver curva de las anteriores evoluciones");
		botonSiguiente.setToolTipText(
			"Ver curva de las siguientes evoluciones");
		
		// configurando elementos
		botonAnterior.setHorizontalTextPosition(JButton.RIGHT);
		botonSiguiente.setHorizontalTextPosition(JButton.LEFT);
		botonAnterior.setEnabled(false);
		botonSiguiente.setEnabled(false);
		
		// agregando al panel
		add(titulo);
		add(subPanelGraficarEvolucion);
		add(botonAnterior);
		add(botonSiguiente);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones que tiene el 
	 * panel de ejecución. En particular se incorpora el escuchador de eventos
	 * del tipo EventoButton a los JButton que tiene el panel.
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
	 * Método que actualiza los datos cada vez que se presiona el botón
	 * transportar.
	 *
	 * @param valor Costo máximo de la generación inicial.
	 */
	public void actualizarDatos(double valor)
	{
		etapa = 0;
		evolucion.removeAllElements();
		subPanelGraficarEvolucion.actualizarPlanoCartesiano(valor);
		subPanelGraficarEvolucion.asignarCurva(new Vector(), etapa);
		habilitarBotones();
	}
	
	/**
	 * Método que asigna en un vector los resultados de costo mínimo durante el
	 * transcurso de cada generación.
	 *
	 * @param e Vector con costos mínimos.
	 */
	public void asignarEvolucion(Vector e)
	{
		evolucion = e;
		if((evolucion.size() - 1) > (etapa + 15))
			botonSiguiente.setEnabled(true);
		else
			botonSiguiente.setEnabled(false);
		subPanelGraficarEvolucion.asignarCurva(obtenerEvolucion(), etapa);
	}
	
	/**
	 * Método que define un vector que es un sub-conjunto del vector de costos
	 * mínimos para efectos de graficar a tramos de generaciones.
	 *
	 * @return subEvolucion Vector subconjunto de vector de costos mínimos.
	 */
	private Vector obtenerEvolucion()
	{
		int i;
		int generaciones;
		
		subEvolucion.removeAllElements();
		
		i = etapa;
		generaciones = evolucion.size();
		
		while(i <= (etapa + 15) && i < generaciones)
		{
			double valor = Double.parseDouble("" + evolucion.elementAt(i));
			subEvolucion.add("" + valor);
			i++;
		}
		
		return subEvolucion;
	}
	
	/**
	 * Método que permite ver en la gráfica la anterior trama del vector de
	 * costos mínimos.
	 */
	public void retrocederEvolucion()
	{
		// decrementar la etapa
		etapa -= 15;
		
		// habilitar los botones
		habilitarBotones();
	}
	
	/**
	 * Método que permite ver en la gráfica la siguiente trama del vector de
	 * costos mínimos.
	 */
	public void continuarEvolucion()
	{
		// incrementar la etapa
		etapa += 15;
		
		// habilitar los botones
		habilitarBotones();
	}
	
	/**
	 * Método en donde se maneja la habilitación de los botones anterior y
	 * siguiente, dependiendo de si lo permite el vector de la evolución.
	 * Finalmente se grafica solo la parte del vector que interesa.
	 */
	private void habilitarBotones()
	{
		if(0 > (etapa - 15))
			botonAnterior.setEnabled(false);
		else
			botonAnterior.setEnabled(true);
		
		if((evolucion.size() - 1) > (etapa + 15))
			botonSiguiente.setEnabled(true);
		else
			botonSiguiente.setEnabled(false);
		
		subPanelGraficarEvolucion.asignarCurva(obtenerEvolucion(), etapa);
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