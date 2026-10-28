package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProductoMenuTest {
	
	private ProductoMenu productoMenu1;
	
	@BeforeEach
	void setUp() {
		productoMenu1 = new ProductoMenu("hamburguesa", 20000);	
	}
	
	
	@Test
	void testGetNombre() {
		assertEquals("hamburguesa", productoMenu1.getNombre(), "El nombre del producto del menu no es el esperado");
	}
	
	
	@Test
	void testGetPrecio() {
		assertEquals(20000, productoMenu1.getPrecio(), "El precio del producto del menu no es el esperado");
	}
	
	
	@Test
	void testGenerarTextoFactura() {
		assertEquals("hamburguesa\n            20000\n", productoMenu1.generarTextoFactura(), "El texto generado para la factura no coincide");
	}

}
