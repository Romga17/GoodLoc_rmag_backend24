package edu.gdlc_project.gdlc_pckgs.service.Location_Service;

import edu.gdlc_project.gdlc_pckgs.model.Location;

import java.util.List;

public interface LocationService {

    public Location saveLocation(Location location);

    public List<Location> getAllLocations();

}
