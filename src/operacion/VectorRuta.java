/**
 * @(#)VectorRuta.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.util.Vector;

/**
 * Clase que representa una estructura de dato del tipo Vector que almacena los
 * nodos recorridos por el algoritmo del agente viajero.
 *
 * @version 2.0 01/03/05
 * @author Héctor Díaz
 * @see Vector
 */
public class VectorRuta extends Vector
{
	/**
	 * Método constructor que inicializa el vector.
	 */
	public VectorRuta()
	{
		super();
	}
	
	/**
	 * Método constructor que inicializa el vector con el valor recibido por
	 * parámetros.
	 *
	 * @param ruta El vector de ruta que será copiado para inicializar el vector
	 *             actual.
	 */
	public VectorRuta(VectorRuta ruta)
	{
		super(new Vector(ruta));
	}
	
	/**
	 * Método que permite agregar un elemento al final del vector.
	 *
	 * @param elemento Elemento correspondiente a un nodo.
	 */
	public void agregar(ElementoRuta elemento)
	{
		super.add(elemento);
	}
	
	/**
	 * Método que permite remover un elemento de la posición indicada.
	 *
	 * @param indice Posición dentro del vector.
	 */
	public void remover(int indice)
	{
		super.removeElementAt(indice);
	}
	
	/**
	 * Método que permite modificar el valor de un elemento en la posición
	 * indicada.
	 *
	 * @param indice Posición dentro del vector.
	 * @param elemento Elemento Correspondiente a un nodo.
	 */
	public void cambiarValor(int indice, ElementoRuta elemento)
	{
		super.setElementAt(elemento, indice);
	}
	
	/**
	 * Método que entrega el elemento de la posición indicada.
	 *
	 * @param indice Posición dentro del vector.
	 *
	 * @return ElementoRestriccion Elemento de la lista de restricción.
	 */
	public ElementoRuta obtenerElemento(int indice)
	{
		return (ElementoRuta) super.get(indice);
	}
	
	/**
	 * Método que intercambia las posiciones de dos elementos dentro del vector.
	 *
	 * @param indiceInicial Posición del primer elemento a intercambiar.
	 * @param indiceFinal Posición del segundo elemento a intercambiar.
	 */
	public void intercambiar(int indiceInicial, int indiceFinal)
	{
		ElementoRuta auxiliar = obtenerElemento(indiceInicial);
		cambiarValor(indiceInicial, obtenerElemento(indiceFinal));
		cambiarValor(indiceFinal, auxiliar);
	}
	
	/**
	 * Método que busca un elemento en el vector. Si lo encuentra retorna la
	 * posición dentro del vector, en caso contrario retorna -1.
	 *
	 * @param elemento El elemento a buscar en el vector.
	 *
	 * @return posicion Ubicación dentro del vector del elemento.
	 */
	public int buscarElemento(ElementoRuta elemento)
	{
		int tamanio = super.size();
		int i;
		int posicion;
		
		posicion = -1;
		for(i = 0;i < tamanio;i++)
		{
			if(compararElementos(elemento, obtenerElemento(i)))
			{
				posicion = i;
				break;
			}
		}
		
		return posicion;
	}
	
	/**
	 * Método que determina si dos elementos son iguales o distintos. En caso de
	 * igualdad se retorna verdadero, en caso contrario se devuelve falso.
	 *
	 * @param elemento1 Elemento a comparar.
	 * @param elemento2 Elemento a comparar.
	 *
	 * @return valor Valor booleano con el resultado de la comparación.
	 */
	public boolean compararElementos(ElementoRuta elemento1,
									 ElementoRuta elemento2)
	{
		boolean valor = false;
		if(elemento1.obtenerCodigo() == elemento2.obtenerCodigo() &&
		   elemento1.obtenerDescripcion().equals(""+elemento2.obtenerDescripcion()) &&
		   elemento1.obtenerTipo().equals(""+elemento2.obtenerTipo()) &&
		   elemento1.obtenerCoordenada().compareTo(elemento2.obtenerCoordenada()) == 0)
			valor = true;
		return valor;
	}
	
	/**
	 * Método que devuelve la cantidad de elementos de un tipo determinado en
	 * en el vector.
	 *
	 * @param tipo Indica el tipo de nodo en cuestión.
	 * 
	 * @return suma Cantidad de elementos del tipo consultado.
	 */
	public int sumatoriaTipoElemento(String tipo)
	{
		int tamanio = super.size();
		int i;
		int suma;
		
		suma = 0;
		for(i = 0;i < tamanio;i++)
		{
			if(obtenerElemento(i).obtenerTipo() == tipo)
				suma++;
		}
		
		return suma;
	}
	
	/**
	 * Método que entrega la ruta como una cadena de texto.
	 *
	 * @return ruta Cadena con los nodos seguidos.
	 */
	public String obtenerRuta()
	{
		int tamanio = super.size();
		int i;
		String ruta = "";
		
		for(i = 0;i < tamanio;i++)
		{
			ruta = ruta + i + ":=> " +
				obtenerElemento(i).obtenerDescripcion() + "\n";
		}
		
		return ruta;
	}
	
	/**
	 * Método que entrega el reporte individualizado para cada nave.
	 *
	 * @param k Indica el indice de la embarcación.
	 * @param matriz matriz tridimensional solución de asignación de recursos.
	 *
	 * @return ruta Cadena con los nodos seguidos y cantidades para cada uno.
	 */
	public String obtenerReporteRuta(int k, double[][][] matriz)
	{
		int tamanio = super.size();
		int e;
		int i;
		int j;
		int pos;
		double cantidad;
		String ruta = "";
		String tabulacion = "";
		String objetivo = "";
		ElementoRuta elemento;
		
		for(e = 0;e < tamanio;e++)
		{
			pos = -1;
			cantidad = 0;
			objetivo = "";
			elemento = obtenerElemento(e);
			
			// Si el nodo es de oferta
			if(elemento.obtenerTipo().equals("Oferta"))
			{
				pos = ConfiguracionAGT.oferta.
					buscarElemento(elemento.obtenerCodigo());
				
				if(pos != -1)
				{
					for(j = 0;j < (ConfiguracionAGT.n - 1);j++)
						cantidad = cantidad + matriz[pos][j][k];
				}
				objetivo = "Extracción  recurso: ";
			}
			
			// si el nodo es de demanda
			else
			if(elemento.obtenerTipo().equals("Demanda"))
			{
				pos = ConfiguracionAGT.demanda.
					buscarElemento(elemento.obtenerCodigo());
				
				if(pos != -1)
				{
					for(i = 0;i < (ConfiguracionAGT.m - 1);i++)
						cantidad = cantidad + matriz[i][pos][k];
				}
				objetivo = "Desembarque recurso: ";
			}
			
			tabulacion = "\t\t";
			
			if(elemento.obtenerDescripcion().length() <= 45)
				tabulacion = tabulacion + "\t\t\t";
			
			ruta = ruta + e + ":=> " + elemento.obtenerDescripcion() +
				   tabulacion + objetivo +
				   Servicio.obtenerNumeroFormateado(cantidad,10) +
				   " (tons)\n";
		}
		
		return ruta;
	}
}