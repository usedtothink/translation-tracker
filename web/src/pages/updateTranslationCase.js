import TranslationTrackerClient from '../api/translationTrackerClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the update traanslation case page of the website.
 */
class UpdateTranslationCase extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addTranslationCaseToPage', 'submit'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addTranslationCaseToPage);
        this.header = new Header(this.dataStore);
        console.log("updateTranslationcCase constructor");
    }

    /**
     * Once the client is loaded, get the translation case metadata.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const translationCaseId = urlParams.get('id');
        document.getElementById('case-nickname').innerText = "Loading translation case ...";
        const translationCase = await this.client.getTranslationCase(translationCaseId);
        this.dataStore.set('translationCase', translationCase);
    }

    /**
     * Add the header to the page and load the TranslationTrackerClient.
     */
    mount() {
        document.getElementById('update').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new TranslationTrackerClient();
        this.clientLoaded();
    }

    /**
     * When the translation case is updated in the datastore, update the translation case metadata on the page.
     */
    addTranslationCaseToPage() {
        const translationCase = this.dataStore.get('translationCase');
        if (translationCase == null) {
            return;
        }

        document.getElementById('case-nickname').innerText = "Case nickname: " + translationCase.caseNickname;
        document.getElementById('project-type').innerText = "Project type: " + translationCase.projectType;
    }

    /**
     * Method to run when the update translation case submit button is pressed. Call the TranslationTrackerService to update the
     * translation case.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const updateButton = document.getElementById('update');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Loading...';

        const translationCaseId = this.dataStore.get('translationCase').translationCaseId;
        const translationClientId = document.getElementById('translation-client-id').value;
        const sourceTextTitle = document.getElementById('source-text-title').value;
        const sourceTextAuthor = document.getElementById('source-text-author').value;
        const translatedTitle = document.getElementById('translated-title').value;
        const dueDate = document.getElementById('due-date').value;
        const startDate = document.getElementById('start-date').value;
        const endDate = document.getElementById('end-date').value;
        const openCase = document.getElementById('open-case').value;
        const rushJob = document.getElementById('rush-job').value;


        const translationCase = await this.client.createTranslationCase(translationCaseId, translationClientId, sourceTextTitle, sourceTextAuthor, translatedTitle, dueDate, startDate, endDate, openCase, rushJob, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('translationCase', translationCase);
        window.location.href = `/translationCase.html?id=${translationCase.translationCaseId}`;
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const updateTranslationCase = new UpdateTranslationCase();
    updateTranslationCase.mount();
};

window.addEventListener('DOMContentLoaded', main);
