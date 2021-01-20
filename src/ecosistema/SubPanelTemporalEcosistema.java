/**
 * @(#)SubPanelTemporalEcosistema.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

/**
 * Clase que extiende de la clase JPanel. En esta clase se muestra al usuario
 * el comportamiento en el tiempo del recurso estudiado. Se muestran tres
 * gráficos: la biomasa vs tiempo, la abundancia vs tiempo y la calidad promedio
 * vs tiempo. En la gráfica se utiliza la técnica denominada doble-buffer, con
 * el objeto de mitigar el flasheo en la pantalla cuando se está haciendo la
 * simulación, y además, acelerar la animación de los gráficos.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Image
 * @see Graphics
 * @see Graphics2D
 * @see Color
 * @see Font
 * @see JPanel
 * @see PanelTemporalEcosistema
 * @see TransformacionGrafica
 * @see Estadistica
 */
public class SubPanelTemporalEcosistema extends JPanel
{
	/** El margen en X del eje de las curvas. */
	private static final int MARGEN_X = 45;
	
	/** El margen en Y del eje de las curvas. */
	private static final int MARGEN_Y = 15;
	
	/** El valor inicial del eje donde se muestra el tiempo. */
    private static final double TIEMPO_INICIAL = 1;
    
    /** El valor final del eje donde se muestra el tiempo. */
    private static final double TIEMPO_FINAL = 13;
    
	/** El valor inicial del eje donde se muestra la biomasa. */
    private static final double BIOMASA_INICIAL = 0;
    
    /** El valor final del eje donde se muestra la biomasa. */
    private static final double BIOMASA_FINAL = 10;
    
    /** La resolución de la biomasa para la regla de las biomasas. */
	private static final double BIOMASA_RESOLUCION = 1;
	
	/** La escala de la biomasa para la regla de las biomasas. */
	private static final double BIOMASA_ESCALA = 10000;
	
    /** El valor inicial del eje donde se muestra la abundancia. */
    private static final double ABUNDANCIA_INICIAL = 0;
    
    /** El valor final del eje donde se muestra la abundancia. */
    private static final double ABUNDANCIA_FINAL = 10;
    
    /** La resolución de la abundancia para la regla de las abundancias. */
	private static final double ABUNDANCIA_RESOLUCION = 1;
	
	/** La escala de la abundancia para la regla de las abundancias. */
	private static final double ABUNDANCIA_ESCALA = 1000000000;
	
    /** El valor inicial del eje donde se muestra la calidad promedio. */
    private static final double CALIDAD_PROMEDIO_INICIAL = 0;
    
    /** El valor final del eje donde se muestra la calidad promedio. */
    private static final double CALIDAD_PROMEDIO_FINAL = 1;
    
    /** La resolución de la calidad para la regla de las calidades promedios. */
	private static final double CALIDAD_PROMEDIO_RESOLUCION = 0.25;
	
	/** El tipo de fuente usada en los planos. */
	private static final Font FUENTE_PLANO = new Font("Arial", Font.PLAIN, 9);
	
    /** El puntero al panel que contiene a este panel. */
	public PanelTemporalEcosistema panelTemporalEcosistema;
	
	/** La imagen usada en el doble-buffer. */
	private Image imagen;
	
	/** El gráfico usado en el doble-buffer. */
    private Graphics pantalla;
    
    /** El gráfico usado para las funciones gráficas del sistema. */
    private Graphics2D grafico2;
	
    /** Las transformaciones gráficas usadas en los gráficos del panel. */
	private TransformacionGrafica[] transformacion;
	
	/**
	 * Método constructor en donde se crean e inicializan los componentes que
	 * contiene este panel. Primero se inicializa el puntero al panel que
	 * contiene a este panel. Luego se llama a los métodos que configurar este
	 * panel y se inicia el doble buffer.
	 *
	 * @param panelTemporalEcosistema Un objeto que hace referencia al
	 *                                PanelTemporalEcosistema que contiene a
	 *                                este panel.
     * @param u1 La posición inicial en el eje U para este panel.
     * @param v1 La posición inicial en el eje V para este panel.
     * @param u2 La posición final en el eje U para este panel.
     * @param v2 La posición final en el eje V para este panel.
	 */
	public SubPanelTemporalEcosistema(
								PanelTemporalEcosistema panelTemporalEcosistema,
								int u1, int v1, int u2, int v2)
	{
		// Apuntar el panel contenedor de este panel.
		this.panelTemporalEcosistema = panelTemporalEcosistema;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarPanel(u1, v1, u2, v2);
	}
	
