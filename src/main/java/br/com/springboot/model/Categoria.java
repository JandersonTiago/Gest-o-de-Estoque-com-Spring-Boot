package br.com.springboot.model;

// Enumeração representando as categorias dos produtos
public enum Categoria {
	// Categoria para celulares
	CELULARES("Celulares"),
	// Categoria para eletrodomésticos
	ELETRODOMESTICO("Eletrodomésticos"),
	// Categoria para produtos de informática
	INFORMATICA("Informática"),
	// Categoria para móveis
	MOVEIS("Móveis");
	
	// Descrição da categoria
	private String descricao;
	
	// Construtor privado para inicializar a descrição da categoria
	private Categoria(String descricao){
		this.descricao = descricao;
	}

	// Método para obter a descrição da categoria
	public String getDescricao() {
		return this.descricao ;
	}	
}
