void procesMPU() {
 
//  Calibracion
  ax -= angle_pitch_acc_cal;
  ay -= angle_roll_acc_cal;
  az -= angle_yaw_acc_cal;
  az = az + 16384;
 
  // Calculo w
  pitchGyro = (gx - gyro_X_cal) / 65.5; // 65.5: si leo 65.5 en raw, significa que gira a 1º/s
  rollGyro = (gy - gyro_Y_cal) / 65.5;
  yawGyro = (gz - gyro_Z_cal) / 65.5;
 
  //Calculo ang
  angulo_pitch += pitchGyro * tiempo_ejecucion / 1000 ;
  angulo_roll += rollGyro * tiempo_ejecucion / 1000 ;
  angulo_pitch += angulo_roll * sin((gz - gyro_Z_cal) * tiempo_ejecucion  * 0.000000266);      // tiempo_ejecucion/1000 /65.5 * PI/180
  angulo_roll -= angulo_pitch * sin((gz - gyro_Z_cal) * tiempo_ejecucion  * 0.000000266);
 
  acc_total_vector = sqrt(pow(ay, 2) + pow(ax, 2) + pow(az, 2));
  angle_pitch_acc = asin((float)ay / acc_total_vector) * 57.2958;     // 57.2958 = Conversion de radianes a grados 180/PI
  angle_roll_acc = asin((float)ax / acc_total_vector) * -57.2958;
 
  angulo_pitch = angulo_pitch * 0.98 + angle_pitch_acc * 0.02;      // Corregimos en drift con filtro complementario
  angulo_roll = angulo_roll * 0.98 + angle_roll_acc * 0.02;

  float ax_m_s2 = (ax * (9.81 /16384)) / 9.8111638;
  float ay_m_s2 = (ay * (9.81 /16384)) / 9.8111638;

  float az_m_s2 = (az * (9.81 /16384)) / 9.8111638;

  int  valueInt_az = (int)az_m_s2 ;
  double decimal_z = (valueInt_az - az_m_s2 ) * 10;
  int valueDec_z = (int)decimal_z;

  int  valueInt_ay = (int)ay_m_s2 ;
  double decimal_y = (valueInt_ay - ay_m_s2) * 10;
  int valueDec_y = (int)decimal_y;

  int  valueInt_ax = (int)ax_m_s2 ;
  double decimal_x = (valueInt_ax - ax_m_s2) * 10;
  int valueDec_x = (int)decimal_x;
  
  Serial.write(0X6C);
  Serial.write(int (abs(angulo_pitch)));
  Serial.write(0X4C);
  Serial.write(int (abs(angulo_roll)));
  Serial.write(0x77);
  Serial.write(abs(valueInt_ax));
  Serial.write(abs(valueDec_x));
  Serial.write(0x78);
  Serial.write(abs(valueInt_ay));
  Serial.write(abs(valueDec_y));
} 
