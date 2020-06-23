package workshopspringmvc.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopspringmvc.model.entity.Exercise;
import workshopspringmvc.model.service.ExerciseServiceModel;
import workshopspringmvc.repository.ExerciseRepository;
import workshopspringmvc.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper mapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
    }


    @Override
    public ExerciseServiceModel addExercise(ExerciseServiceModel exerciseServiceModel) {
        return this.mapper.map(this.exerciseRepository
                .saveAndFlush(this.mapper.map(exerciseServiceModel, Exercise.class)), ExerciseServiceModel.class);
    }
}
