/**
 * @(#)AlgoritmoGeneticoTransporte.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */
 
 import java.util.Random;
 import java.util.Vector; 
 
/**
 * Clase que implementa un algoritmo gen�tico para resolver un problema de 
 * transporte gen�rico. Este algoritmo gen�tico resuelve funciones objetivos
 * tanto lineales como no lineales, adem�s acepta tres conjuntos de
 * restricciones lineales.
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see ConfiguracionAGT
 * @see Random
 * @see Vector
 * @see IndividuoTransporte
 */
public class AlgoritmoGeneticoTransporte
{
 	/** Arreglo que contiene un conjunto de soluciones. */
 	private IndividuoTransporte[] poblacion;
 	
 	/** Variable para generar valores aleatorios. */
 	private Random r;
 	
 	/** Variable para contar la cantidad de apariciones del indice i. */
 	private int[] contadorI;
	
 	/** Variable para contar la cantidad de apariciones del indice j. */
 	private int[] contadorJ;
	
 	/** Variable para contar la cantidad de apariciones del indice k. */
 	private int[] contadorK;
 	
 	/** Variable que contiene la sumatoria de la funci�n de evaluacion. */
 	private double sumatoriaFuncion;
 	
 	/** Variable que contiene el valor m�ximo de la poblaci�n. */
 	private double maximoFuncion;
 	
 	/** Variable que contiene el valor m�nimo de la poblaci�n. */
 	private double minimoFuncion;
 	
 	/** Vector que guarda los m�nimos en cada generaci�n. */
 	private Vector evolucionFuncion;
 	
	/** Frame que se hace referencia al creador de este objeto. */
	public FrameOperacion frameOperacion;
 	
	/**
     * M�todo constructor que incializa los valores utilizados por el algoritmo
     * gen�tico como par�metros.
     *
     * @param frameOperacion El frame donde se crea este objeto.
     */
 	public AlgoritmoGeneticoTransporte(FrameOperacion frameOperacion)
 	{
		this.frameOperacion = frameOperacion;
		
		// Crea un generador de n�meros aleatorios.
		r = new Random();
		
		// Crea las instancias de los individuos de la poblaci�n.
		inicializarIndividuos();
		
		// Crea las instancias de los contadores de �ndices.
		contadorI = new int[ConfiguracionAGT.m];
		contadorJ = new int[ConfiguracionAGT.n];
		contadorK = new int[ConfiguracionAGT.l];
 	}
 	
	/**
	 * M�todo que crea las instancias de los individuos de la poblaci�n.
	 */
 	private void inicializarIndividuos()
 	{
 		poblacion = new IndividuoTransporte[ConfiguracionAGT.tamanoPoblacion];
 		
 		for(int i=0;i<ConfiguracionAGT.tamanoPoblacion;i++)
 		{
 			poblacion[i] = new IndividuoTransporte();
 		}
 	}
 	
	/**
	 * M�todo que inicializa los contadores de �ndices de la matriz soluci�n.
	 */
 	private void inicializarContadoresIndices()
 	{
		int i;
		int j;
		int k;
		
		for(i = 0;i < ConfiguracionAGT.m;i++)
			contadorI[i] = 0;
		
		for(j = 0;j < ConfiguracionAGT.n;j++)
			contadorJ[j] = 0;
		
		for(k = 0;k < ConfiguracionAGT.l;k++)
			contadorK[k] = 0;
 	}
 	
	/**
	 * M�todo que permite generar una poblaci�n de soluciones iniciales
	 * factibles en con valores aleatorios para una funci�n objetivo de tipo
	 * lineal y no lineal.
	 */
 	private void generarPoblacionInicial()
 	{
 		Vector oferta;
 		Vector demanda;
 		Vector capacidad;
 		double[][][] m = new double[ConfiguracionAGT.m]
 								   [ConfiguracionAGT.n]
 								   [ConfiguracionAGT.l];
 		int i;
 		
		oferta = ConfiguracionAGT.obtenerOferta();
		demanda = ConfiguracionAGT.obtenerDemanda();
		capacidad = ConfiguracionAGT.obtenerCapacidad();
 		
 		for(i = 0;i < ConfiguracionAGT.tamanoPoblacion;i++)
 		{
			if(ConfiguracionAGT.tipoFuncion == 0)
			{
				m = inicializacionLineal(new Vector(oferta),
					new Vector(demanda),
					new Vector(capacidad));
			}
			else
			{
				m = inicializacionNoLineal(new Vector(oferta),
					new Vector(demanda),
					new Vector(capacidad));
			}
			
			poblacion[i].establecerMatriz(m);
 		}
 	}
 	
