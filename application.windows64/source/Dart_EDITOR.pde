/////////////////////////// //<>//
//                       //
// DART_EDITOR           //
// Massimiliano Marchese //
// Piero pappalardo      //
// www.dartmobo.com      //
//                       //
///////////////////////////
 //<>//
//a
//Import the library to create an interface //<>// //<>//
import controlP5.*;
//to work with file
import java.util.*;
import java.util.Date;
// Import class for pop up midi port setup
import javax.swing.*; 
//Import the MidiMessage classes http://java.sun.com/j2se/1.5.0/docs/api/javax/sound/midi/MidiMessage.html
import themidibus.*; //Import the library
import javax.sound.midi.MidiMessage; 
import javax.sound.midi.SysexMessage;
import javax.sound.midi.ShortMessage;


// import processing.sound.*;
// SawOsc saw;

import ddf.minim.*;
import ddf.minim.ugens.*;

Minim       minim;
AudioOutput out;
Oscil       wave;

float frequenza2 (float nota)
{
return pow(2,((nota-69)/12))*440;
}

int keyboard_out (int f)
{
if  ( f > 118) {
      return  f-94;
      //ddl.addItem((String)keyboardList.get(i), i+3);
    } else
      if ( f >= 24 ) {
        //ddl.addItem((String)keyboardList.get(i), i+1);
        return  f+8;
      } else {
        return  f;
      }
  //  println("keysRaw", f);
   // println("keys", keyBoard);
    //println("size", keyboardList.size());
}



//***********************************
//        button state to open the ui data settings
//**********************************
int idElement=0;
int infoGraph;
float ex_dimensionX;
float ex_dimensionY;
int setValueActiveElement= 0;
float larghezza = width;
float lunghezza = height;
int colore_ = 100;
int channel_filter = 0;
int value_filter = -1;
long old_timestamp;

int i_sender  = 61;
int init_initmidi;
boolean MIDI_IN_LED;
boolean MIDI_OUT_LED;
float current_db2;
boolean monitor_color;

int general_send; // inviare il general send anche in SEND THIS ma solo se il general send è stato modificato o mai inviato.

 int test_external_ctrl;

int intro;

boolean sawplay = true;
boolean soundstatus = false;
boolean full_screen;
boolean full_screen_toggle = true;
int full_screen_scaler;

int page_count; // avoid doubles - only one modifier can be set to page at a time;
int touch1_count;
int touch2_count;
int spin1_count;
int spin2_count;
int pads_count;
int mouse_count;
int distance_count;
int general_count;

int manda_pagine =0;
byte feedback_counter;

//byte buffer_status;
int buffer_raw1;
//int buffer_Channel;
int buffer_value;
int buffer_intensity;
byte scal1;
byte scal2;

byte post_load_setup;

byte input_remap[] = {
 
6,4,8,3,
7,1,5,2,
};

String scala1 ="_";
String scala2 ="_";
String Dart_device;


boolean write_mon = false;
boolean alert_message = false;
boolean page_selected = false;




String keyCode_ = ".";
String Key_ = ".";

int change;
int change2;
boolean change_do =true;

int mousewheel;
// boolean old_loader = false;
boolean hide_;
boolean add_element;
boolean edit_mode = true;
boolean control_key = false;
// boolean immagine_circuito = false;
int [] monitor_flux1 = {000,000,000};
int [] monitor_flux2 = {000,000,000};
int [] monitor_flux3 = {000,000,000};
int [] monitor_flux4 = {1,1,1};
int [] monitor_flux5 = {1,1,1};
int [] monitor_flux6 = {1,1,1};

boolean monitor_write = true;
boolean monitor_write2 = false;
// MidiMessage old_message;

int old_value0;
int old_value1;
int old_value2;
int raw1;
int raw2;

byte delay_send = 20;
byte show_piano ;

//**********************************
//**********************************


public void settings() {
  fullScreen();
  //size(1024, 640);
 //  frame.setResizable(true);
}


