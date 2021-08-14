/**
 * @(#)SubPanelConfiguracionBP.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase permite configurar la
 * red neuronal BP. La instancia de esta clase se carga en tiempo de ejecución,
 * según la elección de la red que haya elegido en el panel entrenar de
 * economía.
 * 
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Color
 * @see Font
 * @see ResultSet
 * @see SQLException
 * @see JPanel
 * @see JLabel
 * @see JComboBox
 * @see JOptionPane
 * @see ImageIcon
 * @see SwingConstants
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see PanelConfiguracionEconomia
 * @see ConfiguracionNeuronalBP
 * @see Servicio
 * @see CampoNumerico
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class SubPanelConfiguracionBP extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** Cuadro de texto donde se elige el número de ciclos. */
	private CampoNumerico ciclo;
		
	/** Cuadro de texto donde se elige el número de neuronas CO1. */
	private CampoNumerico numeroNeuronasCO1;
	
	/** Cuadro de texto donde se elige el número de neuronas CO2. */
	private CampoNumerico numeroNeuronasCO2;
	
	/** Combo box donde se elige el tipo de función de la capa oculta 1. */
	private JComboBox tipoFuncionCO1;
	
  	/** Combo box donde se elige el tipo de función de la capa oculta 2. */
	private JComboBox tipoFuncionCO2;
	
	/** Combo box donde se elige el tipo de función de la capa de salida. */
	private JComboBox tipoFuncionS;
	
	/** Cuadro de texto donde se elige la velocidad del aprendizaje. */
	private CampoNumerico rata;
	
	/**
	 * Cuadro de texto donde se elige la variación de la velocidad del
	 * aprendizaje.
	 */
	private CampoNumerico variacionRata;
	
	/** Cuadro de texto donde se elige error mínimo aceptable. */
	private CampoNumerico errorMinimo;
	
	/** Cuadro de texto donde se elige error máximo aceptable. */
	private CampoNumerico errorMaximo;
	
	/** Cuadro de texto donde se elige el porcentaje de la validación cruzada. */
	private CampoNumerico porcentajeCruzada;
	
	/**
     * Método constructor del panel de configuración de la red BP.
     *
     * @param x Inicio X del sub Panel.
     * @param y Inicio Y del sub Panel.
     * @param ancho Ancho del sub Panel.
     * @param largo Largo del sub Panel.
     */
	public SubPanelConfiguracionBP(int x,int y,int ancho,int largo)
	{
		super(null);
		
		setBounds(x,y,ancho,largo);
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
	}
	
	/**
	 * Método en donde se configuran las propiedades que tiene el panel. Las
	 * propiedades que se cambian en este método corresponden a los atributos
	 * que se derivan de la clase JPanel.
	 */
	public void configurarPanel()
	{
		setLayout(null);
	}
	
	/**
	 * Método en donde se configuran los componentes GUI que tiene el sub-panel
	 * configuración BP.
	 */
	public void configurarComponentes()
	{
		configurarTexto();
		configurarTextField();
		configurarComboBox();
	}
	
	/**
     * Método que configura los textos que aparecen en el sub-panel 
     * configuración BP.
     */
	private void configurarTexto()
	{
		double[] array = ConfiguracionNeuronalBP.entregarValores();
		
		// creando fuente para la recomendacion
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// creando texto de textField
		JLabel[] textoExplicacion = new JLabel[5];
		textoExplicacion[0] =
			new JLabel(new ImageIcon("../img/economia_configuracion_bp.gif"));
		textoExplicacion[1] =
			new JLabel("Puede ingresar los principales parámetros de " +
			"configuración para la Red Neuronal Artificial Back - " +
			"Propagation.");
		textoExplicacion[2] =
			new JLabel("Pruebe el desempeño de predicción de la red cambiando " +
			"los distintos parámetros de configuración: número de");
		textoExplicacion[3] =
			new JLabel("ciclos, neuronas en las capas, tipos de funciones en " +
			"las capas, tipo de función de salida, velocidad de aprendizaje,");
		textoExplicacion[4] =
			new JLabel("error aceptable y porcentaje de validación cruzada.");
		JLabel textoCiclo = new JLabel("Número de Ciclos:");
		JLabel textoNeuronasCO1 = new JLabel("Neuronas en Capa Oculta 1:");
		JLabel textoNeuronasCO2 = new JLabel("Neuronas en Capa Oculta 2:");
		JLabel textoTipoFuncionCO1= new JLabel("Tipo de Función de la Capa 1:");
		JLabel textoTipoFuncionCO2= new JLabel("Tipo de Función de la Capa 2:");
		JLabel textoTipoFuncionS= new JLabel("Tipo de Función de Salida:");
		JLabel textoRata = new JLabel("Rata de Aprendizaje:");
		JLabel textoVariacionRata = new JLabel("Variación de la Rata:");
		JLabel textoErrorMinimo = new JLabel("Error Mínimo Aceptable:");
		JLabel textoErrorMaximo = new JLabel("Error Máximo Aceptable:");
		JLabel textoPorcentajeCruzada = new JLabel("% Validación Cruzada:");
		
		// creando texto de recomendación
		JLabel recomendacionCiclo = new 
			JLabel((int)+array[13]/1000+" y su rango [1, 300]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionNeuronasCO1 = new 
			JLabel("Mayor que el siguiente campo y su rango [1, "+(int)array[2]+"]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionNeuronasCO2 = new 
			JLabel("Menor que el anterior campo y su rango [1, "+(int)array[3]+"]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionTipoFuncionCO1 = 
			new JLabel("TanHiperbólica",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionTipoFuncionCO2 = 
			new JLabel("TanHiperbólica",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionTipoFuncionS = 
			new JLabel("Lineal",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionRata = 
			new JLabel("Un valor bajo y su rango (0, 0.03]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionVariacionRata = 
			new JLabel(array[18]+" y su rango [1, 3]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionErrorMinimo = 
			new JLabel((int)array[15]+" cifras y su rango [1, 6]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionErrorMaximo = 
			new JLabel((int)array[16]+" cifras y su rango [1, 4]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionPorcentajeCruzada = new 
			JLabel("Un valor bajo y su rango [0, 50]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		
		// posicionando textos de textfield
		textoExplicacion[0].setBounds(20, 25, 100, 80);
		textoExplicacion[1].setBounds(130, 25, 710, 15);
		textoExplicacion[2].setBounds(130, 40, 710, 15);
		textoExplicacion[3].setBounds(130, 55, 710, 15);
		textoExplicacion[4].setBounds(130, 70, 710, 15);
		textoCiclo.setBounds(20,120,190,20);
		textoNeuronasCO1.setBounds(20,150,190,20);
		textoNeuronasCO2.setBounds(20,180,190,20);
		textoTipoFuncionCO1.setBounds(20,210,190,20);
		textoTipoFuncionCO2.setBounds(20,240,190,20);
		textoTipoFuncionS.setBounds(20,270,190,20);
		textoRata.setBounds(20,300,190,20);
		textoVariacionRata.setBounds(20,330,190,20);
		textoErrorMinimo.setBounds(20,360,190,20);
		textoErrorMaximo.setBounds(20,390,190,20);
		textoPorcentajeCruzada.setBounds(20,420,190,20);
		
		// posicionando textos de recomendacion
		recomendacionCiclo.setBounds(355,120,430,20);
		recomendacionNeuronasCO1.setBounds(355,150,430,20);
		recomendacionNeuronasCO2.setBounds(355,180,430,20);
		recomendacionTipoFuncionCO1.setBounds(355,210,430,20);
		recomendacionTipoFuncionCO2.setBounds(355,240,430,20);
		recomendacionTipoFuncionS.setBounds(355,270,430,20);
		recomendacionRata.setBounds(355,300,430,20);
		recomendacionVariacionRata.setBounds(355,330,430,20);
		recomendacionErrorMinimo.setBounds(355,360,1430,20);
		recomendacionErrorMaximo.setBounds(355,390,1430,20);
		recomendacionPorcentajeCruzada.setBounds(355,420,430,20);
		
		// asignando fuente a al recomendacion
		recomendacionCiclo.setFont(fuenteRecomendacion);
		recomendacionNeuronasCO1.setFont(fuenteRecomendacion);
		recomendacionNeuronasCO2.setFont(fuenteRecomendacion);
		recomendacionTipoFuncionCO1.setFont(fuenteRecomendacion);
		recomendacionTipoFuncionCO2.setFont(fuenteRecomendacion);
		recomendacionTipoFuncionS.setFont(fuenteRecomendacion);
		recomendacionRata.setFont(fuenteRecomendacion);
		recomendacionVariacionRata.setFont(fuenteRecomendacion);
		recomendacionErrorMinimo.setFont(fuenteRecomendacion);
		recomendacionErrorMaximo.setFont(fuenteRecomendacion);
		recomendacionPorcentajeCruzada.setFont(fuenteRecomendacion);
		
		// agreganando texto de field text
		add(textoExplicacion[0]);
		add(textoExplicacion[1]);
		add(textoExplicacion[2]);
		add(textoExplicacion[3]);
		add(textoExplicacion[4]);
		add(textoCiclo);
		add(textoNeuronasCO1);
		add(textoNeuronasCO2);
		add(textoTipoFuncionCO1);
		add(textoTipoFuncionCO2);
		add(textoTipoFuncionS);
		add(textoRata);
		add(textoVariacionRata);
		add(textoErrorMinimo);
		add(textoErrorMaximo);
		add(textoPorcentajeCruzada);
		
		// agreganando texto de recomendacion
		add(recomendacionCiclo);
		add(recomendacionNeuronasCO1);
		add(recomendacionNeuronasCO2);
		add(recomendacionTipoFuncionCO1);
		add(recomendacionTipoFuncionCO2);
		add(recomendacionTipoFuncionS);
		add(recomendacionRata);
		add(recomendacionVariacionRata);
		add(recomendacionErrorMinimo);
		add(recomendacionErrorMaximo);
		add(recomendacionPorcentajeCruzada);
	}
	
	/**
     * Método donde se configura los elementos campos de texto en panel de
     * configuración de la red BP.
     */
	private void configurarTextField()
	{
		double[] array = ConfiguracionNeuronalBP.entregarValores();
		
		// asignando cuadro de texto
		ciclo = new CampoNumerico((int) array[13]/1000);
		numeroNeuronasCO1 = new CampoNumerico((int)array[7]);
		numeroNeuronasCO2 = new CampoNumerico((int)array[8]);
		rata = new CampoNumerico(array[17]);
		variacionRata = new CampoNumerico(array[18]);
		errorMinimo = new CampoNumerico((int)array[15]);
		errorMaximo = new CampoNumerico((int)array[16]);
		porcentajeCruzada = new CampoNumerico(array[14]*100);
		
		// asignando posiciones
		ciclo.setBounds(205,120,130,20);
		numeroNeuronasCO1.setBounds(205,150,130,20);
		numeroNeuronasCO2.setBounds(205,180,130,20);
		rata.setBounds(205,300,130,20);
		variacionRata.setBounds(205,330,130,20);
		errorMinimo.setBounds(205,360,130,20);
		errorMaximo.setBounds(205,390,130,20);
		porcentajeCruzada.setBounds(205,420,130,20);
		
		// asignando tips
		ciclo.setToolTipText
			("Ingrese la cantidad de ciclos que durará el entrenamiento");
		numeroNeuronasCO1.setToolTipText
			("Ingrese el número de neuronas de la capa oculta 1");
		numeroNeuronasCO2.setToolTipText
			("Ingrese el número de neuronas de la capa oculta 2");
		rata.setToolTipText
			("Ingrese la velocidad del aprendizaje");
		variacionRata.setToolTipText
			("Ingrese la variación de la velocidad del aprendizaje");
		errorMinimo.setToolTipText
			("Ingrese las cifras de exactitud aceptables");
		errorMaximo.setToolTipText
			("Ingrese las cifras con las cuales se desborda el aprendizaje");
		porcentajeCruzada.setToolTipText
			("Ingrese el porcentaje de los datos de entrenamiento");
	}
	
	/**
     * Método donde se configura los elementos combo box en panel 
     * de configuración de la red BP.
     */
	private void configurarComboBox()
	{
		// carga los valores de la configuracion neuronal BP
		double[] array = ConfiguracionNeuronalBP.entregarValores();
		
		// borde del panel
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// titulos
		TitledBorder titulo = new TitledBorder(borde, 
			" Configuración de la Red Neuronal Artificial Back - Propagation (BP) ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// creando combo box
		tipoFuncionCO1 = new JComboBox();
		tipoFuncionCO2 = new JComboBox();
		tipoFuncionS = new JComboBox();
		
		// cargandos las funciones
		cargarComboFunciones();
		
		// configurando combo box
		tipoFuncionCO1.setSelectedIndex((int) array[10]);
		tipoFuncionCO2.setSelectedIndex((int) array[11]);
		tipoFuncionS.setSelectedIndex((int) array[12]);
		
		// agregando titulo
		setBorder(titulo);
		
		// posicionando combo box
		tipoFuncionCO1.setBounds(205,210,130,20);
		tipoFuncionCO2.setBounds(205,240,130,20);
		tipoFuncionS.setBounds(205,270,130,20);
		
		// asignando tips a los combo box
		tipoFuncionCO1.setToolTipText
			("Elija la función de transferencia de la capa oculta 1");
		tipoFuncionCO2.setToolTipText
			("Elija la función de transferencia de la capa oculta 2");
		tipoFuncionS.setToolTipText
			("Elija la función de transferencia de la capa de salida");
		
		// agregando cuadro de texto
		add(ciclo);
		add(numeroNeuronasCO1);
		add(numeroNeuronasCO2);
		add(rata);
		add(variacionRata);
		add(errorMinimo);
		add(errorMaximo);
		add(porcentajeCruzada);
		
	    // agregando como box
		add(tipoFuncionCO1);
		add(tipoFuncionCO2);
		add(tipoFuncionS);
	}
	
	/**
	 * Método que adjunta los escuchadores eventos a los componentes GUI que
	 * tiene el panel de configuración. En particular, este método no se
	 * implementa porque este panel no maneja eventos.
	 */
	public void configurarEventos()
	{
	}
	
	/**
     * Método que carga los combos de funciones para el menu del subpanel.
     */
	private void cargarComboFunciones()
	{
		String funcion;
		ResultSet resultado;
		BaseDatoMotor conexion = new BaseDatoMotor();
		BaseDatoTablaBasica tablaFuncionNeuronal = new BaseDatoTablaBasica
											(conexion,"funcion_neuronal");
		
		tipoFuncionCO1.removeAllItems();
		tipoFuncionCO1.removeAllItems();
		tipoFuncionS.removeAllItems();
		
		conexion.conectar();
		
		resultado = tablaFuncionNeuronal.buscarCondicion("");
		
		try
		{
			resultado.first();
			do
			{
				funcion = resultado.getString("descripcion_funcion_neuronal");
				tipoFuncionCO1.addItem(funcion);
				tipoFuncionCO2.addItem(funcion);
				tipoFuncionS.addItem(funcion);
				resultado.next();
			}
			while (!resultado.isAfterLast());
		}
		catch (SQLException ex)
		{
			System.out.println("Error al cargar la tabla funcion_neuronal.");
		}
		
		conexion.desconectar();
	}
	
	/**
     * Método que carga una configuración en el panel de configuración de la red
     * neruonal.
     */
	public void cargarConfiguracion()
	{
		double[] parametros = ConfiguracionNeuronalBP.entregarValores();
		
		ciclo.setValue((int)parametros[13]/1000);
		numeroNeuronasCO1.setValue((int)parametros[7]);
		numeroNeuronasCO2.setValue((int)parametros[8]);
		tipoFuncionCO1.setSelectedIndex((int)parametros[10]);
		tipoFuncionCO2.setSelectedIndex((int)parametros[11]);
		tipoFuncionS.setSelectedIndex((int)parametros[12]);
		rata.setValue(parametros[17]);
		variacionRata.setValue(parametros[18]);
		errorMinimo.setValue((int)parametros[15]);
		errorMaximo.setValue((int)parametros[16]);
		porcentajeCruzada.setValue(parametros[14] * 100);
	}
	
	/**
     * Método que configura la red a una configuración por defecto del proyecto.
     * Cuando se carga la configuración, esta se puede visualizar en el panel.
     */
	public void restaurarConfiguracion()
	{
		ConfiguracionNeuronalBP.cargar(Proyecto.CODIGO_NUEVO);
   		cargarConfiguracion();
	}
	
	/**
     * Método que cambia una configuración en la red neuronal Back -
     * Propagation. Verificando si es correcta la información ingresada.
     */
	public void establecerConfiguracion()
	{
		String error = validarConfiguracion();
	 	
	 	if (error == "")
	 	{
	 		double[] array = 
			new double[ConfiguracionNeuronalBP.TOTAL_ELEMENTOS_CONFIGURACION];
			
			array[7] = numeroNeuronasCO1.getValueInt();
			array[8] = numeroNeuronasCO2.getValueInt();
			array[10] = tipoFuncionCO1.getSelectedIndex();
			array[11] = tipoFuncionCO2.getSelectedIndex();
			array[12] = tipoFuncionS.getSelectedIndex();
			array[13] = ciclo.getValueInt() * 1000;
			array[14] = porcentajeCruzada.getValueDouble() / 100.0;
			array[15] = errorMinimo.getValueInt();
			array[16] = errorMaximo.getValueInt();
			array[17] = rata.getValueDouble();
			array[18] = variacionRata.getValueDouble();
			
			error = ConfiguracionNeuronalBP.validarValores(array);
			
			if (error == "")
				ConfiguracionNeuronalBP.modificarValores(array);
			else
				mostrarErrorCambiar(error, "Error en los rangos");
		}
	 	else
	 	{
	 		mostrarErrorCambiar(error, "Error en la sintaxis");
	 	}
	}
	
	/**
     * Método que verifica si los datos ingresados en la configuración tiene una 
     * sintaxis correcta.
     *
     * @return error Retorna un string con el error cometido.
     */
	public String validarConfiguracion()
	{
		String error = "";
		
		// validando ciclos
		if (!Servicio.esNumero(ciclo.getText()) ||
			Servicio.contarCaracter(ciclo.getText(), '.') > 0)
			error += "- En el número de ciclos.\n";
		
		// validando numero Neuronas Co1
		if (!Servicio.esNumero(numeroNeuronasCO1.getText()) ||
			Servicio.contarCaracter(numeroNeuronasCO1.getText(), '.') > 0)
			error += "- En el número de neuronas de la capa oculta 1.\n";
		
		// validando numero Neuronas Co2
		if (!Servicio.esNumero(numeroNeuronasCO2.getText()) ||
			Servicio.contarCaracter(numeroNeuronasCO2.getText(), '.') > 0)
			error += "- En el número de neuronas de la capa oculta 2.\n";
		
		// validando numero rata
		if (!Servicio.esNumero(rata.getText()))
			error += "- En la rata de aprendizaje.\n";
		
		// validando la variacion rata
		if (!Servicio.esNumero(variacionRata.getText()))
			error += "- En la variación de la rata.\n";
		
		// validando el error mínimo
		if (!Servicio.esNumero(errorMinimo.getText()) ||
			Servicio.contarCaracter(errorMinimo.getText(), '.') > 0)
			error += "- En el error mínimo aceptable.\n";
		
		// validando el error maximo
		if (!Servicio.esNumero(errorMaximo.getText()) ||
			Servicio.contarCaracter(errorMaximo.getText(), '.') > 0)
			error += "- En el error máximo aceptable.\n";
		
		// validando el error porcentaje cruzada
		if (!Servicio.esNumero(porcentajeCruzada.getText()))
			error += "- En el porcentaje de validación cruzada.\n";
		
		return error;
	}
	
	/**
     * Método en donde se maneja la habilitación o deshabilitación de los
     * botones principales de de la configuración (restaurar y establecer).
     * En particular, este método no se implementa, ya que lo maneja el panel
     * contenedor de este sub-panel.
     *
     * @param habilitado Inidica si los botones se habilitan o deshabilitan.
     */
    public void habilitarBotones(boolean habilitado)
    {
    }
	
	/**
     * Método que muestra un texto por resultado de un error cometido al
     * intentar cambiar la configuración.
     *
     * @param error String con el detalle del error.
     * @param titulo String con el título del error.
     */
	private void mostrarErrorCambiar(String error, String titulo)
	{
		error = "Se han cometido los siguientes errores:\n" + error;
		JOptionPane.showMessageDialog(this, error, titulo,
									  JOptionPane.ERROR_MESSAGE);
	}
}