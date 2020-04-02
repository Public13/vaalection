import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import './mkd-service.js';
import './colored-div.js';

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
                <mkd-service id="service1" service-name="FWD Uncond"></mkd-service>
                <mkd-service id="service2" service-name="FWD Busy"></mkd-service>
                
                <div class="common-padding">
                    <colored-div>
                        <p>Alarm</p>
                    </colored-div>                    
                    <colored-div background="green">
                        <div>It's OK</div>             
                    </colored-div>                    
                </div>
            </div>
                        
            <div class="common-padding" style="display: none">
                <table>
                    <tr on-click="processElement">
                        <th>First name</th><th>Last name</th><th>Email</th>
                    </tr>
                    
                        <template is="dom-repeat" items="[[developers]]">
                            <tr on-click="handleClick" id="[[item.firstname]]">
                                <td>{{item.firstname}}</td>
                                <td>{{item.lastname}}</td>
                                <td>{{item.email}}</td>
                            </tr>
                        </template>
                   
                </table>
            </div>
`;
    }

    static get is() {
        return 'template-view';
    }
}

customElements.define(TemplateView.is, TemplateView);

