import { Component, Input, OnInit } from "@angular/core";
import { Disc, DiscType } from "../disc";

@Component({
    selector: 'disc',
    templateUrl: './disc.component.html',
    styleUrls: ['./disc.component.css']
})
export class DiscComponent implements OnInit {

    @Input() disc: Disc;

    constructor() { }

    ngOnInit() {
        if (this.disc) {
            console.log(this.disc);
        }
    }
}
