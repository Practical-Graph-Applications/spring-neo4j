package io.dbops.service;

import io.dbops.model.Location;
import io.dbops.model.User;
import io.dbops.repository.LocationRepository;
import io.dbops.repository.UserRepository;
import io.dbops.util.model.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationImpl extends DBOpsService implements LocationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    private User getUser(String userId){
        // find the user that's logged in
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new EntityNotFoundException("No user matches id provided");
        }

        return user;
    }

    public Location getloc(String locationId){
        return locationRepository.getByLocationId(locationId);
    }

    private Location getLocation(String locationId){
        // find the location
        Location location = locationRepository.findByLocationId(locationId);

        if (location == null) {
            throw new EntityNotFoundException("No location matches id provided");
        }

        return location;
    }

    public void viewed(String locationId, String userId){
        // make sure it doesn't exist
        if(!locationRepository.hasBeenViewed(userId,locationId)){
            Location location = getLocation(locationId);
            User user   = getUser(userId);
            user.viewed(location);
            // save the user
            userRepository.save(user);
        }

    }
}
