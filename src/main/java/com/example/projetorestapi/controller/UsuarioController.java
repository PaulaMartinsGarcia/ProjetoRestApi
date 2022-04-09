package com.example.projetorestapi.controller;

import com.example.projetorestapi.model.Usuario;
import com.example.projetorestapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(path = "/api/usuario/{id}")
    public ResponseEntity consultar(@PathVariable("id") Long id){
        return usuarioRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/api/usuario/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/api/usuario/listarTodosUsuarios")
    public List<Usuario> listarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    @DeleteMapping("/api/usuario/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        usuario.setId(id);
        usuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }
}
