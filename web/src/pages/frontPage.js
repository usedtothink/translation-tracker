import TranslationTrackerClient from '../api/translationTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create translation case page of the website.
 */
class FrontPage extends BindingClass {
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
     * Method to run when the create translation case submit button is pressed. Call the TranslationTrackerService
     * to create the translation case.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const translationCaseName = document.getElementById('case-nickname').value;

        const translationCase = await this.client.createTranslationCase(caseNickname, projectType, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('translationCase', translationCase);
    }

    /**
     * When the translationCase is updated in the datastore, redirect to the view translation case page.
     */
    redirectToViewTranslationCase() {
        const translationCase = this.dataStore.get('translationCase');
        if (translationCase != null) {
            window.location.href = `/translationCase.html?id=${translationCase.id}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const frontPage = new frontPage();
    frontPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
