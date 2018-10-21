import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Rx';
import {JhiEventManager} from 'ng-jhipster';

import {Paper} from './paper.model';
import {PaperService} from './paper.service';

@Component({
    selector: 'jhi-paper-detail',
    templateUrl: './paper-detail.component.html'
})
export class PaperDetailComponent implements OnInit, OnDestroy {

    Paper: Paper;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private PaperService: PaperService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPapers();
    }

    load(id) {
        this.PaperService.find(id).subscribe((Paper) => {
            this.Paper = Paper;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPapers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'PaperListModification',
            (response) => this.load(this.Paper.id)
        );
    }
}
