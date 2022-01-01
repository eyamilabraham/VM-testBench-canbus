void rec_Serial() {

  while (Serial.available()) {

    char dat = Serial.read();
    
    //dat += dat;
    if (dat == 'd') {

      digitalWrite(DIRECT_PIN, 0);

    }
    else if (dat == 'm') {

      digitalWrite(DIRECT_PIN, 1);
      digitalWrite(REVERSE_PIN, 1);
    }

    else if (dat == 'r') {

      digitalWrite(REVERSE_PIN, 0);
    }
    else if (dat == 'n') {

      digitalWrite(ON_OFF_PIN, 0);
    }

    else if (dat == 'f') {
      
      digitalWrite(DIRECT_PIN, 1);
      digitalWrite(REVERSE_PIN, 1);
      digitalWrite(ON_OFF_PIN, 1);
    }
  }
}
