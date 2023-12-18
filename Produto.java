package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Produto {
	private int idProduto;
	private String nome;
	private int estoque;
	private double preco;
	private String descricao;
	private String nomeArquivo;
	private String caminho;
	private int status;

}
