package com.nashss.se.translationtracker.utils;

import java.util.Random;

public class IdGenerator {
    /**
     * Returns a new translation case ID based on the project type and case nickname.
     *
     * @param projectType The project type associated with the translation case.
     * @param caseNickname The case nickname.
     * @return The newly created translation case ID.
     */
    public static String newTranslationCaseId(String projectType, String caseNickname) {
        Random random = new Random();

        return projectType.toLowerCase() + caseNickname.replaceAll("\\s", "") +
                random.nextInt(10000);
    }

    /**
     * Returns a new translation client ID based on the project type and client name.
     *
     * @param clientType The client type associated with the translation client.
     * @param clientName The client name.
     * @return The newly created translation client ID.
     */
    public static String newTranslationClientId(String clientType, String clientName) {
        Random random = new Random();

        return clientType.toLowerCase() + clientName.replaceAll("\\s", "") +
                random.nextInt(10000);
    }

}
