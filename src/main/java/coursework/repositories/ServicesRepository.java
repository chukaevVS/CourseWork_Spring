package coursework.repositories;

import coursework.models.Service;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServicesRepository  extends CrudRepository<Service, Long> {
    Service findServiceByName(String name);
    Service findServiceById(Long id);
}