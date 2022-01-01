/*
 * funcion de lectura del sensor humedad y temperatura
 */
void bme_read(){

  int tempAmb;
  int hum;
  int pressure;

    tempAmb = bme.readTemperature();
    hum = bme.readHumidity();
    int pres = bme.readPressure()/1000;
    //pressure = abs pres;
    pressure = pres;
    pressure = abs(pressure);

    Serial.write(0x74);
    Serial.write(tempAmb);                  //temperature
    Serial.write(0x68);
    Serial.write(hum);                      //humidity
    Serial.write(0x70);
    Serial.print(pressure);                 //atm Pressure
}
