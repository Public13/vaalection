import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@polymer/paper-input/paper-input.js';
import '@fooloomanzoo/datetime-picker/datetime-picker';

class MkdService extends PolymerElement {

    static get properties() {
        return {
            serviceName: {
                type: String
            }
        }
    }

    constructor() {
        super();
        this.serviceName = 'Unknown';
    }

    static get template() {
        return html`
            <style include="bootstrap julia">
                .card-width {
                    width: 400px;
                }
                .setup-icon {
                    width: 15px;
                }
                .content-icon {
                    width: 45px;
                    height: 45px;
                }
            </style>
            <div>
                <div class="card-width">
                    <figure class="effect-julia" on-click="openModal">
                        <div class="small-box bg-navy">
                            <div class="small-box-header">
                                [[serviceName]]
                                <iron-icon icon="vaadin:phone" class="header-icon"></iron-icon>                            
                            </div>
            
                            <div class="inner">
                                <div class="">
                                    [[cardCaption]]
                                </div>
                            </div>
                            <div class="small-icon">                            
                                <iron-icon icon="vaadin:group" class="content-icon"></iron-icon>
                            </div>
                        </div>        
                        <figcaption>
                            <div>
                                <p>
                                    Настроить <iron-icon icon="vaadin:arrow-circle-right" class="setup-icon"></iron-icon>
                                </p>
                            </div>
                        </figcaption>
                    </figure>
                </div>      
            </div>
            
            <vaadin-dialog id="dialog" aria-label="polymer templates">
              <template>
                <style>
                    vaadin-button {
                        float: right;
                    }
                </style>
                <div>
                    <paper-input id="inputId" placeholder="Введите номер" value="{{number}}"></paper-input>
                    <datetime-picker datetime="{{date}}"></datetime-picker>
                    <div>
                        <vaadin-button id="applyButton" on-click="applyChanges">Применить</vaadin-button>
                    </div>
                </div>
              </template>
            </vaadin-dialog>`;
    }

    static get is() {
        return 'mkd-service';
    }

    openModal() {
        console.log("open dialog");
        this.$.dialog.opened = true;
    }

    afterServerUpdate() {
        this.setAttribute("id", this.serviceName);
    }
}

customElements.define(MkdService.is, MkdService);

