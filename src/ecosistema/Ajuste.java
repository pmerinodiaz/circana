/**
 * @(#)Ajuste.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

/**
 * Clase que contiene las variables y m�todos que se utilizan para ajustar el
 * comportamiento de los super-individuos a los datos ecosist�micos reales que
 * est�n contenidos en la tabla recurso de la base de datos. Estas variables de
 * ajuste son consideradas, en la mayor�a de los casos, como aleatorias y
 * permiten que los super-individuos de un mismo recurso sean diferentes entre
 * si, sin dejar de considerar las caracter�sticas gen�ticas propias que tiene
 * el recurso. Estas variables de ajuste tambi�n permiten ajustar el modelo a
 * las caracter�sticas de temporada en que han sido creados los super-individuos
 * en el ecosistema.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Servicio
 * @see Recurso
 * @see SuperIndividuo
 */
public class Ajuste
{
	/**
	 * El valor que indica el porcentaje de tendencia hacia los datos
	 * reales (valor entre [0, 1]).
	 */
	private static final double TENDENCIA_DATO_REAL = 0.9;
	
	/** El ajuste para el sexo (valor entre {1, 2}). */
	private int sexo;
	
	/** El ajuste para el estado (valor entre {1, 2, 3, 4}). */
	private int estado;
	
	/** El ajuste para la edad m�xima (valor en a�os). */
	private double edadMaxima;
	
	/** El ajuste para el par�metro Loo (valor en cent�metros). */
	private double parametroLoo;
	
	/** El ajuste para el par�metro To (valor en a�os). */
	private double parametroTo;
	
	/** El ajuste para el par�metro K (valor en 1/a�os). */
	private double parametroK;
	
	/** El ajuste para el par�metro A. */
	private double parametroA;
	
	/** El ajuste para el par�metro B. */
	private double parametroB;
	
	/** El ajuste para la talla m�nima (valor en cent�metros). */
	private double tallaMinima;
	
	/** El ajuste para la talla m�xima (valor en cent�metros). */
	private double tallaMaxima;
	
	/** El ajuste para la talla (valor en cent�metros). */
	private double talla;
	
	/** El ajuste para el peso m�nimo (valor en gramos). */
	private double pesoMinimo;
	
	/** El ajuste para el peso m�ximo (valor en gramos). */
	private double pesoMaximo;
	
	/** El ajuste para el peso (valor en gramos). */
	private double peso;
	
	/** El ajuste para la cantidad (valor en unidades). */
	private long cantidad;
	
	/** El ajuste para el par�metro S1. */
	private double parametroS1;
	
	/** El ajuste para el par�metro S2. */
	private double parametroS2;
	
	/** El ajuste para la madurez sexual (valor entre [0, 1]). */
	private double madurezSexual;
	
	/** El ajuste para el n�mero de huevos m�nimos (valor en unidades). */
	private long huevosMinimo;
	
	/** El ajuste para el n�mero de huevos m�ximos (valor en unidades). */
	private long huevosMaximo;
	
	/** El ajuste para el periodo de portaci�n (valor en d�as). */
	private int periodoPortacion;
	
	/** El ajuste para la latitud inicial (valor en grados). */
	private double latitudInicial;
	
	/** El ajuste para la latitud final (valor en grados). */
	private double latitudFinal;
	
	/** El ajuste para la altitud inicial (valor en metros). */
	private double altitudInicial;
	
	/** El ajuste para la altitud final (valor en metros). */
	private double altitudFinal;
	
	/** El ajuste para la temperatura inicial (valor en �C). */
	private double temperaturaInicial;
	
	/** El ajuste para la temperatura final (valor en �C). */
	private double temperaturaFinal;
	
	/** El ajuste para la salinidad inicial (valor en psu). */
	private double salinidadInicial;
	
	/** El ajuste para la salinidad final (valor en psu). */
	private double salinidadFinal;
	
	/** El ajuste para el ox�geno disuelto inicial (valor en ml/litros). */
	private double oxigenoDisueltoInicial;
	
	/** El ajuste para el ox�geno disuelto final (valor en ml/litros). */
	private double oxigenoDisueltoFinal;
	
	/** El ajuste para la percepci�n (valor en metros/segundos). */
	private double percepcion;
	
	/** El ajuste para la velocidad (valor en metros/segundos). */
	private double velocidad;
	
	/** El ajuste para la energ�a inicial (valor entre [1, 10]). */
	private double energiaInicial;
	
