
#include <SPI.h>
#include <MFRC522.h>

#define RST_PIN         9           // Configurable, see typical pin layout above
#define SS_PIN          10          // Configurable, see typical pin layout above

MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance
MFRC522::MIFARE_Key key;

//*****************************************************************************************//





#include <Keypad.h>

const byte numRows = 4;

const byte numCols = 4;

char keymap[numRows][numCols] = {
  {'1', '2', '3', 'A'},

  {'4', '5', '6', 'B'},

  {'7', '8', '9', 'C'},

  {'*', '0', '#', 'D'}
};

byte rowPins[numRows] = {7, 6, 5, 4}; //Rows 0 to 3

byte colPins[numCols] = {A1, A2, A3, A4}; //Columns 0 to 3

//initializes an instance of the Keypad class

Keypad myKeypad = Keypad(makeKeymap(keymap), rowPins, colPins, numRows, numCols);
//////////////////////////////////////////////////////////////////////////////////////////
void setup() {

   pinMode(8, OUTPUT);
   digitalWrite(8, LOW);
  Serial.begin(9600);                                           // Initialize serial communications with the PC
  SPI.begin();                                                  // Init SPI bus
  mfrc522.PCD_Init();                                              // Init MFRC522 card
  for (byte i = 0; i < 6; i++) key.keyByte[i] = 0xFF;
}

//*****************************************************************************************//
void loop() {

keypad();
rfid();

char letter;
String line;
while (Serial.available() > 0) {
   letter = Serial.read();
   if (letter == '}'){
    break;
    }

    line += letter;
  }

  if (line == "hello"){
    digitalWrite(8, HIGH);
    }
}

void printHex(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? " 0" : "");
    Serial.print(buffer[i], HEX);
  }
}


void keypad() {




  char keypressed = myKeypad.getKey();

  if (keypressed != NO_KEY)

  {

    Serial.println(keypressed);

  }
   

}


void rfid(){
   //some variables we need
  byte block;
  byte len;
  MFRC522::StatusCode status;


  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }

  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }
  byte buffer1[18];

  block = 1;
  len = 18;

  //------------------------------------------- GET FIRST NAME
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, 1, &key, &(mfrc522.uid)); //line 834 of MFRC522.cpp file
  status = mfrc522.MIFARE_Read(block, buffer1, &len);

  printHex(mfrc522.uid.uidByte, mfrc522.uid.size);
  for (byte i = 0; i < 11; i++) {
    Serial.print(buffer1[i] < 0x10 ? " 0" : "");
    Serial.print(buffer1[i], HEX);
  }
  Serial.println("");



  mfrc522.PICC_HaltA();
  mfrc522.PCD_StopCrypto1();
  }
