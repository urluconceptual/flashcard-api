import {Component} from '@angular/core';
import {Checkbox} from "primeng/checkbox";
import {ButtonDirective, ButtonLabel} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {InputText} from "primeng/inputtext";
import {SignInFormComponent} from "./sign-in-form/sign-in-form.component";
import {AuthenticationService} from "../../services/authentication.service";
import {NgIf} from "@angular/common";
import {SignUpFormComponent} from "./sign-up-form/sign-up-form.component";

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [
    Checkbox,
    ButtonDirective,
    Ripple,
    InputText,
    SignInFormComponent,
    ButtonLabel,
    NgIf,
    SignUpFormComponent
  ],
  templateUrl: './authentication.component.html',
  styleUrl: './authentication.component.scss'
})
export class AuthenticationComponent {
  constructor(public authenticationService: AuthenticationService) {
  }
}
