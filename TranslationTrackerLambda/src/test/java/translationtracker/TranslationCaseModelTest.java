package translationtracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TranslationCaseModelTest {


    @Test
    public void basicTest() {
        // GIVEN
        String translationCaseId = "translationCaseId";
        String translationClientId = "translationClientId";
        String caseNickname = "caseNickname";
        String sourceTextTitle = "sourceTextTitle";
        String sourceTextAuthor = "sourceTextAuthor";
        String translatedTitle = "translatedTitle";
        Double rate = 3.5;
        String rateUnit = "NTD";
        Integer count = 301;
        String countUnit = "字";
        Double grossPayment = 1053.5;
        Double taxRate = 0.20;
        ProjectType projectType = ProjectType.ACADEMIC;
        String dueDate = "01/01/2023";
        String startDate = "28/12/2022";
        String endDate = "31/12/2022";
        Boolean openCase = false;
        Boolean casePaid = true;
        Boolean rushJob = false;
        List<TranslationCaseUpdate> progressLog = new ArrayList<>(List.of(TranslationCaseUpdate.builder().build()));
        Double totalWorkingHours = 3.2;
        Double wordsPerHour = 400.3;

        TranslationCaseModel model = TranslationCaseModel.builder()
                .withTranslationCaseId(translationCaseId)
                .withTranslationClientId(translationClientId)
                .withCaseNickname(caseNickname)
                .withSourceTextTitle(sourceTextTitle)
                .withSourceTextAuthor(sourceTextAuthor)
                .withTranslatedTitle(translatedTitle)
                .withRate(rate)
                .withRateUnit(rateUnit)
                .withCount(count)
                .withCountUnit(countUnit)
                .withGrossPayment(grossPayment)
                .withTaxRate(taxRate)
                .withProjectType(projectType)
                .withDueDate(dueDate)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withOpenCase(openCase)
                .withCasePaid(casePaid)
                .withRushJob(rushJob)
                .withProgressLog(progressLog)
                .withTotalWorkingHours(totalWorkingHours)
                .withWordsPerHour(wordsPerHour)
                .build();

        // WHEN & THEN
        assertEquals(translationCaseId, model.getTranslationCaseId());
        assertEquals(translationClientId, model.getTranslationClientId());
        assertEquals(caseNickname, model.getCaseNickname());
        assertEquals(sourceTextTitle, model.getSourceTextTitle());
        assertEquals(sourceTextAuthor, model.getSourceTextAuthor());
        assertEquals(translatedTitle, model.getTranslatedTitle());
        assertEquals(rate, model.getRate());
        assertEquals(rateUnit, model.getRateUnit());
        assertEquals(count, model.getCount());
        assertEquals(countUnit, model.getCountUnit());
        assertEquals(grossPayment, model.getGrossPayment());
        assertEquals(taxRate, model.getTaxRate());
        assertEquals(projectType, model.getProjectType());
        assertEquals(dueDate, model.getDueDate());
        assertEquals(startDate, model.getStartDate());
        assertEquals(endDate, model.getEndDate());
        assertEquals(openCase, model.getOpenCase());
        assertEquals(casePaid, model.getCasePaid());
        assertEquals(rushJob, model.getRushJob());
        assertEquals(progressLog, model.getProgressLog());
        assertEquals(totalWorkingHours, model.getTotalWorkingHours());
        assertEquals(wordsPerHour, model.getWordsPerHour());
    }



}