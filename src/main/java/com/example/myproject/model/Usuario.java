package com.example.myproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.util.StringUtils;

import com.example.myproject.model.enums.TipoPessoa;
import com.example.myproject.model.validation.UsuarioGroupSequenceProvider;
import com.example.myproject.model.validation.group.CnpjGroup;
import com.example.myproject.model.validation.group.CpfGroup;


@Entity
@Table(name= "usuario")
@GroupSequenceProvider(UsuarioGroupSequenceProvider.class)
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String foto;
	
	@Column(name="content_type")
	private String contentType;
	
	@NotBlank(message = "First Name é obrigatório")
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank(message = "Last Name é obrigatório")
	@Column(name="last_name")
	private String lastName;
	
	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Informe um email precisa ser válido")
	private String email;
	
	@NotBlank(message = "Informe uma senha")
	@Length(max = 20, message="A senha deve conter no máximo 20 caracteres")
	private String senha;
	
	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;
	
	@NotBlank(message = "CPF/CNPJ é obrigatório")
	@CPF(groups = CpfGroup.class, message="CPF inválido")
	@CNPJ(groups = CnpjGroup.class, message="CNPJ inválido")
	@Column(name="cpf_ou_cnpj")
	private String cpfOuCnpj;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "destinatario")
	private List<Comentario> comentarios = new ArrayList<>();
	
	@NotNull(message="Informe o tipo pessoa")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;	

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", foto=" + foto + ", contentType=" + contentType + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", senha=" + senha + ", telefone=" + telefone
				+ ", cpfOuCnpj=" + cpfOuCnpj + ", categoria=" + categoria + ", comentarios=" + comentarios
				+ ", tipoPessoa=" + tipoPessoa + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}
	
	public String getFotoOuMock() {
		return StringUtils.isEmpty(foto) ? "mock-profile.png" :foto  ;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
