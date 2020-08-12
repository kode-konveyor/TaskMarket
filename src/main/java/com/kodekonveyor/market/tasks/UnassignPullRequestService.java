package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.project.PullRequestDTO;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kodekonveyor.market.MarketConstants.PR_NOT_FOUND;

public class UnassignPullRequestService {

    @Autowired
    private PullrequestEntityRepository pullrequestEntityRepository;

    public TaskEntity call(final PullRequestDTO pullRequestDTO) {
        PullRequestEntity byId = findPullRequest(pullRequestDTO.getId());
        return disassociateNGetTask(byId);
    }

    private PullRequestEntity findPullRequest(final Long pullRequestId) {
        return pullrequestEntityRepository.findById(pullRequestId)
                .orElseThrow(() -> new ValidationException(PR_NOT_FOUND));
    }

    private TaskEntity disassociateNGetTask(final PullRequestEntity pullRequestEntity) {
        TaskEntity taskFound = pullRequestEntity.getTask();
        pullRequestEntity.setTask(null);
        pullrequestEntityRepository.save(pullRequestEntity);
        return taskFound;
    }
}
