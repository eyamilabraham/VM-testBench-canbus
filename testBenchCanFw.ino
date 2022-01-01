/*
 * @brief
 *  El sistema adquiere datos de bateria, motores, temp ambiente, humedad relativa, presion atmosferica
 *  Datos GPS, inclinacion, fuerza G.
 *  
 * Dev.:Camaño Nicolas E. & Elias Yamil Abraham
 * Date:8/2020
 * V_032021 Date: 03/2021
 * Test Bench
 * Firmware version: 1.1
 * 
 *Control de cambios:
 *Migracion de protocolo CAN de QS a PE
 * 
 */


#include "Arduino.h"
#include "System.h"


void setup() {

  init_components();
  Wire.begin();
  bme.begin(0x76);
  bme_read(); 
  mpu.initialize();
  init_gyro();
  Mpu_initialize();
  init_can();

  

}

void loop() {

  actualTimer = millis();
  //rpm_calculate();

  if (actualTimer - endTimerControllerL >= TIMERCONTROLLERL)
  {  
    MotorLeftPE();
    MotorRightPE();
    endTimerControllerL = actualTimer;
  }


  /*if (actualTimer - endTimerControllerR  >= TIMERCONTROLLERR)
  {
    MotorRightPE();
    endTimerControllerR = actualTimer;
  }*/

    if (actualTimer - endTimerBattery >= TIMERBATTERY)
  { 
    can_battery();
    endTimerBattery = actualTimer;
  }

  if (actualTimer - endTimerBmp >= TIMERBMP)
  {
    bme_read();
    endTimerBmp = actualTimer;
  }

 /* if (actualTimer - endTimerGps >= TIMERGPS) 
  {
    gps_read();
    endTimerGps = actualTimer;
  }*/


  if (actualTimer - endTimerImu >= TIMERIMU) 
  {
    inclinometer();
    procesMPU();
    endTimerImu = actualTimer;
  }

if (actualTimer - endTimerRecSerial >= TIMER_REC_SERIAL)
 {
    rec_Serial();
    endTimerRecSerial = actualTimer;
 }

 TpsValue = map(analogRead(A9), 0, 1023, 0,100);

  //conexion exitosa
  Serial.write(0x45); 

}
