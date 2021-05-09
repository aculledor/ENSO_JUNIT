package SubsistemaIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Test_P9_CrearIncidencia {

	private GestorIncidencias GI;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		GI = new GestorIncidencias();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test_P9_CrearIncidencia: caminos v�lidos (CP109-CP110, CP114-CP117)")
	void P9_CrearIncidencia_CV() throws Exception {
		// Arrange
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = formato.parse("03/03/2021");
		
		// Act
		assertAll(() -> {
			assertDoesNotThrow(() -> {
				GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "descripcion", "Santiago","Suministro");
				}, "(CP109) La creaci�n de la incidencia con valores v�lidos y caso: "
					+ "(Tipo = 'Suministro') lanza una excepci�n.");
				},
				() -> {
					assertDoesNotThrow(() -> {
						GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "descripcion","Santiago", "Limpieza");
					}, "(CP114) La creaci�n de la incidencia con valores v�lidos y caso: "
							+ "(Tipo = 'Limpieza') lanza una excepci�n.");
				},
				() -> {
					assertDoesNotThrow(() -> {
						GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "descripcion", "Santiago","Urban�stica");
						}, "(CP115) La creaci�n de la incidencia con valores v�lidos y caso: "
							+ "(Tipo = 'Urban�stica') lanza una excepci�n.");
				},
				() -> {
					assertDoesNotThrow(() -> {
						GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "descripcion","Santiago", "Legislativa");
					}, "(CP116) La creaci�n de la incidencia con valores v�lidos y caso: "
							+ "(Tipo = 'Legislativa') lanza una excepci�n.");
				},
				() -> {
					assertDoesNotThrow(() -> {
						GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "descripcion","Santiago", "Otros");
					}, "(CP117) La creaci�n de la incidencia con valores v�lidos y caso: "
							+ "(Tipo = 'Otros') lanza una excepci�n.");
				},
				() -> {
					assertDoesNotThrow(() -> {
						GI.crearIncidencia("Jose maria", "49665809T", "12345678901234567890123456789012345678", fechaDate, "descripcion","Santiago", "Otros");
					}, "(CP110) La creaci�n de la incidencia con valores v�lidos y caso: "
							+ "(n-1 iteraciones del bucle for) lanza una excepci�n.");
				}
				);
				
	}
	@Test
	@DisplayName("Test_P9_CrearIncidencia: caminos no v�lidos (CP095-CP108, CP111-CP113)")
	void P9_CrearIncidencia_CNV() throws Exception {
		// Arrange
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = formato.parse("03/03/2021");
		
		// Act
		assertAll(
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia(null, null, null, null, null, null,null);
					}, "(CP095) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(ciudadano = null) no lanza una excepci�n.");
					assertEquals("el ciudadano introducido es null", ex.getMessage(),
							"(CP095) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(ciudadano = null) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", null, null, null, null, null,null);
					}, "(CP096) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(dni = null) no lanza una excepci�n.");
					assertEquals("el dni introducido es null", ex.getMessage(),
							"(CP096) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(dni = null) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", null, null, null, null,null);
					}, "(CP097) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(telefono = null) no lanza una excepci�n.");
					assertEquals("el telefono introducido es null", ex.getMessage(),
							"(CP097) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(telefono = null) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "123123123", null, null, null,null);
					}, "(CP098) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(fecha = null) no lanza una excepci�n.");
					assertEquals("la fecha introducida es null", ex.getMessage(),
							"(CP098) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(fecha = null) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "123123123", fechaDate, null, null,null);
					}, "(CP099) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(descripcion = null) no lanza una excepci�n.");
					assertEquals("la descripci�n introducida es null", ex.getMessage(),
							"(CP099) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(descripcion = null) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "123123123", fechaDate, "descripcion", null,null);
					}, "(CP100) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(localizacion = null) no lanza una excepci�n.");
					assertEquals("la localizacion introducida es null", ex.getMessage(),
							"(CP100) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(localizacion = null) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "123123123", fechaDate, "descripcion", "Santiago",null);
					}, "(CP101) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(tipo = null) no lanza una excepci�n.");
					assertEquals("el tipo introducido es null", ex.getMessage(),
							"(CP101) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(tipo = null) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("JoseMariaIglesiasMartinJorgeTomasFernandez", "49665809T", "123123123", fechaDate, "descripcion", "Santiago","Suministro");
					}, "(CP102) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(nombre > 40) no lanza una excepci�n.");
					assertEquals("nombre de ciudadano demasiado largo", ex.getMessage(),
							"(CP102) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(nomnbre > 40) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("", "49665809T", "123123123", fechaDate, "descripcion", "Santiago","Suministro");
					}, "(CP103) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(nombre < 1) no lanza una excepci�n.");
					assertEquals("nombre de ciudadano demasiado largo", ex.getMessage(),
							"(CP103) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(nomnbre < 1) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809E", "123123123", fechaDate, "descripcion", "Santiago","Suministro");
					}, "(CP104) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(dni no valido) no lanza una excepci�n.");
					assertEquals("dni introducido no valido", ex.getMessage(),
							"(CP104) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(dni no valido) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "123", fechaDate, "descripcion", "Santiago","Suministro");
					}, "(CP105) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(telefono.length() < 9) no lanza una excepci�n.");
					assertEquals("longitud de numero de tel�fono no v�lida", ex.getMessage(),
							"(CP105) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(telefono.length() < 9) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "00987654321098765432109876543210987654321", fechaDate, "descripcion", "Santiago","Suministro");
					}, "(CP106) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(telefono.length() > 40) no lanza una excepci�n.");
					assertEquals("longitud de numero de tel�fono no v�lida", ex.getMessage(),
							"(CP106) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(telefono.length() > 40) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "a33998833", fechaDate, "descripcion", "Santiago","Suministro");
					}, "(CP107) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(1 iteracion del bucle) no lanza una excepci�n.");
					assertEquals("formato del numero de telefono no v�lido", ex.getMessage(),
							"(CP107) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(1 iteracion del bucle) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "3p3998833", fechaDate, "descripcion", "Santiago","Suministro");
					}, "(CP108) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(2 iteraciones del bucle for) no lanza una excepci�n.");
					assertEquals("formato del numero de telefono no v�lido", ex.getMessage(),
							"(CP108) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(2 iteraciones del bucle for) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "Descripci�n de proba que excede o tama�o m�ximo de palabras aceptado. Asdasdasdasdasdasdasdasdas"
								+ "dasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasda sdasdasdasdasdasdasdasdasdasdasdas"
								+ "dasdasdasdasdasdasdasdasdasdasdasdas dasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd", 
								"Santiago","Suministro");
					}, "(CP111) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(descripcion.length() > 400) no lanza una excepci�n.");
					assertEquals("descripcion demasiado larga", ex.getMessage(),
							"(CP111) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(descripcion.length() > 400) tiene un mensaje de excepci�n err�neo.");
					},
				
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "descripcion",
								"SantiagodeCompostelaGalciiaEspa�aEuropaSantiagodeCompostelaGalciiaEspa�aEuropaSantiagodeCompostelaGalciiaEspa�aEuropa","Suministro");
					}, "(CP112) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(localizacion.length() > 100) no lanza una excepci�n.");
					assertEquals("localizacion demasiado larga", ex.getMessage(),
							"(CP112) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(localizacion.length() > 100) tiene un mensaje de excepci�n err�neo.");
					},
				() -> {
					Exception ex = assertThrows(Exception.class, () -> {
						GI.crearIncidencia("Jose maria", "49665809T", "693633448", fechaDate, "descripcion",
								"Santiago","Vecindario");
					}, "(CP113) La creaci�n de la incidencia con valores no v�lidos y caso: "
							+ "(tipo = Vecindario) no lanza una excepci�n.");
					assertEquals("tipo no v�lido", ex.getMessage(),
							"(CP113) La creaci�n del proceso con valores no v�lidos y caso: "
					+ "(tipo = Vecindario) tiene un mensaje de excepci�n err�neo.");
					}
				
				);
				
	}

}