/**
 * @(#)RedNeuronalRBF.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Random;

/**
 * Clase que implementa una red neuronal <b>Radial Base Fase(RBF)</b>, una red
 * híbrida (no supervisada y supervisada). Esta red neuronal se construye en
 * forma genérica para atacar cualquier tipo problema de interpolación.
 * <br>&nbsp;</br>
 * La red neuronal está especializada para atacar problemas de tendencias de 
 * curvas. El tipo problema que ataca es una función definida del tipo:
 * <br>&nbsp;</br>
 * f: Rn -> Rm
 * Esta red tiene 2 tipos de aprendizaje: uno no supervisado en su fase inicial
 * y uno supervisado en su fase final.
 * El aprendizaje no supervisado es un red de Kohonen lineal sin vecindad, donde
 * una sola neurona gana (winner take all).
 * El aprendizaje supervisado es un red unicapa del tipo adeline. Este
 * aprendizaje está basado en el algoritmo LMS, caso particular del
 * Back-Propagation.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ConfiguracionNeuronalRBF
 * @see Random
 * @see Math
 */
/*
 * Observación: Se tomó los siguientes estándar para los sub-indices:
 * i: entradas(o dimensiones),  j: centroides,  r:salidas,  k:patrones
 */
public class RedNeuronalRBF
{
	/** Valores de los centroides para cada entrada. */
	private double[][] c;
	
	/**
	 * Valores de pesos neuronales entre las neuronas de salidas y los
	 * centroides.
	 */
	private double[][] w;
	
	/** Salidas ha aprender. */
	private double[][] salidasAprender;
	
	/** Entradas asociada a las salidas para aprender. */
	private double[][] entradasAprender;
	
	/** Salidas para verificar en la validación cruzada. */
	private double[][] salidasValidacionCruzada;
	
	/** Entradas para verificar en la validación cruzada. */
	private double[][] entradasValidacionCruzada;
	
	/** Entrada para la red. */
	private double[] x;
	
	/** Salida de los centroides. */
	private double[] y;
	
	/** Salida final de la red. */
	private double[] z;
	
	/** Número de entradas. */
	private int numeroPatrones;
	
	/** Números de patrones que ocuparan para el aprendizaje. */
	private int numeroPatronesAprendizaje;
	
	/** Números de patrones que ocuparan para el test. */
	private int numeroPatronesTest;
	
	/** Número de entradas. */
	private int numeroEntradas;
	
	/** Número de centroides. */
	private int numeroCentroides;
	
	/** Indica el número de centroides que estan habilitados o no. */
	private int numeroCentroidesDinamicoHabilitados;
	
	/** Número de salida. */
	private int numeroSalidas;
	
	/** Ciclo de aprendizaje no supervisado. */
	private double cicloNoSupervisado;
	
	/** Ciclo de aprendizaje supervisado. */
	private double cicloSupervisado;
	
	/** Error cometido en el test de la red. */
	private double errorTest;
	
	/** Error cometido en el aprendizaje de la red. */
	private double errorAprendizaje;
	
	/** Error total de la red. */
	private double error;
	
	/** Error mínimo aceptable para la red. */
	private double errorMinimo;
	
	/** Error máximo exigido por la red antes de desbordarse. */
	private double errorMaximo;
	
	/** Rata supervisada. */
	private double rataSupervisada;
	
	/** Deja almacenado en forma temporal rata supervisada. */
	private double rataSupervisadaTemporal;
	
	/** Variación de la rata supervisada. */
	private double variacionRataSupervisada;
	
	/** Rata no supervisada. */
	private double rataNoSupervisada;
	
	/** Rata no supervisada inicial. */
	private double rataINoSupervisada;
	
	/** Rata no supervisada final. */
	private double rataFNoSupervisada;
	
	/** Porcentaje de validación cruzada. */
	private double porcentajeCruzada;
	
	/** Inidica si la red está entrenada. */
	private boolean entrenada;
	
	/** Indica los factores que regulan los intervalos de entrada. */
	private double[][] entradaFactores;
	
	/** Indica los factores que regulan los intervalos de salidas. */
	private double[][] salidaFactores;
	
	/** Indica el si está habilitado el crecimiento dinámico de centroides. */
	private boolean centroidesDinamicoHabilitado;
	
