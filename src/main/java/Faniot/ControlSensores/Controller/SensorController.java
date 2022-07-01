package Faniot.ControlSensores.Controller;


import Faniot.ControlSensores.Entity.Sensor;
import Faniot.ControlSensores.Service.MedicionService;
import Faniot.ControlSensores.Service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/sensores")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private final MedicionService medicionService;

    @GetMapping
    public ModelAndView getSensores(HttpServletRequest request ){
        ModelAndView mav = new ModelAndView("sensor-table");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("sensores", sensorService.getAll());
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView getSensor(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("sensor-select");
        mav.addObject("sensor", sensorService.getById(id));
        mav.addObject("mediciones", medicionService.getByMedicion(id) );
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("sensor-form");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("sensor", inputFlashMap.get("sensor"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("sensor", new Sensor());
        }

        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("sensor-form");
        mav.addObject("sensor", sensorService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Sensor dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/sensores");

        try {
            sensorService.create(dto);
            attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("sensor", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/sensores/form");
        }

        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Sensor dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/sensores");
        sensorService.update(dto);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/enable/{id}")
    public RedirectView enable(@PathVariable Integer id) {
        sensorService.enableById(id);
        return new RedirectView("/sensores");
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/sensores");
        sensorService.deleteById(id);
        return redirect;
    }

}
