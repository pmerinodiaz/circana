/**
 * @(#)SubPanelGraficarEvolucion.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 * Clase que extiende de la clase JPanel. Esta clase permite graficar la evoluci�n
 * del algoritmo gen�tico a trav�s de cada generaci�n. * 
 * 
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see Vector
 * @see Image
 * @see Graphics
 * @see Graphics2D
 * @see Color
 * @see Font
 * @see Rectangle
 * @see JPanel
 * @see PanelEvolucionOperacion
 * @see InterfacePanel
 */
public class SubPanelGraficarEvolucion extends JPanel implements InterfacePanel
{
	/** Margen X que marca el eje x del grafica. */
	private static int MARGENX = 50;
	
	/** Margen X que marca los ejes y del grafica. */
	private static int MARGENY = 70;
	
	/** N�mero de items en el eje y. */
	private static int ITEMSY = 15;
	
	/** N�mero m�ximo de generaciones a mostrar. */
	private static int NUMERO_MAXIMO_GENERACIONES = 15;
	
	/** Contiene el ancho del eje de coordenadas. */
	private int ancho;
	
	/** Contiene el alto del eje de coordenadas. */
	private int alto;
	
	/** Contiene el valor entre cada item del eje y. */
	private int valorItemY;
	
	/** Contiene el espacio en pixeles entre itesm del eje y. */
	private int espacioItemY;
	
	/** Contiene el espacio en pixeles entre itesm del eje x. */
	private int espacioItemX;
	
	/** Contiene el valor monetario de cada pixel. */
	private double valorPixel;
	
	/** Costo m�ximo. */
	private int costoMaximo;
	
	/** Imagen usada para doble-buffer. */
	private Image imagen;
	
	/** Gr�fico usado en doble-buffer. */
	private Graphics pantalla;
	
	/** Gr�fico para funciones 2D. */
	private Graphics2D grafico2D;
	
	/** Tama�o del sub panel. */
	private Rectangle tamanoSubPanel;
	
	/** Vector que contiene los costos m�nimos. */
	private Vector evolucion;
	
	/** Indica la divisi�n de generaciones. */
	private int etapa;
	
	/**
	 * M�todo constructor del sub-panel que grafica la evoluci�n del algoritmo
	 * gen�tico en busca de una soluci�n en el espacio de soluciones.
	 *
	 * @param panelEvolucionOperacion El panel creador de este sub-panel.
	 * @param x Inicio X del sub-panel.
	 * @param y Inicio Y del sub-panel.
	 * @param ancho Ancho del sub-panel.
	 * @param largo largo del sub-panel.
	 */
	public SubPanelGraficarEvolucion(
								PanelEvolucionOperacion panelEvolucionOperacion,
								int x, int y, int ancho, int largo)
	{
		setBounds(x, y, ancho, largo);
		
		// Configurar.
		configurarElementosEspeciales();
		configurarPanel();
	}
	
	/**
	 * M�todo para la configuraci�n de los elementos especiales del sub-panel.
	 */
	public void configurarElementosEspeciales()
	{
		etapa = 0;
		evolucion = new Vector();
		tamanoSubPanel = getBounds();
	 	ancho = tamanoSubPanel.width - 2 * MARGENY;
	 	alto = tamanoSubPanel.height - 2 * MARGENX;
		costoMaximo = 295;
	}
	
	/**
	 * M�todo en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran corresponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este m�todo.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
     * M�todo que configura los componentes que se encuentra en el sub-panel de
     * graficar la evoluci�n del modelo operativo. En particular, este m�todo no
     * es implementado porque no tiene componentes GUI.
     */
	public void configurarComponentes()
	{
	}
	/**
	 * M�todo que adjunta los escuchadores eventos a los componentes GUI que
	 * tiene el panel de configuraci�n. En particular, este m�todo no se
	 * implementa porque este panel no maneja eventos.
	 */
	public void configurarEventos()
	{
	}
	
	/**
	 * M�todo que dibuja el borde para el gr�fico del panel.
	 */
	private void dibujarBorde()
	{
		grafico2D.setColor(Color.WHITE);
		grafico2D.fillRect(0,0,tamanoSubPanel.width - 1,
						  tamanoSubPanel.height - 1);
		grafico2D.setColor(Color.BLACK);
		grafico2D.drawRect(0,0,tamanoSubPanel.width - 1,
						  tamanoSubPanel.height - 1);
	}
	
