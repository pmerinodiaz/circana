/**
 * @(#)PanelEntrenarEconomia.java 2.0 01/03/05
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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que extiende de la clase JPanel. En esta clase se permite al usuario
 * elegir la red que desea utilizar y los datos que necesita entrenar o
 * aprender. La elecci�n de la red es utilizada por todos los demas frames de
 * econom�a.
 * 
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ResultSet
 * @see SQLException
 * @see Color
 * @see Font
 * @see KeyEvent
 * @see JPanel
 * @see JButton
 * @see ImageIcon
 * @see JRadioButton
 * @see ButtonGroup
 * @see JRadioButton
 * @see JLabel
 * @see JCheckBox
 * @see JScrollPane
 * @see JProgressBar
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameEconomia
 * @see EventoButton
 * @see EventoRadioButton
 * @see BaseDatoMotor
 * @see InterfacePanel
 */
public class PanelEntrenarEconomia extends JPanel implements InterfacePanel
{
	/** Constante de color que indica una red no entrenada. */
	private static final Color NO_ENTRENADA = Color.RED;
	
	/** Constante de color que indica una red entrenada. */
	private static final Color ENTRENADA = Color.GREEN;
	
	/** Apuntador al frame creador del panel. */
	public FrameEconomia frameEconomia;
	
	/**
	 * Opci�n de las redes neuronales.
	 * (0:Back-Propgation(BP), 1:Radial Base Fase(RBF).
	 */
	private JRadioButton[] item;
	
	/** Bot�n para iniciar el entrenamiento. */
	private JButton botonEntrenar;
	
	/** Bot�n para detener el entrenamiento. */
	private JButton botonDetener;
	
	/** Bot�n para agregar datos al aprendizaje. */
	private JButton botonAgregar;
	
	/** Bot�n para quitar datos al aprendizaje. */
	private JButton botonQuitar;
	
	/** Bot�n para refrescar los datos en la base de datos. */
	private JButton botonRefrescar;
	
	/** Tabla que contiene los a�os disponibles para entrenar. */
	private TablaSwing tablaAnosDisponibles;
	
	/** Tabla que contiene los a�os que se va ha entrenar. */
	private TablaSwing tablaAnosEntrenadas;
	
	/** Indica como entrena la red. */
	private JProgressBar barraProgreso;
	
	/** Checkbox que habilta el despliege de informaci�n temporal. */
	private JCheckBox detenerPorError;
	
	/** Texto que inidica el aprendizaje que se est� efectuando. */
	private JLabel textoPanelEntrenando;
	
	/** Texto que indica el n�mero de patrones utilizado. */
	private JLabel textoNumeroPatrones;
	
