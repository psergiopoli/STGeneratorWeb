package br.com.stgenerator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.stgenerator.models.Card;

@Transactional
@Repository
public interface CardRepository extends CrudRepository<Card, Long>{

	public Card findCardById(Long id);

	public Card findCardBySecurityHash(String id);
	
	@Query("FROM Card c Where c.aprovado=:aprovado AND c.publico=:publico")
	public Page<Card> findAllCards(Pageable pageRequest,@Param(value = "aprovado") Boolean aprovado,@Param(value = "publico") Boolean publico);
	
	@Modifying
	@Query("UPDATE Card c SET c.aprovado = TRUE WHERE c.id=:id")
	public void aprroveCard(@Param(value = "id") Long id);
	
	@Modifying
	@Query("UPDATE Card c SET c.aprovado = FALSE WHERE c.id=:id")
	public void disapproveCard(@Param(value = "id") Long id);
	
	@Modifying
	@Query("UPDATE Card c SET c.publico = TRUE WHERE c.id=:id")
	public void publishCard(@Param(value = "id") Long id);
	
	@Modifying
	@Query("UPDATE Card c SET c.publico = FALSE WHERE c.id=:id")
	public void unpublishCard(@Param(value = "id") Long id);

	@Modifying
	@Query("UPDATE Card c set c.views = (c.views + 1) WHERE c.id=:id")
    void countView(@Param(value = "id")Long cardId);
}