package SubsistemaGestionProcesos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import SubsistemaIncidencias.*;
import SubsistemaGestionOrdenesTrabajo.*;

class Test_P1_CrearOT_CrearProceso {
	
	private GestorIncidencias GI;
	private GestorOT GOT;
	private GestorProcesos GP;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		GI = Mockito.mock(GestorIncidencias.class);
		GOT = Mockito.mock(GestorOT.class);
		GP = new GestorProcesos(GI, GOT);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("Funcionalidad")
	class Funcionalidad{
		@Test
		@DisplayName("Test_P1_CrearOT_CrearProceso: Casos v�lidos (CP025 - CP029)")
		void P1_CrearOT_CrearProceso_CV() throws Exception {	
			// Arrange
			
			ArrayList<Incidencia> simIncidencias = new ArrayList<>();
			ArrayList<OrdenTrabajo> simOOTT = new ArrayList<>();
			
			// Ya que las incidencias y OOTT no pueden estar previamente asignadas, para poder concentrar todos los casos de prueba en
			// una �nica prueba, se crear�n 8 incidencias y OOTT nuevas y se asignar�n de 2 en 2 a cada caso de prueba.
			for(int i = 1; i <= 10; i++) {
				simIncidencias.add(new Incidencia(i, null, null, null, null, null, null, null));
				simOOTT.add(new OrdenTrabajo(i, null, null, null, null, null, null, null, null, null));
			}
				
			Mockito.when(GI.getIncidencias()).thenReturn(simIncidencias);
			Mockito.when(GOT.getOrdenesTrabajo()).thenReturn(simOOTT);
			
			// Act + Assert
			
			assertAll(
					()->{assertDoesNotThrow(()->{GP.crearProceso("proceso", "sin comenzar", "responsable", "Suministro", "Texto de ejemplo",
							100.87f, new ArrayList<>(List.of(1,2)), new ArrayList<>(List.of(1,2)));},
							"(CP025) La creaci�n del proceso con valores v�lidos y caso: "
							+ "(Estado = 'sin comenzar', Servicio = 'Suministro') lanza una excepci�n.");},
					
					()->{assertDoesNotThrow(()->{GP.crearProceso("proceso", "planificado", "responsable", "Limpieza", "Texto de ejemplo",
							100.87f, new ArrayList<>(List.of(3,4)), new ArrayList<>(List.of(3,4)));},
							"(CP026) La creaci�n del proceso con valores v�lidos y caso: "
							+ "(Estado = 'planificado', Servicio = 'Limpieza') lanza una excepci�n.");},
					
					()->{assertDoesNotThrow(()->{GP.crearProceso("proceso", "en construcci�n", "responsable", "Urban�stica", "Texto de ejemplo",
							100.87f, new ArrayList<>(List.of(5,6)), new ArrayList<>(List.of(5,6)));},
							"(CP027) La creaci�n del proceso con valores v�lidos y caso: "
							+ "(Estado = 'en construcci�n', Servicio = 'Urban�stica') lanza una excepci�n.");},
					
					()->{assertDoesNotThrow(()->{GP.crearProceso("proceso", "finalizado", "responsable", "Legalidad", "Texto de ejemplo",
							100.87f, new ArrayList<>(List.of(7,8)), new ArrayList<>(List.of(7,8)));},
							"(CP028) La creaci�n del proceso con valores v�lidos y caso: "
							+ "(Estado = 'finalizado', Servicio = 'Legalidad') lanza una excepci�n.");},
					
					()->{assertDoesNotThrow(()->{GP.crearProceso("proceso", "finalizado", "responsable", "Otros", "Texto de ejemplo",
							100.87f, new ArrayList<>(List.of(9,10)), new ArrayList<>(List.of(9,10)));},
							"(CP029) La creaci�n del proceso con valores v�lidos y caso: "
							+ "(Estado = 'finalizado', Servicio = 'Otros') lanza una excepci�n.");}
					);			
		}
		
