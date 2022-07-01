package Faniot.ControlSensores.Service;

import Faniot.ControlSensores.Entity.Sensor;
import Faniot.ControlSensores.Repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SensorService {

    private final SensorRepository sensorRepository;

    public void create(Sensor sensordto) {

        Sensor sensor = new Sensor();

        sensor.setName(sensordto.getName());
        sensorRepository.save(sensor);

    }

    public void update(Sensor sensordto){
        Sensor sensor = sensorRepository.findById(sensordto.getId()).get();

        sensor.setName(sensordto.getName());

        sensorRepository.save(sensor);

    }

    public Sensor getById(Integer id){
        return sensorRepository.findById(id).get();
    }

    public List<Sensor> getAll(){
        return sensorRepository.findAll();
    }

    public void deleteById(Integer id){
        sensorRepository.deleteById(id);
    }

    public void enableById(Integer id){
        sensorRepository.enableById(id);
    }


}
