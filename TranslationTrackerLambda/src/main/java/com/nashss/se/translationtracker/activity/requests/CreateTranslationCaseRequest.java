package com.nashss.se.translationtracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.translationtracker.types.ProjectType;

@JsonDeserialize(builder = CreateTranslationCaseRequest.Builder.class)
public class CreateTranslationCaseRequest {
    private final ProjectType projectType;
    private final String translationCaseId;
    private final String caseNickname;

    private CreateTranslationCaseRequest(ProjectType projectType, String caseNickname) {
        this.projectType = projectType;
        this.translationCaseId = "type:" + projectType + "case:" + caseNickname;
        this.caseNickname = caseNickname;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public String getTranslationCaseId() {
        return translationCaseId;
    }

    public String getCaseNickname() {
        return caseNickname;
    }

    @Override
    public String toString() {
        return "CreateTranslationCaseRequest{ " +
                "caseNickname= '" + caseNickname + '\'' +
                ", projectType= '" + projectType + '\'' +
                ", translationCaseId= '" + translationCaseId + "' " +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private ProjectType projectType;
        private String caseNickname;

        public Builder withProjectType(ProjectType projectType) {
            this.projectType = projectType;
            return this;
        }

        public Builder withCaseNickname(String caseNickname) {
            this.caseNickname = caseNickname;
            return this;
        }

        public CreateTranslationCaseRequest build() {
            return new CreateTranslationCaseRequest(projectType, caseNickname);
        }
    }
}
