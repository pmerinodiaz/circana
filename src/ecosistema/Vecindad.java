/**
 * @(#)Vecindad.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa la vecindad de una celda. La vecindad consiste en un
 * conjunto contiguo de celdas, indicando sus posiciones relativas respecto a la
 * celda misma. Para cada celda de ubicación de un super-individuo del sistema
 * es necesario establecer su vecindad, esto es, aquellas otras celdas que son
 * ocupadas por otros super-individuos que serán considerados como sus vecinos.
 * La vecindad de una celda son todas aquellas otras celdas que se encuentran a
 * menos de una cierta distancia o radio, de forma que los más alejadas no
 * ejerzan influencia directa sobre ella.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ConfiguracionAC
 * @see Celda
 * @see SuperIndividuo
 * @see Espacio
 * @see VectorCelda
 * @see Mapa
 * @see Recurso
 * @see TipoDieta
 * @see Servicio
 */
public class Vecindad
{
	/** El valor que indica que la vecindad es Von Neumann. */
	public static final int VON_NEUMANN = 0;
	
	/** El valor que indica que la vecindad es Moore. */
	public static final int MOORE = 1;
	
	/** El valor para los radios por defecto. */
	private static final int RADIO_DEFECTO = 2;
	
	/** El valor para las velocidades por defecto. */
	private static final int VELOCIDAD_DEFECTO = 1;
	
	/** El tipo de vecindad. */
	private int tipo;
	
	/** El radio de la vecindad. */
	private int radio;
	
	/** La velocidad de la vecindad. */
	private int velocidad;
	
	/** La cantidad de vecinos percibidos. */
	private int cantidadVecino;
	
	/** La ubicación del mejor predador. */
	private Celda mejorPredador;
	
	/** La cantidad de predadores percibidos. */
	private int cantidadPredador;
	
	/** La calificación máxima de los predadores. */
	private double calificacionPredador;
	
	/** La ubicación de la mejor presa. */
	private Celda mejorPresa;
	
	/** La cantidad de presas percibidas. */
	private int cantidadPresa;
	
	/** La calificación máxima de las presas. */
	private double calificacionPresa;
	
	/** La ubicación del mejor sexo opuesto. */
	private Celda mejorSexoOpuesto;
	
	/** La cantidad de sexos opuestos percibidos. */
	private int cantidadSexoOpuesto;
	
	/** La calificación máxima de los sexos opuestos. */
	private double calificacionSexoOpuesto;
	
	/** La ubicación de la mejor agua de desove. */
	private Celda mejorAguaDesove;
	
	/** La cantidad de aguas de desove percibidas. */
	private int cantidadAguaDesove;
	
	/** La calificación máxima de las aguas de desove. */
	private double calificacionAguaDesove;
	
	/** La ubicación del mejor conviviente. */
	private Celda mejorConviviente;
	
	/** La cantidad de convivientes percibidos. */
	private int cantidadConviviente;
	
	/** La calificación máxima de los convivientes. */
	private double calificacionConviviente;
	
	/**
	 * El vector que contiene las celdas que son aguas, que se encuentran vacías
	 * y que son alcanzables.
	 */
	private VectorCelda agua = new VectorCelda();
	
	/**
	 * Método constructor en donde se inicializan los atributos de la clase con
	 * los valores por defecto.
	 *
	 * @param radio El valor para el radio de la vecindad.
	 * @param velocidad El valor para la velocidad de la vecindad.
	 */
	public Vecindad(int radio, int velocidad)
	{
		this(ConfiguracionAC.obtenerVecindad(), radio, velocidad);
	}
	
	/**
	 * Método constructor en donde se inicializan los atributos de la clase con
	 * los valores recibidos como parámetros.
	 *
	 * @param tipo El valor para el tipo de vecindad.
	 * @param radio El valor para el radio de la vecindad.
	 * @param velocidad El valor para la velocidad de la vecindad.
	 */
	public Vecindad(int tipo, int radio, int velocidad)
	{
		establecerTipo(tipo);
		establecerRadio(radio);
		establecerVelocidad(velocidad);
	}
	
