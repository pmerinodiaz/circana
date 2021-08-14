/**
 * @(#)SubPanelGraficarEconomia.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 * Clase que extiende de la clase JPanel. Esta clase permite dibujar las curvas
 * de predicción de precios y demandas dentro del panel graficar de economía.
 * 
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Graphics
 * @see Graphics2D
 * @see Color
 * @see Font
 * @see Rectangle
 * @see JPanel
 * @see PanelGraficarEconomia
 */
public class SubPanelGraficarEconomia extends JPanel
{
	/** Margen X que marca el eje x del gráfico. */
	private final int MARGENX = 50;
	
	/** Margen X que marca los ejes y del gráfico. */
	private final int MARGENY = 70;
	
	/** Color del eje abscisa. */
    private final Color COLOR_EJE_X = Color.BLACK;
    
   	/**
   	 * Color de las curvas de predicción.
	 * 0: Tonelada, 1: Precio.
	 */
    private final Color[] COLOR_CURVAS = {Color.RED, Color.BLUE};
    
    /** Cantidad de puntos que en el eje del precio. */
    private final int NUMERO_PUNTOS_PRECIO = 18;
    
	/** Cantidad de puntos que en el eje de tonelada. */
    private final int NUMERO_PUNTOS_TONELADA = 6;
	
	/** Cantidad de puntos que en el eje meses. */
    private final int NUMERO_PUNTOS_MESES = 12;
    
    /** Inicio del eje del valor de precio.  */
    private final int INICIO_EJE_PRECIO = 0;
    
    /** Inicio del eje del valor de tonelada.  */
    private final int INICIO_EJE_TONELADA = 0;
    
    /** Marca cada cuanto se salta los guiones indicadores del eje precio. */
    private final double SALTO_GUIONES_PRECIO = 1;
    
    /** Marca cada cuanto se salta los indicadores del eje precio. */
    private final double SALTO_GUIONES_NUMERICOS_PRECIO = 3;
    
    /** Marca cada cuanto se salta los guiones indicadores del eje tonelada. */
    private final double SALTO_GUIONES_TONELADA = 0.2;
    
    /** Marca cada cuanto se salta los indicadores del eje tonelada. */
    private final double SALTO_GUIONES_NUMERICOS_TONELADA = 1;
    
    /** Referenciador del frame creador del panel graficar economía. */
    public PanelGraficarEconomia panelGraficarEconomia;
    
    /** El largo de punto (pixel) en el precio. */
    private double largoPuntoPrecio;
    
    /** El largo de punto (pixel) en el mes. */
    private double largoPuntoMes;
    
    /** El largo de punto (pixel) en la tonelada. */
    private double largoPuntoTonelada;
    
	/** El gráfico usado para las funciones gráficas del sistema. */
    private Graphics2D grafico2;
    
    /** Tamaño del sub-panel graficar. */
    private Rectangle tamanoSubPanel;
    
    /** Indica si se está graficando las curvas. */
    private boolean graficandoCurva;
    
    /**
     * Método constructor del sub-panel de graficar. En este panel se configura
     * los elementos participante en el panel.
     *
     * @param frameEconomia Referenciador del frame economía.
     * @param x Inicio X del sub-panel.
     * @param y Inicio Y del sub-panel.
     * @param ancho Ancho del sub-panel.
     * @param largo Largo del sub-panel.
     */
	public SubPanelGraficarEconomia(PanelGraficarEconomia panelGraficarEconomia,
									int x, int y, int ancho, int largo)
	{
		super(null);
		
		// Inicializar el puntero.
		this.panelGraficarEconomia = panelGraficarEconomia;
		
		setBounds(x,y,ancho,largo);
		
		// Configurar.
		configurarElementosEspeciales();
	}
	
	/**
     * Método que configura elementos especiales que contiene el sub-panel de
     * graficar. 
     */
	private void configurarElementosEspeciales()
	{
		tamanoSubPanel = getBounds();
		largoPuntoMes = ((tamanoSubPanel.width - 2.0 * MARGENY) /
						NUMERO_PUNTOS_MESES);
		largoPuntoPrecio = ((tamanoSubPanel.height - 2.0 * MARGENX) /
						   NUMERO_PUNTOS_PRECIO);
		largoPuntoTonelada = ((tamanoSubPanel.height - 2.0 * MARGENX) /
							 NUMERO_PUNTOS_TONELADA);
		graficandoCurva = false;
	}
	
	/**
     * Método que dibuja un borde en el gráfico de las curvas.
     */
	private void dibujarBorde()
	{
		grafico2.setColor(Color.WHITE);
		grafico2.fillRect(0,0,tamanoSubPanel.width - 1,
						  tamanoSubPanel.height - 1);
		grafico2.setColor(Color.BLACK);
		grafico2.drawRect(0,0,tamanoSubPanel.width - 1,
						  tamanoSubPanel.height - 1);
	}
	
