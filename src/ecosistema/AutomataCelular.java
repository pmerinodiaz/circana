/**
 * @(#)AutomataCelular.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa al sistema dinámico discreto que se genera en el
 * ecosistema marino de estudio. Este autómata celular es considerado como un
 * sistema de tiempo y espacio discreto, que cumple con la dinámica de un
 * ecosistema marino cuasi-genérico. El espacio es particionado en elementos
 * discretos llamados celdas y el tiempo progresa en pasos discretos. En cada
 * celda del espacio se puede ubicar un super-individuo, el cual se encuentra en
 * cierto estado y tiene ciertas características. La dinámica generada en el
 * tiempo se basa en que en cada paso de tiempo, el estado y ubicación actual de
 * cada super-individuo es considerado como una función de los anteriores
 * estados de los super-individuos vecinos de este.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Tiempo
 * @see Espacio
 * @see VectorCelda
 * @see Estadistica
 * @see VectorOrigenDestino
 * @see Recurso
 * @see TransformacionModelo
 * @see Servicio
 * @see SuperIndividuo
 * @see Celda
 */
public class AutomataCelular
{
	/** El tiempo discreto de la dinámica. */
	private Tiempo tiempo;
	
	/** El espacio discreto de la dinámica. */
	private Espacio espacio;
	
	/**
	 * El vector de rastreo que contiene todas las celdas de ubicación de los
	 * super-individuos de la dinámica en el tiempo actual t.
	 */
	private VectorCelda rastreoActual;
	
	/**
	 * El vector de rastreo que contiene todas las celdas de ubicación de los
	 * super-individuos de la dinámica en el tiempo siguiente t+1.
	 */
	private VectorCelda rastreoSiguiente;
	
	/** La estadística de los super-individuos de la dinámica en el tiempo. */
	private Estadistica estadistica;
	
	/**
	 * El vector de celdas de orígenes y destinos de todos los super-individuos
	 * que participan en las competencias para realizar las acciones con otros
	 * super-individuos ubicados en otras celdas del espacio, moverse a otras
	 * celdas del espacio o bien desovar su parentela en otras celdas del
	 * espacio.
	 */
	private VectorOrigenDestino vectorOrigenDestino;
	
	/**
	 * Método constructor en donde se inician todos los componentes del autómata
	 * celular con los valores por defecto. Se les asigna espacio en memoria a
	 * todos los atributos de esta clase, excepto al atributo tiempo, que
	 * solamente es referenciado.
	 *
	 * @param tiempo El tiempo discreto de la dinámica.
	 */
	public AutomataCelular(Tiempo tiempo)
	{
		// Inicializar el tiempo.
		this.tiempo = tiempo;
		
		// Crear los objetos de la clase.
		espacio = new Espacio();
		rastreoActual = new VectorCelda();
		rastreoSiguiente = new VectorCelda();
		estadistica = new Estadistica();
		vectorOrigenDestino = new VectorOrigenDestino();
	}
	
	/**
	 * Método en donde se limpian todos los atributos que tiene la clase. Es
	 * decir, se eliminan todos los objetos que contiene el espacio, el vector
	 * de rastreo actual, el vector de rastreo siguiente, la estadística y el
	 * vector de orígenes y destinos. Este método permite dejar al autómata
	 * celular en su estado inicial.
	 */
	public void limpiar()
	{
		// Limpiar los objetos de la clase.
		espacio.limpiar();
		rastreoActual.removeAllElements();
		rastreoSiguiente.removeAllElements();
		estadistica.limpiar(tiempo);
		vectorOrigenDestino.removeAllElements();
	}
	
	/**
	 * Método en donde se cargan desde la base de datos todos los
	 * super-individuos que participan en la dinámica. Inicialmente se crea a
	 * cada super-individuo con las características fisiológicas y morfológicas
	 * reales. Estas características se obtienen desde la base de datos y la
	 * situación actual del entorno. Luego, se agrega a cada super-individuo al
	 * espacio contenedor de super-individuos y al vector de rastreo actual.
	 */
	public void cargar()
	{
		// Variables temporales.
		int cantidadRecursos;
		int cantidadCaladeros;
		long abundancia;
		long abundanciaHembras;
		long abundanciaHembrasSH;
		long acumuladoHembras;
		long acumuladoHembrasSH;
		long cantidadSuperIndividuos;
		int sexo;
		int estado;
		double talla;
		double peso;
		long cantidad;
		double latitudInicial, latitudFinal;
		double altitudInicial, altitudFinal;
		double areaCaladero;
		double densidadCaladero;
		
		// Obtener la cantidad de recursos.
		cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Obtener la transformación de modelos usada.
		TransformacionModelo transformacion = espacio.obtenerTransformacion();
		
		// Calcular el área superficial de una celda (kilómetros^2).
		double areaCelda = Servicio.obtenerArea(1, transformacion);
		
		// Ciclo para recorrer todos los recursos.
		for (int recurso=0; recurso<cantidadRecursos; recurso++)
		{
			// Obtener la cantidad de caladeros del recurso.
			cantidadCaladeros = Recurso.obtenerCantidadCaladeros(recurso);
			
			// Cuando hay caladeros del recurso.
			if (cantidadCaladeros > 0)
			{
				// Cargar los caladeros del recurso.
				Recurso.cargarCaladeros(recurso);
				
				// Calcular la abundancia del recurso (unidades).
				abundancia = Recurso.obtenerAbundancia(recurso);
				
				// Calcular la abundancia de hembras del recurso (unidades).
				abundanciaHembras =
					(long) (Recurso.obtenerPorcentajeHembras(recurso) *
							abundancia);
				
				// Calcular la abundancia de hembras sin huevos del recurso
				// (unidades).
				abundanciaHembrasSH =
					(long) (Recurso.obtenerPorcentajeHembrasSH(recurso) *
							abundanciaHembras);
				
				// Obtener los límites del recurso en altitud.
				altitudInicial = Recurso.obtenerAltitudInicial(recurso);
				altitudFinal = Recurso.obtenerAltitudFinal(recurso);
				
				// Ciclo para recorrer todos los caladeros.
				acumuladoHembras = 0;
				acumuladoHembrasSH = 0;
				for (int caladero=0; caladero<cantidadCaladeros; caladero++)
				{
					// Obtener los límites del caladero en latitud.
					latitudInicial =
						Recurso.obtenerLatitudInicialCaladero(caladero);
					latitudFinal =
						Recurso.obtenerLatitudFinalCaladero(caladero);
					
					// Obtener el área del caladero.
					areaCaladero = Recurso.obtenerAreaCaladero(caladero);
					
					// Calcular la cantidad de super-individuos del caladero.
					cantidadSuperIndividuos = (long) (areaCaladero / areaCelda);
					
					// Calcular la densidad del caladero.
					densidadCaladero =
						Recurso.obtenerAbundanciaCaladero(caladero) /
						areaCaladero;
					
					// Calcular la cantidad de individuos que contiene un
					// super-individuo del caladero.
					cantidad = (long) (densidadCaladero * areaCelda);
					
					// Ciclo para recorrer todos los super-individuos.
					for (int superindividuo=0;
						 superindividuo<cantidadSuperIndividuos;
						 superindividuo++)
					{
						// Cuando hay que crear hembras.
						if (acumuladoHembras < abundanciaHembras)
						{
							// Cuando hay que crear hembras sin huevos.
							if (acumuladoHembrasSH < abundanciaHembrasSH)
							{
								// Establecer los valores para el sexo, estado,
								// talla y peso, considerando que el
								// super-individuo es hembra sin huevos.
								sexo = SuperIndividuo.HEMBRA;
								estado = SuperIndividuo.SIN_HUEVOS;
								talla = Recurso.obtenerTallaHembrasSH(recurso);
								peso = Recurso.obtenerPesoHembrasSH(recurso);
								
								// Incrementar la cantidad de hembras sin
								// huevos.
								acumuladoHembrasSH+= cantidad;
							}
							
							// Cuando hay que crear hembras con huevos.
							else
							{
								// Establecer los valores para el sexo, estado,
								// talla y peso, considerando que el
								// super-individuo es hembra con huevos.
								sexo = SuperIndividuo.HEMBRA;
								estado = SuperIndividuo.CON_HUEVOS;
								talla = Recurso.obtenerTallaHembrasCH(recurso);
								peso = Recurso.obtenerPesoHembrasCH(recurso);
							}
							
							// Incrementar la cantidad de hembras.
							acumuladoHembras+= cantidad;
						}
						
						// Cuando hay que crear machos.
						else
						{
							// Establecer los valores para el sexo, estado,
							// talla y peso, considerando que el
							// super-individuo es macho.
							sexo = SuperIndividuo.MACHO;
							estado = SuperIndividuo.SIN_HUEVOS;
							talla = Recurso.obtenerTallaMachos(recurso);
							peso = Recurso.obtenerPesoMachos(recurso);
						}
						
						// Cargar el super-individuo.
						cargarSuperIndividuo(recurso, sexo, estado,
											 talla, peso, cantidad,
											 latitudInicial, latitudFinal,
											 altitudInicial, altitudFinal);
					}
				}
			}
		}
	}
	
