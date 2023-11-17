package com.nashss.se.translationtracker.utils;

import java.util.Random;

public class TranslationCaseIdGenerator {
    /**
     * Returns a new translation case ID based on the customerId, project type, and case nickname.
     *
     * @param customerId The ID of the customer that owns the case.
     * @param projectType The project type associated with the translation case.
     * @param caseNickname The case nickname.
     * @return The newly created translation case ID.
     */
    public static String newId(String customerId, String projectType, String caseNickname) {
        Random random = new Random();

        return projectType.toLowerCase() + caseNickname.replaceAll("\\s", "") +
                random.nextInt(10000);
    }

}
