package br.com.stgenerator.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import br.com.stgenerator.util.CardUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Card {

	@Id
	@SequenceGenerator(name = "CARTA_ID", sequenceName = "CARTA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARTA_ID")
	private Long id;

	private String Titulo;
	
	private Integer views;
	
	private Date dataCriacao;
	
	private boolean publico;
	
	private boolean aprovado;

	@JsonIgnore
	private String securityHash;

	@Transient
	private String uri;
	
	@JsonIgnore
	@Column(columnDefinition = "BYTEA")
	@Basic(fetch=FetchType.LAZY)
	private byte[] imagem;
		
	@JsonIgnore
	@Column(columnDefinition = "BYTEA")
	@Basic(fetch=FetchType.LAZY)
	private byte[] thumb;

	@PrePersist
	void createdAt() {
		this.dataCriacao = new Date();
	}

	public String getSecurityHash() {
		return securityHash;
	}

	public void setSecurityHash(String securityHash) {
		this.securityHash = securityHash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public byte[] getThumb() {
		return thumb;
	}

	public void setThumb(byte[] thumb) {
		this.thumb = thumb;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