	/**
	 * Método que crea un super-individuo y lo carga en el espacio. El
	 * super-individuo es creado con las características del caladero en donde
	 * vive. Luego el super-individuo se carga en el espacio contenedor de
	 * super-individuos y finalmente es agregado al vector de rastreo actual.
	 *
	 * @param recurso El tipo de recurso del super-individuo.
	 * @param sexo El tipo de sexo del super-individuo.
	 * @param estado El tipo de estado del super-individuo.
	 * @param talla La talla del super-individuo.
	 * @param peso El peso del super-individuo.
	 * @param cantidad La cantidad de individuos que contiene el
	 *				   super-individuo.
	 * @param latitudInicial La latitud inicial del caladero.
	 * @param latitudFinal La latitud final del caladero.
	 * @param altitudInicial La altitud inicial del caladero.
	 * @param altitudFinal La altitud final del caladero.
	 */
	private void cargarSuperIndividuo(int recurso, int sexo, int estado,
								double talla, double peso, long cantidad,
								double latitudInicial, double latitudFinal,
								double altitudInicial, double altitudFinal)
	{
		SuperIndividuo superIndividuo = null;
		
		// Dependiendo del tipo de recurso, creamos el objeto.
		switch (recurso)
		{
			// Cuando es Camarón Nailon.
			case Recurso.CAMARON_NAILON:
			superIndividuo =
				new SuperCamaronNailon(sexo, estado, talla, peso, cantidad,
									   espacio);
			break;
			
			// Cuando es Langostino Amarillo.
			case Recurso.LANGOSTINO_AMARILLO:
			superIndividuo =
				new SuperLangostinoAmarillo(sexo, estado, talla, peso, cantidad,
											espacio);
			break;
			
			// Cuando es Langostino Colorado.
			case Recurso.LANGOSTINO_COLORADO:
			superIndividuo =
				new SuperLangostinoColorado(sexo, estado, talla, peso, cantidad,
											espacio);
			break;
			
			// Cuando es Merluza Común.
			case Recurso.MERLUZA_COMUN:
			superIndividuo =
				new SuperMerluzaComun(sexo, estado, talla, peso, cantidad,
									  espacio);
			break;
		}
		
		// Cargar el super-individuo en el espacio.
		cargarEspacio(superIndividuo,
					  latitudInicial, latitudFinal,
					  altitudInicial, altitudFinal);
	}
	
	/**
	 * Método que permite cargar un super-individuo al espacio contenedor de
	 * super-individuos. En este método se busca la celda de ubicación ideal
	 * para contener al super-individuo, dependiendo de los rangos de latitud
	 * recibidos por parámetros en donde habita el recurso. Luego el
	 * super-individuo es cargado en el espacio contenedor de super-individuos.
	 * Finalmente, el super-individuo es agregado al vector de rastreo actual.
	 *
	 * @param superIndividuo El super-individuo al cual se le asigna la celda de
	 *                       ubicación en el espacio contenedor de
	 *                       super-individuos.
	 * @param latitudInicial La latitud inicial del caladero.
	 * @param latitudFinal La latitud final del caladero.
	 * @param altitudInicial La altitud inicial del caladero.
	 * @param altitudFinal La altitud final del caladero.
	 */
	private void cargarEspacio(SuperIndividuo superIndividuo,
							   double latitudInicial, double latitudFinal,
							   double altitudInicial, double altitudFinal)
	{
		// Variables temporales.
		double longitud;
		double latitud;
		double altitud;
		int i;
		int j;
		int k;
		
		// Obtener la transformación de modelo usada.
		TransformacionModelo transformacion = espacio.obtenerTransformacion();
		
		// Crear una coordenada temporal.
		Coordenada coordenada = new Coordenada();
		
		// Ciclo para buscar una ubicación aleatoria del super-individuo en el
		// espacio.
		do
		{
			// Obtener valores aleatorios.
			latitud = Servicio.obtenerAzar(latitudInicial, latitudFinal);
			altitud = Servicio.obtenerAzar(altitudInicial, altitudFinal);
			longitud = Mapa.obtenerLongitud(latitud, altitud);
			
			// Transformar valores aleatorios a coordenadas de celda.
			i = transformacion.obtenerI(longitud);
			j = transformacion.obtenerJ(latitud);
			k = transformacion.obtenerK(altitud);
			
			// Cambiar la coordenada.
			coordenada.establecerLongitud(longitud);
			coordenada.establecerLatitud(latitud);
			coordenada.establecerAltitud(altitud);
		}
		while ((espacio.buscarGrilla(i, j, k) != null) ||
			   (!Mapa.esAgua(coordenada)));
		
		// Crear una celda para la ubicación del super-individuo.
		Celda ubicacion = new Celda(i, j, k, espacio);
		
		// Establecer la ubicación del super-individuo.
		superIndividuo.establecerUbicacion(ubicacion);
		
		// Insertar el super-individuo al espacio.
		espacio.insertarGrilla(superIndividuo);
		
		// Agregar la ubicación del super-individuo al vector de rastreo actual.
		rastreoActual.addElement(ubicacion);
	}
	
