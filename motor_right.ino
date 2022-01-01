/*void motor_right(){

int idCanControllerR;
float currentTr;
float voltageTr;
int StatControllerr;
int StatusSignalr;
int actMotTempr;
unsigned int tpsr;
int actContTempr;
int Errorr1;
int Errorr2;
float torquer;
int speedrKmh;
int rpmMsbRight;
int rpmLsbRight;
int  rpmTr;

  if (mcp2515controllerRight.readMessage(&canMsg) == MCP2515::ERROR_OK)
    {
      if  (canMsg.can_id == 2364612101) {

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
          rpmMsbRight = canMsg.data[1];
          rpmLsbRight = canMsg.data[0];
          rpmTr = (rpmMsbRight << 8) | rpmLsbRight;
          float current = (canMsg.data[3] << 8 ) | (canMsg.data[2]);
          currentTr = current * 0.1;
          float voltage = (canMsg.data[5] << 8 ) | (canMsg.data[4]);
          voltageTr = voltage * 0.1;
          Errorr1 = canMsg.data[6];
          Errorr2 = canMsg.data[7];


        }
      }
      if  (canMsg.can_id == 2364612357) {

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
          tpsr = canMsg.data[0];
          contTempr = (canMsg.data[1] - 40);
          actMotTempr = (canMsg.data[2] - 30);
          StatControllerr = canMsg.data[4];
          StatusSignalr = canMsg.data[5];
       
        }
      }

    }


    float potencia = currentTr * voltageTr;
    float hp = potencia / WATTS_HP;
    torquer = ((hp * 5252) / rpmTr) * LBPIE_NM ;

    float velAng = (DOS_PI * rpmTr) / 60;
    float velTang = velAng * WHELL_RADIO;
    speedrKmh = velTang * MS_KMH;

    
    

    Serial.write(0x52);
    Serial.write(rpmMsbRight);              //Rpm Motor Right
    Serial.write(rpmLsbRight);
    Serial.write(0x43);
    Serial.write((int)currentTr);          //Current motor Right
    Serial.write(0x21);
    Serial.write((int)voltageTr);          //Voltage Motor Right
    Serial.write(0x23);
    Serial.write((int)torquer);            //Torque Motor Right
    Serial.write(0x24);
    Serial.write((int)speedrKmh);          //speed Motor Right
    Serial.write(0x25);
    Serial.write(tpsr);                    //Tps value Right
    Serial.write(0x26);
    Serial.write(actMotTempr);             //Temp Motor Right
    Serial.write(0x2F);
    Serial.write((int)contTempr);          //Controller Temp Right
    Serial.write(0x28);
    Serial.write(Errorr1);                 //Error1 Motor Right
    Serial.write(0x29);
    Serial.write(Errorr2);                 //Error 2 motor Right 
  
}*/