		@Test
		@DisplayName("Test_P1_CrearOT_CrearProceso: Casos no v�lidos (CP030 - CP045)")
		void P1_CrearOT_CrearProceso_CNV() throws Exception {	
			// Arrange
			
			ArrayList<Integer> incidencias = new ArrayList<>(List.of(1,2));
			ArrayList<Incidencia> simIncidencias = new ArrayList<>();
			simIncidencias.add(new Incidencia(1, null, null, null, null, null, null, null));
			simIncidencias.add(new Incidencia(2, null, null, null, null, null, null, null));
			Mockito.when(GI.getIncidencias()).thenReturn(simIncidencias);
			
			ArrayList<Integer> OOTT = new ArrayList<>(List.of(1,2));
			ArrayList<OrdenTrabajo> simOOTT = new ArrayList<>();
			simOOTT.add(new OrdenTrabajo(1, null, null, null, null, null, null, null, null, null));
			simOOTT.add(new OrdenTrabajo(2, null, null, null, null, null, null, null, null, null));
			Mockito.when(GOT.getOrdenesTrabajo()).thenReturn(simOOTT);
			
			// Act + Assert
			
			assertAll(
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("procesoProcesoProceso", "sin comenzar", 
							"responsable", "Suministro", "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP030) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(nombre.length > 20) no lanza una excepci�n.");
						assertEquals("nombre demasiado largo", e.getMessage(),
								"(CP030) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(nombre.length > 20) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso(null, "sin comenzar", 
							"responsable", "Suministro", "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP031) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(nombre = null) no lanza una excepci�n.");
						assertEquals("ning�n campo excepto las �rdenes de trabajo puede ser nulo", e.getMessage(),
								"(CP031) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(nombre = null) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", null, 
							"responsable", "Suministro", "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP032) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(estado = null) no lanza una excepci�n.");
						assertEquals("ning�n campo excepto las �rdenes de trabajo puede ser nulo", e.getMessage(),
								"(CP032) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(estado = null) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "Realizado",
							"responsable", "Suministro", "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP033) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(estado no v�lido) no lanza una excepci�n.");
						assertEquals("estado incorrecto", e.getMessage(),
								"(CP033) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(estado no v�lido) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsableResponsableResponsableResponsa", "Suministro", "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP034) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(responsable.length > 40) no lanza una excepci�n.");
						assertEquals("responsable demasiado largo", e.getMessage(),
								"(CP034) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(responsable.length > 40) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							null, "Suministro", "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP035) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(responsable = null) no lanza una excepci�n.");
						assertEquals("ning�n campo excepto las �rdenes de trabajo puede ser nulo", e.getMessage(),
								"(CP035) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(responsable = null) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", null, "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP036) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(servicio = null) no lanza una excepci�n.");
						assertEquals("ning�n campo excepto las �rdenes de trabajo puede ser nulo", e.getMessage(),
								"(CP036) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(servicio = null) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Derecho", "Texto de ejemplo", 100.87f, incidencias, OOTT);},
							"(CP037) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(servicio no v�lido) no lanza una excepci�n.");
						assertEquals("servicio incorrecto", e.getMessage(),
								"(CP037) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(servicio no v�lido) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcab", 100.87f, incidencias, OOTT);},
							"(CP038) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(descripci�n.length > 400) no lanza una excepci�n.");
						assertEquals("descripci�n demasiado larga", e.getMessage(),
								"(CP038) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(descripci�n.length > 400) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", null, 100.87f, incidencias, OOTT);},
							"(CP039) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(descripci�n = null) no lanza una excepci�n.");
						assertEquals("ning�n campo excepto las �rdenes de trabajo puede ser nulo", e.getMessage(),
								"(CP039) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(descripci�n = null) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", "Texto de ejemplo", -324.87f, incidencias, OOTT);},
							"(CP040) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(coste negativo) no lanza una excepci�n.");
						assertEquals("el coste debe ser un n�mero positivo", e.getMessage(),
								"(CP040) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(coste negativo) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", "Texto de ejemplo", null, incidencias, OOTT);},
							"(CP041) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(coste = null) no lanza una excepci�n.");
						assertEquals("ning�n campo excepto las �rdenes de trabajo puede ser nulo", e.getMessage(),
								"(CP041) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(coste = null) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", "Texto de ejemplo", 100f, null, OOTT);},
							"(CP042) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(incidencias = null) no lanza una excepci�n.");
						assertEquals("ning�n campo excepto las �rdenes de trabajo puede ser nulo", e.getMessage(),
								"(CP042) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(incidencias = null) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", "Texto de ejemplo", 100f, new ArrayList<Integer>(), OOTT);},
							"(CP043) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(incidencias vac�o) no lanza una excepci�n.");
						assertEquals("se debe vincular al menos una incidencia", e.getMessage(),
								"(CP043) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(incidencias vac�o) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", "Texto de ejemplo", 100f, new ArrayList<>(List.of(1,-2)), OOTT);},
							"(CP044) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(incidencias negativas) no lanza una excepci�n.");
						assertEquals("incidencia no existente", e.getMessage(),
								"(CP044) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(incidencias negativas) tiene un mensaje de excepci�n err�neo.");
						},
					()->{Exception e = assertThrows(Exception.class, ()->{GP.crearProceso("proceso", "finalizado",
							"responsable", "Otros", "Texto de ejemplo", 100f, incidencias, new ArrayList<>(List.of(1,-2)));},
							"(CP045) La creaci�n del proceso con valores no v�lidos y caso: "
							+ "(OOTT negativas) no lanza una excepci�n.");
						assertEquals("orden de trabajo no existente", e.getMessage(),
								"(CP045) La creaci�n del proceso con valores no v�lidos y caso: "
								+ "(OOTT negativas) tiene un mensaje de excepci�n err�neo.");
						}
					);			
		}
	}
	
	@Nested
	@DisplayName("Rendimiento")
	class Rendimiento{
		@Test
		@DisplayName("Test_P1_CrearOT_CrearProceso: Prueba de rendimiento (Caso v�lido CP025)")
		void P1_CrearOT_CrearProceso_CV() {
			// Arrange
			
			ArrayList<Integer> incidencias = new ArrayList<>(List.of(1,2));
			ArrayList<Incidencia> simIncidencias = new ArrayList<>();
			simIncidencias.add(new Incidencia(1, null, null, null, null, null, null, null));
			simIncidencias.add(new Incidencia(2, null, null, null, null, null, null, null));
			Mockito.when(GI.getIncidencias()).thenReturn(simIncidencias);
			
			ArrayList<Integer> OOTT = new ArrayList<>(List.of(1,2));
			ArrayList<OrdenTrabajo> simOOTT = new ArrayList<>();
			simOOTT.add(new OrdenTrabajo(1, null, null, null, null, null, null, null, null, null));
			simOOTT.add(new OrdenTrabajo(2, null, null, null, null, null, null, null, null, null));
			Mockito.when(GOT.getOrdenesTrabajo()).thenReturn(simOOTT);
			
			//Act + Assert
			
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{GP.crearProceso("proceso", "sin comenzar", "responsable", "Suministro", "Texto de ejemplo",
					100.87f, incidencias, OOTT);},
					"(CP025) La creaci�n del proceso supera el l�mite temporal de respuesta requerido (2s).");
		}
	}
	
}
