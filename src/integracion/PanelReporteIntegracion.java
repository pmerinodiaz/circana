/**
 * @(#)PanelReporteIntegracion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.TreeSet;
import java.util.Iterator;
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
 * reporte de la planificación de ventas del recurso en estudio generada en la
 * integración.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see TreeSet
 * @see Iterator
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
public class PanelReporteIntegracion extends JPanel
	implements InterfacePanel, InterfaceReporte
{
	/** El frame que contiene a este panel. */
	public FrameIntegracion frameIntegracion;
	
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
	 * @param frameIntegracion Un objeto que hace referencia al FrameIntegracion
	 *                         que contiene a este panel.
	 */
	public PanelReporteIntegracion(FrameIntegracion frameIntegracion)
	{
		// Apuntar el frame contenedor de este panel.
		this.frameIntegracion = frameIntegracion;
		
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
		textoReporte.setEditable(false);
		textoReporte.setAutoscrolls(true);
		
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
	 * Método en donde se verifica si hay o no un reporte integrado en el
	 * proyecto. En caso de haberlo, entonces se carga en el área de texto. En
	 * caso contrario, se deshabilitan los botones guardar e imprimir.
	 */
	public void cargarReporte()
	{
		// Obtener el reporte ecosistémico.
		String reporte = Proyecto.obtenerReporteIntegracion();
		
		// Cuando hay reporte integrado en el proyecto.
		if ((reporte != null) && (reporte.length() > 0))
		{
			textoReporte.setText(reporte);
			habilitarBotones(true);
		}
		
		// Cuando no hay reporte integrado en el proyecto.
		else
		{
			textoReporte.setText("");
			habilitarBotones(false);
		}
	}
	
	/**
	 * Método que muestra el reporte completo que se ha generado. El reporte
	 * completo consiste en el reporte generado desde la planificación de ventas
	 * del recurso en estudio. El reporte completo es mostrado en el área de
	 * texto.
	 */
	public void mostrarReporte()
	{
		// Variables locales.
		TreeSet resultadosVenta;
		String reporte;
		Venta venta;
		int[] mesDia = new int[2];
		double ingreso = 0;
		
		// Obtener el resultado de ventas y ordenarlo en forma ascendente.
		resultadosVenta = new TreeSet(frameIntegracion.obtenerResultadosVenta());
		
		// String para almacenar el reporte.
		reporte = "REPORTE INTEGRACION\n" +
				  "===================\n\n" +
				  "1. Planificación de Ventas:\n\n" +
				  "Fecha de Venta  \t" +
				  "Oferta          \t" +
				  "Demanda         \t" +
				  "Precio          \t" +
				  "Venta           \n" +
				  "(día/mes/año)   \t" +
				  "(toneladas)     \t" +
				  "(toneladas)     \t" +
				  "(US$/kilogramos)\t" +
				  "(toneladas)     \n" +
				  "-----------------------------------------------------" +
				  "-----------------------------------------------------\n";
		
		// Obtener el iterador y si hay próximo.
		Iterator i = resultadosVenta.iterator();
		boolean hasNext = i.hasNext();
        
        // Ciclo para recorrer la planificación de ventas.
		while (hasNext)
		{
			// Obtener la venta i-ésima.
			venta = (Venta) i.next();
			
			// Obtener el mes y el día en formato calendario.
			mesDia = Servicio.obtenerMesDia(venta.obtenerDia());
			
			// Incorporar la oferta, demanda, precio y venta.
			reporte +=
				Servicio.espaciar(
					mesDia[1] + "/" + mesDia[0] + "/" + venta.obtenerAnio(),
					16) + "\t" +
				Servicio.espaciar(
					Servicio.obtenerNumeroFormateado(venta.obtenerOferta(), 4),
					16) + "\t" +
				Servicio.espaciar(
					Servicio.obtenerNumeroFormateado(venta.obtenerDemanda(), 4),
					16) + "\t" +
				Servicio.espaciar(
					Servicio.obtenerNumeroFormateado(venta.obtenerPrecio(), 4),
					16) + "\t" +
				Servicio.espaciar(
					Servicio.obtenerNumeroFormateado(venta.obtenerVenta(), 4),
					16) + "\n";
			
			// Calcular el ingreso.
			ingreso +=
				venta.obtenerPrecio() *
				Servicio.transformarToneladaKilogramo(venta.obtenerVenta());
			
			// Mover a la próxima posición.
			hasNext = i.hasNext();
		}
		
		// Incorporar el ingreso total.
		reporte += "\n" +
				   "2. Ingreso Total:\n\n" +
				   "Ingreso Total Bruto = US$ " +
				   Servicio.obtenerNumeroFormateado(ingreso, 4) + "\n";
		
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
	 * Si existen resultados antiguos de la planificación, entonces son
	 * borrados. Luego, se insertan los resultados en la tabla
	 * dato_integrado_resultado.
	 */
	public void guardarReporte()
	{
		// Variables locales.
		TreeSet resultadosVenta;
		Venta venta;
		String consulta;
		
		// Limpiar los resultados anteriores.
		Proyecto.limpiarResultados(Proyecto.INTEGRACION, -1);
		
		// Conectar a la base de datos.
		Proyecto.CONEXION.conectar();
		
		// Obtener el resultado de ventas y ordenarlo en forma ascendente.
		resultadosVenta = new TreeSet(frameIntegracion.obtenerResultadosVenta());
		
		// Obtener el iterador y si hay próximo.
		Iterator i = resultadosVenta.iterator();
		boolean hasNext = i.hasNext();
        
        // Ciclo para recorrer la planificación de ventas.
		while (hasNext)
		{
			// Obtener la venta i-ésima.
			venta = (Venta) i.next();
			
			// Formar la consulta.
			consulta =
				"INSERT INTO dato_integrado_resultado " +
				"(codigo_recurso," +
				" codigo_region," +
				" codigo_anio," +
				" codigo_dia," +
				" codigo_proyecto," +
				" oferta_dato_integrado_resultado," +
				" demanda_dato_integrado_resultado," +
				" precio_dato_integrado_resultado," +
				" venta_dato_integrado_resultado)"+
				" VALUES (" +
				Proyecto.obtenerCodigoRecurso() + ", " +
				Proyecto.obtenerCodigoRegion() + ", " +
				venta.obtenerAnio() + ", " +
				venta.obtenerDia() + ", " +
				Proyecto.obtenerCodigo() + ", " +
				venta.obtenerOferta() + ", " +
				venta.obtenerDemanda() + ", " +
				venta.obtenerPrecio() + ", " +
				venta.obtenerVenta() + ")";
			
			// Ejecutar la consulta.
			Proyecto.CONEXION.ejecutarConsultaActualizacion(consulta);
			
			// Mover a la próxima posición.
			hasNext = i.hasNext();
		}
		
		// Guardar el reporte del proyecto.
		Proyecto.establecerReporteIntegracion(textoReporte.getText());
		Proyecto.establecerModificado(true);
		
		// Desconectar a la base de datos.
		Proyecto.CONEXION.desconectar();
	}
	
	/**
     * Método que imprime el reporte generado por el módulo integración. Se
     * llama al método del objeto impresora que imprime el contenido del área de
     * texto textoReporte.
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