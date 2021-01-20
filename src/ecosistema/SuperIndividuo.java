/**
 * @(#)SuperIndividuo.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a un grupo de individuos que comparten caracter�sticas
 * similares como tama�o, coordenadas espaciales, requerimiento alimenticio y
 * pertenecen a la misma especie (caracter�sticas fisiol�gicas y morfol�gicas
 * similares). Esta clase permite modelar una gran poblaci�n de individuos,
 * evitando el sesgo potencial que puede inducir la reducci�n del n�mero de
 * individuos en la poblaci�n. Un super-individuo es modelado como un agente
 * basado en metas, con creencias, deseos e intensiones. Las arquitecturas que
 * usa el agente son del tipo: (1) horizontal seg�n las capas que tienen acceso
 * a sensores y actuadores, y (2) h�brida que combina aspectos reactivos y
 * deliberativos, seg�n el tipo de procesamiento empleado.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Ajuste
 * @see Celda
 * @see Vecindad
 * @see Espacio
 * @see Tiempo
 * @see Servicio
 * @see Recurso
 */
public abstract class SuperIndividuo
{
	/** El valor que indica que el estado es desaparecido. */
	public static final int DESAPARECIDO = 1;
	
	/** El valor que indica que el estado es muerto. */
	public static final int MUERTO = 2;
	
	/** El valor que indica que el estado es sin huevos. */
	public static final int SIN_HUEVOS = 3;
	
	/** El valor que indica que el estado es con huevos. */
	public static final int CON_HUEVOS = 4;
	
	/** El valor que indica que el sexo es macho. */
	public static final int MACHO = 1;
	
	/** El valor que indica que el sexo es hembra. */
	public static final int HEMBRA = 2;
	
	/** El valor que indica que el estado de madurez sexual es maduro. */
	public static final int MADURO = 1;
	
	/** El valor que indica que el estado de madurez sexual es inmaduro. */
	public static final int INMADURO = 2;
	
	/** El valor que indica que la acci�n es arrancar. */
	public static final int ARRANCAR = 0;
	
	/** El valor que indica que la acci�n es predar. */
	public static final int PREDAR = 1;
	
	/** El valor que indica que la acci�n es reproducir. */
	public static final int REPRODUCIR = 2;
	
	/** El valor que indica que la acci�n es desovar. */
	public static final int DESOVAR = 3;
	
	/** El valor que indica que la acci�n es convivir. */
	public static final int CONVIVIR = 4;
	
	/** El valor que indica que la acci�n es migrar. */
	public static final int MIGRAR = 5;
	
	/** El valor que indica que la acci�n es divagar. */
	public static final int DIVAGAR = 6;
	
	/** El valor que indica que la acci�n es nada. */
	public static final int NADA = 7;
	
	/** El ajuste de variables. */
	private Ajuste ajuste;
	
	/** El tipo de recurso (valor entre {0, 1, 2, 3, 4, ... , n}). */
	private int recurso;
	
	/** El tipo de animal (valor entre {1, 2}). */
	private int animal;
	
	/** El sexo (valor entre {1, 2}). */
	private int sexo;
	
	/** El estado (valor entre {1, 2, 3, 4}). */
	private int estado;
	
	/** La edad (valor en a�os). */
	private double edad;
	
	/** La talla (valor en cent�metros). */
	private double talla;
	
	/** El peso (valor en gramos). */
	private double peso;
	
	/** La probabilidad de reproducci�n (valor entre [0, 1]). */
	private double reproduccion;
	
	/** El estado de madurez sexual (valor entre {1, 2}). */
	private int madurez;
	
	/** El n�mero de huevos que puede desovar (valor en unidades). */
	private long huevos;
	
	/** El tiempo que le queda para desovar (valor en d�as). */
	private double tiempoPortacion;
	
	/** La cantidad de individuos (valor en unidades). */
	private long cantidad;
	
	/** La energ�a (valor entre [1, 10]). */
	private double energia;
	
	/** La decadencia (valor entre [0, 1]). */
	private double decadencia;
	
	/** La celda de ubicaci�n (i, j, k). */
	private Celda ubicacion;
	
	/** La celda de destino (i, j, k). */
	private Celda destino;
	
	/** La celda de objetivo (i, j, k). */
	private Celda objetivo;
	
	/** La vecindad de percepci�n. */
	private Vecindad percepcion;
	
	/** Los deseos (valor entre [0, 1]). */
	private double[] deseo;
	
	/** Las intenciones (valor entre {0, 1, 2, 3, 4, 5, 6, 7}). */
	private int[] intencion;
	
	/** La acci�n (valor entre {0, 1, 2, 3, 4, 5, 6, 7}). */
	private int accion;
	
	/** El grado de aptitud (valor entre [0, 1]). */
	private double aptitud;
	
	/** El grado de calidad (valor entre [0, 1]). */
	private double calidad;
	
	/**
	 * M�todo constructor que permite crear un super-individuo. Este constructor
	 * se utiliza cuando el super-individuo ha nacido y, por ende, el
	 * super-individuo no tiene caracter�sticas determinadas, excepto por la
	 * cantidad de individuos que contiene el super-individuo.
	 *
	 * @param recurso El tipo de recurso que es el super-individuo.
	 * @param animal El tipo de animal que es el super-individuo.
	 * @param cantidad La cantidad de individuos que contiene el
	 *                 super-individuo.
	 * @param espacio El espacio discreto en que se cre� el super-individuo.
	 */
	public SuperIndividuo(int recurso, int animal, long cantidad,
						  Espacio espacio)
	{
		this.ajuste = new Ajuste(recurso, cantidad);
		this.recurso = recurso;
		this.animal = animal;
		this.sexo = ajuste.obtenerSexo();
		this.estado = ajuste.obtenerEstado();
		this.edad = 0;
		this.talla = Recurso.calcularTalla(ajuste, edad);
		this.peso = Recurso.calcularPeso(ajuste, talla);
		this.reproduccion = Recurso.calcularReproduccion(ajuste, talla);
		this.madurez = Recurso.calcularMadurez(ajuste, reproduccion);
		this.huevos = Recurso.calcularFecundidad(ajuste, talla);
		this.tiempoPortacion = 0;
		this.cantidad = ajuste.obtenerCantidad();
		this.energia = ajuste.obtenerEnergiaInicial();
		this.decadencia = Recurso.calcularDecadencia(ajuste, edad, energia);
		this.ubicacion = new Celda();
		this.destino = new Celda();
		this.objetivo = new Celda();
		this.percepcion = new Vecindad(
			Servicio.transformarMetroCelda(ajuste.obtenerPercepcion(),
			espacio.obtenerTransformacion()),
			Servicio.transformarMetroCelda(ajuste.obtenerVelocidad(),
			espacio.obtenerTransformacion()));
		this.deseo = new double[8];
		this.intencion = new int[8];
		this.accion = NADA;
		this.aptitud = Recurso.calcularAptitud(ajuste, edad, talla, peso);
		this.calidad = Recurso.calcularCalidad(ajuste, estado, edad, talla,
											   peso, reproduccion, madurez,
											   tiempoPortacion);
	}
	
	/**
	 * M�todo constructor que permite crear un super-individuo. Este constructor
	 * se utiliza cuando el super-individuo ya tiene algunos a�os de vida y, por
	 * ende, el super-individuo tiene sexo, estado, talla, peso y cantidad de
	 * individuos determinados.
	 *
	 * @param recurso El tipo de recurso que es el super-individuo.
	 * @param animal El tipo de animal que es el super-individuo.
	 * @param sexo El tipo de sexo que es el super-individuo.
	 * @param estado El tipo de estado en que se encuentra el super-individuo.
	 * @param talla La talla que tiene el super-individuo.
	 * @param peso El peso que tiene el super-individuo.
	 * @param cantidad La cantidad de individuos que contiene el
	 *                 super-individuo.
	 * @param espacio El espacio discreto en que se cre� el super-individuo.
	 */
	public SuperIndividuo(int recurso, int animal, int sexo, int estado,
						  double talla, double peso, long cantidad,
						  Espacio espacio)
	{
		this.ajuste = new Ajuste(recurso, sexo, estado, talla, peso, cantidad);
		this.recurso = recurso;
		this.animal = animal;
		this.sexo = ajuste.obtenerSexo();
		this.estado = ajuste.obtenerEstado();
		this.edad = Recurso.calcularEdad(ajuste, recurso);
		this.talla = ajuste.obtenerTalla();
		this.peso = ajuste.obtenerPeso();
		this.reproduccion = Recurso.calcularReproduccion(ajuste, talla);
		this.madurez = Recurso.calcularMadurez(ajuste, reproduccion);
		this.huevos = Recurso.calcularFecundidad(ajuste, talla);
		if (sexo == HEMBRA && estado == CON_HUEVOS)
			this.tiempoPortacion = Servicio.obtenerAzar(0,
								   ajuste.obtenerPeriodoPortacion());
		else this.tiempoPortacion = 0;
		this.cantidad = ajuste.obtenerCantidad();
		this.energia = ajuste.obtenerEnergiaInicial();
		this.decadencia = Recurso.calcularDecadencia(ajuste, edad, energia);
		this.ubicacion = new Celda();
		this.destino = new Celda();
		this.objetivo = new Celda();
		this.percepcion = new Vecindad(
			Servicio.transformarMetroCelda(ajuste.obtenerPercepcion(),
			espacio.obtenerTransformacion()),
			Servicio.transformarMetroCelda(ajuste.obtenerVelocidad(),
			espacio.obtenerTransformacion()));
		this.deseo = new double[8];
		this.intencion = new int[8];
		this.accion = NADA;
		this.aptitud = Recurso.calcularAptitud(ajuste, edad, talla, peso);
		this.calidad = Recurso.calcularCalidad(ajuste, estado, edad, talla,
											   peso, reproduccion, madurez,
											   tiempoPortacion);
	}
	
