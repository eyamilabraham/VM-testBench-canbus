void MotorRightPE (){

int LSBMotorTemp;
int MSBMotorTemp;
int TempMotorR;
int ControllerTemp;
int Faultr1;
int Faultr2;
int Faultr3;

int SpeedByte0;
int SpeedByte1;
int SpeedByte2;
int SpeedByte3;
int rpmTr;
int speedrKmh;
int TpsByte0;
int TpsByte1;
int TpsByte2;
int TpsByte3; 


  if (mcp2515controllerRight.readMessage(&canMsg) == MCP2515::ERROR_OK)
    {
      if  (canMsg.can_id == 388) {            //ID 184h

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
            Faultr1 = canMsg.data[1];
            Faultr2 = canMsg.data[2];
            Faultr3 = canMsg.data[3];
         
        }
      }
      if  (canMsg.can_id == 389) {            //ID 185h

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
         // LSBMotorTemp  = canMsg.data[0];
         // MSBMotorTemp = canMsg.data[1];
         TempMotorR = (canMsg.data[1] | canMsg.data[0] )- 30;
         ControllerTemp = (canMsg.data[2] - 30);            
        }
      }

            if  (canMsg.can_id == 390) {        // ID 186h speed + tps

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {   
                      
            SpeedByte0 = canMsg.data[0];
            SpeedByte1 = canMsg.data[1];
            //SpeedByte2 = canMsg.data[2];
            //SpeedByte3 = canMsg.data[3];

           //rpmTr  =  (SpeedByte1 | SpeedByte0 );
            
           /* TpsByte0 =canMsg.data[4];
            TpsByte1 =canMsg.data[5];
            TpsByte2 =canMsg.data[6];
            TpsByte3 =canMsg.data[7];*/
            
        }
      }    

    }

    float velAng = (DOS_PI * rpmTr) / 60;
    float velTang = velAng * WHELL_RADIO;
    speedrKmh = velTang * MS_KMH;
    
    Serial.write(0x24);
    Serial.write(SpeedByte0);
    Serial.write(SpeedByte1); 
    //Serial.write(SpeedByte2);
    //Serial.write(SpeedByte3); 
    Serial.write(0x25);
    Serial.write(TpsValue);
   // Serial.write(TpsByte0);
   // Serial.write(TpsByte1);
   // Serial.write(TpsByte2);
   // Serial.write(TpsByte3);
    Serial.write(0x26);
    Serial.write(TempMotorR);
    //Serial.write(MSBMotorTemp);
    Serial.write(0x2F);
    Serial.write(ControllerTemp);         //Controller Temp Right
    Serial.write(0x28);
    Serial.write(Faultr1);                 //Error1 Motor Right
    Serial.write(0x29);
    Serial.write(Faultr2);                 //Error 2 motor Right
    Serial.write(0x52);
    Serial.write(Faultr3);
    
  
}
