/**
 * @(#)AlgoritmoGeneticoRuta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */
 
 import java.util.Random;
 import java.util.Vector;  
 
 /**
 * Clase que implementa un algoritmo gen�tico para resolver el problema del
 * agente viajero. Este algoritmo encuentra un circuito hamiltoniano en un grafo
 * establecido en el cual la soluci�n encontrada es una ruta tal que la
 * distancia recorrida sea m�nima (no asegura la soluci�n �ptima solo una
 * suboptimal).
 *
 * @version 2.0 01/03/05
 * @author H�ctor D�az
 * @see ConfiguracionAGR
 * @see Random
 * @see Vector
 * @see VectorRuta
 * @see IndividuoRuta
 * @see FrameOperacion
 */
public class AlgoritmoGeneticoRuta
{
	/** Vector que contiene los nodos considerados para visitar. */
 	private VectorRuta grafo;
 	
 	/** Arreglo que contiene un conjunto de soluciones. */
 	private IndividuoRuta[] poblacion;
 	
 	/** Variable para generar valores aleatorios. */
 	private Random r;
 	
 	/** Cantidad de puntos de oferta. */
 	private int numeroOferta;
 	
 	/**
 	 * Indica el individuo peor evaluado en la generaci�n actual antes de la
 	 * penalizaci�n.
 	 */
 	private double peorSolucion;
 	
 	/** Variable que contiene la sumatoria de la funci�n de evaluaci�n. */
 	private double sumatoriaFuncion;
 	
 	/** Variable que contiene el valor m�ximo de la poblaci�n. */
 	private double maximoFuncion;
 	
 	/** Variable que contiene el valor m�nimo de la poblaci�n. */
 	private double minimoFuncion;
 	
	/** Frame que se hace referencia al creador de este objeto. */
	public FrameOperacion frameOperacion;
 	
 	/**
 	 * M�todo constructor que inicializa los atributos de la clase.
 	 *
 	 * @param frameOperacion El frame creador de este objeto.
 	 * @param grafo El vector de la ruta.
 	 */
 	public AlgoritmoGeneticoRuta(FrameOperacion frameOperacion,
 								 VectorRuta grafo)
 	{
 		// Inicializa.
 		this.frameOperacion = frameOperacion;
 		this.grafo = new VectorRuta();
 		this.grafo = grafo;
 		
 		// Crea un generador de n�meros aleatorios.
 		r = new Random();
 		
		// Crea las instancias de los individuos de la poblaci�n.
		inicializarIndividuos();
		
		// cantidad de puntos de oferta.
		numeroOferta = grafo.sumatoriaTipoElemento("Oferta");
 	}
 	
	/**
	 * M�todo que crea las instancias de los individuos de la poblaci�n.
	 */
 	private void inicializarIndividuos()
 	{
 		poblacion = new IndividuoRuta[ConfiguracionAGR.tamanoPoblacion];
 		
 		for(int i=0;i<ConfiguracionAGR.tamanoPoblacion;i++)
 		{
 			poblacion[i] = new IndividuoRuta();
 		}
 	}
 	
 	/**
 	 * M�todo que genera un poblaci�n inicial en el algoritmo gen�tico. Esta es
 	 * generada de manera aleatoria.
 	 */
 	private void generarPoblacionInicial()
 	{
 		for(int i=0;i<ConfiguracionAGR.tamanoPoblacion;i++)
 		{
 			poblacion[i].establecerRuta(
 				inicializacionAleatoria(new VectorRuta(grafo)));
 		}
 	}
 	
