/**
 * @(#)PanelReporteEconomia.java 2.0 01/03/05
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
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario
 * un reporte de las diversas variables que influyen para la economía del 
 * recurso en estudio.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Color
 * @see Font
 * @see KeyEvent
 * @see JPanel
 * @see JTextArea
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see JScrollPane
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameEconomia
 * @see EventoButton
 * @see Impresora
 * @see InterfacePanel
 * @see InterfaceReporte
 */
public class PanelReporteEconomia extends JPanel
	implements InterfacePanel, InterfaceReporte
{	
	/**
	 * Constante que maneja cada cuantos días se genera un dato para el
	 * resultado.
	 */
	public final static int SALTO_DIAS = 1;
	
	/** Frame que contiene a la lista de paneles. */
	public FrameEconomia frameEconomia;
	
	/** Memo donde se genera el informe del reporte. */
	private JTextArea informe;
	
	/** Botón que guardará el reporte. */
	private JButton botonGuardar;
	
	/** Botón que imprimirá el reporte por impresora. */
	private JButton botonImprimir;
	
	/**
	 * Objeto que maneja la impresora. Imprime el reporte generado por el módulo
	 * de economía.
	 */
	private Impresora impresora;
	
	/** Reporte generdo por una red BP. */
	private String reporteRedBP;
	
	/** Reporte generdo por una red RBF. */
	private String reporteRedRBF;
	
	/**
     * Constructor del panel reporte con el cual se configuran los eventos y los 
     * botones. Recibe como parámetro el frame creador del panel. 
     *
     * @param frameEconomia Creador del panel list que contiene al reporte.
     */
	public PanelReporteEconomia(FrameEconomia frameEconomia)
	{
		// Inicializar el apuntador.
	 	this.frameEconomia = frameEconomia;
	 	
	 	// Configurar.
	 	configurarElementosEspeciales();
	 	configurarPanel();
	 	configurarComponentes();
	 	configurarEventos();
	}
	
	/**
     * Método que configura los elementos elementos especiales que participan en 
     * el panel reporte economía.
     */
	private void configurarElementosEspeciales()
	{
		reporteRedBP = "";
		reporteRedRBF = "";
		impresora = new Impresora();
	}
	
	/**
	 * Método en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este método corresponden a los atributos
	 * que se derivan de la clase JPanel.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * Método que configura los componentes GUI del panel reporte.
     */
	public void configurarComponentes()
	{
		// Crear el panel.
		JPanel panel = new JPanel();
		Border borde = new LineBorder(Color.darkGray, 1);
		TitledBorder titulo = new TitledBorder(borde,
			" Reporte Generado ", TitledBorder.LEFT,
			TitledBorder.TOP);
		
		// Establecer propiedades al panel.
		panel.setBorder(titulo);
		panel.setLayout(null);
		panel.setBounds(10, 10, 800, 540);
		
		// Crear los componentes.
		informe = new JTextArea();
		JScrollPane barraInforme = new JScrollPane();
		botonGuardar = new JButton("Guardar",
			new ImageIcon("../img/proyecto_guardar.gif"));
		botonImprimir = new JButton("Imprimir",
			new ImageIcon("../img/imprimir.gif"));
		
		// Configurar el área de texto.
		informe.setFont(new Font("Courier New", Font.PLAIN, 12));
		informe.setEditable(false);
		informe.setAutoscrolls(true);
		
		// Configurar el scroll pane.
		barraInforme.setBounds(10, 20, 780, 470);
		barraInforme.
			setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		barraInforme.
			setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		barraInforme.setViewportView(informe);
		
		// Configurar los botones.
		botonGuardar.setBounds(290, 500, 120, 30);
		botonImprimir.setBounds(420, 500, 120, 30);
		botonGuardar.setMnemonic(KeyEvent.VK_G);
		botonImprimir.setMnemonic(KeyEvent.VK_I);
		botonGuardar.setToolTipText("Guardar el reporte generado");
		botonImprimir.setToolTipText("Imprimir el reporte generado");
		
		// Cargar el reporte del proyecto.
		cargarReporte();
		
		// Agregar los componentes al panel.
		panel.add(barraInforme);
		panel.add(botonGuardar);
		panel.add(botonImprimir);
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones de acceso
	 * rápido que tiene el panel de reporte. En particular se incorpora el 
	 * escuchador de eventos del tipo EventoButton a los JButton que tiene el
	 * panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos a los botones.
		botonGuardar.addActionListener(eventoButton);
		botonImprimir.addActionListener(eventoButton);
	}
	
	/**
	 * Método en donde se verifica si hay o no un reporte económico en el
	 * proyecto. En caso de haberlo, entonces se carga en el área de texto. En
	 * caso contrario, se deshabilitan los botones guardar e imprimir.
	 */
	public void cargarReporte()
	{
		// Obtener el reporte ecosistémico.
		String reporte = Proyecto.obtenerReporteEconomia();
		
		// Cuando hay reporte económico en el proyecto.
		if ((reporte != null) && (reporte.length() > 0))
		{
			informe.setText(reporte);
			habilitarBotones(true);
		}
		
		// Cuando no hay reporte económico en el proyecto.
		else
		{
			informe.setText("");
			habilitarBotones(false);
		}
	}
	
	/**
     * Método que genera en el reporte los años utilizados en el proceso de 
     * aprendizaje con sus respectivas cantidades de datos disponibles o
     * patrones por año.
     * 
     * @return informacion String que contiene los años y sus patrones asociados
     *					   utilizados en el proceso de aprendizaje.
     *						
     */
	private String generarReporteAnosAprendizaje()
	{
		int i, numeroPatronesTablaAnosEntrenadas , numeroFilas;
		Object entradas[][];
		String informacion = "1. Patrones Tomados para el Aprendizaje" + "\n" +
							 "---------------------------------------" + "\n";
		
		numeroPatronesTablaAnosEntrenadas = frameEconomia.panelEntrenarEconomia.
			obtenerNumeroPatronesTablaAnosEntrenadas();
		entradas =  frameEconomia.panelEntrenarEconomia.
			obtenerDatosTablaAnosEntrenadas();
		
		numeroFilas = entradas.length;
		
		informacion += "\n" + "Año" + "\t" + "Datos Disponibles" + "\n" +
					   "-------------------------" + "\n";
		
		for (i = 0; i < numeroFilas; i++)
		{
			informacion += "" + entradas[i][0] + "\t" +
						   entradas[i][1] + "\n";
		}
		
		informacion += "\n" + "Total Número de Patrones = " +
					   numeroPatronesTablaAnosEntrenadas;
		
		informacion += "\n" + "Total Datos Procesados por la Red = " +
					   frameEconomia.obtenerNumeroPatrones() + "\n";
		
		return informacion;
	}
	
	/**
     * Método que genera el reporte mensual de predicho. El resumen mensual
     * consta de un promedio del precio y demanda.
     * 
     * @return informacion String que contiene la información mensual de la 
     *					   predicción hecha.
     */
	private String generarReporteMensual()
	{
		String informacion = "";
		int i,j;
		int[] mesDia;
		int mes, anoInicial, anoFinal, contador, diaInicio, diaFinal;
		double sumaDemanda, sumaPrecio; 
		
		mes = 1;
		sumaDemanda = sumaPrecio = contador = 0;
		
		double[] entrada =
				new double[ConfiguracionNeuronalBP.obtenerNumeroEntradas()];
		double[] salida =
				new double[ConfiguracionNeuronalBP.obtenerNumeroSalidas()];
		
		informacion += "\n" +
					   "2. Resumen Mensual" + "\n" +
					   "------------------" + "\n";
		informacion += "\n" +
					   "Año\tMes\t\tDemanda\t\tPrecio" + "\n" +
					   "\t\t\t(toneladas)\t(US$/kilogramos)" + "\n" +
					   "-----------------------------------------------" +
					   "---------" + "\n";
		
		// marcando los años límites
		anoInicial = Proyecto.obtenerFechaInicialFormatoInt()[2];
		anoFinal = Proyecto.obtenerFechaFinalFormatoInt()[2];
		
		for (i = anoInicial; i <= anoFinal; i++)
		{
			diaInicio = 1;
			diaFinal = 365 + 1;
			
			if (i == anoInicial)
			{
				int[] diaMes = {Proyecto.obtenerFechaInicialFormatoInt()[0],
								Proyecto.obtenerFechaInicialFormatoInt()[1]};
				
				diaInicio = Servicio.obtenerDia(diaMes);
				mes = Proyecto.obtenerFechaInicialFormatoInt()[1];
			}
			
			if (i == anoFinal)
			{
				int[] diaMes = {Proyecto.obtenerFechaFinalFormatoInt()[0],
								Proyecto.obtenerFechaFinalFormatoInt()[1]};
				
				diaFinal = Servicio.obtenerDia(diaMes);
			}
			
			for (j = diaInicio; j <= diaFinal; j++)
			{
				entrada[0] = i;
				entrada[1] = j;
				
				salida = frameEconomia.ejecutarSalidaRed(entrada);
				contador++;
				
				mesDia = Servicio.obtenerMesDia(j);
				
				if (mesDia[0] != mes)
				{
					informacion += i + "\t" + mes + "\t\t";
					informacion += (int) ((sumaDemanda / contador) * 100) /100.0 + "\t\t";
					informacion += (int) ((sumaPrecio / contador) * 100) /100.0 + "\n";
					
					sumaDemanda = sumaPrecio = contador = 0;
					
					// cuando se termina el año y despues de diciembre no viene
					// un mes mas
					if (mesDia[1] == -1)
						mes = 1;
					else
						mes = mesDia[0];
				}
				
				sumaDemanda += salida[0];
				sumaPrecio += salida[1];
			}
		}	
		
		// agregando el último mes
		informacion += anoFinal + "\t" + mes + "\t\t";
		informacion += (int) ((sumaDemanda / contador) * 100) /100.0 + "\t\t";
		informacion += (int) ((sumaPrecio / contador) * 100) /100.0 + "\n";
		
		return informacion;
	}
	
	/**
     * Método que encuentra los precios máximos generados en la predicción.
     * 
     * @return informacion String que contiene el momento que genera los precios
     *			           máximos.
     */
	private String generarReportePrecioMaximo()
	{
		String informacion = "";
		int i,j;
		int[] mesDia;
		int mes, anoInicial, anoFinal, contador, diaInicio, diaFinal;
		double salidaAnterior1, salidaAnterior2;
		double salidaPosterior1, salidaPosterior2;
		double salidaActual;
		double deltaDiferencia = 0.00001;
		
		double[] entrada =
				new double[ConfiguracionNeuronalBP.obtenerNumeroEntradas()];
		double[] salida =
				new double[ConfiguracionNeuronalBP.obtenerNumeroSalidas()];
		
		anoInicial = Proyecto.obtenerFechaInicialFormatoInt()[2];
		anoFinal = Proyecto.obtenerFechaFinalFormatoInt()[2];
		
		// calculando los puntos altos de las curvas precio
		informacion += "\n" +
					   "3. Puntos de Mayor Precio" + "\n" +
					   "-------------------------" + "\n";
		informacion += "\n" +
					   "Año\tMes\tDía\t\tPrecio" + "\n" +
					   "\t\t\t\t(US$/kilogramos)" + "\n" +
					   "-----------------------------------------" +
					   "---------" + "\n";
		
		for (i = anoInicial; i <= anoFinal; i++)
		{
			diaInicio = 1;
			diaFinal = 365 + 1;
			
			if (i == anoInicial)
			{
				int[] diaMes = {Proyecto.obtenerFechaInicialFormatoInt()[0],
								Proyecto.obtenerFechaInicialFormatoInt()[1]};
				
				diaInicio = Servicio.obtenerDia(diaMes);
				mes = Proyecto.obtenerFechaInicialFormatoInt()[1];
			}
			
			if (i == anoFinal)
			{
				int[] diaMes = {Proyecto.obtenerFechaFinalFormatoInt()[0],
								Proyecto.obtenerFechaFinalFormatoInt()[1]};
				
				diaFinal = Servicio.obtenerDia(diaMes);
			}
			
			for (j = diaInicio; j <= diaFinal; j++)
			{
				entrada[0] = i;
				entrada[1] = j;
				salidaActual = frameEconomia.ejecutarSalidaRed(entrada)[1];
				
				entrada[0] = i;
				entrada[1] = j - 1;
				salidaAnterior1 = frameEconomia.ejecutarSalidaRed(entrada)[1];
				
				entrada[0] = i;
				entrada[1] = j - 2;
				salidaAnterior2 = frameEconomia.ejecutarSalidaRed(entrada)[1];
				
				entrada[0] = i;
				entrada[1] = j + 1;
				salidaPosterior1 = frameEconomia.ejecutarSalidaRed(entrada)[1];
				
				entrada[0] = i;
				entrada[1] = j + 2;
				salidaPosterior2 = frameEconomia.ejecutarSalidaRed(entrada)[1];
				
				if ((salidaActual - salidaAnterior1) > deltaDiferencia &&
				 	(salidaAnterior1 - salidaAnterior2) > deltaDiferencia &&
				 	(salidaActual - salidaPosterior1) > deltaDiferencia &&
				 	(salidaPosterior1 - salidaPosterior2) > deltaDiferencia)
				{
					mesDia = Servicio.obtenerMesDia(j);
					informacion += i + "\t" + mesDia[0] + "\t" + mesDia[1] + "\t\t";
					informacion += (int) (salidaActual * 1000) /1000.0 + "\n";
				}
			}
		}
		
		return informacion;
	}
	
	/**
     * Método que encuentra las demanda máxima generada en la predicción.
     * 
     * @return informacion String que contiene el momento que genera las
     *                     demandas máximas.
     */
	private String generarReporteDemandaMaxima()
	{
		String informacion = "";
		int i,j;
		int[] mesDia;
		int mes, anoInicial, anoFinal, contador, diaInicio, diaFinal;
		double salidaAnterior1, salidaAnterior2;
		double salidaPosterior1, salidaPosterior2;
		double salidaActual;
		double deltaDiferencia = 0.00001;
		
		double[] entrada =
				new double[ConfiguracionNeuronalBP.obtenerNumeroEntradas()];
		double[] salida =
				new double[ConfiguracionNeuronalBP.obtenerNumeroSalidas()];
		
		anoInicial = Proyecto.obtenerFechaInicialFormatoInt()[2];
		anoFinal = Proyecto.obtenerFechaFinalFormatoInt()[2];
		
		// calculando los puntos altos de las curvas demanda
		informacion += "\n" +
					   "4. Puntos de Mayor Demanda" + "\n" +
					   "--------------------------" + "\n";
		informacion += "\n" +
					   "Año\tMes\tDía\t\tDemanda" + "\n" +
					   "\t\t\t\t(toneladas)" + "\n" +
					   "-----------------------------------------" +
					   "---------" + "\n";
		for (i = anoInicial; i <= anoFinal; i++)
		{
			diaInicio = 1;
			diaFinal = 365 + 1;
			
			if (i == anoInicial)
			{
				int[] diaMes = {Proyecto.obtenerFechaInicialFormatoInt()[0],
								Proyecto.obtenerFechaInicialFormatoInt()[1]};
				
				diaInicio = Servicio.obtenerDia(diaMes);
				mes = Proyecto.obtenerFechaInicialFormatoInt()[1];
			}
			
			if (i == anoFinal)
			{
				int[] diaMes = {Proyecto.obtenerFechaFinalFormatoInt()[0],
								Proyecto.obtenerFechaFinalFormatoInt()[1]};
				
				diaFinal = Servicio.obtenerDia(diaMes);
			}
			
			for (j = diaInicio; j <= diaFinal; j++)
			{
				entrada[0] = i;
				entrada[1] = j;
				salidaActual = frameEconomia.ejecutarSalidaRed(entrada)[0];
				
				entrada[0] = i;
				entrada[1] = j - 1;
				salidaAnterior1 = frameEconomia.ejecutarSalidaRed(entrada)[0];
				
				entrada[0] = i;
				entrada[1] = j - 2;
				salidaAnterior2 = frameEconomia.ejecutarSalidaRed(entrada)[0];
				
				entrada[0] = i;
				entrada[1] = j + 1;
				salidaPosterior1 = frameEconomia.ejecutarSalidaRed(entrada)[0];
				
				entrada[0] = i;
				entrada[1] = j + 2;
				salidaPosterior2 = frameEconomia.ejecutarSalidaRed(entrada)[0];
				
				if ((salidaActual - salidaAnterior1) > deltaDiferencia &&
				 	(salidaAnterior1 - salidaAnterior2) > deltaDiferencia &&
				 	(salidaActual - salidaPosterior1) > deltaDiferencia &&
				 	(salidaPosterior1 - salidaPosterior2) > deltaDiferencia)
				{
					mesDia = Servicio.obtenerMesDia(j);
					informacion += i + "\t" + mesDia[0] + "\t" + mesDia[1] + "\t\t";
					informacion += (int) (salidaActual * 1000) /1000.0 + "\n";
				}
			}
		}
		
		return informacion;
	}
	
	/**
     * Método que genera el reporte relacionado con los errores de aprendizajes
     * y test.
     *
     * @return informacion String que contiene la información de la
     *                     confiabilidad del aprendizaje producido por una red
     *                     neuronal.
     */
	private String generarReporteRedNeuronal()
	{
		String informacion = "";
		double porcentanjeConfiabilidad;
		
		porcentanjeConfiabilidad =
			(int)(frameEconomia.obtenerConfiabilidadAprendizaje() * 1000)/10.0;
		
		informacion += "\n" +
					   "5. Información de la Red Neuronal Artificial" + "\n" +
					   "--------------------------------------------" + "\n";
		informacion += "\n" + "Confiabilidad del Aprendizaje = " +
				 	   porcentanjeConfiabilidad + "%" + "\n" + "\n";
		
		informacion += "Nota: Tener presente que la cercanía o alejamiento\n" +
					   "entre los años de aprendizaje y predicción afecta\n" +
					   "considerablemente a la predicción." + "\n";
		
		return informacion;
	}
	
	/**
     * Método que agrega la información al reporte generado por la predicción
     * de la red neuronal.
     * 
     * @param informacion Contiene la información generada por la predicción de
     *					  la red neuronal.
     * @param eleccionRed Indica la red neuronal.
     */
	private void agregarReporte(String informacion,int eleccionRed)
	{
		switch (eleccionRed)
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    		reporteRedBP = informacion;
	    		break;
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    		reporteRedRBF = informacion;
	    		break;
	    }
	    
	    informe.setText(informacion);
	    habilitarBotones(true);
	}
	
	/**
     * Método que genera y muestra el reporte sobre la predicción lograda.
     */
	public void mostrarReporte()
	{
		String informacion = "";
		
		informacion += "REPORTE ECONOMIA\n"+
					   "==================\n\n";
		
		informacion += generarReporteAnosAprendizaje();
		informacion += generarReporteMensual();
		informacion += generarReportePrecioMaximo();
		informacion += generarReporteDemandaMaxima();
		informacion += generarReporteRedNeuronal();
		
		agregarReporte(informacion, frameEconomia.obtenerEleccionRed());
	}
	
	/**
     * Método que elimina el reporte que se encuentra escrito en el área de
     * texto del reporte.
     */
	public void limpiarReporte()
	{
		switch (frameEconomia.obtenerEleccionRed())
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    		reporteRedBP = "";
	    		break;
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    		reporteRedRBF = "";
	    		break;
	    }
	    
	    informe.setText("");
		habilitarBotones(false);
	}
	
	/**
     * Método que elimina el reporte que se encuentra escrito en el área de
     * texto del reporte.
     *
     * @param eleccionRed El tipo de red neuronal.
     */
	public void limpiarReporte(int eleccionRed)
	{
		switch (eleccionRed)
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    		reporteRedBP = "";
	    		break;
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    		reporteRedRBF = "";
	    		break;
	    }
	    
	    informe.setText("");
	    habilitarBotones(false);
	}
	
	/**
     * Método que guarda en la base de datos el reporte generado sobre la
     * predicción lograda.
     */
	public void guardarReporte()
	{
		BaseDatoMotor conexion = new BaseDatoMotor();
		String sql;
		int i;
		int j;
		int[] mesDia;
		int mes;
		int anoInicial;
		int anoFinal;
		int contador;
		int diaInicio;
		int diaFinal;
		int codigoProyecto;
		int codigoRegion;
		int codigoTipoRed;
		int codigoRecurso;
		
		codigoProyecto = Proyecto.obtenerCodigo();
		codigoRegion = Proyecto.obtenerCodigoRegion();
		codigoRecurso = Proyecto.obtenerCodigoRecurso();
		codigoTipoRed = frameEconomia.obtenerEleccionRed();
		
		double[] entrada =
				new double[ConfiguracionNeuronalBP.obtenerNumeroEntradas()];
		double[] salida =
				new double[ConfiguracionNeuronalBP.obtenerNumeroSalidas()];
		
		// marcando los años límites
		anoInicial = Proyecto.obtenerFechaInicialFormatoInt()[2];
		anoFinal = Proyecto.obtenerFechaFinalFormatoInt()[2];
		
		Proyecto.limpiarResultados(Proyecto.ECONOMIA, codigoTipoRed);
		
		// conectando a la base dato
		conexion.conectar();
		
		for (i = anoInicial; i <= anoFinal; i++)
		{
			diaInicio = 1;
			diaFinal = 365;
			
			if (i == anoInicial)
			{
				int[] diaMes = {Proyecto.obtenerFechaInicialFormatoInt()[0],
								Proyecto.obtenerFechaInicialFormatoInt()[1]};
				
				diaInicio = Servicio.obtenerDia(diaMes);
			}
			
			if (i == anoFinal)
			{
				int[] diaMes = {Proyecto.obtenerFechaFinalFormatoInt()[0],
								Proyecto.obtenerFechaFinalFormatoInt()[1]};
				
				diaFinal = Servicio.obtenerDia(diaMes);
			}
			
			for (j = diaInicio; j <= diaFinal; j += SALTO_DIAS)
			{
				entrada[0] = i;
				entrada[1] = j;
				
				salida = frameEconomia.ejecutarSalidaRed(entrada);
				
				sql =
					"INSERT INTO dato_economico_resultado (codigo_recurso, " +
					"codigo_region, codigo_anio, codigo_dia, codigo_proyecto," +
					"codigo_tipo_red_neuronal, precio_dato_economico_resultado," +
					"demanda_dato_economico_resultado) " +
					" VALUES (" + codigoRecurso + "," + codigoRegion + "," +
					i + "," +  j + "," +
					Proyecto.obtenerCodigo() + "," +
					codigoTipoRed + "," +
					Servicio.obtenerNumeroAproximado(salida[1],3) + "," +
					Servicio.obtenerNumeroAproximado(salida[0],3) + ")";
				
				conexion.ejecutarConsultaActualizacion(sql);
			}
		}
		
		// Guardar el reporte del proyecto.
		Proyecto.establecerReporteEconomia(informe.getText());
		Proyecto.establecerModificado(true);
		
		// Desconectar a la base de datos.
		conexion.desconectar();
	}
	
	/**
     * Método que imprime el reporte generado por el módulo economía.
     */
	public void imprimirReporte()
	{
		impresora.imprimirReporte(informe);
	}
	
	/**
     * Método que cambia el reporte. Este se cambia según la red elegida.
     * 
     * @param eleccionRed Indica la red neuronal.
     */
    public void seleccionarReporte(int eleccionRed)
	{
		String informacion = "";
		
		switch (eleccionRed) 
		{
	    	case FrameEconomia.RED_NEURONAL_BP:
	    		informacion = reporteRedBP;
	    		break;
	    	
	    	case FrameEconomia.RED_NEURONAL_RBF:
	    		informacion = reporteRedRBF;
	    		break;
	    }
	    
	    if (informacion == "")
	    	habilitarBotones(false);
	    else habilitarBotones(true);
	    
	    informe.setText(informacion);
	}
	
	/**
	 * Método donde se maneja la habilitación o deshabilitación de los botones
	 * para guardar e imprimir el reporte.
	 *
	 * @param habilitador Un booleano que indica si se habilita o no los botones
	 *                    guardar e imprimir.
	 */
	public void habilitarBotones(boolean habilitador)
	{
       	botonGuardar.setEnabled(habilitador);
       	botonImprimir.setEnabled(habilitador);
	}
	
	/**
	 * Método que obtiene el objeto botonGuardar.
	 *
	 * @return botonGuardar El botón guardar del panel reporte.
	 */
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
	}
	
	/**
	 * Método que obtiene el objeto botonImprimir.
	 *
	 * @return botonImprimir El botón impirmir del panel reporte.
	 */
	public JButton obtenerBotonImprimir()
	{
		return botonImprimir;
	}
}