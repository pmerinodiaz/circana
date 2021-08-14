/**
 * @(#)BaseDatoTablaBasica.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que permite operar con una tabla de base de dato. Con esta tabla
 * se permite hacer operaciones basica como agregar, borrar y modificar. Esta
 * clase corresponde a la abstracción de una tabla de la base de datos.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ResultSet
 * @see SQLException
 * @see BaseDatoMotor
 */
/*
 * OBSERVACION: Si desea agregar un dato de tipo texto (Ej. "'123'"), entonces
 * no olvidar que si es texto debe llevar comillas simples.
 */
public class BaseDatoTablaBasica
{
	/** Motor de la base dato donde está la tabla. */
	private BaseDatoMotor motor;
	
	/** Nombre de la tabla. */
	private String tabla;
	
	/** Contiene los nombre de los campos que se quieren agregar. */
	private String sqlAgregarCampo;
	
	/** Contiene los valor de los campos que se quieren agregar. */
	private String sqlAgregarValor;
	
	/** Contiene la última consulta agregar hecha a la base de datos. */
	private String sqlAgregar;
	
	/**
	 * Contiene los nombres y valores de los campos que se quieren actualizar.
	 */
	private String sqlActualizarCampo;
	
	/** Contiene la última consulta actualizar hecha a la base de datos. */
	private String sqlActualizar;
	
	/** Contiene la última consulta buscar hecha a la base de datos. */
	private String sqlBuscar;
	
	/**
     * Método constructor de la clase. En este método se inicializa la tabla
     * con cual va a trabajar el objeto y la conexión usada para ejecutar la
     * consulta.
     *
     * @param motor Conexión con cual se hace la consulta.
     * @param tabla Nombre de la tabla con cual se quiere trabajar.
     */
	public BaseDatoTablaBasica(BaseDatoMotor motor, String tabla)
	{
		this.motor = motor;
		this.tabla = tabla;
		
		sqlAgregarCampo = sqlAgregarValor = sqlActualizarCampo = "";
	}
	
	/**
     * Método que prepara un dato para después agregarlo en la tabla.
     *
	 * @param nombreCampo Nombre del campo en cual se quiere agregar.
 	 * @param valorCampo Valor del campo en cual se quiere agregar. No olvidar
 	 *                   que si es texto, entonces debe llevar comillas simples.
 	 */	
	public void agregarCampo(String nombreCampo, String valorCampo)
	{
	   	sqlAgregarCampo += nombreCampo + ",";
    	sqlAgregarValor +=  valorCampo + ",";
    }
    
    /**
     * Método que agrega un registro a la tabla seleccionada, dados los
     * parámetros agregados en el método "agregarCampo".
     */
	public void agregar()
	{
		sqlAgregarCampo =
			sqlAgregarCampo.substring(0, sqlAgregarCampo.length() - 1);
    	sqlAgregarValor =
    		sqlAgregarValor.substring(0, sqlAgregarValor.length() - 1);
    	
    	sqlAgregar = "insert into  " + tabla + "(" + sqlAgregarCampo + ") " +
    				 "values (" + sqlAgregarValor + ")";
    	
    	sqlAgregarCampo = "";
    	sqlAgregarValor = "";
    	
    	motor.ejecutarConsultaActualizacion (sqlAgregar);
    }
	
	/**
     * Método que prepara un dato para después actualizarlo en la tabla.
     *
	 * @param nombreCampo Nombre del campo en cual se quiere actualizar.
	 * @param valorCampo Valor del campo en cual se quiere actualizar. No
	 *                   olvidar que si es texto, entonces debe llevar comillas
	 *                   simples.
	 */
	public void actualizarCampo(String nombreCampo, String valorCampo)
	{
		sqlActualizarCampo += nombreCampo + " = " + valorCampo + ",";
	}
	
	/**
     * Método que actualiza un registro en la tabla, dado el valor de campo de
     * búsqueda. Si el campo se repite, entonces se actualizaran todos los
     * registro que coincidan con el campo.
	 *
	 * @param nombreCampo Nombre del campo que se quiere como condición para
	 *                    actualizar.
     * @param valorCampo Valor del campo que se quiere como condición para
     *                   actualizar.
     *
     * @return numeroFilas Las filas que fueron afectas por la consulta.
     */
	public int actualizar(String nombreCampo, String valorCampo)
	{
	    int numeroFilas = -1;
	    
	    sqlActualizarCampo =
	    	sqlActualizarCampo.substring(0, sqlActualizarCampo.length() - 1);
		
    	sqlActualizar = "update " + tabla + " " +
    					"set " + sqlActualizarCampo + " " +
        				"where " + nombreCampo + " = " + valorCampo;
    	
    	sqlActualizarCampo = "";
    	numeroFilas = motor.ejecutarConsultaActualizacion(sqlActualizar);
    	
    	return numeroFilas;
    }
    
    /**
     * Método que actualiza el(los) registro(s) dado una condición. Esta
     * condición tiene que estar expresada en formato SQL estándar. Por ejemplo:
     * rut = '14.3' and carrera = 'turismo'.
	 *
 	 * @param condicion String de una condición en formato SQL estándar.
     *
	 * @return numeroFilas Las filas que fueron afectas por la consulta.
	 */
	public int actualizarCondicion(String condicion)
	{
		int numeroFilas = -1;
		
		if (condicion != "")
			condicion = "where " + condicion;
        
     	sqlActualizarCampo =
     		sqlActualizarCampo.substring(0, sqlActualizarCampo.length() - 1);
 		
    	sqlActualizar = "update " + tabla + " " +
    					"set " + sqlActualizarCampo + " " +
        				condicion;
    	
    	sqlActualizarCampo = "";
    	
    	numeroFilas = motor.ejecutarConsultaActualizacion(sqlActualizar);
    	
    	return numeroFilas;
    }
	