 	/**
 	 * M�todo que crea un tour (ciclo hamiltoniano). Los nodos son visitados de
 	 * manera aleatoria. El nodo de inicio es similar en cada tour generado.
 	 *
 	 * @param ruta Grafo con los nodos a visitar.
 	 * 
 	 * @return rutaSolucion Tour que entrega una soluci�n aleatoria.
 	 */
 	private VectorRuta inicializacionAleatoria(VectorRuta ruta)
 	{
 		int rnd;
 		int indice;
 		int adicional;
 		
 		// inicializa la soluci�n aleatoria
 		VectorRuta rutaSolucion = new VectorRuta();
 		ElementoRuta elemento;
 		
 		// adjudica el nodo de partida
 		rutaSolucion.agregar(ruta.obtenerElemento(0));
 		if(ruta.obtenerElemento(0).obtenerTipo() == "Oferta")
 			adicional = 1;
 		else
 			adicional = 0;
 		
 		numeroOferta = numeroOferta - adicional;
 		ruta.remover(0);
 		
 		// rellena con el resto de los nodos
 		indice = 0;
 		while(! ruta.isEmpty())
 		{
 			// selecciona un nodo al azar y lo agrega a la soluci�n
 			rnd = r.nextInt(ruta.size());
 			elemento = ruta.obtenerElemento(rnd);
 			//System.out.println("Eleccion = " + elemento.obtenerDescripcion());
 			if(elemento.obtenerTipo() == "Oferta")
 			{
	 			rutaSolucion.agregar(elemento);
	 			ruta.remover(rnd);
	 			indice++;
 			}
 			else
 			if(indice >= this.numeroOferta)
 			{
	 			rutaSolucion.agregar(elemento);
	 			ruta.remover(rnd);
 			}
 		}
 		numeroOferta = numeroOferta + adicional;
 		
 		return rutaSolucion;
 	}
 	
	/**
	 * M�todo que realiza la evaluaci�n de los individuos de la poblaci�n en la
	 * generaci�n actual.
	 */
 	private void funcionEvaluacion()
 	{
  		int i;
  		peorSolucion = 0;
  		double valor = 0;
  		
  		// establece la distancia recorrida por cada individuo
  		for(i = 0;i < ConfiguracionAGR.tamanoPoblacion;i++)
  		{
  			valor = evaluacionRuta(poblacion[i].obtenerRuta());
  			poblacion[i].establecerValorEvaluacion(valor);
  			
  			if(i == 0)
  				peorSolucion = valor;
  			else
  			{
  				if(valor > peorSolucion)
  					peorSolucion = valor;
  			}
  		}
  		
  		// asigna las penalizaciones como parte de evaluaci�n
  		for(i = 0;i < ConfiguracionAGR.tamanoPoblacion;i++)
  		{
  			valor = poblacion[i].obtenerValorEvaluacion() +
  				evaluarPenalizacion(poblacion[i].obtenerRuta())*peorSolucion;
  			poblacion[i].establecerValorEvaluacion(valor);
  		}
 	}
 	
 	/**
 	 * M�todo que evalua e indica cuantos elementos infactibles est�n presentes
 	 * en el tour realizado por indiviudo. Se deben recorrer todos los puntos de
 	 * oferta primero y despu�s los puntos de demanda con excepci�n del punto de
 	 * partida.
 	 *
 	 * @param ruta El vector de la ruta.
 	 *
 	 * @return penalizacion La penalizaci�n evaluada.
 	 */
 	private int evaluarPenalizacion(VectorRuta ruta)
 	{
 		int i;
 		int oferta;
 		int tamanio;
 		int penalizacion;
 		ElementoRuta elemento;
 		
 		tamanio = ruta.size();
 		oferta = 0;
 		penalizacion = 0;
 		
 		// suma las distancias entre cada nodo
 		for(i = 1;i < tamanio;i++)
 		{
 			elemento = ruta.obtenerElemento(i);
 			if(elemento.obtenerTipo() == "Oferta")
 				oferta ++;
 			else
 			if(oferta < this.numeroOferta)
 				penalizacion ++;
 		}
 		
 		return penalizacion;
 	}
 	
