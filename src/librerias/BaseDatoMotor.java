/**
 * @(#)BaseDatoMotor.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.awt.Frame;

/**
 * Clase n�cleo para cualquier conexi�n de base de datos. Desde esta clase se
 * conecta al motor de base de datos MySql. El objetivo principal del motor de
 * base de datos es ofrecer un "middle-ware" a los programodores del sistema.
 * 
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see ResultSet
 * @see Connection
 * @see DriverManager
 * @see Statement
 * @see SQLException
 * @see Frame
 * @see ArchivoTextoPlano
 */
/*
 * OBSERVACION: La clase est� dise�ada para hacer solamente una conexi�n y
 * consulta a la vez. La informaci�n guardada de los resultados y de las
 * consultas estan orientadas a la �ltima de esta.
 */
public class BaseDatoMotor 
{
	/** Contiene el identificador del DRIVER de MySQL. */
	private final String DRIVER = "com.mysql.jdbc.Driver";
	
	/** Nombre del archivo de texto plano donde se encuentra la informaci�n. */
	private final String NOMBRE_ARCHIVO = "../conf/configuracion_servidor.txt";
	
	/** Contiene el puente de la base de datos. */
	private final String PUENTE = "jdbc:mysql://";
	
	/** Contiene de el valor de la conexi�n del servidor. */
	private final String SERVIDOR = "localhost";
	
	/** Contiene el nombre de la base de datos. */
	private final String BASE_DATO = "circanapro";
	
	/** Contiene el nombre del usuario de la base de datos. */
	private final String USUARIO = "circanapro";
	
	/** Contiene el contrase�a del usuario de la base de datos. */
	private final String CONTRASENIA = "circanaprokey";
	
	/** String que contiene el ip o nombre del pc. */
	private static String servidor;
	
	/** String con el nombre de base de datos. */
	private static String baseDato;
	
	/** String con el nombre del usuario de la base de datos. */
	private static String usuario;
	
	/** String con el password del usuario de la base de datos. */
	private static String contrasenia;
	
	/** Almacena la conexi�n establecida al motor de base de datos. */
	private Connection conexion;
	
	/** Almacena el resultado de una consulta sql. */
	private ResultSet resultado;
	
	/** Objeto que sirve para ejecutar consultas sql. */
	private Statement ejecutadorSql;
	
	/** Contiene el string de la �ltima consulta. */
	private String sql;
	
	/**
     * Constructor de la clase. Inicializa los valores necesarios para realizar
     * la conexi�n de la base de datos.
     */
	public BaseDatoMotor()
	{
		if (!cargarConfiguracionArchivo())
		{
			establecerDatosPorDefecto();
			guardarConfiguracionArchivo();
		}
	}
	
	/**
     * M�todo que carga la configuraci�n del servidor de base de datos desde un
     * archivo. Si el archivo no se encuentra, entonces se carga la
     * configuraci�n por defecto que se encuentra en el sistema y crea el
     * archivo con esta configuraci�n.
     *
     * @return estaCargada Indica si la carga fue realizada en forma exitosa.
     */
	private boolean cargarConfiguracionArchivo()
	{
		boolean estaCargada = true;
		
		ArchivoTextoPlano configurarServidor =
			new ArchivoTextoPlano(NOMBRE_ARCHIVO);
		
		servidor = configurarServidor.buscarValorVariable("servidor");
		contrasenia = configurarServidor.buscarValorVariable("contrasenia");
		usuario = configurarServidor.buscarValorVariable("usuario");
		baseDato = configurarServidor.buscarValorVariable("basedato");
		
		if ((servidor == "") ||  (contrasenia == "") ||
			(usuario == "") || (baseDato == ""))
		     estaCargada = false;
		
		return estaCargada;
	}
	
    /**
     * M�todo que establece los datos por defecto para la conexi�n de base de
     * datos.
     */
	private void establecerDatosPorDefecto()
	{
		servidor = SERVIDOR;
    	usuario = USUARIO;
    	baseDato = BASE_DATO;
    	contrasenia = CONTRASENIA;
	}
	
	/**
	 * M�todo que conecta con la base de datos MySQL.
	 */
	public void conectar()
	{
		String textoConexion;
		
		// Texto de la conexi�n.
		textoConexion = PUENTE + servidor + "/" + baseDato;
		
		try
		{
    	 	// Registrando driver.
    	 	Class.forName(DRIVER);
		}
        catch (java.lang.ClassNotFoundException e)
        {
        	// Si no est� registrado en el sistema.
        	System.err.print("ClassNotFoundException(DRIVER) = ");
        	System.err.println(e.getMessage());
    	}
    	try
    	{
       		// Estableciendo la conexi�n.
       		conexion = DriverManager.getConnection(textoConexion,
       											   usuario,
       											   contrasenia);
       		ejecutadorSql = conexion.createStatement();
       	}
       	catch (SQLException ex)
       	{
       		// No es necesario mostrar la conexi�n.
        }
    }
    
