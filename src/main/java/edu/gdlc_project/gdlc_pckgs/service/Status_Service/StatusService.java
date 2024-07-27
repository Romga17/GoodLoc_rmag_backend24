package edu.gdlc_project.gdlc_pckgs.service.Status_Service;

import edu.gdlc_project.gdlc_pckgs.model.Status;

import java.util.List;

public interface StatusService {

    public Status saveStatus(Status status);

    public List<Status> getAllStatuses();
}
