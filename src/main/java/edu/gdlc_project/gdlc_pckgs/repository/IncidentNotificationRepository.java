package edu.gdlc_project.gdlc_pckgs.repository;

import edu.gdlc_project.gdlc_pckgs.model.IncidentNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentNotificationRepository extends JpaRepository<IncidentNotification,Integer> {
    List<IncidentNotification> findDeclarationIncidentsById(int id);
}