	/**
	 * M�todo que crea una matriz que satisface las restricciones de oferta,
	 * demanda y capacidad de transporte para una funci�n objetivo lineal.
	 *
	 * @param a Vector con restricciones de oferta.
	 * @param b Vector con restricciones de demanda.
	 * @param c Vector con restricciones de capacidad de transporte.
	 *
	 * @return matriz Matriz con una soluci�n b�sica factible.
	 */
 	private double[][][] inicializacionLineal(Vector a, Vector b, Vector c)
 	{
 		int i;
 		int j;
 		int k;
 		int rnd;
 		double valor;
 		double x;
 		double[][][] matriz = new double[a.size()][b.size()][c.size()];
 		
 		Double nuevoValor;
 		Vector visitados = new Vector();
 		ElementoLista azar;
 		
 		// Inicializar todos los Xijk como no visitados
 		for(i = 0;i < a.size();i++)
 			for(j = 0;j < b.size();j++)
 				for(k = 0;k < c.size();k++)
 				{
 					visitados.add(new ElementoLista(i,j,k));
 				}
 		
 		// Mientras existan elementos no visitados
 		while(! visitados.isEmpty())
 		{
	 		// Seleccionar un Xijk al azar y marcarlo como visitado
	 		rnd = r.nextInt(visitados.size());
	 		azar = (ElementoLista) visitados.elementAt(rnd);
	 		i = azar.i;
	 		j = azar.j;
	 		k = azar.k;
	 		visitados.removeElementAt(rnd);
	 		
	 		// Asignar el valor Xijk = min{ai, bj, ck}
	 		valor = minValor(Double.parseDouble(a.elementAt(i).toString()),
	 				Double.parseDouble(b.elementAt(j).toString()),
	 				Double.parseDouble(c.elementAt(k).toString()));
	 		matriz[i][j][k] = valor;
			
	 		// Actualizar los valores de oferta, demanda y capacidad
	 		nuevoValor = new Double(
	 					 Double.parseDouble(a.elementAt(i).toString()) - valor);
	 		a.setElementAt(nuevoValor.toString(), i);
	 		nuevoValor = new Double(
	 					 Double.parseDouble(b.elementAt(j).toString()) - valor);
	 		b.setElementAt(nuevoValor.toString(), j);
	 		nuevoValor = new Double(
	 					 Double.parseDouble(c.elementAt(k).toString()) - valor);
	 		c.setElementAt(nuevoValor.toString(), k);
 		}
 		
 		return matriz;
 	}
 	
	/**
	 * M�todo que crea una matriz que satisface las restricciones de oferta,
	 * demanda y capacidad de transporte para una funci�n objetivo no lineal.
	 *
	 * @param a Vector con restricciones de oferta.
	 * @param b Vector con restricciones de demanda.
	 * @param c Vector con restricciones de capacidad de transporte.
	 *
	 * @return matriz Matriz con una soluci�n b�sica factible.
	 */
 	private double[][][] inicializacionNoLineal(Vector a, Vector b, Vector c)
 	{
 		int i;
 		int j;
 		int k;
 		int rnd;
 		double valor;
 		double x;
 		double aux;
 		double[][][] matriz = new double[a.size()][b.size()][c.size()];
 		double[][][] resto = new double[a.size()][b.size()][c.size()];
 		
 		Double nuevoValor;
 		Vector visitados = new Vector();
 		ElementoLista azar;
 		
 		// Inicializar todos los Xijk como no visitados
 		for(i = 0;i < a.size();i++)
 			for(j = 0;j < b.size();j++)
 				for(k = 0;k < c.size();k++)
 				{
 					visitados.add(new ElementoLista(i,j,k));
 				}
 		
 		// Resetear contadores de �ndices
 		inicializarContadoresIndices();
 		
 		// Mientras existan elementos no visitados
 		while(! visitados.isEmpty())
 		{
	 		// Seleccionar un Xijk al azar y marcarlo como visitado
	 		rnd = r.nextInt(visitados.size());
	 		azar = (ElementoLista) visitados.elementAt(rnd);
	 		i = azar.i;
	 		j = azar.j;
	 		k = azar.k;
	 		visitados.removeElementAt(rnd);
	 		
	 		// Asignar el valor aux = min{ai, bj, ck}
	 		aux = minValor(Double.parseDouble(a.elementAt(i).toString()),
	 				Double.parseDouble(b.elementAt(j).toString()),
	 				Double.parseDouble(c.elementAt(k).toString()));
	 		
	 		// Si i, j, k es la �ltima vez que aparece
	 		if(condicionAsignacion(i, j, k) == true)
	 		{
	 			valor = aux;
	 		}
	 		else
	 		{
	 			// Valor real aleatorio entre [0,aux]
	 			if(aux > 1)
	 				valor = r.nextInt((int) aux) + r.nextDouble();
	 			else
	 			{
	 				x = r.nextDouble();
	 				if(x > aux)
	 					valor = aux;
	 				else
	 					valor = x;
	 			}
	 		}
	 		
	 		matriz[i][j][k] = valor;
			
	 		// Actualizar los valores de oferta, demanda y capacidad
	 		nuevoValor = new Double(
	 					 Double.parseDouble(a.elementAt(i).toString()) - valor);
	 		a.setElementAt(nuevoValor.toString(), i);
	 		nuevoValor = new Double(
	 					 Double.parseDouble(b.elementAt(j).toString()) - valor);
	 		b.setElementAt(nuevoValor.toString(), j);
	 		nuevoValor = new Double(
	 					 Double.parseDouble(c.elementAt(k).toString()) - valor);
	 		c.setElementAt(nuevoValor.toString(), k);
 		}
 		
 		// Obtiene una matriz con el sobrante de las restricciones
 		resto = inicializacionLineal(a, b, c);
 		
 		int[] rango = {a.size(), b.size(), c.size()};
 		
 		matriz = sumarMatrices(matriz, resto, rango);
		
 		return matriz;
 	}
	
