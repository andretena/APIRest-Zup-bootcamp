package br.com.zup.zup.Usuarios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping("/cadastraUsuario")
	public ResponseEntity<?> cadastraUsuario(@RequestBody @Valid UsuarioDto dto) {
		Usuario novoUsuario = dto.criaUsuario();
		if (novoUsuario == null) {
			ResponseEntity.status(404)
					.body("As informaçoes recebidas não foram suficiente para criação de um Usuario!");
		}
		manager.persist(novoUsuario);
		return ResponseEntity.status(201).body(novoUsuario);
	}

	@Transactional
	@GetMapping("/listaUsuarios")
	public ResponseEntity<?> listaUsuarios() {
		TypedQuery<Usuario> consultaUsuarios = manager.createQuery("SELECT u FROM Usuario u", Usuario.class);
		List<Usuario> usuarios = consultaUsuarios.getResultList();
		if (usuarios.isEmpty()) {
			return ResponseEntity.status(404).body("Lista vazia!");
		}
		return ResponseEntity.status(200).body(usuarios);
	}

	@Transactional
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> buscaUsuarioPorId(@PathVariable @Valid Long id) {
		Usuario usuarioBuscado = manager.find(Usuario.class, id);
		if (usuarioBuscado == null) {
			return ResponseEntity.status(404).body("Usuario não encontrado!");
		}
		return ResponseEntity.status(200).body(usuarioBuscado);
	}

	@Transactional
	@PutMapping("/atualizaUsuario/{id}")
	public ResponseEntity<?> atualizaUsuario(@RequestBody @Valid UsuarioUpdate dto, @PathVariable @Valid Long id) {
		Usuario usuarioBuscado = manager.find(Usuario.class, id);
		if (usuarioBuscado == null) {
			return ResponseEntity.status(404).body("Usuario não encontrado!");
		}
		usuarioBuscado = dto.updateUsuario(usuarioBuscado);
		manager.merge(usuarioBuscado);
		return ResponseEntity.status(200).body(usuarioBuscado);
	}

	@Transactional
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable @Valid Long id) {
		Usuario usuarioBuscado = manager.find(Usuario.class, id);
		if (usuarioBuscado == null) {
			return ResponseEntity.status(404).body("Usuario não encontrado!");
		}
		manager.remove(usuarioBuscado);
		return ResponseEntity.status(200).body("Usuario deletado com sucesso!");
	}

}
