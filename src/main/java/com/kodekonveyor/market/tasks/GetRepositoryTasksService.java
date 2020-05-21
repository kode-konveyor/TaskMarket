package com.kodekonveyor.market.tasks;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.technical.GithubConstants;

@Service
public class GetRepositoryTasksService {

  @Autowired
  private GithubCallService githubRequest;

  @Autowired
  private MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  private TaskEntityRepository taskEntityRepository;

  public void call(final String repoName) throws JSONException {

    final JSONArray array = githubRequest.call(repoName);

    final List<TaskDTO> dtoList = convertJsonToDTO(array);

    storeEntity(dtoList);

  }

  private void storeEntity(final List<TaskDTO> dtoList) {
    for (final TaskDTO taskDTO : dtoList)
      dtoToEntity(taskDTO);

  }

  private void dtoToEntity(final TaskDTO taskDTO) {
    final TaskEntity entity = new TaskEntity();
    final MarketUserEntity responsible =
        marketUserEntityRepository.findById(taskDTO.getMarketUser()).get();

    entity.setBehaviour(taskDTO.getBehaviour());
    entity.setDescription(taskDTO.getDescription());
    entity.setGithubId(taskDTO.getGithubId());
    entity.setId(taskDTO.getId());
    entity.setService(taskDTO.getService());
    entity.setMarketUser(responsible);

    taskEntityRepository.save(entity);

  }

  private
      List<TaskDTO> convertJsonToDTO(final JSONArray array) throws JSONException {

    final List<TaskDTO> dtoList = new ArrayList<>();

    for (int count = 0; count < array.length(); count++)
      dtoList.add(jsonToDTO(array.getJSONObject(count)));

    return dtoList;
  }

  private TaskDTO jsonToDTO(
      final JSONObject jsonObject
  ) throws JSONException {
    String[] title;
    final TaskDTO dto = new TaskDTO();
    dto.setId(
        Long.parseLong(jsonObject.getString(GithubConstants.ID))
    );

    title = jsonObject.getString(GithubConstants.TITLE)
        .split(GithubConstants.FRONSTSLASH);

    dto.setService(title[0]);
    dto.setBehaviour(title[1]);

    dto.setDescription(jsonObject.getString(GithubConstants.BODY));

    dto.setGithubId(
        Long.parseLong(jsonObject.getString(GithubConstants.NUMBER))
    );

    dto.setMarketUser(
        Long.parseLong(jsonObject.getJSONObject(GithubConstants.USER).getString(GithubConstants.ID))
    );
    return dto;

  }

}
