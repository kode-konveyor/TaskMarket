package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.DTOToEntityConverterService;
import com.kodekonveyor.market.EntityToDTOConverterService;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.project.PullRequestDTO;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.kodekonveyor.market.tasks.TaskConstants.TASK_NOT_FOUND;

@Service
public class AssignPullRequestService {

    @Autowired
    private PullrequestEntityRepository pullrequestEntityRepository;

    @Autowired
    private TaskEntityRepository taskEntityRepository;

    @Autowired
    private UnassignPullRequestService unassignPullRequestService;

    @Autowired
    private EntityToDTOConverterService entityToDTOConverterService;

    @Autowired
    private DTOToEntityConverterService dtoToEntityConverterService;

    public TaskEntity call(final PullRequestDTO pullRequestDTO) {
        checkAndRemoveAlreadyAssignedTask(pullRequestDTO);
        PullRequestEntity pullRequestEntity = dtoToEntityConverterService.convertPRToEntity(pullRequestDTO);
        pullRequestEntity = pullrequestEntityRepository.save(pullRequestEntity);
        return pullRequestEntity.getTask();
    }

    private void checkAndRemoveAlreadyAssignedTask(final PullRequestDTO pullRequestDTO) {
        TaskEntity taskEntity = taskEntityRepository.findById(pullRequestDTO.getTask())
                .orElseThrow(() -> new ValidationException(TASK_NOT_FOUND));

        List<PullRequestEntity> byTask = pullrequestEntityRepository.findByTask(taskEntity);
        if (!CollectionUtils.isEmpty(byTask)) {
            byTask.stream()
                    .map(entityToDTOConverterService::convertPRToDTO)
                    .forEach(unassignPullRequestService::call);
        }
    }
}