	/**
     * Método que dibuja el plano cartesiano asociado a las predicciones.
     */
	private void dibujarPlanoCartesiano()
	{
		double i;
		int largoPunto = 5;
		int largoPuntoNumerico;
		
		// establecer la fuente
		grafico2.setFont(new Font("Arial", Font.PLAIN, 11));
		
		// dibujando eje X
		grafico2.setColor(COLOR_EJE_X);
		grafico2.drawLine(MARGENY,
						  tamanoSubPanel.height - MARGENX,
						  tamanoSubPanel.width - MARGENY,
						  tamanoSubPanel.height - MARGENX);
		
		// dibujando ejes Y
		grafico2.setColor(COLOR_CURVAS[0]);
	 	grafico2.drawLine(MARGENY, MARGENX, MARGENY,
	 					  tamanoSubPanel.height - MARGENX);
	 	
	 	grafico2.setColor(COLOR_CURVAS[1]);
	 	grafico2.drawLine(tamanoSubPanel.width - MARGENY, MARGENX,
	 					  tamanoSubPanel.width - MARGENY, 
	 					  tamanoSubPanel.height - MARGENX);
	 	
	 	// dibujando textos
		grafico2.setColor(COLOR_CURVAS[0]);
		grafico2.drawString("DEMANDA (tonelada)",MARGENY - 40,30);
		
		grafico2.setColor(COLOR_CURVAS[1]);
		grafico2.drawString("PRECIO (US$/kilogramo)",
							tamanoSubPanel.width - MARGENY - 55,30);
		
		// dibujando datos en la abscisa en meses
		grafico2.setColor(COLOR_EJE_X);
		for (i = 0; i <= NUMERO_PUNTOS_MESES; i ++)
		{
			grafico2.drawLine((int) (MARGENY + i * largoPuntoMes),
						(int) (tamanoSubPanel.height - MARGENX + largoPunto),
						(int) (MARGENY + i * largoPuntoMes),
						(int) (tamanoSubPanel.height - MARGENX - largoPunto));
			
			if (i != 12)
			{
				grafico2.drawString(Servicio.obtenerNombreMes((int) i + 1),
					(int) (MARGENY + i * largoPuntoMes),
					(int) (tamanoSubPanel.height - MARGENX + largoPunto + 20));
			}
		}
	    
	    // dibujando textos eje demanda
	    grafico2.setColor(COLOR_CURVAS[0]);
	    for (i = 0; i <= NUMERO_PUNTOS_TONELADA; i += SALTO_GUIONES_TONELADA)
	    {
	    	i = Servicio.obtenerNumeroAproximado(i, 2);
	    	
	    	if (i%SALTO_GUIONES_NUMERICOS_TONELADA == 0)
	    		largoPuntoNumerico = 5;
	    	else
	    		largoPuntoNumerico = 0;
	    	
	    	grafico2.drawLine((int) (MARGENY + largoPunto + largoPuntoNumerico),
							  (int) (tamanoSubPanel.height -
							  		 MARGENX - i * largoPuntoTonelada),
							  (int) (MARGENY - largoPunto - largoPuntoNumerico),
							  (int) (tamanoSubPanel.height -
							  		 MARGENX - i * largoPuntoTonelada));
		}
			
		for (i = 0; i <= NUMERO_PUNTOS_TONELADA;
			 i += SALTO_GUIONES_NUMERICOS_TONELADA)
		{
			grafico2.drawString(
				Servicio.obtenerNumeroFormateado(i + INICIO_EJE_TONELADA,1),
				(int) (MARGENY - largoPunto - 30),
				(int) (tamanoSubPanel.height - MARGENX - i*largoPuntoTonelada));
		}
		
	    // dibujando textos eje precio
	    grafico2.setColor(COLOR_CURVAS[1]);
	    for (i = 0; i <= NUMERO_PUNTOS_PRECIO; i += SALTO_GUIONES_PRECIO)
	    {
	    	i = Servicio.obtenerNumeroAproximado(i,2);
	    	
	    	if (i%SALTO_GUIONES_NUMERICOS_PRECIO == 0)
	    		largoPuntoNumerico = 5;
	    	else
	    		largoPuntoNumerico = 0;
	    	
	    	grafico2.drawLine((int) (tamanoSubPanel.width - MARGENY +
	    							 largoPunto  + largoPuntoNumerico),
							  (int) (tamanoSubPanel.height -
							  		 MARGENX - i * largoPuntoPrecio),
							  (int) (tamanoSubPanel.width - MARGENY -
							  		 largoPunto  - largoPuntoNumerico),
							  (int) (tamanoSubPanel.height -
							  		 MARGENX - i * largoPuntoPrecio));
		}
		
		for (i = 0; i <= NUMERO_PUNTOS_PRECIO;
			 i += SALTO_GUIONES_NUMERICOS_PRECIO)
	    {
	       	grafico2.drawString(
	       		Servicio.obtenerNumeroFormateado(i + INICIO_EJE_PRECIO,1),
				(int) (tamanoSubPanel.width - MARGENY + largoPunto + 10),
				(int) (tamanoSubPanel.height - MARGENX - i * largoPuntoPrecio));
		}
	}
	
