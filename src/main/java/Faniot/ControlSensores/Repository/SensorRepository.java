package Faniot.ControlSensores.Repository;

import Faniot.ControlSensores.Entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    @Modifying
    @Query("UPDATE Sensor s SET s.deleted = false WHERE s.id = ?1")
    void enableById(Integer id);
}
