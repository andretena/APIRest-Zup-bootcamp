package br.com.zup.zup.Usuarios;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import br.com.zup.zup.Validacoes.ImpedirRedundancia;

public class UsuarioDto {

	private @NotBlank @Valid String nome;
	private @NotBlank @Valid @Email @ImpedirRedundancia(campo = "email", classe = Usuario.class) String email;
	private @NotBlank @Valid @CPF @ImpedirRedundancia(campo = "cpf", classe = Usuario.class) String cpf;
	private @NotNull @Valid String dataDeNascimento;

	public UsuarioDto(@NotBlank @Valid String nome, @NotBlank @Valid @Email String email,
			@NotBlank @Valid @CPF String cpf, @NotNull @Valid String dataDeNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}

	public Usuario criaUsuario() {
		return new Usuario(this.nome, this.email, this.cpf, this.dataDeNascimento);

	}

}




