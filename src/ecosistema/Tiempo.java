/**
 * @(#)Tiempo.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa al tiempo discreto usado en el autómata celular. Esta
 * clase corresponde al reloj virtual de cómputo conectado a cada celda del
 * autómata celular, el cual genera tics o pulsos simultáneos a todas las celdas
 * indicando que debe aplicarse la regla de evolución y de esta forma cada celda
 * cambiará de estado. Esta clase maneja el tiempo en diferentes unidades de
 * tiempo.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see ConfiguracionAC
 * @see Servicio
 */
public class Tiempo
{
	/** El valor que indica que la unidad de tiempo es el segundo. */
	public static final int SEGUNDO = 0;
	
	/** El valor que indica que la unidad de tiempo es el minuto. */
	public static final int MINUTO = 1;
	
	/** El valor que indica que la unidad de tiempo es la hora. */
	public static final int HORA = 2;
	
	/** El valor que indica que la unidad de tiempo es el día. */
	public static final int DIA = 3;
	
	/**
	 * La fecha inicial de simulación en formato dd/mm/aa (0:día, 1:mes, 2:año).
	 */
	private int[] fechaInicial = new int[3];
	
	/**
	 * La fecha final de simulación en formato dd/mm/aa (0:día, 1:mes, 2:año).
	 */
	private int[] fechaFinal = new int[3];
	
	/**
	 * La fecha actual de simulación en formato dd/mm/aa (0:día, 1:mes, 2:año).
	 */
	private int[] fechaActual = new int[3];
	
	/**
	 * La fecha de ciclo de la simulación en formato dd/mm/aa (0:día, 1:mes,
	 * 2:año).
	 */
	private int[] fechaCiclo = new int[3];
	
	/**
	 * La hora inicial de simulación en formato hh:mm:ss (0:hora, 1:minuto,
	 * 2:segundo).
	 */
	private int[] horaInicial = new int[3];
	
	/**
	 * La hora final de simulación en formato hh:mm:ss (0:hora, 1:minuto,
	 * 2:segundo).
	 */
	private int[] horaFinal = new int[3];
	
	/**
	 * La hora actual de simulación en formato hh:mm:ss (0:hora, 1:minuto,
	 * 2:segundo).
	 */
	private int[] horaActual = new int[3];
	
	/**
	 * La hora en donde se cumple un ciclo en formato hh:mm:ss (0:hora,
	 * 1:minuto, 2:segundo).
	 */
	private int[] horaCiclo = new int[3];
	
	/** El tiempo inicial de simulación en una unidad de tiempo específica. */
	private int tiempoInicial;
	
	/** El tiempo final de simulación en una unidad de tiempo específica. */
	private int tiempoFinal;
	
	/** El tiempo actual de simulación en una unidad de tiempo específica. */
	private int tiempoActual;
	
	/** El tiempo en donde se cumple un ciclo. */
	private int tiempoCiclo;
	
	/**
	 * El tic de reloj del tiempo, el cual es independiente de la unidad de
	 * tiempo.
	 */
	private int ticReloj;
	
	/**
	 * Método constructor en donde se llama al método que inicializa todos los
	 * atributos de la clase con los valores por defecto.
	 */
	public Tiempo()
	{
		iniciar();
	}
	
	/**
	 * Método en donde se inicializan los atributos de la clase con los valores
	 * por defecto. Se establecen los valores iniciales para: las fechas, las
	 * horas, los tiempos y el tic de reloj.
	 */
	public void iniciar()
	{
		// Iniciar las fechas.
		iniciarFechas();
		
		// Iniciar las horas.
		iniciarHoras();
		
		// Iniciar los tiempos.
		iniciarTiempos();
		
		// Iniciar el tic de reloj.
		iniciarTicReloj();
	}
	
	/**
	 * Método que inicia los valores de los atributos relacionados con las
	 * fechas de simulación. Estos son: la fecha inicial, la fecha final, la
	 * fecha actual y la fecha de ciclo.
	 */
	private void iniciarFechas()
	{
		// Establecer valores a la fecha inicial.
		fechaInicial[0] = Proyecto.obtenerFechaInicialFormatoInt()[0];
		fechaInicial[1] = Proyecto.obtenerFechaInicialFormatoInt()[1];
		fechaInicial[2] = Proyecto.obtenerFechaInicialFormatoInt()[2];
		
		// Establecer valores a la fecha final.
		fechaFinal[0] = Proyecto.obtenerFechaFinalFormatoInt()[0];
		fechaFinal[1] = Proyecto.obtenerFechaFinalFormatoInt()[1];
		fechaFinal[2] = Proyecto.obtenerFechaFinalFormatoInt()[2];
		
		// Establecer valores a la fecha actual.
		fechaActual[0] = fechaInicial[0];
		fechaActual[1] = fechaInicial[1];
		fechaActual[2] = fechaInicial[2];
		
		// Establecer valores a la fecha ciclo.
		fechaCiclo[0] = 30;
		fechaCiclo[1] = 12;
		fechaCiclo[2] = 0;
	}
	
