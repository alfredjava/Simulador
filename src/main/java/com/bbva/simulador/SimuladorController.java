package com.bbva.simulador;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbva.simulador.domain.Fila;
import com.bbva.simulador.domain.Request;
import com.bbva.simulador.domain.Response;
import com.bbva.simulador.util.Util;

@RestController
public class SimuladorController {

 
    
    @RequestMapping(name= "/simulador",method = RequestMethod.POST)
    public Response listarSimulador(@RequestBody Request request) {
    	List<Fila> lista= Util.lista(request);
    	Response r=new Response();
    	r.setData(lista);
    	return r;
    	
    	
    }
    
}
