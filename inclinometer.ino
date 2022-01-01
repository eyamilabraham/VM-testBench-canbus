void inclinometer () {

  Wire.beginTransmission(MPU6050_address);               // Empezamos comunicación
  Wire.write(0x3B);
  Wire.endTransmission();
  Wire.requestFrom(MPU6050_address, 14);                   // Pedimos 14 bytes
  while (Wire.available() < 14);                           // Espramos hasta recibir la informacion
 
  ax = Wire.read() << 8 | Wire.read();
  ay = Wire.read() << 8 | Wire.read();
  az = Wire.read() << 8 | Wire.read();
  temperature = Wire.read() << 8 | Wire.read();
  gx = Wire.read() << 8 | Wire.read();
  gy = Wire.read() << 8 | Wire.read();
  gz = Wire.read() << 8 | Wire.read();
  

}
