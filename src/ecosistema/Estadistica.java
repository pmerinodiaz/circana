/**
 * @(#)Estadistica.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa a las estadísticas generadas en la dinámica ecosistémica
 * en el tiempo de la simulación. Se lleva una estadística de la biomasa,
 * abundancia y calidad. Desde la clase AutomataCelular se va incorporando a los
 * super-individuos a esta estadística en el tiempo en el cual se encuentra la
 * dinámica actualmente.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Tiempo
 * @see SuperIndividuo
 * @see Servicio
 * @see ConfiguracionAC
 * @see VectorCelda
 * @see VectorCaladero
 */
public class Estadistica
{
	/** La cantidad de recursos. */
	private int cantidadRecursos;
	
	/** La cantidad de tiempos. */
	private int cantidadTiempos;
	
	/** Las biomasas (toneladas) de los recursos en los tiempos. */
	private double[][] biomasa;
	
	/** Las abundancias (unidades) de los recursos en los tiempos. */
	private long[][] abundancia;
	
	/**
	 * Las calidades (valor entre [0, 1]) de los recursos en los tiempos.
	 */
	private double[][] calidad;
	
	/** Las cuotas de captura (toneladas) de los recursos. */
	private double[][] cuota;
	
	/** Las cuotas de captura artesanales (toneladas) de los recursos. */
	private double[][] cuotaArtesanal;
	
	/** Las cuotas de captura industriales (toneladas) de los recursos. */
	private double[][] cuotaIndustrial;
	
	/**
	 * Los vectores de celdas ordenados por calidad de los recursos.
	 */
	private VectorCelda[] rastreoOrdenado;
	
	/** Los vectores de caladeros de captura de los recursos. */
	private VectorCaladero[][] caladero;
	
	/**
	 * Método constructor que inicializa esta clase. En este constructor no se
	 * realizan operaciones.
	 */
	public Estadistica()
	{
	}
	
	/**
	 * Método que permite limpiar las variables usadas en las estadísticas del
	 * sistema. Primero se llama al método que inicializa los arreglos y luego
	 * se le asigna el valor por defecto cero a todos los arreglos.
	 *
	 * @param tiempo El tiempo discreto de la dinámica.
	 */
	public void limpiar(Tiempo tiempo)
	{
		// Inicializar los arreglos.
		inicializar(tiempo);
		
		// Ciclo para recorrer los recursos.
		for (int i=0; i<cantidadRecursos; i++)
		{
			// Ciclo para recorrer los tiempos.
			for (int j=0; j<cantidadTiempos; j++)
			{
				biomasa[i][j] = 0;
				abundancia[i][j] = 0;
				calidad[i][j] = 0;
				cuota[i][j] = 0;
				cuotaArtesanal[i][j] = 0;
				cuotaIndustrial[i][j] = 0;
				caladero[i][j] = new VectorCaladero();
			}
			rastreoOrdenado[i] = new VectorCelda();
		}
	}
	
	/**
	 * Método en donde se inicializan los atributos de esta clase. Primero se
	 * obtienen los valores para los atributos cantidad de recursos y cantidad
	 * de tiempos. Luego, se asigna espacio en memoria a los arreglos de esta
	 * clase. Los arreglos representan las estadísticas de los recursos en el
	 * tiempo.
	 *
	 * @param tiempo El tiempo discreto de la dinámica.
	 */
	private void inicializar(Tiempo tiempo)
	{
		// Obtener la cantidad de recursos.
		cantidadRecursos = Recurso.obtenerCantidadRecursos();
		
		// Obtener la cantidad de tiempos.
		cantidadTiempos = Servicio.obtenerDias(tiempo.obtenerFechaInicial(),
											   tiempo.obtenerFechaFinal()) + 1;
		
		// Asignar espacio en memoria a los arreglos.
		biomasa = new double[cantidadRecursos][cantidadTiempos];
		abundancia = new long[cantidadRecursos][cantidadTiempos];
		calidad = new double[cantidadRecursos][cantidadTiempos];
		cuota = new double[cantidadRecursos][cantidadTiempos];
		cuotaArtesanal = new double[cantidadRecursos][cantidadTiempos];
		cuotaIndustrial = new double[cantidadRecursos][cantidadTiempos];
		rastreoOrdenado = new VectorCelda[cantidadRecursos];
		caladero = new VectorCaladero[cantidadRecursos][cantidadTiempos];
	}
	
