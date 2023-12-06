import TranslationTrackerClient from '../api/translationTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreateTranslationCase extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewTranslationCase'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewTranslationCase);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the TranslationTrackerClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new TranslationTrackerClient();
    }

    /**
     * Method to run when the create translation case submit button is pressed. Call the TranslationTrackerService to create the
     * translation case.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const caseNickname = document.getElementById('case-nickname').value;
        const projectType = document.getElementById('project-type').value;

        const translationClientIdText = document.getElementById('translation-client-id').value;
        const sourceTextTitleText = document.getElementById('source-text-title').value;
        const sourceTextAuthorText = document.getElementById('source-text-author').value;
        const translatedTitleText = document.getElementById('translated-title').value;
        const dueDateText = document.getElementById('due-date').value;
        const startDateText = document.getElementById('start-date').value;
        const endDateText = document.getElementById('end-date').value;
        const openCaseText = document.getElementById('open-case').value;
        const rushJobText = document.getElementById('rush-job').value;


        let translationClientId;
        if (translationClientIdText.length < 1) {
            translationClientId = null;
        }
        let sourceTextTitle;
        if (sourceTextTitleText.length < 1) {
            sourceTextTitle = null;
        }
        let sourceTextAuthor;
        if (sourceTextAuthorText.length < 1) {
            sourceTextAuthor = null;
        }
        let translatedTitle;
        if (translatedTitleText.length < 1) {
            translatedTitle = null;
        }
        let dueDate;
        if (dueDateText.length < 1) {
            dueDate = null;
        }
        let startDate;
        if (startDateText.length < 1) {
            startDate = null;
        }
        let endDate;
        if (endDateText.length < 1) {
            endDate = null;
        }
        let openCase;
        if (openCaseText.length < 1) {
            openCase = null;
        }
        let rushJob;
        if (rushJobText.length < 1) {
            rushJob = null;
        }

        const translationCase = await this.client.createTranslationCase(caseNickname, projectType, translationClientId, sourceTextTitle, sourceTextAuthor, translatedTitle, dueDate, startDate, endDate, openCase, rushJob, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('translationCase', translationCase);
    }

    /**
     * When the translationCase is updated in the datastore, redirect to the view translationCase page.
     */
    redirectToViewTranslationCase() {
        const translationCase = this.dataStore.get('translationCase');
        if (translationCase != null) {
            window.location.href = `/translationCase.html?id=${translationCase.translationCaseId}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createTranslationCase = new CreateTranslationCase();
    createTranslationCase.mount();
};

window.addEventListener('DOMContentLoaded', main);
