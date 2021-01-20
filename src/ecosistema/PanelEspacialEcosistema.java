/**
 * @(#)PanelEspacialEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import javax.swing.JPanel;

/**
 * Clase que extiende de la clase JPanel. Se especializa en contener dos
 * paneles, los cuales permiten al usuario: (1) ver la simulación espacial del
 * comportamiento del recurso estudiado en su ecosistema, y (2) controlar las
 * opciones que tiene la simulación espacial sobre el comportamiento del recurso
 * estudiado en su ecosistema.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see JPanel
 * @see FrameEcosistema
 * @see SubPanelMapaEcosistema
 * @see SubPanelControlEcosistema
 * @see InterfacePanel
 */
public class PanelEspacialEcosistema extends JPanel implements InterfacePanel
{
	/** El puntero al frame contenedor de este panel. */
	public FrameEcosistema frameEcosistema;
	
	/** El panel en donde se muestra la gráfica espacial del ecosistema. */
	public SubPanelMapaEcosistema subpanelMapaEcosistema;
	
	/** El panel que contiene los elementos de control de la simulación. */
	public SubPanelControlEcosistema subpanelControlEcosistema;
	
	/**
	 * Método constructor en donde se crean e inicializan los sub-paneles que
	 * contiene este panel. Además se incorporan los sub-paneles a este panel.
	 *
	 * @param frameEcosistema Un objeto que hace referencia al FrameEcosistema
	 *                        que contiene a este panel.
	 */
	public PanelEspacialEcosistema(FrameEcosistema frameEcosistema)
	{
		// Inicializar el frame padre de este panel.
		this.frameEcosistema = frameEcosistema;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
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
	 * Este panel contiene dos paneles: (1) para mostrar el mapa y, (2) mostrar
	 * los controles de la simulación.
	 */
	public void configurarComponentes()
	{
		// Crear los sub-paneles.
		subpanelMapaEcosistema =
			new SubPanelMapaEcosistema(this, 0, 0, 715, 553);
		subpanelControlEcosistema =
			new SubPanelControlEcosistema(this, 718, 0, 105, 553);
		
		// Incorporar los paneles.
		add(subpanelMapaEcosistema);
		add(subpanelControlEcosistema);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los componentes que tiene
	 * el panel de configuración. En particular no se incorporan eventos a este
	 * panel.
	 */
	public void configurarEventos()
	{
	}
}