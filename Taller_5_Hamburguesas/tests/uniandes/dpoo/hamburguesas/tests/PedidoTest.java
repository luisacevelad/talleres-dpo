package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	
	private Pedido pedido1;
	private ProductoMenu corral;
	private ProductoMenu papas;
	private File archivo;
	
	
	
	@BeforeEach
	void setUp() {
		pedido1 = new Pedido("Ana", "Calle 1 # 2-3");
		corral = new ProductoMenu("corral", 14000);
		papas = new ProductoMenu("papas medianas", 5500);
		archivo = new File(System.getProperty("java.io.tmpdir"), "factura_pedido_test.txt");
	}
	
	
	@AfterEach
	void tearDown() {
		archivo.delete();
	}
	
	
	@Test
	void testGetNombreCliente() {
		assertEquals("Ana", pedido1.getNombreCliente(), "El nombre del cliente no es el esperado");
	}
	
	
	@Test
	void testGetIdPedido() {
		Pedido pedido2 = new Pedido("Luis", "Carrera 7");
		assertEquals(pedido1.getIdPedido() + 1, pedido2.getIdPedido(), "El id del pedido no es el esperado");
	}
	
	
	@Test
	void testGetPrecioTotalPedidoVacio() {
		assertEquals(0, pedido1.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	
	@Test
	void testGetPrecioTotalPedido() {
		pedido1.agregarProducto(corral);
		pedido1.agregarProducto(papas);
		assertEquals(23205, pedido1.getPrecioTotalPedido(), "El precio total del pedido no es el esperado");
	}
	
	
	@Test
	void testGenerarTextoFacturaVacia() {
		String esperado = "Cliente: Ana\n"
				+ "Dirección: Calle 1 # 2-3\n"
				+ "----------------\n"
				+ "----------------\n"
				+ "Precio Neto:  0\n"
				+ "IVA:          0\n"
				+ "Precio Total: 0\n";
		assertEquals(esperado, pedido1.generarTextoFactura(), "El texto generado para la factura no coincide");
	}
	
	
	@Test
	void testGenerarTextoFactura() {
		pedido1.agregarProducto(corral);
		pedido1.agregarProducto(papas);
		String esperado = "Cliente: Ana\n"
				+ "Dirección: Calle 1 # 2-3\n"
				+ "----------------\n"
				+ "corral\n"
				+ "            14000\n"
				+ "papas medianas\n"
				+ "            5500\n"
				+ "----------------\n"
				+ "Precio Neto:  19500\n"
				+ "IVA:          3705\n"
				+ "Precio Total: 23205\n";
		assertEquals(esperado, pedido1.generarTextoFactura(), "El texto generado para la factura no coincide");
	}
	
	
	@Test
	void testGuardarFactura() throws IOException {
		pedido1.agregarProducto(corral);
		pedido1.guardarFactura(archivo);
		assertTrue(archivo.exists(), "El archivo de la factura no fue creado");
		assertEquals(pedido1.generarTextoFactura(), Files.readString(archivo.toPath()), "El contenido del archivo no coincide con la factura");
	}
	
	
	@Test
	void testGuardarFacturaRutaInvalida() throws Exception {
		File invalido = new File(System.getProperty("java.io.tmpdir"), "carpeta_que_no_existe/factura.txt");
		try {
			pedido1.guardarFactura(invalido);
			fail("Debe lanzar FileNotFoundException si no se puede crear el archivo");
		} catch (FileNotFoundException e) {
		}
	}

}
