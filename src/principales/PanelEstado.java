/**
 * @(#)PanelEstado.java 2.0 01/03/05
 *
 * Copyright (C) 2003-2005 grupo CIRCANA Pro, Todos los derechos reservados.
 * Escuela Ingenier�a en Computaci�n, Universidad de La Serena, Chile.
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

/**
 * Clase que extiende de la clase JPanel. Este panel corresponde al panel en
 * donde se muestra el estado de la aplicaci�n. Este panel contiene paneles para
 * mostrar informaci�n de la aplicaci�n, el caso de aplicaci�n del proyecto,
 * las fechas de inicio y t�rmino del proyecto, la barra de progreso y el estado
 * de la aplicaci�n.
 *
 * @version 2.0 01/03/05
 * @author Patricio Merino
 * @see Color
 * @see Font
 * @see JPanel
 * @see JLabel
 * @see JProgressBar
 * @see ImageIcon
 * @see Border
 * @see TitledBorder
 * @see LineBorder
 * @see FrameCircanaPro
 * @see InterfacePanel
 */
public class PanelEstado extends JPanel implements InterfacePanel
{
	/** El puntero al frame que contiene a este panel. */
	public FrameCircanaPro frameCircanaPro;
	
	/** La etiqueta que muestra una informaci�n de la aplicaci�n. */
	private JLabel etiquetaInformacion;
	
	/** La etiqueta que muestra el recurso. */
	private JLabel etiquetaRecurso;
	
	/** La etiqueta que muestra la regi�n. */
	private JLabel etiquetaRegion;
	
	/** La etiqueta que muestra la empresa. */
	private JLabel etiquetaEmpresa;
	
	/** La etiqueta que muestra la fecha inicial. */
	private JLabel etiquetaFechaInicial;
	
	/** La etiqueta que muestra la fecha final. */
	private JLabel etiquetaFechaFinal;
	
	/** El icono en verde para el bot�n del estado. */
	private ImageIcon VERDE = new ImageIcon("../img/boton_verde.gif");
	
	/** El icono en rojo para el bot�n del estado. */
	private ImageIcon ROJO = new ImageIcon("../img/boton_rojo.gif");
	
	/**
	 * La barra que muestra el progreso de alg�n proceso que se est� ejecutando
	 * en el m�dulo Ecosistema.
	 */
	private JProgressBar barraEcosistema;
	
	/**
	 * La barra que muestra el progreso de alg�n proceso que se est� ejecutando
	 * en el m�dulo Econom�a.
	 */
	private JProgressBar barraEconomia;
	
	/**
	 * La barra que muestra el progreso de alg�n proceso que se est� ejecutando
	 * en el m�dulo Operaci�n.
	 */
	private JProgressBar barraOperacion;
	
	/**
	 * La barra que muestra el progreso de alg�n proceso que se est� ejecutando
	 * en el m�dulo Integraci�n.
	 */
	private JProgressBar barraIntegracion;
	
	/**
	 * La etiqueta te texto que muestra el progreso de alg�n proceso que se est�
	 * ejecutando en la aplicaci�n.
	 */
	private JLabel etiquetaEstado;
	
	/**
	 * M�todo constructor que invoca a los m�todos que configuran el panel de
	 * estado, sus componentes y los eventos asociados.
	 *
	 * @param frameCircanaPro Objeto que hace referencia al frame que contiene
	 *                        a este panel.
	 */
	public PanelEstado(FrameCircanaPro frameCircanaPro)
	{
		// Inicializar el puntero.
		this.frameCircanaPro = frameCircanaPro;
		
		// Configurar.
		configurarPanel();
		configurarComponentes();
	}
	
	/**
	 * M�todo en donde se configurar diversas propiedades que tiene este panel.
	 * Las opciones que se cambian son los atributos que se derivan de la
	 * clase JPanel.
	 */
	public void configurarPanel()
	{
		// El tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear los bordes.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Setear el borde.
		setBorder(titulo);
		
		// Cambiar el tama�o del panel.
		setBounds(0,
			(int)(frameCircanaPro.getHeight() * (frameCircanaPro.ALTO_BOTONERA +
				  frameCircanaPro.ALTO_ESCRITORIO)),
			frameCircanaPro.getWidth(),
			(int) (frameCircanaPro.getHeight() * frameCircanaPro.ALTO_ESTADO));
		
		// Eliminar el layout.
		setLayout(null);
	}
	
