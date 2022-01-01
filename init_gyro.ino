void init_gyro() {
  
  Wire.beginTransmission(MPU6050_address);
  Wire.write(0x6B);                           // PWR_MGMT_1 registro 6B hex
  Wire.write(0x00);                           // 00000000 para activar
  Wire.endTransmission();
  
  Wire.beginTransmission(MPU6050_address);
  Wire.write(0x1B);                           // GYRO_CONFIG registro 1B hex
  Wire.write(0x00);                           // 00001000: 150dps
  Wire.endTransmission();
  
  Wire.beginTransmission(MPU6050_address);
  Wire.write(0x1C);                          // ACCEL_CONFIG registro 1C hex
  Wire.write(0x00);                          // 00000000: +/- 2g
  Wire.endTransmission();
 
  Wire.beginTransmission(MPU6050_address);
  Wire.write(0x1A);                        // LPF registro 1A hex
  Wire.write(0x03);                        // 256Hz(0ms):0x00 - 188Hz(2ms):0x01 - 98Hz(3ms):0x02 - 42Hz(4.9ms):0x03 - 20Hz(8.5ms):0x04 - 10Hz(13.8ms):0x05 - 5Hz(19ms):0x06
  Wire.endTransmission();
}
