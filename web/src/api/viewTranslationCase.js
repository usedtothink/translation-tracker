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
        this.bindClassMethods(['clientLoaded', 'mount', 'addTranslationCaseToPage', 'addTranslationCaseUpdate'], this);
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
        const playlist = await this.client.getPlaylist(playlistId);
        this.dataStore.set('playlist', playlist);
        document.getElementById('songs').innerText = "(loading songs...)";
        const songs = await this.client.getPlaylistSongs(playlistId);
        this.dataStore.set('songs', songs);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('add-song').addEventListener('click', this.addTranslationCaseUpdate);

        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
        this.clientLoaded();
    }

    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addTranslationCaseToPage() {
        const playlist = this.dataStore.get('playlist');
        if (playlist == null) {
            return;
        }

        document.getElementById('playlist-name').innerText = playlist.name;
        document.getElementById('playlist-owner').innerText = playlist.customerName;

        let tagHtml = '';
        let tag;
        for (tag of playlist.tags) {
            tagHtml += '<div class="tag">' + tag + '</div>';
        }
        document.getElementById('tags').innerHTML = tagHtml;
    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the MusicPlaylistService to add a song to the
     * playlist.
     */
    async addTranslationCaseUpdate() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const playlist = this.dataStore.get('playlist');
        if (playlist == null) {
            return;
        }

        document.getElementById('add-song').innerText = 'Adding...';
        const asin = document.getElementById('album-asin').value;
        const trackNumber = document.getElementById('track-number').value;
        const playlistId = playlist.id;

        const songList = await this.client.addSongToPlaylist(playlistId, asin, trackNumber, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');           
        });

        this.dataStore.set('songs', songList);

        document.getElementById('add-song').innerText = 'Add Song';
        document.getElementById("add-song-form").reset();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewPlaylist = new ViewPlaylist();
    viewPlaylist.mount();
};

window.addEventListener('DOMContentLoaded', main);
