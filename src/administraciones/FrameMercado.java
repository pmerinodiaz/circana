/**
 * @(#)FrameMercado.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */
 
import java.util.Calendar;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
 
/**
 * Clase que extiende de la clase JDialog. Este dialogo permite el ingreso de
 * los datos reales correspondientes a la tabla dato_economico_real.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
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
 * @see JTextField
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
public class FrameMercado extends JDialog
	implements InterfaceFrame, InterfaceAdministracion
{
	/** Frame que hace referencia al creador de este objeto. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Panel con los datos necesarios para crear un registro. */
	private JPanel panelDatos;	
	
	/** Campo de texto con datos de precio. */
	private CampoNumerico campoPrecio;
	
	/** Campo de texto con datos de demanda. */
	private CampoNumerico campoDemanda;
	
	/** Combo para seleccionar la regi�n. */
	private JComboBox comboRegion;
	
	/** Combo para seleccionar el recurso */
	private JComboBox comboRecurso;	
	
	/** Combo para seleccionar el d�a. */
	private JComboBox comboDia;
	
	/** Combo para seleccionar el mes. */
	private JComboBox comboMes;
	
	/** Combo para seleccionar el a�o. */
	private JComboBox comboAnio;
	
	/** El bot�n guardar para grabar los datos ingresados. */
	private JButton botonGuardar;
	
	/** El bot�n cancelar para abortar la operaci�n de ingreso de datos. */
	private JButton botonCancelar;	
	
	/**
	 * M�todo constructor que configura el frame de ingreso de datos reales. Se
	 * configura caja de texto, combobox, botones y eventos.
	 *
	 * @param frameCircanaPro Frame creador de este objeto.
	 */
	public FrameMercado(FrameCircanaPro frameCircanaPro)
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
	 * M�todo en donde se inicializan los objetos que se usan en todo este
	 * frame. En particular, este frame no maneja elementos especiales.
	 */
	public void configurarElementosEspeciales()
	{
	}
	
	/**
	 * M�todo que configura las caracter�sticas de este frame.
	 */	
	public void configurarFrame()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		setTitle("Mercado");
		setSize(620,300);
		setLocation(tk.getScreenSize().width/2-310,10);
		setResizable(false);
		setModal(true);
	}
	
	/**
	 * M�todo que configura los paneles de este frame y sus componentes GUI.
	 */	
	public void configurarComponentes()
	{
		// capturar el container de este frame
		Container contenedor = getContentPane();		
		
		// El tipo de borde de los paneles.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los bordes de los paneles.
		TitledBorder titulo = new TitledBorder(borde,
			" Ingreso de datos de Mercado",
			TitledBorder.LEFT,
			TitledBorder.TOP);
		
		// Crear los paneles.
		panelDatos = new JPanel(null);
		
		// Setear el borde de los paneles.
		panelDatos.setBorder(titulo);
		
		// Setear el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Cambiar el tama�o y posici�n de los paneles.
		panelDatos.setBounds(5, 10, this.getWidth() - 15, 200);
		
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
		botonGuardar.setBounds(this.getWidth()/2-160,230,150,30);
		botonCancelar.setBounds(this.getWidth()/2+10,230,150,30);
		
		// Asignar acciones de teclas.
		botonGuardar.setMnemonic(KeyEvent.VK_G);
		botonCancelar.setMnemonic(KeyEvent.VK_C);
		
		// Asginar tool tips.
		botonGuardar.setToolTipText("Ingresar en la base de datos");
		botonCancelar.setToolTipText("Cerrar la ventana");
		
		// Agregar al contenedor.
		contenedor.add(panelDatos);
		contenedor.add(botonGuardar);
		contenedor.add(botonCancelar);
	}
	
	/**
     * M�todo donde se configura los textos de ingreso de datos reales del 
     * m�dulo econom�a.
     */
	private void configurarTexto()
	{
		// Crear los textos.
		JLabel textoPrecio = new JLabel("Precio:");
		JLabel textoDemanda = new JLabel("Demanda:");
		JLabel textoRegion = new JLabel("Regi�n:");
		JLabel textoRecurso = new JLabel("Recurso:");
		JLabel textoFecha = new JLabel("Fecha (d�a/mes/a�o):");
		
		// Posicionar textos.
		textoPrecio.setBounds(50,20,190,20);		
		textoDemanda.setBounds(50,50,190,20);
		textoRegion.setBounds(50,80,190,20);
		textoRecurso.setBounds(50,110,190,20);
		textoFecha.setBounds(50,140,190,20);
		
		// Agregar textos al panel de datos.
		panelDatos.add(textoPrecio);
		panelDatos.add(textoDemanda);
		panelDatos.add(textoRegion);
		panelDatos.add(textoRecurso);
		panelDatos.add(textoFecha);
	}
	
	/**
     * M�todo donde se configura los textos de recomendaci�n de datos reales del 
     * m�dulo econom�a.
     */
	private void configurarTextoRecomendacion()
	{
		// Creando fuente para la recomendaci�n.
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// Crear los textos.
		JLabel recomendacionPrecio = new JLabel(
			"Ingrese el precio en US$/kilogramos");
		JLabel recomendacionDemanda = new JLabel(
			"Ingrese la demanda toneladas");
		JLabel recomendacionRegion = new JLabel(
			"Ingrese la regi�n del mercado");
		JLabel recomendacionRecurso = new JLabel(
			"Ingrese el recurso objetivo del mercado");
		JLabel recomendacionFecha = new JLabel(
			"Ingrese la fecha de venta del recurso");
		
		// Posicionar recomendaciones.
		recomendacionPrecio.setBounds(405,20,250,20);
		recomendacionDemanda.setBounds(405,50,250,20);
		recomendacionRegion.setBounds(405,80,250,20);
		recomendacionRecurso.setBounds(405,110,250,20);
		recomendacionFecha.setBounds(405,140,250,20);
		
		// Asignar fuente a recomendaci�n.
		recomendacionPrecio.setFont(fuenteRecomendacion);
		recomendacionDemanda.setFont(fuenteRecomendacion);
		recomendacionRegion.setFont(fuenteRecomendacion);
		recomendacionRecurso.setFont(fuenteRecomendacion);
		recomendacionFecha.setFont(fuenteRecomendacion);
		
		// Agregar recomendaciones al panel de datos.
		panelDatos.add(recomendacionPrecio);
		panelDatos.add(recomendacionDemanda);
		panelDatos.add(recomendacionRegion);
		panelDatos.add(recomendacionRecurso);
		panelDatos.add(recomendacionFecha);
	}
	
	/**
     * M�todo donde se configura los cuadros textos de ingreso de datos reales 
     * del m�dulo econom�a.
     */
	private void configurarTextField()
	{
		// Crear los cuadros de textos.
		campoPrecio = new CampoNumerico(0.0);
		campoDemanda = new CampoNumerico(0.0);
		
		// Posicionar cuadros de textos.
		campoPrecio.setBounds(195,20,200,20);
		campoDemanda.setBounds(195,50,200,20);
		
		// Agregar cuadros de textos al panel de datos.
		panelDatos.add(campoPrecio);
		panelDatos.add(campoDemanda);
	}
	
	/**
     * M�todo donde se configura los combobox de ingreso de datos reales 
     * del m�dulo econom�a.
     */
	private void configurarCombobox()
	{
		int i;
		int anioActual;
		int diaActual;
		int mesActual;
		Calendar fecha = Calendar.getInstance();
		
		// Arreglo de mes y d�a.
		String[] mes = new String[12];
		String[] dia = new String[31];
		
		diaActual = fecha.get(Calendar.DAY_OF_MONTH);
		mesActual = fecha.get(Calendar.MONTH) + 1;
		anioActual = fecha.get(Calendar.YEAR);
		
		// Iniciando los valores del string				
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
		
		// Cargar opciones de selecci�n.
		cargarComboRegion();
		cargarComboRecurso();
		cargarComboAnio();
		
		// Configurando combo fecha.
		comboDia.setSelectedItem("" + diaActual);
		comboMes.setSelectedItem("" + mesActual);
		comboAnio.setSelectedItem("" + anioActual);
		
		// Posicionar combobox.
		comboRegion.setBounds(195,80,200,20);
		comboRecurso.setBounds(195,110,200,20);
		comboDia.setBounds(195,140,50,20);
		comboMes.setBounds(265,140,50,20);
		comboAnio.setBounds(325,140,70,20);
		
		// Agregar combobox al panel de datos.
		panelDatos.add(comboRegion);
		panelDatos.add(comboRecurso);
		panelDatos.add(comboDia);
		panelDatos.add(comboMes);
		panelDatos.add(comboAnio);
	}
	
	/**
     * M�todo que carga el combo box de los recursos.
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
				comboRecurso.addItem(
					resultado.getString("nombre_comun_recurso"));
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
     * M�todo que carga el combo box de las regiones.
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
     * M�todo que carga el combo box de los a�os.
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
	 * M�todo que adjunta los escuchadores eventos que tiene el frame. En
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
	 * M�todo que limpia los componentes GUI que maneja este frame.
	 */
	public void limpiar()
	{
		campoPrecio.setText("");
		campoDemanda.setText("");
		comboRegion.setSelectedIndex(0);
		comboRecurso.setSelectedIndex(0);
		comboDia.setSelectedIndex(0);
		comboMes.setSelectedIndex(0);
		comboAnio.setSelectedIndex(0);
	}
	
	/**
     * M�todo que cierra la ventana de ingreso de datos.
     */
	public void cancelar()
	{
		if (frameCircanaPro != null)
			setVisible(false);
		else System.exit(0);
	}
	
	/**
	 * M�todo que graba los datos econ�micos ingresados en la base de datos.
	 */
	public void guardar()
	{
		String error = validar();
		
		// Cuando no hay errores en los datos.
		if (error == "")
		{
			BaseDatoMotor conexion = new BaseDatoMotor();
			BaseDatoTablaBasica tablaEconomicoReal =
				new BaseDatoTablaBasica(conexion, "dato_economico_real");
			
			// Conectar a la base de datos.
			conexion.conectar();
			
			// Preparar los campos para agregar a la base datos.
			tablaEconomicoReal.agregarCampo("codigo_recurso", 
									"" + comboRecurso.getSelectedIndex());
			tablaEconomicoReal.agregarCampo("codigo_region", 
									"" + comboRegion.getSelectedIndex());
			tablaEconomicoReal.agregarCampo("codigo_anio", 
									"" + (String) comboAnio.getSelectedItem());
			tablaEconomicoReal.agregarCampo("codigo_dia", 
									"" + (String) comboDia.getSelectedItem());
			tablaEconomicoReal.agregarCampo("precio_dato_economico_real", 
									"" + campoPrecio.getText());
			tablaEconomicoReal.agregarCampo("demanda_dato_economico_real", 
									"" + campoDemanda.getText());
			
			// Agregar el registro.
			tablaEconomicoReal.agregar();
			
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
				"Error en Ingreso de datos de Mercado",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * M�todo que verifica si los datos ingresados en el frame tienen una 
     * sintaxis correcta.
     *
     * @return error Retorna un string con el error cometido.
     */
	public String validar()
	{
		String error = "";
		
		// Validando campo precio.
		if (!Servicio.esNumero(campoPrecio.getText()) ||
			campoPrecio.getValueDouble() < 0)
			error += "- En el campo precio.\n";
		
		// Validando campo demanda.
		if (!Servicio.esNumero(campoDemanda.getText()) ||
			campoDemanda.getValueDouble() < 0)
			error += "- En el campo demanda.\n";
		
		return error;
	}
	
	/**
     * M�todo que retorna el bot�n guardar.
     *
     * @return botonGuardar Bot�n Guardar del frame.
     */
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
	}
	
	/**
     * M�todo que retorna el bot�n cancelar.
     *
     * @return botonCancelar Bot�n Cancelar del frame.
     */
	public JButton obtenerBotonCancelar()
	{
		return botonCancelar;
	}
}