	/**
	 * Método que inicia los valores de los atributos relacionados con las horas
	 * de simulación. Estos son: la hora inicial, la hora final, la hora actual
	 * y la hora de ciclo.
	 */
	private void iniciarHoras()
	{
		// Establecer valores a la hora inicial.
		horaInicial[0] = 0;
		horaInicial[1] = 0;
		horaInicial[2] = 0;
		
		// Establecer valores a la hora final.
		horaFinal[0] = 24;
		horaFinal[1] = 60;
		horaFinal[2] = 60;
		
		// Establecer valores a la hora actual.
		horaActual[0] = 0;
		horaActual[1] = 0;
		horaActual[2] = 0;
		
		// Establecer valores a la hora ciclo.
		horaCiclo[0] = 24;
		horaCiclo[1] = 60;
		horaCiclo[2] = 60;
	}
	
	/**
	 * Método que inicia los valores de los atributos relacionados con los
	 * tiempos de simulación. Estos son: el tiempo inicial, el tiempo final, el
	 * tiempo actual y el tiempo de ciclo. Estos valores dependen del tipo de
	 * unidad de tiempo que se esté usando actualmente.
	 */
	private void iniciarTiempos()
	{
		// Obtener el día inicial y final del proyecto.
		int diaInicial = Servicio.obtenerDia(fechaInicial);
		int diaFinal = Servicio.obtenerDia(fechaFinal);
		
		// Dependiendo del tipo de unidad del tiempo, se establece los valores
		// para el tiempo inicial, el tiempo final, el tiempo actual y el tiempo
		// de ciclo.
		switch (ConfiguracionAC.obtenerTiempo())
		{
			// Cuando es Segundo.
			case SEGUNDO:
			tiempoInicial = (int) Servicio.transformarDiaSegundo(diaInicial);
			tiempoFinal = (int) Servicio.transformarDiaSegundo(diaFinal);
			tiempoCiclo = (int) Servicio.transformarDiaSegundo(365);
			break;
			
			// Cuando es Minuto.
			case MINUTO:
			tiempoInicial = (int) Servicio.transformarDiaMinuto(diaInicial);
			tiempoFinal = (int) Servicio.transformarDiaMinuto(diaFinal);
			tiempoCiclo = (int) Servicio.transformarDiaMinuto(365);
			break;
			
			// Cuando es Hora.
			case HORA:
			tiempoInicial = (int) Servicio.transformarDiaHora(diaInicial);
			tiempoFinal = (int) Servicio.transformarDiaHora(diaFinal);
			tiempoCiclo = (int) Servicio.transformarDiaHora(365);
			break;
			
			// Cuando es Día.
			case DIA:
			tiempoInicial = diaInicial;
			tiempoFinal = diaFinal;
			tiempoCiclo = 365;
			break;
		}
		
		// Establecer valor a el tiempo actual.
		tiempoActual = tiempoInicial;
	}
	
	/**
	 * Método que establece el valor a los atributos relacionados con los
	 * tics de reloj de la simulación. Estos son: el tic de reloj.
	 */
	private void iniciarTicReloj()
	{
		// Establecer valor al tic de reloj.
		ticReloj = 0;
	}
	
	/**
	 * Método que determina si la fecha actual es menor o igual a la fecha
	 * final. En caso de que la fecha actual sea menor o igual a la fecha final,
	 * entonces se retorna true. En caso contrario, se retorna false.
	 *
	 * @return true En caso de que la fecha actual sea menor o igual que la
	 *              fecha final.
	 * @return false En caso de que la fecha actual sea mayor que la fecha
	 *               final.
	 */
	public boolean esMenorIgualActualFinal()
	{
		return (Servicio.obtenerDias(fechaActual, fechaFinal) >= 0) ?
				true : false;
	}
	
	/**
	 * Método que determina si la fecha actual es mayor o igual a la fecha
	 * final. En caso de que la fecha actual sea mayor o igual a la fecha final,
	 * entonces se retorna true. En caso contrario, se retorna false.
	 *
	 * @return true En caso de que la fecha actual sea mayor o igual que la
	 *              fecha final.
	 * @return false En caso de que la fecha actual sea menor que la fecha
	 *               final.
	 */
	public boolean esMayorIgualActualFinal()
	{
		return (Servicio.obtenerDias(fechaActual, fechaFinal) <= 0) ?
				true : false;
	}
	