	/** 
	 * Constructor de la clase PanelEntrenarEconomia. Donde se configura los 
	 * botones, los eventos. Recibe como par�metro el creador del objeto.
	 * 
	 * @param frameEconomia Creador del panel list que contiene ha entrenar.
	 */
	public PanelEntrenarEconomia(FrameEconomia frameEconomia)
	{
		super(null);
		
		// Inicializar el puntero.
		this.frameEconomia = frameEconomia;
		
		// Configurar.
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * M�todo en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este m�todo corresponden a los atributos
	 * que se derivan de la clase JPanel. En particular, este panel no configura
	 * sus atributos derivados.
	 */
	public void configurarPanel()
	{
	}
	
	/**
     * M�todo que cconfigura los paneles y sus componentes GUI. Este panel tiene
     * tres paneles: el tipo de red neuronal, los datos de aprendizaje y el
     * estado del entrenamiento. Cada panel contiene componentes GUI en
     * espec�fico.
     */
	public void configurarComponentes()
	{
		// nombres de los campos en la base datos
		String[] nombresColumnasTabla = {"A�os","N� Datos"};
		
		// para los radio button
		ButtonGroup grupoRedes = new ButtonGroup();
		
		// peque�os paneles para separar informaci�n
		JPanel panelEleccionRed =  new JPanel(null);
		JPanel panelTiempoEntrenar = new JPanel(null);
		JPanel panelEntrenando = new JPanel(null);
		
		// El tipo de borde de los paneles.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los titulos para los paneles.
		TitledBorder tituloEleccionRed = new TitledBorder(borde, 
			" Tipo de Red Neuronal Artificial ",
			TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloTiempoEntrenar = new TitledBorder(borde, 
			" Datos de Entrenamiento ",
			TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloEntrenando = new TitledBorder(borde, 
			" Estado del Entrenamiento ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// textos
		JLabel textoEleccionRed1 =
			new JLabel(new ImageIcon("../img/economia_tipo_red_neuronal.gif"));
		JLabel textoEleccionRed2 =
			new JLabel("Elija la red neuronal artificial que desea utilizar "+
			"para predecir la demanda y el precio del recurso en estudio.");
		JLabel textoAnosDisponible =
			new JLabel("Datos Disponibles");
		JLabel textoAnosEntrenada =
			new JLabel("Datos Entrenamiento");
		JLabel textoTiempoEntrenar1 =
			new JLabel("Seleccione los datos de aprendizaje (patrones) desde "+
			"la tabla Datos Disponibles. Para agregar patrones elija el dato "+
			"disponible y");
		JLabel textoTiempoEntrenar2 =
			new JLabel("luego pulse Agregar. Para quitar patrones elija el "+
			"dato de entrenamiento y luego pulse Quitar. Para actualizar los "+
			"datos pulse Refrescar.");
		
		// radio button de elecci�n de red
		item = new JRadioButton[2];
		item[0] = new JRadioButton("Back - Propagation (BP)",true);
		item[1] = new JRadioButton("Radial Base Fase (RBF)");
		
		// botones
		botonAgregar = new JButton("Agregar   ",
			new ImageIcon("../img/agregar.gif"));
		botonQuitar = new JButton("Quitar       ",
			new ImageIcon("../img/quitar.gif"));
		botonRefrescar = new JButton("Refrescar",
			new ImageIcon("../img/refrescar.gif"));
		botonEntrenar = new JButton("Entrenar",
			new ImageIcon("../img/iniciar.gif"));
		botonDetener = new JButton("Detener ",
			new ImageIcon("../img/detener.gif"));
		
		// tablas
		tablaAnosDisponibles = new TablaSwing(nombresColumnasTabla);
		tablaAnosEntrenadas = new TablaSwing(nombresColumnasTabla);
		
		// scroll pane
		JScrollPane barraTablaAnosDisponible =
			new JScrollPane(tablaAnosDisponibles);
		JScrollPane barraTablaAnosEntrenada =
			new JScrollPane(tablaAnosEntrenadas);
		
		// check box
		detenerPorError = new JCheckBox
			("Detener el entrenamiento por errores m�ximo y m�nimo",true);
		
		// label
		textoPanelEntrenando = new JLabel("Aprendizaje Supervisado 0/1");
		textoNumeroPatrones = new JLabel("N�mero de Patrones: 0",JLabel.CENTER);
		
		// progress bar
		barraProgreso = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		barraProgreso.setStringPainted(true);
		
		// creando fuente
		Font fuentePequenia = new Font("Arial", Font.PLAIN, 9);
		
		// asignando la fuente
		textoAnosDisponible.setFont(fuentePequenia);
		textoAnosEntrenada.setFont(fuentePequenia);
		
		// asignando titulos
		panelEleccionRed.setBorder(tituloEleccionRed);
		panelTiempoEntrenar.setBorder(tituloTiempoEntrenar);
		panelEntrenando.setBorder(tituloEntrenando);
		
		// bordes cero
		item[0].setBorderPainted(false);
		item[1].setBorderPainted(false);
		
		// asignando posiciones a los paneles
		panelEleccionRed.setBounds(10,15,800,90);
		panelTiempoEntrenar.setBounds(10,120,800,250);
		panelEntrenando.setBounds(10,385,800,110);
		
		// asignando posiciones a los componentes tipo de red
		textoEleccionRed1.setBounds(20,25,70,55);
		textoEleccionRed2.setBounds(100,25,710,15);
		item[0].setBounds(230,50,170,20);
		item[1].setBounds(410,50,170,20);
		
		// asignando posiciones a los componentes datos de aprendizaje
		textoTiempoEntrenar1.setBounds(20,25,770,15);
		textoTiempoEntrenar2.setBounds(20,40,770,15);
		textoAnosDisponible.setBounds(215,75,130,15);
		barraTablaAnosDisponible.setBounds(180,90,130,150);
		textoAnosEntrenada.setBounds(515,75,130,15);
		barraTablaAnosEntrenada.setBounds(490,90,130,150);
		botonAgregar.setBounds(315,90,170,30);
		botonQuitar.setBounds(315,130,170,30);
		botonRefrescar.setBounds(315,170,170,30);
		textoNumeroPatrones.setBounds(315,210,170,30);
		
		// asignando posiciones a los componentes estado del entrenamiento
		textoPanelEntrenando.setBounds(320,15,230,20);
		barraProgreso.setBounds(180,40,440,30);
		detenerPorError.setBounds(240,75,350,20);
		
		// asignando posiciones a los componentes botones
		botonEntrenar.setBounds(270,510,120,30);
		botonDetener.setBounds(400,510,120,30);
		
		// cargando la informacion de la base datos en la tabla
		refrescarTablaAnosDisponible();
		
		// dando cualidades de inicio
		item[0].setForeground(NO_ENTRENADA);
		item[1].setForeground(NO_ENTRENADA);
		botonDetener.setEnabled(false);
		
		// agregando evento de teclas
		botonEntrenar.setMnemonic(KeyEvent.VK_N);
		botonDetener.setMnemonic(KeyEvent.VK_D);
		botonAgregar.setMnemonic(KeyEvent.VK_A);
		botonQuitar.setMnemonic(KeyEvent.VK_Q);
		botonRefrescar.setMnemonic(KeyEvent.VK_R);
		
		// asignando tool tips
		botonEntrenar.setToolTipText("Iniciar el entrenamiento");
		botonDetener.setToolTipText("Detener el entrenamiento");
		botonAgregar.setToolTipText("Agregar un a�o al entrenamiento");
		botonQuitar.setToolTipText("Quitar un a�o al entrenamiento");
		botonRefrescar.setToolTipText("Actualizar los datos de entrenamiento");
		tablaAnosDisponibles.setToolTipText("A�os disponibles para entrenar");
		tablaAnosEntrenadas.setToolTipText("A�os que se quieren entrenar");
		detenerPorError.setToolTipText("Para detener el entrenamiento cuando "+
									   "sobrepasa cualquiera de estos rangos");
		barraProgreso.setToolTipText("Muestra el progreso del entrenamiento");
		item[0].setToolTipText("Ocuparla cuando los datos muestran algunas "+
							   "tendencias en largo plazo");
		item[1].setToolTipText("Ocuparla cuando los datos no muestran algunas "+
							   "tendencias en largo plazo");
		
		// propiedades de las tablas
		tablaAnosDisponibles.configurarEditableTabla(false);
		tablaAnosEntrenadas.configurarEditableTabla(false);
		
		// agregando al grupo
		grupoRedes.add(item[0]);
		grupoRedes.add(item[1]);
		
		// agregando al panel tipo de red.
		panelEleccionRed.add(textoEleccionRed1);
		panelEleccionRed.add(textoEleccionRed2);
		panelEleccionRed.add(item[0]);
		panelEleccionRed.add(item[1]);
		
		// agregando al panel datos de aprendizaje.
		panelTiempoEntrenar.add(textoTiempoEntrenar1);
		panelTiempoEntrenar.add(textoTiempoEntrenar2);
		panelTiempoEntrenar.add(botonAgregar);
		panelTiempoEntrenar.add(botonQuitar);
		panelTiempoEntrenar.add(botonRefrescar);
		panelTiempoEntrenar.add(textoNumeroPatrones);
		panelTiempoEntrenar.add(textoAnosDisponible);
		panelTiempoEntrenar.add(textoAnosEntrenada);
		panelTiempoEntrenar.add(barraTablaAnosDisponible);
		panelTiempoEntrenar.add(barraTablaAnosEntrenada);
		
		// agregando al panel estado del entrenamiento.
		panelEntrenando.add(textoPanelEntrenando);
		panelEntrenando.add(barraProgreso);
		panelEntrenando.add(detenerPorError);
		
		// agregando al panel
		add(panelEleccionRed);
		add(panelTiempoEntrenar);
		add(panelEntrenando);
		add(botonEntrenar);
		add(botonDetener);
	}
	
	/**
	 * M�todo que adjunta los escuchadores eventos a los botones de acceso
	 * r�pido que tiene el panel de entrenar. En particular se incorpora el 
	 * escuchador de eventos del tipo EventoButton y EventoRadioButton a los
	 * JButton y RadioButton que tiene el panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		EventoRadioButton eventoRadioButton = new EventoRadioButton(this);
		
		// Incorporar el escuchador de eventos a los botones y radio botones.
		botonEntrenar.addActionListener(eventoButton);
		botonDetener.addActionListener(eventoButton);
		botonAgregar.addActionListener(eventoButton);
		botonQuitar.addActionListener(eventoButton);
		botonRefrescar.addActionListener(eventoButton);
		item[0].addActionListener(eventoRadioButton);
		item[1].addActionListener(eventoRadioButton);
	}	
	
	/**
     * M�todo que cambia el texto de aprendizaje al segmento de informaci�n de
     * entrenamiento.
     * 
     * @param opcion Opci�n de texto que aparecera en la informacion de
     *				 entrenamiento.
     *
     */
	public void cambiarTextoAprendizaje(int opcion)
	{
		String texto = "";
		
		switch (opcion) 
		{
	    	case 0:
	    		texto = "Aprendizaje Supervisado 0/1";
	    		break;
	    	
	    	case 1: 
	    		texto = "Aprendizaje No Supervisado 0/2";
	    		break;
	    	
	    	case 2: 
	    		texto = "Aprendizaje Supervisado 1/2";
	    		break;
	    	
	    	case 3: 
	    		texto = "Aprendizaje Completado";
	    		break;
	    	
	    	default:
	    		texto = "Aprendizaje Supervisado 0/ 1";
	    		break;
	    }
	    
	    textoPanelEntrenando.setText(texto);
	}
	
	/**
     * M�todo que retorna si est� activo el check box de detener por error.
     * 
     * @return activo Boleano que indica si est� activo o no el check box de
     *				  detener por error.
     */
	public boolean estaActivoDetenerPorError()
	{
		boolean activo = detenerPorError.isSelected();
		return activo;
	}
	
    /**
     * M�todo que habilita o deshabilita el bot�n agregar.
     * 
     * @param bool Boleano que maneja la habilitaci�n del bot�n.
     */
	public void habilitarBotonAgregar(boolean bool)
	{
		botonAgregar.setEnabled(bool);
	}
	
    /**
     * M�todo que habilita o deshabilita el bot�n quitar.
     * 
     * @param bool Boleano que maneja la habilitaci�n del bot�n.
     */
	public void habilitarBotonQuitar(boolean bool)
	{
		botonQuitar.setEnabled(bool);
	}
	
    /**
     * M�todo que habilita o deshabilita el bot�n refrescar.
     * 
     * @param bool Boleano que maneja la habilitaci�n del bot�n.
     */
	public void habilitarBotonRefrescar(boolean bool)
	{
		botonRefrescar.setEnabled(bool);
	}
	
	/**
     * M�todo que habilita o deshabilita el bot�n entrenar.
     * 
     * @param bool Boleano que maneja la habilitaci�n del bot�n.
     */
	public void habilitarBotonEntrenar(boolean bool)
    {
    	botonEntrenar.setEnabled(bool);
    }
    
    /**
     * M�todo que habilita o deshabilita el bot�n detener.
     * 
     * @param bool Boleano que maneja la habilitaci�n del bot�n.
     */
    public void habilitarBotonDetener(boolean bool)
    {
    	botonDetener.setEnabled(bool);
    }
	
    /**
     * M�todo que habilita o deshabilita los radio buton donde se elige la red
     * neuronal.
     * 
     * @param bool Boleano que maneja la habilitaci�n de los radio button.
     */
	public void habilitarRadioButon(boolean bool)
	{
		item[0].setEnabled(bool);
		item[1].setEnabled(bool);
	}
	
	/**
	 * M�todo que cambia el color de la red neuronal elegida. El color que
	 * puede cambiar es tipo entrenada o de tipo no entrenada.
	 *
	 * @param eleccionRed Indicador de la red.
	 * @param entrenada Inidica si la red esta entrenada o no.
	 */
	public void cambiarColorRadioButton(int eleccionRed, boolean entrenada)	
	{
		if (entrenada)
			item[eleccionRed].setForeground(ENTRENADA);
		else
			item[eleccionRed].setForeground(NO_ENTRENADA);
	}
	
	/**
     * M�todo que cambia el estado de la barra de progreso del entrenamiento.
     *
     * @param porcentaje Valor que setea la barra del progreso.
     */
	public void cambiarBarraProgreso(int porcentaje)
	{
		barraProgreso.setValue(porcentaje);
	}
	
	/**
     * M�todo que resfresca los datos disponibles en la base datos a la lista de
     * datos disponibles. Estos datos estan medidos en a�os, lo que puede
     * implicar que puede haber sin los datos de todos los meses completos.
     */
	public void refrescarTablaAnosDisponible()
	{
		int i, codigoRegion, codigoRecurso;
		Integer[] datos = new Integer[2];
		String consulta;
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		
		codigoRegion = Proyecto.obtenerCodigoRegion();
		codigoRecurso = Proyecto.obtenerCodigoRecurso();
		
		consulta = "select codigo_anio,count(codigo_anio) " +
				   "from dato_economico_real where " +
				   "codigo_region = " + codigoRegion + " and " +
				   "codigo_recurso = " + codigoRecurso + " " +
				   "group by codigo_anio";
		
		tablaAnosDisponibles.limpiar();
		tablaAnosEntrenadas.limpiar();
		cambiarTextoNumeroPatrones(0);
		
		conexion.conectar();
		resultado = conexion.ejecutarConsulta(consulta);
		
		try
		{
			resultado.first();
			while (!resultado.isAfterLast())
			{
				datos[0] = new Integer(resultado.getInt("codigo_anio"));
				datos[1] = new Integer(resultado.getInt(2));
				tablaAnosDisponibles.agregarFila(datos);
				resultado.next();
		    }
	    }
	    catch (SQLException ex)
	    {
	    	System.out.println("Error al cargar la tabla dato_economico_real.");
	    }
		
		conexion.desconectar();
	}
	
	/**
     * M�todo que cambia el texto que muestra el n�mero de patrones que se
     * elijen para entrenar la red.
     * 
     * @param numeroPatrones El n�mero de patrones que la red tiene para
     *						 entrenar.
     */
	public void cambiarTextoNumeroPatrones(int numeroPatrones)
	{
		textoNumeroPatrones.setText("N�mero de Patrones: " + numeroPatrones);
	}
	
	/**
     * M�todo que agrega los elementos seleccionados en la tabla a�os
     * disponibles a la tabla a�os entrada, siempre y cuando no haya sido
     * agreado antes. Despu�s de agregarlos limpia la seleccion de los datos.
     */
	public void agregarTablaAnosEntrenada()
	{
		int i,esta;
		Object[][] datos =
			tablaAnosDisponibles.obtenerElementosFilasSeleccionadas();
		
		for (i = 0; i < datos.length; i++)
		{
			esta =
				tablaAnosEntrenadas.buscarPosicionFila("A�os",datos[i][0]);
			
			if (esta == -1)
				tablaAnosEntrenadas.agregarFila(datos[i]);
		}
		
		tablaAnosDisponibles.clearSelection();
	}
	
	/**
     * M�todo que devuelve las entradas que son parte de patrones que necesita 
     * la red neuronal para entrenar. Los devuelve en el formato de matriz,
     * donde las filas son los patrones y las columnas las entradas en
     * particular.
     * 
     * @return entrada Matriz que contiene las entradas para la red neuronal.
     */
	public double[][] obtenerEntradasRed()
	{
		double[][] entradas =
			new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES]
					  [ConfiguracionNeuronalBP.NUMERO_MAXIMO_ENTRADAS];
		
		int i, numeroFilas,codigoRegion,codigoRecurso;
		String consulta, condiciones;
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		
		codigoRegion = Proyecto.obtenerCodigoRegion();
		codigoRecurso = Proyecto.obtenerCodigoRecurso();
		
		condiciones = "";
		numeroFilas = tablaAnosEntrenadas.getRowCount();
		
		for (i = 0; i < numeroFilas; i++)
			condiciones += " or codigo_anio = " +
							(Integer) (tablaAnosEntrenadas.obtenerFila(i))[0];
		
		condiciones = condiciones.substring(4,condiciones.length());
		condiciones = "(" + condiciones + ")";
		
		consulta = "select codigo_anio, codigo_dia " +
				   "from dato_economico_real where " +
				   "codigo_region = " + codigoRegion + " and " +
				   "codigo_recurso = " + codigoRecurso + " and " +
					condiciones;
		
		conexion.conectar();
		resultado = conexion.ejecutarConsulta(consulta);
		
		try 
		{
	    	resultado.first();
	    	for (i = 0; i < obtenerNumeroPatronesTablaAnosEntrenadas(); i++ )
	    	{
	    		entradas[i][0] = resultado.getInt("codigo_anio");
	    		entradas[i][1] = resultado.getInt("codigo_dia");
	     		resultado.next();
	    	}
	    }
	    catch (SQLException ex)
	    {
	    	System.out.println("Error al entregar entradas a la red.");
	    }
		
		conexion.desconectar();
		
		return entradas;
	}
	
	/**
     * M�todo que obtiene las salidas deseadas para los datos del aprendizaje.
     * Estos datos los obtiene de consulta que se hace a traves de la bse de
     * datos con las los datos involucrados en la tabla de datos para aprender.
     * 
     * @return salidas Las salidas de la que la red necesita para aprender en su
     *				   fase de aprendizaje.
     */
	public double[][] obtenerSalidasRed()
	{
		double[][] salidas =
			new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES]
					  [ConfiguracionNeuronalBP.NUMERO_MAXIMO_SALIDAS];
		
		int i, numeroFilas,codigoRegion,codigoRecurso;
		String consulta, condiciones;
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		
		codigoRegion = Proyecto.obtenerCodigoRegion();
		codigoRecurso = Proyecto.obtenerCodigoRecurso();
		
		condiciones = "";
		numeroFilas = tablaAnosEntrenadas.getRowCount();
		
		for (i = 0; i < numeroFilas; i++)
			condiciones += " or codigo_anio = " +
							(Integer) (tablaAnosEntrenadas.obtenerFila(i))[0];
		
		condiciones = condiciones.substring(4,condiciones.length());
		condiciones = "(" + condiciones + ")";
		
		consulta =
			"select precio_dato_economico_real, demanda_dato_economico_real " +
			"from dato_economico_real where " +
			"codigo_region = " + codigoRegion + " and " +
			"codigo_recurso = " + codigoRecurso + " and " +
			condiciones;
		
		conexion.conectar();
		resultado = conexion.ejecutarConsulta(consulta);
		
		try
		{
	    	resultado.first();
	    	for (i = 0; i < obtenerNumeroPatronesTablaAnosEntrenadas(); i++)
	    	{
	    		salidas[i][0] = resultado.getDouble
	    						("demanda_dato_economico_real");
	    		salidas[i][1] = resultado.getDouble
	    						("precio_dato_economico_real");
	    		resultado.next();
	    	}
	    }
	    catch (SQLException ex)
	    {
	    	System.out.println("Error al entregar salidas a la red.");
	    }
		
		conexion.desconectar();
		
		return salidas;
	}
	
	/**
     * M�todo que devuelve la tabla de a�os entrenados.
     * 
     * @return tablaAnosEntrenadas La tabla de a�os en forma entrenada.
     */
	public Object[][] obtenerDatosTablaAnosEntrenadas()
	{
		return tablaAnosEntrenadas.obtenerTodasFilas();
	}
	
	/**
     * M�todo que devuelve el n�mero de patrones que contiene la tabla entrenar.
     * 
     * @return numeroPatrones El n�mero de patrones que contiene la tabla
     *                        entrenar.
     */
	public int obtenerNumeroPatronesTablaAnosEntrenadas()
	{
		Integer dato;
		int i,numeroFilas,numeroPatronesTablaAnosEntrenada;
		
		numeroFilas = tablaAnosEntrenadas.getRowCount();
		numeroPatronesTablaAnosEntrenada = 0;
		
		for (i = 0; i < numeroFilas; i++)
		{
			dato = (Integer) ((tablaAnosEntrenadas.obtenerFila(i))[1]);
			numeroPatronesTablaAnosEntrenada += dato.intValue();
		}
		
		return numeroPatronesTablaAnosEntrenada;
	}
	
	/**
     * M�todo que elimina los datos seleccionados de la tabla a�os entrenada.
     */
	public void eliminarTablaAnosEntrenada()
	{
		tablaAnosEntrenadas.removerFilasSeleccionada();
	}
	
	/**
	 * M�todo que obtiene el objeto botonAgregar
	 *
	 * @return botonAgregar El bot�n agregar del panel entrenar.
	 */
	public JButton obtenerBotonAgregar()
	{
		return botonAgregar;
	}
	
	/**
	 * M�todo que obtiene el objeto botonQuitar.
	 *
	 * @return botonQuitar El bot�n quitar del panel entrenar.
	 */
	public JButton obtenerBotonQuitar()
	{
		return botonQuitar;
	}
	
	/**
	 * M�todo que obtiene el objeto botonRefrescar.
	 *
	 * @return botonRefrescar El bot�n refrescar del panel entrenar.
	 */
	public JButton obtenerBotonRefrescar()
	{
		return botonRefrescar;
	}
	
	/**
	 * M�todo que obtiene el objeto botonEntrenar.
	 *
	 * @return botonEntrenar El bot�n entrenar del panel entrenar.
	 */
	public JButton obtenerBotonEntrenar()
	{
		return botonEntrenar;
	}
	
	/**
	 * M�todo que obtiene el objeto botonDetener.
	 *
	 * @return botonDetener El bot�n detener del panel entrenar.
	 */
	public JButton obtenerBotonDetener()
	{
		return botonDetener;
	}
	
	/**
	 * M�todo que obtiene el objeto itemBP.
	 *
	 * @return itemBP El radio button de selecci�n red.
	 */
	public JRadioButton obtenerItemBP()
	{
		return item[0];
	}
	
	/**
	 * M�todo que obtiene el objeto itemRBF.
	 *
	 * @return itemRBF El radio button de selecci�n red.
	 */
	public JRadioButton obtenerItemRBF()
	{
		return item[1];
	}
}