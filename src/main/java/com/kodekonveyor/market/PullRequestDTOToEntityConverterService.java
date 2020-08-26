package com.kodekonveyor.market;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.market.project.PullRequestDTO;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.tasks.TaskEntity;
import com.kodekonveyor.market.tasks.TaskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.kodekonveyor.market.tasks.TaskConstants.TASK_NOT_FOUND;

@ExcludeFromCodeCoverage("Separate task required for coverage.")
@Service
public class PullRequestDTOToEntityConverterService {

    @Autowired
    private TaskEntityRepository taskEntityRepository;

    public PullRequestEntity call(final PullRequestDTO pullRequestDTO) {//NOPMD
        PullRequestEntity pullRequestEntity = new PullRequestEntity();
        pullRequestEntity.setId(pullRequestDTO.getId());
        pullRequestEntity.setIsAccepted(pullRequestDTO.getIsAccepted());
        pullRequestEntity.setReference(pullRequestDTO.getReference());
        if (Objects.nonNull(pullRequestDTO.getTask())) {
            TaskEntity taskEntity = taskEntityRepository.findById(pullRequestDTO.getTask())
                    .orElseThrow(() -> new ValidationException(TASK_NOT_FOUND));
            pullRequestEntity.setTask(taskEntity);
        }
        return pullRequestEntity;
    }
}
