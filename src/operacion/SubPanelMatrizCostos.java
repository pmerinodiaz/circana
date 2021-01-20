/**
 * @(#)SubPanelMatrizCostos.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase permite configurar
 * la matriz de costos del problema de transporte. 
 * 
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Color
 * @see GridLayout
 * @see GridLayout
 * @see Dimension
 * @see DecimalFormat
 * @see JPanel
 * @see JLabel
 * @see ImageIcon
 * @see JScrollPane
 * @see JOptionPane
 * @see TableColumn
 * @see DefaultTableCellRenderer
 * @see Border
 * @see LineBorder
 * @see TitleBorder
 * @see PanelTransportarOperacion
 * @see InterfacePanel
 */
public class SubPanelMatrizCostos extends JPanel implements InterfacePanel
{
	/** Panel que hace referencia al creador de este objeto. */
	public PanelTransportarOperacion panelTransportarOperacion;
	
	/** Tablas para submatrices. */
	private TablaSwing matriz[];
	
	/** Panel para matriz de costos. */
	private JPanel panelCostos;	
	
	/** Panel para una submatriz de costos de un medio de transporte. */
	JPanel panelMatriz;
	
	/** Texto para panel de origen. */
	private JLabel textoOrigen;
	
	/** Texto para panel de destino. */
	private JLabel textoDestino;
	
	/** Texto para panel de medio de transporte. */
	private JLabel textoMedio;
	
	/**
     * Método constructor del panel matriz de costos.
     *
     * @param panelTransportarOperacion El panel creador de este panel.
     * @param x Inicio X del sub-panel.
     * @param y Inicio Y del sub-panel.
     * @param ancho Ancho del sub-panel.
     * @param largo Largo del sub-panel.
     */
	public SubPanelMatrizCostos(
							PanelTransportarOperacion panelTransportarOperacion,
							int x, int y, int ancho, int largo)
	{
		// Inicializar el puntero.
		this.panelTransportarOperacion = panelTransportarOperacion;
		
		setBounds(x,y,ancho,largo);
		
		// Configurar
		configurarPanel();
		configurarComponentes();
	}
	
