package translationtracker;

import java.util.Objects;

public final class TranslationCaseUpdate {

    private final Integer wordCount;
    private final String startDate;
    private final String endDate;
    private final String startTime;
    private final String endTime;
    private final String notes;

    private TranslationCaseUpdate(Integer wordCount, String startDate, String endDate,
                                  String startTime, String endTime, String notes) {
        this.wordCount = wordCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getNotes() {
        return notes;
    }

    /**
     * Makes a defensive copy of a single TranslationCaseUpdate object.
     * @param original the original TranslationCaseUpdate to be copied.
     * @return the new, copied TranslationCaseUpdate.
     */
    public static TranslationCaseUpdate defensiveCopyTranslationCaseUpdate(TranslationCaseUpdate original) {
        return TranslationCaseUpdate.builder()
                .withWordCount(original.getWordCount())
                .withStartDate(original.getStartDate())
                .withEndDate(original.getEndDate())
                .withStartTime(original.getStartTime())
                .withEndTime(original.getEndTime())
                .withNotes(original.getNotes())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranslationCaseUpdate that = (TranslationCaseUpdate) o;
        return Objects.equals(getWordCount(), that.getWordCount()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getNotes(), that.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWordCount(), getStartDate(), getEndDate(),
                getStartTime(), getEndTime(), getNotes());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer wordCount;
        private String startDate;
        private String endDate;
        private String startTime;
        private String endTime;
        private String notes;

        public Builder withWordCount(Integer wordCount) {
            this.wordCount = wordCount;
            return this;
        }

        public Builder withStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public TranslationCaseUpdate build() {
            return new TranslationCaseUpdate(wordCount, startDate, endDate, startTime,
                    endTime, notes);
        }

    }
}
