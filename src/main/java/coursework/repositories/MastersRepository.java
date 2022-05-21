package coursework.repositories;

import coursework.models.Master;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MastersRepository  extends CrudRepository<Master, Long> {
    Master findMasterByName(String name);
    Master findMasterById(Long id);

    @Query(value = "SELECT DATE_WORK, NAME FROM works JOIN masters m on works.MASTER_ID = m.ID",
            nativeQuery = true)
    List<String> busyMasters();
}
