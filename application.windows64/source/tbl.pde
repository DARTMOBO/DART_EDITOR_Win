String [] button_labels = {"Modifier", "Data byte1", "MINIMUM", "MAXIMUM", "MIDI CHANNEL", "DMX CHANNEL ", "MIDI TYPE", "MODE", "KEY", "HOT KEY", "", "","LED"}; // normal_labels
String [] pot_labels = {"Modifier", "Data byte1", "MINIMUM", "MAXIMUM", "MIDI CHANNEL", "DMX CHANNEL ", "MIDI TYPE", "MODE", "", "", "", "","LED"};
String [] page_labels = {"Page switch", "Data byte1", "Min", "Max", "MIDI CHANNEL", "", "MIDI TYPE", "MODE", "", "", "", "","LED"};
String [] spin_labels = {"SPINNER", "Data byte1", "SPEED ", "", "MIDI CHANNEL", "JOG MODE", "MIDI TYPE", "MODE", "TOUCH-STOP", "", "", "","LED"};
String [] touch_labels = {"TOUCH SENSOR", "Data byte1", "sensitivity", "LED OUT", "MIDI CHANNEL", "RESET VALUE ", "MIDI TYPE", "MODE", "RESET MODE", "", "", "","touch mode"};
String [] mouse_labels = {"MOUSE EMULATOR", "MouseWheel", "X circuit pos", "Y circuit pos", "", "-/mouse/arrows", "", "MODE", "", "", "", "","LED"};
String [] PADS_labels = {"PADS", "Data byte1", "", "", "MIDI CHANNEL", "", "MIDI TYPE", "MODE", "", "", "", "","LED"};
String [] distance_labels = {"DISTANCE SENSOR", "Data byte1", "MIN", "MAX", "MIDI CHANNEL", "DMX CHANNEL", "", "MODE", "POT/BUTTON/scale", "", "", "","LED"};
String [] GENERAL_labels = {"GENERAL SETUP","","1= ExtraPlex", "0= PADS", ".", "spinners", ".", "MODE", "LED EFX", ".", ".", ".","LED"};


String []  toggleList ={
"Blind Input", // 0
  "Button", 
  "Toggle", 
  "Toggle Group 1", 
  "Toggle Group 2", 
  "Toggle Group 3", 
  "Toggle Group 4", 
  "Toggle Group 5", 
  "Toggle Group 6", 
  "Toggle Group 7",
  "Toggle Group 8",  //10
"POT", // 11
"Hypercurve 1",
"Hypercurve 2",
"Center-curve",
"Center-curve2", //15
"user 3", //16
"page switch",  //17
"distance sens.", //18
"encoder",  //19
"PADS",  //20
"SPINNER 1",  //21
"SPINNER 2 ", // 22
"touch 1", // 23
"touch 2", // 24
"MOUSE/ARROWS",  //25
"GENERAL" }; // 26
/*
String []  toggleList ={
  "Pot", // 0
  "Button", 
  "Toggle", 
  "Toggle Group 1", 
  "Toggle Group 2", 
  "Toggle Group 3", 
  "Toggle Group 4", 
  "Toggle Group 5", 
  "Toggle Group 6", 
  "Toggle Group 7",
  "Toggle Group 8",  //10
"Hypercurve 1",
"Hypercurve 2",
"Center-curve",
"Center-curve2", //14
"user 2",
"user 3", //16
"page switch",  //17
"distance sens.", //18
"BLIND INPUT",  //19
"PADS",  //20
"SPINNER1 SET.",  //21
"SPINNER2 SET.", // 22
"touch 1", // 23
"touch 2", // 24
"MOUSE/ARROWS",  //25
"GENERAL" }; // 26*/


String []  toggleList_spinner ={"0", "1"};
String []  keylist_touch ={"0", "1", "2"};
String []  keylist_general ={"0 no efx", "1 pots", "2 Spinners","3 buttons"};


//"DMX channel. 32 channels are available by default"
String []  DMX_Strings ={
"DMX channel. 32 channels are available by default",  // normal modifier pot-button
"0 = 63-65 endless mode. \n 1 = 0-127 endless mode. \n 2 = POT emulation mode, or SCALE-paly mode (when type is NOTE) \n 3 = SCALE-LEARN-play mode", // spinner  - 21 22
"", // page switch - niente - 17
"DMX channel. 32 channels are available by default", // distance sensor  // normale scelta dmx - 18
"RESET VALUE  \n 64 is mid position \n this value will be used for Touch-Reset function and Virtual-Touch-Reset function \n The Spinner value returns to the Reset Position if it is NOT used (touched or turned)", // touch sensor - reset value
// -23 24
"MOUSE/arrows setup \n 0 = NOT active \n 1 = MOUSE emulation \n 2 = Arrows emulation.", //-25
"0 = NO Spinners . \n 1 = Top spinner ACTIVE. \n 2 = Side + Top spinner ACTIVE",
""};