	/**
	 * Método que inicializa los valores del vector de celdas vecinas,
	 * dependiendo del tipo de vecindad que se está utilizando. Las celdas
	 * vecinas corresponden a las celdas adyacentes a la celda recibida por
	 * parámetros, dentro del espacio especificado como parámetro.
	 *
	 * @param superIndividuo El super-individuo al cual se le calculan sus
	 *                       celdas vecinas.
	 * @param espacio El espacio de donde se obtienen las celdas vecinas.
	 */
	public void evaluar(SuperIndividuo superIndividuo, Espacio espacio)
	{
		// Obtener la celda de ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		
		// Obtener las coordenadas de la celda.
		int ubicacionI = ubicacion.obtenerI();
		int ubicacionJ = ubicacion.obtenerJ();
		int ubicacionK = ubicacion.obtenerK();
		
		// Obtener los tamaños del espacio.
		int tamanioI = espacio.obtenerTamanioI();
		int tamanioJ = espacio.obtenerTamanioJ();
		int tamanioK = espacio.obtenerTamanioK();
		
		// Limpiar los datos anteriores.
		limpiar();
		
		// Dependiendo del tipo de vecindad, se establece el valor para los
		// vecinos.
		switch (tipo)
		{
			// Cuando es Von Neumann.
			case VON_NEUMANN:
			
			// Recorrido de la vecindad.
			for (int i=ubicacionI-radio; i<=ubicacionI+radio; i++)
				for (int j=ubicacionJ-radio; j<=ubicacionJ+radio; j++)
					for (int k=ubicacionK-radio; k<=ubicacionK+radio; k++)
						// Cuando la celda con coordenadas i, j y k cumplen las
						// propiedades de Von Neumann.
						if ((i != ubicacionI-radio && j != ubicacionJ-radio) ||
							(i != ubicacionI-radio && j != ubicacionJ+radio) ||
							(i != ubicacionI+radio && j != ubicacionJ-radio) ||
							(i != ubicacionI+radio && j != ubicacionJ+radio))
							limitar(superIndividuo, espacio, i, j, k);
			break;
			
			// Cuando es Moore.
			case MOORE:
			
			// Recorrido de la vecindad.
			for (int i=ubicacionI-radio; i<=ubicacionI+radio; i++)
				for (int j=ubicacionJ-radio; j<=ubicacionJ+radio; j++)
					for (int k=ubicacionK-radio; k<=ubicacionK+radio; k++)
						limitar(superIndividuo, espacio, i, j, k);
			break;
		}
	}
	
	/**
	 * Método en donde se limpian los datos anteriores. Se dejan en nulo las
	 * mejores opciones de la vecindad, las cantidades se establecen en cero y
	 * las calificaciones se establecen en un valor muy bajo.
	 */
	private void limpiar()
	{
		// Inicializar en nulo.
		mejorPredador = null;
		mejorPresa = null;
		mejorSexoOpuesto = null;
		mejorAguaDesove = null;
		mejorConviviente = null;
		
		// Remover los objetos de los vectores.
		agua.removeAllElements();
		
		// Inicializar las cantidades en cero.
		cantidadVecino = 0;
		cantidadPredador = 0;
		cantidadPresa = 0;
		cantidadSexoOpuesto = 0;
		cantidadAguaDesove = 0;
		cantidadConviviente = 0;
		
		// Inicializar las calificaciones en un valor muy bajo.
		calificacionPredador = Double.NEGATIVE_INFINITY;
		calificacionPresa = Double.NEGATIVE_INFINITY;
		calificacionSexoOpuesto = Double.NEGATIVE_INFINITY;
		calificacionAguaDesove = Double.NEGATIVE_INFINITY;
		calificacionConviviente = Double.NEGATIVE_INFINITY;
	}
	
	/**
	 * Método en donde se limita la celda del espacio con posición (i, j, k),
	 * con respecto a si la posición está dentro o fuera de los límites del
	 * espacio. En el caso en que se encuentre fuera de los límites permitidos,
	 * entonces se ve el tipo de límite del espacio y se clasifica al vecino.
	 *
	 * @param superIndividuo El super-individuo al cual se le está viendo su
	 *                       vecindad.
	 * @param espacio El espacio en donde se está viendo su vecindad.
	 * @param i El valor de la componente I.
	 * @param j El valor de la componente J.
	 * @param k El valor de la componente K.
	 */
	private void limitar(SuperIndividuo superIndividuo, Espacio espacio,
						 int i, int j, int k)
	{
		// Cuando estamos dentro de los límites del espacio.
		if (0<=i && i<espacio.obtenerTamanioI() &&
			0<=j && j<espacio.obtenerTamanioJ() &&
			0<=k && k<espacio.obtenerTamanioK())
			clasificar(superIndividuo, espacio, new Celda(i, j, k,
														  Celda.VALIDA));
		
		// Cuando estamos fuera de los límites del espacio.
		else
		{
			// Dependiendo del tipo de límite, se establece el valor para los
			// que están fuera del espacio.
			switch (ConfiguracionAC.obtenerLimite())
			{
				// Cuando es Finito.
				case Espacio.FINITO:
				break;
				
				// Cuando es Infinito.
				case Espacio.INFINITO:
				clasificar(superIndividuo, espacio, new Celda());
				break;
			}
		}
	}
	
