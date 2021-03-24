package by.birukov.spring.dao;

import by.birukov.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAOImpl{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Person> people = new ArrayList<>();
    private static int count;


    public List<Person> showAll(){
        return jdbcTemplate.query("SELECT * FROM persons", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person showById(int id){
        return jdbcTemplate.query("SELECT * FROM persons WHERE id = ?",new Object[]{id} ,
                new PersonMapper()).stream().findAny().orElse(null);
//        return  people.stream().filter(person ->person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO persons(name, age,mail) VALUES (?, ?, ?)",person.getName(),
                person.getAge(), person.getMail());
    }

    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE persons SET name=?, age=?, mail=? WHERE id = ?",
                updatePerson.getName(), updatePerson.getAge(), updatePerson.getMail(),
                updatePerson.getId());
        Person personToBeUpdated = showById(id);
        personToBeUpdated.setName(updatePerson.getName());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM persons WHERE id = ?", id);
    }
}
