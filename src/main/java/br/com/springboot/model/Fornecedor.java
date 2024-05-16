package br.com.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name="fornecedores")
public class Fornecedor {
    // Identificador único do fornecedor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Nome fantasia do fornecedor
    @Column(nullable = false, length = 50)
    @NotBlank(message = "informe o nome fantasia")
    @Size(min = 3, max = 50)
    private String nomeFantasia;
    
    // Razão social do fornecedor
    @Column(nullable = false, length = 50)
    @NotBlank(message = "informe a razão social")
    @Size(min = 3, max = 50)
    private String razaoSocial;
    
    // CNPJ do fornecedor
    @Column(length = 18)
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;
        
    // Telefone do fornecedor
    @Column(length = 14)
    private String telefone;
    
    // Celular do fornecedor
    @Column(length = 15)
    private String celular;
    
    // Email do fornecedor
    @Column(length = 50)
    @Email
    private String email;
    
    // Indica se o fornecedor está ativo
    private boolean ativo;

    // Construtor padrão inicializa o fornecedor como ativo
    public Fornecedor() {
        this.ativo = true;
    }

    // Métodos getters e setters abaixo...
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}














