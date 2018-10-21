import {Component, Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Paper} from './paper.model';
import {PaperService} from './paper.service';

@Injectable()
export class PaperPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private paperService: PaperService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.paperService.find(id).subscribe((rfbEventAttendance) => {
                    if (rfbEventAttendance.attendanceDate) {
                        rfbEventAttendance.attendanceDate = {
                            year: rfbEventAttendance.attendanceDate.getFullYear(),
                            month: rfbEventAttendance.attendanceDate.getMonth() + 1,
                            day: rfbEventAttendance.attendanceDate.getDate()
                        };
                    }
                    this.ngbModalRef = this.paperModalRef(component, rfbEventAttendance);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.paperModalRef(component, new Paper());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    paperModalRef(component: Component, rfbEventAttendance: Paper): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.rfbEventAttendance = rfbEventAttendance;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