void setup() {
 
  settingScreen();    // settaggi spaziatura

  background(100);

 init(); // cp5 library
  

  // THE BUTTON POSITION AND POLYMORFISM in ElementPosition class//
  setUIButtonsPosition(); // panel b - pulsanti exit, page1,page2,load,save,draw,sound
  initTableOfElementData(); // panel b
  setupElement (); // element position
   hintbox();
 
  circuit_position = loadImage("pcb_memorypositions_transp.gif");
  piano =  loadImage("piano_keys.gif");

  for (int i=0; i<elementData.size(); i++) {
      elementData.get(i).setDisplay(false);
      }
 
loadTableSettings("Default_preset.csv");

   page_selected = false ;
  
      for (int i=0; i<60; i++) {
    
       elementData.get(i).labeler();
      }
        for (int i=0; i<60; i++) {
      elementData.get(i).plug_page();
 
   if (elementData.get(i).shape != 1)  del_ = (Knob)cp5.get(Integer.toString(i+1));
  // del_ = cp5.get(Integer.toString(i+1));

      }
      
      

 frameRate(30);
 minim = new Minim(this);
 out = minim.getLineOut();
 wave = new Oscil( 440, 0.0f, Waves.SAW );
// wave.setAmplitude( 0.0 );
 wave.patch( out );
 
   idElement = 0;
  //  elementData.get(idElement).selected = true;
    elementData.get(idElement).labeler();
        elementData.get(idElement).dMode.show();
        elementData.get(idElement).dMode.setVisible(true);
         elementData.get(idElement).selected = true;
       elementData.get(idElement).setStateButton(true); 
    //  elementData.get(0).setStateButton(false);
 
  
// idElement =1; // inizio col primo item selezionato
  
   

}
void draw() {
   init_initmidi();  // non viene eseguita in loop, era un tentativo di fare in modo di ristabilire le connessioni MIDI in caso di distacco del cavo usb
  
 
  
  if (feedback_counter < 255) feedback_counter++; // temporizzatore del buzzer sound, quando si clicca su un item o si riceve un feedback.
  feedback_off();
  
  change();

 interfaceDisp();
  hints.draw();
  
 cp5.draw();

 
  myChart.push("incoming",current_db2);
 if (full_screen == false   ) 
 {
  midiMonitor();
   //if( full_screen_toggle == true)
  if(  full_screen_toggle == true)
  {
  hints2.show();
  Monitor_IN_button.show();
  Monitor_OUT_button.show();
  filter_channel.show();
 filter_value.show();
 sendOnPageOne.show();
 sendOnPageTwo.show();
 sendFirstPage.show();
 sendSecondPage.show();
 load.show();
 edit.show();
 save.show();
 exit.show();
 sound.show();}

 full_screen_toggle = false;
 elementData.get(idElement).labeler();
 }
if (full_screen == true && full_screen_toggle !=  full_screen
) 
 {
   full_screen_toggle = true;
 hints2.hide();
 Monitor_IN_button.hide();
 Monitor_OUT_button.hide();
 filter_channel.hide();
 filter_value.hide();
  sendOnPageOne.hide();
 sendOnPageTwo.hide();
 sendFirstPage.hide();
 sendSecondPage.hide();
  load.hide();
 edit.hide();
 save.hide();
 exit.hide();
 sound.hide();
 elementData.get(idElement).labeler();
 }

   
  if (i_sender >= 59) 
  {
  elementListener(); // to trigger the button element of the dart
  explanationText();
   //  allarme_page_();
  //THE MIDI MONITOR in MIDIClass//
   // in midi class
  // delay(300);
  
 keyPressed_();
 
 image_circuit();

post_load_setup_();
 sawplay ();
}
 
else {
  
if (i_sender == 0) {   myBus.sendMessage(241, 0, 0); delay(50); 
hints2.setText("SENDING the complete preset to the DART device");
}
sender();

if (i_sender == 59)  myBus.sendMessage(241, 0, 0);

}

show_piano ();


// if (test_external_ctrl < 127) test_external_ctrl++; else { test_external_ctrl = 0; delay(5);} // test per controllo esterno delle Knobs
// cp5.get("15").setValue(test_external_ctrl) ;

}
/*void controllo_doppioni () {
    for (int i=0; i<64; i++) {
    if (elementData.get(i).toggleMode == 17) 
    {
    elementData.get(idElement).hintarea.setText("page switch already activated on modifier n."+i); 
  }
  }
}*/




