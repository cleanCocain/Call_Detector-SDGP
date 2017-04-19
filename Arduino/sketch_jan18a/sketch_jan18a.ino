#include <SoftwareSerial.h>
SoftwareSerial BTserial(10, 11); // RX | TX

int AO = 0;
boolean stat = true;
boolean time1 = true;
char c=' ';
int total;
void setup() {
  Serial.begin(9600); // Default communication rate of the Bluetooth module
  BTserial.begin(9600);  
    Serial.println("Arduino is Ready");
    
}

void loop() {
  c = BTserial.read();
  delay(500);

if(stat || c == '1'){
for(int i=0;i<4;i++){
  time1= true;
  int read_get = analogRead(AO);
  delay(20);
  Serial.println(read_get);
//  BTserial.println(read_get);
  total = total+ read_get;
  
  delay(2000);
}

}
if(time1){
  BTserial.println(total);
  total=0;
}
time1 = false;
stat= false;

}
