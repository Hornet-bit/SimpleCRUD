package by.birukov.spring.service;

import by.birukov.spring.dao.PersonDAOImpl;
import by.birukov.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonServiceImpl{

    private final PersonDAOImpl personDAOImpl;

    @Autowired
    public PersonServiceImpl(PersonDAOImpl personDAOImpl){
        this.personDAOImpl = personDAOImpl;
    }

    public List<Person> showAll() {
        return personDAOImpl.showAll();
    }

    public Person showById(int id) {
        return personDAOImpl.showById(id);
    }

    public void save(Person person) {
        personDAOImpl.save(person);
    }

    public void update(int id, Person updatePerson) {
        personDAOImpl.update(id, updatePerson);
    }

    public void delete(int id) {
        personDAOImpl.delete(id);
    }
}


