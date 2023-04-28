package fr.simplon.titrev1_service.dao;

import fr.simplon.titrev1_service.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository <Evenement, Long> {
}
