package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	
	private ProductoAjustado ajustado1;
	private ProductoMenu base1;
	private Ingrediente queso;
	private Ingrediente tomate;
	
	
	
	@BeforeEach
	void setUp() {
		base1 = new ProductoMenu("hamburguesa", 20000);
		ajustado1 = new ProductoAjustado(base1);
		queso = new Ingrediente("queso", 5000);
	    tomate = new Ingrediente("tomate", 1000);
	}
	
	
	@Test
	void testGetNombre() {
		assertEquals("hamburguesa", ajustado1.getNombre(), "El nombre del producto ajustado no es lo esperado");
	}
	
	
	@Test
	void testGetPrecioAgregado() {
		ajustado1.agregarIngrediente(queso);
		assertEquals(25000, ajustado1.getPrecio(), "El precio del producto ajustado no es lo esperado");
	}
	
	
	@Test
	void testGetPrecioAgregadoMultiple() {
		ajustado1.agregarIngrediente(queso);
		ajustado1.agregarIngrediente(tomate);
		assertEquals(26000, ajustado1.getPrecio(), "El precio del producto ajustado no es lo esperado");
	}
	
	
	@Test
	void testGetPrecioEliminado() {
		ajustado1.eliminarIngrediente(queso);
		assertEquals(20000, ajustado1.getPrecio(), "El precio del producto ajustado no es lo esperado");
	}
	
	
	@Test
	void testGetPrecioAgregadoRepetido() {
	    ajustado1.agregarIngrediente(queso);
	    ajustado1.agregarIngrediente(queso);
	    assertEquals(30000, ajustado1.getPrecio(), "...");
	}
	
	
	@Test
	void testGetPrecioOriginal() {
		assertEquals(20000, ajustado1.getPrecio(), "El precio del producto ajustado no es lo esperado");
	}
	
	
	@Test
	void testGenerarTextoFacturaBase() {
		assertEquals("hamburguesa\n            20000\n", ajustado1.generarTextoFactura());
	}
	
	@Test
	void testGenerarTextoFacturaAjustado() {
		ajustado1.agregarIngrediente(queso);
		ajustado1.eliminarIngrediente(tomate);
		assertEquals("hamburguesa\n    +queso                5000\n    -tomate\n            25000\n", ajustado1.generarTextoFactura());
	}
	
	
	@Test
	void testAgregarIngrediente() {
	    ajustado1.agregarIngrediente(queso);
	    assertTrue(ajustado1.generarTextoFactura().contains("+queso"), "El ingrediente no quedó agregado");
	}

	@Test
	void testEliminarIngrediente() {
	    ajustado1.eliminarIngrediente(tomate);
	    assertTrue(ajustado1.generarTextoFactura().contains("-tomate"), "El ingrediente no quedó eliminado");
	}

}
