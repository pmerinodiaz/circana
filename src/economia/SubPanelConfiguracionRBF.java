/**
 * @(#)SubPanelConfiguracionRBF.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingeniería en Computación, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que extiende de la clase JPanel. En esta clase permite configurar la
 * red neuronal RBF. La instancia de esta clase se carga en tiempo de ejecución,
 * según la elección de la red que haya elegido en el panel entrenar de
 * economía.
 * 
 * @version 2.0 01/03/05
 * @author Paul Leger
 * @see Color
 * @see Font
 * @see JPanel
 * @see JLabel
 * @see JOptionPane
 * @see ImageIcon
 * @see SwingConstants
 * @see Border
 * @see LineBorder
 * @see TitledBorder
 * @see PanelConfiguracionEconomia
 * @see ConfiguracionNeuronalRBF
 * @see Servicio
 * @see CampoNumerico
 * @see InterfacePanel
 * @see InterfaceConfiguracion
 */
public class SubPanelConfiguracionRBF extends JPanel
	implements InterfacePanel, InterfaceConfiguracion
{
	/** Cuadro de texto donde se elige el número de centroides. */
	private CampoNumerico numeroCentroides;
	
	/** Cuadro de texto donde se elige el número de ciclos no supervisado. */
	private CampoNumerico cicloNoSupervisada;
	
  	/** Cuadro de texto donde se elige el número de ciclos supervisado. */
	private CampoNumerico cicloSupervisada;
	
	/** Cuadro de texto donde se elige la velocidad del aprendizaje supervisado. */
	private CampoNumerico rataSupervisada;
	
	/**
	 * Cuadro de texto donde se elige la variación de la velocidad del
	 * aprendizaje no supervisado.
	 */
	private CampoNumerico variacionRataSupervisada;
	
	/** Cuadro de texto donde se elige la velocidad inicial no supervisado. */
	private CampoNumerico rataINoSupervisada;
	
	/** Cuadro de texto donde se elige la velocidad final no supervisada. */
	private CampoNumerico rataFNoSupervisada;
	
	/**
	 * Cuadro de texto donde se elige el error mínimo aceptable para la red
	 * neuronal.
	 */
	private CampoNumerico errorMinimo;
	
	/**
	 * Cuadro de texto donde se elige el error mínimo aceptable para la red
	 * neuronal.
	 */
	private CampoNumerico errorMaximo;
	
	/** Cuadro de texto donde se elige el porcentaje de la validación cruzada. */
	private CampoNumerico porcentajeCruzada;
	
	/** Check box donde se elige habilitar los centroides dinámicos. */
	private JCheckBox habilitarCentroidesDinamicos;
	
	/** Panel que separa la información de un entrenamiento supervisado. */
	private JPanel supervisado;
	
	/** Panel que separa la información de un entrenamiento no supervisado. */
	private JPanel noSupervisado;
	
	/** Panel que separa la información de las opciones generales de la red. */
	private JPanel general;
	
	/**
     * Método constructor del panel de configuración de la red RBF.
     * 
     * @param x Inicio X del sub-panel.
     * @param y Inicio Y del sub-panel.
     * @param ancho Ancho del sub-panel.
     * @param largo Largo del sub-panel.
     */
	public SubPanelConfiguracionRBF(int x,int y,int ancho,int largo)  
	{
		super(null);
		
		setBounds(x,y,ancho,largo);
		
		// Configurar
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
	 * configuración RBF.
	 */
	public void configurarComponentes()
	{
		configurarPaneles();
		configurarTexto();
		configurarElementosParticipantes();
	}
	
	/**
     * Método que configurar los paneles que participan el sub-panel de
     * configuración de una red RBF.
     */
	private void configurarPaneles()
	{
		supervisado = new JPanel(null);
		noSupervisado = new JPanel(null);
		general = new JPanel(null);
		
		// Borde del panel.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Títulos.
		TitledBorder titulo = new TitledBorder(borde, 
			" Configuración de la Red Neuronal Artificial Radial Base Fase (RBF) ",
			TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloNoSupervisado = new TitledBorder(borde, 
			" Aprendizaje No Supervisado ",
			TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloSupervisado = new TitledBorder(borde,
			" Aprendizaje Supervisado ",
			TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder tituloGeneral = new TitledBorder(borde,
			" Opciones Generales ",
			TitledBorder.LEFT, TitledBorder.TOP);
		
		// agregando titulo
		supervisado.setBorder(tituloSupervisado);
		noSupervisado.setBorder(tituloNoSupervisado);
		general.setBorder(tituloGeneral);
		setBorder(titulo);
		
		// posicionado paneles
		noSupervisado.setBounds(20,85,760,150);
		supervisado.setBounds(20,242,760,130);
		general.setBounds(20,375,760,120);
		
		// agregando paneles
		add(supervisado);
		add(noSupervisado);
		add(general);
	}
	
	/**
     * Método que configura el texto que aparece en el sub-panel configuración
     * RBF.
     */
	private void configurarTexto()
	{
		double[] array = ConfiguracionNeuronalRBF.entregarValores();
		
		// creando fuente para la recomendacion
		Font fuenteRecomendacion = new Font("Arial", Font.PLAIN, 9);
		
		// creando texto de textField
		JLabel[] textoExplicacion = new JLabel[4];
		textoExplicacion[0] =
			new JLabel(new ImageIcon("../img/economia_configuracion_rbf.gif"));
		textoExplicacion[1] =
			new JLabel("Puede ingresar los principales parámetros de " +
			" configuración para la Red Neuronal Artificial Radial " +
			" Base Fase.");
		textoExplicacion[2] =
			new JLabel("Pruebe el desempeño de predicción de la red cambiando " +
			"los distintos parámetros de configuración: aprendizaje");
		textoExplicacion[3] =
			new JLabel("no supervisado, aprendizaje supervisado y opciones " +
			"generales.");
		JLabel textoCicloNoSupervisada = new JLabel("Número de Ciclos:");
		JLabel textoCentroides = new JLabel("Número de Centroides:");
		JLabel textoRataINoSupervisada = new JLabel("Rata Inicial:");
		JLabel textoRataFNoSupervisada = new JLabel("Rata Final:");
		JLabel textoCicloSupervisada = new JLabel("Número de Ciclos:");
		JLabel textoRataSupervisada = new JLabel("Rata de Aprendizaje:");
		JLabel textoVariacionRataSupervisada = new JLabel("Variación de la Rata:");
		JLabel textoErrorMinimo = new JLabel("Error Mínimo Aceptable:");
		JLabel textoErrorMaximo = new JLabel("Error Máximo Aceptable:");
		JLabel textoPorcentajeCruzada = new JLabel("% Validacion Cruzada:");
		
		// creando texto de recomendacion
		JLabel recomendacionCicloNoSupervisada =
			new JLabel((int)array[7]/1000+" y su rango [1, 50]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionCentroides =
			new JLabel((int)array[5]+" y su rango [1, "+(int)array[2]+"]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionRataINoSupervisada =
			new JLabel("Un valor cercano a 0.5 y su rango (0, 0.6]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionRataFNoSupervisada =
			new JLabel("Un valor menor a la inicial y su rango (0, 0.6]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionHabilitarCentroidesDinamicos =
			new JLabel("Habilitar la opción",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionCicloSupervisada =
			new JLabel((int)array[8]/1000+" y su rango [1, 200]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionRataSupervisada =
			new JLabel("Un valor bajo y su rango (0, 0.1]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionVariacionRataSupervisada =
			new JLabel(array[13]+" y su rango [1, 3]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionErrorMinimo =
			new JLabel((int)array[10]+" cifras y su rango [1, 6]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionErrorMaximo =
			new JLabel((int)array[11]+" cifras y su rango [1, 4]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		JLabel recomendacionPorcentajeCruzada =
			new JLabel("Un valor bajo y su rango [0, 50]",
			new ImageIcon("../img/recomendacion.gif"), SwingConstants.LEFT);
		
		// posicionando textos de textfield
		textoCicloNoSupervisada.setBounds(15,20,150,20);
		textoCentroides.setBounds(15,45,150,20);
		textoRataINoSupervisada.setBounds(15,70,150,20);
		textoRataFNoSupervisada.setBounds(15,95,150,20);
		textoCicloSupervisada.setBounds(15,30,150,20);
		textoRataSupervisada.setBounds(15,60,150,20);
		textoVariacionRataSupervisada.setBounds(15,90,150,20);
		textoErrorMinimo.setBounds(15,30,190,20);
		textoErrorMaximo.setBounds(15,60,190,20);
		textoPorcentajeCruzada.setBounds(15,90,190,20);
		
		// posicionando textos de recomendacion
		textoExplicacion[0].setBounds(20, 20, 100, 65);
		textoExplicacion[1].setBounds(130, 25, 710, 15);
		textoExplicacion[2].setBounds(130, 40, 710, 15);
		textoExplicacion[3].setBounds(130, 55, 710, 15);
		recomendacionCicloNoSupervisada.setBounds(355,20,300,20);
		recomendacionCentroides.setBounds(355,45,300,20);
		recomendacionRataINoSupervisada.setBounds(355,70,300,20);
		recomendacionRataFNoSupervisada.setBounds(355,95,300,20);
		recomendacionHabilitarCentroidesDinamicos.setBounds(355,120,300,20);
		recomendacionCicloSupervisada.setBounds(355,30,300,20);
		recomendacionRataSupervisada.setBounds(355,60,300,20);
		recomendacionVariacionRataSupervisada.setBounds(355,90,300,20);
		recomendacionErrorMinimo.setBounds(355,30,300,20);
		recomendacionErrorMaximo.setBounds(355,60,300,20);
		recomendacionPorcentajeCruzada.setBounds(355,90,300,20);
		
		// asignando fuente a al recomendacion
		recomendacionCicloNoSupervisada.setFont(fuenteRecomendacion);
		recomendacionCentroides.setFont(fuenteRecomendacion);
		recomendacionRataINoSupervisada.setFont(fuenteRecomendacion);
		recomendacionRataFNoSupervisada.setFont(fuenteRecomendacion);
		recomendacionHabilitarCentroidesDinamicos.setFont(fuenteRecomendacion);
		recomendacionCicloSupervisada.setFont(fuenteRecomendacion);
		recomendacionRataSupervisada.setFont(fuenteRecomendacion);
		recomendacionVariacionRataSupervisada.setFont(fuenteRecomendacion);
		recomendacionErrorMinimo.setFont(fuenteRecomendacion);
		recomendacionErrorMaximo.setFont(fuenteRecomendacion);
		recomendacionPorcentajeCruzada.setFont(fuenteRecomendacion);
		
		// asignado texto de textfield a los paneles
		noSupervisado.add(textoCentroides);
		noSupervisado.add(textoCicloNoSupervisada);
		noSupervisado.add(textoRataINoSupervisada);
		noSupervisado.add(textoRataFNoSupervisada);
		supervisado.add(textoCicloSupervisada);
		supervisado.add(textoRataSupervisada);
		supervisado.add(textoVariacionRataSupervisada);
		general.add(textoErrorMinimo);
		general.add(textoErrorMaximo);
		general.add(textoPorcentajeCruzada);
		
		// asignado texto de recomendacion a los paneles
		add(textoExplicacion[0]);
		add(textoExplicacion[1]);
		add(textoExplicacion[2]);
		add(textoExplicacion[3]);
		noSupervisado.add(recomendacionCentroides);
		noSupervisado.add(recomendacionCicloNoSupervisada);
		noSupervisado.add(recomendacionRataINoSupervisada);
		noSupervisado.add(recomendacionRataFNoSupervisada);
		noSupervisado.add(recomendacionHabilitarCentroidesDinamicos);
		supervisado.add(recomendacionCicloSupervisada);
		supervisado.add(recomendacionRataSupervisada);
		supervisado.add(recomendacionVariacionRataSupervisada);
		general.add(recomendacionErrorMinimo);
		general.add(recomendacionErrorMaximo);
		general.add(recomendacionPorcentajeCruzada);
	}
	
	/**
     * Método donde se configura los elementos que participan en la red neuronal 
     * RBF. 
     */
	private void configurarElementosParticipantes()
	{
		double[] array = ConfiguracionNeuronalRBF.entregarValores();
		
		// asignando cuadro de texto para no supervisada
		cicloNoSupervisada = new CampoNumerico((int)array[7]/1000);
		numeroCentroides = new CampoNumerico((int)array[5]);
		rataINoSupervisada = new CampoNumerico(array[14]);
		rataFNoSupervisada = new CampoNumerico(array[15]);
		
		// asignando cuadro de texto para supervisada
		cicloSupervisada = new CampoNumerico((int)array[8]/1000);
		rataSupervisada = new CampoNumerico(array[12]);
		variacionRataSupervisada = new CampoNumerico(array[13]);
		
		// asignando cuadro de texto para opciones generales
		errorMinimo = new CampoNumerico((int)array[10]);
		errorMaximo = new CampoNumerico((int)array[11]);
		porcentajeCruzada = new CampoNumerico(array[9]*100);
		
		habilitarCentroidesDinamicos = 
			new JCheckBox("Crecer en forma dinámica los centroides",
			ConfiguracionNeuronalRBF.obtenerHabilitarCentroidesDinamicos());
		habilitarCentroidesDinamicos.setHorizontalTextPosition(JCheckBox.LEFT);
		
		// posicionando del cuadro de texto y otro en el panel no supervisado
		cicloNoSupervisada.setBounds(205,20,120,20);
		numeroCentroides.setBounds(205,45,120,20);
		rataINoSupervisada.setBounds(205,70,120,20);
		rataFNoSupervisada.setBounds(205,95,120,20);
		habilitarCentroidesDinamicos.setBounds(10,120,270,20);
		
		// posicionando los cuadro de texto no supervisado
		cicloSupervisada.setBounds(205,30,120,20);
		rataSupervisada.setBounds(205,60,120,20);
		variacionRataSupervisada.setBounds(205,90,120,20);
		
		// posicionando los cuadros de texto generales
		errorMinimo.setBounds(205,30,120,20);
		errorMaximo.setBounds(205,60,120,20);
		porcentajeCruzada.setBounds(205,90,120,20);
		
		// asignando tips
		cicloNoSupervisada.setToolTipText
			("Ingrese la cantidad de ciclos que durará el entrenamiento no supervisado");
		numeroCentroides.setToolTipText
			("Ingrese el número de centroides");
		rataINoSupervisada.setToolTipText
			("Ingrese la velocidad inicial del aprendizaje no supervisado");
		rataFNoSupervisada.setToolTipText
			("Ingrese la velocidad final del aprendizaje no supervisado");
		habilitarCentroidesDinamicos.setToolTipText
			("Indicar si el número de centroides crece en forma dinámica en el " +
			 "aprendizaje no supervisado");
		cicloSupervisada.setToolTipText
			("Ingrese la cantidad de ciclos que durará el entrenamiento supervisado");
		rataSupervisada.setToolTipText
			("Ingrese la velocidad del aprendizaje supervisado");
		variacionRataSupervisada.setToolTipText
			("Ingrese la variación de la velocidad del aprendizaje supervisado");
		errorMinimo.setToolTipText
			("Ingrese las cifras de exactitud aceptables");
		errorMaximo.setToolTipText
			("Ingrese las cifras con las cuales se desborda el aprendizaje");
		porcentajeCruzada.setToolTipText
			("Ingrese el porcentaje de los datos de entrenamiento");
		
		// asignado cuadro de texto a los paneles
		noSupervisado.add(numeroCentroides);
		noSupervisado.add(cicloNoSupervisada);
		noSupervisado.add(rataINoSupervisada);
		noSupervisado.add(rataFNoSupervisada);
		noSupervisado.add(habilitarCentroidesDinamicos);
		supervisado.add(cicloSupervisada);
		supervisado.add(rataSupervisada);
		supervisado.add(variacionRataSupervisada);
		general.add(errorMinimo);
		general.add(errorMaximo);
		general.add(porcentajeCruzada);
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
     * Método que carga una configuración en el panel de configuración de la red
     * neruonal.
     */
	public void cargarConfiguracion()
	{
		double[] parametros = ConfiguracionNeuronalRBF.entregarValores();
		
		// aprendizaje no supervisado
		cicloNoSupervisada.setValue((int)parametros[7]/1000);
		numeroCentroides.setValue((int)parametros[5]);
		rataINoSupervisada.setValue(parametros[14]);
		rataFNoSupervisada.setValue(parametros[15]);
		habilitarCentroidesDinamicos.setSelected(ConfiguracionNeuronalRBF
			.obtenerHabilitarCentroidesDinamicos());
		
		// aprendizaje supervisado
		cicloSupervisada.setValue((int)parametros[8]/1000);
		rataSupervisada.setValue(parametros[12]);
		variacionRataSupervisada.setValue(parametros[13]);
		
		// opciones generales
		errorMinimo.setValue((int)parametros[10]);
		errorMaximo.setValue((int)parametros[11]);
		porcentajeCruzada.setValue(parametros[9] * 100);
	}
	
	/**
     * Método que configura la red a una configuración por defecto del proyecto.
     * Cuando se carga la configuración, esta se puede visualizar en el panel.
     */
	public void restaurarConfiguracion()
	{
		ConfiguracionNeuronalRBF.cargar(Proyecto.CODIGO_NUEVO);
   		cargarConfiguracion();
	}
	
	/**
     * Método que cambia una configuración en la red neuronal Radial Base Fase.
     * Verificando si es correcta la información ingresada.
     */
	public void establecerConfiguracion()
	{
		String error = validarConfiguracion();
	 	
	 	if (error == "")
	 	{
	 		double[] array =
			new double[ConfiguracionNeuronalRBF.TOTAL_ELEMENTOS_CONFIGURACION];
			
			array[7]  = cicloNoSupervisada.getValueInt() * 1000;
			array[5]  = numeroCentroides.getValueInt();
			array[14] = rataINoSupervisada.getValueDouble();
			array[15] = rataFNoSupervisada.getValueDouble();
			array[8]  = cicloSupervisada.getValueInt() * 1000;
			array[12] = rataSupervisada.getValueDouble();
			array[13] = variacionRataSupervisada.getValueDouble();
			array[10] = errorMinimo.getValueInt();
			array[11] = errorMaximo.getValueInt();
			array[9] = porcentajeCruzada.getValueDouble() / 100.0;
			
			error = ConfiguracionNeuronalRBF.validarValores(array);
			
			if (error == "")
			{
				ConfiguracionNeuronalRBF.modificarValores(array);
				ConfiguracionNeuronalRBF.establecerHabilitarCentroidesDinamicos
							(habilitarCentroidesDinamicos.isSelected());
			}
			else
				mostrarErrorCambiar(error,"Error en los rangos");
		}
	 	else
	 	{
	 		mostrarErrorCambiar(error,"Error en la sintaxis");
	 	}
	}
	
	/**
     * Método que verifica si los datos ingresados en la configuración tiene una 
     * sintaxis correcta
     *
     * @return error Retorna un string con el error cometido.
     */
	public String validarConfiguracion()
	{
		String error = "";
		
		// validando ciclos no supervisado
		if (!Servicio.esNumero(cicloNoSupervisada.getText()) ||
			Servicio.contarCaracter(cicloNoSupervisada.getText(), '.') > 0)
			error += "- En el número de ciclos no supervisado.\n";
		
		// validando centroides
		if (!Servicio.esNumero(numeroCentroides.getText()) ||
			Servicio.contarCaracter(numeroCentroides.getText(), '.') > 0)
			error += "- En el número de centroides.\n";
		
		// validando numero rata no supervisada inicial
		if (!Servicio.esNumero(rataINoSupervisada.getText()))
			error += "- En la rata inicial no supservisada.\n";
		
		// validando numero rata no supervisada final	 	
		if (!Servicio.esNumero(rataFNoSupervisada.getText()))
			error += "- En la rata final no supervisada.\n";
		
		// validando cilclos no supervisada
		if (!Servicio.esNumero(cicloSupervisada.getText()) ||
			Servicio.contarCaracter(cicloSupervisada.getText(), '.') > 0)
			error += "- En el número de ciclos supervisado.\n";
		
		// validando la rata supervisada
		if (!Servicio.esNumero(rataSupervisada.getText()))
			error += "- En la rata supervisado.\n";
		
		// validando la variacion de la rata supervisada
		if (!Servicio.esNumero(variacionRataSupervisada.getText()))
			error += "- En la variación de la rata supervisada.\n";
		
		// validando el error minimo
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
     * @param titulo String con título del error.
     */
	private void mostrarErrorCambiar(String error, String titulo)
	{
		error = "Se han cometido los siguientes errores:\n" + error;
		JOptionPane.showMessageDialog(this,error,titulo,
									  JOptionPane.ERROR_MESSAGE);
	}
}