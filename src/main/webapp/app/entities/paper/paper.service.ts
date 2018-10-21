import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {SERVER_API_URL} from '../../app.constants';

import {JhiDateUtils} from 'ng-jhipster';

import {Paper} from './paper.model';
import {createRequestOption, ResponseWrapper} from '../../shared';

@Injectable()
export class PaperService {

    private resourceUrl = SERVER_API_URL + 'api/paper';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(Paper: Paper): Observable<Paper> {
        return this.http.post(this.resourceUrl, Paper).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(Paper: Paper): Observable<Paper> {
        const copy = this.convert(Paper);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Paper> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.attendanceDate = this.dateUtils.convertLocalDateFromServer(entity.attendanceDate);
    }

    private convert(Paper: Paper): Paper {
        const copy: Paper = Object.assign({}, Paper);
        copy.attendanceDate = this.dateUtils.convertLocalDateToServer(Paper.attendanceDate);
        return copy;
    }
}
