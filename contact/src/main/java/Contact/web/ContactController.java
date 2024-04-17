package Contact.web;

import Contact.data.ContactRepository;
import Contact.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@SessionAttributes("contact")
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    long id = 0;


    public Contact contact(){
        return Contact.Con();
    }

    @RequestMapping("/")
    public String Contact(Model model){
        model.addAttribute("contact",contact());
        model.addAttribute("list",contactRepository.findAll());
        return "home";
    }

    @PostMapping
    public String processOrder(@Valid Contact contact, Errors errors,
                               SessionStatus sessionStatus,Model model) {
        model.addAttribute("list",contactRepository.findAll());
        if (errors.hasErrors()) {
            return "home";
        }
        contact.setId(id++);
        contactRepository.save(contact);
        model.addAttribute("list",contactRepository.findAll());
        model.addAttribute("contact",contact());
        sessionStatus.setComplete();
        return "home";
    }
}