	/**
	 * M�todo que genera las creencias del super-individuo con respecto a si
	 * mismo y a la percepci�n de su entorno. El super-individuo ya se ha
	 * auto-evaluado y ahora eval�a su vecindad, estableciendo los valores a
	 * las celdas vecinas del espacio. Es decir, se realiza un escaneo de la
	 * vecindad percibible por el super-individuo en el espacio. Las creencias
	 * son el conocimiento que el super-individuo tienen sobre si mismo y su
	 * entorno.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 */
	public void creer(Espacio espacio)
	{
		percepcion.evaluar(this, espacio);
	}
	
	/**
	 * M�todo que genera los deseos del super-individuo con respecto a las
	 * creencias. Se establecen los valores a todos los deseos llamando a los
	 * m�todos que obtienen los valores de los deseos relevantes para el intento
	 * de la acci�n a emprender. Los deseos corresponden a los objetivos a largo
	 * plazo que desea cumplir del super-individuo.
	 *
	 * @param tiempo El tiempo discreto de la din�mica.
	 * @param espacio El espacio discreto de la din�mica.
	 */
	public void desear(Tiempo tiempo, Espacio espacio)
	{
		deseo[ARRANCAR] = desearArrancar();
		deseo[PREDAR] = desearPredar();
		deseo[REPRODUCIR] = desearReproducir(tiempo);
		deseo[DESOVAR] = desearDesovar(tiempo);
		deseo[CONVIVIR] = desearConvivir();
		deseo[MIGRAR] = desearMigrar(espacio);
		deseo[DIVAGAR] = desearDivagar();
		deseo[NADA] = desearNada();
	}
	
	/**
	 * M�todo que genera la intenci�n del super-individuo con respecto a los
	 * deseos. Se establecen el valor de la intenci�n, usando la t�cnica de
	 * intentar lo que m�s se desee. Es decir, la intenci�n m�s importante es la
	 * acci�n que m�s se desea. Como normalmente no se puede cumplir todos los
	 * objetivos a la vez, ya que el super-individuo tiene recursos limitados,
	 * son introducidas las intenciones, que son los objetivos que en cada
	 * momento intenta cumplir el super-individuo.
	 */
	public void intentar()
	{
		// Crear un arreglo de deseos ordenados.
		double[] deseoOrdenado = Servicio.crearArregloOrdenado(deseo);
		
		// Ciclo para asignar los valores al arreglo de intenciones.
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if (deseoOrdenado[i] == deseo[j])
				{
					intencion[i] = j;
					break;
				}
	}
	
	/**
	 * M�todo que obtiene el deseo de arrancar del super-individuo. Se eval�a la
	 * necesidad de arrancar que tiene actualmente el super-individuo (valor
	 * entre [0, 1]) y se pondera con la tendencia de arrancar que tiene el
	 * recurso (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @return deseoArrancar Un valor entre [0, 1] que indica cuanto desea
	 *                       arrancar el super-individuo.
	 *                       0: Indica que el deseo de arrancar es bajo.
	 *                       1: Indica que el deseo de arrancar es alto.
	 */
	private double desearArrancar()
	{
		return necesitarArrancar() * ajuste.obtenerTendenciaArrancar();
	}
	
	/**
	 * M�todo que obtiene el deseo de predar del super-individuo. Se eval�a la
	 * necesidad de predar que tiene actualmente el super-individuo (valor entre
	 * [0, 1]) y se pondera con la tendencia de predar que tiene el recurso
	 * (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @return deseoPredar Un valor entre [0, 1] que indica cuanto desea
	 *                     predar el super-individuo.
	 *                     0: Indica que el deseo de predar es bajo.
	 *                     1: Indica que el deseo de predar es alto.
	 */
	private double desearPredar()
	{
		return necesitarPredar() * ajuste.obtenerTendenciaPredar();
	}
	
	/**
	 * M�todo que obtiene el deseo de repruducir del super-individuo. Se eval�a
	 * la necesidad de reproducir que tiene actualmente el super-individuo
	 * (valor entre [0, 1]) y se pondera con la tendencia de reproducir que
	 * tiene el recurso (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @param tiempo El tiempo discreto de la din�mica.
	 *
	 * @return deseoReproducir Un valor entre [0, 1] que indica cuanto desea
	 *                         reproducirse el super-individuo.
	 *                         0: Indica que el deseo de reproducir es bajo.
	 *                         1: Indica que el deseo de reproducir es alto.
	 */
	private double desearReproducir(Tiempo tiempo)
	{
		return necesitarReproducir(tiempo) *
			   ajuste.obtenerTendenciaReproducir();
	}
	
	/**
	 * M�todo que obtiene el deseo de desovar del super-individuo. Se eval�a la
	 * necesidad de desovar que tiene actualmente el super-individuo (valor
	 * entre [0, 1]) y se pondera con la tendencia de desovar que tiene el
	 * recurso (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @param tiempo El tiempo discreto de la din�mica.
	 *
	 * @return deseoDesovar Un valor entre [0, 1] que indica cuanto desea
	 *                      desovar el super-individuo.
	 *                      0: Indica que el deseo de desovar es bajo.
	 *                      1: Indica que el deseo de desovar es alto.
	 */
	private double desearDesovar(Tiempo tiempo)
	{
		return necesitarDesovar(tiempo) * ajuste.obtenerTendenciaDesovar();
	}
	
	/**
	 * M�todo que obtiene el deseo de convivir del super-individuo. Se eval�a la
	 * necesidad de convivir que tiene actualmente el super-individuo (valor
	 * entre [0, 1]) y se pondera con la tendencia de convivir que tiene el
	 * recurso (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @return deseoConvivir Un valor entre [0, 1] que indica cuanto desea
	 *                       convivir el super-individuo.
	 *                       0: Indica que el deseo de convivir es bajo.
	 *                       1: Indica que el deseo de convivir es alto.
	 */
	private double desearConvivir()
	{
		return necesitarConvivir() * ajuste.obtenerTendenciaConvivir();
	}
	
	/**
	 * M�todo que obtiene el deseo de migrar del super-individuo. Se eval�a la
	 * necesidad de migrar que tiene actualmente el super-individuo (valor entre
	 * [0, 1]) y se pondera con la tendencia de migrar que tiene el recurso
	 * (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 *
	 * @return deseoMigrar Un valor entre [0, 1] que indica cuanto desea migrar
	 *                     el super-individuo.
	 *                     0: Indica que el deseo de migrar es bajo.
	 *                     1: Indica que el deseo de migrar es alto.
	 */
	private double desearMigrar(Espacio espacio)
	{
		return necesitarMigrar(espacio) * ajuste.obtenerTendenciaMigrar();
	}
	
	/**
	 * M�todo que obtiene el deseo de divagar del super-individuo. Se eval�a la
	 * necesidad de divagar que tiene actualmente el super-individuo (valor
	 * entre [0, 1]) y se pondera con la tendencia de divagar que tiene el
	 * recurso (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @return deseoDivagar Un valor entre [0, 1] que indica cuanto desea
	 *                      divagar el super-individuo.
	 *                      0: Indica que el deseo de divagar es bajo.
	 *                      1: Indica que el deseo de divagar es alto.
	 */
	private double desearDivagar()
	{
		return necesitarDivagar() * ajuste.obtenerTendenciaDivagar();
	}
	
