import { Component, Input } from '@angular/core';
import { MatToolbar } from "@angular/material/toolbar";
import { MatIconButton } from "@angular/material/button";
import { MatIcon } from "@angular/material/icon";
import { Menu } from "primeng/menu";
import { MenuItem } from "primeng/api";
import { MatDialog, MatDialogModule } from "@angular/material/dialog";
import { NewCollectionDialogComponent } from "../new-collection-dialog/new-collection-dialog.component";

@Component({
  selector: 'app-top-menu-bar',
  standalone: true,
  imports: [
    MatToolbar,
    MatIconButton,
    MatIcon,
    Menu,
  ],
  templateUrl: './top-menu-bar.component.html',
  styleUrl: './top-menu-bar.component.scss'
})
export class TopMenuBarComponent {

  @Input()
  public menuItems?: MenuItem[] = [{
    label: "New collection",
    command: () => this.dialog.open(NewCollectionDialogComponent, {
      height: "400px",
      width: "600px",
    })
  }];

  constructor(private dialog: MatDialog) {}

}
