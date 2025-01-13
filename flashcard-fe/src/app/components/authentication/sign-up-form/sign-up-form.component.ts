import {Component} from '@angular/core';
import {Button, ButtonDirective, ButtonLabel} from "primeng/button";
import {FloatLabel} from "primeng/floatlabel";
import {InputText} from "primeng/inputtext";
import {Password} from "primeng/password";
import {Ripple} from "primeng/ripple";
import {AuthenticationService} from "../../../services/authentication.service";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {UserRestService} from "../../../services/user-rest.service";
import {UserCredentials} from "../../../models/user-credentials";
import {NgIf} from "@angular/common";
import {Toast} from "primeng/toast";
import {MessageService} from "primeng/api";
import {FormUtil} from "../../util/form.util";

@Component({
  selector: 'app-sign-up-form',
  standalone: true,
  imports: [
    Button,
    ButtonDirective,
    ButtonLabel,
    FloatLabel,
    InputText,
    Password,
    Ripple,
    ReactiveFormsModule,
    NgIf,
    Toast
  ],
  templateUrl: './sign-up-form.component.html',
  styleUrl: './sign-up-form.component.scss'
})
export class SignUpFormComponent {
  protected form = new FormGroup({
    username: new FormControl('', [
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.required
    ]),
  });

  constructor(private authenticationService: AuthenticationService,
              private userRestService: UserRestService,
              private messageService: MessageService) {
  }

  public switchToSignIn(): void {
    this.authenticationService.isSignIn = true;
  }

  public submit(): void {
    if (FormUtil.isFormInvalid(this.form)) {
      FormUtil.showErrorMessage(this.messageService);
      return;
    }
    this.authenticationService.signUp(this.form.value as UserCredentials, this.messageService);
  }

  public isControlInvalid(controlName: string, validationName: string): boolean {
    return FormUtil.isControlInvalid(this.form, controlName, validationName);
  }
}
