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
        this.serviceName = 'Hello!';
    }

    static get template() {
        return html`
            <style include="bootstrap mkd-service-style julia">
                .my-border-style {
                  border: 2px solid grey;
                }
            </style>
            <div>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-6">
                    <figure class="effect-julia">
                        <div class="small-box bg-navy">
                            <div class="small-box-header">
                                [[serviceName]]
                                <i class="fa fa-phone-square header-icon"></i>                            
                            </div>
            
                            <div class="inner">
                                <div class="">
                                    [[greeting]]
                                </div>
                            </div>
                            <div class="small-icon">
                                <i class="fa fa-group"></i>
                            </div>
                        </div>        
                        <figcaption onclick="openModal">
                            <div>
                                <p>
                                    Setup <i class="fa fa-arrow-circle-right"></i>
                                </p>
                            </div>
                        </figcaption>
                    </figure>
                </div>      
<!--                <paper-input id="inputId" value="{{userInput}}"></paper-input>-->
<!--                <button id="helloButton" class="normal-color" on-click="sayHello">Say hello</button>-->
<!--                <div id="greeting">[[greeting]]</div>-->
<!--                <datetime-picker></datetime-picker>-->
            </div>`;
    }

    static get is() {
        return 'mkd-service';
    }
}

customElements.define(MkdService.is, MkdService);

