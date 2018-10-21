import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {RfbloyaltySharedModule} from '../../shared';
import {
    PaperComponent,
    PaperDeleteDialogComponent,
    PaperDeletePopupComponent,
    PaperDetailComponent,
    PaperDialogComponent,
    PaperPopupComponent,
    PaperPopupRoute,
    PaperPopupService,
    PaperRoute,
    PaperService,
} from './';

const ENTITY_STATES = [
    ...PaperRoute,
    ...PaperPopupRoute,
];

@NgModule({
    imports: [
        RfbloyaltySharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PaperComponent,
        PaperDetailComponent,
        PaperDialogComponent,
        PaperDeleteDialogComponent,
        PaperPopupComponent,
        PaperDeletePopupComponent,
    ],
    entryComponents: [
        PaperComponent,
        PaperDialogComponent,
        PaperPopupComponent,
        PaperDeleteDialogComponent,
        PaperDeletePopupComponent,
    ],
    providers: [
        PaperService,
        PaperPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RfbloyaltyPaperModule {}
