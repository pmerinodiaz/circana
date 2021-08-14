/**
 * @(#)FrameCircanaPro.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Toolkit;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;

/**
 * Clase que extiende de la clase JFrame. Contiene todos los componentes GUI de
 * la aplicaci�n que se muestran al usuario. Gracias a estos componentes el
 * usuario puede interactuar en forma simple con la aplicaci�n. La aplicaci�n
 * tiene b�sicamente un menu, un panel de botones de acceso r�pido, un panel
 * visor del proyecto, un panel de escritorio y una panel de estado.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Toolkit
 * @see Container
 * @see JFrame
 * @see JMenuBar
 * @see UIManager
 * @see SwingUtilities
 * @see JOptionPane
 * @see WindowConstants
 * @see MetalLookAndFeel
 * @see MotifLookAndFeel
 * @see WindowsLookAndFeel
 * @see WindowsClassicLookAndFeel
 * @see MenuProyecto
 * @see MenuAdministracionDatos
 * @see MenuHerramientas
 * @see MenuVentana
 * @see MenuAyuda
 * @see PanelBotonera
 * @see PanelVisor
 * @see PanelEscritorio
 * @see PanelEstado
 * @see FrameAbrirGuardarProyecto
 * @see FrameEvaluacionStock
 * @see FrameCaladero
 * @see FrameMercado
 * @see FrameMedioTransporte
 * @see FramePuntoDemanda
 * @see FrameConfiguracionServidor
 * @see FrameInternet
 * @see FrameAcerca
 * @see Proyecto
 * @see InterfaceFrame
 */
public class FrameCircanaPro extends JFrame implements InterfaceFrame
{
	/**
	 * El alto del panel de la botonera como porcentaje con respecto al alto
	 * de este frame.
	 */
	public static final double ALTO_BOTONERA = 0.05;
	
	/**
	 * El alto del panel de escritorio como porcentaje con respecto al alto de
	 * este frame.
	 */
	public static final double ALTO_ESCRITORIO = 0.84;
	
	/**
	 * El alto del panel de estado como porcentaje con respecto al alto de este
	 * frame.
	 */
	public static final double ALTO_ESTADO = 0.05;
	
	/**
	 * El ancho del panel visor del proyecto como porcentaje con respecto al
	 * ancho de este frame.
	 */
	public static final double ANCHO_VISOR = 0.175;
	
	/**
	 * El ancho del panel de escritorio como porcentaje con respecto al ancho
	 * de este frame.
	 */
	public static final double ANCHO_ESCRITORIO = 0.82;
	
	/** Men� con las opciones del proyecto. */
	private MenuProyecto menuProyecto;
	
	/** Men� con las opciones de la administraci�n de datos. */
	private MenuAdministracionDatos menuAdministracionDatos;
	
	/** Men� con las opciones de las herramientas. */
	private MenuHerramientas menuHerramientas;
	
	/** Men� con las opciones de ventana. */
	private MenuVentana menuVentana;
	
	/** Men� con las opciones de ayuda. */
	private MenuAyuda menuAyuda;
	
	/** Panel que contiene los botones de acceso r�pido. */
	public PanelBotonera panelBotonera;
	
	/** Panel que lista el proyecto y sus m�dulos. */
	public PanelVisor panelVisor;
	
	/** Panel en donde se despliegan los diferentes m�dulos. */
	public PanelEscritorio panelEscritorio;
	
	/** Panel en donde se despliega el estado. */
	public PanelEstado panelEstado;
	
	/** Frame del m�dulo abrir y guardar proyecto. */
	public FrameAbrirGuardarProyecto frameAbrirGuardarProyecto;
	
	/** Frame del m�dulo evaluaci�n del stock. */
	public FrameEvaluacionStock frameEvaluacionStock;
	
	/** Frame del m�dulo caladero. */
	public FrameCaladero frameCaladero;
	
	/** Frame del m�dulo mercado. */
	public FrameMercado frameMercado;
	
	/** Frame del m�dulo medio de transporte. */
	public FrameMedioTransporte frameMedioTransporte;
	
	/** Frame del m�dulo punto de demanda. */
	public FramePuntoDemanda framePuntoDemanda;
	