	/** El ajuste para la energ�a m�nima (valor entre [1, 10]). */
	private double energiaMinima;
	
	/** El ajuste para la energ�a m�xima (valor entre [1, 10]). */
	private double energiaMaxima;
	
	/**
	 * El ajuste para la energ�a por unidad de tiempo (valor en energ�a/d�as).
	 */
	private double energiaTiempo;
	
	/**
	 * El ajuste para la energ�a por unidad de consumo (valor en
	 * energ�a/gramos).
	 */
	private double energiaConsumo;
	
	/**
	 * El ajuste para la energ�a por unidad de desplazamiento (valor en
	 * energ�a/kil�metros).
	 */
	private double energiaDesplazamiento;
	
	/** El ajuste para la descomposici�n (valor entre [0, 1]). */
	private double descomposicion;
	
	/** El ajuste para la tendencia de arrancar (valor entre [0, 1]). */
	private double tendenciaArrancar;
	
	/** El ajuste para la tendencia de predar (valor entre [0, 1]). */
	private double tendenciaPredar;
	
	/** El ajuste para la tendencia de reproducir (valor entre [0, 1]). */
	private double tendenciaReproducir;
	
	/** El ajuste para la tendencia de desovar (valor entre [0, 1]). */
	private double tendenciaDesovar;
	
	/** El ajuste para la tendencia de convivir (valor entre [0, 1]). */
	private double tendenciaConvivir;
	
	/** El ajuste para la tendencia de migrar (valor entre [0, 1]). */
	private double tendenciaMigrar;
	
	/** El ajuste para la tendencia de divagar (valor entre [0, 1]). */
	private double tendenciaDivagar;
	
	/** El ajuste para la tendencia de nada (valor entre [0, 1]). */
	private double tendenciaNada;
	
