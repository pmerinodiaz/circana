/**
 * @(#)FrameEvaluacionStock.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */
 
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
 
/**
 * Clase que extiende de la clase JDialog. Este dialog permite el ingreso de los
 * datos reales correspondientes a la tabla dato_ecosistemico_real.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Calendar
 * @see ResultSet
 * @see SQLException
 * @see Toolkit
 * @see Container
 * @see Color
 * @see Font
 * @see KeyEvent
 * @see JDialog
 * @see JPanel
 * @see JLabel
 * @see JButton
 * @see JComboBox
 * @see JOptionPane
 * @see ImageIcon
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see CampoNumerico
 * @see FrameCircanaPro
 * @see InterfaceFrame
 * @see InterfaceAdministracion
 */ 
public class FrameEvaluacionStock extends JDialog
	implements InterfaceFrame, InterfaceAdministracion
{
	/** Frame que hace referencia al creador de este objeto. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Panel con los datos necesarios para crear un registro. */
	private JPanel panelDatos;
	
	/** Campo de texto con datos de abundancia. */
	private CampoNumerico campoAbundancia;
	
	/** Campo de texto con datos de porcentaje de machos. */
	private CampoNumerico campoPorcentajeMachos;
	
	/** Campo de texto con datos de porcentaje de hembras. */
	private CampoNumerico campoPorcentajeHembras;
	
	/** Campo de texto con datos de porcentaje de hembras sin huevos. */
	private CampoNumerico campoPorcentajeHembrasSH;
	
	/** Campo de texto con datos de porcentaje de hembras con huevos. */
	private CampoNumerico campoPorcentajeHembrasCH;
	
	/** Campo de texto con datos de talla. */
	private CampoNumerico campoTalla;
	
	/** Campo de texto con datos de talla de machos. */
	private CampoNumerico campoTallaMachos;
	
	/** Campo de texto con datos de talla de hembras. */
	private CampoNumerico campoTallaHembras;
	
	/** Campo de texto con datos de talla de hembras sin huevos. */
	private CampoNumerico campoTallaHembrasSH;
	
	/** Campo de texto con datos de talla de hembras con huevos. */
	private CampoNumerico campoTallaHembrasCH;
	
	/** Campo de texto con datos de abundancia. */
	private CampoNumerico campoBiomasa;
	
	/** Campo de texto con datos de peso. */
	private CampoNumerico campoPeso;
	
	/** Campo de texto con datos de peso de machos. */
	private CampoNumerico campoPesoMachos;
	
	/** Campo de texto con datos de peso de hembras. */
	private CampoNumerico campoPesoHembras;
	
	/** Campo de texto con datos de peso de hembras sin huevos. */
	private CampoNumerico campoPesoHembrasSH;
	
	/** Campo de texto con datos de peso de hembras con huevos. */
	private CampoNumerico campoPesoHembrasCH;
	
	/** Combo para seleccionar la región. */
	private JComboBox comboRegion;
	
	/** Combo para seleccionar el recurso. */
	private JComboBox comboRecurso;	
	
	/** Combo para seleccionar el día. */
	private JComboBox comboDia;
	
	/** Combo para seleccionar el mes. */
	private JComboBox comboMes;
	
	/** Combo para seleccionar el año. */
	private JComboBox comboAnio;
	
	/** El botón guardar los datos ingresados. */
	private JButton botonGuardar;
	
	/** El botón cancelar para abortar la operación de ingreso de datos. */
	private JButton botonCancelar;	
	
	/**
	 * Método constructor que configura el frame de ingreso de datos reales. Se
	 * configuran las cajas de texto, los combo box, los botones y los eventos.
	 *
	 * @param frameCircanaPro Frame creador de este objeto.
	 */
	public FrameEvaluacionStock(FrameCircanaPro frameCircanaPro)
	{
		super(frameCircanaPro);
		
		// Inicializar el puntero.
		this.frameCircanaPro = frameCircanaPro;
		
		// Configurar.
		configurarFrame();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se inicializan los objetos que se usan en todo este
	 * frame. En particular, este frame no maneja elementos especiales.
	 */
	public void configurarElementosEspeciales()
	{
	}
	
	/**
	 * Método que configura las características de este frame.
	 */	
	public void configurarFrame()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		setTitle("Evaluación de Stock");
		setSize(800,700);
		setLocation(tk.getScreenSize().width/2-400,10);
		setResizable(false);
		setModal(true);
	}
	
	/**
	 * Método que configura los paneles de este frame y sus componentes GUI.
	 */	
	public void configurarComponentes()
	{
		// Capturar el container de este frame.
		Container contenedor = getContentPane();		
		
		// El tipo de borde de los paneles.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los bordes de los paneles.
		TitledBorder titulo = new TitledBorder(borde,
			" Ingreso de datos de Evaluación de Stock ",
			TitledBorder.LEFT,
			TitledBorder.TOP);
		
		// Crear los paneles.
		panelDatos = new JPanel(null);
				
		// Setear el borde de los paneles.
		panelDatos.setBorder(titulo);
		
		// Setear el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Cambiar el tamaño y posición de los paneles.
		panelDatos.setBounds(5, 10, this.getWidth() - 15, 600);
		
		// Configurar el panel de datos.
		configurarTexto();
		configurarTextField();
		configurarTextoRecomendacion();
		configurarCombobox();
		
		// Crear los botones.
		botonGuardar = new JButton("Guardar",
			new ImageIcon("../img/proyecto_guardar.gif"));
		botonCancelar = new JButton("Cancelar",
			new ImageIcon("../img/cancelar.gif"));
		
		// Posicionar los componentes del frame.
		botonGuardar.setBounds(this.getWidth()/2-160,630,150,30);
		botonCancelar.setBounds(this.getWidth()/2+10,630,150,30);
				
		// Asignar acciones de teclas.
		botonGuardar.setMnemonic(KeyEvent.VK_G);
		botonCancelar.setMnemonic(KeyEvent.VK_C);
		
		// Asginar los tool tips.
		botonGuardar.setToolTipText("Guardar en la base de datos");
		botonCancelar.setToolTipText("Cerrar la ventana");			
		
		// Agregar al contenedor.
		contenedor.add(panelDatos);
		contenedor.add(botonGuardar);
		contenedor.add(botonCancelar);
	}
	
	/**
     * Método donde se configura los textos de ingreso de datos reales de la
     * evaluación de stock.
     */
	private void configurarTexto()
	{
		// Crear los textos.
		JLabel textoAbundancia =
			new JLabel("Abundancia:");
		JLabel textoPorcentajeMachos =
			new JLabel("Porcentaje Machos:");
		JLabel textoPorcentajeHembras =
			new JLabel("Porcentaje Hembras:");
		JLabel textoPorcentajeHembrasSH =
			new JLabel("Porcentaje Hembras Sin Huevos:");
		JLabel textoPorcentajeHembrasCH =
			new JLabel("Porcentaje Hembras Con Huevos:");		
		JLabel textoTalla =
			new JLabel("Talla:");
		JLabel textoTallaMachos =
			new JLabel("Talla Machos:");
		JLabel textoTallaHembras =
			new JLabel("Talla Hembras:");
		JLabel textoTallaHembrasSH =
			new JLabel("Talla Hembras Sin Huevos:");
		JLabel textoTallaHembrasCH =
			new JLabel("Talla Hembras Con Huevos:");
		JLabel textoBiomasa =
			new JLabel("Biomasa:");
		JLabel textoPeso =
			new JLabel("Peso:");
		JLabel textoPesoMachos =
			new JLabel("Peso Machos:");
		JLabel textoPesoHembras =
			new JLabel("Peso Hembras:");
		JLabel textoPesoHembrasSH =
			new JLabel("Peso Hembras Sin Huevos:");
		JLabel textoPesoHembrasCH =
			new JLabel("Peso Hembras Con Huevos:");
		JLabel textoRegion =
			new JLabel("Región:");
		JLabel textoRecurso =
			new JLabel("Recurso:");
		JLabel textoFecha =
			new JLabel("Fecha (día/mes/año):");
		
		// Posicionar textos.
		textoAbundancia.setBounds(50,20,190,20);
		textoPorcentajeMachos.setBounds(50,50,190,20);
		textoPorcentajeHembras.setBounds(50,80,190,20);
		textoPorcentajeHembrasSH.setBounds(50,110,190,20);
		textoPorcentajeHembrasCH.setBounds(50,140,190,20);
		textoTalla.setBounds(50,170,190,20);
		textoTallaMachos.setBounds(50,200,190,20);
		textoTallaHembras.setBounds(50,230,190,20);
		textoTallaHembrasSH.setBounds(50,260,190,20);
		textoTallaHembrasCH.setBounds(50,290,190,20);
		textoBiomasa.setBounds(50,320,190,20);
		textoPeso.setBounds(50,350,190,20);
		textoPesoMachos.setBounds(50,380,190,20);
		textoPesoHembras.setBounds(50,410,190,20);
		textoPesoHembrasSH.setBounds(50,440,190,20);
		textoPesoHembrasCH.setBounds(50,470,190,20);
		textoRegion.setBounds(50,500,190,20);
		textoRecurso.setBounds(50,530,190,20);
		textoFecha.setBounds(50,560,190,20);		
		
		// Agregar textos al panel de datos.
		panelDatos.add(textoAbundancia);
		panelDatos.add(textoPorcentajeMachos);
		panelDatos.add(textoPorcentajeHembras);
		panelDatos.add(textoPorcentajeHembrasSH);
		panelDatos.add(textoPorcentajeHembrasCH);
		panelDatos.add(textoTalla);
		panelDatos.add(textoTallaMachos);
		panelDatos.add(textoTallaHembras);
		panelDatos.add(textoTallaHembrasSH);
		panelDatos.add(textoTallaHembrasCH);
		panelDatos.add(textoBiomasa);
		panelDatos.add(textoPeso);
		panelDatos.add(textoPesoMachos);
		panelDatos.add(textoPesoHembras);
		panelDatos.add(textoPesoHembrasSH);
		panelDatos.add(textoPesoHembrasCH);
		panelDatos.add(textoRegion);
		panelDatos.add(textoRecurso);
		panelDatos.add(textoFecha);
	}
	
	/**
     * Método donde se configura los textos de recomendación de la evaluación 
     * del stock.
     */
	private void configurarTextoRecomendacion()
	{
		// Creando fuente para la recomendación.
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// Crear los textos.
		JLabel recomendacionAbundancia =
			new JLabel("Ingrese la abundancia en unidades");
		JLabel recomendacionPorcentajeMachos =
			new JLabel("Ingrese el porcentaje de machos entre [0, 100]");
		JLabel recomendacionPorcentajeHembras =
			new JLabel("Ingrese el porcentaje de hembras entre [0, 100]");
		JLabel recomendacionPorcentajeHembrasSH =
			new JLabel("Ingrese el porcentaje de hembras sin huevos entre [0, 100]");
		JLabel recomendacionPorcentajeHembrasCH =
			new JLabel("Ingrese el porcentaje de hembras con huevos entre [0, 100]");
		JLabel recomendacionTalla =
			new JLabel("Ingrese la talla media en centímetros");
		JLabel recomendacionTallaMachos =
			new JLabel("Ingrese la talla media machos en centímetros");
		JLabel recomendacionTallaHembras =
			new JLabel("Ingrese la talla media hembras en centímetros");
		JLabel recomendacionTallaHembrasSH =
			new JLabel("Ingrese la talla media hembras sin huevos en centímetros");
		JLabel recomendacionTallaHembrasCH =
			new JLabel("Ingrese la talla media hembras con huevos en centímetros");
		JLabel recomendacionBiomasa =
			new JLabel("Ingrese la biomasa en toneladas");
		JLabel recomendacionPeso =
			new JLabel("Ingrese el peso medio en gramos");
		JLabel recomendacionPesoMachos =
			new JLabel("Ingrese el peso medio machos en gramos");
		JLabel recomendacionPesoHembras =
			new JLabel("Ingrese el peso medio hembras en gramos");
		JLabel recomendacionPesoHembrasSH =
			new JLabel("Ingrese el peso hembras sin huevos en gramos");
		JLabel recomendacionPesoHembrasCH =
			new JLabel("Ingrese el peso hembras con huevos en gramos");
		JLabel recomendacionRegion =
			new JLabel("Ingrese la región en donde se hizo la evaluación");
		JLabel recomendacionRecurso =
			new JLabel("Ingrese la especie objetivo de la evaluación");
		JLabel recomendacionFecha =
			new JLabel("Ingrese la fecha cuando se hizo la evaluación");
		
		// Posicionar recomendaciones.
		recomendacionAbundancia.setBounds(465,20,280,20);
		recomendacionPorcentajeMachos.setBounds(465,50,280,20);
		recomendacionPorcentajeHembras.setBounds(465,80,280,20);
		recomendacionPorcentajeHembrasSH.setBounds(465,110,280,20);
		recomendacionPorcentajeHembrasCH.setBounds(465,140,280,20);
		recomendacionTalla.setBounds(465,170,280,20);
		recomendacionTallaMachos.setBounds(465,200,280,20);
		recomendacionTallaHembras.setBounds(465,230,280,20);
		recomendacionTallaHembrasSH.setBounds(465,260,280,20);
		recomendacionTallaHembrasCH.setBounds(465,290,280,20);
		recomendacionBiomasa.setBounds(465,320,280,20);
		recomendacionPeso.setBounds(465,350,280,20);
		recomendacionPesoMachos.setBounds(465,380,280,20);
		recomendacionPesoHembras.setBounds(465,410,280,20);
		recomendacionPesoHembrasSH.setBounds(465,440,280,20);
		recomendacionPesoHembrasCH.setBounds(465,470,280,20);
		recomendacionRegion.setBounds(465,500,280,20);
		recomendacionRecurso.setBounds(465,530,280,20);
		recomendacionFecha.setBounds(465,560,280,20);
		
		// Asignar fuente a recomendación.
		recomendacionAbundancia.setFont(fuenteRecomendacion);
		recomendacionPorcentajeMachos.setFont(fuenteRecomendacion);
		recomendacionPorcentajeHembras.setFont(fuenteRecomendacion);
		recomendacionPorcentajeHembrasSH.setFont(fuenteRecomendacion);
		recomendacionPorcentajeHembrasCH.setFont(fuenteRecomendacion);
		recomendacionTalla.setFont(fuenteRecomendacion);
		recomendacionTallaMachos.setFont(fuenteRecomendacion);
		recomendacionTallaHembras.setFont(fuenteRecomendacion);
		recomendacionTallaHembrasSH.setFont(fuenteRecomendacion);
		recomendacionTallaHembrasCH.setFont(fuenteRecomendacion);
		recomendacionBiomasa.setFont(fuenteRecomendacion);
		recomendacionPeso.setFont(fuenteRecomendacion);
		recomendacionPesoMachos.setFont(fuenteRecomendacion);
		recomendacionPesoHembras.setFont(fuenteRecomendacion);
		recomendacionPesoHembrasSH.setFont(fuenteRecomendacion);
		recomendacionPesoHembrasCH.setFont(fuenteRecomendacion);
		recomendacionRegion.setFont(fuenteRecomendacion);
		recomendacionRecurso.setFont(fuenteRecomendacion);
		recomendacionFecha.setFont(fuenteRecomendacion);
		
		// Agregar recomendaciones al panel de datos.
		panelDatos.add(recomendacionAbundancia);
		panelDatos.add(recomendacionPorcentajeMachos);
		panelDatos.add(recomendacionPorcentajeHembras);
		panelDatos.add(recomendacionPorcentajeHembrasSH);
		panelDatos.add(recomendacionPorcentajeHembrasCH);
		panelDatos.add(recomendacionTalla);
		panelDatos.add(recomendacionTallaMachos);
		panelDatos.add(recomendacionTallaHembras);
		panelDatos.add(recomendacionTallaHembrasSH);
		panelDatos.add(recomendacionTallaHembrasCH);
		panelDatos.add(recomendacionBiomasa);
		panelDatos.add(recomendacionPeso);
		panelDatos.add(recomendacionPesoMachos);
		panelDatos.add(recomendacionPesoHembras);
		panelDatos.add(recomendacionPesoHembrasSH);
		panelDatos.add(recomendacionPesoHembrasCH);
		panelDatos.add(recomendacionRegion);
		panelDatos.add(recomendacionRecurso);
		panelDatos.add(recomendacionFecha);
	}
	
	/**
     * Método donde se configuran los cuadros textos de ingreso de datos de la 
     * evaluación de stock.
     */
	private void configurarTextField()
	{
		// Crear los cuadros de textos.
		campoAbundancia = new CampoNumerico(0);
		campoPorcentajeMachos = new CampoNumerico(0.0);
		campoPorcentajeHembras = new CampoNumerico(0.0);
		campoPorcentajeHembrasSH = new CampoNumerico(0.0);
		campoPorcentajeHembrasCH = new CampoNumerico(0.0);
		campoTalla = new CampoNumerico(0.0);
		campoTallaMachos = new CampoNumerico(0.0);
		campoTallaHembras = new CampoNumerico(0.0);
		campoTallaHembrasSH = new CampoNumerico(0.0);
		campoTallaHembrasCH = new CampoNumerico(0.0);
		campoBiomasa = new CampoNumerico(0.0);
		campoPeso = new CampoNumerico(0.0);
		campoPesoMachos = new CampoNumerico(0.0);
		campoPesoHembras = new CampoNumerico(0.0);
		campoPesoHembrasSH = new CampoNumerico(0.0);
		campoPesoHembrasCH = new CampoNumerico(0.0);
		
		// Posicionar cuadros de textos.
		campoAbundancia.setBounds(265,20,190,20);
		campoPorcentajeMachos.setBounds(265,50,190,20);
		campoPorcentajeHembras.setBounds(265,80,190,20);
		campoPorcentajeHembrasSH.setBounds(265,110,190,20);
		campoPorcentajeHembrasCH.setBounds(265,140,190,20);
		campoTalla.setBounds(265,170,190,20);
		campoTallaMachos.setBounds(265,200,190,20);
		campoTallaHembras.setBounds(265,230,190,20);
		campoTallaHembrasSH.setBounds(265,260,190,20);
		campoTallaHembrasCH.setBounds(265,290,190,20);
		campoBiomasa.setBounds(265,320,190,20);
		campoPeso.setBounds(265,350,190,20);
		campoPesoMachos.setBounds(265,380,190,20);
		campoPesoHembras.setBounds(265,410,190,20);
		campoPesoHembrasSH.setBounds(265,440,190,20);
		campoPesoHembrasCH.setBounds(265,470,190,20);
		
		// Agregar cuadros de textos al panel de datos.
		panelDatos.add(campoAbundancia);
		panelDatos.add(campoPorcentajeMachos);
		panelDatos.add(campoPorcentajeHembras);
		panelDatos.add(campoPorcentajeHembrasSH);
		panelDatos.add(campoPorcentajeHembrasCH);
		panelDatos.add(campoTalla);
		panelDatos.add(campoTallaMachos);
		panelDatos.add(campoTallaHembras);
		panelDatos.add(campoTallaHembrasSH);
		panelDatos.add(campoTallaHembrasCH);
		panelDatos.add(campoBiomasa);
		panelDatos.add(campoPeso);
		panelDatos.add(campoPesoMachos);
		panelDatos.add(campoPesoHembras);
		panelDatos.add(campoPesoHembrasSH);
		panelDatos.add(campoPesoHembrasCH);
	}
	
	/**
     * Método donde se configura los combo box de ingreso de datos de la
     * evaluación del stock.
     */
	private void configurarCombobox()
	{
		int i;
		int anioActual;
		int diaActual;
		int mesActual;
		Calendar fecha = Calendar.getInstance();
		
		// Arreglo de mes y día.
		String[] mes = new String[12];;
		String[] dia = new String[31];
		
		diaActual = fecha.get(Calendar.DAY_OF_MONTH);
		mesActual = fecha.get(Calendar.MONTH) + 1;
		anioActual = fecha.get(Calendar.YEAR);				
				
		// Iniciando los valores del string.
		for (i=0; i<12; i++)
				mes[i] = "" + (i + 1);
		
		for (i=0; i<31; i++)
				dia[i] = "" + (i + 1);
		
		// Crear las instancias.
		comboRegion =  new JComboBox();
		comboRecurso = new JComboBox();
		comboDia = new JComboBox(dia);
		comboMes = new JComboBox(mes);
		comboAnio = new JComboBox();
		
		// Cargar opciones de selección.
		cargarComboRegion();
		cargarComboRecurso();
		cargarComboAnio();
		
		// Configurando combo fecha.
		comboDia.setSelectedItem("" + diaActual);
		comboMes.setSelectedItem("" + mesActual);
		comboAnio.setSelectedItem("" + anioActual);		
		
		// Posicionar combobox.
		comboRegion.setBounds(265,500,190,20);
		comboRecurso.setBounds(265,530,190,20);
		comboDia.setBounds(265,560,50,20);
		comboMes.setBounds(325,560,50,20);
		comboAnio.setBounds(385,560,70,20);
		
		// Agregar combobox al panel de datos.
		panelDatos.add(comboRegion);
		panelDatos.add(comboRecurso);
		panelDatos.add(comboDia);
		panelDatos.add(comboMes);
		panelDatos.add(comboAnio);
	}
	
	/**
     * Método que carga el combo box de los recursos.
     */
	private void cargarComboRecurso()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaRecurso = new BaseDatoTablaBasica(conexion,
																   "recurso");
		
		comboRecurso.removeAllItems();
		conexion.conectar();
		resultado = tablaRecurso.buscarCondicion("");
		
		try
		{
			resultado.first();
			do
			{
				comboRecurso.addItem(resultado.getString("nombre_comun_recurso"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla recurso.");
		}
		
		conexion.desconectar();
	}
	
	/**
     * Método que carga el combo box de las regiones.
     */
	private void cargarComboRegion()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaRegion = new BaseDatoTablaBasica(conexion,
																  "region");
		
		comboRegion.removeAllItems();
		conexion.conectar();
		resultado = tablaRegion.buscarCondicion("");
		
		try
		{
			resultado.first();
			do
			{
				comboRegion.addItem(resultado.getString("descripcion_region"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla region.");
		}
		
		conexion.desconectar();
	}
	
	/**
     * Método que carga el combo box de los años.
     */
	private void cargarComboAnio()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaAno = new BaseDatoTablaBasica(conexion,"anio");
		
		comboAnio.removeAllItems();
		conexion.conectar();
		resultado = tablaAno.buscarCondicion("");
		
		try
		{
			resultado.first();
			do
			{
				comboAnio.addItem(resultado.getString("codigo_anio"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla anio.");
		}
		
		conexion.desconectar();
	}
	
	/**
	 * Método que adjunta los escuchadores eventos que tiene el frame. En
	 * particular se incorpora el escuchador de eventos del tipo EventoButton a
	 * los JButton que tiene el panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonGuardar.addActionListener(eventoButton);
		botonCancelar.addActionListener(eventoButton);		
	}
	
	/**
	 * Método que limpia los componentes GUI que maneja este frame.
	 */
	public void limpiar()
	{
		campoAbundancia.setText("");
		campoPorcentajeMachos.setText("");
		campoPorcentajeHembras.setText("");
		campoPorcentajeHembrasSH.setText("");
		campoPorcentajeHembrasCH.setText("");
		campoTalla.setText("");
		campoTallaMachos.setText("");
		campoTallaHembras.setText("");
		campoTallaHembrasSH.setText("");
		campoTallaHembrasCH.setText("");
		campoBiomasa.setText("");
		campoPeso.setText("");
		campoPesoMachos.setText("");
		campoPesoHembras.setText("");
		campoPesoHembrasSH.setText("");
		campoPesoHembrasCH.setText("");
		comboRegion.setSelectedIndex(0);
		comboRecurso.setSelectedIndex(0);
		comboDia.setSelectedIndex(0);
		comboMes.setSelectedIndex(0);
		comboAnio.setSelectedIndex(0);
	}
	
	/**
     * Método que cierra la ventana de ingreso de datos.
     */
	public void cancelar()
	{
		if (frameCircanaPro != null)
			setVisible(false);
		else System.exit(0);
	}
	
	/**
	 * Método que graba los datos de evaluación de stock ingresados en la base
	 * de datos.
	 */
	public void guardar()
	{
		String error = validar();
		
		// Cuando no hay errores en los datos.
		if (error == "")
		{
			BaseDatoMotor conexion = new BaseDatoMotor();
			BaseDatoTablaBasica tablaEcosistemicoReal =
				new BaseDatoTablaBasica(conexion, "dato_ecosistemico_real");
			
			// Conectar a la base de datos.
			conexion.conectar();
			
			// Preparar los campos para agregar a la base datos.
			tablaEcosistemicoReal.agregarCampo("codigo_recurso", 
				"" + comboRecurso.getSelectedIndex());
			tablaEcosistemicoReal.agregarCampo("codigo_region", 
				"" + comboRegion.getSelectedIndex());
			tablaEcosistemicoReal.agregarCampo("codigo_anio", 
				"" + (String) comboAnio.getSelectedItem());
			tablaEcosistemicoReal.agregarCampo("codigo_dia", 
				"" + (String) comboDia.getSelectedItem());
			tablaEcosistemicoReal.agregarCampo("abundancia_dato_ecosistemico_real", 
				"" + campoAbundancia.getText());
			tablaEcosistemicoReal.agregarCampo("porcentaje_machos_dato_ecosistemico_real", 
				"" + campoPorcentajeMachos.getValueDouble()/100);
			tablaEcosistemicoReal.agregarCampo("porcentaje_hembras_dato_ecosistemico_real", 
				"" + campoPorcentajeHembras.getValueDouble()/100);
			tablaEcosistemicoReal.agregarCampo("porcentaje_hembras_sh_dato_ecosistemico_real", 
				"" + campoPorcentajeHembrasSH.getValueDouble()/100);
			tablaEcosistemicoReal.agregarCampo("porcentaje_hembras_ch_dato_ecosistemico_real", 
				"" + campoPorcentajeHembrasCH.getValueDouble()/100);
			tablaEcosistemicoReal.agregarCampo("talla_dato_ecosistemico_real", 
				"" + campoTalla.getText());
			tablaEcosistemicoReal.agregarCampo("talla_machos_dato_ecosistemico_real", 
				"" + campoTallaMachos.getText());
			tablaEcosistemicoReal.agregarCampo("talla_hembras_dato_ecosistemico_real", 
				"" + campoTallaHembras.getText());
			tablaEcosistemicoReal.agregarCampo("talla_hembras_sh_dato_ecosistemico_real", 
				"" + campoTallaHembrasSH.getText());
			tablaEcosistemicoReal.agregarCampo("talla_hembras_ch_dato_ecosistemico_real", 
				"" + campoTallaHembrasCH.getText());
			tablaEcosistemicoReal.agregarCampo("biomasa_dato_ecosistemico_real", 
				"" + campoBiomasa.getText());
			tablaEcosistemicoReal.agregarCampo("peso_dato_ecosistemico_real", 
				"" + campoPeso.getText());
			tablaEcosistemicoReal.agregarCampo("peso_machos_dato_ecosistemico_real", 
				"" + campoPesoMachos.getText());
			tablaEcosistemicoReal.agregarCampo("peso_hembras_dato_ecosistemico_real", 
				"" + campoPesoHembras.getText());
			tablaEcosistemicoReal.agregarCampo("peso_hembras_sh_dato_ecosistemico_real", 
				"" + campoPesoHembrasSH.getText());
			tablaEcosistemicoReal.agregarCampo("peso_hembras_ch_dato_ecosistemico_real", 
				"" + campoPesoHembrasCH.getText());
			
			// Agregar el registro.
			tablaEcosistemicoReal.agregar();
			
			// Desconectar la base de datos y cerrar la ventana.
			conexion.desconectar();
			frameCircanaPro.
			panelEscritorio.frameOperacion.
			actualizarInformacionProyecto();
			cancelar();
		}
		
		// Cuando hay errores en los datos.
		else
		{
			error = "Se han cometido los siguientes errores:\n" + error;
			JOptionPane.showMessageDialog(this, error,
				"Error en Ingreso de datos de Evaluación de Stock",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * Método que verifica si los datos ingresados en el frame tienen una 
     * sintaxis correcta.
     *
     * @return error Retorna un string con el error cometido.
     */
	public String validar()
	{
		String error = "";
		
		// Validando campo abundancia.
		if (!Servicio.esNumero(campoAbundancia.getText()) ||
			campoAbundancia.getValueInt() <= 0 ||
			Servicio.contarCaracter(campoAbundancia.getText(), '.') > 0)
			error += "- En el campo abundancia.\n";
		
		// Validando campo porcentaje machos.
		if (!Servicio.esNumero(campoPorcentajeMachos.getText()) ||
			campoPorcentajeMachos.getValueDouble() < 0 ||
			campoPorcentajeMachos.getValueDouble() > 100)
			error += "- En el campo porcentaje machos.\n";
		
		// Validando campo porcentaje hembras.
		if (!Servicio.esNumero(campoPorcentajeHembras.getText()) ||
			campoPorcentajeHembras.getValueDouble() < 0 ||
			campoPorcentajeHembras.getValueDouble() > 100)
			error += "- En el campo porcentaje hembras.\n";
		
		// Validando campo latitud porcentaje hembras sin huevos.
		if (!Servicio.esNumero(campoPorcentajeHembrasSH.getText()) ||
			campoPorcentajeHembrasSH.getValueDouble() < 0 ||
			campoPorcentajeHembrasSH.getValueDouble() > 100)
			error += "- En el campo porcentaje hembras sin huevos.\n";
		
		// Validando campo latitud porcentaje hembras con huevos.
		if (!Servicio.esNumero(campoPorcentajeHembrasCH.getText()) ||
			campoPorcentajeHembrasCH.getValueDouble() < 0 ||
			campoPorcentajeHembrasCH.getValueDouble() > 100)
			error += "- En el campo porcentaje hembras con huevos.\n";
		
		// Validando campo talla.
		if (!Servicio.esNumero(campoTalla.getText()) ||
			campoTalla.getValueDouble() <= 0)
			error += "- En el campo talla.\n";
		
		// Validando campo talla machos.
		if (!Servicio.esNumero(campoTallaMachos.getText()) ||
			campoTallaMachos.getValueDouble() <= 0)
			error += "- En el campo talla machos.\n";
		
		// Validando campo talla hembras.
		if (!Servicio.esNumero(campoTallaHembras.getText()) ||
			campoTallaHembras.getValueDouble() <= 0)
			error += "- En el campo talla hembras.\n";
		
		// Validando campo talla hembras sin huevos.
		if (!Servicio.esNumero(campoTallaHembrasSH.getText()) ||
			campoTallaHembrasSH.getValueDouble() <= 0)
			error += "- En el campo talla hembras sin huevos.\n";
		
		// Validando campo talla hembras con huevos.
		if (!Servicio.esNumero(campoTallaHembrasCH.getText()) ||
			campoTallaHembrasCH.getValueDouble() <= 0)
			error += "- En el campo talla hembras con huevos.\n";
		
		// Validando campo biomasa.
		if (!Servicio.esNumero(campoBiomasa.getText()) ||
			campoBiomasa.getValueDouble() <= 0)
			error += "- En el campo biomasa.\n";
		
		// Validando campo peso machos.
		if (!Servicio.esNumero(campoPesoMachos.getText()) ||
			campoPesoMachos.getValueDouble() <= 0)
			error += "- En el campo peso machos.\n";
		
		// Validando campo peso hembras.
		if (!Servicio.esNumero(campoPesoHembras.getText()) ||
			campoPesoHembras.getValueDouble() <= 0)
			error += "- En el campo peso hembras.\n";
		
		// Validando campo peso hembras sin huevos.
		if (!Servicio.esNumero(campoPesoHembrasSH.getText()) ||
			campoPesoHembrasSH.getValueDouble() <= 0)
			error += "- En el campo peso hembras sin huevos.\n";
		
		// Validando campo peso hembras con huevos.
		if (!Servicio.esNumero(campoPesoHembrasCH.getText()) ||
			campoPesoHembrasCH.getValueDouble() <= 0)
			error += "- En el campo peso hembras con huevos.\n";
		
		return error;
	}
	
	/**
     * Método que retorna el botón guardar.
     *
     * @return botonGuardar Botón "Guardar" del frame.
     */
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
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