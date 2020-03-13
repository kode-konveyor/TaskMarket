package com.kodekonveyor.market.tasks;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.market.github.GithubConstants;

@Service
public class GetRepositoryTasksService {

  @Autowired
  GithubRequestService githubRequest; //NOPMD

  public List<TaskDTO> call(final String repoName) throws JSONException {

    final JSONArray array = githubRequest.call(repoName);

    return jsonToTaskDTO(repoName, array);

  }

  private List<TaskDTO>
      jsonToTaskDTO(final String repoName, final JSONArray array) throws JSONException {

    final List<TaskDTO> taskListDTO = new ArrayList<>();

    for (int count = 0; count < array.length(); count++) {
      final TaskDTO dto = new TaskDTO(); //NOPMD
      dto.setGithubId(
          array.getJSONObject(count).getString(GithubConstants.ID)
      );
      dto.setName(
          array.getJSONObject(count).getString(GithubConstants.TITLE)
      );
      dto.setProject(repoName);

      final JSONObject user =
          array.getJSONObject(count).getJSONObject(GithubConstants.USER);
      dto.setResponsible(user.getString(GithubConstants.LOGIN));

      final JSONArray label =
          array.getJSONObject(count).getJSONArray(GithubConstants.LABELS);
      if (label.length() > 0) {

        final JSONObject statusName = label.getJSONObject(0);

        if (
          statusName.getString(GithubConstants.NAME)
              .equals(GithubConstants.UP_FOR_GRAB)
        )
          dto.setStatus(TaskStatusEnum.UP_FOR_GRAB);

        if (
          statusName.getString(GithubConstants.NAME)
              .equals(GithubConstants.IN_PROGRESS)
        )
          dto.setStatus(TaskStatusEnum.IN_PROGRESS);

      } else
        dto.setStatus(TaskStatusEnum.OPEN);
      taskListDTO.add(dto);
    }

    return taskListDTO;

  }

}