	/**
	 * M�todo que configura este panel. Este panel contiene cuatro sub-paneles.
	 * Estos son los siguientes: la informaci�n, las fechas, el caso de
	 * aplicaci�n y el progreso.
	 */
	public void configurarComponentes()
	{
		// Configurar los paneles.
		configurarInformacion();
		configurarFechas();
		configurarCasoAplicacion();
		configurarProgreso();
		
		// Habilitar los componentes.
		habilitarComponentes();
	}
	
	/**
	 * M�todo en donde se configura el panel de la informaci�n que contiene
	 * este panel. El panel de la informaci�n maneja una etiqueta con la
	 * informaci�n de la aplicaci�n.
	 */
	private void configurarInformacion()
	{
		// El tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Establecer el borde.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		int ancho = getWidth() / 4;
		int alto = getHeight();
		panel.setBounds(0, 0, ancho, alto);
		
		// Crear los componentes.
		etiquetaInformacion = new JLabel();
		
		// Posicionar y dimensionar los componentes.
		etiquetaInformacion.setBounds(3, 3, ancho-6, 15);
		
		// Crear la fuente.
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		
		// Establecer la fuente.
		etiquetaInformacion.setFont(fuente);
		
		// Incorporar los componentes.
		panel.add(etiquetaInformacion);
		
		// Incorporar el panel.
		add(panel);
	}
	
	/**
	 * M�todo en donde se configura el panel de las fechas que contiene este
	 * panel. En el panel fecha se maneja dos etiquetas: la fecha inicial y la
	 * fecha final del proyecto.
	 */
	private void configurarFechas()
	{
		// El tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Establecer el borde.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		int ancho = getWidth() / 4;
		int alto = getHeight();
		panel.setBounds(ancho - 1, 0, ancho + 1, alto);
		
		// Crear los componentes.
		etiquetaFechaInicial = new JLabel();
		etiquetaFechaFinal = new JLabel();
		
		// Posicionar y dimensionar los componentes.
		etiquetaFechaInicial.setBounds(3, 3, (ancho - 4) / 2, 15);
		etiquetaFechaFinal.setBounds((ancho - 4) / 2 + 3, 3, (ancho - 4) / 2, 15);
		
		// Crear la fuente.
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		
		// Cambiar la fuente.
		etiquetaFechaInicial.setFont(fuente);
		etiquetaFechaFinal.setFont(fuente);
		
		// Incorporar los componentes.
		panel.add(etiquetaFechaInicial);
		panel.add(etiquetaFechaFinal);
		
		// Incorporar el panel.
		add(panel);
	}
	
	/**
	 * M�todo en donde se configura el panel del caso de aplicaci�n que contiene
	 * este panel. En el panel caso de aplicaci�n se maneja tres etiquetas: el
	 * recurso, la regi�n y la empresa del proyecto.
	 */
	private void configurarCasoAplicacion()
	{
		// El tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Establecer el borde.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		int ancho = getWidth() / 4;
		int alto = getHeight();
		panel.setBounds(2 * ancho - 3, 0, ancho + 2, alto);
		
		// Crear los componentes.
		etiquetaRecurso = new JLabel();
		etiquetaRegion = new JLabel();
		etiquetaEmpresa = new JLabel();
		
		// Posicionar y dimensionar los componentes.
		etiquetaRecurso.setBounds(3, 3, (ancho - 5) / 3 + 10, 15);
		etiquetaRegion.setBounds((ancho - 5) / 3 + 13, 3, (ancho - 5) / 3 - 20, 15);
		etiquetaEmpresa.setBounds(2*(ancho - 5) / 3 - 7, 3, (ancho - 5) / 3 + 10, 15);
		
		// Crear la fuente.
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		
		// Cambiar la fuente.
		etiquetaRecurso.setFont(fuente);
		etiquetaRegion.setFont(fuente);
		etiquetaEmpresa.setFont(fuente);
		
		// Incorporar los componentes.
		panel.add(etiquetaRecurso);
		panel.add(etiquetaRegion);
		panel.add(etiquetaEmpresa);
		
		// Incorporar el panel.
		add(panel);
	}
	