	/** Frame del m�dulo configuraci�n del servidor. */
	public FrameConfiguracionServidor frameConfiguracionServidor;
	
	/** Frame del m�dulo contenido. */
	public FrameInternet frameContenido;
	
	/** Frame del m�dulo ejemplos de inteligencia artificial. */
	public FrameInternet frameEjemplos;
	
	/** Frame del m�dulo CIRCANA Pro en internet. */
	public FrameInternet frameInternet;
	
	/** Frame del m�dulo acerca de CIRCANA Pro. */
	public FrameAcerca frameAcerca;
	
	/** Un valor booleano que indica si hay o no un proyecto abierto. */
	private boolean proyectoAbierto;
	
	/** Un valor booleano que indica si hay o no un proceso corriendo. */
	private boolean procesoCorriendo;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el men�, el
	 * frame, los paneles, los eventos y los di�logos asociados de los
	 * componentes GUI que contiene esta aplicaci�n.
	 */
	public FrameCircanaPro()
	{
		// Configurar.
		configurarElementosEspeciales();
		configurarFrame();
		configurarComponentes();
		configurarEventos();
		configurarDialogos();
	}
	
	/**
	 * M�todo en donde se inicializan los objetos que se usan en todo el frame
	 * CIRCANA Pro. Estos objetos son los siguientes: el proyecto, el atributo
	 * proyectoAbierto y el atributo procesoCorriendo.
	 */
	public void configurarElementosEspeciales()
	{
		// Cargar los datos del nuevo proyecto.
		Proyecto.cargar(Proyecto.CODIGO_NUEVO);
		
		// Establecer que no hay proyectos abiertos.
		proyectoAbierto = false;
		
		// Establecer que no hay procesos corriendo.
		procesoCorriendo = false;
	}
	
	/**
	 * M�todo en donde se configuran diversas propiedades que tiene este frame.
	 * Las opciones que se cambian en este m�todo son los atributos que se
	 * derivan de la clase JFrame. Antes de configurar el frame, se verifica
	 * que la resoluci�n del monitor sea v�lida.
	 */
	public void configurarFrame()
	{
		// Primero verificar la resoluci�n de pantalla.
		Toolkit kit = Toolkit.getDefaultToolkit();
		int ancho = kit.getScreenSize().width;
		int alto = kit.getScreenSize().height;
		esResolucionValida(ancho, alto);
		
		// Cambiar las propiedades del frame.
		setTitle("CIRCANA Pro 2.0");
		setSize(ancho, alto-30);
		setResizable(false);
		setIconImage(kit.getImage("../img/circana_pro.gif"));
		setLocation(0, 0);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}
	
	/**
	 * M�todo en donde se configura el men� de la aplicaci�n y todas sus
	 * opciones. El men� se compone principalmente de cinco opciones: el
	 * proyecto, la administraci�n de datos, las herramientas, la ventana y la
	 * ayuda. Cada una de estas opciones tiene sub-opciones propias para cada
	 * m�dulo del sistema. En este m�todo se crean todos los objetos tipo JMenu
	 * y se incorporan a la barra de men� del tipo JMenuMar que tiene el frame.
	 * Luego, se incorpora a este frame la barra de men� al frame. Adem�s, este
	 * m�todo se encarga de crear los cuatro paneles del frame: la botonera, el
	 * visor del proyecto, el escritorio y el estado. En el panel botonera se
	 * incorporan todos los botones de acceso r�pido que tiene la aplicaci�n. El
	 * panel de escritorio sirve para cargar las opciones que tiene el visor del
	 * proyecto. Estas opciones son cargadas como JInternalFrame dentro del
	 * JDeskotPane que es el escritorio. El panel visor del proyecto tiene por
	 * objetivo mostrar el proyecto actual que est� abierto y sus modelos
	 * asociados. El panel de estado es usado para mostrar diversa informaci�n
	 * relacionada con el estado actual del proyecto y la aplicaci�n.
	 */
	public void configurarComponentes()
	{
		// Crear la barra del men�.
		JMenuBar barraMenu = new JMenuBar();
		
		// Crear las opciones del men�.
		menuProyecto = new MenuProyecto(this);
		menuAdministracionDatos = new MenuAdministracionDatos(this);
		menuHerramientas = new MenuHerramientas(this);
		menuVentana = new MenuVentana(this);
		menuAyuda = new MenuAyuda(this);
		
		// Incorporar las opciones del men� a la barra del men�.
		barraMenu.add(menuProyecto);
		barraMenu.add(menuAdministracionDatos);
		barraMenu.add(menuHerramientas);
		barraMenu.add(menuVentana);
		barraMenu.add(menuAyuda);
		
		// Incorporar la barra del men� al frame.
		setJMenuBar(barraMenu);
		
		// Capturar el panel contenedor del frame.
		Container contenedor = getContentPane();
		
		// Eliminar el layout del panel contenedor del frame.
		contenedor.setLayout(null);
		
		// Crear los paneles para el frame.
		panelBotonera = new PanelBotonera(this);
		panelVisor = new PanelVisor(this);
		panelEscritorio = new PanelEscritorio(this);
		panelEstado = new PanelEstado(this);
		
		// Incorporar los paneles al frame.
		contenedor.add(panelBotonera);
		contenedor.add(panelVisor);
		contenedor.add(panelEscritorio);
		contenedor.add(panelEstado);
	}
	
