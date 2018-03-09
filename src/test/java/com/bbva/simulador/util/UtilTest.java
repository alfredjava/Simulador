package com.bbva.simulador.util;

import org.junit.Test;

import com.bbva.simulador.domain.Request;
import com.bbva.simulador.util.Util;

public class UtilTest {
	@Test
	public void obtenerCuota(){
        Request request=new Request();
        request.setImporte(7000000);
        request.setTasa(0.08);
        request.setPlazo(4);
        System.out.println("cota");
        System.out.println(Util.obtenerCoutaMensual(request));
	}
	@Test
	public void obtenerListaCuota(){
        Request request=new Request();
        request.setImporte(7000000);
        request.setTasa(0.08);
        request.setPlazo(4);
        System.out.println("cota");
        System.out.println(Util.lista(request));
	}
}
