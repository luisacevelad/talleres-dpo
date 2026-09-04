package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;
import uniandes.dpoo.aerolinea.tiquetes.*;
import uniandes.dpoo.aerolinea.modelo.*;

public abstract class Cliente {
	
	//atributos
	
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	
	
	//contructor
	public Cliente() {
		tiquetesSinUsar = new ArrayList<Tiquete>();
		tiquetesUsados = new ArrayList<Tiquete>();
	}
	
	
	//metodos
	
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	public void agregarTiquete(Tiquete tiquete) {
		this.tiquetesSinUsar.add(tiquete);
	}
	
	public int calcularValorTotalTiquetes() {
		int valorTotal = 0;
		
		for (Tiquete tiqueteActual : tiquetesSinUsar) {
			valorTotal = valorTotal + tiqueteActual.getTarifa();
		}
		for (Tiquete tiqueteActual : tiquetesUsados) {
			valorTotal = valorTotal + tiqueteActual.getTarifa();
		}
		
		return valorTotal;
	}
	
	public void usarTiquetes(Vuelo vuelo) {
		
		List<Tiquete> aux = new ArrayList<Tiquete>();
		
		for (Tiquete tiqueteActual : this.tiquetesSinUsar) {
			if (tiqueteActual.getVuelo().equals(vuelo)) {
				tiqueteActual.marcarComoUsado();
				aux.add(tiqueteActual);
			}
		}
		
		for (Tiquete tiqueteActual : aux) {
			this.tiquetesSinUsar.remove(tiqueteActual);
			this.tiquetesUsados.add(tiqueteActual);
		}
	}
}
