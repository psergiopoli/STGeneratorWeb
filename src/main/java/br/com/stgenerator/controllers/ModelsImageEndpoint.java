package br.com.stgenerator.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.stgenerator.util.ModeloCarta;

@RestController
public class ModelsImageEndpoint extends HttpServlet{

	private static final long serialVersionUID = 5621941094281331648L;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/model/{model}", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable(value = "model") String model,HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ModeloCarta> modelos = Arrays.asList(ModeloCarta.values());
		
		for (ModeloCarta modeloCarta : modelos) {
			if(model.equals(new Integer(modeloCarta.numero).toString())){
				resp.setContentType("image/"+modeloCarta.tipoImagem);
				resp.getOutputStream().write(IOUtils.toByteArray(modeloCarta.lerImagem()));
			}
		}	
	}
	
	@Secured("ROLE_GUEST")
	@RequestMapping(value = "/model/list", method = RequestMethod.GET)
	public List<ModeloCarta> getModels(HttpServletRequest req, HttpServletResponse resp) {		
		List<ModeloCarta> modelos = Arrays.asList(ModeloCarta.values());
		return modelos;	
	}
}