	/**
	 * Método que genera una evolución en el autómata celular. Esta evolución
	 * corresponde al avance del tiempo en la dinámica del autómata celular, el
	 * cual le afecta a cada super-individuo. La evolución del autómata celular
	 * se divide en cuatro partes: primero se determina para cada
	 * super-individuo lo que éste intenta hacer, luego se genera la
	 * planificación de la extracción, en seguida se realiza para cada
	 * super-individuo su acción y finalmente se intercambian los vectores de
	 * rastreo.
	 */
	public void evolucionar()
	{
		// Generar las intenciones de los super-individuos.
		generarIntenciones();
		
		// Generar las cuotas de captura y los caladeros de captura de todos los
		// recursos.
		estadistica.planificarExtraccion(tiempo, espacio);
		
		// Generar las acciones de los super-individuos.
		generarAcciones();
		
		// Intercambiar los vectores de rastreo.
		intercambiarRastreos();
	}
	
	/**
	 * Método que genera las intenciones de los super-individuos del autómata
	 * celular. Para determinar lo que un super-individuo vivo intenta hacer se
	 * divide el proceso en tres partes: el super-individuo cree, desea e
	 * intenta. Estos procesos son llevados a cabo gracias a la arquitectura
	 * interna que tienen los super-individuos, la cual es híbrida entre
	 * reactiva y BDI (Creencias - Deseos - Intenciones). Luego, se le asigna
	 * una celda de destino a cada super-individuo. Finalmente, se agrega el
	 * super-individuo al vector de orígenes y destinos. Los super-individuos
	 * muertos no creen, ni desean, ni intentan cosa alguna, pero si se les
	 * asigna un destino y una participación en la dinámica. En este método se
	 * incorpora a cada super-individuo a la estadística.
	 */
	private void generarIntenciones()
	{
		// Variables temporales.
		Celda ubicacion;
		SuperIndividuo superIndividuo;
		int estado;
		
		// Obtener la cantidad de super-individuos de la simulación.
		int tamanio = rastreoActual.size();
		
		// Ciclo en donde recorremos el vector de rastreo actual.
		for (int i=0; i<tamanio; i++)
		{
			// Obtener la celda de ubicación del vector de rastreo.
			ubicacion = rastreoActual.obtenerElemento(i);
			
			// Obtener el super-individuo que tiene la ubicación.
			superIndividuo = espacio.buscarGrilla(ubicacion);
			
			// Cuando el super-individuo existe.
			if (superIndividuo != null)
			{
				// Obtener el tipo de estado del super-individuo.
				estado = superIndividuo.obtenerEstado();
				
				// Cuando el super-individuo no está desaparecido.
				if (estado != SuperIndividuo.DESAPARECIDO)
				{
					// Cuando el super-individuo está sin huevos o con huevos.
					if (estado == SuperIndividuo.SIN_HUEVOS ||
						estado == SuperIndividuo.CON_HUEVOS)
					{
						// El super-individuo cree.
						superIndividuo.creer(espacio);
						
						// El super-individuo desea.
						superIndividuo.desear(tiempo, espacio);
						
						// El super-individuo intenta.
						superIndividuo.intentar();
					}
					
					// Asignar un destino al super-individuo.
					asignarDestino(superIndividuo, 0);
					
					// Agregar el super-individuo al vector de orígenes y
					// destinos.
					agregarOrigenDestino(superIndividuo);
					
					// Incorporar el super-individuo a las estadísticas.
					estadistica.incorporar(superIndividuo, tiempo, espacio);
				}
			}
		}
	}
	