    /**
     * Método que grafica el valor de la tonelada en tabla del gráfico dado un
     * mes y un año en particular.
     * 
     * @param mes Mes del año.
     * @param tonelada Valor de la tonelada.
     */
	private void graficarUnaTonelada(double mes, double tonelada)
	{
		int puntoMes;
		int puntoTonelada;
		
		int origenEjeY = tamanoSubPanel.height -  MARGENX;
		int origenEjeX = MARGENY;
		
		puntoMes = 	(int) ((mes - 1) * largoPuntoMes);
		puntoTonelada = (int) ((tonelada - INICIO_EJE_TONELADA) *
						largoPuntoTonelada);
		
		grafico2.setColor(COLOR_CURVAS[0]);
		grafico2.drawRect(origenEjeX + puntoMes, origenEjeY - puntoTonelada,
						  1, 1);
	}
	
	/**
     * Método que grafica el valor del precio en tabla del gráfico dado un mes y
     * un año en particular.
     * 
     * @param mes Mes del año.
     * @param precio Precio ha graficar.
     */
	private void graficarUnPrecio(double mes, double precio)
	{
		int puntoMes;
		int puntoPrecio;
		int origenEjeY = tamanoSubPanel.height -  MARGENX;
		int origenEjeX = MARGENY;
		
		grafico2.setColor(COLOR_CURVAS[1]);
		
		puntoMes = 	(int) ((mes - 1) * largoPuntoMes);
		puntoPrecio = (int) ((precio  - INICIO_EJE_PRECIO) * largoPuntoPrecio);
		
		grafico2.drawRect(origenEjeX + puntoMes, origenEjeY - puntoPrecio,
						  1, 1);
	}
	
	/**
     * Método que dibuja las curvas de precio y demanda en panel del gráfico.
     */
	private void dibujarPuntos()
	{
		int anoPrediccion;
		int anoInicioPrediccion;
		int anoFinalPrediccion;
		double dia;
		double mes;
		double diaInicio;
		double diaFinal;
		
		double[] entrada =
			new double[ConfiguracionNeuronalBP.obtenerNumeroEntradas()];
		double[] salida =
			new double[ConfiguracionNeuronalBP.obtenerNumeroSalidas()];
		
		anoPrediccion = panelGraficarEconomia.frameEconomia.
						obtenerAnoPrediccion();
		anoInicioPrediccion = Proyecto.obtenerFechaInicialFormatoInt()[2];
		anoFinalPrediccion = Proyecto.obtenerFechaFinalFormatoInt()[2];
		diaInicio = 1;
		diaFinal = 365;
		
		if (anoInicioPrediccion == anoPrediccion)
		{
			int[] diaMes = {Proyecto.obtenerFechaInicialFormatoInt()[0],
							Proyecto.obtenerFechaInicialFormatoInt()[1]};
			
			diaInicio = Servicio.obtenerDia(diaMes);
		}
		
		if (anoFinalPrediccion == anoPrediccion)
		{
			int[] diaMes = {Proyecto.obtenerFechaFinalFormatoInt()[0],
							Proyecto.obtenerFechaFinalFormatoInt()[1]};
			
			diaFinal = Servicio.obtenerDia(diaMes);
		}
		
		for (dia = diaInicio; dia <= diaFinal; dia++)
		{
			entrada[0] = anoPrediccion;
			entrada[1] = dia;
			
			salida = panelGraficarEconomia.frameEconomia.
					 ejecutarSalidaRed(entrada);
			
			graficarUnaTonelada(Servicio.obtenerMes((int)dia),salida[0]);
			graficarUnPrecio(Servicio.obtenerMes((int)dia),salida[1]);
		}
	}
	
	/**
     * Metodo que muestra un texto cuando las curvas no se estan dibujando o
     * no estan disponibles.
     */
	private void mostrarTextoNoDisponible()
	{
		grafico2.setColor(Color.BLACK);
		grafico2.setFont(new Font("WITH ACUTE",Font.ROMAN_BASELINE,20));
		grafico2.drawString("Entrene la red neuronal", getWidth() / 2 - 100,
							getHeight() / 2);
	}
	
    /**
	 * Método que grafica los elementos participantes en el panel. Cuando se
	 * acciona el evento graficar de las curvas pertenecientes a la predicción.
	 *
	 * @param grafico El gráfico utilizado en el panel.
	 */
	public void paintComponent(Graphics grafico)
	{
		super.paintComponent(grafico);
		
		// Castear el gráfico para un mejor manejo.
		grafico2 = (Graphics2D) grafico;
		
		dibujarBorde();
		dibujarPlanoCartesiano();
		
	    if (graficandoCurva)
	    	dibujarPuntos();
	    //else
	    //	mostrarTextoNoDisponible();
	}
	
	/**
     * Método que establace si las curvas de predicción se van ha graficar o no
     * en el panel graficar.
     * 
     * @param graficarCurva Indica si las curvas de predicción se grafican o no.
     */
	public void graficarCurva(boolean graficandoCurva)
	{
		this.graficandoCurva = graficandoCurva;
		repaint();
	}
}