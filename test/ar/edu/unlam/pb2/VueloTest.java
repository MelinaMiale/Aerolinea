package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class VueloTest {

	@Test
	// 4. Que se pueda asignar un asiento para un pasajero en un vuelo.
	public void queCuandoElPasajeroCompraUnPasajeAparezcaEnElListadoDePasajerosDelVuelo() {
		// given
		Pasajero nuevoPasajero = new Pasajero(4501222);
		Asiento asiento3b = new Asiento("3b"), asiento9a = new Asiento("9a");
		Vuelo vueloIguazu = new Vuelo("vueloIguazu", new HashSet<Asiento>(List.of(asiento3b, asiento9a)));
		final int CANTIDAD_DE_PASAJEROS = 1;

		final Double PRECIO = 450.0;
		Pasaje pasaje = new Pasaje(PRECIO);

		// when
		pasaje.setAsiento(asiento3b);
		nuevoPasajero.comprarPasaje(pasaje, vueloIguazu);
		vueloIguazu.agregarPasajero(nuevoPasajero);

		// then
		assertTrue(vueloIguazu.getPasajeros().size() == 1);
		assertEquals(vueloIguazu.getPasajeros().size(), CANTIDAD_DE_PASAJEROS);
		assertTrue(vueloIguazu.getPasajeros().contains(nuevoPasajero));

	}

	@Test
	// 5. Que no se pueda asignar un asiento a un vuelo si el asiento está ocupado.
	public void queNoSePuedaAsignarUnAsientoAUnVueloSiElAsientoEstaOcupado() {
		// given
		Asiento asiento3b = new Asiento("3b"), asiento9a = new Asiento("9a");
		Vuelo vueloIguazu;
		final int CANTIDAD_DE_ASIENTOS_LIBRES = 1;

		// when
		asiento3b.setEstaLibre(false);
		vueloIguazu = new Vuelo("vueloIguazu", new HashSet<Asiento>(List.of(asiento3b, asiento9a)));

		// then
		assertEquals(CANTIDAD_DE_ASIENTOS_LIBRES, vueloIguazu.getAsientos().size());
		assertTrue(vueloIguazu.getAsientos().contains(asiento9a));
		assertFalse(vueloIguazu.getAsientos().contains(asiento3b));

	}

	@Test
	// 6. Que no se pueda asignar un asiento si el avión ya despegó.
	public void queNoSePuedaAsignarUnAsientoSiElAvionYaDespego() {
		// given
		Asiento asiento3b = new Asiento("3b"), asiento9a = new Asiento("9a"), asiento2t = new Asiento("2t"),
				asiento8f = new Asiento("8f");
		Set<Asiento> asientosAAgregar = new HashSet<Asiento>();
		Vuelo vueloIguazu;
		final int CANTIDAD_FINAL_DE_ASIENTOS_EN_EL_AVION = 2;

		// when
		vueloIguazu = new Vuelo("vueloIguazu", new HashSet<Asiento>(List.of(asiento3b, asiento9a)));
		vueloIguazu.setDespegueIniciado(true);
		asientosAAgregar.add(asiento8f);
		asientosAAgregar.add(asiento2t);
		vueloIguazu.setAsientos(asientosAAgregar);

		// then
		assertEquals(CANTIDAD_FINAL_DE_ASIENTOS_EN_EL_AVION, vueloIguazu.getAsientos().size());
		assertFalse(vueloIguazu.getAsientos().containsAll(List.of(asiento8f, asiento2t)));
		assertTrue(vueloIguazu.getAsientos().containsAll(List.of(asiento3b, asiento9a)));
	}

	@Test
	// 7. Que se pueda obtener una lista de pasajeros que no volaron.
	public void queSePuedaObtenerUnaListaDePasajerosQueNoVolaron() {
		// given
		Pasajero primerPasajero = new Pasajero(4501222);
		Pasajero segundoPasajero = new Pasajero(30001);
		Asiento asiento3b = new Asiento("3b"), asiento9a = new Asiento("9a");
		Vuelo vueloIguazu = new Vuelo("vueloIguazu", new HashSet<Asiento>(List.of(asiento3b, asiento9a)));
		final int CANTIDAD_DE_PASAJEROS_ABORDADOS = 1;

		final Double PRECIO = 450.0;
		Pasaje pasaje1 = new Pasaje(PRECIO);
		Pasaje pasaje2 = new Pasaje(PRECIO);

		// when
		pasaje1.setAsiento(asiento3b);
		primerPasajero.comprarPasaje(pasaje1, vueloIguazu);
		vueloIguazu.agregarPasajero(primerPasajero);
		
		pasaje2.setAsiento(asiento9a);
		segundoPasajero.comprarPasaje(pasaje2, vueloIguazu);
		vueloIguazu.agregarPasajero(segundoPasajero);
		
		primerPasajero.setRealizoCheckIn(true);
		segundoPasajero.setRealizoCheckIn(false);
		
		vueloIguazu.setDespegueIniciado(true);

		// then
		assertEquals(vueloIguazu.getPasajeros().size(), CANTIDAD_DE_PASAJEROS_ABORDADOS);
		assertTrue(vueloIguazu.getPasajerosNoAbordados().contains(segundoPasajero));
		assertFalse(vueloIguazu.getPasajeros().contains(segundoPasajero));
		assertTrue(vueloIguazu.getPasajeros().contains(primerPasajero));

	}

}
