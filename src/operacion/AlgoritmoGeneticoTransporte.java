/**
 * @(#)AlgoritmoGeneticoTransporte.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */
 
 import java.util.Random;
 import java.util.Vector; 
 
/**
 * Clase que implementa un algoritmo genético para resolver un problema de 
 * transporte genérico. Este algoritmo genético resuelve funciones objetivos
 * tanto lineales como no lineales, además acepta tres conjuntos de
 * restricciones lineales.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
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
 	
 	/** Variable que contiene la sumatoria de la función de evaluacion. */
 	private double sumatoriaFuncion;
 	
 	/** Variable que contiene el valor máximo de la población. */
 	private double maximoFuncion;
 	
 	/** Variable que contiene el valor mínimo de la población. */
 	private double minimoFuncion;
 	
 	/** Vector que guarda los mínimos en cada generación. */
 	private Vector evolucionFuncion;
 	
	/** Frame que se hace referencia al creador de este objeto. */
	public FrameOperacion frameOperacion;
 	
	/**
     * Método constructor que incializa los valores utilizados por el algoritmo
     * genético como parámetros.
     *
     * @param frameOperacion El frame donde se crea este objeto.
     */
 	public AlgoritmoGeneticoTransporte(FrameOperacion frameOperacion)
 	{
		this.frameOperacion = frameOperacion;
		
		// Crea un generador de números aleatorios.
		r = new Random();
		
		// Crea las instancias de los individuos de la población.
		inicializarIndividuos();
		
		// Crea las instancias de los contadores de índices.
		contadorI = new int[ConfiguracionAGT.m];
		contadorJ = new int[ConfiguracionAGT.n];
		contadorK = new int[ConfiguracionAGT.l];
 	}
 	
	/**
	 * Método que crea las instancias de los individuos de la población.
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
	 * Método que inicializa los contadores de índices de la matriz solución.
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
	 * Método que permite generar una población de soluciones iniciales
	 * factibles en con valores aleatorios para una función objetivo de tipo
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
	 * Método que crea una matriz que satisface las restricciones de oferta,
	 * demanda y capacidad de transporte para una función objetivo lineal.
	 *
	 * @param a Vector con restricciones de oferta.
	 * @param b Vector con restricciones de demanda.
	 * @param c Vector con restricciones de capacidad de transporte.
	 *
	 * @return matriz Matriz con una solución básica factible.
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
	 * Método que crea una matriz que satisface las restricciones de oferta,
	 * demanda y capacidad de transporte para una función objetivo no lineal.
	 *
	 * @param a Vector con restricciones de oferta.
	 * @param b Vector con restricciones de demanda.
	 * @param c Vector con restricciones de capacidad de transporte.
	 *
	 * @return matriz Matriz con una solución básica factible.
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
 		
 		// Resetear contadores de índices
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
	 		
	 		// Si i, j, k es la última vez que aparece
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
	 * Método que suma dos matrices tridimensionales de un rango especificado.
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
	 * Método que indica si un subíndice de i, j, k ha aparecido por última vez.
	 *
	 * @param i Indica el subindice i en la matriz de soluciones.
	 * @param j Indica el subindice j en la matriz de soluciones.
	 * @param k Indica el subindice k en la matriz de soluciones.
	 *
	 * @return condicion Valor booleano que indica si se cumple la condición.
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
	 * Método que devuelve el mínimo de tres valores reales.
	 *
	 * @param a Valor real.
	 * @param b Valor real.
	 * @param c Valor real.
	 *
	 * @return minimo Valor mínimo entre a, b y c.
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
	 * Método que realiza la evaluación de los individuos de la población
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
	 * Método que evalua un individuo de la población de acuerdo a una función
	 * objetivo de tipo lineal.
	 *
	 * @param mc Matriz de costos con valores reales.
	 * @param ms Matriz solución de valores reales.
	 *
	 * @return evaluacion Valor de evaluación obtenido.
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
	 * Método que evalúa un individuo de la población de acuerdo a una función
	 * objetivo de tipo lineal a trozos.
	 *
	 * @param mc Matriz de costos con valores reales.
	 * @param ms Matriz solución de valores reales.
	 *
	 * @return evaluacion Valor de evaluación obtenido.
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
 					
 					// construcción función lineal a trozos
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
	 * Método que evalúa un individuo de la población de acuerdo a una función
	 * objetivo de tipo razonable.
	 *
	 * @param mc Matriz de costos con valores reales.
	 * @param ms Matriz solución de valores reales.
	 *
	 * @return evaluacion Valor de evaluación obtenido.
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
	 * Método que realiza la selección de los individuos de la población actual
	 * que sobrevivirán a la generación siguiente. Las técnicas de selección
	 * utilzadas son la proporcional y la de torneo probabilística ambas con
	 * sustitución generacional completa.
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
	 * Método que asigna los límites máximos y mínimos de la población actual.
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
	 * Método que realiza la selección de los individuos de la población actual
	 * mediante la técnica de selección proporcional.
	 *
	 * @return poblacionSeleccionada Población con nuevos individuos que
	 *                               reemplaza a la población anterior.
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
 		
 		// Calcular la nueva evaluación
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
 		
 		// Nueva población
 		return poblacionSeleccionada;
 	}
 	
 	/**
	 * Método que realiza la selección de los individuos de la población actual
	 * mediante la técnica de selección por torneo que se basa en comparaciones
	 * directas entre dos individuos. La versión utilizada es probabilística.
	 *
	 * @return poblacionIntermedia Población con nuevos individuos que reemplaza
	 *                             a la población anterior.
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
 		
 		// Nueva población intermedia
 		for (i = 0;i < ganadores.size();i++)
 		{
 			poblacionIntermedia[i] = (IndividuoTransporte)
 									 ganadores.elementAt(i);
 		}
 		
 		return poblacionIntermedia;
 	}
 	
	/**
	 * Método que baraja a los individuos dentro de la población, es decir
	 * cambia sus ubicaciones en el vector.
	 *
	 * @param b Población original de individuos.
	 * @return nuevo Población con individuos barajados.
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
	 * Método que escoge en forma probabilística el ganador de un torneo en el
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
	 * Método que devuelve verdadero de acuerdo a una cierta probabilidad p. El 
	 * método genera un número aleatorio r entre 0 y 1 y devuelve verdadero en
	 * caso de que r <= p. De lo contrario devuelve falso.
	 *
	 * @param p Probabilidad (0.5 <= p <= 1).
	 *
	 * @return boolean Resultado de la función.
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
	 * Método que selecciona un subconjunto de la población actual para
	 * aplicarles el operador de cruce de acuerdo a una cierta probabilidad
	 * establecida como parámetro de entrada. Una vez obtenido el subconjunto se
	 * escogen 2 individuos de forma aleatoria, denominados padres y a los
	 * cuales se les aplicará el cruce, el resultado son 2 nuevos individuos
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
 			// Selección de individuos para el cruce
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
	 		
	 		// Reemplazar padres por hijos en población actual
	 		poblacion[i1] = hijos[0];
	 		poblacion[i2] = hijos[1];
	 	}
 	}
 	
	/**
	 * Método que realiza el cruce entre dos individuos denominados padres, los
	 * cuales originan dos individuos hijos que son mezclas aritméticas de
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
 		
 		// Realizar la mezcla aritmética de ambos padres
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
	 * Método que aplica el operador mutación a un grupo de individuos bajo
	 * cierta probabilidad entregada como parámetro de entrada. De acuerdo al
	 * tipo de función objetivo utilizada, se aplican dos operadores diferentes
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
	 * Método que aplica la mutación a un individuo de la población. El operador
	 * selecciona una submatriz de la matriz solución contenida en el individuo
	 * y aplica la inicialización lineal o no lineal, de acuerdo al tipo de
	 * función objetivo utilizada, a esa submatriz para encontrar un nuevo grupo
	 * de valores para esa submatriz y que satisfacen el conjunto de
	 * restricciones.
	 *
	 * @param padre Individuo al que se le aplica la mutación.
	 *
	 * @return hijo Individuo con nuevos valores en la solución.
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
 		
 		//  Posición de inicio dentro de la matriz padre
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
 		
 		// Establece la nueva solución encontrada
 		hijo.establecerMatriz(matriz);
 		
 		return hijo;
 	}
 	
	/**
	 * Método para encuentra la solución de mínimo costo en la población actual.
	 *
	 * @return optimo Individuo con la mejor solución.
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
	 * Método que inicializa una población con soluciones factibles aleatorias.
	 *
	 * @param panelEvolucion Panel que permite actualizar los datos de la
	 *                       gráfica.
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
	 * Método para realiza la ejecución del Algoritmo Genético.
	 *
	 * @return evolucionFuncion Vector de costos mínimos.
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
	 * Método para visualizar los valores de los individuos de la población.
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
	 * Método que genera una cadena de texto con las soluciones de la población
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