	/**
	 * Método que incorpora los datos de un super-individuo a la estadística
	 * de su tipo de recurso en el tiempo especificado por el tic de reloj.
	 * Primero se obtiene el recurso y el día. Luego, por cada característica
	 * que tiene el super-individuo se incorpora o no a la estadística
	 * respectiva a esa característica.
	 *
	 * @param superIndividuo El super-individuo que se incorpora a la
	 *                       estadística.
	 * @param tiempo El tiempo discreto de la dinámica.
	 * @param espacio El espacio discreto de la dinámica.
	 */
	public void incorporar(SuperIndividuo superIndividuo, Tiempo tiempo,
						   Espacio espacio)
	{
		// Variables locales.
		int i;
		int recurso = superIndividuo.obtenerRecurso();
		int dia = 0;
		
		// Dependiendo de la unidad del tiempo, transformar el tic de reloj a
		// la unidad de tiempo día.
		switch (ConfiguracionAC.obtenerTiempo())
		{
			// Cuando es Segundo.
			case Tiempo.SEGUNDO:
			dia = (int) Servicio.transformarSegundoDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Minuto.
			case Tiempo.MINUTO:
			dia = (int) Servicio.transformarMinutoDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Hora.
			case Tiempo.HORA:
			dia = (int) Servicio.transformarHoraDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Día.
			case Tiempo.DIA:
			dia = tiempo.obtenerTicReloj();
			break;
		}
		
		// Obtener el tipo de estado del super-individuo.
		int estado = superIndividuo.obtenerEstado();
		
		// Cuando el super-individuo está sin huevos o con huevos.
		if ((estado == SuperIndividuo.SIN_HUEVOS) ||
			(estado == SuperIndividuo.CON_HUEVOS))
		{
			// Incrementar la biomasa.
        	biomasa[recurso][dia]+= Servicio.transformarGramoTonelada(
        							superIndividuo.obtenerCantidad()*
        							superIndividuo.obtenerPeso());
			
			// Incrementar la abundancia.
			abundancia[recurso][dia]+= superIndividuo.obtenerCantidad();
			
			// Incrementar la calidad.
			calidad[recurso][dia]+= superIndividuo.obtenerCantidad()*
									superIndividuo.obtenerCalidad();
			
			// Obtener el tamaño del vector de rastreo del recurso.
			int tamanio = rastreoOrdenado[recurso].size();
			
			// Obtener la ubicación del super-individuo.
			Celda ubicacion = superIndividuo.obtenerUbicacion();
			
			// Ciclo para recorrer el vector de rastreo del recurso.
			for (i=0; i<tamanio; i++)
			{
				// Obtener la celda i-ésima.
				Celda celdaI = rastreoOrdenado[recurso].obtenerElemento(i);
				
				// Obtener el super-individuo que se ubica en la celda
				// i-ésima.
				SuperIndividuo superIndividuoI = espacio.
													buscarGrilla(celdaI);
				
				// Cuando el nuevo super-individuo tiene mayor calidad que
				// el super-individuo que se ubica en la celda i-ésima.
				if (superIndividuo.obtenerCalidad() >
					superIndividuoI.obtenerCalidad())
					break;
			}
			
			// Agregar la ubicación del super-individuo en la posición i-ésima
			// del vector de rastreo.
			rastreoOrdenado[recurso].add(i, ubicacion.clone());
		}
	}
	
