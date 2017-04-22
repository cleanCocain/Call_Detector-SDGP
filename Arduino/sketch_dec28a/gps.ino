#include 

// GPS Setup
#define GPS1 3
#define GPS2 5
SoftwareSerial serialGPS = SoftwareSerial(GPS1, GPS2);
String stringGPS = "";

void setup() {
  pinMode(GPS1, INPUT);
  pinMode(GPS2, OUTPUT);


  Serial.begin(9600);
  Serial.println("Started");

  serialGPS.begin(4800);
  digitalWrite(GPS2,HIGH);

  while(serialGPS.available())
    if (serialGPS.read() == '\r')
      break;
}

void loop()
{
  String s = GPSchk();
  if(s && s.substring(0, 6) == "$GPGGA")
  {
    Serial.println(s);
  }
}

// Check GPS and returns string if full line recorded, else false
String GPSchk()
{
  if (serialGPS.available())
  {
    char c = serialGPS.read();
    if (c != '\n' && c != '\r')
    {
      stringGPS  = c;
    }
    else
    {
      if (stringGPS != "")
      {
        String tmp = stringGPS;
        stringGPS = "";
        return tmp;
      }
    }
  }
  return false;
}
