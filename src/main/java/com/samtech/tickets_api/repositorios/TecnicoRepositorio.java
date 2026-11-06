package com.samtech.tickets_api.repositorios;

import com.samtech.tickets_api.modelos.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepositorio extends JpaRepository<Tecnico, Integer> {

}