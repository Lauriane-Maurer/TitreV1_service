package fr.simplon.titrev1_service.dao;

import fr.simplon.titrev1_service.entity.Evenement;
import fr.simplon.titrev1_service.entity.Organisme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganismeRepository extends JpaRepository <Organisme, Long> {
}