	/**
	 * M�todo que configura los eventos asociados al frame. En particular se
	 * incorpora el escuchador de eventos del tipo EventoFrame al JFrame
	 * de esta aplicaci�n.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoFrame evento = new EventoFrame(this);
		
		// Incorporar el escuchador de eventos al componente.
		addWindowListener(evento);
	}
	
	/**
     * M�todo en donde se configuran los cuadros de di�logos que aparecen en
     * toda la aplicaci�n CIRCANA Pro. Estos cuadros de di�logos son los frames
     * que se muestran en toda la aplicaci�n CIRCANA Pro. En este m�todo se
     * crean los di�logos, para posteriormente ser mostrados con el m�todo show.
     */
	private void configurarDialogos()
	{
		frameEvaluacionStock = new FrameEvaluacionStock(this);
		frameCaladero = new FrameCaladero(this);
		frameMercado = new FrameMercado(this);
		frameMedioTransporte = new FrameMedioTransporte(this);
		framePuntoDemanda = new FramePuntoDemanda(this);
		frameConfiguracionServidor = new FrameConfiguracionServidor(this);
		frameContenido = new FrameInternet(this, FrameInternet.CONTENIDO,
										  "Contenido");
		frameEjemplos = new FrameInternet(this, FrameInternet.EJEMPLOS,
										  "Ejemplos de Inteligencia Artificial");
		frameInternet = new FrameInternet(this, FrameInternet.CIRCANA_PRO,
										  "CIRCANA Pro en Internet");
		frameAcerca = new FrameAcerca(this);
	}
	
	/**
	 * M�todo que verifica si la resoluci�n de pantalla ocupada en el monitor es
	 * v�lida para ejecutar la aplicaci�n CIRCANA Pro. Esta aplicaci�n requiere
	 * una resoluci�n m�nima de 1024x768 pixeles. Cuando la resoluci�n en
	 * pantalla es menor que 1024x768, entonces se muestra un mensaje al usuario
	 * y luego se aborta la aplicaci�n.
	 *
	 * @param ancho La resoluci�n de pixeles en el eje X del monitor.
	 * @param alto La resoluci�n de pixeles en el eje Y del monitor.
	 */
	private void esResolucionValida(int ancho, int alto)
	{
		// Cuando la resoluci�n de pantalla es menor que la requerida.
		if (ancho<1024 || alto<768)
		{
			// Mostrar el mensaje de error.
			JOptionPane.showMessageDialog(this,
				"Por favor, configure su monitor a una resoluci�n m�nima de "+
				"1024x768 pixeles.",
				"No se pudo iniciar CIRCANA Pro 2.0",
				JOptionPane.ERROR_MESSAGE);
			
			// Abortar la aplicaci�n.
			System.exit(0);
		}
	}
	
