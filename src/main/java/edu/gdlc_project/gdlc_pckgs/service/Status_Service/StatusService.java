package edu.gdlc_project.gdlc_pckgs.service.Status_Service;

import edu.gdlc_project.gdlc_pckgs.model.Status;

import java.util.List;

public interface StatusService {

    public Status saveEtat(Status status);

    public List<Status> getAllEtat();
}
