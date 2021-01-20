/**
 * @(#)FrameCaladero.java 2.0 01/03/05
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
 * datos reales correspondientes a la tabla caladero.
 *
 * @version 2.0 01/01/05
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
public class FrameCaladero extends JDialog
	implements InterfaceFrame, InterfaceAdministracion
{
	/** Frame que hace referencia al creador de este objeto. */
	public FrameCircanaPro frameCircanaPro;
	
	/** Panel con los datos necesarios para crear un registro. */
	private JPanel panelDatos;
	
	/** Campo de texto con datos de área. */
	private CampoNumerico campoArea;
	
	/** Campo de texto con datos de abundancia. */
	private CampoNumerico campoAbundancia;
	
	/** Campo de texto con datos de biomasa. */
	private CampoNumerico campoBiomasa;
	
	/** Campo de texto con datos de la latitud. */
	private CampoNumerico campoLatitudInicial;
	
	/** Campo de texto con datos de la latitud. */
	private CampoNumerico campoLatitudFinal;
	
	/** Combo para seleccionar la latitud inicial. */
	private JComboBox comboLatitudInicial;
	
	/** Combo para seleccionar la latitud final. */
	private JComboBox comboLatitudFinal;
	
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
	
	/** El botón guardar para grabar los datos ingresados. */
	private JButton botonGuardar;
	
	/** El botón cancela" para abortar la operación de ingreso de datos. */
	private JButton botonCancelar;	
	
	/**
	 * Método constructor que configura el frame de ingreso de datos reales. Se
	 * configura caja de texto, combobox, botones y eventos.
	 *
	 * @param frameCircanaPro Frame creador de este objeto.
	 */
	public FrameCaladero(FrameCircanaPro frameCircanaPro)
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
	 * Método que configura las características de este frame. Las
	 * características configuradas son las que se derivan de la clase JDialog.
	 */	
	public void configurarFrame()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		setTitle("Caladero");
		setSize(660,400);
		setLocation(tk.getScreenSize().width/2-330,10);
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
			" Ingreso de datos de Caladero ",
			TitledBorder.LEFT,
			TitledBorder.TOP);
		
		// Crear los paneles.
		panelDatos = new JPanel(null);
				
		// Setear el borde de los paneles.
		panelDatos.setBorder(titulo);
		
		// Setear el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Cambiar el tamaño y posición de los paneles.
		panelDatos.setBounds(5, 10, this.getWidth() - 15, 300);
		
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
		botonGuardar.setBounds(this.getWidth()/2-160,330,150,30);
		botonCancelar.setBounds(this.getWidth()/2+10,330,150,30);
				
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
     * Método donde se configura los textos de ingreso de datos de los caladeros
     * del módulo ecosistema.
     */
	private void configurarTexto()
	{
		// Crear los textos.
		JLabel textoArea = new JLabel("Area:");
		JLabel textoAbundancia = new JLabel("Abundancia:");
		JLabel textoBiomasa = new JLabel("Biomasa:");
		JLabel textoLongitudInicial = new JLabel("Longitud Inicial:");
		JLabel textoLongitudFinal = new JLabel("Longitud Final:");
		JLabel textoLatitudInicial = new JLabel("Latitud Inicial:");
		JLabel textoLatitudFinal = new JLabel("Latitud Final:");		
		JLabel textoRegion = new JLabel("Región:");
		JLabel textoRecurso = new JLabel("Recurso:");
		JLabel textoFecha = new JLabel("Fecha (día/mes/año):");
		
		// Posicionar textos.
		textoArea.setBounds(50,20,190,20);
		textoAbundancia.setBounds(50,50,190,20);
		textoBiomasa.setBounds(50,80,190,20);
		textoLatitudInicial.setBounds(50,110,190,20);
		textoLatitudFinal.setBounds(50,140,190,20);
		textoRegion.setBounds(50,170,190,20);
		textoRecurso.setBounds(50,200,190,20);
		textoFecha.setBounds(50,230,190,20);
		
		// Agregar textos al panel de datos.
		panelDatos.add(textoArea);
		panelDatos.add(textoAbundancia);
		panelDatos.add(textoBiomasa);
		panelDatos.add(textoLatitudInicial);
		panelDatos.add(textoLatitudFinal);
		panelDatos.add(textoRegion);
		panelDatos.add(textoRecurso);
		panelDatos.add(textoFecha);
	}
	
	/**
     * Método donde se configura los textos de recomendación de los caladeros
     * del módulo ecosistema.
     */
	private void configurarTextoRecomendacion()
	{
		// Creando fuente para la recomendación.
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// Crear los textos.
		JLabel recomendacionArea =
			new JLabel("Ingrese el área en kilómetros cuadrados");
		JLabel recomendacionAbundancia =
			new JLabel("Ingrese la abundancia en unidades");
		JLabel recomendacionBiomasa =
			new JLabel("Ingrese la biomasa en toneladas");
		JLabel recomendacionLatitudInicial =
			new JLabel("Ingrese la latitud inicial en grados");
		JLabel recomendacionLatitudFinal =
			new JLabel("Ingrese la latitud final en grados");
		JLabel recomendacionRegion =
			new JLabel("Ingrese la región del caladero");
		JLabel recomendacionRecurso =
			new JLabel("Ingrese la especie objetivo del caladero");
		JLabel recomendacionFecha =
			new JLabel("Ingrese la fecha cuando se evaluó el caladero");
		
		// Posicionar recomendaciones.
		recomendacionArea.setBounds(405,20,250,20);
		recomendacionAbundancia.setBounds(405,50,250,20);
		recomendacionBiomasa.setBounds(405,80,250,20);
		recomendacionLatitudInicial.setBounds(405,110,250,20);
		recomendacionLatitudFinal.setBounds(405,140,250,20);
		recomendacionRegion.setBounds(405,170,250,20);
		recomendacionRecurso.setBounds(405,200,250,20);
		recomendacionFecha.setBounds(405,230,250,20);
		
		// Asignar fuente a recomendación.
		recomendacionArea.setFont(fuenteRecomendacion);
		recomendacionAbundancia.setFont(fuenteRecomendacion);
		recomendacionBiomasa.setFont(fuenteRecomendacion);
		recomendacionLatitudInicial.setFont(fuenteRecomendacion);
		recomendacionLatitudFinal.setFont(fuenteRecomendacion);
		recomendacionRegion.setFont(fuenteRecomendacion);
		recomendacionRecurso.setFont(fuenteRecomendacion);
		recomendacionFecha.setFont(fuenteRecomendacion);
		
		// Agregar recomendaciones al panel de datos.
		panelDatos.add(recomendacionArea);
		panelDatos.add(recomendacionAbundancia);
		panelDatos.add(recomendacionBiomasa);
		panelDatos.add(recomendacionLatitudInicial);
		panelDatos.add(recomendacionLatitudFinal);
		panelDatos.add(recomendacionRegion);
		panelDatos.add(recomendacionRecurso);
		panelDatos.add(recomendacionFecha);
	}
	
	/**
     * Método donde se configuran los cuadros textos de ingreso de los caladeros
     * del módulo ecosistema.
     */
	private void configurarTextField()
	{
		// Crear los cuadros de textos.
		campoArea = new CampoNumerico(0.0);
		campoAbundancia = new CampoNumerico(0);
		campoBiomasa = new CampoNumerico(0.0);
		campoLatitudInicial = new CampoNumerico(0.0);
		campoLatitudFinal = new CampoNumerico(0.0);
		
		// Posicionar cuadros de textos.
		campoArea.setBounds(195,20,200,20);
		campoAbundancia.setBounds(195,50,200,20);
		campoBiomasa.setBounds(195,80,200,20);		
		campoLatitudInicial.setBounds(195,110,200,20);
		campoLatitudFinal.setBounds(195,140,200,20);
		
		// Agregar cuadros de textos al panel de datos.
		panelDatos.add(campoArea);
		panelDatos.add(campoAbundancia);
		panelDatos.add(campoBiomasa);
		panelDatos.add(campoLatitudInicial);
		panelDatos.add(campoLatitudFinal);
	}
	
	/**
     * Método donde se configura los combobox de ingreso de datos reales 
     * del módulo ecosistema.
     */
	private void configurarCombobox()
	{
		int i;
		int diaActual;
		int mesActual;;
		int anioActual;
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
		comboLatitudInicial = new JComboBox();
		comboLatitudFinal = new JComboBox();
		comboRegion =  new JComboBox();
		comboRecurso = new JComboBox();
		comboDia = new JComboBox(dia);
		comboMes = new JComboBox(mes);
		comboAnio = new JComboBox();
		
		// Cargar opciones de selección.
		cargarComboLatitud();
		cargarComboRegion();
		cargarComboRecurso();
		cargarComboAnio();
		
		// Configurando combo fecha.
		comboDia.setSelectedItem("" + diaActual);
		comboMes.setSelectedItem("" + mesActual);
		comboAnio.setSelectedItem("" + anioActual);		
		
		// Posicionar combobox.
		comboLatitudInicial.setBounds(325,110,70, 20);
		comboLatitudFinal.setBounds(325,140,70, 20);
		comboRegion.setBounds(195,170,200,20);
		comboRecurso.setBounds(195,200,200,20);
		comboDia.setBounds(195,230,50,20);
		comboMes.setBounds(265,230,50,20);
		comboAnio.setBounds(325,230,70,20);
		
		// Agregar combobox al panel de datos.
		panelDatos.add(comboLatitudInicial);
		panelDatos.add(comboLatitudFinal);
		panelDatos.add(comboRegion);
		panelDatos.add(comboRecurso);
		panelDatos.add(comboDia);
		panelDatos.add(comboMes);
		panelDatos.add(comboAnio);
	}
	
	/**
     * Método que carga los combo box de las latitudes.
     */
	private void cargarComboLatitud()
	{
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaLatitud = new BaseDatoTablaBasica(conexion,
											"latitud");
		
		comboLatitudInicial.removeAllItems();
		comboLatitudFinal.removeAllItems();
		conexion.conectar();
		resultado = tablaLatitud.buscarCondicion("");
		
		try
		{
			resultado.first();
			do
			{
				comboLatitudInicial.addItem(
					resultado.getString("descripcion_latitud"));
				comboLatitudFinal.addItem(
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
			System.out.println ("Error al cargar los anios.");
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
		campoArea.setText("");
		campoAbundancia.setText("");
		campoBiomasa.setText("");
		campoLatitudInicial.setText("");
		campoLatitudFinal.setText("");
		comboLatitudInicial.setSelectedIndex(0);
		comboLatitudFinal.setSelectedIndex(0);
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
	 * Método que graba los datos del caladero ingresados en la base de datos.
	 */
	public void guardar()
	{
		String error = validar();
		
		// Cuando no hay errores en los datos.
		if (error == "")
		{
			BaseDatoMotor conexion = new BaseDatoMotor();
			BaseDatoTablaBasica tablaCaladeroReal =
				new BaseDatoTablaBasica(conexion, "caladero_real");
			
			// Conectar a la base de datos.
			conexion.conectar();
			
			// Asignar el identificador
			int codigo_caladero_real = tablaCaladeroReal.
				buscarUltimoCodigo("codigo_caladero_real") + 1;
			
			// Preparar los campos para agregar a la base datos.
			tablaCaladeroReal.agregarCampo("codigo_caladero_real",
									"" + codigo_caladero_real);
			tablaCaladeroReal.agregarCampo("codigo_recurso", 
									"" + comboRecurso.getSelectedIndex());
			tablaCaladeroReal.agregarCampo("codigo_region", 
									"" + comboRegion.getSelectedIndex());
			tablaCaladeroReal.agregarCampo("codigo_anio", 
									"" + (String) comboAnio.getSelectedItem());
			tablaCaladeroReal.agregarCampo("codigo_dia", 
									"" + (String) comboDia.getSelectedItem());
			tablaCaladeroReal.agregarCampo("codigo_latitud_inicial", 
									"" + comboLatitudInicial.getSelectedIndex());
			tablaCaladeroReal.agregarCampo("codigo_latitud_final", 
									"" + comboLatitudFinal.getSelectedIndex());
			tablaCaladeroReal.agregarCampo("latitud_inicial_caladero_real", 
									"" + campoLatitudInicial.getText());
			tablaCaladeroReal.agregarCampo("latitud_final_caladero_real", 
									"" + campoLatitudFinal.getText());
			tablaCaladeroReal.agregarCampo("area_caladero_real", 
									"" + campoArea.getText());
			tablaCaladeroReal.agregarCampo("abundancia_caladero_real", 
									"" + campoAbundancia.getText());
			tablaCaladeroReal.agregarCampo("biomasa_caladero_real", 
									"" + campoBiomasa.getText());
			
			// Agregar el registro.
			tablaCaladeroReal.agregar();
			
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
				"Error en Ingreso de datos de Caladero",
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
		
		// Validando campo área.
		if (!Servicio.esNumero(campoArea.getText()) ||
			campoArea.getValueDouble() <= 0)
			error += "- En el campo área.\n";
		
		// Validando campo abundancia.
		if (!Servicio.esNumero(campoAbundancia.getText()) ||
			campoAbundancia.getValueInt() <= 0 ||
			Servicio.contarCaracter(campoAbundancia.getText(), '.') > 0)
			error += "- En el campo abundancia.\n";
		
		// Validando campo biomasa.
		if (!Servicio.esNumero(campoBiomasa.getText()) ||
			campoBiomasa.getValueDouble() <= 0)
			error += "- En el campo biomasa.\n";
		
		// Validando campo latitud inicial.
		if (!Servicio.esNumero(campoLatitudInicial.getText()) ||
			campoLatitudInicial.getValueDouble() <= 0 ||
			campoLatitudInicial.getValueDouble() > 90)
			error += "- En el campo latitud inicial.\n";
		
		// Validando campo latitud final.
		if (!Servicio.esNumero(campoLatitudFinal.getText()) ||
			campoLatitudFinal.getValueDouble() <= 0 ||
			campoLatitudFinal.getValueDouble() > 90)
			error += "- En el campo latitud final.\n";
		
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