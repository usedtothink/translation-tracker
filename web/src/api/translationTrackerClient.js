import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call Translation Tracker.
  */
export default class TranslationTrackerClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getTranslationCase',
            'getAllTranslationCases', 'createTranslationCase', 'updateTranslationCase', 'archiveTranslationCase',
            'getTranslationClient', 'getAllTranslationClients', 'createTranslationClient', 'updateTranslationClient',
            'archiveTranslationClient'];
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
     * Gets the translation case for the given ID.
     * @param id Unique identifier for a translation case
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The translation case's metadata.
     */
    async getTranslationCase(id, errorCallback) {
        try {
            const response = await this.axiosClient.get(`translationcases/${id}`);
            return response.data.playlist;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the translation client for the given ID.
     * @param id Unique identifier for a translation client
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The translation case's metadata.
     */
    async getTranslationClient(id, errorCallback) {
        try {
            const response = await this.axiosClient.get(`translationclients/${id}`);
            return response.data.playlist;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new translation case owned by the current user.
     * @param name The name of the translation case to create.
     * @param tags Metadata tags to associate with a translation case.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The translation case that has been created.
     */
    async createTranslationCase(caseNickname, projectType, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create translation cases.");
            const response = await this.axiosClient.post(`translationcases`, {
                caseNickname: caseNickname,
                projectType: projectType
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
