package ar.edu.unlam.pb2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pasajero {

	private Integer dni;
	private Set<Pasaje> pasajesComprados;
	private Set<Vuelo> vuelos;
	private Boolean realizoCheckIn;
	
	public Pasajero(Integer dni) {
		this.dni = dni;
		this.pasajesComprados = new HashSet<Pasaje>();
		this.vuelos = new HashSet<Vuelo>();
		this.realizoCheckIn = false;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Set<Pasaje> getPasajesComprados() {
		return pasajesComprados;
	}

	public void setPasajesComprados(HashSet<Pasaje> pasajesComprados) {
		this.pasajesComprados = pasajesComprados;
	}

	public Set<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(Set<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public Boolean getRealizoCheckIn() {
		return realizoCheckIn;
	}

	public void setRealizoCheckIn(Boolean realizoCheckIn) {
		this.realizoCheckIn = realizoCheckIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasajero other = (Pasajero) obj;
		return Objects.equals(dni, other.dni);
	}

	public void comprarPasaje(Pasaje pasaje, Vuelo vuelo) {
		if(!this.vuelos.contains(vuelo) && vuelo.getAsientos().contains(pasaje.getAsiento()) && pasaje.getAsiento().getEstaLibre()) {
			this.pagarPasaje(pasaje);
			if(pasaje.getPasajePagado()) {
				this.pasajesComprados.add(pasaje);
				pasaje.getAsiento().setEstaLibre(false);
				this.vuelos.add(vuelo);
			}
		}
		
	}
	
	public void pagarPasaje(Pasaje pasaje) {
		pasaje.getPrecio();
		pasaje.setPasajePagado(true);
	}

	public Pasaje encontrarPasaje(Pasaje pasaje) {
		if(this.pasajesComprados.contains(pasaje)) {
			return pasaje;
		}
		return null;
	}
	
	
	
}
