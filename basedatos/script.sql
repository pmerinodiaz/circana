/**
 * Proyecto					: CIRCANA Pro.
 * Administrador BD	: MySQL 4.0.4 Beta Max Nt.
 * Host							: Localhost y otros.
 * Base de Datos		: Circanapro.
 * Creadores				: Héctor Díaz - Patricio Merino Díaz - Paul Leger.
 * Fecha						: 03 de Marzo del 2005.
 * Uso							: Creación de la Base de Datos.
 *                    Creación de las tablas de la Base de Datos.
 *                    Inserción de datos en la Base de Datos.
 *                    Creación del usuario que administra la Base de Datos.
 */

DROP DATABASE IF EXISTS circanapro;
CREATE DATABASE circanapro;
USE circanapro;

/**
 * Tabla: empresa.
 */
DROP TABLE IF EXISTS empresa;
CREATE TABLE empresa
(
	codigo_empresa INT UNSIGNED NOT NULL,
	nombre_empresa VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_empresa),
  UNIQUE KEY (nombre_empresa),
  KEY (codigo_empresa)
) TYPE=InnoDB;

/**
 * Tabla: longitud.
 */
DROP TABLE IF EXISTS longitud;
CREATE TABLE longitud
(
	codigo_longitud INT UNSIGNED NOT NULL,
	descripcion_longitud VARCHAR(10) NOT NULL,
  PRIMARY KEY (codigo_longitud),
  UNIQUE KEY (descripcion_longitud),
  KEY (codigo_longitud)
) TYPE=InnoDB;

/**
 * Tabla: latitud.
 */
DROP TABLE IF EXISTS latitud;
CREATE TABLE latitud
(
	codigo_latitud INT UNSIGNED NOT NULL,
	descripcion_latitud VARCHAR(10) NOT NULL,
  PRIMARY KEY (codigo_latitud),
  UNIQUE KEY (descripcion_latitud),
  KEY (codigo_latitud)
) TYPE=InnoDB;

/**
 * Tabla: region.
 */
DROP TABLE IF EXISTS region;
CREATE TABLE region
(
	codigo_region INT UNSIGNED NOT NULL,
	codigo_longitud_inicial INT UNSIGNED NOT NULL,
	codigo_longitud_final INT UNSIGNED NOT NULL,
	codigo_latitud_inicial INT UNSIGNED NOT NULL,
	codigo_latitud_final INT UNSIGNED NOT NULL,
	descripcion_region VARCHAR(50) NOT NULL,
	longitud_inicial_region DOUBLE UNSIGNED NOT NULL,
	longitud_final_region DOUBLE UNSIGNED NOT NULL,
	latitud_inicial_region DOUBLE UNSIGNED NOT NULL,
	latitud_final_region DOUBLE UNSIGNED NOT NULL,
	altitud_inicial_region DOUBLE NOT NULL,
	altitud_final_region DOUBLE NOT NULL,
  PRIMARY KEY (codigo_region),
  UNIQUE KEY (descripcion_region),
  FOREIGN KEY (codigo_longitud_inicial) REFERENCES longitud(codigo_longitud),
  FOREIGN KEY (codigo_longitud_final) REFERENCES longitud(codigo_longitud),
  FOREIGN KEY (codigo_latitud_inicial) REFERENCES latitud(codigo_latitud),
  FOREIGN KEY (codigo_latitud_final) REFERENCES latitud(codigo_latitud),
  KEY (codigo_region),
  KEY (codigo_longitud_inicial),
  KEY (codigo_longitud_final),
  KEY (codigo_latitud_inicial),
  KEY (codigo_latitud_final)
) TYPE=InnoDB;

/**
 * Tabla: tipo_recurso.
 */
DROP TABLE IF EXISTS tipo_recurso;
CREATE TABLE tipo_recurso
(
	codigo_tipo_recurso INT UNSIGNED NOT NULL,
	descripcion_tipo_recurso VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tipo_recurso),
  UNIQUE KEY (descripcion_tipo_recurso),
  KEY (codigo_tipo_recurso)
) TYPE=InnoDB;

/**
 * Tabla: tipo_animal.
 */
DROP TABLE IF EXISTS tipo_animal;
CREATE TABLE tipo_animal
(
	codigo_tipo_animal INT UNSIGNED NOT NULL,
	descripcion_tipo_animal VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tipo_animal),
  UNIQUE KEY (descripcion_tipo_animal),
  KEY (codigo_tipo_animal)
) TYPE=InnoDB;

/**
 * Tabla: zona_sistema.
 */
DROP TABLE IF EXISTS zona_sistema;
CREATE TABLE zona_sistema
(
	codigo_zona_sistema INT UNSIGNED NOT NULL,
	descripcion_zona_sistema VARCHAR(50) NOT NULL,
	distancia_fondo_minima_zona_sistema DOUBLE UNSIGNED NOT NULL,
	distancia_fondo_maxima_zona_sistema DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_zona_sistema),
  UNIQUE KEY (descripcion_zona_sistema),
  KEY (codigo_zona_sistema)
) TYPE=InnoDB;

/**
 * Tabla: zona_pelagica.
 */
DROP TABLE IF EXISTS zona_pelagica;
CREATE TABLE zona_pelagica
(
	codigo_zona_pelagica INT UNSIGNED NOT NULL,
	descripcion_zona_pelagica VARCHAR(50) NOT NULL,
	altitud_inicial_zona_pelagica DOUBLE NOT NULL,
	altitud_final_zona_pelagica DOUBLE NOT NULL,
  PRIMARY KEY (codigo_zona_pelagica),
  UNIQUE KEY (descripcion_zona_pelagica),
  KEY (codigo_zona_pelagica)
) TYPE=InnoDB;

/**
 * Tabla: zona_bentonica.
 */
DROP TABLE IF EXISTS zona_bentonica;
CREATE TABLE zona_bentonica
(
	codigo_zona_bentonica INT UNSIGNED NOT NULL,
	descripcion_zona_bentonica VARCHAR(50) NOT NULL,
	altitud_inicial_zona_bentonica DOUBLE NOT NULL,
	altitud_final_zona_bentonica DOUBLE NOT NULL,
  PRIMARY KEY (codigo_zona_bentonica),
  UNIQUE KEY (descripcion_zona_bentonica),
  KEY (codigo_zona_bentonica)
) TYPE=InnoDB;

/**
 * Tabla: zona_region.
 */
DROP TABLE IF EXISTS zona_region;
CREATE TABLE zona_region
(
	codigo_zona_region INT UNSIGNED NOT NULL,
	descripcion_zona_region VARCHAR(50) NOT NULL,
	distancia_continente_minima_zona_region DOUBLE UNSIGNED NOT NULL,
	distancia_continente_maxima_zona_region DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_zona_region),
  UNIQUE KEY (descripcion_zona_region),
  KEY (codigo_zona_region)
) TYPE=InnoDB;

/**
 * Tabla: zona_luz.
 */
DROP TABLE IF EXISTS zona_luz;
CREATE TABLE zona_luz
(
	codigo_zona_luz INT UNSIGNED NOT NULL,
	descripcion_zona_luz VARCHAR(50) NOT NULL,
	altitud_inicial_zona_luz DOUBLE NOT NULL,
	altitud_final_zona_luz DOUBLE NOT NULL,
  PRIMARY KEY (codigo_zona_luz),
  UNIQUE KEY (descripcion_zona_luz),
  KEY (codigo_zona_luz)
) TYPE=InnoDB;

/**
 * Tabla: conducta.
 */
DROP TABLE IF EXISTS conducta;
CREATE TABLE conducta
(
	codigo_conducta INT UNSIGNED NOT NULL,
	descripcion_conducta VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_conducta),
  UNIQUE KEY (descripcion_conducta),
  KEY (codigo_conducta)
) TYPE=InnoDB;

/**
 * Tabla: color.
 */
DROP TABLE IF EXISTS color;
CREATE TABLE color
(
	codigo_color INT UNSIGNED NOT NULL,
	descripcion_color VARCHAR(50) NOT NULL,
	rojo_color INT UNSIGNED NOT NULL,
	verde_color INT UNSIGNED NOT NULL,
	azul_color INT UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_color),
  UNIQUE KEY (descripcion_color),
  UNIQUE KEY (rojo_color, verde_color, azul_color),
  KEY (codigo_color)
) TYPE=InnoDB;

/**
 * Tabla: tipo_reproduccion.
 */
DROP TABLE IF EXISTS tipo_reproduccion;
CREATE TABLE tipo_reproduccion
(
	codigo_tipo_reproduccion INT UNSIGNED NOT NULL,
	descripcion_tipo_reproduccion VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tipo_reproduccion),
  UNIQUE KEY (descripcion_tipo_reproduccion),
  KEY (codigo_tipo_reproduccion)
) TYPE=InnoDB;

/**
 * Tabla: recurso.
 */
DROP TABLE IF EXISTS recurso;
CREATE TABLE recurso
(
	codigo_recurso INT UNSIGNED NOT NULL,
	codigo_tipo_animal INT UNSIGNED NOT NULL,
	codigo_zona_sistema INT UNSIGNED NOT NULL,
	codigo_zona_pelagica INT UNSIGNED NOT NULL,
	codigo_zona_bentonica INT UNSIGNED NOT NULL,
	codigo_zona_region INT UNSIGNED NOT NULL,
	codigo_zona_luz INT UNSIGNED NOT NULL,
	codigo_conducta INT UNSIGNED NOT NULL,
	codigo_color INT UNSIGNED NOT NULL,
	codigo_latitud_inicial INT UNSIGNED NOT NULL,
	codigo_latitud_final INT UNSIGNED NOT NULL,
	codigo_tipo_recurso INT UNSIGNED NOT NULL,
	codigo_tipo_reproduccion INT UNSIGNED NOT NULL,
	nombre_comun_recurso VARCHAR(50) NOT NULL,
	edad_maxima_macho_recurso DOUBLE UNSIGNED NOT NULL,
	edad_maxima_hembra_recurso DOUBLE UNSIGNED NOT NULL,
	parametro_loo_macho_recurso DOUBLE NOT NULL,
	parametro_to_macho_recurso DOUBLE NOT NULL,
	parametro_k_macho_recurso DOUBLE NOT NULL,
	parametro_a_macho_recurso DOUBLE NOT NULL,
	parametro_b_macho_recurso DOUBLE NOT NULL,
	parametro_loo_hembra_recurso DOUBLE NOT NULL,
	parametro_to_hembra_recurso DOUBLE NOT NULL,
	parametro_k_hembra_recurso DOUBLE NOT NULL,
	parametro_a_hembra_recurso DOUBLE NOT NULL,
	parametro_b_hembra_recurso DOUBLE NOT NULL,
	talla_minima_macho_recurso DOUBLE UNSIGNED NOT NULL,
	talla_maxima_macho_recurso DOUBLE UNSIGNED NOT NULL,
	talla_minima_hembra_recurso DOUBLE UNSIGNED NOT NULL,
	talla_maxima_hembra_recurso DOUBLE UNSIGNED NOT NULL,
	peso_minimo_macho_recurso DOUBLE UNSIGNED NOT NULL,
	peso_maximo_macho_recurso DOUBLE UNSIGNED NOT NULL,
	peso_minimo_hembra_recurso DOUBLE UNSIGNED NOT NULL,
	peso_maximo_hembra_recurso DOUBLE UNSIGNED NOT NULL,
	parametro_s1_recurso DOUBLE NOT NULL,
	parametro_s2_recurso DOUBLE NOT NULL,
	madurez_sexual_recurso DOUBLE UNSIGNED NOT NULL,
	huevos_minimo_recurso BIGINT UNSIGNED NOT NULL,
	huevos_maximo_recurso BIGINT UNSIGNED NOT NULL,
	periodo_portacion_recurso INT UNSIGNED NOT NULL,
	tasa_natalidad_recurso DOUBLE UNSIGNED NOT NULL,
	tasa_mortalidad_recurso BIGINT UNSIGNED NOT NULL,
	latitud_inicial_recurso DOUBLE UNSIGNED NOT NULL,
	latitud_final_recurso DOUBLE UNSIGNED NOT NULL,
	altitud_inicial_recurso DOUBLE NOT NULL,
	altitud_final_recurso DOUBLE NOT NULL,
	temperatura_inicial_recurso DOUBLE NOT NULL,
	temperatura_final_recurso DOUBLE NOT NULL,
	salinidad_inicial_recurso DOUBLE NOT NULL,
	salinidad_final_recurso DOUBLE NOT NULL,
	oxigeno_disuelto_inicial_recurso DOUBLE NOT NULL,
	oxigeno_disuelto_final_recurso DOUBLE NOT NULL,
	percepcion_recurso DOUBLE UNSIGNED NOT NULL,
	velocidad_recurso DOUBLE UNSIGNED NOT NULL,
	energia_inicial_recurso DOUBLE UNSIGNED NOT NULL,
	energia_minima_recurso DOUBLE UNSIGNED NOT NULL,
	energia_maxima_recurso DOUBLE UNSIGNED NOT NULL,
	energia_tiempo_recurso DOUBLE UNSIGNED NOT NULL,
	energia_consumo_recurso DOUBLE UNSIGNED NOT NULL,
	energia_desplazamiento_recurso DOUBLE UNSIGNED NOT NULL,
	descomposicion_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_arrancar_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_predar_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_reproducir_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_desovar_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_convivir_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_migrar_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_divagar_recurso DOUBLE UNSIGNED NOT NULL,
	tendencia_nada_recurso DOUBLE UNSIGNED NOT NULL,
	PRIMARY KEY (codigo_recurso),
	UNIQUE KEY (nombre_comun_recurso),
	FOREIGN KEY (codigo_tipo_animal) REFERENCES tipo_animal(codigo_tipo_animal),
	FOREIGN KEY (codigo_zona_sistema) REFERENCES zona_sistema(codigo_zona_sistema),
	FOREIGN KEY (codigo_zona_pelagica) REFERENCES zona_pelagica(codigo_zona_pelagica),
	FOREIGN KEY (codigo_zona_bentonica) REFERENCES zona_bentonica(codigo_zona_bentonica),
	FOREIGN KEY (codigo_zona_region) REFERENCES zona_region(codigo_zona_region),
	FOREIGN KEY (codigo_zona_luz) REFERENCES zona_luz(codigo_zona_luz),
	FOREIGN KEY (codigo_conducta) REFERENCES conducta(codigo_conducta),
	FOREIGN KEY (codigo_color) REFERENCES color(codigo_color),
	FOREIGN KEY (codigo_latitud_inicial) REFERENCES latitud(codigo_latitud),
	FOREIGN KEY (codigo_latitud_final) REFERENCES latitud(codigo_latitud),
	FOREIGN KEY (codigo_tipo_recurso) REFERENCES tipo_recurso(codigo_tipo_recurso),
	FOREIGN KEY (codigo_tipo_reproduccion) REFERENCES tipo_reproduccion(codigo_tipo_reproduccion),
	KEY (codigo_recurso),
	KEY (codigo_tipo_animal),
	KEY (codigo_zona_sistema),
	KEY (codigo_zona_pelagica),
	KEY (codigo_zona_bentonica),
	KEY (codigo_zona_region),
	KEY (codigo_zona_luz),
	KEY (codigo_conducta),
	KEY (codigo_color),
	KEY (codigo_latitud_inicial),
	KEY (codigo_latitud_final),
	KEY (codigo_tipo_recurso),
	KEY (codigo_tipo_reproduccion)
) TYPE=InnoDB;

