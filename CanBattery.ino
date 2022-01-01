/*
 * funcion que lee can bus de bateria
 * en caso de cambio de bms se deben cambiar los ID de cada msj
 */

void can_battery() {
  
int idCanBattery;
float voltT;
int capT;
int SysStat1;
int SysStat2;
int volt1;
String msg;
String msg2;
float battTemp;
int ampMSB;
int ampLSB;

for (int i = 0; i < 5 ; i++) {
    if (mcp2515battery.readMessage(&canMsg) == MCP2515::ERROR_OK)
    {

      if  (canMsg.can_id == 2550267892)               // ID en valor decimal. en hexa 0x9801FFF4 (1 mensaje)
      {
        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
          volt1 = (canMsg.data[1] << 8) | (canMsg.data[0]);
          voltT = volt1 * 0.1;

          ampMSB = canMsg.data[3];
          ampLSB = canMsg.data[2];

          // int amp = (canMsg.data[3] << 8)| canMsg.data[2];
          // ampLSB = (amp - 32000) * 0.1;

          int cap1 = (canMsg.data[5] << 8) | (canMsg.data[4]);
          capT = cap1 * 0.1;

          SysStat1 = canMsg.data[6];
          SysStat2 = canMsg.data[7];

        }
      }


      if  (canMsg.can_id == 2550398964) {
        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
          battTemp = canMsg.data[0] - 40;
        }

      }


    }
  }
    Serial.write(0x76);
    Serial.write((int)voltT);               //Battery voltage
    Serial.write(0x61);
    Serial.write(ampMSB);
    Serial.write(ampLSB);                   //Current Battery
    Serial.write(0x73);
    Serial.write(capT);                     //Battery SOC%
    Serial.write(0x42);
    Serial.write((int)battTemp);
    Serial.write(0x4D);
    Serial.write(SysStat1);                 //Sys status1 battery
    Serial.write(0x6D);
    Serial.write(SysStat2);                 //Sys status2 battery
}