	/**
	 * M�todo que dibuja los ejes del plano cartesiano.
	 */
	private void dibujarPlanoCartesiano()
	{
		// variables locales
		int resto;
		int i;
	 	String texto;
	 	
	 	// fijar color de los ejes
	 	grafico2D.setColor(Color.BLACK);
	 	
	 	// dibujar eje x
		grafico2D.drawLine(MARGENY,
						   tamanoSubPanel.height - MARGENX,
						   tamanoSubPanel.width - MARGENY, 
						   tamanoSubPanel.height - MARGENX);
		
		// dibujar eje y
	 	grafico2D.drawLine(MARGENY, MARGENX, MARGENY,
	 					   tamanoSubPanel.height - MARGENX);
	 	
	 	// unidades de medida
	 	resto = costoMaximo % ITEMSY;
	 	if(resto != 0)
	 	{
	 		costoMaximo = (((int) costoMaximo / ITEMSY) + 1) * ITEMSY;
	 	}
	 	
	 	valorItemY = costoMaximo / ITEMSY;
	 	espacioItemY = (int) alto / ITEMSY;
	 	valorPixel = (double) valorItemY / espacioItemY;
	 	
	 	// dibuja items eje y
	 	for(i = 1;i <= ITEMSY;i++)
	 	{
	 		grafico2D.drawLine((int) MARGENY - 5,
	 						   (int) (tamanoSubPanel.height - MARGENX) -
	 								  i*espacioItemY,
	 						   (int) MARGENY + 5,
	 						   (int) (tamanoSubPanel.height - MARGENX) -
	 								  i*espacioItemY);
	 		texto = Integer.toString(valorItemY*i) + ".0";
	 		grafico2D.drawString(texto, MARGENY-60,
	 							 (int) (tamanoSubPanel.height - MARGENX + 5) -
	 							 		i*espacioItemY);
	 	}
	 	
	 	espacioItemX = (int) ancho / NUMERO_MAXIMO_GENERACIONES;
	 	
	 	// dibuja items eje x
	 	for(i = 1;i <= NUMERO_MAXIMO_GENERACIONES;i++)
	 	{
	 		grafico2D.drawLine((int) MARGENY + i*espacioItemX,
	 						   (int) tamanoSubPanel.height - MARGENX - 5,
	 						   (int) MARGENY + i*espacioItemX,
	 						   (int) tamanoSubPanel.height - MARGENX + 5);
	 	}
	 	
	 	// agregar textos a los ejes
	 	grafico2D.setColor(Color.RED);
	 	grafico2D.drawString("COSTOS ($)",MARGENY - 30,30);
	 	grafico2D.setColor(Color.BLACK);
	 	grafico2D.drawString("NUMERO DE GENERACIONES",
	 						 tamanoSubPanel.width-MARGENY*3,
	 						 tamanoSubPanel.height - 15);
	}
	
	/**
	 * M�todo que actualiza el eje y con el valor de costo m�ximo.
	 *
	 * @param costo Valor m�ximo de costo.
	 */	
	public void actualizarPlanoCartesiano(double costo)
	{
		costoMaximo = (int) costo;
		repaint();
	}
	
	/**
	 * M�todo que es utilizado para graficar los costos m�nimos en el intervalo
	 * actual.
	 *
	 * @param v Vector que contiene los costos m�nimos en el intervalo de
	 *          generaciones.
	 * @param e Indica la etapa del intervalo.
	 */
	public void asignarCurva(Vector v, int e)
	{
		evolucion = v;
		etapa = e;
		repaint();
	}
	
	/**
	 * M�todo que grafica en el plano los puntos correspondientes a los costos
	 * m�nimos en el intervalo de las generaciones.
	 */
	public void graficarPuntos()
	{
		int puntoAnterior;
		int puntoActual;
		int i;
		int j;
		String texto = "";
		
		puntoAnterior = (int) (Double.parseDouble(""+evolucion.elementAt(0)) /
							   valorPixel);
		
		for(i = 1,j = etapa + 1;i < evolucion.size();i++)
	 	{
	 		puntoActual = (int) (Double.parseDouble(""+evolucion.elementAt(i)) /
	 							 valorPixel);
	 		
	 		grafico2D.setColor(Color.RED);
	 		
	 		grafico2D.drawLine((int) MARGENY + (i-1)*espacioItemX,
	 						   (int) tamanoSubPanel.height - MARGENX -
	 						   		 puntoAnterior,
	 						   (int) MARGENY + i*espacioItemX,
	 						   (int) tamanoSubPanel.height - MARGENX -
	 						   		 puntoActual);
	 		
	 		texto = Integer.toString(j++);
	 		grafico2D.setColor(Color.BLACK);
	 		grafico2D.drawString(texto, MARGENY + i*espacioItemX - 5,
	 							 (int) tamanoSubPanel.height - MARGENX + 20);
	 		
	 		puntoAnterior = puntoActual;
	 	}
	}
	
	/**
	 * M�todo sobrecargado que reduce el flasheo. Se llama al m�todo paint de la
	 * clase. Este m�todo es llamado cuando se setea el foco en este panel.
	 */
	public void update(Graphics g)
	{
		paint(g);
	}
	
	/**
	 * M�todo sobrecargado que permite la utilizaci�n de la t�cnica de doble
	 * buffer para reducir el parpadeo.
	 *
	 * @param g Gr�fico utilizado en el panel.
	 */
	public void paint(Graphics g)
	{
		paintComponent(g);
	}
	
	/**
	 * M�todo sobrecargado que grafica los elementos del panel.
	 *
	 * @param g Gr�fico utilizado en el panel.
	 */	  
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		grafico2D = (Graphics2D) g;
		dibujarBorde();
		dibujarPlanoCartesiano();
		if(!evolucion.isEmpty())
			graficarPuntos();
	}
}