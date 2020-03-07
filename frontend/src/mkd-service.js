import {PolymerElement, html} from '@polymer/polymer/polymer-element.js';
import '@polymer/paper-input/paper-input.js';
import '@fooloomanzoo/datetime-picker/datetime-picker';

class MkdService extends PolymerElement {

    static get template() {
        return html`
            <div>
                <paper-input id="inputId" value="{{userInput}}"></paper-input>
                <button id="helloButton" on-click="sayHello">Say hello</button>
                <div id="greeting">[[greeting]]</div>
                <datetime-picker></datetime-picker>
            </div>`;
    }

    static get is() {
        return 'mkd-service';
    }

    afterServerUpdate() {
        console.log("The new 'text' value is: " + this.text);
        //const picker = datepicker("#datepicker");
    }
}

customElements.define(MkdService.is, MkdService);

