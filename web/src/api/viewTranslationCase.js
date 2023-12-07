import TranslationTrackerClient from '../api/translationTrackerClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view traanslation case page of the website.
 */
class ViewTranslationCase extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addTranslationCaseToPage', 'addProgressUpdate'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addTranslationCaseToPage);
        this.header = new Header(this.dataStore);
        console.log("viewtranslationcase constructor");
    }

    /**
     * Once the client is loaded, get the translation case metadata.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const translationCaseId = urlParams.get('translationCaseId');
        document.getElementById('case-nickname').innerText = "Loading translation case ...";
        const translationCase = await this.client.getTranslationCase(translationCaseId);
        this.dataStore.set('translationCase', translationCase);
        document.getElementById('paymentRecord').innerText = "(loading payment record...)";
        const paymentRecord = await this.client.getPaymentRecord(translationCaseId);
        this.dataStore.set('paymentRecord', paymentRecord);
    }

    /**
     * Add the header to the page and load the TranslationTrackerClient.
     */
    mount() {
        document.getElementById('add-song').addEventListener('click', this.addTranslationCaseUpdate);

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

        document.getElementById('case-nickname').innerText = translationCase.caseNickname;
        document.getElementById('project-type').innerText = translationCase.projectType;
        document.getElementById('translation-client-id').innerText = translationCase.translationClientId;
        document.getElementById('source-text-title').innerText = translationCase.sourceTextTitle;
        document.getElementById('source-text-author').innerText = translationCase.sourceTextAuthor;
        document.getElementById('translated-title').innerText = translationCase.translatedTitle;
        document.getElementById('due-date').innerText = translationCase.dueDate;
        document.getElementById('start-date').innerText = translationCase.startDate;
        document.getElementById('end-date').innerText = translationCase.endDate;
        document.getElementById('open-case').innerText = translationCase.openCase;
        document.getElementById('rush-job').innerText = translationCase.rushJob;
        document.getElementById('total-working-hours').innerText = translationCase.totalWorkingHours;
        document.getElementById('words-per-hour').innerText = translationCase.wordsPerHour;
        document.getElementById('progress-log').innerText = translationCase.progressLog;

        document.getElementById('case-paid').innerText = paymentRecord.casePaid;
        document.getElementById('payment-date').innerText = paymentRecord.paymentDate;
        document.getElementById('on-time').innerText = paymentRecord.onTime;
        document.getElementById('gross-payment').innerText = paymentRecord.grossPayment;
        document.getElementById('tax-rate').innerText = paymentRecord.taxRate;
        document.getElementById('pay-rate').innerText = paymentRecord.payRate;
        document.getElementById('pay-rate-unit').innerText = paymentRecord.payRateUnit;
        document.getElementById('word-count-payment').innerText = paymentRecord.wordCount;
        document.getElementById('word-count-unit').innerText = paymentRecord.wordCountUnit;

        let progressUpdateHtml = '';
        let progressUpdate;
        for (progressUpdate of translationCase.progressLog) {
            tagHtml += '<div class="progress-log">' + progressUpdate + '</div>';
        }
        document.getElementById('progress-log').innerHTML = progressUpdateHtml;
    }

    /**
     * Method to run when the add progress update submit button is pressed. Call the TranslationTrackerService to add a progress update to the
     * translation case.
     */
    async addProgressUpdate() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const playlist = this.dataStore.get('translationCase');
        if (translationCase == null) {
            return;
        }

        document.getElementById('add-progress-update').innerText = 'Adding...';
        
        const translationCaseId = translationCase.translationCaseId;
        const startDate = document.getElementById('album-asin').value;
        const startTime = document.getElementById('track-number').value;
        const endDate = document.getElementById('end-date');
        const endTime = document.getElementById('end-time');
        const wordCount = document.getElementById('word-count');
        const notes = document.getElementById('notes');

        const songList = await this.client.addProgressUpdate(translationCaseId, startDate, startTime, endDate, endTime, wordCount, notes, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');           
        });

        location.reload();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewtranslationcase = new ViewTranslationCase();
    ViewTranslationCase.mount();
};

window.addEventListener('DOMContentLoaded', main);
