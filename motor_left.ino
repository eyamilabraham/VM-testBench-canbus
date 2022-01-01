/*void motor_left(){

int idCanControllerL;
float currentTl;
float voltageTl;
int StatControllerl;
int StatusSignall;
int actMotTempl;
unsigned int tpsl;
int actContTempl;
int Errorl1;
int Errorl2;
float torquel;
int speedlKmh;
int rpmMsbLeft;
int rpmLsbLeft;
int  rpmTl;

   if (mcp2515controllerLeft.readMessage(&canMsg) == MCP2515::ERROR_OK)
    {

      if  (canMsg.can_id == 2364612101) {             //0x8CF11E05

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
          rpmMsbLeft = canMsg.data[1];
          rpmLsbLeft = canMsg.data[0]; 
          rpmTl = (rpmMsbLeft << 8) | rpmLsbLeft;
          
          float current = (canMsg.data[3] << 8 ) | (canMsg.data[2]);
          currentTl = current * 0.1;
          
          float voltage = (canMsg.data[5] << 8 ) | (canMsg.data[4]);
          voltageTl = voltage * 0.1;

          Errorl1 = canMsg.data[6];
          Errorl2 = canMsg.data[7];

        }
      }
      if  (canMsg.can_id == 2364612357) {

        for (int i = 0 ; i < canMsg.can_dlc ; i++)
        {
          tpsl = canMsg.data[0];
          contTempl = (canMsg.data[1] - 40);
          actMotTempl = (canMsg.data[2] - 30);
          //StatControllerl = canMsg.data[4];
          StatusSignall = canMsg.data[5];
        }
      }

    }


    float potencia = currentTl * voltageTl;
    float hp = potencia / WATTS_HP;
    torquel = ((hp * CONSTANTE_K) / rpmTl) * LBPIE_NM ;

    float velAng = (DOS_PI * rpmTl) / 60;
    float velTang = velAng * WHELL_RADIO;
    speedlKmh = velTang * MS_KMH;

    Serial.write(0x72);
    Serial.write(rpmMsbLeft);                //Rpm Motor left
    Serial.write(rpmLsbLeft);
    Serial.write(0x63);
    Serial.write((int)currentTl);           //Current motor left
    Serial.write(0x56);
    Serial.write((int)voltageTl);           //Voltage Motor Left
    Serial.write(0x71);
    Serial.write((int)torquel);             //Torque Motor left
    Serial.write(0x6B);
    Serial.write((int)speedlKmh);           //speed Motor left
    Serial.write(0x55);
    Serial.write(tpsl);                     //Tps value left
    Serial.write(0x2A);
    Serial.write(actMotTempl);             //Temp Motor  left
    Serial.write(0x3F);
    Serial.write((int)contTempl);           //Controller Temp left
    Serial.write(0x6F);
    Serial.write(Errorl1);                 //Error1 Motor Left
    Serial.write(0x4F);
    Serial.write(Errorl2);                //Error 2 motor Left   
}*/
