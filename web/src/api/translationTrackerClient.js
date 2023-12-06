import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call Translation Tracker.
  */
export default class TranslationTrackerClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createTranslationCase', 'getTranslationCase', 'getAllTranlsationCases', 'updateTranslationCase', 'archiveTranslationCase', 'createTranslationClient', 'getTranslationClient', 'getAllTranslationClients', 'archiveTranslationClient', 'addProgressUpdate', 'getPaymentRecord', 'updatePaymentRecord'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the translation case for the given translation case ID.
     * @param translationCaseId Unique identifier for a translation case
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The translation case's metadata.
     */
        async getTranslationCase(translationCaseId, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can access translation cases.");
                const response = await this.axiosClient.get(`translationcases/case`, {
                    translationCaseId: translationCaseId
                }, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.translationCase;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
    

    /**
     * Create a new translation case owned by the current user.
     * @param caseNickname The nickname of the translation case to create.
     * @param projectType The project type to associate with a translation case.
     * @param translationClientId The ID of the client associated with the translation case.
     * @param sourceTextTitle The title of the source text to be translated.
     * @param sourceTextAuthor The author of the source text to be translated.
     * @param translatedTitle The source text title translated into the target language.
     * @param dueDate The date the translation case is due.
     * @param startDate The date the translator started the translation case.
     * @param endDate The date the translator completed the translation case.
     * @param openCase Specifies whether this is an open or closed translation case.
     * @param rushJob Specifies whether this case is being translated as a rush job.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The translation case that has been created.
     */
    async createTranslationCase(caseNickname, projectType, translationClientId, sourceTextTitle, sourceTextAuthor, translatedTitle, dueDate, startDate, endDate, openCase, rushJob, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create translation cases.");
            const response = await this.axiosClient.post(`translationcases`, {
                caseNickname: caseNickname,
                projectType: projectType,
                translationClientId: translationClientId,
                sourceTextTitle: sourceTextTitle,
                sourceTextAuthor: sourceTextAuthor,
                translatedTitle: translatedTitle,
                dueDate: dueDate,
                startDate: startDate,
                endDate: endDate,
                openCase: openCase,
                rushJob: rushJob,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.translationCase;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}
