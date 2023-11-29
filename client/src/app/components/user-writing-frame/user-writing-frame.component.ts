import {Component, Input, OnInit, forwardRef, Output, EventEmitter, ViewChild, ElementRef} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import {MessageService} from "../../api";

@Component({
  selector: 'app-user-writing-frame',
  templateUrl: './user-writing-frame.component.html',
  styleUrls: ['./user-writing-frame.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => UserWritingFrameComponent),
      multi: true,
    },
  ],
})
export class UserWritingFrameComponent implements ControlValueAccessor, OnInit {
  @Input() placeholder: string = '';
  @Input() type: string = '';

  private innerValue: any = '';

  @Output() messageSent = new EventEmitter<string>();


  onChange: any = () => {};
  onTouched: any = () => {};

  constructor() {}

  ngOnInit() {}

  writeValue(value: any): void {
    if (value !== undefined) {
      this.innerValue = value;
    }
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    // You can implement this method if you need to support disabling the control.
  }

  onInput(event: any) {
    this.innerValue = event.target.value;
    this.onChange(this.innerValue);
    this.onTouched();
  }
}
