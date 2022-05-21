package coursework.controllers;

import coursework.models.Car;
import coursework.models.Master;
import coursework.repositories.MastersRepository;
import coursework.repositories.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/masters")
public class MasterController {

    private MastersRepository repository;
    private WorksRepository worksRepository;
    private static String errorMessage;

    @Autowired
    public void WorkRepository(WorksRepository repository) {
        this.worksRepository = repository;
    }

    @Autowired
    public void MasterRepository(MastersRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/new")
    public String addMaster (Model model) {
        model.addAttribute("master", new Master());
        return "masters/new";
    }

    @PostMapping()
    public String create (@ModelAttribute("master") Master master) {
        repository.save(master);
        return "redirect:/masters";
    }

    @GetMapping()
    public String getMasters(Model model) {
        model.addAttribute("masters", repository.findAll());
        model.addAttribute("message", errorMessage);
        System.out.println("<<LOG>> : " + repository.busyMasters());
        errorMessage = "";
        return "masters/masters";
    }

    @GetMapping("/{id}/edit")
    public String index(@PathVariable("id") Long id,
                        Model model) {
        model.addAttribute("master", repository.findMasterById(id));
        System.out.println(id);
        return "masters/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("master") Master master){
        System.out.println(master.getId() + " " + master.getName());
        repository.save(master);
        return "redirect:/masters";
    }

    @PostMapping( "/{id}/delete")
    public String deleteMaster(@PathVariable("id") Long id){
        if(worksRepository.findWorkByMaster_Id(id).isEmpty()){
            repository.deleteById(id);
            return "redirect:/masters";
        } else {
            errorMessage = "Master is busy!";
        }
        return "redirect:/masters";
    }

}
