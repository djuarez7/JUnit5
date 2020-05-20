package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
	
	private String cadena;

	@BeforeAll
	static void beforeAll(TestInfo info) {
		System.out.println("Abriendo conexión a bd " + info.getDisplayName());
	}

	@AfterAll
	static void afterAll(TestInfo info) {
		System.out.println("Cerrando conexión a bd " + info.getDisplayName());
	}

	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Inicio de prueba unitaria de método " + info.getDisplayName());
	}

	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("Fin de prueba unitaria de método " + info.getDisplayName());
	}

	@Test
	@DisplayName("Prueba unitaria DisplayName")
	void lengthBasic() {
//		fail("Not yet implemented");

		int length = "ABCD".length();
		int expectedLength = 4;

		assertEquals(expectedLength, length);

	}

	@Test
	void lengthBasic_ExpectedException() {

		String cadena = null;

		assertThrows(NullPointerException.class, () -> {
			cadena.length();
		}

		);

	}

	@Test
	void toUpperCase() {
		String cadena = "abcd";
		String resultado = cadena.toUpperCase();
		assertNotNull(resultado);
		assertEquals("ABCD", resultado);

	}

	@Test
	void containsBasics() {

		String cadena = "abcd";
//		assertTrue(cadena.contains("a"));
		assertEquals(true, cadena.contains("a"));

	}

	@Test
	@Disabled
	@RepeatedTest(value = 10)
	void testArray() {
		String cadena = "abcd efgh ijkl";
		String[] actual = cadena.split(" ");
		assertArrayEquals(new String[] { "abcd", "efgh", "ijkl" }, actual);

	}

	@Test
	void lengthGreaterThanZero() {
		assertTrue("A".length() > 0);
		assertTrue("B".length() > 0);
		assertTrue("C".length() > 0);
	}

	@Disabled
	@ParameterizedTest
	@ValueSource(strings = { "A", "B", "C" })
	void lengthGreaterThanZeroParameterized(String str) {
		assertTrue(str.length() > 0);
	}

	// CSV + PARAMETERIZED TEST
	@ParameterizedTest(name = "{0} toUpper  {1}")
	@CsvSource(value = { "a, A", "b, B", "c, C" })
	void comparaMayusculas(String minuscula, String mayuscula) {
		assertEquals(mayuscula, minuscula.toUpperCase());
	}

	@Disabled
	@ParameterizedTest(name = "{0} legnth = {1}")
	@CsvSource(value = { "a, 1", "bbb, 3", "cccde, 5" })
	void comparaLongitud(String word, int length) {
		assertEquals(length, word.length());
	}

	// UNIT TESTING FOR PERFORMANCE
	@Disabled
	@Test
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(1) ,() -> {
			for (int i = 0; i < 1000; i++) {
				int j = i;
				System.out.println("numero :" + j);
			}
		}
		);
	}
	
	
	
	//Agrupar TESTS con NESTED
	@Nested
	class EmptyString {
		
		@BeforeEach
		void setEmptyString() {
			cadena = "";
		}
		
		
		@Test
		@DisplayName("Cadena es igual a vacío")
		void emptyString() {
			assertEquals("", cadena);
		}
		
	}

}
