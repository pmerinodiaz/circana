/**
 * @(#)Espacio.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

/**
 * Clase que representa un espacio discreto en tres dimensiones (3D). Este
 * espacio es una grilla dividida en celdas, las cuales tienen coordenadas del
 * tipo (i, j, k) únicas. Cada celda de la grilla puede o no contener un
 * super-individuo. Cuando una celda está vacía, entonces se almacena un valor
 * por defecto que indica que la celda está vacía. Este espacio puede
 * representar un espacio finito o infinito.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see SuperIndividuo
 * @see Mapa
 * @see ConfiguracionAC
 * @see Celda
 */
public class Espacio
{
	/** El valor que indica que el límite del espacio es finito. */
	public final static int FINITO = 0;
	
	/** El valor que indica que el límite del espacio es infinito. */
	public final static int INFINITO = 1;
	
	/** La cantidad máxima de celdas permitidas para la grilla. */
	private final static int MAXIMO_CELDAS = 8000000;
	
	/**
	 * La transformación de modelo usada para discretizar la ubicación de los
	 * super-individuos (continua) a una grilla de puntos (discreta).
	 */
	private static TransformacionModelo transformacion;
	
	/** El tamaño de la grilla en la coordenada I. */
	private int tamanioI;
	
	/** El tamaño de la grilla en la coordenada J. */
	private int tamanioJ;
	
	/** El tamaño de la grilla en la coordenada K. */
	private int tamanioK;
	
	/** La grilla que contiene a los super-individuos. */
	private SuperIndividuo[][][] grilla;
	
	/**
	 * Método constructor en donde se inicializan todos los atributos de la
	 * clase. Se establece la transformación de modelo usada en el mapa, el
	 * tamaño de la grilla en sus coordenadas I, J y K, con los valores
	 * obtenidos desde la transformación de modelo del mapa de estudio. En caso
	 * que el tamaño exceda el máximo de celdas permitidas, entonces se realiza
	 * un cambio en el tamaño de la grilla en sus coordenadas I, J y K.
	 * Finalmente se crea la grilla que contiene a los super-individuos.
	 */
	public Espacio()
	{
		// Establecer la transformación de modelo.
		transformacion = Mapa.obtenerTransformacion();
		
		// Establecer los valores del tamaño de la grilla.
		tamanioI = transformacion.obtenerIDel();
		tamanioJ = transformacion.obtenerJDel();
		tamanioK = transformacion.obtenerKDel();
		
		// Cuando se excede el máximo de celdas permitidas.
		if (tamanioI * tamanioJ * tamanioK > MAXIMO_CELDAS)
		{
			// Calcular las proporciones a y b.
			double a = (double)tamanioJ / (double)tamanioI;
			double b = (double)tamanioK / (double)tamanioI;
			
			// Calcular la raíz cúbica.
			double raiz = Math.pow((double)MAXIMO_CELDAS / (a*b), 1.0 / 3.0);
			
			// Establecer los nuevos tamaños.
			tamanioI = (int) raiz;
			tamanioJ = (int) (a * raiz);
			tamanioK = (int) (b * raiz);
			
			// Establecer la nueva transformación de modelo.
			transformacion =
			new TransformacionModelo(0, tamanioI,
									 0, tamanioJ,
									 0, tamanioK,
									 transformacion.obtenerXMin(),
									 transformacion.obtenerXMax(),
									 transformacion.obtenerYMin(),
									 transformacion.obtenerYMax(),
									 transformacion.obtenerZMin(),
									 transformacion.obtenerZMax());
		}
		
		// Asignar el tamaño de la grilla.
		try
		{
			// Crear la grilla contenedora de super-individuos.
			grilla = new SuperIndividuo[tamanioI][tamanioJ][tamanioK];
		}
		
		// Captuar la excepción y mostrarla.
		catch (Exception excepcion)
		{
			System.err.println("Error de memoria.");
		}
	}
	
	/**
	 * Método que limpia la grilla contenedora de super-individuos. Es decir,
	 * se elimina a todos los super-individuos que están en la grilla, dejando
	 * en cada celda el valor null.
	 */
	public void limpiar()
	{
		for (int i=0; i<tamanioI; i++)
			for (int j=0; j<tamanioJ; j++)
				for (int k=0; k<tamanioK; k++)
					grilla[i][j][k] = null;
	}
	
	/**
	 * Método que inserta un super-individuo en la grilla contenedora de
	 * super-individuos. El super-individuo tiene una celda de ubicación única,
	 * la cual indica la ubicación en donde debe ser insertado el
	 * super-individuo dentro de la grilla.
	 *
	 * @param superIndividuo El super-individuo que se inserta en la grilla.
	 */
	public void insertarGrilla(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		int i = ubicacion.obtenerI();
		int j = ubicacion.obtenerJ();
		int k = ubicacion.obtenerK();
		
		// Insertar el super-individuo en la grilla.
		insertarGrilla(i, j, k, superIndividuo);
	}
	
	/**
	 * Método que inserta un super-individuo en la grilla contenedora de
	 * super-individuos. El super-individuo es insertado en la posición
	 * (i, j, k) dentro de la grilla.
	 *
	 * @param i La componente i de la grilla.
	 * @param j La componente j de la grilla.
	 * @param k La componente k de la grilla.
	 * @param superIndividuo El super-individuo que se inserta en la posición
	 *                       (i, j, k) de la grilla.
	 */
	public void insertarGrilla(int i, int j, int k,
							   SuperIndividuo superIndividuo)
	{
		grilla[i][j][k] = superIndividuo;
	}
	
