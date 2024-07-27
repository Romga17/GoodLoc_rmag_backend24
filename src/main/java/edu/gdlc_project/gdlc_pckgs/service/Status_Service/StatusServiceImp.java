package edu.gdlc_project.gdlc_pckgs.service.Status_Service;

import edu.gdlc_project.gdlc_pckgs.model.Status;
import edu.gdlc_project.gdlc_pckgs.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImp implements StatusService {

    private StatusRepository statusRepository;

    @Override
    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}