	/**
	 * M�todo que suma dos matrices tridimensionales de un rango especificado.
	 *
	 * @param a Matriz de valores reales.
	 * @param b Matriz de valores reales.
	 * @param rango Rango de la matriz tridimensional.
	 *
	 * @return resultado Matriz con el resultado de la suma.
	 */
 	private double[][][] sumarMatrices(double[][][] a, double[][][] b,
 									   int[] rango)
 	{
 		int i;
 		int j;
 		int k;
 		double[][][] resultado = new double[rango[0]][rango[1]][rango[2]];
 		
 		for(i = 0;i < rango[0];i++)
 			for(j = 0;j < rango[1];j++)
 				for(k = 0;k < rango[2];k++)
 				{
 					resultado[i][j][k] = a[i][j][k] + b[i][j][k];
 				}
 		
 		return resultado;
 	}
 	
	/**
	 * M�todo que indica si un sub�ndice de i, j, k ha aparecido por �ltima vez.
	 *
	 * @param i Indica el subindice i en la matriz de soluciones.
	 * @param j Indica el subindice j en la matriz de soluciones.
	 * @param k Indica el subindice k en la matriz de soluciones.
	 *
	 * @return condicion Valor booleano que indica si se cumple la condici�n.
	 */
 	private boolean condicionAsignacion(int i, int j, int k)
 	{
 		boolean condicion = false;
 		
 		// Incrementar el numero de apariciones del indices i, j, k
 		contadorI[i] = contadorI[i] + 1;
 		contadorJ[j] = contadorJ[j] + 1;
 		contadorK[k] = contadorK[k] + 1;
 		
 		if(contadorI[i] >= ConfiguracionAGT.n*ConfiguracionAGT.l)
 			condicion = true;
 		
 		if(contadorJ[j] >= ConfiguracionAGT.m*ConfiguracionAGT.l)
 			condicion = true;
		
 		if(contadorK[k] >= ConfiguracionAGT.m*ConfiguracionAGT.n)
 			condicion = true;
 		
 		return condicion;
 	}
 	
	/**
	 * M�todo que devuelve el m�nimo de tres valores reales.
	 *
	 * @param a Valor real.
	 * @param b Valor real.
	 * @param c Valor real.
	 *
	 * @return minimo Valor m�nimo entre a, b y c.
	 */
 	private double minValor(double a, double b, double c)
 	{
 		double minimo;
 		
 		if(a < b)
 		{
 			if(a < c)
 			{
 				minimo = a;
 			}
 			else
 			{
 				minimo = c;
 			}
 		}
 		else
 		{
 			if(b < c)
 			{
 				minimo = b;
 			}
 			else
 			{
 				minimo = c;
 			}
 		}
 		
 		return minimo;
 	}
 	
	/**
	 * M�todo que realiza la evaluaci�n de los individuos de la poblaci�n
	 * actual.
	 */
 	private void funcionEvaluacion()
 	{
  		int i;
  		double valor = 0;
  		
  		for(i = 0;i < ConfiguracionAGT.tamanoPoblacion;i++)
 		{
			if(ConfiguracionAGT.tipoFuncion == 0)
			{
				valor = evaluacionLineal(ConfiguracionAGT.matrizCostos,
						poblacion[i].obtenerSolucion());
			}
			else
			if(ConfiguracionAGT.tipoFuncion == 1)
			{
				valor = evaluacionNoLinealUno(ConfiguracionAGT.matrizCostos,
						poblacion[i].obtenerSolucion());
			}
			else
			if(ConfiguracionAGT.tipoFuncion == 2)
			{
				valor = evaluacionNoLinealDos(ConfiguracionAGT.matrizCostos,
						poblacion[i].obtenerSolucion());
			}
			poblacion[i].establecerValorEvaluacion(valor);
 		}
 	}
 	