	/**
     * Método que busca los registros dados en el campo de búsqueda. Puede ser
     * uno o mas. Para eso se entrega un "ResultSet" que después puede ser
     * adminstrado.
     *
     * @param nombreCampo Nombre del campo en cual se quiere buscar.
     * @param valorCampo Valor del campo en cual se quiere buscar.
     *
     * @return resultado Resultado del consulta hecha a la base de datos.
     */
	public ResultSet buscar(String nombreCampo, String valorCampo)
	{
		String condicion = "";
		
		if (nombreCampo != "" || valorCampo != "")
	    	condicion = "where " + nombreCampo + " = " + valorCampo;
		
		ResultSet resultado;
	   	
	   	sqlBuscar = "select * " +
	   				"from " + tabla + " " +
	   				condicion;
        
    	resultado = motor.ejecutarConsulta(sqlBuscar);
    	
    	return resultado;
    }
    
	/**
     * Método que busca el(los) registro(s) dada una condición. Esta condición
     * tiene que estar expresada en formato SQL estándar. Por ejemplo:
     * rut = '14.3' and carrera = 'turismo'.
     *
     * @param condicion String de una condición en formato SQL estándar.
     *
	 * @return resultado Resultado del consulta hecha a la base de datos.
	 */
	public ResultSet buscarCondicion(String condicion) 
	{
		ResultSet resultado;
	    
	    if (condicion != "")
	    	condicion = "where " + condicion;
    	
    	sqlBuscar = "select * " +
    				"from " + tabla + " " +
    				condicion;
    	
    	resultado = motor.ejecutarConsulta(sqlBuscar);
    	
     	return resultado;
	}
    
    /**
     * Método que borra el(los) regitro(s) dados en el campo de búsqueda. Puede
     * ser uno o mas. Para eso se entrega un "ResultSet", que despues puede ser
     * adminstrado.
     *
     * @param nombreCampo Nombre del campo en cual se quiere borrar.
     * @param valorCampo Valor del campo en cual se quiere borrar.
     *
     * @return resultado Resultado del consulta hecha a la base de dato.
     */
	public ResultSet borrar(String nombreCampo, String valorCampo)
	{
		String condicion = "";
		
		if (nombreCampo != "" || valorCampo != "")
	    	condicion = "where " + nombreCampo + " = " + valorCampo;
		
		ResultSet resultado;
	   	
	   	sqlBuscar = "delete * " +
	   				"from " + tabla + " " +
	   				condicion;
        
    	resultado = motor.ejecutarConsulta(sqlBuscar);
    	
    	return resultado;
    }
    
	/**
     * Método que borrar el(los) registro(s) dada una condición. Esta condición
     * tiene que estar expresada en formato SQL estándar. Por ejemplo:
     * rut = '14.3' and carrera = 'turismo'.
     *
     * @param condicion String de una condición en formato SQL estándar.
     *
	 * @return resultado Resultado del consulta hecha a la base de datos.
	 */
	public ResultSet borrarCondicion(String condicion)
	{
	    ResultSet resultado;
	    
	    if (condicion != "")
	    	condicion = "where " + condicion;
    	
    	sqlBuscar = "delete * " +
    				"from " + tabla + " " +
    				condicion;
    	
    	resultado = motor.ejecutarConsulta(sqlBuscar);
    	
     	return resultado;
	}
	
	/**
	 * Método que obtiene el ultimo código de la tabla.
	 *
	 * @param condicion String con la condición de búsqueda.
	 *
	 * @return maximo Ultimo código de la tabla.
	 */
	public int buscarUltimoCodigo(String condicion)
	{
	    ResultSet resultado;
	    String sql;
	    int maximo = -1;
	    
	    if (condicion != "")
	    {
	    	sql = "select max(" + condicion + ") as maximo from " + tabla;
	    	resultado = motor.ejecutarConsulta(sql);
	    	
			try
			{
				resultado.first();
				if(resultado.getRow() != 0)
					maximo = resultado.getInt("maximo");
			}
			catch (SQLException ex)
			{
				System.out.println ("Error al buscar el codigo.");
			}
	    }
		
	 	return maximo;
	}
	
	/**
     * Método que entrega la última consulta ejecutada, en el sentido de
     * agregar datos.
     *
	 * @return sqlAgregar Devuelve un string con la última consulta del tipo
	 *                    agregar hecha a la tabla seleccionada.
  	 */
	public String obtenerUltimaConsultaAgregar()
	{
		return sqlAgregar;
	}
	
	/**
     * Método que entrega la última consulta ejecutada, en el sentido de
     * actualizar datos.
     *
	 * @return sqlActualizar Devuelve un string con la última consulta del tipo
	 *                       actualizar hecha a la tabla seleccionada.
	 */
	public String obtenerUltimaConsultaActualizar()
	{
		return sqlActualizar;
	}
	
	/**
     * Método que entrega la última consulta ejecutada, en el sentido de buscar
     * datos.
	 * 
	 * @return sqlBuscar String con la última consulta del tipo buscar hecha a 
	 *					 la tabla seleccionada.
	 */
	public String obtenerUltimaConsultaBuscar()
	{
	    return sqlBuscar;
	}
}