void post_load_setup_()
{
  if (post_load_setup == 1) { 
  
  }
  
  post_load_setup =0;
  
  
}
void keyPressed_ () {
  if (keyPressed) {
    keycode2string();
    Key_ = str(key);
    
     if (key =='f'){
      full_screen=false;
      
   // if (full_screen == true) 
    { myChart.show();
          for (int i=0; i<60; i++) {
 // elementData.get(i).loadTableRow(table.getRow(i));
{
  Knob t = (Knob)cp5.get(Integer.toString(i+1)); 
// if (elementData.get(i).hide == 1) t.hide(); else t.show();
    t.setPosition(
    int(elementData.get(i).posX),
    int(elementData.get(i).posY)
    );
    
   // elementData.get(i).myKnobA.setPosition(elementData.get(i).posX,elementData.get(i).posY);
 //   t.setLabel(elementData.get(i).label);
    t.setWidth(elementData.get(i).radiusW);
    t.setHeight(elementData.get(i).radiusH);
  //  t.setSize(elementData.get(i).radiusW,3);
   // t.setSize(int(elementData.get(i).radiusW),3);
  //  t.hide();
 }

   
  //  delay(10);
  
  
  }}
  
 /* else { myChart.hide();
    for (int i=0; i<60; i++) {
 // elementData.get(i).loadTableRow(table.getRow(i));
{
  Knob t = (Knob)cp5.get(Integer.toString(i+1)); 
// if (elementData.get(i).hide == 1) t.hide(); else t.show();
    t.setPosition(
    int(elementData.get(i).posX*1.33+gridCols[1]*1.2),
    int(elementData.get(i).posY*1.33-gridRow[8])
    );
    
 
    t.setWidth(int(elementData.get(i).radiusW*1.33));
    t.setHeight(int(elementData.get(i).radiusH*1.33));

 }

  }
  
  }*/
  
  
    //  settingScreen();
    
    }
    
   
    if (key =='g'){
      full_screen=true;
    //  settingScreen();
    myChart.hide();
    for (int i=0; i<60; i++) {
 // elementData.get(i).loadTableRow(table.getRow(i));
{
  Knob t = (Knob)cp5.get(Integer.toString(i+1)); 
// if (elementData.get(i).hide == 1) t.hide(); else t.show();
    t.setPosition(
    int(elementData.get(i).posX*1.33+gridCols[1]*1.2),
    int(elementData.get(i).posY*1.33-gridRow[8])
    );
    
 
    t.setWidth(int(elementData.get(i).radiusW*1.33));
    t.setHeight(int(elementData.get(i).radiusH*1.33));

 }

  }
    
    }
    
  if (edit_mode == false) { 
    
   /* if (key =='t') {
    elementData.get(idElement).feedback_ = true;
    }
    */
    
   
    
    
       if (key == '+' || key =='9')
    if ( control_key== true) {
     add_element = true;
   for (int ix=0; ix< 60 ; ix++) {   
   //  elementData.size
     Button t = (Button)cp5.get(Integer.toString(ix+1));
  //int mouse_X = (mouseX/35)*35;
 // int mouse_Y = (mouseY/35)*35;
 // mouse_X = mouse_X*50;
  hide_ = t.isVisible(); 
   if (hide_ == false && add_element==true) {t.show(); add_element= false; key = 0; elementData.get(ix).hide = 0; // elementData.get(ix).memoryPosition = 0;
 }
   }
   
    }
    
    if (key == '0'  && control_key== true
    ) 
    {
     // delay(100);
  for (int del=0; del<60; del++)
   // if (  elementData.get(del-1).toggleMode != 26) 
 //  if (  elementData.get(del).dMode.getValue() != 26) 
   if (elementData.get(del).toggleMode != 26) {
   del_ = (Knob)cp5.get(Integer.toString(del+1));
   elementData.get(del).hide = 1;
    elementData.get(del).toggleMode = 0;
    elementData.get(del).toggleMode_2nd = 0;  
    del_.hide();   
   // del_.setVisible(false);
   
  }
  key = 0;
}
  if (key == '-' || key =='8') if ( control_key== true //&& elementData.get(idElement).toggleMode != 26
  )
  if (elementData.get(idElement).toggleMode != 26) {
    Button t = (Button)cp5.get(Integer.toString(idElement+1));
  t.hide();
  elementData.get(idElement).hide = 1;
      elementData.get(idElement).toggleMode = 0;
    elementData.get(idElement).toggleMode_2nd = 0;

    }
  }
    
    if ( key == ENTER)
  //{ delay(100); elementData.get(idElement).labell.setFocus(false);      elementData.get(idElement).labell.setText(elementData.get(idElement).label);}
   {   Knob t = (Knob)cp5.get(Integer.toString(idElement+1));
    t.setLabel(elementData.get(idElement).label);
  //  labell.setText(elementData.get(idElement).label);
 // delay(10);
 elementData.get(idElement).label_hint.setFocus(false); // delay(10); // 

// elementData.get(idElement).hintarea.setText(elementData.get(idElement).hint_message);
 //  delay(100); 
   elementData.get(idElement).labell.setFocus(false);      
   //elementData.get(idElement).labell.setText(elementData.get(idElement).label);
}
    if (keyCode == CONTROL) {control_key = true; }
  }
}