	/**
	 * M�todo que evalua un individuo de la poblaci�n de acuerdo a una funci�n
	 * objetivo de tipo lineal.
	 *
	 * @param mc Matriz de costos con valores reales.
	 * @param ms Matriz soluci�n de valores reales.
	 *
	 * @return evaluacion Valor de evaluaci�n obtenido.
	 */
 	private double evaluacionLineal(double[][][] mc,double[][][] ms)
 	{
 		int i;
 		int j;
 		int k;
 		double evaluacion = 0;
 		
 		for(i = 0;i < ConfiguracionAGT.m;i++)
 			for(j = 0;j < ConfiguracionAGT.n;j++)
 				for(k = 0;k < ConfiguracionAGT.l;k++)
 				{
 					evaluacion = evaluacion + mc[i][j][k]*ms[i][j][k];
 				}
 		
 		return evaluacion;
 	}
 	
	/**
	 * M�todo que eval�a un individuo de la poblaci�n de acuerdo a una funci�n
	 * objetivo de tipo lineal a trozos.
	 *
	 * @param mc Matriz de costos con valores reales.
	 * @param ms Matriz soluci�n de valores reales.
	 *
	 * @return evaluacion Valor de evaluaci�n obtenido.
	 */
 	private double evaluacionNoLinealUno(double[][][] mc,double[][][] ms)
 	{
 		int i;
 		int j;
 		int k;
 		double evaluacion = 0;
 		double costo;
 		int S = 2;
 		
 		for(i = 0;i < ConfiguracionAGT.m;i++)
 			for(j = 0;j < ConfiguracionAGT.n;j++)
 				for(k = 0;k < ConfiguracionAGT.l;k++)
 				{
 					costo = 0;
 					
 					// construcci�n funci�n lineal a trozos
 					if(0 <= ms[i][j][k] && ms[i][j][k] <= S)
 					{
 						costo = 0;
 					}
 					else
 					if(S < ms[i][j][k] && ms[i][j][k] <= (2*S))
 					{
 						costo = mc[i][j][k];
 					}
 					else
 					if((2*S) < ms[i][j][k] && ms[i][j][k] <= (3*S))
 					{
 						costo = 2*mc[i][j][k];
 					}
 					else
 					if((3*S) < ms[i][j][k] && ms[i][j][k] <= (4*S))
 					{
 						costo = 3*mc[i][j][k];
 					}
 					else
 					if((4*S) < ms[i][j][k] && ms[i][j][k] <= (5*S))
 					{
 						costo = 4*mc[i][j][k];
 					}
 					else
 					if(ms[i][j][k] > (5*S))
 					{
 						costo = 5*mc[i][j][k];
 					}
 					evaluacion = evaluacion + costo;
 				}
 		
 		return evaluacion;
 	}
	
	/**
	 * M�todo que eval�a un individuo de la poblaci�n de acuerdo a una funci�n
	 * objetivo de tipo razonable.
	 *
	 * @param mc Matriz de costos con valores reales.
	 * @param ms Matriz soluci�n de valores reales.
	 *
	 * @return evaluacion Valor de evaluaci�n obtenido.
	 */
 	private double evaluacionNoLinealDos(double[][][] mc,double[][][] ms)
 	{
 		int i;
 		int j;
 		int k;
 		double evaluacion = 0;
 		int c = 5;
 		
 		for(i = 0;i < ConfiguracionAGT.m;i++)
 			for(j = 0;j < ConfiguracionAGT.n;j++)
 				for(k = 0;k < ConfiguracionAGT.l;k++)
 				{
 					evaluacion = evaluacion +
 								 (mc[i][j][k]*Math.sqrt(ms[i][j][k] + c));
 				}
 		
 		return evaluacion;
 	}
 	
	/**
	 * M�todo que realiza la selecci�n de los individuos de la poblaci�n actual
	 * que sobrevivir�n a la generaci�n siguiente. Las t�cnicas de selecci�n
	 * utilzadas son la proporcional y la de torneo probabil�stica ambas con
	 * sustituci�n generacional completa.
	 */
 	private void operadorSeleccion()
 	{
		if(ConfiguracionAGT.tecnicaSeleccion == 0)
		{
			poblacion = seleccionProporcional();
		}
		else
		{
			poblacion = seleccionTorneo();
		}
 	}
 	
	/**
	 * M�todo que asigna los l�mites m�ximos y m�nimos de la poblaci�n actual.
	 */
 	private void asignarLimites()
 	{
 		double valor;
 		
 		// asignar valores iniciales
 		minimoFuncion = poblacion[0].obtenerEvaluacion();
 		maximoFuncion = minimoFuncion;
 		
 		for(int i = 0;i < ConfiguracionAGT.tamanoPoblacion; i++)
 		{
 			valor = poblacion[i].obtenerEvaluacion();
 			if(minimoFuncion > valor)
 				minimoFuncion = valor;
 			if(maximoFuncion < valor)
 				maximoFuncion = valor;
 		}
 	}
 	
