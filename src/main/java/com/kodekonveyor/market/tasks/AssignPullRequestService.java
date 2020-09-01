package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.PullRequestDTOToEntityConverterService;
import com.kodekonveyor.market.PullRequestEntityToDTOConverterService;
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
    private PullRequestEntityToDTOConverterService pullRequestEntityToDTOConverterService;

    @Autowired
    private PullRequestDTOToEntityConverterService pullRequestDtoToEntityConverterService;

    public TaskEntity call(final PullRequestDTO pullRequestDTO) {
        checkAndRemoveAlreadyAssignedTask(pullRequestDTO);
        PullRequestEntity pullRequestEntity = pullRequestDtoToEntityConverterService.call(pullRequestDTO);
        pullRequestEntity = pullrequestEntityRepository.save(pullRequestEntity);
        return pullRequestEntity.getTask();
    }

    private void checkAndRemoveAlreadyAssignedTask(final PullRequestDTO pullRequestDTO) {
        TaskEntity taskEntity = taskEntityRepository.findById(pullRequestDTO.getTask())
                .orElseThrow(() -> new ValidationException(TASK_NOT_FOUND));

        List<PullRequestEntity> byTask = pullrequestEntityRepository.findByTask(taskEntity);
        if (!CollectionUtils.isEmpty(byTask)) {
            byTask.stream()
                    .map(pullRequestEntityToDTOConverterService::call)
                    .forEach(unassignPullRequestService::call);
        }
    }
}