//void submit_label () {
//{ delay(100); elementData.get(idElement).labell.setFocus(false);      elementData.get(idElement).labell.setText(elementData.get(idElement).label);}
//}

void keyReleased(){
// Key_ = str(key);


keyCode = 0; key=0; control_key=false; keyCode_=""; Key_ =""; elementData.get(idElement).feedback_ = false;}

void mouseWheel(MouseEvent event) {
 
   // float e = event.getCount();
  if (event.getCount() == 1) //{mousewheel = constrain(mousewheel++,0,10); } else { mousewheel = constrain(mousewheel--,0,10);}
  mousewheel++; else mousewheel--;
 if (edit_mode == false) {
  if (keyCode == SHIFT) {
    
  mousewheel = constrain(mousewheel,1,5);
 // println(e);
Knob t = (Knob)cp5.get(Integer.toString(idElement+1));
  t.setSize(30*mousewheel, 30*mousewheel);
    elementData.get(idElement).dimension = mousewheel; 
     elementData.get(idElement).radiusW = 30*mousewheel; 
     elementData.get(idElement).radiusH = 30*mousewheel; 
 //  t.setPosition(mouseX, mouseY);
 }
   if (key =='w' || key =='W') {
    
  mousewheel = constrain(mousewheel,1,5);
 // println(e);
Knob t = (Knob)cp5.get(Integer.toString(idElement+1));
//  t.setSize(30*mousewheel, elementData.get(idElement).radiusH);
   t.setWidth(30*mousewheel);
   //  elementData.get(idElement).dimension = mousewheel; 
     elementData.get(idElement).radiusW = 30*mousewheel; 
    // elementData.get(idElement).radiusH = 30*mousewheel; 
 //  t.setPosition(mouseX, mouseY);
 }
 
    if (key =='h' || key =='H') {
    
  mousewheel = constrain(mousewheel,1,5);
 // println(e);
Knob t = (Knob)cp5.get(Integer.toString(idElement+1));
  t.setSize((elementData.get(idElement).radiusW), 30*mousewheel);
   t.setHeight(30*mousewheel);
   //  elementData.get(idElement).dimension = mousewheel; 
   //  elementData.get(idElement).radiusW = 30*mousewheel; 
     elementData.get(idElement).radiusH = 30*mousewheel; 
 //  t.setPosition(mouseX, mouseY);
 }
 
/*   if (key =='q' || key =='Q') {
    
  mousewheel = constrain(mousewheel,1,6);
 // println(e);
//Button t = (Button)cp5.get(Integer.toString(memPosLUT[idElement]));
 // t.setSize((elementData.get(idElement).radiusW), 30*mousewheel);
   //  elementData.get(idElement).dimension = mousewheel; 
   //  elementData.get(idElement).radiusW = 30*mousewheel; 
     elementData.get(idElement).labeler_ = mousewheel; 
     elementData.get(idElement).labeler();
 //  t.setPosition(mouseX, mouseY);
 }*/
 
 
 
 if (keyCode == CONTROL) {
    mousewheel = constrain(mousewheel,1,12);
 // println(e);
changeshape(idElement);



 
 }
  }
 
}

