import {Component} from '@angular/core';
import {InputText} from "primeng/inputtext";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {FloatLabel} from "primeng/floatlabel";
import {Password} from "primeng/password";
import {Button, ButtonDirective, ButtonLabel} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {RouterLink} from "@angular/router";
import {AuthenticationService} from "../../../services/authentication.service";
import {UserRestService} from "../../../services/user-rest.service";
import {UserCredentials} from "../../../models/user-credentials";
import {MessageService} from "primeng/api";
import {Toast} from "primeng/toast";
import {NgIf} from "@angular/common";
import {FormUtil} from "../../util/form.util";

@Component({
  selector: 'app-sign-in-form',
  standalone: true,
  imports: [
    InputText,
    FormsModule,
    FloatLabel,
    Password,
    ButtonDirective,
    ButtonLabel,
    Ripple,
    Button,
    RouterLink,
    ReactiveFormsModule,
    Toast,
    NgIf
  ],
  templateUrl: './sign-in-form.component.html',
  styleUrl: './sign-in-form.component.scss'
})
export class SignInFormComponent {
  protected form = new FormGroup({
    username: new FormControl('', [
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.required]),
  });

  constructor(private authenticationService: AuthenticationService,
              private userRestService: UserRestService,
              private messageService: MessageService) {
  }

  public switchToSignUp(): void {
    this.authenticationService.isSignIn = false;
  }

  public submit(): void {
    if (FormUtil.isFormInvalid(this.form)) {
      FormUtil.showErrorMessage(this.messageService);
      return;
    }
    this.authenticationService.signIn(this.form.value as UserCredentials, this.messageService);
  }

  public isControlInvalid(controlName: string, validationName: string): boolean {
    return FormUtil.isControlInvalid(this.form, controlName, validationName);
  }
}
