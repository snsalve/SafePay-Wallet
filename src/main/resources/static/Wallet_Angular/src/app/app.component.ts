import { Component } from '@angular/core';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html'
})

export class AppComponent { 

    // Creating date object
  todaysDate = new Date();

  constructor(){
    //setInterval -> asynchronous function
    setInterval( ()=>{
      this.todaysDate= new Date();
    },1000);
  }
}