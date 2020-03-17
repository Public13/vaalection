import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import './mkd-service.js';

class TemplateView extends PolymerElement {

    static get template() {
        return html`
            <style>
                .common-padding {
                  padding-left: 7px;
                }
            </style>
            <div>
                <div class="common-padding"><p>Выберите услугу для редактирования</p></div>
                <!-- Имя проперти видоизменяется до атрибута с тире -->
                <mkd-service id="service1" service-name="FWD Uncond"></mkd-service>
                <mkd-service id="service2" service-name="FWD Busy"></mkd-service>
            </div>`;
    }

    static get is() {
        return 'template-view';
    }
}

customElements.define(TemplateView.is, TemplateView);