	/**
	 * Método en donde se clasifica una celda vecina del espacio con respecto a
	 * un super-individuo. La clasificación consiste en ver si la celda vecina
	 * es: predador, presa, sexo opuesto, conviviente, agua o tierra.
	 *
	 * @param superIndividuo El super-individuo al cual se le está viendo su
	 *                       vecindad.
	 * @param espacio El espacio en donde se está viendo su vecindad.
	 * @param celdaVecina La celda vecina del super-individuo.
	 */
	private void clasificar(SuperIndividuo superIndividuo, Espacio espacio,
							Celda celdaVecina)
	{
		// Cuando la celda vecina es una celda válida.
		if (celdaVecina.obtenerTipo() == Celda.VALIDA)
		{
			// Obtener el super-individuo vecino.
			SuperIndividuo superIndividuoVecino =
			espacio.buscarGrilla(celdaVecina);
			
			// Cuando el super-individuo vecino existe y no es el mismo
			// super-individuo.
			if ((superIndividuoVecino != null) &&
				(superIndividuoVecino != superIndividuo))
			{
				// Cuando el super-individuo vecino es un predador del
				// super-individuo.
				if (esPredador(superIndividuoVecino, superIndividuo))
				{
					// Calificar como predador.
					calificarPredador(superIndividuoVecino, superIndividuo);
					cantidadPredador++;
				}
				
				// Cuando el super-individuo vecino es una presa del
				// super-individuo.
				if (esPredador(superIndividuo, superIndividuoVecino))
				{
					// Calificar como presa.
					calificarPresa(superIndividuoVecino, superIndividuo);
					cantidadPresa++;
				}
				
				// Cuando el super-individuo vecino es un sexo opuesto del
				// super-individuo.
				if (esSexoOpuesto(superIndividuoVecino, superIndividuo))
				{
					// Calificar como sexo opuesto.
					calificarSexoOpuesto(superIndividuoVecino, superIndividuo);
					cantidadSexoOpuesto++;
				}
				
				// Cuando el super-individuo vecino es un conviviente del
				// super-individuo.
				if (esConviviente(superIndividuoVecino, superIndividuo))
				{
					// Calificar como conviviente.
					calificarConviviente(superIndividuoVecino, superIndividuo);
					cantidadConviviente++;
				}
			}
			
			// Cuando el super-individuo vecino no existe o es el
			// mismo super-individuo.
			else
			{
				// Obtener la coordenada de la celda vecina.
				Coordenada coordenada =
								Servicio.transformarCeldaCoordenada(celdaVecina,
								espacio.obtenerTransformacion());
				
				// Cuando el vecino no existe y la coordenada de la celda vecina
				// es agua y alcanzable por el super-individuo.
				if ((superIndividuoVecino == null) &&
					(Mapa.esAgua(coordenada)) &&
					(superIndividuo.esAlcanzable(celdaVecina)))
				{
					// Calificar como agua para desove.
					calificarAguaDesove(celdaVecina, superIndividuo);
					cantidadAguaDesove++;
					
					// Agregar la celda vecina al vector agua.
					agua.addElement(celdaVecina.clone());
				}
				
				// Cuando el super-individuo se alimenta de fitoplancton y la
				// coordenada de la celda vecina tiene fitoplancton.
				if ((esAlimento(superIndividuo, TipoDieta.FITOPLANCTIVORO)) &&
					(Mapa.tieneFitoplancton(coordenada)))
				{
					// Calificar como presa.
					calificarPresa(celdaVecina, superIndividuo);
					cantidadPresa++;
				}
				
				// Cuando el super-individuo se alimenta de zooplancton y la
				// coordenada de la celda vecina tiene zooplancton.
				if ((esAlimento(superIndividuo, TipoDieta.ZOOPLANCTIVORO)) &&
					(Mapa.tieneZooplancton(coordenada)))
				{
					// Calificar como presa.
					calificarPresa(celdaVecina, superIndividuo);
					cantidadPresa++;
				}
				
				// Cuando el super-individuo se alimenta de detrito o sedimento
				// y la coordenada de la celda vecina tiene materia orgánica.
				if (((esAlimento(superIndividuo, TipoDieta.DETRITIVORO)) ||
					 (esAlimento(superIndividuo, TipoDieta.SEDIMENTIVORO))) &&
					(Mapa.tieneMateriaOrganica(coordenada)))
				{
					// Calificar como presa.
					calificarPresa(celdaVecina, superIndividuo);
					cantidadPresa++;
				}
			}
		}
		
		// Cuando la celda vecina es una celda no válida.
		else
		{
		}
		
		cantidadVecino++;
	}
	
