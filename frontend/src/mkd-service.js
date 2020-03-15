import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@polymer/paper-input/paper-input.js';
import '@fooloomanzoo/datetime-picker/datetime-picker';

class MkdService extends PolymerElement {

    static get template() {
        return html`
            <style include="mkd-service-style">
                .my-border-style {
                  border: 2px solid grey;
                }
            </style>
            <div>
                <paper-input id="inputId" value="{{userInput}}"></paper-input>
                <button id="helloButton" class="normal-color" on-click="sayHello">Say hello</button>
                <div id="greeting">[[greeting]]</div>
                <datetime-picker></datetime-picker>
            </div>`;
    }

    static get is() {
        return 'mkd-service';
    }
}

customElements.define(MkdService.is, MkdService);

