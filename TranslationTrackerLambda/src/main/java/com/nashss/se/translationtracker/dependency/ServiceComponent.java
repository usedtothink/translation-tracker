package com.nashss.se.translationtracker.dependency;

import com.nashss.se.translationtracker.activity.CreateTranslationCaseActivity;
import com.nashss.se.translationtracker.activity.GetTranslationCaseActivity;
import com.nashss.se.translationtracker.activity.UpdateTranslationCaseActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in Translation Tracker.
 */
@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return CreateTranslationCaseActivity
     */
    CreateTranslationCaseActivity provideCreateTranslationCaseActivity();

    /**
     * Provides the relevant activity.
     * @return GetTranslationCaseActivity
     */
    GetTranslationCaseActivity provideGetTranslationCaseActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateTranslationCaseActivity
     */
    UpdateTranslationCaseActivity provideUpdateTranslationCaseActivity();
}
