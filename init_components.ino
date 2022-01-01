void init_components(){
  Serial.begin(BAUD_RATE);              // conexion con PC
//  Serial1.begin(GPS_BAUD_RATE);                  // conexion con GPS

  //attachInterrupt(digitalPinToInterrupt(IR_SENSOR_RIGHT),REncoder,FALLING);                
  //attachInterrupt(digitalPinToInterrupt(IR_SENSOR_LEFT),LEncoder,FALLING);            

  pinMode(LED,OUTPUT); 
  pinMode(DIRECT_PIN,OUTPUT);
  pinMode(REVERSE_PIN,OUTPUT);
  pinMode(ON_OFF_PIN,OUTPUT);

  //la placa de reles trabaja con valores inversos para activar y desactivar
  //apago todo
  digitalWrite(DIRECT_PIN, 1);
  digitalWrite(REVERSE_PIN, 1);
  digitalWrite(ON_OFF_PIN,1);

  pinMode(LED,OUTPUT);
  analogWrite(LED,10);

}