	/**
	 * Método que determina si el super-individuo A es o no predador del
	 * super-individuo B. En caso de serlo, se retorna true. En caso contrario,
	 * se retorna false. El super-individuo A es predador del super-individuo B
	 * si se cumple que: (1) el super-individuo A está vivo, (2) el
	 * super-individuo A tiene un tipo de dieta carnívoro, (3) el
	 * super-individuo A se alimenta de super-individuo B y (4) la razón de
	 * tallas es menor que el umbral de predación ó el super-individuo B está
	 * muerto.
	 *
	 * @param superIndividuoA El super-individuo A de la comparación.
	 * @param superIndividuoB El super-individuo B de la comparación.
	 *
	 * @return true Cuando el super-individuo A es predador del super-individuo
	 *              B.
	 * @return false Cuando el super-individuo A no es predador del
	 *               super-individuo B.
	 */
	private boolean esPredador(SuperIndividuo superIndividuoA,
							   SuperIndividuo superIndividuoB)
	{
		// Obtener los códigos de los super-individuos.
		int codigoRecursoA = superIndividuoA.obtenerRecurso();
		int codigoRecursoB = superIndividuoB.obtenerRecurso();
		
		// Obtener el estado de los super-individuos.
		int estadoA = superIndividuoA.obtenerEstado();
		int estadoB = superIndividuoB.obtenerEstado();
		
		// Obtener las tallas de los super-individuos.
		double tallaA = superIndividuoA.obtenerTalla();
		double tallaB = superIndividuoB.obtenerTalla();
		
		return
			(estadoA != SuperIndividuo.DESAPARECIDO) &&
			(estadoA != SuperIndividuo.MUERTO) &&
			(Recurso.obtenerAlimentacionTipoDieta(codigoRecursoA,
				TipoDieta.CARNIVORO).equalsIgnoreCase("Si")) &&
			(Recurso.obtenerAlimentacionFaunaAcompaniante(codigoRecursoA,
				codigoRecursoB) > 0) &&
			((Recurso.calcularAdecuacionTamanio(tallaA, tallaB) >=
			  Recurso.UMBRAL_PREDACION) ||
			 (estadoB == SuperIndividuo.MUERTO));
	}
	
	/**
	 * Método que determina si el super-individuo A es o no de sexo opuesto y
	 * con ganas de reproducirse con respecto al super-individuo B. En caso de
	 * serlo, se retorna true. En caso contrario, se retorna false. El
	 * super-individuo A es de sexo opuesto y con ganas de reproducirse con el
	 * super-individuo B si se cumple que: (1) el super-individuo A es
	 * conviviente con el super-individuo B, (2) el sexo del super-individuo A
	 * es diferente al sexo del super-individuo B, (3) el super-individuo A
	 * tiene probabilidades de reproducción.
	 *
	 * @param superIndividuoA El super-individuo A de la comparación.
	 * @param superIndividuoB El super-individuo B de la comparación.
	 *
	 * @return true Cuando el super-individuo A es de sexo opuesto y con ganas
	 *              de reproducirse con respecto al super-individuo B.
	 * @return false Cuando el super-individuo A no es de sexo opuesto o no
	 *               tiene ganas de reproducirse con respecto al super-individuo
	 *               B.
	 */
	private boolean esSexoOpuesto(SuperIndividuo superIndividuoA,
								  SuperIndividuo superIndividuoB)
	{
		// Obtener el sexo de los super-individuos.
		int sexoA = superIndividuoA.obtenerSexo();
		int sexoB = superIndividuoB.obtenerSexo();
		
		// Obtener la probabilidad de reproducción del super-individuo A.
		double reproduccionA = superIndividuoA.obtenerReproduccion();
		
		return
			(esConviviente(superIndividuoA, superIndividuoB)) &&
			(sexoA != sexoB) &&
			(reproduccionA > 0);
	}
	