/**
 * Tabla: tipo_dieta.
 */
DROP TABLE IF EXISTS tipo_dieta;
CREATE TABLE tipo_dieta
(
	codigo_tipo_dieta INT UNSIGNED NOT NULL,
	descripcion_tipo_dieta VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tipo_dieta),
  UNIQUE KEY (descripcion_tipo_dieta),
  KEY (codigo_tipo_dieta)
) TYPE=InnoDB;

/**
 * Tabla: recurso_tipo_dieta.
 */
DROP TABLE IF EXISTS recurso_tipo_dieta;
CREATE TABLE recurso_tipo_dieta
(
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_tipo_dieta INT UNSIGNED NOT NULL,
  alimentacion_tipo_dieta VARCHAR(2) NOT NULL,
  PRIMARY KEY (codigo_recurso, codigo_tipo_dieta),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_tipo_dieta) REFERENCES tipo_dieta(codigo_tipo_dieta),
  KEY (codigo_recurso),
  KEY (codigo_tipo_dieta)
) TYPE=InnoDB;

/**
 * Tabla: mes.
 */
DROP TABLE IF EXISTS mes;
CREATE TABLE mes
(
	codigo_mes INT UNSIGNED NOT NULL,
  descripcion_mes VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_mes),
  UNIQUE KEY (descripcion_mes),
  KEY (codigo_mes)
) TYPE=InnoDB;

/**
 * Tabla: recurso_mes.
 */
DROP TABLE IF EXISTS recurso_mes;
CREATE TABLE recurso_mes
(
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_mes INT UNSIGNED NOT NULL,
  portacion_recurso_mes DOUBLE UNSIGNED NOT NULL,
  desove_recurso_mes DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_recurso, codigo_mes),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_mes) REFERENCES mes(codigo_mes),
  KEY (codigo_recurso),
  KEY (codigo_mes)
) TYPE=InnoDB;

/**
 * Tabla: fauna_acompaniante.
 */
DROP TABLE IF EXISTS fauna_acompaniante;
CREATE TABLE fauna_acompaniante
(
  codigo_recurso_objetivo INT UNSIGNED NOT NULL,
  codigo_recurso_acompaniante INT UNSIGNED NOT NULL,
  alimentacion_fauna_acompaniante DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_recurso_objetivo, codigo_recurso_acompaniante),
  FOREIGN KEY (codigo_recurso_objetivo) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_recurso_acompaniante) REFERENCES recurso(codigo_recurso),
  KEY (codigo_recurso_objetivo),
  KEY (codigo_recurso_acompaniante)
) TYPE=InnoDB;

/**
 * Tabla: proyecto.
 */
DROP TABLE IF EXISTS proyecto;
CREATE TABLE proyecto
(
	codigo_proyecto INT UNSIGNED NOT NULL AUTO_INCREMENT,
	codigo_empresa INT UNSIGNED NOT NULL,
	codigo_region INT UNSIGNED NOT NULL,
	codigo_recurso INT UNSIGNED NOT NULL,
	nombre_proyecto VARCHAR(50) NOT NULL,
  fecha_inicial_proyecto DATE NOT NULL,
  fecha_final_proyecto DATE NOT NULL, 	
  autor_proyecto VARCHAR(50),
  version_proyecto VARCHAR(50),
  observacion_proyecto TEXT,
  reporte_ecosistema_proyecto TEXT,
  reporte_economia_proyecto TEXT,
  reporte_operacion_proyecto TEXT,
  reporte_integracion_proyecto TEXT,
  PRIMARY KEY (codigo_proyecto),
  UNIQUE KEY (nombre_proyecto),
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo_empresa),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  KEY (codigo_proyecto),
  KEY (codigo_empresa),
  KEY (codigo_region),
  KEY (codigo_recurso)
) TYPE=InnoDB;

/**
 * Tabla: funcion_neuronal.
 */
DROP TABLE IF EXISTS funcion_neuronal;
CREATE TABLE funcion_neuronal
(
	codigo_funcion_neuronal INT UNSIGNED NOT NULL,
	descripcion_funcion_neuronal VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_funcion_neuronal),
  UNIQUE KEY (descripcion_funcion_neuronal),
  KEY (codigo_funcion_neuronal)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_bp.
 */
DROP TABLE IF EXISTS configuracion_bp;
CREATE TABLE configuracion_bp
(
	codigo_proyecto INT UNSIGNED NOT NULL,
  codigo_funcion_neuronalco1 INT UNSIGNED NOT NULL,
  codigo_funcion_neuronalco2 INT UNSIGNED NOT NULL,
  codigo_funcion_neuronals INT UNSIGNED NOT NULL,
  numero_patrones_configuracion_bp INT UNSIGNED NOT NULL,
  numero_entradas_configuracion_bp INT UNSIGNED NOT NULL,
  numero_neuronasco1_configuracion_bp INT UNSIGNED NOT NULL,
  numero_neuronasco2_configuracion_bp INT UNSIGNED NOT NULL,
  numero_salidas_configuracion_bp INT UNSIGNED NOT NULL,
  ciclo_configuracion_bp DOUBLE UNSIGNED NOT NULL,
  porcentaje_cruzada_configuracion_bp DOUBLE UNSIGNED NOT NULL,
  error_minimo_configuracion_bp INT UNSIGNED NOT NULL,
  error_maximo_configuracion_bp INT UNSIGNED NOT NULL,
  rata_configuracion_bp DOUBLE UNSIGNED NOT NULL,
  variacion_rata_configuracion_bp DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_proyecto),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_funcion_neuronalco1) REFERENCES funcion_neuronal(codigo_funcion_neuronal),
  FOREIGN KEY (codigo_funcion_neuronalco2) REFERENCES funcion_neuronal(codigo_funcion_neuronal),
  FOREIGN KEY (codigo_funcion_neuronals) REFERENCES funcion_neuronal(codigo_funcion_neuronal),
	KEY (codigo_proyecto),
  KEY (codigo_funcion_neuronalco1),
  KEY (codigo_funcion_neuronalco2),
  KEY (codigo_funcion_neuronals)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_rbf.
 */
DROP TABLE IF EXISTS configuracion_rbf;
CREATE TABLE configuracion_rbf
(
	codigo_proyecto INT UNSIGNED NOT NULL,
  numero_patrones_configuracion_rbf INT UNSIGNED NOT NULL,
  numero_entradas_configuracion_rbf INT UNSIGNED NOT NULL,
  numero_centroides_configuracion_rbf INT UNSIGNED NOT NULL,
  numero_salidas_configuracion_rbf INT UNSIGNED NOT NULL,
  habilitar_centroides_dinamicos_configuracion_rbf TINYINT UNSIGNED NOT NULL,
	ciclo_supervisado_configuracion_rbf DOUBLE UNSIGNED NOT NULL,
  ciclo_no_supervisado_configuracion_rbf DOUBLE UNSIGNED NOT NULL,
  porcentaje_cruzada_configuracion_rbf DOUBLE UNSIGNED NOT NULL,
  error_minimo_configuracion_rbf INT UNSIGNED NOT NULL,
  error_maximo_configuracion_rbf INT UNSIGNED NOT NULL,
  rata_supervisada_configuracion_rbf DOUBLE UNSIGNED NOT NULL,
  variacion_rata_supervisada_configuracion_rbf DOUBLE UNSIGNED NOT NULL,
  ratai_no_supervisada_configuracion_rbf DOUBLE UNSIGNED NOT NULL,
  rataf_no_supervisada_configuracion_rbf DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_proyecto),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  KEY (codigo_proyecto)
) TYPE=InnoDB;

/**
 * Tabla: anio.
 */
DROP TABLE IF EXISTS anio;
CREATE TABLE anio
(
	codigo_anio INT UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_anio),
  KEY (codigo_anio)
) TYPE=InnoDB;

/**
 * Tabla: dia.
 */
DROP TABLE IF EXISTS dia;
CREATE TABLE dia
(
	codigo_dia INT UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_dia),
  KEY (codigo_dia)
) TYPE=InnoDB;

/**
 * Tabla: tipo_red_neuronal.
 */
DROP TABLE IF EXISTS tipo_red_neuronal;
CREATE TABLE tipo_red_neuronal
(
	codigo_tipo_red_neuronal INT UNSIGNED NOT NULL,
	descripcion_tipo_red_neuronal VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tipo_red_neuronal),
  UNIQUE KEY (descripcion_tipo_red_neuronal),
  KEY (codigo_tipo_red_neuronal)
) TYPE=InnoDB;

/**
 * Tabla: dato_economico_resultado.
 */
DROP TABLE IF EXISTS dato_economico_resultado;
CREATE TABLE dato_economico_resultado
(
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_region INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_tipo_red_neuronal INT UNSIGNED NOT NULL,
  precio_dato_economico_resultado DOUBLE NOT NULL,
  demanda_dato_economico_resultado DOUBLE NOT NULL,
	PRIMARY KEY (codigo_recurso, codigo_region, codigo_anio, codigo_dia, codigo_proyecto, codigo_tipo_red_neuronal),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
	FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
	FOREIGN KEY (codigo_tipo_red_neuronal) REFERENCES tipo_red_neuronal(codigo_tipo_red_neuronal),
  KEY (codigo_recurso),
  KEY (codigo_region),
	KEY (codigo_anio),
  KEY (codigo_dia),
  KEY (codigo_proyecto),
  KEY (codigo_tipo_red_neuronal)
) TYPE=InnoDB;

/**
 * Tabla: dato_economico_real.
 */
DROP TABLE IF EXISTS dato_economico_real;
CREATE TABLE dato_economico_real
(
  codigo_recurso INT UNSIGNED NOT NULL,
	codigo_region INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	precio_dato_economico_real DOUBLE NOT NULL,
	demanda_dato_economico_real DOUBLE NOT NULL,
	PRIMARY KEY (codigo_recurso, codigo_region, codigo_anio, codigo_dia),
	FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
	FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
	FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
	FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
	KEY (codigo_recurso),
	KEY (codigo_region),
	KEY (codigo_anio),
	KEY (codigo_dia)
) TYPE=InnoDB;

/**
 * Tabla: dato_ecosistemico_resultado.
 */
DROP TABLE IF EXISTS dato_ecosistemico_resultado;
CREATE TABLE dato_ecosistemico_resultado
(
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_region INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_proyecto INT UNSIGNED NOT NULL,
	biomasa_dato_ecosistemico_resultado DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_recurso, codigo_region, codigo_anio, codigo_dia, codigo_proyecto),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  KEY (codigo_recurso),
  KEY (codigo_region),
	KEY (codigo_anio),
	KEY (codigo_dia),
	KEY (codigo_proyecto)
) TYPE=InnoDB;

/**
 * Tabla: dato_ecosistemico_real.
 */
DROP TABLE IF EXISTS dato_ecosistemico_real;
CREATE TABLE dato_ecosistemico_real
(
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_region INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	abundancia_dato_ecosistemico_real BIGINT UNSIGNED NOT NULL,
	porcentaje_machos_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	porcentaje_hembras_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	porcentaje_hembras_sh_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	porcentaje_hembras_ch_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	talla_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	talla_machos_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	talla_hembras_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	talla_hembras_sh_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	talla_hembras_ch_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	biomasa_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	peso_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	peso_machos_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	peso_hembras_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	peso_hembras_sh_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
	peso_hembras_ch_dato_ecosistemico_real DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_recurso, codigo_region, codigo_anio, codigo_dia),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
  KEY (codigo_recurso),
  KEY (codigo_region),
	KEY (codigo_anio),
	KEY (codigo_dia)
) TYPE=InnoDB;

/**
 * Tabla: dato_operativo_resultado.
 */
DROP TABLE IF EXISTS dato_operativo_resultado;
CREATE TABLE dato_operativo_resultado
(	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_proyecto INT UNSIGNED NOT NULL,
  costo_total_dato_operativo_resultado DOUBLE NOT NULL,
  cantidad_total_dato_operativo_resultado DOUBLE NOT NULL,
	PRIMARY KEY (codigo_anio, codigo_dia, codigo_proyecto),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
	FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
	KEY (codigo_anio),
  KEY (codigo_dia),
  KEY (codigo_proyecto)
) TYPE=InnoDB;

