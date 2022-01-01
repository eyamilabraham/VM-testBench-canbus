void REncoder() {                                                                   // funciòn de interrupciòn del enconder motor derecho
      
      CR++;

      if (CR == InterruptCount){
          float media = 0;
          deltaMuestreoIntR = muestreoActualIntR -  muestreoAnteriorIntR;             // diferencia tiempos de interruciones     
          
           for(int i=0;i < SAMPLES -1;i++){                                                                    // relleno del vector para calculo posterior del promedio
              vectorR[i]=vectorR[i+1];
            }
           vectorR[SAMPLES -1]=deltaMuestreoIntR ;                                     // ùltimo dato del vector 

           for(int i=0;i<SAMPLES ;i++){                                                // Càlculo de la media del vector
              media = vectorR[i]+ media;
            }
            media = media/SAMPLES ;
            deltaMuestreoIntR = media;                                                 // se reemplaza por el valor de su medìa. 
           
            frecuencyR = (1000)/ deltaMuestreoIntR;                                    // frecuencia de interrupciòn 
          
          muestreoAnteriorIntR = muestreoActualIntR;                                   // se actualiza el tiempo de interrupciòn anterior
          CR = 0;                                                                      //Reinicio de contador
      } 
 } 
