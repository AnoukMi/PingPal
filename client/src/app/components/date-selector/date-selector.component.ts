import { Component, Output, EventEmitter  } from '@angular/core';

@Component({
  selector: 'app-date-selector',
  templateUrl: './date-selector.component.html',
  styleUrls: ['./date-selector.component.css']
})
export class DateSelectorComponent {
  selectedMonth : string ='';
  selectedDay : string ='';
  selectedYear : string ='';
  birthday:string='';
  @Output() birthdayChange: EventEmitter<string> =new EventEmitter<string>();
  onMonthChange(event: any) {
    this.selectedMonth = event.currentTarget.value;
    this.updateBirthday();
  }

  onDayChange(event: any) {
    this.selectedDay = event.currentTarget.value;
    this.updateBirthday();
  }

  onYearChange(event: any) {
    this.selectedYear = event.currentTarget.value;
    this.updateBirthday();
  }

  updateBirthday(){
    this.birthday=this.selectedMonth+"-"+this.selectedDay+"-"+this.selectedYear;
    this.birthdayChange.emit(this.birthday);
  }
}
