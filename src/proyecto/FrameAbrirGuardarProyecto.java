/**
 * @(#)FrameAbrirGuardarProyecto.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
 
/**
 * Clase que extiende de la clase JDialog. Permite abrir o guardar un proyecto
 * en la base de datos. Se despliega una lista con todos los proyectos
 * existentes en la base de datos y que son distintos del proyecto nuevo.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see ResultSet
 * @see SQLException
 * @see Toolkit
 * @see Container
 * @see KeyEvent
 * @see JDialog
 * @see JScrollPane
 * @see JLabel
 * @see JTextField
 * @see JButton
 * @see ImageIcon
 * @see EventoList
 * @see EventoButton
 * @see JOptionPane
 * @see FrameCircanaPro
 * @see InterfaceFrame
 */ 
public class FrameAbrirGuardarProyecto extends JDialog implements InterfaceFrame
{
	/** Frame que hace referencia al creador de este objeto. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Indica si el frame es llamado para abrir o guardar un proyecto. */
	private boolean opcionProyecto;	
	
	/** Texto que contiene el título de la ventana. */
	private String titulo;
	
	/** Panel desplazable que contiene a la tabla de proyectos. */
	private JScrollPane barraListaProyectos;
	
	/** Campo de texto con el nombre del proyecto. */
	private JTextField textoNombreProyecto;
	
	/** El botón "Aceptar" para abrir el proyecto seleccionado. */
	private JButton botonAceptar;
	
	/** El botón "Cancelar" para abortar la operación de abrir un proyecto. */
	private JButton botonCancelar;
	
	/** Tabla que contiene la lista de proyectos. */
	private TablaSwing listaProyectos;
	
	/** Código del proyecto seleccionado. */
	private int codigoProyecto;
	
	/** Vector con los códigos de los proyectos. */
	private int[] listaCodigoProyecto;
	
	/**
	 * Método constructor que configura el frame Abrir/Guardar Proyecto. Se
	 * configura la lista de proyectos, botones y eventos.
	 *
	 * @param frameCircanaPro Frame creaador de este objeto.
	 * @param opcionProyecto Opción de abrir o guardar un proyecto.
	 */
	public FrameAbrirGuardarProyecto(FrameCircanaPro frameCircanaPro,
									 boolean opcionProyecto)
	{
		super(frameCircanaPro, true);
		
		// Inicializar los punteros.
		this.frameCircanaPro = frameCircanaPro;
		this.frameCircanaPro.frameAbrirGuardarProyecto = this;
		
		// Tipo de opción abrir/guardar.
		this.opcionProyecto = opcionProyecto;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarFrame();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se inicializan los atributos que se usan en todo este
	 * frame. En particular, este frame maneja elementos especiales para el
	 * código del proyecto y el título del frame.
	 */
	public void configurarElementosEspeciales()
	{
		// Proyecto por defecto.
		codigoProyecto = -1;
		
		// Cuando es Abrir.
		if(!opcionProyecto)
			titulo = "Abrir Proyecto";
		
		// Cuando es Guardar.
		else
			titulo = "Guardar Proyecto"	;	
	}
	
	/**
	 * Método que configura las características de este frame.
	 */	
	public void configurarFrame()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		setTitle(titulo);
		setSize(600,400);
		setLocation(tk.getScreenSize().width/2-300,
					tk.getScreenSize().height/2-250);
		setResizable(false);
	}
	
	/**
	 * Método que configura los paneles de este frame y sus componentes GUI.
	 */	
	public void configurarComponentes()
	{
		// Capturar el container de este frame.
		Container contenedor = getContentPane();
		
		// Setear el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Crear los botones.
		if(opcionProyecto)		
			botonAceptar = new JButton("Guardar",
				new ImageIcon("../img/proyecto_guardar.gif"));
		else
			botonAceptar = new JButton("Abrir",
				new ImageIcon("../img/proyecto_abrir.gif"));
		
		botonCancelar = new JButton("Cancelar",
			new ImageIcon("../img/cancelar.gif"));		
		
		// Cabecera para la lista de proyectos.
		String[] cabecera = {"Nombre del Proyecto",
							 "Autor del Proyecto",
							 "Versión del Proyecto"};
		
		// Datos de la tabla.
		Object[][] datos = listarProyectos();
		
		// Crear una tabla para los proyectos.
		listaProyectos = new TablaSwing(cabecera, datos);
		listaProyectos.configurarEditableTabla(false);
		listaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Crear un scroll y agregar la lista de proyectos.
		barraListaProyectos = new JScrollPane(listaProyectos);	
		
		// Identificador del nombre del proyecto.
		JLabel labelNombreProyecto = new JLabel("Nombre Proyecto:");
		textoNombreProyecto = new JTextField();		
		
		// Posicionar los componentes del frame.
		barraListaProyectos.setBounds(20,20,560,200);
		labelNombreProyecto.setBounds(this.getWidth()/2-200,250,120,30);
		textoNombreProyecto.setBounds(this.getWidth()/2-70,250,250,30);
		botonAceptar.setBounds(this.getWidth()/2-130,300,120,30);
		botonCancelar.setBounds(this.getWidth()/2+10,300,120,30);
		textoNombreProyecto.setEditable(opcionProyecto);
		
		// Asignar acciones de teclas.
		botonCancelar.setMnemonic(KeyEvent.VK_C);
		if(opcionProyecto)		
			botonAceptar.setMnemonic(KeyEvent.VK_G);
		else
			botonAceptar.setMnemonic(KeyEvent.VK_A);
		
		// Asginar tool tips.
		botonCancelar.setToolTipText("Cerrar la ventana");
		if(opcionProyecto)		
			botonAceptar.setToolTipText("Guardar el proyecto");
		else
			botonAceptar.setToolTipText("Abrir el proyecto");
		textoNombreProyecto.setToolTipText("Ingrese el nombre del proyecto");
		
		// Agregar al contenedor.
		contenedor.add(barraListaProyectos);
		contenedor.add(labelNombreProyecto);
		contenedor.add(textoNombreProyecto);
		contenedor.add(botonAceptar);
		contenedor.add(botonCancelar);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos que tiene el panel de abrir o
	 * guardar proyecto. En particular se incorpora el escuchador de eventos
	 * del tipo EventoButton a los JButton que tiene el panel y el escuchador de
	 * eventos del tipo EventoList.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		EventoList eventoList = new EventoList(this);
		ListSelectionModel filaSeleccionada = listaProyectos.getSelectionModel();
		
		// Incorporar el escuchador de eventos a los botones.
		botonAceptar.addActionListener(eventoButton);
		botonCancelar.addActionListener(eventoButton);
		filaSeleccionada.addListSelectionListener(eventoList);
	}
	
