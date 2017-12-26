package br.com.stgenerator.controllers;

import br.com.stgenerator.models.Card;
import br.com.stgenerator.models.CreateCardForm;
import br.com.stgenerator.service.CardService;
import br.com.stgenerator.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import java.io.IOException;

@RestController
public class CardEndPoint {

    CardService cs;

    @Autowired
    public CardEndPoint(CardService cs) {
        this.cs = cs;
    }

    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public ResponseEntity<Card> gerarCarta(@RequestBody CreateCardForm form) throws IOException {

        Card c = cs.createCard(form);
        return new ResponseEntity<Card>(c, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/card/{cardId}", method = RequestMethod.GET)
    public ResponseEntity<Card> getCardById(@PathVariable(name = "cardId") Long cardId, ServletResponse res) throws IOException {
        Card c = cs.getCardById(cardId);
        if (c == null || !c.isPublico() || !c.isAprovado())
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Card>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "/card/hash/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Card> getCardByUUID(@PathVariable(name = "uuid") String uuid) throws IOException {
        Card c = cs.getCardByUUID(uuid);
        if (c == null)
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Card>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public ResponseEntity<Page<Card>> getCardsPaginated(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) throws IOException {
        Page<Card> cards = cs.getCardsByViews(page, size);
        if (cards == null)
            return new ResponseEntity<Page<Card>>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Page<Card>>(cards, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/card/admin", method = RequestMethod.GET)
    public ResponseEntity<Page<Card>> getCardsPaginatedAdmin(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) throws IOException {
        Page<Card> cards = cs.getCardsByViewsAdmin(page, size);
        if (cards == null)
            return new ResponseEntity<Page<Card>>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Page<Card>>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseUtil> countViewToCard(@PathVariable(name = "id") Long cardId) {
        cs.countViewToCard(cardId);
        //TODO NAO DEIXAR MESMO IP FAZER MAIS DE UMA CONTAGEM
        return new ResponseEntity<ResponseUtil>(new ResponseUtil("ok"), HttpStatus.CREATED);
    }

    ;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/card/publish/{cardId}", method = RequestMethod.PATCH)
    public ResponseEntity<Card> publishCard(@PathVariable(name = "cardId") Long cardId) {
        Card c = cs.publishCard(cardId);
        if (c == null)
            return new ResponseEntity<Card>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<Card>(HttpStatus.ACCEPTED);
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/card/approve/{cardId}", method = RequestMethod.PATCH)
    public ResponseEntity<Card> approveCard(@PathVariable(name = "cardId") Long cardId) {
        Card c = cs.approveCard(cardId);
        if (c == null)
            return new ResponseEntity<Card>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<Card>(HttpStatus.ACCEPTED);
    }

}