	/**
	 * M�todo que realiza la selecci�n de los individuos de la poblaci�n actual
	 * mediante la t�cnica de selecci�n proporcional.
	 *
	 * @return poblacionSeleccionada Poblaci�n con nuevos individuos que
	 *                               reemplaza a la poblaci�n anterior.
	 */
 	private IndividuoTransporte[] seleccionProporcional()
 	{
 		int i;
 		int j;
 		double valor;
 		double rnd;
 		double[] calificacionAcumulada =
 			new double[ConfiguracionAGT.tamanoPoblacion];
 		
 		IndividuoTransporte[] poblacionIntermedia =
 			new IndividuoTransporte[ConfiguracionAGT.tamanoPoblacion];
 		IndividuoTransporte[] poblacionSeleccionada =
 			new IndividuoTransporte[ConfiguracionAGT.tamanoPoblacion];
 		
 		poblacionIntermedia = poblacion;
 		sumatoriaFuncion = 0;
 		
 		// Calcular la nueva evaluaci�n
 		for(i = 0;i < ConfiguracionAGT.tamanoPoblacion;i++)
 		{
 			valor = (maximoFuncion + minimoFuncion) -
 					poblacionIntermedia[i].obtenerEvaluacion();
 			poblacionIntermedia[i].establecerValorEvaluacion(valor);
 			sumatoriaFuncion = sumatoriaFuncion + valor;
 			calificacionAcumulada[i] = sumatoriaFuncion;
 		}
 		
 		// Seleccionar individuos
 		for(i = 0;i < ConfiguracionAGT.tamanoPoblacion;i++)
 		{
 			rnd = Math.random() * sumatoriaFuncion;
 			for(j = 0;j < ConfiguracionAGT.tamanoPoblacion;j++)
 			{
 				poblacionSeleccionada[i] = new IndividuoTransporte();
 				
 				if(calificacionAcumulada[j] > rnd)
 				{
 					poblacionSeleccionada[i] = poblacionIntermedia[j];
 					break;
 				}
 			}
 		}
 		
 		// Nueva poblaci�n
 		return poblacionSeleccionada;
 	}
 	
 	/**
	 * M�todo que realiza la selecci�n de los individuos de la poblaci�n actual
	 * mediante la t�cnica de selecci�n por torneo que se basa en comparaciones
	 * directas entre dos individuos. La versi�n utilizada es probabil�stica.
	 *
	 * @return poblacionIntermedia Poblaci�n con nuevos individuos que reemplaza
	 *                             a la poblaci�n anterior.
	 */
 	private IndividuoTransporte[] seleccionTorneo()
 	{
 		int i;
 		Vector baraja = new Vector();
 		Vector ganadores = new Vector();
 		IndividuoTransporte[] poblacionIntermedia =
 			new IndividuoTransporte[ConfiguracionAGT.tamanoPoblacion];
 		
 		for(i = 0;i < ConfiguracionAGT.tamanoPoblacion;i++)
 		{
 			baraja.add(poblacion[i]);
 		}
 		
 		// Primera barajada
 		baraja = barajarPoblacion(baraja);
 		
 		// Agregar ganadores de la primera barajada
 		for(i = 0;i < baraja.size();i = i + 2)
 		{
 			try
 			{
				ganadores.add(
					ganadorTorneo((IndividuoTransporte) baraja.elementAt(i),
								  (IndividuoTransporte) baraja.elementAt(i+1)));
 			}
 			catch(ArrayIndexOutOfBoundsException msg)
 			{
 				// No seleccionar individuo
 			}
 		}
 		
 		// Segunda barajada
 		baraja = barajarPoblacion(baraja);
 		
 		// Agregar ganadores segunda barajada
 		for(i = 0;i < baraja.size();i = i + 2)
 		{
 			try
 			{
				ganadores.add(
					ganadorTorneo((IndividuoTransporte) baraja.elementAt(i),
								  (IndividuoTransporte) baraja.elementAt(i+1)));
 			}
 			catch(ArrayIndexOutOfBoundsException msg)
 			{
				ganadores.add(
					ganadorTorneo((IndividuoTransporte) baraja.elementAt(i),
								  (IndividuoTransporte) baraja.elementAt(i)));
 			}
 		}
 		
 		// Nueva poblaci�n intermedia
 		for (i = 0;i < ganadores.size();i++)
 		{
 			poblacionIntermedia[i] = (IndividuoTransporte)
 									 ganadores.elementAt(i);
 		}
 		
 		return poblacionIntermedia;
 	}
 	
	/**
	 * M�todo que baraja a los individuos dentro de la poblaci�n, es decir
	 * cambia sus ubicaciones en el vector.
	 *
	 * @param b Poblaci�n original de individuos.
	 * @return nuevo Poblaci�n con individuos barajados.
	 */
 	private Vector barajarPoblacion(Vector b)
 	{
 		int rnd;
 		Vector nuevo = new Vector();
 		
 		while (! b.isEmpty())
 		{
	 		rnd = r.nextInt(b.size());
	 		nuevo.add(b.elementAt(rnd));
	 		b.removeElementAt(rnd);
 		}
 		
 		return nuevo;
 	}
 	