/**
 * Tabla: coordenada.
 */
DROP TABLE IF EXISTS coordenada;
CREATE TABLE coordenada
(
	codigo_coordenada INT UNSIGNED NOT NULL,
	codigo_region INT UNSIGNED NOT NULL,
	codigo_longitud INT UNSIGNED NOT NULL,
	codigo_latitud INT UNSIGNED NOT NULL,
	longitud_coordenada DOUBLE UNSIGNED NOT NULL,
	latitud_coordenada DOUBLE UNSIGNED NOT NULL,
	altitud_coordenada DOUBLE NOT NULL,
  PRIMARY KEY (codigo_coordenada),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_longitud) REFERENCES longitud(codigo_longitud),
  FOREIGN KEY (codigo_latitud) REFERENCES latitud(codigo_latitud),
	KEY (codigo_coordenada),
	KEY (codigo_region),
  KEY (codigo_longitud),
  KEY (codigo_latitud)
) TYPE=InnoDB;

/**
 * Tabla: caladero_real.
 */
DROP TABLE IF EXISTS caladero_real;
CREATE TABLE caladero_real
(
	codigo_caladero_real INT UNSIGNED NOT NULL,
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_region INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_latitud_inicial INT UNSIGNED NOT NULL,
	codigo_latitud_final INT UNSIGNED NOT NULL,
	latitud_inicial_caladero_real DOUBLE UNSIGNED NOT NULL,
	latitud_final_caladero_real DOUBLE UNSIGNED NOT NULL,
	area_caladero_real DOUBLE UNSIGNED NOT NULL,
	abundancia_caladero_real BIGINT UNSIGNED NOT NULL,
	biomasa_caladero_real DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_caladero_real, codigo_recurso, codigo_region, codigo_anio, codigo_dia),
	FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
  FOREIGN KEY (codigo_latitud_inicial) REFERENCES latitud(codigo_latitud),
  FOREIGN KEY (codigo_latitud_final) REFERENCES latitud(codigo_latitud),
  KEY (codigo_caladero_real),
  KEY (codigo_recurso),
  KEY (codigo_region),
  KEY (codigo_anio),
  KEY (codigo_dia),
  KEY (codigo_latitud_inicial),
  KEY (codigo_latitud_final)
) TYPE=InnoDB;

/**
 * Tabla: tipo_caladero_resultado.
 */
DROP TABLE IF EXISTS tipo_caladero_resultado;
CREATE TABLE tipo_caladero_resultado
(
	codigo_tipo_caladero_resultado INT UNSIGNED NOT NULL,
	descripcion_tipo_caladero_resultado VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tipo_caladero_resultado),
  UNIQUE KEY (descripcion_tipo_caladero_resultado),
  KEY (codigo_tipo_caladero_resultado)
) TYPE=InnoDB;

/**
 * Tabla: caladero_resultado.
 */
DROP TABLE IF EXISTS caladero_resultado;
CREATE TABLE caladero_resultado
(
	codigo_caladero_resultado INT UNSIGNED NOT NULL,
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_region INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_longitud_inicial INT UNSIGNED NOT NULL,
	codigo_longitud_final INT UNSIGNED NOT NULL,
	codigo_latitud_inicial INT UNSIGNED NOT NULL,
	codigo_latitud_final INT UNSIGNED NOT NULL,
	codigo_tipo_caladero_resultado INT UNSIGNED NOT NULL,
	longitud_inicial_caladero_resultado DOUBLE UNSIGNED NOT NULL,
	longitud_final_caladero_resultado DOUBLE UNSIGNED NOT NULL,
	latitud_inicial_caladero_resultado DOUBLE UNSIGNED NOT NULL,
	latitud_final_caladero_resultado DOUBLE UNSIGNED NOT NULL,
	biomasa_caladero_resultado DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_caladero_resultado, codigo_recurso, codigo_region, codigo_anio, codigo_dia, codigo_proyecto),
	FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_longitud_inicial) REFERENCES longitud(codigo_longitud),
  FOREIGN KEY (codigo_longitud_final) REFERENCES longitud(codigo_longitud),
  FOREIGN KEY (codigo_latitud_inicial) REFERENCES latitud(codigo_latitud),
  FOREIGN KEY (codigo_latitud_final) REFERENCES latitud(codigo_latitud),
  FOREIGN KEY (codigo_tipo_caladero_resultado) REFERENCES tipo_caladero_resultado(codigo_tipo_caladero_resultado),
  KEY (codigo_caladero_resultado),
  KEY (codigo_recurso),
  KEY (codigo_region),
  KEY (codigo_anio),
  KEY (codigo_dia),
  KEY (codigo_proyecto),
  KEY (codigo_longitud_inicial),
  KEY (codigo_longitud_final),
  KEY (codigo_latitud_inicial),
  KEY (codigo_latitud_final),
  KEY (codigo_tipo_caladero_resultado)
) TYPE=InnoDB;

/**
 * Tabla: vista.
 */
DROP TABLE IF EXISTS vista;
CREATE TABLE vista
(
	codigo_vista INT UNSIGNED NOT NULL,
	descripcion_vista VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_vista),
  UNIQUE KEY (descripcion_vista),
  KEY (codigo_vista)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_espacial.
 */
DROP TABLE IF EXISTS configuracion_espacial;
CREATE TABLE configuracion_espacial
(
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_vista INT UNSIGNED NOT NULL,
	codigo_longitud_inicial INT UNSIGNED NOT NULL,
	codigo_longitud_final INT UNSIGNED NOT NULL,
	codigo_latitud_inicial INT UNSIGNED NOT NULL,
	codigo_latitud_final INT UNSIGNED NOT NULL,
  longitud_inicial_configuracion_espacial DOUBLE UNSIGNED NOT NULL,
  longitud_final_configuracion_espacial DOUBLE UNSIGNED NOT NULL,
  latitud_inicial_configuracion_espacial DOUBLE UNSIGNED NOT NULL,
  latitud_final_configuracion_espacial DOUBLE UNSIGNED NOT NULL,
  altitud_inicial_configuracion_espacial DOUBLE NOT NULL,
  altitud_final_configuracion_espacial DOUBLE NOT NULL,
  PRIMARY KEY (codigo_proyecto),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_vista) REFERENCES vista(codigo_vista),
  FOREIGN KEY (codigo_longitud_inicial) REFERENCES longitud(codigo_longitud),
  FOREIGN KEY (codigo_longitud_final) REFERENCES longitud(codigo_longitud),
  FOREIGN KEY (codigo_latitud_inicial) REFERENCES latitud(codigo_latitud),
  FOREIGN KEY (codigo_latitud_final) REFERENCES latitud(codigo_latitud),
  KEY (codigo_proyecto),
  KEY (codigo_vista),
  KEY (codigo_longitud_inicial),
  KEY (codigo_longitud_final),
  KEY (codigo_latitud_inicial),
  KEY (codigo_latitud_final)
) TYPE=InnoDB;

/**
 * Tabla: limite.
 */
DROP TABLE IF EXISTS limite;
CREATE TABLE limite
(
	codigo_limite INT UNSIGNED NOT NULL,
	descripcion_limite VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_limite),
  UNIQUE KEY (descripcion_limite),
  KEY (codigo_limite)
) TYPE=InnoDB;

/**
 * Tabla: vecindad.
 */
DROP TABLE IF EXISTS vecindad;
CREATE TABLE vecindad
(
	codigo_vecindad INT UNSIGNED NOT NULL,
	descripcion_vecindad VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_vecindad),
  UNIQUE KEY (descripcion_vecindad),
  KEY (codigo_vecindad)
) TYPE=InnoDB;

/**
 * Tabla: tiempo.
 */
DROP TABLE IF EXISTS tiempo;
CREATE TABLE tiempo
(
	codigo_tiempo INT UNSIGNED NOT NULL,
	descripcion_tiempo VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tiempo),
  UNIQUE KEY (descripcion_tiempo),
  KEY (codigo_tiempo)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_ac.
 */
DROP TABLE IF EXISTS configuracion_ac;
CREATE TABLE configuracion_ac
(
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_limite INT UNSIGNED NOT NULL,
	codigo_vecindad INT UNSIGNED NOT NULL,
	codigo_tiempo INT UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_proyecto),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_limite) REFERENCES limite(codigo_limite),
  FOREIGN KEY (codigo_vecindad) REFERENCES vecindad(codigo_vecindad),
  FOREIGN KEY (codigo_tiempo) REFERENCES tiempo(codigo_tiempo),
  KEY (codigo_proyecto),
  KEY (codigo_limite),
  KEY (codigo_vecindad),
  KEY (codigo_tiempo)
) TYPE=InnoDB;

/**
 * Tabla: tipo_calidad.
 */
DROP TABLE IF EXISTS tipo_calidad;
CREATE TABLE tipo_calidad
(
	codigo_tipo_calidad INT UNSIGNED NOT NULL,
  descripcion_tipo_calidad VARCHAR(50) NOT NULL,
  valor_tipo_calidad DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_tipo_calidad),
  UNIQUE KEY (descripcion_tipo_calidad),
  KEY (codigo_tipo_calidad)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_recurso.
 */
DROP TABLE IF EXISTS configuracion_recurso;
CREATE TABLE configuracion_recurso
(
  codigo_proyecto INT UNSIGNED NOT NULL,
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_tipo_calidad INT UNSIGNED NOT NULL,
  espacial_configuracion_recurso VARCHAR(2) NOT NULL,
  temporal_configuracion_recurso VARCHAR(2) NOT NULL,
  porcentaje_configuracion_recurso DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_proyecto, codigo_recurso, codigo_tipo_calidad),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_tipo_calidad) REFERENCES tipo_calidad(codigo_tipo_calidad),
  KEY (codigo_proyecto),
  KEY (codigo_recurso),
  KEY (codigo_tipo_calidad)
) TYPE=InnoDB;

/**
 * Tabla: funcion_evaluacion.
 */
DROP TABLE IF EXISTS funcion_evaluacion;
CREATE TABLE funcion_evaluacion
(
	codigo_funcion_evaluacion INT UNSIGNED NOT NULL,
	descripcion_funcion_evaluacion VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_funcion_evaluacion),
  UNIQUE KEY (descripcion_funcion_evaluacion),
  KEY (codigo_funcion_evaluacion)
) TYPE=InnoDB;

/**
 * Tabla: tecnica_seleccion.
 */
DROP TABLE IF EXISTS tecnica_seleccion;
CREATE TABLE tecnica_seleccion
(
	codigo_tecnica_seleccion INT UNSIGNED NOT NULL,
	descripcion_tecnica_seleccion VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tecnica_seleccion),
  UNIQUE KEY (descripcion_tecnica_seleccion),
  KEY (codigo_tecnica_seleccion)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_agt.
 */
DROP TABLE IF EXISTS configuracion_agt;
CREATE TABLE configuracion_agt
(
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_funcion_evaluacion INT UNSIGNED NOT NULL,
	codigo_tecnica_seleccion INT UNSIGNED NOT NULL,
	tamanio_poblacion_configuracion_agt INT UNSIGNED NOT NULL,
	numero_generaciones_configuracion_agt INT UNSIGNED NOT NULL,
	probabilidad_cruce_configuracion_agt DOUBLE UNSIGNED NOT NULL,
	probabilidad_mutacion_configuracion_agt DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_proyecto),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_funcion_evaluacion) REFERENCES funcion_evaluacion(codigo_funcion_evaluacion),
  FOREIGN KEY (codigo_tecnica_seleccion) REFERENCES tecnica_seleccion(codigo_tecnica_seleccion),
  KEY (codigo_proyecto),
  KEY (codigo_funcion_evaluacion),
  KEY (codigo_tecnica_seleccion)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_agr.
 */
DROP TABLE IF EXISTS configuracion_agr;
CREATE TABLE configuracion_agr
(
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_tecnica_seleccion INT UNSIGNED NOT NULL,
	tamanio_poblacion_configuracion_agr INT UNSIGNED NOT NULL,
	numero_generaciones_configuracion_agr INT UNSIGNED NOT NULL,
	probabilidad_cruce_configuracion_agr DOUBLE UNSIGNED NOT NULL,
	probabilidad_mutacion_configuracion_agr DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_proyecto),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_tecnica_seleccion) REFERENCES tecnica_seleccion(codigo_tecnica_seleccion),
  KEY (codigo_proyecto),
  KEY (codigo_tecnica_seleccion)
) TYPE=InnoDB;

/**
 * Tabla: punto_demanda.
 */
DROP TABLE IF EXISTS punto_demanda;
CREATE TABLE punto_demanda
(
	codigo_punto_demanda INT UNSIGNED NOT NULL,
	codigo_empresa INT UNSIGNED NOT NULL,
	codigo_coordenada INT UNSIGNED NOT NULL,
	descripcion_punto_demanda VARCHAR(50) NOT NULL,
	PRIMARY KEY (codigo_punto_demanda),
	UNIQUE KEY (descripcion_punto_demanda),
	FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo_empresa),
	FOREIGN KEY (codigo_coordenada) REFERENCES coordenada(codigo_coordenada),
	KEY (codigo_punto_demanda),
	KEY (codigo_empresa),
	KEY (codigo_coordenada)
) TYPE=InnoDB;

/**
 * Tabla: medio_transporte.
 */
DROP TABLE IF EXISTS medio_transporte;
CREATE TABLE medio_transporte
(
	codigo_medio_transporte INT UNSIGNED NOT NULL,
	codigo_empresa INT UNSIGNED NOT NULL,
	descripcion_medio_transporte VARCHAR(50) NOT NULL,
	capacidad_medio_transporte DOUBLE UNSIGNED NOT NULL,
	potencia_motor_medio_transporte DOUBLE UNSIGNED NOT NULL,
	eslora_medio_transporte DOUBLE UNSIGNED NOT NULL,
	PRIMARY KEY (codigo_medio_transporte),
	UNIQUE KEY (descripcion_medio_transporte),
	FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo_empresa),
	KEY (codigo_medio_transporte),
	KEY (codigo_empresa)
) TYPE=InnoDB;

