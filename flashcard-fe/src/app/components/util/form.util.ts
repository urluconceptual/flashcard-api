import {FormGroup} from "@angular/forms";
import {MessageService} from "primeng/api";

export class FormUtil {
    static isControlInvalid(form: FormGroup, controlName: string, validationName: string): boolean {
        const formControl = form.controls[controlName];
        return formControl.invalid && formControl.dirty && formControl.errors?.[validationName];
    }

    static isFormInvalid(form: FormGroup): boolean {
      return !form.valid;
    }

    static showErrorMessage(messageService: MessageService) {
      messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Form is not correctly filled in. Review and try again!'
      });
    }
}
