package br.com.stgenerator.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value = "/cardImage/{cardId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCardImage(@PathVariable(name="cardId") Long cardId, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		if(cardId==null){
			return new ResponseEntity<byte[]>(CardUtil.getFotoDefault(),headers,HttpStatus.OK);
		}
		else{
			try{
				Card c = cs.getCardById(cardId);
				headers.setContentType(MediaType.IMAGE_JPEG);
	            return new ResponseEntity<byte[]>(c.getImagem(),headers,HttpStatus.OK);
			}catch(Exception e){
	            return new ResponseEntity<byte[]>(CardUtil.getFotoDefault(),headers,HttpStatus.OK);
			}
		}
	}
	
	@RequestMapping(value = "/cardThumb/{cardId}", method = RequestMethod.GET)
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