	/**
	 * M�todo constructor en donde se inicializan los valores de las variables
	 * de ajuste. Este constructor se utiliza cuando los super-individuos nacen,
	 * por lo que el sexo y el estado no son conocidos. El sexo es obtenido en
	 * forma aleatoria y el estado corresponde al estado inicial del
	 * super-individuo. Lo que s� se conoce del super-individuo es el tipo de
	 * recurso y la cantidad de individuos que contiene.
	 *
	 * @param recurso El tipo de recurso del super-individuo.
	 * @param cantidad El valor de la cantidad de individuos del
	 *                 super-individuo.
	 */
	public Ajuste(int recurso, long cantidad)
	{
		// Obtener un valor aleatorio considerando la tendencia al dato real.
		double azar = Servicio.obtenerAzar(TENDENCIA_DATO_REAL,
										   2 - TENDENCIA_DATO_REAL);
		
		// Establecer el sexo en forma aleatoria.
		this.sexo = Servicio.obtenerAzar(SuperIndividuo.MACHO,
										 SuperIndividuo.HEMBRA);
		
		// Dependiendo del sexo, establecer los valores a las variables de
		// ajuste en forma aleatoria, considerando las caracter�sticas gen�ticas
		// propias del recurso.
		switch (this.sexo)
		{
			// Cuando es Macho.
			case SuperIndividuo.MACHO:
			this.edadMaxima = Recurso.obtenerEdadMaximaMacho(recurso)*azar;
			this.parametroLoo = Recurso.obtenerParametroLooMacho(recurso)*azar;
			this.parametroTo = Recurso.obtenerParametroToMacho(recurso)*azar;
			this.parametroK = Recurso.obtenerParametroKMacho(recurso)*azar;
			this.parametroA = Recurso.obtenerParametroAMacho(recurso)*azar;
			this.parametroB = Recurso.obtenerParametroBMacho(recurso)*azar;
			this.tallaMinima = Recurso.obtenerTallaMinimaMacho(recurso)*azar;
			this.tallaMaxima = Recurso.obtenerTallaMaximaMacho(recurso)*azar;
			this.pesoMinimo = Recurso.obtenerPesoMinimoMacho(recurso)*azar;
			this.pesoMaximo = Recurso.obtenerPesoMaximoMacho(recurso)*azar;
			this.madurezSexual = 0;
			break;
			
			// Cuando es Hembra.
			case SuperIndividuo.HEMBRA:
			this.edadMaxima = Recurso.obtenerEdadMaximaHembra(recurso)*azar;
			this.parametroLoo = Recurso.obtenerParametroLooHembra(recurso)*azar;
			this.parametroTo = Recurso.obtenerParametroToHembra(recurso)*azar;
			this.parametroK = Recurso.obtenerParametroKHembra(recurso)*azar;
			this.parametroA = Recurso.obtenerParametroAHembra(recurso)*azar;
			this.parametroB = Recurso.obtenerParametroBHembra(recurso)*azar;
			this.tallaMinima = Recurso.obtenerTallaMinimaHembra(recurso)*azar;
			this.tallaMaxima = Recurso.obtenerTallaMaximaHembra(recurso)*azar;
			this.pesoMinimo = Recurso.obtenerPesoMinimoHembra(recurso)*azar;
			this.pesoMaximo = Recurso.obtenerPesoMaximoHembra(recurso)*azar;
			this.madurezSexual = Servicio.obtenerAzar(0, 1,
								 Recurso.obtenerMadurezSexual(recurso),
								 TENDENCIA_DATO_REAL);
			break;
		}
		this.estado = SuperIndividuo.SIN_HUEVOS;
		this.parametroS1 = Recurso.obtenerParametroS1(recurso)*azar;
		this.parametroS2 = Recurso.obtenerParametroS2(recurso)*azar;
		this.huevosMinimo = (long)(Recurso.obtenerHuevosMinimo(recurso)*azar);
		this.huevosMaximo = (long)(Recurso.obtenerHuevosMaximo(recurso)*azar);
		this.periodoPortacion = (int)
								(Recurso.obtenerPeriodoPortacion(recurso)*azar);
		this.cantidad = cantidad;
		this.latitudInicial = Recurso.obtenerLatitudInicial(recurso)*azar;
		this.latitudFinal = Recurso.obtenerLatitudFinal(recurso)*azar;
		this.altitudInicial = Recurso.obtenerAltitudInicial(recurso)*azar;
		this.altitudFinal = Recurso.obtenerAltitudFinal(recurso)*azar;
		this.temperaturaInicial = Recurso.obtenerTemperaturaInicial(recurso)*
								  azar;
		this.temperaturaFinal = Recurso.obtenerTemperaturaFinal(recurso)*azar;
		this.salinidadInicial = Recurso.obtenerSalinidadInicial(recurso)*azar;
		this.salinidadFinal = Recurso.obtenerSalinidadFinal(recurso)*azar;
		this.oxigenoDisueltoInicial =
							Recurso.obtenerOxigenoDisueltoInicial(recurso)*azar;
		this.oxigenoDisueltoFinal =
							Recurso.obtenerOxigenoDisueltoFinal(recurso)*azar;
		this.percepcion = Recurso.obtenerPercepcion(recurso)*azar;
		this.velocidad = Recurso.obtenerVelocidad(recurso)*azar;
		this.energiaInicial = Servicio.obtenerAzar(1, 10,
							  Recurso.obtenerEnergiaInicial(recurso),
							  TENDENCIA_DATO_REAL);
		this.energiaMinima = Servicio.obtenerAzar(1, 10,
							 Recurso.obtenerEnergiaMinima(recurso),
							 TENDENCIA_DATO_REAL);
		this.energiaMaxima = Servicio.obtenerAzar(1, 10,
							 Recurso.obtenerEnergiaMaxima(recurso),
							 TENDENCIA_DATO_REAL);
		this.energiaTiempo = Recurso.obtenerEnergiaTiempo(recurso)*azar;
		this.energiaConsumo = Recurso.obtenerEnergiaConsumo(recurso)*azar;
		this.energiaDesplazamiento =
							Recurso.obtenerEnergiaDesplazamiento(recurso)*azar;
		this.descomposicion = Recurso.obtenerDescomposicion(recurso)*azar;
		this.tendenciaArrancar = Servicio.obtenerAzar(0, 1,
								 Recurso.obtenerTendenciaArrancar(recurso),
								 TENDENCIA_DATO_REAL);
		this.tendenciaPredar = Servicio.obtenerAzar(0, 1,
							   Recurso.obtenerTendenciaPredar(recurso),
							   TENDENCIA_DATO_REAL);
		this.tendenciaReproducir = Servicio.obtenerAzar(0, 1,
								   Recurso.obtenerTendenciaReproducir(recurso),
								   TENDENCIA_DATO_REAL);
		this.tendenciaDesovar = Servicio.obtenerAzar(0, 1,
								Recurso.obtenerTendenciaDesovar(recurso),
								TENDENCIA_DATO_REAL);
		this.tendenciaConvivir = Servicio.obtenerAzar(0, 1,
								 Recurso.obtenerTendenciaConvivir(recurso),
								 TENDENCIA_DATO_REAL);
		this.tendenciaMigrar = Servicio.obtenerAzar(0, 1,
							   Recurso.obtenerTendenciaMigrar(recurso),
							   TENDENCIA_DATO_REAL);
		this.tendenciaDivagar = Servicio.obtenerAzar(0, 1,
								Recurso.obtenerTendenciaDivagar(recurso),
								TENDENCIA_DATO_REAL);
		this.tendenciaNada = Servicio.obtenerAzar(0, 1,
							 Recurso.obtenerTendenciaNada(recurso),
							 TENDENCIA_DATO_REAL);
	}
	
