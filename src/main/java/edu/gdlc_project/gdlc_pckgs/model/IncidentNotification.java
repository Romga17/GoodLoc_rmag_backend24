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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incident_notification")
    protected int id;

    @Column(columnDefinition = "DATE")
    protected Date incidentNotificationDate;

    protected String incidentNotificationType;

    protected String incidentNotificationDescription;

}
