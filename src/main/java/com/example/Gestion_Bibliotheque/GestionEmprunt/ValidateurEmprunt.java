package com.example.Gestion_Bibliotheque.GestionEmprunt;

import com.example.Gestion_Bibliotheque.Entity.Livre;
import com.example.Gestion_Bibliotheque.Entity.Membre;
import com.example.Gestion_Bibliotheque.Exception.IndisponibleLivreException;
import com.example.Gestion_Bibliotheque.Exception.LimiteAtteintException;
import com.example.Gestion_Bibliotheque.Repository.EmpruntRepository;

import org.springframework.stereotype.Component;

@Component
public class ValidateurEmprunt {
	
	private final EmpruntRepository repo;

	public ValidateurEmprunt(EmpruntRepository repo) {
		this.repo = repo;
	}
	
	public void  DisponibiliteLivre (Livre livre) {
		if (repo.existsByLivreIdAndDateRetourEffectiveIsNull(livre.getId())) {
			throw new IndisponibleLivreException("ce livre est deja emprunté");
		}
		
	}
	
	public void VerifierLimiteMembre (Membre membre ) {
		if(repo.countByMembreIdAndDateRetourEffectiveIsNull(membre.getId()) >= 3) {
			throw new LimiteAtteintException("vous avez atteint la limite");
		}
	}
	
	public void ValiderEmprnt(Livre livre , Membre membre) {
		DisponibiliteLivre(livre);
		VerifierLimiteMembre(membre);
	}
	
	

}