    /**
     * M�todo que se desconecta de la base de datos.
     */
    public void desconectar()
    {
    	try
    	{
       	    // Cerrando ejecutador y conexi�n.
       	    ejecutadorSql.close();
    		conexion.close();
    	}
    	catch (SQLException ex)
       	{
       		// Capturar la excepci�n y mostrarla.
       		System.err.println("SQLExceptionn(DESCONEXION) = " +
       						   ex.getMessage());
        }
    }
	
    /**
     * M�todo que ejecuta una consulta SQL. El tipo consulta comprende los
     * siguientes m�todos: SELECT.
     *
     * @param sql String que contiene la consulta a ejecutar del tipo SELECT.
     *
     * @return resultado Resultado de la consulta.
     */
    public ResultSet ejecutarConsulta(String sql)
    {
    	// Almacenando consulta.
    	this.sql = sql;
    	
    	try
    	{
    	   	// Ejecutando consulta.
    	   	if (conexion != null)
    	   		resultado = ejecutadorSql.executeQuery(sql);
       	}
    	catch (SQLException ex)
       	{
       		// Capturar la excepci�n y mostrarla.
       		System.err.println("SQLExceptionn(CONSULTA SELECT) = " +
           					   ex.getMessage());
        	System.err.println(sql);
        }
        
    	return resultado;
    }
    
    /**
     * M�todo que ejecuta una consulta del tipo actualizaci�n en la tabla. El
     * tipo actualizacion comprende los siguientes m�todos: INSERT, DELETE y
     * UPDATE.
     *
     * @param sql String que contiene la consulta a ejecutar.
     *
     * @return filas Entero que contiene cuantas filas afecto la consulta.
     */
    public int ejecutarConsultaActualizacion(String sql)
    {
    	int filas = -1;
    	
    	// Almacenando consulta.
    	this.sql = sql;
    	
    	try 
    	{
    		// Ejecutando consulta.
    		if (conexion != null)
    	   		filas = ejecutadorSql.executeUpdate(sql);
    	}
    	catch (SQLException ex)
       	{
            // Capturar la excepci�n y mostrarla.
            System.err.println("SQLExceptionn(CONSULTA INSERT, DELETE, UPDATE)"+
            				   " = " + ex.getMessage());
			System.err.println(sql);
        }
       	
    	return filas;
    }
    
    /**
     * M�todo que testea si la conexi�n de la base de datos est� habilitada o
     * no.
     * 
     * @return conectada Indica si est� habilitada la conexi�n a la base de
     *                   datos.
     */
    public boolean estaConectada()
    {
 		boolean conectada;
 		String textoConexion;
 		
 		// Texto del conexi�n.
 		textoConexion = PUENTE + servidor + "/" + baseDato;
 		
 		try
		{
    	 	// Registrando dirver.
    	 	Class.forName(DRIVER);
		}
        catch (java.lang.ClassNotFoundException e)
        {
        	System.err.print("ClassNotFoundException(DRIVER) = ");
        	System.err.println(e.getMessage());
    	}
    	try
    	{
    		// Conectando a la BD.
       		conexion = DriverManager.getConnection(textoConexion,
       											   usuario,
       											   contrasenia);
       		ejecutadorSql = conexion.createStatement();
       		conectada = true;
       		
       		// Desconectando.
       		ejecutadorSql.close();
    		conexion.close();
    	}
       	catch (SQLException ex)
       	{
          	conectada = false;
          	System.err.println("SQLExceptionn(ESTA_CONECTADA) = " +
          					   ex.getMessage());
        }
        
        return conectada;
	}
	
    /**
     * M�todo que configura el servidor de la base de datos. Para as� configurar
     * el servicio de base de datos.
     *
     * @param servidor El IP o nombre del PC del servidor de la base de datos.
     * @param usuario El nombre del usuario de la base de datos.
     * @param contrasenia La contrase�a del usuario que accede a la base de
     *                    datos.
     * @param baseDato El nombre de la base de datos que se quiere acceder.
     */
    public void configurarServidor(String servidor, String usuario,
    							   String contrasenia, String baseDato)
    {
    	this.servidor = servidor;
    	this.usuario = usuario;
    	this.baseDato = baseDato;
    	this.contrasenia = contrasenia;
    }
    
