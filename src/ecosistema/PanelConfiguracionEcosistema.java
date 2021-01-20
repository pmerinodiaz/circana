/**
 * @(#)PanelConfiguracionEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario
 * las opciones de configuraci�n que tiene el m�dulo ecosistema. Principalmente
 * contiene opciones para configurar el aut�mata celular, la din�mica espacial,
 * la din�mica temporal y las cuotas de captura. Cada una de estas
 * configuraciones son agregadas al panel a trav�s de paneles.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see Color
 * @see Font
 * @see KeyEvent
 * @see JPanel
 * @see JComboBox
 * @see JButton
 * @see JLabel
 * @see JCheckBox
 * @see ImageIcon
 * @see SwingConstants
 * @see JOptionPane
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see CampoNumerico
 * @see FrameEcosistema
 * @see ConfiguracionAC
 * @see ConfiguracionEspacial
 */
public class PanelConfiguracionEcosistema extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** El puntero al frame contenedor de este panel. */
	public FrameEcosistema frameEcosistema;
	
	/** Combo box para seleccionar el tipo de l�mite del aut�mata celular. */
	private JComboBox comboLimite;
	
	/** Combo box para seleccionar el tipo de vecindad del aut�mata celular. */
	private JComboBox comboVecindad;
	
	/** Combo box para seleccionar la unidad de tiempo del aut�mata celular. */
	private JComboBox comboTiempo;
	
	/** Combo box para seleccionar el tipo de vista de la din�mica espacial. */
	private JComboBox comboVista;
	
	/**
	 * Check box para seleccionar los recursos vistos en la din�mica espacial.
	 */
	private JCheckBox[] checkEspacial;
	
	/**
	 * Check box para seleccionar los recursos vistos en la din�mica temporal.
	 */
	private JCheckBox[] checkTemporal;
	
	/**
	 * Campo de texto para ingresar el porcentaje del excedente de biomasa de
	 * los recursos.
	 */
	private CampoNumerico[] campoPorcentaje;
	
	/**
	 * Combo box para seleccionar la calidad considerada en la cuota del
	 * recurso.
	 */
	private JComboBox[] comboCalidad;
	
	/** Bot�n que permite restaurar la configuraci�n por defecto. */
	private JButton botonRestaurar;
	
	/** Bot�n que permite aplicar la nueva configuraci�n. */
	private JButton botonEstablecer;
	
	/**
	 * M�todo constructor en donde se crean e inicializan los componentes GUI
	 * que contiene este panel. Primero se inicializa el puntero al frame que
	 * contiene a este panel. Luego se llama a los m�todos que configurar este
	 * panel, los paneles que contiene este panel y los eventos que contiene
	 * este panel.
	 *
	 * @param frameEcosistema Un objeto que hace referencia al FrameEcosistema
	 *                        que contiene a este panel.
	 */
	public PanelConfiguracionEcosistema(FrameEcosistema frameEcosistema)
	{
		// Apuntar al frame contenedor de este panel.
		this.frameEcosistema = frameEcosistema;
		
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
     * contiene cuatro paneles de configuraci�n: el aut�mata celular, la
     * din�mica espacial, la din�mica temporal y las cuotas de captura. A su
     * vez, cada panel contiene diferentes componentes GUI.
     */
	public void configurarComponentes()
	{
		// Crear los componentes que tiene este panel.
		JLabel recomendacion = new JLabel("Se aconseja seguir la "+
							   "recomendaci�n dada para cada item.",
							   new ImageIcon("../img/recomendacion.gif"),
							   JLabel.LEFT);
		botonRestaurar = new JButton("Restaurar",
						 new ImageIcon("../img/restaurar.gif"));
		botonEstablecer = new JButton("Establecer",
					   new ImageIcon("../img/establecer.gif"));
		
		// Posicionar y dimensionar.
		recomendacion.setBounds(10, 525, 400, 30);
		botonRestaurar.setBounds(530, 525, 135, 30);
		botonEstablecer.setBounds(675, 525, 135, 30);
		
		// Asignar los eventos de tecla.
		botonRestaurar.setMnemonic(KeyEvent.VK_R);
		botonEstablecer.setMnemonic(KeyEvent.VK_E);
		
		// Asignar los tips.
		botonRestaurar.setToolTipText(
			"Restaurar la configuraci�n por defecto del ecosistema");
		botonEstablecer.setToolTipText(
			"Establecer los cambios en la configuraci�n del ecosistema");
		
		// Agregar los componentes a este panel.
		add(recomendacion);
		add(botonRestaurar);
		add(botonEstablecer);
		
		// Configurar los paneles.
		configurarAutomataCelular();
		configurarDinamicaEspacial();
		configurarDinamicaTemporal();
		configurarCuotasCaptura();
	}
	
	/**
	 * M�todo en donde se configura el panel de configuraci�n del aut�mata
	 * celular. El panel de configuraci�n del aut�mata celular contiene las
	 * opciones para el tipo de l�mite del espacio, el tipo de vecindad y el
	 * tipo de unidad del tiempo.
	 */
	private void configurarAutomataCelular()
	{
		// Variables temporales.
		int i;
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Crear el borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde,
							  " Configuraci�n del Aut�mata Celular ",
							  TitledBorder.LEFT, TitledBorder.TOP);
		
		// Establecer el borde.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		panel.setBounds(10, 10, 395, 250);
		
		// Crear los componentes para el panel.
		JLabel[] etiqueta = new JLabel[4];
		etiqueta[0] = new JLabel(new ImageIcon("../img/" +
					  "ecosistema_configuracion_ac.gif"));
		etiqueta[1] = new JLabel("Puede cambiar los componentes b�sicos del "+
					  "aut�mata");
		etiqueta[2] = new JLabel("celular usado para modelar el ecosistema "+
					  "marino de");
		etiqueta[3] = new JLabel("estudio.");
		JLabel etiquetaLimite = new JLabel("L�mite:");
		JLabel etiquetaVecindad = new JLabel("Vecindad:");
		JLabel etiquetaTiempo = new JLabel("Tiempo:");
		comboLimite = new JComboBox();
		comboVecindad = new JComboBox();
		comboTiempo = new JComboBox();
		JLabel recomendacionLimite = new JLabel("Infinito",
									 new ImageIcon("../img/recomendacion.gif"),
									 SwingConstants.LEFT);
		JLabel recomendacionVecindad = new JLabel("Moore",
									 new ImageIcon("../img/recomendacion.gif"),
									 SwingConstants.LEFT);
		JLabel recomendacionTiempo = new JLabel("D�a",
									 new ImageIcon("../img/recomendacion.gif"),
									 SwingConstants.LEFT);
		
		// Cargar los combos para el panel.
		cargarComboLimite();
		cargarComboVecindad();
		cargarComboTiempo();
		
		// Posicionar y dimensionar los componentes para el panel.
		etiqueta[0].setBounds(20, 40, 40, 40);
		etiqueta[1].setBounds(75, 40, 330, 15);
		etiqueta[2].setBounds(75, 55, 330, 15);
		etiqueta[3].setBounds(75, 70, 330, 15);
		etiquetaLimite.setBounds(20, 110, 60, 20);
		comboLimite.setBounds(90, 110, 150, 20);
		recomendacionLimite.setBounds(250, 110, 150, 20);
		etiquetaVecindad.setBounds(20, 140, 60, 20);
		comboVecindad.setBounds(90, 140, 150, 20);
		recomendacionVecindad.setBounds(250, 140, 150, 20);
		etiquetaTiempo.setBounds(20, 170, 60, 20);
		comboTiempo.setBounds(90, 170, 150, 20);
		recomendacionTiempo.setBounds(250, 170, 150, 20);
		
		// Asignar los tips.
		comboLimite.setToolTipText("Seleccione el tipo de l�mite del espacio");
		comboVecindad.setToolTipText("Seleccione el tipo de vecindad");
		comboTiempo.setToolTipText("Seleccione la unidad de tiempo");
		
		// Crear la fuente para la recomendaci�n.
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		
		// Establecer la fuente.
		recomendacionLimite.setFont(fuente);
		recomendacionVecindad.setFont(fuente);
		recomendacionTiempo.setFont(fuente);
		
		// Agregar los componentes para el panel.
		panel.add(etiqueta[0]);
		panel.add(etiqueta[1]);
		panel.add(etiqueta[2]);
		panel.add(etiqueta[3]);
		panel.add(etiquetaLimite);
		panel.add(comboLimite);
		panel.add(recomendacionLimite);
		panel.add(etiquetaVecindad);
		panel.add(comboVecindad);
		panel.add(recomendacionVecindad);
		panel.add(etiquetaTiempo);
		panel.add(comboTiempo);
		panel.add(recomendacionTiempo);
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * M�todo en donde se configura el panel de configuraci�n de la din�mica
	 * espacial. El panel de configuraci�n de la din�mica espacial contiene las
	 * opciones para el tipo de vista y la posibilidad de mostrar o no el
	 * recurso.
	 */
	private void configurarDinamicaEspacial()
	{
		// Variables temporales.
		int i;
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Crear el borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde,
							  " Configuraci�n de la Din�mica Espacial ",
							  TitledBorder.LEFT, TitledBorder.TOP);
		
		// Establecer el borde.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		panel.setBounds(10, 270, 395, 250);
		
		// Crear los componentes para el panel.
		JLabel[] etiqueta = new JLabel[3];
		etiqueta[0] = new JLabel(new ImageIcon("../img/"+
					  "ecosistema_configuracion_espacial.gif"));
		etiqueta[1] = new JLabel("Puede especificar el tipo de vista de la "+
					  "din�mica");
		etiqueta[2] = new JLabel("espacial y los recursos que se desean "+
					  "mostrar.");
		JLabel etiquetaVista = new JLabel("Vista:");
		comboVista = new JComboBox();
		JLabel recomendacionVista = new JLabel("Longitud-Latitud",
									new ImageIcon("../img/recomendacion.gif"),
									SwingConstants.LEFT);
		JLabel etiquetaMostrar = new JLabel("Mostrar:");
		checkEspacial = new JCheckBox[cantidadRecursos];
		JLabel[] color = new JLabel[cantidadRecursos];
		for (i=0; i<cantidadRecursos; i++)
		{
			if (ConfiguracionRecurso.obtenerEspacial(i).equalsIgnoreCase("Si"))
				checkEspacial[i] = new JCheckBox(Recurso.obtenerNombreComun(i),
								   true);
			else checkEspacial[i] = new JCheckBox(Recurso.obtenerNombreComun(i),
									false);
			color[i] = new JLabel("Color",
					   new ImageIcon("../img/color_"+i+".gif"),
					   SwingConstants.LEFT);
		}
		
		// Cargar los combos para el panel.
		cargarComboVista();
		
		// Posicionar y dimensionar los componentes para el panel.
		etiqueta[0].setBounds(20, 40, 40, 40);
		etiqueta[1].setBounds(75, 40, 330, 15);
		etiqueta[2].setBounds(75, 55, 330, 15);
		etiquetaVista.setBounds(20, 95, 60, 20);
		comboVista.setBounds(90, 95, 150, 20);
		recomendacionVista.setBounds(250, 95, 150, 20);
		etiquetaMostrar.setBounds(20, 125, 60, 20);
		for (i=0; i<cantidadRecursos; i++)
		{
			checkEspacial[i].setBounds(90, 125+30*i, 150, 20);
			color[i].setBounds(250, 125+30*i, 150, 20);
			checkEspacial[i].setToolTipText("Indique si desea mostrar el "+
											Recurso.obtenerNombreComun(i));
		}
		
		// Asignar los tips.
		comboVista.setToolTipText("Seleccione el tipo de vista de la "+
								  "din�mica espacial");
		
		// Crear la fuente para la recomendaci�n.
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		
		// Establecer la fuente.
		recomendacionVista.setFont(fuente);
		for (i=0; i<cantidadRecursos; i++)
			color[i].setFont(fuente);
		
		// Agregar los componentes para el panel.
		panel.add(etiqueta[0]);
		panel.add(etiqueta[1]);
		panel.add(etiqueta[2]);
		panel.add(etiquetaVista);
		panel.add(comboVista);
		panel.add(recomendacionVista);
		panel.add(etiquetaMostrar);
		for (i=0; i<cantidadRecursos; i++)
		{
			panel.add(checkEspacial[i]);
			panel.add(color[i]);
		}
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * M�todo en donde se configura el panel de configuraci�n de la din�mica
	 * temporal. El panel de configuraci�n de la din�mica temporal contiene las
	 * opciones para la posibilidad de mostrar o no el recurso.
	 */
	private void configurarDinamicaTemporal()
	{
		// Variables temporales.
		int i;
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Crear el panel de configuraci�n.
		JPanel panel = new JPanel();
		
		// Crear el borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde,
							  " Configuraci�n de la Din�mica Temporal ",
							  TitledBorder.LEFT, TitledBorder.TOP);
		
		// Establecer el borde.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		panel.setBounds(415, 10, 395, 250);
		
		// Crear los componentes para el panel.
		JLabel[] etiqueta = new JLabel[3];
		etiqueta[0] = new JLabel(new ImageIcon("../img/"+
					  "ecosistema_configuracion_temporal.gif"));
		etiqueta[1] = new JLabel("Puede controlar los recursos que se desean "+
					  "mostrar");
		etiqueta[2] = new JLabel("en la din�mica temporal.");
		JLabel etiquetaMostrar = new JLabel("Mostrar:");
		checkTemporal = new JCheckBox[cantidadRecursos];
		JLabel[] color = new JLabel[cantidadRecursos];
		for (i=0; i<cantidadRecursos; i++)
		{
			if (ConfiguracionRecurso.obtenerTemporal(i).equalsIgnoreCase("Si"))
				checkTemporal[i] = new JCheckBox(Recurso.obtenerNombreComun(i),
								   true);
			else checkTemporal[i] = new JCheckBox(Recurso.obtenerNombreComun(i),
									false);
			color[i] = new JLabel("Color",
					   new ImageIcon("../img/color_"+i+".gif"),
					   SwingConstants.LEFT);
		}
		
		// Posicionar y dimensionar los componentes para el panel.
		etiqueta[0].setBounds(20, 40, 40, 40);
		etiqueta[1].setBounds(75, 40, 330, 15);
		etiqueta[2].setBounds(75, 55, 330, 15);
		etiquetaMostrar.setBounds(20, 110, 60, 20);
		for (i=0; i<cantidadRecursos; i++)
		{
			checkTemporal[i].setBounds(90, 110+30*i, 150, 20);
			color[i].setBounds(250, 110+30*i, 150, 20);
			checkTemporal[i].setToolTipText("Indique si desea mostrar el "+
											Recurso.obtenerNombreComun(i));
		}
		
		// Crear la fuente para la recomendaci�n.
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		
		// Establecer la fuente.
		for (i=0; i<cantidadRecursos; i++)
			color[i].setFont(fuente);
		
		// Agregar los componentes para el panel.
		panel.add(etiqueta[0]);
		panel.add(etiqueta[1]);
		panel.add(etiqueta[2]);
		panel.add(etiquetaMostrar);
		for (i=0; i<cantidadRecursos; i++)
		{
			panel.add(checkTemporal[i]);
			panel.add(color[i]);
		}
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * M�todo en donde se configura el panel de configuraci�n de las cuotas de
	 * captura. El panel de configuraci�n de las cuotas de captura contiene las
	 * opciones para el porcentaje del excedente de biomasa y la calidad
	 * promedio del recurso necesaria para considerar en las cuotas.
	 */
	private void configurarCuotasCaptura()
	{
		// Variables temporales.
		int i;
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Crear el borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde,
							  " Configuraci�n de las Cuotas de Captura ",
							  TitledBorder.LEFT, TitledBorder.TOP);
		
		// Establecer el borde.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		panel.setBounds(415, 270, 395, 250);
		
		// Crear los componentes para el panel.
		JLabel[] etiqueta = new JLabel[4];
		etiqueta[0] = new JLabel(new ImageIcon("../img/"+
					  "ecosistema_configuracion_cuotas.gif"));
		etiqueta[1] = new JLabel("Para generar el plan de cuotas de captura, "+
					  "ingrese el");
		etiqueta[2] = new JLabel("porcentaje del excedente de biomasa y la "+
					  "calidad");
		etiqueta[3] = new JLabel("promedio de los recursos que desea extraer.");
		JLabel etiquetaPorcentaje = new JLabel("Porcentaje");
		JLabel etiquetaCalidad = new JLabel("Calidad");
		JLabel[] etiquetaRecurso = new JLabel[cantidadRecursos];
		campoPorcentaje = new CampoNumerico[cantidadRecursos];
		comboCalidad = new JComboBox[cantidadRecursos];
		JLabel[] recomendacionCalidad = new JLabel[cantidadRecursos];
		for (i=0; i<cantidadRecursos; i++)
		{
			etiquetaRecurso[i] = new JLabel(Recurso.obtenerNombreComun(i)+":");
			campoPorcentaje[i] = new CampoNumerico(
								 ConfiguracionRecurso.obtenerPorcentaje(i));
			comboCalidad[i] = new JComboBox();
			cargarComboCalidad(i);
			recomendacionCalidad[i] = new JLabel("Media",
									  new ImageIcon("../img/recomendacion.gif"),
									  SwingConstants.LEFT);
		}
		
		// Posicionar y dimensionar los componentes para el panel.
		etiqueta[0].setBounds(20, 40, 40, 40);
		etiqueta[1].setBounds(75, 40, 330, 15);
		etiqueta[2].setBounds(75, 55, 330, 15);
		etiqueta[3].setBounds(75, 70, 330, 15);
		etiquetaPorcentaje.setBounds(150, 95, 50, 10);
		etiquetaCalidad.setBounds(235, 95, 90, 10);
		for (i=0; i<cantidadRecursos; i++)
		{
			etiquetaRecurso[i].setBounds(20, 110+30*i, 120, 20);
			campoPorcentaje[i].setBounds(150, 110+30*i, 50, 20);
			comboCalidad[i].setBounds(210, 110+30*i, 90, 20);
			recomendacionCalidad[i].setBounds(310, 110+30*i, 50, 20);
			campoPorcentaje[i].setToolTipText("Ingrese el porcentaje del "+
			"excedente de biomasa del "+Recurso.obtenerNombreComun(i));
			comboCalidad[i].setToolTipText("Seleccione el tipo de calidad del "+
			Recurso.obtenerNombreComun(i));
		}
		
		// Crear la fuente para la recomendaci�n.
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		
		// Establecer la fuente.
		etiquetaPorcentaje.setFont(fuente);
		etiquetaCalidad.setFont(fuente);
		for (i=0; i<cantidadRecursos; i++)
			recomendacionCalidad[i].setFont(fuente);
		
		// Agregar los componentes al panel.
		panel.add(etiqueta[0]);
		panel.add(etiqueta[1]);
		panel.add(etiqueta[2]);
		panel.add(etiqueta[3]);
		panel.add(etiquetaPorcentaje);
		panel.add(etiquetaCalidad);
		for (i=0; i<cantidadRecursos; i++)
		{
			panel.add(etiquetaRecurso[i]);
			panel.add(campoPorcentaje[i]);
			panel.add(comboCalidad[i]);
			panel.add(recomendacionCalidad[i]);
		}
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
     * Metodo que carga el combo box del tipo de l�mite desde la base de datos.
     * Primero se realiza la consuta SQL para obtener todos los tipos de l�mites
     * y luego se incorporan los resultados al combo box del tipo de l�mite.
     */
	private void cargarComboLimite()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select * from limite order by codigo_limite asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboLimite.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboLimite.addItem(resultado.getString("descripcion_limite"));
				resultado.next();
			}
			while (!resultado.isAfterLast());
			
			// Elegir el l�mite que tiene la configuraci�n.
			comboLimite.setSelectedIndex(ConfiguracionAC.obtenerLimite());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla limite.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Metodo que carga el combo box del tipo de vecindad desde la base de
     * datos. Primero se realiza la consuta SQL para obtener todos los tipos de
     * vecindad y luego se incorporan los resultados al combo box del tipo de
     * vecindad.
     */
	private void cargarComboVecindad()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select * from vecindad order by codigo_vecindad asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboVecindad.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboVecindad.addItem(
				resultado.getString("descripcion_vecindad"));
				resultado.next();
			}
			while (!resultado.isAfterLast());
			
			// Elegir la vecindad que tiene la configuraci�n.
			comboVecindad.setSelectedIndex(ConfiguracionAC.obtenerVecindad());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla vecindad.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Metodo que carga el combo box del tipo de tiempo desde la base de datos.
     * Primero se realiza la consuta SQL para obtener todos los tipos de tiempo
     * y luego se incorporan los resultados al combo box del tiempo.
     */
	private void cargarComboTiempo()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select * from tiempo order by codigo_tiempo asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboTiempo.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboTiempo.addItem(resultado.getString("descripcion_tiempo"));
				resultado.next();
			}
			while (!resultado.isAfterLast());
			
			// Elegir el tiempo que tiene la configuraci�n.
			comboTiempo.setSelectedIndex(ConfiguracionAC.obtenerTiempo());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla tiempo.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Metodo que carga el combo box del tipo de vista desde la base de datos.
     * Primero se realiza la consuta SQL para obtener todos los tipos de vistas
     * del mapa y luego se incorporan los resultados al combo box del tipo de
     * vista.
     */
	private void cargarComboVista()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select * from vista order by codigo_vista asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboVista.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboVista.addItem(resultado.getString("descripcion_vista"));
				resultado.next();
			}
			while (!resultado.isAfterLast());
			
			// Elegir la vista que tiene la configuraci�n.
			comboVista.setSelectedIndex(ConfiguracionEspacial.obtenerVista());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla vista.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Metodo que carga el combo box del tipo de calidad desde la base de datos.
     * Primero se realiza la consuta SQL para obtener todos los tipos de
     * calidades y luego se incorporan los resultados al combo box del tipo de
     * calidad.
     *
     * @param indice El �ndice del combo box de la calidad.
     */
	private void cargarComboCalidad(int indice)
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select * from tipo_calidad "+
						  "order by valor_tipo_calidad asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboCalidad[indice].removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboCalidad[indice].addItem(
				resultado.getString("descripcion_tipo_calidad"));
				resultado.next();
			}
			while (!resultado.isAfterLast());
			
			// Elegir el tipo de calidad que tiene la configuraci�n.
			comboCalidad[indice].setSelectedIndex(
			ConfiguracionRecurso.obtenerCalidad(indice));
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla tipo_calidad.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
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
		botonRestaurar.addActionListener(eventoButton);
		botonEstablecer.addActionListener(eventoButton);
	}
	
	/**
	 * M�todo que carga la configuraci�n en el panel. Se obtiene los datos de la
	 * configuraci�n desde las clases est�ticas ConfiguracionAC y
	 * ConfiguracionRecurso.
	 */
	public void cargarConfiguracion()
	{
		// Variables temporales.
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Establecer la configuraci�n del aut�mata celular.
		comboLimite.setSelectedIndex(ConfiguracionAC.obtenerLimite());
		comboVecindad.setSelectedIndex(ConfiguracionAC.obtenerVecindad());
		comboTiempo.setSelectedIndex(ConfiguracionAC.obtenerTiempo());
		
		// Establecer la configuraci�n de la din�mica espacial, temporal y
		// cuotas.
		comboVista.setSelectedIndex(ConfiguracionEspacial.obtenerVista());
		for (int i=0; i<cantidadRecursos; i++)
		{
			// Establecer la configuraci�n de la din�mica espacial.
			if (ConfiguracionRecurso.obtenerEspacial(i).equalsIgnoreCase("Si"))
				checkEspacial[i].setSelected(true);
			else checkEspacial[i].setSelected(false);
			
			// Establecer la configuraci�n de la din�mica temporal.
			if (ConfiguracionRecurso.obtenerTemporal(i).equalsIgnoreCase("Si"))
				checkTemporal[i].setSelected(true);
			else checkTemporal[i].setSelected(false);
			
			// Establecer la configuraci�n de las cuotas.
			campoPorcentaje[i].setValue(
				ConfiguracionRecurso.obtenerPorcentaje(i));
				comboCalidad[i].setSelectedIndex(
				ConfiguracionRecurso.obtenerCalidad(i));
		}
	}
	
	/**
	 * M�todo que establece la configuraci�n por defecto. Se eligen y setean los
	 * valores de los componentes que tiene este panel de configuraci�n con los
	 * valores por defecto. Antes de restaurar la configuraci�n se pregunta al
	 * usuario si realmente desea realizar la operaci�n.
	 */
	public void restaurarConfiguracion()
	{
		int opcion = JOptionPane.showConfirmDialog(this,
					"Se borrar�n todos los resultados ecosist�micos generados "+
					"anteriormente para esta configuraci�n.\nDesea continuar?",
					"CIRCANA Pro 2.0",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// Cuando el usuario acepta el cambio de configuraci�n.
		if (opcion == JOptionPane.YES_OPTION)
		{
			// Obtener la cantidad de recursos.
			int cantidadRecursos = Recurso.obtenerCantidadRecursos();
			
			// Elegir la configuraci�n por defecto del aut�mata celular.
			comboLimite.setSelectedIndex(1);
			comboVecindad.setSelectedIndex(1);
			comboTiempo.setSelectedIndex(3);
			
			// Elegir la configuraci�n por defecto de la din�mica espacial.
			comboVista.setSelectedIndex(0);
			
			// Establecer la configuraci�n por defecto de los recursos.
			for (int i=0; i<cantidadRecursos; i++)
			{
				checkEspacial[i].setSelected(true);
				checkTemporal[i].setSelected(true);
				campoPorcentaje[i].setValue(0);
				comboCalidad[i].setSelectedIndex(TipoCalidad.MEDIA);
			}
			
			// Cambiar el proyecto.
			Proyecto.limpiarResultados(Proyecto.ECOSISTEMA, -1);
			Proyecto.establecerReporteEcosistema(null);
			Proyecto.establecerModificado(true);
			
			// Limpiar los componentes.
			frameEcosistema.obtenerAutomataCelular().limpiar();
			frameEcosistema.panelReporteEcosistema.limpiarReporte();
			frameEcosistema.panelEscritorio.frameIntegracion.
				panelPlanificarIntegracion.cargarTextoEcosistema();
		}
	}
	
	/**
	 * M�todo que establece los valores de la configuraci�n aplicada por el
	 * usuario. En este m�todo se cambian los valores de los atributos de las
	 * clases est�ticas ConfiguracionAC, ConfiguracionEspacial y
	 * ConfiguracionRecurso. Luego se notifican los cambios efectuados en el
	 * proyecto. Los datos son validados. Antes de establecer la configuraci�n
	 * se pregunta al usuario si realmente desea realizar la operaci�n.
	 */
	public void establecerConfiguracion()
	{
		int opcion = JOptionPane.showConfirmDialog(this,
				"Se borrar�n todos los resultados ecosist�micos generados "+
				"anteriormente para esta configuraci�n.\nDesea continuar?",
				"CIRCANA Pro 2.0",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// Cuando el usuario acepta el cambio de configuraci�n.
		if (opcion == JOptionPane.YES_OPTION)
		{
			// Validar la configuraci�n.
			String error = validarConfiguracion();
		 	
		 	// Cuando no hay errores.
			if (error == "")
			{
				// Obtener la cantidad de recursos.
				int cantidadRecursos = Recurso.obtenerCantidadRecursos();
				
				// Establecer la configuraci�n del aut�mata celular.
				ConfiguracionAC.establecerLimite(
					comboLimite.getSelectedIndex());
				ConfiguracionAC.establecerVecindad(
					comboVecindad.getSelectedIndex());
				ConfiguracionAC.establecerTiempo(
					comboTiempo.getSelectedIndex());
				
				// Establecer la configuraci�n del espacio.
				ConfiguracionEspacial.establecerVista(
					comboVista.getSelectedIndex());
				
				// Establecer la configuraci�n de los recursos.
				for (int i=0; i<cantidadRecursos; i++)
				{
					ConfiguracionRecurso.establecerCalidad(
						comboCalidad[i].getSelectedIndex(), i);
					
					if (checkEspacial[i].isSelected())
						ConfiguracionRecurso.establecerEspacial("Si", i);
					else ConfiguracionRecurso.establecerEspacial("No", i);
					
					if (checkTemporal[i].isSelected())
						ConfiguracionRecurso.establecerTemporal("Si", i);
					else ConfiguracionRecurso.establecerTemporal("No", i);
					
					ConfiguracionRecurso.establecerPorcentaje(
						campoPorcentaje[i].getValueDouble(), i);
				}
				
				// Cambiar el proyecto.
				Proyecto.limpiarResultados(Proyecto.ECOSISTEMA, -1);
				Proyecto.establecerReporteEcosistema(null);
				Proyecto.establecerModificado(true);
				
				// Limpiar los componentes.
				frameEcosistema.panelEspacialEcosistema.
					subpanelMapaEcosistema.cambiarMundoReal();
				frameEcosistema.obtenerTiempo().iniciar();
				frameEcosistema.obtenerAutomataCelular().limpiar();
				frameEcosistema.panelReporteEcosistema.limpiarReporte();
				frameEcosistema.panelEscritorio.frameIntegracion.
					panelPlanificarIntegracion.cargarTextoEcosistema();
			}
			
			// Cuando si hay errores.
			else
			{
				error = "Se han cometido los siguientes errores:\n" + error;
				JOptionPane.showMessageDialog(this, error,
					"Error en la configuraci�n", JOptionPane.ERROR_MESSAGE);
			}
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
		// Obtener la cantidad de recursos.
		int cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Para almacenar los errores.
		String error = "";
		
		// Validar los porcentajes.
		for (int i=0; i<cantidadRecursos; i++)
			if (!Servicio.esNumero(campoPorcentaje[i].getText()) ||
				campoPorcentaje[i].getValueDouble() < 0 ||
				campoPorcentaje[i].getValueDouble() > 100)
			{
				error+= "- En el porcentaje del excedente de biomasa del "+
						"recurso " + Recurso.obtenerNombreComun(i) + ".\n";
			}
		
		return error;
	}
	
	/**
	 * M�todo que habilita o deshabilita los botones del panel. Espec�ficamente
	 * se establece la propiedad de habilitaci�n de los botones, dependiendo del
	 * estado actual en que se encuentra la simulaci�n.
	 */
	public void habilitarBotones()
	{
		// Dependiendo del estado de la simulaci�n, deshabilitar los botones.
		switch (frameEcosistema.obtenerEstado())
		{
			// Proceso en segundo plano iniciado.
			case ProcesoSegundoPlano.INICIADO:
			habilitarBotones(false);
			break;
			
			// Proceso en segundo plano pausado.
			case ProcesoSegundoPlano.PAUSADO:
			habilitarBotones(false);
			break;
			
			// Proceso en segundo plano detenido.
			case ProcesoSegundoPlano.DETENIDO:
			habilitarBotones(true);
			break;
		}
	}
	
	/**
     * M�todo en donde se maneja la habilitaci�n o deshabilitaci�n de los
     * botones principales de de la configuraci�n (restaurar y establecer).
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
	public void habilitarBotones(boolean habilitado)
	{
		botonRestaurar.setEnabled(habilitado);
		botonEstablecer.setEnabled(habilitado);
	}
	
	/**
     * M�todo que retorna el bot�n de restaurar la configuraci�n.
     *
     * @return botonRestaurar Bot�n de restaurar la configuraci�n del panel
     *                         configurar.
     */
	public JButton obtenerBotonRestaurar()
	{
		return botonRestaurar;
	}
	
	/**
     * M�todo que retorna el boton de establecer la configuraci�n.
     *
     * @return botonEstablecer Bot�n de establecer la configuraci�n del panel
     *                         configurar.
     */
	public JButton obtenerBotonEstablecer()
	{
		return botonEstablecer;
	}
}