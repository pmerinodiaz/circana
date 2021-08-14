/**
 * @(#)TablaSwing.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.util.Vector;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Clase que extiende de JTable. El motivo de su creaci�n es facilitar la
 * progranaci�n o utilizaci�n de tablas swing. Alivia las complicaciones para
 * de construir modelos de tabla para manejar los datos.
 *
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Vector
 * @see Color
 * @see JTable
 * @see DefaultTableModel
 * @see DefaultTableCellRenderer
 * @see TableColumn
 */
/*
 * OBSERVACION: La clase es bastante b�sica, pero se ha construido para que se
 * siga ampliando. Por favor seguir trabajando para termimnar de construir una
 * clase eficiente.
 */ 
public class TablaSwing extends JTable
{
	/** N�mero m�ximo de columnas que acepta esta clase. */
	private int NUMERO_MAXIMO_COLUMNAS = 30;
	
	/** Administrador de los datos de la tabla. */
	private DefaultTableModel administradorDatos;
	
	/** Indice si la tabla en general es editabla o no. */
	private boolean editableTabla;
	
	/** Indica si las columnas en particular son editable o no. */
	private boolean[] editableColumna;
	
	/**
     * M�todo constructor de TablaSwing. Inicializa la tabla con sus
     * componentes.
     */
	public TablaSwing()
	{
		administradorDatos = new DefaultTableModel();
		editableColumna = new boolean[NUMERO_MAXIMO_COLUMNAS];
		setModel(administradorDatos);
		configurarColumnasEditable(0,NUMERO_MAXIMO_COLUMNAS,true);
		editableTabla = true;
	}
	
	/**
     * M�todo constructor de TablaSwing. Incializa la tabla con sus componentes.
     * Adem�s se establece los nombres de las columnas de la tabla.
     * 
     * @param nombreColumnas Arreglo de string que contiene los nombres de
     *  					 las columnas de la tabla.
     */
	public TablaSwing(String[] nombreColumnas)
	{
		administradorDatos = new DefaultTableModel(nombreColumnas,0);
		editableColumna = new boolean[NUMERO_MAXIMO_COLUMNAS];
		setModel(administradorDatos);
		configurarColumnasEditable(0,NUMERO_MAXIMO_COLUMNAS,true);
		editableTabla = true;
	}
	
	/**
     * M�todo constructor de TablaSwing. Incializa la tabla con sus componentes.
     * Adem�s establece los nombres de las columnas de la tabla y los datos de
     * las celdas.
     * 
     * @param nombreColumnas Arreglo de string que contiene los nombres de 
     *						las columnas de la tabla.
     * @param datos Arreglo que contiene los datos de la tabla.
     */
	public TablaSwing(String[] nombreColumnas, Object[][] datos)	
	{
		administradorDatos = new DefaultTableModel(datos, nombreColumnas);
		editableColumna = new boolean[NUMERO_MAXIMO_COLUMNAS];
		setModel(administradorDatos);
		configurarColumnasEditable(0,NUMERO_MAXIMO_COLUMNAS,true);
		editableTabla = true;
	}
	
	/**
     * M�todo constructor de TablaSwing. Incializa la tabla con sus componentes.
     * Construye una tabla de acuerdo a un modelo ya definido.
     *
     * @param modelo Modelo personalizado de una tabla.
     */
	public TablaSwing(ModeloTabla modelo)
	{
		administradorDatos = modelo;
		editableColumna = new boolean[NUMERO_MAXIMO_COLUMNAS];
		setModel(administradorDatos);
		configurarColumnasEditable(0,NUMERO_MAXIMO_COLUMNAS,true);
		editableTabla = true;
	}
	
	/**
     * M�todo que configura cu�les columnas en particular son editables o no.
     * Recordar que los campos empiezan en el �ndice cero (0).
     *
     * @param inicio Indice de que marca el inicio de las columnas que se quiere
     *				 cambiar.
     * @param fin	 Indice de que marca el fin de las columnas que se quiere
     *				 cambiar.
     * @param editable Indica si se quiere que sean editable o no.
     */
	public void configurarColumnasEditable(int inicio, int fin,
										   boolean editable)
	{
		for (int i = inicio; i < fin; i++)
			editableColumna[i] = editable;
	}
	
	/**
     * M�todo que indica si una columna en particular es editable o no.
     *
     * @param nombreColumna Nombre de la columna que se quiere cambiar su estado
     * @param editable Indica si es editable o no.
     */
	public void configurarColumnaEditable(String nombreColumna, boolean editable)
	{
		int columna = administradorDatos.findColumn(nombreColumna);
		editableColumna[columna] = editable;
	}
	
	/**
     * M�todo que indica si la tabla en general es editable o no.
     *
     * @param editableTabla Indica si la tabla en general es editabla o no.
     */
	public void configurarEditableTabla(boolean editableTabla)
	{
		this.editableTabla = editableTabla;
	}
	
