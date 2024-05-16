package br.com.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="nota_entrada_itens")
public class NotaEntradaItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do item da nota de entrada
    
    @ManyToOne
    @JoinColumn(name="produto_id")
    @NotNull
    private Produto produto; // Produto associado ao item da nota de entrada
    
    @ManyToOne
    @JoinColumn(name="nota_entrada_id")
    @NotNull
    private NotaEntrada notaEntrada; // Nota de entrada à qual o item pertence
    
    @NotNull(message=("Informe a quantidade"))
    private Integer quantidade; // Quantidade do produto no item da nota de entrada
    
    @NotNull(message=("Informe o valor unitário"))
    private Float valorUnitario; // Valor unitário do produto no item da nota de entrada
    
    private Float valorTotal; // Valor total do item (quantidade * valor unitário)
    
    // Métodos getters e setters para acessar e modificar os atributos da classe

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public NotaEntrada getNotaEntrada() {
        return notaEntrada;
    }

    public void setNotaEntrada(NotaEntrada notaEntrada) {
        this.notaEntrada = notaEntrada;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }
}
