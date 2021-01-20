/**
 * @(#)FrameConfiguracionServidor.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que deriva de JDialog y que contiene el frame que se muestra al usuario
 * cuando se pulsa el botón "Configurar Servidor". Este frame tiene por objetivo
 * mostrar las opciones de configuración del servidor de base dato. Este frame
 * también puede ser mostrado al usuario cuando no se puede iniciar la
 * aplicación por problemas de conexión a la base de datos.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Container
 * @see Font
 * @see Toolkit
 * @see Color
 * @see KeyEvent
 * @see JDialog
 * @see JPanel
 * @see JLabel
 * @see JTextField
 * @see JPasswordField
 * @see JButton
 * @see JFrame
 * @see JOptionPane
 * @see ImageIcon
 * @see WindowsConstants
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see BaseDatoMotor
 * @see FrameCircanaPro
 * @see EventoButton
 * @see EventoFrame
 * @see InterfaceFrame
 */
public class FrameConfiguracionServidor extends JDialog
	implements InterfaceFrame
{
	/** Referencia al frame creador del objeto. */
	public FrameCircanaPro frameCircanaPro;		
	
	/** El botón "Probar Conexión" para probar la conexión actual. */
	private JButton botonProbarConexion;
	
	/** El botón "Instalar Base de Datos" para instalar la base de datos. */
	private JButton botonInstalarBaseDatos;
	
	/** El botón "Establecer" para establecer la configuración actual. */
	private JButton botonEstablecer;
		
	/** El botón "Cancelar" para cerrar este frame. */
	private JButton botonCancelar;
	
	/** Contiene el nombre del servidor. */
	private JTextField servidor;
	
	/** Contiene el nombre del usuario. */
	private JTextField usuario;
	
	/** Contiene la contraseña del servidor. */
	private JPasswordField contrasenia;
	
	/** Contiene el nombre de la base de datos. */
	private JTextField nombreBaseDatos;
	
	/** Conexión con la base de datos. */
	private BaseDatoMotor baseDatos;
	
	/*
	 * Método constructor en donde se crea y configura este frame. Este
	 * constructor es utilizado cuando se llama a este dialog como aplicación
	 * independiente.
	 */
	public FrameConfiguracionServidor()
	{
		// Configurar.
		configurarFrame();
		configurarComponentes();
		configurarEventos();
	}
	
	/*
	 * Método constructor en donde se crea y configura este frame. Este
	 * constructor es utilizado cuando se llama como elemento de la
	 * aplicación CircanaPro.
	 *
	 * @param frameCircanaPro El frame que contiene a este frame.
	 */
	public FrameConfiguracionServidor(FrameCircanaPro frameCircanaPro)
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
     * Método que configura los elementos especiales que hay en el frame. En
     * particular, este método no se implementa, ya que no existen elementos
     * especiales.
     */
	public void configurarElementosEspeciales()
	{
    }
    
	/**
	 * Método que configura las características de este frame.
	 */
	public void configurarFrame()
	{
		int anchoFrame = 560;
		int altoFrame = 390;
		Toolkit tk = Toolkit.getDefaultToolkit();
		setTitle("Configuración del servidor de base de datos MySQL");
		setSize(anchoFrame, altoFrame);
		setLocation(tk.getScreenSize().width/2 - anchoFrame/2,
					tk.getScreenSize().height/2 - altoFrame/2);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	
	/**
	 * Método que configura los paneles de este frame y sus componentes GUI.
	 */
	public void configurarComponentes()
	{
		// Inicializa la base de datos.
		baseDatos = new BaseDatoMotor();
		
		// Capturar el container de este frame.
		Container contenedor = getContentPane();
				
		// El tipo de borde de los paneles.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los bordes de los paneles.
		TitledBorder titulo = new TitledBorder(borde, " Opciones del Servidor ", 
							  TitledBorder.LEFT,TitledBorder.TOP);
		
		// Crear los paneles.
		JPanel panelInfo = new JPanel(null);
		
		// Creando fuente para la recomendación.
		Font fuenteRecomendacion = new Font("WITH ACUTE",
									Font.ROMAN_BASELINE,10);
				
		// Setear el borde de los paneles.
		panelInfo.setBorder(titulo);
		
		// Setear el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Cambiar el tamaño y posición de los paneles.
		panelInfo.setBounds(5, 10, 540, 300);
				
		// Crear los textos.
		JLabel texto1 = new JLabel(
			new ImageIcon("../img/configuracion_servidor.gif"));
		JLabel texto2 = new JLabel(
			"Esta ventana le permite configurar la base de datos utilizada " +
			"por la aplicación");
		JLabel texto3 = new JLabel(
			"CIRCANA Pro 2.0. Si aún no ha instalado la base de datos, " +
			"entonces le");
		JLabel texto4 = new JLabel("recomendamos que pruebe la conexión y " +
			"luego instale la base de datos.");
		JLabel textoUsuario = new JLabel("Usuario:");
		JLabel textoServidor = new JLabel("Servidor:");
		JLabel textoContrasenia = new JLabel("Contraseña:");
		JLabel textoNombreBaseDato = new JLabel("Base de Datos:");
		
		// Crear los textos de recomendación.
		JLabel recomendacionUsuario = new JLabel
					("Nombre del usuario de la base de datos");
		JLabel recomendacionServidor = new JLabel
					("Nombre o IP del servidor de base de datos");
		JLabel recomendacionContrasenia = new JLabel
					("Contraseña del usuario de la base de datos");
		JLabel recomendacionNombreBaseDato = new JLabel
					("Nombre de la base de datos");
		
		// Crear los cuadros de texto.
		servidor = new JTextField(baseDatos.obtenerServidor());
		usuario = new JTextField(baseDatos.obtenerUsuario());
		contrasenia = new JPasswordField(baseDatos.obtenerContrasenia());
		nombreBaseDatos = new JTextField(baseDatos.obtenerBaseDato());
		
		// Crear los botones.
		botonProbarConexion = new JButton("Probar la Conexión",
							new ImageIcon("../img/probar_conexion.gif"));
		botonInstalarBaseDatos = new JButton("Instalar la Base de Datos",
							new ImageIcon("../img/instalar_base_datos.gif"));
		botonEstablecer = new JButton("Establecer",
							new ImageIcon("../img/establecer.gif"));
		botonCancelar = new JButton("Cancelar",
							new ImageIcon("../img/cancelar.gif"));
		
		// Asignar las fuentes.
		recomendacionUsuario.setFont(fuenteRecomendacion);
		recomendacionServidor.setFont(fuenteRecomendacion);
		recomendacionContrasenia.setFont(fuenteRecomendacion);
		recomendacionNombreBaseDato.setFont(fuenteRecomendacion);
		
		// Asignando eventos de tipo teclas a los botones.
		botonProbarConexion.setMnemonic(KeyEvent.VK_P);
		botonInstalarBaseDatos.setMnemonic(KeyEvent.VK_I);
		botonEstablecer.setMnemonic(KeyEvent.VK_E);
		botonCancelar.setMnemonic(KeyEvent.VK_C);
		
		// Asignar tool tips.
		servidor.setToolTipText(
			"Ingrese el nombre del servidor de base de datos MySQL");
		usuario.setToolTipText(
			"Ingrese el nombre del usuario de la base de datos");
		contrasenia.setToolTipText(
			"Ingrese la contraseña del usuario de la base de datos");
		nombreBaseDatos.setToolTipText(
			"Ingrese el nombre de la base de datos");
		botonProbarConexion.setToolTipText(
			"Probar la conexión a la base de datos");
		botonInstalarBaseDatos.setToolTipText("Instalar la base de datos");
		botonEstablecer.setToolTipText(
			"Establecer la configuración del servidor de base de datos");
		botonCancelar.setToolTipText("Cerrar la ventana");
		
		// Posicionar los textos.
		texto1.setBounds(20,40,40,40);
		texto2.setBounds(75,40,450,15);
		texto3.setBounds(75,55,450,15);
		texto4.setBounds(75,70,450,15);
		textoServidor.setBounds(20,110,100,25);
		textoUsuario.setBounds(20,145,100,25);
		textoContrasenia.setBounds(20,180,100,25);
		textoNombreBaseDato.setBounds(20,215,100,25);
		
		// Posicionar los cuadros de textos.
		servidor.setBounds(150,110,100,25);
		usuario.setBounds(150,145,100,25);
		contrasenia.setBounds(150,180,100,25);
		nombreBaseDatos.setBounds(150,215,100,25);
		
		// Posicionando los textos de recomendación.
		recomendacionServidor.setBounds(270,110,210,25);
		recomendacionUsuario.setBounds(270,145,210,25);
		recomendacionContrasenia.setBounds(270,180,210,25);
		recomendacionNombreBaseDato.setBounds(270,215,210,25);
				
		// Posicionar los botones.
		botonProbarConexion.setBounds(20,260,230,30);
		botonInstalarBaseDatos.setBounds(270,260,250,30);
		botonEstablecer.setBounds(255,320,140,30);
		botonCancelar.setBounds(405,320,140,30);
		
		// Incorporar los componentes a los paneles.
		panelInfo.add(texto1);
		panelInfo.add(texto2);
		panelInfo.add(texto3);
		panelInfo.add(texto4);
		panelInfo.add(textoUsuario);
		panelInfo.add(textoServidor);
		panelInfo.add(textoContrasenia);
		panelInfo.add(textoNombreBaseDato);
		panelInfo.add(recomendacionUsuario);
		panelInfo.add(recomendacionServidor);
		panelInfo.add(recomendacionContrasenia);
		panelInfo.add(recomendacionNombreBaseDato);
		panelInfo.add(servidor);
		panelInfo.add(usuario);
		panelInfo.add(contrasenia);
		panelInfo.add(nombreBaseDatos);
		panelInfo.add(botonProbarConexion);
		panelInfo.add(botonInstalarBaseDatos);
		
		// Incorporar los paneles al contenedor del frame.
		contenedor.add(panelInfo);
		contenedor.add(botonEstablecer);
		contenedor.add(botonCancelar);
	}
	
	/**
	 * Método para configurar los eventos de los componentes GUI de este frame.
	 * En particular se incorpora el escuchador de eventos del tipo EventoButton
	 * y EventoFrame a los JButton que tiene el frame y al propio frame.
	 */
	public void configurarEventos()
	{
		// Crear el escuhcador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		EventoFrame eventoFrame = new EventoFrame(this);
		
		// Incorporar el evento al botón.
		botonProbarConexion.addActionListener(eventoButton);
		botonInstalarBaseDatos.addActionListener(eventoButton);
		botonEstablecer.addActionListener(eventoButton);
		botonCancelar.addActionListener(eventoButton);
				
		// Incorporar el escuchador de eventos al componente frame.
		addWindowListener(eventoFrame);
	}
	
	/**
     * Metodo que se llama cuando al frame configurar servidor se cierra.
     */
	public void cancelar()
	{
		// Cuando la aplicación depende de Circana Pro.
		if (frameCircanaPro != null)
			setVisible(false);
		
		// Cuando la aplicación no depende de Circana Pro.
		else System.exit(0);
	}
	
	/**
     * Metodo que sirve para probar la conexión de la base de datos. Muestra un
     * mensaje de respuesta al quererse conectar.
     */
	public void probarConexion()
	{
		String titulo = "Probando la conexión de la base de datos";
		
		// Cuando está conectada.
		if (baseDatos.estaConectada())
		{
			JOptionPane.showMessageDialog(this,
				"La conexión ha sido exitosa.\n",
				titulo,
				JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Cuando no está conectada.
		else
		{
			JOptionPane.showMessageDialog(this,
				"La conexión ha sido fallida.\n",
				titulo,
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * Método que cambia la configuración del servidor tanto en el sistema como
     * en el archivo de configuración.
     */
	public void establecer()
	{
		baseDatos.configurarServidor(servidor.getText(), usuario.getText(),
									 new String(contrasenia.getPassword()),
									 nombreBaseDatos.getText());
		
		baseDatos.guardarConfiguracionArchivo();
	}
	
	/**
     * Método que instala la base de datos en el servidor MySQL. Para lograrlo
     * se ejecutan el script correpondiente del sistema.
     */	
	public void instalarBaseDatos()
	{
		BaseDatoMotor conexion = new BaseDatoMotor();
		
		int opcion;
		
		String texto = "";
		
		if (frameCircanaPro != null)
			texto+= "Se borrarán todos los datos almacenados hasta ahora.\n";
		
		texto+= "Esta operación puede tardarse algunos minutos. Por favor, " +
				"espere.\n";
		texto+= "Después de realizar esta operación deberá reiniciar CIRCANA " +
				"Pro 2.0.\n";
		texto+= "¿Desea continuar?";
		
		String titulo = "Instalar la Base de Datos";
		
		opcion = JOptionPane.showConfirmDialog(this, texto, titulo,
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if (opcion == JOptionPane.YES_OPTION)
		{
			if (conexion.estaConectada())
			{
				// Conectando.
				conexion.conectar();
				
				// Ejecutando el script.
				conexion.ejecutarScript("../basedatos/script.sql");
				
				// Desconectando.
				conexion.desconectar();
				
				// Cerrando aplicación.
				System.exit(0);
			}
			else
			{
				JOptionPane.showMessageDialog(this,
				"Compruebe sus permisos de usuario del servidor.\n",
				"No se puede instalar la base de datos.",
				JOptionPane.ERROR_MESSAGE);
			}
		}
	}	
	
	/**
	 * Método que obtiene el botón "Probar la Conexión".
	 *
	 * @return botonProbarConexion El botón "Probar la Conexión" que tiene este
	 *                             frame.
	 */
	public JButton obtenerBotonProbarConexion()
	{
		return botonProbarConexion;
	}
	
	/**
	 * Método que obtiene el botón "Instalar la Base de Datos".
	 *
	 * @return botonInstalarBaseDato El botón "Instalar la Base de Datos" que
	 *                               tiene este frame.
	 */
	public JButton obtenerBotonInstalarBaseDatos()
	{
		return botonInstalarBaseDatos;
	}
	
	/**
	 * Método que obtiene el botón "Establecer".
	 *
	 * @return botonEstablecer El botón "Establecer" que tiene este frame.
	 */
	public JButton obtenerBotonEstablecer()
	{
		return botonEstablecer;
	}
	
	/**
	 * Método que obtiene el botón "Cancelar".
	 *
	 * @return botonCerrar El botón "Cancelar" que tiene este frame.
	 */
	public JButton obtenerBotonCancelar()
	{
		return botonCancelar;
	}
}