	/**
	 * M�todo que escoge en forma probabil�stica el ganador de un torneo en el
	 * cual participan dos individuos.
	 *
	 * @param i1 Primer individuo seleccionado.
	 * @param i2 Segundo individuo seleccionado.
	 *
	 * @return ganador Individuo ganador del torneo.
	 */
 	private IndividuoTransporte ganadorTorneo(IndividuoTransporte i1,
 											  IndividuoTransporte i2)
 	{
 		IndividuoTransporte ganador = new IndividuoTransporte();
 		
 		if(flip(ConfiguracionAGT.PROBABILIDAD_FLIP) == true)
 		{
	 		if(i1.obtenerEvaluacion() <= i2.obtenerEvaluacion())
	 			ganador = i1;
	 		else
	 			ganador = i2;
	 	}
	 	else
	 	{
	 		if(i1.obtenerEvaluacion() <= i2.obtenerEvaluacion())
	 			ganador = i2;
	 		else
	 			ganador = i1;
	 	}
	 	
 		return ganador;
 	}
 	
	/**
	 * M�todo que devuelve verdadero de acuerdo a una cierta probabilidad p. El 
	 * m�todo genera un n�mero aleatorio r entre 0 y 1 y devuelve verdadero en
	 * caso de que r <= p. De lo contrario devuelve falso.
	 *
	 * @param p Probabilidad (0.5 <= p <= 1).
	 *
	 * @return boolean Resultado de la funci�n.
	 */
 	private boolean flip(double p)
 	{
 		double r = Math.random();
 		
 		if(r <= p)
 			return true;
 		else
 			return false;
 	}
	
	/**
	 * M�todo que selecciona un subconjunto de la poblaci�n actual para
	 * aplicarles el operador de cruce de acuerdo a una cierta probabilidad
	 * establecida como par�metro de entrada. Una vez obtenido el subconjunto se
	 * escogen 2 individuos de forma aleatoria, denominados padres y a los
	 * cuales se les aplicar� el cruce, el resultado son 2 nuevos individuos
	 * denominados hijos los cuales reemplazan a los padres.
	 */
 	private void operadorCruce()
 	{
 		int i;
 		int resto;
 		int i1;
 		int i2;
 		int azar;
 		double rnd;
 		String individuo;
 		Vector cruce = new Vector();
 		IndividuoTransporte[] hijos = new IndividuoTransporte[2];
 		
 		// Realizar mientras la cantidad de seleccionados sea menor a dos
 		do
 		{
 			// Selecci�n de individuos para el cruce
	 		for(i = 0;i < ConfiguracionAGT.tamanoPoblacion;i++)
	 		{
	 			rnd = Math.random();
	 			if(rnd <= ConfiguracionAGT.probabilidadCruce)
	 			{
	 				individuo = ""+i;
	 				cruce.add(individuo);
	 			}
	 		}
	 		resto = cruce.size() % 2;
	 		if(resto != 0)
	 			cruce.remove(r.nextInt(cruce.size()));
	 	}
	 	while(cruce.size() < 2);
	 	
	 	// Cruzar individuos en forma aleatoria
	 	while(!cruce.isEmpty())
	 	{
	 		// Primer individuo padre
	 		azar = r.nextInt(cruce.size());
	 		i1 = Integer.parseInt(cruce.elementAt(azar).toString());
	 		cruce.removeElementAt(azar);
			
	 		// Segundo individuo padre
	 		azar = r.nextInt(cruce.size());
	 		i2 = Integer.parseInt(cruce.elementAt(azar).toString());
	 		cruce.removeElementAt(azar);
	 		
	 		hijos = realizarCruce(poblacion[i1], poblacion[i2]);
	 		
	 		// Reemplazar padres por hijos en poblaci�n actual
	 		poblacion[i1] = hijos[0];
	 		poblacion[i2] = hijos[1];
	 	}
 	}
 	