	/**
     * M�todo sobre-cargado que sirve para aplicar los atributos de edici�n de
     * la tabla. El m�toodo devuelve si una celda en particular es editable o
     * no.
     *
     * @param row Indica fila de la celda.
     * @param column Indica columna de la celda.
     * 
     * @return editable Indica si la celda es editable o no.
     */
	public boolean isCellEditable(int row, int column)
	{
		boolean editable = true;
		
		if (editableTabla == true)
		{
			if (editableColumna[column] == true)
				editable = true;
			else
				editable = false;
		}
		else
			editable = false;
		
		return editable;
	}
	
	/**
     * Agrega una fila de datos a la tabla.
     * 
     * @param datos Arreglo que contiene los datos de la fila ha agregar.
     */
	public void agregarFila(Object[] datos)
	{
		administradorDatos.addRow(datos);
	}
	
	/**
     * Remueve o elimina una fila seg�n el indice.
     *
     * @param indice Indica la fila que se quiere eliminar.
     */
	public void removerFila(int indice)
	{
		administradorDatos.removeRow(indice);
	}
	
	/**
     * Remueve o elimina la fila seg�n el dato en particular que contiene la
     * fila.
     * 
     * @param nombreColumna El nombre de columna que se quiere buscar el dato.
     * @param dato Dato que se quiere buscar para eliminiar la fila.
     */
	public void removerFila(String nombreColumna, Object dato)
	{
		int fila = buscarPosicionFila(nombreColumna,dato);
		
		if (fila != -1)
			administradorDatos.removeRow(fila);
	}
	
	/**
     * M�todo que devuelve todos los elementos de las filas seleccionadas. Los
     * datos que entrega se encuentra en un formato de matriz donde las filas
     * son los registros.
     *
     * @return datos Matriz con los datos seleccionado.
     */
	public Object[][] obtenerElementosFilasSeleccionadas()
	{
		int i;
		int j;
		int numeroFilasSeleccionadas;
		int numeroColumnas;
		int[] filas = getSelectedRows();
		
		numeroColumnas = getColumnCount();
		numeroFilasSeleccionadas = getSelectedRowCount();
		
		Object[][] datos = new Object[numeroFilasSeleccionadas][numeroColumnas];
		
		for (i = 0; i < numeroFilasSeleccionadas; i++)
			for (j = 0; j < numeroColumnas; j++)
			{
				datos[i][j] = administradorDatos.getValueAt(filas[i],j);
			}
		
		return datos;
	}
	
	/**
     * M�todo que devuelve una fila. El formato es devuelto en formato de
     * arreglo.
     *
     * @param indice Indica que fila quiere se devuelve.
     *
     * @return fila Arreglo que contiene la fila ha encontrar.
     */
	public Object[] obtenerFila(int indice)
	{
		int i;
		int numeroColumnas = getColumnCount();
		Object[] fila = new Object[numeroColumnas];
		
		for (i = 0; i < numeroColumnas; i++)
			fila[i] = getValueAt(indice,i);
		
		return fila;
	}
	
	/**
     * M�todo que remueve o elimina todos filas seleccionadas.
     */
	public void removerFilasSeleccionada()
	{
		int i;
		int posicion;
		int numeroFilasSeleccionadas = getSelectedRowCount();
		Object[][] datos = obtenerElementosFilasSeleccionadas();
		
		for (i = 0; i < numeroFilasSeleccionadas; i++)
		{
			posicion = 
				buscarPosicionFila(administradorDatos.getColumnName(0),
								   datos[i][0]);
			
			administradorDatos.removeRow(posicion);
		}
	}
	
	/**
     * M�todo que limpia todos los datos de la tabla.
     */
	public void limpiar()
	{
		administradorDatos.setNumRows(0);
	}
	
	/**
     * M�todo que busca una fila en particular en la tabla seg� un dato
     * entregado. El m�todo entrega la posici�n donde se encuentra la fila
     * buscada. Devuelve -1 si la fila no es encontrada.
     *
     * @param nombreColumna Nombre de la columna en se quiere buscar el dato.
     * @param dato Dato ha buscar.
     *
     * @return posicion La posici�n de la fila ha buscar. Es -1 si no fue
     *                  encontrada la fila.
     */
	public int buscarPosicionFila(String nombreColumna, Object dato)
	{
		Object elemento;
		int i;
		int numeroFilas;
		int posicion;
		int columna;
		
		columna = administradorDatos.findColumn(nombreColumna);
		
		numeroFilas = administradorDatos.getRowCount();
		posicion = -1; // no encontrado
		
		for (i = 0; i < numeroFilas; i++)
		{
			elemento = administradorDatos.getValueAt(i,columna);
			
			if (elemento.equals(dato))
			{
				posicion = i;
				break;
			}
		}
		
		return posicion;
	}
	
