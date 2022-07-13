package RunLab.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import RunLab.Models.MongoDB.User;

public interface UserRepository extends MongoRepository<User, String>{
    @Query("{userName:'?0'}")
    public User findByUserName(String userName);
    @Query(value="{lastName:'?0'}", fields="{'lastName' : 1, 'quantity' : 1}")
    public List<User> findByLastName(String lastName);
}
