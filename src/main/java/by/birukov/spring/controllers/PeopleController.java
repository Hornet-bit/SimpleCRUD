package by.birukov.spring.controllers;

import by.birukov.spring.models.Person;
import by.birukov.spring.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public PeopleController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personServiceImpl.showAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personServiceImpl.showById(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") Person person){
        System.out.println(person.getName());
        personServiceImpl.save(person);
        return "redirect:/people";
    }

    //page update person
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personServiceImpl.showById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personServiceImpl.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personServiceImpl.delete(id);
        return "redirect:/people";
    }

}
