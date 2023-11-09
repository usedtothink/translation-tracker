package com.nashss.se.translationtracker.dependency;

import com.nashss.se.translationtracker.activity.CreateTranslationCaseActivity;
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
}
