import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import './mkd-service.js';

class TemplateView extends PolymerElement {

    static get template() {
        return html`
            <style>
                .template-border-style {
                  border: 2px solid grey;
                }
            </style>
            <div>
                <span><p>Hello, it's a simple template</p></span>
                <mkd-service id="service1"></mkd-service>
            </div>`;
    }

    static get is() {
        return 'template-view';
    }
}

customElements.define(TemplateView.is, TemplateView);

