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
        this.bindClassMethods(['clientLoaded', 'mount', 'addTranslationCasesAndClientsToPage', 'getHTMLForTranslationCases'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addTranslationCasesAndClientsToPage);
        this.header = new Header(this.dataStore);
    }

    /**
     * Once the client is loaded, get the translation case and translation client metadata.
     */
    async clientLoaded() {
        document.getElementById('translation-cases-display').innerText = "Loading translation cases...";
        document.getElementById('translation-clients-display').innerText = "Loading translation clients...";
        const translationCaseList = await this.client.getAllTranslationCases();
        this.dataStore.set('translationCaseList', translationCaseList);

        const translationClientList = await this.client.getAllTranslationClients();
        this.dataStore.set('translationClientList', translationClientList);
        document.getElementById('new-translation-case').href='/createTranslationCase.html';
        document.getElementById('new-translation-client').href='/createTranslationClient.html';
    }

    /**
     * Add the header to the page and load the TranslationTrackerClient.
     */
    mount() {
        this.header.addHeaderToPage();

        this.client = new TranslationTrackerClient();
        this.clientLoaded();
    }

    /**
     * When the translation case list is updated in the datastore, update the translation case metadata on the page.
     */
    addTranslationCasesAndClientsToPage() {
        const translationCaseList = this.dataStore.get('translationCaseList');
        if (translationCaseList == null) {
            return;
        }

        const translationClientList = this.dataStore.get('translationClientList');
        if (translationClientList == null) {
            return;
        }

        const translationCaseDisplay = document.getElementById('translation-cases-display');
        translationCaseDisplay.innerHTML = this.getHTMLForTranslationCases(translationCaseList);

        const translationClientDisplay = document.getElementById('translation-clients-display');
        translationClientDisplay.innerHTML = this.getHTMLForTranslationClients(translationClientList);
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
     * Create appropriate HTML for displaying translation clients on the page.
     * @param translationCaseList An array of translation client objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
        getHTMLForTranslationClients(translationClientList) {
            if (translationClientList.length === 0) {
                return '<h4>No translation clients found</h4>';
            }
            let html = '<table><tr><th>Client Name</th><th>Client Type</th></tr>';
            for (const translationClient of translationClientList) {
                if (!translationClient.translationClientId.startsWith("archived - ")) {
                    html += `
                    <tr>
                        <td>
                            <a href="translationClient.html?id=${translationClient.translationClientId}">${translationClient.translationClientName}</a>
                        </td>
                        <td>${translationClient.clientType}</td>
                    </tr>`;
                }
            }
            html += '</table>';
    
            return html;
        }


}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const frontPage = new FrontPage();
    frontPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
