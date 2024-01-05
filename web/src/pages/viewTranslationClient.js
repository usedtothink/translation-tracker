import TranslationTrackerClient from '../api/translationTrackerClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view traanslation client page of the website.
 */
class ViewTranslationClient extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addTranslationClientandCasesToPage', 'archiveTranslationClient', 'getHTMLForTranslationCases'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addTranslationClientandCasesToPage);
        this.dataStore.addChangeListener(this.addTranslationClientandCasesToPage);
        this.header = new Header(this.dataStore);
        console.log("viewTranslationClient constructor");
    }

    /**
     * Once the client is loaded, get the translation client metadata.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const translationClientId = urlParams.get('id');
        document.getElementById('translation-client-name').innerText = "Loading translation client ...";
        const translationClient = await this.client.getTranslationClient(translationClientId);
        this.dataStore.set('translationClient', translationClient);
        document.getElementById('translation-cases-display').innerText = "Loading translation cases...";
        const translationCaseList = await this.client.getAllTranslationCasesForTranslationClient(translationClientId);
        this.dataStore.set('translationCaseList', translationCaseList);
    }

    /**
     * Add the header to the page and load the TranslationTrackerClient.
     */
    mount() {
        document.getElementById('archive-translation-client').addEventListener('click', this.archiveTranslationClient);

        this.header.addHeaderToPage();

        this.client = new TranslationTrackerClient();
        this.clientLoaded();
    }

    /**
     * When the translation client is updated in the datastore, update the translation client metadata on the page.
     */
    addTranslationClientandCasesToPage() {
        const translationClient = this.dataStore.get('translationClient');
        if (translationClient == null) {
            return;
        }

        document.getElementById('translation-client-name').innerText = translationClient.translationClientName;
        document.getElementById('translation-client-type').innerText = "Client type: " + translationClient.clientType;

        const translationCaseList = this.dataStore.get('translationCaseList');
        if (translationCaseList == null) {
            document.getElementById('translation-cases-display').innerText = "No translation cases found";
            return;
        }

        const translationCaseDisplay = document.getElementById('translation-cases-display');
        translationCaseDisplay.innerHTML = this.getHTMLForTranslationCases(translationCaseList);
    }

        /**
     * Create appropriate HTML for displaying translation cases on the page.
     * @param translationCaseList An array of translation case objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForTranslationCases(translationCaseList) {
        if (translationCaseList.length === 0) {
            return '<h4>No translation cases found</h4>';
        }
        let html = '<table><tr><th>Case Nickname</th><th>Project Type</th><th>Open</th></tr>';
        for (const translationCase of translationCaseList) {
            html += `
            <tr>
                <td>
                    <a href="translationCase.html?id=${translationCase.translationCaseId}">${translationCase.caseNickname}</a>
                </td>
                <td>${translationCase.projectType}</td>
                <td>${translationCase.openCase}</td>
            </tr>`;
        }
        html += '</table>';

        return html;
    }

    /**
     * Method to run when the archive translation client button is pressed. Call the TranslationTrackerService to archive the translation client.
     */
    async archiveTranslationClient() {
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const translationClient = this.dataStore.get('translationClient');
        if (translationClient == null) {
            return;
        }

        const archivedCase = await this.client.archiveTranslationClient(translationClient.translationClientId, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');           
        });

        window.location.href = '/index.html';
    }


}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewTranslationClient = new ViewTranslationClient();
    viewTranslationClient.mount();
};

window.addEventListener('DOMContentLoaded', main);
