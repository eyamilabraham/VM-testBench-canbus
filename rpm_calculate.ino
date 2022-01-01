void rpm_calculate()
  {
  
      muestreoActualIntR = millis();                     // se asigna el tiempo de ejecuciòn a el muestreo actual
      muestreoActualIntL = millis();                     // se asigna el tiempo de ejecuciòn a el muestreo actual

      Wr = InterruptCount *((2*3.141516)/ENCODER_NUMBER)*frecuencyR;          // frecuencia angular Rad/s
      Wl = InterruptCount *((2*3.141516)/ENCODER_NUMBER)*frecuencyL;          // frecuencia angular Rad/s

           Serial.write(0x4B);
           Serial.print((int)Wr);            
           Serial.write(0x48);
           Serial.print((int)Wl);           
           
  }