	/**
	 * M�todo que realiza el cruce entre dos individuos denominados padres, los
	 * cuales originan dos individuos hijos que son mezclas aritm�ticas de
	 * ambos.
	 *
	 * @param padre1 Es el primer individuo padre de la cruza.
	 * @param padre2 Es el segundo individuo padre de la cruza.
	 *
	 * @return hijos Dos nuevos individuos originados por la cruza.
	 */
 	private IndividuoTransporte[] realizarCruce(IndividuoTransporte padre1,
 												IndividuoTransporte padre2)
 	{
 		IndividuoTransporte[] hijos = new IndividuoTransporte[2];
 		hijos[0] = new IndividuoTransporte();
 		hijos[1] = new IndividuoTransporte();
 		int i;
 		int j;
 		int k;
 		double r1;
 		double r2;
 		
 		double[][][] p1 = padre1.obtenerSolucion();
 		double[][][] p2 = padre2.obtenerSolucion();
 		
 		double[][][] h1 = new double[ConfiguracionAGT.m]
 									[ConfiguracionAGT.n]
 									[ConfiguracionAGT.l];
		
 		double[][][] h2 = new double[ConfiguracionAGT.m]
 									[ConfiguracionAGT.n]
 									[ConfiguracionAGT.l];
 		
 		r1 = Math.random();
 		r2 = 1 - r1;
 		
 		// Realizar la mezcla aritm�tica de ambos padres
 		for(i = 0;i < ConfiguracionAGT.m;i++)
 			for(j = 0;j < ConfiguracionAGT.n;j++)
 				for(k = 0;k < ConfiguracionAGT.l;k++)
 				{
 					h1[i][j][k] = r1*p1[i][j][k] + r2*p2[i][j][k];
 					h2[i][j][k] = r1*p2[i][j][k] + r2*p1[i][j][k];
 				}
 		
 		// Establecer las nuevas soluciones en los hijos
 		hijos[0].establecerMatriz(h1);
 		hijos[1].establecerMatriz(h2);
 		
 		return hijos;
 	}
 	
	/**
	 * M�todo que aplica el operador mutaci�n a un grupo de individuos bajo
	 * cierta probabilidad entregada como par�metro de entrada. De acuerdo al
	 * tipo de funci�n objetivo utilizada, se aplican dos operadores diferentes
	 * para el caso lineal y el no lineal.
	 */
 	private void operadorMutacion()
 	{
 		int i;
 		double rnd;
 		
 		for(i = 0;i < ConfiguracionAGT.tamanoPoblacion;i++)
 		{
 			rnd = Math.random();
 			if(rnd <= ConfiguracionAGT.probabilidadMutacion)
 			{
 				poblacion[i] = realizarMutacion(poblacion[i]);
 			}
 		}
 	}
 	
	/**
	 * M�todo que aplica la mutaci�n a un individuo de la poblaci�n. El operador
	 * selecciona una submatriz de la matriz soluci�n contenida en el individuo
	 * y aplica la inicializaci�n lineal o no lineal, de acuerdo al tipo de
	 * funci�n objetivo utilizada, a esa submatriz para encontrar un nuevo grupo
	 * de valores para esa submatriz y que satisfacen el conjunto de
	 * restricciones.
	 *
	 * @param padre Individuo al que se le aplica la mutaci�n.
	 *
	 * @return hijo Individuo con nuevos valores en la soluci�n.
	 */
 	private IndividuoTransporte realizarMutacion(IndividuoTransporte padre)
 	{
 		int p1;
 		int p2;
 		int q1;
 		int q2;
 		int s1;
 		int s2;
 		int i;
 		int j;
 		int k;
 		int x;
 		int y;
 		int z;
 		double[][][] matriz;
 		double[][][] submatriz;
 		double valor;
 		IndividuoTransporte hijo = new IndividuoTransporte();
 		matriz = padre.obtenerSolucion();
 		String sm;
 		
 		// Establecer el rango p*q*s de la submatriz
 		p1 = r.nextInt(ConfiguracionAGT.m) + 1;
 		q1 = r.nextInt(ConfiguracionAGT.n) + 1;
 		s1 = r.nextInt(ConfiguracionAGT.l) + 1;
 		submatriz = new double[p1][q1][s1];
 		
 		//  Posici�n de inicio dentro de la matriz padre
 		p2 = r.nextInt(ConfiguracionAGT.m - p1 + 1);
 		q2 = r.nextInt(ConfiguracionAGT.n - q1 + 1);
 		s2 = r.nextInt(ConfiguracionAGT.l - s1 + 1);
 		
 		for(i = p2, x = 0;x < p1;i++, x++)
 			for(j = q2, y = 0;y < q1;j++, y++)
 				for(k = s2, z = 0;z < s1;k++, z++)
 				{
 					submatriz[x][y][z] = matriz[i][j][k];
 				}
 		
 		// Calcular las nuevas restricciones de oferta
 		Vector a = new Vector();
 		for(i = 0;i < p1;i++)
 		{
 			valor = 0;
 			for(j = 0;j < q1;j++)
 				for(k = 0;k < s1;k++)
 				{
 					valor = valor + submatriz[i][j][k];
 				}
 			a.add(new Double(valor));
 		}
		
 		// Calcular las nuevas restricciones de demanda
 		Vector b = new Vector();
 		for(j = 0;j < q1;j++)
 		{
 			valor = 0;
 			for(i = 0;i < p1;i++)
 				for(k = 0;k < s1;k++)
 				{
 					valor = valor + submatriz[i][j][k];
 				}
 			b.add(new Double(valor));
 		}
		
 		// Calcular las nuevas restricciones de capacidad
 		Vector c = new Vector();
 		for(k = 0;k < s1;k++)
 		{
 			valor = 0;
 			for(i = 0;i < p1;i++)
 				for(j = 0;j < q1;j++)
 				{
 					valor = valor + submatriz[i][j][k];
 				}
 			c.add(new Double(valor));
 		}
 		
 		if(ConfiguracionAGT.tipoFuncion == 0)
 		{
	 		// Asignar nuevos valores para la submatriz para el caso lineal
	 		submatriz = inicializacionLineal(a, b, c);
 		}
 		else
 		{
	 		// Asignar nuevos valores para la submatriz para el caso no lineal
	 		submatriz = inicializacionNoLineal(a, b, c);
 		}
 		
 		// Sustituir los nuevos elementos de la submatriz en la matriz original
 		for(i = p2, x = 0;x < p1;i++, x++)
 			for(j = q2, y = 0;y < q1;j++, y++)
 				for(k = s2, z = 0;z < s1;k++, z++)
 				{
 					 matriz[i][j][k] = submatriz[x][y][z];
 				}
 		
 		// Establece la nueva soluci�n encontrada
 		hijo.establecerMatriz(matriz);
 		
 		return hijo;
 	}
 	
