package ar.edu.unlam.pb2;

public class PasajeroVip extends Pasajero {

	private Integer porcentajeDeDescuento;
	
	public PasajeroVip(Integer dni, Integer porcentajeDeDescuento) {
		super(dni);
		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}

	public Integer getPorcentajeDeDescuento() {
		return porcentajeDeDescuento;
	}

	public void setPorcentajeDeDescuento(Integer porcentajeDeDescuento) {
		this.porcentajeDeDescuento = porcentajeDeDescuento;
	}

	@Override
	public void pagarPasaje(Pasaje pasaje) {
		Double precioConDescuentoAplicado = pasaje.getPrecio() - ((pasaje.getPrecio() * this.porcentajeDeDescuento) / 100);
		pasaje.setPrecio(precioConDescuentoAplicado);
		pasaje.setPasajePagado(true);
	}
}
