package com.example.Gestion_Bibliotheque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Gestion_Bibliotheque.Entity.Emprunt;

@Repository
public interface EmpruntRepository  extends JpaRepository <Emprunt,Long>{

	boolean existsByLivreIdAndDateRetourEffectiveIsNull(Long livreId);

    int countByMembreIdAndDateRetourEffectiveIsNull(Long membreId);

    List<Emprunt> findByLivreId(Long livreId);


}