	/**
	 * Método en donde se generan las cuotas de captura y los caladeros de
	 * captura de todos los recursos, en el tiempo especificado en el parámetro.
	 * Las cuotas de capturas de los recursos son consideradas como un
	 * porcentaje del excedente de biomasa en cada paso de tiempo. Este
	 * porcentaje es ingresado por el usuario y está guardado en la clase
	 * ConfiguracionRecurso. Los caladeros de captura de los recursos son
	 * considerados como las ubicaciones geográficas de los super-individuos que
	 * tienen una mayor calidad.
	 *
	 * @param tiempo El tiempo discreto de la dinámica.
	 * @param espacio El espacio duscreto de la dinámica.
	 */
	public void planificarExtraccion(Tiempo tiempo, Espacio espacio)
	{
		// Variables locales.
		int i;
		int tamanio;
		int dia = 0;
		long excedenteAbundancia;
		double excedenteBiomasa;
		double biomasaAcumulada;
		double calidad = 0;
		Coordenada coordenadaInicial;
		Coordenada coordenadaFinal;
		double biomasaCaladero;
		TransformacionModelo transformacion = espacio.obtenerTransformacion();
		
		// Dependiendo de la unidad del tiempo, transformar el tic de reloj a
		// la unidad de tiempo día.
		switch (ConfiguracionAC.obtenerTiempo())
		{
			// Cuando es Segundo.
			case Tiempo.SEGUNDO:
			dia = (int) Servicio.transformarSegundoDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Minuto.
			case Tiempo.MINUTO:
			dia = (int) Servicio.transformarMinutoDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Hora.
			case Tiempo.HORA:
			dia = (int) Servicio.transformarHoraDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Día.
			case Tiempo.DIA:
			dia = tiempo.obtenerTicReloj();
			break;
		}
		
		// Ciclo para recorrer todos los recursos.
		for (int recurso=0; recurso<cantidadRecursos; recurso++)
		{
			// Cuando el día es mayor que cero.
			if (dia > 0)
			{
				// Calcular el excedente de biomasa.
				excedenteBiomasa = biomasa[recurso][dia] -
								   biomasa[recurso][dia - 1];
				
				// Dependiendo de la calidad elegida en la configuración,
				// calcular la calidad.
				switch (ConfiguracionRecurso.obtenerCalidad(recurso))
				{
					// Cuando es Muy Baja.
					case TipoCalidad.MUY_BAJA:
					calidad = TipoCalidad.CALIDAD_MUY_BAJA;
					break;
					
					// Cuando es Baja.
					case TipoCalidad.BAJA:
					calidad = TipoCalidad.CALIDAD_BAJA;
					break;
					
					// Cuando es Media.
					case TipoCalidad.MEDIA:
					calidad = TipoCalidad.CALIDAD_MEDIA;
					break;
					
					// Cuando es Alta.
					case TipoCalidad.ALTA:
					calidad = TipoCalidad.CALIDAD_ALTA;
					break;
					
					// Cuando es Muy Alta.
					case TipoCalidad.MUY_ALTA:
					calidad = TipoCalidad.CALIDAD_MUY_ALTA;
					break;
				}
				
				// Cuando el excedente de biomasa es mayor que cero y la calidad
				// promedio es mayor o igual que la calidad de mortalidad por
				// cuota.
				if ((excedenteBiomasa > 0) &&
					(obtenerCalidadPromedio(recurso, dia) >= calidad))
				{
					// Inicializar las variables.
					i = 0;
					tamanio = rastreoOrdenado[recurso].size();
					biomasaAcumulada = 0;
					
					// Ciclo para establecer los caladeros.
					while ((i < tamanio) &&
						   (biomasaAcumulada <
						    excedenteBiomasa *
						    ConfiguracionRecurso.obtenerPorcentaje(recurso) /
						    100))
					{
						// Obtener la ubicación i-ésima.
						Celda ubicacion = rastreoOrdenado[recurso].
										  obtenerElemento(i);
						
						// Obtener el super-individuo que se encuentra en la
						// ubicación i-ésima.
						SuperIndividuo superIndividuo = espacio.
														buscarGrilla(ubicacion);
						
						// Obtener la coordenada inicial, final y biomasa del
						// caladero.
						coordenadaInicial = Servicio.transformarCeldaCoordenada(
											ubicacion, transformacion);
						coordenadaFinal = Servicio.transformarCeldaCoordenada(
										  ubicacion, transformacion);
						biomasaCaladero = Servicio.transformarGramoTonelada(
										  superIndividuo.obtenerPeso() *
										  superIndividuo.obtenerCantidad());
						
						// Crear el caladero de captura.
						Caladero ubicacionCaladero =
							new Caladero(coordenadaInicial,
								coordenadaFinal,
								biomasaCaladero);
						
						// Cuando el caladero es artesanal.
						if (ubicacionCaladero.obtenerTipo() ==
							TipoCaladero.ARTESANAL)
							cuotaArtesanal[recurso][dia]+= biomasaCaladero;
						
						// Cuando el caladero es industrial.
						else cuotaIndustrial[recurso][dia]+= biomasaCaladero;
						
						// Agregar el caladero al vector de caladeros.
						caladero[recurso][dia].addElement(ubicacionCaladero);
						
						// Incrementar el índice.
						i++;
						
						// Incrementar la biomasa acumulada.
						biomasaAcumulada+= Servicio.transformarGramoTonelada(
										   superIndividuo.obtenerCantidad() *
										   superIndividuo.obtenerPeso());
					}
					
					// Calcular la cuota.
					cuota[recurso][dia] = cuotaArtesanal[recurso][dia] +
										  cuotaIndustrial[recurso][dia];
				}
				
				// Cuando el excedente de biomasa es menor o igual que cero o la
				// calidad promedio es menor que la calidad de mortalidad por
				// cuota.
				else
				{
					cuota[recurso][dia] = 0;
					cuotaArtesanal[recurso][dia] = 0;
					cuotaIndustrial[recurso][dia] = 0;
				}
			}
			
			// Limpiar el vector de rastreo.
			rastreoOrdenado[recurso].removeAllElements();
		}
	}
	
