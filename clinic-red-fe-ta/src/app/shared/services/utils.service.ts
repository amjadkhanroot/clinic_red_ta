import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { AppConfig } from '../../configs/app.config';
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private snackBar: MatSnackBar, private toastrService: ToastrService) {
  }

  showToastr(title: string, description: any, action?: 'success'|'error'|'info'|'warning'): void {

    switch (action){
      case 'success':
        this.toastrService.success(description, title,{
          timeOut: 3000,
        });
        break;
      case 'error':
        this.toastrService.error(description, title, {
          timeOut: 5000,
        });
        break;
      case 'info':
        this.toastrService.info(description, title,{
          timeOut: 3000,
        });
        break;
      case 'warning':
        this.toastrService.warning(description, title,{
          timeOut: 5000,
        });
        break;

      default:
        this.toastrService.show(description, title,{
          timeOut: 3000,
        });
        break;
    }
  }

  showSnackBar(name: string, panelClass: string, action?: string): void {
    const config: any = new MatSnackBarConfig();
    config.duration = panelClass === 'warning-snack-bar' ? 50000 : AppConfig.snackBarDuration;
    config.horizontalPosition = 'right';
    config.verticalPosition = 'top';
    config.panelClass = panelClass;
    this.snackBar.open(name, action, config);
  }

}