/**
 * Tabla: unidad.
 */
DROP TABLE IF EXISTS unidad;
CREATE TABLE unidad
(
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_caladero_resultado INT UNSIGNED NOT NULL,
	codigo_punto_demanda INT UNSIGNED NOT NULL,
	codigo_medio_transporte INT UNSIGNED NOT NULL,
	costo_unidad DOUBLE UNSIGNED NOT NULL,
	cantidad_unidad DOUBLE UNSIGNED NOT NULL,
	PRIMARY KEY (codigo_anio, codigo_dia, codigo_proyecto, codigo_caladero_resultado, codigo_punto_demanda, codigo_medio_transporte),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
	FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
	FOREIGN KEY (codigo_caladero_resultado) REFERENCES caladero_resultado(codigo_caladero_resultado),
	FOREIGN KEY (codigo_punto_demanda) REFERENCES punto_demanda(codigo_punto_demanda),
	FOREIGN KEY (codigo_medio_transporte) REFERENCES medio_transporte(codigo_medio_transporte),
	KEY (codigo_anio),
  KEY (codigo_dia),
  KEY (codigo_proyecto),
  KEY (codigo_caladero_resultado),
  KEY (codigo_punto_demanda),
  KEY (codigo_medio_transporte)
) TYPE=InnoDB;

/**
 * Tabla: ruta.
 */
DROP TABLE IF EXISTS ruta;
CREATE TABLE ruta
(
  codigo_medio_transporte INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_proyecto INT UNSIGNED NOT NULL,
  tour_ruta TEXT NOT NULL,
  distancia_total_ruta DOUBLE UNSIGNED NOT NULL,
	PRIMARY KEY (codigo_medio_transporte, codigo_anio, codigo_dia, codigo_proyecto),
	FOREIGN KEY (codigo_medio_transporte) REFERENCES medio_transporte(codigo_medio_transporte),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
	FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  KEY (codigo_medio_transporte),
	KEY (codigo_anio),
  KEY (codigo_dia),
  KEY (codigo_proyecto)
) TYPE=InnoDB;

/**
 * Tabla: dato_integrado_resultado.
 */
DROP TABLE IF EXISTS dato_integrado_resultado;
CREATE TABLE dato_integrado_resultado
(
  codigo_recurso INT UNSIGNED NOT NULL,
  codigo_region INT UNSIGNED NOT NULL,
	codigo_anio INT UNSIGNED NOT NULL,
	codigo_dia INT UNSIGNED NOT NULL,
	codigo_proyecto INT UNSIGNED NOT NULL,
  oferta_dato_integrado_resultado DOUBLE NOT NULL,
  demanda_dato_integrado_resultado DOUBLE NOT NULL,
  precio_dato_integrado_resultado DOUBLE NOT NULL,
  venta_dato_integrado_resultado DOUBLE NOT NULL,
	PRIMARY KEY (codigo_recurso, codigo_region, codigo_anio, codigo_dia, codigo_proyecto),
  FOREIGN KEY (codigo_recurso) REFERENCES recurso(codigo_recurso),
  FOREIGN KEY (codigo_region) REFERENCES region(codigo_region),
  FOREIGN KEY (codigo_anio) REFERENCES anio(codigo_anio),
  FOREIGN KEY (codigo_dia) REFERENCES dia(codigo_dia),
	FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  KEY (codigo_recurso),
  KEY (codigo_region),
	KEY (codigo_anio),
  KEY (codigo_dia),
  KEY (codigo_proyecto)
) TYPE=InnoDB;

/**
 * Tabla: tipo_heuristica.
 */
DROP TABLE IF EXISTS tipo_heuristica;
CREATE TABLE tipo_heuristica
(
	codigo_tipo_heuristica INT UNSIGNED NOT NULL,
	descripcion_tipo_heuristica VARCHAR(50) NOT NULL,
  PRIMARY KEY (codigo_tipo_heuristica),
  UNIQUE KEY (descripcion_tipo_heuristica),
  KEY (codigo_tipo_heuristica)
) TYPE=InnoDB;

/**
 * Tabla: configuracion_heuristica.
 */
DROP TABLE IF EXISTS configuracion_heuristica;
CREATE TABLE configuracion_heuristica
(
	codigo_proyecto INT UNSIGNED NOT NULL,
	codigo_tipo_heuristica INT UNSIGNED NOT NULL,
	codigo_tipo_red_neuronal INT UNSIGNED NOT NULL,
	stock_acumulado_configuracion_heuristica DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (codigo_proyecto),
  FOREIGN KEY (codigo_proyecto) REFERENCES proyecto(codigo_proyecto),
  FOREIGN KEY (codigo_tipo_heuristica) REFERENCES tipo_heuristica(codigo_tipo_heuristica),
  FOREIGN KEY (codigo_tipo_red_neuronal) REFERENCES tipo_red_neuronal(codigo_tipo_red_neuronal),
  KEY (codigo_proyecto),
  KEY (codigo_tipo_heuristica),
  KEY (codigo_tipo_red_neuronal)
) TYPE=InnoDB;

/**
 * Tabla: empresa.
 */
INSERT INTO empresa (codigo_empresa, nombre_empresa) VALUES (0, 'Pesca Marina Ltda.');

/**
 * Tabla: longitud.
 */
INSERT INTO longitud (codigo_longitud, descripcion_longitud) VALUES (0, 'Este');
INSERT INTO longitud (codigo_longitud, descripcion_longitud) VALUES (1, 'Oeste');

/**
 * Tabla: latitud.
 */
INSERT INTO latitud (codigo_latitud, descripcion_latitud) VALUES (0, 'Norte');
INSERT INTO latitud (codigo_latitud, descripcion_latitud) VALUES (1, 'Sur');

/**
 * Tabla: region.
 */
INSERT INTO region (codigo_region, descripcion_region, codigo_longitud_inicial, codigo_longitud_final, codigo_latitud_inicial, codigo_latitud_final, longitud_inicial_region, longitud_final_region, latitud_inicial_region, latitud_final_region, altitud_inicial_region, altitud_final_region) VALUES (0, 'IV Región', 1, 1, 1, 1, 71.25, 72.5, 29.5, 32.25, -1000, 0);

/**
 * Tabla: tipo_recurso.
 */
INSERT INTO tipo_recurso (codigo_tipo_recurso, descripcion_tipo_recurso) VALUES (0, 'Predecible');
INSERT INTO tipo_recurso (codigo_tipo_recurso, descripcion_tipo_recurso) VALUES (1, 'No Predecible');

/**
 * Tabla: tipo_animal.
 */
INSERT INTO tipo_animal (codigo_tipo_animal, descripcion_tipo_animal) VALUES (0, 'Crustáceo');
INSERT INTO tipo_animal (codigo_tipo_animal, descripcion_tipo_animal) VALUES (1, 'Pez');

/**
 * Tabla: zona_sistema.
 */
INSERT INTO zona_sistema (codigo_zona_sistema, descripcion_zona_sistema, distancia_fondo_minima_zona_sistema, distancia_fondo_maxima_zona_sistema) VALUES (0, 'Pelágico', 500, 1000);
INSERT INTO zona_sistema (codigo_zona_sistema, descripcion_zona_sistema, distancia_fondo_minima_zona_sistema, distancia_fondo_maxima_zona_sistema) VALUES (1, 'Bentónico', 0, 10);
INSERT INTO zona_sistema (codigo_zona_sistema, descripcion_zona_sistema, distancia_fondo_minima_zona_sistema, distancia_fondo_maxima_zona_sistema) VALUES (2, 'Demersal', 0, 100);
INSERT INTO zona_sistema (codigo_zona_sistema, descripcion_zona_sistema, distancia_fondo_minima_zona_sistema, distancia_fondo_maxima_zona_sistema) VALUES (3, 'Costero', 0, 10);

/**
 * Tabla: zona_pelagica.
 */
INSERT INTO zona_pelagica (codigo_zona_pelagica, descripcion_zona_pelagica, altitud_inicial_zona_pelagica, altitud_final_zona_pelagica) VALUES (0, 'Epipelágico', 0, -100);
INSERT INTO zona_pelagica (codigo_zona_pelagica, descripcion_zona_pelagica, altitud_inicial_zona_pelagica, altitud_final_zona_pelagica) VALUES (1, 'Mesopelágico', -100, -1000);
INSERT INTO zona_pelagica (codigo_zona_pelagica, descripcion_zona_pelagica, altitud_inicial_zona_pelagica, altitud_final_zona_pelagica) VALUES (2, 'Batipelágico', -1000, -4000);
INSERT INTO zona_pelagica (codigo_zona_pelagica, descripcion_zona_pelagica, altitud_inicial_zona_pelagica, altitud_final_zona_pelagica) VALUES (3, 'Abisopelágico', -4000, -10000);

/**
 * Tabla: zona_bentonica.
 */
INSERT INTO zona_bentonica (codigo_zona_bentonica, descripcion_zona_bentonica, altitud_inicial_zona_bentonica, altitud_final_zona_bentonica) VALUES (0, 'Supralitoral', 5, 0);
INSERT INTO zona_bentonica (codigo_zona_bentonica, descripcion_zona_bentonica, altitud_inicial_zona_bentonica, altitud_final_zona_bentonica) VALUES (1, 'Litoral', 0, -50);
INSERT INTO zona_bentonica (codigo_zona_bentonica, descripcion_zona_bentonica, altitud_inicial_zona_bentonica, altitud_final_zona_bentonica) VALUES (2, 'Sublitoral', -50, -200);
INSERT INTO zona_bentonica (codigo_zona_bentonica, descripcion_zona_bentonica, altitud_inicial_zona_bentonica, altitud_final_zona_bentonica) VALUES (3, 'Batial', -200, -4000);
INSERT INTO zona_bentonica (codigo_zona_bentonica, descripcion_zona_bentonica, altitud_inicial_zona_bentonica, altitud_final_zona_bentonica) VALUES (4, 'Abisal', -4000, -5000);
INSERT INTO zona_bentonica (codigo_zona_bentonica, descripcion_zona_bentonica, altitud_inicial_zona_bentonica, altitud_final_zona_bentonica) VALUES (5, 'Hadal', -5000, -10000);

/**
 * Tabla: zona_region.
 */
INSERT INTO zona_region (codigo_zona_region, descripcion_zona_region, distancia_continente_minima_zona_region, distancia_continente_maxima_zona_region) VALUES (0, 'Nerítica', 0, 10000);
INSERT INTO zona_region (codigo_zona_region, descripcion_zona_region, distancia_continente_minima_zona_region, distancia_continente_maxima_zona_region) VALUES (1, 'Oceánica', 10000, 100000);

/**
 * Tabla: zona_luz.
 */
INSERT INTO zona_luz (codigo_zona_luz, descripcion_zona_luz, altitud_inicial_zona_luz, altitud_final_zona_luz) VALUES (0, 'Eufótica', 0, -80);
INSERT INTO zona_luz (codigo_zona_luz, descripcion_zona_luz, altitud_inicial_zona_luz, altitud_final_zona_luz) VALUES (1, 'Disfótica', -80, -250);
INSERT INTO zona_luz (codigo_zona_luz, descripcion_zona_luz, altitud_inicial_zona_luz, altitud_final_zona_luz) VALUES (2, 'Afótica', -250, -10000);

/**
 * Tabla: conducta.
 */
INSERT INTO conducta (codigo_conducta, descripcion_conducta) VALUES (0, 'Solitaria');
INSERT INTO conducta (codigo_conducta, descripcion_conducta) VALUES (1, 'Grupal');

/**
 * Tabla: color.
 */
INSERT INTO color (codigo_color, descripcion_color, rojo_color, verde_color, azul_color) VALUES (0, 'Salmón', 255, 200, 0);
INSERT INTO color (codigo_color, descripcion_color, rojo_color, verde_color, azul_color) VALUES (1, 'Amarillo', 255, 255, 0);
INSERT INTO color (codigo_color, descripcion_color, rojo_color, verde_color, azul_color) VALUES (2, 'Rojo', 255, 0, 0);
INSERT INTO color (codigo_color, descripcion_color, rojo_color, verde_color, azul_color) VALUES (3, 'Gris', 128, 128, 128);

/**
 * Tabla: tipo_reproduccion.
 */
INSERT INTO tipo_reproduccion (codigo_tipo_reproduccion, descripcion_tipo_reproduccion) VALUES (0, 'Sexuada');
INSERT INTO tipo_reproduccion (codigo_tipo_reproduccion, descripcion_tipo_reproduccion) VALUES (1, 'Asexuada');

/**
 * Tabla: recurso.
 */
