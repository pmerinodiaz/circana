/**
 * @(#)PanelReporteOperacion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario un
 * reporte de las diversas variables que influyen en el proceso de captura y
 * operación del recurso en estudio.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Color
 * @see Font
 * @see KeyEvent
 * @see JPanel
 * @see JTextAtea
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see JScrollPane
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameOperacion
 * @see Impresora
 * @see EventoButton
 * @see InterfacePanel
 * @see InterfaceReporte
 */
public class PanelReporteOperacion extends JPanel
	implements InterfacePanel, InterfaceReporte
{
	/** El frame que contiene a este panel. */
	public FrameOperacion frameOperacion;	
	
	/** Memo donde se genera el informe del reporte. */
	private JTextArea textoReporte;
	
	/** El botón para guardar los resultados. */
	private JButton botonGuardar;
	
	/** El botón para imprimir el reporte. */
	private JButton botonImprimir;
	
	/** Reporte generado por los módulos de operación. */
	private String reporteCompleto;
	
	/** Reporte generado por el algoritmo genético de transporte. */
	private String reporteTransporte;
	
	/** Reporte generado por el algoritmo genético de ruteo de naves. */
	private String reporteRuta;
	
	/**
	 * Objeto que maneja la impresora. Imprime el reporte generado por el
	 * módulo de operación.
	 */
	private Impresora impresora;
	
	/**
     * Cosntructor del panel con el cual se configuran los eventos y los botones
     * del panel. Recibe como parámetro el frame creador de este panel.
     *
     * @param frameOperacion Un objeto que hace referencia al FrameOperacion
     *                       que contiene a este panel.
     */
	public PanelReporteOperacion(FrameOperacion frameOperacion)
	{
		// Apuntar el frame contenedor de este panel.
		this.frameOperacion = frameOperacion;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarPanel();
		configurarComponentes();
		configurarEventos();
	}
	
	/**
	 * Método en donde se inician los atributos que tiene esta clase y que son
	 * utilizados en todo el proceso de reporte.
	 */
	public void configurarElementosEspeciales()
	{
		reporteCompleto = "";
		reporteTransporte = "";
		reporteRuta = "";
		impresora = new Impresora();
	}
	
	/**
	 * Método en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran corresponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este método.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
		
	/**
     * Método que configura los componentes GUI que tiene este panel.
     */
	public void configurarComponentes()
	{
		// Crear el panel.
		JPanel panel = new JPanel();
		Border borde = new LineBorder(Color.darkGray, 1);
		TitledBorder titulo = new TitledBorder(borde,
							  " Reporte Generado ", TitledBorder.LEFT,
							  TitledBorder.TOP);
		panel.setBorder(titulo);
		panel.setLayout(null);
		panel.setBounds(10, 10, 800, 540);
		
		// Crear los componentes.
		textoReporte = new JTextArea();
		JScrollPane barraReporte = new JScrollPane(textoReporte);
		botonGuardar = new JButton("Guardar",
			new ImageIcon("../img/proyecto_guardar.gif"));
		botonImprimir = new JButton("Imprimir",
			new ImageIcon("../img/imprimir.gif"));
		textoReporte.setFont(new Font("Courier New", Font.PLAIN, 12));
		barraReporte.setBounds(10, 20, 780, 470);
		botonGuardar.setBounds(290, 500, 120, 30);
		botonImprimir.setBounds(420, 500, 120, 30);
		textoReporte.setEditable(false);
		textoReporte.setAutoscrolls(true);
		botonGuardar.setMnemonic(KeyEvent.VK_G);
		botonImprimir.setMnemonic(KeyEvent.VK_I);
		botonGuardar.setToolTipText("Guardar el reporte generado");
		botonImprimir.setToolTipText("Imprimir el reporte generado");
		
		// Cargar el reporte del proyecto.
		cargarReporte();
		
		// Agregar los componentes al panel.
		panel.add(barraReporte);
		panel.add(botonGuardar);
		panel.add(botonImprimir);
		
		// Agregar el panel.
		add(panel);		
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones que tiene el
	 * panel de reporte. En particular se incorpora el escuchador de eventos del
	 * tipo EventoButton a los JButton que tiene este panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos al botón.
		botonImprimir.addActionListener(eventoButton);
		botonGuardar.addActionListener(eventoButton);
	}
	
	/**
	 * Método en donde se verifica si hay o no un reporte operativo en el
	 * proyecto. En caso de haberlo, entonces se carga en el área de texto. En
	 * caso contrario, se deshabilitan los botones guardar e imprimir.
	 */
	public void cargarReporte()
	{
		// Obtener el reporte ecosistémico.
		String reporte = Proyecto.obtenerReporteOperacion();
		
		// Cuando hay reporte operativo en el proyecto.
		if ((reporte != null) && (reporte.length() > 0))
		{
			textoReporte.setText(reporte);
			habilitarBotones(true);
		}
		
		// Cuando no hay reporte operativo en el proyecto.
		else
		{
			textoReporte.setText("");
			habilitarBotones(false);
		}
	}
	
	/**
	 * Método que genera el reporte a partir de la solución encontrada por el
	 * el algoritmo genético de transporte.
	 */
	public void generarReporteTransporte()
	{
		int i;
		int j;
		int k;
		int largo;
		String descripcion;
		String tabulacion;
		String barra;
		reporteTransporte = "";
 		IndividuoTransporte optimo = frameOperacion.obtenerSolucionTransporte();
 		double costo = optimo.obtenerEvaluacion();
 		double[][][] valor = optimo.obtenerSolucion();
 		
 		reporteTransporte = "1. Plan de Distribución del Recurso:\n" +
 							"------------------------------------\n\n" +
 							"Costo Total Mínimo = $ " +
 							Servicio.obtenerNumeroFormateado(costo,4) + "\n\n";
 		
 		for(k = 0;k < ConfiguracionAGT.l;k ++)
 		{
 			// descripción de la nave
 			descripcion = "Embarcación : " +
 						ConfiguracionAGT.capacidad.obtenerElemento(k).
 						obtenerDescripcion();
			
 			barra = "";
 			for(largo = 0;largo < descripcion.length();largo++)
 				barra = barra + "=";
			
 			reporteTransporte = reporteTransporte + descripcion + "\n" +
 								barra + "\n\n";
 			
 			// tabla con distribución de origen a destino por nave
 			tabulacion = "\t\t\t\t\t\t\t\t\t\t\t";
 			reporteTransporte = reporteTransporte + tabulacion;
 			for(j = 0;j < ConfiguracionAGT.n;j++)
 				reporteTransporte = reporteTransporte +
 									ConfiguracionAGT.demanda.
 									obtenerElemento(j).obtenerDescripcion()
 									+ "\t";
 			
 			reporteTransporte = reporteTransporte + "\n";
 			for(i = 0;i < ConfiguracionAGT.m;i++)
 			{
 				descripcion = ConfiguracionAGT.oferta.
 							  obtenerElemento(i).obtenerDescripcion();
 				
 				if(descripcion.length() > 95)
 					tabulacion = "\t";
 				else
 				if(descripcion.length() > 30 && descripcion.length() <= 95)
 					tabulacion = "\t\t";
 				else
 				if(descripcion.length() <= 30)
 					tabulacion = "\t\t\t\t\t\t\t\t";
				
 				reporteTransporte = reporteTransporte +
 									descripcion +
 									tabulacion;
	 			
	 			for(j = 0;j < ConfiguracionAGT.n;j++)
	 				reporteTransporte = reporteTransporte +
	 					Servicio.obtenerNumeroFormateado(valor[i][j][k],10) +
	 					"\t\t\t\t\t";
	 			
	 			reporteTransporte = reporteTransporte + "\n";
 			}
 			
 			reporteTransporte = reporteTransporte + "\n\n";
 		}
	}
	
	/**
	 * Método que genera el reporte a partir de la solución encontrada por el
	 * el algoritmo genético de ruteo.
	 */
	public void generarReporteRuta(String reporte)
	{
		reporteRuta = "2. Planificación de Ruta por Nave:\n" +
					  "----------------------------------\n\n" +
						reporte;
		reporteRuta = reporteRuta;
	}
	
	/**
	 * Método que genera un reporte con las soluciones encontradas al problema 
	 * de transporte y ruteo de las naves.
	 */
	public void mostrarReporte()
	{
		reporteCompleto = "REPORTE OPERACION\n" +
						  "=================\n\n" +
		 				  reporteTransporte + "\n\n" +
		 				  reporteRuta;
		
		textoReporte.setText(reporteCompleto);
		habilitarBotones(true);
	}
	
	/**
	 * Método que limpia el reporte generado y mostrado en pantalla.
	 */
	public void limpiarReporte()
	{
		reporteTransporte = "";
		reporteRuta = "";
		reporteCompleto = "";
		textoReporte.setText(reporteCompleto);
		habilitarBotones(false);
	}
	
	/**
	 * Método en donde se guardan en la base de datos los resultados generados.
	 * Si existen resultados antiguos, entonces son borrados. Luego, se insertan
	 * los resultados en la tabla dato_operativo_resultado, unidad y ruta.
	 */
	public void guardarReporte()
	{
		// Variables temporales.
		String consulta;
		int codigoProyecto = Proyecto.obtenerCodigo();
		int codigoDia = ConfiguracionAGT.obtenerDiaCaladero();
		int codigoAnio = ConfiguracionAGT.obtenerAnioCaladero();
		int codigoCaladero;
		int codigoPuntoDemanda;
		int codigoMedioTransporte;
		int i;
		int j;
		int k;
		
 		IndividuoTransporte optimo = frameOperacion.obtenerSolucionTransporte();
 		double[][][] valor = optimo.obtenerSolucion();
 		double[][][] costo = ConfiguracionAGT.matrizCostos;
 		double costoTotal = optimo.obtenerEvaluacion();
 		double cantidadTotal = 0;
 		
 		AlgoritmoGeneticoRuta[] naves = frameOperacion.obtenerAGR();
 		IndividuoRuta elemento;
 		String ruta = "";
 		double distancia = 0;
 		
 		// calcular cantidad total
 		for(i = 0;i < (ConfiguracionAGT.m - 1);i++)
 			for(j = 0;j < (ConfiguracionAGT.n - 1);j++)
 				for(k = 0;k < (ConfiguracionAGT.l - 1);k++)
 					cantidadTotal = cantidadTotal + valor[i][j][k];
		
		// Limpiar los resultados anteriores.
		Proyecto.limpiarResultados(Proyecto.OPERACION, -1);
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// guardar en tabla dato_operativo_resultado
		consulta = "INSERT INTO dato_operativo_resultado " +
				   "(codigo_proyecto, codigo_anio, codigo_dia, " +
				   "costo_total_dato_operativo_resultado, " +
				   "cantidad_total_dato_operativo_resultado) " +
				   "VALUES (" + codigoProyecto + "," + codigoAnio + "," +
				   codigoDia + "," + costoTotal + "," + cantidadTotal + ")";
		
		Proyecto.CONEXION.ejecutarConsultaActualizacion(consulta);
		
		// guardar en tabla unidad
 		for(i = 0;i < (ConfiguracionAGT.m - 1);i++)
 			for(j = 0;j < (ConfiguracionAGT.n - 1);j++)
 				for(k = 0;k < (ConfiguracionAGT.l - 1);k++)
 				{
					consulta =
						"INSERT INTO unidad " +
						"(codigo_proyecto, codigo_anio, codigo_dia, " +
						"codigo_caladero_resultado, codigo_punto_demanda, " +
						"codigo_medio_transporte , costo_unidad, " +
						"cantidad_unidad) VALUES(" +
						codigoProyecto + "," +
						codigoAnio + "," +
						codigoDia + "," +
						ConfiguracionAGT.oferta.obtenerElemento(i).obtenerCodigo() + "," +
						ConfiguracionAGT.demanda.obtenerElemento(j).obtenerCodigo() + "," +
						ConfiguracionAGT.capacidad.obtenerElemento(k).obtenerCodigo() + "," +
						costo[i][j][k] + "," +
						valor[i][j][k] + ")";
					
					Proyecto.CONEXION.ejecutarConsultaActualizacion(consulta);
 				}
 		
 		// guardar en tabla ruta
 		for(k = 0;k < (ConfiguracionAGT.l - 1);k++)
 		{
 			elemento = naves[k].encontrarOptimo();
 			
 			if(elemento.obtenerRuta().size() > 0)
 			{
 				ruta = "" + elemento.obtenerRuta().obtenerRuta();
 				distancia = naves[k].evaluacionRuta(elemento.obtenerRuta());
				consulta = "INSERT INTO ruta (codigo_proyecto, " +
						   "codigo_anio, codigo_dia, codigo_medio_transporte , " +
						   "tour_ruta, distancia_total_ruta) " +
						   "VALUES (" + codigoProyecto + "," +
						   codigoAnio + "," +
						   codigoDia + "," +
						   ConfiguracionAGT.capacidad.obtenerElemento(k).obtenerCodigo() +
						   ",'" + ruta + "'," + distancia + ")";
				
				Proyecto.CONEXION.ejecutarConsultaActualizacion(consulta);
 			}
 		}
 		
		// Guardar el reporte del proyecto.
		Proyecto.establecerReporteOperacion(textoReporte.getText());
		Proyecto.establecerModificado(true);
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que imprime el reporte generado por el módulo operación.
     */
	public void imprimirReporte()
	{
		impresora.imprimirReporte(textoReporte);
	}
	
	/**
	 * Método que habilita o deshabilita el boton guardar e imprimir.
	 *
	 * @param habilitador Un booleano que indica si se habilita o no el botón
	 *                    guardar e imprimir.
	 */
	public void habilitarBotones(boolean habilitador)
	{
		botonImprimir.setEnabled(habilitador);
		botonGuardar.setEnabled(habilitador);
	}
	
	/**
	 * Método que obtiene el objeto botonGuardar.
	 *
	 * @return botonGuardar el botón del panel Reporte.
	 */
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
	}
	
	/**
	 * Método que obtiene el objeto botonImprimir.
	 *
	 * @return botonImprimir el botón del panel Reporte.
	 */
	public JButton obtenerBotonImprimir()
	{
		return botonImprimir;
	}
}