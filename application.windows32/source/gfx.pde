//********************************** 
//  POSITION GLOBAL VARIABLES FOR THE LOOK OF THE EDITOR
//  interface grid for element location
//**********************************
int cols = 25;
int rows = 25;
float [] gridRow = new float [rows];
float [] gridCols = new float [cols];
float W, H;
PImage logo;//, jogWheel ; 
PImage circuit_position;
PImage piano;

int spaceBetw;
int Betw2;
int Betw3;
int rowBetw;

//logo = loadImage("logo.gif") ;
//**********************************
/// THE AMBIENT VARIABLES
//***********************************
PFont f;
int fontDimension=10;
int rW = 60;
int rH = 60;
int rR = 30;

void settingScreen() {

  W=width/rows;
  H=height/cols;

  for (int i = 0; i < cols; i++) {
    gridCols[i] = W*i ;
    // println("i: "+ gridCols[i]);
  }
  for (int j = 0; j < rows; j++) {
    gridRow[j] =  H*j ;
    // println("j: "+ gridRow[j]);
  }
   spaceBetw =  int(gridCols[1]*1.3);
  rowBetw = int(gridRow[1]/2);
  Betw2 =  int(gridCols[1]/2);
  Betw3 =  int(gridCols[1]/1.5);
  
}
// *****************************************
//           the background look
//******************************************

void interfaceDisp() {
  // Background
 // background(colore_);
  background(100, 50);
  // box for Setting Label
  strokeWeight(3);
  stroke(150);
  fill(100);
 //  rect(gridCols[17],  gridRow[1], gridCols[7], int(rowBetw*1.8),7);
  

  stroke(150);
  fill(100, 100, 100);

if (full_screen == false)  {
  rect(gridCols[17],  gridRow[2]+rowBetw, gridCols[7], gridRow[16]   ,7);  // settings box
   
     fill(160);
   textSize(Betw2/1.7);
   
    
// String [] normal_labels = {"Modifier", "VALUE", "MINIMUM", "MAXIMUM", "MIDI CHANNEL", "DMX CHANNEL ", "MIDI TYPE", "MODE", "KEY", "KEY MODIFIERS", "-", "- "};
// String [] page_labels = {"Page switch", "VALUE", "-", "-", "MIDI CHANNEL", "- ", "MIDI TYPE", "-", "-", "-", "-", "- "};
// String [] spin_labels = {"SPINNER", "VALUE", "SPEED ", "INVERT", "MIDI CHANNEL", "endless type", "MIDI TYPE", "POT/ENDLESS", "TOUCH-STOP", "-", "-", "- "};
// String [] touch_labels = {"TOUCH SENSOR", "VALUE", "-", "-", "MIDI CHANNEL", "RESET VALUE ", "MIDI TYPE", "RESET MODE", "-", "-", "-", "-"};
// String [] mouse_labels = {"MOUSE EMULATOR", "ciao", "pippo", "baudo", "beppe", "grillo ", "gatto", "luigi", "di", "maio", "nese", "ciro "};
//  String [] distance_labels = {"DISTANCE SENSOR", "ciao", "pippo", "baudo", "beppe", "grillo ", "gatto", "luigi", "di", "maio", "nese", "ciro "}; 

         if ( elementData.get(idElement).dMode.getValue()  ==0)             // Scrivi etichette sotto i selettori a tendina.
 {text(page_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5  );
  text("", gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5  ); // comunque Scrivi MODE - page_labels [7]
  text("", gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5  );
  text("", gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5  );
   }
   
   else if ( elementData.get(idElement).dMode.getValue()  ==17)
   { text(page_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5 );
  text(page_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5 );
  text(page_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5 );
  text(page_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5 );
   }
      else 
         if ( elementData.get(idElement).dMode.getValue()  ==25)
   { text(mouse_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5 );
  text(mouse_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5 );
  text(mouse_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5 );
  text(mouse_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5 );
   }
      else
        if ( elementData.get(idElement).dMode.getValue()  ==18)
   { text(spin_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5 );
  text(distance_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5 );
  text(distance_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5 );
  text(distance_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5 );
   }
   else
      if ( elementData.get(idElement).dMode.getValue() < 11)
   {
  text(button_labels[7], gridCols[20]+spaceBetw/3 , gridRow[6]-rowBetw*0.5  );
  text(button_labels[6], gridCols[20]+spaceBetw/3
  , gridRow[8]-rowBetw*0.5
  );
  text(button_labels[8], gridCols[20]+spaceBetw/3
  , gridRow[10]-rowBetw*0.5
  );
  text(button_labels[9], gridCols[20]+spaceBetw/3
  , gridRow[12]-rowBetw*0.5
  );
   } 
      else
      if ( elementData.get(idElement).dMode.getValue() < 17)
   {
  text(pot_labels[7], gridCols[20]+spaceBetw/3 , gridRow[6]-rowBetw*0.5  );
  text(pot_labels[6], gridCols[20]+spaceBetw/3
  , gridRow[8]-rowBetw*0.5
  );
  text(pot_labels[8], gridCols[20]+spaceBetw/3
  , gridRow[10]-rowBetw*0.5
  );
  text(pot_labels[9], gridCols[20]+spaceBetw/3
  , gridRow[12]-rowBetw*0.5
  );
   } 
  
  
     else //joystick_labels
      if ( elementData.get(idElement).dMode.getValue()  ==20)
   { text(PADS_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5  );
  text(PADS_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5  );
  text(PADS_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5  );
  text(PADS_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5  );
   }
      else
   if ( elementData.get(idElement).dMode.getValue()  <23)
   { text(spin_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5  );
  text(spin_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5  );
  text(spin_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5  );
  text(spin_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5  );
   }
      else
   if ( elementData.get(idElement).dMode.getValue()  <25)
   { text(touch_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5 );
  text(touch_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5 );
  text(touch_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5 );
  text(touch_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5 );
   }
     else
   if ( elementData.get(idElement).dMode.getValue()  == 26)
   { text(GENERAL_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5 );
  text(GENERAL_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5 );
  text(GENERAL_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5 );
  text(GENERAL_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5 );
   }
      else


   if ( elementData.get(idElement).dMode.getValue()  ==18)
   { text(spin_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5 );
  text(distance_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5 );
  text(distance_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5 );
  text(distance_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5 );
   }
}
   
}

