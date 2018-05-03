import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { CreateAccount } from './services/createPlayerAccount/createAccount.service';
import { GameService } from './services/game/game.service';
import { BoardComponent } from './entities/board/component/board.component';
import { DiscComponent } from './entities/disc/component/disc.component';
import { ScoreComponent } from './entities/score/score.component';


@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    DiscComponent,
    ScoreComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
