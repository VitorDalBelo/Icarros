package model;

import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class Filme {
	private Integer id;
	private String nome;
	private String duracao;
	private Date lancamento;
	private String img;
	private String mod;
	private String sinopse;
	private Integer cliente;
	private Integer genero;
	
	public Filme(Integer id, String nome, String duracao, Date lancamento, String img, String mod, String sinopse,Integer cliente,Integer genero) {
		super();
		this.id = id;
		this.nome = nome;
		this.duracao = duracao;
		this.lancamento = lancamento;
		this.img = img;
		this.mod = mod;
		this.sinopse = sinopse;
		this.cliente = cliente;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDuracao() {
		return duracao;
	}

	public Date getLancamento() {
		return lancamento;
	}

	public String getImg() {
		return img;
	}

	public String getMod() {
		return mod;
	}

	public Integer getCliente() {
		return cliente;
	}

	public Integer getGenero() {
		return genero;
	}	
	
	public String getSinopse() {
		return sinopse;
	}

	public Vector<String> toVector() {
		
		return new Vector<String>(Arrays.asList(String.valueOf(this.id),this.nome,this.duracao, this.lancamento.toString(), String.valueOf(this.cliente),String.valueOf(this.genero)));
	}

}
