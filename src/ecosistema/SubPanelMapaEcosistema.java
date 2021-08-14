/**
 * @(#)SubPanelMapaEcosistema.java 2.0 01/03/05
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
import java.util.Vector;
import jp.go.ipa.jgcl.JgclPoint3D;
import jp.go.ipa.jgcl.JgclCartesianPoint3D;

/**
 * Esta clase extiende de la clase JPanel. En esta clase se muestra al usuario
 * el comportamiento espacial (geográfico) del recurso estudiado en su
 * ecosistema. Esta clase se especializa en realizar los gráficos del sistema en
 * estudio en dos dimensiones (2D). En la gráfica se utiliza la técnica
 * denominada doble-buffer, con el objeto de mitigar el flasheo en la pantalla
 * cuando se está haciendo la simulación, y además, acelerar la animación. En la
 * simulación también se permite el movimiento de coordenadas del foco de
 * visualización en dirección: Noroeste, Norte, Noreste, Oeste, Centro, Este,
 * Suroeste, Sur, y Sureste. Además la simulación permite las operaciones de
 * zoom: Acercar, Alejar y Restablecer.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Image
 * @see Graphics
 * @see Graphics2D
 * @see Color
 * @see Font
 * @see JPanel
 * @see Vector
 * @see JgclPoint3D
 * @see JgclCartesianPoint3D
 * @see Servicio
 * @see PanelEspacialEcosistema
 * @see TransformacionGrafica
 * @see ConfiguracionEspacial
 * @see Vista
 * @see Mapa
 * @see AutomataCelular
 * @see Vector
 * @see Espacio
 * @see SuperIndividuo
 * @see Celda
 * @see TransformacionModelo
 * @see Recurso
 * @see InterfacePanel
 */
public class SubPanelMapaEcosistema extends JPanel implements InterfacePanel
{
	/** La resolución de minutos para la regla de las longitudes. */
	private static final int LONGITUD_RESOLUCION = 5;
	
	/** La resolución de minutos para la regla de las latitudes. */
	private static final int LATITUD_RESOLUCION = 5;
	
	/** La resolución de metros para la regla de las altitudes. */
	private static final int ALTITUD_RESOLUCION = 100;
	
	/** La cantidad de grados para el desplazamiento en longitud. */
	private static final double LONGITUD_DESPLAZAMIENTO =
													Servicio.obtenerGrados(1.0);
	
	/** La cantidad de grados para el desplazamiento en latitud. */
	private static final double LATITUD_DESPLAZAMIENTO =
													Servicio.obtenerGrados(1.0);
	
	/** La cantidad de metros para el desplazamiento en altitud. */
	private static final double ALTITUD_DESPLAZAMIENTO = 10;
	
	/** La cantidad de grados para el zoom en longitud. */
	private static final double LONGITUD_ZOOM = Servicio.obtenerGrados(15.0);
	
	/** La cantidad de grados para el zoom en latitud. */
	private static final double LATITUD_ZOOM = Servicio.obtenerGrados(15.0);
	
	/** La cantidad de metros para el zoom en altitud. */
	private static final double ALTITUD_ZOOM = 100;
	
	/** Los colores para los rangos de altitudes del mapa. */
	private static final Color[] COLOR_ALTITUD = {new Color(79, 169, 206),
												  new Color(103, 188, 216),
												  new Color(127, 197, 223),
												  new Color(157, 216, 232),
												  new Color(193, 230, 239),
												  new Color(204, 236, 233),
												  new Color(231, 242, 238),
												  new Color(209, 232, 160),
												  new Color(179, 213, 127),
												  new Color(156, 200, 123),
												  new Color(0, 124, 63)};
	
	/** El color del agua del mapa. */
	private static final Color COLOR_AGUA = Color.BLUE;
	
	/** El color del suelo del mapa. */
	private static final Color COLOR_SUELO = new Color(153, 102, 0);
	
	/** El tipo de fuente usada en las reglas. */
	private static final Font FUENTE_REGLA = new Font("Arial", Font.PLAIN, 9);
	
	/** El puntero al panel que contiene a este panel. */
	public PanelEspacialEcosistema panelEspacialEcosistema;
	
	/** La imagen usada en el doble-buffer. */
	private Image imagen;
	
	/** El gráfico usado en el doble-buffer. */
    private Graphics pantalla;
    
    /** El gráfico usado para las funciones gráficas del sistema. */
    private Graphics2D grafico2;
    
    /** Las transformaciones gráficas usadas en los gráficos del panel. */
	private TransformacionGrafica[] transformacion;
	
	/** Los mínimos valores en el eje U del sistema mundo visión. */
    private int[] uMin;
    
    /** Los máximos valores en el eje U del sistema mundo visión. */
    private int[] uMax;
	
	/**
	 * Las diferencias entre los máximos y los mínimos valores en el eje U del
	 * sistema mundo visión.
	 */
	private int[] uDel;
	
	/** Los mínimos valores en el eje V del sistema mundo visión. */
	private int[] vMin;
	
	/** Los máximos valores en el eje V del sistema mundo visión. */
	private int[] vMax;
	
	/**
	 * Las diferencias entre los máximos y los mínimos valores en el eje V del
	 * sistema mundo visión.
	 */
	private int[] vDel;
	
	/** El espaciado entre minutos en el eje U del sistema mundo visión. */
	private double uEsp;
	
	/** El espaciado entre minutos en el eje V del sistema mundo visión. */
	private double vEsp;
	
	/** El rango en el eje X del sistema mundo real. */
	private	double xDel;
	
	/** El rango en el eje Y del sistema mundo real. */
	private	double yDel;
	
	/** El valor de la longitud usada en la vista Latitud-Altitud. */
	private double longitudPosicion;
	
	/** El valor de la latitud usada en la vista Longitud-Altitud. */
	private double latitudPosicion;
	
	/** El valor de la altitud usada en la vista Longitud-Latitud. */
	private double altitudPosicion;
	
	/**
     * Método constructor en donde se inicializa el mapa de estudio, se
     * establece el tamaño de este panel, se inicializa la transformación
     * gráfica usada en el sistema de despliegue gráfico y se inicializan los
     * componentes utilizados en la técnica del doble-buffer.
     *
     * @param panelEspacialEcosistema Un objeto que hace referencia al panel
     *                                contenedor de este sub-panel.
     * @param u1 La posición inicial en el eje U para este panel.
     * @param v1 La posición inicial en el eje V para este panel.
     * @param u2 La posición final (+1) en el eje U para este panel.
     * @param v2 La posición final (+1) en el eje V para este panel.
     */
	public SubPanelMapaEcosistema(
								PanelEspacialEcosistema panelEspacialEcosistema,
								int u1, int v1, int u2, int v2)
	{
		// Inicializar el panel padre.
		this.panelEspacialEcosistema = panelEspacialEcosistema;
		
		// Configurar.
		configurarElementosEspeciales(u1, v1, u2, v2);
		configurarPanel();
		configurarEventos();
	}
	
