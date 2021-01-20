/**
 * @(#)BaseDatoMotor.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.awt.Frame;

/**
 * Clase núcleo para cualquier conexión de base de datos. Desde esta clase se
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
 * OBSERVACION: La clase está diseñada para hacer solamente una conexión y
 * consulta a la vez. La información guardada de los resultados y de las
 * consultas estan orientadas a la última de esta.
 */
public class BaseDatoMotor 
{
	/** Contiene el identificador del DRIVER de MySQL. */
	private final String DRIVER = "com.mysql.jdbc.Driver";
	
	/** Nombre del archivo de texto plano donde se encuentra la información. */
	private final String NOMBRE_ARCHIVO = "../conf/configuracion_servidor.txt";
	
	/** Contiene el puente de la base de datos. */
	private final String PUENTE = "jdbc:mysql://";
	
	/** Contiene de el valor de la conexión del servidor. */
	private final String SERVIDOR = "localhost";
	
	/** Contiene el nombre de la base de datos. */
	private final String BASE_DATO = "circanapro";
	
	/** Contiene el nombre del usuario de la base de datos. */
	private final String USUARIO = "circanapro";
	
	/** Contiene el contraseña del usuario de la base de datos. */
	private final String CONTRASENIA = "circanaprokey";
	
	/** String que contiene el ip o nombre del pc. */
	private static String servidor;
	
	/** String con el nombre de base de datos. */
	private static String baseDato;
	
	/** String con el nombre del usuario de la base de datos. */
	private static String usuario;
	
	/** String con el password del usuario de la base de datos. */
	private static String contrasenia;
	
	/** Almacena la conexión establecida al motor de base de datos. */
	private Connection conexion;
	
	/** Almacena el resultado de una consulta sql. */
	private ResultSet resultado;
	
	/** Objeto que sirve para ejecutar consultas sql. */
	private Statement ejecutadorSql;
	
	/** Contiene el string de la última consulta. */
	private String sql;
	
	/**
     * Constructor de la clase. Inicializa los valores necesarios para realizar
     * la conexión de la base de datos.
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
     * Método que carga la configuración del servidor de base de datos desde un
     * archivo. Si el archivo no se encuentra, entonces se carga la
     * configuración por defecto que se encuentra en el sistema y crea el
     * archivo con esta configuración.
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
     * Método que establece los datos por defecto para la conexión de base de
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
	 * Método que conecta con la base de datos MySQL.
	 */
	public void conectar()
	{
		String textoConexion;
		
		// Texto de la conexión.
		textoConexion = PUENTE + servidor + "/" + baseDato;
		
		try
		{
    	 	// Registrando driver.
    	 	Class.forName(DRIVER);
		}
        catch (java.lang.ClassNotFoundException e)
        {
        	// Si no está registrado en el sistema.
        	System.err.print("ClassNotFoundException(DRIVER) = ");
        	System.err.println(e.getMessage());
    	}
    	try
    	{
       		// Estableciendo la conexión.
       		conexion = DriverManager.getConnection(textoConexion,
       											   usuario,
       											   contrasenia);
       		ejecutadorSql = conexion.createStatement();
       	}
       	catch (SQLException ex)
       	{
       		// No es necesario mostrar la conexión.
        }
    }
    
    /**
     * Método que se desconecta de la base de datos.
     */
    public void desconectar()
    {
    	try
    	{
       	    // Cerrando ejecutador y conexión.
       	    ejecutadorSql.close();
    		conexion.close();
    	}
    	catch (SQLException ex)
       	{
       		// Capturar la excepción y mostrarla.
       		System.err.println("SQLExceptionn(DESCONEXION) = " +
       						   ex.getMessage());
        }
    }
	
    /**
     * Método que ejecuta una consulta SQL. El tipo consulta comprende los
     * siguientes métodos: SELECT.
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
       		// Capturar la excepción y mostrarla.
       		System.err.println("SQLExceptionn(CONSULTA SELECT) = " +
           					   ex.getMessage());
        	System.err.println(sql);
        }
        
    	return resultado;
    }
    
    /**
     * Método que ejecuta una consulta del tipo actualización en la tabla. El
     * tipo actualizacion comprende los siguientes métodos: INSERT, DELETE y
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
            // Capturar la excepción y mostrarla.
            System.err.println("SQLExceptionn(CONSULTA INSERT, DELETE, UPDATE)"+
            				   " = " + ex.getMessage());
			System.err.println(sql);
        }
       	
    	return filas;
    }
    
    /**
     * Método que testea si la conexión de la base de datos está habilitada o
     * no.
     * 
     * @return conectada Indica si está habilitada la conexión a la base de
     *                   datos.
     */
    public boolean estaConectada()
    {
 		boolean conectada;
 		String textoConexion;
 		
 		// Texto del conexión.
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
     * Método que configura el servidor de la base de datos. Para así configurar
     * el servicio de base de datos.
     *
     * @param servidor El IP o nombre del PC del servidor de la base de datos.
     * @param usuario El nombre del usuario de la base de datos.
     * @param contrasenia La contraseña del usuario que accede a la base de
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
     * Método que guarda la configuración actual que hay en el sistema del
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
     * Método que ejecuta un scritpt en forma secuencial por setencias. Durante
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
     * Método que retorna el nombre de la base de datos.
     * 
     * @return baseDato String con el nombre de la base de datos.
     */
	public String obtenerBaseDato()
	{
		return baseDato;
	}
	
	/**
     * Método que retorna el nombre del servidor de base de datos.
     * 
     * @return nombreBaseDato String con el nombre del servidor de la base de
     *                        datos.
     */
	public String obtenerServidor()
	{
		return servidor;
	}
	
	/**
     * Método que retorna del usuario de la base de datos.
     * 
     * @return nombreBaseDato String con el usuario de la base de datos.
     */
	public String obtenerUsuario()
	{
		return usuario;
	}
	
	/**
     * Método que retorna la contraseña del usuario de la base de datos.
     *
     * @return nombreBaseDato String que contiene la contraseña del usuario de
     *						  de la base de datos.
     */
	public String obtenerContrasenia()
	{
		return contrasenia;
	}
	
	/** 
	 * Método que retorna la conexión a la base de datos.
	 *
	 * @return conexion La conexión de la base de datos.
	 */
	public Connection obtenerConexion()
	{
		return conexion;
	} 
	
    /**
	 * Método que retorna el resultado de la última consulta.
	 *
	 * @return resultado Conjunto de resultados de la última consulta.
	 */
	public ResultSet obtenerUltimoResultado()
	{
		return resultado;
	}
	
	/**
	 * Método que retorna el string de la última consulta.
	 *
	 * @return sql String de la última consulta a la base de datos.
	 */
	public String obtenerUltimaConsulta()
	{
		return sql;
	}
	
	/**
     * Método que comprueba que un texto no tenga un tipo de código que afecta a
     * una consulta a la base de datos.
     * 
     * @param dato Texto que contiene el dato que se quiere validar.
     * 
     * @return valido Indica si es un texto valido o no.
     */
    /*
     * OBSERVACION :Si hay caracteres especiales, por favor incluirlos en el if
     * o en una función privada que cumpla con este objetivo (parecido a
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