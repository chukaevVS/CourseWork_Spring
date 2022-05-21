package coursework.controllers;

import coursework.models.Car;
import coursework.models.Master;
import coursework.models.Work;
import coursework.repositories.CarsRepository;
import coursework.repositories.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private WorksRepository worksRepository;
    private CarsRepository repository;

    @Autowired
    public void WorksRepository(WorksRepository repository) {
        this.worksRepository = repository;
    }

    @Autowired
    public void CarsRepository(CarsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/new")
    public String addCar (Model model) {
        model.addAttribute("car", new Car());
        return "cars/new";
    }

    @PostMapping()
    public String create (@ModelAttribute("car") Car car) {
        repository.save(car);
        return "redirect:/cars";
    }

    @GetMapping()
    public String getCars(Model model) {
        model.addAttribute("cars", repository.findAll());
        return "cars/cars";
    }

    @GetMapping("/{id}")
    public String index (Model model,
                         @PathVariable("id") Long id) {
        List<Work> list = worksRepository.findWorkByCar_Id(id);
        int sum = 0;
        for(Work work : list){
            sum += work.getService().getCost_foreign();
        }
        model.addAttribute("works", list);
        model.addAttribute("sum", sum);

        String countingNum = repository.findCarById(id).getNum();
        System.out.println("<<LOG>> :" + repository.countOfBusyCarsWithNum(countingNum));
        System.out.println("<<LOG>> total price :" + repository.totalPriceCar(countingNum));
        return "cars/id";
    }

    @GetMapping("/{id}/edit")
    public String indexForEdit(@PathVariable("id") Long id,
                        Model model) {
        model.addAttribute("car", repository.findCarById(id));
        System.out.println(id);
        return "cars/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("car") Car car){
        repository.save(car);
        return "redirect:/cars";
    }
}