	/**
	 * Método en donde se inicializan los objetos que se usan en todo este
	 * panel.
	 *
     * @param u1 La posición inicial en el eje U para este panel.
     * @param v1 La posición inicial en el eje V para este panel.
     * @param u2 La posición final (+1) en el eje U para este panel.
     * @param v2 La posición final (+1) en el eje V para este panel.
	 */
	private void configurarElementosEspeciales(int u1, int v1, int u2, int v2)
	{
		// Asignar tamaño al arreglo de transformaciones gráficas.
		transformacion = new TransformacionGrafica[3];
		
		// Inicializar los valores de las transformaciones gráficas.
		int i;
		for (i=0; i<3; i++)
			transformacion[i] = new TransformacionGrafica();
		
		// Inicializar los valores del sistema mundo visión de las
		// transformacion gráficas.
		transformacion[0].establecerMundoVision(u1, u2, v1, v2);
		transformacion[1].establecerMundoVision(u1+35, u2-35, v1+35, v2-35);
		transformacion[2].establecerMundoVision(u1+40, u2-40, v1+40, v2-40);
		
		// Asignar tamaño al arreglo de coordenadas en U.
		uMin = new int[3];
		uMax = new int[3];
		uDel = new int[3];
		
		// Asignar tamaño al arreglo de coordenadas en V.
		vMin = new int[3];
		vMax = new int[3];
		vDel = new int[3];
		
		// Inicializar los valores de las coordenadas en U y V.
		for (i=0; i<3; i++)
		{
			// Inicializar los valores de las coordenadas en U.
			uMin[i] = transformacion[i].obtenerUMin();
			uMax[i] = transformacion[i].obtenerUMax();
			uDel[i] = transformacion[i].obtenerUDel();
			
			// Inicializar los valores de las coordenadas en V.
			vMin[i] = transformacion[i].obtenerVMin();
			vMax[i] = transformacion[i].obtenerVMax();
			vDel[i] = transformacion[i].obtenerVDel();
		}
		
		// Inicializar los valores del sistema mundo real de las
		// transformaciones gráficas.
		cambiarMundoReal();
				
		// Inicializar los componentes del doble-buffer.
		iniciarDobleBuffer();
	}
	
