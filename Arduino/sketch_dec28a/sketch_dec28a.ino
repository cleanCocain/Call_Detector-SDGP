#include <SoftwareSerial.h>
SoftwareSerial BTserial(10, 11); // RX | TX
#define ledPin 7
#define ledPin2 12

char c = ' ';
int x;
boolean get1 = true;
const int buttonPin = 2;
const int led = 8;
String color;
String state;
boolean call;
boolean call2;
boolean standS = true;
boolean standS1 = true;

void setup() {
  Serial.begin(9600); // Default communication rate of the Bluetooth module
  BTserial.begin(9600);  
  Serial.println("Arduino is Ready");
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);
  pinMode(buttonPin, INPUT);
  pinMode(led, OUTPUT);
  digitalWrite(led, HIGH);
}

void Bluetooth (){
  
  while (BTserial.available())
    {  
      delay(10);
        c = BTserial.read();
        color += c;   
        if(color=="on"){
          call = true;
          call2 = false;
        }
        if(color=="resend"){
          call2 = true;
          call = false;
        }
    }
      if (color.length() >0)
       {
        
        if(call){
          while(get1){
          digitalWrite(ledPin,HIGH);
          delay(200);
          digitalWrite(ledPin,LOW);
          delay(200);         
         c = BTserial.read();
         Serial.print(c);
//         BTserial.flush();
         if(c == 'f'){
//          BTserial.flush();
          break;
         }
         c=' ';
         
          }
//        }else if(color == "off"){
//          get = false;
//          digitalWrite(ledPin, LOW);
//          delay(20);
//        }
    }
            if(call2){
          while(get1){
          digitalWrite(ledPin2,HIGH);
          delay(200);
          digitalWrite(ledPin2,LOW);
          delay(200);         
         c = BTserial.read();
         Serial.print(c);
//         BTserial.flush();
         if(c == 'f'){
//          BTserial.flush();
          break;
         }
         c=' ';
         
          }

    }
 
color ="";
}
}

void loop() {
  
  Bluetooth();
  stand();

}

void stand(){
  int butt = digitalRead(buttonPin);
  int LED = digitalRead(led);

  if(butt == HIGH){
    digitalWrite(led,LOW);
    standS1 = true;
    if(standS){
    Serial.print("yes");
    BTserial.print("yes");
    delay(100);
    }
      standS=false;
    
    
  }else{
    digitalWrite(led,HIGH);
    standS=true;
    if(standS1){
    Serial.print("no");
    BTserial.print("no");
    delay(100);
    }
    standS1=false;
  }

}


