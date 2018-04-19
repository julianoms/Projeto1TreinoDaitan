package br.com.project.crud.daos;

import br.com.project.crud.models.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entity;


    @Transactional
    public List<Person> findByName(String name){
        String query = String.format("select * from person where name ='%s';",name);
        return entity.createNativeQuery(query,Person.class).getResultList();
    }

    @Transactional
    public void savePerson(Person person){
        entity.persist(person);
    }


    @Transactional
    public Person findById(long id){

        return entity.find(Person.class,id);
    }
    @Transactional
    public Person merge(Person person){

        return entity.merge(person);
    }

    @Transactional
    public List<Person> findAll(){
        return  entity.createNativeQuery("select * from person;", Person.class).getResultList();
    }

    @Transactional
    public void deleteById (long id){
            entity.createNativeQuery("delete from person where id = :id").setParameter("id", id).executeUpdate();

    }

}
