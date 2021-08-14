/**
 * @(#)ProcesoSegundoPlano.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import javax.swing.SwingUtilities;

/**
 * Clase que maneja los procesos secundarios en la apliación. Los procesos 
 * secundarios están divididos en los módulos de: ecosistema, economía,
 * operación e integración. Es importante hacer notar que los procesos
 * secundarios son la administración especializadas de los hilos (THREAD), lo
 * que implica que esta clase está basada en ellos.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see SwingUtilities
 * @see Thread
 * @see FrameEcosistema
 * @see FrameEconomia
 * @see FrameOperacion
 * @see FrameIntegracion
 */
/*
 * OBSERVACION: Un objeto de esta clase puede manejar varios procesos de
 * segundo plano definidos previamente los proceso que seran de segundo plano,
 * pero puede manejar un solo proceso a la vez. Para menejar mas de uno a la
 * vez se debe instanciar otro objeto de esta clase (aún no testeado). Basado
 * en teoría de hilos para swing (diferentes a los de Apleet).
 */
public class ProcesoSegundoPlano
{
	/** El valor que indica que un proceso ha sido iniciado. */
	public static final int INICIADO = 1;
	
	/** El valor que indica que un proceso ha sido pausado. */
	public static final int PAUSADO = 2;
	
	/** El valor que indica que un proceso ha sido detenido. */
	public static final int DETENIDO = 3;
	
	/** Constante que indica cuantos mili-segundos duerme un hilo. */
	private final static int DUERME = 1;
	
	/** Constante que indica si es que hay algún proceso ejecutandose. */
	private final static int NO_EJECUTANDOSE = -1;
	
	/** Puntero que hace referencia al FrameEcosistema. */
	private FrameEcosistema frameEcosistema;
	
	/** Puntero que hace referencia al FrameEconomia. */
	private FrameEconomia frameEconomia;
	
	/** Puntero que hace referencia al FrameOperacion. */
	private FrameOperacion frameOperacion;
	
	/** Puntero que hace referencia al FrameIntegracion. */
	private FrameIntegracion frameIntegracion;
	
	/** Hilo base para manejar los procesos de segundo plano. */
    private Thread hilo;
    
    /**
     * Indica qué proceso del módulo elegido es ejecutado (Rango entre [0, n]).
     */
    private int indiceProceso;
    
    /**
	 * Método constructor que sirve para ser llamado desde el frame de
	 * ecosistema que tiene la aplicación. En este método se inicializan el
	 * objeto que se usará posteriormente en la clase.
	 *
	 * @param frameEcosistema Es un referenciador al FrameEcosistema.
	 */
	public ProcesoSegundoPlano(FrameEcosistema frameEcosistema)
	{
		this.frameEcosistema = frameEcosistema;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame de economía
	 * que tiene la aplicación. En este método se inicializan el objeto que se
	 * usará posteriormente en la clase.
	 *
	 * @param frameEconomia Es un referenciador al FrameEconomia.
	 */
	public ProcesoSegundoPlano(FrameEconomia frameEconomia)
	{
		this.frameEconomia = frameEconomia;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame de operación
	 * que tiene la aplicación. En este método se inicializan el objeto que se
	 * usará posteriormente en la clase.
	 *
	 * @param FrameOperacion Es un referenciador al FrameOperacion.
	 */
	public ProcesoSegundoPlano(FrameOperacion frameOperacion)
	{
		this.frameOperacion = frameOperacion;
	}
	
	/**
	 * Método constructor que sirve para ser llamado desde el frame de
	 * integración que tiene la aplicación. En este método se inicializan el
	 * objeto que se usará posteriormente en la clase.
	 *
	 * @param FrameIntegracion Es un referenciador al FrameIntegracion.
	 */
	public ProcesoSegundoPlano(FrameIntegracion frameIntegracion)
	{
		this.frameIntegracion = frameIntegracion;
	}
	
	/**
	 * Método que ejecuta el proceso inidicado anteriormente en seguno plano.
	 * Este proceso puede ser para cualquier módulo.
	 */
	private void procesosEjecutar()
	{
		// Si es un proceso de FrameEcosistema.
		if (frameEcosistema != null)
		{
			// Si es el proceso de simular dinámicas.
			if (indiceProceso == 0)
				frameEcosistema.simularDinamicas();
		}
		
		// Si es un proceso de FrameEconomia.
		else
		if (frameEconomia != null)
		{
			// Si es el proceso de entrenar a un red neuronal BP.
			if (indiceProceso == 0)
				frameEconomia.entrenarBP();
			
			// Si es el proceso de entrenar a un red neuronal RBF.
			else
			if (indiceProceso == 1)
				frameEconomia.entrenarRBF();
		}
		
		// Si es un proceso de FrameOperacion.
		else
		if (frameOperacion != null)
		{
			// Si es el proceso ejecutar los algoritmos genéticos.
			if (indiceProceso == 0)
				frameOperacion.ejecutarAlgoritmoGenetico();
		}
		
		// Si es un proceso de FrameIntegracion.
		else
		if (frameIntegracion != null)
		{
			// Si es el proceso de planificación las ventas.
			if (indiceProceso == 0)
				frameIntegracion.planificarHeuristica();
		}
	}
	
	/**
	 * Método que duerme a cualquier hilo durante los milli-segundos declarados
	 * en la constante.
	 */
	public static void dormir()
	{
		try
		{
			Thread.sleep(DUERME);
		}
		catch (InterruptedException e)
		{
			System.out.println("Error al intentar dormir hilo.");
		}
	}
	
	/**
	 * Método que indica si se está ejecutando el proceso.
	 *
	 * @return ejecutando Indica si el proceso se está ejecuntando o no.
	 */
	public boolean estaEjecutandose()
	{
		boolean ejecutando = hilo.isAlive();
		
		if (!ejecutando)
			indiceProceso = NO_EJECUTANDOSE;
		
		return ejecutando;
	}
	
	/**
	 * Método que retorna el proceso que esta ejecutando. Retorna
	 * NO_EJECUTANDOSE si no está ejecutando ningún proceso.
	 *
	 * @return indiceProceso Indica cual proceso se esta ejecutando.
	 */
	public int obtenerIndiceProceso()
	{
		estaEjecutandose();
		
		return indiceProceso;
	}
	
	/**
	 * Método que detiene un hilo.
	 */
	public void detener()
	{
	}
	
	/**
	 * Método que ejecuta un proceso en segundo plano determinado para cada
	 * módulo.
	 *
	 * @param indiceProceso Indica cuál es el proceso se va ejecutar.
	 */
	public void ejecutar(int indiceProceso) 
	{
		this.indiceProceso = indiceProceso;
		
		final Runnable finalizar = new Runnable()
		{
			public void run()
			{
				detener();
			}
		};
		
		Runnable  haciendo = new Runnable()
		{
			public void run()
			{
				synchronized(ProcesoSegundoPlano.this)
				{
					procesosEjecutar();
					hilo = null;
				}
				
				SwingUtilities.invokeLater(finalizar);
			}
		};
		
		hilo = new Thread(haciendo);
		hilo.start();
	}
}