void controlEvent(CallbackEvent theEvent) {
  //println("id element: "+ idElement);
  // theEvent.getController().getId()
  // elementData.get(theEvent.getController().getId()).labeler();
  switch(theEvent.getAction()) {
    case (ControlP5.ACTION_ENTER): 
     if ( theEvent.getController().isVisible() == true) infoGraph = theEvent.getController().getId(); else {}
     
     change= 0;
     break;
     
        case (ControlP5.ACTION_LEAVE): 
        if ( theEvent.getController().isVisible() == true) infoGraph = 2020;  // 2020 -- messaggio triggerato quando si esce col mouse da un elemento.
      else {}
     //infoGraph = 2020;
     change= 1;
     
    {page_count =0;
    touch1_count =0;
    touch2_count =0;
    spin1_count =0;
    spin2_count =0;
    pads_count =0;
    mouse_count =0;
    distance_count =0;
    general_count =0;
    
      for (int del=0; del<60; del++)  // per visualizzare di nuovo tutti i modificatori dopo aver visto l'immagine del circuito dartmobo

   {
     if (elementData.get(del).toggleMode == 17) { page_count++;}
     if (elementData.get(del).toggleMode == 23) { touch1_count++;}        // touch 1
      if (elementData.get(del).toggleMode == 24) { touch2_count++;}          // touch2
       if (elementData.get(del).toggleMode == 21) { spin1_count++;}  //spin1
        if (elementData.get(del).toggleMode == 22) { spin2_count++;}   // spin2
         if (elementData.get(del).toggleMode == 20) { pads_count++;}  //pads
          if (elementData.get(del).toggleMode == 25) { mouse_count++;}    // mouse
          // if (elementData.get(del).toggleMode == 18) { distance_count++;}    // distance
            if (elementData.get(del).toggleMode == 26) { general_count++;}    // general
   del_ = (Knob)cp5.get(Integer.toString(del+1));
  if (elementData.get(del).hide == 0)  
 { del_.show();   }
 // if (elementData.get(del).toggleMode == 26)  { if ( edit_mode == true) del_.hide(); else del_.show();}
 hints2.setSize((int) gridCols[15]+Betw2,(int) gridRow[5]);
 
  }
  
  
  if (page_count >1 || touch1_count >1 || touch2_count > 1 || spin1_count >1 || spin2_count >1 || pads_count >1 || mouse_count >1 || distance_count >1 || general_count > 1) { 
  elementData.get(idElement).toggleMode = 0;
   elementData.get(idElement).dMode.setValue(0);
 //  elementData.get(idElement).labeler();
 //  elementData.get(idElement).plug_page();
  infoGraph = 2021;                                    // messaggio avviso , l'item scelto è già attivato

}
    }
     // immagine_circuito = false;
    // if (elementData.get(idElement).togglestate == true ) 
 
     
  //   hints2.setText("hint box:  ");
  
     break;
     
    case(ControlP5.ACTION_PRESSED): 
   /* if (monitor_color == false) {                                     // tentativo di cambiare colore al monitoring chart dei segnali midi - non si può fare
      myChart.setColors("incoming", color(30,30,30),color(50,50,50));
      myChart.setColorBackground(color(0,0,0)) ; 
      monitor_color = true;
}
    else
    {
     myChart.setColors("incoming", color(0,0,0),color(50,50,50));
      myChart.setColorBackground(color(30,30,30)) ; 
      monitor_color = false;
    }*/
    
    
    change = 2;
    feedback_counter = 0; // temporizzatore del sound
  
      infoGraph = theEvent.getController().getId();
  
      if (infoGraph != -1 && infoGraph<elementData.size()) {
 
      idElement = theEvent.getController().getId();
      
   
   //  
 if(  elementData.get(idElement).toggleMode == 1 && page_selected == false ||
 elementData.get(idElement).toggleMode_2nd == 1 && page_selected == true 
 ) elementData.get(idElement).sendMidiMessageON(); // invia messaggio midi quando premo sul modificatore -  se siamo in modalità 1 -button 
 
   
    elementData.get(idElement).setStateButton(true);                                               // gestione modalità toggle
    
    //////////////////////////////////////////////////////////////////
   
    if ( elementData.get(idElement).toggleMode > 1 && elementData.get(idElement).toggleMode < 11    )   
   { if (page_selected == false) { if (elementData.get(idElement).togglestate == false) elementData.get(idElement).sendMidiMessageON();
    elementData.get(idElement).togglestate  = ! elementData.get(idElement).togglestate;
    // ----------------------------  sezione off groups
    for (int del=0; del<60; del++)  // per visualizzare di nuovo tutti i modificatori dopo aver visto l'immagine del circuito dartmobo
{  
  if(del != idElement)
  {    if (elementData.get(del).toggleMode >2 && elementData.get(del).toggleMode < 11 ) {
        if( elementData.get(del).togglestate == true && elementData.get(del).toggleMode == elementData.get(idElement).toggleMode )
      {
elementData.get(del).togglestate = false; // il pulsante che prima era acceso (grigio) adesso è pronto per essere premuto e riacceso.
 elementData.get(del).setStateButton(false); // 
 
 myBus.sendMessage(144+(elementData.get(del).indexMidiType)*16+elementData.get(del).midiChannel-1,  // invio messaggio midi off del pulsante precedente.
 elementData.get(del).note[0], 0);
    if (Monitor_OUT_button.isOn() )
       {
   buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(del).midiChannel;
  Note = elementData.get(del).note[0]; 
  optCC= typeMidiList[(elementData.get(del).indexMidiType)];
  Intensity = 0;
   current_db2 = Intensity;
  raw1 = elementData.get(del).indexMidiType*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;
  feedback_counter = 0;
       }}}}} // ----------------------------
}}


////////////////////////////////
  if ( elementData.get(idElement).toggleMode_2nd > 1 && elementData.get(idElement).toggleMode_2nd < 11    )   
   { if (page_selected == true) { if (elementData.get(idElement).togglestate_2nd == false) elementData.get(idElement).sendMidiMessageON();
    elementData.get(idElement).togglestate_2nd  = ! elementData.get(idElement).togglestate_2nd;
    // ----------------------------  sezione off groups
    for (int del=0; del<60; del++)  // per visualizzare di nuovo tutti i modificatori dopo aver visto l'immagine del circuito dartmobo
{  
  if(del != idElement)
  {    if (elementData.get(del).toggleMode_2nd >2 && elementData.get(del).toggleMode_2nd < 11 ) {
        if( elementData.get(del).togglestate_2nd == true && elementData.get(del).toggleMode_2nd == elementData.get(idElement).toggleMode_2nd )
      {
elementData.get(del).togglestate_2nd = false; // il pulsante che prima era acceso (grigio) adesso è pronto per essere premuto e riacceso.
 elementData.get(del).setStateButton(false); // 
 
 myBus.sendMessage(144+(elementData.get(del).indexMidiType_2nd)*16+elementData.get(del).midiChannel_2nd-1,  // invio messaggio midi off del pulsante precedente.
 elementData.get(del).note[1], 0);
    if (Monitor_OUT_button.isOn() )
       {
   buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(del).midiChannel_2nd;
  Note = elementData.get(del).note[1]; 
  optCC= typeMidiList[(elementData.get(del).indexMidiType_2nd)];
  Intensity = 0;
   current_db2 = Intensity;
  raw1 = elementData.get(del).indexMidiType_2nd*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;
  feedback_counter = 0;
       }}}}} // ----------------------------
}}



 /* if (elementData.get(idElement).toggleMode_2nd > 1 && elementData.get(idElement).toggleMode_2nd < 11 && page_selected == true )
    { if (elementData.get(idElement).togglestate_2nd == false) elementData.get(idElement).sendMidiMessageON();
    elementData.get(idElement).togglestate_2nd  = ! elementData.get(idElement).togglestate_2nd;}
    */
    

  if(idElement != 0 ) {
  //
  if (page_selected == false){
if ( elementData.get(0).toggleMode <2 || elementData.get(0).toggleMode >10) {elementData.get(0).setStateButton(false);}}
else
{if ( elementData.get(0).toggleMode_2nd <2 || elementData.get(0).toggleMode_2nd >10) {elementData.get(0).setStateButton(false);}
}

}

  
   
    
    }
    


    

    break;
    case(ControlP5.ACTION_RELEASED):
    change =3;
   // saw.stop();
    if (idElement != -1 && idElement<elementData.size()) {
     
      if (elementData.get(idElement).togglestate == false && page_selected == false) 
    {elementData.get(idElement).setStateButton(false);  elementData.get(idElement).sendMidiMessageOFF();}
    
      if (elementData.get(idElement).togglestate_2nd == false && page_selected == true) 
    {elementData.get(idElement).setStateButton(false);  elementData.get(idElement).sendMidiMessageOFF();}
    
    }
    //println("Action:RELEASED");
    break;
  }
}
void elementListener() {
  if (idElement != -1 && idElement< elementData.size()) {
    // for (int i=0; i<elementData.size(); i++) {
    if ( elementData.get(idElement).getStateButton()  ) {
      
    
     if( !elementData.get(idElement).getStateDisplay()){
      elementData.get(idElement).setDisplay(true);
      elementData.get(idElement).labeler();
      if (elementData.get(idElement).selected == false) elementData.get(idElement).selected = true;
  
     elementData.get(idElement).labell.setText(elementData.get(idElement).label);
   
    elementData.get(idElement).mp.setValue((elementData.get(idElement).memoryPosition));
   //   elementData.get(idElement).hintarea.setText(elementData.get(idElement).hint_message);
       hint_message_send (idElement, idElement);
   
     }
  
    } 
   
    if (full_screen == false)  elementData.get(idElement).displaySettingsUI();
  //  elementData.get(idElement).setStateButton(true);
    
  }
  for (int i=0; i<60; i++) {
    if (i != idElement && idElement< elementData.size()) {
    // 
   // if (elementData.get(idElement).togglestate == false )  elementData.get(i).setStateButton(false); // -----------------------------
    
    
      elementData.get(i).setDisplay(false);
      elementData.get(i).selected = false;
    
  //    Textfield t = (Textfield)cp5.get(Integer.toString(i));
    // t.setActive(false);
     elementData.get(i).labell.setFocus(false);
      elementData.get(i).label_hint.setFocus(false);
    }
  }
  //println("reset others");
}

