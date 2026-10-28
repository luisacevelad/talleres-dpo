package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	
	private Restaurante restaurante1;
	private File ingredientes;
	private File menu;
	private File combos;
	private File factura;
	
	
	
	@BeforeEach
	void setUp() {
		restaurante1 = new Restaurante();
		ingredientes = new File("data/ingredientes.txt");
		menu = new File("data/menu.txt");
		combos = new File("data/combos.txt");
	}
	
	
	@AfterEach
	void tearDown() {
		if (factura != null) {
			factura.delete();
		}
	}
	
	
	@Test
	void testRestauranteVacio() {
		assertNull(restaurante1.getPedidoEnCurso(), "No debe haber un pedido en curso");
		assertEquals(0, restaurante1.getPedidos().size(), "No debe haber pedidos");
	}
	
	
	@Test
	void testCargarInformacionRestaurante() throws Exception {
		restaurante1.cargarInformacionRestaurante(ingredientes, menu, combos);
		assertEquals(15, restaurante1.getIngredientes().size(), "La cantidad de ingredientes no es la esperada");
		assertEquals(22, restaurante1.getMenuBase().size(), "La cantidad de productos del menu no es la esperada");
		assertEquals(4, restaurante1.getMenuCombos().size(), "La cantidad de combos no es la esperada");
	}
	
	
	@Test
	void testCargarIngredientesRepetidos() throws Exception {
		File repetidos = new File("data/pruebas/ingredientes_repetidos.txt");
		try {
			restaurante1.cargarInformacionRestaurante(repetidos, menu, combos);
			fail("Debe lanzar IngredienteRepetidoException");
		} catch (IngredienteRepetidoException e) {
		}
	}
	
	
	@Test
	void testCargarMenuRepetido() throws Exception {
		File repetido = new File("data/pruebas/menu_repetido.txt");
		try {
			restaurante1.cargarInformacionRestaurante(ingredientes, repetido, combos);
			fail("Debe lanzar ProductoRepetidoException");
		} catch (ProductoRepetidoException e) {
		}
	}
	
	
	@Test
	void testCargarCombosRepetidos() throws Exception {
		File repetidos = new File("data/pruebas/combos_repetidos.txt");
		try {
			restaurante1.cargarInformacionRestaurante(ingredientes, menu, repetidos);
			fail("Debe lanzar ProductoRepetidoException");
		} catch (ProductoRepetidoException e) {
		}
	}
	
	
	@Test
	void testCargarCombosProductoFaltante() throws Exception {
		File faltante = new File("data/pruebas/combos_producto_faltante.txt");
		try {
			restaurante1.cargarInformacionRestaurante(ingredientes, menu, faltante);
			fail("Debe lanzar ProductoFaltanteException");
		} catch (ProductoFaltanteException e) {
		}
	}
	
	
	@Test
	void testIniciarPedido() throws Exception {
		restaurante1.iniciarPedido("Ana", "Calle 1 # 2-3");
		assertNotNull(restaurante1.getPedidoEnCurso(), "Debe haber un pedido en curso");
		assertEquals("Ana", restaurante1.getPedidoEnCurso().getNombreCliente(), "El cliente del pedido no es el esperado");
	}
	
	
	@Test
	void testIniciarPedidoYaHayUnPedido() throws Exception {
		restaurante1.iniciarPedido("Ana", "Calle 1 # 2-3");
		try {
			restaurante1.iniciarPedido("Luis", "Carrera 7");
			fail("Debe lanzar YaHayUnPedidoEnCursoException");
		} catch (YaHayUnPedidoEnCursoException e) {
		}
	}
	
	
	@Test
	void testCerrarYGuardarPedido() throws Exception {
		restaurante1.iniciarPedido("Ana", "Calle 1 # 2-3");
		int id = restaurante1.getPedidoEnCurso().getIdPedido();
		factura = new File("./facturas/factura_" + id + ".txt");
		restaurante1.cerrarYGuardarPedido();
		assertNull(restaurante1.getPedidoEnCurso(), "No debe quedar un pedido en curso");
		assertEquals(1, restaurante1.getPedidos().size(), "El pedido cerrado debe quedar en la lista de pedidos");
		assertTrue(factura.exists(), "El archivo de la factura no fue creado");
	}
	
	
	@Test
	void testCerrarYGuardarPedidoSinPedido() throws Exception {
		try {
			restaurante1.cerrarYGuardarPedido();
			fail("Debe lanzar NoHayPedidoEnCursoException");
		} catch (NoHayPedidoEnCursoException e) {
		}
	}

}
