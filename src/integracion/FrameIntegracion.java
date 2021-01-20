/**
 * @(#)FrameIntegracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase que extiende de la clase JInternalFrame. Esta clase contiene todos los
 * componentes GUI del m�dulo "Integraci�n" que tiene la aplicaci�n. Gracias a
 * estos componentes el usuario puede interactuar en forma simple con las
 * opciones que contiene el M�dulo "Integraci�n".  Estas opciones son las
 * siguientes: "Planificar", "Graficar" y "Reporte".
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see Container
 * @see KeyEvent
 * @see JInternalFrame
 * @see JTabbedPane
 * @see ImageIcon
 * @see	JOptionPane
 * @see PanelEscritorio
 * @see PanelPlanificarIntegracion
 * @see PanelGraficarIntegracion
 * @see ProcesoSegundoPlano
 * @see PanelReporteIntegracion
 * @see Resultado
 * @see ConfiguracionHeuristica
 * @see VectorResultado
 * @see VectorVenta
 * @see InterfaceInternalFrame
 */
public class FrameIntegracion extends JInternalFrame
	implements InterfaceInternalFrame
{
	/** Apuntador al panel creador de este frame. */
	public PanelEscritorio panelEscritorio;
	
	/** El panel dedicado a mostrar el planificar de integraci�n. */
	public PanelPlanificarIntegracion panelPlanificarIntegracion;
	
	/** El panel dedicado a mostrar el graficar de integraci�n. */	
	public PanelGraficarIntegracion panelGraficarIntegracion;
	
	/** El panel dedicado a mostrar el reporte de integraci�n. */
	public PanelReporteIntegracion panelReporteIntegracion;
	
	/** La paleta que contiene la lista de paneles. */
	private JTabbedPane paleta;
	
	/** El vector de resultados de ofertas. */
	private VectorResultado resultadosOferta;
	
	/** El vector de resultados de demandas. */
	private VectorResultado resultadosDemanda;
	
	/** El vector de resultados de precios. */
	private VectorResultado resultadosPrecio;
	
	/**
	 * Puntero al vector de resultados que gu�a el proceso de planificaci�n.
	 * Este puntero puede apuntar a la oferta, demanda o precio.
	 */
	private VectorResultado resultadosPlanificacion;
	
	/** El vector de resultados de ventas. */
	private VectorVenta resultadosVenta;
	
	/** Indica el a�o de planificaci�n que se est� usando. */
	private int anioPlanificacion;
	
	/** El estado actual de la planificaci�n. */
	private int estado;
	
	/** El proceso de segundo plano que hace la planificaci�n. */
	private ProcesoSegundoPlano procesoPlanificar;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el frame, los
	 * paneles y los eventos asociados de los componentes GUI que contiene este
	 * frame.
	 *
	 * @param panelEscritorio El objeto que hace referencia al panel de
	 *                        escritorio creador de este frame.
	 */
	public FrameIntegracion(PanelEscritorio panelEscritorio)
	{
		// Apuntar al panel contenedor de este frame.
		this.panelEscritorio = panelEscritorio;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarFrame();
		configurarComponentes();
	}
	
	/**
     * M�todo que configura los elementos especiales de este frame. Los
     * elementos especiales son el proceso en segundo plano, el a�o de
     * planificaci�n, los vectores de resultados y el estado.
     */	
	public void configurarElementosEspeciales()
	{
		// Crear los vectores de resultados.
		resultadosOferta = new VectorResultado();
		resultadosDemanda = new VectorResultado();
		resultadosPrecio = new VectorResultado();
		resultadosVenta = new VectorVenta();
		
		// Iniciar el a�o de planificaci�n.
		anioPlanificacion = Proyecto.obtenerFechaInicialFormatoInt()[2];
		
		// Iniciar el estado.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Iniciar el proceso de segundo plano.
		procesoPlanificar = new ProcesoSegundoPlano(this);
	}
	
	/**
	 * M�todo en donde se configuran algunas propiedades de este frame. Las
	 * propiedades que se configuran corresponden a los atributos derivados
	 * desde la clase JInternalFrame.
	 */
	public void configurarFrame()
	{
		setTitle("Integraci�n");
		setResizable(true);
		setClosable(false);
		setMaximizable(true);
		setIconifiable(true);
		setSize(panelEscritorio.getSize());
		setFrameIcon(new ImageIcon("../img/circana_pro.gif"));
	}
	
	/**
	 * M�todo que configura los paneles del frame. Los paneles son adjuntados al
	 * frame en forma de una paleta de paneles.
	 */
	public void configurarComponentes()
	{
		// Capturar el panel contenedor del frame.
		Container contenedor = getContentPane();
		
		// Crear la paleta para los paneles.
		paleta = new JTabbedPane();
		
		// Crear los paneles.
		panelPlanificarIntegracion = new PanelPlanificarIntegracion(this);
		panelGraficarIntegracion = new PanelGraficarIntegracion(this);
		panelReporteIntegracion = new PanelReporteIntegracion(this);
		
		// Incorporar los paneles al contenedor.
		paleta.addTab("Planificar",
			new ImageIcon("../img/integracion_planificar.gif"),
			panelPlanificarIntegracion,
			"Planificar la Integraci�n");
		paleta.addTab("Graficar",
			new ImageIcon("../img/integracion_graficar.gif"),
			panelGraficarIntegracion,
			"Graficar la Integraci�n");
		paleta.addTab("Reporte",
			new ImageIcon("../img/integracion_reporte.gif"),
			panelReporteIntegracion,
			"Reporte de la Integraci�n");
		
		// Adjuntar un comando �nico a cada panel.
		paleta.setMnemonicAt(0, KeyEvent.VK_P);
		paleta.setMnemonicAt(1, KeyEvent.VK_G);
		paleta.setMnemonicAt(2, KeyEvent.VK_R);
		
		// Incorporar la paleta de paneles al contendor del frame.
		contenedor.add(paleta);
	}
	
	/**
     * M�todo que ejecuta la planificaci�n de ventas de la integraci�n final.
     * Esta ejecuci�n genera un reporte. Se cambia el estado de la planificaci�n
     * a iniciada, se habilitan los botones de la planificaci�n y finalmente se
     * invoca al m�todo que ejecuta el proceso en segundo plano. Desde el
     * proceso de segundo plano se llama al m�todo ejecutarHeuristica(), en
     * donde se realiza el ciclo de planificaci�n.
     */
	public void planificar()
	{
		// Validar la configuraci�n.
		String error = panelPlanificarIntegracion.validarConfiguracion();
	 	
	 	// Cuando no hay errores.
		if (error == "")
		{
			// Cambiar el estado de la planificaci�n a iniciada.
			estado = ProcesoSegundoPlano.INICIADO;
			
			// Cambiar el estado del proyecto.
			panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(true);
			
			// Establecer la configuraci�n y deshabilitar los botones.
			panelPlanificarIntegracion.establecerConfiguracion();
			panelPlanificarIntegracion.habilitarBotones();
			
			// Invocar al proceso de segundo plano.
			procesoPlanificar.ejecutar(0);
		}
		
		// Cuando si hay errores.
		else
		{
			error = "Se han cometido los siguientes errores:\n" + error;
			JOptionPane.showMessageDialog(this, error,
				"Error en la planificaci�n", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * M�todo que detiene el proceso de planificaci�n de ventas. Se cambia el
	 * estado de la planificaci�n a detenida. Se habilitan los botones de la
	 * planificaci�n.
	 */
	public void detener()
	{
		// Cambiar el estado de la planificaci�n a detenida.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Cambiar el estado del proyecto.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(false);
		
		// Habilitar los botones de la planificaci�n.
		panelPlanificarIntegracion.habilitarBotones();
	}
	
	/**
	 * M�todo en donde se realiza el ciclo de planificaci�n de ventas del
	 * recurso en estudio. Este m�todo es llamado desde la clase
	 * ProcesoSegundoPlano, con el motivo de que no bloquee los demas procesos
	 * que pudieran encontrarse en ejecuci�n al mismo momento. En el ciclo de
	 * planificaci�n se resuelve la planificaci�n de ventas a trav�s de la
     * siguiente heur�stica: "Vender lo que m�s se pueda al mayor precio".
     */	
	public void planificarHeuristica()
	{
		// Variables locales.
		int tamanio;
		Resultado resultado;
		int dia;
		int anio;
		double ofertaAcumulada;
		double demanda;
		double precio;
		double venta;
		
		// Cargar los resultados.
		cargarResultados();
		
		// Eliminar las ventas anteriores.
		resultadosVenta.removeAllElements();
		
		// Inicializaci�n de variables.
		tamanio = resultadosPlanificacion.size();
		
		// Ciclo de planificaci�n de ventas.
		for (int i=0; i<tamanio; i++)
		{
			// Pintar el avance en la barra de progreso.
			panelEscritorio.frameCircanaPro.panelEstado.
				cambiarBarraIntegracion((int)(((double) i/tamanio)*100));
			
			// Obtener el resultado i-�simo.
			resultado = resultadosPlanificacion.obtenerElemento(i);
			
			// Obtener el d�a, el a�o, la demanda y el precio.
			dia = resultado.obtenerDia();
			anio = resultado.obtenerAnio();
			demanda = obtenerCantidad(dia, anio, resultadosDemanda);
			precio = obtenerCantidad(dia, anio, resultadosPrecio);
			
			// Calcular la oferta acumulada.
			ofertaAcumulada = obtenerOfertaAcumulada(dia, anio) -
							  obtenerVentaAcumulada(dia, anio);
			
			// Cuando la oferta acumulada es menor que la demanda.
			if (ofertaAcumulada < demanda)
				venta = ofertaAcumulada;
			
			// Cuando la oferta acumulada es mayor o igual que la demanda.
			else venta = demanda;
			
			// Cuando la venta es positiva.
			if (venta > 0)
				resultadosVenta.add(new Venta(dia, anio, ofertaAcumulada,
											  demanda, precio, venta));
		}
		
		// Detener la planificaci�n.
		detener();
		
		// Mostrar la gr�fica.
		panelGraficarIntegracion.subPanelGraficarIntegracion.graficarCurva(true);
		
		// Mostrar el reporte.
		panelReporteIntegracion.mostrarReporte();
	}
	
	/**
	 * M�todo en donde se cargan todos los resultados de los dem�s modelos. Se
	 * llama a los m�todo que cargan los resultados de los modelos: ecosistema y
	 * econom�a. Los resultados son la oferta, demanda y precio.
	 */
	private void cargarResultados()
	{
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Cargar los vectores de resultados desde la base de datos.
		cargarResultadosOferta();
		cargarResultadosDemanda();
		cargarResultadosPrecio();
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
	 * M�todo en donde se carga la oferta disponible del recurso en estudio. Se
	 * cargan los valores desde la base de datos. La tabla que se consulta es
	 * dato_ecosistemico_resultado. La oferta es tomada de los valores
	 * entregados por el modelo ecosist�mico.
	 */
	private void cargarResultadosOferta()
	{
		// Variables locales.
		int dia;
		int anio;
		double oferta;
		
		// Formar la consulta.
		String consulta =
			"select * " +
			"from dato_ecosistemico_resultado " +
			"where codigo_proyecto = " + Proyecto.obtenerCodigo() + " ";
		
		// Cuando la heur�stica es vender a mayor oferta.
		if (ConfiguracionHeuristica.obtenerHeuristica() ==
			TipoHeuristica.MAYOR_OFERTA)
		{
			// Terminar la consulta.
			consulta+= "order by biomasa_dato_ecosistemico_resultado desc, " +
					   "codigo_anio asc, codigo_dia asc";
			
			// Apuntar el puntero.
			resultadosPlanificacion = resultadosOferta;
		}
		
		// Ejecutar la consulta.
		ResultSet resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el vector oferta.
		try
		{
			// Eliminar los elementos del vector.
			resultadosOferta.removeAllElements();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del vector.
			do
			{
				// Obtener los valores.
				dia = resultado.getInt("codigo_dia");
				anio = resultado.getInt("codigo_anio");
				oferta =
					resultado.getDouble("biomasa_dato_ecosistemico_resultado");
				
				// Incorporar el resultado.
				resultadosOferta.addElement(new Resultado(dia, anio, oferta));
				
				// Avanzar el puntero del resultado.
				resultado.next();
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla " +
							   "dato_ecosistemico_resultado.");
		}
	}
	
	/**
	 * M�todo en donde se carga la demanda del recurso en estudio. Se cargan los
	 * valores desde la base de datos. La tabla que se consulta es
	 * dato_economico_resultado. La demanda es tomada de los valores entregados
	 * por el modelo econ�mico.
	 */
	private void cargarResultadosDemanda()
	{
		// Variables locales.
		int dia;
		int anio;
		double demanda;
		
		// Formar la consulta.
		String consulta =
			"select * " +
			"from dato_economico_resultado " +
			"where codigo_proyecto = " + Proyecto.obtenerCodigo() + " and " +
			"codigo_tipo_red_neuronal = " +
			ConfiguracionHeuristica.obtenerRedNeuronal() + " ";
		
		// Cuando la heur�stica es vender a mayor demanda.
		if (ConfiguracionHeuristica.obtenerHeuristica() ==
			TipoHeuristica.MAYOR_DEMANDA)
		{
			// Terminar la consulta.
			consulta+= "order by demanda_dato_economico_resultado desc, " +
					   "codigo_anio asc, codigo_dia asc";
			
			// Apuntar el puntero.
			resultadosPlanificacion = resultadosDemanda;
		}
		
		// Ejecutar la consulta.
		ResultSet resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el vector demanda.
		try
		{
			// Eliminar los elementos del vector.
			resultadosDemanda.removeAllElements();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del vector.
			do
			{
				// Obtener los valores.
				dia = resultado.getInt("codigo_dia");
				anio = resultado.getInt("codigo_anio");
				demanda =
					resultado.getDouble("demanda_dato_economico_resultado");
				
				// Incorporar el resultado.
				resultadosDemanda.addElement(new Resultado(dia, anio, demanda));
				
				// Avanzar el puntero del resultado.
				resultado.next();
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla " +
							   "dato_economico_resultado.");
		}
	}
	
	/**
	 * M�todo en donde se carga el precio del recurso en estudio. Se cargan los
	 * valores desde la base de datos. La tabla que se consulta es
	 * dato_economico_resultado. El precio es tomado de los valores entregados
	 * por el modelo econ�mico.
	 */
	private void cargarResultadosPrecio()
	{
		// Variables locales.
		int dia;
		int anio;
		double precio;
		
		// Formar la consulta.
		String consulta =
			"select * " +
			"from dato_economico_resultado " +
			"where codigo_proyecto = " + Proyecto.obtenerCodigo() + " and " +
			"codigo_tipo_red_neuronal = " +
			ConfiguracionHeuristica.obtenerRedNeuronal() + " ";
		
		// Cuando la heur�stica es vender a mayor precio.
		if (ConfiguracionHeuristica.obtenerHeuristica() ==
			TipoHeuristica.MAYOR_PRECIO)
		{
			// Terminar la consulta.
			consulta+= "order by precio_dato_economico_resultado desc, " +
					   "codigo_anio asc, codigo_dia asc";
			
			// Apuntar el puntero.
			resultadosPlanificacion = resultadosPrecio;
		}
		
		// Ejecutar la consulta.
		ResultSet resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Guardar los elementos en el vector demanda.
		try
		{
			// Eliminar los elementos del vector.
			resultadosPrecio.removeAllElements();
			
			// Posicionar en el primer resultado.
			resultado.first();
			
			// Ciclo para almacenar los resultados como elementos del vector.
			do
			{
				// Obtener los valores.
				dia = resultado.getInt("codigo_dia");
				anio = resultado.getInt("codigo_anio");
				precio = resultado.getDouble("precio_dato_economico_resultado");
				
				// Incorporar el resultado.
				resultadosPrecio.addElement(new Resultado(dia, anio, precio));
				
				// Avanzar el puntero del resultado.
				resultado.next();
			}
			while (!resultado.isAfterLast());
		}
		
		// Capturar la exepci�n y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla " +
							   "dato_economico_resultado.");
		}
	}
	
	/**
	 * M�todo que obtiene la cantidad de un resultado con d�a del a�o
	 * especificados por par�metros. Se hace un recorrido hacia adelante del
	 * vector de resultados buscando la cantidad que coincida con la fecha
	 * recibida por par�metros. Se retorna la cantidad correspondiende a esa
	 * fecha.
	 *
	 * @param dia El valor del d�a del a�o.
	 * @param anio El valor del a�o.
	 * @param resultados El vector de resultados.
	 *
	 * @return cantidad La cantidad del resultado correspondiente al d�a del a�o
	 *                  especidicados por par�metros.
	 */
	private double obtenerCantidad(int dia, int anio, VectorResultado resultados)
	{
		// Variables locales.
		int tamanio;
		Resultado r;
		
		// Inicializaci�n de las variables.
		tamanio = resultados.size();
		
		// Ciclo de b�squeda de la cantidad.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener el resultado i-�simo.
			r = resultados.obtenerElemento(i);
			
			// Cuando el d�a es igual y el a�o es igual.
			if (r.obtenerDia() == dia && r.obtenerAnio() == anio)
				return r.obtenerCantidad();
		}
		
		return -1;
	}
	
	/**
	 * M�todo que obtiene la oferta acumulada hasta un d�a del a�o especificados
	 * por par�metros. Se hace un recorrido hacia atr�s del vector de resultados
	 * de oferta buscando las ofertas que deben incluirse dentro de la oferta
	 * que se tiene antes de la fecha recibida por par�metros. Se retorna la
	 * sumatoria de ofertas m�s el stock acumulado en bodega.
	 *
	 * @param dia El valor del d�a del a�o.
	 * @param anio El valor del a�o.
	 *
	 * @return ofertaAcumulada La oferta que se encuentra acumulada desde el
	 *                         primer d�a hasta el d�a del a�o especidicados por
	 *                         par�metros.
	 */
	private double obtenerOfertaAcumulada(int dia, int anio)
	{
		// Variables locales.
		int tamanio;
		Resultado resultado;
		double sumatoria;
		double stockAcumulado;
		
		// Inicializaci�n de las variables.
		tamanio = resultadosOferta.size();
		sumatoria = 0;
		stockAcumulado = panelPlanificarIntegracion.obtenerStockAcumulado();
		
		// Ciclo de b�squeda de ofertas.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener el resultado i-�simo.
			resultado = resultadosOferta.obtenerElemento(i);
			
			// Cuando el d�a es menor o igual y el a�o es menor o igual.
			if (resultado.obtenerDia() <= dia &&
				resultado.obtenerAnio() <= anio)
				sumatoria += resultado.obtenerCantidad();
		}
		
		return sumatoria + stockAcumulado;
	}
	
	/**
	 * M�todo que obtiene la venta acumulada hasta un d�a del a�o especificados
	 * por par�metros. Se hace un recorrido hacia atr�s del vector de resultados
	 * de venta buscando las ventas que deben incluirse dentro de la venta
	 * que se tiene antes de la fecha recibida por par�metros. Se retorna la
	 * sumatoria de ventas.
	 *
	 * @param dia El valor del d�a del a�o.
	 * @param anio El valor del a�o.
	 *
	 * @return ventaAcumulada La venta que se encuentra acumulada desde el
	 *                        primer d�a hasta el d�a del a�o especidicados por
	 *                        par�metros.
	 */
	private double obtenerVentaAcumulada(int dia, int anio)
	{
		// Variables locales.
		int tamanio;
		Venta venta;
		double sumatoria;
		
		// Inicializaci�n de las variables.
		tamanio = resultadosVenta.size();
		sumatoria = 0;
		
		// Ciclo de b�squeda de ventas.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener la venta i-�sima.
			venta = resultadosVenta.obtenerElemento(i);
			
			// Cuando el d�a es menor o igual y el a�o es menor o igual.
			if (venta.obtenerDia() <= dia &&
				venta.obtenerAnio() <= anio)
				sumatoria += venta.obtenerVenta();
		}
		
		return sumatoria;
	}
	
	/**
     * M�todo que carga la informaci�n del proyecto en el frame interno del
     * proyecto.
     */
	public void cargarConfiguracion()
	{
		panelPlanificarIntegracion.cargarConfiguracion();
		panelReporteIntegracion.cargarReporte();
	}
	
	/**
     * M�todo que carga la informacion con respecto al proyecto que se encuentra 
     * en la clase estatica Proyecto. De esta manera se actualizan las opciones
     * que se encuetran relacioandas con el proyecto.
     */
	public void actualizarInformacionProyecto()
	{
		// Actualizar otros elementos.
		anioPlanificacion = Proyecto.obtenerFechaInicialFormatoInt()[2];
		
		// Actualizar el panel de planificar.
		panelPlanificarIntegracion.cargarTextoEcosistema();
		panelPlanificarIntegracion.cargarTextoEconomia();
		panelPlanificarIntegracion.cargarTextoOperacion();
		
		// Actualizar el panel graficar
		panelGraficarIntegracion.actualizarInformacionProyecto();
		
		// Volver a crear los paneles.
		panelGraficarIntegracion = new PanelGraficarIntegracion(this);
		
		// Volver a incorporar los paneles.
		paleta.setComponentAt(1, panelGraficarIntegracion);
	}
	
	/**
     * M�todo que cambia el a�o de planificaci�n. Para hacerlo se le indica al
     * a�o actual de planificaci�n si quiere incrementar o disminuir en un a�o.
     * 
     * @param tipoCambio Indica si aumenta o desminuye en un a�o. Con valor
     * 					 2 aumenta un a�o, 1 disminuye en un a�o.	
     */
	public void cambiarTiempoPlanificacion(int tipoCambio)
	{
		// Retroceder en un a�o.
		if (tipoCambio == 1)
			anioPlanificacion--;
		
		// Avanzar en un a�o.
		if (tipoCambio == 2)
			anioPlanificacion++;
		
		panelGraficarIntegracion.cambiarAnioPlanificacion(anioPlanificacion);
	}
	
	/**
	 * M�todo que muestra la ventanita "Planificar" de Integraci�n en el
	 * panel de escritorio. Se setea el foco en el panel "Planificacion".
	 */
	public void elegirPlanificar()
	{
		paleta.setSelectedIndex(0);
	}
	
	/**
	 * M�todo que muestra la ventanita "Graficar" de Integraci�n en el panel de
	 * escritorio. Se setea el foco en el panel "Graficar".
	 */
	public void elegirGraficar()
	{
		paleta.setSelectedIndex(1);
	}
	
	/**
	 * M�todo que muestra la ventanita "Reporte" de Integraci�n en el panel de
	 * escritorio. Se setea el foco en el panel "Reporte".
	 */
	public void elegirReporte()
	{
		paleta.setSelectedIndex(2);
	}
	
	/**
	 * Metodo que obtiene el vector de resultados de ventas.
	 *
	 * return resultadosVenta El vector de resultados de ventas.
	 */
	public VectorVenta obtenerResultadosVenta()
	{
		return resultadosVenta;
	}
	/**
     * M�todo que retorna el a�o actual que se esta planificando.
     * 
     * @return anioPlanificacion El a�o de planificaci�n que se est� ejecutando
     *                           en este momento.
     */
	public int obtenerAnioPlanificacion()
	{
		return anioPlanificacion;
	}
	
	/**
	 * Metodo que obtiene el valor del atributo estado.
	 *
	 * return estado El valor del atributo estado.
	 */
	public int obtenerEstado()
	{
		return estado;
	}
}