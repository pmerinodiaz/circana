/**
 * @(#)FramePuntoDemanda.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
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
 * Clase que extiende de la clase JDialog. Este diálogo permite el ingreso de
 * datos reales correspondientes a la tabla punto_demanda.
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
public class FramePuntoDemanda extends JDialog
	implements InterfaceFrame, InterfaceAdministracion
{
	/** Frame que hace referencia al creador de este objeto. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Panel con los datos necesarios para crear un registro. */
	private JPanel panelDatos;	
	
	/** Campo de texto con datos de descripción. */
	private JTextField campoDescripcion;
	
	/** Campo de texto con datos de longitud. */
	private CampoNumerico campoLongitud;
	
	/** Campo de texto con datos de latitud. */
	private CampoNumerico campoLatitud;
	
	/** Combo para seleccionar la longitud. */
	private JComboBox comboLongitud;
	
	/** Combo para seleccionar la latitud. */
	private JComboBox comboLatitud;
	
	/** Combo para seleccionar la región. */
	private JComboBox comboRegion;
	
	/** Combo para seleccionar la empresa. */
	private JComboBox comboEmpresa;
	
	/** El botón "Guardar" para grabar los datos ingresados. */
	private JButton botonGuardar;
	
	/** El botón "Cancelar" para abortar la operación de ingreso de datos. */
	private JButton botonCancelar;	
	
	/**
	 * Método constructor que configura el frame de ingreso de datos reales. Se
	 * configura caja de texto, combobox, botones y eventos.
	 *
	 * @param frameCircanaPro Frame creador de este objeto.
	 */
	public FramePuntoDemanda(FrameCircanaPro frameCircanaPro)
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
		setTitle("Punto de Demanda");
		setSize(750,300);
		setLocation(tk.getScreenSize().width/2-350,10);
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
			" Ingreso de datos de Punto de Demanda ",
			TitledBorder.LEFT,
			TitledBorder.TOP);
		
		// Crear los paneles.
		panelDatos = new JPanel(null);
		
		// Setear el borde de los paneles.
		panelDatos.setBorder(titulo);
		
		// Setear el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Cambiar el tamaño y posición de los paneles.
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
     * Método donde se configura los textos de ingreso de datos reales del 
     * módulo operación.
     */
	private void configurarTexto()
	{
		// Crear los textos.
		JLabel textoDescripcion = new JLabel("Descripción:");
		JLabel textoLongitud = new JLabel("Longitud:");
		JLabel textoLatitud = new JLabel("Latitud:");
		JLabel textoRegion = new JLabel("Región:");
		JLabel textoEmpresa = new JLabel("Empresa:");
		
		// Posicionar textos.
		textoDescripcion.setBounds(50,20,190,20);
		textoLongitud.setBounds(50,50,190,20);
		textoLatitud.setBounds(50,80,190,20);
		textoRegion.setBounds(50,110,190,20);
		textoEmpresa.setBounds(50,140,190,20);
		
		// Agregar textos al panel de datos.
		panelDatos.add(textoDescripcion);
		panelDatos.add(textoLongitud);
		panelDatos.add(textoLatitud);
		panelDatos.add(textoRegion);
		panelDatos.add(textoEmpresa);
	}
	
	/**
     * Método donde se configura los textos de recomendación de datos reales del 
     * módulo operación.
     */
	private void configurarTextoRecomendacion()
	{
		// Creando fuente para la recomendación.
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// Crear los textos.
		JLabel recomendacionDescripcion =
			new JLabel("Ingrese una identificación del punto de demanda");
		JLabel recomendacionLongitud =
			new JLabel("Ingrese la longitud geográfica");
		JLabel recomendacionLatitud =
			new JLabel("Ingrese la latitud geográfica");
		JLabel recomendacionRegion =
			new JLabel("Ingrese la región a la que pertenece");
		JLabel recomendacionEmpresa =
			new JLabel("Ingrese la empresa matriz");
		
		// Posicionar recomendaciones.
		recomendacionDescripcion.setBounds(405,20,250,20);
		recomendacionLongitud.setBounds(405,50,250,20);
		recomendacionLatitud.setBounds(405,80,250,20);
		recomendacionRegion.setBounds(405,110,250,20);
		recomendacionEmpresa.setBounds(405,140,250,20);
		
		// Asignar fuente a recomendación.
		recomendacionDescripcion.setFont(fuenteRecomendacion);
		recomendacionLongitud.setFont(fuenteRecomendacion);
		recomendacionLatitud.setFont(fuenteRecomendacion);
		recomendacionRegion.setFont(fuenteRecomendacion);
		recomendacionEmpresa.setFont(fuenteRecomendacion);
		
		// Agregar recomendaciones al panel de datos.
		panelDatos.add(recomendacionDescripcion);
		panelDatos.add(recomendacionLongitud);
		panelDatos.add(recomendacionLatitud);
		panelDatos.add(recomendacionRegion);
		panelDatos.add(recomendacionEmpresa);
	}
	
	/**
     * Método donde se configura los cuadros textos de ingreso de datos reales 
     * del módulo operación.
     */
	private void configurarTextField()
	{
		// Crear los cuadros de textos.
		campoDescripcion = new JTextField();
		campoLongitud = new CampoNumerico(0.0);
		campoLatitud = new CampoNumerico(0.0);
		
		// Posicionar cuadros de textos.
		campoDescripcion.setBounds(195,20,200,20);
		campoLongitud.setBounds(195,50,120,20);
		campoLatitud.setBounds(195,80,120,20);
		
		// Agregar cuadros de textos al panel de datos.
		panelDatos.add(campoDescripcion);
		panelDatos.add(campoLongitud);
		panelDatos.add(campoLatitud);
	}
	
	/**
     * Método donde se configura los combobox de ingreso de datos reales 
     * del módulo operacion.
     */
	private void configurarCombobox()
	{
		// Crear las instancias.
		comboLongitud = new JComboBox();
		comboLatitud = new JComboBox();
		comboRegion =  new JComboBox();
		comboEmpresa = new JComboBox();
		
		// Cargar opciones de selección.
		cargarComboLongitud();
		cargarComboLatitud();
		cargarComboRegion();
		cargarComboEmpresa();
		
		// Posicionar combobox.
		comboLongitud.setBounds(325,50,70,20);
		comboLatitud.setBounds(325,80,70,20);
		comboRegion.setBounds(195,110,200,20);
		comboEmpresa.setBounds(195,140,200,20);
		
		// Agregar combobox al panel de datos.
		panelDatos.add(comboLongitud);
		panelDatos.add(comboLatitud);
		panelDatos.add(comboRegion);
		panelDatos.add(comboEmpresa);
	}
	
	/**
     * Método que carga el combo región.
     */
	private void cargarComboRegion()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaRegion = new BaseDatoTablaBasica(conexion,
																  "region");
		
		comboRegion.removeAllItems();
		conexion.conectar();
		resultado = tablaRegion.
					buscarCondicion("codigo_region >= 0 order by codigo_region");
		
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
     * Método que carga el combo empresa.
     */
	private void cargarComboEmpresa()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaEmpresa = new BaseDatoTablaBasica(conexion,
																   "empresa");
		
		comboEmpresa.removeAllItems();
		conexion.conectar();
		resultado = tablaEmpresa.
					buscarCondicion("codigo_empresa >= 0 order by codigo_empresa");
		
		try
		{
			resultado.first();
			do
			{
				comboEmpresa.addItem(resultado.getString("nombre_empresa"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar la tabla empresa.");
		}
		
		conexion.desconectar();	
	}
	
	/**
     * Método que carga el combo longitud.
     */
	private void cargarComboLongitud()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaLongitud = new BaseDatoTablaBasica(conexion,
																	"longitud");
		
		comboLongitud.removeAllItems();
		conexion.conectar();
		resultado = tablaLongitud.
				buscarCondicion("codigo_longitud >= 0 order by codigo_longitud");
		
		try
		{
			resultado.first();
			do
			{
				comboLongitud.addItem(
								resultado.getString("descripcion_longitud"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar tabla longitud.");
		}
		
		conexion.desconectar();
	}
	
	/**
     * Método que carga el combo latitud.
     */
	private void cargarComboLatitud()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaLatitud = new BaseDatoTablaBasica(conexion,
																   "latitud");
		
		comboLatitud.removeAllItems();
		conexion.conectar();
		resultado = tablaLatitud.
				buscarCondicion("codigo_latitud >= 0 order by codigo_latitud");
		
		try
		{
			resultado.first();
			do
			{
				comboLatitud.addItem(
								resultado.getString("descripcion_latitud"));
				resultado.next();
			}
			while(!resultado.isAfterLast());
		}
		catch(SQLException ex)
		{
			System.out.println ("Error al cargar tabla latitud.");
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
		campoDescripcion.setText("");
		campoLongitud.setText("");
		campoLatitud.setText("");
		comboLongitud.setSelectedIndex(0);
		comboLatitud.setSelectedIndex(0);
		comboRegion.setSelectedIndex(0);
		comboEmpresa.setSelectedIndex(0);
	}
	
	/**
     * Metodo que cierra la ventana de ingreso de datos.
     */
	public void cancelar()
	{
		if (frameCircanaPro != null)
			setVisible(false);
		else System.exit(0);
	}
	
	/**
	 * Método que graba los datos de punto de demanda ingresados en la base de
	 * datos.
	 */
	public void guardar()
	{
		String error = validar();
		
		if(error == "")
		{
			int codigo_punto_demanda;
			int codigo_coordenada;
			BaseDatoMotor conexion = new BaseDatoMotor();
			BaseDatoTablaBasica tablaCoordenada = 
				new BaseDatoTablaBasica(conexion,"coordenada");
			BaseDatoTablaBasica tablaPuntoDemanda = 
				new BaseDatoTablaBasica(conexion,"punto_demanda");
			
			// Conectar a la base de datos.
			conexion.conectar();
			
			// Asignar el identificador.
			codigo_coordenada = tablaCoordenada.
							buscarUltimoCodigo("codigo_coordenada") + 1;
			
			// Preparando los campos para agregar a la base datos.
			tablaCoordenada.agregarCampo("codigo_coordenada", 
											""+codigo_coordenada);
			tablaCoordenada.agregarCampo("codigo_region",
											""+comboRegion.getSelectedIndex());
			tablaCoordenada.agregarCampo("codigo_longitud", 
											""+comboLongitud.getSelectedIndex());
			tablaCoordenada.agregarCampo("codigo_latitud", 
											""+comboLatitud.getSelectedIndex());
			tablaCoordenada.agregarCampo("longitud_coordenada",
											""+campoLongitud.getText());
			tablaCoordenada.agregarCampo("latitud_coordenada",
											""+campoLatitud.getText());
			tablaCoordenada.agregarCampo("altitud_coordenada", "0");
			
			// Asignar el identificador.
			codigo_punto_demanda = tablaPuntoDemanda.
						buscarUltimoCodigo("codigo_punto_demanda") + 1;
			
			// Preparando los campos para agregar a la base datos.
			tablaPuntoDemanda.agregarCampo("codigo_punto_demanda", 
											""+codigo_punto_demanda);
			tablaPuntoDemanda.agregarCampo("codigo_empresa", 
											""+comboEmpresa.getSelectedIndex());
			tablaPuntoDemanda.agregarCampo("codigo_coordenada", 
											""+codigo_coordenada);
			tablaPuntoDemanda.agregarCampo("descripcion_punto_demanda", 
											"'"+campoDescripcion.getText()+"'");
			// Agregar el registro.
			tablaCoordenada.agregar();
			tablaPuntoDemanda.agregar();
			
			// Desconectar la base de datos y cerrar la ventana.
			conexion.desconectar();
			frameCircanaPro.panelEscritorio.frameOperacion.
							actualizarInformacionProyecto();
			cancelar();
		}
		else
		{
			error = "Se han cometido los siguientes errores:\n" + error;
			JOptionPane.showMessageDialog(this, error,
				"Error en Ingreso de Datos de Punto de Demanda",
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
		
		// Validando campo descripción.
		if (campoDescripcion.getText().length() == 0)
			error += "- En el campo descripción.\n";
		
		// Validando campo longitud.
		if (!Servicio.esNumero(campoLongitud.getText()) ||
			campoLongitud.getValueDouble() <= 0 ||
			campoLongitud.getValueDouble() > 90) 
			error += "- En el campo longitud.\n";
		
		// Validando campo latitud.
		if (!Servicio.esNumero(campoLatitud.getText()) ||
			campoLatitud.getValueDouble() <= 0 ||
			campoLatitud.getValueDouble() > 90)
			error += "- En el campo latitud.\n";
		
		return error;
	}
	
	/**
     * Método que retorna el botón guardar.
     *
     * @return botonGuardar Botón Guardar del frame.
     */	
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
	}
	
	/**
     * Método que retorna el botón cancelar.
     *
     * @return botonCancelar Botón Cancelar del frame.
     */
	public JButton obtenerBotonCancelar()
	{
		return botonCancelar;
	}
}