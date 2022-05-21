package coursework.controllers;

import coursework.models.Master;
import coursework.models.Service;
import coursework.repositories.ServicesRepository;
import coursework.repositories.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceController {
    private ServicesRepository repository;
    private WorksRepository worksRepository;

    private static String errorMessage;

    @Autowired
    public void WorkRepository(WorksRepository repository) {
        this.worksRepository = repository;
    }

    @Autowired
    public void ServiceRepository(ServicesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/new")
    public String addService (Model model) {
        model.addAttribute("service", new Service());
        return "services/new";
    }

    @PostMapping()
    public String create (@ModelAttribute("service") Service service) {
        repository.save(service);
        return "redirect:/services";
    }

    @GetMapping()
    public String getServices(Model model) {
        model.addAttribute("services", repository.findAll());
        model.addAttribute("message", errorMessage);
        errorMessage = "";
        return "services/services";
    }

    @GetMapping("/{id}/edit")
    public String index(@PathVariable("id") Long id,
                        Model model) {
        model.addAttribute("service", repository.findServiceById(id));
        return "services/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("service") Service service){
        repository.save(service);
        return "redirect:/services";
    }

    @PostMapping( "/{id}/delete")
    public String deleteMaster(@PathVariable("id") Long id){
        if(worksRepository.findWorkByService_Id(id).isEmpty()){
            repository.deleteById(id);
            return "redirect:/services";
        } else {
            errorMessage = "Service in the work!";
        }
        return "redirect:/services";
    }
}
