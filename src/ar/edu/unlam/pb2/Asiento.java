package ar.edu.unlam.pb2;

import java.util.Objects;

public class Asiento {

	private String filaYNumero; //ubicacion del asiento en el avion
	private Boolean estaLibre;
	
	public Asiento(String filaYNumero) {
		this.filaYNumero = filaYNumero;
		this.estaLibre = true;
	}

	public String getFilaYNumero() {
		return filaYNumero;
	}

	public void setFilaYNumero(String filaYNumero) {
		this.filaYNumero = filaYNumero;
	}

	public Boolean getEstaLibre() {
		return estaLibre;
	}

	public void setEstaLibre(Boolean estaLibre) {
		this.estaLibre = estaLibre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(filaYNumero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asiento other = (Asiento) obj;
		return Objects.equals(filaYNumero, other.filaYNumero);
	}
	
	
}
