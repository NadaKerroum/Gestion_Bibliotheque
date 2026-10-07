package com.example.Gestion_Bibliotheque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion_Bibliotheque.Entity.Membre;

@Repository
public interface MembreRepository  extends JpaRepository <Membre,Long>{

	boolean existsByEmail(String email);

}
