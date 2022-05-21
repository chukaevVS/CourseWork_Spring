package coursework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String num;
    private String color;
    private String mark;
    private int isForeign;

    @OneToMany(mappedBy = "car")
    List<Work> car_id;

    public Car() {
    }

    public Car(String num, String color, String mark, int isForeign) {
        this.num = num;
        this.color = color;
        this.mark = mark;
        this.isForeign = isForeign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int isForeign() {
        return isForeign;
    }

    public void setForeign(int foreign) {
        isForeign = foreign;
    }
}
