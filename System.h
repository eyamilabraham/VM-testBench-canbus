#ifndef System.h
  #define System.h
#endif
 
#include <Adafruit_Sensor.h>
#include <Adafruit_BME280.h>
#include <Wire.h>
#include <SPI.h>
#include <mcp2515.h>
#include <TinyGPS++.h>
#include "I2Cdev.h"
#include "MPU6050.h"


#define TIMERBATTERY          700
#define TIMERCONTROLLERL      20
#define TIMERCONTROLLERR      10
#define TIMERGPS              1500
#define TIMERBMP              10000        
#define TIMERSERIAL           100
#define TIMERIMU              2000
#define TIMER_REC_SERIAL      100


#define CONSTANTE_TORQUE      5252              // constante calculo de torque
#define LBPIE_NM              1.356             // cambio de unidades Libra/pie ---> Newton/Metro
#define WATTS_HP              746               // watts a hp (1hp = 746 W)
#define WATTS_CV              735

#define WHELL_RADIO           0.138             // radio motor en mts
#define DOS_PI                2*PI
#define K                     60               
#define MS_KMH                3.6               // Factor de cambio de unidades M/s ---> Km/h

//////////// Serial baud rate//////////////////////////////////////
#define BAUD_RATE                4800
#define GPS_BAUD_RATE            9600
////////////////////////////////////////////////////////////////
///// chip select SPI ///////////////////////////////////////////
#define CAN_BATTERY_CS           10
#define CAN_MOTOR_LEFT_CS        9
#define CAN_MOTOR_RIGHT_CS       8

//// Sensor RPM inductivo o IR/////////////
#define IR_SENSOR_LEFT           3
#define IR_SENSOR_RIGHT          2

//// Hard pins ///////////////////////////////
#define DIRECT_PIN              31
#define REVERSE_PIN             33
#define ON_OFF_PIN              35
#define LED                     13

// i2c Address IMU sensor

#define MPU6050_address 0x68

#define ENCODER_NUMBER     2             // nùmero de ranuras del encoder
#define SAMPLES           10             // tamaño del vector del calculo de promedio

struct can_frame canMsg;

MCP2515 mcp2515battery(CAN_BATTERY_CS);
MCP2515 mcp2515controllerLeft(CAN_MOTOR_LEFT_CS);
MCP2515 mcp2515controllerRight(CAN_MOTOR_RIGHT_CS); 

Adafruit_BME280 bme;

TinyGPSPlus gps;
 
// mpuAddress = 0x68;  // Puede ser 0x68 o 0x69
MPU6050 mpu(MPU6050_address);


int InterruptCount = 2;        // Cantidad de interrupciones para calculo de velocidad


///-------------------------------Right Motor---------------------------------------------

volatile unsigned muestreoActualIntR = 0;             // variables para definiciòn del tiempo de interrupciòn y calculo de la velocidad motor derecho
volatile unsigned muestreoAnteriorIntR = 0;
float deltaMuestreoIntR = 0;

int encoderR = 3;                                       // pin de conexiòn del encoder derecho

float frecuencyR = 0;                                  // frecuencia de interrupciòn motor R
float Wr = 0;                                          // Velocidad angular R
float Vr = 0;                                          // velocidad Lineal
int CR = 0;                                             // contador de entradas al vector de interrupcion
float vectorR[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};       // vector de almacenamiento de datos para promedio del tiempo de interrupciones


//------------------------------ Left Motor ------------------------------------------------

volatile unsigned muestreoActualIntL = 0;               // variables para definiciòn del tiempo de interrupciòn y calculo de la velocidad motor Izquierdo
volatile unsigned muestreoAnteriorIntL = 0;
float deltaMuestreoIntL = 0;

int encoderL = 2;                                        // pin de conexiòn del encoder Izquierdo

float frecuencyL = 0;                                   // frecuencia de interrupciòn motor Izquierdo
float Wl = 0;                                           // Velocidad angular L
float Vl = 0;                                           // velocidad Lineal
int CL = 0;                                              // contador interrupciones
float vectorL[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};        // vector de almacenamiento de datos para promedio del tiempo de interrupciones

//---------------------------------------------------------------------------------------------------------------------------------------

String CanMsgMr;
String CanMsgMl;
String CanMsgE2Ml;
String CanMsgE2Mr;

float contTempl;
float contTempr;

unsigned long endTimerBattery = 0;
unsigned long endTimerControllerL = 0;
unsigned long endTimerControllerR = 0;
unsigned long endTimerGps = 0;
unsigned long endTimerBmp = 0;
unsigned long endTimerImu = 0;
unsigned long endTimerRecSerial = 0;
unsigned long actualTimer;

int16_t ax, ay, az , gx, gy, gz;        //raw values
long tiempo_prev;
float dt;
float ang_x, ang_y;
float ang_x_prev, ang_y_prev;
float accel_ang_y;
float accel_ang_x;
float angulo_pitch, angulo_roll, yawGyro, pitchGyro, rollGyro, angle_pitch_acc, angle_roll_acc, temperature, gyro_X_cal, gyro_Y_cal, gyro_Z_cal;
int cal_int;
float acc_total_vector;
bool set_gyro_angles, accCalibOK  = false;
float angle_yaw_acc_cal, angle_roll_acc_cal, angle_pitch_acc_cal;
 
long tiempo_ejecucion, loop_timer;


bool stateSat = false;
float start_lat, start_lon, flat, flon;
float latitude;
float longitude;
int Altitude;
int speedGps;
float travel;
int distance = 0;
int avSatellites;
int sats;
String gpsState;
int speedKmh;

int TpsValue;


///////////SPI PINOUT ARDUINO MEGA////////////
/*
 * MOSI----------->51
 * MISO----------->50
 * CLK------------>52
 * 
 * I2C 
 * SDA------------>20
 * SCL------------>21
 * 
 * GPS
 * TX1------------>18
 * RX1------------>19
 * 
 * LED------------>13
 * 
 * RESET---------->RESET
 * 
 * 2 SENSORES OPTICOS
 * SENSOR DE CORRIENTE
 * CAMARAS I2C MELEXIS
 * 
 * 
 * 
 */
