import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';

class ColoredDiv extends PolymerElement {

    static get properties() {
        return {
            background: {
                type: String,
                value: "red"
            }
        }
    }

    static get template() {
        return html`
            <style>
                .normal-size {
                    width: 60px;
                    padding-left: 5px;
                }
            </style>
            <div class="normal-size" style$="background-color: [[background]];">
               <slot></slot>
            </div>`;
    }
    static get is() {
        return 'colored-div';
    }
}
customElements.define(ColoredDiv.is, ColoredDiv);