	/**
	 * Método que, dependiendo de la intención del super-individuo, llama a los
	 * métodos en donde se le asigna una celda de destino al super-individuo.
	 * Estos métodos asignan un valor a la celda de destino del super-individuo.
	 * En este método se realiza un ciclo para revisar todas las intenciones del
	 * super-individuo. Cuando la intención se ve que es provechosa para el
	 * super-individuo, entonces se le asigna el destino correspondiente a esa
	 * intención. En caso contrario, se continúa con la iteración hasta llegar
	 * al final de las intenciones.
	 *
	 * @param superIndividuo El super-individuo al cual se le asigna la celda de
	 *                       destino.
	 * @param inicial El valor de la intención inicial desde donde se comienza a
	 *                revisar las intenciones.
	 */
	private void asignarDestino(SuperIndividuo superIndividuo, int inicial)
	{
		// Variables locales.
		int intencion;
		
		// Obtener la percepción del super-individuo.
		Vecindad percepcion = superIndividuo.obtenerPercepcion();
		
		// Ciclo para recorrer las intenciones del super-individuo.
		for (int i=inicial; i<=7; i++)
		{
			// Obtener la intención i-ésima del super-individuo.
			intencion = superIndividuo.obtenerIntencion(i);
			
			// Cuando intenta arrancar y el mejor predador existe.
			if ((intencion == SuperIndividuo.ARRANCAR) &&
				(percepcion.obtenerMejorPredador() != null))
			{
				// Asignar el destino de arrancar y salir del ciclo.
				asignarDestinoArrancar(superIndividuo);
				break;
			}
			
			// Cuando intenta predar y la mejor presa existe.
			else
			if ((intencion == SuperIndividuo.PREDAR) &&
				(percepcion.obtenerMejorPresa() != null))
			{
				// Asignar el destino de predar y salir del ciclo.
				asignarDestinoPredar(superIndividuo);
				break;
			}
			
			// Cuando intenta reproducir y el mejor sexo opuesto existe.
			else
			if ((intencion == SuperIndividuo.REPRODUCIR) &&
				(percepcion.obtenerMejorSexoOpuesto() != null))
			{
				// Asignar el destino de reproducir y salir del ciclo.
				asignarDestinoReproducir(superIndividuo);
				break;
			}
			
			// Cuando intenta desovar y la mejor agua de desove existe.
			else
			if ((intencion == SuperIndividuo.DESOVAR) &&
				(percepcion.obtenerMejorAguaDesove() != null))
			{
				// Asignar el destino de desovar y salir del ciclo.
				asignarDestinoDesovar(superIndividuo);
				break;
			}
			
			// Cuando intenta convivir y el mejor conviviente existe.
			else
			if ((intencion == SuperIndividuo.CONVIVIR) &&
				(percepcion.obtenerMejorConviviente() != null))
			{
				// Asignar el destino de convivir y salir del ciclo.
				asignarDestinoConvivir(superIndividuo);
				break;
			}
			
			// Cuando intenta migrar y la mejor celda a migrar existe.
			else
			if ((intencion == SuperIndividuo.MIGRAR) &&
				(superIndividuo.obtenerCeldaMigrar(espacio) != null))
			{
				// Asignar el destino de migrar y salir del ciclo.
				asignarDestinoMigrar(superIndividuo);
				break;
			}
			
			// Cuando intenta divagar y la mejor celda a divagar existe.
			else
			if ((intencion == SuperIndividuo.DIVAGAR) &&
				(superIndividuo.obtenerCeldaDivagar(espacio) != null))
			{
				// Asignar el destino de divagar y salir del ciclo.
				asignarDestinoDivagar(superIndividuo);
				break;
			}
			
			// Cuando intenta nada y la mejor celda de ubicación existe.
			else
			if ((intencion == SuperIndividuo.NADA) &&
				(superIndividuo.obtenerUbicacion() != null))
			{
				// Asignar el destino de nada y salir del ciclo.
				asignarDestinoNada(superIndividuo);
				break;
			}
		}
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * arrancar. Se obtiene la ubicación del mejor predador del super-individuo,
	 * y al super-individuo se le asigna un destino lo más lejano posible de
	 * donde se encuentra ubicado el mejor predador. Finalmente, se le asigna el
	 * objetivo del super-individuo como el mejor predador.
	 *
	 * @param superIndividuo El super-individuo que intenta arrancar.
	 */
	private void asignarDestinoArrancar(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del mejor predador.
		Celda mejorPredador = superIndividuo.obtenerPercepcion().
							  obtenerMejorPredador();
		
		// Cuando el mejor predador existe.
		if (mejorPredador != null)
			asignarDestinoLejano(superIndividuo, mejorPredador, false);
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.ARRANCAR);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorPredador);
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * predar. Se obtiene la ubicación de la mejor presa del super-individuo, y
	 * al super-individuo se le asigna un destino lo más cercano posible de
	 * donde se encuentra ubicada la mejor presa. Finalmente, se le asigna el
	 * objetivo del super-individuo como la mejor presa.
	 *
	 * @param superIndividuo El super-individuo que intenta predar.
	 */
	private void asignarDestinoPredar(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación de la mejor presa.
		Celda mejorPresa = superIndividuo.obtenerPercepcion().
		                   obtenerMejorPresa();
		
		// Cuando la mejor presa existe.
		if (mejorPresa != null)
		{
			// Obtener la coordenada de la ubicación de la mejor presa.
			Coordenada coordenada = Servicio.transformarCeldaCoordenada(
									mejorPresa,espacio.obtenerTransformacion());
			
			// Cuando la coordenada es agua.
			if (Mapa.esAgua(coordenada))
				asignarDestinoCercano(superIndividuo, mejorPresa, true);
			
			// Cuando la coordenada es tierra.
			if (Mapa.esTierra(coordenada))
				asignarDestinoCercano(superIndividuo, mejorPresa, false);
		}
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.PREDAR);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorPresa);
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * reproducirse. Se obtiene la ubicación del mejor sexo opuesto para el
	 * super-individuo, y al super-individuo se le asigna un destino lo más
	 * cercano posible de donde se encuentra ubicado el mejor sexo opuesto.
	 * Finalmente, se le asigna el objetivo del super-individuo como el mejor
	 * sexo opuesto.
	 *
	 * @param superIndividuo El super-individuo que intenta reproducirse.
	 */
	private void asignarDestinoReproducir(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del mejor sexo opuesto.
		Celda mejorSexoOpuesto = superIndividuo.obtenerPercepcion().
								 obtenerMejorSexoOpuesto();
		
		// Cuando el mejor sexo opuesto existe.
		if (mejorSexoOpuesto != null)
			asignarDestinoCercano(superIndividuo, mejorSexoOpuesto, false);
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.REPRODUCIR);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorSexoOpuesto);
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * desovar. Se obtiene la ubicación de la mejor agua para desovar para el
	 * super-individuo y al super-individuo se le asigna un destino lo más
	 * cercano posible de donde se encuentra ubicada la mejor agua para desovar.
	 * Finalmente, se le asigna el objetivo del super-individuo como la mejor
	 * agua para desovar.
	 *
	 * @param superIndividuo El super-individuo que intenta desovar.
	 */
	private void asignarDestinoDesovar(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación de la mejor agua para desovar.
		Celda mejorAguaDesove = superIndividuo.obtenerPercepcion().
								obtenerMejorAguaDesove();
		
		// Cuando la mejor agua para desovar existe.
		if (mejorAguaDesove != null)
			asignarDestinoCercano(superIndividuo, mejorAguaDesove, true);
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.DESOVAR);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorAguaDesove);
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * convivir. Se obtiene la ubicación del mejor conviviente de un
	 * super-individuo y se le asigna un destino lo más lejano, o lo más
	 * cercano posible de donde se encuentra ubicado el mejor conviviente,
	 * dependiendo de su tipo de conducta grupal o solitaria. Finalmente, se
	 * le asigna el objetivo del super-individuo como el mejor conviviente.
	 *
	 * @param superIndividuo El super-individuo que intenta convivir.
	 */
	private void asignarDestinoConvivir(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del mejor conviviente.
		Celda mejorConviviente = superIndividuo.obtenerPercepcion().
								 obtenerMejorConviviente();
		
		// Cuando el mejor conviviente existe.
		if (mejorConviviente != null)
		{
			// Dependiendo del tipo de conducta, se asigna una celda de destino
			// lejano o cercano.
			switch (Recurso.obtenerConducta(superIndividuo.obtenerRecurso()))
			{
				// Cuando la conducta es Solitaria.
				case Conducta.SOLITARIA:
				asignarDestinoLejano(superIndividuo, mejorConviviente, false);
				break;
				
				// Cuando la conducta es Grupal.
				case Conducta.GRUPAL:
				asignarDestinoCercano(superIndividuo, mejorConviviente, false);
				break;
			}
		}
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.CONVIVIR);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorConviviente);
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * migrar. Se obtiene la ubicación de la mejor celda a migrar para el
	 * super-individuo, y se le asigna un destino lo más cercano posible de
	 * donde se encuentra ubicada la mejor celda a migrar. Finalmente, se le
	 * asigna el objetivo del super-individuo como la mejor celda a migrar.
	 *
	 * @param superIndividuo El super-individuo que intenta migrar.
	 */
	private void asignarDestinoMigrar(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación de la mejor celda a migrar.
		Celda mejorMigrar = superIndividuo.obtenerCeldaMigrar(espacio);
		
		// Cuando la mejor celda a migrar existe.
		if (mejorMigrar != null)
		{
			// Cuando la mejor celda a migrar es válida y en la mejor celda a
			// migrar no hay un super-individuo.
			if ((mejorMigrar.obtenerTipo() == Celda.VALIDA) &&
				(espacio.buscarGrilla(mejorMigrar) == null))
				asignarDestinoCercano(superIndividuo, mejorMigrar, true);
			
			// Cuando la mejor celda a migrar no es válida o en la mejor celda
			// a migrar hay un super-individuo.
			else asignarDestinoCercano(superIndividuo, mejorMigrar, false);
		}
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.MIGRAR);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorMigrar);
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * divagar. Se obtiene la ubicación de la mejor celda a divagar para el
	 * super-individuo, y se le asigna un destino lo más cercano posible de
	 * donde se encuentra ubicada la mejor celda a divagar. Finalmente, se le
	 * asigna el objetivo del super-individuo como la mejor celda a divagar.
	 *
	 * @param superIndividuo El super-individuo que intenta divagar.
	 */
	private void asignarDestinoDivagar(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación de la mejor celda a divagar.
		Celda mejorDivagar = superIndividuo.obtenerCeldaDivagar(espacio);
		
		// Cuando la mejor celda a divagar existe.
		if (mejorDivagar != null)
		{
			// Cuando la mejor celda a divagar es válida y en la mejor celda a
			// divagar no hay un super-individuo.
			if ((mejorDivagar.obtenerTipo() == Celda.VALIDA) &&
				(espacio.buscarGrilla(mejorDivagar) == null))
				asignarDestinoCercano(superIndividuo, mejorDivagar, true);
			
			// Cuando la mejor celda a divagar no es válida o en la mejor celda
			// a divagar hay un super-individuo.
			else asignarDestinoCercano(superIndividuo, mejorDivagar, false);
		}
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.DIVAGAR);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorDivagar);
	}
	
	/**
	 * Método en donde se asigna un destino al super-individuo que intenta
	 * hacer nada. Se obtiene la mejor ubicación del super-individuo y se le
	 * asigna un destino lo más cercano posible de donde se encuentra ubicado.
	 * Finalmente, se le asigna el objetivo del super-individuo como la mejor
	 * ubicación del super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta nada.
	 */
	private void asignarDestinoNada(SuperIndividuo superIndividuo)
	{
		// Obtener la mejor ubicación.
		Celda mejorUbicacion = superIndividuo.obtenerUbicacion();
		
		// Cuando la mejor ubicacion existe.
		if (mejorUbicacion != null)
			asignarDestinoCercano(superIndividuo, mejorUbicacion, true);
		
		// Establecer la acción super-individuo.
		superIndividuo.establecerAccion(SuperIndividuo.NADA);
		
		// Establecer el objetivo del super-individuo.
		superIndividuo.establecerObjetivo(mejorUbicacion);
	}
	
	/**
	 * Método que asigna al super-individuo una celda de destino lo más lejana
	 * posible de una celda destino recibida por parámetros. Si la concurrencia
	 * espacial es permitida, el destino es válido y el super-individuo puede
	 * alcanzar el destino, entonces se le asigna como destino el mismo valor de
	 * destino recibido como parámetro. En caso contrario, se busca una celda
	 * vacía alcanzable lo más lejana posible de la celda de destino y luego se
	 * le asigna ésta como destino al super-individuo.
	 *
	 * @param superIndividuo El super-individuo al cual se le asigna el destino.
	 * @param destino La celda de destino para el super-individuo.
	 * @param concurrenciaEspacial La aceptación o no de la concurrencia
	 *                             espacial con el destino.
	 */
	private void asignarDestinoLejano(SuperIndividuo superIndividuo,
									  Celda destino,
									  boolean concurrenciaEspacial)
	{
		// Cuando la concurrencia espacial es permitida, el destino es una celda
		// válida y el destino es alcanzable.
		if ((concurrenciaEspacial) &&
			(destino.obtenerTipo() == Celda.VALIDA) &&
			(superIndividuo.esAlcanzable(destino)))
			superIndividuo.establecerDestino(destino);
		
		// Cuando la concurrencia espacial no es permitida o el destino es
		// una celda no válida o el destino no es alcanzable.
		else
		{
			// Obtener las aguas alcanzables.
			VectorCelda aguasAlcanzables = superIndividuo.obtenerPercepcion().
										   obtenerAgua();
			
			// Cuando hay aguas alcanzables.
			if ((aguasAlcanzables != null) && (aguasAlcanzables.size() > 0))
			{
				// Establecer la distancia mayor en la ubicación actual.
				Celda celda = superIndividuo.obtenerUbicacion();
				double distanciaMayor = Servicio.obtenerDistancia(celda,
				                                                  destino);
				superIndividuo.establecerDestino(celda);
				
				// Ciclo para recorrer las aguas alcanzables.
				int tamanio = aguasAlcanzables.size();
				for (int i=0; i<tamanio; i++)
				{
					// Obtener la celda de agua alcanzable.
					celda = aguasAlcanzables.obtenerElemento(i);
					
					// Cuando la distancia es mayor.
					if (Servicio.obtenerDistancia(celda, destino) >
						distanciaMayor)
						superIndividuo.establecerDestino(celda);
				}
			}
			
			// Cuando no hay aguas alcanzables.
			else superIndividuo.establecerDestino(
											superIndividuo.obtenerUbicacion());
		}
	}
	
	/**
	 * Método que asigna al super-individuo una celda de destino lo más cercana
	 * posible a una celda de destino. Si la concurrencia espacial es permitida,
	 * el destino es válido y el super-individuo puede alcanzar el destino,
	 * entonces se puede asignar como destino el mismo valor del destino
	 * recibido como parámetros. En caso contrario, se busca una celda vacía
	 * alcanzable lo más cercana posible y luego se le asigna ésta como destino
	 * al super-individuo.
	 *
	 * @param superIndividuo El super-individuo al cual se le asigna el destino.
	 * @param destino La celda de destino para el super-individuo.
	 * @param concurrenciaEspacial La aceptación o no de la concurrencia
	 *                             espacial con el destino.
	 */
	private void asignarDestinoCercano(SuperIndividuo superIndividuo,
									   Celda destino,
									   boolean concurrenciaEspacial)
	{
		// Cuando la concurrencia espacial es permitida, el destino es una celda
		// válida y el destino es alcanzable.
		if ((concurrenciaEspacial) &&
			(destino.obtenerTipo() == Celda.VALIDA) &&
			(superIndividuo.esAlcanzable(destino)))
			superIndividuo.establecerDestino(destino);
		
		// Cuando la concurrencia espacial no es permitida o el destino es
		// una celda no válida o el destino no es alcanzable.
		else
		{
			// Obtener las aguas alcanzables.
			VectorCelda aguasAlcanzables = superIndividuo.obtenerPercepcion().
										   obtenerAgua();
			
			// Cuando hay aguas alcanzables.
			if ((aguasAlcanzables != null) && (aguasAlcanzables.size() > 0))
			{
				// Establecer la distancia menor en la ubicación actual.
				Celda celda = superIndividuo.obtenerUbicacion();
				double distanciaMenor = Servicio.obtenerDistancia(celda,
				                                                  destino);
				superIndividuo.establecerDestino(celda);
				
				// Ciclo para recorrer las aguas alcanzables.
				int tamanio = aguasAlcanzables.size();
				for (int i=0; i<tamanio; i++)
				{
					// Obtener la celda de agua alcanzable.
					celda = aguasAlcanzables.obtenerElemento(i);
					
					// Cuando la distancia es menor.
					if (Servicio.obtenerDistancia(celda, destino) <
						distanciaMenor)
						superIndividuo.establecerDestino(celda);
				}
			}
			
			// Cuando no hay aguas alcanzables.
			else superIndividuo.establecerDestino(
											superIndividuo.obtenerUbicacion());
		}
	}
	
	/**
	 * Método que agrega un super-individuo al vector de celdas de orígenes y
	 * destinos del autómata celular. El procedimiento es el siguiente: Primero
	 * se busca en el vector de orígenes y destino el nodo que contenga el
	 * destino del super-individuo A. En caso de no encontrarse, se inserta el
	 * origen y destino del super-individuo A al final del vector de orígenes y
	 * destinos. En caso contrario, se obtiene al super-individuo que ya tenía
	 * el mismo destino. Ahora, se compara cual de los dos super-individuos
	 * tiene mayor aptitud. El que tiene mayor aptitud mantiene su destino y
	 * ubicación en el vector de orígenes y destinos. En cambio, el que tiene
	 * menor aptitud cambia su destino y ubicación en el vector de orígenes y
	 * destinos. El super-individuo perdedor realiza una búsqueda de su nuevo
	 * destino hasta que encuentra uno disponible.
	 *
	 * @param superIndividuoA El superIndividuo que se agrega en el vector de
	 *                        las celdas orígenes y destinos.
	 */
	private void agregarOrigenDestino(SuperIndividuo superIndividuoA)
	{
		// Variables locales.
		SuperIndividuo superIndividuoPerdedor;
		Celda origen;
		Celda destino;
		int intencion = 0;
		
		// Obtener el origen y destino del super-individuo A.
		Celda origenA = superIndividuoA.obtenerUbicacion();
		Celda destinoA = superIndividuoA.obtenerDestino();
		
		// Buscar el par ordenado que contenga el destino del super-individuo A.
		OrigenDestino origenDestino =
		vectorOrigenDestino.buscarDestino(destinoA);
		
		// Cuando el destino A no se encuentra en el vector.
		if (origenDestino == null)
		{
			// Obtener el origen y el destino.
			origen = origenA;
			destino = destinoA;
		}
		
		// Cuando el destino A si se encuentra en el vector.
		else
		{
			// Obtener el super-individuo que tiene el mismo destino.
			SuperIndividuo superIndividuoB =
			espacio.buscarGrilla(origenDestino.obtenerOrigen());
			
			// Cuando la aptitud del super-individuo A es menor o igual que la
			// aptitud del super-individuo B.
			if (superIndividuoA.obtenerAptitud() <=
				superIndividuoB.obtenerAptitud())
			{
				// El perdedor es el super-individuo A.
				superIndividuoPerdedor = superIndividuoA;
			}
			
			// Cuando la aptitud del super-individuo A es mayor que la aptitud
			// del super-individuo B.
			else
			{
				// Cambiar el origen del par ordenado.
				origenDestino.establecerOrigen(origenA);
				
				// El perdedor es el super-individuo B.
				superIndividuoPerdedor = superIndividuoB;
			}
			
			// Ciclo para buscar el destino del super-individuo perdedor.
			do
			{
				// Asignar el destino.
				asignarDestino(superIndividuoPerdedor, intencion);
				
				// Obtener el origen y el destino.
				origen = superIndividuoPerdedor.obtenerUbicacion();
				destino = superIndividuoPerdedor.obtenerDestino();
				
				// Incrementar el destino.
				intencion++;
			}
			while ((vectorOrigenDestino.buscarDestino(destino) == null) &&
				   (intencion < 7));
		}
		
		// Cuando el destino no está ocupado.
		if (vectorOrigenDestino.buscarDestino(destino) == null)
			vectorOrigenDestino.addElement(origen, destino);
	}
	
	/**
	 * Método que genera las acciones de los super-individuos del autómata
	 * celular. Para determinar la acción que un super-individuo quiere hacer
	 * se revisa su acción y, dependiendo de estas, se realiza la acción o no.
	 * En las acciones se debe ver el tipo de interacción que ocurre con los
	 * otros super-individuos, ya que la acción va a afectar a los otros
	 * super-individuos. Se recorre el vector de orígenes y destino de
	 * super-individuos y se llama al método que asigna las acciones a cada
	 * super-individuo. Los super-individiuos que desaparecen en la próxima
	 * evolución son eliminados de la dinámica.
	 */
	private void generarAcciones()
	{
		// Variables locales.
		int estado;
		Celda ubicacion;
		OrigenDestino origenDestino;
		SuperIndividuo superIndividuo;
		
		// Obtener la cantidad de super-individuos de la simulación.
		int tamanio = vectorOrigenDestino.size();
		
		// Ciclo para recorrer el vector de orígenes y destinos.
		while (vectorOrigenDestino.size() > 0)
		{
			// Obtener en forma aleatoria un índice del vector.
			int i = Servicio.obtenerAzar(0, vectorOrigenDestino.size() - 1);
			
			// Obtener el par origen-destino.
			origenDestino = vectorOrigenDestino.obtenerElemento(i);
			
			// Obtener la celda de ubicación.
			ubicacion = origenDestino.obtenerOrigen();
			
			// Obtener el super-individuo.
			superIndividuo = espacio.buscarGrilla(ubicacion);
			
			// Cuando el super-individuo existe.
			if (superIndividuo != null)
			{
				// Cuando el super-individuo es un caladero.
				if (estadistica.esCaladero(superIndividuo, tiempo, espacio))
				{
					// Eliminar el super-individuo del espacio contenedor de
					// super-individuos.
					espacio.eliminarGrilla(superIndividuo);
				}
				
				// Cuando el super-individuo no es un caladero.
				else
				{
					// Asignar la acción al super-individuo.
					asignarAccion(superIndividuo);
					
					// Evaluar al super-individuo.
					superIndividuo.autoEvaluar(espacio);
					
					// Cuando el estado del super-individuo es desaparecido.
					if (superIndividuo.obtenerEstado() ==
						SuperIndividuo.DESAPARECIDO)
					{
						// Eliminar el super-individuo del espacio contenedor de
						// super-individuos.
						espacio.eliminarGrilla(superIndividuo);
					}
					
					// Cuando el estado del super-individuo no es desaparecido.
					else
					{
						// Agregar el super-individuo al vector de rastreo
						// siguiente.
						rastreoSiguiente.addElement(
						superIndividuo.obtenerUbicacion().clone());
					}
				}
			}
			
			// Eliminar el par ordenado origen-destino i-ésimo del vector.
			vectorOrigenDestino.remove(i);
		}
	}
	
	/**
	 * Método que, dependiendo de la acción del super-individuo, llama a los
	 * métodos en donde se asigna la acción del super-individuo. Esos métodos
	 * realizan la acción del super-individuo, considerando la interacción con
	 * los otros super-individuos de la dinámica.
	 *
	 * @param superIndividuo El super-individuo al cual se le asigna la acción.
	 */
	private void asignarAccion(SuperIndividuo superIndividuo)
	{
		// Dependiendo de la acción del super-individuo se llama a los métodos
		// que asignan la acción al super-individuo.
		switch (superIndividuo.obtenerAccion())
		{
			// Cuando intenta Arrancar.
			case SuperIndividuo.ARRANCAR:
			asignarAccionArrancar(superIndividuo);
			break;
			
			// Cuando intenta Predar.
			case SuperIndividuo.PREDAR:
			asignarAccionPredar(superIndividuo);
			break;
			
			// Cuando intenta Reproducir.
			case SuperIndividuo.REPRODUCIR:
			asignarAccionReproducir(superIndividuo);
			break;
			
			// Cuando intenta Desovar.
			case SuperIndividuo.DESOVAR:
			asignarAccionDesovar(superIndividuo);
			break;
			
			// Cuando intenta Convivir.
			case SuperIndividuo.CONVIVIR:
			asignarAccionConvivir(superIndividuo);
			break;
			
			// Cuando intenta Migrar.
			case SuperIndividuo.MIGRAR:
			asignarAccionMigrar(superIndividuo);
			break;
			
			// Cuando intenta Divagar.
			case SuperIndividuo.DIVAGAR:
			asignarAccionDivagar(superIndividuo);
			break;
			
			// Cuando intenta Nada.
			case SuperIndividuo.NADA:
			asignarAccionNada(superIndividuo);
			break;
		}
	}
	
	/**
	 * Método en donde se realiza la acción de arrancar de un super-individuo
	 * recibido por parámetros. Se obtiene el origen del super-individuo y se
	 * busca en el vector de orígenes y destino si el origen del super-individuo
	 * es destino de otro super-individuo. En caso de serlo, se hace competir al
	 * par de super-individuos y, dependiendo de quien gane, se realiza la
	 * acción de predar o mover al super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta arrancar.
	 */
	private void asignarAccionArrancar(SuperIndividuo superIndividuo)
	{
		// Obtener el origen del super-individuo.
		Celda origen = superIndividuo.obtenerUbicacion();
		
		// Buscar el origen del super-individuo como destino de otro
		// super-individuo en el vector.
		OrigenDestino origenDestino = vectorOrigenDestino.buscarDestino(origen);
		
		// Cuando el origen del super-individuo es destino de otro
		// super-individuo en el vector.
		if (origenDestino != null)
		{
			// Obtener al super-individuo X.
			SuperIndividuo superIndividuoX = espacio.buscarGrilla(
											 origenDestino.obtenerOrigen());
			
			// Cuando la aptitud del super-individuo es menor que a la aptitud
			// del super-individuo X y el super-individuo X puede alcanzar al
			// super-individuo.
			if (superIndividuoX != null &&
				superIndividuo.obtenerAptitud() <
				superIndividuoX.obtenerAptitud() &&
				superIndividuoX.esAlcanzable(origen))
				// El super-individuo es predado.
				superIndividuoX.predar(espacio, superIndividuo);
			
			// Cuando la aptitud del super-individuo es mayor o igual que la
			// aptitud del super-individuo X o el super-individuo X no puede
			// alcanzar al super-individuo.
			else
				// El super-individuo se mueve.
				superIndividuo.mover(espacio);
		}
		
		// Cuando el origen del super-individuo no es destino de otro
		// super-individuo en el vector.
		else
			// El super-individuo se mueve.
			superIndividuo.mover(espacio);
	}
	
	/**
	 * Método en donde se realiza la acción de predar de un super-individuo
	 * recibido por parámetros. Se obtiene el destino del super-individuo y se
	 * busca en el vector de orígenes y destino si el destino del
	 * super-individuo es origen de otro super-individuo. En caso de serlo, se
	 * hace competir al par de super-individuos y, dependiendo de quien gane, se
	 * realiza la acción de fijar o predar del super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta predar.
	 */
	private void asignarAccionPredar(SuperIndividuo superIndividuo)
	{
		// Obtener el destino del super-individuo.
		Celda destino = superIndividuo.obtenerDestino();
		
		// Buscar el destino del super-individuo como origen de otro
		// super-individuo en el vector.
		OrigenDestino origenDestino = vectorOrigenDestino.buscarOrigen(destino);
		
		// Cuando el destino del super-individuo es origen de otro
		// super-individuo en el vector.
		if (origenDestino != null)
		{
			// Obtener al super-individuo X.
			SuperIndividuo superIndividuoX = espacio.buscarGrilla(
												 origenDestino.obtenerOrigen());
			
			// Cuando la aptitud del super-individuo X es menor a la aptitud
			// del super-individuo y el super-individuo puede alcanzar al
			// super-individuo X.
			if (superIndividuoX != null &&
				superIndividuoX.obtenerAptitud() <
				superIndividuo.obtenerAptitud() &&
				superIndividuo.esAlcanzable(destino))
				// El super-individuo preda.
				superIndividuo.predar(espacio, superIndividuoX);
			
			// Cuando la aptitud del super-individuo X es mayor o igual a la
			// aptitud del super-individuo o el super-individuo no puede
			// alcanzar al super-individuo X.
			else
				// El super-individuo se fija.
				superIndividuo.fijar(espacio);
		}
		
		// Cuando el destino del super-individuo no es origen de otro
		// super-individuo en el vector.
		else
			// El super-individuo se mueve.
			superIndividuo.mover(espacio);
	}
	
	/**
	 * Método en donde se realiza la acción de reproducir de un super-individuo
	 * recibido por parámetros. Se obtiene el objetivo del super-individuo y se
	 * busca en el vector de orígenes y destinos si el objetivo del
	 * super-individuo es origen de otro super-individuo. En caso de serlo, se
	 * hace competir al par de super-individuos y, dependiendo de quien gane,
	 * entonces se realiza la acción de mover o reproducir al super-individuo.
	 * En caso de no serlo, se realiza la acción de mover al super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta reproducir.
	 */
	private void asignarAccionReproducir(SuperIndividuo superIndividuo)
	{
		// Obtener el objetivo del super-individuo.
		Celda objetivo = superIndividuo.obtenerObjetivo();
		
		// Cuando el objetivo existe.
		if (objetivo != null)
		{
			// Buscar el objetivo del super-individuo como origen de otro
			// super-individuo en el vector.
			OrigenDestino origenDestino = vectorOrigenDestino.buscarOrigen(
																	  objetivo);
			
			// Cuando el objetivo del super-individuo es origen de otro
			// super-individuo en el vector.
			if (origenDestino != null)
			{
				// Obtener al super-individuo X.
				SuperIndividuo superIndividuoX = espacio.buscarGrilla(
												 origenDestino.obtenerOrigen());
				
				// Cuando la aptitud del super-individuo X es menor a la aptitud
				// del super-individuo y el super-individuo puede alcanzar al
				// super-individuo X.
				if (superIndividuoX != null &&
					superIndividuoX.obtenerAptitud() <
					superIndividuo.obtenerAptitud() &&
					superIndividuo.esAlcanzable(objetivo))
					// El super-individuo se reproduce.
					superIndividuo.reproducir(espacio, superIndividuoX);
				
				// Cuando la aptitud del super-individuo X es mayor o igual a la
				// aptitud del super-individuo o el super-individuo no puede
				// alcanzar al super-individuo X.
				else
					// El super-individuo se mueve.
					superIndividuo.mover(espacio);
			}
			
			// Cuando el objetivo del super-individuo no es origen de otro
			// super-individuo en el vector.
			else
				// El super-individuo se mueve.
				superIndividuo.mover(espacio);
		}
		
		// Cuando el objetivo no existe.
		else
			// El super-individuo se mueve.
			superIndividuo.mover(espacio);
	}
	
	/**
	 * Método en donde se realiza la acción de convivir de un super-individuo
	 * recibido por parámetros. Se obtiene el destino del super-individuo y se
	 * busca en el vector de orígenes y destino si el destino del
	 * super-individuo es origen de otro super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta desovar.
	 */
	private void asignarAccionDesovar(SuperIndividuo superIndividuo)
	{
		// Obtener el destino del super-individuo.
		Celda destino = superIndividuo.obtenerDestino();
		
		// Buscar el destino del super-individuo como origen de otro
		// super-individuo en el vector.
		OrigenDestino origenDestino = vectorOrigenDestino.buscarOrigen(destino);
		
		// Cuando el destino del super-individuo es origen de otro
		// super-individuo en el vector.
		if (origenDestino != null)
		{
			// Obtener al super-individuo X.
			SuperIndividuo superIndividuoX = espacio.buscarGrilla(
												 origenDestino.obtenerOrigen());
			
			// Cuando el super-individuo es el mismo que el super-individuo X.
			if (superIndividuoX != null &&
				superIndividuo == superIndividuoX)
				superIndividuo.mover(espacio);
			
			// Cuando el super-individuo no es el mismo que el super-individuo
			// X.
			else superIndividuo.fijar(espacio);
		}
		
		// Cuando el destino del super-individuo no es origen de otro
		// super-individuo en el vector.
		else rastreoSiguiente.addElement(
									   superIndividuo.desovar(espacio).clone());
	}
	
	/**
	 * Método en donde se realiza la acción de convivir de un super-individuo
	 * recibido por parámetros. Se obtiene el destino del super-individuo y se
	 * busca en el vector de orígenes y destino si el destino del
	 * super-individuo es origen de otro super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta convivir.
	 */
	private void asignarAccionConvivir(SuperIndividuo superIndividuo)
	{
		// Obtener el destino del super-individuo.
		Celda destino = superIndividuo.obtenerDestino();
		
		// Buscar el destino del super-individuo como origen de otro
		// super-individuo en el vector.
		OrigenDestino origenDestino = vectorOrigenDestino.buscarOrigen(destino);
		
		// Cuando el destino del super-individuo es origen de otro
		// super-individuo en el vector.
		if (origenDestino != null)
		{
			// Obtener al super-individuo X.
			SuperIndividuo superIndividuoX = espacio.buscarGrilla(
												 origenDestino.obtenerOrigen());
			
			// Cuando el super-individuo es el mismo que el super-individuo X.
			if (superIndividuoX != null &&
				superIndividuo == superIndividuoX)
				superIndividuo.mover(espacio);
			
			// Cuando el super-individuo no es el mismo que el super-individuo
			// X.
			else superIndividuo.fijar(espacio);
		}
		
		// Cuando el destino del super-individuo no es origen de otro
		// super-individuo en el vector.
		else superIndividuo.mover(espacio);
	}
	
	/**
	 * Método en donde se realiza la acción de migrar de un super-individuo
	 * recibido por parámetros. Se obtiene el destino del super-individuo y se
	 * busca en el vector de orígenes y destino si el destino del
	 * super-individuo es origen de otro super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta migrar.
	 */
	private void asignarAccionMigrar(SuperIndividuo superIndividuo)
	{
		// Obtener el destino del super-individuo.
		Celda destino = superIndividuo.obtenerDestino();
		
		// Buscar el destino del super-individuo como origen de otro
		// super-individuo en el vector.
		OrigenDestino origenDestino = vectorOrigenDestino.buscarOrigen(destino);
		
		// Cuando el destino del super-individuo es origen de otro
		// super-individuo en el vector.
		if (origenDestino != null)
		{
			// Obtener al super-individuo X.
			SuperIndividuo superIndividuoX = espacio.buscarGrilla(
												 origenDestino.obtenerOrigen());
			
			// Cuando el super-individuo es el mismo que el super-individuo X.
			if (superIndividuoX != null &&
				superIndividuo == superIndividuoX)
				superIndividuo.mover(espacio);
			
			// Cuando el super-individuo no es el mismo que el super-individuo
			// X.
			else superIndividuo.fijar(espacio);
		}
		
		// Cuando el destino del super-individuo no es origen de otro
		// super-individuo en el vector.
		else superIndividuo.mover(espacio);
	}
	
	/**
	 * Método en donde se realiza la acción de divagar de un super-individuo
	 * recibido por parámetros. Se obtiene el destino del super-individuo y se
	 * busca en el vector de orígenes y destino si el destino del
	 * super-individuo es origen de otro super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta divagar.
	 */
	private void asignarAccionDivagar(SuperIndividuo superIndividuo)
	{
		// Obtener el destino del super-individuo.
		Celda destino = superIndividuo.obtenerDestino();
		
		// Buscar el destino del super-individuo como origen de otro
		// super-individuo en el vector.
		OrigenDestino origenDestino = vectorOrigenDestino.buscarOrigen(destino);
		
		// Cuando el destino del super-individuo es origen de otro
		// super-individuo en el vector.
		if (origenDestino != null)
		{
			// Obtener al super-individuo X.
			SuperIndividuo superIndividuoX = espacio.buscarGrilla(
												 origenDestino.obtenerOrigen());
			
			// Cuando el super-individuo es el mismo que el super-individuo X.
			if (superIndividuoX != null &&
				superIndividuo == superIndividuoX)
				superIndividuo.mover(espacio);
			
			// Cuando el super-individuo no es el mismo que el super-individuo
			// X.
			else superIndividuo.fijar(espacio);
		}
		
		// Cuando el destino del super-individuo no es origen de otro
		// super-individuo en el vector.
		else superIndividuo.mover(espacio);
	}
	
	/**
	 * Método en donde se realiza la acción de hacer nada de un super-individuo
	 * recibido por parámetros. Se obtiene el destino del super-individuo y se
	 * busca en el vector de orígenes y destino si el destino del
	 * super-individuo es origen de otro super-individuo.
	 *
	 * @param superIndividuo El super-individuo que intenta nada.
	 */
	private void asignarAccionNada(SuperIndividuo superIndividuo)
	{
		// Obtener el destino del super-individuo.
		Celda destino = superIndividuo.obtenerDestino();
		
		// Buscar el destino del super-individuo como origen de otro
		// super-individuo en el vector.
		OrigenDestino origenDestino = vectorOrigenDestino.buscarOrigen(destino);
		
		// Cuando el destino del super-individuo es origen de otro
		// super-individuo en el vector.
		if (origenDestino != null)
		{
			// Obtener al super-individuo X.
			SuperIndividuo superIndividuoX = espacio.buscarGrilla(
												 origenDestino.obtenerOrigen());
			
			// Cuando el super-individuo es el mismo que el super-individuo X.
			if (superIndividuoX != null &&
				superIndividuo == superIndividuoX)
				superIndividuo.mover(espacio);
			
			// Cuando el super-individuo no es el mismo que el super-individuo
			// X.
			else superIndividuo.fijar(espacio);
		}
		
		// Cuando el destino del super-individuo no es origen de otro
		// super-individuo en el vector.
		else superIndividuo.mover(espacio);
	}
	
	/**
	 * Método que intercambia el vector de rastreo actual por el vector de
	 * rastreo siguiente. Luego el vector de rastreo siguiente es limpiado. Este
	 * método se llama cada vez que ocurre un avance en la evolución del
	 * autómata celular.
	 */
	private void intercambiarRastreos()
	{
		// Clonar el vector de rastreo siguiente y asignarlo al vector de
		// rastreo actual.
		rastreoActual = (VectorCelda) rastreoSiguiente.clone();
		
		// Limpiar el vector de rastreo siguiente.
		rastreoSiguiente.removeAllElements();
	}
	
	/**
	 * Método que obtiene el valor del atributo tiempo.
	 *
	 * @return tiempo El valor del atributo tiempo.
	 */
	public Tiempo obtenerTiempo()
	{
		return tiempo;
	}
	
	/**
	 * Método que obtiene el valor del atributo espacio.
	 *
	 * @return espacio El valor del atributo espacio.
	 */
	public Espacio obtenerEspacio()
	{
		return espacio;
	}
	
	/**
	 * Método que obtiene el valor del atributo rastreoActual.
	 *
	 * @return rastreoActual El valor del atributo rastreoActual.
	 */
	public VectorCelda obtenerRastreoActual()
	{
		return rastreoActual;
	}
	
	/**
	 * Método que obtiene el valor del atributo rastreoSiguiente.
	 *
	 * @return rastreoSiguiente El valor del atributo rastreoSiguiente.
	 */
	public VectorCelda obtenerRastreoSiguiente()
	{
		return rastreoSiguiente;
	}
	
	/**
	 * Método que obtiene el valor del atributo estadistica.
	 *
	 * @return estadistica El valor del atributo estadistica.
	 */
	public Estadistica obtenerEstadistica()
	{
		return estadistica;
	}
	
	/**
	 * Método que obtiene el valor del atributo vectorOrigenDestino.
	 *
	 * @return vectorOrigenDestino El valor del atributo vectorOrigenDestino.
	 */
	public VectorOrigenDestino obtenerVectorOrigenDestino()
	{
		return vectorOrigenDestino;
	}
}