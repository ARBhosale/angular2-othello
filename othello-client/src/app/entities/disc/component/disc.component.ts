import { Component, Input } from "@angular/core";
import { Disc, DiscType } from "../disc";

@Component({
    selector: 'disc',
    templateUrl: './disc.component.html',
    styleUrls: ['./disc.component.css']
})
export class DiscComponent {

    @Input() rowNumber: number;
    @Input() colNumber: number;
    @Input() discType: DiscType;


}