String []  MIN_Strings ={
"MINIMUM Value \n sets the lower limit of the second DATA BYTE of the midi message.",  // normal modifier pot-button
"SPEED \n Sets the speed multiplier of the Spinner (POT MODE) \n 8 = NORMAL speed \n It is recommended  to lower the speed, for mousewheel emulation \n Range: -32 - +32 \n Negative values will invert encoding direction.", // spinner  - 21 22
"", // page switch - niente - 17
"MINIMUM Value \n sets the lower limit of the second DATA BYTE of the midi message.", // distance sensor  // normale scelta dmx - 18
"Touch sensitivity setup: Lower limit multiplier", // touch sensor - reset value // -23 24
"joystick X axis - citcuit position", //-mouse -25
"0 = NO Extra Plexer. \n 1 = Extra Plexer ACTIVE - the secondary touch sensor will be disabled. UP to 8 Extra modifiers can be connected to the DartMobo",
""};

String []  MAX_Strings ={
"MAXIMUM Value \n sets the upper limit of the second DATA BYTE of the midi message. \n  In a common MIDI NOTE message this value defines the VELOCITY of the note-ON message.",  // normal modifier pot-button
"INVERT spinner message direction \n 0 = NORMAL. \n 1 = INVERTED.", // spinner  - 21 22
"", // page switch - niente - 17
"MAXIMUM Value \n sets the upper limit of the second DATA BYTE of the midi message. \n  In a common MIDI NOTE message this value defines the VELOCITY of the note-ON message.", // distance sensor  // normale scelta dmx - 18
"Touch sensitivity setup: Upper limit multiplier", // touch sensor - reset value
// -23 24
"joystick Y axis - citcuit position", //-25
"0 = PADS ACTIVE - the last analog input (A5) will be scanned at a higher frequency. Just set a modifier to PADS to access the Pads settings. \n 1 = NO PADS - the last analog input (A5) is free free for common modifiers (pot, buttons, sensors)",
""};

String []  VALUE_Strings ={
"VALUE of the MIDI message. \n it's the FIRST DATA BYTE of the midi message. \n In a common MIDI NOTE message this value defines the PITCH of the note.",  // normal modifier pot-button
"VALUE of the MIDI message. \n it's the FIRST DATA BYTE of the midi message. \n In a common MIDI NOTE message this value defines the PITCH of the note.", // spinner  - 21 22
"VALUE of the MIDI message. \n it's the FIRST DATA BYTE of the midi message. \n In a common MIDI NOTE message this value defines the PITCH of the note.", // page switch - niente - 17
"VALUE of the MIDI message. \n it's the FIRST DATA BYTE of the midi message. \n In a common MIDI NOTE message this value defines the PITCH of the note.", // distance sensor  // normale scelta dmx - 18
"VALUE of the MIDI message. \n it's the FIRST DATA BYTE of the midi message. \n In a common MIDI NOTE message this value defines the PITCH of the note.", // touch sensor - reset value
// -23 24
"MOUSEWHEEL \n 0= no mouse wheel emulation \n 1=  ACTIVE : the main SPINNER will emulate mousewheel movements, use SPEED settings of the Spinner to setup it.", //-25
"",
""};

String []  KEY_Strings ={
"KEYS: QWERTY keyboard emulation.\n"+
      "list of ASCII keys.\n"+
      "MIDI data will not be output in QWERTY key mode, choose NULL to have midi output.",  // normal modifier pot-button
"TOUCH-STOP \n 0 = NO touch-stop \n 1 = touch-stop active \n The touch-stop function will stop the flow of midi data produced by the rotation of the Spinner. This is useful to have more precision and control over a parameter, avoiding the INERTIA of the spinner to influence a parameter. \n If the spinner is set in ENDLESS mode, the touch-stop function will just translate spinning data to the upper MIDI Channel if touched", // spinner  - 21 22
"", // page switch - niente - 17
"POT / BUTTON emulation \n 0 (null) = Potentiometer mode : the distance sensor produces a continuous MIDI output flow, like it was a potentiometer. \n 1 = BUTTON emulation : the sensor emulates an ON-OFF switch, distance will regulate the second data byte (velocity) of the MIDI signal. \n 2 = Scale play mode (set type to NOTE for scale-play!) ", // distance sensor  // normale scelta dmx - 18
"TOUCH RESET \n 0 (null) = no touch-reset \n 1 = touch reset ACTIVE \n_\nThe touch-reset function will turn back the Spinner value to a specified RESET-VALUE when it is not touched or turned (virtual touch)", // touch sensor - reset value
// -23 24
"", //-25
"",
""};