	/**
	 * M�todo que permite crear un nuevo proyecto de trabajo. El proyecto
	 * contiene los siguientes modelos: ecosistema, econom�a, operaci�n e
	 * integraci�n.
	 */
	private void nuevo()
	{
		// Cargar el nuevo proyecto.
		Proyecto.cargar(Proyecto.CODIGO_NUEVO);
		
		// Limpiar los resultados del proyecto cargado.
		Proyecto.limpiarResultados();
		
		// Establecer que hay un proyecto abierto.
		establecerProyectoAbierto(true);
		
		// Establecer que no hay procesos corriendo.
		establecerProcesoCorriendo(false);
	}
	
	/**
     * M�todo que permite crear un nuevo proyecto. Este nuevo proyecto tiene
     * la configuraci�n por defecto establecida para cada m�dulo. Este m�todo es
     * invocado cuando el usuario selecciona desde el men� el item nuevo o bien
     * desde la botonera. Cuando ya existe un proyecto abierto y ha sido
     * modificado, entonces se pregunta al usuario si desea guardar los cambios
     * del proyecto antiguo.
     */
	public void nuevoProyecto()
	{
		// Cerrar el proyecto actual.
		cerrar();
		
		// Crear un proyecto nuevo.
		nuevo();
		
		// Cargar la configuraci�n en el escritorio.
		panelEscritorio.cargarConfiguracion();
		
		// Actualizar la informaci�n en el escritorio.
		panelEscritorio.actualizarInformacionProyecto();
	}
	
	/**
	 * M�todo que permite abrir un proyecto de trabajo ya existente. Se utiliza
	 * la clase JDialog para permitir al usuario elegir el proyecto que desea
	 * abrir.
	 */
	public void abrir()	
	{
		// Crear y mostrar el cuadro de di�logo.
		frameAbrirGuardarProyecto = new FrameAbrirGuardarProyecto(this, false);
		frameAbrirGuardarProyecto.setVisible(true);
	}
	
	/**
     * M�todo que abre un proyecto desde la base de datos, escogido desde el
     * frame abrir guardar proyecto por el usuario. Este m�todo es invocado
     * cuando el usuario ha escogido un proyecto que desea abrir y luego pulsa
     * el bot�n abrir en el frame abrir guardar proyecto.
     */
	public void abrirProyecto()
	{
		// Obtener el c�digo del proyecto a abrir.
		int codigo = frameAbrirGuardarProyecto.obtenerCodigoProyecto();
		
		// Cuando el c�digo existe.
		if (codigo > 0)
		{
			// Cerrar el proyecto actual.
			cerrar();
			
			// Hacer invisible el frame.
			frameAbrirGuardarProyecto.setVisible(false);
			
			// Cargar el proyecto a abrir.
			Proyecto.cargar(codigo);
			
			// Cargar la configuraci�n en el escritorio.
			panelEscritorio.cargarConfiguracion();
			
			// Actualizar la informaci�n en el escritorio.
			panelEscritorio.actualizarInformacionProyecto();
			
			// Establecer que hay un proyecto abierto.
			establecerProyectoAbierto(true);
			
			// Establecer que no hay procesos corriendo.
			establecerProcesoCorriendo(false);
		}
	}
	
