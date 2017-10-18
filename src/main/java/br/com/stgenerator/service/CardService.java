package br.com.stgenerator.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.stgenerator.IGenerator;
import br.com.stgenerator.models.Card;
import br.com.stgenerator.models.CreateCardForm;
import br.com.stgenerator.repository.CardRepository;
import br.com.stgenerator.util.CardUtil;

@Service
public class CardService {
	
	CardRepository cr;
	
	@Autowired
	public CardService(CardRepository cr) {
		this.cr = cr;
	}
	
	public Card getCardById(Long id){
		Card c = cr.findCardById(id);
		if(c!=null)
			c.setUri("/cardImage/"+c.getId());
		return c;
	}
	
	public Page<Card> getCardsByViews(Integer page, Integer size){
		PageRequest pr = new PageRequest(page, size,Direction.DESC,"views");
		return cr.findAll(pr);		
	}
	
	public Card createCard(CreateCardForm form) throws IOException{

		IGenerator i = new IGenerator(CardUtil.getModeloCartaById(form.getModeloSelecionado()));
		
		i.escreveTitulo(form.getTituloCarta(),"black", "Times New Roman");
		i.escreveAtributo1(form.getAtributo1(), form.getValorAtributo1(), "black", "Times New Roman");
		i.escreveAtributo2(form.getAtributo2(), form.getValorAtributo2(), "black", "Times New Roman");
		i.escreveAtributo3(form.getAtributo3(), form.getValorAtributo3(), "black", "Times New Roman");
		i.escreveAtributo4(form.getAtributo4(), form.getValorAtributo4(), "black", "Times New Roman");
		i.escreveAtributo5(form.getAtributo5(), form.getValorAtributo5(), "black", "Times New Roman");
		i.setNumeracao("black", form.getNumeroCarta());
		
		Card c = new Card();
		
		BufferedImage imgp = CardUtil.Base64ToImage(form.getImagem());
		i.setImagemPrincipal(imgp);
		i.criarThumb();
		
		c.setTitulo(form.getTituloCarta());
		c.setViews(1);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayOutputStream baosThumb = new ByteArrayOutputStream();
		
		if(i.getModelo().tipoImagem.equals("jpg")){
			ImageIO.write(i.getImg(), "jpg", baos);
			ImageIO.write(i.getImgThumb(), "jpg", baosThumb);
		}else if(i.getModelo().tipoImagem.equals("png")){
			ImageIO.write(i.getImg(), "png", baos);
			ImageIO.write(i.getImgThumb(), "png", baosThumb);
		}
		c.setImagem(baos.toByteArray());	
		c.setThumb(baosThumb.toByteArray());
		
		c.setPublico(form.isPublico());
		c.setAprovado(false);
		
		cr.save(c);
		return c;
	}

	public Card approveCard(Long cardId) {
		Card c = getCardById(cardId);
		if(c.isAprovado()){
			cr.disapproveCard(cardId);
			c.setAprovado(false);
		}
		else{
			cr.aprroveCard(cardId);
			c.setAprovado(true);
		}
		return c;
	}
	
	public Card publishCard(Long cardId) {
		Card c = getCardById(cardId);
		if(c.isPublico()){
			cr.unpublishCard(cardId);
			c.setPublico(false);
		}
		else{
			cr.publishCard(cardId);
			c.setPublico(true);
		}
		return c;
	}
	
}
