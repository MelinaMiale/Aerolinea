package ar.edu.unlam.pb2;

import java.util.Objects;

public class Pasaje {

	private Asiento asiento;
	private Double precio;
	private Boolean pasajePagado;
	
	
	public Pasaje(Double precio) {
		this.precio = precio;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getPasajePagado() {
		return pasajePagado;
	}

	public void setPasajePagado(Boolean pasajePagado) {
		this.pasajePagado = pasajePagado;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(asiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasaje other = (Pasaje) obj;
		return Objects.equals(asiento, other.asiento);
	}
	
	
	
	
	
}