	/**
	 * Método que determina si el super-individuo A es o no conviviente con
	 * respecto al super-individuo B. En caso de serlo, se retorna true. En caso
	 * contrario, se retorna false. El super-individuo A es conviviente con
	 * respecto al super-individuo B si se cumple que: (1) el super-individuo A
	 * está vivo y (2) el super-individuo A es el mismo recurso que el
	 * super-individuo B.
	 *
	 * @param superIndividuoA El super-individuo A de la comparación.
	 * @param superIndividuoB El super-individuo B de la comparación.
	 *
	 * @return true Cuando el super-individuo A es conviviente del
	 *              super-individuo B.
	 * @return false Cuando el super-individuo A no es conviviente del
	 *               super-individuo B.
	 */
	private boolean esConviviente(SuperIndividuo superIndividuoA,
								  SuperIndividuo superIndividuoB)
	{
		// Obtener el estado del super-individuo A.
		int estadoA = superIndividuoA.obtenerEstado();
		
		// Obtener los códigos de los super-individuos.
		int codigoRecursoA = superIndividuoA.obtenerRecurso();
		int codigoRecursoB = superIndividuoB.obtenerRecurso();
		
		return
			(estadoA != SuperIndividuo.DESAPARECIDO) &&
			(estadoA != SuperIndividuo.MUERTO) &&
			(codigoRecursoA == codigoRecursoB);
	}
	
	/**
	 * Método que determina si el super-individuo se alimenta o no del tipo de
	 * dieta. En caso de serlo, se retorna true. En caso contrario, se retorna
	 * false. El super-individuo se alimenta del tipo de dieta si se cumple
	 * que: (1) el super-individuo está vivo y (2) el super-individuo A se
	 * alimenta del tipo de dieta especificado.
	 *
	 * @param superIndividuo El super-individuo de la comparación.
	 * @param tipoDieta El tipo de dieta de la comparación.
	 *
	 * @return true Cuando el super-individuo se alimenta del tipo de dieta.
	 * @return false Cuando el super-individuo no se alimenta dle tipo de dieta.
	 */
	private boolean esAlimento(SuperIndividuo superIndividuo,
							   int tipoDieta)
	{
		// Obtener el código del super-individuo.
		int codigoRecurso = superIndividuo.obtenerRecurso();
		
		// Obtener el estado del super-individuo.
		int estado = superIndividuo.obtenerEstado();
		
		return
			(estado != SuperIndividuo.DESAPARECIDO) &&
			(estado != SuperIndividuo.MUERTO) &&
			(Recurso.obtenerAlimentacionTipoDieta(codigoRecurso, tipoDieta).
			 equalsIgnoreCase("Si"));
	}
	
	/**
	 * Método en donde se realiza calificación por predación del super-individuo
	 * A con respecto al super-individuo B. La calificación por predación mide:
	 * (1) el grado de importancia de la alimentación del super-individuo A con
	 * respecto al super-individuo B y (2) el grado de importancia de la
	 * distancia entre los super-individuos A y B.
	 *
	 * @param superIndividuoA El super-individuo A de la calificación.
	 * @param superIndividuoB El super-individuo B de la calificación.
	 */
	private void calificarPredador(SuperIndividuo superIndividuoA,
								   SuperIndividuo superIndividuoB)
	{
		// Obtener los códigos de los super-individuos.
		int codigoRecursoA = superIndividuoA.obtenerRecurso();
		int codigoRecursoB = superIndividuoB.obtenerRecurso();
		
		// Obtener las ubicaciones de los super-individuos.
		Celda ubicacionA = superIndividuoA.obtenerUbicacion();
		Celda ubicacionB = superIndividuoB.obtenerUbicacion();
		
		// Calificar al super-individuo A.
		double calificacion =
		Recurso.obtenerAlimentacionFaunaAcompaniante(codigoRecursoA,
													 codigoRecursoB) * 0.5 -
		Servicio.obtenerDistancia(ubicacionA, ubicacionB) * 0.5;
		
		// Cuando la calificación actual es más alta que la calificación
		// anterior.
		if (calificacion > calificacionPredador)
		{
			mejorPredador = (Celda) ubicacionA.clone();
			calificacionPredador = calificacion;
		}
	}
	
