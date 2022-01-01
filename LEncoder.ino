void LEncoder() {                                                                                    // funciòn de interrupciòn del enconder llanta derecha
      CL++;

      if (CL == InterruptCount){
          float media = 0;
          deltaMuestreoIntL = muestreoActualIntL -  muestreoAnteriorIntL;     // diferencia tiempos de interruciones de ticks del motor     

          for(int i=0;i < SAMPLES -1;i++){                         // relleno del vector para calculo posterior del promedio
              vectorL[i]=vectorL[i+1];
            }
           vectorL[SAMPLES -1]=deltaMuestreoIntL;                 // ùltimo dato del vector 
  
           for(int i=0;i<SAMPLES ;i++){                           // Càlculo de la media del vector
              media = vectorL[i]+ media;
            }
            media = media/SAMPLES ;
            deltaMuestreoIntL = media;                            // se reemplaza por el valor de su medìa. 
          
          frecuencyL = (1000)/ deltaMuestreoIntL;                 // frecuencia de interrupciòn 
                                                                   // velocidad lineal cm/s
          muestreoAnteriorIntL = muestreoActualIntL;               // se actualiza el tiempo de interrupciòn anterior
          CL = 0;
       } 
 } 
