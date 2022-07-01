package Faniot.ControlSensores.Repository;

import Faniot.ControlSensores.Entity.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicionRepository extends JpaRepository<Medicion, Integer>{
}