	/**
	 * Método en donde se inicializan los objetos que se usan en todo este
	 * panel.
	 */
	private void configurarElementosEspeciales()
	{
		// Calcular el tamaño de los ejes.
		int anchoEje = 770;
		int altoEje = 140;
		
		// Asignar tamaño al arreglo de transformaciones gráficas.
		transformacion = new TransformacionGrafica[3];
		
		// Inicializar los valores de las transformaciones gráficas.
		transformacion[0] = new TransformacionGrafica(
							MARGEN_X, MARGEN_X + anchoEje,
							MARGEN_Y, MARGEN_Y + altoEje,
							TIEMPO_INICIAL, TIEMPO_FINAL,
							BIOMASA_FINAL, BIOMASA_INICIAL);
		transformacion[1] = new TransformacionGrafica(
							MARGEN_X, MARGEN_X + anchoEje,
							3*MARGEN_Y + altoEje, 3*MARGEN_Y + 2*altoEje,
							TIEMPO_INICIAL, TIEMPO_FINAL,
							ABUNDANCIA_FINAL, ABUNDANCIA_INICIAL);
		transformacion[2] = new TransformacionGrafica(
							MARGEN_X, MARGEN_X + anchoEje,
							5*MARGEN_Y + 2*altoEje, 5*MARGEN_Y + 3*altoEje,
							TIEMPO_INICIAL, TIEMPO_FINAL,
							CALIDAD_PROMEDIO_FINAL, CALIDAD_PROMEDIO_INICIAL);
		
		// Inicializar los componentes del doble-buffer.
		iniciarDobleBuffer();
	}
	
	/**
	 * Método que crea e inicializa los elementos utilizados en la técnica del
	 * doble-buffer. Estos elementos corresponden a una imagen y un gráfico.
	 */
	private void iniciarDobleBuffer()
	{
		try
		{
			// Crear la imagen utilizada para el doble-buffer. La imagen tiene
			// el tamaño de este panel.
			imagen = createImage(getWidth(), getHeight());
			
			// Obtener el gráfico de la imagen,
			pantalla = imagen.getGraphics();
		}
		catch (Exception excepcion)
		{
			// Inicializar el gráfico de la imagen en nulo.
			pantalla = null;
		}
	}
	
	/**
	 * Método en donde se configuran varias propiedades que tiene este panel.
	 * Las propiedades que se configuran correnponden a los atributos derivados
	 * de la clase JPanel y que son modificados por este método.
	 *
     * @param u1 La posición inicial en el eje U para este panel.
     * @param v1 La posición inicial en el eje V para este panel.
     * @param u2 La posición final en el eje U para este panel.
     * @param v2 La posición final en el eje V para este panel.
	 */
	private void configurarPanel(int u1, int v1, int u2, int v2)
	{
		setBounds(u1, v1, u2, v2);
	}
	
	/**
	 * Método sobrecargado que permite utilizar la técnica del doble-buffer, con
	 * el objeto de reducir el flasheo de la imagen graficada y simulada en la
	 * pantalla. Se llama al método paintComponent() para graficar las curvas de
	 * la biomasa, abundancia y calidad en movimiento. Este método es llamado
	 * por defecto cada vez que se muestra este panel.
	 *
	 * @param grafico El gráfico utilizado en el panel.
	 */
	public void paint(Graphics grafico)
	{
		// Cuando si se puede usar el doble-buffer.
		if (pantalla != null)
		{
			paintComponent(pantalla);
			grafico.drawImage(imagen, 0, 0, this);
		}
		
		// Cuando no se puede usar el doble-buffer.
		else paintComponent(grafico);
	}
	
	/**
	 * Método sobrecargado que reduce el flasheo. Se llama al método paint() de
	 * la clase. Este método es llamado cuando se setea el foco en este panel.
	 *
	 * @param grafico El gráfico utilizado en el panel.
	 */
	public void update(Graphics grafico)
	{
		paint(grafico);
	}
	
