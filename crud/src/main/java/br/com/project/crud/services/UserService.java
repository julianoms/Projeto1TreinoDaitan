package br.com.project.crud.services;

import br.com.project.crud.daos.UserRepository;
import br.com.project.crud.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void CreateUser(User user){
        repository.save(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByName(User user){
        return repository.findByName(user.getUsername());
    }
}