	/**
	 * Método que obtiene todos los proyectos guardados en la base de datos.
	 *
	 * @return datos Arreglo que contiene los datos de la tabla.
	 */
	private Object[][] listarProyectos()
	{
		// Resultado de la consulta.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		resultado = Proyecto.obtenerTodosProyectos();
		
		Object[][] datos = new Object[0][3];
		listaCodigoProyecto = new int[0];
		
		try
		{			
			resultado.last();			
			datos = new Object[resultado.getRow()][3];
			listaCodigoProyecto = new int[resultado.getRow()];
			
			resultado.first();
			
			// Si hay proyectos guardados.
			if (resultado.getRow() != 0)
			{
				// Mientras no sea el último registro.
				while(!resultado.isAfterLast())
				{
					listaCodigoProyecto[resultado.getRow() - 1] =
						resultado.getInt("codigo_proyecto");
					datos[resultado.getRow() - 1][0] =
						""+resultado.getObject("nombre_proyecto");
					datos[resultado.getRow() - 1][1] =
						""+resultado.getObject("autor_proyecto");
					datos[resultado.getRow() - 1][2] =
						""+resultado.getObject("version_proyecto");
					
					resultado.next();
				}
			}
			
			// Si no hay proyectos guardados.
			else
			{
				// Cuando no hay proyectos en un cuadro de dialogo abrir.
				if(!opcionProyecto)
					botonAceptar.setEnabled(false);
			}
		}
		catch(SQLException ex)
		{
			// Si hay problemas al conectar la base datos.
			System.err.println("No se pudo listar los proyectos.");
		}
		
		// Desconectar la base de datos.
		Proyecto.CONEXION.desconectar();
		
		return datos;
	}
	
	/**
	 * Método que establece la fila seleccionada en la tabla de proyectos. Se 
	 * asigna el código correspondiente al proyecto seleccionado para abrir o 
	 * guardar.
	 *
	 * @param fila Indica la fila selecionada en la tabla.
	 */	
	public void establecerProyecto(int fila)
	{		
		Object[] registro = listaProyectos.obtenerFila(fila);
		
		codigoProyecto = listaCodigoProyecto[fila];
		
		textoNombreProyecto.setText(registro[0].toString());
	}
	
	/**
	 * Método que devuelve el código de proyecto seleccionado en la tabla de
	 * proyectos de la ventana de abrir/guardar proyecto.
	 *
	 * @return codigoProyecto Código del proyecto en la base de datos.
	 */
	public int obtenerCodigoProyecto()
	{
		return codigoProyecto;
	}
	
	/**
	 * Método que devuelve el nombre del proyecto de la ventana de abrir/guardar
	 * proyecto.
	 *
	 * @return textoNombreProyecto Nombre del proyecto.
	 */
	public String obtenerNombreProyecto()
	{
		return textoNombreProyecto.getText();
	}
	
	/**
	 * Método que devuelve si el proyecto seleccionado es llamado para abrir o
	 * guardar.
	 *
	 * @return opcionProyecto Abrir/Guardar el proyecto en la base de datos.
	 */
	public boolean obtenerOpcionProyecto()
	{
		return opcionProyecto;
	}
	
	/**
     * Método que retorna el botón aceptar.
     *
     * @return botonAceptar Botón "Aceptar" del frame.
     */
	public JButton obtenerBotonAceptar()
	{
		return botonAceptar;
	}
	
	/**
     * Método que retorna el botón cancelar.
     *
     * @return botonCancelar Botón "Cancelar" del frame.
     */
	public JButton obtenerBotonCancelar()
	{
		return botonCancelar;
	}
}