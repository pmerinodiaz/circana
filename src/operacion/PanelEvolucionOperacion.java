/**
 * @(#)PanelEvolucionOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Vector; 
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

/**
 * Clase que extiende de la clase JPanel. En esta clase permite al usuario ver
 * la evoluci�n de la funci�n de costos del algoritmo gen�tico que resuelve el
 * problema operativo (transporte y distribuci�n).
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
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
	
	/** Panel en cual se grafica la evoluci�n del algoritmo gen�tico. */
	public SubPanelGraficarEvolucion subPanelGraficarEvolucion;	
	
	/** Bot�n que permite atrasar la ejecuci�n del algoritmo gen�tico. */
	private  JButton botonAnterior;
	
	/** Bot�n que permite continuar la ejecuci�n del algoritmo gen�tico. */
	private JButton botonSiguiente;
	
	/** Vector que contiene los costos m�nimos de cada generaci�n. */
	private Vector evolucion;
	
	/** Sub-vector del vector de evoluci�n. */
	private Vector subEvolucion;
	
	/** Indica la etapa analizada. */
	private int etapa;
	
	/**
	 * M�todo constructor en donde se crean e inicializan los componentes GUI
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
     * M�todo que configura los elementos especiales que hay en el panel. Los
     * elementos especiales son: la etapa y los vectores de evoluci�n y
     * sub-evoluci�n.
     */
	private void configurarElementosEspeciales()
	{
		etapa = 0;
		evolucion = new Vector();
		subEvolucion = new Vector();
	}
	
	/**
	 * M�todo en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran corresponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este m�todo.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * M�todo que configura los componentes que se encuentra en el panel de
     * evoluci�n del modelo operativo.
     */
	public void configurarComponentes()
	{
		// mitad del frame
		int mitadFrame = frameOperacion.getWidth() / 2;
		
		// Crear titulo
		JLabel titulo = new JLabel(
			"Gr�fico de la Evoluci�n de la Funci�n de Costos");
		
		// crear panel de gr�fico
		subPanelGraficarEvolucion =
			new SubPanelGraficarEvolucion(this,0,40,823,480);
		
		// botones del panel de ejecuci�n
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
	 * M�todo que adjunta los escuchadores eventos a los botones que tiene el 
	 * panel de ejecuci�n. En particular se incorpora el escuchador de eventos
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
	 * M�todo que actualiza los datos cada vez que se presiona el bot�n
	 * transportar.
	 *
	 * @param valor Costo m�ximo de la generaci�n inicial.
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
	 * M�todo que asigna en un vector los resultados de costo m�nimo durante el
	 * transcurso de cada generaci�n.
	 *
	 * @param e Vector con costos m�nimos.
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
	 * M�todo que define un vector que es un sub-conjunto del vector de costos
	 * m�nimos para efectos de graficar a tramos de generaciones.
	 *
	 * @return subEvolucion Vector subconjunto de vector de costos m�nimos.
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
	 * M�todo que permite ver en la gr�fica la anterior trama del vector de
	 * costos m�nimos.
	 */
	public void retrocederEvolucion()
	{
		// decrementar la etapa
		etapa -= 15;
		
		// habilitar los botones
		habilitarBotones();
	}
	
	/**
	 * M�todo que permite ver en la gr�fica la siguiente trama del vector de
	 * costos m�nimos.
	 */
	public void continuarEvolucion()
	{
		// incrementar la etapa
		etapa += 15;
		
		// habilitar los botones
		habilitarBotones();
	}
	
	/**
	 * M�todo en donde se maneja la habilitaci�n de los botones anterior y
	 * siguiente, dependiendo de si lo permite el vector de la evoluci�n.
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