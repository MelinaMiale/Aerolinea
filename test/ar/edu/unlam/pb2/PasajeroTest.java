package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class PasajeroTest {

	@Test
	public void queElPasajeroPuedaComprarUnPasajeEnUnVuelo() {
		//given
		Pasajero nuevoPasajero = new Pasajero(4501222);
		Asiento asiento3b = new Asiento("3b"), asiento9a = new Asiento("9a");
		Vuelo vueloIguazu = new Vuelo("vueloIguazu", new HashSet<Asiento> (List.of(asiento3b, asiento9a)));
		Double precio = 450.0;
		final Double PRECIO_ESPERADO_FINAL_DEL_PASAJE = precio;

		Pasaje pasaje = new Pasaje(precio);

		//when		
		pasaje.setAsiento(asiento3b);
		nuevoPasajero.comprarPasaje(pasaje, vueloIguazu);

		//then
		assertTrue(nuevoPasajero.getPasajesComprados().size() == 1);
		assertFalse(pasaje.getAsiento().getEstaLibre());
		assertEquals(pasaje, nuevoPasajero.encontrarPasaje(pasaje));
		assertEquals(PRECIO_ESPERADO_FINAL_DEL_PASAJE, pasaje.getPrecio(), 2);

	}

	@Test
	public void queNoSePuedaComprarDosPasajesParaElMismoPasajeroEnElMismoVuelo() {
		//given
		Pasajero nuevoPasajero = new Pasajero(4501222);
		Asiento asiento3b = new Asiento("3b"), asiento9a = new Asiento("9a");
		Vuelo vueloIguazu = new Vuelo("vueloIguazu", new HashSet<Asiento> (List.of(asiento3b, asiento9a)));
		final int CANTIDAD_DE_PASAJEROS= 1, CANTIDAD_DE_VOLETOS_COMPRADOS_POR_EL_PASAJERO = 1;

		final Double PRECIO = 450.0;
		Pasaje pasaje = new Pasaje(PRECIO);

		final Double PRECIO2 = 500.0;
		Pasaje pasaje2 = new Pasaje(PRECIO2);

		//when		
		pasaje.setAsiento(asiento3b);
		pasaje2.setAsiento(asiento9a);
		nuevoPasajero.comprarPasaje(pasaje, vueloIguazu);
		nuevoPasajero.comprarPasaje(pasaje2, vueloIguazu);
		vueloIguazu.agregarPasajero(nuevoPasajero);

		//then
		assertTrue(vueloIguazu.getPasajeros().size() == CANTIDAD_DE_PASAJEROS);
		assertTrue(vueloIguazu.getPasajeros().contains(nuevoPasajero));
		assertEquals(CANTIDAD_DE_VOLETOS_COMPRADOS_POR_EL_PASAJERO, nuevoPasajero.getVuelos().size());
	}

	@Test
	public void queALosPasajerosVipSeLesApliqueUn5PorcientoDeDescuentoEnElPrecioDelPasaje() {
		//given
		PasajeroVip nuevoPasajero = new PasajeroVip(4501222, 5);
		Asiento asiento3b = new Asiento("3b"), asiento9a = new Asiento("9a");
		Vuelo vueloIguazu = new Vuelo("vueloIguazu", new HashSet<Asiento> (List.of(asiento3b, asiento9a)));
		Double precio = 450.0;
		final Double PRECIO_ESPERADO_FINAL_DEL_PASAJE = precio - (precio * nuevoPasajero.getPorcentajeDeDescuento() / 100);
		
		Pasaje pasaje = new Pasaje(precio);

		//when		
		pasaje.setAsiento(asiento3b);
		nuevoPasajero.comprarPasaje(pasaje, vueloIguazu);

		//then
		assertTrue(nuevoPasajero.getPasajesComprados().size() == 1);
		assertEquals(PRECIO_ESPERADO_FINAL_DEL_PASAJE, pasaje.getPrecio(), 2);	
	}

}
