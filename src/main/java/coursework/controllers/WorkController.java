package coursework.controllers;

import coursework.models.Service;
import coursework.models.Work;
import coursework.repositories.CarsRepository;
import coursework.repositories.MastersRepository;
import coursework.repositories.ServicesRepository;
import coursework.repositories.WorksRepository;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/works")
public class WorkController {

    private WorksRepository repository;
    private CarsRepository carsRepository;
    private ServicesRepository servicesRepository;
    private MastersRepository mastersRepository;

    @Autowired
    public void MasterRepository(MastersRepository repository) {
        this.mastersRepository = repository;
    }

    @Autowired
    public void ServiceRepository(ServicesRepository repository) {
        this.servicesRepository = repository;
    }

    @Autowired
    public void CarsRepository(CarsRepository repository) {
        this.carsRepository = repository;
    }

    @Autowired
    public void WorkRepository(WorksRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String getWorks(Model model) {
        model.addAttribute("works", repository.findAll());
        System.out.println("<<LOG>> : " + repository.printWorksWithServiceList());
        return "works/works";
    }

    @GetMapping("/new")
    public String addWork(Model model){
        model.addAttribute("work", new Work());
        model.addAttribute("cars", carsRepository.findAll());
        model.addAttribute("services", servicesRepository.findAll());
        model.addAttribute("masters", mastersRepository.findAll());
        return "works/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("car") String num,
                         @ModelAttribute("service") String service,
                         @ModelAttribute("master") String master,
                         @ModelAttribute("date") String date){
        Work work = new Work();

        work.setCar(carsRepository.findCarByNum(num));
        work.setMaster(mastersRepository.findMasterByName(master));
        work.setService(servicesRepository.findServiceByName(service));
        work.setDateWork(date);
        repository.save(work);
        return "redirect:/works";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") Long id,
                        Model model){
        model.addAttribute("work", repository.findWorkById(id));
        return "works/id";
    }

    @PostMapping( "/{id}")
    public String deleteWork(@PathVariable("id") Long id){
        Work temp = repository.findWorkById(id);
        Long carId = temp.getCar().getId();
        int sizeOfWorksList = repository.findWorkByCar_Id(carId).size();
        if(sizeOfWorksList > 1) {
            repository.deleteById(id);
        } else {
            repository.deleteById(id);
            carsRepository.deleteById(carId);
        }
        return "redirect:/works";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        model.addAttribute("work", repository.findWorkById(id));
        model.addAttribute("cars", carsRepository.findAll());
        model.addAttribute("masters", mastersRepository.findAll());
        model.addAttribute("services", servicesRepository.findAll());
        return "works/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("car") Long num,
                         @ModelAttribute("service") Long service,
                         @ModelAttribute("master") Long master,
                         @ModelAttribute("date") String date,
                         @ModelAttribute("work") Work work) {

        work.setCar(carsRepository.findById(num).get());
        work.setMaster(mastersRepository.findById(master).get());
        work.setService(servicesRepository.findById(service).get());
        work.setDateWork(date);
        System.out.println(work.getMaster().getName());
        repository.save(work);

        return "redirect:/works";
    }
}
