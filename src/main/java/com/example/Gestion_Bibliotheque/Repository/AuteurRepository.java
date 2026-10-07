package com.example.Gestion_Bibliotheque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gestion_Bibliotheque.Entity.Auteur;

@Repository
public interface AuteurRepository  extends JpaRepository <Auteur,Long>{

}
