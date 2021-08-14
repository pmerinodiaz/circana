/**
 * @(#)FrameCircanaPro.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
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
 * la aplicación que se muestran al usuario. Gracias a estos componentes el
 * usuario puede interactuar en forma simple con la aplicación. La aplicación
 * tiene básicamente un menu, un panel de botones de acceso rápido, un panel
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
	
	/** Menú con las opciones del proyecto. */
	private MenuProyecto menuProyecto;
	
	/** Menú con las opciones de la administración de datos. */
	private MenuAdministracionDatos menuAdministracionDatos;
	
	/** Menú con las opciones de las herramientas. */
	private MenuHerramientas menuHerramientas;
	
	/** Menú con las opciones de ventana. */
	private MenuVentana menuVentana;
	
	/** Menú con las opciones de ayuda. */
	private MenuAyuda menuAyuda;
	
	/** Panel que contiene los botones de acceso rápido. */
	public PanelBotonera panelBotonera;
	
	/** Panel que lista el proyecto y sus módulos. */
	public PanelVisor panelVisor;
	
	/** Panel en donde se despliegan los diferentes módulos. */
	public PanelEscritorio panelEscritorio;
	
	/** Panel en donde se despliega el estado. */
	public PanelEstado panelEstado;
	
	/** Frame del módulo abrir y guardar proyecto. */
	public FrameAbrirGuardarProyecto frameAbrirGuardarProyecto;
	
	/** Frame del módulo evaluación del stock. */
	public FrameEvaluacionStock frameEvaluacionStock;
	
	/** Frame del módulo caladero. */
	public FrameCaladero frameCaladero;
	
	/** Frame del módulo mercado. */
	public FrameMercado frameMercado;
	
	/** Frame del módulo medio de transporte. */
	public FrameMedioTransporte frameMedioTransporte;
	
	/** Frame del módulo punto de demanda. */
	public FramePuntoDemanda framePuntoDemanda;
	
	/** Frame del módulo configuración del servidor. */
	public FrameConfiguracionServidor frameConfiguracionServidor;
	
	/** Frame del módulo contenido. */
	public FrameInternet frameContenido;
	
	/** Frame del módulo ejemplos de inteligencia artificial. */
	public FrameInternet frameEjemplos;
	
	/** Frame del módulo CIRCANA Pro en internet. */
	public FrameInternet frameInternet;
	
	/** Frame del módulo acerca de CIRCANA Pro. */
	public FrameAcerca frameAcerca;
	
	/** Un valor booleano que indica si hay o no un proyecto abierto. */
	private boolean proyectoAbierto;
	
	/** Un valor booleano que indica si hay o no un proceso corriendo. */
	private boolean procesoCorriendo;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el menú, el
	 * frame, los paneles, los eventos y los diálogos asociados de los
	 * componentes GUI que contiene esta aplicación.
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
	 * Método en donde se inicializan los objetos que se usan en todo el frame
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
	 * Método en donde se configuran diversas propiedades que tiene este frame.
	 * Las opciones que se cambian en este método son los atributos que se
	 * derivan de la clase JFrame. Antes de configurar el frame, se verifica
	 * que la resolución del monitor sea válida.
	 */
	public void configurarFrame()
	{
		// Primero verificar la resolución de pantalla.
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
	 * Método en donde se configura el menú de la aplicación y todas sus
	 * opciones. El menú se compone principalmente de cinco opciones: el
	 * proyecto, la administración de datos, las herramientas, la ventana y la
	 * ayuda. Cada una de estas opciones tiene sub-opciones propias para cada
	 * módulo del sistema. En este método se crean todos los objetos tipo JMenu
	 * y se incorporan a la barra de menú del tipo JMenuMar que tiene el frame.
	 * Luego, se incorpora a este frame la barra de menú al frame. Además, este
	 * método se encarga de crear los cuatro paneles del frame: la botonera, el
	 * visor del proyecto, el escritorio y el estado. En el panel botonera se
	 * incorporan todos los botones de acceso rápido que tiene la aplicación. El
	 * panel de escritorio sirve para cargar las opciones que tiene el visor del
	 * proyecto. Estas opciones son cargadas como JInternalFrame dentro del
	 * JDeskotPane que es el escritorio. El panel visor del proyecto tiene por
	 * objetivo mostrar el proyecto actual que está abierto y sus modelos
	 * asociados. El panel de estado es usado para mostrar diversa información
	 * relacionada con el estado actual del proyecto y la aplicación.
	 */
	public void configurarComponentes()
	{
		// Crear la barra del menú.
		JMenuBar barraMenu = new JMenuBar();
		
		// Crear las opciones del menú.
		menuProyecto = new MenuProyecto(this);
		menuAdministracionDatos = new MenuAdministracionDatos(this);
		menuHerramientas = new MenuHerramientas(this);
		menuVentana = new MenuVentana(this);
		menuAyuda = new MenuAyuda(this);
		
		// Incorporar las opciones del menú a la barra del menú.
		barraMenu.add(menuProyecto);
		barraMenu.add(menuAdministracionDatos);
		barraMenu.add(menuHerramientas);
		barraMenu.add(menuVentana);
		barraMenu.add(menuAyuda);
		
		// Incorporar la barra del menú al frame.
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
	 * Método que configura los eventos asociados al frame. En particular se
	 * incorpora el escuchador de eventos del tipo EventoFrame al JFrame
	 * de esta aplicación.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoFrame evento = new EventoFrame(this);
		
		// Incorporar el escuchador de eventos al componente.
		addWindowListener(evento);
	}
	
	/**
     * Método en donde se configuran los cuadros de diálogos que aparecen en
     * toda la aplicación CIRCANA Pro. Estos cuadros de diálogos son los frames
     * que se muestran en toda la aplicación CIRCANA Pro. En este método se
     * crean los diálogos, para posteriormente ser mostrados con el método show.
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
	 * Método que verifica si la resolución de pantalla ocupada en el monitor es
	 * válida para ejecutar la aplicación CIRCANA Pro. Esta aplicación requiere
	 * una resolución mínima de 1024x768 pixeles. Cuando la resolución en
	 * pantalla es menor que 1024x768, entonces se muestra un mensaje al usuario
	 * y luego se aborta la aplicación.
	 *
	 * @param ancho La resolución de pixeles en el eje X del monitor.
	 * @param alto La resolución de pixeles en el eje Y del monitor.
	 */
	private void esResolucionValida(int ancho, int alto)
	{
		// Cuando la resolución de pantalla es menor que la requerida.
		if (ancho<1024 || alto<768)
		{
			// Mostrar el mensaje de error.
			JOptionPane.showMessageDialog(this,
				"Por favor, configure su monitor a una resolución mínima de "+
				"1024x768 pixeles.",
				"No se pudo iniciar CIRCANA Pro 2.0",
				JOptionPane.ERROR_MESSAGE);
			
			// Abortar la aplicación.
			System.exit(0);
		}
	}
	
	/**
	 * Método que permite crear un nuevo proyecto de trabajo. El proyecto
	 * contiene los siguientes modelos: ecosistema, economía, operación e
	 * integración.
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
     * Método que permite crear un nuevo proyecto. Este nuevo proyecto tiene
     * la configuración por defecto establecida para cada módulo. Este método es
     * invocado cuando el usuario selecciona desde el menú el item nuevo o bien
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
		
		// Cargar la configuración en el escritorio.
		panelEscritorio.cargarConfiguracion();
		
		// Actualizar la información en el escritorio.
		panelEscritorio.actualizarInformacionProyecto();
	}
	
	/**
	 * Método que permite abrir un proyecto de trabajo ya existente. Se utiliza
	 * la clase JDialog para permitir al usuario elegir el proyecto que desea
	 * abrir.
	 */
	public void abrir()	
	{
		// Crear y mostrar el cuadro de diálogo.
		frameAbrirGuardarProyecto = new FrameAbrirGuardarProyecto(this, false);
		frameAbrirGuardarProyecto.setVisible(true);
	}
	
	/**
     * Método que abre un proyecto desde la base de datos, escogido desde el
     * frame abrir guardar proyecto por el usuario. Este método es invocado
     * cuando el usuario ha escogido un proyecto que desea abrir y luego pulsa
     * el botón abrir en el frame abrir guardar proyecto.
     */
	public void abrirProyecto()
	{
		// Obtener el código del proyecto a abrir.
		int codigo = frameAbrirGuardarProyecto.obtenerCodigoProyecto();
		
		// Cuando el código existe.
		if (codigo > 0)
		{
			// Cerrar el proyecto actual.
			cerrar();
			
			// Hacer invisible el frame.
			frameAbrirGuardarProyecto.setVisible(false);
			
			// Cargar el proyecto a abrir.
			Proyecto.cargar(codigo);
			
			// Cargar la configuración en el escritorio.
			panelEscritorio.cargarConfiguracion();
			
			// Actualizar la información en el escritorio.
			panelEscritorio.actualizarInformacionProyecto();
			
			// Establecer que hay un proyecto abierto.
			establecerProyectoAbierto(true);
			
			// Establecer que no hay procesos corriendo.
			establecerProcesoCorriendo(false);
		}
	}
	
	/**
	 * Método que permite cerrar un proyecto de trabajo ya existente. Cuando se
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
			String[] opciones = {"Sí", "No", "Cancelar"};
			
			// Cuando el proyecto ha sido modificado.
			if (Proyecto.obtenerModificado())
			{
				// Obtener la opción del usuario.
				opcion = JOptionPane.showOptionDialog(this,
					"¿Guardar cambios en " + Proyecto.obtenerNombre() + "?",
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
	 * Método que permite guardar el proyecto de trabajo ya existente con el
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
	 * Método que permite guardar el proyecto de trabajo ya existente con otro
	 * nombre. Se utiliza la clase JDialog para permitir al usuario escribir el
	 * nombre del proyecto que desea guardar.
	 */
	public void guardarComo()
	{
		// Crear y mostrar el cuadro de diálogo.
		frameAbrirGuardarProyecto = new FrameAbrirGuardarProyecto(this, true);
		frameAbrirGuardarProyecto.setVisible(true);
	}
	
	/**
     * Método que guarda un proyecto en la base de datos desde el frame abrir
     * guardar proyecto. Este método es invocado desde el frame abrir guardar
     * proyecto cuando el usuario escoje un nombre para el proyecto y luego
     * pulsa el botón guardar.
     */
	public void guardarProyecto()
	{
		// Obtener el nombre del proyecto a guardar.
		String nombre = "" + frameAbrirGuardarProyecto.obtenerNombreProyecto();
		
		// Cuando el nombre del proyecto existe y es válido.
		if ((!nombre.equals("")) &&
			(BaseDatoMotor.esValidoDatoIngresado(nombre)))
		{			
			// Hacer invisible el frame.
			frameAbrirGuardarProyecto.setVisible(false);
			
			// Guardar el proyecto.
			Proyecto.guardarComo(nombre);
			
			// Cargar la configuración en el escritorio.
			panelEscritorio.cargarConfiguracion();
			
			// Actualizar la información en el escritorio.
			panelEscritorio.actualizarInformacionProyecto();
		}
	}
	
	/**
	 * Método que permite salir de la aplicación. Primero se cierra el proyecto
	 * y luego se sale de la aplicación.
	 */
	public void salir()
	{
		// Cerrar el proyecto.
		cerrar();
		
		// Abortar la aplicación.
		System.exit(0);
	}
	
	/**
	 * Método en donde se muestra el frame evaluación de stock del módulo
	 * administración de datos. Este frame ya ha sido creado, por lo que en este
	 * método solo se llama a los métodos limpiar y visible del frame.
	 */
	public void evaluacionStock()
	{
		frameEvaluacionStock.limpiar();
		frameEvaluacionStock.setVisible(true);
	}
	
	/**
	 * Método en donde se muestra el frame caladero del módulo administración
	 * de datos. Este frame ya ha sido creado, por lo que en este método solo se
	 * llama a los métodos limpiar y visible del frame.
	 */
	public void caladero()
	{
		frameCaladero.limpiar();
		frameCaladero.setVisible(true);
	}
	
	/**
	 * Método en donde se muestra el frame mercado del módulo administración de
	 * datos. Este frame ya ha sido creado, por lo que en este método solo se
	 * llama a los métodos limpiar y visible frame.
	 */
	public void mercado()
	{
		frameMercado.limpiar();
		frameMercado.setVisible(true);
	}
	
	/**
	 * Método en donde se muestra el frame medio de transporte del módulo
	 * administración de datos. Este frame ya ha sido creado, por lo que en este
	 * método solo se llama a los métodos limpiar y visible frame.
	 */
	public void medioTransporte()
	{
		frameMedioTransporte.limpiar();
		frameMedioTransporte.setVisible(true);
	}
	
	/**
	 * Método en donde se muestra el frame punto de demanda del módulo
	 * administración de datos. Este frame ya ha sido creado, por lo que en este
	 * método solo se llama a los métodos limpiar y visible frame.
	 */
	public void puntoDemanda()
	{
		framePuntoDemanda.limpiar();
		framePuntoDemanda.setVisible(true);
	}
	
	/**
	 * Método que muestra la ventanita configuración del servidor. Este frame ya
	 * ha sido creado, por lo que en este método solo se llama al método visible
	 * del frame.
	 */
	public void configuracionServidor()
	{
		frameConfiguracionServidor.setVisible(true);
	}
	
	/**
	 * Método que muestra la ventanita contenido. Este frame ya ha sido creado,
	 * por lo que en este método se llama al método cargarPagina y después
	 * visible del frame.
	 */
	public void contenido()
	{
		frameContenido.cargarPagina();
		frameContenido.setVisible(true);
	}
	
	/**
	 * Método que muestra la ventanita ejemplos de inteligencia artificial. Este
	 * frame ya ha sido creado, por lo que en este método se llama al método
	 * cargarPagina y después visible del frame.
	 */
	public void ejemplos()
	{
		frameEjemplos.cargarPagina();
		frameEjemplos.setVisible(true);
	}
	
	/**
	 * Método que muestra la ventanita CIRCANA Pro en internet. Este frame ya ha
	 * sido creado, por lo que en este método se llama al método cargarPagina y
	 * después visible del frame.
	 */
	public void internet()
	{
		frameInternet.cargarPagina();
		frameInternet.setVisible(true);
	}
	
	/**
	 * Método que muestra la ventanita acerca de CIRCANA Pro. Este frame ya ha
	 * sido creado, por lo que en este método solo se llama al método visible
	 * del frame.
	 */
	public void acerca()
	{
		frameAcerca.setVisible(true);
	}
	
	/**
	 * Método que cambia el tipo de look and feel al frame y todos sus
	 * componentes. Existen la opción de elegir uno de los cuatro tipos de look
	 * and feel: metal (moderno), motif (básico), windows (windows xp) ó
	 * windows classic (windows clásico).
	 *
	 * @param tipo El tipo de apariencia seleccionada por el usuario.
	 */
	public void apariencia(int tipo)
	{
		// Intentar cambiar el look and feel.
		try
		{
			// Dependiendo del tipo de apariencia, cambiar el look and feel de
			// la aplicación y los componenes GUI.
			switch (tipo)
			{
				// Apariencia Moderno.
				case 1:
				{
					UIManager.setLookAndFeel(new MetalLookAndFeel());
					actualizarLookAndFeel();
					break;
				}
				
				// Apariencia Básico.
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
				
				// Apariencia Windows Clásico.
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
     * Método en donde se actualiza el look and feel del frame y todos sus
     * componentes GUI que tiene. Además se actualiza el look and feel de todos
     * los cuadros de diálogos de la aplicación.
     */
	private void actualizarLookAndFeel()
	{
		// Actualizar este frame y todos sus componentes GUI.
		SwingUtilities.updateComponentTreeUI(this);
		
		// Actualizar los cuadros de diálogos.
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
     * Método que actualiza la configuración del frame Circana Pro. En
     * específico, se cambia el nombre del frame.
     */
	public void cargarConfiguracion()
	{
		setTitle("CIRCANA Pro 2.0  - " + Proyecto.obtenerNombre()); 
	}
	
	/**
	 * Método en donde se establece un valor al atributo proyectoAbierto. Cada
	 * vez que este método es invocado, se hablitan o deshabilitan las opciones
	 * que dependen de si un proyecto está abierto o no.
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
	 * Método en donde se obtiene el valor del atributo proyectoAbierto.
	 *
	 * @return proyectoAbierto El valor del atributo proyectoAbierto.
	 */
	public boolean obtenerProyectoAbierto()
	{
		return proyectoAbierto;
	}
	
	/**
	 * Método en donde se establece un valor al atributo procesoCorriendo. Cada
	 * vez que este método es invocado, se hablitan o deshabilitan las opciones
	 * que dependen de si un proceso está corriendo o no.
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
	 * Método en donde se obtiene el valor del atributo procesoCorriendo.
	 *
	 * @return procesoCorriendo El valor del atributo procesoCorriendo.
	 */
	public boolean obtenerProcesoCorriendo()
	{
		return procesoCorriendo;
	}
}