package Contact.web;

import Contact.Contact;
import Contact.data.ContactRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping(path="/api",
        produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class ContactApiController {
    private ContactRepository repo;

    public ContactApiController(ContactRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path="/contact/{id}", produces="application/json")
    public Optional<Contact> putContact(@PathVariable("id") Long id, HttpServletResponse response) {
        if(!repo.findById(id).isPresent()){
            response.setStatus(404);
            return null;
        }
        return repo.findById(id);
    }

    @GetMapping(path="/contacts", produces="application/json")
    public Iterable<Contact> allContacts() {
        return repo.findAll();
    }

    @PostMapping(path="/contact", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact postContact(@RequestBody Contact contact) {
        return repo.save(contact);
    }

    @PutMapping(path="/contact/{id}", consumes="application/json")
    public Contact putContact(
            @PathVariable("id") Long id,
            @RequestBody Contact contact) {
        contact.setId(id);
        return repo.save(contact);
    }

    @DeleteMapping("/contact/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("id") Long id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }
}