INSERT INTO recurso VALUES("0", "0", "1", "1", "3", "0", "2", "1", "0", "1", "1", "0", "0", "Camarón Nailon", "11", "13", "4.07", "-0.81", "0.2", "0.0004", "3.0295", "4.83", "-0.51", "0.17", "0.0002", "3.1775", "0.5", "4.75", "0.5", "5.3", "0.001", "34", "0.001", "36", "11.67", "-0.54", "0.5", "2000", "13000", "60", "0.01", "100", "25", "40", "-200", "-600", "9", "10", "34", "34", "0", "1", "5", "2.5", "10", "1", "10", "0.5", "0.5", "0.5", "0.1", "0.85", "0.75", "0.65", "0.65", "0.25", "0.25", "0.01", "0.01");
INSERT INTO recurso VALUES("1", "0", "1", "1", "3", "0", "2", "1", "1", "1", "1", "1", "0", "Langostino Amarillo", "13", "11", "6.21", "-1.62", "0.165", "0.0003", "3.2536", "5.46", "-1.87", "0.177", "0.0005", "3.0732", "0.9", "5.4", "0.9", "4.85", "0.005", "38", "0.005", "36", "16.009233", "-0.624593", "0.5", "1000", "20000", "60", "0.01", "100", "21", "44", "-50", "-450", "10", "11", "34.6", "34.7", "0", "1", "5", "2.5", "10", "1", "10", "0.5", "0.5", "0.5", "0.1", "0.85", "0.75", "0.65", "0.65", "0.25", "0.25", "0.01", "0.01");
INSERT INTO recurso VALUES("2", "0", "1", "1", "3", "0", "2", "1", "2", "1", "1", "1", "0", "Langostino Colorado", "13", "11", "5.05", "-0.51", "0.197", "0.0006", "2.9625", "4.46", "-0.51", "0.179", "0.0006", "2.9625", "0.95", "5.11", "0.95", "4.95", "0.005", "37", "0.005", "37", "6.74", "-0.24", "0.5", "2000", "34000", "60", "0.01", "100", "18", "42", "-50", "-350", "9", "10", "34.6", "34.7", "0", "1", "5", "2.5", "10", "1", "10", "0.5", "0.5", "0.5", "0.1", "0.85", "0.75", "0.65", "0.65", "0.25", "0.25", "0.01", "0.01");
INSERT INTO recurso VALUES("3", "1", "2", "1", "3", "0", "2", "1", "3", "1", "1", "1", "0", "Merluza Común", "15", "15", "56", "-0.061", "0.27", "0.01", "2.9895", "64.3", "-0.24", "0.206", "0.01", "2.8927", "3.325", "50", "3.325", "80", "0.0175", "1200", "0.0175", "3200", "9.7246", "-0.2631", "0.5", "143177", "185591", "60", "0.01", "1000", "22", "48", "-50", "-500", "10", "12", "33.52", "34.73", "0.1", "2", "10", "5", "10", "1", "10", "0.5", "0.5", "0.5", "0.1", "0.75", "0.85", "0.65", "0.65", "0.25", "0.25", "0.01", "0.01");

/**
 * Tabla: tipo_dieta.
 */
INSERT INTO tipo_dieta (codigo_tipo_dieta, descripcion_tipo_dieta) VALUES (0, 'Fitoplanctívoro');
INSERT INTO tipo_dieta (codigo_tipo_dieta, descripcion_tipo_dieta) VALUES (1, 'Zooplanctívoro');
INSERT INTO tipo_dieta (codigo_tipo_dieta, descripcion_tipo_dieta) VALUES (2, 'Detritívoro');
INSERT INTO tipo_dieta (codigo_tipo_dieta, descripcion_tipo_dieta) VALUES (3, 'Hervívoro');
INSERT INTO tipo_dieta (codigo_tipo_dieta, descripcion_tipo_dieta) VALUES (4, 'Carnívoro');
INSERT INTO tipo_dieta (codigo_tipo_dieta, descripcion_tipo_dieta) VALUES (5, 'Sedimentívoro');

/**
 * Tabla: recurso_tipo_dieta.
 */
INSERT INTO recurso_tipo_dieta VALUES("0", "0", "No");
INSERT INTO recurso_tipo_dieta VALUES("0", "1", "No");
INSERT INTO recurso_tipo_dieta VALUES("0", "2", "Si");
INSERT INTO recurso_tipo_dieta VALUES("0", "3", "No");
INSERT INTO recurso_tipo_dieta VALUES("0", "4", "No");
INSERT INTO recurso_tipo_dieta VALUES("0", "5", "Si");
INSERT INTO recurso_tipo_dieta VALUES("1", "0", "No");
INSERT INTO recurso_tipo_dieta VALUES("1", "1", "No");
INSERT INTO recurso_tipo_dieta VALUES("1", "2", "Si");
INSERT INTO recurso_tipo_dieta VALUES("1", "3", "No");
INSERT INTO recurso_tipo_dieta VALUES("1", "4", "No");
INSERT INTO recurso_tipo_dieta VALUES("1", "5", "Si");
INSERT INTO recurso_tipo_dieta VALUES("2", "0", "No");
INSERT INTO recurso_tipo_dieta VALUES("2", "1", "No");
INSERT INTO recurso_tipo_dieta VALUES("2", "2", "Si");
INSERT INTO recurso_tipo_dieta VALUES("2", "3", "No");
INSERT INTO recurso_tipo_dieta VALUES("2", "4", "No");
INSERT INTO recurso_tipo_dieta VALUES("2", "5", "Si");
INSERT INTO recurso_tipo_dieta VALUES("3", "0", "No");
INSERT INTO recurso_tipo_dieta VALUES("3", "1", "Si");
INSERT INTO recurso_tipo_dieta VALUES("3", "2", "No");
INSERT INTO recurso_tipo_dieta VALUES("3", "3", "No");
INSERT INTO recurso_tipo_dieta VALUES("3", "4", "Si");
INSERT INTO recurso_tipo_dieta VALUES("3", "5", "No");

/**
 * Tabla: mes.
 */
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (1, 'Enero');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (2, 'Febrero');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (3, 'Marzo');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (4, 'Abril');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (5, 'Mayo');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (6, 'Junio');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (7, 'Julio');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (8, 'Agosto');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (9, 'Septiembre');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (10, 'Octubre');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (11, 'Noviembre');
INSERT INTO  mes (codigo_mes, descripcion_mes) VALUES (12, 'Diciembre');

/**
 * Tabla: recurso_mes.
 */
INSERT INTO recurso_mes VALUES("0", "1", "0", "0.25");
INSERT INTO recurso_mes VALUES("0", "2", "0", "0");
INSERT INTO recurso_mes VALUES("0", "3", "0", "0");
INSERT INTO recurso_mes VALUES("0", "4", "0.25", "0");
INSERT INTO recurso_mes VALUES("0", "5", "0.5", "0");
INSERT INTO recurso_mes VALUES("0", "6", "0.75", "0");
INSERT INTO recurso_mes VALUES("0", "7", "1", "0.25");
INSERT INTO recurso_mes VALUES("0", "8", "0.75", "0.5");
INSERT INTO recurso_mes VALUES("0", "9", "0.5", "0.75");
INSERT INTO recurso_mes VALUES("0", "10", "0.25", "1");
INSERT INTO recurso_mes VALUES("0", "11", "0", "0.75");
INSERT INTO recurso_mes VALUES("0", "12", "0", "0.5");
INSERT INTO recurso_mes VALUES("1", "1", "0", "0.25");
INSERT INTO recurso_mes VALUES("1", "2", "0", "0");
INSERT INTO recurso_mes VALUES("1", "3", "0", "0");
INSERT INTO recurso_mes VALUES("1", "4", "0.25", "0");
INSERT INTO recurso_mes VALUES("1", "5", "0.5", "0");
INSERT INTO recurso_mes VALUES("1", "6", "0.75", "0");
INSERT INTO recurso_mes VALUES("1", "7", "1", "0.25");
INSERT INTO recurso_mes VALUES("1", "8", "0.75", "0.5");
INSERT INTO recurso_mes VALUES("1", "9", "0.5", "0.75");
INSERT INTO recurso_mes VALUES("1", "10", "0.25", "1");
INSERT INTO recurso_mes VALUES("1", "11", "0", "0.75");
INSERT INTO recurso_mes VALUES("1", "12", "0", "0.5");
INSERT INTO recurso_mes VALUES("2", "1", "0", "0.5");
INSERT INTO recurso_mes VALUES("2", "2", "0", "0.25");
INSERT INTO recurso_mes VALUES("2", "3", "0", "0");
INSERT INTO recurso_mes VALUES("2", "4", "0.25", "0");
INSERT INTO recurso_mes VALUES("2", "5", "0.5", "0");
INSERT INTO recurso_mes VALUES("2", "6", "0.75", "0");
INSERT INTO recurso_mes VALUES("2", "7", "1", "0");
INSERT INTO recurso_mes VALUES("2", "8", "0.75", "0.25");
INSERT INTO recurso_mes VALUES("2", "9", "0.5", "0.5");
INSERT INTO recurso_mes VALUES("2", "10", "0.25", "0.75");
INSERT INTO recurso_mes VALUES("2", "11", "0", "1");
INSERT INTO recurso_mes VALUES("2", "12", "0", "0.75");
INSERT INTO recurso_mes VALUES("3", "1", "0.75", "1");
INSERT INTO recurso_mes VALUES("3", "2", "1", "1");
INSERT INTO recurso_mes VALUES("3", "3", "0.75", "1");
INSERT INTO recurso_mes VALUES("3", "4", "0.5", "1");
INSERT INTO recurso_mes VALUES("3", "5", "0.25", "1");
INSERT INTO recurso_mes VALUES("3", "6", "0.5", "1");
INSERT INTO recurso_mes VALUES("3", "7", "0.75", "1");
INSERT INTO recurso_mes VALUES("3", "8", "1", "1");
INSERT INTO recurso_mes VALUES("3", "9", "0.75", "1");
INSERT INTO recurso_mes VALUES("3", "10", "0.5", "1");
INSERT INTO recurso_mes VALUES("3", "11", "0.25", "1");
INSERT INTO recurso_mes VALUES("3", "12", "0.5", "1");

/**
 * Tabla: fauna_acompaniante.
 */
INSERT INTO fauna_acompaniante VALUES("0", "0", "0");
INSERT INTO fauna_acompaniante VALUES("0", "1", "0");
INSERT INTO fauna_acompaniante VALUES("0", "2", "0");
INSERT INTO fauna_acompaniante VALUES("0", "3", "0");
INSERT INTO fauna_acompaniante VALUES("1", "0", "0");
INSERT INTO fauna_acompaniante VALUES("1", "1", "0");
INSERT INTO fauna_acompaniante VALUES("1", "2", "0");
INSERT INTO fauna_acompaniante VALUES("1", "3", "0");
INSERT INTO fauna_acompaniante VALUES("2", "0", "0");
INSERT INTO fauna_acompaniante VALUES("2", "1", "0");
INSERT INTO fauna_acompaniante VALUES("2", "2", "0");
INSERT INTO fauna_acompaniante VALUES("2", "3", "0");
INSERT INTO fauna_acompaniante VALUES("3", "0", "1");
INSERT INTO fauna_acompaniante VALUES("3", "1", "1");
INSERT INTO fauna_acompaniante VALUES("3", "2", "1");
INSERT INTO fauna_acompaniante VALUES("3", "3", "0");

/**
 * Tabla: proyecto.
 */
INSERT INTO proyecto VALUES("1", "0", "0", "0", "Nuevo Proyecto", "2004-1-1", "2005-1-1", "H. Díaz, P. Leger, P. Merino", "Primer estudio", "Aquí escriba las observaciones\n y/o acotaciones del proyecto.", NULL, NULL, NULL, NULL);

/**
 * Tabla: funcion_neuronal.
 */
INSERT INTO funcion_neuronal (codigo_funcion_neuronal, descripcion_funcion_neuronal) VALUES (0, 'Lineal');
INSERT INTO funcion_neuronal (codigo_funcion_neuronal, descripcion_funcion_neuronal) VALUES (1, 'Sigmoidal');
INSERT INTO funcion_neuronal (codigo_funcion_neuronal, descripcion_funcion_neuronal) VALUES (2, 'TanHiperbólica');

/**
 * Tabla: configuracion_bp.
 */
INSERT INTO configuracion_bp (codigo_proyecto, codigo_funcion_neuronalco1, codigo_funcion_neuronalco2, codigo_funcion_neuronals, numero_patrones_configuracion_bp, numero_entradas_configuracion_bp, numero_neuronasco1_configuracion_bp, numero_neuronasco2_configuracion_bp, numero_salidas_configuracion_bp, ciclo_configuracion_bp, porcentaje_cruzada_configuracion_bp, error_minimo_configuracion_bp, error_maximo_configuracion_bp, rata_configuracion_bp, variacion_rata_configuracion_bp) VALUES (1, 2, 2, 0, 50, 2, 20, 19, 2, 50000, 0.05, 1, 2, 0.03, 1.1);

/**
 * Tabla: configuracion_rbf.
 */
INSERT INTO configuracion_rbf (codigo_proyecto, numero_patrones_configuracion_rbf, numero_entradas_configuracion_rbf, numero_centroides_configuracion_rbf, numero_salidas_configuracion_rbf, ciclo_supervisado_configuracion_rbf, ciclo_no_supervisado_configuracion_rbf, porcentaje_cruzada_configuracion_rbf, error_minimo_configuracion_rbf, error_maximo_configuracion_rbf, rata_supervisada_configuracion_rbf, variacion_rata_supervisada_configuracion_rbf, ratai_no_supervisada_configuracion_rbf, rataf_no_supervisada_configuracion_rbf, habilitar_centroides_dinamicos_configuracion_rbf) VALUES (1, 50, 2, 30, 2, 60000, 20000, 0.05, 1, 2, 0.03, 1.1, 0.6, 0.01, 1);

/**
 * Tabla: anio.
 */
INSERT INTO  anio (codigo_anio) VALUES (2002);
INSERT INTO  anio (codigo_anio) VALUES (2003);
INSERT INTO  anio (codigo_anio) VALUES (2004);
INSERT INTO  anio (codigo_anio) VALUES (2005);
INSERT INTO  anio (codigo_anio) VALUES (2006);
INSERT INTO  anio (codigo_anio) VALUES (2007);
INSERT INTO  anio (codigo_anio) VALUES (2008);
INSERT INTO  anio (codigo_anio) VALUES (2009);
INSERT INTO  anio (codigo_anio) VALUES (2010);

/**
 * Tabla: dia.
 */
