package coursework.repositories;

import coursework.models.Service;
import coursework.models.Work;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorksRepository extends CrudRepository<Work, Long> {
   List<Work> findWorkByCar_Id(Long id);
   Work findWorkById(Long id);
   List<Work> findWorkByMaster_Id(Long id);
   Work findWorkByCar_Num(String num);
   List<Work> findWorkByService_Id(Long id);

   @Query(value = "SELECT DATE_WORK,MASTER_ID,s.NAME FROM works JOIN services s on s.ID = works.SERVICE_ID",
           nativeQuery = true)
   List<String> printWorksWithServiceList();

}