	/**
     * M�todo que devuelve cantidades de filas seg�n el objeto buscado. El
     * formato que entrega es una matriz.
     *
     * @param nombreColumna Nombre de la columna en se quiere buscar el dato.
     * @param dato Dato ha buscar.
     *
     * @return datos Los datos devueltos seg�n la consulta hecha a la tabla.
     */
	public Object[][] buscarFila(String nombreColumna, Object dato)
	{
		Vector elementos = new Vector();
		Object[] fila;
		Object elemento;
		Object[][] datos;
		int i;
		int numeroFilas;
		int numeroColumnas;
		int posicion;
		int columna;
		
		columna = administradorDatos.findColumn(nombreColumna);
		
		numeroFilas = administradorDatos.getRowCount();
		numeroColumnas = administradorDatos.getColumnCount();
		
		fila = new Object[numeroColumnas];
		
		posicion = -1; // no encontrado
		
		for (i = 0; i < numeroFilas; i++)
		{
			elemento = administradorDatos.getValueAt(i,columna);
			if (elemento.equals(dato))
			{
				fila = obtenerFila(i);
				elementos.addElement(fila);
				posicion = i;
			}
		}
		
		datos = new Object[elementos.size()][numeroColumnas];
		datos = (Object [][]) elementos.toArray();
		
		return datos;
	}
	
	/**
     * M�todo que devuelve todos los elementos que contiene la tabla. El formato
     * que los devuelve es el de una matriz de objetos.
     *
     * @return datos Matriz que contiene todos los elementos de la tabla.
     */
	public Object[][] obtenerTodasFilas()
	{
		Object[][] datos;
		int i;
		int j;
		int numeroFilas;
		int numeroColumnas;
		
		numeroFilas = administradorDatos.getRowCount();
		numeroColumnas = administradorDatos.getColumnCount();
		
		datos = new Object[numeroFilas][numeroColumnas];
		
		for (i = 0; i < numeroFilas; i++)
			for (j = 0 ;j < numeroColumnas; j++ )
				datos[i][j] = administradorDatos.getValueAt(i,j);
		
		return datos;
	}
	
	/**
     * M�todo que fija la cantidad de filas y columnas de una tabla.
     *
     * @param filas Indica la cantidad de filas.
     * @param columnas Indica la cantidad de columnas.
     */
	public void establecerFilasColumnas(int filas, int columnas)
	{
		administradorDatos.setRowCount(filas);
		administradorDatos.setColumnCount(columnas);
	}
	
	/**
	 * M�todo que cambia el ancho de la columna especificada en la tabla.
	 *
	 * @param col Indica la columna de la tabla.
	 * @param ancho Indica el ancho establecido para la columna especificada.
	 */
	public void establecerAnchoColumna(int col, int ancho)
	{
		TableColumn columna = this.getColumnModel().getColumn(col);
		columna.setPreferredWidth(ancho);
	}
	
	/**
	 * M�todo que cambia el color del fondo y establece una ayuda de una columna
	 * que pertenece a un punto ficticio.
	 *
	 * @param col Columna de la tabla.
	 * @param color Color asignado al fondo de la columna.
	 * @param tooltips Texto de ayuda de la columna.
	 */	
	public void establecerColorColumna(int col, Color color, String tooltips)
	{
		TableColumn columna = this.getColumnModel().getColumn(col);
        DefaultTableCellRenderer celda =
                new DefaultTableCellRenderer();
        celda.setToolTipText(tooltips);
        celda.setBackground(color);
        celda.setHorizontalAlignment(celda.RIGHT);
        columna.setCellRenderer(celda);
	}
	
	/**
	 * M�todo que cambia el color del fondo y establece una ayuda de una fila
	 * que pertenece a un punto ficticio.
	 *
	 * @param fila Fila de la tabla.
	 * @param color Color asignado al fondo de la fila.
	 * @param tooltips Texto de ayuda de la fila.
	 */
	public void establecerColorFila(int fila, Color color, String tooltips)
	{
		ColorFilaTabla filaColor = new ColorFilaTabla(color, tooltips);
		filaColor.establecerColorFila(fila);
		this.setDefaultRenderer(this.getColumnClass(0), filaColor);
	}
	
	/**
     * M�todo que devuelve el objeto que administra los datos de la tabla.
     *
     * @return administradorDatos El adminitrador de los datos de la tabla.
     */
	public DefaultTableModel obtenerAdministradorDatos()
	{
		return administradorDatos;
	}
	
	/**
     * M�todo que obtiene el �ndice donde se encuentra la columna dado el nombre
     * de la columna.
     *
     * @param nombreColumna El nombre de la columna que se quiere encontrar.
     *
     * @return posicion Posici�n de la columna encontrada.
     */
	public int obtenerIndiceColumna(String nombreColumna)
	{
		return administradorDatos.findColumn(nombreColumna);
	}
	
	/**
     * M�todo que obtiene el nombre de la columna dado una posici�n.
     * 
     * @param posicion Posici�n de la columna que se quiere encontrar.
     *
     * @return nombreColumna Nombre de la columna econtrada.
     */
	public String obtenerNombreColumna(int posicion)
	{
		return administradorDatos.getColumnName(posicion);
	}
	
	/**
     * M�todo que devuelve si la tabla en general es editable o no.
     *
     * @return editableTabla Indica si la tabla es editable o no.
     */
	public boolean esEditableTabla()
	{
		return editableTabla;
	}
}