INSERT INTO dia (codigo_dia) VALUES(1);
INSERT INTO dia (codigo_dia) VALUES(2);
INSERT INTO dia (codigo_dia) VALUES(3);
INSERT INTO dia (codigo_dia) VALUES(4);
INSERT INTO dia (codigo_dia) VALUES(5);
INSERT INTO dia (codigo_dia) VALUES(6);
INSERT INTO dia (codigo_dia) VALUES(7);
INSERT INTO dia (codigo_dia) VALUES(8);
INSERT INTO dia (codigo_dia) VALUES(9);
INSERT INTO dia (codigo_dia) VALUES(10);
INSERT INTO dia (codigo_dia) VALUES(11);
INSERT INTO dia (codigo_dia) VALUES(12);
INSERT INTO dia (codigo_dia) VALUES(13);
INSERT INTO dia (codigo_dia) VALUES(14);
INSERT INTO dia (codigo_dia) VALUES(15);
INSERT INTO dia (codigo_dia) VALUES(16);
INSERT INTO dia (codigo_dia) VALUES(17);
INSERT INTO dia (codigo_dia) VALUES(18);
INSERT INTO dia (codigo_dia) VALUES(19);
INSERT INTO dia (codigo_dia) VALUES(20);
INSERT INTO dia (codigo_dia) VALUES(21);
INSERT INTO dia (codigo_dia) VALUES(22);
INSERT INTO dia (codigo_dia) VALUES(23);
INSERT INTO dia (codigo_dia) VALUES(24);
INSERT INTO dia (codigo_dia) VALUES(25);
INSERT INTO dia (codigo_dia) VALUES(26);
INSERT INTO dia (codigo_dia) VALUES(27);
INSERT INTO dia (codigo_dia) VALUES(28);
INSERT INTO dia (codigo_dia) VALUES(29);
INSERT INTO dia (codigo_dia) VALUES(30);
INSERT INTO dia (codigo_dia) VALUES(31);
INSERT INTO dia (codigo_dia) VALUES(32);
INSERT INTO dia (codigo_dia) VALUES(33);
INSERT INTO dia (codigo_dia) VALUES(34);
INSERT INTO dia (codigo_dia) VALUES(35);
INSERT INTO dia (codigo_dia) VALUES(36);
INSERT INTO dia (codigo_dia) VALUES(37);
INSERT INTO dia (codigo_dia) VALUES(38);
INSERT INTO dia (codigo_dia) VALUES(39);
INSERT INTO dia (codigo_dia) VALUES(40);
INSERT INTO dia (codigo_dia) VALUES(41);
INSERT INTO dia (codigo_dia) VALUES(42);
INSERT INTO dia (codigo_dia) VALUES(43);
INSERT INTO dia (codigo_dia) VALUES(44);
INSERT INTO dia (codigo_dia) VALUES(45);
INSERT INTO dia (codigo_dia) VALUES(46);
INSERT INTO dia (codigo_dia) VALUES(47);
INSERT INTO dia (codigo_dia) VALUES(48);
INSERT INTO dia (codigo_dia) VALUES(49);
INSERT INTO dia (codigo_dia) VALUES(50);
INSERT INTO dia (codigo_dia) VALUES(51);
INSERT INTO dia (codigo_dia) VALUES(52);
INSERT INTO dia (codigo_dia) VALUES(53);
INSERT INTO dia (codigo_dia) VALUES(54);
INSERT INTO dia (codigo_dia) VALUES(55);
INSERT INTO dia (codigo_dia) VALUES(56);
INSERT INTO dia (codigo_dia) VALUES(57);
INSERT INTO dia (codigo_dia) VALUES(58);
INSERT INTO dia (codigo_dia) VALUES(59);
INSERT INTO dia (codigo_dia) VALUES(60);
INSERT INTO dia (codigo_dia) VALUES(61);
INSERT INTO dia (codigo_dia) VALUES(62);
INSERT INTO dia (codigo_dia) VALUES(63);
INSERT INTO dia (codigo_dia) VALUES(64);
INSERT INTO dia (codigo_dia) VALUES(65);
INSERT INTO dia (codigo_dia) VALUES(66);
INSERT INTO dia (codigo_dia) VALUES(67);
INSERT INTO dia (codigo_dia) VALUES(68);
INSERT INTO dia (codigo_dia) VALUES(69);
INSERT INTO dia (codigo_dia) VALUES(70);
INSERT INTO dia (codigo_dia) VALUES(71);
INSERT INTO dia (codigo_dia) VALUES(72);
INSERT INTO dia (codigo_dia) VALUES(73);
INSERT INTO dia (codigo_dia) VALUES(74);
INSERT INTO dia (codigo_dia) VALUES(75);
INSERT INTO dia (codigo_dia) VALUES(76);
INSERT INTO dia (codigo_dia) VALUES(77);
INSERT INTO dia (codigo_dia) VALUES(78);
INSERT INTO dia (codigo_dia) VALUES(79);
INSERT INTO dia (codigo_dia) VALUES(80);
INSERT INTO dia (codigo_dia) VALUES(81);
INSERT INTO dia (codigo_dia) VALUES(82);
INSERT INTO dia (codigo_dia) VALUES(83);
INSERT INTO dia (codigo_dia) VALUES(84);
INSERT INTO dia (codigo_dia) VALUES(85);
INSERT INTO dia (codigo_dia) VALUES(86);
INSERT INTO dia (codigo_dia) VALUES(87);
INSERT INTO dia (codigo_dia) VALUES(88);
INSERT INTO dia (codigo_dia) VALUES(89);
INSERT INTO dia (codigo_dia) VALUES(90);
INSERT INTO dia (codigo_dia) VALUES(91);
INSERT INTO dia (codigo_dia) VALUES(92);
INSERT INTO dia (codigo_dia) VALUES(93);
INSERT INTO dia (codigo_dia) VALUES(94);
INSERT INTO dia (codigo_dia) VALUES(95);
INSERT INTO dia (codigo_dia) VALUES(96);
INSERT INTO dia (codigo_dia) VALUES(97);
INSERT INTO dia (codigo_dia) VALUES(98);
INSERT INTO dia (codigo_dia) VALUES(99);
INSERT INTO dia (codigo_dia) VALUES(100);
INSERT INTO dia (codigo_dia) VALUES(101);
INSERT INTO dia (codigo_dia) VALUES(102);
INSERT INTO dia (codigo_dia) VALUES(103);
INSERT INTO dia (codigo_dia) VALUES(104);
INSERT INTO dia (codigo_dia) VALUES(105);
INSERT INTO dia (codigo_dia) VALUES(106);
INSERT INTO dia (codigo_dia) VALUES(107);
INSERT INTO dia (codigo_dia) VALUES(108);
INSERT INTO dia (codigo_dia) VALUES(109);
INSERT INTO dia (codigo_dia) VALUES(110);
INSERT INTO dia (codigo_dia) VALUES(111);
INSERT INTO dia (codigo_dia) VALUES(112);
INSERT INTO dia (codigo_dia) VALUES(113);
INSERT INTO dia (codigo_dia) VALUES(114);
INSERT INTO dia (codigo_dia) VALUES(115);
INSERT INTO dia (codigo_dia) VALUES(116);
INSERT INTO dia (codigo_dia) VALUES(117);
INSERT INTO dia (codigo_dia) VALUES(118);
INSERT INTO dia (codigo_dia) VALUES(119);
INSERT INTO dia (codigo_dia) VALUES(120);
INSERT INTO dia (codigo_dia) VALUES(121);
INSERT INTO dia (codigo_dia) VALUES(122);
INSERT INTO dia (codigo_dia) VALUES(123);
INSERT INTO dia (codigo_dia) VALUES(124);
INSERT INTO dia (codigo_dia) VALUES(125);
INSERT INTO dia (codigo_dia) VALUES(126);
INSERT INTO dia (codigo_dia) VALUES(127);
INSERT INTO dia (codigo_dia) VALUES(128);
INSERT INTO dia (codigo_dia) VALUES(129);
INSERT INTO dia (codigo_dia) VALUES(130);
INSERT INTO dia (codigo_dia) VALUES(131);
INSERT INTO dia (codigo_dia) VALUES(132);
INSERT INTO dia (codigo_dia) VALUES(133);
INSERT INTO dia (codigo_dia) VALUES(134);
INSERT INTO dia (codigo_dia) VALUES(135);
INSERT INTO dia (codigo_dia) VALUES(136);
INSERT INTO dia (codigo_dia) VALUES(137);
INSERT INTO dia (codigo_dia) VALUES(138);
INSERT INTO dia (codigo_dia) VALUES(139);
INSERT INTO dia (codigo_dia) VALUES(140);
INSERT INTO dia (codigo_dia) VALUES(141);
INSERT INTO dia (codigo_dia) VALUES(142);
INSERT INTO dia (codigo_dia) VALUES(143);
INSERT INTO dia (codigo_dia) VALUES(144);
INSERT INTO dia (codigo_dia) VALUES(145);
INSERT INTO dia (codigo_dia) VALUES(146);
INSERT INTO dia (codigo_dia) VALUES(147);
INSERT INTO dia (codigo_dia) VALUES(148);
INSERT INTO dia (codigo_dia) VALUES(149);
INSERT INTO dia (codigo_dia) VALUES(150);
INSERT INTO dia (codigo_dia) VALUES(151);
INSERT INTO dia (codigo_dia) VALUES(152);
INSERT INTO dia (codigo_dia) VALUES(153);
INSERT INTO dia (codigo_dia) VALUES(154);
INSERT INTO dia (codigo_dia) VALUES(155);
INSERT INTO dia (codigo_dia) VALUES(156);
INSERT INTO dia (codigo_dia) VALUES(157);
INSERT INTO dia (codigo_dia) VALUES(158);
INSERT INTO dia (codigo_dia) VALUES(159);
INSERT INTO dia (codigo_dia) VALUES(160);
INSERT INTO dia (codigo_dia) VALUES(161);
INSERT INTO dia (codigo_dia) VALUES(162);
INSERT INTO dia (codigo_dia) VALUES(163);
INSERT INTO dia (codigo_dia) VALUES(164);
INSERT INTO dia (codigo_dia) VALUES(165);
INSERT INTO dia (codigo_dia) VALUES(166);
INSERT INTO dia (codigo_dia) VALUES(167);
INSERT INTO dia (codigo_dia) VALUES(168);
INSERT INTO dia (codigo_dia) VALUES(169);
INSERT INTO dia (codigo_dia) VALUES(170);
INSERT INTO dia (codigo_dia) VALUES(171);
INSERT INTO dia (codigo_dia) VALUES(172);
INSERT INTO dia (codigo_dia) VALUES(173);
INSERT INTO dia (codigo_dia) VALUES(174);
INSERT INTO dia (codigo_dia) VALUES(175);
INSERT INTO dia (codigo_dia) VALUES(176);
INSERT INTO dia (codigo_dia) VALUES(177);
INSERT INTO dia (codigo_dia) VALUES(178);
INSERT INTO dia (codigo_dia) VALUES(179);
INSERT INTO dia (codigo_dia) VALUES(180);
INSERT INTO dia (codigo_dia) VALUES(181);
INSERT INTO dia (codigo_dia) VALUES(182);
INSERT INTO dia (codigo_dia) VALUES(183);
INSERT INTO dia (codigo_dia) VALUES(184);
INSERT INTO dia (codigo_dia) VALUES(185);
INSERT INTO dia (codigo_dia) VALUES(186);
INSERT INTO dia (codigo_dia) VALUES(187);
INSERT INTO dia (codigo_dia) VALUES(188);
INSERT INTO dia (codigo_dia) VALUES(189);
INSERT INTO dia (codigo_dia) VALUES(190);
INSERT INTO dia (codigo_dia) VALUES(191);
INSERT INTO dia (codigo_dia) VALUES(192);
INSERT INTO dia (codigo_dia) VALUES(193);
INSERT INTO dia (codigo_dia) VALUES(194);
INSERT INTO dia (codigo_dia) VALUES(195);
INSERT INTO dia (codigo_dia) VALUES(196);
INSERT INTO dia (codigo_dia) VALUES(197);
INSERT INTO dia (codigo_dia) VALUES(198);
INSERT INTO dia (codigo_dia) VALUES(199);
INSERT INTO dia (codigo_dia) VALUES(200);
INSERT INTO dia (codigo_dia) VALUES(201);
INSERT INTO dia (codigo_dia) VALUES(202);
INSERT INTO dia (codigo_dia) VALUES(203);
INSERT INTO dia (codigo_dia) VALUES(204);
INSERT INTO dia (codigo_dia) VALUES(205);
INSERT INTO dia (codigo_dia) VALUES(206);
INSERT INTO dia (codigo_dia) VALUES(207);
INSERT INTO dia (codigo_dia) VALUES(208);
INSERT INTO dia (codigo_dia) VALUES(209);
INSERT INTO dia (codigo_dia) VALUES(210);
INSERT INTO dia (codigo_dia) VALUES(211);
INSERT INTO dia (codigo_dia) VALUES(212);
INSERT INTO dia (codigo_dia) VALUES(213);
INSERT INTO dia (codigo_dia) VALUES(214);
INSERT INTO dia (codigo_dia) VALUES(215);
INSERT INTO dia (codigo_dia) VALUES(216);
INSERT INTO dia (codigo_dia) VALUES(217);
INSERT INTO dia (codigo_dia) VALUES(218);
INSERT INTO dia (codigo_dia) VALUES(219);
INSERT INTO dia (codigo_dia) VALUES(220);
INSERT INTO dia (codigo_dia) VALUES(221);
INSERT INTO dia (codigo_dia) VALUES(222);
INSERT INTO dia (codigo_dia) VALUES(223);
INSERT INTO dia (codigo_dia) VALUES(224);
INSERT INTO dia (codigo_dia) VALUES(225);
INSERT INTO dia (codigo_dia) VALUES(226);
INSERT INTO dia (codigo_dia) VALUES(227);
INSERT INTO dia (codigo_dia) VALUES(228);
INSERT INTO dia (codigo_dia) VALUES(229);
INSERT INTO dia (codigo_dia) VALUES(230);
INSERT INTO dia (codigo_dia) VALUES(231);
INSERT INTO dia (codigo_dia) VALUES(232);
INSERT INTO dia (codigo_dia) VALUES(233);
INSERT INTO dia (codigo_dia) VALUES(234);
INSERT INTO dia (codigo_dia) VALUES(235);
INSERT INTO dia (codigo_dia) VALUES(236);
INSERT INTO dia (codigo_dia) VALUES(237);
INSERT INTO dia (codigo_dia) VALUES(238);
INSERT INTO dia (codigo_dia) VALUES(239);
INSERT INTO dia (codigo_dia) VALUES(240);
INSERT INTO dia (codigo_dia) VALUES(241);
INSERT INTO dia (codigo_dia) VALUES(242);
INSERT INTO dia (codigo_dia) VALUES(243);
INSERT INTO dia (codigo_dia) VALUES(244);
INSERT INTO dia (codigo_dia) VALUES(245);
INSERT INTO dia (codigo_dia) VALUES(246);
INSERT INTO dia (codigo_dia) VALUES(247);
INSERT INTO dia (codigo_dia) VALUES(248);
INSERT INTO dia (codigo_dia) VALUES(249);
INSERT INTO dia (codigo_dia) VALUES(250);
INSERT INTO dia (codigo_dia) VALUES(251);
INSERT INTO dia (codigo_dia) VALUES(252);
INSERT INTO dia (codigo_dia) VALUES(253);
INSERT INTO dia (codigo_dia) VALUES(254);
INSERT INTO dia (codigo_dia) VALUES(255);
INSERT INTO dia (codigo_dia) VALUES(256);
INSERT INTO dia (codigo_dia) VALUES(257);
INSERT INTO dia (codigo_dia) VALUES(258);
INSERT INTO dia (codigo_dia) VALUES(259);
INSERT INTO dia (codigo_dia) VALUES(260);
INSERT INTO dia (codigo_dia) VALUES(261);
INSERT INTO dia (codigo_dia) VALUES(262);
INSERT INTO dia (codigo_dia) VALUES(263);
INSERT INTO dia (codigo_dia) VALUES(264);
INSERT INTO dia (codigo_dia) VALUES(265);
INSERT INTO dia (codigo_dia) VALUES(266);
INSERT INTO dia (codigo_dia) VALUES(267);
INSERT INTO dia (codigo_dia) VALUES(268);
INSERT INTO dia (codigo_dia) VALUES(269);
INSERT INTO dia (codigo_dia) VALUES(270);
INSERT INTO dia (codigo_dia) VALUES(271);
INSERT INTO dia (codigo_dia) VALUES(272);
INSERT INTO dia (codigo_dia) VALUES(273);
INSERT INTO dia (codigo_dia) VALUES(274);
INSERT INTO dia (codigo_dia) VALUES(275);
INSERT INTO dia (codigo_dia) VALUES(276);
INSERT INTO dia (codigo_dia) VALUES(277);
INSERT INTO dia (codigo_dia) VALUES(278);
INSERT INTO dia (codigo_dia) VALUES(279);
INSERT INTO dia (codigo_dia) VALUES(280);
INSERT INTO dia (codigo_dia) VALUES(281);
INSERT INTO dia (codigo_dia) VALUES(282);
INSERT INTO dia (codigo_dia) VALUES(283);
INSERT INTO dia (codigo_dia) VALUES(284);
INSERT INTO dia (codigo_dia) VALUES(285);
INSERT INTO dia (codigo_dia) VALUES(286);
INSERT INTO dia (codigo_dia) VALUES(287);
INSERT INTO dia (codigo_dia) VALUES(288);
INSERT INTO dia (codigo_dia) VALUES(289);
INSERT INTO dia (codigo_dia) VALUES(290);
INSERT INTO dia (codigo_dia) VALUES(291);
INSERT INTO dia (codigo_dia) VALUES(292);
INSERT INTO dia (codigo_dia) VALUES(293);
INSERT INTO dia (codigo_dia) VALUES(294);
INSERT INTO dia (codigo_dia) VALUES(295);
INSERT INTO dia (codigo_dia) VALUES(296);
INSERT INTO dia (codigo_dia) VALUES(297);
INSERT INTO dia (codigo_dia) VALUES(298);
INSERT INTO dia (codigo_dia) VALUES(299);
INSERT INTO dia (codigo_dia) VALUES(300);
INSERT INTO dia (codigo_dia) VALUES(301);
INSERT INTO dia (codigo_dia) VALUES(302);
INSERT INTO dia (codigo_dia) VALUES(303);
INSERT INTO dia (codigo_dia) VALUES(304);
INSERT INTO dia (codigo_dia) VALUES(305);
INSERT INTO dia (codigo_dia) VALUES(306);
INSERT INTO dia (codigo_dia) VALUES(307);
INSERT INTO dia (codigo_dia) VALUES(308);
INSERT INTO dia (codigo_dia) VALUES(309);
INSERT INTO dia (codigo_dia) VALUES(310);
INSERT INTO dia (codigo_dia) VALUES(311);
INSERT INTO dia (codigo_dia) VALUES(312);
INSERT INTO dia (codigo_dia) VALUES(313);
INSERT INTO dia (codigo_dia) VALUES(314);
INSERT INTO dia (codigo_dia) VALUES(315);
INSERT INTO dia (codigo_dia) VALUES(316);
INSERT INTO dia (codigo_dia) VALUES(317);
INSERT INTO dia (codigo_dia) VALUES(318);
INSERT INTO dia (codigo_dia) VALUES(319);
INSERT INTO dia (codigo_dia) VALUES(320);
INSERT INTO dia (codigo_dia) VALUES(321);
INSERT INTO dia (codigo_dia) VALUES(322);
INSERT INTO dia (codigo_dia) VALUES(323);
INSERT INTO dia (codigo_dia) VALUES(324);
INSERT INTO dia (codigo_dia) VALUES(325);
INSERT INTO dia (codigo_dia) VALUES(326);
INSERT INTO dia (codigo_dia) VALUES(327);
INSERT INTO dia (codigo_dia) VALUES(328);
INSERT INTO dia (codigo_dia) VALUES(329);
INSERT INTO dia (codigo_dia) VALUES(330);
INSERT INTO dia (codigo_dia) VALUES(331);
INSERT INTO dia (codigo_dia) VALUES(332);
INSERT INTO dia (codigo_dia) VALUES(333);
INSERT INTO dia (codigo_dia) VALUES(334);
INSERT INTO dia (codigo_dia) VALUES(335);
INSERT INTO dia (codigo_dia) VALUES(336);
INSERT INTO dia (codigo_dia) VALUES(337);
INSERT INTO dia (codigo_dia) VALUES(338);
INSERT INTO dia (codigo_dia) VALUES(339);
INSERT INTO dia (codigo_dia) VALUES(340);
INSERT INTO dia (codigo_dia) VALUES(341);
INSERT INTO dia (codigo_dia) VALUES(342);
INSERT INTO dia (codigo_dia) VALUES(343);
INSERT INTO dia (codigo_dia) VALUES(344);
INSERT INTO dia (codigo_dia) VALUES(345);
INSERT INTO dia (codigo_dia) VALUES(346);
INSERT INTO dia (codigo_dia) VALUES(347);
INSERT INTO dia (codigo_dia) VALUES(348);
INSERT INTO dia (codigo_dia) VALUES(349);
INSERT INTO dia (codigo_dia) VALUES(350);
INSERT INTO dia (codigo_dia) VALUES(351);
INSERT INTO dia (codigo_dia) VALUES(352);
INSERT INTO dia (codigo_dia) VALUES(353);
INSERT INTO dia (codigo_dia) VALUES(354);
INSERT INTO dia (codigo_dia) VALUES(355);
INSERT INTO dia (codigo_dia) VALUES(356);
INSERT INTO dia (codigo_dia) VALUES(357);
INSERT INTO dia (codigo_dia) VALUES(358);
INSERT INTO dia (codigo_dia) VALUES(359);
INSERT INTO dia (codigo_dia) VALUES(360);
INSERT INTO dia (codigo_dia) VALUES(361);
INSERT INTO dia (codigo_dia) VALUES(362);
INSERT INTO dia (codigo_dia) VALUES(363);
INSERT INTO dia (codigo_dia) VALUES(364);
INSERT INTO dia (codigo_dia) VALUES(365);

