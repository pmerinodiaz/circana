/**
 * @(#)RedNeuronalBP.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */
 
import java.util.Random;

/**
 * Clase que implementa una red neuronal <b>Back - Propagation</b>. Una red 
 * supervisada.Esta red neuronal se construye en forma genérica para atacar
 * cualquier tipo problema de interpolación.<br>&nbsp;</br>
 * La red neuronal esta especializada para atacar problemas de tendencias de 
 * curvas. El tipo problema que ataca es una función definida del tipo:
 * <br>&nbsp;</br>
 * f: Rn -> Rm
 * Esta red tiene dos capas ocultas con distinas funciones de transición.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ConfiguracionNeuronalBP
 * @see Random
 * @see Math
 */
/*
 * OBSERVACION:
 *	Siglas tomadas para abreviar métodos y variables:
 *		CO1 = Capa Oculta 1
 *		CO2 = Capa Oculta 2
 *		S  = salidas
 *
 * Se tomó el siguiente estándar para los sub-indices:
 * i:entradas(o dimensiones), q:Capa Oculta 1,  j: Capa Oculta 2,  r:salidas,  
 * k:patrones
 * 
 */
public class RedNeuronalBP 
{
	/**
	 * Valor de los pesos sinápticos de las entradas
	 * (neuronas ocultas 1, entradas).
	 */
	private double[][] wCO1;
	
	/**
	 * Valor de los pesos sinápticos de las capas ocultas
	 * (neuronas ocultas 2, neuronas ocultas 1).
	 */
	private double[][] wCO2;
	
	/**
	 * Valor de los pesos sinápticos de la capas de salida 
	 * (salidas, neuronas ocultas 2).
	 */
	private double[][] wS;
	
	/** Salidas ha aprender. */
	private double[][] salidasAprender;
	
	/** Entradas asociadas a las salidas para aprender. */
	private double[][] entradasAprender;
	
	/** Salidas para verificar en la validación cruzada. */
	private double[][] salidasValidacionCruzada;
	
	/** Entradas para verificar en la validación cruzada. */
	private double[][] entradasValidacionCruzada;
	
	/** La cantidad de ciclos que ejecuta los ajuste en los pesos sinápticos. */
	private double ciclo;
	
	/**
	 * Indica el tipo de función de transición de la capa oculta 1
	 * (0:Lineal, 1:Sigmoidal, 2:TangenteHiperBolica).
	 */
	private int tipoFuncionCO1;
	
	/**
	 * Indica el tipo de función de transición de la capa oculta 2
	 * (0:Lineal, 1:Sigmoidal, 2:TangenteHiperBolica).
	 */
	private int tipoFuncionCO2;
	
	/**
	 * Indica el tipo de función de transición de la capa de salida
	 * (0:Lineal, 1:Sigmoidal, 2:TangenteHiperBolica).
	 */
	private int tipoFuncionS;
	
	/** Valor de las entradas a la red. */
	private double[] x;
	
	/** Valor de las salidas de la capa ocultas 1. */
	private double[] y;
	
	/** Valor de las salidas de la capa ocultas 2. */
	private double[] z;
	
	/** Valor de las salidas. */
	private double[] u;
	
	/** Valor de la velocidad aprendizaje. */
	private double rata;
	
	/** Deja almacenado en forma temporal rata inicial. */
	private double rataTemporal;
	
	/** Variación de rata. */
	private double variacionRata;
	
	/** Error cometido en el test de la red. */
	private double errorTest;
	
	/** Error cometido en el aprendizaje de la red. */
	private double errorAprendizaje;
	
	/** Valor del error actual de la red. */
	private double error;
	
	/** Valor del error mínimo exigible a la red. */
	private double errorMinimo;
	
	/** Valor del error máximo exigible antes que la red se desborde. */
	private double errorMaximo;
	
	/** Número de patrones. */
	private int numeroPatrones;
	
	/** Números de patrones que ocuparan para el aprendizaje. */
	private int numeroPatronesAprendizaje;
	
	/** Números de patrones que ocuparan para el test. */
	private int numeroPatronesTest;
	
	/** Número de entradas. */
	private int numeroEntradas;
	
	/** Número de neuronas de la capa oculta 1. */
	private int numeroNeuronasCO1;
	
	/** Número de neuronas de la capa oculta 2. */
	private int numeroNeuronasCO2;
	
	/** Número de salidas. */
	private int numeroSalidas;
	
	/** Porcentaje de validación cruzada. */
	private double porcentajeCruzada;
	
	/** Indica si está entrenada o no. */
	private boolean entrenada;
	
	/** Indica los factores que regulan los intervalos de entrada. */
	private double[][] entradaFactores;
	
	/** Indica los factores que regulan los intervalos de salidas. */
	private double[][] salidaFactores;
	
	/**
     * Constructor de la clase. Aquí se reserva memoria para los pesos
     * sinápticos, entradas, salidas y los datos para aprender en la red.
     * Tambien se inicializan los valores y se cargan los valores en la
     * configuración.
     */
	public RedNeuronalBP()
	{
		wCO1 = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_NEURONAS_CO1]
					  [ConfiguracionNeuronalBP.NUMERO_MAXIMO_ENTRADAS + 1];
		