	/**
	 * Método que incrementa el tiempo actual en una unidad.
	 */
	public void incrementar()
	{
		incrementar(1);
	}
	
	/**
	 * Método que incrementa el tiempo actual en la unidad especificada como
	 * parámetro.
	 *
	 * @param incremento El valor del incremento que se va a sumar al tiempo
	 *                   actual.
	 */
	private void incrementar(int incremento)
	{
		// Cuando el tiempo actual es menor que el tiempo ciclo.
		if (tiempoActual < tiempoCiclo)
			tiempoActual+= incremento;
		
		// Cuando el tiempo actual es mayor o igual que el tiempo ciclo.
		else tiempoActual = 1;
		
		// Incrementar el tic de reloj.
		ticReloj+= incremento;
		
		// Avanzar en el tiempo.
		avanzar();
	}
	
	/**
	 * Método que decrementa el tiempo actual en una unidad.
	 */
	public void decrementar()
	{
		decrementar(1);
	}
	
	/**
	 * Método que decrementa el tiempo actual en la unidad especificada como
	 * parámetro.
	 *
	 * @param decremento El valor del decremento que se va a restar al tiempo
	 *                   actual.
	 */
	private void decrementar(int decremento)
	{
		// Cuando el tiempo actual es mayor que uno.
		if (tiempoActual > 1)
			tiempoActual-= decremento;
		
		// Cuando el tiempo actual es menor o igual que uno.
		else tiempoActual = tiempoCiclo;
		
		// Decrementar el tic de reloj.
		ticReloj-= decremento;
		
		// Retroceder en el tiempo.
		retroceder();
	}
	
	/**
	 * Método que azanva la fecha y hora actual en una unidad de tiempo
	 * específica, considerando la propiedad cíclica que tiene la fecha y hora.
	 */
	private void avanzar()
	{
		// Dependiendo del tipo de unidad del tiempo, se establece el valor para
		// la fecha y hora actual.
		switch (ConfiguracionAC.obtenerTiempo())
		{
			// Cuando es Segundo.
			case SEGUNDO:
			avanzarSegundo();
			break;
			
			// Cuando es Minuto.
			case MINUTO:
			avanzarMinuto();
			break;
			
			// Cuando es Hora.
			case HORA:
			avanzarHora();
			break;
			
			// Cuando es Día.
			case DIA:
			avanzarDia();
			break;
		}
	}
	
	/**
	 * Método que hace avanzar los segundos de la hora actual. El avance de los
	 * segundos depende de si se ha llegado al tope de segundos permitidos, que
	 * es los segundos de ciclos.
	 */
	private void avanzarSegundo()
	{
		// Cuando los segundos actuales son menores que los segundos de ciclo.
		if (horaActual[2] < horaCiclo[2])
			horaActual[2]++;
		
		// Cuando los segundos son mayores o iguales que los segundos de ciclo.
		else
		{
			horaActual[2] = horaInicial[2];
			avanzarMinuto();
		}
	}
	
	/**
	 * Método que hace avanzar los minutos de la hora actual. El avance de los
	 * minutos depende de si se ha llegado al tope de minutos permitidos, que
	 * es los minutos de ciclos.
	 */
	private void avanzarMinuto()
	{
		// Cuando los minutos son menores que los minutos de ciclo.
		if (horaActual[1] < horaCiclo[1])
			horaActual[1]++;
		
		// Cuando los minutos son mayores o iguales que los minutos de ciclo.
		else
		{
			horaActual[1] = horaInicial[1];
			avanzarHora();
		}
	}
	
	/**
	 * Método que hace avanzar las horas de la hora actual. El avance de las
	 * horas depende de si se ha llegado al tope de horas permitidas, que
	 * es las horas de ciclos.
	 */
	private void avanzarHora()
	{
		// Cuando las horas son menores que las horas de ciclo.
		if (horaActual[0] < horaCiclo[0])
			horaActual[0]++;
		
		// Cuando las horas son mayores o iguales que las horas de ciclo.
		else
		{
			horaActual[0] = horaInicial[0];
			horaActual[1] = horaInicial[1];
			horaActual[2] = horaInicial[2];
			avanzarDia();
		}
	}
	