	/**
	 * Este método determina si un super-individuo es considerado como un
	 * caladero de captura o no. Si la ubicación geográfica del super-individuo
	 * se encuentra en el vector de caladeros, entonces se retorna true. En caso
	 * contrario, se retorna false.
	 *
	 * @param superIndividuo El super-individuo que se verifica si es caladero
	 *                       o no.
	 * @param tiempo El tiempo discreto de la dinámica.
	 * @param espacio El espacio discreto de la dinámica.
	 *
	 * @return true Cuando la ubicación geográfica del super-individuo se
	 *              encuentra en el vector de caladeros.
	 * @return false Cuando la ubicación geográfica del super-individuo no se
	 *               encuentra en el vector de caladeros.
	 */
	public boolean esCaladero(SuperIndividuo superIndividuo, Tiempo tiempo,
							  Espacio espacio)
	{
		// Variables locales.
		int recurso = superIndividuo.obtenerRecurso();
		int dia = 0;
		
		// Dependiendo de la unidad del tiempo, transformar el tic de reloj a
		// la unidad de tiempo día.
		switch (ConfiguracionAC.obtenerTiempo())
		{
			// Cuando es Segundo.
			case Tiempo.SEGUNDO:
			dia = (int) Servicio.transformarSegundoDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Minuto.
			case Tiempo.MINUTO:
			dia = (int) Servicio.transformarMinutoDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Hora.
			case Tiempo.HORA:
			dia = (int) Servicio.transformarHoraDia(tiempo.obtenerTicReloj());
			break;
			
			// Cuando es Día.
			case Tiempo.DIA:
			dia = tiempo.obtenerTicReloj();
			break;
		}
		
		// Obtener la ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		
		// Obtener la transformación de modelo usada.
		TransformacionModelo transformacion = espacio.obtenerTransformacion();
		
		// Obtener la coordenada inicial, final y biomasa del caladero.
		Coordenada coordenadaInicial = Servicio.transformarCeldaCoordenada(
									   ubicacion, transformacion);
		Coordenada coordenadaFinal = Servicio.transformarCeldaCoordenada(
									 ubicacion, transformacion);
		double biomasaCaladero = Servicio.transformarGramoTonelada(
									superIndividuo.obtenerPeso() *
									superIndividuo.obtenerCantidad());
		
		// Crear el caladero de captura.
		Caladero caladeroBuscado = new Caladero(coordenadaInicial,
												coordenadaFinal,
												biomasaCaladero);
		
		// Devolver si está o no el caladero buscado.
		return caladero[recurso][dia].estar(caladeroBuscado);
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadRecursos.
	 *
	 * @return cantidadRecursos El valor del atributo cantidadRecursos.
	 */
	public int obtenerCantidadRecursos()
	{
		return cantidadRecursos;
	}
	
	/**
	 * Método que obtiene el valor del atributo cantidadTiempos.
	 *
	 * @return cantidadTiempos El valor del atributo cantidadTiempos.
	 */
	public int obtenerCantidadTiempos()
	{
		return cantidadTiempos;
	}
	
	/**
	 * Método que obtiene el valor del atributo biomasa de un recurso en un
	 * tiempo conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return biomasa[recurso][tiempo] La biomasa de un recurso en un tiempo
	 *                                  determinado.
	 */
	public double obtenerBiomasa(int recurso, int tiempo)
	{
		return biomasa[recurso][tiempo];
	}
	
	/**
	 * Método que obtiene el valor del atributo abundancia de un recurso en un
	 * tiempo conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return abundancia[recurso][tiempo] La abundancia de un recurso en un
	 *                                     tiempo determinado.
	 */
	public long obtenerAbundancia(int recurso, int tiempo)
	{
		return abundancia[recurso][tiempo];
	}
	
	/**
	 * Método que obtiene el valor de la calidad de un recurso en un tiempo
	 * conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return calidad[recurso][tiempo] La calidad de un recurso en un tiempo
	 *                                  determinado.
	 */
	public double obtenerCalidad(int recurso, int tiempo)
	{
		return calidad[recurso][tiempo];
	}
	
	/**
	 * Método que obtiene el valor de la calidad promedio de un recurso en un
	 * tiempo conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return calidad[recurso][tiempo]/abundancia[recurso][tiempo] La calidad
	 *                        promedio de un recurso en un tiempo determinado.
	 */
	public double obtenerCalidadPromedio(int recurso, int tiempo)
	{
		return (abundancia[recurso][tiempo] > 0) ?
				calidad[recurso][tiempo] / abundancia[recurso][tiempo] : 0;
	}
	
	/**
	 * Método que obtiene el valor de la cuota de captura de un recurso en un
	 * tiempo conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return cuota[recurso][tiempo] La cuota de captura de un recurso en un
	 *                                tiempo determinado.
	 */
	public double obtenerCuota(int recurso, int tiempo)
	{
		return cuota[recurso][tiempo];
	}
	
	/**
	 * Método que obtiene el valor de la cuota de captura artesanal de un
	 * recurso en un tiempo conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return cuotaArtesanal[recurso][tiempo] La cuota de captura artesanal de
	 *                                         un recurso en un tiempo
	 *                                         determinado.
	 */
	public double obtenerCuotaArtesanal(int recurso, int tiempo)
	{
		return cuotaArtesanal[recurso][tiempo];
	}
	
	/**
	 * Método que obtiene el valor de la cuota de captura industrial de un
	 * recurso en un tiempo conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return cuotaIndustrial[recurso][tiempo] La cuota de captura industrial
	 *                                          de un recurso en un tiempo
	 *                                          determinado.
	 */
	public double obtenerCuotaIndustrial(int recurso, int tiempo)
	{
		return cuotaIndustrial[recurso][tiempo];
	}
	
	/**
	 * Método que obtiene el valor del vector de caladeros de captura de un
	 * recurso en un tiempo conocido.
	 *
	 * @param recurso El recurso al cual se le está viendo su estadística.
	 * @param tiempo El tiempo al cual se le está viendo su estadística.
	 *
	 * @return caladero[recurso][tiempo] El vector de caladeros de captura de un
	 *                                   recurso en un tiempo determinado.
	 */
	public VectorCaladero obtenerCaladero(int recurso, int tiempo)
	{
		return caladero[recurso][tiempo];
	}
	
	/**
	 * Método que entrega un string con todos los atributos de la clase.
	 *
	 * @return string Los atributos de la clase compuestos como un string.
	 */
	public String toString()
	{
		String texto = "";
		
		// Ciclo para recorrer los recursos.
		for (int i=0; i<cantidadRecursos; i++)
		{
			// Mostrar el nombre del recurso.
			texto+= Recurso.obtenerNombreComun(i)+":\n";
			
			// Ciclo para recorrer los tiempos.
			for (int j=0; j<cantidadTiempos; j++)
			{
				// Mostrar la biomasa.
				texto+= "Biomasa="+
					Servicio.obtenerNumeroFormateado(
					obtenerBiomasa(i, j), 1)+
					" ton ";
				
				// Mostrar la abundancia.
				texto+= "Abundancia="+
					Servicio.obtenerNumeroFormateado(
					obtenerAbundancia(i, j), 0)+
					" un ";
				
				// Mostrar la calidad promedio.
				texto+= "calidadPromedio["+i+"]["+j+"]="+
					Servicio.obtenerNumeroFormateado(
					obtenerCalidadPromedio(i, j), 1)+" ";
				
				// Mostrar la cuota.
				texto+= "cuota["+i+"]["+j+"]="+
					Servicio.obtenerNumeroFormateado(
					obtenerCuota(i, j), 1)+" ";
				
				// Mostrar la cuota artesanal.
				texto+= "cuotaArtesanal["+i+"]["+j+"]="+
					Servicio.obtenerNumeroFormateado(
					obtenerCuotaArtesanal(i, j), 1)+" ";
				
				// Mostrar la cuota industrial.
				texto+= "cuotaIndustrial["+i+"]["+j+"]="+
					Servicio.obtenerNumeroFormateado(
					obtenerCuotaIndustrial(i, j), 1)+" ";
				
				// Mostrar los caladeros.
				texto+= "caladero["+i+"]["+j+"]="+
					obtenerCaladero(i, j)+"\n";
			}
		}
		
		return texto;
	}
}