import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {Paper} from './paper.model';
import {PaperPopupService} from './paper-popup.service';
import {PaperService} from './paper.service';

@Component({
    selector: 'jhi-paper-delete-dialog',
    templateUrl: './paper-delete-dialog.component.html'
})
export class PaperDeleteDialogComponent {

    Paper: Paper;

    constructor(
        private PaperService: PaperService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.PaperService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'PaperListModification',
                content: 'Deleted an Event Attendance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-paper-delete-popup',
    template: ''
})
export class PaperDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private PaperPopupService: PaperPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.PaperPopupService
                .open(PaperDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