	/**
	 * Método que elimina un super-individuo en la grilla contenedora de
	 * super-individuo. El super-individuo tienen una celda de ubicación única,
	 * la cual indica la ubicación en donde debe ser eliminado el
	 * super-individuo dentro de la grilla.
	 *
	 * @param superIndividuo El super-individuo que se elimina en la grilla.
	 */
	public void eliminarGrilla(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		
		// Eliminar el super-individuo en la grilla.
		eliminarGrilla(ubicacion);
	}
	
	/**
	 * Método que elimina una celda en la grilla contenedora de
	 * super-individuos. La celda indica la ubicación en donde debe ser
	 * eliminado el super-individuo contenido dentro de la grilla.
	 *
	 * @param celda La celda en donde se elimina el super-individuo.
	 */
	public void eliminarGrilla(Celda celda)
	{
		// Obtener los componentes.
		int i = celda.obtenerI();
		int j = celda.obtenerJ();
		int k = celda.obtenerK();
		
		// Eliminar el super-individuo en la celda.
		eliminarGrilla(i, j, k);
	}
	
	/**
	 * Método que elimina una coordenada en la la grilla contenedora de
	 * super-individuo. La coordenada (i, j, k) indica la ubicación en donde
	 * debe se eliminado el super-individuo contenido dentro de la grilla.
	 *
	 * @param i La componente i de la grilla.
	 * @param j La componente j de la grilla.
	 * @param k La componente k de la grilla.
	 */
	public void eliminarGrilla(int i, int j, int k)
	{
		grilla[i][j][k] = null;
	}
	
	/**
	 * Método que busca un super-individuo en la grilla contenedora de
	 * super-individuo. El super-individuo tienen una celda de ubicación única,
	 * la cual indica la ubicación en donde debe ser buscado el super-individuo
	 * dentro de la grilla.
	 *
	 * @param superIndividuo El super-individuo que se busca en la grilla.
	 */
	public SuperIndividuo buscarGrilla(SuperIndividuo superIndividuo)
	{
		// Obtener la ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		
		// Buscar el super-individuo en la grilla.
		return buscarGrilla(ubicacion);
	}
	
	/**
	 * Método que busca una celda en la grilla contenedora de super-individuos.
	 * La celda indica la ubicación en donde debe ser buscado el super-individuo
	 * contenido dentro de la grilla.
	 *
	 * @param celda La celda en donde se busca el super-individuo.
	 */
	public SuperIndividuo buscarGrilla(Celda celda)
	{
		// Obtener los componentes.
		int i = celda.obtenerI();
		int j = celda.obtenerJ();
		int k = celda.obtenerK();
		
		// Buscar el super-individuo en la grilla.
		return buscarGrilla(i, j, k);
	}
	
	/**
	 * Método que busca una coordenada en la la grilla contenedora de
	 * super-individuo. La coordenada (i, j, k) indica la ubicación en donde
	 * debe se buscado el super-individuo contenido dentro de la grilla.
	 *
	 * @param i La componente i de la grilla.
	 * @param j La componente j de la grilla.
	 * @param k La componente k de la grilla.
	 */
	public SuperIndividuo buscarGrilla(int i, int j, int k)
	{
		return grilla[i][j][k];
	}
	
	/**
	 * Método que actualiza la posición de un super-individuo en la grilla
	 * contenedora de super-individuos. El super-individuo tienen una celda de
	 * ubicación única, la cual indica la ubicación de donde debe ser eliminado
	 * el super-individuo dentro de la grilla y la celda destino indica la
	 * posición única en donde debe ser coloado el super-individuo.
	 *
	 * @param superIndividuo El super-individuo que se reposiciona en la grilla.
	 * @param destino La celda destino en donde debe ponerse el super-individuo.
	 */
	public void reposicionarGrilla(SuperIndividuo superIndividuo, Celda destino)
	{
		// Obtener la ubicación del super-individuo.
		Celda ubicacion = superIndividuo.obtenerUbicacion();
		
		// Actualizar la posición del super-individuo.
		eliminarGrilla(superIndividuo);
		superIndividuo.establecerUbicacion(destino);
		insertarGrilla(superIndividuo);
	}
	
	/**
	 * Método que cuenta la cantidad de celdas en la grilla contenedora de
	 * super-individuos que se encuentran ocupadas.
	 *
	 * @return cantidad La cantidad de grillas que se encuentran ocupadas.
	 */
	public int contarOcupadas()
	{
		int cantidad = 0;
		
		for (int i=0; i<tamanioI; i++)
			for (int j=0; j<tamanioJ; j++)
				for (int k=0; k<tamanioK; k++)
					if (grilla[i][j][k] != null)
						cantidad++;
		
		return cantidad;
	}
	
	/**
	 * Método que obtiene el valor del atributo tamanioI.
	 *
	 * @return tamanioI El valor del atributo tamanioI.
	 */
	public int obtenerTamanioI()
	{
		return tamanioI;
	}
	
	/**
	 * Método que obtiene el valor del atributo tamanioJ.
	 *
	 * @return tamanioJ El valor del atributo tamanioJ.
	 */
	public int obtenerTamanioJ()
	{
		return tamanioJ;
	}
	
	/**
	 * Método que obtiene el valor del atributo tamanioK.
	 *
	 * @return tamanioK El valor del atributo tamanioK.
	 */
	public int obtenerTamanioK()
	{
		return tamanioK;
	}
	
	/**
	 * Método que obtiene el valor del atributo grilla.
	 *
	 * @return grilla El valor del atributo grilla.
	 */
	public SuperIndividuo[][][] obtenerGrilla()
	{
		return grilla;
	}
	
	/**
	 * Método que obtiene el valor del atributo transformacion.
	 *
	 * @return transformacion El valor del atributo transformacion.
	 */
	public TransformacionModelo obtenerTransformacion()
	{
		return transformacion;
	}
}