/**
 * Tabla: tipo_red_neuronal.
 */
INSERT INTO tipo_red_neuronal (codigo_tipo_red_neuronal, descripcion_tipo_red_neuronal) VALUES (0, 'Back-Propagation (BP)');
INSERT INTO tipo_red_neuronal (codigo_tipo_red_neuronal, descripcion_tipo_red_neuronal) VALUES (1, 'Radial Base Fase (RBF)');

/**
 * Tabla: dato_economico_real.
 */
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "11", "8.4", "2.5");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "51", "8.5", "1.7");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "65", "8.7", "1.3");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "70", "8.8", "1.5");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "77", "9.1", "1.7");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "100", "9", "2");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "137", "8.7", "2.2");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "140", "8.6", "2.4");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "150", "8.4", "2.47");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "163", "8.5", "2.48");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "190", "8.55", "2.51");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "215", "8.6", "2.58");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "250", "8.2", "2.88");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "303", "8.08", "3.21");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "330", "8.06", "3.3");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "343", "8.08", "3.6");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "350", "8", "3.4");
INSERT INTO dato_economico_real VALUES("0", "0", "2002", "357", "7.9", "3.1");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "7", "8.1", "2.8");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "46", "8.45", "1.8");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "60", "8.5", "1.2");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "71", "9", "1.5");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "91", "9", "1.8");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "111", "8.75", "2.75");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "127", "8.5", "2.55");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "154", "8.5", "2.52");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "235", "8.7", "2.61");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "270", "8.4", "3");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "291", "8.11", "3.1");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "313", "8.04", "3.3");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "320", "8", "3.5");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "349", "7.95", "3.846");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "359", "7.78", "3.2");
INSERT INTO dato_economico_real VALUES("0", "0", "2003", "363", "7.74", "3.1");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "3", "8.1", "2.8");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "40", "8.31", "1.8");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "64", "8.5", "1.05");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "71", "9.14", "1.32");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "91", "9", "1.8");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "110", "8.75", "2.92");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "130", "8.4", "2.52");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "160", "8.31", "2.4");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "240", "8.7", "2.55");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "270", "8.4", "3");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "298", "8.11", "3.1");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "313", "7.94", "3.3");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "320", "7.75", "3.69");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "350", "7.75", "4.03");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "357", "7.48", "3.26");
INSERT INTO dato_economico_real VALUES("0", "0", "2004", "362", "7.43", "3.1");

/**
 * Tabla: dato_ecosistemico_real.
 */
