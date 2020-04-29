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
  GithubCallService githubRequest; //NOPMD

  public List<TaskDTO> call(final String repoName) throws JSONException {

    final JSONArray array = githubRequest.call(repoName);

    return jsonToTaskDTO(repoName, array);

  }

  private List<TaskDTO>
      jsonToTaskDTO(final String repoName, final JSONArray array) throws JSONException {

    final List<TaskDTO> taskListDTO = new ArrayList<>();

    for (int count = 0; count < array.length(); count++) {
      final TaskDTO dto = newDTO();

      final JSONObject jsonObject = array.getJSONObject(count);
      dto.setGithubId(
          jsonObject.getString(GithubConstants.ID)
      );
      dto.setName(
          jsonObject.getString(GithubConstants.TITLE)
      );
      dto.setProject(repoName);

      final JSONObject user =
          jsonObject.getJSONObject(GithubConstants.USER);
      dto.setResponsible(user.getString(GithubConstants.LOGIN));

      final JSONArray label =
          jsonObject.getJSONArray(GithubConstants.LABELS);
      if (label.length() > 0) {

        final String statusName =
            label.getJSONObject(0).getString(GithubConstants.NAME);

        for (final TaskStatusEnum status : TaskStatusEnum.values())
          if (statusName.equals(status.getValue()))
            dto.setStatus(status);
      } else
        dto.setStatus(TaskStatusEnum.OPEN);
      taskListDTO.add(dto);
    }

    return taskListDTO;

  }

  private TaskDTO newDTO() {
    return new TaskDTO();
  }

}
