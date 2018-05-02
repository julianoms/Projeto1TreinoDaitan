package br.com.project.crud.daos;

import br.com.project.crud.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user){
        entityManager.persist(user);
    }

    public User findByName(String name){
        String query = String.format("select * from user where name ='%s';",name);
        return (User)entityManager.createNativeQuery(query,User.class).getSingleResult();

    }

}
