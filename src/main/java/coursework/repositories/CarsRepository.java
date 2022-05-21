package coursework.repositories;

import coursework.models.Car;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarsRepository extends CrudRepository<Car, Long> {
    Car findCarByNum(String num);
    Car findCarById(Long id);

    @Query(value = "SELECT COUNT(*) FROM works JOIN cars c on c.ID = works.CAR_ID WHERE NUM = :num",
            nativeQuery = true)
    List<String> countOfBusyCarsWithNum(@Param("num") String num);

    @Query(value = "SELECT SUM(s.COST_FOREIGN) FROM cars JOIN works w on cars.ID = w.CAR_ID JOIN services s on s.ID = w.SERVICE_ID WHERE cars.NUM = ?",
            nativeQuery = true)
    List<String> totalPriceCar(@Param("num") String num);
}
