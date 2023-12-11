import TranslationTrackerClient from '../api/translationTrackerClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create translation client page of the website.
 */
class CreateTranslationClient extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewTranslationClient'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewTranslationClient);
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
     * Method to run when the create translation client submit button is pressed. Call the TranslationTrackerService to create the
     * translation client.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const translationClientName = document.getElementById('client-name').value;
        const translationClientType = document.getElementById('client-type').value;

        const translationClient = await this.client.createTranslationClient(translationClientName, translationClientType, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('translationClient', translationClient);
    }

    /**
     * When the translationClient is updated in the datastore, redirect to the view translationClient page.
     */
    redirectToViewTranslationClient() {
        const translationClient = this.dataStore.get('translationClient');
        if (translationClient != null) {
            window.location.href = `/translationClient.html?id=${translationClient.translationClientId}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createTranslationClient = new CreateTranslationClient();
    createTranslationClient.mount();
};

window.addEventListener('DOMContentLoaded', main);