	/**
     * Constructor de la clase. Aquí se reserva memoria para los centroides,
     * pesos sinápticos entradas, salidas y los datos para aprender de la red.
     * También se inicializan los valores y se cargan los valores en la
     * configuración.
     */
	public RedNeuronalRBF()
	{
		c = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_CENTROIDES]
					  [ConfiguracionNeuronalRBF.NUMERO_MAXIMO_ENTRADAS];
		
		w = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS]
						[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_CENTROIDES + 1];
		
		entradasAprender = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
							 [ConfiguracionNeuronalRBF.NUMERO_MAXIMO_ENTRADAS];
		
		salidasAprender = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
							[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS];
		
		entradasAprender = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
							 [ConfiguracionNeuronalRBF.NUMERO_MAXIMO_ENTRADAS];
		
		salidasValidacionCruzada =
					new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
						[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS];
		
		entradasValidacionCruzada =
					new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_PATRONES]
						[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS];
		
		entradaFactores = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_ENTRADAS]
									[2];
		
		salidaFactores = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS]
									[2];
		
		x = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_ENTRADAS];
		y = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_CENTROIDES];
		z = new double[ConfiguracionNeuronalRBF.NUMERO_MAXIMO_SALIDAS];
		
		cargarValores();
		inicializar();
	}	
	
	/**
     * Método que encuentra los factores que configuran los intervalos de la
     * red. La red neuronal necesita que los valores de entrada y salida se
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
			
			for (k = 0; k < numeroPatrones; k++)
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
			// porque no se conoce el verdadero espacio cuando el mayor y el
			// menor son iguales (hay un solo elemento)
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
			
			for (k = 0; k < numeroPatrones; k++)
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
			// porque no se conoce el verdadero espacio cuando el mayor y el
			// menor son iguales (hay un solo elemento)
			if (mayor == menor)
			{
				salidaFactores[i][0] = mayor - distanciaIgual;
				salidaFactores[i][1] = mayor + distanciaIgual;
			}
		}
	}
	
	/**
     * Método que ajusta los valores que la red necesita aprender. Los ajusta a
     * los intervalos deseados por la red o intervalos que representan la
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
     * el test general de la validacion del sistema. La utilizacion del metodo
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
     		for (i = 0; i < numeroEntradas; i++)
     			entradasValidacionCruzada[k][i] = entradasAprender[k][i];
     		
     		for (r = 0; r < numeroSalidas; r++)
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
	private void ajustarRataSupervisada(double errorTemporalAprendizaje)
	{
		double porcentaje;
		
		if (errorAprendizaje != -1)
			porcentaje = (errorTemporalAprendizaje - errorAprendizaje)
										/ errorAprendizaje*100.0;
      	else
      		porcentaje = 100;
      	
		if (porcentaje > 0 && porcentaje < 2.0)
    		rataSupervisada = rataSupervisadaTemporal;
       	
       	else
      	if (porcentaje >= 2.0)
      		rataSupervisada /= variacionRataSupervisada;
		
		else
      	if (porcentaje < 0.0)
       		rataSupervisada *= variacionRataSupervisada;
    }
    
	/**
     * Método que entrega el radio del centroide j-esimo.
     * 
     * @param j El sub-inidice del centroide J.
     *
     * @return radio El valor del radio del centroide j-esimo.
     */
	private double obtenerRadio(int j)
	{
		int i;//mirar observacion
 		double sum = 0.0;
 		double radio;
		
 		for (i = 0; i < numeroEntradas; i++)
 			sum += Math.pow(x[i] - c[j][i],2.0);
		
 		radio = Math.sqrt(sum);
 		
 		return radio;
	}
	
	/**
     * Método que entrega y almacena la salida del centroide j-esimo.
     * 
     * @param j El sub-inidice del centroide J.
     *
     * @return y[j] El valor de la salida j-esima.
     */
	private double obtenerSalidaOculta(int j)
	{
		double radio;
		radio = obtenerRadio(j);
		
  		if (radio != 0)
			y[j] = Math.pow(radio,2.0)*Math.log(radio);
  		else
  			y[j] = 0;
  		
  		return y[j];
	}
	
	/**
     * Método que almacena todas las salidas de los centroides.
     */
    private void ejecutarSalidasOcultas()
	{
 		int j;//mirar observacion
 		
 		for (j = 0; j < numeroCentroidesDinamicoHabilitados; j++)
			obtenerSalidaOculta(j);
	}
	
	/**
     * Método que almacena una entrada en la red.
     * 
     * @param x Vector que contiene una entrada a la red.
     */
	private void almacenarEntrada(double[] x)
	{
 		int i;//mirar observacion
 		
 		for (i = 0;i < numeroEntradas; i++)
 			this.x[i] = x[i];
	}
	
	/**
     * Método que refuerza la conexión del centroide ganador j.
     *
     * @param j Inidicador del centroide.
     */
	private void reforzarConexion(int j)
 	{
 		int i;//mirar observacion
  		
  		for (i = 0; i < numeroEntradas; i++)
     		c[j][i] += rataNoSupervisada*( x[i] - c[j][i] );
 	}
 	
 	/**
     * Método que hace competir a los centroides de la red neuronal.
     *
     * @return j Inidicador del centroide que gano en la competencia.
     */
	private int competir()
	{
		int i,j;//mirar observacion
 		double sum;
 		double[] menor = new double[2];
 		double[] distancia = new double[numeroCentroides];
 		double factorPresicion = ConfiguracionNeuronalRBF.FACTOR_DISTANCIA;
		
  		for (j = 0;j < numeroCentroidesDinamicoHabilitados; j++)
   		{
    		sum = 0.0;
     		for (i = 0; i < numeroEntradas ; i++)
      			sum += Math.pow(x[i] - c[j][i],2.0);
      		
    		distancia[j] = sum;
   		}
		
		menor[0] = distancia[0];
		menor[1] = 0;
		
   		for (j = 0; j < numeroCentroidesDinamicoHabilitados ; j++)
     		if (menor[0] >= distancia[j])
       		{
       			menor[0] = distancia[j];
       			menor[1] = j;
       		}
		
		// Si sobre paso la distancia maxima entre centroides y datos, y
		// si esta habilitado el crecimiento de centroides dinamicos
		if (menor[0] > factorPresicion &&
				numeroCentroidesDinamicoHabilitados < numeroCentroides)
			menor[1] = habilitarCentroide();
		
 		return (int) menor[1];
	}
	
	/**
     * Método que habilita un centroide en el espacio dado que hay un dato que
     * sobrepasó la distancia máxima entre centroides y patrones. Este se ocupa
     * cuando está habilitado el crecimiento dinámico de centroides. Además
     * entrega el identenficador cuando el centroide esta habilitado o no.
     *
     * @return centroideHabilitado Identificador del centroide habilitado.
     */
	private int habilitarCentroide()
	{
		int i,centroideHabilitado;
		
		centroideHabilitado = numeroCentroidesDinamicoHabilitados;
		
		for (i = 0; i < numeroEntradas ; i++)
      		x[i] = c[numeroCentroidesDinamicoHabilitados][i];
      	
		numeroCentroidesDinamicoHabilitados++;
		//System.out.println ("Num = " + numeroCentroidesDinamicoHabilitados);
		
		return centroideHabilitado;
	}
	
	/**
     * Método que entrega y almacena la salida de la neurona r.
     * 
     * @param r El sub-inidice  de la neurona r.
     *
     * @return z[r] El valor de la salida j-esima neurona.
     */
	private double obtenerSalida(int r)
	{
		int j;
 		double sum = 0.0;
 		
 		for (j = 0;j < numeroCentroidesDinamicoHabilitados ;j++)
			sum += w[r][j]*y[j];
		
		sum	+= w[r][numeroCentroidesDinamicoHabilitados];
		
		z[r] = sum;
		
 		return z[r];
	}
	
	/**
     * Método que desordena los patrones ingresados a la red neuronal.
     *
     * @param entradas Matriz que contiene las entradas a desordenar.
     * @param salidas Matriz que contiene las salidas a desordenar.
     * @param tamano Cantidad de patrones a desordenar.
     */
	private void desordenarPatrones(double[][] entradas,double[][] salidas,
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
     * Método donde se inicializan los valores para la red. Este método borra
     * todo aprendizaje que haya podido lograr la red.
     */
	public void inicializar()
	{
		int i,j,r; //mirar observacion
		Random azar = new Random();
		entrenada = false;
		errorAprendizaje = errorTest = error = -1; //no inicializado
		
		rataSupervisada = rataSupervisadaTemporal;
		rataNoSupervisada = rataINoSupervisada;
		
		for (j = 0;j < numeroCentroides; j++)
			for (i = 0;i < numeroEntradas; i++)
				c[j][i] = azar.nextInt(20)/10.0-1.0;
		
		for (r = 0;	r < numeroSalidas; r++)
 			for (j = 0; j <= numeroCentroides; j++)
 				w[r][j] = azar.nextInt(20)/10.0-1.0;
 		
 		for (i = 0; i < numeroEntradas; i++)
 			x[i] = 0;
 		
 		for (j = 0; j < numeroCentroides; j++)
 			y[j] = 0;
 		
 		for (r = 0; r < numeroSalidas; r++)
 			z[r] = 0;
 		
 		if (centroidesDinamicoHabilitado)
 			numeroCentroidesDinamicoHabilitados = 1;
 		else
 			numeroCentroidesDinamicoHabilitados = numeroCentroides;
 	}
	
	/**
     * Método que carga los valores de configuración para la red neuronal.
     */
	public void cargarValores()
 	{
 		double[] parametros = ConfiguracionNeuronalRBF.entregarValores();
 		
 		numeroPatrones = (int) parametros[3];
 		numeroEntradas = (int) parametros[4];
 		numeroCentroides = (int) parametros[5];
 		numeroSalidas = (int) parametros[6];
 		cicloNoSupervisado = parametros[7];
 		cicloSupervisado = parametros[8];
 		errorMinimo = parametros[10];
 		errorMaximo = parametros[11];
 		rataSupervisada = rataSupervisadaTemporal =  parametros[12];
 		variacionRataSupervisada = parametros[13];
 		rataNoSupervisada = rataINoSupervisada = parametros[14];
 		rataFNoSupervisada = parametros[15];
 		porcentajeCruzada = parametros[9];
 		centroidesDinamicoHabilitado = ConfiguracionNeuronalRBF.
 								obtenerHabilitarCentroidesDinamicos();
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
			for (i = 0; i < numeroEntradas; i++)
				this.entradasAprender[k][i] = entradasAprender[k][i];
			
			for (r = 0; r < numeroSalidas; r++)
				this.salidasAprender[k][r] = salidasAprender[k][r];
		}
		
		encontrarFactores();
		ajustarDatosAprendizaje(0);
		
		dividirDatosAprendizaje();
	}
	
	/**
     * Método que ajusta en un ciclo los valores de los centroides en el
     * aprendizaje no supervisado.
     */
	public void ajustarCentroides()
	{
		int k,neuronaGanadora;
		
		for (k = 0; k < numeroPatrones ; k++)
     	{
      		almacenarEntrada(entradasAprender[k]);
      		neuronaGanadora = competir();
      		reforzarConexion(neuronaGanadora);
     	}
	}
	
	/**
     * Método que ajusta en un ciclo los valores de los pesos sinápticos en el
     * aprendizaje supervisado.
     */
	public void ajustarPesosSinapticos()
	{
		int j,r,k; //mirar observacion
		
		for (k = 0; k < numeroPatronesAprendizaje; k++)
       	{
	  		ejecutarRed(entradasAprender[k]);
	  		for (r = 0; r < numeroSalidas; r++)
	  		{
	  			for (j = 0; j < numeroCentroidesDinamicoHabilitados; j++)
	  				w[r][j] +=  rataSupervisada * (salidasAprender[k][r] - z[r])*
	  							1*y[j];
	  			
	  			w[r][numeroCentroidesDinamicoHabilitados] += rataSupervisada *
	  									(salidasAprender[k][r] - z[r])*1;
	  		}
	  	}
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
     					Math.pow(salidasAprender[k][r] - z[r] ,2.0);
     		
	 		errorTemporalAprendizaje = sumaDiferencias;
	 	}
	 	
	 	// calculando error de la red en el test
	 	sumaDiferencias = 0;
	 	for (k = 0; k < numeroPatronesTest; k++)
	 	{
	 		ejecutarRed(entradasValidacionCruzada[k]);
	 		for (r = 0; r < numeroSalidas; r++)
     			sumaDiferencias += 1.0 / 2.0 *
     					Math.pow(salidasValidacionCruzada[k][r] - z[r] ,2.0);
     		
	 		errorTest = sumaDiferencias;
	 	}
	 	
	 	ajustarRataSupervisada(errorTemporalAprendizaje);
     	
     	errorAprendizaje = errorTemporalAprendizaje;
     	
     	if (errorTest != ConfiguracionNeuronalRBF.ERROR_NO_INICIADO)
     	   	error = errorAprendizaje + errorTest;
       	else
       		error = errorAprendizaje;
  	}
	
	/**
     * Método que entrega y almacena las salidas externas de la red neuronal. 
     * Este método ejecuta la red por completo dado una entrada. Frecuentemente
     * se ultiza el método para obtener su salida.
     * 
     * @param x Vector con la entrada a ejecutar.
     * 
     * @return z Vector con las salidas correspondiente a la entrada.
     */
	public double[] ejecutarRed(double[] x)
	{
		int r;
 		almacenarEntrada(x);
 		ejecutarSalidasOcultas();
 		
 		for (r = 0; r < numeroSalidas; r++)
 			obtenerSalida(r);
 		
 		return z;
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
		
		intervaloMenor = -0.7;
		intervaloMayor = 0.7;
		
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
     * @param z Vector que contiene una entrada a la red.
     * @param tipoAjuste Eleccion del tipo de transformacion (0:red ,1:exterior).
     * 
     * @return z Vector con los ajustado, segun el tipo de juste elegido.
     **/
	public double[] ajustarSalida(double[] z, int tipoAjuste)
	{
		int r;
		double intervaloMayor, intervaloMenor;
		
		intervaloMenor = -0.7;
		intervaloMayor = 0.7;
		
		// ajustando ha intervalos de la red
		if (tipoAjuste ==  0)
		{
			// funcion : z' = (FM - Fm)/(M - m) *(z - m) + Fm
			for (r = 0; r < numeroEntradas; r++)
			{
				z[r] = (intervaloMayor - intervaloMenor) /
						(salidaFactores[r][1] - salidaFactores[r][0]) *
						(z[r] - salidaFactores[r][0]) + intervaloMenor;
			}
		}
		
		// ajustando ha intervalos de reales
		if (tipoAjuste == 1)
		{
			// funcion : x = m + (x' - Fm) * (M - m) / (FM - Fm)
			for (r = 0; r < numeroEntradas; r++)
			{
				z[r] = salidaFactores[r][0] + (z[r] - intervaloMenor) *
						(salidaFactores[r][1] - salidaFactores[r][0]) /
						(intervaloMayor - intervaloMenor);
			}
		}
		
		return z;
	}
	
	/**
     * Método que decrementa la rata no supervisada. Este se debe ocupar en el
     * entrenamiento no supervisado.
     * 
     * @param ciclo Valor de ciclo actual en ese momento .
     */
	public void decrementarRataNoAprendizaje(double ciclo)
	{
		rataNoSupervisada = rataINoSupervisada + (rataFNoSupervisada
							- rataINoSupervisada)*(ciclo / cicloNoSupervisado);
	}
	
	/**
     * Método que retorna verdadero si la red esta entreanda.
     *
     * @return entrenda Indica si la red esta entrenada.
     */
	public boolean estaEntrenada()
	{
		return entrenada;
	}
	
	/**
     * Método que retorna verdadero si está habilitado el crecimiento dinámico
     * de los centroides.
     *
     * @return centroidesDinamicoHabilitado Indica si esta habilitado el
     *                                      crecimiento dinámico de los
     *                                      centroides.
     */
	public boolean estaCentroidesDinamicoHabilitado()
	{
		return centroidesDinamicoHabilitado;
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
	 * Método que retorna si la red esta sobre el error máximo exigido.
	 * 
	 * @return sobre Boleano que indica si la red está sobre pasada o no en su
	 *				  error máximo.
	*/
    public boolean estaSobreErrorMaximo(double ciclo)
    {
    	boolean sobre;
     	
     	if (error >= Math.pow(10,errorMaximo) && ciclo/cicloSupervisado > 0.05)
     		sobre = true;
     	else
     		sobre = false;
     	
     	return sobre;
    }
    
    /**
	 * Método que retorna si la red esta bajo en el error mínimo exigido.
	 * 
	 * @return bajo Boleano que indica si la red está bajo o no en su error
	 *              mínimo.
	 */
    public boolean estaBajoErrorMinimo(double ciclo)
    {
    	boolean bajo;
     	
     	if (error <= 1.0/Math.pow(10,errorMinimo) &&
     		ciclo/cicloSupervisado > 0.05) 
     		bajo = true;
     	else
     		bajo = false;
     	
     	return bajo;
	}
    
    /**
	 * Método que cambia el número de patrones que establece la red.
	 * 
	 * @param numerosPatrones Numeros de patrones que la red quiere aprender.
	 */
	public void cambiarNumerosPatrones(int numerosPatrones)
	{
		this.numeroPatrones = numerosPatrones;
	}
	
	/**
     * Método que retorna los valores de los centroides en la red.
     *
     * @return c Matriz que contiene los valores de los centroides.
     */
	public double[][] obtenerCentroides()
	{
		return c;
	}
 	
 	/**
     * Método que retorna los valores de los pesos en la red.
     *
     * @return w Matriz que contiene los valores de los pesos sinápticos.
     */
	public double[][] obtenerPesos()
	{
		return w;
	}
	
	/**
     * Método que retorna la cantidad de ciclo que da en el aprendizaje
     * supervisado.
     *
     * @return cicloSupevisado Cantidad de ciclo que debe dar la red en el
     *                         aprendizaje supervisado.
     */
	public double obtenerCicloSupervisado()
	{
		return cicloSupervisado;
	}
	
	/**
     * Método que retorna la cantidad de ciclo que da en el aprendizaje no
     * supervisado.
     *
     * @return cicloSupevisado Cantidad de ciclo que debe dar la red en el
     *                         aprendizje no supervisado.
     */
	public double obtenerCicloNoSupervisado()
	{
		return cicloNoSupervisado;
	}
	
	/**
     * Método que retorna la rata supervisada en ese momento.
     *
     * @return rataSupervisada Valor de la rata supervisada actual.
     */
	public double obtenerRataSupervisada()
	{
		return rataSupervisada;
	}
	
	/**
     * Método que retorna la rata no supervisada en ese momento.
     *
     * @return rataSupervisada Valor de la rata no supervisada actual.
     */
	public double obtenerRataNoSupervisada()
	{
		return rataNoSupervisada;
	}
	
	/**
     * Método que retorna el error máximo que la red acepta para que el
     * aprendizaje pare insatisfactoriamente.
     *
     * @return errorMaximo Error máximo para que la se detenga en forma
     *					   insatisfactoria.
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
     * Método que retorna el número de centroides en la red.
     *
     * @return numeroCentroides El número de centroides en la red.
     */
	public int obtenerNumeroCentroides()
	{
		return numeroCentroides;
	}
	
	/**
     * Método que retorna el número de centroides en la red.
     *
     * @return numeroCentroidesDinamicoHabilitados El número de centroides en
     *											   la red.
     */
	public int obtenerNumeroCentroidesDinamicoHabilitados()
	{
		return numeroCentroidesDinamicoHabilitados;
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
     * Método que retorna el número de patrones ha aprender en la red.
     *
     * @return numeroEntradas El número de patrones ha aprender en la red.
     */
	public int obtenerNumeroPatrones()
	{
		return numeroPatrones;
	}
	
	/**
     * Método que retorna el número de patrones deseables que la red necesita
     * para lograr el error minimo requerido.
     * 
     * @return numeroPatronesDeseable El numero de patrones deseables que la red
     *                                necesita para lograr el error mínimo
     *                                requerido.
     */
    public int obtenerNumeroPatronesDeseables()
	{
		int numeroPatronesDeseable;
		int numeroPesosCentroides;
		int numeroCentroidesOcupados;
		
		if (centroidesDinamicoHabilitado)
			numeroCentroidesOcupados = numeroCentroidesDinamicoHabilitados;
		else
			numeroCentroidesOcupados = numeroCentroides;
		
		numeroPesosCentroides = (numeroEntradas *
								numeroCentroidesDinamicoHabilitados) +
								(numeroCentroidesOcupados * numeroSalidas) +
								numeroSalidas;
		
		numeroPatronesDeseable = (int) (numeroPesosCentroides * Math.pow(10,
														errorMinimo - 1) );
		
		return numeroPatronesDeseable;
	}
	
	/**
     * Método que devuelve la confiabilidad del aprendizaje que ejecuto la red 
     * neuronal. La confiablidad se mide en un factor que se encuentra entre
     * [0,1]. Si la red neuronal no está entrenada devuelve 0.
     * 
     * @return confiablidad Indica la confiablidad que tiene la red en el
     *						aprendizaje 
     */
	public double obtenerConfiabilidadAprendizaje()
	{
		double confiabilidad = 0.0;
		double confiabilidadNumeroPatrones, confiabilidadError;
		
		if (entrenada)
		{
			confiabilidadNumeroPatrones = 1.0 * numeroPatrones /
										  obtenerNumeroPatronesDeseables();
			
			confiabilidadError = (1 - Servicio.obtenerRazon(error,
								 1.0 / Math.pow(10,errorMinimo),
								 Math.pow(10,errorMaximo)));
			
			confiabilidad = 0.5 * confiabilidadNumeroPatrones +
							0.5 * confiabilidadError;
			
			//System.out.println ("PASO 1=" + confiabilidadNumeroPatrones);
			//System.out.println ("PASO 2=" + confiabilidadError);
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
		int i,j;
		boolean habilitadoCentroides = false , habilitadoPesos = true;
		
		String informacion = "\n";
		String textoEntrenada,textoHabilitadoCentroideDinamico;
		
		if (entrenada)
	    	textoEntrenada = "Si";
	    else
	    	textoEntrenada = "No";
	    
	    if (ConfiguracionNeuronalRBF.obtenerHabilitarCentroidesDinamicos())
   	    	textoHabilitadoCentroideDinamico = "Si";
	    else
	    	textoHabilitadoCentroideDinamico = "No";
		
		informacion += "Informacion Configuracion" + "\n";
		informacion += "=========== =============" + "\n";
		informacion += "Nº Patrones = " + numeroPatrones + "\n";
		informacion += "Nº Patrones Aprendizaje = " + numeroPatronesAprendizaje + "\n";
		informacion += "Nº Patrones Test= " + numeroPatronesTest + "\n";
		informacion += "Nº Entradas = " + numeroEntradas + "\n";
		informacion += "Nº Centroides = " + numeroCentroides + "\n";
		informacion += "Nº Salidas = " + numeroSalidas + "\n";
		informacion += "Nº Ciclos No Supervisado = " + cicloNoSupervisado + "\n";
		informacion += "Nº Ciclos Supervisado = " + cicloSupervisado + "\n";
		informacion += "Rata Supervisada = " + rataSupervisadaTemporal + "\n";
		informacion += "Variacion Rata Supervisada = " + variacionRataSupervisada + "\n";
		informacion += "Rata Inicial No Supervisada = " + rataINoSupervisada + "\n";
		informacion += "Rata Final No Supervisada = " + rataFNoSupervisada + "\n";
		informacion += "Error Maximo = " + errorMaximo + "\n";
		informacion += "Error Minimo = " + errorMinimo + "\n";
		informacion += "Porcentaje Cruzada = " + porcentajeCruzada + "\n";
		informacion += "Habilitado Dinamico = " + textoHabilitadoCentroideDinamico + "\n";
		informacion += "\n";
		informacion += "Informacion Actual" + "\n";
		informacion += "=========== ======" + "\n";
		informacion += "Error = " + error + "\n";
		informacion += "ErrorAprendizaje = " + errorAprendizaje + "\n";
		informacion += "Errortest = " + errorTest + "\n";
		informacion += "RataSupervisada = " + rataSupervisada + "\n";
		informacion += "Rata No Supervisada = " + rataNoSupervisada + "\n";
		informacion += "Entrenada = " + textoEntrenada + "\n";
		informacion += "Centroides Habilitados = " + numeroCentroidesDinamicoHabilitados + "\n";
		informacion += "\n";
		
		if (habilitadoCentroides)
		{
			informacion += "Informacion Centroides" + "\n";
			informacion += "=========== ==========" + "\n";
			informacion += "Centroides:" + numeroCentroides + "\n";
			informacion += " {"; 
			
			for (j = 0; j < numeroCentroides; j++)
			{
				informacion += " (";
				for( i = 0; i < numeroEntradas; i++)
					informacion += (int)(c[j][i]*100) / 100.0 + ",";
				informacion += ") ," + "\n";
			}
			
			informacion += " }";
		}
		
		return informacion;
	}
}