	/**
	 * M�todo constructor en donde se inicializan los valores de las variables
	 * de ajuste. Este constructor se utilizada cuando los super-individuos son
	 * creados con una edad ya conocida. Es decir, los atributos de sexo,
	 * estado, talla, peso y cantidad de individuos del super-individuo ya han
	 * sido determinados. A�n as�, las variables de ajuste se obtienen en forma
	 * aletaria, pero considerando estos datos reales.
	 *
	 * @param recurso El tipo de recurso del super-individuo.
	 * @param sexo El tipo de sexo del super-individuo.
	 * @param estado El tipo de estado del super-individuo.
	 * @param talla El valor de la talla del super-individuo.
	 * @param peso El valor del peso del super-individuo.
	 * @param cantidad El valor de la cantidad de individuos del
	 *                 super-individuo.
	 */
	public Ajuste(int recurso, int sexo, int estado, double talla, double peso,
				  long cantidad)
	{
		// Obtener un valor aleatorio considerando la tendencia al dato real.
		double azar = Servicio.obtenerAzar(TENDENCIA_DATO_REAL,
										   2 - TENDENCIA_DATO_REAL);
		
		// Establecer el sexo en forma aleatoria con tendencia al dato real.
		this.sexo = Servicio.obtenerAzar(SuperIndividuo.MACHO,
										 SuperIndividuo.HEMBRA,
										 sexo, TENDENCIA_DATO_REAL);
		
		// Dependiendo del sexo, establecer los valores a las variables de
		// ajuste en forma aleatoria con tendencia al dato real y considerando
		// las caracter�sticas gen�ticas propias del recurso.
		switch (this.sexo)
		{
			// Cuando es Macho.
			case SuperIndividuo.MACHO:
			this.estado = SuperIndividuo.SIN_HUEVOS;
			this.edadMaxima = Recurso.obtenerEdadMaximaMacho(recurso)*azar;
			this.parametroLoo = Recurso.obtenerParametroLooMacho(recurso)*azar;
			this.parametroTo = Recurso.obtenerParametroToMacho(recurso)*azar;
			this.parametroK = Recurso.obtenerParametroKMacho(recurso)*azar;
			this.parametroA = Recurso.obtenerParametroAMacho(recurso)*azar;
			this.parametroB = Recurso.obtenerParametroBMacho(recurso)*azar;
			this.tallaMinima = Recurso.obtenerTallaMinimaMacho(recurso)*azar;
			this.tallaMaxima = Recurso.obtenerTallaMaximaMacho(recurso)*azar;
			this.pesoMinimo = Recurso.obtenerPesoMinimoMacho(recurso)*azar;
			this.pesoMaximo = Recurso.obtenerPesoMaximoMacho(recurso)*azar;
			this.madurezSexual = 0;
			break;
			
			// Cuando es Hembra.
			case SuperIndividuo.HEMBRA:
			this.estado = Servicio.obtenerAzar(SuperIndividuo.SIN_HUEVOS,
											   SuperIndividuo.CON_HUEVOS,
											   estado, TENDENCIA_DATO_REAL);
			this.edadMaxima = Recurso.obtenerEdadMaximaHembra(recurso)*azar;
			this.parametroLoo = Recurso.obtenerParametroLooHembra(recurso)*azar;
			this.parametroTo = Recurso.obtenerParametroToHembra(recurso)*azar;
			this.parametroK = Recurso.obtenerParametroKHembra(recurso)*azar;
			this.parametroA = Recurso.obtenerParametroAHembra(recurso)*azar;
			this.parametroB = Recurso.obtenerParametroBHembra(recurso)*azar;
			this.tallaMinima = Recurso.obtenerTallaMinimaHembra(recurso)*azar;
			this.tallaMaxima = Recurso.obtenerTallaMaximaHembra(recurso)*azar;
			this.pesoMinimo = Recurso.obtenerPesoMinimoHembra(recurso)*azar;
			this.pesoMaximo = Recurso.obtenerPesoMaximoHembra(recurso)*azar;
			this.madurezSexual = Servicio.obtenerAzar(0, 1,
								 Recurso.obtenerMadurezSexual(recurso),
								 TENDENCIA_DATO_REAL);
			break;
		}
		this.talla = Servicio.obtenerAzar(tallaMinima, tallaMaxima, talla,
										  TENDENCIA_DATO_REAL);
		this.peso = Servicio.obtenerAzar(pesoMinimo, pesoMaximo, peso,
										 TENDENCIA_DATO_REAL);
		this.parametroS1 = Recurso.obtenerParametroS1(recurso)*azar;
		this.parametroS2 = Recurso.obtenerParametroS2(recurso)*azar;
		this.huevosMinimo = (long)(Recurso.obtenerHuevosMinimo(recurso)*azar);
		this.huevosMaximo = (long)(Recurso.obtenerHuevosMaximo(recurso)*azar);
		this.periodoPortacion = (int)
								(Recurso.obtenerPeriodoPortacion(recurso)*azar);
		this.cantidad = cantidad;
		this.latitudInicial = Recurso.obtenerLatitudInicial(recurso)*azar;
		this.latitudFinal = Recurso.obtenerLatitudFinal(recurso)*azar;
		this.altitudInicial = Recurso.obtenerAltitudInicial(recurso)*azar;
		this.altitudFinal = Recurso.obtenerAltitudFinal(recurso)*azar;
		this.temperaturaInicial = Recurso.obtenerTemperaturaInicial(recurso)*
								  azar;
		this.temperaturaFinal = Recurso.obtenerTemperaturaFinal(recurso)*azar;
		this.salinidadInicial = Recurso.obtenerSalinidadInicial(recurso)*azar;
		this.salinidadFinal = Recurso.obtenerSalinidadFinal(recurso)*azar;
		this.oxigenoDisueltoInicial =
							Recurso.obtenerOxigenoDisueltoInicial(recurso)*azar;
		this.oxigenoDisueltoFinal =
							Recurso.obtenerOxigenoDisueltoFinal(recurso)*azar;
		this.percepcion = Recurso.obtenerPercepcion(recurso)*azar;
		this.velocidad = Recurso.obtenerVelocidad(recurso)*azar;
		this.energiaInicial = Servicio.obtenerAzar(1, 10,
							  Recurso.obtenerEnergiaInicial(recurso),
							  TENDENCIA_DATO_REAL);
		this.energiaMinima = Servicio.obtenerAzar(1, 10,
							 Recurso.obtenerEnergiaMinima(recurso),
							 TENDENCIA_DATO_REAL);
		this.energiaMaxima = Servicio.obtenerAzar(1, 10,
							 Recurso.obtenerEnergiaMaxima(recurso),
							 TENDENCIA_DATO_REAL);
		this.energiaTiempo = Recurso.obtenerEnergiaTiempo(recurso)*azar;
		this.energiaConsumo = Recurso.obtenerEnergiaConsumo(recurso)*azar;
		this.energiaDesplazamiento =
							Recurso.obtenerEnergiaDesplazamiento(recurso)*azar;
		this.descomposicion = Recurso.obtenerDescomposicion(recurso)*azar;
		this.tendenciaArrancar = Servicio.obtenerAzar(0, 1,
								 Recurso.obtenerTendenciaArrancar(recurso),
								 TENDENCIA_DATO_REAL);
		this.tendenciaPredar = Servicio.obtenerAzar(0, 1,
							   Recurso.obtenerTendenciaPredar(recurso),
							   TENDENCIA_DATO_REAL);
		this.tendenciaReproducir = Servicio.obtenerAzar(0, 1,
								   Recurso.obtenerTendenciaReproducir(recurso),
								   TENDENCIA_DATO_REAL);
		this.tendenciaDesovar = Servicio.obtenerAzar(0, 1,
								Recurso.obtenerTendenciaDesovar(recurso),
								TENDENCIA_DATO_REAL);
		this.tendenciaConvivir = Servicio.obtenerAzar(0, 1,
								 Recurso.obtenerTendenciaConvivir(recurso),
								 TENDENCIA_DATO_REAL);
		this.tendenciaMigrar = Servicio.obtenerAzar(0, 1,
							   Recurso.obtenerTendenciaMigrar(recurso),
							   TENDENCIA_DATO_REAL);
		this.tendenciaDivagar = Servicio.obtenerAzar(0, 1,
								Recurso.obtenerTendenciaDivagar(recurso),
								TENDENCIA_DATO_REAL);
		this.tendenciaNada = Servicio.obtenerAzar(0, 1,
							 Recurso.obtenerTendenciaNada(recurso),
							 TENDENCIA_DATO_REAL);
	}
	