		wCO2 = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_NEURONAS_CO2]
						[ConfiguracionNeuronalBP.NUMERO_MAXIMO_NEURONAS_CO1 + 1];
		
		wS = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_SALIDAS]
						[ConfiguracionNeuronalBP.NUMERO_MAXIMO_NEURONAS_CO2 + 1];
		
		entradasAprender = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
							 [ConfiguracionNeuronalRBF.NUMERO_MAXIMO_ENTRADAS];
		
		salidasAprender = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
							[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS];
		
		salidasValidacionCruzada =
					new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_PATRONES]
						[ConfiguracionNeuronalBP.NUMERO_MAXIMO_SALIDAS];
		
		entradasValidacionCruzada =
					new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
						[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS];
		
		entradaFactores = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_ENTRADAS]
									[2];
		
		salidaFactores = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_SALIDAS]
									[2];
		
		x = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_ENTRADAS];
		y = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_NEURONAS_CO1];
		z = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_NEURONAS_CO2];
		u = new double[ConfiguracionNeuronalBP.NUMERO_MAXIMO_SALIDAS];
		
		cargarValores();
		inicializar();
	}
	
	/**
     * Método que encuentra los factores que configuran los intervalos de la
     * red. La red Neuronal necesita que los valores de entrada y salida se
     * encuentren en ciertos intervalos, entonces es necesario transformar los
     * datos de salida y entrada ha intervalos aceptados por la red.
     */
	private void encontrarFactores()
	{
		int k,i,r; //mirar observacion
		double menor,mayor;
		double distanciaIgual = 10;
		
		// encontrando factores de entrada
		for (i = 0; i < numeroEntradas; i++)
		{
			mayor = menor = entradasAprender[0][i];
			
			for( k = 0; k <numeroPatrones; k++)
			{
				if (menor > entradasAprender[k][i])
					menor = entradasAprender[k][i];
				
				if (mayor < entradasAprender[k][i])	
					mayor = entradasAprender[k][i];
			}
			
			entradaFactores[i][0] = menor;
			entradaFactores[i][1] = mayor;
			
			// si los factores son iguales (mayor y menor) se da una distancia
			// opcional por el usuario de programcion (parece parche pero no es)
			// porque no se conoce el verdadero espacio cuando el mayor y el menor
			// son iguales ( hay un solo elemento )
			if (mayor == menor)
			{
				entradaFactores[i][0] = mayor - distanciaIgual;
				entradaFactores[i][1] = mayor + distanciaIgual;
			}
		}
		
		// encontrando factores de salida
		for (r = 0; r < numeroSalidas; r++)
		{
			mayor = menor = salidasAprender[0][r];
			
			for( k = 0; k <numeroPatrones; k++)
			{
				if (menor > salidasAprender[k][r])
					menor = salidasAprender[k][r];
				
				if (mayor < salidasAprender[k][r])
					mayor = salidasAprender[k][r];
			}
			
			salidaFactores[r][0] = menor;
			salidaFactores[r][1] = mayor;
			
			// si los factores son iguales (mayor y menor) se da una distancia
			// opcional por el usuario de programcion (parece parche pero no es)
			// porque no se conoce el verdadero espacio cuando el mayor y el menor
			// son iguales ( hay un solo elemento )
			if (mayor == menor)
			{
				salidaFactores[i][0] = mayor - distanciaIgual;
				salidaFactores[i][1] = mayor + distanciaIgual;
			}
		}
	}
	
	/**
     * Método que ajusta los valores que la red necesita aprender. Los ajusta
     * a los intervalos deseados por la red o intervalos que representan la
     * realidad.
     * 
     * @param tipoAjuste Elección del tipo de transformación (0:red ,1:exterior).
     */
	private void ajustarDatosAprendizaje(int tipoAjuste)
	{
		int k;
		for (k = 0; k < numeroPatrones; k++)
		{
			ajustarEntrada(entradasAprender[k],tipoAjuste);
			ajustarSalida(salidasAprender[k],tipoAjuste);
		}
	}
	
	/**
     * Método que divide en dos grupos los datos de aprendizaje. Un grupo es
     * el que se ocupa para el aprendizaje y el siguiente grupo se ocupa para 
     * el test general de la validación del sistema. La utilización del método
     * es la validación cruzada. La validación cruzada sirve de cierta manera
     * (no es la cura) para el problema del sobre-entrenamiento y el 
     * sobre-ajuste.
     */
     private void dividirDatosAprendizaje()
     {
     	int i, k, r, contador = 0;
		
		desordenarPatrones(entradasAprender,salidasAprender,numeroPatrones);
		
		for (k = 0; k < numeroPatrones*porcentajeCruzada; k++)
     	{
     		for(i = 0; i < numeroEntradas; i++)
			   	entradasValidacionCruzada[k][i] = entradasAprender[k][i];
			
			for(r = 0; r < numeroSalidas; r++)
     			salidasValidacionCruzada[k][r] = salidasAprender[k][r];
     	}
     	
     	for (k = (int) (numeroPatrones*porcentajeCruzada); k < numeroPatrones; k++)
     	{
     		for (i = 0; i < numeroEntradas; i++)
     			entradasAprender[contador][i] = entradasAprender[k][i];
     		
     		for (r = 0; r < numeroSalidas; r++)
     			salidasAprender[contador][r] = salidasAprender[k][r];
     		
     		contador++;
     	}
     	
     	numeroPatronesAprendizaje = (int)(0.5 + numeroPatrones*
     										(1 - porcentajeCruzada));
		
		numeroPatronesTest = (int) (numeroPatrones*porcentajeCruzada);
     }
	
	/**
     * Método que ajusta la velocidad del aprendizaje supervisada. Se ajusta
     * según el porcentaje de error, cual se calcula con el error cometido en el
     * aprendizaje anterior.
     *
     * @param errorTemporalAprendizaje Error cometido en la actualidad del 
     *								   aprendizaje.
     */
	private void ajustarRata(double errorTemporalAprendizaje)
	{
		double porcentaje; 
		
		if (errorAprendizaje != ConfiguracionNeuronalBP.ERROR_NO_INICIADO)
			porcentaje = (errorTemporalAprendizaje - errorAprendizaje)
										/ errorAprendizaje*100.0;
      	else
      		porcentaje = 100;
      	
		if (porcentaje > 0 && porcentaje < 2.0)
    		rata = rataTemporal;
       	
      	if (porcentaje >= 2.0)
      		rata /= variacionRata;
		
      	if (porcentaje < 0.0)
       		rata *= variacionRata;
    }
    
 	/**
	 * Método que retorna la suma ponderada de la neurona q de la capa oculta 1. 
	 * Este método entrega la entrada como parámetro a la función de umbral de
	 * la neurona q de la capa oculta 1.
	 *
	 * @param q Indice de la neurona q de la capa oculta 1.
	 *
	 * @return sum Suma ponderda de la neurona q de la capa oculta 1.
	 */
 	private double obtenerSumaPonderadaCO1(int q)
  	{
   		int i;
   		double sum = 0.0;
   		
   		for (i = 0;i < numeroEntradas; i++)
   			sum += x[i]*wCO1[q][i];
   		
   		sum += wCO1[q][numeroEntradas];
		
   		return sum;
   	}
	
  	/**
	 * Método que retorna la suma ponderada de la neurona j de la capa oculta 2.
	 * Este método entrega la entrada como parámetro a la función de umbral de
	 * la neurona j de la capa oculta 2.
	 *
	 * @param j Indice de la neurona j de la capa oculta 2.
	 *
	 * @return sum Suma ponderda de la neurona j de la capa oculta 2.
	 */
 	private double obtenerSumaPonderadaCO2(int j)
  	{
   		int q;
   		double sum = 0.0;
   		
   		for (q = 0;q < numeroNeuronasCO1; q++)
   			sum += y[q]*wCO2[j][q];
   		
   		sum += wCO2[j][numeroNeuronasCO1];
		
   		return sum;
   	}
   	
   	/**
	 * Método que retorna la suma ponderada de la neurona r de la capa de la 
	 * salida. Este método entrega la entrada como parámetro a la función
	 * de umbral de la neurona r de la salida.
	 *
	 * @param r Indice de la neurona r de la capa de salida.
	 *
	 * @return sum Suma ponderda de la neurona r de la capa de salida.
	 */
 	private double obtenerSumaPonderadaSalida(int r)
  	{
   		int j;
   		double sum = 0.0;
   		
   		for (j = 0;j < numeroNeuronasCO2; j++)
   			sum += z[j]*wS[r][j];
   		
   		sum += wS[r][numeroNeuronasCO2];
		
	    return sum;
   	}
	
	/**
     * Método que calcula el valor de la función umbral de la capa oculta 1. El
     * valor de la función depende la "función de transicion" que se haya
     * elegido.
     *	
     * @param x Parámetro de la función.
     *
     * @return f Valor de la función.
     */
 	private double obtenerFuncionUmbralCO1(double x)
  	{
  		double f;
  		
  		switch (tipoFuncionCO1)
  			{
		  		case 0: f = x;
		  				break;
		  		
		  		case 1: f = 1.0/(1 + Math.exp(-x));
		  				break;
		  		
		  		case 2: f = (Math.exp(x) - Math.exp(-x))/
		  					(Math.exp(x) + Math.exp(-x));
		  				break;
		  		
		  		default: f = x;
		  				break;
		  }
   		
  		return f;
  	}
	
	/**
     * Método que calcula el valor de la función umbral de la capa oculta 2. El
     * valor de la función depende la "función de transición" que se haya
     * elegido.
     *	
     * @param x Parámetro de la función.
     *
     * @return f valor de la función.
     */
	private double obtenerFuncionUmbralCO2(double x)
  	{
  		double f;
  		
  		switch (tipoFuncionCO2)
  			{
		  		case 0: f = x;
		  				break;
		  		
		  		case 1: f = 1.0/(1 + Math.exp(-x));
		  				break;
		  		
		  		case 2: f = (Math.exp(x) - Math.exp(-x))/
		  					(Math.exp(x) + Math.exp(-x));
		  				break;
		  		
		  		default: f = x;
		  				break;
		  }
   		
  		return f;
  	}
  	
  	/**
     * Método que calcula el valor de la función umbral de la capa de salida. El
     * valor de la función depende la "función de transición" que se haya
     * elegido.
     *	
     * @param x Parámetro de la función.
     *
     * @return f Valor de la función.
     */
	private double obtenerFuncionUmbralSalida(double x)
  	{
  		double f;
  		
  		switch (tipoFuncionS) 
  			{
		  		case 0: f = x;
		  				break;
		  		
		  		case 1: f = 1.0/(1 + Math.exp(-x));
		  				break;
		  		
		  		case 2: f = (Math.exp(x) - Math.exp(-x))/
		  					(Math.exp(x) + Math.exp(-x));
		  				break;
		  		
		  		default: f = x;
		  				break;
		  }
   		
  		return f;
  	}
  	
  	/**
	 * Método que obtiene el valor de la derivada de la función de transición 
	 * de la capa oculta 1. El parámetro ingresado es el valor de la función de
	 * capa oculta 1 (No su parámetro X!!!). El valor de la derivada depende del
	 * tipo de función elegida.
	 *
	 * @param y Valor de la funcion de la capa oculta 1.
	 *
	 * @return df Valor de la derivada de la funcion de la capa oculta 1.
	 */
  	private double obtenerDerivadaFuncionUmbralCO1(double y)
  	{
  		double df;
  		
  		switch (tipoFuncionCO1) 
  			{
		  		case 0: df = 1;
		  				break;
		  		
		  		case 1: df = y*(1 - y);
		  				break;
		  		
		  		case 2: df = 1 - Math.pow(y ,2.0);
		  				break;
		  		
		  		default: df = 1;
		  				break;
		  }
   		
  		return df;
  	}
  	
  	/**
	 * Método que obtiene el valor de la derivada de la función de transición 
	 * de la capa oculta 2. El parámetro ingresado es el valor de la función de
	 * capa oculta 2 (No su parámetro X!!!). El valor de la derivada depende del
	 * tipo de función elegida.
	 *
	 * @param z Valor de la función de la capa oculta 2.
	 *
	 * @return df Valor de la derivada de la función de la capa oculta 2.
	 */
	private double obtenerDerivadaFuncionUmbralCO2(double z)
  	{
  		double df;
  		
  		switch (tipoFuncionCO2)
  			{
		  		case 0: df = 1;
		  				break;
		  		
		  		case 1: df = z*(1 - z);
		  				break;
		  		
		  		case 2: df = 1 - Math.pow(z ,2.0);
		  				break;
		  		
		  		default: df = 1;
		  				break;
		  }
   		
  		return df;
  	}
  	
  	/**
	 * Método que obtiene el valor de la derivada de la función de transición de
	 * la capa de salida. El parámetro ingresado es el valor de la función de
	 * capa de salida (No su parámetro X!!!). El valor de la derivada depende
	 * del tipo de función elegida.
	 *
	 * @param u Valor de la función de la capa de salida.
	 *
	 * @return df Valor de la derivada de la función de la capa de salida.
	 */
  	private double obtenerDerivadaFuncionUmbralSalida(double u)
  	{
  		double df;
  		
  		switch (tipoFuncionS) 
  			{
		  		case 0: df = 1;
		  				break;
		  		
		  		case 1: df = u*(1 - u);
		  				break;
		  		
		  		case 2: df = 1 - Math.pow(u ,2.0);
		  				break;
		  		
		  		default: df = 1;
		  				break;
		  }
   		
  		return df;
  	}
  	
  	/**
     * Método que almacena una entrada en la red.
     * 
     * @param x Vector que contiene una entrada a la red.
     */
	private void almacenarEntrada(double[] x)
	{
 		int i; // mirar observación
 		
 		for (i = 0; i<numeroEntradas; i++)
			this.x[i] = x[i];
	}
	
	/**
     * Método que genera y almacena la salida de neurona q de la capa oculta 1.
     * Generalmente se ocupa para almacenar la salida, porque se utiliza en una
     * posterior tarea.
     *  
     * @param q Indice de la neurona q de la capa oculta 1.
     *  	
     * @return y[q] Valor de la salida de la neurona q de la capa oculta 1.
     */
	private double obtenerSalidaCO1(int q)
  	{
    	y[q] = obtenerFuncionUmbralCO1(obtenerSumaPonderadaCO1(q));
    	return y[q];
  	}
	
 	/**
     * Método que genera y almacena la salida de neurona j de la capa oculta 2
     * Generalmente se ocupa para almacenar la salida, porque se utiliza en una
     * posterior tarea.
     * 
     * @param j Indice de la neurona j de la capa oculta 2.
     * 
     * @return z[j] Valor de la salida de la neurona j de la capa oculta 2.
     */
	private double obtenerSalidaCO2(int j)
  	{
    	z[j] = obtenerFuncionUmbralCO2(obtenerSumaPonderadaCO2(j));
    	return z[j];
  	}
  	
  	/**
     * Método que genera y almacena la salida de neurona r de la capa de salida.
     * Generalmente se ocupa para almacenar la salida, porque se utiliza en una
     * posterior tarea.
     * 
     * @param r Indice de la neurona r de la capa de salida.
     * 
     * @return u[r] valor de la salida de la neurona r de la capa de salida.
     */
	private double obtenerSalida(int r)
  	{
  		u[r] = obtenerFuncionUmbralSalida(obtenerSumaPonderadaSalida(r));
    	return u[r];
	}
	
	/**
     * Método que desordena los patrones ingresados a la red neuronal.
     *
     * @param entradas Matriz que contiene las entradas a desordenar.
     * @param salidas Matriz que contiene las salidas a desordenar.
     * @param tamano  Cantidad de patrones a desordenar.
     */
	private void desordenarPatrones(double[][] entradas, double[][] salidas,
									int tamano)
	{
  		int k,k2,k3;
  		int i,r;
  		
  		Random azar = new Random();
 		int[][] azarPatrones = new int[tamano][2];
 		double[] entradaAux = new double[numeroEntradas];
 		double[] salidaAux = new double[numeroSalidas];
 		int[] cambioAzarPatrones =  new int[2];
 		
  		for (k = 0;k < tamano; k++)
   		{
    		azarPatrones[k][0] = azar.nextInt(1000);
    		azarPatrones[k][1] = k;
    	}
      	
      	for (k = 0; k < tamano; k++)
    		for (k2 = k + 1; k2 < tamano; k2++)
     			if (azarPatrones[k][0] > azarPatrones[k2][0])
      			{
       				// pasando aux
       				for (r = 0;r < numeroSalidas; r++)
      					salidaAux[r] = salidas[azarPatrones[k2][1]][r];
      				
      				// pasando aux
       				for (i = 0;i < numeroEntradas; i++)
	 					entradaAux[i] = entradas[azarPatrones[k2][1]][i];
	 				
      				for (r = 0;r < numeroSalidas; r++)
      					salidaAux[r] = salidas[azarPatrones[k2][1]][r];
      				
      				// pasando k2
      				for (i = 0;i < numeroEntradas; i++)
	 					entradas[azarPatrones[k2][1]][i] =
	 					entradas[azarPatrones[k][1]][i];
	 				
      				for (r = 0;r < numeroSalidas; r++)
      					salidas[azarPatrones[k2][1]][r] =
      					salidas[azarPatrones[k][1]][r];
      				
      				// pasando k
      				for (i = 0;i < numeroEntradas; i++)
	 					entradas[azarPatrones[k][1]][i] = entradaAux[i];
	 				
      				for (r = 0;r < numeroSalidas; r++)
      					salidas[azarPatrones[k][1]][r] = salidaAux[r];
      			}
    }
	
	/**
	 * Método que almacena los datos que necesita aprender la red.
	 * 
	 * @param entradasAprender Entradas que necesita tomar la red para aprender.
	 * @param salidasAprender Salidas que necesita tomar la red para aprender.
	 */
 	public void almacenarDatosAprendizaje(double[][] entradasAprender,
 										  double[][] salidasAprender,
 										  int numeroPatrones)
	{
 		int k,i,r;
 		
 		this.numeroPatrones = numeroPatrones;
 		
 		for (k = 0; k < numeroPatrones; k++)
		{
			for(i = 0; i < numeroEntradas; i++)
				this.entradasAprender[k][i] = entradasAprender[k][i];
			
			for(r = 0; r < numeroSalidas; r++)
				this.salidasAprender[k][r] = salidasAprender[k][r];
		}
		
		encontrarFactores();
		ajustarDatosAprendizaje(0);
		
		dividirDatosAprendizaje();
	}
	
	/**
     * Método donde se inicializan los valores para la red. En este método se
     * borra todo el aprendizaje que haya podido lograr la red.
     */
	public void inicializar()
	{
		int i,j,r,q; // mirar observación
		Random azar = new Random();
		entrenada = false;
		errorAprendizaje = errorTest = error = -1; // no inicializado
		
		rata = rataTemporal;
		
		for (q = 0;q < numeroNeuronasCO1; q++)
			for (i = 0;i <= numeroEntradas; i++)
				wCO1[q][i] = azar.nextInt(20)/10.0-1.0;
		
		for (j = 0;	j < numeroNeuronasCO2; j++)
 			for (q = 0;q <= numeroNeuronasCO1; q++)
 				wCO2[j][q] = azar.nextInt(20)/10.0-1.0;
 		
 		for (r = 0;	r < numeroSalidas; r++)
 			for (j = 0;j <= numeroNeuronasCO2; j++)
 				wS[r][j] = azar.nextInt(20)/10.0-1.0;
 		
 		for (i = 0; i < numeroEntradas; i++)
 			x[i] = 0;
 		
 		for (q = 0; q < numeroNeuronasCO1; q++)
 			y[q] = 0;
 		
 		for (j = 0; j < numeroNeuronasCO2; j++)
 			z[j] = 0;
 		
 		for (r = 0; r < numeroSalidas; r++)
 			u[r] = 0;
 	}
 	
	/**
     * Método que carga los valores de configuración para la red neuronal.
     */
	public void cargarValores()
 	{
 		double[] parametros = ConfiguracionNeuronalBP.entregarValores();
 		
 		numeroPatrones = (int) parametros[5];
 		numeroEntradas = (int) parametros[6];
 		numeroNeuronasCO1 = (int) parametros[7];
 		numeroNeuronasCO2 = (int) parametros[8];
 		numeroSalidas = (int) parametros[9];
 		ciclo = parametros[13];
 		errorMinimo = parametros[15];
 		errorMaximo = parametros[16];
 		rata = rataTemporal = parametros[17];
 		variacionRata = parametros[18];
 		tipoFuncionCO1 = (int)  parametros[10];
 		tipoFuncionCO2 = (int) parametros[11];
 		tipoFuncionS = (int) parametros[12];
 		porcentajeCruzada = parametros[14];
 	}
 	
  	/**
     * Método que almacena y genera las salidas externas de la red neuronal. 
     * Este método ejecuta la red por completo dado una entrada.
     * Frecuentemente se utiliza el método para obtener su salida.
     * 
     * @param x Vector con la entrada a ejecutar.
     * 
     * @return u Vector con las salidas correspondiente a la entrada.
     */
	public double [] ejecutarRed(double[] x)
   	{
   		almacenarEntrada(x);
   		int q,j,r;
   		
   		for (q = 0;q < numeroNeuronasCO1; q++)
   			obtenerSalidaCO1(q);
		
   		for (j = 0;j < numeroNeuronasCO2; j++)
   			obtenerSalidaCO2(j);
   		
   		for (r = 0;r < numeroSalidas; r++)
   			obtenerSalida(r);
   		
   		return u;
  	}
  	
  	/**
     * Método que entrega y almacena el error general de la red.
     * 
     * @return error Error general de la red.
     */
	public void calcularErrores()
	{
		int k,r;
		double errorTemporalAprendizaje = 0, sumaDiferencias;
		
		// calculando error en la red en el aprendizaje
		sumaDiferencias = 0;
		for (k = 0; k < numeroPatronesAprendizaje; k++)
     	{
     		ejecutarRed(entradasAprender[k]);
     		for (r = 0; r < numeroSalidas; r++)
     			sumaDiferencias += 1.0 / 2.0 *
     					Math.pow(salidasAprender[k][r] - u[r] ,2.0);
     		
	 		errorTemporalAprendizaje = sumaDiferencias;
	 	}
	 	
	 	// calculando error de la red en el test
	 	sumaDiferencias = 0;
	 	for (k = 0; k < numeroPatronesTest; k++)
	 	{
	 		ejecutarRed(entradasValidacionCruzada[k]);
	 		for (r = 0; r < numeroSalidas; r++)
     			sumaDiferencias += 1.0 / 2.0 *
     					Math.pow(salidasValidacionCruzada[k][r] - u[r] ,2.0);
     		
	 		errorTest = sumaDiferencias;
	 	}
	 	
	 	ajustarRata(errorTemporalAprendizaje);
     	
       	errorAprendizaje = errorTemporalAprendizaje;
       	
       	if (errorTest != ConfiguracionNeuronalBP.ERROR_NO_INICIADO)
     	   	error = errorAprendizaje + errorTest;
       	else
       		error = errorAprendizaje;
	}
  	
	/**
     * Método que ajusta los pesos sinápticos de las 3 capas de neuronas
     * que hay (capa de salida, oculta 2, oculta 1). Es un ajuste por un ciclo
     * de tiempo. El algoritmo de aprendizaje es el conocido Back - Propagation
     * para aprendizaje supervisado.
     */
	public void ajustarPesosSinapticos()
	{
		int k;
		int i,q,j,r;
		double sum,sum1,dfs,dfco2,dfco1;
		double diferencia;
		
		//desordenarPatrones();
		
  	 	for (k = 0;k < numeroPatronesAprendizaje; k++)
 		{
			ejecutarRed(entradasAprender[k]);
			
			// ajustando pesos de salida
			for (r = 0;r < numeroSalidas; r++)
			{
				diferencia = (salidasAprender[k][r] - u[r]);
				dfs = obtenerDerivadaFuncionUmbralSalida(u[r]);
				for (j = 0;j < numeroNeuronasCO2; j++)
					wS[r][j] +=  rata*dfs*diferencia*z[j];
				
				wS[r][numeroNeuronasCO2] += rata*dfs*diferencia;
			}
			
			// ajustando pesos de neuronas ocultas
			for (j = 0; j < numeroNeuronasCO2; j++)
			{
				sum = 0;
				for (r = 0; r < numeroSalidas; r++)
				{
					diferencia = (salidasAprender[k][r] - u[r]);
					dfs = obtenerDerivadaFuncionUmbralSalida(u[r]);
					sum += diferencia*dfs*wS[r][j];
				}
				
				dfco2 = obtenerDerivadaFuncionUmbralCO2(z[j]);
				
				for (q = 0;q < numeroNeuronasCO1; q++)
					wCO2[j][q] += rata*sum*dfco2*y[q];
				
				wCO2[j][numeroNeuronasCO1] += rata*sum*dfco2;
			}
			
			// ajustando pesos de neuronas ocultas 1
			for (q = 0; q < numeroNeuronasCO1; q++)
			{
				sum = 0;
				for (r = 0; r < numeroSalidas; r++)
				{
					diferencia = (salidasAprender[k][r] - u[r]);
					dfs = obtenerDerivadaFuncionUmbralSalida(u[r]);
					sum += diferencia*dfs*wS[r][j];
					
					sum1 = 0;
					for (j = 0; j < numeroNeuronasCO2; j++)
					{
						dfco2 = obtenerDerivadaFuncionUmbralCO2(z[j]);
						sum1 += wCO2[r][j]*dfco2*wCO1[j][q];
					}
					
					sum = sum * sum1;
				}
				
				dfco1 = obtenerDerivadaFuncionUmbralCO1(y[q]);
				
				for (i = 0;i < numeroEntradas; i++)
					wCO1[q][i] += rata*sum*dfco1*x[i];
				
				wCO1[q][numeroEntradas] += rata*sum*dfco1;
			}
		}
	}
	
  	/**
     * Método que ajusta una entrada ha formato de la red o al formato del 
     * exterior. Para elegir el tipo de intervalo o espacio se ocupa la variable
     * tipoAjuste.
     *
     * @param x Vector que contiene una entrada a la red.
     * @param tipoAjuste Eleccion del tipo de transformacion (0:red ,1:exterior).
     * 
     * @return x Vector con los ajustado, segun el tipo de juste elegido.
     */
	public double[] ajustarEntrada(double[] x, int tipoAjuste)
	{
		int i;
		double intervaloMayor, intervaloMenor;
		
		intervaloMenor = ConfiguracionNeuronalBP.INTERVALO_MENOR;
		intervaloMayor = ConfiguracionNeuronalBP.INTERVALO_MAYOR;
		
		// ajustando ha intervalos de la red
		if (tipoAjuste ==  0)
		{
			// funcion : x' = (FM - Fm)/(M - m) *(x - m) + Fm
			for (i = 0; i < numeroEntradas; i++)
			{
				x[i] = (intervaloMayor - intervaloMenor) /
						(entradaFactores[i][1] - entradaFactores[i][0]) *
						(x[i] - entradaFactores[i][0]) + intervaloMenor;
			}
		}
		
		// ajustando ha intervalos de reales
		if (tipoAjuste == 1)
		{
			// funcion : x = m + (x' - Fm) * (M - m) / (FM - Fm)
			for (i = 0; i < numeroEntradas; i++)
			{
				x[i] = entradaFactores[i][0] + (x[i] - intervaloMenor) *
						(entradaFactores[i][1] - entradaFactores[i][0]) /
						(intervaloMayor - intervaloMenor);
			}
		}
		
		return x;
	}
	
	/**
     * Método que ajusta una salida ha formato de la red o al formato del 
     * exterior. Para elegir el tipo de intervalo o espacio se ocupa la variable
     * tipoAjuste.
     *
     * @param u Vector que contiene una entrada a la red.
     * @param tipoAjuste Eleccion del tipo de transformacion (0:red ,1:exterior). 
     * 
     * @return u Vector con los ajustado, segun el tipo de juste elegido.
     **/
	public double[] ajustarSalida(double[] u, int tipoAjuste)
	{
		int r;
		double intervaloMayor, intervaloMenor;
		
		intervaloMenor = ConfiguracionNeuronalBP.INTERVALO_MENOR;
		intervaloMayor = ConfiguracionNeuronalBP.INTERVALO_MAYOR;
		
		// ajustando ha intervalos de la red
		if (tipoAjuste ==  0)
		{
			// funcion : z' = (FM - Fm)/(M - m) *(z - m) + Fm
			for (r = 0; r < numeroEntradas; r++)
			{
				u[r] = (intervaloMayor - intervaloMenor) /
						(salidaFactores[r][1] - salidaFactores[r][0]) *
						(u[r] - salidaFactores[r][0]) + intervaloMenor;
			}
		}
		
		// ajustando ha intervalos de reales
		if (tipoAjuste == 1)
		{
			// funcion : x = m + (x' - Fm) * (M - m) / (FM - Fm)
			for (r = 0; r < numeroEntradas; r++)
			{
				u[r] = salidaFactores[r][0] + (u[r] - intervaloMenor) *
						(salidaFactores[r][1] - salidaFactores[r][0]) /
						(intervaloMayor - intervaloMenor);
			}
		}
		
		return u;
	}
	
	/**
     * Método que retorna verdadero si la red esta entrenada.
     *
     * @return entrenda Indica si la red esta entrenada.
     */
	public boolean estaEntrenada()
	{
		return entrenada;
	}
	
	/**
     * Método que cambia el estado de la red neuronal.
     *
     * @param entrenada Indica si la red esta entrenada.
     */
	public void cambiarEstadoEntrenada(boolean entrenada)
	{
		this.entrenada = entrenada;
	}
	
    /**
	 * Método que retorna si la red está sobre el error máximo exigido.
	 * 
	 * @return sobre Boleano que indica si la red está sobre pasada o no en su
	 *				  error máximo.
	 */
	public boolean estaSobreErrorMaximo(double ciclo)
	{
		boolean sobre;
		
		if (error != -1 &&
			error >= Math.pow(10,errorMaximo) &&
			ciclo/this.ciclo > 0.05)
			sobre = true;
		else
			sobre = false;
		
		return sobre;
	}
	
	/**
	 * Método que retorna si la red está bajo en el error mínimo exigido.
	 * 
	 * @return bajo Boleano que indica si la red está bajo o no en su error
	 *              mínimo. 
	 */
	public boolean estaBajoErrorMinimo(double ciclo)
	{
		boolean bajo;
		
		if (error != -1 &&
			error <= 1.0/Math.pow(10,errorMinimo) &&
			ciclo/this.ciclo > 0.05)
			bajo = true;
		else
			bajo = false;
		
		return bajo;
	}
	
	/**
	 * Método que cambia el número de patrones que establece la red.
	 * 
	 * @param numerosPatrones Número de patrones que la red quiere aprender.
	 */
	public void cambiarNumerosPatrones(int numerosPatrones)
	{
		this.numeroPatrones = numerosPatrones;
	}
	
	/**
     * Método que retorna los valores de los pesos sinápticos de la capa oculta
     * 1 de la red.
     *
     * @return wCO1 Matriz que contiene los valores de los pesos sinápticos de
     *				la capa oculta 1.
     */
	public double[][] obtenerPesosSinapticosOculta1()
	{
		return wCO1;
	}
 	
 	/**
     * Método que retorna los valores de los pesos sinápticos de la capa oculta
     * 2 de la red.
     *
     * @return wCO2 Matriz que contiene los valores de los pesos sinápticos de
     *				la capa oculta 2.
     */
	public double[][] obtenerPesosSinapticosOculta2()
	{
		return wCO2;
	}
	
	/**
     * Método que retorna los valores de los pesos sinápticos de la capa de
     * salida.
     *
     * @return wS Matriz que contiene los valores de los pesos sinápticos de la
     *			  capa de salida.
     */
	public double[][]  obtenerPesosSinapticosSalida()
	{
		return wS;
	}
	
	/**
     * Método que retorna la cantidad de ciclo que da en el aprendizaje.
     *
     * @return ciclo Cantidad de ciclo que debe dar la red en el aprendizaje.
     */
	public double obtenerCiclo()
	{
		return ciclo;
	}
	
	/**
     * Método que retorna la rata de aprendizaje.
     *
     * @return rata Valor de la rata de aprendizaje en ese momento.
     */
	public double obtenerRata()
	{
		return rata;
	}
	
	/**
     * Método que retorna el error máximo que la red acepta para que el
     * aprendizaje pare insatisfactoriamente.
     *
     * @return errorMaximo Error máximo para que la se detenga en forma
     *					   insatisfactorio. 
     */
	public double obtenerErrorMaximo()
	{
		return errorMaximo;
	}
	
	/**
     * Método que retorna el error mínimo de la red para que el aprendizaje
     * pare satisfactoriamente.
     *
     * @return errorMinimo Error mínimo para que la se detenga en forma
     *					   satisfactoria.
     */
	public double obtenerErrorMinimo()
	{
		return errorMinimo;
	}
	
	/**
     * Método que retorna el error cometido de los patrones dejados para testear
     * la red neuronal.
     *
     * @return errorTest Error cometido con los datos indicados para el test.
     */
	public double obtenerErrorTest()
	{
		return errorTest;
	}
	
	/**
     * Método que retorna el error cometido de los patrones dejados para
     * aprender la red neuronal.
     *
     * @return errorAprendizaje Error cometido con los datos indicados para los
     *                          datos del aprendizaje.
     */
	public double obtenerErrorAprendizaje()
	{
		return errorAprendizaje;
	}
	
	/**
     * Método que retorna el error total cometido en la red neuronal.
     *
     * @return error Error total cometido en la red neuronal.
     */
	public double obtenerError()
	{
		return error;
	}
	
	/**
     * Método que retorna el número de entradas (dimensión) en la red.
     *
     * @return numeroEntradas El número de entradas (dimensión) en la red.
     */
	public int obtenerNumeroEntradas()
	{
		return numeroEntradas;
	}
	
	/**
     * Método que retorna el número de neuronas de la capa oculta 1.
     *
     * @return numeroNeuronasCO1 El número de neuronas de la capa oculta 1.
     */
	public int obtenerNumeroNeuronasOculta1()
	{
		return numeroNeuronasCO1;
	}
	
	/**
     * Método que retorna el número de neuronas de la capa oculta 2.
     *
     * @return numeroNeuronasCO2 El número de neuronas de la capa oculta 2.
     */
	public int obtenerNumeroNeuronasOculta2()
	{
		return numeroNeuronasCO2;
	}
	
	/**
     * Método que retorna el número de salidas en la red.
     *
     * @return numeroEntradas El número de salidas en la red.
     */
	public int obtenerNumeroSalidas()
	{
		return numeroSalidas;
	}
	
	/**
     * Método que retorna las el número de patrones ha aprender en la red.
     *
     * @return numeroEntradas El número de patrones ha aprender en la red.
     */
	public int obtenerNumeroPatrones()
	{
		return numeroPatrones;
	}
	
	/**
     * Método que retorna el número de patrones deseables que la red necesita
     * para lograr el error mínimo requerido.
     * 
     * @return numeroPatronesDeseable El número de patrones deseables que la red necesita
     * 								  para lograr el error mínimo requerido.
     */
    public int obtenerNumeroPatronesDeseables()
	{
		int numeroPatronesDeseable, numeroPesos;
		
		numeroPesos = (numeroEntradas * numeroNeuronasCO1) +
						(numeroNeuronasCO1 * numeroNeuronasCO2) +
						(numeroNeuronasCO2 * numeroSalidas) +
						(numeroNeuronasCO1 + numeroNeuronasCO2 + numeroSalidas);
		
		numeroPatronesDeseable = (int) (numeroPesos * Math.pow(10 , 
														errorMinimo - 1) );
		
		//System.out.println("Cuantos :" + numeroPesos + " , "+
		//				   numeroPatronesDeseable);
		
		return numeroPatronesDeseable;
	}
	
	/**
     * Método que devuelve la confiabilidad del aprendizaje que ejecuto la red
     * neuronal. La confiablidad se mide en un factor que se encuentra entre
     * [0,1]. Si la red neuronal no esta entrenada devuelve 0.
     * 
     * @return confiablidad Indica la confiablidad que tiene la red en el
     *						aprendizaje.
     */
	public double obtenerConfiabilidadAprendizaje()
	{
		double confiabilidad = 0.0;
		double confiabilidadNumeroPatrones, confiabilidadErrorAprendizaje;
		
		if (entrenada)
		{
			confiabilidadNumeroPatrones = 1.0 * numeroPatrones /
										  obtenerNumeroPatronesDeseables();
			
			confiabilidadErrorAprendizaje = (1 - Servicio.obtenerRazon(error, 
											1.0 / Math.pow(10,errorMinimo),
											Math.pow(10,errorMaximo)));
			
			confiabilidad = 0.5 * confiabilidadNumeroPatrones +
							0.5 * confiabilidadErrorAprendizaje;
			
			//System.out.println ("PASO 1=" + confiabilidadNumeroPatrones);
			//System.out.println ("PASO 2=" + confiabilidadErrorAprendizaje);
		}
		
		return confiabilidad;
	}
	
	/**
     * Método que se re-define de la clase Object y que sirve para mostrar la
     * información de la red neruonal. La información de la red nueronal está
     * dividida en 2 partes, una que es la configuración de la red y otra que
     * es la información actual de red nueronal.
     * 
     * @return informacion Información detallada sobre la red neuronal.
     */
	public String toString()
	{
		String informacion = "\n";
		String textoEntrenada = "", textoTipoFuncionCO1 = "",
				textoTipoFuncionCO2 = "", textoTipoFuncionS = "";
		
		switch (tipoFuncionCO1)
		{
	    	case 0: textoTipoFuncionCO1 = "Lineal";
	    			break;
	    	
	    	case 1: textoTipoFuncionCO1 = "Signmoidal";
	    			break;
	    	
	    	case 2: textoTipoFuncionCO1 = "HyperBolica";
	    			break;
	    }
		
		switch (tipoFuncionCO2)
		{
	    	case 0: textoTipoFuncionCO2 = "Lineal";
	    			break;
	    	
	    	case 1: textoTipoFuncionCO2 = "Signmoidal";
	    			break;
	    	
	    	case 2: textoTipoFuncionCO2 = "HyperBolica";
	    			break;
	    }
	    
		switch (tipoFuncionS)
		{
	    	case 0: textoTipoFuncionS = "Lineal";
	    			break;
	    	
	    	case 1: textoTipoFuncionS = "Signmoidal";
	    			break;
	    	
	    	case 2: textoTipoFuncionS = "HyperBolica";
	    			break;
	    }
	    
	    if (entrenada)
	    	textoEntrenada = "Si";
	    else
	    	textoEntrenada = "No";
	    
		informacion += "Informacion Configuracion" + "\n";
		informacion += "=========== =============" + "\n";
		informacion += "Nº Patrones = " + numeroPatrones + "\n";
		informacion += "Nº Patrones Aprendizaje = " + numeroPatronesAprendizaje + "\n";
		informacion += "Nº Patrones Test= " + numeroPatronesTest + "\n";
		informacion += "Nº Entradas = " + numeroEntradas + "\n";
		informacion += "Nº Neuronas Ocultas 1 = " + numeroNeuronasCO1 + "\n";
		informacion += "Nº Neuronas Ocultas 2 = " + numeroNeuronasCO1 + "\n";
		informacion += "Nº Salidas = " + numeroSalidas + "\n";
		informacion += "Nº Ciclos = " + ciclo + "\n";
		informacion += "Tipo Funcion Capa Oculta 1 = " + textoTipoFuncionCO1 + "\n";
		informacion += "Tipo Funcion Capa Oculta 2 = " + textoTipoFuncionCO2 + "\n";
		informacion += "Tipo Funcion Capa Salida = " + textoTipoFuncionS + "\n";
		informacion += "Rata = " + rataTemporal + "\n";
		informacion += "Variacion Rata = " + variacionRata + "\n";
		informacion += "Error Maximo = " + errorMaximo + "\n";
		informacion += "Error Minimo = " + errorMinimo + "\n";
		informacion += "Porcentaje Cruzada = " + porcentajeCruzada + "\n";
		informacion += "\n";
		informacion += "Informacion Actual" + "\n";
		informacion += "=========== ======" + "\n";
		informacion += "Error = " + error + "\n";
		informacion += "ErrorAprendizaje = " + errorAprendizaje + "\n";
		informacion += "Errortest = " + errorTest + "\n";
		informacion += "RataSupervisada = " + rata + "\n";
		informacion += "Entrenada = " + textoEntrenada + "\n";
		
		return informacion;
	}
}