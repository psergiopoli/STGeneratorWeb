package br.com.stgenerator.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.stgenerator.models.Card;
import br.com.stgenerator.service.CardService;
import br.com.stgenerator.util.CardUtil;

@RestController
public class CardImageEndpoint {
	
	CardService cs;
	
	@Autowired
	public CardImageEndpoint(CardService cs) {
		this.cs = cs;
	}
	
	@RequestMapping(value = "/cardImage/{cardId}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
	public void getCardImage(@PathVariable(name="cardId") Long cardId, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(cardId==null){
			resp.setContentType("image/jpeg");
            resp.getOutputStream().write(CardUtil.getFotoDefault());
		}
		else{
			try{
				Card c = cs.getCardById(cardId);
				resp.setContentType("image/jpeg");
	            resp.getOutputStream().write(c.getImagem());
			}catch(Exception e){
				resp.setContentType("image/jpeg");
	            resp.getOutputStream().write(CardUtil.getFotoDefault());
			}
		}		
	}
	
	@RequestMapping(value = "/cardThumb/{cardId}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
	public void getCardThumb(@PathVariable(name="cardId") Long cardId, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(cardId==null){
			resp.setContentType("image/jpeg");
            resp.getOutputStream().write(CardUtil.getFotoDefault());
		}
		else{
			try{
				Card c = cs.getCardById(cardId);
				resp.setContentType("image/jpeg");
	            resp.getOutputStream().write(c.getThumb());
			}catch(Exception e){
				resp.setContentType("image/jpeg");
	            resp.getOutputStream().write(CardUtil.getFotoDefault());
			}
		}		
	}

}
