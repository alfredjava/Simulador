package com.bbva.simulador.util;

import java.util.ArrayList;
import java.util.List;

import com.bbva.simulador.domain.Fila;
import com.bbva.simulador.domain.Request;

public class Util {

	public static double obtenerCoutaMensual(Request request){
		double cuota=0;
		if(request!=null){
			double i=request.getTasa();
			int n=request.getPlazo();
			double cn=request.getImporte();			
			cuota=cn/((1-(Math.pow((1+i), -1*n)))/i);
		}
		
		return round(cuota,2);
	}
	public static List<Fila> lista(Request request){
		List<Fila> filas=new ArrayList<Fila>();
		double cuota=obtenerCoutaMensual(request);
		double i=request.getTasa();
		Fila fila=new Fila();
		fila.setNroCouta(0);
		fila.setInteres(0);
		fila.setAmortizacion(0);
		fila.setCuotaPago(0);
		fila.setTotalAmortizado(0);
		fila.setCapitalPendiente(request.getImporte());
		filas.add(fila);
		double capitalPendiente=request.getImporte();
		double amortizacion=0;
		double amorTizadoAnterior=0;
		double totalamortizado=0;
		for (int j = 1; j <= request.getPlazo(); j++) {
			fila=new Fila();
			fila.setNroCouta(j);			
			double interes=round(i*capitalPendiente,2);
			amortizacion=round(cuota-interes,2);
			fila.setInteres(interes);
			fila.setAmortizacion(amortizacion);
			fila.setCuotaPago(cuota);
			totalamortizado=round(amortizacion+amorTizadoAnterior,2);
			fila.setTotalAmortizado(totalamortizado);
			fila.setCapitalPendiente(round(capitalPendiente-amortizacion,2));
			filas.add(fila);
			amorTizadoAnterior=fila.getTotalAmortizado();
			capitalPendiente=fila.getCapitalPendiente();
			
		}
		
		return filas;
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