	/**
	 * M�todo que obtiene el deseo de hacer nada del super-individuo. Se eval�a
	 * la necesidad de hacer nada que tiene actualmente el super-individuo
	 * (valor entre [0, 1]) y se pondera con la tendencia de hacer nada que
	 * tiene el recurso (valor entre [0, 1]). Luego, se devuelve este valor.
	 *
	 * @return deseoNada Un valor entre [0, 1] que indica cuanto desea hacer
	 *                   nada el super-individuo.
	 *                   0: Indica que el deseo de hacer nada es bajo.
	 *                   1: Indica que el deseo de hacer nada es alto.
	 */
	private double desearNada()
	{
		return necesitarNada() * ajuste.obtenerTendenciaNada();
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de arrancar que tiene actualmente
	 * el super-individuo, considerando sus creencias. La necesidad de arrancar
	 * es un valor entre [0, 1]. La necesidad de arrancar considera los
	 * siguientes factores: (1) la densidad de predadores y (2) la distancia al
	 * mejor predador.
	 *
	 * return necesidadArrancar Un valor entre [0, 1] que indica la necesidad de
	 *                          arrancar actual del super-individuo.
	 *                          0: Indica que la necesidad de arrancar es baja.
	 *                          1: Indica que la necesidad de arrancar es alta.
	 */
	private double necesitarArrancar()
	{
		double factorDensidad = 0;
		double factorDistancia = 0;
		
		// Obtener el mejor predador y la cantidad de predadores.
		Celda mejorPredador = percepcion.obtenerMejorPredador();
		double cantidadPredador = (double) percepcion.obtenerCantidadPredador();
		
		// Obtener la cantidad de vecinos.
		double cantidadVecino = (double) percepcion.obtenerCantidadVecino();
		
		// Cuando hay vecinos.
		if (cantidadVecino > 0)
			// Calcular el factor densidad.
			factorDensidad = cantidadPredador/cantidadVecino;
		
		// Cuando hay mejor predador.
		if (mejorPredador != null)
			// Calcular el factor distancia.
			factorDistancia = 1 -
							Servicio.obtenerDistancia(ubicacion, mejorPredador)/
							(Math.sqrt(3) * percepcion.obtenerRadio());
		
		return 0.4 * factorDensidad + 0.6 * factorDistancia;
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de predar que tiene actualmente el
	 * super-individuo, considerando sus creencias. La necesidad de predar es un
	 * valor entre [0, 1]. La necesidad de predar considera los siguientes
	 * factores: (1) la densidad de presas, (2) la distancia a la mejor presa y
	 * (3) el nivel de energ�a.
	 *
	 * return necesidadPredar Un valor entre [0, 1] que indica la necesidad de
	 *                        predar actual del super-individuo.
	 *                        0: Indica que la necesidad de predar es baja.
	 *                        1: Indica que la necesidad de predar es alta.
	 */
	private double necesitarPredar()
	{
		double factorDensidad = 0;
		double factorDistancia = 0;
		double factorEnergia = 0;
		
		// Obtener la mejor presa y la cantidad de presas.
		Celda mejorPresa = percepcion.obtenerMejorPresa();
		double cantidadPresa = (double) percepcion.obtenerCantidadPresa();
		
		// Obtener la cantidad de vecinos.
		double cantidadVecino = (double) percepcion.obtenerCantidadVecino();
		
		// Cuando hay vecinos.
		if (cantidadVecino > 0)
			// Calcular el factor densidad.
			factorDensidad = cantidadPresa/cantidadVecino;
		
		// Cuando hay mejor presa.
		if (mejorPresa != null)
			// Calcular el factor distancia.
			factorDistancia = 1 -
						  Servicio.obtenerDistancia(ubicacion, mejorPresa)/
						  (Math.sqrt(3) * percepcion.obtenerRadio());
		
		// Obtener la energ�a m�nima y m�xima del recurso.
		double energiaMinima = ajuste.obtenerEnergiaMinima();
		double energiaMaxima = ajuste.obtenerEnergiaMaxima();
		
		// Cuando la energ�a es menor que la m�nima.
		if (energia < energiaMinima)
			factorEnergia = 1;
		
		// Cuando la energ�a es mayor que la m�nima.
		else
			// Cuando la energ�a es mayor o igual que la m�nima y menor o igual
			// que la m�xima.
			if (energiaMinima <= energia && energia <= energiaMaxima)
				factorEnergia = 1 - (energia - energiaMinima)/
				                    (energiaMaxima - energiaMinima);
		
		return 0.3 * factorDensidad + 0.3 * factorDistancia +
			   0.4 * factorEnergia;
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de reproducir que tiene
	 * actualmente el super-individuo, considerando sus creencias. La necesidad
	 * de reproducir es un valor entre [0, 1]. La necesidad de reproducir
	 * considera los siguientes factores: (1) la densidad de sexos opuestos, (2)
	 * la distancia al mejor sexo opuesto, (3) la probabilidad de reproducci�n y
	 * (4) la probabilidad de portaci�n seg�n el mes actual.
	 *
	 * @param tiempo El tiempo discreto de la din�mica.
	 *
	 * return necesidadReproducir Un valor entre [0, 1] que indica la necesidad
	 *                            de reproducir actual del super-individuo.
	 *                            0: Indica que la necesidad de reproducir es
	 *                            baja.
	 *                            1: Indica que la necesidad de reproducir es
	 *                            alta.
	 */
	private double necesitarReproducir(Tiempo tiempo)
	{
		double factorDensidad = 0;
		double factorDistancia = 0;
		double factorReproduccion = 0;
		double factorPortacion = 0;
		
		// Cuando est� sin huevos y est� maduro sexualmente.
		if (estado == SIN_HUEVOS && madurez == MADURO)
		{
			// Obtener el mejor sexo opuesto y la cantidad de sexos opuestos.
			Celda mejorSexoOpuesto = percepcion.obtenerMejorSexoOpuesto();
			double cantidadSexoOpuesto =
							   (double) percepcion.obtenerCantidadSexoOpuesto();
			
			// Obtener la cantidad de vecinos.
			double cantidadVecino = (double) percepcion.obtenerCantidadVecino();
			
			// Cuando hay vecinos.
			if (cantidadVecino > 0)
				// Calcular el factor densidad.
				factorDensidad = cantidadSexoOpuesto/cantidadVecino;
			
			// Cuando hay mejor sexo opuesto.
			if (mejorSexoOpuesto != null)
				// Calcular el factor distancia.
				factorDistancia = 1 -
						Servicio.obtenerDistancia(ubicacion, mejorSexoOpuesto)/
						(Math.sqrt(3) * percepcion.obtenerRadio());
			
			// Calcular el factor de reproducci�n.
			factorReproduccion = reproduccion;
			
			// Calcular el factor de portaci�n mensual.
			factorPortacion = Recurso.obtenerPortacion(recurso,
							  tiempo.obtenerFechaActual()[1] - 1);
		}
		
		return 0.1 * factorDensidad + 0.2 * factorDistancia +
			   0.3 * factorReproduccion + 0.4 * factorPortacion;
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de desovar que tiene actualmente
	 * el super-individuo, considerando sus creencias. La necesidad de desovar
	 * es un valor entre [0, 1]. La necesidad de desovar considera los
	 * siguientes factores: (1) la densidad de predadores, (2) la distancia al
	 * mejor predador, (3) la tasa de variaci�n del tiempo de portaci�n y (4) la
	 * probabilidad de desove seg�n el mes actual.
	 *
	 * @param tiempo El tiempo discreto de la din�mica.
	 *
	 * return necesidadDesovar Un valor entre [0, 1] que indica la necesidad de
	 *                         desovar actual del super-individuo.
	 *                         0: Indica que la necesidad de desovar es baja.
	 *                         1: Indica que la necesidad de desovar es alta.
	 */
	private double necesitarDesovar(Tiempo tiempo)
	{
		double factorDensidad = 0;
		double factorDistancia = 0;
		double factorTiempoPortacion = 0;
		double factorDesove = 0;
		
		// Cuando est� con huevos.
		if (estado == CON_HUEVOS)
		{
			// Obtener el mejor predador y la cantidad de predadores.
			Celda mejorPredador = percepcion.obtenerMejorPredador();
			double cantidadPredador =
								  (double) percepcion.obtenerCantidadPredador();
			
			// Obtener la cantidad de vecinos.
			double cantidadVecino = (double) percepcion.obtenerCantidadVecino();
			
			// Cuando hay vecinos.
			if (cantidadVecino > 0)
				// Calcular el factor densidad.
				factorDensidad = 1 - cantidadPredador/cantidadVecino;
			
			// Cuando no hay vecinos.
			else factorDensidad = 1;
			
			// Cuando hay mejor predador.
			if (mejorPredador != null)
				// Calcular el factor distancia.
				factorDistancia =
							Servicio.obtenerDistancia(ubicacion, mejorPredador)/
							(Math.sqrt(3) * percepcion.obtenerRadio());
			
			// Cuando no hay mejor predador.
			else factorDistancia = 1;
			
			// Obtener el tiempo de portaci�n m�ximo del recurso.
			double periodoPortacion = ajuste.obtenerPeriodoPortacion();
			
			// Cuando el tiempo de portaci�n es mayor que cero y menor o igual
			// que el periodo de portaci�n.
			if (0 < tiempoPortacion && tiempoPortacion <= periodoPortacion)
				factorTiempoPortacion = 1 - (periodoPortacion-tiempoPortacion)/
											 periodoPortacion;
			
			// Cuando el tiempo de portaci�n es menor o igual que cero o mayor
			// que el periodo de portaci�n.
			else factorTiempoPortacion = 1;
			
			// Calcular el factor de desove mensual.
			factorDesove = Recurso.obtenerDesove(recurso,
						   tiempo.obtenerFechaActual()[1] - 1);
		}
		
		return 0.05 * factorDensidad + 0.05 * factorDistancia +
			   0.2 * factorTiempoPortacion + 0.7 * factorDesove;
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de convivir que tiene actualmente
	 * el super-individuo, considerando sus creencias. La necesidad de convivir
	 * es un valor entre [0, 1]. La necesidad de convivir considera los
	 * siguientes factores: (1) la densidad de convivientes y (2) la distancia
	 * al mejor conviviente.
	 *
	 * return necesidadConvivir Un valor entre [0, 1] que indica la necesidad de
	 *                          convivir actual del super-individuo.
	 *                          0: Indica que la necesidad de convivir es baja.
	 *                          1: Indica que la necesidad de convivir es alta.
	 */
	private double necesitarConvivir()
	{
		double factorDensidad = 0;
		double factorDistancia = 0;
		
		// Obtener el mejor conviviente y la cantidad de convivientes.
		Celda mejorConviviente = percepcion.obtenerMejorConviviente();
		double cantidadConviviente =
							   (double) percepcion.obtenerCantidadConviviente();
		
		// Obtener la cantidad de vecinos.
		double cantidadVecino = (double) percepcion.obtenerCantidadVecino();
		
		// Dependiendo del tipo de conducta del recurso, calculamos sus
		// factores.
		switch (Recurso.obtenerConducta(recurso))
		{
			// Cuando es Solitaria.
			case Conducta.SOLITARIA:
			
			// Cuando hay vecinos.
			if (cantidadVecino > 0)
				// Calcular el factor densidad.
				factorDensidad = cantidadConviviente/cantidadVecino;
			
			// Cuando hay mejor conviviente.
			if (mejorConviviente != null)
				// Calcular el factor distancia.
				factorDistancia = 1 -
						Servicio.obtenerDistancia(ubicacion, mejorConviviente)/
						(Math.sqrt(3) * percepcion.obtenerRadio());
			
			break;
			
			// Cuando es Grupal.
			case Conducta.GRUPAL:
			
			// Cuando hay vecinos.
			if (cantidadVecino > 0)
				// Calcular el factor densidad.
				factorDensidad = 1 - cantidadConviviente/cantidadVecino;
			
			// Cuando hay mejor conviviente.
			if (mejorConviviente != null)
				// Calcular el factor distancia.
				factorDistancia =
						Servicio.obtenerDistancia(ubicacion, mejorConviviente)/
						(Math.sqrt(3) * percepcion.obtenerRadio());
			
			break;
		}
		
		return 0.5 * factorDensidad + 0.5 * factorDistancia;
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de migrar que tiene actualmente el
	 * super-individuo, considerando sus creencias. La necesidad de migrar es un
	 * valor entre [0, 1]. La necesidad de migrar considera los siguientes
	 * factores: (1) la migraci�n en longitud y (2) la migraci�n en latitud, (3)
	 * la migraci�n en altitud, (4) la migraci�n en longitud por la distancia al
	 * fondo, (5) la migraci�n en latitud por la distancia al fondo, (6) la
	 * migraci�n en altitud por la distancia al fondo, (7) la migraci�n en
	 * longitud por la segregaci�n espacial, (8) la migraci�n en latitud por la
	 * segregaci�n espacial y (9) la migraci�n en altitud por la segregaci�n
	 * espacial.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 *
	 * return necesidadMigrar Un valor entre [0, 1] que indica la necesidad de
	 *                        migrar actual del super-individuo.
	 *                        0: Indica que la necesidad de migrar es baja.
	 *                        1: Indica que la necesidad de migrar es alta.
	 */
	private double necesitarMigrar(Espacio espacio)
	{
		double factorLongitud = 0;
		double factorLatitud = 0;
		double factorAltitud = 0;
		double factorDistanciaFondoLongitud = 0;
		double factorDistanciaFondoLatitud = 0;
		double factorDistanciaFondoAltitud = 0;
		double factorSegregacionLongitud = 0;
		double factorSegregacionLatitud = 0;
		double factorSegregacionAltitud = 0;
		double longitudOptima = 0;
		double latitudOptima = 0;
		double altitudOptima = 0;
		
		// Obtener la transformaci�n de modelos usada.
		TransformacionModelo transformacion = espacio.obtenerTransformacion();
		
		// Obtener la longitud, latitud y altitud actual.
		double longitud = transformacion.obtenerX(ubicacion.obtenerI());
		double latitud = transformacion.obtenerY(ubicacion.obtenerJ());
		double altitud = transformacion.obtenerZ(ubicacion.obtenerK());
		
		// Obtener los rangos en longitud, latitud y altitud.
		double longitudInicial = Mapa.obtenerLongitudInicial();
		double longitudFinal = Mapa.obtenerLongitudFinal();
		double latitudInicial = ajuste.obtenerLatitudInicial();
		double latitudFinal = ajuste.obtenerLatitudFinal();
		double altitudInicial = ajuste.obtenerAltitudInicial();
		double altitudFinal = ajuste.obtenerAltitudFinal();
		
		// Calcular el factor de migraci�n en longitud.
		if (longitud < longitudInicial)
			factorLongitud = 1;
		else
			if (longitudInicial <= longitud && longitud <= longitudFinal)
				factorLongitud = 0;
			else factorLongitud = 1;
		
		// Calcular el factor de migraci�n en latitud.
		if (latitud < latitudInicial)
			factorLatitud = 1;
		else
			if (latitudInicial <= latitud && latitud <= latitudFinal)
				factorLatitud = 0;
			else factorLatitud = 1;
		
		// Calcular el factor de migraci�n en altitud.
		if (altitud < altitudInicial)
			factorAltitud = 1;
		else
			if (altitudInicial <= altitud && altitud <= altitudFinal)
				factorAltitud = 0;
			else factorAltitud = 1;
		
		// Obtener la longitud, latitud y altitud �ptima por migraci�n de
		// distancia al fondo.
		longitudOptima = Mapa.obtenerLongitudOptimaDistanciaFondo(
					   longitud, latitud, altitud,
					   Recurso.obtenerDistanciaFondoMinimaZonaSistema(recurso),
					   Recurso.obtenerDistanciaFondoMaximaZonaSistema(recurso));
		latitudOptima = Mapa.obtenerLatitudOptimaDistanciaFondo(
					   longitud, latitud, altitud,
					   Recurso.obtenerDistanciaFondoMinimaZonaSistema(recurso),
					   Recurso.obtenerDistanciaFondoMaximaZonaSistema(recurso));
		altitudOptima = Mapa.obtenerAltitudOptimaDistanciaFondo(
					   longitud, latitud, altitud,
					   Recurso.obtenerDistanciaFondoMinimaZonaSistema(recurso),
					   Recurso.obtenerDistanciaFondoMaximaZonaSistema(recurso));
		
		// Calcular el factor por distancia al fondo en longitud.
		if (longitud < longitudInicial)
			factorDistanciaFondoLongitud = 1;
		else
			if (longitudInicial <= longitud && longitud < longitudOptima)
				factorDistanciaFondoLongitud = 1 - (longitud - longitudInicial)/
											 (longitudOptima - longitudInicial);
			else
				if (longitudOptima <= longitud && longitud < longitudFinal)
					factorDistanciaFondoLongitud = (longitud - longitudOptima)/
											   (longitudFinal - longitudOptima);
				else factorDistanciaFondoLongitud = 1;
		
		// Calcular el factor por distancia de fondo en latitud.
		if (latitud < latitudInicial)
			factorDistanciaFondoLatitud = 1;
		else
			if (latitudInicial <= latitud && latitud < latitudOptima)
				factorDistanciaFondoLatitud = 1 - (latitud - latitudInicial)/
											  (latitudOptima - latitudInicial);
			else
				if (latitudOptima <= latitud && latitud < latitudFinal)
					factorDistanciaFondoLatitud = (latitud - latitudOptima)/
												 (latitudFinal - latitudOptima);
				else factorDistanciaFondoLatitud = 1;
		
		// Calcular el factor por distancia de fondo en altitud.
		if (altitud < altitudInicial)
			factorDistanciaFondoAltitud = 1;
		else
			if (altitudInicial <= altitud && altitud < altitudOptima)
				factorDistanciaFondoAltitud = 1 - (altitud - altitudInicial)/
											  (altitudOptima - altitudInicial);
			else
				if (altitudOptima <= altitud && altitud < altitudFinal)
					factorDistanciaFondoAltitud = (altitud - altitudOptima)/
												 (altitudFinal - altitudOptima);
				else factorDistanciaFondoAltitud = 1;
		
		// Obtener la mejor longitud, latitud y altitud de segregaci�n espacial.
		longitudOptima = Recurso.calcularLongitudSegregacion(ajuste, edad);
		latitudOptima = Recurso.calcularLatitudSegregacion(ajuste, edad);
		altitudOptima = Recurso.calcularAltitudSegregacion(ajuste, edad);
		
		// Calcular el factor de segregaci�n por longitud.
		factorSegregacionLongitud = 0;
		
		// Calcular el factor de segregaci�n por latitud.
		factorSegregacionLatitud = 0;
		
		// Calcular el factor de segregaci�n por altitud.
		if (altitud < altitudInicial)
			factorSegregacionAltitud = 1;
		else
			if (altitudInicial <= altitud && altitud < altitudOptima)
				factorSegregacionAltitud = 1 - (altitud - altitudInicial)/
											   (altitudOptima - altitudInicial);
			else
				if (altitudOptima <= altitud && altitud < altitudFinal)
					factorSegregacionAltitud = (altitud - altitudInicial)/
											   (altitudOptima - altitudInicial);
				else factorSegregacionAltitud = 1;
		
		return 0.1666 * factorLongitud +
			   0.1666 * factorLatitud +
			   0.1666 * factorAltitud +
			   0.1000 * factorDistanciaFondoLongitud +
			   0.1000 * factorDistanciaFondoLatitud +
			   0.1000 * factorDistanciaFondoAltitud +
			   0.0666 * factorSegregacionLongitud +
			   0.0666 * factorSegregacionLatitud +
			   0.0666 * factorSegregacionAltitud;
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de divagar que tiene actualmente
	 * el super-individuo, considerando sus creencias. La necesidad de divagar
	 * es un valor entre [0, 1]. La necesidad de divagar considera los
	 * siguientes factores: (1) un valor aleatorio.
	 *
	 * return necesidadDivagar Un valor entre [0, 1] que indica la necesidad de
	 *                         divagar actual del super-individuo.
	 *                         0: Indica que la necesidad de divagar es baja.
	 *                         1: Indica que la necesidad de divagar es alta.
	 */
	private double necesitarDivagar()
	{
		return Servicio.obtenerAzar(0, 1);
	}
	
	/**
	 * M�todo en donde se eval�a la necesidad de hacer nada que tiene
	 * actualmente el super-individuo, considerando sus creencias. La necesidad
	 * de hacer nada es un valor entre [0, 1]. La necesidad de hacer nada
	 * considera los siguientes factores: (1) un valor aleatorio.
	 *
	 * return necesidadNada Un valor entre [0, 1] que indica la necesidad de
	 *                      hacer nada actual del super-individuo.
	 *                      0: Indica que la necesidad de hacer nada es baja.
	 *                      1: Indica que la necesidad de hacer nada es alta.
	 */
	private double necesitarNada()
	{
		return Servicio.obtenerAzar(0, 1);
	}
	
	/**
	 * M�todo que determina si una celda es alcanzable o no por el
	 * super-individuo. Esto depende de si la velocidad del super-individuo le
	 * permite o no llegar a alcanzar a la celda. En caso afirmativo, se retorna
	 * true. En caso contrario, se retorna false.
	 *
	 * @param celda La celda a la cual se quiere llegar.
	 *
	 * @return true Cuando el super-individuo puede alcanzar a la celda.
	 * @return false Cuando el super-individuo no puede alcanzar a la celda.
	 */
	public boolean esAlcanzable(Celda celda)
	{
		// Obtener las componentes de la ubicaci�n del super-individuo.
		int i1 = ubicacion.obtenerI();
		int j1 = ubicacion.obtenerJ();
		int k1 = ubicacion.obtenerK();
		
		// Obtener las componentes de la ubicaci�n de la celda.
		int i2 = celda.obtenerI();
		int j2 = celda.obtenerJ();
		int k2 = celda.obtenerK();
		
		// Cuando la resta de componentes es menor que la velocidad.
		if (Math.abs(i2 - i1) <= percepcion.obtenerVelocidad() &&
			Math.abs(j2 - j1) <= percepcion.obtenerVelocidad() &&
			Math.abs(k2 - k1) <= percepcion.obtenerVelocidad())
			return true;
		
		return false;
	}
	
	/**
	 * M�todo que obtiene la celda a la cual desea migrar el super-individuo. El
	 * calculo de la celda a migrar considera los siguientes factores: (1) la
	 * migraci�n en longitud y (2) la migraci�n en latitud, (3) la migraci�n en
	 * altitud, (4) la migraci�n en longitud por la distancia al fondo, (8) la
	 * migraci�n en latitud por la distancia al fondo, (9) la migraci�n en
	 * altitud por la distancia al fondo, (10) la migraci�n en longitud por la
	 * segregaci�n espacial, (11) la migraci�n en latitud por la segregaci�n
	 * espacial y (12) la migraci�n en altitud por la segregaci�n espacial.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 *
	 * return celda La celda a la cual desea migrar el super-individuo.
	 */
	public Celda obtenerCeldaMigrar(Espacio espacio)
	{
		double factorLongitud = 0;
		double factorLatitud = 0;
		double factorAltitud = 0;
		double factorDistanciaFondoLongitud = 0;
		double factorDistanciaFondoLatitud = 0;
		double factorDistanciaFondoAltitud = 0;
		double factorSegregacionLongitud = 0;
		double factorSegregacionLatitud = 0;
		double factorSegregacionAltitud = 0;
		
		// Obtener la transformaci�n de modelos usada.
		TransformacionModelo transformacion = espacio.obtenerTransformacion();
		
		// Obtener la longitud, latitud y altitud actual.
		double longitud = transformacion.obtenerX(ubicacion.obtenerI());
		double latitud = transformacion.obtenerY(ubicacion.obtenerJ());
		double altitud = transformacion.obtenerZ(ubicacion.obtenerK());
		
		// Obtener la migraci�n en longitud, latitud y altitud.
		factorLongitud = Recurso.calcularLongitud(ajuste);
		factorLatitud = Recurso.calcularLatitud(ajuste);
		factorAltitud = Recurso.calcularAltitud(ajuste);
		
		// Obtener la migraci�n en longitud, latitud y altitud por distancia al
		// fondo.
		factorDistanciaFondoLongitud =
					   Mapa.obtenerLongitudOptimaDistanciaFondo(
					   longitud, latitud, altitud,
					   Recurso.obtenerDistanciaFondoMinimaZonaSistema(recurso),
					   Recurso.obtenerDistanciaFondoMaximaZonaSistema(recurso));
		factorDistanciaFondoLatitud =
					   Mapa.obtenerLatitudOptimaDistanciaFondo(
					   longitud, latitud, altitud,
					   Recurso.obtenerDistanciaFondoMinimaZonaSistema(recurso),
					   Recurso.obtenerDistanciaFondoMaximaZonaSistema(recurso));
		factorDistanciaFondoAltitud =
					   Mapa.obtenerAltitudOptimaDistanciaFondo(
					   longitud, latitud, altitud,
					   Recurso.obtenerDistanciaFondoMinimaZonaSistema(recurso),
					   Recurso.obtenerDistanciaFondoMaximaZonaSistema(recurso));
		
		// Obtener la migraci�n en longitud, latitud y altitud por segregaci�n
		// espacial.
		factorSegregacionLongitud = Recurso.calcularLongitudSegregacion(ajuste,
																		edad);
		factorSegregacionLatitud = Recurso.calcularLatitudSegregacion(ajuste,
																		edad);
		factorSegregacionAltitud = Recurso.calcularAltitudSegregacion(ajuste,
																		edad);
		
		// Obtener las componentes promedio.
		int iPromedio = transformacion.obtenerI((factorLongitud +
												factorDistanciaFondoLongitud +
												factorSegregacionLongitud)/3);
		int jPromedio = transformacion.obtenerJ((factorLatitud +
												factorDistanciaFondoLatitud +
												factorSegregacionLatitud)/3);
		int kPromedio = transformacion.obtenerK((factorAltitud +
												factorDistanciaFondoAltitud +
												factorSegregacionAltitud)/3);
		
		return new Celda(iPromedio, jPromedio, kPromedio, espacio);
	}
	
	/**
	 * M�todo que obtiene la celda a la cual desea divagar el super-individuo.
	 * La celda a donde se quiere divagar es elegida en forma aleatoria dentro
	 * del alcance de vecindad que tiene el super-individuo. El alcance del
	 * super-individuo est� dado por su velocidad.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 *
	 * return celda La celda a la cual desea divagar el super-individuo.
	 */
	public Celda obtenerCeldaDivagar(Espacio espacio)
	{
		// Obtener los componentes.
		int i = ubicacion.obtenerI();
		int j = ubicacion.obtenerJ();
		int k = ubicacion.obtenerK();
		
		// Obtener la velocidad.
		int velocidad = percepcion.obtenerVelocidad();
		
		// Obtener los componentes aleatorios crecientes.
		int iCreciente = Servicio.obtenerAzar(i, i + velocidad);
		int jCreciente = Servicio.obtenerAzar(j, j + velocidad);
		int kCreciente = Servicio.obtenerAzar(k, k + velocidad);
		
		// Obtener los componentes aleatorios decrecientes.
		int iDecreciente = -1 * Servicio.obtenerAzar(i, i + velocidad);
		int jDecreciente = -1 * Servicio.obtenerAzar(j, j + velocidad);
		int kDecreciente = -1 * Servicio.obtenerAzar(k, k + velocidad);
		
		// Calcular los componentes aleatorios promedios.
		int iPromedio = (iCreciente + iDecreciente)/2;
		int jPromedio = (jCreciente + jDecreciente)/2;
		int kPromedio = (kCreciente + kDecreciente)/2;
		
		return new Celda(iPromedio, jPromedio, kPromedio, espacio);
	}
	
	/**
	 * M�todo que genera la acci�n de movmiento del super-individuo en el
	 * espacio discreto, desde la celda de ubicaci�n actual hacia la celda de
	 * destino. Primero se decrementa su energ�a por concepto de unidad de
	 * desplazamiento, luego se elimina el super-individuo del espacio, despu�s
	 * se cambia su celda de ubicaci�n por la celda de destino y finalmente se
	 * inserta el super-individuo en el espacio.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 */
	public void mover(Espacio espacio)
	{
		// Obtener la transformaci�n de modelo usado en el espacio.
		TransformacionModelo transformacion = espacio.obtenerTransformacion();
		
		// Decrementar la energ�a por unidad desplazada.
		decrementarEnergia(
		Servicio.obtenerDistancia(
		Servicio.transformarCeldaCoordenada(ubicacion, transformacion),
		Servicio.transformarCeldaCoordenada(destino, transformacion)));
		
		// Eliminar del espacio.
		espacio.eliminarGrilla(this);
		
		// Cambiar la ubicaci�n.
		ubicacion = destino;
		
		// Insertar en el espacio.
		espacio.insertarGrilla(this);
	}
	
	/**
	 * M�todo que genera la acci�n de fijar el movimiento del super-individuo en
	 * el espacio discreto. Primero se cambia el destino por la ubicaci�n actual
	 * y luego se llama al m�todo mover.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 */
	public void fijar(Espacio espacio)
	{
		// Cambiar la ubicaci�n.
		destino = ubicacion;
		
		// Mover en el espacio.
		mover(espacio);
	}
	
	/**
	 * M�todo que genera la acci�n de predar del super-individuo a otro
	 * super-individuo en el espacio discreto. Primero se decrementa el peso y
	 * talladel super-individuo predado, despu�s se incrementa el peso del
	 * super-individuo que pred�, luego se incrementa la energ�a por concepto de
	 * unidad de consumo, se mata al super-individuo predado y finalmente se
	 * llama al m�todo mover.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 * @param superIndividuo El super-individuo que es predado.
	 */
	public void predar(Espacio espacio, SuperIndividuo superIndividuo)
	{
		// Incrementar el peso del super-individuo que pred�.
		incrementarPeso(superIndividuo.peso * superIndividuo.cantidad);
		
		// Incrementar la energ�a por unidad consumida del super-individuo que
		// pred�.
		incrementarEnergia(superIndividuo.peso * superIndividuo.cantidad);
		
		// Decrementar el peso del super-individuo predado.
		superIndividuo.decrementarPeso(energia/ajuste.obtenerEnergiaConsumo());
		
		// Decrementar la talla del super-individuo predado.
		superIndividuo.decrementarTalla(energia/ajuste.obtenerEnergiaConsumo());
		
		// Matar al super-individuo predado.
		superIndividuo.morir();
		
		// Mover en el espacio al super-individuo que pred�.
		mover(espacio);
	}
	
	/**
	 * M�todo que genera la acci�n de reproducci�n del super-individuo con otro
	 * super-individuo en el espacio discreto. Dependiendo del sexo, se realizan
	 * los cambios provocados por la reproducci�n en este super-individuo o en
	 * el otro super-individuo. Luego se se llama al m�todo mover.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 * @param superIndividuo El super-individuo que es considerado para
	 *                       reproducirse.
	 */
	public void reproducir(Espacio espacio, SuperIndividuo superIndividuo)
	{
		// Dependiendo del sexo, se realizan los cambios en este o en el otro
		// super-individuo.
		switch (sexo)
		{
			// Cuando es Macho.
			case MACHO:
			superIndividuo.estado = CON_HUEVOS;
			superIndividuo.tiempoPortacion = ajuste.obtenerPeriodoPortacion();
			break;
			
			// Cuando es Hembra.
			case HEMBRA:
			estado = CON_HUEVOS;
			tiempoPortacion = ajuste.obtenerPeriodoPortacion();
			break;
		}
		
		// Mover en el espacio.
		mover(espacio);
	}
	
	/**
	 * M�todo que genera la acci�n de desove del super-individuo en el espacio
	 * discreto. Primero se calcula la cantidad de individuos que contiene el
	 * nuevo super-individuo, luego se crea el objeto, despu�s se incorpora al
	 * espacio y finalmente se cambia el estado de este super-individuo.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 *
	 * @return destino La celda de ubicaci�n del super-individuo que ha nacido.
	 */
	public Celda desovar(Espacio espacio)
	{
		// Calcular la cantidad de individuos que contiene el super-individuo
		// que se va a crear.
		long cantidadNacen = (long)(huevos * cantidad *
							  Recurso.obtenerTasaNatalidad(recurso));
		
		// El nuevo super-individuo que va a nacer.
		SuperIndividuo superIndividuo = null;
		
		// Dependiendo del recurso, creamos el nuevo super-individuo.
		switch (recurso)
		{
			// Cuando es Camar�n Nailon.
			case Recurso.CAMARON_NAILON:
			superIndividuo = new SuperCamaronNailon(cantidadNacen, espacio);
			break;
			
			// Cuando es Langostino Amarillo.
			case Recurso.LANGOSTINO_AMARILLO:
			superIndividuo = new SuperLangostinoAmarillo(cantidadNacen,espacio);
			break;
			
			// Cuando es Langostino Colorado.
			case Recurso.LANGOSTINO_COLORADO:
			superIndividuo = new SuperLangostinoColorado(cantidadNacen,espacio);
			break;
			
			// Cuando es Merluza Com�n.
			case Recurso.MERLUZA_COMUN:
			superIndividuo = new SuperMerluzaComun(cantidadNacen, espacio);
			break;
		}
		
		// Establecer la ubicaci�n para el super-individuo que ha nacido.
		superIndividuo.establecerUbicacion(destino);
		
		// Insertar en el espacio al super-individuo que ha nacido.
		espacio.insertarGrilla(superIndividuo);
		
		// Cambiar el estado.
		estado = SIN_HUEVOS;
		
		// Cambiar el tiempo de portaci�n.
		tiempoPortacion = 0;
		
		return destino;
	}
	
	/**
	 * M�todo que genera la acci�n de morir del super-individuo. Primero se
	 * cambia el estado a muerto, luego al destino y al objetivo se les asigna
	 * el valor de la ubicaci�n. Finalmente el super-individuo muerto intenta
	 * hacer nada.
	 */
	private void morir()
	{
		// Cambiar el estado.
		estado = MUERTO;
		
		// Cambiar el destino.
		destino = ubicacion;
		
		// Cambiar el objetivo.
		objetivo = ubicacion;
		
		// Cambiar la acci�n.
		accion = NADA;
	}
	
	/**
	 * M�todo en donde el super-individuo se auto-eval�a. Es decir, se conoce a
	 * si mismo. En este m�todo se llaman a todos los m�todos que establecen los
	 * valores a todos los atributos del super-individuo.
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 */
	public void autoEvaluar(Espacio espacio)
	{
		// Cuando el super-individuo no est� muerto.
		if (estado != MUERTO)
		{
			evaluarEdad();
			evaluarTalla();
			evaluarPeso();
			evaluarReproduccion();
			evaluarMadurez();
			evaluarHuevos();
			evaluarTiempoPortacion();
			evaluarCantidad();
			evaluarEnergia(espacio);
			evaluarDecadencia();
			evaluarCalidad();
		}
		
		// Cuando el super-individuo est� muerto.
		else descomponer();
		
		// Evaluar la aptitud para los vivos y muertos.
		evaluarAptitud();
	}
	
	/**
	 * M�todo que permite descomponer al super-individuo. Primero se calulca el
	 * factor de descomposici�n por concepto de unidad de tiempo transcurrida.
	 * Luego, se decrementa su peso y su talla.
	 */
	private void descomponer()
	{
		// Cuando el peso es mayor o igual al peso m�nimo y la talla es mayor o
		// igual a la talla m�nima.
		if (peso >= ajuste.obtenerPesoMinimo() &&
			talla >= ajuste.obtenerTallaMinima())
		{
			// El factor de descomposici�n.
			double descomposicion = 0;
			
			// Dependiendo de la unidad de tiempo, se asigna el valor para el
			// factor de descomposici�n.
			switch (ConfiguracionAC.obtenerTiempo())
			{
				// Cuando es Segundo.
				case Tiempo.SEGUNDO:
				descomposicion = ajuste.obtenerDescomposicion()/(24*60*60);
				break;
				
				// Cuando es Minuto.
				case Tiempo.MINUTO:
				descomposicion = ajuste.obtenerDescomposicion()/(24*60);
				break;
				
				// Cuando es Hora.
				case Tiempo.HORA:
				descomposicion = ajuste.obtenerDescomposicion()/24;
				break;
				
				// Cuando es D�a.
				case Tiempo.DIA:
				descomposicion = ajuste.obtenerDescomposicion();
				break;
			}
			
			// Decrementar el peso.
			peso*= descomposicion;
			
			// Decrementar la talla.
			talla*= descomposicion;
		}
		
		// Cuando el peso es menor al peso m�nimo o la talla es menor a la talla
		// m�nima.
		else estado = DESAPARECIDO;
	}
	
	/**
	 * M�todo que eval�a la edad del super-individuo. Dependiendo de la unidad
	 * de tiempo usada, se establece el valor para el incremento de la edad. La
	 * edad del super-individuo est� medida en a�os.
	 */
	private void evaluarEdad()
	{
		// El incremento de la edad.
		double incremento = 0;
		
		// Dependiendo de la unidad de tiempo, se asigna el valor para el
		// incremento de la edad.
		switch (ConfiguracionAC.obtenerTiempo())
		{
			// Cuando es Segundo.
			case Tiempo.SEGUNDO:
			incremento = Servicio.transformarSegundoAnio(1);
			break;
			
			// Cuando es Minuto.
			case Tiempo.MINUTO:
			incremento = Servicio.transformarMinutoAnio(1);
			break;
			
			// Cuando es Hora.
			case Tiempo.HORA:
			incremento = Servicio.transformarHoraAnio(1);
			break;
			
			// Cuando es D�a.
			case Tiempo.DIA:
			incremento = Servicio.transformarDiaAnio(1);
			break;
		}
		
		// Incrementar la edad.
		edad+= incremento;
	}
	
	/**
	 * M�todo que eval�a la talla del super-individuo. Se llama al m�todo que
	 * determina la talla del super-individuo con respecto a su edad. La talla
	 * del super-individuo est� medida en cent�metros.
	 */
	private void evaluarTalla()
	{
		talla = Recurso.calcularTalla(ajuste, edad);
	}
	
	/**
	 * M�todo que eval�a el peso del super-individuo. Se llama al m�todo que
	 * determina el peso del super-individuo con respecto a su edad. El peso del
	 * super-individuo est� medido en gramos.
	 */
	private void evaluarPeso()
	{
		peso = Recurso.calcularPeso(ajuste, talla);
	}
	
	/**
	 * M�todo que eval�a la probabilidad de reproducci�n del super-individuo. Se
	 * llama al m�todo que determina la probabilidad de reproducci�n del
	 * super-individuo con respecto a su talla. La probabilidad de reproducci�n
	 * del super-individuo es un valor entre [0, 1].
	 */
	private void evaluarReproduccion()
	{
		reproduccion = Recurso.calcularReproduccion(ajuste, talla);
	}
	
	/**
	 * M�todo que eval�a la madurez sexual del super-individuo. Se llama al
	 * m�todo que determina el estado de madurez sexual del super-individuo con
	 * respecto a su probabilidad de reproducci�n. La madurez del
	 * super-individuo es un valor entre {1, 2}.
	 */
	private void evaluarMadurez()
	{
		madurez = Recurso.calcularMadurez(ajuste, reproduccion);
	}
	
	/**
	 * M�todo que eval�a el n�mero de huevos del super-individuo. Se llama al
	 * m�todo que determina la fecundiadad del super-individuo con respecto a su
	 * talla. El n�mero de huevos del super-individuo es medido en unidades.
	 */
	private void evaluarHuevos()
	{
		huevos = Recurso.calcularFecundidad(ajuste, talla);
	}
	
	/**
	 * M�todo que eval�a el tiempo de portaci�n del super-individuo. Dependiendo
	 * de la unidad de tiempo usada, se establece el valor para el decremento
	 * del tiempo de portaci�n. El tiempo de portaci�n est� medido en d�as.
	 */
	private void evaluarTiempoPortacion()
	{
		// Cuando es hembra y est� con huevos.
		if (sexo == HEMBRA && estado == CON_HUEVOS)
		{
			// Cuando el tiempo de portaci�n es mayor que cero, entonces
			// decrementar el tiempo de portaci�n.
			if (tiempoPortacion > 0)
			{
				// El decremento del tiempo de portaci�n.
				double decremento = 0;
				
				// Dependiendo de la unidad de tiempo, se asigna el valor para
				// el decremento del tiempo de portaci�n.
				switch (ConfiguracionAC.obtenerTiempo())
				{
					// Cuando es Segundo.
					case Tiempo.SEGUNDO:
					decremento = Servicio.transformarSegundoDia(1);
					break;
					
					// Cuando es Minuto.
					case Tiempo.MINUTO:
					decremento = Servicio.transformarMinutoDia(1);
					break;
					
					// Cuando es Hora.
					case Tiempo.HORA:
					decremento = Servicio.transformarHoraDia(1);
					break;
					
					// Cuando es D�a.
					case Tiempo.DIA:
					decremento = 1;
					break;
				}
				
				// Decrementar el tiempo de portaci�n.
				tiempoPortacion-= decremento;
			}
			
			// Cuando el tiempo de portacci�n es menor o igual que cero,
			// entonces dejar el tiempo de portaci�n en cero.
			else tiempoPortacion = 0;
		}
	}
	
	/**
	 * M�todo que eval�a la cantidad de individuos del super-individuo.
	 * Dependiendo de la unidad de tiempo usada, se establece el valor para el
	 * decremento de la cantidad de individuos. La cantidad est� medida en
	 * unidades.
	 */
	private void evaluarCantidad()
	{
		// Cuando la cantidad es mayor que cero, entonces decrementar la
		// cantidad de individuos.
		if (cantidad > 0)
		{
			// El decremento de la cantidad de individuos por d�a.
			double decremento = 0;
			
			// La tasa de mortalidad por d�a.
			long tasaMortalidad = Recurso.obtenerTasaMortalidad(recurso);
			
			// Dependiendo de la unidad de tiempo, se asigna el valor para el
			// decremento de la cantidad por d�a.
			switch (ConfiguracionAC.obtenerTiempo())
			{
				// Cuando es Segundo.
				case Tiempo.SEGUNDO:
				decremento = tasaMortalidad/Servicio.transformarDiaSegundo(1);
				break;
				
				// Cuando es Minuto.
				case Tiempo.MINUTO:
				decremento = tasaMortalidad/Servicio.transformarDiaMinuto(1);
				break;
				
				// Cuando es Hora.
				case Tiempo.HORA:
				decremento = tasaMortalidad/Servicio.transformarDiaHora(1);
				break;
				
				// Cuando es D�a.
				case Tiempo.DIA:
				decremento = tasaMortalidad;
				break;
			}
			
			// Decrementar la cantidad de individuos.
			cantidad-= decremento;
		}
		
		// Cuando la cantidad es menor o igual a cero.
		else estado = DESAPARECIDO;
	}
	
	/**
	 * M�todo que eval�a la energ�a del super-individuo. Primero se incrementa
	 * la energ�a por concepto de alimentos que se encuentran en el medio
	 * ambiente. Luego, dependiendo de la unidad de tiempo usada, se establece
	 * el valor para el decremento de energ�a del super-individuo. La energ�a
	 * del super-individuo es un valor entre [1, 10].
	 *
	 * @param espacio El espacio discreto de la din�mica.
	 */
	private void evaluarEnergia(Espacio espacio)
	{
		// El peso de los alimentos que se encuentran en el medio ambiente.
		double pesoAlimento = 0;
		
		// Obtener la coordenada de la ubicaci�n.
		Coordenada coordenada = Servicio.transformarCeldaCoordenada(ubicacion,
								espacio.obtenerTransformacion());
		
		// Cuando el super-individuo se alimenta de fitoplancton.
		if (Recurso.obtenerAlimentacionTipoDieta(recurso,
			TipoDieta.FITOPLANCTIVORO).equalsIgnoreCase("Si"))
			pesoAlimento+= Mapa.obtenerFitoplancton(coordenada);
		
		// Cuando el super-individuo se alimenta de zooplancton.
		if (Recurso.obtenerAlimentacionTipoDieta(recurso,
			TipoDieta.ZOOPLANCTIVORO).equalsIgnoreCase("Si"))
			pesoAlimento+= Mapa.obtenerZooplancton(coordenada);
		
		// Cuando el super-individuo se alimenta de detrito o sedimento.
		if (Recurso.obtenerAlimentacionTipoDieta(recurso,
				TipoDieta.DETRITIVORO).equalsIgnoreCase("Si") ||
			Recurso.obtenerAlimentacionTipoDieta(recurso,
				TipoDieta.SEDIMENTIVORO).equalsIgnoreCase("Si"))
			pesoAlimento+= Mapa.obtenerMateriaOrganica(coordenada);
		
		// Incrementar la energ�a por concepto de alimentos que se encuentran en
		// el medio ambiente.
		incrementarEnergia(pesoAlimento);
		
		// Cuando la energ�a es mayor que la energ�a m�nima, entonces
		// decrementar la energ�a.
		if (ajuste.obtenerEnergiaMinima() < energia)
		{
			// El decremento de la energ�a por concepto de tiempo vivido.
			double decremento = 0;
			
			// La energ�a por tiempo.
			double energiaTiempo = ajuste.obtenerEnergiaTiempo();
			
			// Dependiendo de la unidad de tiempo, se asigna el valor para el
			// decremento de la energ�a.
			switch (ConfiguracionAC.obtenerTiempo())
			{
				// Cuando es Segundo.
				case Tiempo.SEGUNDO:
				decremento = Servicio.transformarDiaSegundo(energiaTiempo);
				break;
				
				// Cuando es Minuto.
				case Tiempo.MINUTO:
				decremento = Servicio.transformarDiaMinuto(energiaTiempo);
				break;
				
				// Cuando es Hora.
				case Tiempo.HORA:
				decremento = Servicio.transformarDiaHora(energiaTiempo);
				break;
				
				// Cuando es D�a.
				case Tiempo.DIA:
				decremento = energiaTiempo;
				break;
			}
			
			// Decrementar la energ�a.
			energia-= decremento;
		}
		
		// Cuando la energ�a es menor o igual que la energ�a m�nima, entonces
		// dejar la energ�a en la energ�a m�nima.
		else energia = ajuste.obtenerEnergiaMinima();
	}
	
	/**
	 * M�todo que eval�a la decadencia del super-individuo. Se llama al m�todo
	 * que determina la decadencia del super-individuo con respecto a su edad y
	 * energ�a.
	 */
	private void evaluarDecadencia()
	{
		// Calcular la decadencia m�xima.
		double decadenciaMaxima = Recurso.calcularDecadencia(ajuste,
								  ajuste.obtenerEdadMaxima(),
								  ajuste.obtenerEnergiaMinima());
		
		// Cuando la decadencia m�xima menos la m�nima es menor o igual al valor
		// de muerte por decadencia.
		if ((decadenciaMaxima - decadencia) <= Recurso.MUERTE_DECADENCIA)
			morir();
		
		// Cuando la decadencia m�xima menos la m�nima es mayor al valor de
		// muerte por decadencia.
		else decadencia = Recurso.calcularDecadencia(ajuste, edad, energia);
	}
	
	/**
	 * M�todo que eval�a la aptitud del super-individuo. Se llama al m�todo que
	 * determina la aptitud del super-individuo con respecto a edad, talla y
	 * peso. La aptitud del super-individuo es un valor entre [0, 1].
	 */
	private void evaluarAptitud()
	{
		aptitud = Recurso.calcularAptitud(ajuste, edad, talla, peso);
	}
	
	/**
	 * M�todo que eval�a la calidad del super-individuo. Se llama al m�todo que
	 * determina la calidad del super-individuo con respecto a estado, edad,
	 * talla, peso, reproducci�n, madurez y tiempo de portaci�n. La calidad del
	 * super-individuo es un valor entre [0, 1].
	 */
	private void evaluarCalidad()
	{
		calidad = Recurso.calcularCalidad(ajuste, estado, edad, talla, peso,
										  reproduccion, madurez,
										  tiempoPortacion);
	}
	
	/**
	 * M�todo que incrementa la energ�a por concepto de consumo. La energ�a se
	 * incrementa en las unidades de peso (gramos) consumidas por el
	 * super-individuo.
	 *
	 * @param peso El peso a incrementar en la energ�a.
	 */
	private void incrementarEnergia(double peso)
	{
		// Cuando la energ�a es menor que la energ�a m�xima.
		if (energia < ajuste.obtenerEnergiaMaxima())
			energia+= ajuste.obtenerEnergiaConsumo() * peso;
		
		// Cuando la energ�a es mayor o igual que la energ�a m�xima.
		else energia = ajuste.obtenerEnergiaMaxima();
	}
	
	/**
	 * M�todo que decrementa la energ�a por concepto de desplazamiento. La
	 * energ�a se decrementa en las unidades de distancia (kil�metros)
	 * desplazados por el super-individuo.
	 *
	 * @param distancia La distancia a decrementar en la energ�a.
	 */
	private void decrementarEnergia(double distancia)
	{
		// Cuando la energ�a es mayor que la energ�a m�nima.
		if (ajuste.obtenerEnergiaMinima() < energia)
			energia-= ajuste.obtenerEnergiaDesplazamiento() * distancia;
		
		// Cuando la energ�a es menor o igual que la energ�a m�nima.
		else energia = ajuste.obtenerEnergiaMinima();
	}
	
	/**
	 * M�todo que incrementa el peso por concepto de unidad de peso consumida.
	 * El peso se incrementa en las unidades de peso (gramos) consumidas por el
	 * super-individuo predador.
	 *
	 * @param peso El incremento del peso.
	 */
	private void incrementarPeso(double peso)
	{
		this.peso+= peso;
	}
	
	/**
	 * M�todo que decrementa el peso por concepto de unidad de peso consumida
	 * por un predador. El peso se decrementa en las unidades de peso (gramos)
	 * consumidas por el super-individuo predador.
	 *
	 * @param peso El decremento del peso.
	 */
	private void decrementarPeso(double peso)
	{
		this.peso-= peso;
	}
	
	/**
	 * M�todo que decrementa la talla por concepto de unidad de talla consumida
	 * por un predador. La talla se decrementa en las unidades de talla
	 * (cent�metros) consumidas por el super-individuo predador.
	 *
	 * @param talla El decremento de la talla.
	 */
	private void decrementarTalla(double talla)
	{
		this.talla-= talla;
	}
	
	/**
	 * M�todo que obtiene el valor del tipo de recurso que es el
	 * super-individuo.
	 *
	 * @return recurso El valor del atributo recurso.
	 */
	public int obtenerRecurso()
	{
		return recurso;
	}
	
	/**
	 * M�todo que obtiene el valor del tipo de animal que es el super-individuo.
	 *
	 * @return animal El valor del atributo animal.
	 */
	public int obtenerAnimal()
	{
		return animal;
	}
	
	/**
	 * M�todo que obtiene el valor del tipo de sexo que es el super-individuo.
	 *
	 * @return sexo El valor del atributo sexo.
	 */
	public int obtenerSexo()
	{
		return sexo;
	}
	
	/**
	 * M�todo que obtiene el valor del estado actual del super-individuo.
	 *
	 * @return estado El valor del atributo estado.
	 */
	public int obtenerEstado()
	{
		return estado;
	}
	
	/**
	 * M�todo que obtiene el valor de la edad que tiene el super-individuo.
	 *
	 * @return edad El valor del atributo edad.
	 */
	public double obtenerEdad()
	{
		return edad;
	}
	
	/**
	 * M�todo que obtiene el valor de la talla que tiene el super-individuo.
	 *
	 * @return talla El valor del atributo talla.
	 */
	public double obtenerTalla()
	{
		return talla;
	}
	
	/**
	 * M�todo que obtiene el valor del peso que tiene el super-individuo.
	 *
	 * @return peso El valor del atributo peso.
	 */
	public double obtenerPeso()
	{
		return peso;
	}
	
	/**
	 * M�todo que obtiene el valor de la probabilidad de reproducci�n que tiene
	 * el super-individuo.
	 *
	 * @return reproduccion El valor del atributo reproduccion.
	 */
	public double obtenerReproduccion()
	{
		return reproduccion;
	}
	
	/**
	 * M�todo que obtiene el valor del estado de madurez sexual que tiene el
	 * super-individuo.
	 *
	 * @return madurez El valor del atributo madurez.
	 */
	public int obtenerMadurez()
	{
		return madurez;
	}
	
	/**
	 * M�todo que obtiene el valor del n�mero de nuevos que puede desovar el
	 * super-individuo.
	 *
	 * @return huevos El valor del atributo huevos.
	 */
	public long obtenerHuevos()
	{
		return huevos;
	}
	
	/**
	 * M�todo que obtiene el valor del tiempo que le queda para desovar al
	 * super-individuo.
	 *
	 * @return tiempoPortacion El valor del atributo tiempoPortacion.
	 */
	public double obtenerTiempoPortacion()
	{
		return tiempoPortacion;
	}
	
	/**
	 * M�todo que obtiene el valor de la cantidad de individuos que tiene el
	 * super-individuo.
	 *
	 * @return cantidad El valor del atributo cantidad.
	 */
	public long obtenerCantidad()
	{
		return cantidad;
	}
	
	/**
	 * M�todo que obtiene el valor de la energ�a actual que tiene el
	 * super-individuo.
	 *
	 * @return energia El valor del atributo energia.
	 */
	public double obtenerEnergia()
	{
		return energia;
	}
	
	/**
	 * M�todo que obtiene el valor de la decadencia actual que tiene el
	 * super-individuo.
	 *
	 * @return decadencia El valor del atributo decadencia.
	 */
	public double obtenerDecadencia()
	{
		return decadencia;
	}
	
	/**
	 * M�todo que establece el valor de la celda de ubicaci�n del
	 * super-individuo.
	 *
	 * @param ubicacion El valor para el atributo ubicacion.
	 */
	public void establecerUbicacion(Celda ubicacion)
	{
		this.ubicacion = ubicacion;
	}
	
	/**
	 * M�todo que obtiene el valor de la celda de ubicaci�n del super-individuo.
	 *
	 * @return ubicacion El valor del atributo ubicacion.
	 */
	public Celda obtenerUbicacion()
	{
		return ubicacion;
	}
	
	/**
	 * M�todo que establece el valor de la celda destino del super-individuo.
	 *
	 * @param destino El valor para el atributo destino.
	 */
	public void establecerDestino(Celda destino)
	{
		this.destino = destino;
	}
	
	/**
	 * M�todo que obtiene el valor de la celda destino del super-individuo.
	 *
	 * @return destino El valor del atributo destino.
	 */
	public Celda obtenerDestino()
	{
		return destino;
	}
	
	/**
	 * M�todo que establece el valor de la celda de objetivo del
	 * super-individuo.
	 *
	 * @param objetivo El valor para el atributo objetivo.
	 */
	public void establecerObjetivo(Celda objetivo)
	{
		this.objetivo = objetivo;
	}
	
	/**
	 * M�todo que obtiene el valor de la celda de objetivo del super-individuo.
	 *
	 * @return objetivo El valor del atributo objetivo.
	 */
	public Celda obtenerObjetivo()
	{
		return objetivo;
	}
	
	/**
	 * M�todo que obtiene el valor de la vecindad de percepci�n del
	 * super-individuo.
	 *
	 * @return percepcion El valor del atributo percepcion.
	 */
	public Vecindad obtenerPercepcion()
	{
		return percepcion;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo deseo del super-individuo con un
	 * �ndice conocido.
	 *
	 * @param i El �ndice del arreglo deseo.
	 *
	 * @return deseo[i] El valor del atributo deseo[i].
	 */
	public double obtenerDeseo(int i)
	{
		return deseo[i];
	}
	
	/**
	 * M�todo que obtiene el valor del atributo intencion del super-individuo
	 * con un �ndice conocido.
	 *
	 * @param i El �ndice del arreglo intencion.
	 *
	 * @return intencion[i] El valor del atributo intencion[i].
	 */
	public int obtenerIntencion(int i)
	{
		return intencion[i];
	}
	
	/**
	 * M�todo que establece el valor de la acci�n del super-individuo.
	 *
	 * @param accion El valor para el atributo accion.
	 */
	public void establecerAccion(int accion)
	{
		this.accion = accion;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo accion del super-individuo.
	 *
	 * @return accion El valor del atributo accion.
	 */
	public int obtenerAccion()
	{
		return accion;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo aptitud del super-individuo.
	 *
	 * @return aptitud El valor del atributo aptitud.
	 */
	public double obtenerAptitud()
	{
		return aptitud;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo calidad del super-individuo.
	 *
	 * @return calidad El valor del atributo calidad.
	 */
	public double obtenerCalidad()
	{
		return calidad;
	}
	
	/**
	 * M�todo que entrega un string con todos los atributos de la clase.
	 *
	 * @return string Los atributos de la clase compuestos como un string.
	 */
	public String toString()
	{
		// Las variables de texto.
		String texto = "";
		String textoRecurso = "";
		String textoAnimal = "";
		String textoSexo = "";
		String textoEstado = "";
		String textoMadurez = "";
		String textoAccion = "";
		
		// Texto del tipo de recurso.
		switch (recurso)
		{
			case Recurso.CAMARON_NAILON:
			textoRecurso = "Camaron Nailon";
			break;
			
			case Recurso.LANGOSTINO_AMARILLO:
			textoRecurso = "Langostino Amarillo";
			break;
			
			case Recurso.LANGOSTINO_COLORADO:
			textoRecurso = "Langostino Colorado";
			break;
			
			case Recurso.MERLUZA_COMUN:
			textoRecurso = "Merluza Comun";
			break;
		}
		
		// Texto del tipo de animal.
		switch (animal)
		{
			case TipoAnimal.CRUSTACEO:
			textoAnimal = "Crustaceo";
			break;
			
			case TipoAnimal.PEZ:
			textoAnimal = "Pez";
			break;
		}
		
		// Texto del tipo de sexo.
		switch (sexo)
		{
			case MACHO:
			textoSexo = "Macho";
			break;
			
			case HEMBRA:
			textoSexo = "Hembra";
			break;
		}
		
		// Texto del tipo de estado.
		switch (estado)
		{
			case DESAPARECIDO:
			textoEstado = "Desaparecido";
			break;
			
			case MUERTO:
			textoEstado = "Muerto";
			break;
			
			case SIN_HUEVOS:
			textoEstado = "Sin Huevos";
			break;
			
			case CON_HUEVOS:
			textoEstado = "Con Huevos";
			break;
		}
		
		// Texto del tipo de madurez.
		switch (madurez)
		{
			case MADURO:
			textoMadurez = "Maduro";
			break;
			
			case INMADURO:
			textoMadurez = "Inmaduro";
			break;
		}
		
		// Texto de la acci�n.
		switch (accion)
		{
			case ARRANCAR:
			textoAccion = "Arrancar";
			break;
			
			case PREDAR:
			textoAccion = "Predar";
			break;
			
			case REPRODUCIR:
			textoAccion = "Reproducir";
			break;
			
			case DESOVAR:
			textoAccion = "Desovar";
			break;
			
			case CONVIVIR:
			textoAccion = "Convivir";
			break;
			
			case MIGRAR:
			textoAccion = "Migrar";
			break;
			
			case DIVAGAR:
			textoAccion = "Divagar";
			break;
			
			case NADA:
			textoAccion = "Nada";
			break;
		}
		
		texto+= "Recurso:"+textoRecurso+" ";
		texto+= "Animal:"+textoAnimal+" ";
		texto+= "Sexo:"+textoSexo+" ";
		texto+= "Estado:"+textoEstado+" ";
		texto+= "Edad:"+edad+" ";
		texto+= "Talla:"+talla+" ";
		texto+= "Peso:"+peso+" ";
		texto+= "Reproduccion:"+reproduccion+" ";
		texto+= "Madurez:"+textoMadurez+" ";
		texto+= "Huevos:"+huevos+" ";
		texto+= "Tiempo Portacion:"+tiempoPortacion+" ";
		texto+= "Cantidad:"+cantidad+" ";
		texto+= "Energia:"+energia+" ";
		texto+= "Decadencia:"+decadencia+" ";
		texto+= "Ubicacion:"+ubicacion+" ";
		texto+= "Destino:"+destino+" ";
		texto+= "Objetivo:"+objetivo+" ";
		texto+= "Deseo[Arrancar]:"+deseo[ARRANCAR]+" ";
		texto+= "Deseo[Predar]:"+deseo[PREDAR]+" ";
		texto+= "Deseo[Reproducir]:"+deseo[REPRODUCIR]+" ";
		texto+= "Deseo[Desovar]:"+deseo[DESOVAR]+" ";
		texto+= "Deseo[Convivir]:"+deseo[CONVIVIR]+" ";
		texto+= "Deseo[Migrar]:"+deseo[MIGRAR]+" ";
		texto+= "Deseo[Divagar]:"+deseo[DIVAGAR]+" ";
		texto+= "Deseo[Nada]:"+deseo[NADA]+" ";
		texto+= "Accion:"+textoAccion+" ";
		texto+= "Aptitud:"+aptitud+" ";
		texto+= "Calidad:"+calidad;
		
		return texto;
	}
}