String []  LED_Strings ={
"LED OUTPUT \n This value defines wich digital output will be controlled by the current modifier. \n choose a value that is outside the 74HC595 chain (higher than 31) to have NO LED output, and avoid conflicts. "
      ,  // normal modifier pot-button
"LED OUTPUT \n This value defines wich digital output will be controlled by the current modifier. \n choose a value that is outside the 74HC595 chain (higher than 31) to have NO LED output, and avoid conflicts. ", // spinner  - 21 22
"LED OUTPUT \n This value defines wich digital output will be controlled by the current modifier. \n choose a value that is outside the 74HC595 chain (higher than 31) to have NO LED output, and avoid conflicts. ", // page switch - niente - 17
"LED OUTPUT \n This value defines wich digital output will be controlled by the current modifier. \n choose a value that is outside the 74HC595 chain (higher than 31) to have NO LED output, and avoid conflicts.  ", // distance sensor  // normale scelta dmx - 18
"TOUCH MODE: \n 0 = Capacitive touch \n 1 = Virtual Touch \n 2 = Sensor monitoring MODE", // touch sensor - reset value
// -23 24
"", //-25
"",
""};

// "LED OUTPUT \n This value defines wich digital output will be controlled by the current modifier. \n choose a value that is outside the 74HC595 chain (higher than 31) to have NO LED output, and avoid conflicts. ");
  


// !°£$ %/à)
//  =(^,'.-0
//  12345678
//  9çò;ì:_"
//  ABCDEFGH
//  IJKLMNOP
//  QRSTUVWX

//  YZèù+&?\
//  abcdefgh
//  ijklmnop
//  qrstuvwx
//  yzé§*|



  String [] keyboardList = {"null", 
  "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", 
  "f9", "f10", "f11",  "f12", "right arrow", "left arrow", "down arrow", "up arrow", 
  "left ctrl", "left shift",    "left alt", "backspace", "return", "esc", "delete",   "space", 
  "!", "°", "£", "$", "%", "/", "à", ")",
  
  "=", "(",   "^", ",", "'", ".", "-", "0", 
  "1", "2", "3", "4", "5", "6",    "7", "8", 
  
  "9", "ç", "ò", ";", "ì", ":", "_", "''", 
  "A", "B", "C",  "D", "E", "F", "G", "H", 
  "I", "J", "K", "L", "M", "N", "O", "P", 
    "Q", "R", "S", "T", "U", "V", "W", "X",
    
    "Y", "Z", "è", "ù", "+",   "&", "?", "\\", 
    "a", "b", "c", "d", "e", "f", "g", "h",
    "i", "j",    "k", "l", "m", "n", "o", "p",
    "q", "r", "s", "t", "u", "v", "w",   "x",
  "y", "z", "é", "§", "*", "|",   "mouse left", "mouse right",
  "mouse center"
  };

  String [] modifiersList = {"Null", "ctrl", "shift", "alt", "ctrl+shift", "ctrl+alt", "ctrl+alt+shift"
  };
  
  
  int [] memPosLUT = {
  // first multi
  6, 8, 4, 2,
  7, 1, 5, 3, 
  // second multi
  14,16,12,10,
  15,9,13,11,
  //terzo multi
  22,24,20,18,
  23,17,21,19,
  //quarto multi
  30,32,28,26,
  31,25,29,27,
  //quinto multi
  38,40,36,34,
  39,33,37,35,
  //sesto multi
  46,48,44,42,
  47,41,45,43,
  // 7
  54,56,52,50,
  55,49,53,51,
  // 8
  62,64,60,58,
  63,57,61,59
  
};

int [] hint_strings_map = {
1, //0
1,
1,
1,
1,
1,
1,
1,
1,
1,
1, //10
1,
1,
1,
1,
1,
1,
3, //17
4, // 18
3, // 19
3, // 20
2, // 21
2, // 22
5, // 23
5, // 24
6, // 25
7, // general
1,
1,
1,
1,
1,

};
