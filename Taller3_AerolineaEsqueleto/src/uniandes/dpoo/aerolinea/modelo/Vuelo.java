package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import uniandes.dpoo.aerolinea.tiquetes.*;
import uniandes.dpoo.aerolinea.modelo.tarifas.*;
import uniandes.dpoo.aerolinea.modelo.cliente.*;
import uniandes.dpoo.aerolinea.modelo.tarifas.*;

public class Vuelo {
	
	private Ruta ruta;
	private String fecha;
	private Avion avion;
	private HashMap<String, Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
		this.tiquetes = new HashMap<String, Tiquete>();
		
	}
	
	//Funciones de Get
	
	public Ruta getRuta() {
		return this.ruta;
	}
	
	
	public String getFecha() {
		return this.fecha;
	}
	
	
	public Avion getAvion() {
		return this.avion;
	}
	
	
	public Collection<Tiquete> getTiquetes(){
		return this.tiquetes.values();
	}
	
	
	//Otros metodos
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException { //TODo implementacion completa 
		int tarifa = 0;
		
		for (int i = 1; i <= cantidad;  i++) {
			tarifa = tarifa + calculadora.calcularTarifa(this, cliente);
		}
		
		return tarifa;
	}

}
