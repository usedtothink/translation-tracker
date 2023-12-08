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
        this.dataStore.add
        this.header = new Header(this.dataStore);
        console.log("viewtranslationcase constructor");
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
        document.getElementById('payment-record').innerText = "(loading payment record...)";
        const paymentRecord = await this.client.getPaymentRecord(translationCaseId);
        this.dataStore.set('paymentRecord', paymentRecord);
    }

    /**
     * Add the header to the page and load the TranslationTrackerClient.
     */
    mount() {
        document.getElementById('add-progress-update').addEventListener('click', this.addProgressUpdate);

        document.getElementById('update-translation-case').addEventListener('click', this.redirectToUpdateTranslationCase);

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

        const paymentRecord = this.dataStore.get('paymentRecord');
        if (paymentRecord == null) {
            return;
        }

        document.getElementById('case-nickname').innerText = "Case nickname: " + translationCase.caseNickname;
        document.getElementById('project-type').innerText = "Project type: " + translationCase.projectType;

        if (!Object.is(translationCase.translationClientId, null)) {
            document.getElementById('translation-client-id').innerText = "Client id: " + translationCase.translationClientId;
        }
        if (!Object.is(translationCase.sourceTextTitle, null)) {
            document.getElementById('source-text-title').innerText = "Source text title: " + translationCase.sourceTextTitle;
        }
        if (!Object.is(translationCase.sourceTextAuthor, null)) {
            document.getElementById('source-text-author').innerText = "Source text author: " + translationCase.sourceTextAuthor;
        }
        if (!Object.is(translationCase.translatedTitle, null)) {
            document.getElementById('translated-title').innerText = "Translated title: " + translationCase.translatedTitle;
        }
        if (!Object.is(translationCase.dueDate, null)) {
            document.getElementById('due-date').innerText = "Due date: " + translationCase.dueDate;
        }
        if (!Object.is(translationCase.startDate, null)) {
            document.getElementById('start-date').innerText = "Start date: " + translationCase.startDate;
        }
        if (!Object.is(translationCase.endDate, null)) {
            document.getElementById('end-date').innerText = "End date: " + translationCase.endDate;
        }
        if (!Object.is(translationCase.openCase, null)) {
            document.getElementById('open-case').innerText = "Still open: " + translationCase.openCase;
        }
        if (!Object.is(translationCase.rushJob, null)) {
            document.getElementById('rush-job').innerText = "Rush job: " + translationCase.rushJob;
        }
        if (!Object.is(translationCase.totalWorkingHours, null)) {
            document.getElementById('total-working-hours').innerText = "Total working hours: " + translationCase.totalWorkingHours;
        }
        if (!Object.is(translationCase.wordsPerHour, null)) {
            document.getElementById('words-per-hour').innerText = "Words per hour: " + translationCase.wordsPerHour;
        }
        document.getElementById('progress-log').innerText = "Progress log: ";

        let progressUpdateHtml = '';
        let progressUpdate;
        let progressUpdateCount = 1;
        if (translationCase.progressLog != null) {
            for (progressUpdate of translationCase.progressLog) {
                progressUpdateHtml += '<div class="progress-update">' + 
                '<b>' + 'Progress update #' + progressUpdateCount + '</b>' + '<br>' + 
                'Word count: ' + progressUpdate.wordCount + '<br>' + 
                'Start date: ' + progressUpdate.startDate + '<br>' +
                'Start time: ' + progressUpdate.startTime + '<br>' +
                'End date: ' + progressUpdate.endDate  + '<br>' +
                'End time: ' + progressUpdate.endTime  + '<br>' +
                'Notes: ' + progressUpdate.notes  + '<br>' +
                '</div>';
                progressUpdateCount++;
            }
        } else {
            progressUpdateHtml += '<div class="progress-update">' + "No progress updates available" + '</div>';
        }
        document.getElementById('progress-update').innerHTML = progressUpdateHtml;
        
        document.getElementById('payment-record').innerText = "Payment record: ";

        document.getElementById('case-paid').innerText = "Paid: ";
        if (!Object.is(paymentRecord.casePaid, null)) {
            document.getElementById('case-paid').innerText = " " + paymentRecord.casePaid;
        }

        document.getElementById('payment-date').innerText = "Payment date: ";
        if (!Object.is(paymentRecord.paymentDate, null)) {
            document.getElementById('payment-date').innerText += " " + paymentRecord.paymentDate;
        } 

        document.getElementById('on-time').innerText = "On-time payment: ";
        if (!Object.is(paymentRecord.onTime, null)) {
            document.getElementById('on-time').innerText += " " + paymentRecord.onTime;
        } 
        
        document.getElementById('gross-payment').innerText = "Gross pay: ";
        if (!Object.is(paymentRecord.grossPayment, null)) {
            document.getElementById('gross-payment').innerText += " " + paymentRecord.grossPayment;
        }

        document.getElementById('tax-rate').innerText = "Tax rate: ";
        if (!Object.is(paymentRecord.taxRate, null)) {
            document.getElementById('tax-rate').innerText += " " + paymentRecord.taxRate;
        }

        document.getElementById('pay-rate').innerText = "Pay rate: ";
        if (!Object.is(paymentRecord.payRate, null)) {
            document.getElementById('pay-rate').innerText = "Pay rate: " + paymentRecord.payRate;
        }

        if (!Object.is(paymentRecord.payRateUnit, null)) {
            document.getElementById('pay-rate').innerText += " " + paymentRecord.payRateUnit;
        }

        document.getElementById('word-count-payment').innerText = "Word count: "
        if (!Object.is(paymentRecord.wordCount, null)) {
            document.getElementById('word-count-payment').innerText += " " + paymentRecord.wordCount;
        }

        if (!Object.is(paymentRecord.wordCountUnit, null)) {
            document.getElementById('word-count-payment').innerText += " " + paymentRecord.wordCountUnit;
        }
    }

    /**
     * Method to run when the add progress update submit button is pressed. Call the TranslationTrackerService to add a progress update to the
     * translation case.
     */
    async addProgressUpdate() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const translationCase = this.dataStore.get('translationCase');
        if (translationCase == null) {
            return;
        }

        document.getElementById('add-progress-update').innerText = 'Adding...';
        
        const translationCaseId = translationCase.translationCaseId;
        const startDate = document.getElementById('start-date-update').value;
        const startTime = document.getElementById('start-time').value;
        const endDate = document.getElementById('end-date-update').value;
        const endTime = document.getElementById('end-time').value;
        const wordCount = document.getElementById('word-count').value;
        const notes = document.getElementById('notes').value;

        const progressUpdate = await this.client.addProgressUpdate(translationCaseId, startDate, startTime, endDate, endTime, wordCount, notes, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');           
        });

        this.dataStore.set('translationCase', progressUpdate);

        document.getElementById('add-progress-update').innerText = 'Add progress update';
        document.getElementById("add-progress-update-form").reset();
    }

    /**
     * When the button is clicked, redirect to the update translationCase page.
     */
    redirectToUpdateTranslationCase(translationCaseId) {
        window.location.href = `/updateTranslationCase.html?id=${translationCaseId}`;
    }
}



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewTranslationCase = new ViewTranslationCase();
    viewTranslationCase.mount();
};

window.addEventListener('DOMContentLoaded', main);
