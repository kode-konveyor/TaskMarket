package com.kodekonveyor.market;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.market.project.PullRequestDTO;
import com.kodekonveyor.market.project.PullRequestEntity;
import org.springframework.stereotype.Service;

@Service
@ExcludeFromCodeCoverage("pojo converter service")
public class PullRequestEntityToDTOConverterService {//NOPMD

    public PullRequestDTO call(final PullRequestEntity pullRequestEntity) {
        PullRequestDTO pullRequestDto = new PullRequestDTO();
        pullRequestDto.setId(pullRequestEntity.getId());
        pullRequestDto.setIsAccepted(pullRequestEntity.getIsAccepted());
        pullRequestDto.setReference(pullRequestEntity.getReference());
        pullRequestDto.setTask(pullRequestEntity.getTask().getId());
        return pullRequestDto;
    }
}
