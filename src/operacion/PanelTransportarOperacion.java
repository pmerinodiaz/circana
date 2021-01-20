/**
 * @(#)PanelTransportarOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestran al usuario
 * las opciones de asignación de costos y restricciones asociados al problema de
 * transporte de recursos. Además el usuario puede realizar el proceso de
 * transporte.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see JPanel
 * @see JButton
 * @see ImageIcon
 * @see InterfacePanel
 */
public class PanelTransportarOperacion extends JPanel implements InterfacePanel
{
	/** Frame que se hace referencia al creador de este objeto. */
	public FrameOperacion frameOperacion;
	
	/** Panel con la matriz de costos. */
	public SubPanelMatrizCostos subpanelCostos;
	
	/** Panel con las restricciones. */
	public SubPanelRestricciones subpanelRestricciones;
	
	/** Botón transportar del panel. */
	private JButton botonTransportar;
	
	/**
	 * Método constructor en donde se crean e inicializan los componentes GUI
	 * que contiene este panel.
	 *
	 * @param frameOperacion Un objeto que hace referencia al FrameOperacion que
	 *                       contiene a este panel.
	 */
	public PanelTransportarOperacion(FrameOperacion frameOperacion)
	{
		// Inicializar el puntero.
		this.frameOperacion = frameOperacion;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
		configurarEventos();
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
     * Método que configura el panel transportar del modelo operativo.
     */
	public void configurarComponentes()
	{
		// crear subpaneles
		subpanelCostos = new SubPanelMatrizCostos(this,10,15,800,230);
		subpanelRestricciones = new SubPanelRestricciones(this,10,255,800,245);
		
		// crear el botón
		botonTransportar = new JButton("Transportar",
							new ImageIcon("../img/iniciar.gif"));
		
		// posicionar el botón
		botonTransportar.setBounds(320,510,150,30);
		
		// asignar tips
		botonTransportar.setToolTipText("Ejecutar el transporte");
		
		// incorporar el panel
		add(subpanelCostos);
		add(subpanelRestricciones);
		add(botonTransportar);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones que tiene el 
	 * panel de transporte. En particular se incorpora el escuchador de eventos
	 * del tipo EventoButton a los JButton que tiene el panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.		
		botonTransportar.addActionListener(eventoButton);
	}
	
	/**
	 * Método que actualiza los paneles de costos y restricciones, cuando cambia
	 * el item seleccionado en el combobox de la fecha.
	 */
	public void actualizarPaneles()
	{
		int[] fecha = Servicio.obtenerFecha(subpanelRestricciones.obtenerFecha());
		int dia = Servicio.obtenerDia(fecha[0], fecha[1]);
		ConfiguracionAGT.establecerDiaAnio(dia, fecha[2]);
		subpanelCostos = new SubPanelMatrizCostos(this,10,15,800,230);
		this.remove(0);
		this.add(subpanelCostos,0);
		subpanelRestricciones.actualizarPaneles();
		this.updateUI();
	}
	
	/**
     * Método que establece nuevos valores de configuración del problema de
     * transporte.
     */
	public void aceptarTransporte()
	{
		// Establecer la matriz de costos y las restricciones.
		subpanelCostos.establecerMatrizCostos();
		subpanelRestricciones.establecerRestricciones();
		
		// Resetear las configuraciones.
		ConfiguracionAGT.resetPuntosFicticios();
		ConfiguracionAGT.balancearProblema();
	}
	
	/**
	 * Metodo que maneja la habilitacion/deshabilitacion de los botones del 
	 * panel de transporte. Generalmente se bloquean cuando los algoritmos
	 * genéticos se encuentran en ejecución.
	 *
	 * @param habilitado Indica si el botón se habilita o deshabilita.
	 */
	public void habilitarBotones(boolean habilitado)
	{
		botonTransportar.setEnabled(habilitado);
	}
	
	/**
     * Método que retorna el botón transportar.
     *
     * @return botonTransportar Botón transportar del panel.
     */
	public JButton obtenerBotonTransportar()
	{
		return botonTransportar;
	}
}