	/**
	 * Método en donde se realiza calificación por presa del super-individuo A
	 * con respecto al super-individuo B. La calificación por presa mide: (1) el
	 * grado de importancia de la alimentación del super-individuo B con
	 * respecto al super-individuo A y (2) el grado de importancia de la
	 * distancia entre los super-individuos A y B.
	 *
	 * @param superIndividuoA El super-individuo A de la calificación.
	 * @param superIndividuoB El super-individuo B de la calificación.
	 */
	private void calificarPresa(SuperIndividuo superIndividuoA,
								SuperIndividuo superIndividuoB)
	{
		// Obtener los códigos de los super-individuos.
		int codigoRecursoA = superIndividuoA.obtenerRecurso();
		int codigoRecursoB = superIndividuoB.obtenerRecurso();
		
		// Obtener las ubicaciones de los super-individuos.
		Celda ubicacionA = superIndividuoA.obtenerUbicacion();
		Celda ubicacionB = superIndividuoB.obtenerUbicacion();
		
		// Calificar al super-individuo A.
		double calificacion =
		Recurso.obtenerAlimentacionFaunaAcompaniante(codigoRecursoB,
													 codigoRecursoA) * 0.5 -
		Servicio.obtenerDistancia(ubicacionA, ubicacionB) * 0.5;
		
		// Cuando la calificación actual es más alta que la calificación
		// anterior.
		if (calificacion > calificacionPresa)
		{
			mejorPresa = (Celda) ubicacionA.clone();
			calificacionPresa = calificacion;
		}
	}
	
	/**
	 * Método en donde se realiza calificación por sexo opuesto del
	 * super-individuo A con respecto al super-individuo B. La calificación
	 * por sexo opuesto mide: (1) el grado de importancia de que la probabilidad
	 * de que el super-individuo A se encuentre con ganas de reproducirse y (2)
	 * el grado de importancia de la distancia entre los super-individuos A y B.
	 *
	 * @param superIndividuoA El super-individuo A de la calificación.
	 * @param superIndividuoB El super-individuo B de la calificación.
	 */
	private void calificarSexoOpuesto(SuperIndividuo superIndividuoA,
									  SuperIndividuo superIndividuoB)
	{
		// Obtener la probabilidad de reproducción del super-individuo A.
		double reproduccionA = superIndividuoA.obtenerReproduccion();
		
		// Obtener las ubicaciones de los super-individuos.
		Celda ubicacionA = superIndividuoA.obtenerUbicacion();
		Celda ubicacionB = superIndividuoB.obtenerUbicacion();
		
		// Calificar al super-individuo A.
		double calificacion =
		reproduccionA * 0.5 -
		Servicio.obtenerDistancia(ubicacionA, ubicacionB) * 0.5;
		
		// Cuando la calificación actual es más alta que la calificación
		// anterior.
		if (calificacion > calificacionSexoOpuesto)
		{
			mejorSexoOpuesto = (Celda) ubicacionA.clone();
			calificacionSexoOpuesto = calificacion;
		}
	}
	
	/**
	 * Método en donde se realiza calificación por agua para desovar de la celda
	 * vecina con respecto al super-individuo. La calificación por agua para
	 * desovar mide: (1) el grado de importancia de la distancia entre la celda
	 * vecina y el super-individuo.
	 *
	 * @param vecina La celda vecina de la calificación.
	 * @param superIndividuo El super-individuo de la calificación.
	 */
	private void calificarAguaDesove(Celda vecina,
									 SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		
		// Calificar a la vecina.
		double calificacion =
		Servicio.obtenerDistancia(vecina, ubicacion) * -1;
		
		// Cuando la calificación actual es más alta que la calificación
		// anterior.
		if (calificacion > calificacionAguaDesove)
		{
			mejorAguaDesove = (Celda) vecina.clone();
			calificacionAguaDesove = calificacion;
		}
	}
	
	/**
	 * Método en donde se realiza calificación por conviviente del
	 * super-individuo A con respecto al super-individuo B. La calificación
	 * por conviviente mide: (1) el grado de importancia de la distancia entre
	 * los super-individuos A y B.
	 *
	 * @param superIndividuoA El super-individuo A de la calificación.
	 * @param superIndividuoB El super-individuo B de la calificación.
	 */
	private void calificarConviviente(SuperIndividuo superIndividuoA,
									  SuperIndividuo superIndividuoB)
	{
		// Obtener las ubicaciones de los super-individuos.
		Celda ubicacionA = superIndividuoA.obtenerUbicacion();
		Celda ubicacionB = superIndividuoB.obtenerUbicacion();
		
		// Calificar al super-individuo A.
		double calificacion =
		Servicio.obtenerDistancia(ubicacionA, ubicacionB) * -1;
		
		// Cuando la calificación actual es más alta que la calificación
		// anterior.
		if (calificacion > calificacionConviviente)
		{
			mejorConviviente = (Celda) ubicacionA.clone();
			calificacionConviviente = calificacion;
		}
	}
	
