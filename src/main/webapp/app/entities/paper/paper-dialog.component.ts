import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Response} from '@angular/http';

import {Observable} from 'rxjs/Rx';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {Paper} from './paper.model';
import {PaperPopupService} from './paper-popup.service';
import {PaperService} from './paper.service';
import {RfbEvent, RfbEventService} from '../rfb-event';
import {ResponseWrapper} from '../../shared';
import {User} from '../../shared/user/user.model';
import {UserService} from '../../shared/user/user.service';

@Component({
    selector: 'jhi-paper-dialog',
    templateUrl: './paper-dialog.component.html'
})
export class PaperDialogComponent implements OnInit {

    Paper: Paper;
    isSaving: boolean;
    rfbevents: RfbEvent[];
    users: User[];
    attendanceDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private PaperService: PaperService,
        private rfbEventService: RfbEventService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.rfbEventService.query()
            .subscribe((res: ResponseWrapper) => { this.rfbevents = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.userService.findByAuthority('ROLE_RUNNER')
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        if (typeof this.Paper.rfbEventDTO === 'undefined') {
            this.Paper.rfbEventDTO = new RfbEvent();
            this.Paper.userDTO = new User();
        }
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.Paper.id !== undefined) {
            this.subscribeToSaveResponse(
                this.PaperService.update(this.Paper));
        } else {
            this.subscribeToSaveResponse(
                this.PaperService.create(this.Paper));
        }
    }

    private subscribeToSaveResponse(result: Observable<Paper>) {
        result.subscribe((res: Paper) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Paper) {
        this.eventManager.broadcast({ name: 'PaperListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackRfbEventById(index: number, item: RfbEvent) {
        return item.id;
    }

    trackRfbUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-paper-popup',
    template: ''
})
export class PaperPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private PaperPopupService: PaperPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.PaperPopupService
                    .open(PaperDialogComponent as Component, params['id']);
            } else {
                this.PaperPopupService
                    .open(PaperDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