INSERT INTO dato_ecosistemico_real VALUES("0", "0", "2002", "1", "9620788808", "0.4079", "0.5921", "0.3851", "0.6149", "2.33", "2.28", "2.39", "2.14", "2.64", "6408.4", "6.19", "5.28", "7.1", "4.61", "9.6");
INSERT INTO dato_ecosistemico_real VALUES("0", "0", "2003", "1", "428175003", "0.3432", "0.6568", "0.15", "0.85", "2.5", "2.37", "2.57", "2.27", "2.65", "2992", "6.99", "6", "7.5", "7", "8");
INSERT INTO dato_ecosistemico_real VALUES("0", "0", "2004", "1", "1039026169", "0.404", "0.596", "0.187", "0.813", "2.56", "2.48", "2.64", "2.35", "2.69", "6325.8", "6.09", "5.97", "6.17", "5.7", "6.98");
INSERT INTO dato_ecosistemico_real VALUES("1", "0", "2002", "1", "159917024", "0.68", "0.32", "0.084", "0.916", "2.72", "2.76", "2.64", "2.64", "2.64", "2340", "13.58", "14.64", "10.9", "10.9", "10.9");
INSERT INTO dato_ecosistemico_real VALUES("1", "0", "2003", "1", "313672285", "0.4879", "0.5121", "0.3", "0.7", "2.82", "2.96", "2.69", "2.36", "2.73", "5985.1", "9.245", "10.99", "7.5", "7", "8");
INSERT INTO dato_ecosistemico_real VALUES("1", "0", "2004", "1", "171726969", "0.485", "0.515", "0.205", "0.795", "2.66", "2.73", "2.59", "2.15", "2.7", "2125", "12.87", "14.12", "11.02", "6.22", "12.53");
INSERT INTO dato_ecosistemico_real VALUES("2", "0", "2002", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
INSERT INTO dato_ecosistemico_real VALUES("2", "0", "2003", "1", "145018393", "0.56", "0.44", "0.2", "0.8", "2.43", "2.55", "2.32", "2.35", "2.31", "1471", "8.275", "9.05", "7.5", "7", "8");
INSERT INTO dato_ecosistemico_real VALUES("2", "0", "2004", "1", "92734525", "0.836", "0.164", "0.018", "0.982", "2.73", "2.74", "2.65", "2.41", "2.65", "1063", "11.8", "11.1", "9.2", "6.82", "9.2");
INSERT INTO dato_ecosistemico_real VALUES("3", "0", "2002", "1", "405318638", "0.4292", "0.5708", "0.25", "0.75", "44.68", "40.27", "49.1", "49", "49.2", "210665.8", "627.58", "507.23", "747.93", "720", "775.86");
INSERT INTO dato_ecosistemico_real VALUES("3", "0", "2003", "1", "406318638", "0.4292", "0.5708", "0.25", "0.75", "44.68", "40.27", "49.1", "49", "49.2", "210665.8", "627.58", "507.23", "747.93", "720", "775.86");
INSERT INTO dato_ecosistemico_real VALUES("3", "0", "2004", "1", "406318638", "0.4292", "0.5708", "0.25", "0.75", "44.68", "40.27", "49.1", "49", "49.2", "210665.8", "627.58", "507.23", "747.93", "720", "775.86");

/**
 * Tabla: caladero_real.
 */
INSERT INTO caladero_real VALUES("0", "0", "0", "2002", "1", "1", "1", "29.5", "29.7", "125", "552368518", "3558.3");
INSERT INTO caladero_real VALUES("1", "0", "0", "2002", "1", "1", "1", "29.767", "30.1834", "299.8", "117246200", "806.2");
INSERT INTO caladero_real VALUES("2", "0", "0", "2002", "1", "1", "1", "30.234", "31.067", "256.2", "222829400", "1524.6");
INSERT INTO caladero_real VALUES("3", "0", "0", "2002", "1", "1", "1", "31.434", "32.25", "506.2", "69634690", "519.3");
INSERT INTO caladero_real VALUES("4", "1", "0", "2002", "1", "1", "1", "29.5", "30.05", "184", "36063857", "637");
INSERT INTO caladero_real VALUES("5", "1", "0", "2002", "1", "1", "1", "30.3834", "32.167", "528.7", "123853167", "1703");
INSERT INTO caladero_real VALUES("6", "3", "0", "2002", "1", "1", "1", "29.5", "31.04", "667.1", "120373334", "73381");
INSERT INTO caladero_real VALUES("7", "3", "0", "2002", "1", "1", "1", "31.417", "32.25", "596.9", "285945304", "137284.8");
INSERT INTO caladero_real VALUES("8", "0", "0", "2003", "1", "1", "1", "29.5", "32.25", "2407", "428175003", "2992");
INSERT INTO caladero_real VALUES("9", "1", "0", "2003", "1", "1", "1", "29.5", "29.82", "190.24", "120163053", "2345.6");
INSERT INTO caladero_real VALUES("10", "1", "0", "2003", "1", "1", "1", "30.25", "31.75", "657.34", "105180157", "2106");
INSERT INTO caladero_real VALUES("11", "1", "0", "2003", "1", "1", "1", "31.95", "32.25", "136.33", "88329075", "1533.5");
INSERT INTO caladero_real VALUES("12", "2", "0", "2003", "1", "1", "1", "29.5", "29.88", "216.1", "145018393", "1471");
INSERT INTO caladero_real VALUES("13", "3", "0", "2003", "1", "1", "1", "29.5", "31.04", "667.1", "120373334", "73381");
INSERT INTO caladero_real VALUES("14", "3", "0", "2003", "1", "1", "1", "31.417", "32.25", "596.9", "285945304", "137284.8");
INSERT INTO caladero_real VALUES("15", "0", "0", "2004", "1", "1", "1", "29.5", "32.25", "1083", "1039026169", "6325.8");
INSERT INTO caladero_real VALUES("16", "1", "0", "2004", "1", "1", "1", "29.78", "29.88", "27.7", "19285242", "262");
INSERT INTO caladero_real VALUES("17", "1", "0", "2004", "1", "1", "1", "30.33", "32", "737", "152441727", "1863");
INSERT INTO caladero_real VALUES("18", "2", "0", "2004", "1", "1", "1", "29.5", "29.88", "168.1", "92734525", "1063");
INSERT INTO caladero_real VALUES("19", "3", "0", "2004", "1", "1", "1", "29.5", "31.4", "667.1", "120373334", "73381");
INSERT INTO caladero_real VALUES("20", "3", "0", "2004", "1", "1", "1", "31.42", "32.25", "596.9", "285945304", "137284.8");

/**
 * Tabla: coordenada.
 */
INSERT INTO coordenada (codigo_coordenada, codigo_region, codigo_longitud, codigo_latitud, longitud_coordenada, latitud_coordenada, altitud_coordenada) VALUES (0, 0, 1, 1, 70.50, 29.30, 0);
INSERT INTO coordenada (codigo_coordenada, codigo_region, codigo_longitud, codigo_latitud, longitud_coordenada, latitud_coordenada, altitud_coordenada) VALUES (1, 0, 1, 1, 70.50, 29.40, 0);
INSERT INTO coordenada (codigo_coordenada, codigo_region, codigo_longitud, codigo_latitud, longitud_coordenada, latitud_coordenada, altitud_coordenada) VALUES (2, 0, 1, 1, 70.50, 29.50, 0);
INSERT INTO coordenada (codigo_coordenada, codigo_region, codigo_longitud, codigo_latitud, longitud_coordenada, latitud_coordenada, altitud_coordenada) VALUES (3, 0, 1, 1, 70.46, 30.00, 0);
INSERT INTO coordenada (codigo_coordenada, codigo_region, codigo_longitud, codigo_latitud, longitud_coordenada, latitud_coordenada, altitud_coordenada) VALUES (4, 0, 1, 1, 70.50, 30.20, 0);

/**
 * Tabla: tipo_caladero_resultado.
 */
INSERT INTO tipo_caladero_resultado (codigo_tipo_caladero_resultado, descripcion_tipo_caladero_resultado) VALUES (0, 'Artesanal');
INSERT INTO tipo_caladero_resultado (codigo_tipo_caladero_resultado, descripcion_tipo_caladero_resultado) VALUES (1, 'Industrial');

/**
 * Tabla: vista.
 */
INSERT INTO vista (codigo_vista, descripcion_vista) VALUES (0, 'Longitud-Latitud');
INSERT INTO vista (codigo_vista, descripcion_vista) VALUES (1, 'Longitud-Altitud');
INSERT INTO vista (codigo_vista, descripcion_vista) VALUES (2, 'Latitud-Altitud');

/**
 * Tabla: configuracion_espacial.
 */
INSERT INTO configuracion_espacial (codigo_proyecto, codigo_vista, codigo_longitud_inicial, codigo_longitud_final, codigo_latitud_inicial, codigo_latitud_final, longitud_inicial_configuracion_espacial, longitud_final_configuracion_espacial, latitud_inicial_configuracion_espacial, latitud_final_configuracion_espacial, altitud_inicial_configuracion_espacial, altitud_final_configuracion_espacial) VALUES (1, 0, 1, 1, 1, 1, 71.25, 72.5, 29.5, 30.45, -1000, 0);

/**
 * Tabla: limite.
 */
INSERT INTO limite (codigo_limite, descripcion_limite) VALUES (0, 'Finito');
INSERT INTO limite (codigo_limite, descripcion_limite) VALUES (1, 'Infinito');

/**
 * Tabla: vecindad.
 */
INSERT INTO vecindad (codigo_vecindad, descripcion_vecindad) VALUES (0, 'Von Neumann');
INSERT INTO vecindad (codigo_vecindad, descripcion_vecindad) VALUES (1, 'Moore');

/**
 * Tabla: tiempo.
 */
INSERT INTO tiempo (codigo_tiempo, descripcion_tiempo) VALUES (0, 'Segundo');
INSERT INTO tiempo (codigo_tiempo, descripcion_tiempo) VALUES (1, 'Minuto');
INSERT INTO tiempo (codigo_tiempo, descripcion_tiempo) VALUES (2, 'Hora');
INSERT INTO tiempo (codigo_tiempo, descripcion_tiempo) VALUES (3, 'Día');

/**
 * Tabla: configuracion_ac.
 */
INSERT INTO configuracion_ac (codigo_proyecto, codigo_limite, codigo_vecindad, codigo_tiempo) VALUES (1, 1, 1, 3);

/**
 * Tabla: tipo_calidad.
 */
INSERT INTO tipo_calidad (codigo_tipo_calidad, descripcion_tipo_calidad, valor_tipo_calidad) VALUES (0, 'Muy Baja', 0);
INSERT INTO tipo_calidad (codigo_tipo_calidad, descripcion_tipo_calidad, valor_tipo_calidad) VALUES (1, 'Baja', 0.25);
INSERT INTO tipo_calidad (codigo_tipo_calidad, descripcion_tipo_calidad, valor_tipo_calidad) VALUES (2, 'Media', 0.5);
INSERT INTO tipo_calidad (codigo_tipo_calidad, descripcion_tipo_calidad, valor_tipo_calidad) VALUES (3, 'Alta', 0.75);
INSERT INTO tipo_calidad (codigo_tipo_calidad, descripcion_tipo_calidad, valor_tipo_calidad) VALUES (4, 'Muy Alta', 1);

/**
 * Tabla: configuracion_recurso.
 */
INSERT INTO configuracion_recurso VALUES("1", "0", "2", "Si", "Si", "0");
INSERT INTO configuracion_recurso VALUES("1", "1", "2", "Si", "Si", "0");
INSERT INTO configuracion_recurso VALUES("1", "2", "2", "Si", "Si", "0");
INSERT INTO configuracion_recurso VALUES("1", "3", "2", "Si", "Si", "0");

/**
 * Tabla: funcion_evaluacion.
 */
INSERT INTO funcion_evaluacion (codigo_funcion_evaluacion, descripcion_funcion_evaluacion) VALUES (0, 'Lineal');
INSERT INTO funcion_evaluacion (codigo_funcion_evaluacion, descripcion_funcion_evaluacion) VALUES (1, 'Lineal a Trozos');
INSERT INTO funcion_evaluacion (codigo_funcion_evaluacion, descripcion_funcion_evaluacion) VALUES (2, 'Razonable');

/**
 * Tabla: tecnica_seleccion.
 */
INSERT INTO tecnica_seleccion (codigo_tecnica_seleccion, descripcion_tecnica_seleccion) VALUES (0, 'Proporcional');
INSERT INTO tecnica_seleccion (codigo_tecnica_seleccion, descripcion_tecnica_seleccion) VALUES (1, 'Torneo');

/**
 * Tabla: configuracion_agt.
 */
INSERT INTO configuracion_agt (codigo_proyecto, codigo_funcion_evaluacion, codigo_tecnica_seleccion, tamanio_poblacion_configuracion_agt, numero_generaciones_configuracion_agt, probabilidad_cruce_configuracion_agt, probabilidad_mutacion_configuracion_agt) VALUES (1, 1, 1, 20, 500, 0.15, 0.4);

/**
 * Tabla: configuracion_agr.
 */
INSERT INTO configuracion_agr (codigo_proyecto, codigo_tecnica_seleccion, tamanio_poblacion_configuracion_agr, numero_generaciones_configuracion_agr, probabilidad_cruce_configuracion_agr, probabilidad_mutacion_configuracion_agr) VALUES (1, 1, 20, 100, 0.5, 0.05);

/**
 * Tabla: punto_demanda.
 */
INSERT INTO punto_demanda (codigo_punto_demanda, codigo_empresa, codigo_coordenada, descripcion_punto_demanda) VALUES (0, 0, 0, 'Pesca Marina');

/**
 * Tabla: medio_transporte.
 */
INSERT INTO medio_transporte (codigo_medio_transporte, codigo_empresa, descripcion_medio_transporte, capacidad_medio_transporte, potencia_motor_medio_transporte, eslora_medio_transporte) VALUES (0, 0, 'Isla Lenox', 81, 365, 21.87);
INSERT INTO medio_transporte (codigo_medio_transporte, codigo_empresa, descripcion_medio_transporte, capacidad_medio_transporte, potencia_motor_medio_transporte, eslora_medio_transporte) VALUES (1, 0, 'Gringo', 140, 360, 22.36);
INSERT INTO medio_transporte (codigo_medio_transporte, codigo_empresa, descripcion_medio_transporte, capacidad_medio_transporte, potencia_motor_medio_transporte, eslora_medio_transporte) VALUES (2, 0, 'Polux', 138, 365, 22.41);

/**
 * Tabla: tipo_heuristica.
 */
INSERT INTO tipo_heuristica (codigo_tipo_heuristica, descripcion_tipo_heuristica) VALUES (0, 'Vender a Mayor Oferta');
INSERT INTO tipo_heuristica (codigo_tipo_heuristica, descripcion_tipo_heuristica) VALUES (1, 'Vender a Mayor Demanda');
INSERT INTO tipo_heuristica (codigo_tipo_heuristica, descripcion_tipo_heuristica) VALUES (2, 'Vender a Mayor Precio');

/**
 * Tabla: configuracion_heuristica.
 */
INSERT INTO configuracion_heuristica (codigo_proyecto, codigo_tipo_heuristica, codigo_tipo_red_neuronal, stock_acumulado_configuracion_heuristica) VALUES (1, 2, 0, 0);

/**
 * Borrar el usuario circanapro del HOST localhost.
 */
DELETE FROM mysql.user WHERE Host='localhost' AND User='circanapro';
DELETE FROM mysql.db WHERE Host='localhost' AND User='circanapro';
DELETE FROM mysql.tables_priv WHERE Host='localhost' AND User='circanapro';
DELETE FROM mysql.columns_priv WHERE Host='localhost' AND User='circanapro';

/**
 * Agregar el usuario circanapro al HOST localhost.
 */
GRANT ALL PRIVILEGES ON circanapro.* TO 'circanapro'@'localhost' IDENTIFIED BY 'circanaprokey' WITH GRANT OPTION;

/**
 * Borrar el usuario circanapro del HOST %.
 */
DELETE FROM mysql.user WHERE Host='%' AND User='circanapro';
DELETE FROM mysql.db WHERE Host='%' AND User='circanapro';
DELETE FROM mysql.tables_priv WHERE Host='%' AND User='circanapro';
DELETE FROM mysql.columns_priv WHERE Host='%' AND User='circanapro';

/**
 * Agregar el usuario circanapro al HOST %.
 */
GRANT ALL PRIVILEGES ON circanapro.* TO 'circanapro'@'%' IDENTIFIED BY 'circanaprokey' WITH GRANT OPTION;

/**
 * Actualizar los privilegios.
 */
FLUSH PRIVILEGES;
