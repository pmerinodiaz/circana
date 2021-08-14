/**
 * @(#)ProcesoSegundoPlano.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import javax.swing.SwingUtilities;

/**
 * Clase que maneja los procesos secundarios en la apliaci�n. Los procesos 
 * secundarios est�n divididos en los m�dulos de: ecosistema, econom�a,
 * operaci�n e integraci�n. Es importante hacer notar que los procesos
 * secundarios son la administraci�n especializadas de los hilos (THREAD), lo
 * que implica que esta clase est� basada en ellos.
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
 * vez se debe instanciar otro objeto de esta clase (a�n no testeado). Basado
 * en teor�a de hilos para swing (diferentes a los de Apleet).
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
	
	/** Constante que indica si es que hay alg�n proceso ejecutandose. */
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
     * Indica qu� proceso del m�dulo elegido es ejecutado (Rango entre [0, n]).
     */
    private int indiceProceso;
    
    /**
	 * M�todo constructor que sirve para ser llamado desde el frame de
	 * ecosistema que tiene la aplicaci�n. En este m�todo se inicializan el
	 * objeto que se usar� posteriormente en la clase.
	 *
	 * @param frameEcosistema Es un referenciador al FrameEcosistema.
	 */
	public ProcesoSegundoPlano(FrameEcosistema frameEcosistema)
	{
		this.frameEcosistema = frameEcosistema;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el frame de econom�a
	 * que tiene la aplicaci�n. En este m�todo se inicializan el objeto que se
	 * usar� posteriormente en la clase.
	 *
	 * @param frameEconomia Es un referenciador al FrameEconomia.
	 */
	public ProcesoSegundoPlano(FrameEconomia frameEconomia)
	{
		this.frameEconomia = frameEconomia;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el frame de operaci�n
	 * que tiene la aplicaci�n. En este m�todo se inicializan el objeto que se
	 * usar� posteriormente en la clase.
	 *
	 * @param FrameOperacion Es un referenciador al FrameOperacion.
	 */
	public ProcesoSegundoPlano(FrameOperacion frameOperacion)
	{
		this.frameOperacion = frameOperacion;
	}
	
	/**
	 * M�todo constructor que sirve para ser llamado desde el frame de
	 * integraci�n que tiene la aplicaci�n. En este m�todo se inicializan el
	 * objeto que se usar� posteriormente en la clase.
	 *
	 * @param FrameIntegracion Es un referenciador al FrameIntegracion.
	 */
	public ProcesoSegundoPlano(FrameIntegracion frameIntegracion)
	{
		this.frameIntegracion = frameIntegracion;
	}
	
	/**
	 * M�todo que ejecuta el proceso inidicado anteriormente en seguno plano.
	 * Este proceso puede ser para cualquier m�dulo.
	 */
	private void procesosEjecutar()
	{
		// Si es un proceso de FrameEcosistema.
		if (frameEcosistema != null)
		{
			// Si es el proceso de simular din�micas.
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
			// Si es el proceso ejecutar los algoritmos gen�ticos.
			if (indiceProceso == 0)
				frameOperacion.ejecutarAlgoritmoGenetico();
		}
		
		// Si es un proceso de FrameIntegracion.
		else
		if (frameIntegracion != null)
		{
			// Si es el proceso de planificaci�n las ventas.
			if (indiceProceso == 0)
				frameIntegracion.planificarHeuristica();
		}
	}
	
	/**
	 * M�todo que duerme a cualquier hilo durante los milli-segundos declarados
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
	 * M�todo que indica si se est� ejecutando el proceso.
	 *
	 * @return ejecutando Indica si el proceso se est� ejecuntando o no.
	 */
	public boolean estaEjecutandose()
	{
		boolean ejecutando = hilo.isAlive();
		
		if (!ejecutando)
			indiceProceso = NO_EJECUTANDOSE;
		
		return ejecutando;
	}
	
	/**
	 * M�todo que retorna el proceso que esta ejecutando. Retorna
	 * NO_EJECUTANDOSE si no est� ejecutando ning�n proceso.
	 *
	 * @return indiceProceso Indica cual proceso se esta ejecutando.
	 */
	public int obtenerIndiceProceso()
	{
		estaEjecutandose();
		
		return indiceProceso;
	}
	
	/**
	 * M�todo que detiene un hilo.
	 */
	public void detener()
	{
	}
	
	/**
	 * M�todo que ejecuta un proceso en segundo plano determinado para cada
	 * m�dulo.
	 *
	 * @param indiceProceso Indica cu�l es el proceso se va ejecutar.
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