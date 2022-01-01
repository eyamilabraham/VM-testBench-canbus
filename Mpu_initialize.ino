void Mpu_initialize(){


 //==== Calibrar giroscopio
 
  for (cal_int = 0; cal_int < 3000 ; cal_int ++) {
    inclinometer ();      // Leemos los datos del MPU6050 3000 veces y //sacamos el valor medio para obtener los offset del giroscopio
    gyro_X_cal += gx;
    gyro_Y_cal += gy;
    gyro_Z_cal += gz;
    delayMicroseconds(1000);
  }
  gyro_X_cal = gyro_X_cal / 3000;       // valor medio de las 3000 muestras
  gyro_Y_cal = gyro_Y_cal / 3000;
  gyro_Z_cal = gyro_Z_cal / 3000;
 
  //==== Calibrar acelerometro
  for (cal_int = 0; cal_int < 3000 ; cal_int ++) {
   
    angle_pitch_acc_cal += ax;
    angle_roll_acc_cal += ay;
    angle_yaw_acc_cal += az;
  }
  angle_pitch_acc_cal = angle_pitch_acc_cal / 3000;
  angle_roll_acc_cal = angle_roll_acc_cal / 3000;
  angle_yaw_acc_cal = angle_yaw_acc_cal / 3000;
  accCalibOK = true;
 
  digitalWrite(LED, LOW);
  loop_timer = micros();
 
}
