package edu.gdlc_project.gdlc_pckgs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "incident_notification")
public class IncidentNotification {

    //@Column(name = "id_declaration_incident")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(columnDefinition = "DATE")
    protected Date incidentNotificationDate;

    protected String incidentNotificationType;

    protected String incidentNotificationDescription;

}