void changeshape(int elemento) {
 
 // if (elementData.get(elemento).shape != 1)
  {
  
  Knob t = (Knob)cp5.get(Integer.toString(elemento+1));

  elementData.get(elemento).shape= mousewheel;  
  
 switch (mousewheel) {
 case 1: 
 t.setView(new Manopolox());
 break;
 case 2: 
 t.setView(new Manopolox2());
 break;
 case 3:
 t.setView(new SisButton2());
 break;
 case 4:
 t.setView(new RectButton());
 break;
 case 5:
 t.setView(new fader());
 break;
 case 6:
 t.setView(new polygon_());
 break;
 case 7:
 t.setView(new Marrone_polygon());
 break;  
 case 8:
 t.setView(new SensorButton());
 break;
 case 9:
 t.setView(new fader2_orizz());
 break;
 case 10:
 t.setView(new fader3_vert());
 break;
 case 11: 
 t.setView(new Grigio());
 break;
 case 12: 
 t.setView(new faderox());
 break;

 
 }
 
}

}

void hint_message_send (int quale, int dove) {
 // elementData.get(idElement).hintarea.setText(elementData.get(idElement).hint_message);
//  String [] hint_message_righe = {"0","0","0","0","0","0","0","0"};
String [] hint_message_righe  = {".",".",".",".",".",".",".",".","."};
  String messaggio_entrata = elementData.get(quale).hint_message;
  String Messaggio_elaborato;
  int riga = 0;
   for (int ix=0; ix< messaggio_entrata.length() ; ix++) 
 {
  
   
 if ( messaggio_entrata.charAt(ix) == '_') 
 {
   if (hint_message_righe[riga].length() == 0 || hint_message_righe[riga].length() == '_') hint_message_righe[riga] = "_";
 riga++;
 }
  else hint_message_righe [constrain(riga,0,8)] = hint_message_righe[riga]+messaggio_entrata.charAt(ix);
  
 }
 /*Messaggio_elaborato = 
 (
        hint_message_righe[0].substring(1)
 +" \n"+hint_message_righe[2].substring(1)
 +" \n"+hint_message_righe[2]
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 );
 */
 Messaggio_elaborato = 
 (
  hint_message_righe[0].substring(1)+"\n"
 +hint_message_righe[1].substring(1)+"\n"
 +hint_message_righe[2].substring(1)+"\n"
 +hint_message_righe[3].substring(1)+"\n"
 +hint_message_righe[4].substring(1)+"\n"
 +hint_message_righe[5].substring(1)+"\n"
 +hint_message_righe[6].substring(1)+"\n"
 +hint_message_righe[7].substring(1)+"\n"
 +hint_message_righe[8].substring(1)+"\n"
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 // +" \n"+hint_message_righe[1].substring(1)
 );
 //Messaggio_elaborato = (hint_message_righe[0]+" \n"+hint_message_righe[1]);
  hints2.setText("Hint box:  "+ Messaggio_elaborato);
 }

