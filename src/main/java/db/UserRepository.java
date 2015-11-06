package db;

import user.User;


import java.net.UnknownHostException;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.Mongo;
import java.net.UnknownHostException;

public class UserRepository {
	   public MongoOperations getMongoOperations() throws UnknownHostException {
	        MongoOperations mongoOps = new MongoTemplate(new Mongo(), "test");
	        return mongoOps;
	    }

	    public MongoOperations getMongoOperationInstance() {
	        MongoOperations mongoOps = null;
	        try {
	            mongoOps = getMongoOperations();
	        } catch (UnknownHostException uhe) {
	            System.out.println("Unknown Host");
	            return null;
	        }
	        return mongoOps;
	    }


	    public boolean insertUser(User user) {
	        MongoOperations mongoOps = getMongoOperationInstance();
	        if (mongoOps != null) {
	            mongoOps.insert(user);
	            return true;
	        }
	        return false;
	    }

	    public User getUser(String username) throws NullMongoTemplateException {
	        MongoOperations mongoOps = getMongoOperationInstance();
	        if (mongoOps != null) {
	            User u = mongoOps.findOne(new Query(where("username").is(username)), User.class);
	            return u;
	        } else {
	            throw new NullMongoTemplateException();
	        }
	    }

	    public User validateAndGetUser(User user) throws NullMongoTemplateException {
	        MongoOperations mongoOps = getMongoOperationInstance();
	        String username = user.getUsername();
	        String password = user.getPassword();
	        if (mongoOps != null) {
	            User u = mongoOps.findOne(new Query(where("username").is(username).and("password").is(password)), User.class);
	            return u;
	        } else {
	            throw new NullMongoTemplateException();
	        }
	    }	

}