	/**
	 * M�todo para encuentra la soluci�n de m�nimo costo en la poblaci�n actual.
	 *
	 * @return optimo Individuo con la mejor soluci�n.
	 */
	public IndividuoTransporte encontrarOptimo()
	{
		IndividuoTransporte optimo = new IndividuoTransporte();
		int i;
		double min;
		
		optimo = poblacion[0];
		min = optimo.obtenerEvaluacion();
		
		for(i = 1;i < ConfiguracionAGT.tamanoPoblacion;i++)
		{
			if(min > poblacion[i].obtenerEvaluacion())
			{
				optimo = poblacion[i];
				min = optimo.obtenerEvaluacion();
			}
		}
		
		return optimo;
	}
	
	/**
	 * M�todo que inicializa una poblaci�n con soluciones factibles aleatorias.
	 *
	 * @param panelEvolucion Panel que permite actualizar los datos de la
	 *                       gr�fica.
	 */
	public void inicializarAG(PanelEvolucionOperacion panelEvolucion)
	{
	 	evolucionFuncion = new Vector();
	 	generarPoblacionInicial();
	 	funcionEvaluacion();
	 	asignarLimites();
	 	evolucionFuncion.add(""+minimoFuncion);
	 	panelEvolucion.actualizarDatos(maximoFuncion);
	}
	
	/**
	 * M�todo para realiza la ejecuci�n del Algoritmo Gen�tico.
	 *
	 * @return evolucionFuncion Vector de costos m�nimos.
	 */
 	public Vector ejecutarAG()
 	{
 		int i;
 		int j;
 		int k;
 		double indice;
 		double aux;
 		
 		for(i = 0;i < ConfiguracionAGT.numeroGeneraciones;i++)
 		{
			operadorSeleccion();
			operadorCruce();
			operadorMutacion();
			funcionEvaluacion();
			asignarLimites();
			evolucionFuncion.add(""+minimoFuncion);
			this.frameOperacion.indiceBarraProgreso ++;
			indice = ((double) this.frameOperacion.indiceBarraProgreso /
							   this.frameOperacion.cantidadTotal) * 100;
			this.frameOperacion.panelEscritorio.frameCircanaPro.
				panelEstado.cambiarBarraOperacion((int) indice);
			
			ProcesoSegundoPlano.dormir();
 		}
 		
 		return evolucionFuncion;
 	}
	
	/**
	 * M�todo para visualizar los valores de los individuos de la poblaci�n.
	 */
 	public void verDatos()
 	{
 		System.out.println("Poblacion Actual");
 		for(int i=0;i<ConfiguracionAGT.tamanoPoblacion;i++)
 		{
 			System.out.println(poblacion[i].obtenerSolucionString() +
 							   " = " + poblacion[i].obtenerEvaluacion());
 		}
 	}
 	
	/**
	 * M�todo que genera una cadena de texto con las soluciones de la poblaci�n
	 * actual.
	 *
	 * @param encabezado Encabezado del texto.
	 *
	 * @return solucion Cadena de texto con las soluciones encontradas.
	 */
 	public String generarReporte(String encabezado)
 	{
 		String solucion = "" + encabezado + "\n";
 		
 		for(int i=0;i<ConfiguracionAGT.tamanoPoblacion;i++)
 		{
 			solucion =	solucion +
 						poblacion[i].obtenerSolucionString() + " = " +
 						Double.toString(poblacion[i].obtenerEvaluacion()) + "\n";
 		}
 		
 		return solucion;
 	}
 }