void change()  {
if (change2 != change) {
  change2 = change;
  change_do = true;

} else   change_do = false;


}

void image_circuit() {
if (infoGraph == 2000 || infoGraph == 2014) {image(circuit_position, (int) gridCols[1],(int)gridRow[8] //+rowBetw
, (int) gridCols[15], (int) gridRow[16]+rowBetw); 
delay (10); delay(100);
//(int) gridCols[15]+Betw2/2,(int) gridRow[5]

}

}


void keycode2string() {
switch (keyCode) {
case CONTROL:
keyCode_ = "CONTROL ";
break;
case UP:
keyCode_ = "UP ";
break;
case DOWN:
keyCode_ = "DOWN ";
break;
case RIGHT:
keyCode_ = "RIGHT ";
break;
case LEFT:
keyCode_ = "LEFT ";
break;
case ALT:
keyCode_ = "ALT ";
break;
case SHIFT:
keyCode_ = "SHIFT ";
break;

}





}


void feedback_off ()
{
if (feedback_counter == 4) {
for (byte i = 0;i < 60; i++)
{
//if (elementData.get(i).feedback_ == true)
elementData.get(i).feedback_ = false;
}

// saw.amp(0.0);
 wave.setAmplitude( 0.0 );
MIDI_IN_LED = false;
MIDI_OUT_LED = false;

}

}

void sawplay ()
{
  if ( soundstatus == true && sawplay == false) { // saw.stop(); 
  wave.setAmplitude( 0.0 );
sawplay = true;}
  if ( soundstatus == false && sawplay == true) {// saw.play(); 
   wave.setAmplitude( 0.5 );
sawplay = false;}
}

void show_piano ()
{
if (show_piano == 1) image(piano, gridCols[20]+(Betw2*0.95)
,gridRow[16]+rowBetw , 
gridCols[3]-(Betw2/6),  gridCols[1]); 
   // .setPosition(gridCols[20]+Betw2, gridRow[16]+rowBetw)
   //  .setSize((int) gridCols[3],(int) gridCols[3]/12)
}

void init_initmidi()
{
  if (init_initmidi ==0 ){
 //     fill(0, 255, 0);
 // textSize(Betw2/1.7);
 //  text("grgrgrgr ", gridCols[2]+(Betw2*0.4), (int) gridRow[1]+rowBetw*2.4); delay (400);
  
    initMidi();
  myBus = new MidiBus(this, inD, outD);   
  myBus.sendTimestamps(false);
  init_initmidi =1;}
}
