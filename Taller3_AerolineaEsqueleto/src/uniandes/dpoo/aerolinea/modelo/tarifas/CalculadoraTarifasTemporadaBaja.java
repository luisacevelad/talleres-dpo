package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1;
	protected final double DESCUENTO_GRANDES = 0.2;

	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		int distancia = calcularDistanciaVuelo(vuelo.getRuta());
		int costoPorKm = ClienteNatural.NATURAL.equals(cliente.getTipoCliente()) ? COSTO_POR_KM_NATURAL : COSTO_POR_KM_CORPORATIVO;
		return costoPorKm * distancia;
	}

	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		if (!ClienteCorporativo.CORPORATIVO.equals(cliente.getTipoCliente())) {
			return 0;
		}

		ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
		switch (corporativo.getTamanoEmpresa()) {
			case ClienteCorporativo.GRANDE:
				return DESCUENTO_GRANDES;
			case ClienteCorporativo.MEDIANA:
				return DESCUENTO_MEDIANAS;
			case ClienteCorporativo.PEQUENA:
				return DESCUENTO_PEQ;
			default:
				return 0;
		}
	}
}
