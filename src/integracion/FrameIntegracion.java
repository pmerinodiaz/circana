/**
 * @(#)FrameIntegracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
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
 * componentes GUI del módulo "Integración" que tiene la aplicación. Gracias a
 * estos componentes el usuario puede interactuar en forma simple con las
 * opciones que contiene el Módulo "Integración".  Estas opciones son las
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
	
	/** El panel dedicado a mostrar el planificar de integración. */
	public PanelPlanificarIntegracion panelPlanificarIntegracion;
	
	/** El panel dedicado a mostrar el graficar de integración. */	
	public PanelGraficarIntegracion panelGraficarIntegracion;
	
	/** El panel dedicado a mostrar el reporte de integración. */
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
	 * Puntero al vector de resultados que guía el proceso de planificación.
	 * Este puntero puede apuntar a la oferta, demanda o precio.
	 */
	private VectorResultado resultadosPlanificacion;
	
	/** El vector de resultados de ventas. */
	private VectorVenta resultadosVenta;
	
	/** Indica el año de planificación que se está usando. */
	private int anioPlanificacion;
	
	/** El estado actual de la planificación. */
	private int estado;
	
	/** El proceso de segundo plano que hace la planificación. */
	private ProcesoSegundoPlano procesoPlanificar;
	
	/**
	 * Método constructor que invoca a los métodos que configuran el frame, los
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
     * Método que configura los elementos especiales de este frame. Los
     * elementos especiales son el proceso en segundo plano, el año de
     * planificación, los vectores de resultados y el estado.
     */	
	public void configurarElementosEspeciales()
	{
		// Crear los vectores de resultados.
		resultadosOferta = new VectorResultado();
		resultadosDemanda = new VectorResultado();
		resultadosPrecio = new VectorResultado();
		resultadosVenta = new VectorVenta();
		
		// Iniciar el año de planificación.
		anioPlanificacion = Proyecto.obtenerFechaInicialFormatoInt()[2];
		
		// Iniciar el estado.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Iniciar el proceso de segundo plano.
		procesoPlanificar = new ProcesoSegundoPlano(this);
	}
	
	/**
	 * Método en donde se configuran algunas propiedades de este frame. Las
	 * propiedades que se configuran corresponden a los atributos derivados
	 * desde la clase JInternalFrame.
	 */
	public void configurarFrame()
	{
		setTitle("Integración");
		setResizable(true);
		setClosable(false);
		setMaximizable(true);
		setIconifiable(true);
		setSize(panelEscritorio.getSize());
		setFrameIcon(new ImageIcon("../img/circana_pro.gif"));
	}
	
	/**
	 * Método que configura los paneles del frame. Los paneles son adjuntados al
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
			"Planificar la Integración");
		paleta.addTab("Graficar",
			new ImageIcon("../img/integracion_graficar.gif"),
			panelGraficarIntegracion,
			"Graficar la Integración");
		paleta.addTab("Reporte",
			new ImageIcon("../img/integracion_reporte.gif"),
			panelReporteIntegracion,
			"Reporte de la Integración");
		
		// Adjuntar un comando único a cada panel.
		paleta.setMnemonicAt(0, KeyEvent.VK_P);
		paleta.setMnemonicAt(1, KeyEvent.VK_G);
		paleta.setMnemonicAt(2, KeyEvent.VK_R);
		
		// Incorporar la paleta de paneles al contendor del frame.
		contenedor.add(paleta);
	}
	
	/**
     * Método que ejecuta la planificación de ventas de la integración final.
     * Esta ejecución genera un reporte. Se cambia el estado de la planificación
     * a iniciada, se habilitan los botones de la planificación y finalmente se
     * invoca al método que ejecuta el proceso en segundo plano. Desde el
     * proceso de segundo plano se llama al método ejecutarHeuristica(), en
     * donde se realiza el ciclo de planificación.
     */
	public void planificar()
	{
		// Validar la configuración.
		String error = panelPlanificarIntegracion.validarConfiguracion();
	 	
	 	// Cuando no hay errores.
		if (error == "")
		{
			// Cambiar el estado de la planificación a iniciada.
			estado = ProcesoSegundoPlano.INICIADO;
			
			// Cambiar el estado del proyecto.
			panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(true);
			
			// Establecer la configuración y deshabilitar los botones.
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
				"Error en la planificación", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método que detiene el proceso de planificación de ventas. Se cambia el
	 * estado de la planificación a detenida. Se habilitan los botones de la
	 * planificación.
	 */
	public void detener()
	{
		// Cambiar el estado de la planificación a detenida.
		estado = ProcesoSegundoPlano.DETENIDO;
		
		// Cambiar el estado del proyecto.
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(false);
		
		// Habilitar los botones de la planificación.
		panelPlanificarIntegracion.habilitarBotones();
	}
	
	/**
	 * Método en donde se realiza el ciclo de planificación de ventas del
	 * recurso en estudio. Este método es llamado desde la clase
	 * ProcesoSegundoPlano, con el motivo de que no bloquee los demas procesos
	 * que pudieran encontrarse en ejecución al mismo momento. En el ciclo de
	 * planificación se resuelve la planificación de ventas a través de la
     * siguiente heurística: "Vender lo que más se pueda al mayor precio".
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
		
		// Inicialización de variables.
		tamanio = resultadosPlanificacion.size();
		
		// Ciclo de planificación de ventas.
		for (int i=0; i<tamanio; i++)
		{
			// Pintar el avance en la barra de progreso.
			panelEscritorio.frameCircanaPro.panelEstado.
				cambiarBarraIntegracion((int)(((double) i/tamanio)*100));
			
			// Obtener el resultado i-ésimo.
			resultado = resultadosPlanificacion.obtenerElemento(i);
			
			// Obtener el día, el año, la demanda y el precio.
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
		
		// Detener la planificación.
		detener();
		
		// Mostrar la gráfica.
		panelGraficarIntegracion.subPanelGraficarIntegracion.graficarCurva(true);
		
		// Mostrar el reporte.
		panelReporteIntegracion.mostrarReporte();
	}
	
	/**
	 * Método en donde se cargan todos los resultados de los demás modelos. Se
	 * llama a los método que cargan los resultados de los modelos: ecosistema y
	 * economía. Los resultados son la oferta, demanda y precio.
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
	 * Método en donde se carga la oferta disponible del recurso en estudio. Se
	 * cargan los valores desde la base de datos. La tabla que se consulta es
	 * dato_ecosistemico_resultado. La oferta es tomada de los valores
	 * entregados por el modelo ecosistémico.
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
		
		// Cuando la heurística es vender a mayor oferta.
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
		
		// Capturar la exepción y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla " +
							   "dato_ecosistemico_resultado.");
		}
	}
	
	/**
	 * Método en donde se carga la demanda del recurso en estudio. Se cargan los
	 * valores desde la base de datos. La tabla que se consulta es
	 * dato_economico_resultado. La demanda es tomada de los valores entregados
	 * por el modelo económico.
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
		
		// Cuando la heurística es vender a mayor demanda.
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
		
		// Capturar la exepción y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla " +
							   "dato_economico_resultado.");
		}
	}
	
	/**
	 * Método en donde se carga el precio del recurso en estudio. Se cargan los
	 * valores desde la base de datos. La tabla que se consulta es
	 * dato_economico_resultado. El precio es tomado de los valores entregados
	 * por el modelo económico.
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
		
		// Cuando la heurística es vender a mayor precio.
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
		
		// Capturar la exepción y mostrarla.
		catch (SQLException exepcion)
		{
			System.err.println("No se pudo cargar la tabla " +
							   "dato_economico_resultado.");
		}
	}
	
	/**
	 * Método que obtiene la cantidad de un resultado con día del año
	 * especificados por parámetros. Se hace un recorrido hacia adelante del
	 * vector de resultados buscando la cantidad que coincida con la fecha
	 * recibida por parámetros. Se retorna la cantidad correspondiende a esa
	 * fecha.
	 *
	 * @param dia El valor del día del año.
	 * @param anio El valor del año.
	 * @param resultados El vector de resultados.
	 *
	 * @return cantidad La cantidad del resultado correspondiente al día del año
	 *                  especidicados por parámetros.
	 */
	private double obtenerCantidad(int dia, int anio, VectorResultado resultados)
	{
		// Variables locales.
		int tamanio;
		Resultado r;
		
		// Inicialización de las variables.
		tamanio = resultados.size();
		
		// Ciclo de búsqueda de la cantidad.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener el resultado i-ésimo.
			r = resultados.obtenerElemento(i);
			
			// Cuando el día es igual y el año es igual.
			if (r.obtenerDia() == dia && r.obtenerAnio() == anio)
				return r.obtenerCantidad();
		}
		
		return -1;
	}
	
	/**
	 * Método que obtiene la oferta acumulada hasta un día del año especificados
	 * por parámetros. Se hace un recorrido hacia atrás del vector de resultados
	 * de oferta buscando las ofertas que deben incluirse dentro de la oferta
	 * que se tiene antes de la fecha recibida por parámetros. Se retorna la
	 * sumatoria de ofertas más el stock acumulado en bodega.
	 *
	 * @param dia El valor del día del año.
	 * @param anio El valor del año.
	 *
	 * @return ofertaAcumulada La oferta que se encuentra acumulada desde el
	 *                         primer día hasta el día del año especidicados por
	 *                         parámetros.
	 */
	private double obtenerOfertaAcumulada(int dia, int anio)
	{
		// Variables locales.
		int tamanio;
		Resultado resultado;
		double sumatoria;
		double stockAcumulado;
		
		// Inicialización de las variables.
		tamanio = resultadosOferta.size();
		sumatoria = 0;
		stockAcumulado = panelPlanificarIntegracion.obtenerStockAcumulado();
		
		// Ciclo de búsqueda de ofertas.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener el resultado i-ésimo.
			resultado = resultadosOferta.obtenerElemento(i);
			
			// Cuando el día es menor o igual y el año es menor o igual.
			if (resultado.obtenerDia() <= dia &&
				resultado.obtenerAnio() <= anio)
				sumatoria += resultado.obtenerCantidad();
		}
		
		return sumatoria + stockAcumulado;
	}
	
	/**
	 * Método que obtiene la venta acumulada hasta un día del año especificados
	 * por parámetros. Se hace un recorrido hacia atrás del vector de resultados
	 * de venta buscando las ventas que deben incluirse dentro de la venta
	 * que se tiene antes de la fecha recibida por parámetros. Se retorna la
	 * sumatoria de ventas.
	 *
	 * @param dia El valor del día del año.
	 * @param anio El valor del año.
	 *
	 * @return ventaAcumulada La venta que se encuentra acumulada desde el
	 *                        primer día hasta el día del año especidicados por
	 *                        parámetros.
	 */
	private double obtenerVentaAcumulada(int dia, int anio)
	{
		// Variables locales.
		int tamanio;
		Venta venta;
		double sumatoria;
		
		// Inicialización de las variables.
		tamanio = resultadosVenta.size();
		sumatoria = 0;
		
		// Ciclo de búsqueda de ventas.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener la venta i-ésima.
			venta = resultadosVenta.obtenerElemento(i);
			
			// Cuando el día es menor o igual y el año es menor o igual.
			if (venta.obtenerDia() <= dia &&
				venta.obtenerAnio() <= anio)
				sumatoria += venta.obtenerVenta();
		}
		
		return sumatoria;
	}
	
	/**
     * Método que carga la información del proyecto en el frame interno del
     * proyecto.
     */
	public void cargarConfiguracion()
	{
		panelPlanificarIntegracion.cargarConfiguracion();
		panelReporteIntegracion.cargarReporte();
	}
	
	/**
     * Método que carga la informacion con respecto al proyecto que se encuentra 
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
     * Método que cambia el año de planificación. Para hacerlo se le indica al
     * año actual de planificación si quiere incrementar o disminuir en un año.
     * 
     * @param tipoCambio Indica si aumenta o desminuye en un año. Con valor
     * 					 2 aumenta un año, 1 disminuye en un año.	
     */
	public void cambiarTiempoPlanificacion(int tipoCambio)
	{
		// Retroceder en un año.
		if (tipoCambio == 1)
			anioPlanificacion--;
		
		// Avanzar en un año.
		if (tipoCambio == 2)
			anioPlanificacion++;
		
		panelGraficarIntegracion.cambiarAnioPlanificacion(anioPlanificacion);
	}
	
	/**
	 * Método que muestra la ventanita "Planificar" de Integración en el
	 * panel de escritorio. Se setea el foco en el panel "Planificacion".
	 */
	public void elegirPlanificar()
	{
		paleta.setSelectedIndex(0);
	}
	
	/**
	 * Método que muestra la ventanita "Graficar" de Integración en el panel de
	 * escritorio. Se setea el foco en el panel "Graficar".
	 */
	public void elegirGraficar()
	{
		paleta.setSelectedIndex(1);
	}
	
	/**
	 * Método que muestra la ventanita "Reporte" de Integración en el panel de
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
     * Método que retorna el año actual que se esta planificando.
     * 
     * @return anioPlanificacion El año de planificación que se está ejecutando
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