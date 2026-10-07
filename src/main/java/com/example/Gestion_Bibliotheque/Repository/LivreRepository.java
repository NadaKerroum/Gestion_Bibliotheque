package com.example.Gestion_Bibliotheque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion_Bibliotheque.Entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository <Livre,Long> {

	List<Livre> findByAuteurId (Long auteurId);
	boolean existsByIsbn(Long isbn);
}
