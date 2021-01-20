/*
 * @(#)PanelOpcionalProyecto.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
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
 * un �rea de texto donde el usuario puede escribir en lenguaje natural las
 * observaciones hechas al proyecto, los autores y la versi�n.
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
	
	/** Campo de texto para la versi�n del proyecto. */
	private JTextField campoVersion;	
	
	/** Area de texto para la observaci�n del proyecto. */
	private JTextArea areaObservacion;	
	
	/** Bot�n que establece la informaci�n opcional del proyecto. */
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
	 * M�todo en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran en este panel correnponden a los
	 * atributos derivados de la clase JPanel, los cuales son modificados por
	 * este m�todo.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * M�todo que configura los componentes GUI que tiene este panel. Este panel
     * contiene un panel de configuraci�n: otra informaci�n. A su vez, cada
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
									   "informaci�n opcional del proyecto");
		
		// Agregar los componentes.
		add(botonEstablecer);
		
		// Configurar los paneles.
		configurarInformacionOpcional();
	}
	
	/**
	 * M�todo en donde se configuran los componentes que tiene el panel de la
	 * informaci�n opcional del proyecto. Este panel contiene dos campos de
	 * texto y un �rea de texto.
	 */
	private void configurarInformacionOpcional()
	{
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Crear el tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde, " Informaci�n Opcional ",
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
		etiquetaExplicacion[1] = new JLabel("Esta informaci�n es opcional "+
								 "para el proyecto y sirve para que Usted "+
								 "lleve un control de la informaci�n que se "+
								 "est�");
		etiquetaExplicacion[2] = new JLabel("manejando en este proyecto. A "+
								 "continuaci�n ingrese el nombre de el autor, "+
								 "la versi�n y la observaci�n del proyecto.");
		JLabel etiquetaAutor = new JLabel("Autor:");
		JLabel etiquetaVersion = new JLabel("Versi�n:");
		JLabel etiquetaObservacion = new JLabel("Observaci�n:");
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
		campoVersion.setToolTipText("Ingrese la versi�n del proyecto");
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
	 * M�todo que adjunta los escuchadores eventos a los botones de acceso
	 * r�pido que tiene el panel de configuracion. En particular se incorpora el 
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
     * M�todo que carga la configuraci�n del proyecto en el panel de la
     * informaci�n opcional del proyecto. La informaci�n opcional que se carga
     * est� almacenada en la clase Proyecto.
     */
	public void cargarConfiguracion()	
	{
		// Cargar el autor.
		campoAutor.setText(Proyecto.obtenerAutor());
		
		// Cargar la versi�n.
		campoVersion.setText(Proyecto.obtenerVersion());
		
		// Cargar la observacion.
		areaObservacion.setText(Proyecto.obtenerObservacion());
	}
	
	/**
	 * M�todo que establece la configuraci�n por defecto. Se eligen y setean los
	 * valores de los componentes que tiene este panel de configuraci�n con los
	 * valores por defecto. En particular, este m�todo no es implementado porque
	 * no existe la opci�n de restaurar las opciones.
	 */
	public void restaurarConfiguracion()
	{
	}
	
	/**
     * M�todo que establece una configuraci�n en el proyecto con los valores
     * selecionados en el panel de la informaci�n opcional del proyecto.
     */
	public void establecerConfiguracion()
	{
		// Validar la configuraci�n.
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
     * M�todo que valida la configuraci�n. Por cada error cometido se agrega una
     * mensaje al string del error. Un error se eval�a seg�n los tipos de datos
     * y los rangos de cada item de la configuraci�n.
     * 
     * @return error String con los errores cometidos en la configuraci�n.
     */
	public String validarConfiguracion()
	{
		// Variables temporales.
		String error = "";
		
		// Validar el autor.
		if (campoAutor.getText().indexOf("'") != -1)
			error+= "- Error en el autor del proyecto.\n";
		
		// Validar la versi�n.
		if (campoVersion.getText().indexOf("'") != -1)
			error+= "- Error en la versi�n del proyecto.\n";
		
		// Validar la observaci�n.
		if (areaObservacion.getText().indexOf("'") != -1)
			error+= "- Error en la observaci�n del proyecto.\n";
		
		return error;
	}
	
	/**
     * M�todo en donde se maneja la habilitaci�n o deshabilitaci�n de los
     * botones principales de de la configuraci�n (establecer). En particular,
     * este m�todo no es implementado porque no se necesita deshabilitar o
     * habilitar los botones de este panel, dada cualquier circunstancia.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
	public void habilitarBotones(boolean habilitado)
	{
	}
	
	/**
     * M�todo que entrega el bot�n establecer.
     *
     * @return botonEstablecer El bot�n establecer.
     */
	public JButton obtenerBotonEstablecer()
	{
		return botonEstablecer;
	}
}