	/**
	 * Método que permite inicializar los valores del sistema mundo real de las
	 * transformaciones gráficas utilizadas en este panel. Dependiendo de la
	 * vista del mapa, se establecen los valores para los valores del sistema
	 * mundo real en las transformaciones gráficas. Este método también
	 * establece los valores a los espaciados entre minutos en el eje U y V del
	 * sistema mundo visión. Estos espaciados sirven como incremento en el
	 * recorrido en longitud, latitud y altitud del mapa.
	 */
	public void cambiarMundoReal()
	{
		// Dependiendo de la vista, inicializar los valores del sistema mundo
		// real de la transformación gráfica.
		switch (ConfiguracionEspacial.obtenerVista())
		{
			// Vista en Longitud-Latitud.
			case Vista.LONGITUD_LATITUD:
			
			// Establecer los valores al mundo real.
			transformacion[2].establecerMundoReal(
			ConfiguracionEspacial.obtenerLongitudFinal(),
			ConfiguracionEspacial.obtenerLongitudInicial(),
			ConfiguracionEspacial.obtenerLatitudInicial(),
			ConfiguracionEspacial.obtenerLatitudFinal());
			
			// Calcular el rango en el eje X del sistema mundo real.
			xDel = Servicio.obtenerMinutos(
							ConfiguracionEspacial.obtenerLongitudInicial(),
							ConfiguracionEspacial.obtenerLongitudFinal());
			
			// Calcular el rango en el eje Y del sistema mundo real.
			yDel = Servicio.obtenerMinutos(
							ConfiguracionEspacial.obtenerLatitudInicial(),
							ConfiguracionEspacial.obtenerLatitudFinal());
			
			// Calcular el espaciado en U y V.
			uEsp = uDel[2]/xDel;
			vEsp = vDel[2]/yDel;
			
			break;
			
			// Vista en Longitud-Altitud.
			case Vista.LONGITUD_ALTITUD:
			
			// Establecer los valores al mundo real.
			transformacion[2].establecerMundoReal(
			ConfiguracionEspacial.obtenerLongitudFinal(),
			ConfiguracionEspacial.obtenerLongitudInicial(),
			ConfiguracionEspacial.obtenerAltitudFinal(),
			ConfiguracionEspacial.obtenerAltitudInicial());
			
			// Calcular el rango en el eje X del sistema mundo real.
			xDel = Servicio.obtenerMinutos(
							ConfiguracionEspacial.obtenerLongitudInicial(),
							ConfiguracionEspacial.obtenerLongitudFinal());
			
			// Calcular el rango en el eje Y del sistema mundo real.
			yDel = Servicio.obtenerDistancia(
							ConfiguracionEspacial.obtenerAltitudInicial(),
							ConfiguracionEspacial.obtenerAltitudFinal());
			
			// Calcular el espaciado en U y V.
			uEsp = uDel[2]/xDel;
			vEsp = vDel[2]/(yDel/ALTITUD_RESOLUCION);
			
			break;
			
			// Vista en Latitud-Altitud.
			case Vista.LATITUD_ALTITUD:
			
			// Establecer los valores al mundo real.
			transformacion[2].establecerMundoReal(
			ConfiguracionEspacial.obtenerLatitudInicial(),
			ConfiguracionEspacial.obtenerLatitudFinal(),
			ConfiguracionEspacial.obtenerAltitudFinal(),
			ConfiguracionEspacial.obtenerAltitudInicial());
			
			// Calcular el rango en el eje X del sistema mundo real.
			xDel = Servicio.obtenerMinutos(
							ConfiguracionEspacial.obtenerLatitudInicial(),
							ConfiguracionEspacial.obtenerLatitudFinal());
			
			// Calcular el rango en el eje Y del sistema mundo real.
			yDel = Servicio.obtenerDistancia(
							ConfiguracionEspacial.obtenerAltitudInicial(),
							ConfiguracionEspacial.obtenerAltitudFinal());
			
			// Calcular el espaciado en U y V.
			uEsp = uDel[2]/xDel;
			vEsp = vDel[2]/(yDel/ALTITUD_RESOLUCION);
			
			break;
		}
		
		// Inicializar los valores de los factores de las transformaciones
		// gráficas.
		transformacion[2].establecerFactores();
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
			// el tamaño del mundo visión.
			imagen = createImage(uDel[0], vDel[0]);
			
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
	 */
	public void configurarPanel()
	{
		setBounds(uMin[0], vMin[0], uMax[0] + 1, vMax[0] + 1);
	}
	
	/**
	 * Método que configura los componentes GUI que tiene este panel. En
	 * particular este método no se se implementa.
	 */
	public void configurarComponentes()
	{
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a este panel de simulación
	 * espacial. En particular se incorpora el escuchador de eventos del tipo
	 * EventoMouse a este JPanel.
	 */
	public void configurarEventos()
	{
		// Crear los escuchadores de eventos.
		EventoMouse eventoMouse = new EventoMouse(this);
		
		// Incorporar el escuchador de eventos al panel.
		addMouseMotionListener(eventoMouse);
	}
	
	/**
	 * Método sobrecargado que permite utilizar la técnica del doble-buffer, con
	 * el objeto de reducir el flasheo de la imagen graficada y simulada en la
	 * pantalla. Se llama al método paintComponent() para graficar el espacio
	 * marino y los super-individuos en movimiento. Este método es llamado por
	 * defecto cada vez que se muestra este panel.
	 *
	 * @param grafico El gráfico utilizado en el panel.
	 */
	public void paint(Graphics grafico)
	{
		// Cuando si se puede usar el doble-buffer.
		if (pantalla != null)
		{
			paintComponent(pantalla);
			grafico.drawImage(imagen, uMin[0], vMin[0], this);
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
	 * Método que, dependiendo de la opción del tipo de vista del mapa, llama
	 * a los métodos que grafican el ecosistema en sus diferentes vistas.
	 * Primero se transforma el gráfico utilizado en el panel a Graphics2D para
	 * un manejo eficaz de los gráficos en la clase. Luego, dependiendo de la
	 * opción del tipo de vista del mapa, se grafica el mapa.
	 *
	 * @param grafico El gráfico utilizado en el panel.
	 */
	public void paintComponent(Graphics grafico)
	{
		// Castear el gráfico para un mejor manejo.
		grafico2 = (Graphics2D) grafico;
		
		// Dependiendo de la vista, graficar el mapa.
		switch (ConfiguracionEspacial.obtenerVista())
		{
			// Vista en Longitud-Latitud.
			case Vista.LONGITUD_LATITUD:
			graficarMapaLongitudLatitud();
			break;
			
			// Vista en Longitud-Altitud.
			case Vista.LONGITUD_ALTITUD:
			graficarMapaLongitudAltitud();
			break;
			
			// Vista en Latitud-Altitud.
			case Vista.LATITUD_ALTITUD:
			graficarMapaLatitudAltitud();
			break;
		}
		
		// Graficar los super-individuos.
		graficarSuperIndividuos();
		
		// Graficar el marco del panel.
		graficarMarcoPanel();
	}
	
	/**
	 * Método que grafica el mapa estudiado sin considerar la dimensión de la
	 * altitud. Es decir, se deja fija la altitud (en 0 mt) y solo se grafica el
	 * plano compuesto por la longitud y la latitud. La longitud se muestra en
	 * el eje U del sistema portal de visión. La latitud se muestra en el eje V
	 * del sistema portal de visión.
	 */
	private void graficarMapaLongitudLatitud()
	{
		// La altitud usada en la vista Longitud-Latitud.
		altitudPosicion = 0;
		
		// Graficar las altitudes del mapa.
		graficarAltitudes();
		
		// Graficar las reglas de longitudes y latitudes del mapa.
		graficarFondoPanel();
		graficarReglaLongitudes();
		graficarReglaLatitudes("v");
	}
	
	/**
	 * Método que grafica el mapa estudiado sin considerar la dimensión de la
	 * latitud. Es decir, se deja fija la latitud y solo se grafica el plano
	 * compuesto por la longitud y la altitud. La longitud se muestra en el eje
	 * U del sistema portal de visión. La altitud se muestra en el eje V del
	 * sistema portal de visión.
	 */
	private void graficarMapaLongitudAltitud()
	{
		// La latitud usada en la vista Longitud-Altitud.
		latitudPosicion = ConfiguracionEspacial.obtenerLatitudInicial();
		
		// Graficar las latitudes del mapa.
		graficarLatitudes();
		
		// Graficar las reglas de longitudes y altitudes del mapa.
		graficarFondoPanel();
		graficarReglaLongitudes();
		graficarReglaAltitudes();
	}
	
	/**
	 * Método que grafica el mapa estudiado sin considerar la dimensión de la
	 * longitud. Es decir, se deja fija la longitud y solo se grafica el plano
	 * compuesto por la latitud y la altitud. La latitud se muestra en el eje U
	 * del sistema portal de visión. La altitud se muestra en el eje V del
	 * sistema portal de visión.
	 */
	private void graficarMapaLatitudAltitud()
	{
		// La longitud usada en la vista Latitud-Altitud.
		longitudPosicion = ConfiguracionEspacial.obtenerLongitudInicial();
		
		// Graficar las longitudes del mapa.
		graficarLongitudes();
		
		// Graficar las reglas de latitudes y altitudes del mapa.
		graficarFondoPanel();
		graficarReglaLatitudes("u");
		graficarReglaAltitudes();
	}
	
	/**
	 * Método que muestra las altitudes del mapa de estudio. Se hace un barrido
	 * en longitud y latitud del mapa de estudio y se pintan de diferentes
	 * colores los rangos de altitudes del mapa.
	 */
	private void graficarAltitudes()
	{
		// Sirven para guardar las coordenadas del punto obtenido del mapa.
		double longitud, latitud, altitud;
		
		// Recorrido en longitud.
		for (int u=uMin[2]; u<uMax[2]; u+=uEsp)
		{
			// Recorrido en latitud.
			for (int v=vMin[2]; v<vMax[2]; v+=vEsp)
			{
				// Transformar las coordenadas a mundo real.
				longitud = transformacion[2].obtenerX(u);
				latitud = transformacion[2].obtenerY(v);
				
				// Cuando la longitud y la latitud están en el rango del área de
				// estudio.
				if (Mapa.obtenerLongitudInicial() <= longitud &&
					longitud <= Mapa.obtenerLongitudFinal() &&
					Mapa.obtenerLatitudInicial() <= latitud &&
					latitud <= Mapa.obtenerLatitudFinal())
				{
					// Obtener la altitud.
					altitud = Mapa.obtenerAltitud(longitud, latitud);
					
					// La altitud está en el rango (-infinito; -6000).
					if (altitud<-6000)
						grafico2.setColor(COLOR_ALTITUD[0]);
					
					// La altitud está en el rango [-6000; -4000).
					else
					if (-6000<=altitud && altitud<-4000)
						grafico2.setColor(COLOR_ALTITUD[1]);
					
					// La altitud está en el rango [-4000; -2000).
					else
					if (-4000<=altitud && altitud<-2000)
						grafico2.setColor(COLOR_ALTITUD[2]);
					
					// La altitud está en el rango [-2000; -1000).
					else
					if (-2000<=altitud && altitud<-1000)
						grafico2.setColor(COLOR_ALTITUD[3]);
					
					// La altitud está en el rango [-1000; -500).
					else
					if (-1000<=altitud && altitud<-500)
						grafico2.setColor(COLOR_ALTITUD[4]);
					
					// La altitud está en el rango [-500; -200).
					else
					if (-500<=altitud && altitud<-200)
						grafico2.setColor(COLOR_ALTITUD[5]);
					
					// La altitud está en el rango [-200; 0).
					else
					if (-200<=altitud && altitud<0)
						grafico2.setColor(COLOR_ALTITUD[6]);
					
					// La altitud está en el rango [0; 300).
					else
					if (0<=altitud && altitud<300)
						grafico2.setColor(COLOR_ALTITUD[7]);
					
					// La altitud está en el rango [300; 600).
					else
					if (300<=altitud && altitud<600)
						grafico2.setColor(COLOR_ALTITUD[8]);
					
					// La altitud está en el rango [600; 1000).
					else
					if (600<=altitud && altitud<1000)
						grafico2.setColor(COLOR_ALTITUD[9]);
					
					// La altitud está en el rango [1000; +infinito).
					else
					if (1000<=altitud)
						grafico2.setColor(COLOR_ALTITUD[10]);
					
					// Pintar el punto.
					grafico2.fillRect(u, v, (int)uEsp, (int)vEsp);
				}
			}
		}
	}
	
	/**
	 * Método que muestra las latitudes del mapa de estudio. Se hace un barrido
	 * en longitud del mapa de estudio. La latitud que se ocupa es el valor de
	 * la latitud usada como posición fija de visualización.
	 */
	private void graficarLatitudes()
	{
		// Sirven para guardar las coordenadas del punto obtenido del mapa.
		double longitud, latitud, altitud;
		int v;
		
		// Obtener la latitud.
		latitud = latitudPosicion;
		
		// Recorrido en longitud.
		for (int u=uMin[2]; u<uMax[2]; u+=uEsp)
		{
			// Transformar las coordenadas a mundo real.
			longitud = transformacion[2].obtenerX(u);
			
			// Obtener el punto del mapa.
			altitud = Mapa.obtenerAltitud(longitud, latitud);
			v = transformacion[2].obtenerV(altitud);
			
			// Pintar el agua.
			grafico2.setColor(COLOR_AGUA);
			grafico2.fillRect(u, vMin[2], (int) (u+uEsp), v);
			
			// Pintar la tierra.
			grafico2.setColor(COLOR_SUELO);
			grafico2.fillRect(u, v, (int) (u+uEsp), vMax[2]);
		}
	}
	
	/**
	 * Método que muestra las longitudes del mapa de estudio. Se hace un barrido
	 * en latitud y altitud del mapa de estudio. La longitud que se ocupa es el
	 * valor de la longitud usada como posición fija de visualización.
	 */
	private void graficarLongitudes()
	{
		// Recorrido en latitud.
		for (int u=uMin[2]; u<uMax[2]; u+=uEsp)
		{
			// Pintar el agua.
			grafico2.setColor(COLOR_AGUA);
			grafico2.fillRect(u, vMin[2], (int) (u+uEsp), vMax[2]);
		}
	}
	
	/**
	 * Método que grafica el fondo que tiene este panel del mapa geográfico. Se
	 * pintan cuatro rectángulos blancos (aparte del mapa), con el objeto de que
	 * el usuario perciba de mejor manera las coordenadas geográficas.
	 */
	private void graficarFondoPanel()
	{
		// Dibujar los rectágulos horizontales.
		grafico2.setColor(Color.WHITE);
		grafico2.fillRect(uMin[0], vMin[0], uDel[0], vMin[2]-vMin[0]);
		grafico2.fillRect(uMin[0], vMax[2], uDel[0], vMax[0]-vMax[2]);
		grafico2.fillRect(uMin[0], vMin[2], uMin[2]-uMin[0], vDel[2]);
		grafico2.fillRect(uMax[2], vMin[2], uMax[0]-uMax[2], vDel[2]);
	}
	
	/**
	 * Método que grafica una regla con las longitudes actuales del sistema
	 * simulado en el eje U del sistema mundo visión.
	 */
	private void graficarReglaLongitudes()
	{
		// Obtener el grado final de la regla.
		Longitud fin = new Longitud(
							ConfiguracionEspacial.obtenerLongitudFinal(),
							ConfiguracionEspacial.obtenerTipoLongitudFinal());
		
		// Obtener el grado y minuto final del foco de visualización.
		int grado = fin.obtenerGrado();
		int minuto = fin.obtenerMinuto();
		
		// Establecer la fuente.
		grafico2.setFont(FUENTE_REGLA);
		
		// Ciclo para dibujar las longitudes.
		for (double u=uMin[2]; u<=uMax[2]; u+=uEsp, minuto--)
		{
			// Dibujar las líneas verticales.
			grafico2.setColor(Color.BLACK);
			grafico2.drawLine((int) u, vMin[1], (int) u, vMin[2]);
			grafico2.drawLine((int) u, vMax[1], (int) u, vMax[2]);
			
			// Cuando el minuto es múltiplo de la resolución en longitud.
			if (minuto%LONGITUD_RESOLUCION == 0)
			{
				// Cuando el minuto es distinto de 0 y 60.
				if (minuto != 0 && minuto != 60)
				{
					// Mostrar el minuto.
					grafico2.setColor(Color.BLACK);
					grafico2.drawString(""+minuto+"'", (int) u, vMin[0]+25);
					grafico2.drawString(""+minuto+"'", (int) u, vMax[0]-15);
				}
				
				// Cuando el minuto es igual a 0 ó 60.
				else
				{
					// Mostrar el grado.
					grafico2.setColor(Color.RED);
					grafico2.drawString(""+grado+"º", (int) u, vMin[0]+25);
					grafico2.drawString(""+grado+"º", (int) u, vMax[0]-15);
				}
			}
			
			// Cuando el minuto es igual a 0.
			if (minuto == 0)
			{
				// Setear el minuto en 60 y decrementar el grado para el
				// próximo paso de iteración.
				minuto = 60;
				grado--;
			}
		}
	}
	
	/**
	 * Método que grafica una regla con las latitudes actuales del sistema
	 * simulado en el eje U o en el eje V del sistema mundo visión, dependiendo
	 * del tipo de vista que se tiene actualmente.
	 *
	 * @param eje Un string que indica que eje del portal de visión corresponde
	 *            a las latitudes.
	 */
	private void graficarReglaLatitudes(String eje)
	{
		// Obtener el grado inicial de la regla.
		Latitud inicio = new Latitud(
							ConfiguracionEspacial.obtenerLatitudInicial(),
							ConfiguracionEspacial.obtenerTipoLatitudInicial());
		
		// Obtener el grado y minuto inicial del foco de visualización.
		int grado = inicio.obtenerGrado();
		int minuto = inicio.obtenerMinuto();
		
		// Establecer la fuente.
		grafico2.setFont(FUENTE_REGLA);
		
		// Cuando las latitudes se muestran en el eje U del sistema portal de
		// visión.
		if (eje.compareTo("u") == 0)
		{
			// Ciclo para dibujar las latitudes.
			for (double u=uMin[2]; u<=uMax[2]; u+=uEsp, minuto++)
			{
				// Dibujar las líneas verticales.
				grafico2.setColor(Color.BLACK);
				grafico2.drawLine((int) u, vMin[1], (int) u, vMin[2]);
				grafico2.drawLine((int) u, vMax[1], (int) u, vMax[2]);
				
				// Cuando el minuto es múltiplo de la resolución en latitud.
				if (minuto%LATITUD_RESOLUCION == 0)
				{
					// Cuando el minuto es distinto de 0 y 60.
					if (minuto != 0 && minuto != 60)
					{
						// Mostrar el minuto.
						grafico2.setColor(Color.BLACK);
						grafico2.drawString(""+minuto+"'", (int) u, vMin[0]+25);
						grafico2.drawString(""+minuto+"'", (int) u, vMax[0]-15);
					}
					
					// Cuando el minuto es igual a 0 ó 60.
					else
					{
						// Cuando el minuto es igual a 60.
						if (minuto == 60)
						{
							// Setear el minuto en 0 e incrementar los grados para
							// el próximo paso de iteración.
							minuto = 0;
							grado++;
						}
						
						// Mostrar el grado.
						grafico2.setColor(Color.RED);
						grafico2.drawString(""+grado+"º", (int) u, vMin[0]+25);
						grafico2.drawString(""+grado+"º", (int) u, vMax[0]-15);
					}
				}
			}
		}
		
		// Cuando las latitudes se muestran en el eje V del sistema portal de
		// visión.
		else
		{
			// Ciclo para dibujar las latitudes.
			for (double v=vMin[2]; v<=vMax[2]; v+=vEsp, minuto++)
			{
				// Dibujar las líneas horizontales.
				grafico2.setColor(Color.BLACK);
				grafico2.drawLine(uMin[1], (int) v, uMin[2], (int) v);
				grafico2.drawLine(uMax[1], (int) v, uMax[2], (int) v);
				
				// Cuando el minuto es múltiplo de la resolución en latitud.
				if (minuto%LATITUD_RESOLUCION == 0)
				{
					// Cuando el minuto es distinto de 0 y 60.
					if (minuto != 0 && minuto != 60)
					{
						// Mostrar el minuto.
						grafico2.setColor(Color.BLACK);
						grafico2.drawString(""+minuto+"'", uMin[0]+15, (int) v);
						grafico2.drawString(""+minuto+"'", uMax[0]-25, (int) v);
					}
					
					// Cuando el minuto es igual a 0 ó 60.
					else
					{
						// Cuando el minuto es igual a 60.
						if (minuto == 60)
						{
							// Setear el minuto en 0 e incrementar los grados para
							// el próximo paso de iteración.
							minuto = 0;
							grado++;
						}
						
						// Mostrar el grado.
						grafico2.setColor(Color.RED);
						grafico2.drawString(""+grado+"º", uMin[0]+15, (int) v);
						grafico2.drawString(""+grado+"º", uMax[0]-25, (int) v);
					}
				}
			}
		}
	}
	
	/**
	 * Método que grafica una regla con las altitudes actuales del sistema
	 * simulado en el eje U o en el eje V del sistema mundo visión, dependiendo
	 * del tipo de vista que se tiene actualmente.
	 */
	private void graficarReglaAltitudes()
	{
		// Obtener el metro final de la regla.
		double metro = ConfiguracionEspacial.obtenerAltitudFinal();
		
		// Establecer la fuente.
		grafico2.setFont(FUENTE_REGLA);
		
		// Ciclo para dibujar las altitudes.
		for (double v=vMin[2]; v<=vMax[2]; v+=vEsp, metro-=100)
		{
			// Dibujar las líneas horizontales.
			grafico2.setColor(Color.BLACK);
			grafico2.drawLine(uMin[1], (int) v, uMin[2], (int) v);
			grafico2.drawLine(uMax[1], (int) v, uMax[2], (int) v);
			
			// Mostrar la altitud.
			grafico2.drawString((int) metro+" mt", uMin[0]+2, (int) v);
			grafico2.drawString((int) metro+" mt", uMax[0]-33, (int) v);
		}
	}
	
	/**
	 * Método que grafica los super-individuos en el mapa de estudio. Se recorre
	 * todo el vector de rastreo actual de ubicaciones de super-individuos y se
	 * muestra su posición actual en el mapa. Cada recurso tiene un color
	 * específico de visualización en pantalla. Se muestran solo los
	 * super-individuos que se encuentran en estado sin huevos o con huevos.
	 */
	private void graficarSuperIndividuos()
	{
		// Variables temporales.
		Celda ubicacion;
		SuperIndividuo superIndividuo;
		int recurso;
		int estado;
		
		// Obtener el autómata celular.
		AutomataCelular automataCelular =
		panelEspacialEcosistema.frameEcosistema.obtenerAutomataCelular();
		
		// Obtener el vector de rastreo actual del autómata celular.
		Vector rastreoActual = automataCelular.obtenerRastreoActual();
		
		// Obtener el espacio del autómata celular.
		Espacio espacio = automataCelular.obtenerEspacio();
		
		// Obtener la cantidad de super-individuos de la simulación.
		int tamanio = rastreoActual.size();
		
		// Obtener la transformación de modelo del espacio.
		TransformacionModelo transformacionModelo =
												espacio.obtenerTransformacion();
		
		// Coordenadas del mundo real.
		double x = 0;
		double y = 0;
		double z = 0;
		
		// Coordenadas del portal de visión.
		int u = 0;
		int v = 0;
		
		// Ciclo en donde recorremos el vector de rastreo actual.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener la celda de ubicación del vector de rastreo.
			ubicacion = (Celda) rastreoActual.elementAt(i);
			
			// Obtener el super-individuo que tiene la ubicación.
			superIndividuo = espacio.buscarGrilla(ubicacion);
			
			// Cuando el super-individuo existe.
			if (superIndividuo != null)
			{
				// Obtener el tipo de recurso y si hay que motrar o no el
				// super-individuo.
				recurso = superIndividuo.obtenerRecurso();
				
				// Cuando hay que mostrar el recurso.
				if (ConfiguracionRecurso.obtenerEspacial(recurso).
					equalsIgnoreCase("Si"))
				{
					// Obtener el tipo de estado del super-individuo.
					estado = superIndividuo.obtenerEstado();
					
					// Cuando el super-individuo está sin huevos o con huevos.
					if (estado == SuperIndividuo.SIN_HUEVOS ||
						estado == SuperIndividuo.CON_HUEVOS)
					{
						// Transformamos de coordenadas de celdas a coordenadas
						// de mundo real y luego a coordenadas de portal de
						// visión. Antes de hacer esto, debemos saber qué vista
						// está configurada actualmente.
						switch (ConfiguracionEspacial.obtenerVista())
						{
							// Vista en Longitud-Latitud.
							case Vista.LONGITUD_LATITUD:
							x = transformacionModelo.obtenerX(
								ubicacion.obtenerI());
							y = transformacionModelo.obtenerY(
								ubicacion.obtenerJ());
							u = transformacion[2].obtenerU(x);
							v = transformacion[2].obtenerV(y);
							break;
							
							// Vista en Longitud-Altitud.
							case Vista.LONGITUD_ALTITUD:
							x = transformacionModelo.obtenerX(
								ubicacion.obtenerI());
							z = transformacionModelo.obtenerZ(
								ubicacion.obtenerK());
							u = transformacion[2].obtenerU(x);
							v = transformacion[2].obtenerV(z);
							break;
							
							// Vista en Latitud-Altitud.
							case Vista.LATITUD_ALTITUD:
							y = transformacionModelo.obtenerY(
								ubicacion.obtenerJ());
							z = transformacionModelo.obtenerZ(
								ubicacion.obtenerK());
							u = transformacion[2].obtenerU(y);
							v = transformacion[2].obtenerV(z);
							break;
						}
						
						// Cuando estamos dentro del sistema portal de visión.
						if (uMin[2] <= u && u+uEsp <= uMax[2] &&
							vMin[2] <= v && v+vEsp <= vMax[2])
						{
							// Setear el color propio del recurso.
							grafico2.setColor(
									new Color(Recurso.obtenerColorRojo(recurso),
											Recurso.obtenerColorVerde(recurso),
											Recurso.obtenerColorAzul(recurso)));
							
							// Pintar el punto.
							grafico2.fillRect(u, v, (int)uEsp, (int)vEsp);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Método que grafica el marco que tiene el panel del mapa geográfico. Se
	 * dibujan tres rectángulos negros (aparte del mapa), con el objeto de que
	 * el usuario visualice de mejor manera las coordenadas geográficas.
	 */
	private void graficarMarcoPanel()
	{
		// Dibujar los rectángulos exterior, medio e interior.
		grafico2.setColor(Color.BLACK);
		grafico2.drawRect(uMin[0], vMin[0], uDel[0], vDel[0]);
		grafico2.drawRect(uMin[1], vMin[1], uDel[1], vDel[1]);
		grafico2.drawRect(uMin[2], vMin[2], uDel[2], vDel[2]);
	}
	
	/**
	 * Método que cambia los límites de latitud y longitud actuales del mapa que
	 * se está visualizando. Si podemos movernos al Norte y Oeste, entonces se
	 * mueve el foco de visualización en dirección hacia el Noroeste. Es decir,
	 * se decrementan los límites latitudinales de visualización y se
	 * incrementan los límites longitudinales de visualización.
	 */
	public void moverNoroeste()
	{
		// Cuando podemos movernos al Norte y Oeste, entonces cambiamos los
		// límites de visualización.
		if (poderMoverNorte() && poderMoverOeste())
		{
			// Calcular los topes en longitud y en latitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			double latitudInicial =
								 ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial+
													   LONGITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal+
													   LONGITUD_DESPLAZAMIENTO);
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial-
														LATITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal-
														LATITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de latitud actuales del mapa que se está
	 * visualizando. Si podemos movernos al Norte, entonces se mueve el foco de
	 * visualización hacia el Norte. Es decir, se decrementan los límites
	 * latitudinales de visualización.
	 */
	public void moverNorte()
	{
		// Cuando podemos movernos al Norte, entonces cambiamos los límites de
		// visualización.
		if (poderMoverNorte())
		{
			// Calcular los topes en latitud.
			double latitudInicial =
								  ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial-
														LATITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal-
														LATITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de latitud y longitud actuales del mapa que
	 * se está visualizando. Si podemos movernos al Norte y Este, entonces se
	 * mueve el foco de visualización hacia el Norte y al Este. Es decir, se
	 * decrementan los límites latitudinales y se decrementan los límites
	 * longitudinales de visualización.
	 */
	public void moverNoreste()
	{
		// Cuando podemos movernos al Norte y Este, entonces cambiamos los
		// límites de visualización.
		if (poderMoverNorte() && poderMoverEste())
		{
			// Calcular los topes en longitud y en latitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			double latitudInicial =
								 ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial-
													   LONGITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal-
													   LONGITUD_DESPLAZAMIENTO);
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial-
														LATITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal-
														LATITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de longitud actuales del mapa que se está
	 * visualizando. Si podemos movernos al Oeste, entonces se mueve el foco de
	 * visualización hacia el Oeste. Es decir, se incrementan los límites
	 * longitudinales de visualización.
	 */
	public void moverOeste()
	{
		// Cuando podemos movernos al Oeste, entonces cambiamos los límites de
		// visualización.
		if (poderMoverOeste())
		{
			// Calcular los topes en longitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial+
													   LONGITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal+
													   LONGITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de longitud, latitud y altitud actuales del
	 * mapa que se está visualizando. Se mueve el foco de visualización hacia el
	 * centro.
	 */
	public void moverCentro()
	{
	}
	
	/**
	 * Método que cambia los límites de longitud actuales del mapa que se está
	 * visualizando. Si podemos movernos al Este, entonces se mueve el foco de
	 * visualización hacia el Este. Es decir, se decrementan los límites
	 * longitudinales de visualización.
	 */
	public void moverEste()
	{
		// Cuando podemos movernos al Este, entonces cambiamos los límites de
		// visualización.
		if (poderMoverEste())
		{
			// Calcular los topes en longitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial-
													   LONGITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal-
													   LONGITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de longitud y latitud actuales del mapa que
	 * se está visualizando. Si podemos movernos al Sur y Oeste, entonces se
	 * mueve el foco de visualización hacia el Suroeste. Es decir, se
	 * incrementan los límites longitudinales y latitudinales de visualización.
	 */
	public void moverSuroeste()
	{
		// Cuando podemos movernos al Sur y Oeste, entonces cambiamos los
		// límites de visualización.
		if (poderMoverSur() && poderMoverOeste())
		{
			// Calcular los topes en longitud y latitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			double latitudInicial =
								 ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial+
													   LONGITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal+
													   LONGITUD_DESPLAZAMIENTO);
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial+
														LATITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal+
														LATITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de latitud actuales del mapa que se está
	 * visualizando. Si podemos movernos al Sur, entonces se mueve el foco de
	 * visualización hacia el Sur. Es decir, se incrementan los límites
	 * latitudinales de visualización.
	 */
	public void moverSur()
	{
		// Cuando podemos movernos al Sur, entonces cambiamos los límites de
		// visualización.
		if (poderMoverSur())
		{
			// Calcular los topes en latitud.
			double latitudInicial =
								  ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial+
														LATITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal+
														LATITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de longitud y latitud actuales del mapa que
	 * se está visualizando. Si podemos movernos al Sur y Este, entonces se
	 * mueve el foco de visualización hacia el Sureste. Es decir, se decrementan
	 * los límites longitudinales de visualización y se incrementan los límites
	 * latitudinales de visualización.
	 */
	public void moverSureste()
	{
		// Cuando podemos movernos al Sur y Este, entonces cambiamos los límites
		// de visualización.
		if (poderMoverSur() && poderMoverEste())
		{
			// Calcular los topes en longitud y latitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			double latitudInicial =
								  ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial-
													   LONGITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal-
													   LONGITUD_DESPLAZAMIENTO);
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial+
														LATITUD_DESPLAZAMIENTO);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal+
														LATITUD_DESPLAZAMIENTO);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de longitud y latitud actuales del mapa que
	 * se está visualizando. Si podemos acercanos, entonces se reduce el rango
	 * de visualización del mapa. Es decir, se decrementan los límites
	 * longitudinales de visualización y se decrementan los límites
	 * latitudinales de visualización.
	 */
	public void acercar()
	{
		// Cuando se puede acercar.
		if (poderAcercar())
		{
			// Calcular los topes en longitud y latitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			double latitudInicial =
								 ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial+
																 LONGITUD_ZOOM);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal-
																 LONGITUD_ZOOM);
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial+
																  LATITUD_ZOOM);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal-
																  LATITUD_ZOOM);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Habilitar los botones de zooms.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarZooms();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de longitud y latitud actuales del mapa que
	 * se está visualizando. Si podemos alejarnos, entonces se aumenta el rango
	 * de visualización del mapa. Es decir, se incrementan los límites
	 * longitudinales de visualización y se incrementan los límites
	 * latitudinales de visualización.
	 */
	public void alejar()
	{
		// Cuando se puede alejar.
		if (poderAlejar())
		{
			// Calcular los topes en longitud y latitud.
			double longitudInicial =
								 ConfiguracionEspacial.obtenerLongitudInicial();
			double longitudFinal = ConfiguracionEspacial.obtenerLongitudFinal();
			double latitudInicial =
								 ConfiguracionEspacial.obtenerLatitudInicial();
			double latitudFinal = ConfiguracionEspacial.obtenerLatitudFinal();
			
			// Cambiar las longitudes.
			ConfiguracionEspacial.establecerLongitudInicial(longitudInicial-
																 LONGITUD_ZOOM);
			ConfiguracionEspacial.establecerLongitudFinal(longitudFinal+
																 LONGITUD_ZOOM);
			
			// Cambiar las latitudes.
			ConfiguracionEspacial.establecerLatitudInicial(latitudInicial-
																  LATITUD_ZOOM);
			ConfiguracionEspacial.establecerLatitudFinal(latitudFinal+
																  LATITUD_ZOOM);
			
			// Cambiar la transformacion gráfica.
			cambiarMundoReal();
			
			// Habilitar los botones de movimientos.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarMovimientos();
			
			// Habilitar los botones de zooms.
			panelEspacialEcosistema.
			subpanelControlEcosistema.
			habilitarZooms();
			
			// Establecer que el proyecto a sido modificado.
			Proyecto.establecerModificado(true);
			
			// Repintar el gráfico.
			repaint();
		}
	}
	
	/**
	 * Método que cambia los límites de longitud y latitud actuales del mapa que
	 * se está visualizando. Se restablece el rango del mapa a visualizar y se
	 * restablecen los límites longitudinales y latitudinales de visualización.
	 */
	public void restablecer()
	{
		// Cambiar las longitudes.
		ConfiguracionEspacial.establecerLongitudInicial(
												 Mapa.obtenerLongitudInicial());
		ConfiguracionEspacial.establecerLongitudFinal(
												 Mapa.obtenerLongitudFinal());
		
		// Cambiar las latitudes.
		ConfiguracionEspacial.establecerLatitudInicial(
												 Mapa.obtenerLatitudInicial());
		ConfiguracionEspacial.establecerLatitudFinal(30.45);
		
		// Cambiar la transformacion gráfica.
		cambiarMundoReal();
		
		// Habilitar los botones de movimientos.
		panelEspacialEcosistema.
		subpanelControlEcosistema.
		habilitarMovimientos();
		
		// Habilitar los botones de zooms.
		panelEspacialEcosistema.
		subpanelControlEcosistema.
		habilitarZooms();
		
		// Establecer que el proyecto a sido modificado.
		Proyecto.establecerModificado(true);
		
		// Repintar el gráfico.
		repaint();
	}
	
	/**
	 * Método que determina si es posible o no moverse en dirección al Norte.
	 * Cuando es posible se retorna true. En caso contrario se retorna false.
	 *
	 * @return true Cuando se puede mover al Norte.
	 * @return false Cuando no se puede mover al Norte.
	 */
	public boolean poderMoverNorte()
	{
		 return ConfiguracionEspacial.obtenerLatitudInicial() >
		 		Mapa.obtenerLatitudInicial();
	}
	
	/**
	 * Método que determina si es posible o no moverse en dirección al Sur.
	 * Cuando es posible se retorna true. En caso contrario se retorna false.
	 *
	 * @return true Cuando se puede mover al Sur.
	 * @return false Cuando no se puede mover al Sur.
	 */
	public boolean poderMoverSur()
	{
		return ConfiguracionEspacial.obtenerLatitudFinal() <
			   Mapa.obtenerLatitudFinal();
	}
	
	/**
	 * Método que determina si es posible o no moverse en dirección al Oeste.
	 * Cuando es posible se retorna true. En caso contrario se retorna false.
	 *
	 * @return true Cuando se puede mover al Oeste.
	 * @return false Cuando no se puede mover al Oeste.
	 */
	public boolean poderMoverOeste()
	{
		return ConfiguracionEspacial.obtenerLongitudFinal() <
			   Mapa.obtenerLongitudFinal();
	}
	
	/**
	 * Método que determina si es posible o no moverse en dirección al Centro.
	 * Cuando es posible se retorna true. En caso contrario se retorna false.
	 *
	 * @return true Cuando se puede mover al Centro.
	 * @return false Cuando no se puede mover al Centro.
	 */
	public boolean poderMoverCentro()
	{
		return true;
	}
	
	/**
	 * Método que determina si es posible o no moverse en dirección al Este.
	 * Cuando es posible se retorna true. En caso contrario se retorna false.
	 *
	 * @return true Cuando se puede mover al Este.
	 * @return false Cuando no se puede mover al Este.
	 */
	public boolean poderMoverEste()
	{
		return ConfiguracionEspacial.obtenerLongitudInicial() >
			   Mapa.obtenerLongitudInicial();
	}
	
	/**
	 * Método que determina si es posible o no acercar la simulación. Cuando es
	 * posible se retorna true. En caso contrario se retorna false.
	 *
	 * @return true Cuando se puede acercar la simulación.
	 * @return false Cuando no se puede acercar la simulación.
	 */
	public boolean poderAcercar()
	{
		return xDel > Servicio.obtenerMinutos(2*LONGITUD_ZOOM) &&
			   yDel > Servicio.obtenerMinutos(2*LATITUD_ZOOM);
	}
	
	/**
	 * Método que determina si es posible o no alejar la simulación. Cuando es
	 * posible se retorna true. En caso contrario se retorna false.
	 *
	 * @return true Cuando se puede alejar la simulación.
	 * @return false Cuando no se puede alejar la simulación.
	 */
	public boolean poderAlejar()
	{
		return (xDel+2*LONGITUD_ZOOM) <
				Servicio.obtenerMinutos(Mapa.obtenerLongitudInicial(),
										Mapa.obtenerLongitudFinal()) &&
				(yDel+2*LATITUD_ZOOM) <
				Servicio.obtenerMinutos(Mapa.obtenerLatitudInicial(),
										Mapa.obtenerLatitudFinal());
	}
	
	/**
	 * Método en donde se muestra la posición actual que se tiene en el mapa.
	 * La posición se muestra en el panel de control del Ecosistema.
	 *
	 * @param u El valor en el eje X del sistema portal de visión en donde se
	 *          ubica el mouse actualmente.
	 * @param v El valor en el eje Y del sistema portal de visión en donde se
	 *          ubica el mouse actualmente.
	 */
	public void graficarPosicion(int u, int v)
	{
		Grado longitud = null;
		Grado latitud = null;
		double altitud = 0;
		
		// Cuando las posición del mouse si está en el mapa.
		if (uMin[2]<=u && u<=uMax[2] && vMin[2]<=v && v<=vMax[2])
		{
			// Dependiendo del tipo de vista, se realiza la transformación del
			// sistema portal de visión al sistema mundo real.
			switch (ConfiguracionEspacial.obtenerVista())
			{
				// Vista Longitud-Latitud.
				case Vista.LONGITUD_LATITUD:
				longitud = new Grado(transformacion[2].obtenerX(u));
				latitud = new Grado(transformacion[2].obtenerY(v));
				altitud = altitudPosicion;
				break;
				
				// Vista Longitud-Altitud.
				case Vista.LONGITUD_ALTITUD:
				longitud = new Grado(transformacion[2].obtenerX(u));
				latitud = new Grado(latitudPosicion);
				altitud = transformacion[2].obtenerY(v);
				break;
				
				// Vista Latitud-Altitud.
				case Vista.LATITUD_ALTITUD:
				longitud = new Grado(longitudPosicion);
				latitud = new Grado(transformacion[2].obtenerX(u));
				altitud = transformacion[2].obtenerY(v);
				break;
			}
			
			// Mostrar la longitud, latitud y altitud.
			panelEspacialEcosistema.subpanelControlEcosistema.
			cambiarEtiquetaLongitud(longitud.toString());
			panelEspacialEcosistema.subpanelControlEcosistema.
			cambiarEtiquetaLatitud(latitud.toString());
			panelEspacialEcosistema.subpanelControlEcosistema.
			cambiarEtiquetaAltitud(""+
			Servicio.obtenerNumeroFormateado(altitud, 1)+" mt");
		}
		
		// Cuando las posición del mouse no está en el mapa.
		else
		{
			// Limpiar la longitud, latitud y altitud.
			panelEspacialEcosistema.subpanelControlEcosistema.
			cambiarEtiquetaLongitud("");
			panelEspacialEcosistema.subpanelControlEcosistema.
			cambiarEtiquetaLatitud("");
			panelEspacialEcosistema.subpanelControlEcosistema.
			cambiarEtiquetaAltitud("");
		}
	}
	
	/**
	 * Método que retorna el valor del atributo panelEspacialEcosistema.
	 *
	 * @return panelEspacialEcosistema El valor actual del atributo
	 *                                 panelEspacialEcosistema.
	 */
	public PanelEspacialEcosistema obtenerPanelEspacialEcosistema()
	{
		return panelEspacialEcosistema;
	}
	
	/**
	 * Método que retorna el valor del atributo transformacion con un índice
	 * conocido.
	 *
	 * @param i El índice del arreglo.
	 *
	 * @return transformacion[i] El valor actual del atributo transformacion[i].
	 */
	public TransformacionGrafica obtenerTransformacion(int i)
	{
		return transformacion[i];
	}
}