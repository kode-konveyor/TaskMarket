package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.project.PullRequestDTO;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.kodekonveyor.logging.LoggingMarkerConstants.TASK;
import static com.kodekonveyor.market.MarketConstants.PR_NOT_FOUND;
import static com.kodekonveyor.market.tasks.TaskConstants.LOG_ASSIGN_PR_CALL;
import static com.kodekonveyor.market.tasks.TaskConstants.LOG_ASSIGN_PR_CALL_FAILURE;
import static com.kodekonveyor.market.tasks.TaskConstants.LOG_ASSIGN_PR_CALL_SUCCESS;

@Service
public class UnassignPullRequestService {

    @Autowired
    private PullrequestEntityRepository pullrequestEntityRepository;

    @Autowired
    private Logger logger;

    public TaskEntity call(final PullRequestDTO pullRequestDTO) {
        logger.info(TASK, LOG_ASSIGN_PR_CALL, pullRequestDTO.getTask());
        PullRequestEntity byId = findPullRequest(pullRequestDTO.getId());
        TaskEntity taskEntity = disassociateNGetTask(byId);
        logger.debug(TASK, LOG_ASSIGN_PR_CALL_SUCCESS, pullRequestDTO.getTask());
        return taskEntity;
    }

    private PullRequestEntity findPullRequest(final Long pullRequestId) {
        return pullrequestEntityRepository.findById(pullRequestId)
                .orElseThrow(() -> {
                    logger.warn(TASK, LOG_ASSIGN_PR_CALL_FAILURE, PR_NOT_FOUND);
                    return new ValidationException(PR_NOT_FOUND);
                });
    }

    private TaskEntity disassociateNGetTask(final PullRequestEntity pullRequestEntity) {
        TaskEntity taskFound = pullRequestEntity.getTask();
        pullRequestEntity.setTask(null);
        pullrequestEntityRepository.save(pullRequestEntity);
        return taskFound;
    }
}