	/**
	 * M�todo que obtiene el valor del atributo sexo.
	 *
	 * @return sexo El valor que tiene el atributo sexo.
	 */
	public int obtenerSexo()
	{
		return sexo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo estado.
	 *
	 * @return estado El valor que tiene el atributo estado.
	 */
	public int obtenerEstado()
	{
		return estado;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo edadMaxima.
	 *
	 * @return edadMaxima El valor que tiene el atributo edadMaxima.
	 */
	public double obtenerEdadMaxima()
	{
		return edadMaxima;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroLoo.
	 *
	 * @return parametroLoo El valor que tiene el atributo parametroLoo.
	 */
	public double obtenerParametroLoo()
	{
		return parametroLoo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroTo.
	 *
	 * @return parametroTo El valor que tiene el atributo parametroTo.
	 */
	public double obtenerParametroTo()
	{
		return parametroTo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroK.
	 *
	 * @return parametroK El valor que tiene el atributo parametroK.
	 */
	public double obtenerParametroK()
	{
		return parametroK;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroA.
	 *
	 * @return parametroA El valor que tiene el atributo parametroA.
	 */
	public double obtenerParametroA()
	{
		return parametroA;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroB.
	 *
	 * @return parametroB El valor que tiene el atributo parametroB.
	 */
	public double obtenerParametroB()
	{
		return parametroB;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaMinima.
	 *
	 * @return tallaMinima El valor que tiene el atributo tallaMinima.
	 */
	public double obtenerTallaMinima()
	{
		return tallaMinima;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tallaMaxima.
	 *
	 * @return tallaMaxima El valor que tiene el atributo tallaMaxima.
	 */
	public double obtenerTallaMaxima()
	{
		return tallaMaxima;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo talla.
	 *
	 * @return talla El valor que tiene el atributo talla.
	 */
	public double obtenerTalla()
	{
		return talla;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoMinimo.
	 *
	 * @return pesoMinimo El valor que tiene el atributo pesoMinimo.
	 */
	public double obtenerPesoMinimo()
	{
		return pesoMinimo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo pesoMaximo.
	 *
	 * @return pesoMaximo El valor que tiene el atributo pesoMaximo.
	 */
	public double obtenerPesoMaximo()
	{
		return pesoMaximo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo peso.
	 *
	 * @return peso El valor que tiene el atributo peso.
	 */
	public double obtenerPeso()
	{
		return peso;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroS1.
	 *
	 * @return parametroS1 El valor que tiene el atributo parametroS1.
	 */
	public double obtenerParametroS1()
	{
		return parametroS1;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo parametroS2.
	 *
	 * @return parametroS2 El valor que tiene el atributo parametroS2.
	 */
	public double obtenerParametroS2()
	{
		return parametroS2;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo madurezSexual.
	 *
	 * @return madurezSexual El valor que tiene el atributo madurezSexual.
	 */
	public double obtenerMadurezSexual()
	{
		return madurezSexual;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo huevosMinimo.
	 *
	 * @return huevosMinimo El valor que tiene el atributo huevosMinimo.
	 */
	public long obtenerHuevosMinimo()
	{
		return huevosMinimo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo huevosMaximo.
	 *
	 * @return huevosMaximo El valor que tiene el atributo huevosMaximo.
	 */
	public long obtenerHuevosMaximo()
	{
		return huevosMaximo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo periodoPortacion.
	 *
	 * @return periodoPortacion El valor que tiene el atributo periodoPortacion.
	 */
	public int obtenerPeriodoPortacion()
	{
		return periodoPortacion;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo cantidad.
	 *
	 * @return cantidad El valor que tiene el atributo cantidad.
	 */
	public long obtenerCantidad()
	{
		return cantidad;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudInicial.
	 *
	 * @return latitudInicial El valor que tiene el atributo latitudInicial.
	 */
	public double obtenerLatitudInicial()
	{
		return latitudInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo latitudFinal.
	 *
	 * @return latitudFinal El valor que tiene el atributo latitudFinal.
	 */
	public double obtenerLatitudFinal()
	{
		return latitudFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudInicial.
	 *
	 * @return altitudInicial El valor que tiene el atributo altitudInicial.
	 */
	public double obtenerAltitudInicial()
	{
		return altitudInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo altitudFinal.
	 *
	 * @return altitudFinal El valor que tiene el atributo altitudFinal.
	 */
	public double obtenerAltitudFinal()
	{
		return altitudFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo temperaturaInicial.
	 *
	 * @return temperaturaInicial El valor que tiene el atributo
	 *                            temperaturaInicial.
	 */
	public double obtenerTemperaturaInicial()
	{
		return temperaturaInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo temperaturaFinal.
	 *
	 * @return temperaturaFinal El valor que tiene el atributo temperaturaFinal.
	 */
	public double obtenerTemperaturaFinal()
	{
		return temperaturaFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo salinidadInicial.
	 *
	 * @return salinidadInicial El valor que tiene el atributo
	 *                          salinidadInicial.
	 */
	public double obtenerSalinidadInicial()
	{
		return salinidadInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo salinidadFinal.
	 *
	 * @return salinidadFinal El valor que tiene el atributo salinidadFinal.
	 */
	public double obtenerSalinidadFinal()
	{
		return salinidadFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo oxigenoDisueltoInicial.
	 *
	 * @return oxigenoDisuelto El valor que tiene el atributo
	 *                         oxigenoDisueltoInicial.
	 */
	public double obtenerOxigenoDisueltoInicial()
	{
		return oxigenoDisueltoInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo oxigenoDisueltoFinal.
	 *
	 * @return oxigenoDisueltoFinal El valor que tiene el atributo
	 *                              oxigenoDisueltoFinal.
	 */
	public double obtenerOxigenoDisueltoFinal()
	{
		return oxigenoDisueltoFinal;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo percepcion.
	 *
	 * @return percepcion El valor que tiene el atributo percepcion.
	 */
	public double obtenerPercepcion()
	{
		return percepcion;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo velocidad.
	 *
	 * @return velocidad El valor que tiene el atributo velocidad.
	 */
	public double obtenerVelocidad()
	{
		return velocidad;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaInicial.
	 *
	 * @return energiaInicial El valor que tiene el atributo energiaInicial.
	 */
	public double obtenerEnergiaInicial()
	{
		return energiaInicial;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaMinima.
	 *
	 * @return energiaMinima El valor que tiene el atributo energiaMinima.
	 */
	public double obtenerEnergiaMinima()
	{
		return energiaMinima;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaMaxima.
	 *
	 * @return energiaMaxima El valor que tiene el atributo energiaMaxima.
	 */
	public double obtenerEnergiaMaxima()
	{
		return energiaMaxima;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaTiempo.
	 *
	 * @return energiaTiempo El valor que tiene el atributo energiaTiempo.
	 */
	public double obtenerEnergiaTiempo()
	{
		return energiaTiempo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaConsumo.
	 *
	 * @return energiaConsumo El valor que tiene el atributo energiaConsumo.
	 */
	public double obtenerEnergiaConsumo()
	{
		return energiaConsumo;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo energiaDesplazamiento.
	 *
	 * @return energiaDesplazamiento El valor que tiene el atributo
	 *                               energiaDesplazamiento.
	 */
	public double obtenerEnergiaDesplazamiento()
	{
		return energiaDesplazamiento;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo descomposicion.
	 *
	 * @return descomposicion El valor que tiene el atributo descomposicion.
	 */
	public double obtenerDescomposicion()
	{
		return descomposicion;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaArrancar.
	 *
	 * @return tendenciaArrancar El valor que tiene el atributo
	 *                           tendenciaArrancar.
	 */
	public double obtenerTendenciaArrancar()
	{
		return tendenciaArrancar;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaPredar.
	 *
	 * @return tendenciaPredar El valor que tiene el atributo tendenciaPredar.
	 */
	public double obtenerTendenciaPredar()
	{
		return tendenciaPredar;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaReproducir.
	 *
	 * @return tendenciaReproducir El valor que tiene el atributo
	 *                             tendenciaReproducir.
	 */
	public double obtenerTendenciaReproducir()
	{
		return tendenciaReproducir;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaDesovar.
	 *
	 * @return tendenciaDesovar El valor que tiene el atributo tendenciaDesovar.
	 */
	public double obtenerTendenciaDesovar()
	{
		return tendenciaDesovar;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaConvivir.
	 *
	 * @return tendenciaConvivir El valor que tiene el atributo
	 *                           tendenciaConvivir.
	 */
	public double obtenerTendenciaConvivir()
	{
		return tendenciaConvivir;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaMigrar.
	 *
	 * @return tendenciaMigrar El valor que tiene el atributo tendenciaMigrar.
	 */
	public double obtenerTendenciaMigrar()
	{
		return tendenciaMigrar;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaDivagar.
	 *
	 * @return tendenciaDivagar El valor que tiene el atributo tendenciaDivagar.
	 */
	public double obtenerTendenciaDivagar()
	{
		return tendenciaDivagar;
	}
	
	/**
	 * M�todo que obtiene el valor del atributo tendenciaNada.
	 *
	 * @return tendenciaNada El valor que tiene el atributo tendenciaNada.
	 */
	public double obtenerTendenciaNada()
	{
		return tendenciaNada;
	}
	
	/**
	 * M�todo que entrega un string con todos los atributos de la clase.
	 *
	 * @return string Los atributos de la clase compuestos como un string.
	 */
	public String toString()
	{
		String texto = "Ajuste:{";
		
		texto+= "Sexo:"+sexo+" ";
		texto+= "Estado:"+estado+" ";
		texto+= "Edad Maxima:"+edadMaxima+" ";
		texto+= "Parametro Loo:"+parametroLoo+" ";
		texto+= "Parametro To:"+parametroTo+" ";
		texto+= "Parametro K:"+parametroK+" ";
		texto+= "Parametro A:"+parametroA+" ";
		texto+= "Parametro B:"+parametroB+" ";
		texto+= "Talla Minima:"+tallaMinima+" ";
		texto+= "Talla Maxima:"+tallaMaxima+" ";
		texto+= "Talla:"+talla+" ";
		texto+= "Peso Minimo:"+pesoMinimo+" ";	
		texto+= "Peso Maximo:"+pesoMaximo+" ";
		texto+= "Peso:"+peso+" ";
		texto+= "Cantidad:"+cantidad+" ";
		texto+= "Parametro S1:"+parametroS1+" ";
		texto+= "Parametro S2:"+parametroS1+" ";
		texto+= "Madurez Sexual:"+madurezSexual+" ";
		texto+= "Huevos Minimo:"+huevosMinimo+" ";
		texto+= "Huevos Maximo:"+huevosMaximo+" ";
		texto+= "Periodo Portacion:"+periodoPortacion+" ";
		texto+= "Latitud Inicial:"+latitudInicial+" ";
		texto+= "Latitud Final:"+latitudFinal+" ";
		texto+= "Altitud Inicial:"+altitudInicial+" ";
		texto+= "Altitud Final:"+altitudFinal+" ";
		texto+= "Temperatura Inicial:"+temperaturaInicial+" ";
		texto+= "Temperatura Final:"+temperaturaFinal+" ";
		texto+= "Salinidad Inicial:"+salinidadInicial+" ";
		texto+= "Salinidad Final:"+salinidadFinal+" ";
		texto+= "Oxigeno Disuelto Inicial:"+oxigenoDisueltoInicial+" ";
		texto+= "Oxigeno Disuelto Final:"+oxigenoDisueltoFinal+" ";
		texto+= "Percepcion:"+percepcion+" ";
		texto+= "Velocidad:"+velocidad+" ";
		texto+= "Energia Inicial:"+energiaInicial+" ";
		texto+= "Energia Minima:"+energiaMinima+" ";
		texto+= "Energia Maxima:"+energiaMaxima+" ";
		texto+= "Energia Tiempo:"+energiaTiempo+" ";
		texto+= "Energia Consumo:"+energiaConsumo+" ";
		texto+= "Energia Desplazamiento:"+energiaDesplazamiento+" ";
		texto+= "Descomposicion:"+descomposicion+" ";
		texto+= "Tendencia Arrancar:"+tendenciaArrancar+" ";
		texto+= "Tendencia Predar:"+tendenciaPredar+" ";
		texto+= "Tendencia Reproducir:"+tendenciaReproducir+" ";
		texto+= "Tendencia Desovar:"+tendenciaDesovar+" ";
		texto+= "Tendencia Convivir:"+tendenciaConvivir+" ";
		texto+= "Tendencia Migrar:"+tendenciaMigrar+" ";
		texto+= "Tendencia Divagar:"+tendenciaDivagar+" ";
		texto+= "Tendencia Nada:"+tendenciaNada+"}";
		
		return texto;
	}
}