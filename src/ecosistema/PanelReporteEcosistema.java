/**
 * @(#)PanelReporteEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario un
 * reporte de las diversas variables que influyen en los recursos del
 * ecosistema. El reporte consiste en un informe generado desde la dinámica
 * espacial, la dinámica temporal, las cuotas de captura y los caladeros de
 * captura.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ResultSet
 * @see SQLException
 * @see Color
 * @see Font
 * @see KeyEvent
 * @see JPanel
 * @see JTextArea
 * @see JScrollPane
 * @see JButton
 * @see JLabel
 * @see ImageIcon
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see FrameEcosistema
 * @see Impresora
 * @see EventoButton
 * @see InterfacePanel
 * @see InterfaceReporte
 */
public class PanelReporteEcosistema extends JPanel
	implements InterfacePanel, InterfaceReporte
{
	/** El frame que contiene a este panel. */
	public FrameEcosistema frameEcosistema;
	
	/** El área de texto en donde se genera el reporte. */
	private JTextArea textoReporte;
	
	/** El botón para guardar los resultados. */
	private JButton botonGuardar;
	
	/** El botón para imprimir el reporte. */
	private JButton botonImprimir;
		
	/** El objeto que maneja la impresión del reporte. */
	private Impresora impresora;
	
	/**
     * Cosntructor del panel con el cual se configuran los eventos y los botones
     * del panel. Recibe como parámetro el frame que contiene a este panel.
	 *
	 * @param frameEcosistema Un objeto que hace referencia al FrameEcosistema
	 *                        que contiene a este panel.
	 */
	public PanelReporteEcosistema(FrameEcosistema frameEcosistema)
	{
		// Apuntar el frame contenedor de este panel.
		this.frameEcosistema = frameEcosistema;
		
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
	private void configurarElementosEspeciales()
	{
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
	 * Método en donde se configuran los componentes que contiene este frame.
	 * Este frame contiene principalmente dos botones y un área de texto.
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
		textoReporte = new JTextArea();
		JScrollPane barraReporte = new JScrollPane(textoReporte);
		botonGuardar = new JButton("Guardar",
					   new ImageIcon("../img/proyecto_guardar.gif"));
		botonImprimir = new JButton("Imprimir",
					    new ImageIcon("../img/imprimir.gif"));
		
		// Configurar el área de texto.
		textoReporte.setFont(new Font("Courier New", Font.PLAIN, 12));
		textoReporte.setAutoscrolls(true);
		textoReporte.setEditable(false);
		
		// Configurar el scroll pane.
		barraReporte.setBounds(10, 20, 780, 470);
		barraReporte.
			setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		barraReporte.
			setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		barraReporte.setViewportView(textoReporte);
		
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
		panel.add(barraReporte);
		panel.add(botonGuardar);
		panel.add(botonImprimir);
		
		// Agregar el panel.
		add(panel);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los botones que tiene el
	 * panel de reporte. En particular se incorpora el escuchador de eventos del
	 * tipo EventoBoton a los JButton que tiene este panel.
	 */
	public void configurarEventos()
	{
		// Crear el escuchador de eventos.
		EventoButton eventoButton = new EventoButton(this);
		
		// Incorporar el escuchador de eventos al botón.
		botonGuardar.addActionListener(eventoButton);
		botonImprimir.addActionListener(eventoButton);
	}
	
	/**
	 * Método en donde se verifica si hay o no un reporte ecosistémico en el
	 * proyecto. En caso de haberlo, entonces se carga en el área de texto. En
	 * caso contrario, se deshabilitan los botones guardar e imprimir.
	 */
	public void cargarReporte()
	{
		// Obtener el reporte ecosistémico.
		String reporte = Proyecto.obtenerReporteEcosistema();
		
		// Cuando hay reporte ecosistémico en el proyecto.
		if ((reporte != null) && (reporte.length() > 0))
		{
			textoReporte.setText(reporte);
			habilitarBotones(true);
		}
		
		// Cuando no hay reporte ecosistémico en el proyecto.
		else
		{
			textoReporte.setText("");
			habilitarBotones(false);
		}
	}
	
	/**
	 * Método que muestra el reporte completo que se ha generado. El reporte
	 * completo consiste en el reporte generado desde la dinámica espacial, más
	 * el reporte generado desde la dinámica temporal, más el reporte generado
	 * desde las cuotas de captura y más el reporte generado desde los
	 * caladeros de captura. El reporte completo es mostrado en el área de
	 * texto.
	 */
	public void mostrarReporte()
	{
		// Variables locales.
		int punto;
		int dd;
		int mm;
		int aa;
		int mes;
		double cuotaAnual = 0;
		double cuotaAnualArtesanal = 0;
		double cuotaAnualIndustrial = 0;
		double[] cuotaMensual = new double[12];
		double[] cuotaMensualArtesanal = new double[12];
		double[] cuotaMensualIndustrial = new double[12];
		String fecha;
		String biomasa;
		String abundancia;
		String calidad;
		
		// String para almacenar el reporte.
		String reporte = "REPORTE ECOSISTEMA\n"+
						 "==================\n\n";
		
		// Obtener la estadística.
		Estadistica estadistica = frameEcosistema.
								  obtenerAutomataCelular().
								  obtenerEstadistica();
		
		// Obtener la cantidad de recursos y tiempos de la estadística.
		int cantidadRecursos = estadistica.obtenerCantidadRecursos();
		int cantidadTiempos = estadistica.obtenerCantidadTiempos();
		
		// Crear un tiempo.
		Tiempo tiempo = new Tiempo();
		
		// Ciclo para recorrer los recursos.
		for (int recurso=0; recurso<cantidadRecursos; recurso++)
		{
			// Incorporar el nombre del recurso al reporte.
			punto = recurso + 1;
			reporte+= punto+". "+
			Recurso.obtenerNombreComun(recurso)+"\n"+
					  "--------------------\n\n";
			
			// Iniciar las variables.
			cuotaAnual = 0;
			for (mes=0; mes<12; mes++)
				cuotaMensual[mes] = 0;
			tiempo.iniciar();
			
			// Incorporar el item 1 al reporte.
			reporte+= punto+".1. Evaluación del Recurso:\n\n"+
					  "Fecha                    \t"+
					  "Biomasa                  \t"+
					  "Abundancia               \t"+
					  "Calidad                  \n"+
					  "(día/mes/año)            \t"+
					  "(toneladas)              \t"+
					  "(unidades)               \t"+
					  "([0;1])                  \n"+
					  "-----------------------------------------------------"+
					  "-----------------------------------------------------\n";
			
			// Ciclo para recorrer los tiempos.
			for (int t=0; t<cantidadTiempos; t++)
			{
				// Obtener los indicadores.
				fecha = Servicio.obtenerFecha(
					tiempo.obtenerFechaActual());
				biomasa = Servicio.obtenerNumeroFormateado(
					estadistica.obtenerBiomasa(recurso, t), 2);
				abundancia = Servicio.obtenerNumeroFormateado(
					estadistica.obtenerAbundancia(recurso, t), 0);
				calidad = Servicio.obtenerNumeroFormateado(
					estadistica.obtenerCalidadPromedio(recurso, t), 2);
				
				// Incorporar los indicadores.
				reporte+= Servicio.espaciar(fecha, 25)+"\t"+
						  Servicio.espaciar(biomasa, 25)+"\t"+
						  Servicio.espaciar(abundancia, 25)+"\t"+
						  Servicio.espaciar(calidad, 25)+"\n";
				
				// Obtener el día, el mes y el año, en formato dd/mm/aa.
				dd = tiempo.obtenerFechaActual()[0];
				mm = tiempo.obtenerFechaActual()[1];
				aa = tiempo.obtenerFechaActual()[2];
				
				// Incrementar la cuota mensual.
				cuotaMensual[mm-1]+=
					estadistica.obtenerCuota(recurso, t);
				cuotaMensualArtesanal[mm-1]+=
					estadistica.obtenerCuotaArtesanal(recurso, t);
				cuotaMensualIndustrial[mm-1]+=
					estadistica.obtenerCuotaIndustrial(recurso, t);
				
				// Incrementar el tiempo.
				tiempo.incrementar();
			}
			
			// Ciclo para calcular la cuota anual, artesanal e industrial.
			for (mes=0; mes<12; mes++)
			{
				cuotaAnual+= cuotaMensual[mes];
				cuotaAnualArtesanal+= cuotaMensualArtesanal[mes];
				cuotaAnualIndustrial+= cuotaMensualIndustrial[mes];
			}
			
			// Incorporar el item 2 al reporte.
			reporte+= "\n"+punto+".2. Cuota de Captura:\n\n"+
					  "Area de Unidad de Pesquería: "+
					  Proyecto.obtenerNombreRegion()+"\n"+
					  "Cuota Global Anual de Captura: "+
					  Servicio.obtenerNumeroFormateado(cuotaAnual, 2)+
					  " ton/año\n";
			
			// Incorporar las cuotas mensuales artesanales.
			reporte+= " |-- Flota Artesanal\t: "+
				Servicio.obtenerNumeroFormateado(cuotaAnualArtesanal, 2)+
				" ton/año\n";
			for (mes=1; mes<=12; mes++)
				reporte+= "    |-- "+
					Servicio.obtenerNombreMes(mes)+
					"    \t: "+
					Servicio.obtenerNumeroFormateado(cuotaMensualArtesanal[mes-1], 2)+
					" ton/mes\n";
			
			// Incorporar las cuotas mensuales industriales.
			reporte+= " |-- Fota Industrial\t: "+
				Servicio.obtenerNumeroFormateado(cuotaAnualIndustrial, 2)+
				" ton/año\n";
			for (mes=1; mes<=12; mes++)
				reporte+= "    |-- "+
					Servicio.obtenerNombreMes(mes)+
					"    \t: "+
					Servicio.obtenerNumeroFormateado(cuotaMensualIndustrial[mes-1], 2)+
					" ton/mes\n";
			
			reporte+= "\n";
			
			// Limpiar los arreglos.
			for (mes=0; mes<12; mes++)
			{
				cuotaMensual[mes] = 0;
				cuotaMensualArtesanal[mes] = 0;
				cuotaMensualIndustrial[mes] = 0;
			}
			
			// Limpiar las cuotas.
			cuotaAnual = 0;
			cuotaAnualArtesanal = 0;
			cuotaAnualIndustrial = 0;
		}
		
		// Mostrar el reporte.
		textoReporte.setText(reporte);
		
		// Habilitar los botones.
		habilitarBotones(true);
	}
	
	/**
	 * Método que limpia el reporte generado y mostrado en pantalla.
	 */
	public void limpiarReporte()
	{
		textoReporte.setText("");
		habilitarBotones(false);
	}
	
	/**
	 * Método en donde se guardan los resultados generados en la base de datos.
	 * Si existen resultados antiguos de la simulación, entonces son borrados.
	 * Luego, se insertan los resultados en la tabla
	 * dato_ecosistemico_resultado y caladero_resultado.
	 */
	public void guardarReporte()
	{
		// Variables locales.
		int cantidadCaladeros;
		int codigoCaladero = 0;
		int recurso = Proyecto.obtenerCodigoRecurso();
		String consulta;
		Caladero caladero;
		
		// Obtener la estadística.
		Estadistica estadistica = frameEcosistema.
								  obtenerAutomataCelular().
								  obtenerEstadistica();
		
		// Obtener la cantidad de recursos y tiempos de la estadística.
		int cantidadRecursos = estadistica.obtenerCantidadRecursos();
		int cantidadTiempos = estadistica.obtenerCantidadTiempos();
		
		// Crear e iniciar el tiempo.
		Tiempo tiempo = new Tiempo();
		tiempo.iniciar();
		
		// Limpiar los resultados anteriores.
		Proyecto.limpiarResultados(Proyecto.ECOSISTEMA, -1);
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Formar la consulta.
		consulta = "SELECT codigo_caladero_resultado "+
				   "FROM caladero_resultado "+
				   "ORDER BY codigo_caladero_resultado DESC";
		ResultSet resultado = Proyecto.CONEXION.ejecutarConsulta(consulta);
		
		// Obtener el caladero con mayor código.
		try
		{
			resultado.first();
			codigoCaladero = resultado.getInt("codigo_caladero_resultado") + 1;
		}
		
		// Capturar la excepción.
		catch (SQLException exepcion)
		{
		}
		
		// Ciclo para recorrer los tiempos.
		for (int t=0; t<cantidadTiempos; t++)
		{
			// Cuando la cuota es mayor que cero.
			if (estadistica.obtenerCuota(recurso, t) > 0)
			{
				// Formar la consulta.
				consulta =
					"INSERT INTO dato_ecosistemico_resultado "+
					"(codigo_recurso, codigo_region, codigo_anio, codigo_dia,"+
					" codigo_proyecto, biomasa_dato_ecosistemico_resultado)"+
					" VALUES ("+recurso+", "+
					Proyecto.obtenerCodigoRegion()+", "+
					tiempo.obtenerFechaActual()[2]+", "+
					tiempo.obtenerTiempoActual()+", "+
					Proyecto.obtenerCodigo()+", "+
					estadistica.obtenerCuota(recurso, t)+")";
				
				// Ejecutar la consulta.
				Proyecto.CONEXION.ejecutarConsultaActualizacion(consulta);
				
				// Obtener el vector de caladeros.
				VectorCaladero vectorCaladero =
					estadistica.obtenerCaladero(recurso, t);
				
				// Ciclo para insertar los caladeros.
				cantidadCaladeros = vectorCaladero.size();
				for (int c=0; c<cantidadCaladeros; c++)
				{
					// Obtener el caladero c-ésimo.
					caladero = vectorCaladero.obtenerElemento(c);
					
					// Formar la consulta.
					consulta =
						"INSERT INTO caladero_resultado VALUES ("+
						codigoCaladero+", "+
						recurso+", "+
						Proyecto.obtenerCodigoRegion()+", "+
						tiempo.obtenerFechaActual()[2]+", "+
						tiempo.obtenerTiempoActual()+", "+
						Proyecto.obtenerCodigo()+", "+
						"1, 1, 1, 1, "+
						caladero.obtenerTipo()+", "+
						caladero.obtenerCoordenadaInicial().obtenerLongitud()+", "+
						caladero.obtenerCoordenadaFinal().obtenerLongitud()+", "+
						caladero.obtenerCoordenadaInicial().obtenerLatitud()+", "+
						caladero.obtenerCoordenadaFinal().obtenerLatitud()+", "+
						caladero.obtenerBiomasa()+")";
					
					// Ejecutar la consulta.
					Proyecto.CONEXION.ejecutarConsultaActualizacion(consulta);
					
					// Incrementar el código del caladero.
					codigoCaladero++;
				}
			}
			
			// Incrementar el tiempo.
			tiempo.incrementar();
		}
		
		// Guardar el reporte del proyecto.
		Proyecto.establecerReporteEcosistema(textoReporte.getText());
		Proyecto.establecerModificado(true);
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que imprime el reporte generado por el módulo ecosistema. Se llama
     * al método del objeto impresora que imprime el contenido del área de texto
     * textoReporte.
     */
	public void imprimirReporte()
	{
		impresora.imprimirReporte(textoReporte);
	}
	
	/**
	 * Método que habilita o deshabilita los botones guardar e imprimir.
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
	 * Método que obtiene el valor del atributo botonGuardar.
	 *
	 * @return botonGuardar El valor del atributo botonGuardar.
	 */
	public JButton obtenerBotonGuardar()
	{
		return botonGuardar;
	}
	
	/**
	 * Método que obtiene el valor del atributo botonImprimir.
	 *
	 * @return botonImprimir El valor del atributo botonImprimir.
	 */
	public JButton obtenerBotonImprimir()
	{
		return botonImprimir;
	}
}