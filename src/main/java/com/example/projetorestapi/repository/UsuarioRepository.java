package com.example.projetorestapi.repository;

import com.example.projetorestapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
}
