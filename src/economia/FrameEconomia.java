/**
 * @(#)FrameEconomia.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase que extiende de la clase JInternalFrame. Contiene todos los componentes
 * GUI del módulo "Economía" que tiene la aplicación. Gracias a estos
 * componentes el usuario puede interactuar en forma simple con las opciones que
 * contiene el Módulo "Economía".
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see KeyEvent
 * @see Container
 * @see JInternalFrame
 * @see JTabbedPane
 * @see ImageIcon
 * @see JOptionPane
 * @see PanelEscritorio
 * @see PanelEntrenarEconomia
 * @see PanelGraficarEconomia
 * @see PanelReporteEconomia
 * @see PanelConfiguracionEconomia
 * @see RedNeuronalRBF
 * @see RedNeuronalBP
 * @see ProcesoSegundoPlano
 * @see InterfaceInternalFrame
 */
public class FrameEconomia extends JInternalFrame
	implements InterfaceInternalFrame
{
	/** Constante que identifica la red neuronal BP. */
	public static final int RED_NEURONAL_BP = 0;
	
	/** Constante que identifica la red neuronal RBF. */
	public static final int RED_NEURONAL_RBF = 1;
	
	/**
	 * Constante que indica cuando un año es genérico. Valor utilizado por la
	 * red neuronal RBF.
	 */
	public static final int ANO_GENERICO = -1;
	
	/** Apuntador al creador del FrameEconomia. */
	public PanelEscritorio panelEscritorio;
	
	/** El panel dedicado a mostrar la pantalla de entrenar de la red. */
	public PanelEntrenarEconomia panelEntrenarEconomia;
	
	/** El panel dedicado a mostrar la gráfica de la predicción. */
	public PanelGraficarEconomia panelGraficarEconomia;
	
	/** El panel dedicado a mostrar el reporte económico. */
	public PanelReporteEconomia panelReporteEconomia;
	
	/** El panel dedicado a mostrar la configuración. */
	public PanelConfiguracionEconomia panelConfiguracionEconomia;
	
	/** Contenedor de paneles del frame economía. */
	private JTabbedPane paleta;
	
	/** Red neuronal Back-Propagation del modelo económico. */
	private RedNeuronalBP redBP;
	
	/** Red neuronal Radial Base Fase (RBF) del modelo económico. */
	private RedNeuronalRBF redRBF;
	
	/** Número de patrones que se eligieron para aprender. */
	private int numeroPatrones;
	
	/** Valor que indica la red elegida(0:BP , 1:RBF). */
	private int eleccionRed;
	
	/** Indica el año de predicción que la red esta predicciendo. */
	private int anoPrediccion; 
	
	/** Valor que indica si la red esta entrenando o no. */
	private boolean entrenando;
	
	/** Valores de las entredas para entrenar. */
	private double[][] entradas;
	
	/** Valores de las salidas para entrenar. */
	private double[][] salidas;
	
	/**
	 * Objeto que maneja los proceso de segundo plano de entrenar en el frame.
	 */
	private ProcesoSegundoPlano procesoEntrenar; 
	
	/**
	 * Método constructor que invoca a los métodos que configuran el frame, como
	 * los paneles y los eventos asociados de los componentes GUI que contiene
	 * el frame.
	 *
	 * @param panelEscritorio El objeto que hace referencia al escritorio de la
	 *                        aplicación principal.
	 */
	public FrameEconomia(PanelEscritorio panelEscritorio)
	{
		// Inicializar el puntero.
		this.panelEscritorio = panelEscritorio;
		
		// Configurar.
		configurarElementosEspeciales();
		configurarFrame();
		configurarComponentes();
	}
	
	/**
     * Método que configura los elementos especiales que hay en el frame. Los
     * elementos especiales son: Objetos que se encargan de los proceso de
     * segundo plano y la inicializacion de los valores que la red desea
     * aprender.
     */
	public void configurarElementosEspeciales()
	{
    	redBP = new RedNeuronalBP();
    	redRBF = new RedNeuronalRBF();
		procesoEntrenar = new ProcesoSegundoPlano(this);
		
		entradas = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES]
							[ConfiguracionNeuronalBP.NUMERO_MAXIMO_ENTRADAS];
		
		salidas = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES]
							[ConfiguracionNeuronalBP.NUMERO_MAXIMO_SALIDAS];
		
		anoPrediccion = Proyecto.obtenerFechaInicialFormatoInt()[2];
	}
	
	/**
	 * Método que configura las propiedades del frame. Se cambia el icono, el
	 * tamaño y otras propiedades derivadas de JFrame.
	 */
	public void configurarFrame()
	{
		setTitle("Economía");
		setResizable(true);
		setClosable(false);
		setMaximizable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("../img/circana_pro.gif"));
		setSize(panelEscritorio.getSize());
	}
	
	/**
	 * Método que configura los paneles del frame y sus componentes. Los paneles
	 * son adjuntados al frame en forma de paleta de paneles.
	 */
	public void configurarComponentes()
	{
		// Capturar el panel contenedor del frame.
		Container contenedor = getContentPane();
		
		// Iniciando paleta.
		paleta = new JTabbedPane();
		
		// Crear los paneles.
		panelEntrenarEconomia = new PanelEntrenarEconomia(this);
		panelGraficarEconomia = new PanelGraficarEconomia(this);
		panelReporteEconomia = new PanelReporteEconomia(this);
		panelConfiguracionEconomia = new PanelConfiguracionEconomia(this);
		
		// Incorporar los paneles al contenedor.
		paleta.addTab("Entrenar", new ImageIcon("../img/economia_entrenar.gif"),
			panelEntrenarEconomia, "Entrenar de la Economía");
		paleta.addTab("Graficar", new ImageIcon("../img/economia_graficar.gif"),
			panelGraficarEconomia, "Graficar de la Economía");
		paleta.addTab("Reporte", new ImageIcon("../img/economia_reporte.gif"),
			panelReporteEconomia, "Reporte de la Economía");
		paleta.addTab("Configuración", new ImageIcon("../img/economia_configuracion.gif"),
			panelConfiguracionEconomia, "Configuración de la Economía");
		
		// Adjuntar un comando único a cada panel.
		paleta.setMnemonicAt(0, KeyEvent.VK_E);
		paleta.setMnemonicAt(1, KeyEvent.VK_G);
		paleta.setMnemonicAt(2, KeyEvent.VK_R);
		paleta.setMnemonicAt(3, KeyEvent.VK_C);
		
		// Incorporar la paleta de paneles al contendor del frame.
		contenedor.add(paleta);
	}
	
	/**
     * Método que muestra un mensaje de diálogo en la pantalla cuando la red
     * neuronal ha sobrepasado el error máximo exigido. Después de este mensaje
     * la red detiene el entrenamiento sin quedar en estado de entrenada.
     */
	private void mostrarTextoErrorMaximo()
	{
		String texto = "";
		texto += "La red neuronal ha detenido su entrenamiento\n";
		texto += "porque ha sobre pasado el error máximo aceptado en "; 
		texto += "el entrenamiento de la red.\n";
		texto += "Por favor vuelva a entrenar la red.\n";
		texto += "Si el problema persiste, entonces pruebe cambiando la ";
		texto += "configuración de la red neuronal artificial.";
		JOptionPane.showMessageDialog(this,texto,
			"Sobre el error máximo exigido por la red",
			JOptionPane.ERROR_MESSAGE);
	}
	
	/**
     * Método que muestra un mensaje de diálogo en la pantalla cuando la red
     * neuronal ha entregado errores por de bajo del error mínimo exigido.
     * Después de este mensaje la red detiene el entrenamiento quedando en
     * estado de entrenada.
     */
	private void mostrarTextoErrorMinimo()
	{
		String texto = "";
		
		texto += "La red neuronal ha entregado errores bajo el mínimo ";
		texto += "aceptado en el entrenamiento.\n";
		JOptionPane.showMessageDialog(this,texto,
			"Cumplió con el error mínimo exigido por la red",
			JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
     * Método que cambia los estados de habilitación de los botones del panel
     * entrenar y configuración de economía. Esto cambia cuando la red está
     * entrenando o no.
     */
	private void cambiarEstadosBotones()
	{
		panelEntrenarEconomia.habilitarBotonEntrenar(!entrenando);
		panelEntrenarEconomia.habilitarRadioButon(!entrenando);
		panelEntrenarEconomia.habilitarBotonDetener(entrenando);
		panelEntrenarEconomia.habilitarBotonAgregar(!entrenando);
		panelEntrenarEconomia.habilitarBotonQuitar(!entrenando);
		panelEntrenarEconomia.habilitarBotonRefrescar(!entrenando);
		panelConfiguracionEconomia.habilitarBotones(!entrenando);
	}
	
	/**
     * Método que verifica el número de patrones y lo almacena para entregarlo
     * a la red neuronal que se desea entrenar. Entrega verdadero si verificó
     * correctamente el número de patrones.
     * 
     * @return almacenado Si almacenó los patrones en la red.
     */
	private boolean almacenarPatronesRed()
	{
		boolean almacenado =  true; 
		String textoError = "";
		
		numeroPatrones = panelEntrenarEconomia.
						obtenerNumeroPatronesTablaAnosEntrenadas();
		
		// testeando posibles errores
		if (numeroPatrones > ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES ||
			numeroPatrones > ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES)
			textoError = "Por favor disminuya el número de patrones";
		
		if (numeroPatrones == 0)
			textoError = "Deben haber patrones para entrenar";
		
		if (textoError != "")
		{
			JOptionPane.showMessageDialog(this, textoError,
				"Error en el número de patrones", JOptionPane.ERROR_MESSAGE);
			almacenado = false;
		}
		
		// si no hay errores se almacena los patrones
		if (textoError == "")
		{
			entradas = panelEntrenarEconomia.obtenerEntradasRed();
			salidas = panelEntrenarEconomia.obtenerSalidasRed();
			almacenado = true;
		}
		
		return almacenado;
	}
	
	/**
     * Método que ajusta los patrones entradas y salidas para la red neuronal 
     * RBF. Lo que hace es promediar los datos entragados anteriormente
     * a la red neuronal.
     */
	private void transformarDatosEntradasRBF()
	{
		int i;
		int j;
		int k;
		int contador;
		int contadorTemporal;
		double entradasTemporal[][];
		double salidasTemporal[][];
		double valorElegido;
		
		// nuevo numero patrones que contendra la red RBF
		contador = 0;
		
		entradasTemporal = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES]
							[ConfiguracionNeuronalBP.NUMERO_MAXIMO_ENTRADAS];
		
		salidasTemporal = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES]
							[ConfiguracionNeuronalBP.NUMERO_MAXIMO_SALIDAS]; 							
		
		// recorriendo los patrones.
		for (k=0; k<numeroPatrones; k++)
		{
			valorElegido = ANO_GENERICO;
			// encontrar un valor que para promediar
			for (j=k; j<numeroPatrones; j++)
			{
				// si no esta asignada ya
				if (entradas[j][1] != ANO_GENERICO)
				{
					valorElegido = entradas[k][1];
					break;
				}
			}
			
			// si hay valores para promediar
			if (valorElegido != ANO_GENERICO)
			{
				contadorTemporal = 0;
				// promediar los valores
				for (j=k; j<numeroPatrones; j++)
				{
					// asignar Valores Promedios
					if (entradas[j][1] == valorElegido)
					{
						entradasTemporal[contador][0] = ANO_GENERICO;
						entradasTemporal[contador][1] = entradas[j][1];
						
						salidasTemporal[contador][0]  += salidas[j][0];
						salidasTemporal[contador][1]  += salidas[j][1];
						
						contadorTemporal++;
						entradas[j][1] = ANO_GENERICO;
					}
				}
				
				salidasTemporal[contador][0] /= contadorTemporal;
				salidasTemporal[contador][1] /= contadorTemporal;
				contador++;
			}
		}
		
		entradas = entradasTemporal;
		salidas = salidasTemporal;
		numeroPatrones = contador;
		redRBF.cambiarNumerosPatrones(numeroPatrones);
	}
	
	/**
     * Método que genera el reporte en el panel reporte de economía.
     */			
	private void generarReporte()
	{
		double errorAprendizaje = 0;
		double errorTest = 0;
		
		switch (eleccionRed) 
		{
	    	case RED_NEURONAL_BP:
	    	{
	    		errorAprendizaje = redBP.obtenerErrorAprendizaje();
	    		errorTest = redBP.obtenerErrorTest();
	    		break;
	    	}
	    	
	    	case RED_NEURONAL_RBF:
	    	{
	    		errorAprendizaje = redRBF.obtenerErrorAprendizaje();
	    		errorTest = redRBF.obtenerErrorTest();
	    		break;
	    	}
	    }
		
		panelReporteEconomia.mostrarReporte();
	}
	
	/**
     * Método que ejecuta el proceso no supervisado del entrenamiento de la red
     * neuronal RBF.
     */
	private void entrenarNoSupervisadoRBF()
	{
		double ciclo;
		double totalCiclosNoSupervisado = redRBF.obtenerCicloNoSupervisado();
		int porcentaje = 0;
		
		for (ciclo=1; ciclo<=totalCiclosNoSupervisado && (entrenando); ciclo++)
		{
			if ((100*ciclo/totalCiclosNoSupervisado) >= (porcentaje + 1))
			{
				porcentaje = (int) (100*ciclo/totalCiclosNoSupervisado);
				panelEntrenarEconomia.cambiarBarraProgreso(porcentaje);
				panelEscritorio.frameCircanaPro.panelEstado.
									cambiarBarraEconomia(porcentaje);
			}
			
			redRBF.ajustarCentroides();
			redRBF.decrementarRataNoAprendizaje(ciclo);
			
			// lineas de codigo temporales
			//if (ciclo%5000 == 0)
			//{
			//	System.out.println ();
			//	System.out.println ("ciclo:"+ciclo);
			//	System.out.println ("rataN:"+redRBF.obtenerRataNoSupervisada());
			//}
			// fin de lineas de codigo temporal
			
			ProcesoSegundoPlano.dormir();
		}
		// fin de aprendizaje no supervisado
		
		//	si fue exitoso
		if (porcentaje == 100)
		{
			panelEntrenarEconomia.cambiarTextoAprendizaje(2);
			panelEntrenarEconomia.cambiarBarraProgreso(0);
			panelEscritorio.frameCircanaPro.panelEstado.
									cambiarBarraEconomia(0);
		}
	}
	
	/**
     * Método que ejecuta el proceso supervisado del entrenamiento de la 
     * red neuronal RBF.
     */
	private void entrenarSupervisadoRBF()
	{
		double ciclo;
		double totalCiclosSupervisado = redRBF.obtenerCicloSupervisado();
		int porcentaje = 0;
		
		//System.out.println (redRBF);
		
		for (ciclo=1; ciclo<=totalCiclosSupervisado && (entrenando); ciclo++)
		{
			if ((100*ciclo/totalCiclosSupervisado) >= (porcentaje + 1))
			{
				porcentaje = (int) (100*ciclo/totalCiclosSupervisado);
				panelEntrenarEconomia.cambiarBarraProgreso(porcentaje);
				panelEscritorio.frameCircanaPro.panelEstado.
									cambiarBarraEconomia(porcentaje);
			}
			
			redRBF.calcularErrores();
			redRBF.ajustarPesosSinapticos();
			
			// si esta habilitado el testear error en la red
			if (panelEntrenarEconomia.estaActivoDetenerPorError())
			{
				if (redRBF.estaBajoErrorMinimo(ciclo))
				{
					mostrarTextoErrorMinimo();
					entrenando = false;
					porcentaje = 100;
				}
				
				if (redRBF.estaSobreErrorMaximo(ciclo))
				{
					mostrarTextoErrorMaximo();
					entrenando = false;
					porcentaje = 0;
				}
			}
			
			// comienzo de lineas temporales
			//if (ciclo%1000 == 0)
			//{
			//	System.out.println ();
			//	System.out.println ("ciclo:"+ciclo);
			//	System.out.println (" errorTo:"+redRBF.obtenerError() + 
			//						" errorAp:"+redRBF.obtenerErrorAprendizaje() + 
			//						" errorTe:"+redRBF.obtenerErrorTest());
			//	System.out.println (" rataS: "+redRBF.obtenerRataSupervisada());
			//}
			// fin de lineas temporales
			
			ProcesoSegundoPlano.dormir();
		}
		// fin de aprendizaje supervisado
	 	
	 	// si fue exitoso el entrenamiento
	 	if (porcentaje == 100)
		{
			// si esta entrenada
			redRBF.cambiarEstadoEntrenada(true);
			
			generarReporte();
			panelEntrenarEconomia.cambiarColorRadioButton(eleccionRed,true);
			panelGraficarEconomia.subPanelGraficarEconomia.graficarCurva(true);
		}
	}
	
	/**
     * Método que entrena la red neuronal Back - Propagation. Este es un proceso
     * que se ejecuta en segundo plano. 
     */
	public void entrenarBP()
	{
		double ciclo;
		double totalCiclos = redBP.obtenerCiclo();
		int porcentaje = 0;
		
		redBP.inicializar();
		redBP.almacenarDatosAprendizaje(entradas,salidas,numeroPatrones);
		
		for (ciclo=1; ciclo<=totalCiclos && (entrenando); ciclo++)
		{
			if ((100*ciclo/totalCiclos) >= (porcentaje + 1))
			{
				porcentaje = (int) (100*ciclo/totalCiclos);
				panelEntrenarEconomia.cambiarBarraProgreso(porcentaje);
				panelEscritorio.frameCircanaPro.panelEstado.
									cambiarBarraEconomia(porcentaje);
			}
			
			redBP.calcularErrores();
			redBP.ajustarPesosSinapticos();
			
			// si esta habilitado el testear error en la red
			if (panelEntrenarEconomia.estaActivoDetenerPorError())
			{
				if (redBP.estaBajoErrorMinimo(ciclo))
				{
					mostrarTextoErrorMinimo();
					entrenando = false;
					porcentaje = 100;
				}
				
				if (redBP.estaSobreErrorMaximo(ciclo))
				{
					mostrarTextoErrorMaximo();
					entrenando = false;
					porcentaje = 0;
				}
			}
			
			// lineas de codigo temporales
			//if (ciclo%1000 == 0)
			//{
			//	System.out.println ();
			//	System.out.println ("ciclo:"+ciclo);
			//	System.out.println (" errorTo:"+redBP.obtenerError() + 
			//						" errorAp:"+redBP.obtenerErrorAprendizaje() + 
			//						" errorTe:"+redBP.obtenerErrorTest());
			//	System.out.println (" rata: "+redBP.obtenerRata());
			//}
			// fin lineas de codigo temporales
			
			ProcesoSegundoPlano.dormir();
		}
		
		// si fue exitoso el entrenamiento
		if (porcentaje == 100)
		{
			// si esta entrenada
			redBP.cambiarEstadoEntrenada(true);
			
			generarReporte();
			panelEntrenarEconomia.cambiarColorRadioButton(eleccionRed,true);
			panelGraficarEconomia.subPanelGraficarEconomia.graficarCurva(true);
		}
		
		entrenando = false;
		cambiarEstadosBotones();
		panelEntrenarEconomia.cambiarBarraProgreso(0);
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(false);
	}
	
	/**
     * Método que entrena la red neuronal Radial Base Fase (RBF). Este es un 
     * proceso que se ejecuta en segundo plano. 
     */
	public void entrenarRBF()
	{
		transformarDatosEntradasRBF();
		
		redRBF.inicializar();
		redRBF.almacenarDatosAprendizaje(entradas,salidas,numeroPatrones);
		
		entrenarNoSupervisadoRBF();
		
		//System.out.println (redRBF);
		
		entrenarSupervisadoRBF();
		
		entrenando = false;
	 	cambiarEstadosBotones();
		panelEntrenarEconomia.cambiarTextoAprendizaje(1);
		panelEntrenarEconomia.cambiarBarraProgreso(0);
		panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(false);
	}
	
	/**
     * Método que ejecuta una salida en la red neuronal elegida en el frame.
     * Usada frecuentemente para los métodos externos a este frame.
     * 
     * @param x Arreglo con los valores de entrada a la red neuronal.
     * 
     * @return y Arreglo con las salidas de la red neuronal.
     */
    /*
	 * OBSERVACION: Ya que la red neuronal RBF no cambia su resultado por el
	 * cambio de los años (ya que no hay tendencias en años) que se ingresen la
	 * red RBF, se modifica los años para indicarle a la red que en un año
	 * genérico (Se utiliza simplemente en esta red neuronal).
	 */
	public double[] ejecutarSalidaRed(double[] x)
	{
		int numeroSalidas = redBP.obtenerNumeroSalidas();
		
		double[] y = new double[numeroSalidas];
		
		switch (eleccionRed)
		{
	    	case RED_NEURONAL_BP:
	    	{
	    		redBP.ajustarEntrada(x,0);
	    		y = redBP.ejecutarRed(x);
	    		
	    		x = redBP.ajustarEntrada(x,1);
	    		y = redBP.ajustarSalida(y,1);
	    		
	    		break;
	    	}
			
	    	case RED_NEURONAL_RBF:
	    	{
	    	 	/*
				 * Ya que la red neuronal RBF no cambia su resultado por el
				 * cambio de los años (ya que no hay tendencias en años) que se
				 * ingresen la red RBF, se modifica los años para indicarle a la
				 * red que en un año genérico (Se utiliza simplemente en esta
				 * red neuronal).
				 */
				x[0] = ANO_GENERICO;
				
	    	 	redRBF.ajustarEntrada(x,0);
	    	 	y = redRBF.ejecutarRed(x);
	    	 			
	    	 	x = redRBF.ajustarEntrada(x,1);
	    	 	y = redRBF.ajustarSalida(y,1);
	    	 			
	    	 	break;
	    	}
	    	
	    	/*
	    	case RED_NEURONAL_BP:
    		{
    			int i;
    			int j;
    			
    			JgclPoint3D[][] puntosDemanda = new JgclCartesianPoint3D[2][16];
    			JgclPoint3D[][] puntosPrecio = new JgclCartesianPoint3D[2][16];
    			
    			JgclPoint3D puntoDemanda;
    			JgclPoint3D puntoPrecio;
    			
    			JgclBsplineSurface3D prePrecio;
    			JgclBsplineSurface3D preDemanda;
    			
				j=0;
   				for (i=0; i<numeroPatrones; i++)
				{
					if (entradas[i][0] == 2002 && j < 16)
					{
						System.out.println
						("entrada:" + entradas[i][0] +" , "+entradas[i][1]+
									" , " + salidas[i][1] + " , " + j);
						
						puntosPrecio[0][j] = new JgclCartesianPoint3D(
															entradas[i][0],
															 entradas[i][1],
															 salidas[i][1]);
						
						puntosDemanda[0][j] = new JgclCartesianPoint3D(
															entradas[i][0],
															 entradas[i][1],
															 salidas[i][0]);
						j++;
					}
				}
				
				j=0;
				for (i=0; i<numeroPatrones; i++)
				{
					if (entradas[i][0] == 2003 && j < 16)
					{
						System.out.println
							("entrada:" + entradas[i][0] +" , "+entradas[i][1]+
							" , " + salidas[i][1] + " , " + j);
						
						puntosPrecio[1][j] = new JgclCartesianPoint3D(
															entradas[i][0],
															 entradas[i][1],
															 salidas[i][1]);	
						
						puntosDemanda[1][j] = new JgclCartesianPoint3D(
															entradas[i][0],
															 entradas[i][1],
															 salidas[i][0]);
						j++;
					}
				}
    			
    			prePrecio = new JgclBsplineSurface3D(
									3, false, JgclKnotType.UNIFORM_KNOTS,
									3, false, JgclKnotType.UNIFORM_KNOTS,
									puntosPrecio);
    			
    			preDemanda = new JgclBsplineSurface3D(
									3, false, JgclKnotType.UNIFORM_KNOTS,
									3, false, JgclKnotType.UNIFORM_KNOTS,
									puntosDemanda);
				
				System.out.println (x[0] + ", " + x[1] );		
				puntoPrecio = prePrecio.coordinates(x[0], x[1]);
				puntoDemanda = preDemanda.coordinates(x[0], x[1]);
				
				y[0] = puntoDemanda.z();
				y[1] = puntoPrecio.z();
			}
			*/
		}
		
		return y;  
	}
	
	/**
     * Método que devuelve la confiabilidad que obtuvo la red elegida en su
     * proceso de aprendizaje. La confiabilidad esta medida en un rango [0,1].
     * 
     * @return confiabilidad Indica la confiablidad que tiene la red neuronal 
     *						 elegida en su fase de aprendizaje.
     */
	public double obtenerConfiabilidadAprendizaje()
	{
		double confiabilidad = 0;
		
		switch (eleccionRed)
		{
	    	case RED_NEURONAL_BP:
	    	{
	    		confiabilidad = redBP.obtenerConfiabilidadAprendizaje();
	    		break;
	    	}
	    	
	    	case RED_NEURONAL_RBF:
	    	{
	    		confiabilidad = redRBF.obtenerConfiabilidadAprendizaje();
	    		break;
	    	}
	    }
	    
	    return confiabilidad;
	}
	
	/**
	 * Método que entrena la red seleccionada. En este también se llama a los
	 * proceso de segundo plano como: entrenarBP y entrenarRBF.
	 */
	public void entrenar()
	{
		entrenando =  true;
		
		if (almacenarPatronesRed())
		{
			panelEscritorio.frameCircanaPro.establecerProcesoCorriendo(true);
			panelReporteEconomia.limpiarReporte();
			panelGraficarEconomia.subPanelGraficarEconomia.graficarCurva(false);
			cambiarEstadosBotones();
			procesoEntrenar.ejecutar(eleccionRed);
		}
	}
	
	/**
	 * Método que detiene el entrenamiento a la red. En este método también
	 * se detiene los procesos de segundo plano como: entrenarBP y entrenarRBF.
	 */
	public void detener()
	{
		entrenando =  false;
		
		cambiarEstadosBotones();
		panelEntrenarEconomia.cambiarBarraProgreso(0);
		panelEntrenarEconomia.cambiarColorRadioButton(eleccionRed,false);
		panelGraficarEconomia.subPanelGraficarEconomia.graficarCurva(false);
		panelReporteEconomia.limpiarReporte();
		panelEscritorio.frameCircanaPro.panelEstado.cambiarBarraEconomia(0);
		
		switch (eleccionRed)
		{
			case RED_NEURONAL_BP:
			{
				redBP.cambiarEstadoEntrenada(false);
				break;
			}
			
			case RED_NEURONAL_RBF:
			{
				redRBF.cambiarEstadoEntrenada(false);
				break;
			}
		}
	}
	
	/**
     * Método que carga la información que está disponible en las diversas
     * configuraciones, tanto de las redes y/o las que se encuentran en el
     * proyecto. Importante este metodo carga la configuracion en el frame.
     */
	public void cargarConfiguracion()
	{
		// cargar las redes
		redBP.cargarValores();
		redRBF.cargarValores();
		
		// cargar otros elementos
		anoPrediccion = Proyecto.obtenerFechaInicialFormatoInt()[2];
		cambiarTiempoPrediccion(anoPrediccion);
		
		// cargar el panel entrenar
		panelEntrenarEconomia.cambiarColorRadioButton(RED_NEURONAL_BP, false);
		panelEntrenarEconomia.cambiarColorRadioButton(RED_NEURONAL_RBF, false);
		panelEntrenarEconomia.refrescarTablaAnosDisponible();
		
		// cargar el panel graficar
		panelGraficarEconomia.subPanelGraficarEconomia.graficarCurva(false);
		
		// cargar el panel reporte
		panelReporteEconomia.cargarReporte();
		
		// cargar el panel configuracion
		panelConfiguracionEconomia.cargarConfiguracion(RED_NEURONAL_BP);
		panelConfiguracionEconomia.cargarConfiguracion(RED_NEURONAL_RBF);
	}
	
	/**
     * Método que configura la red elegida a una configuración estándar.
     * Aunque los datos de entrada para configurar la red sean erroneos
     * la red borra los resultados generados por esta red.
     */
	public void restaurarConfiguracion()
	{
		int opcion = JOptionPane.showConfirmDialog(this,
			"Se borrarán todos los resultados económicos generados " +
			"anteriormente por esta configuración.\n" +
			"Desea continuar?",
			"CIRCANA Pro 2.0",
			JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// cuando el usuario acepta
		if (opcion == JOptionPane.YES_OPTION)
		{
			// cambiar el proyecto
			Proyecto.limpiarResultados(Proyecto.ECONOMIA, eleccionRed);
			Proyecto.establecerReporteEconomia(null);
			Proyecto.establecerModificado(true);
			
			// limpiar los componentes
			panelEntrenarEconomia.cambiarColorRadioButton(eleccionRed, false);
			panelGraficarEconomia.subPanelGraficarEconomia.graficarCurva(false);
			panelReporteEconomia.limpiarReporte();
			panelConfiguracionEconomia.restaurarConfiguracion();
			panelEscritorio.frameIntegracion.panelPlanificarIntegracion.
				cargarTextoEconomia();
			
			// cargar las redes
			switch (eleccionRed)
			{
	    		case RED_NEURONAL_BP:
	    		{
					redBP.cargarValores();
					break;
	    		}
	    		
	    		case RED_NEURONAL_RBF:
	    		{
    				redRBF.cargarValores();
    				break;
	    		}
	    	}
		}
	}
	
	/**
     * Método que configura la red elegida a una configuración personalizada.
     * Aunque los datos de entrada para configurar la red sean erroneos
     * la red borra los resultados generados por esta red.
     */
     public void establecerConfiguracion()
	 {
		int opcion = JOptionPane.showConfirmDialog(this,
			"Se borrarán todos los resultados económicos generados " +
			"anteriormente por esta red.\n" +
			"Desea continuar?",
			"CIRCANA Pro 2.0",
			JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		// cuando el usuario acepta
		if (opcion == JOptionPane.YES_OPTION)
		{
			// cambiar el proyecto
			Proyecto.limpiarResultados(Proyecto.ECONOMIA, eleccionRed);
			Proyecto.establecerReporteEconomia(null);
			Proyecto.establecerModificado(true);
			
			// limpiar los componentes
			panelEntrenarEconomia.cambiarColorRadioButton(eleccionRed, false);
			panelGraficarEconomia.subPanelGraficarEconomia.graficarCurva(false);
			panelReporteEconomia.limpiarReporte();
			panelConfiguracionEconomia.establecerConfiguracion();
			panelEscritorio.frameIntegracion.panelPlanificarIntegracion.
				cargarTextoEconomia();
			
			// cargar las redes
			switch (eleccionRed)
			{
	    		case RED_NEURONAL_BP:
	    		{
					redBP.cargarValores();
					break;
	    		}
	    		
	    		case RED_NEURONAL_RBF:
	    		{
    				redRBF.cargarValores();
    				break;
	    		}
	    	}
		}
	}
	
	/**
     * Método que carga la información con respecto al proyecto que se encuentra
     * en la clase estática Proyecto. De esta manera se actualizan las opciones
     * que se encuentran relacionadas con el proyecto.
     */
	public void actualizarInformacionProyecto()
	{
		// actualizar otros elementos
		anoPrediccion = Proyecto.obtenerFechaInicialFormatoInt()[2];
		cambiarTiempoPrediccion(anoPrediccion);
		
		// actualizar el panel entrenar
		panelEntrenarEconomia.refrescarTablaAnosDisponible();
		
		// actualizar el panel graficar
		panelGraficarEconomia.actualizarInformacionProyecto();
	}
	
	/**
	 * Método que cambia la red elegida para entrenar y cambiar su configuración
	 * correspondiente.
	 * 
	 * @param eleccionRed Indica la cual es la red elegida. 
	 */
	public void seleccionarRed(int eleccionRed)
	{
		this.eleccionRed = eleccionRed;
		panelEntrenarEconomia.cambiarTextoAprendizaje(eleccionRed);
		panelConfiguracionEconomia.cambiarPanel();
		
		switch (eleccionRed)
		{
			case RED_NEURONAL_BP:
			{
				panelGraficarEconomia.subPanelGraficarEconomia.
									graficarCurva(redBP.estaEntrenada());
				panelReporteEconomia.seleccionarReporte(RED_NEURONAL_BP);
				break;
			}
			
			case RED_NEURONAL_RBF:
			{
				panelGraficarEconomia.subPanelGraficarEconomia.
									graficarCurva(redRBF.estaEntrenada());
				panelReporteEconomia.seleccionarReporte(RED_NEURONAL_RBF);
				break;
			}
		}
	}
	
	/**
     * Método que cambia el año de predicción. Para hacerlo se le indica al año
     * actual de predicción si quiere incrementar o disminuir en un año.
     * 
     * @param tipoCambio Indica si aumenta o desminuye en un año. Con valor
     * 					 2 aumenta un año, 1 disminuye en un año.	
     */
	public void cambiarTiempoPrediccion(int tipoCambio)
	{
		// retrocede en un año
		if (tipoCambio == 1)
			anoPrediccion--;
		
		// avanza en un año
		if (tipoCambio == 2)
			anoPrediccion++;
		
		panelGraficarEconomia.cambiarAnoPrediccion(anoPrediccion);
	}
	
	/**
     * Método que agrega patrones a la tabla que contiene los patrones que 
     * va ha aprender. Los elementos agregados son los elementos seleccionados
     * en la tabla años disponible. Además actualiza el número de patrones.
     */
	public void agregarDatosTablaEntrenada()
	{
		panelEntrenarEconomia.agregarTablaAnosEntrenada();
		
		numeroPatrones = panelEntrenarEconomia.
						obtenerNumeroPatronesTablaAnosEntrenadas();
		ConfiguracionNeuronalBP.cambiarNumeroPatrones(numeroPatrones);
		ConfiguracionNeuronalRBF.cambiarNumeroPatrones(numeroPatrones);
		
		panelEntrenarEconomia.cambiarTextoNumeroPatrones(numeroPatrones);
		redBP.cambiarNumerosPatrones(numeroPatrones);
		redRBF.cambiarNumerosPatrones(numeroPatrones);
	}
	
	/**
     * Método que elimina patrones de la tabla que contiene los patrones que va 
     * ha aprender. Los elementos eliminados son los elementos seleccionados
     * en la tabla años entrenada. Ademas actualiza el numero de patrones.
     */
	public void eliminarDatosTablaEntrenada()
	{
		panelEntrenarEconomia.eliminarTablaAnosEntrenada();
		
		numeroPatrones = panelEntrenarEconomia.
						obtenerNumeroPatronesTablaAnosEntrenadas();
		
		ConfiguracionNeuronalBP.cambiarNumeroPatrones(numeroPatrones);
		ConfiguracionNeuronalRBF.cambiarNumeroPatrones(numeroPatrones);
		
		panelEntrenarEconomia.cambiarTextoNumeroPatrones(numeroPatrones);
		redBP.cambiarNumerosPatrones(numeroPatrones);
		redRBF.cambiarNumerosPatrones(numeroPatrones);
	}
	
	/**
     * Método que devuelve las entradas usadas para entrenar la red neuronal.
     *
     * @return salidas Matriz que contiene las entradas que utilizan las redes
     *				   neuronales para el proceso de aprendizaje.
     */
	public double[][] obtenerEntradas()
	{
		return entradas;
	}
	
	/**
     * Método que devuelve las salidas usadas para entrenar la red neuronal.
     *
     * @return salidas Matriz que contiene las salidas que utilizan las redes
     * 				   neuronales para el proceso de aprendizaje.
     */
	public double[][] obtenerSalidas()
	{
		return salidas;
	}
	
	/**
     * Método que devuelve el número de patrones usados para entrenar la red
     * neuronal.
     *
     * @return numerosPatrones Números de patrones usados para entrenar la red
     *						   neuronal
     */
	public int obtenerNumeroPatrones()
	{
		return numeroPatrones;
	}
	
	/**
     * Método que devuelve el tipo de red neuronal usada para realizar la
     * predicción.
     *
     * @return eleccionRed El tipo de red neuronal elegida.
     */
	public int obtenerEleccionRed()
	{
		return eleccionRed;
	}
	
	/**
     * Método que retorna el año actual que se esta prediciendo.
     * 
     * @return anoPrediccion El año de predicción que se esta ejecutando en este
     *						 momento.
     */
	public int obtenerAnoPrediccion()
	{
		return anoPrediccion;
	}
	
	/**
     * Método que muestra el panel entrenar del frame economía.
     */
	public void elegirEntrenar()
	{
		paleta.setSelectedIndex(0);
	} 
	
	/**
     * Método que muestra el panel graficar del frame economía.
     */
	public void elegirGraficar()
	{
		paleta.setSelectedIndex(1);
	} 
	
	/**
     * Método que muestra el panel reporte del frame economía.
     */
	public void elegirReporte()
	{
		paleta.setSelectedIndex(2);
	} 
	
	/**
    * Método que muestra el panel configuracion del frame economía.
    */
	public void elegirConfiguracion()
	{
		paleta.setSelectedIndex(3);
	} 
}