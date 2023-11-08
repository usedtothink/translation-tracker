package com.nashss.se.translationtracker.dynamodb;

import com.nashss.se.translationtracker.dynamodb.models.TranslationCase;
import com.nashss.se.translationtracker.exceptions.TranslationCaseNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a translation case using {@link TranslationCase} to represent the model in DynamoDB.
 */
@Singleton
public class TranslationCaseDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the playlists table
     */
    @Inject
    public TranslationCaseDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link TranslationCase} corresponding to the specified id.
     *
     * @param translationCaseId the TranslationCase ID
     * @return the stored TranslationCase, or null if none was found.
     */
    public TranslationCase getTranslationCase(String translationCaseId) {
        TranslationCase translationCase = this.dynamoDbMapper.load(TranslationCase.class, translationCaseId);

        if (translationCase == null) {
            throw new TranslationCaseNotFoundException("Could not find translation case with id" + translationCaseId);
        }
        return translationCase;
    }

    /**
     * Saves (creates or updates) the given translation case.
     *
     * @param translationCase The translation case to save
     * @return The TranslationCase object that was saved
     */
    public TranslationCase saveTranslationCase(TranslationCase translationCase) {
        this.dynamoDbMapper.save(translationCase);
        return translationCase;
    }
}
