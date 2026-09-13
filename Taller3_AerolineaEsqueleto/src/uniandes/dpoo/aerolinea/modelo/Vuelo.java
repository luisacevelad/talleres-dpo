package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import uniandes.dpoo.aerolinea.tiquetes.*;
import uniandes.dpoo.aerolinea.modelo.tarifas.*;
import uniandes.dpoo.aerolinea.modelo.cliente.*;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;

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
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
		if (this.tiquetes.size() + cantidad > this.avion.getCapacidad()) {
			throw new VueloSobrevendidoException(this);
		}

		int tarifaTotal = 0;

		for (int i = 1; i <= cantidad; i++) {
			int tarifa = calculadora.calcularTarifa(this, cliente);
			Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
			GeneradorTiquetes.registrarTiquete(tiquete);
			this.tiquetes.put(tiquete.getCodigo(), tiquete);
			tarifaTotal = tarifaTotal + tarifa;
		}

		return tarifaTotal;
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vuelo)) {
			return false;
		}
		Vuelo otro = (Vuelo) obj;
		return this.ruta.getCodigoRuta().equals(otro.ruta.getCodigoRuta()) && this.fecha.equals(otro.fecha);
	}

}