void hintbox () {
  hints2 = hints.addTextarea("hints")
         .setPosition((int) gridCols[1],(int)gridRow[2]+rowBetw)
       .setSize((int) gridCols[15]+Betw2,(int) gridRow[5])
   
  
      .setFont(createFont("typeO.TTF", (Betw2/1.9)))
   .setId(2011)
  .setColor(color(200))
                  .setColorBackground(110)
                  .setColorForeground(color(255,100))   
                  
          
                  .setText("."
                    );
}




void midiMonitor() {
  // background
  //noStroke();
 
 // strokeWeight(3);
  stroke(150);  
  noFill();
  rect(gridCols[17],  gridRow[18]+rowBetw, gridCols[7], gridRow[6],7); //red out
  //noStroke();
  noFill();
  strokeWeight(3);
  stroke(128);  
  //rect((int)gridCols[18]+1, (int) gridCols[11]+25, (int)gridCols[5]-2, spaceBetw*1.8-2);// red in

  fill(0, 255, 0);
  textSize(Betw2/1.7);
//  if (channel_filter ==0) 
  {
  //text(""+scala1+"_"+scala2+"_"+scal1+"-"+scal2, gridCols[17]+(Betw2*0.4), (int) gridRow[17]+rowBetw*2.4);
  
  text("Channel  : "+ Channel, gridCols[17]+(Betw2*0.4), (int) gridRow[19]+rowBetw*2.4);
  text("Type         : "+ optCC, gridCols[17]+(Betw2*0.4), (int) gridRow[20]+rowBetw*2.4);
  text("Data b.1    : "+Note, gridCols[17]+(Betw2*0.4), (int) gridRow[21]+rowBetw*2.4);
  text("Data b.2    : "+Intensity, gridCols[17]+(Betw2*0.4), (int) gridRow[22]+rowBetw*2.4);
   fill(255, 150, 0);
   text("MOD/KEYS  : "+keyCode_+"/"+Key_, gridCols[17]+(Betw2*0.4), (int) gridRow[23]+rowBetw*2.4);
     fill(150);
  //  text("FILTER", gridCols[19]+(Betw2), (int) gridRow[18]+rowBetw*2.4);
      text("MONITOR", gridCols[17]+(Betw2*0.4)
  , (int) gridRow[18]+rowBetw*2.4);
  
  fill(150);
  //text("test:"+ Intensity, gridCols[20]+Betw2, (int) gridRow[23]+rowBetw);
   text(monitor_flux6[0]+",  "+monitor_flux6[1]+",  "+monitor_flux6[2],  gridCols[21]+Betw2*1.4, (int) gridRow[23]+rowBetw*2.4);
   text(monitor_flux5[0]+",  "+monitor_flux5[1]+",  "+monitor_flux5[2],  gridCols[21]+Betw2*1.4, (int) gridRow[22]+rowBetw*2.4);
   text(monitor_flux4[0]+",  "+monitor_flux4[1]+",  "+monitor_flux4[2],  gridCols[21]+Betw2*1.4, (int) gridRow[21]+rowBetw*2.4);
   text(monitor_flux3[0]+",  "+monitor_flux3[1]+",  "+monitor_flux3[2],  gridCols[21]+Betw2*1.4, (int) gridRow[20]+rowBetw*2.4);
   text(monitor_flux2[0]+",  "+monitor_flux2[1]+",  "+monitor_flux2[2],  gridCols[21]+Betw2*1.4, (int) gridRow[19]+rowBetw*2.4);
   text(monitor_flux1[0]+",  "+monitor_flux1[1]+",  "+monitor_flux1[2],  gridCols[21]+Betw2*1.4, (int) gridRow[18]+rowBetw*2.4);
  }
   
 
   if (write_mon== true) {
    // if ( buffer_status == 1) 
    { monitor_flux1 [0]  = monitor_flux2 [0];   monitor_flux1 [1]  = monitor_flux2 [1];   monitor_flux1 [2]  = monitor_flux2 [2];
      monitor_flux2 [0]  = monitor_flux3 [0];   monitor_flux2 [1]  = monitor_flux3 [1];   monitor_flux2 [2]  = monitor_flux3 [2];
      monitor_flux3 [0]  = monitor_flux4 [0];   monitor_flux3 [1]  = monitor_flux4 [1];   monitor_flux3 [2]  = monitor_flux4 [2];
      monitor_flux4 [0]  = monitor_flux5 [0];   monitor_flux4 [1]  = monitor_flux5 [1];   monitor_flux4 [2]  = monitor_flux5 [2];
      monitor_flux5 [0]  = buffer_raw1;   monitor_flux5 [1]  = buffer_value;   monitor_flux5 [2]  = buffer_intensity;
      monitor_flux6 [0] = raw1; monitor_flux6 [1] = Note ; monitor_flux6 [2] = Intensity;}
    //  else  {}
   

   write_mon = false;
 //  buffer_status = 0;
   }
   
     if (filter_channel.getValue() == 0) {  filter_channel.setValueLabel("off");}
    if (filter_value.getValue() == -1) {  filter_value.setValueLabel("off");}

}
void explanationText() {   if (change_do == true)
  if (infoGraph<65) {
    // generic
 //   text("HiNT BOX \n" + "Each Modifier input on the DartMobo circuit can be configured for Pots or Buttons. \n"+ "Set Mode to POT or HYPERCURVE to manage pot-like modifiers or sensors. \n" 
 //   + "Set Mode to BUTTON or TOGGLE to manage on-off modifiers like buttons or switches.", 
 //    gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
 //   
// elementData.get(idElement).hintarea.setText("HiNT BOX \n" + "Each Modifier input on the DartMobo circuit can be configured for Pots or Buttons. \n"+ "Set Mode to POT or HYPERCURVE to manage pot-like modifiers or sensors. \n" 
  //  + "Set Mode to BUTTON or TOGGLE to manage on-off modifiers like buttons or switches.");
    
 //   elementData.get(idElement).hintarea.setText(elementData.get(infoGraph).hint_message); 
   hint_message_send (infoGraph, idElement);
 } 
  
   else if (infoGraph==2000) {  hints2.setColor(190);
   hints2.setText("MEMORY POSITION: \n This value defines the position (from 0 to 64) where the configuration of the current modifier will be stored, in the DartMobo controller's EEPROM memory. \n The memory-position is directly related to the CIRCUIT-POSITION, wich in the number of the input pin to wich the modifier (pot/button/sensor) is connected on the DartMobo. \n"+
   " Example : a button connected to the input CIRCUIT POSITION 26 will control a MIDI, DMX or HID signal, defined and stored on the MEMORY POSITION 26 \n"+
   " A Memory-position number can NOT be shared between more modifiers. When a number that is already used is selected, it will be reset to 0. The modifiers with Memory-position 0 are not sent out to the DART unit for setup.");
   
hide_all ();
 // image(circuit_position, 10, 400, 1000, 600); delay (10);
 }
  else if (infoGraph==2002) {    hints2.setColor(190);
  //  elementData.get(idElement).hintarea.setText("VALUE of the MIDI message. \n it's the FIRST DATA BYTE of the midi message. \n In a common MIDI NOTE message this value defines the PITCH of the note.");
  hints2.setText(VALUE_Strings[hint_strings_map[(elementData.get(idElement).toggleMode)]-1]);
  } 
   else if (infoGraph==2001) {    hints2.setColor(190);
   hints2.setText("Midi event TYPE:  \n Note, Poly-AT, Control-Change, Program-Change, Channel-AT and Pitch-Bend messages.");
 
  }
  
  else if (infoGraph==2003) {  hints2.setColor(190);
  //  text("HiNT BOX: \n"+  "Value of the note", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
   //  elementData.get(idElement).hintarea.setText("MINIMUM Value \n sets the lower limit of the second DATA BYTE of the midi message.");
   
    hints2.setText(MIN_Strings[hint_strings_map[(elementData.get(idElement).toggleMode)]-1]);
  } 
  else if (infoGraph==2011) {  hints2.setColor(190);
  //  text("HiNT BOX: \n"+  "Value of the note", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
    hints2.setText("hint box itself");
  }
   else if (infoGraph==2012) { hints2.setColor(190);
  //  text("HiNT BOX: \n"+  "Value of the note", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
     hints2.setText("HINTs - suggestions to users \n It is possible to write a note that explains what a modifier does. \n Use '_' ASCII symbol to separate lines.");
  }
  
   else if (infoGraph==2013) {  hints2.setColor(190);
  //  text("HiNT BOX: \n"+  "Value of the note", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
    hints2.setText("name of the modifier");
  }
     else if (infoGraph==2014) {  hints2.setColor(190);
  //  text("HiNT BOX: \n"+  "Value of the note", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
//    hints2.setText("LED OUTPUT \n This value defines wich digital output will be controlled by the current modifier. \n choose a value that is outside the 74HC595 chain (higher than 31) to have NO LED output, and avoid conflicts. ");
  
   hints2.setText(LED_Strings[hint_strings_map[(elementData.get(idElement).toggleMode)]-1]);
     
     hide_all ();
// image(circuit_position, 10, 400, 1000, 600); delay (10);
  }
  
  else if (infoGraph==2004) {  hints2.setColor(190);
 //   text("HiNT BOX: \n"+  "Max Value", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
    // elementData.get(idElement).hintarea.setText("MAXIMUM Value \n sets the upper limit of the second DATA BYTE of the midi message. \n  In a common MIDI NOTE message this value defines the VELOCITY of the note-ON message.");
  hints2.setText(MAX_Strings[hint_strings_map[(elementData.get(idElement).toggleMode)]-1]);

} 
  
  
  else if (infoGraph==2005) { hints2.setColor(190);
    //text("HiNT BOX: \n"+ "Midi Channel", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
   hints2.setText("MIDI Channel");
  } else if (infoGraph==2006) {  hints2.setColor(190);   // DMX

   hints2.setText(DMX_Strings[hint_strings_map[(elementData.get(idElement).toggleMode)]-1]);
     
  } else if (infoGraph==2007) {  hints2.setColor(190);
   
     
     hints2.setText("MODE - how a modifier works \n---------------------------------\n"+ 
      "BLIND INPUT: the input is not read.  \n_\n"+  //0
      "BUTTON: debounced on-off output.  \n_\nTOGGLE: debounced Toggle mode.\n_\n"+
      "TOGGLE GROUPS: Only one can be ON at a time in a group.\n_\n"+
      "POT: Continuous output, for potentiometers and sensors. \n_\n"+
      "HYPERCURVE 1-2: the maximum value is reached in a reduced range \n(from top to mid in hypercurve-1 / from bottom to mid in hypercurve-2) \n_\n"+
      "CENTER-CURVE 1-2: the center value (64) has a larger flat range.\n_\n"+
      "PAGE SWITCH: the page-switch itself can control a MIDI message. The settings are the same for both pages. \n_\n"+
      "DISTANCE SENSOR: It can be set to work as a normal POT or as a ON-OFF virtual button \n_\n"+
      "PADS: settings for piezo-sensors inputs. the selected value is used for the first Pad-input, all other inputs are set to the following pitch value \n_\n"+ 
      "SPINNER1 Setup: Main encoder settings. \n_\n"+  
      "SPINNER2 Setup: Secondary encoder settings. \n_\n"+
      "TOUCH SENSOR 1 and 2: Midi values, sensitivity and touch-reset funcion settings. The Virtual-touch function can be enabled by setting the LED value to 1 (EDIT mode).\n_\n"+
      "MOUSE/ARROWS: Mouse emulation setting. The JOYSTICK X and Y potentiometers position on the circuit can be defined here. when Mouse or arrow-keys emulation is activated, these potentiometers will stop their common MIDI-control function and will act as a mouse/arrows X-Y controller. To emulate mouse-buttons, just set a modifier to BUTTON MODE and set the KEYBOARD selector to MOUSE BUTTONS 1, 2 or 3. The mouse-wheel emulation can be assigned to Main o Secondary spinner.\n_\n"+
      "GENERAL: ciruit settings of the controller. A GENERAL modifier must alwais be present in a preset, otherwise the controller will work in auto-detect mode!!\n_\n" 
      ); 
     // control2.hints.setText
     /*"Hypercurve 1",


"BLIND INPUT",  //19
"PADS",  //20
"SPINNER1 SET.",  //21
"SPINNER2 SET.", // 22
"touch 1", // 23
"touch 2", // 24
"MOUSE/ARROWS",  //25
"GENERAL" }; // 26
*/
     hide_all ();
     hints2.setSize((int) gridCols[15]+Betw2,(int) gridRow[22]);
      
  } else if (infoGraph==2008) {  hints2.setColor(190);   // MODIFIERS
     hints2.setText(  "MODIFIERS - CTRL , ALT , SHIFT\n"+ 
      "if QWERTY keys emulation is active, it's possible to add MODIFIERS and combinations of them.\n"+
      "So, it's possible to emulate complex QWERTY keystrokes on a single button.");

  } else if (infoGraph==2009) {  hints2.setColor(190);
       hints2.setText("HiNT BOX: \n"+  "MODIFIERS\n"+ 
      "if QWERTY keys emulation is active, it's possible to add MODIFIERS and combinations of them.\n"+
      "So, it's possible to emulate complex QWERTY keystrokes on a single button." );
    
  //  text("HiNT BOX: \n"+  "MODIFIERS\n"+ 
   //   "if QWERTY keys emulation is active, it's possible to add MODIFIERS and combinations of them.\n"+
    //  "So, it's possible to emulate complex QWERTY keystrokes on a single button.", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  } else if (infoGraph==2010) {  //elementData.get(idElement).hintarea.setColor(190);
   hints2.setColor(190);
   //  elementData.get(idElement).hintarea.setText("HiNT BOX: \n"+  "KEYS: QWERTY keyboard emulation.\n"+
    //  "list of ASCII keys.\n"+
     // "MIDI data will not be output in QWERTY key mode, choose NULL to have midi output." );
      
       hints2.setText(KEY_Strings[hint_strings_map[(elementData.get(idElement).toggleMode)]-1]);
     // text("HiNT BOX: \n"+  "KEYS: QWERTY keyboard emulation.\n"+
    //  "list of ASCII keys.\n"+
    //  "MIDI data will not be output in QWERTY key mode, choose NULL to have midi output.", gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  else if (infoGraph==1003) {  hints2.setColor(190);
   hints2.setText("SEND ALL \n The complete preset of both two pages will be sent to the DartMobo controller. ");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  else if (infoGraph==1004) {  hints2.setColor(190);
   hints2.setText("SAVE \n - \n Presets are saved in a .csv (comma separated values) files that can be edited with a common notepad-app. \n A preset contains : \n *all infos on the graphic layout configuration (modifier's type, dimension, name, notes) \n *all infos regarding the MIDI, DMX, HID messages to be configured and sent to the DartMobo controller unit.");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
   else if (infoGraph==1005) {  hints2.setColor(190);
   hints2.setText("LOAD \n - \n Presets are saved in a .csv (comma separated values) files that can be edited with a common notepad-app. \n A preset contains : \n *all infos on the graphic layout configuration (modifier's type, dimension, name, notes) \n *all infos regarding the MIDI, DMX, HID messages to be configured and sent to the DartMobo controller unit.");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  else if (infoGraph==1006) {  hints2.setColor(190);
   hints2.setText("DRAW MODE: \n SHIFT + Mouse-left-click and drag = move a modifier, within the Modifiers Area. \nSHIFT + MouseWheel = change modifier size \n CTRL + Mouse Wheel = change modifier shape \n 'w' + MouseWheel = change modifier width \n 'h' + MouseWheel = change modifier height \n  CTRL + '-' or CTRL + '8' = hide current modifier. \n CTRL + '0' = hide all modifiers. \n CTRL + '+' or CTRL + '9'= Add a modifier ( up to 64 modifiers can be addedd)");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
   else if (infoGraph==1001) {  hints2.setColor(190);
   hints2.setText("SELECT PAGE 1 \n A Dart controller preset is divided two pages.");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
   else if (infoGraph==1002) {  hints2.setColor(190);
   hints2.setText("SELECT PAGE 2 \n A DartMobo based controller can store two presets (pages).");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  else if (infoGraph==1000) {  hints2.setColor(190);
   hints2.setText("EXIT \n or press ESC key");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  
  else if (infoGraph==1007) {  hints2.setColor(190);
   hints2.setText("SEND single element \n - \n The config of the current modifier is sent to DartMobo controller on the specified page.");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
   else if (infoGraph==1008) {  hints2.setColor(190);
   hints2.setText("Enable / disable buzzer sound");
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  
    else if (infoGraph==2020) {  hints2.setColor(190);
 //  hints2.setText("");
   hints2.setText("Hint box:  ");
  //  immagine_circuito = true;
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
     else if (infoGraph==2021) {
        // fill(255,0,0);
        hints2.setColor(color(255,150,0));
   hints2.setText("PAGE SWITCH ALREADY ACTIVATED!!"); delay(300);
   
 //  fill(140);
  //  immagine_circuito = true;
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
      else if (infoGraph==2015) {  hints2.setColor(190);
   hints2.setText("CHANNEL FILTER \n Show incoming MIDI data only on selected channel 0-16 \n If set ot OFF (0) - show all channels.");
  //  immagine_circuito = true;
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  
        else if (infoGraph==2016) {  hints2.setColor(190);
   hints2.setText("VALUE FILTER \n Show incoming MIDI data of the selected value. \n All other messages are fitered \n If set ot OFF (-1) - all messages are shown.");
  //  immagine_circuito = true;
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
          else if (infoGraph==2017) {  hints2.setColor(190);
   hints2.setText("enable / disable monitoring on incoming MIDI data");
  //  immagine_circuito = true;
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
         else if (infoGraph==2018) {  hints2.setColor(190);
   hints2.setText("enable / disable monitoring on outgoing MIDI data");
  //  immagine_circuito = true;
   // text("HiNT BOX: \n"+  "name"
     // , gridCols[1]+spaceBetw/9, gridRow[3]+rowBetw*0.4);
  }
  
  
}

void hide_all () {
 for (int del=1; del<61; del++)
   // if ( del< elementData.size()) 
   {
   //  if (elementData.get(del-1).shape != 1) 
     {
   del_ = (Knob)cp5.get(Integer.toString(del));
  //  elementData.get(del-1).hide = 1;
    del_.hide();  
     }
  }
  
  
}
