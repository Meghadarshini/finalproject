package project295B;

import org.springframework.beans.factory.annotation.Autowired;

import user.User;
import db.NullMongoTemplateException;
import db.UserRepository;

public class ProjectHandler {
    @Autowired
    UserRepository userRepository;

    public void addToRepository(User user) {
        userRepository.insertUser(user);
    }

    public User doesUserExist(String username) {
        User user = null;
        try {
            user = userRepository.getUser(username);
        } catch (NullMongoTemplateException e) {
            e.printStackTrace();
        }
        return user;

    }

    public User validateUserDetails(User user) {
        try {
            user = userRepository.validateAndGetUser(user);
        } catch (NullMongoTemplateException e) {
            e.printStackTrace();
        }
        return user;
    }	

}
