package workshopspringmvc.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshopspringmvc.model.binding.ExerciseAddBindingModel;
import workshopspringmvc.model.service.ExerciseServiceModel;
import workshopspringmvc.service.ExerciseService;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper mapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ModelMapper mapper) {
        this.exerciseService = exerciseService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(@Valid @ModelAttribute("exerciseAddBindingModel")
                              ExerciseAddBindingModel exerciseAddBindingModel,
                      BindingResult bindingResult) {
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("exerciseAddBindingModel")
                                     ExerciseAddBindingModel exerciseAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel",
                    exerciseAddBindingModel);
            return "redirect:/exercises/add";
        } else {
//            if (!exerciseAddBindingModel.getDueDate().isBefore(LocalDateTime.now()) &&
//                    !exerciseAddBindingModel.getStartedOn().isAfter(LocalDateTime.now())) {
            this.exerciseService
                    .addExercise(this.mapper.map(exerciseAddBindingModel, ExerciseServiceModel.class));
            return "redirect:/";
        }
    }
}
