package coursework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long cost_our;
    private Long cost_foreign;

    @OneToMany(mappedBy = "service")
    List<Work> service_id;

    public Service() {}

    public Service(String name, Long cost_our, Long cost_foreign) {
        this.name = name;
        this.cost_our = cost_our;
        this.cost_foreign = cost_foreign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost_our() {
        return cost_our;
    }

    public void setCost_our(Long cost_our) {
        this.cost_our = cost_our;
    }

    public Long getCost_foreign() {
        return cost_foreign;
    }

    public void setCost_foreign(Long cost_foreign) {
        this.cost_foreign = cost_foreign;
    }
}
