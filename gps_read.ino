/*void gps_read() {

unsigned long age;

  while (Serial1.available()) {

    char codeSat = Serial1.read();
    if (gps.encode(codeSat))
    {
      Altitude = gps.altitude.meters();
      speedGps = gps.speed.kmph();  
      sats = gps.satellites.value(); 
       if (sats > 0 ){
        stateSat = true;
       }   

    }

  }
  if (Serial.available())
  {
    char Received = Serial.read();

    if (Received == 'D')         //If the first character of "Received" is "0" //Distance start mode
    {

    }
    if (Received == 'd')        //If the first character of "Received" is "1"   //distance finish mode
    {


    }

  }


  
   // Serial.write(0x41);
   // Serial.write((int)Altitude);
     Serial.write(0x4B);
     Serial.write(speedGps);            //SpeedGps
     Serial.write(0x48);
     Serial.write(distance);            //Travel
   //Serial.write(0x57);
   //Serial.write(sats);                //Available Satellite
   //Serial.write(0x4A);
   //Serial.write(stateSat);            //GpsState
 
}*/
