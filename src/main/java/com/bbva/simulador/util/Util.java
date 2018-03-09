package com.bbva.simulador.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		
		return cuota;
	}
	public static List<Fila> lista(Request request){
		List<Fila> filas=new ArrayList<Fila>();
		double cuota=obtenerCoutaMensual(request);
		double i=request.getTasa();
		Date fecha=stringToDate(request.getFecha());
		
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
			//agregar a la fecha un mes
			Date fechaCouta=addMonth(fecha,1);
			fila.setFecha(dateToString(fechaCouta));
			fila.setNroCouta(j);			
			double interes=i*capitalPendiente;
			amortizacion=cuota-interes;
			fila.setInteres(round(interes,2));
			fila.setAmortizacion(round(amortizacion,2));
			fila.setCuotaPago(round(cuota,2));
			totalamortizado=amortizacion+amorTizadoAnterior;
			fila.setTotalAmortizado(round(totalamortizado,2));
			capitalPendiente=capitalPendiente-amortizacion;
			fila.setCapitalPendiente(round(capitalPendiente,2));
			filas.add(fila);
			amorTizadoAnterior=totalamortizado;
//			capitalPendiente=fila.getCapitalPendiente();
			fecha=fechaCouta;
			
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
	public static Date stringToDate(String dateInString){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 Date date=new Date();
	        try {
	            date = formatter.parse(dateInString);

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return date;
	}
	public static String dateToString(Date date){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 String strDate=null;
	        strDate = formatter.format(date);
	        return strDate;
	}
	 public static Date addMonth(Date date, int i) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, i);
	        return cal.getTime();
	    }
}
