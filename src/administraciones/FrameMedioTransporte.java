/**
 * @(#)FrameMedioTransporte.java 2.0 01/03/05
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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
 
/**
 * Clase que extiende de la clase JDialog. Este dialog permite el ingreso de
 * datos reales correspondientes a la tabla medio_transporte.
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
 * @see CampoNumerico
 * @see JButton
 * @see JTextField
 * @see JComboBox
 * @see JOptionPane
 * @see ImageIcon
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameCircanaPro
 * @see InterfaceFrame
 * @see InterfaceAdministracion
 */ 
public class FrameMedioTransporte extends JDialog
	implements InterfaceFrame, InterfaceAdministracion
{
	/** Frame que hace referencia al creador de este objeto. */
	public FrameCircanaPro frameCircanaPro;	
	
	/** Panel con los datos necesarios para crear un registro. */
	private JPanel panelDatos;
	
	/** Campo de texto con datos de descripción. */
	private JTextField campoDescripcion;
	
	/** Campo de texto con datos de capacidad. */
	private CampoNumerico campoCapacidad;
	
	/** Campo de texto con datos de potencia. */
	private CampoNumerico campoPotencia;	
	
	/** Campo de texto con datos de eslora. */
	private CampoNumerico campoEslora;	
	
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
	public FrameMedioTransporte(FrameCircanaPro frameCircanaPro)
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
		setTitle("Medio de Transporte");
		setSize(700,300);
		setLocation(tk.getScreenSize().width/2-300,10);
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
			" Ingreso de datos de Medio de Transporte ",
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
		
		// Asginar los tool tips.
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
		JLabel textoDescripcion = new JLabel("Nombre Nave:");
		JLabel textoCapacidad = new JLabel("Capacidad de Bodega:");
		JLabel textoPotencia = new JLabel("Potencia de Motor:");
		JLabel textoEslora = new JLabel("Eslora:");
		JLabel textoEmpresa = new JLabel("Empresa:");
		
		// Posicionar textos.
		textoDescripcion.setBounds(50,20,190,20);
		textoCapacidad.setBounds(50,50,190,20);
		textoPotencia.setBounds(50,80,190,20);
		textoEslora.setBounds(50,110,190,20);
		textoEmpresa.setBounds(50,140,190,20);
		
		// Agregar textos al panel de datos.
		panelDatos.add(textoDescripcion);
		panelDatos.add(textoCapacidad);
		panelDatos.add(textoPotencia);
		panelDatos.add(textoEslora);
		panelDatos.add(textoEmpresa);
	}
	
	/**
     * Método donde se configura los textos de recomendación de datos reales del 
     * módulo operacion.
     */
	private void configurarTextoRecomendacion()
	{
		// Creando fuente para la recomendación.
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// Crear los textos.
		JLabel recomendacionDescripcion = 
			new JLabel("Ingrese una identificación de la embarcación");
		JLabel recomendacionCapacidad = 
			new JLabel("Ingrese la capacidad en metros cúbicos");
		JLabel recomendacionPotencia = 
			new JLabel("Ingrese la potencia del motor HP");
		JLabel recomendacionEslora = 
			new JLabel("Ingrese el largo total de la nave en metros");
		JLabel recomendacionEmpresa = 
			new JLabel("Ingrese empresa propietaria de la embarcación");
		
		// Posicionar recomendaciones.
		recomendacionDescripcion.setBounds(355,20,250,20);
		recomendacionCapacidad.setBounds(355,50,250,20);
		recomendacionPotencia.setBounds(355,80,250,20);
		recomendacionEslora.setBounds(355,110,250,20);
		recomendacionEmpresa.setBounds(355,140,250,20);
		
		// Asignar fuente a recomendación.
		recomendacionDescripcion.setFont(fuenteRecomendacion);
		recomendacionCapacidad.setFont(fuenteRecomendacion);
		recomendacionPotencia.setFont(fuenteRecomendacion);
		recomendacionEslora.setFont(fuenteRecomendacion);
		recomendacionEmpresa.setFont(fuenteRecomendacion);	
		
		// Agregar recomendaciones al panel de datos.
		panelDatos.add(recomendacionDescripcion);
		panelDatos.add(recomendacionCapacidad);
		panelDatos.add(recomendacionPotencia);
		panelDatos.add(recomendacionEslora);
		panelDatos.add(recomendacionEmpresa);
	}
	
	/**
     * Método donde se configura los cuadros textos de ingreso de datos reales 
     * del módulo operacion.
     */
	private void configurarTextField()
	{
		// crear los cuadros de textos
		campoDescripcion = new JTextField();
		campoCapacidad = new CampoNumerico(0.0);
		campoPotencia = new CampoNumerico(0.0);
		campoEslora = new CampoNumerico(0.0);
		
		// posicionar cuadros de textos
		campoDescripcion.setBounds(195,20,150,20);
		campoCapacidad.setBounds(195,50,150,20);
		campoPotencia.setBounds(195,80,150,20);
		campoEslora.setBounds(195,110,150,20);
		
		// agregar cuadros de textos al panel de datos
		panelDatos.add(campoDescripcion);
		panelDatos.add(campoCapacidad);
		panelDatos.add(campoPotencia);
		panelDatos.add(campoEslora);
	}
	
	/**
     * Método donde se configura los combobox de ingreso de datos reales 
     * del módulo operacion.
     */
	private void configurarCombobox()
	{
		// Crear las instancias.
		comboEmpresa = new JComboBox();
		
		// Cargar opciones de selección.
		cargarComboEmpresa();
		
		// Posicionar combobox.
		comboEmpresa.setBounds(195,140,150,20);
		
		// Agregar combobox al panel de datos.
		panelDatos.add(comboEmpresa);
	}
	
	/**
     * Método que carga el combo box de las empresas.
     */
	private void cargarComboEmpresa()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaEmpresa =
			new BaseDatoTablaBasica(conexion,"empresa");
		
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
		campoCapacidad.setText("");
		campoPotencia.setText("");
		campoEslora.setText("");
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
	 * Método que graba los datos operativos ingresados en la base de datos.
	 */
	public void guardar()
	{
		String error = validar();
		
		if (error == "")
		{
			int codigo_medio_transporte;
			BaseDatoMotor conexion = new BaseDatoMotor();
			BaseDatoTablaBasica tablaMedioTransporte = 
				new BaseDatoTablaBasica(conexion,"medio_transporte");
			
			// Conectar a la base de datos.
			conexion.conectar();
			
			// Asignar el identificador.
			codigo_medio_transporte = tablaMedioTransporte.
				buscarUltimoCodigo("codigo_medio_transporte") + 1;
			
			// Preparando los campos para agregar a la base datos.
			tablaMedioTransporte.agregarCampo("codigo_medio_transporte", 
											""+codigo_medio_transporte);
			tablaMedioTransporte.agregarCampo("codigo_empresa", 
											""+comboEmpresa.getSelectedIndex());
			tablaMedioTransporte.agregarCampo("descripcion_medio_transporte", 
											"'"+campoDescripcion.getText()+"'");
			tablaMedioTransporte.agregarCampo("capacidad_medio_transporte",
											""+campoCapacidad.getText());
			tablaMedioTransporte.agregarCampo("potencia_motor_medio_transporte",
											""+campoPotencia.getText());
			tablaMedioTransporte.agregarCampo("eslora_medio_transporte",
											""+campoEslora.getText());
			
			// Agregar el registro.
			tablaMedioTransporte.agregar();
			
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
				"Error en Ingreso de Datos de Medio de Transporte",
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
		
		// validando campo nombre de nave
		if (campoDescripcion.getText().length() == 0) 
			error += "- En el campo nombre nave.\n";
		
		// validando campo capacidad de bodega
		if (!Servicio.esNumero(campoCapacidad.getText()) ||
			campoCapacidad.getValueDouble() <= 0)
			error += "- En el campo capacidad de bodega.\n";
		
		// validando campo potencia de motor
		if (!Servicio.esNumero(campoPotencia.getText()) ||
			campoPotencia.getValueDouble() <= 0)
			error += "- En el campo potencia de motor.\n";
		
		// validando campo eslora
		if (!Servicio.esNumero(campoEslora.getText()) ||
			campoEslora.getValueDouble() <= 0)
			error += "- En el campo eslora.\n";
		
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