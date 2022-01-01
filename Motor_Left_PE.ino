void MotorLeftPE (){

int LSBMotorTemp;
int MSBMotorTemp;
int TempMotor;
int ControllerTemp;
int Fault1;
int Fault2;
int Fault3;
int Fault4;
int SpeedByte0;
int SpeedByte1;
int SpeedByte2;
int SpeedByte3;
int rpmTl;
int speedlKmh;
int TpsByte0;
int TpsByte1;
int TpsByte2;
int TpsByte3;

  


  if (mcp2515controllerLeft.readMessage(&canMsg) == MCP2515::ERROR_OK)
    {
      if  (canMsg.can_id == 644) {            //ID 284h

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
            Fault1 = canMsg.data[1];
            Fault2 = canMsg.data[2];
            Fault3 = canMsg.data[3];
         
        }
      }


      if  (canMsg.can_id == 645) {            //ID 285h

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
           // LSBMotorTemp = canMsg.data[0];
           // MSBMotorTemp = canMsg.data[1];
         TempMotor = ((canMsg.data[1] | canMsg.data[0]) - 30);
         ControllerTemp = (canMsg.data[2] - 30);            
        }
      }
      

    if  (canMsg.can_id == 646) {        // ID 286h speed + tps

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
            SpeedByte0  = canMsg.data[0];
            SpeedByte1 = canMsg.data[1];
            //SpeedByte2 = canMsg.data[2];
            //SpeedByte3 = canMsg.data[3];
            
            /*
            TpsByte0 =canMsg.data[4];
            TpsByte1 =canMsg.data[5];
            TpsByte2 =canMsg.data[6];
            TpsByte3 =canMsg.data[7];*/
            
        }
      } 

   

    }
    float velAng = (DOS_PI * rpmTl) / 60;
    float velTang = velAng * WHELL_RADIO;
    speedlKmh = velTang * MS_KMH;
    
    Serial.write(0x6B);
    Serial.write(SpeedByte0);
    Serial.write(SpeedByte1); 
    //Serial.write(SpeedByte2);
    //Serial.write(SpeedByte3); 
    Serial.write(0x55);
    Serial.write(TpsValue);
    //Serial.write(TpsByte0);
    //Serial.write(TpsByte1);
    //Serial.write(TpsByte2);
    //Serial.write(TpsByte3);
    Serial.write(0x2A);
    Serial.write(TempMotor);
    //Serial.write(MSBMotorTemp);
    Serial.write(0x3F);
    Serial.write(ControllerTemp);         //Controller Temp Left
    Serial.write(0x6F);
    Serial.write(Fault1);                 //Error1 Motor Left
    Serial.write(0x4F);
    Serial.write(Fault2);                 //Error 2 motor Left 
    Serial.write(0x72);
    Serial.write(Fault3);
    
   
    
  
}
