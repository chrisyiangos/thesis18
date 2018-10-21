import {Routes} from '@angular/router';

import {UserRouteAccessService} from '../../shared';

import {PaperComponent} from './paper.component';
import {PaperDetailComponent} from './paper-detail.component';
import {PaperPopupComponent} from './paper-dialog.component';
import {PaperDeletePopupComponent} from './paper-delete-dialog.component';

export const PaperRoute: Routes = [
    {
        path: 'paper',
        component: PaperComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'Papers'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'paper/:id',
        component: PaperDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'Papers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const PaperPopupRoute: Routes = [
    {
        path: 'paper-new',
        component: PaperPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'Papers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'paper/:id/edit',
        component: PaperPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'Papers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'paper/:id/delete',
        component: PaperDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'Papers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