	/**
	 * M�todo en donde se configura el panel del progreso de la aplicaci�n que
	 * contiene este panel. En el panel del progreso de la aplicaci�n se maneja
	 * tres barras de progreso: el ecosistema, la econom�a y la operaci�n.
	 */
	private void configurarProgreso()
	{
		// El tipo de borde.
		Border borde = new LineBorder(Color.darkGray, 1);
		
		// Crear el t�tulo.
		TitledBorder titulo = new TitledBorder(borde, "", TitledBorder.LEFT,
											   TitledBorder.TOP);
		
		// Crear el panel.
		JPanel panel = new JPanel();
		
		// Establecer el t�tulo.
		panel.setBorder(titulo);
		
		// Eliminar el layout.
		panel.setLayout(null);
		
		// Posicionar y dimensionar.
		int ancho = getWidth() / 4;
		int alto = getHeight();
		panel.setBounds(3 * ancho - 3, 0, ancho + 3, alto);
		
		// Crear los componentes.
		barraEcosistema = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		barraEconomia = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		barraOperacion = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		barraIntegracion = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		etiquetaEstado = new JLabel();
		
		// Posicionar y dimensionar los componentes.
		barraEcosistema.setBounds(3, 3, (ancho - 18) / 4, 15);
		barraEconomia.setBounds((ancho - 18) / 4 + 2, 3, (ancho - 18) / 4, 15);
		barraOperacion.setBounds(2*(ancho - 18) / 4 + 1, 3, (ancho - 18) / 4, 15);
		barraIntegracion.setBounds(3*(ancho - 18) / 4 + 1, 3, (ancho - 18) / 4, 15);
		etiquetaEstado.setBounds((ancho - 18) + 1, 3, 15, 15);
		
		// Incorporar los componentes.
		panel.add(barraEcosistema);
		panel.add(barraEconomia);
		panel.add(barraOperacion);
		panel.add(barraIntegracion);
		panel.add(etiquetaEstado);
		
		// Incorporar el panel.
		add(panel);
	}
	
	/**
	 * M�todo que adjunta los escuchadores de eventos a los componentes que
	 * tiene este panel. En particular, este panel no maneja eventos.
	 */
	public void configurarEventos()
	{
	}
	
	/**
	 * M�todo que configura los componentes de los paneles. Espec�ficamente se 
	 * establece la propiedad de texto y la habilitaci�n de las etiquetas de los
	 * paneles, dependiendo de si se encuentra abierto o no un proyecto.
	 */
	public void habilitarComponentes()
	{
		// Cuando no hay un proyecto abierto.
		if (!frameCircanaPro.obtenerProyectoAbierto())
		{
			// Cambiar los textos.
			cambiarEtiquetaInformacion("");
			cambiarEtiquetaFechaInicial("");
			cambiarEtiquetaFechaFinal("");
			cambiarEtiquetaRecurso("");
			cambiarEtiquetaRegion("");
			cambiarEtiquetaEmpresa("");
			cambiarBarraEcosistema(0);
			cambiarBarraEconomia(0);
			cambiarBarraOperacion(0);
			cambiarBarraIntegracion(0);
			habilitarEtiquetas();
			
			// Cambiar la habilitaci�n.
			etiquetaInformacion.setEnabled(false);
			etiquetaFechaInicial.setEnabled(false);
			etiquetaFechaFinal.setEnabled(false);
			etiquetaRecurso.setEnabled(false);
			etiquetaRegion.setEnabled(false);
			etiquetaEmpresa.setEnabled(false);
			barraEcosistema.setEnabled(false);
			barraEconomia.setEnabled(false);
			barraOperacion.setEnabled(false);
			barraIntegracion.setEnabled(false);
			etiquetaEstado.setEnabled(false);
		}
		// Cuando hay un proyecto abierto.
		else
		{
			// Cambiar los textos.
			cambiarEtiquetaInformacion("");
			cambiarEtiquetaFechaInicial("Fecha inicial: "+
			Servicio.obtenerFecha(Proyecto.obtenerFechaInicialFormatoInt()));
			cambiarEtiquetaFechaFinal("Fecha final: "+
			Servicio.obtenerFecha(Proyecto.obtenerFechaFinalFormatoInt()));
			cambiarEtiquetaRecurso(Proyecto.obtenerNombreRecurso()+",");
			cambiarEtiquetaRegion(Proyecto.obtenerNombreRegion()+",");
			cambiarEtiquetaEmpresa(Proyecto.obtenerNombreEmpresa());
			cambiarBarraEcosistema(0);
			cambiarBarraEconomia(0);
			cambiarBarraOperacion(0);
			cambiarBarraIntegracion(0);
			habilitarEtiquetas();
			
			// Cambiar la habilitaci�n.
			etiquetaInformacion.setEnabled(true);
			etiquetaFechaInicial.setEnabled(true);
			etiquetaFechaFinal.setEnabled(true);
			etiquetaRecurso.setEnabled(true);
			etiquetaRegion.setEnabled(true);
			etiquetaEmpresa.setEnabled(true);
			barraEcosistema.setEnabled(true);
			barraEconomia.setEnabled(true);
			barraOperacion.setEnabled(true);
			barraIntegracion.setEnabled(true);
			etiquetaEstado.setEnabled(true);
		}
	}
	