 	/**
	 * M�todo que realiza la selecci�n de los individuos de la poblaci�n actual
	 * que sobreviviran a la generaci�n siguiente. Las t�cnicas de selecci�n
	 * utilzadas son la proporcional y la de torneo probabil�stica ambas con
	 * sustituci�n generacional completa.
	 */
 	private void operadorSeleccion()
 	{
		if(ConfiguracionAGR.tecnicaSeleccion == 0)
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
 		minimoFuncion = poblacion[0].obtenerValorEvaluacion();
 		maximoFuncion = minimoFuncion;
 		
 		for(int i = 0;i < ConfiguracionAGR.tamanoPoblacion; i++)
 		{
 			valor = poblacion[i].obtenerValorEvaluacion();
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
	 *         reemplaza a la poblaci�n anterior.
	 */ 	
 	private IndividuoRuta[] seleccionProporcional()
 	{
 		int i;
 		int j;
 		double valor;
 		double rnd;
 		double[] calificacionAcumulada =
 			new double[ConfiguracionAGR.tamanoPoblacion];
 		
 		IndividuoRuta[] poblacionIntermedia =
 			new IndividuoRuta[ConfiguracionAGR.tamanoPoblacion];
 		IndividuoRuta[] poblacionSeleccionada =
 			new IndividuoRuta[ConfiguracionAGR.tamanoPoblacion];
 		
 		poblacionIntermedia = poblacion;
 		sumatoriaFuncion = 0;
 		
 		// Calcular la nueva evaluaci�n
 		for(i = 0;i < ConfiguracionAGR.tamanoPoblacion;i++)
 		{
 			valor = (maximoFuncion + minimoFuncion) -
 					poblacionIntermedia[i].obtenerValorEvaluacion();
 			poblacionIntermedia[i].establecerValorEvaluacion(valor);
 			sumatoriaFuncion = sumatoriaFuncion + valor;
 			calificacionAcumulada[i] = sumatoriaFuncion;
 		}
 		
 		// Seleccionar individuos
 		for(i = 0;i < ConfiguracionAGR.tamanoPoblacion;i++)
 		{
 			rnd = Math.random() * sumatoriaFuncion;
 			for(j = 0;j < ConfiguracionAGT.tamanoPoblacion;j++)
 			{
 				poblacionSeleccionada[i] = new IndividuoRuta();
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
 	private IndividuoRuta[] seleccionTorneo()
 	{
 		int i;
 		Vector baraja = new Vector();
 		Vector ganadores = new Vector();
 		IndividuoRuta[] poblacionIntermedia =
 			new IndividuoRuta[ConfiguracionAGR.tamanoPoblacion];
 		
 		for(i = 0;i < ConfiguracionAGR.tamanoPoblacion;i++)
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
				ganadores.add(ganadorTorneo((IndividuoRuta) baraja.elementAt(i),
				 						(IndividuoRuta) baraja.elementAt(i+1)));
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
				ganadores.add(ganadorTorneo((IndividuoRuta) baraja.elementAt(i),
				 						(IndividuoRuta) baraja.elementAt(i+1)));
 			}
 			catch(ArrayIndexOutOfBoundsException msg)
 			{
				ganadores.add(ganadorTorneo((IndividuoRuta) baraja.elementAt(i),
										(IndividuoRuta) baraja.elementAt(i)));
 			}
 		}
 		
 		// Nueva poblaci�n intermedia
 		for(i = 0;i < ganadores.size();i++)
 		{
 			poblacionIntermedia[i] = (IndividuoRuta) ganadores.elementAt(i);
 		}
 		
 		return poblacionIntermedia;
 	}
 	
	/**
	 * M�todo que baraja a los individuos dentro de la poblaci�n, es decir cambia
	 * sus ubicaciones en el vector.
	 *
	 * @param b Poblaci�n original de individuos.
	 *
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
 	private IndividuoRuta ganadorTorneo(IndividuoRuta i1, IndividuoRuta i2)
 	{
 		IndividuoRuta ganador = new IndividuoRuta();
 		
 		if(flip(ConfiguracionAGR.PROBABILIDAD_FLIP) == true)
 		{
	 		if(i1.obtenerValorEvaluacion() <= i2.obtenerValorEvaluacion())
	 			ganador = i1;
	 		else
	 			ganador = i2;
	 	}
	 	else
	 	{
	 		if(i1.obtenerValorEvaluacion() <= i2.obtenerValorEvaluacion())
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
	 * denominados hijos, los cuales reemplazan a los padres.
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
 		IndividuoRuta[] hijos = new IndividuoRuta[2];
 		
 		// Realizar mientras la cantidad de seleccionados sea menor a dos
 		do
 		{
 			// Selecci�n de individuos para el cruce
	 		for(i = 0;i < ConfiguracionAGR.tamanoPoblacion;i++)
	 		{
	 			rnd = Math.random();
	 			if(rnd <= ConfiguracionAGR.probabilidadCruce)
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
	 		
	 		hijos[0] = realizarCruce(new IndividuoRuta(poblacion[i1]),
	 								 new IndividuoRuta(poblacion[i2]));
	 		hijos[1] = realizarCruce(new IndividuoRuta(poblacion[i2]),
	 								 new IndividuoRuta(poblacion[i1]));
	 		
	 		// Reemplazar padres por hijos en poblaci�n actual
	 		poblacion[i1] = hijos[0];
	 		poblacion[i2] = hijos[1];
	 	}
 	}
 	
	/**
	 * M�todo que realiza el cruce entre dos individuos denominados padres, los
	 * cuales originan un nuevo descendiente. La t�cnica de corresponde a la
	 * cruza para permutaciones denominada Order Croosover (OX).
	 *
	 * @param padre1 Es el primer individuo padre de la cruza.
	 * @param padre2 Es el segundo individuo padre de la cruza.
	 *
	 * @return hijo Nuevo individuo producto de la cruza de ambos.
	 */
 	private IndividuoRuta realizarCruce(IndividuoRuta padre1,
 										IndividuoRuta padre2)
 	{
 		IndividuoRuta hijo = new IndividuoRuta();
 		VectorRuta ruta = new VectorRuta();
 		ElementoRuta elemento = new ElementoRuta();
 		
 		int largoSubcadena = 0;
 		int inicioSubcadena = 0;
 		int finSubcadena = 0;
 		int numeroNodos = padre1.obtenerRuta().size();
 		int indice, i;
 		
 		if(numeroNodos <= 2)
 			return padre1;
 		
 		do
 		{
 			// n�mero aleatorio [1,n-1]
 			largoSubcadena = r.nextInt(numeroNodos - 1);
 		}
 		while(largoSubcadena == 0);
 		
 		// n�mero aleatorio [1,n - subcadena]
 		inicioSubcadena = r.nextInt(numeroNodos - largoSubcadena) + 1;
 		finSubcadena = inicioSubcadena + largoSubcadena - 1;
 		
 		// elimina los elementos (subcadena) del segundo padre
 		for(i = inicioSubcadena;i <= finSubcadena;i++)
 		{
 			elemento = padre1.obtenerRuta().obtenerElemento(i);
 			indice = padre2.obtenerRuta().buscarElemento(elemento);
 			
 			// anula el valor del nodo en el segundo padre 
 			if(indice != -1)
 				padre2.obtenerRuta().remover(indice);
 		}
 		
 		// crea una nueva ruta con elementos de ambos padres
 		indice = 0;
 		for(i = 0;i < numeroNodos;i++)
 		{
 			if(i >= inicioSubcadena && i <= finSubcadena)
 			{
 				ruta.agregar(padre1.obtenerRuta().obtenerElemento(i));
 			}
 			else
 			{
 				ruta.agregar(padre2.obtenerRuta().obtenerElemento(indice));
 				indice++;
 			}
 		}
 		hijo.establecerRuta(ruta);
 		
 		// devuelve el nuevo individuo generado de ambos padres
 		return hijo;
 	}
 	
	/**
	 * M�todo que aplica el operador mutaci�n a un grupo de individuos bajo
	 * cierta probabilidad entregada como par�metro de entrada.
	 */
 	private void operadorMutacion()
 	{
 		int i;
 		double rnd;
 		
 		for(i = 0;i < ConfiguracionAGR.tamanoPoblacion;i++)
 		{
 			rnd = Math.random();
 			if(rnd <= ConfiguracionAGR.probabilidadMutacion)
 			{
 				poblacion[i] = realizarMutacion(new IndividuoRuta(poblacion[i]));
 			}
 		}
 	}
 	
	/**
	 * M�todo que aplica la mutaci�n a un individuo de la poblaci�n. El operador
	 * corresponde a la t�cnica para permutaciones denominada Mutaci�n por
	 * intercambio rec�proco.
	 *
	 * @param padre Individuo al que se le aplica la mutaci�n.
	 * 
	 * @return hijo Individuo con nuevos valores en la soluci�n.
	 */
 	private IndividuoRuta realizarMutacion(IndividuoRuta padre)
 	{
 		// �ndices o nodos a intercambiar
 		int i1;
 		int i2;
 		int tamanio;
 		
 		// ruta a modificar
 		VectorRuta ruta = padre.obtenerRuta();
 		
 		tamanio = ruta.size();
 		
 		// intercambiar posiciones en forma aleatoria
 		if(tamanio > 1)
 		{
 			i1 = r.nextInt(ruta.size() - 1) + 1;
 			i2 = r.nextInt(ruta.size() - 1) + 1;
	 		
	 		ruta.intercambiar(i1,i2);
	 		padre.establecerRuta(ruta);
 		}
 		
 		// devolver el nuevo individuo
 		return padre;
 	}
 	
 	/**
 	 * M�todo que calcula la distancia total recorrida por el tour soluci�n.
 	 *
 	 * @param ruta Tour recorrido.
 	 * 
 	 * @return evaluacion Distancia total recorrida.
 	 */
 	public double evaluacionRuta(VectorRuta ruta)
 	{
 		int i;
 		int tamanio;
 		int i1;
 		int i2;
 		double evaluacion = 0;
 		
 		tamanio = ruta.size() - 1;
 		
 		// suma las distancias entre cada nodo
 		for(i = 0;i < tamanio;i++)
 		{
 			i1 = ruta.obtenerElemento(i).obtenerIndice();
 			i2 = ruta.obtenerElemento(i+1).obtenerIndice();
 			
 			evaluacion = evaluacion + ConfiguracionAGR.tablaDistancia[i1][i2];
 		}
 		
 		// termina el ciclo
		i1 = ruta.obtenerElemento(tamanio).obtenerIndice();
		i2 = ruta.obtenerElemento(0).obtenerIndice();
		evaluacion = evaluacion + ConfiguracionAGR.tablaDistancia[i1][i2];
		
		return evaluacion;
 	}
 	
	/**
	 * M�todo para encuentra la soluci�n de m�nimo costo en la poblaci�n actual.
	 *
	 * @return optimo Individuo con la mejor soluci�n.
	 */
	public IndividuoRuta encontrarOptimo()
	{
		IndividuoRuta optimo = new IndividuoRuta();
		int i;
		double min;
		
		optimo = poblacion[0];
		min = optimo.obtenerValorEvaluacion();
		
		for(i = 1;i < ConfiguracionAGR.tamanoPoblacion;i++)
		{
			if(min > poblacion[i].obtenerValorEvaluacion())
			{
				optimo = poblacion[i];
				min = optimo.obtenerValorEvaluacion();
			}
		}
		
		return optimo;
	}
	
	/**
	 * M�todo que inicializa una poblaci�n con soluciones factibles aleatorias.
	 */
	public void inicializarAG()
	{
	 	generarPoblacionInicial();
	 	funcionEvaluacion();
	 	asignarLimites();
	}
	
	/**
	 * M�todo para realiza la ejecuci�n del Algoritmo Gen�tico.
	 *
	 * @return evolucionFuncion Vector de costos m�nimos.
	 */
 	public void ejecutarAG()
 	{
 		double indice;
 		for(int i = 0;i < ConfiguracionAGR.numeroGeneraciones;i++)
 		{
			operadorSeleccion();
			operadorCruce();
			operadorMutacion();
			funcionEvaluacion();
			asignarLimites();
			this.frameOperacion.indiceBarraProgreso ++;
			indice = ((double) this.frameOperacion.indiceBarraProgreso /
							   this.frameOperacion.cantidadTotal) * 100;
			this.frameOperacion.panelEscritorio.frameCircanaPro.
				panelEstado.cambiarBarraOperacion((int) indice);
			ProcesoSegundoPlano.dormir();
 		}
 	}
 	
	/**
	 * M�todo para visualizar los valores de los individuos de la poblaci�n.
	 */
 	public void verDatos()
 	{
 		System.out.println("Poblacion Actual");
 		
 		for(int i=0;i<ConfiguracionAGR.tamanoPoblacion;i++)
 		{
 			System.out.println(poblacion[i].obtenerRuta().obtenerRuta()
 				+ " = " +poblacion[i].obtenerValorEvaluacion());
 		}
 		
 		IndividuoRuta optimo = encontrarOptimo();
 		System.out.println("Ruta = " + optimo.obtenerRuta().obtenerRuta() +
 						   "===>" + optimo.obtenerValorEvaluacion());
 	}
 }