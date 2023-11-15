package com.nashss.se.translationtracker.utils;

import com.nashss.se.translationtracker.types.ProjectType;

import java.util.Random;

public class TranslationCaseIdGenerator {
    public static String newId(String customerId, String projectType, String caseNickname) {
        Random random = new Random();

        return customerId + "::" + projectType.toLowerCase() + "::" + caseNickname.replaceAll("\\s", "")
                + random.nextInt(10000);
    }

}
