package br.com.stgenerator.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.stgenerator.util.ModeloCarta;

@RestController
public class ModelsImageEndpoint{

	@RequestMapping(value = "/model/{model}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable(value = "model") String model,HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ModeloCarta> modelos = Arrays.asList(ModeloCarta.values());
		final HttpHeaders headers = new HttpHeaders();
		
		for (ModeloCarta modeloCarta : modelos) {
			if(model.equals(new Integer(modeloCarta.numero).toString())){
				if(modeloCarta.tipoImagem.equals("jpg"))
					headers.setContentType(MediaType.IMAGE_JPEG);
				else if(modeloCarta.tipoImagem.equals("png"))
					headers.setContentType(MediaType.IMAGE_PNG);
				return new ResponseEntity<byte[]>(IOUtils.toByteArray(modeloCarta.lerImagem()),headers,HttpStatus.OK);
			};
		}
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/model/list", method = RequestMethod.GET)
	public List<ModeloCarta> getModels(HttpServletRequest req, HttpServletResponse resp) {		
		List<ModeloCarta> modelos = Arrays.asList(ModeloCarta.values());
		return modelos;	
	}
}