	/**
	 * Método en donde se realiza calificación por presa de la celda vecina con
	 * respecto al super-individuo. La calificación por presa mide: (1) el grado
	 * de importancia de la distancia entre la celda vecina y el
	 * super-individuo.
	 *
	 * @param celdaVecina La celda vecina de la calificación.
	 * @param superIndividuo El super-individuo de la calificación.
	 */
	private void calificarPresa(Celda celdaVecina,
								SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		
		// Calificar a la celda vecina.
		double calificacion =
		Servicio.obtenerDistancia(celdaVecina, ubicacion) * -1;
		
		// Cuando la calificación actual es más alta que la calificación
		// anterior.
		if (calificacion > calificacionPresa)
		{
			mejorPresa = (Celda) celdaVecina.clone();
			calificacionPresa = calificacion;
		}
	}
	
	/**
	 * Método que establece un valor al atributo tipo.
	 *
	 * @param tipo El valor para el atributo tipo.
	 */
	public void establecerTipo(int tipo)
	{
		this.tipo = tipo;
	}
	
	/**
	 * Método que obtiene el valor del atributo tipo.
	 *
	 * @return tipo El valor del atributo tipo.
	 */
	public int obtenerTipo()
	{
		return tipo;
	}
	
	/**
	 * Método que establece un valor al atributo radio. Si el valor del radio
	 * es mayor que cero, entonces se establece el valor recibido por
	 * parámetros. En caso contrario, se establece el valor uno.
	 *
	 * @param radio El valor para el atributo radio.
	 */
	public void establecerRadio(int radio)
	{
		if (radio > 0)
			this.radio = radio;
		else this.radio = RADIO_DEFECTO;
	}
	
	/**
	 * Método que establece un valor al atributo velocidad. Si el valor de la
	 * velocidad es mayor que cero, entonces se establece el valor recibido por
	 * parámetros. En caso contrario, se establece el valor uno.
	 *
	 * @param velocidad El valor para el atributo velocidad.
	 */
	public void establecerVelocidad(int velocidad)
	{
		if (velocidad > 0)
			this.velocidad = velocidad;
		else this.velocidad = VELOCIDAD_DEFECTO;
	}
	
	/**
	 * Método que obtiene el valor del atributo radio.
	 *
	 * @return radio El valor del atributo radio.
	 */
	public int obtenerRadio()
	{
		return radio;
	}
	
