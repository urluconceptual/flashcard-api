import {Component, Input} from '@angular/core';
import {MatToolbar} from "@angular/material/toolbar";
import {MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {Menu} from "primeng/menu";
import {MenuItem} from "primeng/api";
import {CollectionDialog} from "../collection-dialog/collection-dialog";
import {AuthenticationService} from "../../services/authentication.service";
import {Dialog} from "primeng/dialog";
import {Toolbar} from "primeng/toolbar";

@Component({
  selector: 'app-top-menu-bar',
  standalone: true,
  imports: [
    MatToolbar,
    MatIconButton,
    MatIcon,
    Menu,
    CollectionDialog,
    Dialog,
    Toolbar,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @Input()
  public menuItems?: MenuItem[] = [
    {
      label: "New collection",
      icon: "pi pi-plus",
      command: () => this.openCollectionDialog()
    },
    {
      label: "Sign out",
      icon: "pi pi-sign-out",
      command: () => this.authService.signOut()
    }];
  public collectionDialogVisible: boolean = false;

  constructor(private authService: AuthenticationService) {
  }

  public onCollectionDialogClose(): void {
    this.collectionDialogVisible = false;
  }

  private openCollectionDialog(): void {
    this.collectionDialogVisible = true;
  }
}
