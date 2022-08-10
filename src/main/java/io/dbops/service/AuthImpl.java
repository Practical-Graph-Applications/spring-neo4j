package io.dbops.service;

import io.dbops.model.User;
import io.dbops.repository.UserRepository;
import io.dbops.util.model.DataValidationException;
import io.dbops.util.model.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthImpl extends DBOpsService implements AuthService  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    public User login(String username){

        if(StringUtils.isEmpty(username)){
            throw new DataValidationException("No username provided");
        }

        User obj = null;

        try{
            obj = userRepository.findByUsername(username);
        }catch (Exception e){
            logger.error(e.toString());
        }


        if (obj == null) {
            throw new EntityNotFoundException("No user matches username provided");
        }

        return obj;
    }

}
