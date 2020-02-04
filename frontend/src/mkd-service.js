import {PolymerElement, html} from '@polymer/polymer/polymer-element.js';
import '@polymer/paper-input/paper-input.js';
// import '../js/jquery-3.4.1.min.js';
// import '../js/datepicker.js';
// import '../js/datepicker.en.js';
// import '../js/test.js';

class MkdService extends PolymerElement {

    static get template() {
        return html`
            <div>
                <paper-input id="inputId" value="{{userInput}}"></paper-input>
                <button id="helloButton" on-click="sayHello">Say hello</button>
                <input type='text' class="datepicker-here" id="datepicker-here" data-position="right top" />
                <div id="greeting">[[greeting]]</div>
            </div>
            <script> 
            $('#datepicker-here').datepicker();
            alert('bbbb');
            scrim();
            </script>`;
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

