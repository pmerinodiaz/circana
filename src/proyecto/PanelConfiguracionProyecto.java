/**
 * @(#)PanelConfiguracionProyecto.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En este panel se permite configurar la
 * fecha de inicio y término del proyecto, el recurso, la región y la empresa.
 * Estas opciones son mostradas dentro de paneles.
 *  
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Calendar
 * @see ResultSet
 * @see SQLException
 * @see Color
 * @see Font
 * @see KeyEvent
 * @see JPanel
 * @see JLabel
 * @see JButton
 * @see JComboBox
 * @see ImageIcon
 * @see JTextField
 * @see JOptionPane
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class PanelConfiguracionProyecto extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** Puntero al frame contenedor de este panel. */
	public FrameProyecto frameProyecto;
	
	/** Etiqueta para el nombre del proyecto. */
	private JTextField campoNombre;
	
	/** Combo box para seleccionar el día inicial. */
	private JComboBox comboDiaInicial;
	
	/** Combo box para seleccionar el mes inicial. */
	private JComboBox comboMesInicial;
	
	/** Combo box para seleccionar el año inicial. */
	private JComboBox comboAnioInicial;
	
	/** Combo box para seleccionar el día final. */
	private JComboBox comboDiaFinal;
	
	/** Combo box para seleccionar el año final. */
	private JComboBox comboMesFinal;
	
	/** Combo box para seleccionar el año inicial. */
	private JComboBox comboAnioFinal;
	
	/** Combo box para seleccionar el recurso asociado al proyecto. */
	private JComboBox comboRecurso;
	
	/** Combo box para seleccionar la región asociada al proyecto. */
	private JComboBox comboRegion;
	
	/** Combo box para seleccionar la empresa asociada al proyecto. */
	private JComboBox comboEmpresa;
	
	/** Botón que establece la configuración del proyecto. */
	private JButton botonEstablecer;
	
	/** 
	 * Método constructor de la clase PanelConfiguracionProyecto. En este método
	 * se configura el panel, los componentes y los eventos. Recibe como
	 * parámetro el frame que contiene a este panel.
	 * 
	 * @param frameProyecto El frame que contiene a este panel.
	 */
	public PanelConfiguracionProyecto(FrameProyecto frameProyecto)
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
     * contiene tres paneles de configuración: el nombre, las fechas y el caso
     * de aplicación. A su vez, cada panel contiene diferentes componentes GUI.
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
									   "configuración del proyecto");
		
		// Agregar los componentes.
		add(botonEstablecer);
		
		// Configurar los paneles.
		configurarNombre();
		configurarFechas();
		configurarCasoAplicacion();
	}
	
	/**
	 * Método en donde se configuran los componentes que tiene el panel del
	 * nombre del proyecto. Este panel contiene una compo de texto con el nombre
	 * del proyecto.
	 */
	private void configurarNombre()
	{
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// El tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el título.
		TitledBorder titulo = new TitledBorder(borde, " Nombre ",
											   TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Agregar el borde.
		panel.setBorder(titulo);
								
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar el panel.
		panel.setBounds(10, 15, 800, 110);
		
		// Crear los componentes.
		JLabel[] etiquetaExplicacion = new JLabel[3];
		etiquetaExplicacion[0] = new JLabel(
								 new ImageIcon("../img/proyecto_nombre.gif"));
		etiquetaExplicacion[1] = new JLabel("El proyecto es una herramienta "+
								 "que le permite trabajar en forma conjunta "+
								 "los modelos ecosistémico, económico, "+
								 "operativo e");
		etiquetaExplicacion[2] = new JLabel("integración. A continuación se "+
								 "muestra el nombre del proyecto que está "+
								 "abierto.");
		JLabel etiquetaNombre = new JLabel("Nombre:");
		campoNombre = new JTextField(Proyecto.obtenerNombre());
		
		// Posicionar y dimensionar los componentes.
		etiquetaExplicacion[0].setBounds(20, 25, 50, 50);
		etiquetaExplicacion[1].setBounds(80, 25, 760, 15);
		etiquetaExplicacion[2].setBounds(80, 40, 760, 15);
		etiquetaNombre.setBounds(80, 75, 60, 20);
		campoNombre.setBounds(170, 75, 190, 20);
		campoNombre.setEditable(false);
		
		// Asignar los tips.
		campoNombre.setToolTipText("Nombre del proyecto");
		
		// Agregar los componentes al panel.
		panel.add(etiquetaExplicacion[0]);
		panel.add(etiquetaExplicacion[1]);
		panel.add(etiquetaExplicacion[2]);
		panel.add(etiquetaNombre);
		panel.add(campoNombre);
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * Método en donde se configuran los componentes que tiene el panel de las
	 * fechas del proyecto. Este panel contiene seis combo box para ingresar la
	 * fecha inicial y final de la simulación.
	 */
	private void configurarFechas()
	{
		// Variables temporales.
		int i, anioActual, diaActual, mesActual;;
				
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Crear el tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el título.
		TitledBorder titulo = new TitledBorder(borde, " Fechas ",
											   TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Agregar el borde.
		panel.setBorder(titulo);
								
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar el panel.
		panel.setBounds(10, 150, 800, 150);
		
		// Obtener la fecha actual.
		Calendar fecha = Calendar.getInstance();
		
		// Arreglo de mes y día.
		String[] mes = new String[12];;
		String[] dia = new String[31];
		
		// Obtener el día, mes y año actual.
		diaActual = fecha.get(Calendar.DAY_OF_MONTH);
		mesActual = fecha.get(Calendar.MONTH) + 1;
		anioActual = fecha.get(Calendar.YEAR);
				
		// Iniciando los valores del string.
		for (i=0; i<12; i++)
			mes[i] = "" + (i + 1);
		
		for (i=0; i<31; i++)
			dia[i] = "" + (i + 1);
		
		// Crear los componentes.
		JLabel[] etiquetaExplicacion = new JLabel[3];
		etiquetaExplicacion[0] = new JLabel(
								 new ImageIcon("../img/proyecto_fechas.gif"));
		etiquetaExplicacion[1] = new JLabel("En un proyecto se estudian los "+
								 "aspectos ecosistémicos, económicos, "+
								 "operativos e integración de un recurso "+
								 "pesquero en un ");
		etiquetaExplicacion[2] = new JLabel("lapso de tiempo determinado. A "+
								 "continuación seleccione las fechas de "+
								 "inicio y término del proyecto.");
		JLabel[] etiquetaSeparador = new JLabel[4];
		etiquetaSeparador[0] = new JLabel("/");
		etiquetaSeparador[1] = new JLabel("/");
		etiquetaSeparador[2] = new JLabel("/");
		etiquetaSeparador[3] = new JLabel("/");
		JLabel etiquetaDia = new JLabel("Día");
		JLabel etiquetaMes = new JLabel("Mes");
		JLabel etiquetaAnio = new JLabel("Año");
		JLabel etiquetaFechaInicial = new JLabel("Fecha Inicial:");
		JLabel etiquetaFechaFinal = new JLabel("Fecha Final:");
		comboDiaInicial = new JComboBox(dia);								
		comboMesInicial = new JComboBox(mes);
		comboAnioInicial = new JComboBox();
		comboDiaFinal = new JComboBox(dia);										
		comboMesFinal = new JComboBox(mes);								
		comboAnioFinal = new JComboBox();
		
		// Cargar los combo box.
		cargarComboAnios();
		
		// Cuando es un proyecto nuevo.
		if (Proyecto.obtenerCodigo() == Proyecto.CODIGO_NUEVO)
		{
			comboDiaInicial.setSelectedItem(""+diaActual);
			comboMesInicial.setSelectedItem(""+mesActual);
			comboAnioInicial.setSelectedItem(""+anioActual);
			comboDiaFinal.setSelectedItem(""+diaActual);
			comboMesFinal.setSelectedItem(""+mesActual);
			comboAnioFinal.setSelectedItem(""+(anioActual + 1));
		}
		// Cuando no es un proyecto nuevo.
		else
		{
			comboDiaInicial.setSelectedItem(""+
			Proyecto.obtenerFechaInicialFormatoInt()[0]);
			comboMesInicial.setSelectedItem(""+
			Proyecto.obtenerFechaInicialFormatoInt()[1]);
			comboAnioInicial.setSelectedItem(""+
			Proyecto.obtenerFechaInicialFormatoInt()[2]);
			comboDiaFinal.setSelectedItem(""+
			Proyecto.obtenerFechaFinalFormatoInt()[0]);
			comboMesFinal.setSelectedItem(""+
			Proyecto.obtenerFechaFinalFormatoInt()[1]);
			comboAnioFinal.setSelectedItem(""+
			Proyecto.obtenerFechaFinalFormatoInt()[2]);
		}
		
		// Posicionar y dimensionar los componentes.
		etiquetaExplicacion[0].setBounds(20, 25, 50, 50);
		etiquetaExplicacion[1].setBounds(80, 25, 770, 15);
		etiquetaExplicacion[2].setBounds(80, 40, 770, 15);
		etiquetaDia.setBounds(185, 65, 45, 10);
		etiquetaMes.setBounds(245, 65, 45, 10);
		etiquetaAnio.setBounds(315, 65, 60, 10);
		etiquetaFechaInicial.setBounds(80, 80, 100, 20);
		comboDiaInicial.setBounds(170, 80, 45, 20);
		etiquetaSeparador[0].setBounds(225, 80, 10, 20);
		comboMesInicial.setBounds(235, 80, 45, 20);
		etiquetaSeparador[1].setBounds(290, 80, 10, 20);
		comboAnioInicial.setBounds(300, 80, 60, 20);
		etiquetaFechaFinal.setBounds(80, 110, 100, 20);
		comboDiaFinal.setBounds(170, 110, 45, 20);
		etiquetaSeparador[2].setBounds(225, 110, 10, 20);
		comboMesFinal.setBounds(235, 110, 45, 20);
		etiquetaSeparador[3].setBounds(290, 110, 10, 20);
		comboAnioFinal.setBounds(300, 110, 60, 20);
		
		// Asignar los tips.
		comboDiaInicial.setToolTipText(
			"Seleccione el día inicial del proyecto");
		comboMesInicial.setToolTipText(
			"Seleccione el mes inicial del proyecto");
		comboAnioInicial.setToolTipText(
			"Seleccione el año inicial del proyecto");
		comboDiaFinal.setToolTipText(
			"Seleccione el día final del proyecto");
		comboMesFinal.setToolTipText(
			"Seleccione el mes final del proyecto");
		comboAnioFinal.setToolTipText(
			"Seleccione el año final del proyecto");
		
		// Crear la fuente.
		Font fuente = new Font("Arial", Font.PLAIN, 9);
		
		// Establecer la fuente.
		etiquetaDia.setFont(fuente);
		etiquetaMes.setFont(fuente);
		etiquetaAnio.setFont(fuente);
		
		// Agregar los componentes al panel.
		panel.add(etiquetaExplicacion[0]);
		panel.add(etiquetaExplicacion[1]);
		panel.add(etiquetaExplicacion[2]);
		panel.add(etiquetaDia);
		panel.add(etiquetaMes);
		panel.add(etiquetaAnio);
		panel.add(etiquetaFechaInicial);
		panel.add(comboDiaInicial);
		panel.add(etiquetaSeparador[0]);
		panel.add(comboMesInicial);
		panel.add(etiquetaSeparador[1]);
		panel.add(comboAnioInicial);
		panel.add(etiquetaFechaFinal);
		panel.add(comboDiaFinal);
		panel.add(etiquetaSeparador[2]);
		panel.add(comboMesFinal);
		panel.add(etiquetaSeparador[3]);
		panel.add(comboAnioFinal);
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * Método en donde se configuran los componentes que tiene el panel del caso
	 * de aplicación del proyecto. Este panel contiene tres combo box para
	 * ingresar el recurso, la región y la empresa.
	 */
	private void configurarCasoAplicacion()
	{
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Crear el tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el título.
		TitledBorder titulo = new TitledBorder(borde, " Caso de Aplicación ",
											   TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Agregar el borde.
		panel.setBorder(titulo);
								
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar el panel.
		panel.setBounds(10, 320, 800, 170);
		
		// Crear los componentes.
		JLabel[] etiquetaExplicacion = new JLabel[3];
		etiquetaExplicacion[0] = new JLabel(
								 new ImageIcon("../img/"+
								 "proyecto_caso_aplicacion.gif"));
		etiquetaExplicacion[1] = new JLabel("El caso de aplicación del "+
								 "proyecto corresponde a la selección de un "+
								 "recurso pesquero, una región geográfica y "+
								 "una empresa.");
		etiquetaExplicacion[2] = new JLabel("A continuación elija el caso "+
								 "de aplicación de este proyecto.");
		JLabel etiquetaRecurso = new JLabel("Recurso:");
		JLabel etiquetaRegion = new JLabel("Región:");
		JLabel etiquetaEmpresa = new JLabel("Empresa:");
		comboRecurso = new JComboBox();
		comboRegion =  new JComboBox();
		comboEmpresa = new JComboBox();
		
		// Cargar los combo box.
		cargarComboRecurso();
		cargarComboRegion();
		cargarComboEmpresa();
		
		// Posicionar y dimensionar los componentes.
		etiquetaExplicacion[0].setBounds(20, 25, 50, 50);
		etiquetaExplicacion[1].setBounds(80, 25, 760, 15);
		etiquetaExplicacion[2].setBounds(80, 40, 760, 15);
		etiquetaRecurso.setBounds(80, 70, 100, 20);
		comboRecurso.setBounds(170, 70, 150, 20);
		etiquetaRegion.setBounds(80, 100, 100, 20);
		comboRegion.setBounds(170, 100, 150, 20);
		etiquetaEmpresa.setBounds(80, 130, 100, 20);
		comboEmpresa.setBounds(170, 130, 150, 20);
		
		// Asignar los tips.
		comboRecurso.setToolTipText(
			"Seleccione el recurso de aplicación del proyecto");
		comboRegion.setToolTipText(
			"Seleccione la región de aplicación del proyecto");
		comboEmpresa.setToolTipText(
			"Seleccione la empresa de aplicación del proyecto");
		
		// Agregar los componentes al panel.
		panel.add(etiquetaExplicacion[0]);
		panel.add(etiquetaExplicacion[1]);
		panel.add(etiquetaExplicacion[2]);
		panel.add(etiquetaRecurso);
		panel.add(comboRecurso);
		panel.add(etiquetaRegion);
		panel.add(comboRegion);
		panel.add(etiquetaEmpresa);
		panel.add(comboEmpresa);
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones que tiene el
	 * panel de configuracion. En particular se incorpora el escuchador de
	 * eventos del tipo EventoButton a los JButton que tiene el panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonEstablecer.addActionListener(eventoButton);
	}
	
	/**
     * Método en donde se cargan los combo box de los años del panel
     * configuración de fechas proyecto. Los datos son obtenidos desde la base
     * de datos.
     */
	private void cargarComboAnios()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select * " +
						  "from anio "+
						  "order by codigo_anio asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos de los combo.
			comboAnioInicial.removeAllItems();
			comboAnioFinal.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboAnioInicial.addItem(resultado.getString("codigo_anio"));
				comboAnioFinal.addItem(resultado.getString("codigo_anio"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		
		// Capturar la exepción y mostrarla.
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla anio.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método en donde se cargan el combo box de los recursos del panel caso de
     * aplicación del proyecto. Los datos son obtenidos desde la base de datos.
     */
	private void cargarComboRecurso()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select nombre_comun_recurso "+
						  "from recurso "+
						  "where codigo_tipo_recurso = "+
						  TipoRecurso.PREDECIBLE+" "+
						  "order by codigo_recurso asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboRecurso.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboRecurso.addItem(
					resultado.getString("nombre_comun_recurso"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
			
			// Seleccionar el recurso del proyecto.
			comboRecurso.setSelectedIndex(Proyecto.obtenerCodigoRecurso());
		}
		
		// Capturar la exepción y mostrarla.
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla recurso.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método en donde se cargan el combo box de las regiones del panel caso de
     * aplicación del proyecto. Los datos son obtenidos desde la base de datos.
     */
	private void cargarComboRegion()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select descripcion_region "+
						  "from region "+
						  "order by codigo_region asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboRegion.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboRegion.addItem(resultado.getString("descripcion_region"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
			
			// Seleccionar la región del proyecto.
			comboRegion.setSelectedIndex(Proyecto.obtenerCodigoRegion());
		}
		
		// Capturar la exepción y mostrarla.
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla region.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método en donde se cargan el combo box de las empresas del panel caso de
     * aplicación del proyecto. Los datos son obtenidos desde la base de datos.
     */
	private void cargarComboEmpresa()
	{
		// Para almacenar los resultados.
		ResultSet resultado;
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Realizar la consulta.
		String consulta = "select nombre_empresa "+
						  "from empresa "+
						  "order by codigo_empresa asc";
		resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el combo.
		try
		{
			// Eliminar los elementos del combo.
			comboEmpresa.removeAllItems();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del combo.
			do
			{
				comboEmpresa.addItem(resultado.getString("nombre_empresa"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
			
			// Seleccionar la empresa del proyecto.
			comboEmpresa.setSelectedIndex(Proyecto.obtenerCodigoEmpresa());
		}
		
		// Capturar la exepción y mostrarla.
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla empresa.");
		}
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que obtiene la fecha incial donde se ejecuta el proyecto.
     * La fecha tiene formato de (0:día, 1:mes, 2:año).
     * 
     * @return fechaInicial Vector con la fecha inicial del donde se ejecuta
     *						la simulacion. El formato del arreglo es (0:día,
     *                      1:mes, 2:año).
     */
	public String[] obtenerFechaInicial()
	{
		String[] fechaInicial = new String[3];
		
		fechaInicial[0] = (String) comboDiaInicial.getSelectedItem();
		fechaInicial[1] = (String) comboMesInicial.getSelectedItem();
		fechaInicial[2] = (String) comboAnioInicial.getSelectedItem();
		
		return fechaInicial;
	}
	
	/**
     * Método que obtiene la fecha final donde se ejecuta el proyecto.
     * La fecha tiene formato de (0:día, 1:mes, 2:año).
     * 
     * @return fechaFinal Vector con la fecha inicial del donde se ejecuta
     *					  la simulacion. El formato del arreglo es (0:día,
     *                    1:mes, 2:año).
     */
	public String[] obtenerFechaFinal()
	{
		String[] fechaFinal = new String[3];
		
		fechaFinal[0] = (String) comboDiaFinal.getSelectedItem();
		fechaFinal[1] = (String) comboMesFinal.getSelectedItem();
		fechaFinal[2] = (String) comboAnioFinal.getSelectedItem();
		
		return fechaFinal;
	}
	
	/**
     * Método que carga la configuración del proyecto en el panel de
     * configuración del proyecto. La configuración que se carga está almacenada
     * en la clase Proyecto.
     */
	public void cargarConfiguracion()	
	{
		// Obtener la fecha inicial y final del proyecto.
		String[] fechaInicial = Proyecto.obtenerFechaInicial();
		String[] fechaFinal = Proyecto.obtenerFechaFinal();
		
		// Cargar el nombre.
		campoNombre.setText(Proyecto.obtenerNombre());
		
		// Cargar la fecha inicial.
		comboDiaInicial.setSelectedItem(""+
			(new Integer(fechaInicial[0])).intValue());
		comboMesInicial.setSelectedItem(""+
			(new Integer(fechaInicial[1])).intValue());
		comboAnioInicial.setSelectedItem(""+
			(new Integer(fechaInicial[2])).intValue());
		
		// Cargar la fecha final.
		comboDiaFinal.setSelectedItem(""+
			(new Integer(fechaFinal[0])).intValue());
		comboMesFinal.setSelectedItem(""+
			(new Integer(fechaFinal[1])).intValue());
		comboAnioFinal.setSelectedItem(""+
			(new Integer(fechaFinal[2])).intValue());
		
		// Cargar el recurso, la región y la empresa.
		comboRecurso.setSelectedIndex(Proyecto.obtenerCodigoRecurso());
		comboRegion.setSelectedIndex(Proyecto.obtenerCodigoRegion());
		comboEmpresa.setSelectedIndex(Proyecto.obtenerCodigoEmpresa());
	}
	
	/**
	 * Método que establece la configuración por defecto. Se eligen y setean los
	 * valores de los componentes que tiene este panel de configuración con los
	 * valores por defecto. En particular, este método no es implementado porque
	 * no existe la opción de restaurar la configuración.
	 */
	public void restaurarConfiguracion()
	{
	}
	
	/**
     * Método que establece una configuración en el proyecto con los valores
     * selecionados en el panel de configuración del proyecto. Además, se
     * verifica si las fechas incluidas en el panel de configuración se
     * encuentran correctas.
     */
	public void establecerConfiguracion()
	{
		// Obtener la opción del usuario.
		int opcion = JOptionPane.showConfirmDialog(this,
					 "Se borrarán todos los resultados generados anteriormente "+
					 "en el proyecto.\nDesea continuar?",
					 "CIRCANA Pro 2.0",
					 JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// Cuando el usuario acepta.
		if (opcion == JOptionPane.YES_OPTION)
		{
			// Validar la configuración.
			String error = validarConfiguracion();
			
			// Cuando no ocurrieron errores.
			if (error == "")
			{
				// Limpiar los resultados.
				Proyecto.limpiarResultados();
				
				// Establecer los datos del proyecto.
				Proyecto.establecerCodigoRecurso(comboRecurso.getSelectedIndex());
				Proyecto.establecerCodigoRegion(comboRegion.getSelectedIndex());
				Proyecto.establecerCodigoEmpresa(comboEmpresa.getSelectedIndex());
				Proyecto.establecerFechaInicial(obtenerFechaInicial());
				Proyecto.establecerFechaFinal(obtenerFechaFinal());
				Proyecto.establecerModificado(true);
				
				// Notificar los cambios.
				frameProyecto.panelEscritorio.actualizarInformacionProyecto();
				frameProyecto.panelEscritorio.frameCircanaPro.panelEstado.
					habilitarComponentes();
			}
			
			// Cuando si ocurrieron errores.
			else JOptionPane.showMessageDialog(this, error, "CIRCANA Pro 2.0",
											   JOptionPane.ERROR_MESSAGE);
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
		
		// Obtener la fecha inicial y final.
		String[] fechaInicial = obtenerFechaInicial();
		String[] fechaFinal = obtenerFechaFinal();
		
		// Validar la fecha inicial.
		if (!Servicio.esFechaValida(fechaInicial))
			error+= "- Error en la fecha inicial del proyecto.\n";
		
		// Validar la fecha final.
		if (!Servicio.esFechaValida(fechaFinal))
			error+= "- Error en la fecha final del proyecto.\n";
		
		// Validar las fechas.
		if (Servicio.compararFechas(fechaInicial, fechaFinal) != -1)
			error+= "- La fecha inicial del proyecto es mayor o igual\n"+
					"  a la fecha final del proyecto.\n";
		
		return error;
	}
	
	/**
	 * Método que habilitar o deshabilita los botones del panel. Específicamente
	 * se establece la propiedad de habilitación de los botones, dependiendo del
	 * estado actual en que se encuentra la aplicación.
	 */
	public void habilitarBotones()
	{
		// Cuando hay procesos corriendo.
		if (frameProyecto.panelEscritorio.frameCircanaPro.
			obtenerProcesoCorriendo())
			habilitarBotones(false);
		
		// Cuando no hay procesos corriendo.
		else habilitarBotones(true);
	}
	
	/**
     * Método en donde se maneja la habilitación o deshabilitación de los
     * botones principales de de la configuración (establecer).
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
	public void habilitarBotones(boolean habilitado)
	{
		botonEstablecer.setEnabled(habilitado);
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