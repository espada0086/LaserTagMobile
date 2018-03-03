/**
 * Simple routine that performs the following:
 *  1. Configures the software UART on pins 10 and 11 (RX,TX)
 *  2. Increments a 32-bit variable every 500ms
 *  4. If it receives a '1' character from bluetooth, it toggles an LED
 *     
 *  @author Jordan Olive
 */

#include <SoftwareSerial.h> // use the software uart
SoftwareSerial bluetooth(10, 11); // Set RX and TX here

unsigned long previousMillis = 0;        // will store last time
const long interval = 500;           // interval at which to delay
static uint32_t tmp; // increment this

void setup() {
  pinMode(13, OUTPUT); // for LED status
  bluetooth.begin(9600); // start the bluetooth uart at 9600 which is its default
  delay(200); // wait for voltage stabilize
  bluetooth.print("AT+NAMEmcuhq.com"); // place your name in here to configure the bluetooth name.
                                       // will require reboot for settings to take affect. 
  delay(3000); // wait for settings to take affect. 
}

void loop() {
  //bluetooth control here
  if (bluetooth.available()) {
     // check if anything in UART buffer
    if(bluetooth.read() == '1'){ // did we receive this character?
       digitalWrite(13,!digitalRead(13)); // if so, toggle the onboard LED
    }
  }
  //timer
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    previousMillis = currentMillis;
    bluetooth.print(tmp++); // print this to bluetooth module
  }

}