	/**
	 * Método que hace avanzar los días de la fecha actual. El avance de los
	 * días depende de si se ha llegado al tope de días permitidos, que es los
	 * días de ciclo.
	 */
	private void avanzarDia()
	{
		// Cuando los días son menores que los días de ciclo.
		if (fechaActual[0] < Servicio.obtenerCantidadDias(fechaActual[1]))
			fechaActual[0]++;
		
		// Cuando los días son mayores o iguales que los días de ciclo.
		else
		{
			fechaActual[0] = 1;
			avanzarMes();
		}
	}
	
	/**
	 * Método que hace avanzar los meses de la fecha actual. El avance de los
	 * meses depende de si se ha llegado al tope de mseses permitidos, que es
	 * los meses de ciclo.
	 */
	private void avanzarMes()
	{
		// Cuando los meses son menores a los meses de ciclo.
		if (fechaActual[1] < fechaCiclo[1])
			fechaActual[1]++;
		
		// Cuando los meses son mayores o iguales a los meses de ciclo.
		else
		{
			fechaActual[1] = 1;
			avanzarAnio();
		}
	}
	
	/**
	 * Método que hace avanzar los años de la fecha actual. El avance de los
	 * años no depende de si se ha llegado a algún tope de meses.
	 */
	private void avanzarAnio()
	{
		fechaActual[2]++;
	}
	
	/**
	 * Método que retrocede la fecha y hora actual en una unidad de tiempo
	 * específica, considerando la propiedad cíclica que tiene la fecha y hora.
	 */
	private void retroceder()
	{
	}
	
	/**
	 * Método que hace retroceder los años de la fecha actual. El retroceso de
	 * los años no depende de si se ha llegado a algún tope de meses.
	 */
	private void retrocederAnio()
	{
		fechaActual[2]--;
	}
	
	/**
	 * Método que obtiene el valor del atributo fechaInicial.
	 *
	 * @return fechaInicial El valor del atributo fechaInicial.
	 */
	public int[] obtenerFechaInicial()
	{
		return fechaInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo fechaFinal.
	 *
	 * @return fechaFinal El valor del atributo fechaFinal.
	 */
	public int[] obtenerFechaFinal()
	{
		return fechaFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo fechaActual.
	 *
	 * @return fechaActual El valor del atributo fechaActual.
	 */
	public int[] obtenerFechaActual()
	{
		return fechaActual;
	}
	
	/**
	 * Método que obtiene el valor del atributo fechaCiclo.
	 *
	 * @return fechaCiclo El valor del atributo fechaCiclo.
	 */
	public int[] obtenerFechaCiclo()
	{
		return fechaCiclo;
	}
	
	/**
	 * Método que obtiene el valor del atributo horaInicial.
	 *
	 * @return horaInicial El valor del atributo horaInicial.
	 */
	public int[] obtenerHoraInicial()
	{
		return horaInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo horaFinal.
	 *
	 * @return horaFinal El valor del atributo horaFinal.
	 */
	public int[] obtenerHoraFinal()
	{
		return horaFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo horaActual.
	 *
	 * @return horaActual El valor del atributo horaActual.
	 */
	public int[] obtenerHoraActual()
	{
		return horaActual;
	}
	
	/**
	 * Método que obtiene el valor del atributo horaCiclo.
	 *
	 * @return horaCiclo El valor del atributo horaCiclo.
	 */
	public int[] obtenerHoraCiclo()
	{
		return horaCiclo;
	}
	
	/**
	 * Método que obtiene el valor del atributo tiempoInicial.
	 *
	 * @return tiempoInicial El valor del atributo tiempoInicial.
	 */
	public int obtenerTiempoInicial()
	{
		return tiempoInicial;
	}
	
	/**
	 * Método que obtiene el valor del atributo tiempoFinal.
	 *
	 * @return tiempoFinal El valor del atributo tiempoFinal.
	 */
	public int obtenerTiempoFinal()
	{
		return tiempoFinal;
	}
	
	/**
	 * Método que obtiene el valor del atributo tiempoActual.
	 *
	 * @return tiempoActual El valor del atributo tiempoActual.
	 */
	public int obtenerTiempoActual()
	{
		return tiempoActual;
	}
	
	/**
	 * Método que obtiene el valor del atributo tiempoCiclo.
	 *
	 * @return tiempoCiclo El valor del atributo tiempoCiclo.
	 */
	public int obtenerTiempoCiclo()
	{
		return tiempoCiclo;
	}
	
	/**
	 * Método que obtiene el valor del atributo ticReloj.
	 *
	 * @return ticReloj El valor del atributo ticReloj.
	 */
	public int obtenerTicReloj()
	{
		return ticReloj;
	}
}