package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Set;

public class Vuelo {

	private String nombreVuelo;
	private Set<Pasajero> pasajeros;
	private Set<Asiento> asientos;
	private Set<Pasajero> pasajerosNoAbordados;
	private Boolean despegueIniciado;

	public Vuelo(String nombreVuelo, HashSet<Asiento> asientos) {
		this.nombreVuelo = nombreVuelo;
		this.despegueIniciado = false;
		this.asientos = this.validarAsientos(asientos);
		this.pasajeros = new HashSet<Pasajero>();
		this.pasajerosNoAbordados = new HashSet<Pasajero>();
	}

	public String getNombreVuelo() {
		return nombreVuelo;
	}

	public void setNombreVuelo(String nombreVuelo) {
		this.nombreVuelo = nombreVuelo;
	}

	public Set<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(HashSet<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public Set<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(Set<Asiento> asientos) {
		Set<Asiento> asientosValidos = this.validarAsientos(asientos);
		for(Asiento nuevoAsiento : asientosValidos) {
			this.asientos.add(nuevoAsiento);
		}
	}

	public Set<Pasajero> getPasajerosNoAbordados() {
		return pasajerosNoAbordados;
	}

	public void setPasajerosNoAbordados(HashSet<Pasajero> pasajerosNoAbordados) {
		this.pasajerosNoAbordados = pasajerosNoAbordados;
	}

	public Boolean getDespegueIniciado() {
		return despegueIniciado;
	}

	public void setDespegueIniciado(Boolean despegueIniciado) {
		this.despegueIniciado = despegueIniciado;
		if(this.despegueIniciado) {
			this.actualizarListadosDePasajerosDelVuelo();
		}
	}

	public void actualizarListadosDePasajerosDelVuelo() {
		for(Pasajero pasajero : this.getPasajeros()) {
			if(!pasajero.getRealizoCheckIn()) {
				this.pasajerosNoAbordados.add(pasajero);
			}
		}
		
		this.pasajeros.removeAll(this.pasajerosNoAbordados);	
	}


	public void agregarPasajero(Pasajero nuevoPasajero) {
		if(this.encontrarPasajero(nuevoPasajero) == null) {
			this.pasajeros.add(nuevoPasajero);
		}

	}

	public Pasajero encontrarPasajero(Pasajero pasajero) {
		if(this.pasajeros.contains(pasajero)) {
			return pasajero;
		}
		return null;
	}

	private HashSet<Asiento> validarAsientos(Set<Asiento> asientos) {
		HashSet<Asiento> asientosValidos = new HashSet<>();
		if(!this.despegueIniciado) {
			for(Asiento asiento : asientos) {
				if(asiento.getEstaLibre()) {
					asientosValidos.add(asiento);
				}
			}
		}
		return asientosValidos;
	}

}