	/**
	 * M�todo que permite cerrar un proyecto de trabajo ya existente. Cuando se
	 * cierra el proyecto, se pregunta al usuario si desea guardar los cambios
	 * (en caso de haberlos). Si el usuario acepta guardar los cambios, entonces
	 * los datos son guardados en la base de datos. En caso contrario, los datos
	 * se pierden.
	 */
	public void cerrar()
	{
		// Cuando hay un proyecto abierto.
		if (proyectoAbierto)
		{
			// Variables temporales.
			int opcion;
			String[] opciones = {"S�", "No", "Cancelar"};
			
			// Cuando el proyecto ha sido modificado.
			if (Proyecto.obtenerModificado())
			{
				// Obtener la opci�n del usuario.
				opcion = JOptionPane.showOptionDialog(this,
					"�Guardar cambios en " + Proyecto.obtenerNombre() + "?",
					getTitle(), JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
				
				// Cuando el usuario desea o no desea guardar los cambios.
				if ((opcion == JOptionPane.YES_OPTION) ||
					(opcion == JOptionPane.NO_OPTION))
				{
					// Cuando el usuario desea guardar los cambios.
					if (opcion == JOptionPane.YES_OPTION)
					{
						// Cuando el proyecto actual es el proyecto por defecto.
						if (Proyecto.obtenerCodigo() ==
							Proyecto.CODIGO_NUEVO)
							guardarComo();
						
						// Cuando el proyecto actual no es el proyecto por
						// defecto.
						else guardar();
					}
					
					// Establecer que no hay un proyecto abierto.
					establecerProyectoAbierto(false);
					
					// Establecer que no hay procesos corriendo.
					establecerProcesoCorriendo(false);
				}
			}
			
			// Cuando el proyecto no ha sido modificado.
			else
			{
				// Establecer que no hay un proyecto abierto.
				establecerProyectoAbierto(false);
				
				// Establecer que no hay procesos corriendo.
				establecerProcesoCorriendo(false);
			}
		}
	}
	
	/**
	 * M�todo que permite guardar el proyecto de trabajo ya existente con el
	 * mismo nombre. Si el proyecto ya tiene un nombre, entonces se guarda el
	 * proyecto con el mismo nombre. En caso contrario, se utiliza la clase
	 * JDialog para permitir al usuario escribir el nombre del proyecto que
	 * desea guardar.
	 */
	public void guardar()
	{
		if (Proyecto.guardar())
			guardarComo();
	}
	
	/**
	 * M�todo que permite guardar el proyecto de trabajo ya existente con otro
	 * nombre. Se utiliza la clase JDialog para permitir al usuario escribir el
	 * nombre del proyecto que desea guardar.
	 */
	public void guardarComo()
	{
		// Crear y mostrar el cuadro de di�logo.
		frameAbrirGuardarProyecto = new FrameAbrirGuardarProyecto(this, true);
		frameAbrirGuardarProyecto.setVisible(true);
	}
	
	/**
     * M�todo que guarda un proyecto en la base de datos desde el frame abrir
     * guardar proyecto. Este m�todo es invocado desde el frame abrir guardar
     * proyecto cuando el usuario escoje un nombre para el proyecto y luego
     * pulsa el bot�n guardar.
     */
	public void guardarProyecto()
	{
		// Obtener el nombre del proyecto a guardar.
		String nombre = "" + frameAbrirGuardarProyecto.obtenerNombreProyecto();
		
		// Cuando el nombre del proyecto existe y es v�lido.
		if ((!nombre.equals("")) &&
			(BaseDatoMotor.esValidoDatoIngresado(nombre)))
		{			
			// Hacer invisible el frame.
			frameAbrirGuardarProyecto.setVisible(false);
			
			// Guardar el proyecto.
			Proyecto.guardarComo(nombre);
			
			// Cargar la configuraci�n en el escritorio.
			panelEscritorio.cargarConfiguracion();
			
			// Actualizar la informaci�n en el escritorio.
			panelEscritorio.actualizarInformacionProyecto();
		}
	}
	
	/**
	 * M�todo que permite salir de la aplicaci�n. Primero se cierra el proyecto
	 * y luego se sale de la aplicaci�n.
	 */
	public void salir()
	{
		// Cerrar el proyecto.
		cerrar();
		
		// Abortar la aplicaci�n.
		System.exit(0);
	}
	
	/**
	 * M�todo en donde se muestra el frame evaluaci�n de stock del m�dulo
	 * administraci�n de datos. Este frame ya ha sido creado, por lo que en este
	 * m�todo solo se llama a los m�todos limpiar y visible del frame.
	 */
	public void evaluacionStock()
	{
		frameEvaluacionStock.limpiar();
		frameEvaluacionStock.setVisible(true);
	}
	
	/**
	 * M�todo en donde se muestra el frame caladero del m�dulo administraci�n
	 * de datos. Este frame ya ha sido creado, por lo que en este m�todo solo se
	 * llama a los m�todos limpiar y visible del frame.
	 */
	public void caladero()
	{
		frameCaladero.limpiar();
		frameCaladero.setVisible(true);
	}
	
	/**
	 * M�todo en donde se muestra el frame mercado del m�dulo administraci�n de
	 * datos. Este frame ya ha sido creado, por lo que en este m�todo solo se
	 * llama a los m�todos limpiar y visible frame.
	 */
	public void mercado()
	{
		frameMercado.limpiar();
		frameMercado.setVisible(true);
	}
	
	/**
	 * M�todo en donde se muestra el frame medio de transporte del m�dulo
	 * administraci�n de datos. Este frame ya ha sido creado, por lo que en este
	 * m�todo solo se llama a los m�todos limpiar y visible frame.
	 */
	public void medioTransporte()
	{
		frameMedioTransporte.limpiar();
		frameMedioTransporte.setVisible(true);
	}
	
	/**
	 * M�todo en donde se muestra el frame punto de demanda del m�dulo
	 * administraci�n de datos. Este frame ya ha sido creado, por lo que en este
	 * m�todo solo se llama a los m�todos limpiar y visible frame.
	 */
	public void puntoDemanda()
	{
		framePuntoDemanda.limpiar();
		framePuntoDemanda.setVisible(true);
	}
	
	/**
	 * M�todo que muestra la ventanita configuraci�n del servidor. Este frame ya
	 * ha sido creado, por lo que en este m�todo solo se llama al m�todo visible
	 * del frame.
	 */
	public void configuracionServidor()
	{
		frameConfiguracionServidor.setVisible(true);
	}
	
	/**
	 * M�todo que muestra la ventanita contenido. Este frame ya ha sido creado,
	 * por lo que en este m�todo se llama al m�todo cargarPagina y despu�s
	 * visible del frame.
	 */
	public void contenido()
	{
		frameContenido.cargarPagina();
		frameContenido.setVisible(true);
	}
	
	/**
	 * M�todo que muestra la ventanita ejemplos de inteligencia artificial. Este
	 * frame ya ha sido creado, por lo que en este m�todo se llama al m�todo
	 * cargarPagina y despu�s visible del frame.
	 */
	public void ejemplos()
	{
		frameEjemplos.cargarPagina();
		frameEjemplos.setVisible(true);
	}
	
	/**
	 * M�todo que muestra la ventanita CIRCANA Pro en internet. Este frame ya ha
	 * sido creado, por lo que en este m�todo se llama al m�todo cargarPagina y
	 * despu�s visible del frame.
	 */
	public void internet()
	{
		frameInternet.cargarPagina();
		frameInternet.setVisible(true);
	}
	
	/**
	 * M�todo que muestra la ventanita acerca de CIRCANA Pro. Este frame ya ha
	 * sido creado, por lo que en este m�todo solo se llama al m�todo visible
	 * del frame.
	 */
	public void acerca()
	{
		frameAcerca.setVisible(true);
	}
	
	/**
	 * M�todo que cambia el tipo de look and feel al frame y todos sus
	 * componentes. Existen la opci�n de elegir uno de los cuatro tipos de look
	 * and feel: metal (moderno), motif (b�sico), windows (windows xp) �
	 * windows classic (windows cl�sico).
	 *
	 * @param tipo El tipo de apariencia seleccionada por el usuario.
	 */
	public void apariencia(int tipo)
	{
		// Intentar cambiar el look and feel.
		try
		{
			// Dependiendo del tipo de apariencia, cambiar el look and feel de
			// la aplicaci�n y los componenes GUI.
			switch (tipo)
			{
				// Apariencia Moderno.
				case 1:
				{
					UIManager.setLookAndFeel(new MetalLookAndFeel());
					actualizarLookAndFeel();
					break;
				}
				
				// Apariencia B�sico.
				case 2:
				{
					UIManager.setLookAndFeel(new MotifLookAndFeel());
					actualizarLookAndFeel();
					break;
				}
				
				// Apariencia Windows XP.
				case 3:
				{
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
					actualizarLookAndFeel();
					break;
				}
				
				// Apariencia Windows Cl�sico.
				case 4:
				{
					UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
					actualizarLookAndFeel();
					break;
				}
			}
		}
		
		// Cuando el look and feel no es soportado por el sistema operativo.
		catch (Exception excepcion)
		{
			// Mostrar el mensaje de error.
			JOptionPane.showMessageDialog(this,
				"Su sistema operativo no soporta este cambio de apariencia.",
				"No se pudo cambiar la apariencia de CIRCANA Pro 2.0",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * M�todo en donde se actualiza el look and feel del frame y todos sus
     * componentes GUI que tiene. Adem�s se actualiza el look and feel de todos
     * los cuadros de di�logos de la aplicaci�n.
     */
	private void actualizarLookAndFeel()
	{
		// Actualizar este frame y todos sus componentes GUI.
		SwingUtilities.updateComponentTreeUI(this);
		
		// Actualizar los cuadros de di�logos.
		SwingUtilities.updateComponentTreeUI(frameEvaluacionStock);
		SwingUtilities.updateComponentTreeUI(frameCaladero);
		SwingUtilities.updateComponentTreeUI(frameMercado);
		SwingUtilities.updateComponentTreeUI(frameMedioTransporte);
		SwingUtilities.updateComponentTreeUI(framePuntoDemanda);
		SwingUtilities.updateComponentTreeUI(frameConfiguracionServidor);
		SwingUtilities.updateComponentTreeUI(frameContenido);
		SwingUtilities.updateComponentTreeUI(frameEjemplos);
		SwingUtilities.updateComponentTreeUI(frameInternet);
		SwingUtilities.updateComponentTreeUI(frameAcerca);
	}
	
	/**
     * M�todo que actualiza la configuraci�n del frame Circana Pro. En
     * espec�fico, se cambia el nombre del frame.
     */
	public void cargarConfiguracion()
	{
		setTitle("CIRCANA Pro 2.0  - " + Proyecto.obtenerNombre()); 
	}
	
	/**
	 * M�todo en donde se establece un valor al atributo proyectoAbierto. Cada
	 * vez que este m�todo es invocado, se hablitan o deshabilitan las opciones
	 * que dependen de si un proyecto est� abierto o no.
	 *
	 * @param proyectoAbierto El valor para el atributo proyectoAbierto.
	 */
	public void establecerProyectoAbierto(boolean proyectoAbierto)
	{
		// Cambiar el valor.
		this.proyectoAbierto = proyectoAbierto;
		
		// Cambiar el estado de habilitado.
		menuProyecto.habilitarOpciones();
		menuAdministracionDatos.habilitarOpciones();
		menuHerramientas.habilitarOpciones();
		menuVentana.habilitarOpciones();
		menuAyuda.habilitarOpciones();
		panelBotonera.habilitarBotones();
		panelVisor.habilitarProyecto();
		panelEscritorio.habilitarFrames();
		panelEstado.habilitarComponentes();
		
		// Cuando hay un proyecto abierto.
		if (proyectoAbierto)
			cargarConfiguracion();
		
		// Cuando no hay un proyecto abierto.
		else setTitle("CIRCANA Pro 2.0");
	}
	
	/**
	 * M�todo en donde se obtiene el valor del atributo proyectoAbierto.
	 *
	 * @return proyectoAbierto El valor del atributo proyectoAbierto.
	 */
	public boolean obtenerProyectoAbierto()
	{
		return proyectoAbierto;
	}
	
	/**
	 * M�todo en donde se establece un valor al atributo procesoCorriendo. Cada
	 * vez que este m�todo es invocado, se hablitan o deshabilitan las opciones
	 * que dependen de si un proceso est� corriendo o no.
	 *
	 * @param procesoCorriendo El valor para el atributo procesoCorriendo.
	 */
	public void establecerProcesoCorriendo(boolean procesoCorriendo)
	{
		// Cambiar el valor.
		this.procesoCorriendo = procesoCorriendo;
		
		// Cambiar el estado de habilitado.
		panelEscritorio.frameProyecto.
		panelConfiguracionProyecto.habilitarBotones();
		panelEstado.habilitarEtiquetas();
		
		// Cuando no hay un proceso corriendo.
		if (!procesoCorriendo)
		{
			panelEstado.cambiarBarraEcosistema(0);
			panelEstado.cambiarBarraEconomia(0);
			panelEstado.cambiarBarraOperacion(0);
			panelEstado.cambiarBarraIntegracion(0);
		}
	}
	
	/**
	 * M�todo en donde se obtiene el valor del atributo procesoCorriendo.
	 *
	 * @return procesoCorriendo El valor del atributo procesoCorriendo.
	 */
	public boolean obtenerProcesoCorriendo()
	{
		return procesoCorriendo;
	}
}