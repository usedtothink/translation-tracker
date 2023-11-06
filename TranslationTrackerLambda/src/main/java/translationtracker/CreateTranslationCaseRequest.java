package translationtracker;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateTranslationCaseRequest.Builder.class)
public class CreateTranslationCaseRequest {
    private final String translationClientId;
    private final String translationCaseId;
    private final String caseNickname;

    private CreateTranslationCaseRequest(String translationClientId, String caseNickname) {
        this.translationClientId = translationClientId;
        this.translationCaseId = "client:" + translationClientId + "case:" + caseNickname;
        this.caseNickname = caseNickname;
    }

    public String getTranslationClientId() {
        return translationClientId;
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
                ", translationClientId= '" + translationClientId + '\'' +
                ", translationCaseId= '" + translationCaseId + "' " +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String translationClientId;
        private String caseNickname;

        public Builder withTranslationClientId(String translationClientId) {
            this.translationClientId = translationClientId;
            return this;
        }

        public Builder withCaseNickname(String caseNickname) {
            this.caseNickname = caseNickname;
            return this;
        }

        public CreateTranslationCaseRequest build() {
            return new CreateTranslationCaseRequest(translationClientId, caseNickname);
        }
    }
}
