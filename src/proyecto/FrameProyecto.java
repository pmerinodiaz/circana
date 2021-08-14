/**
 * @(#)FrameProyecto.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

/**
 * Clase que extiende de la clase JInternalFrame. Este frame contiene todos los
 * componentes GUI del módulo proyecto que tiene la aplicación. Gracias a estos
 * componentes el usuario puede interactuar en forma simple con las opciones que
 * contiene el módulo proyecto. Las opciones que contiene el módulo proyecto son
 * las siguientes: la configuración y la información opcional. Cada una de ellas
 * son incorporadas a este frame a través de un panel, el cual se adjunta a este
 * frame mediante una paleta de paneles.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Container
 * @see KeyEvent
 * @see JInternalFrame
 * @see JTabbedPane
 * @see ImageIcon
 * @see PanelEscritorio
 * @see PanelConfiguracionProyecto
 * @see PanelOpcionalProyecto
 * @see InterfaceInternalFrame
 */
public class FrameProyecto extends JInternalFrame
	implements InterfaceInternalFrame
{
	/** Apuntador al panel que contiene a este frame. */
	public PanelEscritorio panelEscritorio;
	
	/** El panel dedicado a mostrar la configuración del proyecto. */
	public PanelConfiguracionProyecto panelConfiguracionProyecto;
	
	/** El panel dedicado a mostrar la información opcional del proyecto. */
	public PanelOpcionalProyecto panelOpcionalProyecto;
	
	/** La paleta que contiene a todos los paneles del frame. */
	private JTabbedPane paleta;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el frame, los
	 * paneles y los eventos asociados de los componentes GUI que contiene este
	 * frame.
	 *
	 * @param panelEscritorio El objeto que hace referencia al panel que
	 *                        contiene a este frame.
	 */
	public FrameProyecto(PanelEscritorio panelEscritorio)
	{
		// Inicializar el puntero.
		this.panelEscritorio = panelEscritorio;
		
		// Configurar.
		configurarFrame();
		configurarComponentes();
	}
	
	/**
	 * Método en donde se inicializan los objetos que se usan en todo el módulo
	 * proyecto. Este método no es llamado y no necesita implementación alguna.
	 * Está declarado en esta clase porque debe implementarse la interface.
	 */
	public void configurarElementosEspeciales()
	{
	}
	
	/**
	 * Método en donde se configuran algunas propiedades de este frame. Las
	 * propiedades que se configuran corresponden a los atributos derivados
	 * desde la clase JInternalFrame.
	 */
	public void configurarFrame()
	{
		setTitle("Proyecto");
		setResizable(true);
		setClosable(false);
		setMaximizable(true);
		setIconifiable(true);
		setSize(panelEscritorio.getSize());
		setFrameIcon(new ImageIcon("../img/circana_pro.gif"));
	}
	
	/**
	 * Método en donde se configuran los componentes de este frame. Estos
	 * componentes son los siguientes: el panel de la configuración del proyecto
	 * y el panel de la información opcional del proyecto. Los paneles son
	 * adjuntados al frame através de una paleta de paneles.
	 */
	public void configurarComponentes()
	{
		// Capturar el panel contenedor del frame.
		Container contenedor = getContentPane();
		
		// Crear la paleta para los paneles.
		paleta = new JTabbedPane();
		
		// Crear los paneles.
		panelConfiguracionProyecto = new PanelConfiguracionProyecto(this);
		panelOpcionalProyecto = new PanelOpcionalProyecto(this);
		
		// Incorporar los paneles a la paleta.
		paleta.addTab("Configuración",
					  new ImageIcon("../img/proyecto_configuracion.gif"),
					  panelConfiguracionProyecto,
					  "Configuración del Proyecto");
		paleta.addTab("Información Opcional",
					  new ImageIcon("../img/proyecto_opcional.gif"),
					  panelOpcionalProyecto,
					  "Información Opcional del Proyecto");
		
		// Adjuntar un comando único a cada panel de la paleta.
		paleta.setMnemonicAt(0, KeyEvent.VK_C);
		paleta.setMnemonicAt(1, KeyEvent.VK_I);
		
		// Incorporar la paleta al contenedor.
		contenedor.add(paleta);
	}
	
	/**
	 * Método que muestra la ventanita configuración del proyecto en el panel de
	 * escritorio. Se setea el foco en el panel configuración.
	 */
	public void elegirConfiguracion()
	{
		paleta.setSelectedIndex(0);
	}
	
	/**
	 * Método que muestra la ventanita información opcional en el panel de
	 * escritorio. Se setea el foco en el panel información opcional.
	 */
	public void elegirOpcional()
	{
		paleta.setSelectedIndex(1);
	}

	/**
     * Método que carga la información del proyecto en el frame interno del
     * proyecto. La información cargada se encuentra almacenada en clase
     * proyecto.
     */
	public void cargarConfiguracion()
	{
		panelConfiguracionProyecto.cargarConfiguracion();
		panelOpcionalProyecto.cargarConfiguracion();
	}
	
	/**
     * Método que carga la información con respecto al proyecto que se encuentra 
     * en la clase estática Proyecto. De esta manera, se actualizan las opciones
     * del proyecto que se encuentran relacionadas con el proyecto. Este método
     * no necesita ser implementado, porque no se realizan acciones del tipo de
     * actualización.
     */
	public void actualizarInformacionProyecto()
	{
	}
}