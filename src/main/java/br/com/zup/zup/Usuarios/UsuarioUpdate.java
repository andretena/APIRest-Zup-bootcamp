package br.com.zup.zup.Usuarios;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioUpdate {

	private @NotBlank @Valid String nome;
	private @NotBlank @Valid String email;
	private @NotBlank @Valid String cpf;
	private @NotNull @Valid String dataDeNascimento;

	public Usuario updateUsuario(Usuario usuario) {
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setCpf(cpf);
		usuario.setDataDeNascimento(dataDeNascimento);
		return usuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

}