	/**
	 * M�todo que cambia el string de texto que tiene el atributo
	 * etiquetaInformacion.
	 *
	 * @param texto El texto que se establece al atributo etiquetaInformacion.
	 */
	public void cambiarEtiquetaInformacion(String texto)
	{
		etiquetaInformacion.setText(texto);
	}
	
	/**
	 * M�todo que cambia el string de texto que tiene el atributo
	 * etiquetaFechaInicial.
	 *
	 * @param texto El texto que se establece al atributo etiquetaFechaInicial.
	 */
	public void cambiarEtiquetaFechaInicial(String texto)
	{
		etiquetaFechaInicial.setText(texto);
	}
	
	/**
	 * M�todo que cambia el string de texto que tiene el atributo
	 * etiquetaFechaFinal.
	 *
	 * @param texto El texto que se establece al atributo etiquetaFechaFinal.
	 */
	public void cambiarEtiquetaFechaFinal(String texto)
	{
		etiquetaFechaFinal.setText(texto);
	}
	
	/**
	 * M�todo que cambia el string de texto que tiene el atributo
	 * etiquetaRecurso.
	 *
	 * @param texto El texto que se establece al atributo etiquetaRecurso.
	 */
	public void cambiarEtiquetaRecurso(String texto)
	{
		etiquetaRecurso.setText(texto);
	}
	
	/**
	 * M�todo que cambia el string de texto que tiene el atributo
	 * etiquetaRegion.
	 *
	 * @param texto El texto que se establece al atributo etiquetaRegion.
	 */
	public void cambiarEtiquetaRegion(String texto)
	{
		etiquetaRegion.setText(texto);
	}
	
	/**
	 * M�todo que cambia el string de texto que tiene el atributo
	 * etiquetaEmpresa.
	 *
	 * @param texto El texto que se establece al atributo etiquetaEmpresa.
	 */
	public void cambiarEtiquetaEmpresa(String texto)
	{
		etiquetaEmpresa.setText(texto);
	}
	
	/**
	 * M�todo que cambia el valor de avance del atributo barraEcosistema.
	 *
	 * @param valor El valor de avance del atributo barraEcosistema.
	 */
	public void cambiarBarraEcosistema(int valor)
	{
		barraEcosistema.setValue(valor);
		barraEcosistema.setToolTipText("Ecosistema: "+valor+" %");
	}
	
	/**
	 * M�todo que cambia el valor de avance del atributo barraEconomia.
	 *
	 * @param valor El valor de avance del atributo barraEconomia.
	 */
	public void cambiarBarraEconomia(int valor)
	{
		barraEconomia.setValue(valor);
		barraEconomia.setToolTipText("Econom�a: "+valor+" %");
	}
	
	/**
	 * M�todo que cambia el valor de avance del atributo barraOperacion.
	 *
	 * @param valor El valor de avance del atributo barraOperacion.
	 */
	public void cambiarBarraOperacion(int valor)
	{
		barraOperacion.setValue(valor);
		barraOperacion.setToolTipText("Operaci�n: "+valor+" %");
	}
	
	/**
	 * M�todo que cambia el valor de avance del atributo barraIntegracion.
	 *
	 * @param valor El valor de avance del atributo barraIntegracion.
	 */
	public void cambiarBarraIntegracion(int valor)
	{
		barraIntegracion.setValue(valor);
		barraIntegracion.setToolTipText("Integraci�n: "+valor+" %");
	}
	
	/**
     * Metodo que cambia a un estado ocupado (espera) del sistema y visceversa.
     */
	public void habilitarEtiquetas()
	{
		// Cuando hay procesos corriendo.
		if (frameCircanaPro.obtenerProcesoCorriendo())
			etiquetaEstado.setIcon(ROJO);
		
		// Cuando no hay procesos corriendo.
		else etiquetaEstado.setIcon(VERDE);
		
		// Cambiar el tool tip.
		etiquetaEstado.setToolTipText("Estado del proyecto");
	}
}