	/**
	 * Método que obtiene el valor del atributo velocidad.
	 *
	 * @return velocidad El valor del atributo velocidad.
	 */
	public int obtenerVelocidad()
	{
		return velocidad;
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadVecino.
	 *
	 * @return cantidadVecino El valor del atributo cantidadVecino.
	 */
	public int obtenerCantidadVecino()
	{
		return cantidadVecino;
	}
	
	/**
	 * Método que obtiene el valor del atributo mejorPredador.
	 *
	 * @return mejorPredador El valor del atributo mejorPredador.
	 */
	public Celda obtenerMejorPredador()
	{
		return mejorPredador;
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadPredador.
	 *
	 * @return cantidadPredador El valor del atributo cantidadPredador.
	 */
	public int obtenerCantidadPredador()
	{
		return cantidadPredador;
	}
	
	/**
	 * Método que obtiene el valor del atributo calificacionPredador.
	 *
	 * @return calificacionPredador El valor del atributo calificacionPredador.
	 */
	public double obtenerCalificacionPredador()
	{
		return calificacionPredador;
	}
	
	/**
	 * Método que obtiene el valor del atributo mejorPresa.
	 *
	 * @return mejorPresa El valor del atributo mejorPresa.
	 */
	public Celda obtenerMejorPresa()
	{
		return mejorPresa;
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadPresa.
	 *
	 * @return cantidadPresa El valor del atributo cantidadPresa.
	 */
	public int obtenerCantidadPresa()
	{
		return cantidadPresa;
	}
	
	/**
	 * Método que obtiene el valor del atributo calificacionPresa.
	 *
	 * @return calificacionPresa El valor del atributo calificacionPresa.
	 */
	public double obtenerCalificacionPresa()
	{
		return calificacionPresa;
	}
	
	/**
	 * Método que obtiene el valor del atributo mejorSexoOpuesto.
	 *
	 * @return mejorSexoOpuesto El valor del atributo mejorSexoOpuesto.
	 */
	public Celda obtenerMejorSexoOpuesto()
	{
		return mejorSexoOpuesto;
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadSexoOpuesto.
	 *
	 * @return cantidadSexoOpuesto El valor del atributo cantidadSexoOpuesto.
	 */
	public int obtenerCantidadSexoOpuesto()
	{
		return cantidadSexoOpuesto;
	}
	
	/**
	 * Método que obtiene el valor del atributo calificacionSexoOpuesto.
	 *
	 * @return calificacionSexoOpuesto El valor del atributo
	 *                                 calificacionSexoOpuesto.
	 */
	public double obtenerCalificacionSexoOpuesto()
	{
		return calificacionSexoOpuesto;
	}
	
	/**
	 * Método que obtiene el valor del atributo mejorAguaDesove.
	 *
	 * @return mejorAguaDesove El valor del atributo mejorAguaDesove.
	 */
	public Celda obtenerMejorAguaDesove()
	{
		return mejorAguaDesove;
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadAguaDesove.
	 *
	 * @return cantidadAguaDesove El valor del atributo cantidadAguaDesove.
	 */
	public int obtenerCantidadAguaDesove()
	{
		return cantidadAguaDesove;
	}
	
	/**
	 * Método que obtiene el valor del atributo calificacionAguaDesove.
	 *
	 * @return calificacionAguaDesove El valor del atributo
	 *                                calificacionAguaDesove.
	 */
	public double obtenerCalificacionAguaDesove()
	{
		return calificacionAguaDesove;
	}
	
	/**
	 * Método que obtiene el valor del atributo mejorConviviente.
	 *
	 * @return mejorConviviente El valor del atributo mejorConviviente.
	 */
	public Celda obtenerMejorConviviente()
	{
		return mejorConviviente;
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadConviviente.
	 *
	 * @return cantidadConviviente El valor del atributo cantidadConviviente.
	 */
	public int obtenerCantidadConviviente()
	{
		return cantidadConviviente;
	}
	
	/**
	 * Método que obtiene el valor del atributo calificacionConviviente.
	 *
	 * @return calificacionConviviente El valor del atributo
	 *                                 calificacionConviviente.
	 */
	public double obtenerCalificacionConviviente()
	{
		return calificacionConviviente;
	}
	
	/**
	 * Método que obtiene el valor del atributo agua.
	 *
	 * @return agua El valor del atributo agua.
	 */
	public VectorCelda obtenerAgua()
	{
		return agua;
	}
	
	/**
	 * Método que entrega un string con todos los atributos de la clase.
	 *
	 * @return string Los atributos de la clase compuestos como un string.
	 */
	public String toString()
	{
		String texto = "";
		
		texto+= "tipo="+tipo+" ";
		texto+= "radio="+radio+" ";
		texto+= "velocidad="+velocidad+" ";
		texto+= "cantidadVecino="+cantidadVecino+"\n";
		texto+= "mejorPredador="+mejorPredador+" ";
		texto+= "cantidadPredador="+cantidadPredador+" ";
		texto+= "calificacionPredador="+calificacionPredador+"\n";
		texto+= "mejorPresa="+mejorPresa+" ";
		texto+= "cantidadPresa="+cantidadPresa+" ";
		texto+= "calificacionPresa="+calificacionPresa+"\n";
		texto+= "mejorSexoOpuesto="+mejorSexoOpuesto+" ";
		texto+= "cantidadSexoOpuesto="+cantidadSexoOpuesto+" ";
		texto+= "calificacionSexoOpuesto="+calificacionSexoOpuesto+"\n";
		texto+= "mejorAguaDesove="+mejorAguaDesove+" ";
		texto+= "cantidadAguaDesove="+cantidadAguaDesove+" ";
		texto+= "calificacionAguaDesove="+calificacionAguaDesove+"\n";
		texto+= "mejorConviviente="+mejorConviviente+" ";
		texto+= "cantidadConviviente="+cantidadConviviente+" ";
		texto+= "calificacionConviviente="+calificacionConviviente+"\n";
		
		return texto;
	}
}