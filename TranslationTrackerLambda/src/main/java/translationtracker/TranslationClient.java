package translationtracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.util.List;
import java.util.Objects;

public class TranslationClient {
    private String translationClientId;
    private String translationClientName;
    private TranslationClientType translationClientType;
    private String mostRecentActivity;
    private List<String> translationCaseHistory;
    private List<PaymentHistoryRecord> paymentHistory;

    @DynamoDBHashKey(attributeName = "translationClientID")
    public String getTranslationClientId() {
        return translationClientId;
    }

    public void setTranslationClientId(String translationClientId) {
        this.translationClientId = translationClientId;
    }

    @DynamoDBAttribute(attributeName = "translationClientName")
    public String getTranslationClientName() {
        return translationClientName;
    }

    public void setTranslationClientName(String translationClientName) {
        this.translationClientName = translationClientName;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "translationClientType")
    public TranslationClientType getTranslationClientType() {
        return translationClientType;
    }

    public void setTranslationClientType(TranslationClientType translationClientType) {
        this.translationClientType = translationClientType;
    }

    @DynamoDBAttribute(attributeName = "mostRecentActivity")
    public String getMostRecentActivity() {
        return mostRecentActivity;
    }

    public void setMostRecentActivity(String mostRecentActivity) {
        this.mostRecentActivity = mostRecentActivity;
    }

    @DynamoDBAttribute(attributeName = "translationCaseHistory")
    public List<String> getTranslationCaseHistory() {
        return translationCaseHistory;
    }

    public void setTranslationCaseHistory(List<String> translationCaseHistory) {
        this.translationCaseHistory = translationCaseHistory;
    }

    @DynamoDBTypeConverted(converter = PaymentHistoryRecordListConverter.class)
    @DynamoDBAttribute(attributeName = "paymentHistory")
    public List<PaymentHistoryRecord> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<PaymentHistoryRecord> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationClient that = (TranslationClient) o;
        return Objects.equals(getTranslationClientId(), that.getTranslationClientId()) &&
                Objects.equals(getTranslationClientName(), that.getTranslationClientName()) &&
                getTranslationClientType() == that.getTranslationClientType() &&
                Objects.equals(getMostRecentActivity(), that.getMostRecentActivity()) &&
                Objects.equals(getTranslationCaseHistory(), that.getTranslationCaseHistory()) &&
                Objects.equals(getPaymentHistory(), that.getPaymentHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTranslationClientId(), getTranslationClientName(), getTranslationClientType(),
                getMostRecentActivity(), getTranslationCaseHistory(), getPaymentHistory());
    }
}
