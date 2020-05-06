package com.kodekonveyor.market.project;

import org.springframework.data.repository.CrudRepository;

public interface PullrequestEntityRepository //NOPMD
    extends CrudRepository<PullRequestEntity, Long> {

}
