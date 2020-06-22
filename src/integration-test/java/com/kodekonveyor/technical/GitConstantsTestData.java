package com.kodekonveyor.technical;

public class GitConstantsTestData {
    public static final String TEST_GET_GITHUB_REPO_COMMAND = "/repos/kode-konveyor/TaskMarket";

    public static final String TEST_INVALID_GITHUB_REPO_COMMAND = "/repos/kode-konveyor/SomeInvalidRepoName";

    public static final String NODE_ID = "node_id";

    public static final String EXPECTED_NODE_ID = "MDEwOlJlcG9zaXRvcnkxNzgyNDgwMzA=";

    public static final String EXPECTED_ERROR_MSG_WHN_INVALID_TYPE = "Unable to convert response to the given value type.";

    public static final String EXPECTED_ERROR_MSG_WHEN_4XX_ERROR = "Failure with status 404, while making get call to github.";

}