    /**
	 * Método que llama a los métodos que grafican las curvas de la dinámica
	 * temporal del ecosistema. Primero se transforma el gráfico utilizado en el
	 * panel a Graphics2D para un manejo eficaz de los gráficos en la clase.
	 * Luego, se grafican los puntos de las curvas.
	 *
	 * @param grafico El gráfico utilizado en el panel.
	 */
	public void paintComponent(Graphics grafico)
	{
		// Castear el gráfico para un mejor manejo.
		grafico2 = (Graphics2D) grafico;
		
		// Graficar el marco del panel.
		graficarMarcoPanel();
		
		// Graficar los planos.
		graficarPlanoBiomasaTiempo();
		graficarPlanoAbundanciaTiempo();
		graficarPlanoCalidadTiempo();
		
		// Graficar los puntos de las curvas.
		graficarPuntos();
	}
	
	/**
	 * Método que grafica el marco que tiene el panel de la dinámica temporal.
	 * Se pinta un rectángulos blanco y un rectángulo negro, con el objeto de
	 * que el usuario perciba de mejor manera las las curvas.
	 */
	private void graficarMarcoPanel()
	{
		// Dibujar los rectágulos horizontales.
		grafico2.setColor(Color.WHITE);
		grafico2.fillRect(0, 0, getWidth()-1, getHeight()-1);
		
		// Dibujar los rectángulos exterior, medio e interior.
		grafico2.setColor(Color.BLACK);
		grafico2.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
	
	/**
	 * Método que grafica el plano biomasa vs tiempo. En el eje U del sistema
	 * portal de visión se muestra el plano de tiempo. En el eje V del sistema
	 * portal de visión se muestra el plano biomasa.
	 */
	private void graficarPlanoBiomasaTiempo()
	{
		// Establecer el color.
		grafico2.setColor(Color.BLACK);
		
		// Establecer la fuente.
		grafico2.setFont(FUENTE_PLANO);
		
		// Dibujar el eje tiempo.
		grafico2.drawLine(transformacion[0].obtenerUMin(),
						  transformacion[0].obtenerVMax(),
						  transformacion[0].obtenerUMax(),
						  transformacion[0].obtenerVMax());
		
		// Ciclo para recorrer los tiempos.
		for (int tiempo=(int) TIEMPO_INICIAL;
			 tiempo<=TIEMPO_FINAL;
			 tiempo++)
		{
			grafico2.drawLine(transformacion[0].obtenerU(tiempo),
							  transformacion[0].obtenerVMax()-2,
							  transformacion[0].obtenerU(tiempo),
							  transformacion[0].obtenerVMax()+2);
			
			// Cuando el tiempo es menor que el tiempo final.
			if (tiempo < TIEMPO_FINAL)
				grafico2.drawString(Servicio.obtenerNombreMes(tiempo),
								  transformacion[0].obtenerU(tiempo)+10,
								  transformacion[0].obtenerVMax()+11);
		}
		
		// Dibujar el eje biomasa.
		grafico2.drawLine(transformacion[0].obtenerUMin(),
						  transformacion[0].obtenerVMin(),
						  transformacion[0].obtenerUMin(),
						  transformacion[0].obtenerVMax());
		
		// Dibujar el nombre del eje biomasa.
		grafico2.drawString("BIOMASA (tonelada x "+
						Servicio.obtenerNumeroFormateado(BIOMASA_ESCALA, 1)+")",
						transformacion[0].obtenerUMin()+4,
						transformacion[0].obtenerVMin()-1);
		
		// Ciclo para recorrer las biomasas.
		for (double biomasa=BIOMASA_FINAL;
			 biomasa>=BIOMASA_INICIAL;
			 biomasa-=BIOMASA_RESOLUCION)
		{
			grafico2.drawLine(transformacion[0].obtenerUMin()-2,
							  transformacion[0].obtenerV(biomasa),
							  transformacion[0].obtenerUMin()+2,
							  transformacion[0].obtenerV(biomasa));
			grafico2.drawString("" + (int) biomasa + ".0", 20,
							  transformacion[0].obtenerV(biomasa));
		}
	}
	
	/**
	 * Método que grafica el plano abundancia vs tiempo. En el eje U del sistema
	 * portal de visión se muestra el plano de tiempo. En el eje V del sistema
	 * portal de visión se muestra el plano abundancia.
	 */
	private void graficarPlanoAbundanciaTiempo()
	{
		// Establecer el color.
		grafico2.setColor(Color.BLACK);
		
		// Establecer la fuente.
		grafico2.setFont(FUENTE_PLANO);
		
		// Dibujar el eje tiempo.
		grafico2.drawLine(transformacion[1].obtenerUMin(),
						  transformacion[1].obtenerVMax(),
						  transformacion[1].obtenerUMax(),
						  transformacion[1].obtenerVMax());
		
		// Ciclo para recorrer los tiempos del eje tiempo.
		for (int tiempo=(int) TIEMPO_INICIAL;
			 tiempo<=TIEMPO_FINAL;
			 tiempo++)
		{
			grafico2.drawLine(transformacion[1].obtenerU(tiempo),
							  transformacion[1].obtenerVMax()-2,
							  transformacion[1].obtenerU(tiempo),
							  transformacion[1].obtenerVMax()+2);
			
			// Cuando el tiempo es menor que el tiempo final.
			if (tiempo < TIEMPO_FINAL)
				grafico2.drawString(Servicio.obtenerNombreMes(tiempo),
								  transformacion[1].obtenerU(tiempo)+10,
								  transformacion[1].obtenerVMax()+11);
		}
		
		// Dibujar el eje abundancia.
		grafico2.drawLine(transformacion[1].obtenerUMin(),
						  transformacion[1].obtenerVMin(),
						  transformacion[1].obtenerUMin(),
						  transformacion[1].obtenerVMax());
		
		// Dibujar el nombre del eje abundancia.
		grafico2.drawString("ABUNDANCIA (unidad x "+
					Servicio.obtenerNumeroFormateado(ABUNDANCIA_ESCALA, 1)+")",
					transformacion[1].obtenerUMin()+4,
					transformacion[1].obtenerVMin()-1);
		
		// Ciclo para recorrer las abundancias.
		for (double abundancia=ABUNDANCIA_FINAL;
			 abundancia>=ABUNDANCIA_INICIAL;
			 abundancia-=ABUNDANCIA_RESOLUCION)
		{
			grafico2.drawLine(transformacion[1].obtenerUMin()-2,
							  transformacion[1].obtenerV(abundancia),
							  transformacion[1].obtenerUMin()+2,
							  transformacion[1].obtenerV(abundancia));
			grafico2.drawString("" + (int) abundancia + ".0", 20,
							  transformacion[1].obtenerV(abundancia));
		}
	}
	
	/**
	 * Método que grafica el plano calidad promedio vs tiempo. En el eje U del
	 * sistema portal de visión se muestra el plano de tiempo. En el eje V del
	 * sistema portal de visión se muestra el plano calidad promedio.
	 */
	private void graficarPlanoCalidadTiempo()
	{
		// Variables locales.
		String textoCalidad = "";
		
		// Establecer el color y la fuente.
		grafico2.setColor(Color.BLACK);
		grafico2.setFont(FUENTE_PLANO);
		
		// Dibujar el eje tiempo.
		grafico2.drawLine(transformacion[2].obtenerUMin(),
						  transformacion[2].obtenerVMax(),
						  transformacion[2].obtenerUMax(),
						  transformacion[2].obtenerVMax());
		
		// Ciclo para recorrer los tiempos del eje tiempo.
		for (int tiempo=(int) TIEMPO_INICIAL;
			 tiempo<=TIEMPO_FINAL;
			 tiempo++)
		{
			grafico2.drawLine(transformacion[2].obtenerU(tiempo),
							  transformacion[2].obtenerVMax()-2,
							  transformacion[2].obtenerU(tiempo),
							  transformacion[2].obtenerVMax()+2);
			
			// Cuando el tiempo es menor que el tiempo final.
			if (tiempo < TIEMPO_FINAL)
				grafico2.drawString(Servicio.obtenerNombreMes(tiempo),
								  transformacion[2].obtenerU(tiempo)+10,
								  transformacion[2].obtenerVMax()+11);
		}
		
		// Dibujar el eje calidad promedio.
		grafico2.drawLine(transformacion[2].obtenerUMin(),
						  transformacion[2].obtenerVMin(),
						  transformacion[2].obtenerUMin(),
						  transformacion[2].obtenerVMax());
		
		// Dibujar el nombre del eje calidad promedio.
		grafico2.drawString("CALIDAD PROMEDIO",
						  transformacion[2].obtenerUMin()+4,
						  transformacion[2].obtenerVMin()-1);
		
		// Ciclo para recorrer las calidades promedios.
		for (double calidad=CALIDAD_PROMEDIO_FINAL;
			 calidad>=CALIDAD_PROMEDIO_INICIAL;
			 calidad-=CALIDAD_PROMEDIO_RESOLUCION)
		{
			// Muy Alta.
			if (calidad == TipoCalidad.CALIDAD_MUY_ALTA)
				textoCalidad = "Muy alta";
			
			// Alta.
			else
			if (calidad == TipoCalidad.CALIDAD_ALTA)
				textoCalidad = "Alta";
			
			// Media.
			else
			if (calidad == TipoCalidad.CALIDAD_MEDIA)
				textoCalidad = "Media";
			
			// Baja.
			else
			if (calidad == TipoCalidad.CALIDAD_BAJA)
				textoCalidad = "Baja";
			
			// Muy Baja.
			else
			if (calidad == TipoCalidad.CALIDAD_MUY_BAJA)
				textoCalidad = "Muy baja";
			
			grafico2.drawLine(transformacion[2].obtenerUMin()-2,
							  transformacion[2].obtenerV(calidad),
							  transformacion[2].obtenerUMin()+2,
							  transformacion[2].obtenerV(calidad));
			grafico2.drawString(textoCalidad,2,
								transformacion[2].obtenerV(calidad));
		}
	}
	
	/**
	 * Método que grafica los puntos de las curvas de las dinámicas temporales
	 * de los super-individuos. Se recorre todo el arreglo de las estadísticas y
	 * se muestra su información actual. Cada recurso tiene un color específico
	 * de visualización en pantalla.
	 */
	private void graficarPuntos()
	{
		// Obtener la estadística del autómata celular.
		Estadistica estadistica = panelTemporalEcosistema.
								  frameEcosistema.
								  obtenerAutomataCelular().
								  obtenerEstadistica();
		
		// Cuando la estadística tiene recursos y tiempos que mostrar.
		if (estadistica.obtenerCantidadRecursos() > 0 &&
			estadistica.obtenerCantidadTiempos() > 0)
		{
			// Variables temporales.
			int recurso;
			int cantidadRecursos;
			int diaInicial=0;
			int diaActual=0;
			int diaInferior=0;
			int diaSuperior=0;
			int diaEstadistica;
			int diaNormal;
			double mes;
			double biomasa;
			double abundancia;
			double calidadPromedio;
			int u0, v0;
			int u1, v1;
			int u2, v2;
			int[] diaMes = new int[2];
			
			// Obtener el tiempo.
			Tiempo tiempo =
			panelTemporalEcosistema.frameEcosistema.obtenerTiempo();
			
			int anio = panelTemporalEcosistema.obtenerAnio();
			int anioInicial = tiempo.obtenerFechaInicial()[2];
			int anioActual = tiempo.obtenerFechaActual()[2];
			
			diaMes[0] = tiempo.obtenerFechaActual()[0];
			diaMes[1] = tiempo.obtenerFechaActual()[1];
			diaActual = Servicio.obtenerDia(diaMes);
			diaInicial = 1;
			
			if (anio == anioInicial)
			{
				diaMes[0] = tiempo.obtenerFechaInicial()[0];
				diaMes[1] = tiempo.obtenerFechaInicial()[1];
				diaInicial = Servicio.obtenerDia(diaMes);
				
				diaInferior = 0;
				if (Servicio.obtenerDias(tiempo.obtenerFechaInicial(),
										 tiempo.obtenerFechaActual()) > 365)
					diaSuperior = 365 - diaActual + 1;
				else diaSuperior = tiempo.obtenerTicReloj()-1;
			}
			
			else
			if (anioInicial < anio && anio < anioActual)
			{
				diaMes[0] = tiempo.obtenerFechaInicial()[0];
				diaMes[1] = tiempo.obtenerFechaInicial()[1];
				diaInicial = Servicio.obtenerDia(diaMes);
				
				diaInferior = (anio - anioInicial)*365 - diaInicial + 1;
				diaSuperior = diaInferior + 365 - 1;
			}
			
			else
			if (anio == anioActual)
			{
				diaMes[0] = tiempo.obtenerFechaActual()[0];
				diaMes[1] = tiempo.obtenerFechaActual()[1];
				diaActual = Servicio.obtenerDia(diaMes);
				
				diaMes[0] = tiempo.obtenerFechaInicial()[0];
				diaMes[1] = tiempo.obtenerFechaInicial()[1];
				int diaInicio = Servicio.obtenerDia(diaMes);
				
				diaSuperior = (anio - anioInicial)*365;
				diaInferior = diaSuperior - diaInicio + 1;
			}
			
			// Obtener la cantidad de recuros.
			cantidadRecursos = Recurso.obtenerCantidadRecursos();
			
			// Ciclo para recorrer los recursos.
			for (recurso=0; recurso<cantidadRecursos; recurso++)
			{
				// Cuando hay que mostrar el recurso.
				if (ConfiguracionRecurso.obtenerTemporal(recurso).
					equalsIgnoreCase("Si"))
				{
					// Setear el color propio del recurso.
					grafico2.setColor(new Color(
									  Recurso.obtenerColorRojo(recurso),
									  Recurso.obtenerColorVerde(recurso),
									  Recurso.obtenerColorAzul(recurso)));
					
					diaNormal = diaInicial;
					
					// Ciclo para recorrer los tiempos.
					for (diaEstadistica=diaInferior;
						 diaEstadistica<=diaSuperior;
						 diaEstadistica++)
					{
						// Obtener el mes del día.
						mes = Servicio.obtenerMes(diaNormal);
						
						// Obtener la biomasa del recurso en el día.
						biomasa = estadistica.obtenerBiomasa(recurso,
								  diaEstadistica);
						
						// Obtener la abundancia del recurso en el día.
						abundancia = estadistica.obtenerAbundancia(recurso,
									 diaEstadistica);
						
						// Obtener la calidad promedio del recurso en el día.
						calidadPromedio = estadistica.
										  obtenerCalidadPromedio(recurso,
										  diaEstadistica);
						
						// Transformar la coordenadas del plano biomasa vs
						// tiempo a coordenadas del sistema portal de visión.
						u0 = transformacion[0].obtenerU(mes);
						v0 = transformacion[0].obtenerV(biomasa/BIOMASA_ESCALA);
						
						// Transformar la coordenadas del plano abundancia vs
						// tiempo a coordenadas del sistema portal de visión.
						u1 = transformacion[1].obtenerU(mes);
						v1 = transformacion[1].obtenerV(
							 abundancia/ABUNDANCIA_ESCALA);
						
						// Transformar la coordenadas del plano calidad promedio
						// vs tiempo a coordenadas del sistema portal de visión.
						u2 = transformacion[2].obtenerU(mes);
						v2 = transformacion[2].obtenerV(calidadPromedio);
						
						// Cuando estamos dentro del plano biomasa vs tiempo.
						if (transformacion[0].obtenerUMin() <= u0 &&
							u0 <= transformacion[0].obtenerUMax())
							grafico2.drawRect(u0, v0, 1, 1);
						
						// Cuando estamos dentro del plano abundancia vs tiempo.
						if (transformacion[1].obtenerUMin() <= u1 &&
							u1 <= transformacion[1].obtenerUMax())
							grafico2.drawRect(u1, v1, 1, 1);
						
						// Cuando estamos dentro del plano calidad promedio vs
						// tiempo.
						if (transformacion[2].obtenerUMin() <= u2 &&
							u2 <= transformacion[2].obtenerUMax())
							grafico2.drawRect(u2, v2, 1, 1);
						
						if (diaNormal >= 365)
							diaNormal = 1;
						else diaNormal++;
					}
				}
			}
		}
	}
}