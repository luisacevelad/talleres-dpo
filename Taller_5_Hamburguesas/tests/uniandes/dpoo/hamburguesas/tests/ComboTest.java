package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

	private ArrayList<ProductoMenu> items;
	private Combo combo1;

	@BeforeEach
	void setUp() {
		items = new ArrayList<ProductoMenu>();
		items.add(new ProductoMenu("corral", 14000));
		items.add(new ProductoMenu("papas medianas", 5500));
		items.add(new ProductoMenu("gaseosa", 5000));
		combo1 = new Combo("combo corral", 0.10, items);
	}

	
	@Test
	void testGetNombre() {
		assertEquals("combo corral", combo1.getNombre(), "El nombre del combo no es el esperado");
	}

	
	@Test
	void testGetPrecioConDescuento() {
		assertEquals(22050, combo1.getPrecio(), "El precio del combo no aplica bien el descuento");
	}

	
	@Test
	void testGetPrecioConDescuentoDecimal() {
		Combo especial = new Combo("combo especial", 0.095, items);
		assertEquals(22172, especial.getPrecio(), "El precio con descuento no es el esperado");
	}

	
	@Test
	void testGetPrecioSinDescuento() {
		Combo sinDescuento = new Combo("combo sin descuento", 0, items);
		assertEquals(24500, sinDescuento.getPrecio(), "Sin descuento el precio debe ser la suma de los productos");
	}

	@Test
	void testGetPrecioComboVacio() {
		Combo vacio = new Combo("combo vacio", 0.10, new ArrayList<ProductoMenu>());
		assertEquals(0, vacio.getPrecio(), "Un combo sin productos debe costar 0");
	}


	@Test
	void testConstructorCopiaLista() {
		items.add(new ProductoMenu("papas grandes", 6900));
		assertEquals(22050, combo1.getPrecio());
	}


	@Test
	void testGenerarTextoFactura() {
		String esperado = "Combo combo corral\n"
				+ " Descuento: 0.1\n"
				+ "            22050\n";
		assertEquals(esperado, combo1.generarTextoFactura());
	}

}
