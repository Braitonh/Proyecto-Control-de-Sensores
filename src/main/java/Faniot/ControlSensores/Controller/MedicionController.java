package Faniot.ControlSensores.Controller;

import Faniot.ControlSensores.Entity.Medicion;
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
@RequestMapping("/mediciones")
@RequiredArgsConstructor
public class MedicionController {

    private final MedicionService medicionService;
    private final SensorService sensorService;

    @GetMapping
    public ModelAndView getMediciones(HttpServletRequest request ){
        ModelAndView mav = new ModelAndView("mediciones-table");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("mediciones", medicionService.getAll());

        return mav;
    }

    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("mediciones-form");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("medicion", inputFlashMap.get("medicion"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("medicion", new Medicion());
        }

        mav.addObject("action", "create");
        mav.addObject("sensores", sensorService.getAll());
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("mediciones-form");
        mav.addObject("medicion", medicionService.getByMedicion(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Medicion dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/mediciones");

        try {
            medicionService.create(dto);
            attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("medicion", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/mediciones/form");
        }

        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Medicion dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/mediciones");
        medicionService.update(dto);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/sensores/{id}");
        medicionService.deleteByMedicion(id);
        return redirect;
    }

}