	/**
     * M�todo que guarda la configuraci�n actual que hay en el sistema del
     * servidor de base de datos en un archivo. Si el archivo no exite lo crea.
     */
	public void guardarConfiguracionArchivo()
	{
		ArchivoTextoPlano configurarServidor =
			new ArchivoTextoPlano(NOMBRE_ARCHIVO);
		
		configurarServidor.cambiarValorVariable("servidor",servidor);
		configurarServidor.cambiarValorVariable("contrasenia",contrasenia);
		configurarServidor.cambiarValorVariable("usuario",usuario);
		configurarServidor.cambiarValorVariable("basedato",baseDato);
	}
	
	/**
     * M�todo que ejecuta un scritpt en forma secuencial por setencias. Durante
     * ese periodo se congela el sistema. Importante es que el formato del
     * archivo de script debe estar en texto plano y las setencias deben
     * finalizar con punto y coma.
     * 
     * @param nombreArchivo El nombre del archivo que contiene el script que se
     * 						quiere ejecutar.
     */
	public void ejecutarScript(String nombreArchivo)
	{
		ArchivoTextoPlano archivoScript = new ArchivoTextoPlano(nombreArchivo);
		int posicionInicial;
		int posicionFinal;
		String script;
		String sql;
		
		// Obtienedo el script ha ejecutar.
		script = archivoScript.leer();
		
		sql = "";
		
		posicionInicial = posicionFinal = 0;
		
		// Ejecutando setencia por setencia (separado por coma).
		for (posicionInicial = 0; posicionInicial < script.length(); 
													posicionInicial++)
		{
			posicionFinal = script.indexOf(';',posicionInicial);
			
			if (posicionFinal != -1)
			{
				sql = script.substring(posicionInicial,posicionFinal);
				posicionInicial = posicionFinal + 1;
			}
			else
			{
				sql = "";
				posicionInicial = script.length();
			}
			
			// Si hay sentencia ejecuta.
			if (sql.length() != 0)
			{
				ejecutarConsultaActualizacion(sql);
			}
		}
	}
	
	/**
     * M�todo que retorna el nombre de la base de datos.
     * 
     * @return baseDato String con el nombre de la base de datos.
     */
	public String obtenerBaseDato()
	{
		return baseDato;
	}
	
	/**
     * M�todo que retorna el nombre del servidor de base de datos.
     * 
     * @return nombreBaseDato String con el nombre del servidor de la base de
     *                        datos.
     */
	public String obtenerServidor()
	{
		return servidor;
	}
	
	/**
     * M�todo que retorna del usuario de la base de datos.
     * 
     * @return nombreBaseDato String con el usuario de la base de datos.
     */
	public String obtenerUsuario()
	{
		return usuario;
	}
	
	/**
     * M�todo que retorna la contrase�a del usuario de la base de datos.
     *
     * @return nombreBaseDato String que contiene la contrase�a del usuario de
     *						  de la base de datos.
     */
	public String obtenerContrasenia()
	{
		return contrasenia;
	}
	
	/** 
	 * M�todo que retorna la conexi�n a la base de datos.
	 *
	 * @return conexion La conexi�n de la base de datos.
	 */
	public Connection obtenerConexion()
	{
		return conexion;
	} 
	
    /**
	 * M�todo que retorna el resultado de la �ltima consulta.
	 *
	 * @return resultado Conjunto de resultados de la �ltima consulta.
	 */
	public ResultSet obtenerUltimoResultado()
	{
		return resultado;
	}
	
	/**
	 * M�todo que retorna el string de la �ltima consulta.
	 *
	 * @return sql String de la �ltima consulta a la base de datos.
	 */
	public String obtenerUltimaConsulta()
	{
		return sql;
	}
	
	/**
     * M�todo que comprueba que un texto no tenga un tipo de c�digo que afecta a
     * una consulta a la base de datos.
     * 
     * @param dato Texto que contiene el dato que se quiere validar.
     * 
     * @return valido Indica si es un texto valido o no.
     */
    /*
     * OBSERVACION :Si hay caracteres especiales, por favor incluirlos en el if
     * o en una funci�n privada que cumpla con este objetivo (parecido a
     * "Encoding").
     */
	public static boolean esValidoDatoIngresado(String dato)
	{
		int i;
		boolean valido = true;
		char caracter;
		
		for (i = 0; i < dato.length(); i ++)
		{
			caracter = dato.charAt(i);
			
			if (caracter == '\'')
				valido = false;
		}
		
		return valido;
	}
}