/*
 * @(#)PanelOpcionalProyecto.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario
 * un área de texto donde el usuario puede escribir en lenguaje natural las
 * observaciones hechas al proyecto, los autores y la versión.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Color
 * @see KeyEvent
 * @see JPanel
 * @see JLabel
 * @see JTextField
 * @see JTextArea
 * @see JScrollPane
 * @see JButton
 * @see ImageIcon
 * @see JOptionPane
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class PanelOpcionalProyecto extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** Apuntador al frame contenedor de este panel. */
	public FrameProyecto frameProyecto;
	
	/** Campo de texto para el autor del proyecto. */
	private JTextField campoAutor;	
	
	/** Campo de texto para la versión del proyecto. */
	private JTextField campoVersion;	
	
	/** Area de texto para la observación del proyecto. */
	private JTextArea areaObservacion;	
	
	/** Botón que establece la información opcional del proyecto. */
	private JButton botonEstablecer;
	
	/**
     * Constructor del panel opcional en el cual se configuran todos sus
     * componentes GUI. Recibe como parametro el frame contenedor de este panel.
     *
     * @param frameProyecto Creador del panel list que contiene a este panel.
     */
	public PanelOpcionalProyecto(FrameProyecto frameProyecto)
	{
		// Inicializar el puntero.
		this.frameProyecto = frameProyecto;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran en este panel correnponden a los
	 * atributos derivados de la clase JPanel, los cuales son modificados por
	 * este método.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * Método que configura los componentes GUI que tiene este panel. Este panel
     * contiene un panel de configuración: otra información. A su vez, cada
     * panel contiene diferentes componentes GUI.
     */
	public void configurarComponentes()
	{
		// Crear los componentes.
		botonEstablecer = new JButton("Establecer",
						  new ImageIcon("../img/establecer.gif"));
		
		// Posicionar y dimensionar.
		botonEstablecer.setBounds(340, 510, 120, 30);
		
		// Asignar los eventos de tecla.
		botonEstablecer.setMnemonic(KeyEvent.VK_E);
		
		// Asignar los tips.
		botonEstablecer.setToolTipText("Establecer los cambios en la "+
									   "información opcional del proyecto");
		
		// Agregar los componentes.
		add(botonEstablecer);
		
		// Configurar los paneles.
		configurarInformacionOpcional();
	}
	
	/**
	 * Método en donde se configuran los componentes que tiene el panel de la
	 * información opcional del proyecto. Este panel contiene dos campos de
	 * texto y un área de texto.
	 */
	private void configurarInformacionOpcional()
	{
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Crear el tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el título.
		TitledBorder titulo = new TitledBorder(borde, " Información Opcional ",
											   TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Agregar el borde.
		panel.setBorder(titulo);
								
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		panel.setBounds(10, 15, 800, 480);
		
		// Crear los componentes.
		JLabel[] etiquetaExplicacion = new JLabel[3];
		etiquetaExplicacion[0] = new JLabel(
								 new ImageIcon("../img/"+
								 "proyecto_informacion_opcional.gif"));
		etiquetaExplicacion[1] = new JLabel("Esta información es opcional "+
								 "para el proyecto y sirve para que Usted "+
								 "lleve un control de la información que se "+
								 "está");
		etiquetaExplicacion[2] = new JLabel("manejando en este proyecto. A "+
								 "continuación ingrese el nombre de el autor, "+
								 "la versión y la observación del proyecto.");
		JLabel etiquetaAutor = new JLabel("Autor:");
		JLabel etiquetaVersion = new JLabel("Versión:");
		JLabel etiquetaObservacion = new JLabel("Observación:");
		campoAutor = new JTextField(Proyecto.obtenerAutor());
		campoVersion = new JTextField(Proyecto.obtenerVersion());
		areaObservacion =  new JTextArea(Proyecto.obtenerObservacion());
		JScrollPane barraObservacion = new JScrollPane(areaObservacion);
		
		// Posicionar y dimensionar los componentes.
		etiquetaExplicacion[0].setBounds(20, 25, 50, 50);
		etiquetaExplicacion[1].setBounds(80, 25, 760, 15);
		etiquetaExplicacion[2].setBounds(80, 40, 760, 15);
		etiquetaAutor.setBounds(80, 75, 60, 20);
		campoAutor.setBounds(170, 75, 190, 20);
		etiquetaVersion.setBounds(80, 105, 60, 20);
		campoVersion.setBounds(170, 105, 190, 20);
		etiquetaObservacion.setBounds(80, 135, 90, 20);
		barraObservacion.setBounds(170, 135, 400, 300);
		
		// Asignar los tips.
		campoAutor.setToolTipText("Ingrese el nombre del autor del proyecto");
		campoVersion.setToolTipText("Ingrese la versión del proyecto");
		areaObservacion.setToolTipText("Ingrese las observaciones del proyecto");
		
		// Agregar los componentes.
		panel.add(etiquetaExplicacion[0]);
		panel.add(etiquetaExplicacion[1]);
		panel.add(etiquetaExplicacion[2]);
		panel.add(etiquetaAutor);
		panel.add(campoAutor);
		panel.add(etiquetaVersion);
		panel.add(campoVersion);
		panel.add(etiquetaObservacion);
		panel.add(barraObservacion);
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones de acceso
	 * rápido que tiene el panel de configuracion. En particular se incorpora el 
	 * escuchador de eventos del tipo EventoBoton a los JButton que tiene el
	 * panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonEstablecer.addActionListener(eventoButton);
	}
	
	/**
     * Método que carga la configuración del proyecto en el panel de la
     * información opcional del proyecto. La información opcional que se carga
     * está almacenada en la clase Proyecto.
     */
	public void cargarConfiguracion()	
	{
		// Cargar el autor.
		campoAutor.setText(Proyecto.obtenerAutor());
		
		// Cargar la versión.
		campoVersion.setText(Proyecto.obtenerVersion());
		
		// Cargar la observacion.
		areaObservacion.setText(Proyecto.obtenerObservacion());
	}
	
	/**
	 * Método que establece la configuración por defecto. Se eligen y setean los
	 * valores de los componentes que tiene este panel de configuración con los
	 * valores por defecto. En particular, este método no es implementado porque
	 * no existe la opción de restaurar las opciones.
	 */
	public void restaurarConfiguracion()
	{
	}
	
	/**
     * Método que establece una configuración en el proyecto con los valores
     * selecionados en el panel de la información opcional del proyecto.
     */
	public void establecerConfiguracion()
	{
		// Validar la configuración.
		String error = validarConfiguracion();
		
		// Cuando no ocurrieron errores.
		if (error == "")
		{
			// Establecer los datos del proyecto.
			Proyecto.establecerAutor(campoAutor.getText());
			Proyecto.establecerVersion(campoVersion.getText());
			Proyecto.establecerObservacion(areaObservacion.getText());
			Proyecto.establecerModificado(true);
		}
		
		// Cuando si ocurrieron errores.
		else
		{
			error = "Se han cometido los siguientes errores:\n" + error;
			JOptionPane.showMessageDialog(this, error,
				"Error en la sintaxis", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * Método que valida la configuración. Por cada error cometido se agrega una
     * mensaje al string del error. Un error se evalúa según los tipos de datos
     * y los rangos de cada item de la configuración.
     * 
     * @return error String con los errores cometidos en la configuración.
     */
	public String validarConfiguracion()
	{
		// Variables temporales.
		String error = "";
		
		// Validar el autor.
		if (campoAutor.getText().indexOf("'") != -1)
			error+= "- Error en el autor del proyecto.\n";
		
		// Validar la versión.
		if (campoVersion.getText().indexOf("'") != -1)
			error+= "- Error en la versión del proyecto.\n";
		
		// Validar la observación.
		if (areaObservacion.getText().indexOf("'") != -1)
			error+= "- Error en la observación del proyecto.\n";
		
		return error;
	}
	
	/**
     * Método en donde se maneja la habilitación o deshabilitación de los
     * botones principales de de la configuración (establecer). En particular,
     * este método no es implementado porque no se necesita deshabilitar o
     * habilitar los botones de este panel, dada cualquier circunstancia.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
	public void habilitarBotones(boolean habilitado)
	{
	}
	
	/**
     * Método que entrega el botón establecer.
     *
     * @return botonEstablecer El botón establecer.
     */
	public JButton obtenerBotonEstablecer()
	{
		return botonEstablecer;
	}
}