	/**
	 * Método en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este método corresponden a los atributos
	 * que se derivan de la clase JPanel.
	 */
	public void configurarPanel()
	{
		// borde del panel
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// titulos
		TitledBorder titulo = new TitledBorder(borde,
			" Matriz de Costos del Problema de Transporte ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// agrega el titulo
		setBorder(titulo);
		setLayout(null);
	}
	
	/**
	 * Método que configura los componentes GUI contenidos en este panel.
	 */
	public void configurarComponentes()
	{
		// borde
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Color de fondo para puntos ficticios
		Color colorFondo = new Color(238,201,201);
		
		// texto de ayuda para los puntos ficticios
		String tooltips = "Indica un Punto Ficticio";
		
		// crea un arreglo de paneles
		panelCostos = new JPanel(new GridLayout(1,ConfiguracionAGT.l,10,0));
		matriz = new TablaSwing[ConfiguracionAGT.l];
		
		// matriz de valores reales inicializados en cero
		Object[][] datos = new Object[0][0];
		
		// barra de desplazamiemto y panel temporales
		JScrollPane barraTemporal;
		JPanel panelTemporal;
		
		// creando textos
		JLabel textoExplicacion =
			new JLabel("Ingrese los costos de transporte en los que incurre " +
			"la empresa al enviar sus naves pesquera desde un origen hacia un " +
			"destino.");
		
		for(int i = 0;i < ConfiguracionAGT.l;i++)
		{
			panelMatriz = new JPanel();
			panelMatriz.setBorder(borde);
			panelMatriz.setSize(500,150);
			panelMatriz.setLayout(null);
			
			// establecer textos
			textoMedio = new JLabel("Transporte",
				new ImageIcon("../img/barco.gif"),JLabel.CENTER);
			textoMedio.setText("" +
				ConfiguracionAGT.capacidad.obtenerElemento(i).
				obtenerDescripcion());
			textoOrigen = new JLabel(new ImageIcon("../img/texto_origen.gif"));
			textoDestino = new JLabel(new ImageIcon("../img/texto_destino.gif"));
			
			// establecer posicion y configuracion
			textoMedio.setBounds(10,0,150,150);
			textoMedio.setVerticalTextPosition(JLabel.BOTTOM);
			textoMedio.setHorizontalTextPosition(JLabel.CENTER);
			
			textoOrigen.setBounds(160,0,30,152);
			textoOrigen.setBorder(borde);
			
			textoDestino.setBounds(190,0,310,30);
			textoDestino.setHorizontalAlignment(JLabel.CENTER);
			textoDestino.setBorder(borde);
			
			datos = obtenerDatos(i);
			
			// modelo de la matriz de costos
			ModeloTabla modelo = new ModeloTabla(datos, ConfiguracionAGT.n);
			
			// crear la tabla que contendra la submatriz
			matriz[i] = new TablaSwing(modelo);
			if(i == (ConfiguracionAGT.l - 1))
				matriz[i].setBackground(colorFondo);
			
			// Establece los puntos ficticios en la matriz
			matriz[i].establecerColorColumna(ConfiguracionAGT.n-1,
											 colorFondo, tooltips);
			
			matriz[i].establecerColorFila(matriz[i].getRowCount()-1,
										  colorFondo, tooltips);
			matriz[i].setBorder(borde);
			validarNumeros(matriz[i]);
			
			// panel temporal para ubicar la matriz
			panelTemporal = new JPanel();
			panelTemporal.setSize(305,120);
			panelTemporal.add(matriz[i]);
			
			// ubicar el panel de la matriz en un scrollpane
			barraTemporal = new JScrollPane(panelTemporal);
			barraTemporal.setBounds(190,30,305,120);
			barraTemporal.setBorder(borde);
			
			// agregar al panel matriz
			panelMatriz.add(textoMedio);
			panelMatriz.add(textoOrigen);
			panelMatriz.add(textoDestino);
			panelMatriz.add(barraTemporal);
			
			// agregar al panel de costos
			panelCostos.add(panelMatriz);
		}
		
		// redimensionar el panel de costos
		panelCostos.setPreferredSize(
			new Dimension(500*(ConfiguracionAGT.l),150));
		
		// crear la barra
		JScrollPane barraCostos = new JScrollPane(panelCostos);
		
		// crear y posicionar
		textoExplicacion.setBounds(20,25,760,15);
		barraCostos.setBounds(20,50,760,170);
		
		// incorporar al panel
		add(textoExplicacion);
		add(barraCostos);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los componentes GUI que
	 * tiene el panel de configuración. En particular, este método no se
	 * implementa porque este panel no maneja eventos.
	 */
	public void configurarEventos()
	{
	}
	
	/**
	 * Método que inicializa los datos por defecto correspondientes a las matriz
	 * de costo en cero.
	 *
	 * @return datos Arreglo de datos.
	 */
	private Object[][] obtenerDatos(int medio)
	{
		Object datos[][] = new Object[ConfiguracionAGT.m][ConfiguracionAGT.n];
		int i;
		int j;
		
		for(i = 0;i < ConfiguracionAGT.m;i++)
			for(j = 0;j < ConfiguracionAGT.n;j++)
			{
				datos[i][j] =
					new Double(ConfiguracionAGT.matrizCostos[i][j][medio]);
			}
		
		return datos;
	}
	
	/**
	 * Método que configura la validación de los campos con formato decimal de
	 * una tabla.
	 */
    private void validarNumeros(TablaSwing tabla)
    {
        // Configura el editor para ingreso de valores reales
        final CampoNumerico formatoCampo = new CampoNumerico(0);
        formatoCampo.setHorizontalAlignment(CampoNumerico.RIGHT);
		
        FormatoDecimal formatoCelda = new FormatoDecimal(formatoCampo);
        tabla.setDefaultEditor(Double.class, formatoCelda);
    }
	
	/**
	 * Método que permite establecer la matriz de costos del problema de
	 * transporte de acuerdo a los valores entregados por el usuario.
	 */
	public void establecerMatrizCostos()
	{
		int i;
		int j;
		int k;
		double[][][] costos = new double[ConfiguracionAGT.m]
										[ConfiguracionAGT.n]
										[ConfiguracionAGT.l];
		String solucion;
		String error;
		String titulo;
		DecimalFormat formatoDecimal = new DecimalFormat("##0.###");
		
		error = "";
		solucion = "{";
		
		try
		{
			for (i = 0;i < ConfiguracionAGT.m;i++)
			{
				solucion = solucion + "[";
				for (j = 0;j < ConfiguracionAGT.n;j++)
				{
					solucion = solucion + "(";
					for (k = 0;k < ConfiguracionAGT.l;k++)
					{
						costos[i][j][k] =
							Double.parseDouble("0"+ matriz[k].getValueAt(i, j));
						solucion = solucion +
								   formatoDecimal.format(costos[i][j][k]) + " ";
					}
					solucion = solucion + ")";
				}
				solucion = solucion + "]";
			}
		}
		catch(NumberFormatException msg)
		{
			error = "Debe ingresar números reales";
			titulo = "Error en la Matriz de Costos";
			JOptionPane.showMessageDialog(this,error,titulo,
										  JOptionPane.ERROR_MESSAGE);
		}
		solucion = solucion + "}";
		
		if(error == "")
		{
			//System.out.println("Costos =" + solucion);
			ConfiguracionAGT.establecerMatrizCostos(costos);
		}
	}
}