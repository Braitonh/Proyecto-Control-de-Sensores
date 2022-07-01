package Faniot.ControlSensores.Service;

import Faniot.ControlSensores.Entity.Medicion;
import Faniot.ControlSensores.Entity.Sensor;
import Faniot.ControlSensores.Repository.MedicionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicionService {

    private final MedicionRepository medicionRepository;

    public void create (Medicion mediciondto){

        Medicion medicion = new Medicion();

        medicion.setTemperature(mediciondto.getTemperature());
        medicion.setDate(mediciondto.getDate());
        medicion.setTime(mediciondto.getTime());

        medicionRepository.save(medicion);

    }

    public void update (Medicion mediciondto){

        Medicion medicion = medicionRepository.findById(mediciondto.getId()).get();

        medicion.setTemperature(mediciondto.getTemperature());
        medicion.setDate(mediciondto.getDate());
        medicion.setTime(mediciondto.getTime());

        medicionRepository.save(medicion);

    }

    public Medicion getByMedicion(Integer id){
        return medicionRepository.findById(id).get();
    }

    public List<Medicion> getAll(){
        return medicionRepository.findAll();
    }

    public void deleteByMedicion(Integer id){
        medicionRepository.deleteById(id);
    }

}
