import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import java.util.*; 
import java.util.Date; 
import javax.swing.*; 
import themidibus.*; 
import javax.sound.midi.MidiMessage; 
import javax.sound.midi.SysexMessage; 
import javax.sound.midi.ShortMessage; 
import ddf.minim.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DART_EDITOR extends PApplet {

/////////////////////////// //<>//
//                       //
// DART_EDITOR  V1.55    //
// Massimiliano Marchese //
// Piero pappalardo      //
// www.dartmobo.com      //
//                       //
///////////////////////////
 //<>//
//a
//Import the library to create an interface //<>// //<>//

//to work with file


// Import class for pop up midi port setup
 
//Import the MidiMessage classes http://java.sun.com/j2se/1.5.0/docs/api/javax/sound/midi/MidiMessage.html
 //Import the library
 




// import processing.sound.*;
// SawOsc saw;




Minim       minim;
AudioOutput out;
Oscil       wave;

public float frequenza2 (float nota)
{
return pow(2,((nota-69)/12))*440;
}

public int keyboard_out (int f)
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


public void setup() {
 
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
public void draw() {
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




public void post_load_setup_()
{
  if (post_load_setup == 1) { 
  
  }
  
  post_load_setup =0;
  
  
}
public void keyPressed_ () {
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
    PApplet.parseInt(elementData.get(i).posX),
    PApplet.parseInt(elementData.get(i).posY)
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
    PApplet.parseInt(elementData.get(i).posX*1.33f+gridCols[1]*1.2f),
    PApplet.parseInt(elementData.get(i).posY*1.33f-gridRow[8])
    );
    
 
    t.setWidth(PApplet.parseInt(elementData.get(i).radiusW*1.33f));
    t.setHeight(PApplet.parseInt(elementData.get(i).radiusH*1.33f));

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
     Knob t = (Knob)cp5.get(Integer.toString(ix+1));
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
    Knob t = (Knob)cp5.get(Integer.toString(idElement+1));
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

public void keyReleased(){
// Key_ = str(key);


keyCode = 0; key=0; control_key=false; keyCode_=""; Key_ =""; elementData.get(idElement).feedback_ = false;}

public void mouseWheel(MouseEvent event) {
 
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

public void controlEvent(CallbackEvent theEvent) {
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
public void elementListener() {
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

public void changeshape(int elemento) {
 
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

public void hint_message_send (int quale, int dove) {
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

public void change()  {
if (change2 != change) {
  change2 = change;
  change_do = true;

} else   change_do = false;


}

public void image_circuit() {
if (infoGraph == 2000 || infoGraph == 2014) {image(circuit_position, (int) gridCols[1],(int)gridRow[8] //+rowBetw
, (int) gridCols[15], (int) gridRow[16]+rowBetw); 
delay (10); delay(100);
//(int) gridCols[15]+Betw2/2,(int) gridRow[5]

}

}


public void keycode2string() {
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


public void feedback_off ()
{
if (feedback_counter == 4) {
for (byte i = 0;i < 60; i++)
{
//if (elementData.get(i).feedback_ == true)
elementData.get(i).feedback_ = false;
}

// saw.amp(0.0);
 wave.setAmplitude( 0.0f );
MIDI_IN_LED = false;
MIDI_OUT_LED = false;

}

}

public void sawplay ()
{
  if ( soundstatus == true && sawplay == false) { // saw.stop(); 
  wave.setAmplitude( 0.0f );
sawplay = true;}
  if ( soundstatus == false && sawplay == true) {// saw.play(); 
   wave.setAmplitude( 0.5f );
sawplay = false;}
}

public void show_piano ()
{
if (show_piano == 1) image(piano, gridCols[20]+(Betw2*0.95f)
,gridRow[16]+rowBetw , 
gridCols[3]-(Betw2/6),  gridCols[1]); 
   // .setPosition(gridCols[20]+Betw2, gridRow[16]+rowBetw)
   //  .setSize((int) gridCols[3],(int) gridCols[3]/12)
}

public void init_initmidi()
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
// toggle e toggle group non funziona bene nella seconda page 

// l'item 1 rimane grigio se cicco altri item all'avvio

// aggiungere hint per tutti i modificatori e sul general. - fatto 

// aggiungere hint sound - fatto
// problemi nella modalità draw - non spariscono i box della memoryposition. - fatto 

// button mode - pc e pb non devono mandare note off - ok
// il toggle deve lasciare il pulsante in grigio quando è acceso - deve valere per tutti i pulsanti
// ved per invertire il senso del mouse con la rotazione delle manopole


// problema feedback - quando agisco sulla value della knob dall'esterno, mi triggera comunque la void ctrl... ed esce midi - fatto
// feedback : se ricevo una nota da fuori, il note off mi azzera il volume - fatto

// diminuire il tempo di permanenza del suono quando si preme su un toggle. - fatto

// 
// feedback visivo movimento knobs aggiungere - fatto
// comunicazione via sysex
// sostituire la parola modifiers, con hotkeys - fatto

// 
 Table table;
// *****************************************
// init TABLE to set data // 
//******************************************
public void initTableOfElementData() {
  table = new Table();
  table.addColumn("Name");
  table.addColumn("MemoryPosition");
  table.addColumn("midiType");
  table.addColumn("midiType_2nd");
  table.addColumn("Note");
  table.addColumn("Note_2nd");
  table.addColumn("MaximumValue");
  table.addColumn("MaximumValue_2nd");
  table.addColumn("MinimumValue");
  table.addColumn("MinimumValue_2nd");
  table.addColumn("MidiChannel");
  table.addColumn("MidiChannel_2nd");
  table.addColumn("Toggle");
  table.addColumn("Toggle_2nd");
  table.addColumn("Keys");
  table.addColumn("Keys_2nd");
  table.addColumn("Modifiers");
  table.addColumn("Modifiers_2nd");
  table.addColumn("DMXAddress");
  table.addColumn("DMXAddress_2nd");
  table.addColumn("Page");
  table.addColumn("Hide");
   table.addColumn("posX");
    table.addColumn("posY");
    table.addColumn("shape");
     table.addColumn("dimensionX");
     table.addColumn("dimensionY");
     table.addColumn("labeler");
     table.addColumn("led");
       table.addColumn("hint_message");
       
}
// *****************************************
// SAVE AND LOAD SETTINGS INTO TABLE // 
//******************************************
public void saveTableSettings(String s ) {
   initTableOfElementData();
  for (int i =0; i<60; i++) {
    TableRow r = table.getRow(i);
    r.setString("Name", elementData.get(i).label);
    r.setInt("MemoryPosition", elementData.get(i).memoryPosition);
   
 //  r.setInt("MemoryPosition", input_remap[((elementData.get(i).memoryPosition-1) -(((elementData.get(i).memoryPosition-1)/8)*8))]+ (((elementData.get(i).memoryPosition-1)/8)*8)  ); // remapper new memoryposition semplificata
   
    r.setInt("midiType", elementData.get(i).indexMidiType);
    r.setInt("midiType_2nd", elementData.get(i).indexMidiType_2nd);
    r.setInt("Note", elementData.get(i).note[0]);
     r.setInt("Note_2nd", elementData.get(i).note[1]);
    r.setInt("MaximumValue", elementData.get(i).setExcursionControllMax);
    r.setInt("MaximumValue_2nd", elementData.get(i).setExcursionControllMax_2nd);
    r.setInt("MinimumValue", elementData.get(i).setExcursionControllMin);
    r.setInt("MinimumValue_2nd", elementData.get(i).setExcursionControllMin_2nd);
    r.setInt("MidiChannel", elementData.get(i).midiChannel);
    r.setInt("MidiChannel_2nd", elementData.get(i).midiChannel_2nd);
    r.setInt("Toggle", elementData.get(i).toggleMode);
    r.setInt("Toggle_2nd",elementData.get(i).toggleMode_2nd);
    r.setInt("Keys", elementData.get(i).keyBoard);
    r.setInt("Keys_2nd", elementData.get(i).keyBoard_2nd);
    r.setInt("Modifiers", elementData.get(i).modifiers);
    r.setInt("Modifiers_2nd", elementData.get(i).modifiers_2nd);
    r.setInt("DMXAddress", elementData.get(i).addressDMX);
    r.setInt("DMXAddress_2nd", elementData.get(i).addressDMX_2nd);
    r.setInt("Page", PApplet.parseInt (elementData.get(i).togglePage));
   
   r.setInt("Hide", elementData.get(i).hide);
    r.setInt("posX", elementData.get(i).posX);
    r.setInt("posY", elementData.get(i).posY);
    r.setInt("shape", elementData.get(i).shape);
      r.setInt("dimensionX", elementData.get(i).radiusW);
       r.setInt("dimensionY", elementData.get(i).radiusH);
       r.setInt("labeler", elementData.get(i).labeler_);
        r.setInt("led", elementData.get(i).led_);
       r.setString("hint_message", elementData.get(i).hint_message);
       }
       
     //  if (i == 63) 
       {  
         TableRow r = table.getRow(64);   
        r.setInt("dimensionX", width);
       r.setInt("dimensionY", height);
                    }
  
  saveTable(table, s+".csv");
}












public void loadTableSettings(String s) {
  page_selected = false;                   // rimettiti sulla prima pagina

  sendFirstPage.setOn();
  sendSecondPage.setOff();
  table = loadTable(s, "header");
  TableRow roow;  roow = table.getRow(64); ex_dimensionX = roow.getInt("dimensionX"); ex_dimensionY = roow.getInt("dimensionY");
  idElement = 1;
  
 // println("----------");
  for (int i=0; i<60; i++) {
  elementData.get(i).loadTableRow(table.getRow(i));
   

{
  Knob t = (Knob)cp5.get(Integer.toString(i+1)); 
 if (elementData.get(i).hide == 1) t.hide(); else t.show();
    t.setPosition(elementData.get(i).posX,elementData.get(i).posY);
   // elementData.get(i).myKnobA.setPosition(elementData.get(i).posX,elementData.get(i).posY);
  //  t.setSize(elementData.get(i).radiusW,3);
    t.setLabel(elementData.get(i).label);
    t.setWidth(elementData.get(i).radiusW);
    t.setHeight(elementData.get(i).radiusH);
   
  //  t.hide();
 }
   
    
    hint_message_send (i, i);
    elementData.get(i).label_hint.setText(elementData.get(i).hint_message); 
    mousewheel = elementData.get(i).shape;
    changeshape(i);
   
    delay(10);
  
  
  }
  
 
  
 /* for (int i=0; i<60; i++) {
      elementData.get(i).setDisplay(false);
      elementData.get(i).selected = false;
      if  (elementData.get(i).hide == 0) idElement = i;
      }*/
      
      
    //  elementData.get(4).selected = true;
//          sendSecondPage.setOff();   
 //       sendFirstPage.setOn();
        
  /*   page_selected = true;
       for (int i=0; i<60; i++) {
      
      elementData.get(i).plug_page();
         elementData.get(i).labeler();
      }
        page_selected = false;
       for (int i=0; i<60; i++) {
      
      elementData.get(i).plug_page();
         elementData.get(i).labeler();
      }
      */
      //  delay(5000);
      sendSecondPage.setOff();   
        sendFirstPage.setOn();
        
     page_selected = false;
       for (int i=0; i<60; i++) {
      
      elementData.get(i).plug_page();
         elementData.get(i).labeler();
      }
       initTableOfElementData();
  //  idElement = 0;
  //  elementData.get(idElement).selected = true;
//  cp5.get(Integer.toString(idElement)).setOn();
   
}







public void fileToSave(File selection) {
  if (selection == null) {
  //  println("Window was closed or the user hit cancel.");
  } else {
   // println("User selected " + selection.getAbsolutePath());
    saveTableSettings(selection.getAbsolutePath());
  }
}
public void fileToLoad(File selection) {
  if (selection == null) {
  //  println("Window was closed or the user hit cancel.");
  } else {
   // println("User selected " + selection.getAbsolutePath());
    loadTableSettings(selection.getAbsolutePath());

 
  }
}
// *****************************************
// This function returns all the files in a directory as an array of Strings  
//******************************************
public String[] listFileNames(String dir) {
  File file = new File(dir);
  if (file.isDirectory()) {
    String names[] = file.list();
    return names;
  } else {
    // If it's not a directory
    return null;
  }
}
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

public void settingScreen() {

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
   spaceBetw =  PApplet.parseInt(gridCols[1]*1.3f);
  rowBetw = PApplet.parseInt(gridRow[1]/2);
  Betw2 =  PApplet.parseInt(gridCols[1]/2);
  Betw3 =  PApplet.parseInt(gridCols[1]/1.5f);
  
}
// *****************************************
//           the background look
//******************************************

public void interfaceDisp() {
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
   textSize(Betw2/1.7f);
   
    
// String [] normal_labels = {"Modifier", "VALUE", "MINIMUM", "MAXIMUM", "MIDI CHANNEL", "DMX CHANNEL ", "MIDI TYPE", "MODE", "KEY", "KEY MODIFIERS", "-", "- "};
// String [] page_labels = {"Page switch", "VALUE", "-", "-", "MIDI CHANNEL", "- ", "MIDI TYPE", "-", "-", "-", "-", "- "};
// String [] spin_labels = {"SPINNER", "VALUE", "SPEED ", "INVERT", "MIDI CHANNEL", "endless type", "MIDI TYPE", "POT/ENDLESS", "TOUCH-STOP", "-", "-", "- "};
// String [] touch_labels = {"TOUCH SENSOR", "VALUE", "-", "-", "MIDI CHANNEL", "RESET VALUE ", "MIDI TYPE", "RESET MODE", "-", "-", "-", "-"};
// String [] mouse_labels = {"MOUSE EMULATOR", "ciao", "pippo", "baudo", "beppe", "grillo ", "gatto", "luigi", "di", "maio", "nese", "ciro "};
//  String [] distance_labels = {"DISTANCE SENSOR", "ciao", "pippo", "baudo", "beppe", "grillo ", "gatto", "luigi", "di", "maio", "nese", "ciro "}; 

         if ( elementData.get(idElement).dMode.getValue()  ==0)             // Scrivi etichette sotto i selettori a tendina.
 {text(page_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f  );
  text("", gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f  ); // comunque Scrivi MODE - page_labels [7]
  text("", gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f  );
  text("", gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f  );
   }
   
   else if ( elementData.get(idElement).dMode.getValue()  ==17)
   { text(page_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f );
  text(page_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f );
  text(page_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f );
  text(page_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f );
   }
      else 
         if ( elementData.get(idElement).dMode.getValue()  ==25)
   { text(mouse_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f );
  text(mouse_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f );
  text(mouse_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f );
  text(mouse_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f );
   }
      else
        if ( elementData.get(idElement).dMode.getValue()  ==18)
   { text(spin_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f );
  text(distance_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f );
  text(distance_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f );
  text(distance_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f );
   }
   else
      if ( elementData.get(idElement).dMode.getValue() < 11)
   {
  text(button_labels[7], gridCols[20]+spaceBetw/3 , gridRow[6]-rowBetw*0.5f  );
  text(button_labels[6], gridCols[20]+spaceBetw/3
  , gridRow[8]-rowBetw*0.5f
  );
  text(button_labels[8], gridCols[20]+spaceBetw/3
  , gridRow[10]-rowBetw*0.5f
  );
  text(button_labels[9], gridCols[20]+spaceBetw/3
  , gridRow[12]-rowBetw*0.5f
  );
   } 
      else
      if ( elementData.get(idElement).dMode.getValue() < 17)
   {
  text(pot_labels[7], gridCols[20]+spaceBetw/3 , gridRow[6]-rowBetw*0.5f  );
  text(pot_labels[6], gridCols[20]+spaceBetw/3
  , gridRow[8]-rowBetw*0.5f
  );
  text(pot_labels[8], gridCols[20]+spaceBetw/3
  , gridRow[10]-rowBetw*0.5f
  );
  text(pot_labels[9], gridCols[20]+spaceBetw/3
  , gridRow[12]-rowBetw*0.5f
  );
   } 
  
  
     else //joystick_labels
      if ( elementData.get(idElement).dMode.getValue()  ==20)
   { text(PADS_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f  );
  text(PADS_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f  );
  text(PADS_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f  );
  text(PADS_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f  );
   }
      else
   if ( elementData.get(idElement).dMode.getValue()  <23)
   { text(spin_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f  );
  text(spin_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f  );
  text(spin_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f  );
  text(spin_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f  );
   }
      else
   if ( elementData.get(idElement).dMode.getValue()  <25)
   { text(touch_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f );
  text(touch_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f );
  text(touch_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f );
  text(touch_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f );
   }
     else
   if ( elementData.get(idElement).dMode.getValue()  == 26)
   { text(GENERAL_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f );
  text(GENERAL_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f );
  text(GENERAL_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f );
  text(GENERAL_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f );
   }
      else


   if ( elementData.get(idElement).dMode.getValue()  ==18)
   { text(spin_labels[7], gridCols[20]+spaceBetw/3  , gridRow[6]-rowBetw*0.5f );
  text(distance_labels[6], gridCols[20]+spaceBetw/3  , gridRow[8]-rowBetw*0.5f );
  text(distance_labels[8], gridCols[20]+spaceBetw/3 , gridRow[10]-rowBetw*0.5f );
  text(distance_labels[9], gridCols[20]+spaceBetw/3 , gridRow[12]-rowBetw*0.5f );
   }
}
   
}

public void hintbox () {
  hints2 = hints.addTextarea("hints")
         .setPosition((int) gridCols[1],(int)gridRow[2]+rowBetw)
       .setSize((int) gridCols[15]+Betw2,(int) gridRow[5])
   
  
      .setFont(createFont("typeO.TTF", (Betw2/1.9f)))
   .setId(2011)
  .setColor(color(200))
                  .setColorBackground(110)
                  .setColorForeground(color(255,100))   
                  
          
                  .setText("."
                    );
}




public void midiMonitor() {
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
  textSize(Betw2/1.7f);
//  if (channel_filter ==0) 
  {
  //text(""+scala1+"_"+scala2+"_"+scal1+"-"+scal2, gridCols[17]+(Betw2*0.4), (int) gridRow[17]+rowBetw*2.4);
  
  text("Channel  : "+ Channel, gridCols[17]+(Betw2*0.4f), (int) gridRow[19]+rowBetw*2.4f);
  text("Type         : "+ optCC, gridCols[17]+(Betw2*0.4f), (int) gridRow[20]+rowBetw*2.4f);
  text("Data b.1    : "+Note, gridCols[17]+(Betw2*0.4f), (int) gridRow[21]+rowBetw*2.4f);
  text("Data b.2    : "+Intensity, gridCols[17]+(Betw2*0.4f), (int) gridRow[22]+rowBetw*2.4f);
   fill(255, 150, 0);
   text("MOD/KEYS  : "+keyCode_+"/"+Key_, gridCols[17]+(Betw2*0.4f), (int) gridRow[23]+rowBetw*2.4f);
     fill(150);
  //  text("FILTER", gridCols[19]+(Betw2), (int) gridRow[18]+rowBetw*2.4);
      text("MONITOR", gridCols[17]+(Betw2*0.4f)
  , (int) gridRow[18]+rowBetw*2.4f);
  
  fill(150);
  //text("test:"+ Intensity, gridCols[20]+Betw2, (int) gridRow[23]+rowBetw);
   text(monitor_flux6[0]+",  "+monitor_flux6[1]+",  "+monitor_flux6[2],  gridCols[21]+Betw2*1.4f, (int) gridRow[23]+rowBetw*2.4f);
   text(monitor_flux5[0]+",  "+monitor_flux5[1]+",  "+monitor_flux5[2],  gridCols[21]+Betw2*1.4f, (int) gridRow[22]+rowBetw*2.4f);
   text(monitor_flux4[0]+",  "+monitor_flux4[1]+",  "+monitor_flux4[2],  gridCols[21]+Betw2*1.4f, (int) gridRow[21]+rowBetw*2.4f);
   text(monitor_flux3[0]+",  "+monitor_flux3[1]+",  "+monitor_flux3[2],  gridCols[21]+Betw2*1.4f, (int) gridRow[20]+rowBetw*2.4f);
   text(monitor_flux2[0]+",  "+monitor_flux2[1]+",  "+monitor_flux2[2],  gridCols[21]+Betw2*1.4f, (int) gridRow[19]+rowBetw*2.4f);
   text(monitor_flux1[0]+",  "+monitor_flux1[1]+",  "+monitor_flux1[2],  gridCols[21]+Betw2*1.4f, (int) gridRow[18]+rowBetw*2.4f);
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
public void explanationText() {   if (change_do == true)
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

public void hide_all () {
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
// THE MIDI VARIABLES
MidiBus myBus; // The MidiBus
int Channel, Note, Intensity;
String optCC;
int DartIN, DartOUT;
int inD, outD;


  

// THE MIDI INIT SETTINGS //
public void initMidi() {

  try { 
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  } 
  catch (Exception e) { 
    e.printStackTrace();
  } 
  
 MidiBus.findMidiDevices();
//  MidiBus.list();
  // List all available Midi devices on STDOUT. This will show each device's index and name.
  String [] deviceIn= MidiBus.availableInputs();
  String [] deviceOut=MidiBus.availableOutputs();
  boolean checkIN=false;
  boolean checkOUT=false;



  //----------------------------------------------------------------------------------------  auto link to DART controller
  
  for (int i=0; i<deviceIn.length; i++) {
    if (deviceIn[i].equals( "DART") || deviceIn[i].indexOf("DART") != -1)
    { 
 
     Dart_device = "DART";
    }
     if (deviceIn[i].equals( "Arduino Leonardo") || deviceIn[i].indexOf("Arduino Leonardo") != -1)
    { 
 
     Dart_device = "Arduino Leonardo";
    }
    
  }
 
  
  //----------------------------------------------------------------------------------------
  
  
  if (checkIN && checkOUT) {
    myBus = new MidiBus(this, DartIN, DartOUT); // Create a new MidiBus object
  } else {

    String inputOpt = (String) JOptionPane.showInputDialog(
      null, //component parentComponent
      "DART - MIDI In", //object message
      "DART_EDITOR", //string title
     JOptionPane.QUESTION_MESSAGE, // int messagetype
   // null,
      null, //icon icon
      deviceIn, // object [] section values
    //  deviceIn[2]); // object initial values
      Dart_device);
      
    for (int i=0; i<deviceIn.length; i++) {
      if (deviceIn[i].equals(inputOpt))
      { 
        inD =i;
      //  println(DartIN);
        break;
      }
    }
  //  println(inputOpt);

    String outputOpt = (String) JOptionPane.showInputDialog(
      null, 
      "DART - m MIDI Out", 
      "DART_EDITOR", 
      JOptionPane.QUESTION_MESSAGE, 
      null, 
      deviceOut, 
       Dart_device);
    for (int i=0; i<deviceOut.length; i++) {
      if (deviceOut[i].equals(outputOpt))
      { 
        outD =i;
      //  println(DartOUT);
        break;
      }
    }
  //  println(outputOpt);
  //  myBus = new MidiBus(this, inD, outD); // Create a new MidiBus object
  //  delay(300);
  }
 
  // MidiBus.addMidiListener(MidiListener rawMidiMessage);
//addMidiListener(MidiListener listener) ;
}


public void addMidiListener(MidiListener midiMessage) {
// write_mon = true;
}






public void delay(int time) {
  int current = millis();
  while (millis () < current+time) Thread.yield();
}

///////////////////////-------------------------------------------------------------------
public void midiMessage(MidiMessage message//,long timestamp, String Busname
) { 
  // You can also use midiMessage(MidiMessage message, long timestamp, String bus_name)
  // Receive a MidiMessage
  // MidiMessage is an abstract class, the actual passed object will be either javax.sound.midi.MetaMessage, javax.sound.midi.ShortMessage, javax.sound.midi.SysexMessage.
  // Check it out here http://java.sun.com/j2se/1.5.0/docs/api/javax/sound/midi/package-summary.html

  //  write_mon = true;
  // write_mon = false;
  //buffer_status++; // ricevo un nuovo messaggio da visualizzare
  int midiSignal = constrain ((int)(message.getStatus()), 144, 240);
 
  int indexTypeMidi = constrain(((midiSignal-144)/16), 0, 5);
 optCC= typeMidiList[(indexTypeMidi)];
 // println("Type: "+ optCC);
 
 int value_show = (int)(message.getMessage()[1] & 0xFF);
 
 if (channel_filter == 0 ) {     // visualizza tutti i canali midi 1-16
   if (value_show == value_filter || value_filter == -1)  // visualizza solo la value scelta - oppure tutto, se value_filter è -1
{  
  if (Monitor_IN_button.isOn())
  { buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1; // ho sempre una copia del messaggio vecchio
  Channel = midiSignal- typeMidiArray[indexTypeMidi]+1;
  Note = value_show;
  Intensity = (int)(message.getMessage()[2] & 0xFF);
  raw1 = (int)(message.getStatus() & 0xFF); 
  write_mon = true;  
  }
  
  if (raw1 < 160) {  // se è una nota
    //saw.freq(frequenza2(Note)); 
  // saw.amp(int(boolean(Intensity)));
   wave.setFrequency( frequenza2(value_show) );
    wave.setAmplitude( PApplet.parseInt(PApplet.parseBoolean((int)(message.getMessage()[2] & 0xFF)))); // se note on si sente , se note off va a zero
    MIDI_IN_LED = true;
    feedback_counter = 0;

 
}
    else //  if (raw1 < 176)
  { 
    // saw.freq(frequenza2(Intensity)); 
     wave.setFrequency( frequenza2((int)(message.getMessage()[2] & 0xFF)) );
  // saw.amp(0.5); 
    wave.setAmplitude( 0.5f );
      MIDI_IN_LED = true;
feedback_counter = 0; }
   // monitor_flux6 [0] = raw1; monitor_flux6 [1] = Note ; monitor_flux6 [2] = Intensity;
}
} 

 
 
 
 else  // visualizza solo il canale Midi scelto.
 { int cosa = midiSignal- typeMidiArray[indexTypeMidi]+1; 
 if ( channel_filter == cosa) { 
   
   if (value_show == value_filter || value_filter == -1) {
   //  buffer_Channel = byte(Channel); 
  if (Monitor_IN_button.isOn())
  {   buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;
   write_mon = true;
     Channel = cosa;     
   
   Note = value_show;

  //if (indexTypeMidi == 3 || indexTypeMidi == 4) {} else 
  Intensity = (int)(message.getMessage()[2] & 0xFF);
    raw1 = (int)(message.getStatus() & 0xFF);
   }
    
      if (raw1 < 160) {
          wave.setFrequency( frequenza2(value_show) );
    wave.setAmplitude(PApplet.parseInt(PApplet.parseBoolean(Intensity)) );
    MIDI_IN_LED = true;
    feedback_counter = 0; 
        //saw.freq(frequenza2(Note)); 
      // saw.amp(int(boolean(Intensity)));
    } 
    else  //  if (raw1 < 176)
  { // saw.freq(frequenza2(Intensity)); saw.amp(0.5); 
          wave.setFrequency( frequenza2((int)(message.getMessage()[2] & 0xFF)) );
    wave.setAmplitude(0.5f );
    MIDI_IN_LED = true;
  
feedback_counter = 0; 

}
   // monitor_flux6 [0] = raw1; monitor_flux6 [1] = Note ; monitor_flux6 [2] = Intensity;
}
  
  
 }
}

for (byte i =0;i < 60; i++) 
{
 // if (page_selected == false) 
  {
if (elementData.get(i).note[PApplet.parseInt(page_selected)] == value_show  ) 
if ( elementData.get(i).midiChannel ==  midiSignal- typeMidiArray[indexTypeMidi]+1 && page_selected == false && elementData.get(i).toggleMode > 0 || 
elementData.get(i).midiChannel_2nd ==  midiSignal- typeMidiArray[indexTypeMidi]+1 && page_selected == true && elementData.get(i).toggleMode_2nd > 0
) 
{ 

elementData.get(i).feedback_ = true ; // segnale verse sull'item
//elementData.get(i).feedback_value = Intensity; // movimento del cursore sull'item
 if (Intensity >0)  cp5.get(Integer.toString(i+1)).setValue(Intensity) ;
 
feedback_counter = 0;
}  
  }
}


}
class SisButton implements ControllerView<Button> { 
  public void display(PGraphics theApplet, Button theButton) {
    theApplet.pushMatrix();
    theApplet.textSize(Betw2/1.7f);
    strokeWeight(3);
    stroke(150);
   // if (theButton.isOn() == false ) 
  //  if (theButton.isOn() == true ) fill(0,0,150); else 
    fill(70);
 
    rect(0, 0,   theButton.getWidth()  ,   theButton.getHeight(),7   );
    theApplet.strokeWeight(3);


    if (theButton.isInside()) {
       // mouse hovers the button
        theApplet.fill(ControlP5.ORANGE);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7); 
        
      if (theButton.isPressed() == true ) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
        move_element ();
          
      } // else {theApplet.fill(180, 150, 255); theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);}
    
    }
        

    

    
   
        
    // center the caption label 
    theApplet.textSize(Betw2/1.7f);
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}

class MIDI_IN_OUT implements ControllerView<Button> { 
  public void display(PGraphics theApplet, Button theButton) {
    theApplet.pushMatrix();
     theApplet.textSize(4);
    strokeWeight(2);
    noStroke();
   // stroke(50);
   // if (theButton.isOn() == false ) 
   if (theButton.isOn() == true ) fill(80,80,250); 
   else 
    fill(50);
 
   // rect(0, 0,   theButton.getWidth()  ,   theButton.getHeight(),7   );
  //  theApplet.strokeWeight(1);


    if (theButton.isInside()) {
       // mouse hovers the button
        theApplet.fill(ControlP5.ORANGE);
      //  theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7); 
    }
        

    {

    if (theButton.isPressed() == true ) { // button is pressed
        theApplet.fill(ControlP5.RED);
      //  theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
        move_element ();
          
      } // else {theApplet.fill(180, 150, 255); theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);}
    } 
   if (MIDI_OUT_LED == true && theButton.getId()  == 2018 ){ theApplet.fill(ControlP5.GREEN); }
   if (MIDI_IN_LED == true && theButton.getId()  == 2017 ){ theApplet.fill(ControlP5.GREEN); }
   
    rect(0, 0,   theButton.getWidth()  ,   theButton.getHeight(),4  );
      //  theButton.fill(0, 102, 153);
    // center the caption label 
  //   theApplet.textSize(Betw2/4);
    //  theButton.getCaptionLabel().textSize(Betw2/4);
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - PApplet.parseInt(theButton.getCaptionLabel().getHeight()/1.8f);
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
    strokeWeight(0);
    stroke(100);
  }
}

class SisButton_page implements ControllerView<Button> { 
  public void display(PGraphics theApplet, Button theButton) {
    theApplet.pushMatrix();
    theApplet.textSize(Betw2/1.7f);
//    strokeWeight(3);
    stroke(150);
   // if (theButton.isOn() == false ) 
   if (theButton.isOn() == true ) fill(80,80,250); 
   else 
    fill(70);
 
    rect(0, 0,   theButton.getWidth()  ,   theButton.getHeight(),7   );
    theApplet.strokeWeight(3);


    if (theButton.isInside()) {
       // mouse hovers the button
        theApplet.fill(ControlP5.ORANGE);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7); }
        

    {

    if (theButton.isPressed() == true ) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
        move_element ();
          
      } // else {theApplet.fill(180, 150, 255); theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);}
    } 
   
        
    // center the caption label 
    theApplet.textSize(Betw2/1.7f);
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}


/*
class SisButton_selectpage implements ControllerView<Button> { 
  public void display(PGraphics theApplet, Button theButton) {
    theApplet.pushMatrix();
    theApplet.textSize(Betw2/1.7);
//    strokeWeight(3);
    stroke(150);
   // if (theButton.isOn() == false ) 
    fill(70);
 
    rect(0, 0,   theButton.getWidth()  ,   theButton.getHeight(),7   );
    theApplet.strokeWeight(3);


    if (theButton.isInside()) {
       // mouse hovers the button
        theApplet.fill(ControlP5.ORANGE);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7); }
        

    {

    if (theButton.isPressed() == true ) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
        move_element ();
          
      } // else {theApplet.fill(180, 150, 255); theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);}
    } 
   
        
    // center the caption label 
    theApplet.textSize(Betw2/1.7);
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}
*/

class SisButton2 implements ControllerView<Knob> { 
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    theApplet.textSize(Betw2/1.7f);
//    strokeWeight(3);
    stroke(150);
   // if (theButton.isOn() == false ) 
    
   if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(70); //elementData.get(idElement).selected= true;
    }
     if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    
    rect(0, 0,   theButton.getWidth()  ,   theButton.getHeight(),7   );
    theApplet.strokeWeight(3);


    if (theButton.isInside()) {
       // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7); }
        

    {

    if (theButton.isMousePressed() == true ) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
        move_element ();
          
      } // else {theApplet.fill(180, 150, 255); theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);}
    } 
        
        noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 255, 255);
//   line(theButton.getWidth()*0.1, theButton.getHeight()*1, theButton.getWidth()*0.1, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
 
  line(0
  +theButton.getWidth()*0.2f//-(theButton.getWidth()/127)
  , theButton.getHeight(), 
  //theButton.getWidth()-
  (theButton.getValue()*theButton.getWidth()/210)+theButton.getWidth()*0.2f, 
  theButton.getHeight());
  
  
  theApplet.strokeWeight(3);
  theApplet.stroke(150, 150, 150);
  
    // center the caption label 
    theApplet.textSize(Betw2/1.7f);
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}

class SisButton_toggle implements ControllerView<Button> { 
  public void display(PGraphics theApplet, Button theButton) {
    theApplet.pushMatrix();
    theApplet.textSize(Betw2/1.7f);
//    strokeWeight(3);
    stroke(150);
  //  if (edit_mode == true )// theApplet.fill(ControlP5.GRAY);
    if (theButton.isOn() == false)
    fill(70); else theApplet.fill(ControlP5.RED);
 //   fill(70);
    rect(0, 0,   theButton.getWidth()  ,   theButton.getHeight(),7   );
    theApplet.strokeWeight(3);


    if (theButton.isInside()) {
       // mouse hovers the button
        theApplet.fill(ControlP5.ORANGE);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7); }
        

    {

    if (theButton.isPressed() == true ) { // button is pressed
        if (edit_mode == true ) { theApplet.fill(ControlP5.RED);
        theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);}
      //   move_element ();
          
      }  // else {theApplet.fill(180, 150, 255); theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);}
    } 
    
    
   
           //   if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    // center the caption label 
    theApplet.textSize(Betw2/1.7f);
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}

class RectButton implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    strokeWeight(3);
    stroke(175);
  if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    } //Width //Height
//    rect(-theButton.getWidth()*0.24, theButton.getHeight()*0.22, theButton.getWidth()*1.5, theButton.getHeight()*0.6,7);
     // rect(theButton.getWidth()*0.2, -theButton.getHeight()*0.2, theButton.getWidth()*0.6, theButton.getHeight()*1.5,7);
    theApplet.strokeWeight(3);
    //stroke(255, 0, 0);
    //strokeWeight(2);
    //line(0, theButton.getHeight()/2, theButton.getWidth()/5, theButton.getHeight()/2);
    //line(theButton.getWidth()*4/5, theButton.getHeight()/2, theButton.getWidth(), theButton.getHeight()/2);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
   //     theApplet.rect(-theButton.getWidth()*0.24, theButton.getHeight()*0.22, theButton.getWidth()*1.5, theButton.getHeight()*0.6,7);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
     //   theApplet.rect(-theButton.getWidth()*0.24, theButton.getHeight()*0.22, theButton.getWidth()*1.5, theButton.getHeight()*0.6,7);
      }
    } else { // the mouse is located outside the button area
     // theApplet.fill(180, 150, 255);
       // theApplet.fill(ControlP5.BLACK);
    }
         if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    
    // center the caption label 
    theApplet.rect(-theButton.getWidth()*0.24f, theButton.getHeight()*0.22f, theButton.getWidth()*1.5f, theButton.getHeight()*0.6f,7);
    
     noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 255, 255);
//   line(theButton.getWidth()*0.1, theButton.getHeight()*1, theButton.getWidth()*0.1, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
 
  line(0
  +theButton.getWidth()*0.2f//-(theButton.getWidth()/127)
  , theButton.getHeight()*0.22f, 
  (theButton.getValue()*theButton.getWidth()/210)+theButton.getWidth()*0.2f, 
  theButton.getHeight()*0.22f);
  
  theApplet.strokeWeight(3);
  theApplet.stroke(150, 150, 150);
    
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}
  //    rect(theButton.getHeight()*0.35, -theButton.getWidth()*0.8, theButton.getHeight()*0.3, theButton.getWidth()*2.6,7);
    //      rect( theButton.getWidth()*0.35, -theButton.getHeight()*0.8, theButton.getWidth()*0.3 , theButton.getHeight()*2.6 ,7);

class fader2_orizz implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    strokeWeight(3);
    stroke(175);
    theApplet.fill(ControlP5.BLACK);
     
    
    rect( -theButton.getWidth()*0.8f, theButton.getHeight()*0.35f, theButton.getWidth()*2.6f , theButton.getHeight()*0.3f ,7);
    if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
    }
       if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
   ;
    rect((theButton.getValue()*theButton.getWidth()/127) -theButton.getWidth()/2  , 0, theButton.getWidth(), theButton.getHeight(), 7);   
    theApplet.strokeWeight(3);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect((theButton.getValue()*theButton.getWidth()/127) -theButton.getWidth()/2 , 0, theButton.getWidth(), theButton.getHeight(), 7);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
        theApplet.rect((theButton.getValue()*theButton.getWidth()/127) -theButton.getWidth()/2  , 0, theButton.getWidth(), theButton.getHeight(), 7);
      }
    } else { // the mouse is located outside the button area
      theApplet.fill(180, 150, 255);
    }
          if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
          
    
          
    int x = PApplet.parseInt(theButton.getValue()*theButton.getWidth()/127   - theButton.getCaptionLabel().getWidth()/2);
    int y = theButton.getHeight()/2 - 2 -theButton.getCaptionLabel().getHeight()/2;
    theApplet.popMatrix();
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
  }
}

class fader3_vert implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    strokeWeight(3);
    stroke(175);
    theApplet.fill(ControlP5.BLACK);
  
    rect( theButton.getWidth()*0.35f, -theButton.getHeight()*0.8f, theButton.getWidth()*0.3f , theButton.getHeight()*2.6f ,7);
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
    }
       if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK - COLORE VERDE
   ;
    rect(0  , 
     theButton.getHeight()/2-(theButton.getValue()*theButton.getHeight()/127) 
    ,                                // muovo il cursore - secondo il feedback midi
    theButton.getWidth(), theButton.getHeight(), 7);  
    
    theApplet.strokeWeight(3);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(0  , theButton.getHeight()/2-(theButton.getValue()*theButton.getHeight()/127) , theButton.getWidth(), theButton.getHeight(), 7);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
        theApplet.rect(0  ,  theButton.getHeight()/2-(theButton.getValue()*theButton.getHeight()/127) , theButton.getWidth(), theButton.getHeight(), 7);
      }
    } else { // the mouse is located outside the button area
      theApplet.fill(180, 150, 255);
    }
       
          
    // int x = int(theButton.getValue()*theButton.getWidth()/127   - theButton.getCaptionLabel().getWidth()/2);
    // int y = theButton.getHeight()/2 - 2 -theButton.getCaptionLabel().getHeight()/2;
   
    int x =   theButton.getWidth()/2 - 1 - theButton.getCaptionLabel().getWidth()/2;
    int y = PApplet.parseInt(
    
    theButton.getHeight() 
    -theButton.getCaptionLabel().getHeight()/2 
    - theButton.getValue()*theButton.getHeight()/127   )
    ;
    
    theApplet.popMatrix();
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
  }
}





class fader implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    strokeWeight(3);
    stroke(175);
    //fill(32);
     if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    }
     if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    rect(theButton.getWidth()*0.1f, -theButton.getHeight()*0.2f, theButton.getWidth()*0.8f, theButton.getHeight()*1.5f,7);
    theApplet.strokeWeight(3);
    //stroke(255, 0, 0);
    //strokeWeight(2);
    //line(0, theButton.getHeight()/2, theButton.getWidth()/5, theButton.getHeight()/2);
    //line(theButton.getWidth()*4/5, theButton.getHeight()/2, theButton.getWidth(), theButton.getHeight()/2);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(theButton.getWidth()*0.1f, -theButton.getHeight()*0.2f, theButton.getWidth()*0.8f, theButton.getHeight()*1.5f,7);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
        theApplet.rect(theButton.getWidth()*0.1f, -theButton.getHeight()*0.2f, theButton.getWidth()*0.8f, theButton.getHeight()*1.5f,7);
      }
    } else { // the mouse is located outside the button area
      theApplet.fill(180, 150, 255);
    }
         
                    noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 255, 255);
  line(theButton.getWidth()*0.1f, theButton.getHeight()*1, theButton.getWidth()*0.1f, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
  theApplet.strokeWeight(3);
  theApplet.stroke(150, 150, 150);
          
    // center the caption label 
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}

class polygon_ implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    strokeWeight(3);
    stroke(175);
  //  fill(250);
     if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    }
  //   polygon(0, 0, 82, 5);
    pushMatrix();
  translate(theButton.getHeight()/2, theButton.getHeight()/2);
  //color(255,0,0);
  rotate(frameCount / 100.0f);
  polygon(0, 0, theButton.getWidth(), 5);  // Triangle
  popMatrix();
  
   // rect(-theButton.getHeight()*0.2, theButton.getWidth()*0.2, theButton.getHeight()*1.5, theButton.getWidth()*0.6,7);
     // rect(theButton.getWidth()*0.2, -theButton.getHeight()*0.2, theButton.getWidth()*0.6, theButton.getHeight()*1.5,7);
    theApplet.strokeWeight(3);
    //stroke(255, 0, 0);
    //strokeWeight(2);
    //line(0, theButton.getHeight()/2, theButton.getWidth()/5, theButton.getHeight()/2);
    //line(theButton.getWidth()*4/5, theButton.getHeight()/2, theButton.getWidth(), theButton.getHeight()/2);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        // theApplet.rect(-theButton.getHeight()*0.2, theButton.getWidth()*0.2, theButton.getHeight()*1.5, theButton.getWidth()*0.6,7);
        //theApplet.polygon(0, 0, theButton.getHeight(), 5);
              if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
       pushMatrix();
  translate(theButton.getHeight()/2, theButton.getHeight()/2);
  //color(255,0,0);
  rotate(frameCount / 100.0f);
  polygon(0, 0, theButton.getHeight(), 5);  // Triangle
  popMatrix();
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
       // theApplet.rect(-theButton.getHeight()*0.2, theButton.getWidth()*0.2, theButton.getHeight()*1.5, theButton.getWidth()*0.6,7);
       pushMatrix();
  translate(theButton.getHeight()/2, theButton.getHeight()/2);
  //color(255,0,0);
  rotate(frameCount / 100.0f);
  polygon(0, 0, theButton.getHeight(), 5);  // Triangle
  popMatrix();
  
  
  
      }
    } else { // the mouse is located outside the button area
      theApplet.fill(180, 150, 255);
      
      
      
    }
    // center the caption label 
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
    
      noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 128, 0);
  arc(0, 0, theButton.getWidth(), theButton.getWidth(), theButton.getStartAngle(), theButton.getAngle());
  theApplet.strokeWeight(3);
   theApplet.stroke(150, 150, 150);
    
    
  }
}

class Grigio implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
      }
    } else { // the mouse is located outside the button area
    //  if (theButton.isActive() == true) theApplet.fill(ControlP5.BLACK); else  theApplet.fill(ControlP5.WHITE);
      if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    }
   //   theApplet.fill(ControlP5.BLACK); 
          theApplet.strokeWeight(3);
     theApplet.stroke(255, 128, 0);
    
    }

          if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
       // translate(0, 0);
        theApplet.ellipse(0, 0, theButton.getWidth(), theButton.getWidth());     
    //theApplet.ellipse(0, 0, theButton.getWidth(), theButton.getHeight()); //radiusW
    // theApplet.ellipse(0, 0, theButton.getWidth(), theButton.getWidth()); 
  // theApplet.
  // ellipse(0, 0, elementData.get(theButton.getId()).radiusW, elementData.get(theButton.getId()).radiusW);
 // ellipse(0, 0, elementData.get(theButton.getId()).radiusW, elementData.get(theButton.getId()).radiusW);
    // center the caption label 
    int x = theButton.getWidth()/2  - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getWidth()/2  -theButton.getCaptionLabel().getHeight()/2;
 // int y = theButton.getWidth()/2 - 1 - theButton.getCaptionLabel().getWidth()/2;
    theApplet.pushMatrix();
    translate(x, y);
    // ellipse(0, 0, elementData.get(theButton.getId()).radiusW, elementData.get(theButton.getId()).radiusW);
    // ellipse(0, 0, theButton.getWidth(), theButton.getWidth());
    theButton.getCaptionLabel().draw(theApplet);
    
  
    theApplet.popMatrix();
    theApplet.popMatrix();
    // translate(0, 0);
  }
}


class Manopolox implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
      }
    } else { // the mouse is located outside the button area
      // if (theButton.isActive() == true) theApplet.fill(ControlP5.BLACK); else  // theApplet.fill(ControlP5.WHITE);
      theApplet.fill(ControlP5.BLACK);
      if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
      
      if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE);} //elementData.get(idElement).selected= false;
   // }
   //   else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
   // }
     // theApplet.fill(ControlP5.BLACK); 
          theApplet.strokeWeight(3);
     theApplet.stroke(150, 150, 150);
    
           }

           if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    theApplet.ellipse(0, 0, theButton.getWidth(), theButton.getWidth());
  noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 128, 0);
  arc(0, 0, theButton.getWidth(), theButton.getWidth(), theButton.getStartAngle(), theButton.getAngle());
  theApplet.strokeWeight(3);
   theApplet.stroke(150, 150, 150);
    // center the caption label 
    int x = theButton.getWidth()/2 - 1 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getWidth()/2 - 2 -theButton.getCaptionLabel().getHeight()/2;
    theApplet.pushMatrix();
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
   // theButton.getValueLabel().draw(theApplet);
  // theButton.draw(theApplet);
    theApplet.popMatrix();
    theApplet.popMatrix();
  }
}

class Manopolox2 implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
      }
    } else { // the mouse is located outside the button area
      // if (theButton.isActive() == true) theApplet.fill(ControlP5.BLACK); else  // theApplet.fill(ControlP5.WHITE);
      theApplet.fill(ControlP5.BLACK);
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
       
      if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE);} //elementData.get(idElement).selected= false;
   // }
   //   else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
   // }
     // theApplet.fill(ControlP5.BLACK); 
          theApplet.strokeWeight(3);
     theApplet.stroke(150, 150, 150);
    
    }

      //     if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    theApplet.ellipse(0, 0, theButton.getWidth(),theButton.getWidth());
  noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 255, 255);
  arc(0, 0, theButton.getWidth(), theButton.getWidth(), theButton.getStartAngle(), theButton.getAngle());
  theApplet.strokeWeight(3);
   theApplet.stroke(150, 150, 150);
    // center the caption label 
    int x = theButton.getWidth()/2 - 1 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getWidth()/2 - 2 -theButton.getCaptionLabel().getHeight()/2;
    theApplet.pushMatrix();
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
   // theButton.getValueLabel().draw(theApplet);
  // theButton.draw(theApplet);
    theApplet.popMatrix();
    theApplet.popMatrix();
  }
}

class faderox implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    strokeWeight(3);
    stroke(175);
    //fill(32);
     if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    }
     if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    rect(theButton.getWidth()*0.1f, -theButton.getHeight()*0.2f, theButton.getWidth()*0.8f, theButton.getHeight()*1.5f,7);
    theApplet.strokeWeight(3);
    //stroke(255, 0, 0);
    //strokeWeight(2);
    //line(0, theButton.getHeight()/2, theButton.getWidth()/5, theButton.getHeight()/2);
    //line(theButton.getWidth()*4/5, theButton.getHeight()/2, theButton.getWidth(), theButton.getHeight()/2);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(theButton.getWidth()*0.1f, -theButton.getHeight()*0.2f, theButton.getWidth()*0.8f, theButton.getHeight()*1.5f,7);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
        theApplet.rect(theButton.getWidth()*0.1f, -theButton.getHeight()*0.2f, theButton.getWidth()*0.8f, theButton.getHeight()*1.5f,7);
      }
    } else { // the mouse is located outside the button area
      theApplet.fill(180, 150, 255);
    }
         
          
          noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 128, 0);
  line(theButton.getWidth()*0.1f, theButton.getHeight()*1, theButton.getWidth()*0.1f, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
  theApplet.strokeWeight(3);
  theApplet.stroke(150, 150, 150);
   
    // center the caption label 
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}


class SensorButton implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
    theApplet.pushMatrix();
    strokeWeight(3);
     stroke(180, 180, 180);
    noFill();
    if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.noFill(); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    }
    
     if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
   // rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
    theApplet.strokeWeight(3);
    stroke(180, 180, 180);
    strokeWeight(3);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
         move_element ();
        theApplet.fill(ControlP5.RED);
     //   theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
     //   theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),7);
      }
    } else { // the mouse is located outside the button area
    //  theApplet.fill(180, 150, 255);
    }
    
    theApplet.rect(0, 0, theButton.getWidth(), theButton.getHeight(),4);
  noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 255, 255);
//   line(theButton.getWidth()*0.1, theButton.getHeight()*1, theButton.getWidth()*0.1, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
 
  if (elementData.get(theButton.getId()).toggleMode < 17 && page_selected == false || 
  elementData.get(theButton.getId()).toggleMode_2nd < 17 && page_selected == true )
  line(0
  +theButton.getWidth()*0.2f//-(theButton.getWidth()/127)
  , theButton.getHeight(), 
  //theButton.getWidth()-
  (theButton.getValue()*theButton.getWidth()/210)+theButton.getWidth()*0.2f, 
  theButton.getHeight());
  
  
  theApplet.strokeWeight(3);
  theApplet.stroke(150, 150, 150);
  
    // center the caption label 
    int x = theButton.getWidth()/2 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getHeight()/2 - theButton.getCaptionLabel().getHeight()/2;
    translate(x, y);
    //theApplet.textSize(80);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
  }
}




class Marrone implements ControllerView<Button> {
  public void display(PGraphics theApplet, Button theButton) {
    theApplet.pushMatrix();
       
    if (theButton.isInside()) {
      if (theButton.isPressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
  
  move_element ();
       
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
      }
    } else { // the mouse is located outside the button area
       if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    }
    }
    
    if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); /// FEEDBACK
    theApplet.strokeWeight(3);
     //theApplet.fill(0);
      theApplet.stroke(255, 255, 255);
       //  theApplet.fill(255,128,0);
       
   // theApplet.ellipse(0, 0, theButton.getWidth(), theButton.getHeight());
    theApplet.ellipse(0, 0, theButton.getWidth(), theButton.getWidth());
    
    int x = theButton.getWidth()/2 - 1 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getWidth()/2 - 2 -theButton.getCaptionLabel().getHeight()/2;
    theApplet.pushMatrix();
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
    theApplet.popMatrix();
  }
}


class Marrone_polygon implements ControllerView<Knob> {
  public void display(PGraphics theApplet, Knob theButton) {
       //  theApplet.
          fill(100);
         theApplet.stroke(180);
    pushMatrix();
    noFill();
  translate(theButton.getWidth()/2, theButton.getWidth()/2);
  // color(255,0,0);
  // fill(32);
  
  rotate(frameCount / 100.0f);
  polygon(0, 0, theButton.getWidth()*1.5f, 5);  // Triangle
  popMatrix();
    
    theApplet.pushMatrix();
       
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
      //  theApplet.
        fill(ControlP5.RED);
  
  move_element ();
       
      } else { // mouse hovers the button
      //  theApplet.
        fill(ControlP5.YELLOW);
      }
    } else { // the mouse is located outside the button area
     // theApplet.
     // fill(ControlP5.BLACK);
     
      if (elementData.get(theButton.getId()).selected == true) { theApplet.fill(ControlP5.BLUE); //elementData.get(idElement).selected= false;
    }
      else { theApplet.fill(ControlP5.BLACK); //elementData.get(idElement).selected= true;
       if (elementData.get(theButton.getId()).getStateButton() == true) { theApplet.fill(170, 170, 170);  } // GREY
    }
    }
    theApplet.strokeWeight(3);
     //theApplet.fill(0);
      theApplet.stroke(150, 150, 150);
       //  theApplet.fill(255,128,0);
       if (elementData.get(theButton.getId()).feedback_ == true)     theApplet.fill(0, 255, 0); //// FEEDBACK
    /*   pushMatrix();
  translate(theButton.getHeight()/2, theButton.getHeight()/2);
  //color(255,0,0);
  rotate(frameCount / 100.0);
  polygon(0, 0, theButton.getHeight(), 5);  // Triangle
  popMatrix();
  */
    theApplet.ellipse(0, 0, theButton.getWidth(), theButton.getWidth());
    
      noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 128, 0);
  //line(theButton.getWidth()*0.1, theButton.getHeight()*1, theButton.getWidth()*0.1, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
 arc(0, 0, theButton.getWidth(), theButton.getWidth(), theButton.getStartAngle(), theButton.getAngle());

  theApplet.strokeWeight(3);
   theApplet.stroke(150, 150, 150);
    
    
    int x = theButton.getWidth()/2 - 1 - theButton.getCaptionLabel().getWidth()/2;
    int y = theButton.getWidth()/2 - 2 -theButton.getCaptionLabel().getHeight()/2;
    theApplet.pushMatrix();
    translate(x, y);
    theButton.getCaptionLabel().draw(theApplet);
    theApplet.popMatrix();
    theApplet.popMatrix();
  }
}



public class NumberboxInput {
  String text = "";
  Numberbox n;
  boolean active;
  NumberboxInput(Numberbox theNumberbox) {
    n = theNumberbox;
    registerMethod("keyEvent", this );
  }
  public void keyEvent(KeyEvent k) {
    // only process key event if input is active 
    if (k.getAction()==KeyEvent.PRESS && active) {
      if (k.getKey()=='\n') { // confirm input with enter
        submit();
        return;
      } else if (k.getKeyCode()==BACKSPACE) { 
        text = text.isEmpty() ? "":text.substring(0, text.length()-1);
        //text = ""; // clear all text with backspace
      } else if (k.getKey()<255) {
        // check if the input is a valid (decimal) number
        // final String regex = "\\d+([.]\\d{0,2})?";
        final String regex = "\\d+";
        String s = text + k.getKey();
        if ( java.util.regex.Pattern.matches(regex, s ) ) {
          text += k.getKey();
        }
      }
      n.getValueLabel().setText(this.text);
    }
  }
  public void setActive(boolean b) {
    active = b;
    if (active) {
      n.getValueLabel().setText("");
      text = "";
    }
  }
  public void submit() {
    if (!text.isEmpty()) {
      n.setValue( PApplet.parseInt( text ) );
      text = "";
    } else {
      n.getValueLabel().setText(""+n.getValue());
    }
  }
}

public void move_element () 
{
     if (keyPressed == true) {
    if (keyCode == SHIFT && edit_mode == false) {
      Knob t = (Knob)cp5.get(Integer.toString(idElement+1));
  int mouse_X = (mouseX/15)*15;
  int mouse_Y = (mouseY/15)*15;
 // mouse_X = mouse_X*50;
   t.setPosition(constrain(mouse_X,0,(width/3)*2), constrain(mouse_Y,height/3, height));
   elementData.get(idElement).posX = constrain(mouse_X,0,(width/3)*2);
    elementData.get(idElement).posY = constrain(mouse_Y,height/3, height); 
  // keyCode = 0 ;
    }
     }
}

public void polygon(float x, float y, float radius, int npoints) {
  float angle = TWO_PI / npoints;
  beginShape();
  for (float a = 0; a < TWO_PI; a += angle) {
    float sx = x + cos(a) * radius;
    float sy = y + sin(a) * radius;
    vertex(sx, sy);
  }
  endShape(CLOSE);
}
public void firstPosition() {
  //myKnob(PApplet applet, String label_, int posx_, int posy_, int rW, int rH, int r_)
 //  myUI (PApplet applet, ControlP5 cp5_, String label_, int mem_pos, int id_, float posx_, float posy_, int rW, int rH, int setView_) {
  for (int i=0; i<4; i++) {
    elementData.add( new myUI(this, cp5, Integer.toString( i+1), i+1, i, Betw3+i*(gridCols[1]), gridRow[9], Betw3, Betw3, 2));
  //  elementData.get(i).posX = int(Betw3+i*(gridCols[1]));
   //  elementData.get(i).posY = int(gridRow[9]);
       //  elementData.get(i).mp.setValue(memPosLUT[i]);
  }
  for (int i=4; i<8; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, Betw3+(i-4)*(gridCols[1]), gridRow[9]+gridRow[1]*2, Betw3, Betw3, 2));
  }
  for (int i=8; i<12; i++) {
    elementData.add( new myUI(this, cp5, Integer.toString( i+1), i+1, i, Betw3+(i-4)*(gridCols[1]), gridRow[9], Betw3, Betw3, 1));
  }
  for (int i=12; i<16; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, Betw3+(i-8)*(gridCols[1]), gridRow[9]+gridRow[1]*2, Betw3, Betw3, 1));
  }
  for (int i=16; i<20; i++) {
    elementData.add( new myUI(this, cp5, Integer.toString( i+1), i+1, i, Betw3+(i-8)*(gridCols[1]), gridRow[9], Betw3, Betw3, 2));
  }
  for (int i=20; i<24; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, Betw3+(i-12)* (gridCols[1]), gridRow[9]+gridRow[1]*2, Betw3, Betw3, 2));
  }
  for (int i=24; i<28; i++) {
    elementData.add( new myUI(this, cp5, Integer.toString( i+1), i+1, i, Betw3+(i-12)* (gridCols[1]), gridRow[9], Betw3, Betw3, 1));
  }
  for (int i=28; i<32; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, Betw3+(i-16)* (gridCols[1]), gridRow[9]+gridRow[1]*2, Betw3, Betw3, 1));
  }
  for (int i=32; i<36; i++) {
    elementData.add( new myUI(this, cp5, Integer.toString( i+1), i+1, i, gridCols[2]+Betw3+(i-34)* (gridCols[1]),gridRow[15], Betw3, Betw3, 2));
  }
  for (int i=36; i<40; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, gridCols[2]+Betw3+(i-38)* (gridCols[1]), gridRow[17], Betw3, Betw3, 2));
  }
  for (int i=40; i<44; i++) {
    elementData.add( new myUI(this, cp5, Integer.toString( i+1), i+1, i, gridCols[2]+Betw3+(i-38)* (gridCols[1]), gridRow[15], Betw3, Betw3, 1));
  }
  for (int i=44; i<48; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, gridCols[2]+Betw3+(i-42)* (gridCols[1]), gridRow[17], Betw3, Betw3, 1));
  }

  //elementData.add(new myUI(this, cp5, "Switch", memPosLUT[48], 48, gridCols[3]+5*spaceBetw+rW/2, gridRow[9]+spaceBetw+2*rH, rW*11/12+rW/2, rH/2, 0));
  //elementData.add(new myUI(this, cp5, "Mouse", memPosLUT[49], 49, gridCols[3]+2*spaceBetw+8, gridRow[9]+spaceBetw+2*rH, rW*11/12+rW/2, rH/2, 0));
  for (int i=48; i<52; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, gridCols[2]+Betw3+(i-42)* (gridCols[1]), gridRow[15], Betw3, Betw3, 2));
  }
    for (int i=52; i<56; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, gridCols[2]+Betw3+(i-46)* (gridCols[1]), gridRow[17], Betw3, Betw3, 2));
  }
  for (int i=56; i<60; i++) {
    elementData.add(new myUI(this, cp5, Integer.toString (i+1), i+1, i, gridCols[2]+Betw3+(i-46)* (gridCols[1]), gridRow[15], Betw3, Betw3, 1));
  }
    elementData.get(59).label= "G"; //
    elementData.get(59).toggleMode= 26; 
     elementData.get(59).toggleMode_2nd= 26; 
    // if(elementData.get(
    
 if (elementData.get(59).shape == 2) 
     {Button t = (Button)cp5.get("60");    
     t.setLabel("G");}
      elementData.get(59).plugga_page();
  
  
 // for (int i=60; i<64; i++) {
 //   elementData.add(new myUI(this, cp5, Integer.toString (memPosLUT[i]), memPosLUT[i], i, gridCols[2]+Betw3+(i-50)* (gridCols[1]), gridRow[17], Betw3, Betw3, 1));
 // }

}
// THE BUTTON POSITION AND POLYMORFISM in ElementPosition class//
public void setupElement () {
  firstPosition();
}
// INTERFACE CONTROLL BUTTON
ControlP5 control,control2,hints;
Button exit, save, load, sendFirstPage, sendSecondPage, sendOnPageOne, sendOnPageTwo, edit, sound, New, Monitor_IN_button, Monitor_OUT_button ;
Knob del_;
Chart myChart;
Textarea hints2;
//Button scale1;
Matrix scale1;
 Numberbox color_, filter_channel, filter_value;

//**************************** 
//*****    the control p5 init
//**************************** 

public void init() {
  control = new ControlP5(this);
   control2 = new ControlP5(this);
    hints = new ControlP5(this);
    hints.setAutoDraw(false);
    
  cp5 = new ControlP5(this);
    cp5.setAutoDraw(false);
    
  ellipseMode(CENTER);
  f = createFont("typeO.TTF", Betw2*0.4f); 
  textFont(f, Betw2*0.4f);
cp5.setFont(f, (int)(Betw2*0.6f));
  control.setFont(f, PApplet.parseInt(Betw2*0.7f));
  control2.setFont(f, PApplet.parseInt(Betw2*0.5f));
 
 //  control.setColorBackground(color(90, 90, 90) );
}
// FUNCTION TO ACTIVATE PANEL BUTTON
public void mousePressed() {
  if (exit.isPressed() && i_sender >= 59)    {delay(500); exit(); //\myBus.close(); 
 //  myBus.removeInput(DartIN) ;  myBus.removeOutput(DartOUT) ; 
 //  myBus.close();
  // MidiBus.close();
//  myBus.clearAll();
 // myBus.dispose();
//  initMidi();
 // myBus.findMidiDevices();
 // MidiBus.findMidiDevices();
 // myBus.addInput("Arduino Leonardo") ;  myBus.addOutput("Arduino Leonardo") ;
  //initMidi();// myBus.addInput(DartIN) ; // myBus = new  MidiBus(this, DartIN, DartOUT);
 // myBus.addInput(DartIN) ; 
 //   myBus.addOutput(DartOUT) ; 
}//  MidiBus(this, DartIN, DartOUT); //initMidi(); // 


  
  if (edit.isPressed() && i_sender >= 59) {if (edit.isOn() == true ) {edit_activate();  // initMidi(); // saw.play(); 
} else {edit_deactivate();// saw.stop(); 
}   }



 if (sound.isPressed()) {if (sound.isOn() == true ) {soundstatus = true; //edit_activate(); // saw.play(); 
} else { soundstatus = false; //edit_deactivate();// saw.stop(); 
}   }



if (save.isPressed() && i_sender >= 59) { delay(500); save.setOff(); selectOutput("Save Editor Settings:", "fileToSave"); save.setOff(); }
if (load.isPressed()) {post_load_setup= 1; delay(500);   load.setOff(); selectInput("Load Editor Settings:", "fileToLoad"); load.setOff();}


  ////// SELETTORE PAGE
  if (sendFirstPage.isPressed() && i_sender >= 59) {      // selettore page - anche se si chiama send
      page_selected = false;
      sendSecondPage.setOff();      
      for (int i=0; i<60; i++) {
      elementData.get(i).plug_page();
      elementData.get(i).labeler();}
      set_scale_view();     
   }
   
   
   
    if (sendSecondPage.isPressed() && i_sender >= 59)  {
       page_selected = true;
       sendFirstPage.setOff();   
       for (int i=0; i<60; i++) {
       elementData.get(i).plug_page();
       elementData.get(i).labeler();}
       set_scale_view();
    }
    
    
    
    
    
    
  //*************
  //send selected to first page   // invia tutto
  //**************
 if (sendOnPageOne.isPressed() && i_sender >= 59) {   // send ALL
   i_sender = 0;
   general_send =1;
   }
  
  
  

  if (sendOnPageTwo.isPressed() && i_sender >= 59 ) {                 // send THIS

    myBus.sendMessage(241, 0, 0);
    delay(260);
    for (int i=0; i<60; i++) {
      
         if (elementData.get(i).getStateDisplay())  // è L'ELEMENTO SELEZIONATO?
     { 
     sender_single(i);
      if (elementData.get(i).toggleMode == 26) {general_send =1;}
     
     }
      
      
      if (elementData.get(i).toggleMode == 26 && general_send == 0) { // devo inviare anche l'ITEM general, se non è mai stato inviato
      general_send =1;
      sender_single(i);
      } 
      
      
   
      
    } 
    myBus.sendMessage(241, 0, 0);
    
  }
  
  
  
}
// TO SET THE BUTTON POSITION
public void setUIButtonsPosition () {
  
 // rect(gridCols[2],  gridRow[2], gridCols[15], gridRow[14]  ,7);
  // hintbox();
   
  
   
  exit = control.addButton("EXIT")
    .setId(1000)
    .setValue(0)
    .setPosition(gridCols[1], gridRow[1])
    .setSize(PApplet.parseInt(Betw2*4), PApplet.parseInt(rowBetw*1.8f))
    .setView(new SisButton())
    ;
  ;
  sendFirstPage = control.addButton("PAGE 1")
    .setId(1001)
    .setValue(0)
    .setPosition(gridCols[3]+Betw2*0.5f, gridRow[1])
    .setSize(PApplet.parseInt(Betw2*4), PApplet.parseInt(rowBetw*1.8f))
   // .setSize(int(Betw2*6),int(rowBetw*1.8))
    .setView(new SisButton_page())
   // .isSwitch()
  
   .setSwitch(true) 
    .setOn()
    ;
  ;
  
  // sendFirstPage.isSwitch();
   
  sendSecondPage = control.addButton("PAGE 2")
    .setId(1002)
    .setValue(0)
    .setPosition(gridCols[5]+Betw2, gridRow[1])
    .setSize(PApplet.parseInt(Betw2*4), PApplet.parseInt(rowBetw*1.8f))
   // .setSize(int(Betw2*6), int(rowBetw*1.8))
    .setView(new SisButton_page())
  .setSwitch(true) 
    ;
  ;
 // sendSecondPage.isSwitch();
  Monitor_IN_button = control2.addButton("IN")
    .setId(2017)
    .setValue(0)
    .setPosition(gridCols[19]+Betw2*0.3f, gridRow[18]+rowBetw*1.5f)
    .setSize(PApplet.parseInt(Betw2*1.7f), PApplet.parseInt(rowBetw*1.4f))
   // .setSize(int(Betw2*6), int(rowBetw*1.8))
    .setView(new MIDI_IN_OUT())
  .setSwitch(true) 
   .setOn()
    ;
  ;
  
   Monitor_OUT_button = control2.addButton("OUT")
    .setId(2018)
    .setValue(0)
   
    .setPosition(gridCols[20]+Betw2*0.3f, gridRow[18]+rowBetw*1.5f)
    .setSize(PApplet.parseInt(Betw2*1.7f), PApplet.parseInt(rowBetw*1.4f))
   // .setSize(int(Betw2*6), int(rowBetw*1.8))
    .setView(new MIDI_IN_OUT())
  .setSwitch(true) 
   .setOn()
    ;
  ;
    
  


  save = control.addButton("SAVE")
    .setId(1004)
    .setValue(0)
    .setPosition(gridCols[10], gridRow[1])
    .setSize(PApplet.parseInt(Betw2*4), PApplet.parseInt(rowBetw*1.8f))
    .setView(new SisButton())
     .setSwitch(false) 
    ;
  ;
  edit = control.addButton("DRAW")
    .setId(1006)
    .setValue(0)
    .setPosition(gridCols[12]+Betw2/2
    , gridRow[1])
    .setSize(PApplet.parseInt(Betw2*4), PApplet.parseInt(rowBetw*1.8f))
    .setView(new SisButton_toggle())
    .setSwitch(true) 
    ;
  ;
  
  
 // "logo.gif"
    sound = control.addButton("SOUND")
    // .setImage(logo) 
    .setId(1008)
    
    .setValue(0)
    .setPosition(gridCols[14]+Betw2
    , gridRow[1])
    .setSize(PApplet.parseInt(Betw2*4), PApplet.parseInt(rowBetw*1.8f))
    .setView(new SisButton_toggle())
    .setSwitch(true) 
    .setOn()
    ;
  ;
  
/////////////////////////////////////////////////////////////////////////////////////////////
  scale1= control2.addMatrix("")
     .setPosition(gridCols[20]+Betw2, gridRow[16]+rowBetw)
     .setSize((int) gridCols[3]-(Betw2/4),(int) gridCols[3]/12)
     .setGrid(12, 1)
     .setGap(2, 2)
     //.setInterval(200)
     .setMode(ControlP5.MULTIPLES)
     .setColorBackground(color(120))
     .setBackground(color(40))
     .setId(1005)
     //.setMoveable(true) 
    //
  // .onClick(componi)
   //.onPress(componi)
   //onEnter
   .addCallback(componi)
     .stop()
     ;
 //if ( scale1.get(2,0)== true) scala1 = "si"; else scala1= "no";
  // scale1.set(4, 0,  true) ;
  
  load = control.addButton("LOAD")
    .setId(1005)
    .setValue(0)
    .setPosition(gridCols[8]-Betw2*0.5f, gridRow[1])
    .setSize(PApplet.parseInt(Betw2*4), PApplet.parseInt(rowBetw*1.8f))
    .setView(new SisButton())
      .setSwitch(false) 
    ;
   /*   New = control.addButton("NEW")
    //.setId(1005)
    .setValue(0)
    .setPosition(gridCols[19]-Betw2*0.5, gridRow[15])
    .setSize(int(Betw2*5), int(rowBetw*1.8))
    .setView(new SisButton())
    ;
  ; */
    sendOnPageOne = control.addButton("Send ALL")
    .setId(1003)
    .setValue(0)
    .setPosition(gridCols[17], gridRow[1])
    .setSize(PApplet.parseInt(Betw2*6.5f), PApplet.parseInt(rowBetw*1.8f))
    .setView(new SisButton())
    .setSwitch(false) 
    ;
  ;
  sendOnPageTwo = control.addButton("Send THIS")
    .setId(1007)
    .setValue(0)
    .setPosition(gridCols[21]-Betw2 *0.5f
    , gridRow[1])
    .setSize(PApplet.parseInt(Betw2*6.5f), PApplet.parseInt(rowBetw*1.8f))
    .setView(new SisButton())
      .setSwitch(false) 
    ;
  ;
  
    //strokeWeight(0);
   //  noStroke();
    
 filter_channel = control2.addNumberbox("Filter Channel", (int) (gridCols[19]+Betw2), (int) (gridRow[19]+rowBetw*1.5f
 ), (int)(Betw2*3), (int)(rowBetw*1.5f)) //   
      .plugTo( this, "channel_filter" )
      .setId(2015)
      .setRange(0, 16)
  
       .setColorCaptionLabel(color(160))
   
     .setColorBackground(color(80)) 
   
      .setLabel("Channel f.")
      

     .setVisible(true);
    ;
    
    makeEditable (filter_channel);
    
    
    
     filter_value = control2.addNumberbox("Filter Value", (int) (gridCols[19]+Betw2), (int) (gridRow[21]+rowBetw*1.5f
     ), (int)(Betw2*3), (int)(rowBetw*1.5f)) //   
      .plugTo( this, "value_filter" )
      .setId(2016)
      .setRange(-1, 127)
       .setColorBackground(color(80)) 
       .setColorCaptionLabel(color(160))
       .setValue(-1)
      .setLabel("Value f.")
     // .setValueLabel("zero")
      // .setValue(memoryPosition) 
     .setVisible(true);
    ;
    // makeEditable( filter_channel );
       makeEditable( filter_value );
       
      
       
         myChart = cp5.addChart("")
               .setId(2019)
               
               .setPosition(gridCols[17],  gridRow[18]+rowBetw)
             //   rect(gridCols[17],  gridRow[18]+rowBetw, gridCols[7], gridRow[6],7); //red out
               .setSize( PApplet.parseInt(gridCols[7]), PApplet.parseInt(gridRow[6]))
               .setRange(-10, 137)
               .setView(Chart.AREA) // use Chart.LINE, Chart.PIE, Chart.AREA, Chart.BAR_CENTERED
              // .setStrokeWeight(7)
             // .setColor(ControlP5.BLUE) 
               .setColorBackground(color(0,0,0)) 
          //    .setColorForeground(color(90,90,90)) 
          //  .setColorCaptionLabel(color(90,90,90)) 
          //     .setColorValue(color(90,90,90)) 
          //     .setColorActive(color(90,90,90)) 
          //    .setColorValueLabel(color(90,90,90))  
          //     .setColorLabel(color(90,90,90)) 
              // .setColorCaptionLabel(57)
               ;
 
  myChart.addDataSet("incoming");
  myChart.setData("incoming", new float[100]);
  myChart.setColors("incoming", color(30,30,30),color(50,50,50));

}


/*
CallbackListener toFront_mon = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    theEvent.getController().bringToFront();
    ((ScrollableList)theEvent.getController()).open();
  }
};

CallbackListener close_mon = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    ((ScrollableList)theEvent.getController()).close();
  }
};
*/


public void edit_activate() {
edit_mode = true;
//saw.play();
    // Button t = (Button)cp5.get("3");
   // t.setView(new Marrone());
    // t.hide();
 //  t.setPosition(500, 500);
   
   for (int i=0; i<60; i++) {
   //  if (i != idElement && idElement< elementData.size()) 
    {
      elementData.get(i).labell.setVisible(false);
       elementData.get(i).label_hint.setVisible(false);
      elementData.get(i).mp.setVisible(false);
        
  //      elementData.get(i).labell.hide();
   //    elementData.get(i).label_hint.hide();
    //    elementData.get(i).mp.hide();
     
       // elementData.get(i).led.setVisible(false);
   //     if (elementData.get(i).toggleMode == 26) {      // general mode 
    //      Button t = (Button)cp5.get(Integer.toString(i+1));
     //      t.hide();}
     
    }
  }
}

public void edit_deactivate() {
edit_mode = false;
//saw.stop();
   //  Button t = (Button)cp5.get("3");
   // t.setView(new Marrone());
    // t.hide();
 //  t.setPosition(600, 600);
   for (int i=0; i<elementData.size(); i++) {
   //  if (i != idElement && idElement< elementData.size()) 
    {
        elementData.get(i).labell.setVisible(true);
        elementData.get(i).label_hint.setVisible(true);
        elementData.get(i).mp.setVisible(true);
        
   //    elementData.get(i).labell.hide();
    //   elementData.get(i).label_hint.hide();
    //   elementData.get(i).mp.hide();
        
       //  elementData.get(i).led.setVisible(true);
      //   if (elementData.get(i).toggleMode == 26) {      
       //   Button t = (Button)cp5.get(Integer.toString(i+1));
       //   t.show();}
     
    }
  }
}


CallbackListener componi = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
 //  elementData.get(idElement).
 scala1 = ""; 
 scala2 = "";
 //if ( scale1.get(0,0) == true) scala1 = "1"; else scala1 = "0";
 for (byte ii = 0; ii < 7; ii++) 
 {
 if ( scale1.get(ii,0) == true) // {scala1 = scala1.substring(1); } else scala1 = scala1.substring(0);// 
 scala1 ="1"+scala1; else scala1= "0"+scala1;
 
 }
 //scala1= "0"+scala1;
  for (byte ii = 7; ii < 12; ii++) 
 {
 if ( scale1.get(ii,0) == true) // {scala1 = scala1.substring(1); } else scala1 = scala1.substring(0);// 
 scala2 ="1"+scala2; else scala2= "0"+scala2;
 
 }
 ///scala2= "000"+scala2;
 scal1 = (byte)unbinary(scala1);
 scal2 = (byte)unbinary(scala2);
if(page_selected == false) 
{ elementData.get(idElement).note[0] = scal1;
 elementData.get(idElement).setExcursionControllMax = scal2;}
 else
 { elementData.get(idElement).note[1] = scal1;
 elementData.get(idElement).setExcursionControllMax_2nd = scal2;}
 //elementData.get(idElement).setExcursionControllMax = scal2;
 elementData.get(idElement).nT.setValue(scal1); 
 elementData.get(idElement).max.setValue(scal2); 
 
  }
  
  //bitset(0);
 // scal1 = String.toInt( unbinary(scala1

};
/*

CallbackListener close_mon = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    ((ScrollableList)theEvent.getController()).close();
  }
};*/

  public void makeEditable( Numberbox n ) {
    // allows the user to click a numberbox and type in a number which is confirmed with RETURN
    final NumberboxInput nin = new NumberboxInput( n ); // custom input handler for the numberbox
    // control the active-status of the input handler when releasing the mouse button inside 
    // the numberbox. deactivate input handler when mouse leaves.
    n.onClick(new CallbackListener() {
      public void controlEvent(CallbackEvent theEvent) {
        nin.setActive( true );
      }
    }
    )
 
    .onLeave(new CallbackListener() {
      public void controlEvent(CallbackEvent theEvent) {
        nin.setActive( false ); 
       // nin.submit();
      }
    }
    );
  }
  
         public void set_scale_view()
         {  
           if (elementData.get(idElement).midiTypeOpt.getValue() == 0 && elementData.get(idElement).dmx.getValue() == 2) 
         
         if (elementData.get(idElement).dMode.getValue() == 21 || elementData.get(idElement).dMode.getValue() == 22  ) 
         {scale1.show(); 
       //  image(piano, (int) gridCols[1],(int)gridRow[8] , (int) gridCols[15], (int) gridRow[16]+rowBetw); 
       show_piano =1;
         
         elementData.get(idElement).nT.hide();
           String temp_scale1;
           char bit_sc1;
           boolean bit_scale1;
           byte numm;
           byte numm2;
           if (page_selected== false) 
           { numm =(byte)elementData.get(idElement).note[0];
            numm2 =(byte)elementData.get(idElement).setExcursionControllMax;}
            else
            { numm =(byte)elementData.get(idElement).note[1];
             numm2 =(byte)elementData.get(idElement).setExcursionControllMax_2nd;}
           // temp_scale1 = binary(elementData.get(idElement).note);
           temp_scale1 = binary(numm);
          for (byte i =0; i<8; i++)
          {  
            bit_sc1 =  temp_scale1.charAt(7-i);
           if ( bit_sc1 == '0') bit_scale1 = false; else bit_scale1 = true;
            scale1.set(i, 0,  bit_scale1) ;
          }
         
          temp_scale1 = binary(numm2);
              for (byte i =0; i<5; i++)
          {  
            bit_sc1 =  temp_scale1.charAt(7-i);
           if ( bit_sc1 == '0') bit_scale1 = false; else bit_scale1 = true;
            scale1.set(i+7, 0,  bit_scale1) ;}
          
         // scale1.set(2, 0,  true) ;
        }
        if (elementData.get(idElement).midiTypeOpt.getValue() != 0 || elementData.get(idElement).dmx.getValue() != 2)  
        if (elementData.get(idElement).dMode.getValue() == 21 || elementData.get(idElement).dMode.getValue() == 22 )
        {scale1.hide(); show_piano = 0;
        
         elementData.get(idElement).nT.show();
      }
        }
        
        
        
        public void sender_single (int i)
        {
          
        {
       if (page_selected == false  ){
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).note[0], elementData.get(i).memoryPosition-1);
        delay(delay_send);
       
        if (  elementData.get(i).keyBoard == 0) 
        {
          if (elementData.get(i).toggleMode == 21 || elementData.get(i).toggleMode == 22 || elementData.get(i).toggleMode == 19)  // encoders e spinners
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).setExcursionControllMin+32); // speed range -32 +32 viene inviata come 0-64
          else myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).setExcursionControllMin);
        } 
        
        else {
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).modifiers);
        }
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, PApplet.parseInt(elementData.get(i).toggleMode), elementData.get(i).addressDMX);
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, PApplet.parseInt(keyboard_out(elementData.get(i).keyBoard)), PApplet.parseInt ( elementData.get(i).indexMidiType ));
         delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, PApplet.parseInt(elementData.get(i).led_), 0);
        delay(80);
        
      }
     
      else // SECONDA PAGINA
      {
       myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).note[1], elementData.get(i).memoryPosition+63);
        delay(delay_send);
        
        if (  elementData.get(i).keyBoard_2nd == 0) 
        {
           if (elementData.get(i).toggleMode == 21 || elementData.get(i).toggleMode == 22 || elementData.get(i).toggleMode == 19) 
          myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).setExcursionControllMax_2nd, elementData.get(i).setExcursionControllMin_2nd+32);
          else 
          myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).setExcursionControllMax_2nd, elementData.get(i).setExcursionControllMin_2nd);
        
        } 
        else {
          myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).setExcursionControllMax_2nd, elementData.get(i).modifiers_2nd);
        }
        
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, PApplet.parseInt(elementData.get(i).toggleMode_2nd), elementData.get(i).addressDMX_2nd);
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, keyboard_out(PApplet.parseInt(elementData.get(i).keyBoard_2nd)), PApplet.parseInt ( elementData.get(i).indexMidiType_2nd ));
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, PApplet.parseInt(elementData.get(i).led_), 0);
        delay(80);
        
      }
      
      }
        }
        
        
        
        public void sender ()
        {
          
        i_sender ++;
       
    int i = i_sender;
  //  delay(260);
        
  //   for (int i=0; i<60; i++) 
    {
     //  if (elementData.get(i).hide == 0) 
      {
      // if (page_selected == false  )
       {
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).note[0], elementData.get(i).memoryPosition-1);
        delay(delay_send);
        if (  elementData.get(i).keyBoard == 0) {
          if (elementData.get(i).toggleMode == 21 || elementData.get(i).toggleMode == 22 || elementData.get(i).toggleMode == 19) 
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).setExcursionControllMin+32); // speed range -32 +32 viene inviata come 0-64
          else myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).setExcursionControllMin);
        } else {
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).modifiers);
        }
        delay(delay_send);
       if (elementData.get(i).hide == 0)  myBus.sendMessage(176+elementData.get(i).midiChannel-1, PApplet.parseInt(elementData.get(i).toggleMode), elementData.get(i).addressDMX);
       else myBus.sendMessage(176+elementData.get(i).midiChannel-1, 0, elementData.get(i).addressDMX);
       
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, PApplet.parseInt(keyboard_out(elementData.get(i).keyBoard)), PApplet.parseInt ( elementData.get(i).indexMidiType ));
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, PApplet.parseInt(elementData.get(i).led_), 0 );
        delay(80);
      //  break;
      }
    
     
     //  SECONDA PAGINA
     
     
      {
       myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).note[1], elementData.get(i).memoryPosition+63);
        delay(delay_send);
        if (  elementData.get(i).keyBoard_2nd == 0) {
           if (elementData.get(i).toggleMode == 21 || elementData.get(i).toggleMode == 22 || elementData.get(i).toggleMode == 19) 
          myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).setExcursionControllMax_2nd, elementData.get(i).setExcursionControllMin_2nd+32);
          else 
          myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).setExcursionControllMax_2nd, elementData.get(i).setExcursionControllMin_2nd);
        
        } else {
          myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).setExcursionControllMax_2nd, elementData.get(i).modifiers_2nd);
        }
        delay(delay_send);
        if (elementData.get(i).hide == 0)  myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, PApplet.parseInt(elementData.get(i).toggleMode_2nd), elementData.get(i).addressDMX_2nd);
        else myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, 0, elementData.get(i).addressDMX_2nd);
        
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, PApplet.parseInt(keyboard_out(elementData.get(i).keyBoard_2nd)), PApplet.parseInt ( elementData.get(i).indexMidiType_2nd ));
          delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, PApplet.parseInt(elementData.get(i).led_), 0 );
        delay(80);
     //   break;
      }
      
      }



  
    } 
    
    
   
    
  stroke(100);
  fill(0, 200, 0);

 rect(gridCols[17],  gridRow[0]+rowBetw, 
 
 gridCols[1] /3  // +(Betw2/5)
 +(i_sender*(Betw2/10)), 
 
 gridRow[1]-rowBetw*1.2f 
 ,4);
        
        }
String [] button_labels = {"Modifier", "Data byte1", "MINIMUM", "MAXIMUM", "MIDI CHANNEL", "DMX CHANNEL ", "MIDI TYPE", "MODE", "KEY", "HOT KEY", "", "","LED"}; // normal_labels
String [] pot_labels = {"Modifier", "Data byte1", "MINIMUM", "MAXIMUM", "MIDI CHANNEL", "DMX CHANNEL ", "MIDI TYPE", "MODE", "", "", "", "","LED"};
String [] page_labels = {"Page switch", "Data byte1", "Min", "Max", "MIDI CHANNEL", "", "MIDI TYPE", "MODE", "", "", "", "","LED"};
String [] spin_labels = {"SPINNER", "Data byte1", "SPEED ", "", "MIDI CHANNEL", "SPIN MODE", "MIDI TYPE", "MODE", "TOUCH-STOP", "", "", "","LED"};
String [] touch_labels = {"TOUCH SENSOR", "Data byte1", "sensitivity", "LED OUT", "MIDI CHANNEL", "RESET VALUE ", "MIDI TYPE", "MODE", "RESET MODE", "", "", "","touch mode"};
String [] mouse_labels = {"MOUSE EMULATOR", "MouseWheel", "X circuit pos", "Y circuit pos", "", "-/mouse/arrows", "", "MODE", "", "", "", "","1 = INVERT"};
String [] PADS_labels = {"PADS", "Data byte1", "", "", "MIDI CHANNEL", "", "MIDI TYPE", "MODE", "", "", "", "","LED"};
String [] distance_labels = {"DISTANCE SENSOR", "Data byte1", "MIN", "MAX", "MIDI CHANNEL", "DMX CHANNEL", "MIDI TYPE", "MODE", "POT/BUTTON/scale", "", "", "","LED"};
String [] GENERAL_labels = {"GENERAL SETUP","1= NoMobo","1= ExtraPlex", "0= PADS", ".", "spinners", ".", "MODE", "LED EFX", ".", ".", ".","LED"};

int [] typeMidiArray={ 144, 160, 176, 192, 208, 224};
String [] typeMidiList= {
"Note", // 0
"PolyAT", // 1
"CC", // 2
"PC",  // 3
"AT", // 4
"PB" // 5
};


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
"0 = NO Spinners . \n 1 = Top spinner ACTIVE. \n 2 = Side + Top spinner ACTIVE \n 3 = multiple encoders active",
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
"MOUSEWHEEL \n 0= no mouse wheel emulation \n 1 =  ACTIVE : the main SPINNER will emulate mousewheel movements, use SPEED settings of the Spinner to setup it.", //-25
"0 = shifters and multiplexers are enabled \n 1 = shifters and multiplexers are disabled, the Arduino pins 4,5,6,10,11,12 can be used as output pins for LEDs.",
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
  "left ctrl", "left shift",    "left alt", "tab", "return", "esc", "delete",   "space", 
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
  "mouse right","mouse center","**","***"
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
//THE CLASS OBJ of the Element of the DART
ArrayList <myUI> elementData = new ArrayList <myUI>();
ControlP5 cp5;

class myUI {
  //**************************  Data
  int labeler_= 1;
  int note[] = {0,0};
 // int note_2nd;
  //  memoryPosition
  int led_;
  int memoryPosition = 1;
  int mem;
  boolean selected = false;
  boolean noteControll;
 // int setExcursionControllMin[] = {0,0} ;
 int setExcursionControllMin;
  int setExcursionControllMin_2nd;
  int setExcursionControllMax; 
   int setExcursionControllMax_2nd; 
  // int setExcursionControllMax_2nd = 127;
  //int midiChannel[] = {1,1};
    int midiChannel;
  int midiChannel_2nd;
  int addressDMX;
  int addressDMX_2nd;
  // toggle buttons
  int toggleMode = 0;
  int toggleMode_2nd = 0;
  boolean noteCC;
  boolean togglePage;
  int hide;
  int indexMidiType; //optCC= typeMidiList[(indexTypeMidi)];
  int indexMidiType_2nd;
  int keyBoard;
   int keyBoard_2nd;
  int modifiers;
  int modifiers_2nd;
  int shape;
  int dimension =1;
  //***************************  Settings
  String label;
  String hint_message = ".";
  String temp;
  int posX;
  int posY;
  //int possX;
  //int possY;
  int radiusW;
  int radiusH;
  // dimension of the numberbox
  int boxX=60;
  int boxY=24;
  
  boolean feedback_;
// int feedback_value;

  /////////// instances of default class
  ControlP5 sett;
  //****************************** the button data

  boolean stateButton;
  boolean stateButton_2nd;
  boolean togglestate;
  boolean togglestate_2nd;

  //int [] typeMidiArray={144, 160, 176, 192, 208, 224};
  //******************************  
  //  for menus
  //****************************** 
  Numberbox mp, nT, min, max, midiC, dmx, led;
  ScrollableList dMode, midiTypeOpt,
 // dKeys, 
  dModif;
  Knob myKnobA;
  
  ListBox dKeys;
  // TextField labell;
  Textfield labell, label_hint;
//  Textarea hintarea;
 // String labelC;



      
 
  
  
  public void settingsBox() {
       // textFont(f, 20);
       
        //   min = sett.addNumberbox("setExcursionControllMin",  (int) gridCols[17]+Betw2, (int)gridRow[6]+rowBetw, boxX, boxY)
        label_hint =  sett.addTextfield("label_hint")
     .setPosition((int) (gridCols[20]+Betw2*0.8f),(int)gridRow[12]+rowBetw)
   //  .setSize(Betw2*6,30)
     .setSize( Betw2*6, (int)(rowBetw*1.5f))
     
     .plugTo(this, "label_hint" )
      .setFont(createFont("typeO.TTF", (Betw2/1.7f)))
        .setAutoClear(false)
.setId(2012)
     .setColorBackground(color(80,80,80)) 
    .setColorForeground(color(80,80,80))  
    .setColorValueLabel(color(60,60,60)) 
     .setColorCaptionLabel(color(160))
     .setLabel("hint")
     .setColor(color(220,220,220))
     .setVisible(false)
     ;
     
       
   labell =  sett.addTextfield("label")
     .setPosition( (int) (gridCols[20]+Betw2*0.8f), (int)gridRow[14]+rowBetw  // gridRow[10]+rowBetw
     ) 
      .setSize( Betw2*6, (int)(rowBetw*1.5f)) 
     .plugTo(this, "label" )
      .setFont(createFont("typeO.TTF", (Betw2/1.7f)))   
       .setAutoClear(false )
      .setId(2013)
     .setColorBackground(color(80,80,80)) 
    .setColorForeground(color(80,80,80)) 
    .setColorValueLabel(color(60,60,60)) 
     .setColorCaptionLabel(color(160))
     .setLabel("name")
     .setColor(color(220,220,220))  
     .setVisible(false)
     ;
  
    
 /*  hintarea =  sett.addTextarea("hints")
  //  cp5.addTextfield("lino")
     .setPosition((int) gridCols[1],(int)gridRow[2]+rowBetw)
       .setSize((int) gridCols[15]+Betw2/2,(int) gridRow[5])
     //  .plugTo(this, "label" )
  
      .setFont(createFont("typeO.TTF", (Betw2/1.7)))
   .setId(2011)
  .setColor(color(200))
                  .setColorBackground(110)
                  .setColorForeground(color(255,100))   
                    .setVisible(false)
                    ;*/
                    
//----------------------


               
//----------------------





    
    midiTypeOpt = sett.addScrollableList("midiTypeOpt")
      .plugTo(this, "indexMidiType" )
  .setPosition( (int)gridCols[20]+Betw2*0.8f, gridRow[6]+rowBetw)
      .setSize(Betw2*6, Betw2*6)
     // .setColorBackground(ControlP5.BLACK)
      .setBarHeight(21)
      .setItemHeight(30)
      .setOpen(false)
      .onEnter(toFront)
      .onLeave(close)
      .setType(ControlP5.DROPDOWN)
      //.setLabel("Type")
        .setLabel(typeMidiList[indexMidiType])
      .setId(2001)
      .addItems(typeMidiList)
      .addCallback(open_scale) 
      ;
      
         min = sett.addNumberbox("setExcursionControllMin",  (int) gridCols[17]+Betw2, (int)gridRow[6]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5f))
      .plugTo( this, "setExcursionControllMin" )
      .setId(2003)
    //  .setColorBackground(ControlP5.BLACK)
      .setLabel("Minimum")
        
       .setColorCaptionLabel(color(160))
      // .setColorValueLabel(150) 
   //    .onChange(general_open) 
      .setRange(-127, 127)
      .onLeave(closenumberbox)
  //   .onChange(general_open) 
      ;
      
    nT = sett.addNumberbox("note",  (int) gridCols[17]+Betw2 , (int)gridRow[4]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5f))
      .plugTo( this, "note" )
      .setId(2002)
      .setRange(0, 127)
     //  .onEnter(nT.setColorCaptionLabel(color(160)))
      .onLeave(closenumberbox)
      .onChange(general_open) 
      .setLabel("Value")
       .setColorCaptionLabel(color(160))
       // .setVisible(false);
        //.setHide();
      ;
    //  nT.hide();
     if (toggleMode == 17)  nT.plugTo( this, "note_2nd" );
     
     
     
 
      
    max = sett.addNumberbox("setExcursionControllMax", (int) gridCols[17]+Betw2, (int)gridRow[8]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5f))
      .plugTo( this, "setExcursionControllMax" )
      .setId(2004)
     // .setColorBackground(ControlP5.BLACK)
      .setValue(127)
      .setLabel("Maximum")
       .setColorCaptionLabel(color(160))
      .setRange(0, 127)
      .onLeave(closenumberbox)
      .onChange(general_open) 
      ;   
    midiC = sett.addNumberbox("midiChannel", (int) gridCols[17]+Betw2, (int)gridRow[10]+rowBetw,(int)(Betw2*3), (int)(rowBetw*1.5f))
      .plugTo( this, "midiChannel" )
      .setId(2005)
     // .setColorBackground(ControlP5.BLACK)
      .setLabel("Midi Channel")
       .setColorCaptionLabel(color(160))
      .setRange(1, 16)
      .onLeave(closenumberbox)
      ;
    dmx = sett.addNumberbox("addressDMX", (int) gridCols[17]+Betw2, (int)gridRow[12]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5f))
      .plugTo( this, "addressDMX" )
      .setId(2006)
     // .setColorBackground(ControlP5.BLACK)
      .setLabel("DMX Channel")
       .setColorCaptionLabel(color(160))
      .setRange(0, 127)
      .addCallback(open_scale) 
      .onLeave(closenumberbox)
      .onChange(general_open) 
      ;
      
      
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
    dMode =  sett.addScrollableList("toggleMode")
      
       .setPosition((int)gridCols[20]+Betw2*0.8f, gridRow[4]+rowBetw)
      .setSize(Betw2*6, Betw2*8)
      .plugTo( this, "toggleMode" )
       .onEnter(toFront)
      .onLeave(close)
      .setId(2007)
    
      .setBarHeight(21)
      .setItemHeight(30)
      .addItems(toggleList)
      .setOpen(false)
      .setType(ControlP5.DROPDOWN)
      .setLabelVisible(true) 
       .onChange(doppioni) 
  
     .setLabel(toggleList[toggleMode]);
      ;
     

  
  
  //  dKeys = sett.addScrollableList("keyBoard")
    // addListBox
     dKeys = sett.addListBox("keyBoard")
      .setPosition( (int)gridCols[20]+Betw2*0.8f, gridRow[8]+rowBetw)
       .setSize(Betw2*6, Betw2*8)
      .plugTo( this, "keyBoard" )
      .onEnter(toFrontBox)
      .onLeave(closeBox)
      .setId(2010)
     // .setColorBackground(ControlP5.BLACK)
      .setBarHeight(21)
      .setItemHeight(35)
      //.toUpperCase(false)
      // .toUpperCase(true)
     // .captionLabel().toUpperCase(false);
     // .valueLabel().toUpperCase(false);
      .addItems(keyboardList)
      .setType(ControlP5.DROPDOWN)
      .setOpen(false)
     .onChange(general_open) 
     // .setLabel("Keys");
     .setLabel(keyboardList[keyBoard]);
     dKeys.getValueLabel().toUpperCase(false);
     
    dModif = sett.addScrollableList("Modifiers")
      .setPosition( (int)gridCols[20]+Betw2*0.8f, gridRow[10]+rowBetw)
       .setSize(Betw2*6, Betw2*8)
      .plugTo( this, "modifiersBox" )
      .onEnter(toFront)
      .onLeave(close)
      .setId(2009)
    //  .setColorBackground(ControlP5.BLACK)
      .setBarHeight(21)
      .setItemHeight(30)
      .addItems(modifiersList)
      .setType(ControlP5.DROPDOWN)
      .setOpen(false)
      //.setLabel("Modifiers");
     .setLabel(modifiersList[modifiers]);
  
    sett.addToggle("togglePage")  
      .plugTo( this, "togglePage" )
      .setId(2008)
      .setMode(ControlP5.SWITCH)
   //   .setColorBackground(color(255, 100))
      .setSize(boxX-10, boxY-10)
      .setPosition(gridCols[18]+5, gridRow[18])
      .setVisible(false); // dModif
    mp = sett.addNumberbox("memorySlot",  (int) gridCols[17]+Betw2  , (int) gridRow[16]+rowBetw,  (int)(Betw2*3), (int)(rowBetw*1.5f)) //  
 //   (int) gridCols[17]+Betw2  , (int) gridRow[16]+rowBetw, 
      .onLeave(mp_doppioni)
      .plugTo( this, "memoryPosition" )
      .setId(2000)
      .setRange(0, 64)
       .setColorCaptionLabel(color(160))
      .setLabel("Memory pos.")
      .onLeave(closenumberbox)
     //.onChange(mp_doppioni)
      
    //  .setValue
   // .setValue(memoryPosition) 
     .setVisible(false);
    ;
   led = sett.addNumberbox("LED-OUT", (int) gridCols[17]+Betw2  , (int) gridRow[14]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5f)) //   
      .plugTo( this, "led_" )
      .setId(2014)
      .setRange(0, 32)
       .setColorCaptionLabel(color(160))
      .setLabel("LED")
  //    .setVisible(false)
      .onLeave(closenumberbox)
      ;
    ;
      
      
    // makeEditable( mp );
    makeEditable( nT );
       makeEditable( mp );
        makeEditable( led );
    makeEditable( min ); 
    makeEditable( max ); 
    makeEditable( midiC ); 
    makeEditable( dmx );
    
    labeler ();

  }
  
   myUI (PApplet applet, ControlP5 cp5_, String label_, int mem_pos, int id_, float posx_, float posy_, int rW, int rH, int setView_) {
     label = label_;
    shape = setView_;
 //  memoryPosition=mem_pos;
   // mp.setValue(float(3));
    posX = PApplet.parseInt(posx_);
    posY = PApplet.parseInt(posy_);
  //  possX = int(posx_);
  //  possY = int(posy_);
    radiusW = rW;
    radiusH = rH;

       sett = new ControlP5(applet);
    sett.setFont(f, (int)(Betw2*0.6f));
    sett.setColorBackground(color(90, 90, 90) );
    
    

   /* switch(setView_) {
    case 0:
      cp5_.addButton(label)
        .setId(id_)
        .setPosition(posX, posY)
        .setSize(radiusW, radiusH)
        .setView(new RectButton())
        ;
      break;
    case 1: 
         cp5_.addKnob(label)
         .setId(id_)
               .setRange(0,127)
               .setValue(127)
               .setLabelVisible(false)            
               .setNumberOfTickMarks(0) 
               .setViewStyle(3)
               .setTickMarkLength(0)            
               .setDragDirection(Knob.VERTICAL)
               .setView(new Manopolox()) 
               ;
      break;
    case 2:
     
          cp5_.addKnob(label)
         .setId(id_)
               .setRange(0,127)
               .setValue(127)
               .setLabelVisible(false)            
               .setNumberOfTickMarks(0) 
               .setViewStyle(3)
               .setTickMarkLength(0)            
               .setDragDirection(Knob.VERTICAL)
               .setView(new faderox()) 
               ;
        
      break;
    case 3:
      cp5_.addButton(label)
        .setId(id_)
        .setPosition(posX, posY)
        .setSize(radiusW, radiusH)
        ;
      break;
    }
    
    */
    
      cp5_.addKnob(label)
         .setId(id_)
               .setRange(0,127)
               .setValue(64)
               .setLabelVisible(false)            
               .setNumberOfTickMarks(0) 
               .setViewStyle(3)
               .setTickMarkLength(0)            
             //  .setDragDirection(Knob.VERTICAL)
               
                 .setDragDirection(Knob.HORIZONTAL)
              //  .setView(new Manopolox()) 
               .onChange(send_ctrl)
               .onReleaseOutside(leave_item)
               ;
    
     settingsBox();
      memoryPosition=mem_pos;
  }
  
  
  public boolean getStateButton () {
  if (page_selected == false) { return stateButton;}
  else {return stateButton_2nd;}
  }
  public void setStateButton (boolean b) {
    if (page_selected == false) { stateButton = b;}
    else  stateButton_2nd = b;
   
  }
  ////////////// THE BOX SETTING DESIGN AND TOGGLE///////////////////////

  public void setDisplay (boolean b) {
    sett.setVisible(b); // to set visibility of box settings on
    if (b) {sett.show(); }else sett.hide();
  }
  public boolean getStateDisplay() {
    //println("sett is visible: " +  sett.isVisible());
    return sett.isVisible();
  }

 

  // THE LISTENER EVENT TO CAPTURE THE TOGGLE EVENT //

  public void note(int v) {
    note[0] = v;
    //    println("Value of the Note: "+ v);
  }
    public void note_2nd(int v) {
    note[1] = v;
    //    println("Value of the Note: "+ v);
  }
  
  public void setExcursionControllMin(int v) {
    setExcursionControllMin = v;
    // println(" Minimum Value: "+ v);
  }
  
  public void setExcursionControllMax(int v) {
    setExcursionControllMax= v;
    //println(" Maximum Value: "+ v);
  }
  public void midiChannel(int v) {
    midiChannel = v;
    //println("Value of MIDI Channel: "+v);
  }
  public void addressDMX(int v) {
    addressDMX = v;
    //println("Value of DMX Address: "+v);
  }
  public void toggleMode(int f) {  
   // println("toggle: " + f);
    toggleMode = f;
  }
  public void labell(String f) {  
  //  println("toggle: " + f);
    label = f;
    labell.setText(elementData.get(idElement).label);
  labell.setFocus(false);
  }
  public void label_hint (String f) {  
  //  println("toggle: " + f);
    hint_message = f;
  // label_hint.setText(elementData.get(idElement).hint_message);
  // labell.setFocus(false);
  //  label_hint.setFocus(false);
  }
  public void midiTypeOpt(int a) {
    indexMidiType =  a;
 //   println("index midi type: " + indexMidiType);
  }
  /* void keyBoard(int f) {  
    if  ( f > 118) {
      keyBoard = f-94;
      //ddl.addItem((String)keyboardList.get(i), i+3);
    } else
      if ( f >= 24 ) {
        //ddl.addItem((String)keyboardList.get(i), i+1);
        keyBoard = f+8;
      } else {
        keyBoard = f;
      }
  //  println("keysRaw", f);
   // println("keys", keyBoard);
    //println("size", keyboardList.size());
  }
  */
  
  public void modifiersBox(int f) {  
    //println("toggle", f);
    modifiers = f;
    //println(f);
  }
  public void togglePage(boolean f) {
    //println("togglePage", f);
    //  togglePage = f;
  }
   public void led(int f) {
    //println("togglePage", f);
    led_= f;
    //  togglePage = f;
  }
  
  
  public void memoryPosition(int v) {
     memoryPosition = v;
     mem = memoryPosition;
   //  mp.setValue(memoryPosition);
    //   println("Value of Memory Position: "+v);
  }
  public void displaySettingsUI() {

      fill(155);
    textSize(Betw2/1.6f);
    // textSize(Betw2/1.7);
    text("Settings box: ITEM ", gridCols[17]+ Betw2*0.5f
    , gridRow[3]  +rowBetw/2
    ); // box settings  label
  
 
  //    text("SETTINGS", gridCols[17]+ Betw2*0.5
  //  , gridRow[4] //+rowBetw*1.2
  //  ); // box settings  label
  
   fill(255);
      text(label, gridCols[20]+ Betw2*0.5f
    , gridRow[3]+rowBetw/2); // box settings  label
    
  }
  
  //TO EDIT THE NUMBER OF SETTINGS IN NUMBERBOX
 /*  makeEditable( nT );
     
     
    makeEditable( min ); 
    makeEditable( max ); 
    makeEditable( midiC ); 
    makeEditable( dmx );*/
  public void plug_page () 
 {
  if (page_selected == false){
  
    if ( toggleMode != 17 &&  toggleMode != 26) {  // page e general devono essere uguali
      
      nT.unplugFrom (this); //  else note = note_2nd;
    nT.plugTo( this, "note" );
     nT.setValue(note[0]);
    
        min.unplugFrom (this);
        min.plugTo( this, "setExcursionControllMin" );
        min.setValue(setExcursionControllMin);
        
        max.unplugFrom (this);
        max.plugTo( this, "setExcursionControllMax" );
        max.setValue(setExcursionControllMax);
      
         midiC.unplugFrom (this);
        midiC.plugTo( this, "midiChannel" );
        midiC.setValue(midiChannel);
        
          dmx.unplugFrom (this);
        dmx.plugTo( this, "addressDMX" );
        dmx.setValue(addressDMX);
       // dKeys
          dKeys.unplugFrom (this);
        dKeys.plugTo( this, "keyBoard" );
       // dKeys.setLabel(keyboardList[keyBoard]);
           if (toggleMode < 21) dKeys.setLabel(keyboardList[keyBoard]); else dKeys.setLabel(keylist_touch[keyBoard]);
        dKeys.setValue(keyBoard);
     //dModif
         dModif.unplugFrom (this);
        dModif.plugTo( this, "modifiers" );
        dModif.setValue(modifiers);
     //  midiTypeOpt 
     
            midiTypeOpt.unplugFrom (this);
        midiTypeOpt.plugTo( this, "indexMidiType" );
         midiTypeOpt.setLabel(typeMidiList[indexMidiType]);
        midiTypeOpt.setValue(indexMidiType);
       //   midiTypeOpt.setLabel(typeMidiList[indexMidiType]);
  }
  
  
      //  dMode
          if ( toggleMode < 17 || toggleMode == 0)  dMode.unplugFrom (this);
       dMode.plugTo( this, "toggleMode" );
       dMode.setValue(toggleMode);
        
             //  labeler();
        
  }

   if (page_selected == true) {
     
  if ( toggleMode != 17 &&  toggleMode != 26) {  
    
    nT.unplugFrom (this);  // else note_2nd = note;
     nT.plugTo( this, "note_2nd" );
     nT.setValue(note[1]);
   
         min.unplugFrom (this);
        min.plugTo( this, "setExcursionControllMin_2nd" );
        min.setValue(setExcursionControllMin_2nd);
        
              max.unplugFrom (this);
        max.plugTo( this, "setExcursionControllMax_2nd" );
        max.setValue(setExcursionControllMax_2nd);
        
              midiC.unplugFrom (this);
        midiC.plugTo( this, "midiChannel_2nd" );
        midiC.setValue(midiChannel_2nd);
        
              dmx.unplugFrom (this);
        dmx.plugTo( this, "addressDMX_2nd" );
        dmx.setValue(addressDMX_2nd);
        
            dKeys.unplugFrom (this);
        dKeys.plugTo( this, "keyBoard_2nd" );
         if (toggleMode_2nd < 21) dKeys.setLabel(keyboardList[keyBoard_2nd]); else dKeys.setLabel(keylist_touch[keyBoard_2nd]);
        dKeys.setValue(keyBoard_2nd);
        
              dModif.unplugFrom (this);
        dModif.plugTo( this, "modifiers_2nd" );
        dModif.setValue(modifiers_2nd);
        
                    midiTypeOpt.unplugFrom (this);
        midiTypeOpt.plugTo( this, "indexMidiType_2nd" );
        
        midiTypeOpt.setLabel(typeMidiList[indexMidiType_2nd]);
        midiTypeOpt.setValue(indexMidiType_2nd);
  }
        
             if ( toggleMode < 17 || toggleMode == 0)    dMode.unplugFrom (this);
       dMode.plugTo( this, "toggleMode_2nd" );
       dMode.setValue(toggleMode_2nd);
        
       // labeler();
  }
  }
  
  public void plugga_page() {  // linka un modificatore sulla prima e seconda pagina
  midiC.plugTo( this, "midiChannel" ); midiC.plugTo( this, "midiChannel_2nd" ); 
nT.plugTo( this, "note" ); nT.plugTo( this, "note_2nd" );
min.plugTo( this, "setExcursionControllMin" ); min.plugTo( this, "setExcursionControllMin_2nd" );
max.plugTo( this, "setExcursionControllMax" ); max.plugTo( this, "setExcursionControllMax_2nd" );
midiC.plugTo( this, "midiChannel" ); midiC.plugTo( this, "midiChannel_2nd" );
dmx.plugTo( this, "addressDMX" ); dmx.plugTo( this, "addressDMX_2nd" );
midiTypeOpt.plugTo(this, "indexMidiType" ); midiTypeOpt.plugTo(this, "indexMidiType_2nd" );
dKeys.plugTo( this, "keyBoard" ); dKeys.plugTo( this, "keyBoard_2nd" );
  }
  
  
  
  public void labeler () {
  
    if (full_screen == false)    {
      
       if (edit_mode == false){  
         labell.show();
      label_hint.show();
      mp.show(); 
      
    
  }
      
      
//  if ( elementData.get(idElement).labeler_ == 1) { elementData.get(i).label_hint.setFocus(false);
 //mp, nT, min, max, midiC, dmx;
 
 
// String [] normal_labels = {"Modifier", "Data byte1", "MINIMUM", "MAXIMUM", "MIDI CHANNEL", "DMX CHANNEL ", "MIDI TYPE", "MODE", "KEY", "KEY MODIFIERS", "-", "- "};
// String [] page_labels = {"Page switch", "Data byte1", "-", "-", "MIDI CHANNEL", "- ", "MIDI TYPE", "-", "-", "-", "-", "- "};
// String [] spin_labels = {"SPINNER", "Data byte1", "SPEED ", "INVERT", "MIDI CHANNEL", "endless type", "MIDI TYPE", "POT/ENDLESS", "TOUCH-STOP", "-", "-", "- "};
// String [] touch_labels = {"TOUCH SENSOR", "Data byte1", "-", "-", "MIDI CHANNEL", "RESET VALUE ", "MIDI TYPE", "RESET MODE", "-", "-", "-", "-"};
// String [] mouse_labels = {"MOUSE EMULATOR", "ciao", "pippo", "baudo", "beppe", "grillo ", "gatto", "luigi", "di", "maio", "nese", "ciro "};
//  String [] distance_labels = {"DISTANCE SENSOR", "ciao", "pippo", "baudo", "beppe", "grillo ", "gatto", "luigi", "di", "maio", "nese", "ciro "}; 
 if (dMode.getValue() == 0) { 
           nT.setLabel(PADS_labels[1]);
             nT.setLabel(PADS_labels[1]); 
          min.setLabel(PADS_labels[2]); 
           max.setLabel(PADS_labels[3]); 
            midiC.setLabel(PADS_labels[4]); 
             dmx.setLabel(PADS_labels[5]);  
             led.setLabel(PADS_labels[12]); 
                  dKeys.setItems(toggleList);
                //     dModif.setVisible(false);
                nT.hide();
           //   nT.setVisible(false);
            dModif.lock(); 
                dModif.hide(); 
               // dModif.setVisible(false) ; 
                dKeys.hide();
                    min.hide();
                     min.setRange(0,127);
            max.hide(); 
             led.hide(); 
                          dmx.hide(); 
            midiC.hide(); 
            midiTypeOpt.hide();
            scale1.hide(); show_piano =0;
                dMode.show();
                 }
                 
 else if (dMode.getValue() <11 ) {    // buttons -toggle groups
         nT.setLabel(button_labels[1]); 
          min.setLabel(button_labels[2]); 
           max.setLabel(button_labels[3]); 
            midiC.setLabel(button_labels[4]); 
             dmx.setLabel(button_labels[5]); 
             led.setLabel(button_labels[12]); 
       dKeys.setItems(keyboardList);
      //  dModif.setVisible(true); // dModif
        nT.show();
      //  nT.setVisible(true);
       dModif.show(); 
     //  dModif.setVisible(true);
      dModif.unlock(); 
        dKeys.show();
          min.show(); 
          min.setRange(0,127);
            max.show(); 
             led.show(); 
            
              dmx.show(); 
            midiC.show(); 
            midiTypeOpt.show();
            scale1.hide(); show_piano =0;
                dMode.show();     
         }
         
else if (dMode.getValue() <17 ) {  // pot e hypercurve
         nT.setLabel(pot_labels[1]); 
          min.setLabel(pot_labels[2]); 
           max.setLabel(pot_labels[3]); 
            midiC.setLabel(pot_labels[4]); 
             dmx.setLabel(pot_labels[5]); 
              led.setLabel(pot_labels[12]); 
       dKeys.setItems(keyboardList);
      //  dModif.setVisible(true); // dModif
        nT.show();
      //  nT.setVisible(true);
       dModif.hide(); 
     //  dModif.setVisible(true);
      dModif.unlock(); 
        dKeys.hide();
          min.show(); 
          min.setRange(0,127);
            max.show(); 
             led.show(); 
            
              dmx.show(); 
            midiC.show(); 
            midiTypeOpt.show();
            scale1.hide(); show_piano =0;
                dMode.show();     
         }
         
else if (dMode.getValue() == 17) {    // page
           nT.setLabel(page_labels[1]);
             nT.setLabel(page_labels[1]); 
          min.setLabel(page_labels[2]); 
           max.setLabel(page_labels[3]); 
            midiC.setLabel(page_labels[4]); 
             dmx.setLabel(page_labels[5]);   
               led.setLabel(page_labels[12]); 
                  dKeys.setItems(toggleList);
                //     dModif.setVisible(false);
                  nT.show();
                dModif.hide(); 
                dKeys.hide();
                    min.show(); 
                     min.setRange(0,127);
            max.show(); 
            led.hide(); 
                          dmx.hide(); 
            midiC.show(); 
            midiTypeOpt.show();
            scale1.hide(); show_piano =0;
                dMode.show();
                 }
                 
                 
else if (dMode.getValue() == 18) {  // distance
           nT.setLabel(distance_labels[1]);
             nT.setLabel(distance_labels[1]); 
          min.setLabel(distance_labels[2]); 
           max.setLabel(distance_labels[3]); 
            midiC.setLabel(distance_labels[4]); 
             dmx.setLabel(distance_labels[5]);   
               led.setLabel(distance_labels[12]); 
                  dKeys.setItems(keylist_touch);
                 //    dModif.setVisible(false);
                   nT.show();
                 dModif.hide(); 
                 dKeys.show();
                     min.show(); 
                      min.setRange(0,127);
            max.show(); 
            led.hide(); 
                          dmx.show(); 
            midiC.show();
            midiTypeOpt.show();
         scale1.hide(); show_piano =0;
          dMode.show();
                 }
                 
                  else if (dMode.getValue() == 19) { 
           nT.setLabel(spin_labels[1]);
             nT.setLabel(spin_labels[1]); 
          min.setLabel(spin_labels[2]); 
           max.setLabel(spin_labels[3]); 
            midiC.setLabel(spin_labels[4]); 
             dmx.setLabel(spin_labels[5]);   
                led.setLabel(spin_labels[12]);   
             dKeys.setItems(toggleList_spinner);
         //     dModif.setVisible(false);
           nT.show();
          dModif.hide(); 
          dKeys.show();
              min.show(); 
              min.setRange(-32,32);
            max.hide(); 
            led.hide(); 
                          dmx.show(); 
            midiC.show(); 
            midiTypeOpt.show();
              
          set_scale_view();
              dMode.show();
         
          
                 }
                 
else if (dMode.getValue() == 20) { 
           nT.setLabel(PADS_labels[1]);
             nT.setLabel(PADS_labels[1]); 
          min.setLabel(PADS_labels[2]); 
           max.setLabel(PADS_labels[3]); 
            midiC.setLabel(PADS_labels[4]); 
             dmx.setLabel(PADS_labels[5]); 
             led.setLabel(PADS_labels[12]);  
                  dKeys.setItems(toggleList);
                //     dModif.setVisible(false);
                  nT.show();
                dModif.hide(); 
                dKeys.hide();
                    min.hide(); 
                     min.setRange(0,127);
            max.hide(); 
            led.hide(); 
                          dmx.hide(); 
            midiC.show(); 
            midiTypeOpt.show();
         scale1.hide(); show_piano =0;
             dMode.show();
                 }
                 
         
 else if (dMode.getValue() == 21 || dMode.getValue() == 22) { 
           nT.setLabel(spin_labels[1]);
             nT.setLabel(spin_labels[1]); 
          min.setLabel(spin_labels[2]); 
           max.setLabel(spin_labels[3]); 
            midiC.setLabel(spin_labels[4]); 
             dmx.setLabel(spin_labels[5]);   
                led.setLabel(spin_labels[12]);   
             dKeys.setItems(toggleList_spinner);
         //     dModif.setVisible(false);
           nT.show();
          dModif.hide(); 
          dKeys.show();
              min.show(); 
              min.setRange(-32,32);
            max.hide(); 
            led.hide(); 
                          dmx.show(); 
            midiC.show(); 
            midiTypeOpt.show();
              
          set_scale_view();
              dMode.show();
         
          
                 }
                
else if (dMode.getValue() == 23 || dMode.getValue() == 24) { 
           nT.setLabel(touch_labels[1]);
             nT.setLabel(touch_labels[1]); 
          min.setLabel(touch_labels[2]); 
           max.setLabel(touch_labels[3]); 
            midiC.setLabel(touch_labels[4]); 
             dmx.setLabel(touch_labels[5]);     
               led.setLabel(touch_labels[12]);  
                  dKeys.setItems(toggleList_spinner); //toggleList_spinner
                 //    dModif.setVisible(false);
                   nT.show();
                 dModif.hide(); 
                 dKeys.show();
                     min.show(); 
                      min.setRange(0,127);
            max.show(); 
            led.show(); 
                          dmx.show(); 
            midiC.show(); 
            midiTypeOpt.show();
            scale1.hide(); show_piano =0;
                dMode.show();
         
                 }
               

 
                 
                 
 
                 
 else if (dMode.getValue() == 25) { 
           nT.setLabel(mouse_labels[1]);
             nT.setLabel(mouse_labels[1]); 
          min.setLabel(mouse_labels[2]); 
           max.setLabel(mouse_labels[3]); 
            midiC.setLabel(mouse_labels[4]); 
             dmx.setLabel(mouse_labels[5]);     
                 led.setLabel(mouse_labels[12]); 
                  dKeys.setItems(toggleList);
                 //    dModif.setVisible(false);
                   nT.show();
                 dModif.hide(); 
                 dKeys.hide();
                     min.show(); 
                      min.setRange(0,127);
            max.show(); 
            led.show(); 
                          dmx.show(); 
            midiC.hide(); 
            midiTypeOpt.hide();
            scale1.hide(); show_piano =0;
                dMode.show();
         
                 }
else if (dMode.getValue() == 26) { 
           nT.setLabel(GENERAL_labels[1]);
             nT.setLabel(GENERAL_labels[1]); 
          min.setLabel(GENERAL_labels[2]); 
           max.setLabel(GENERAL_labels[3]); 
            midiC.setLabel(GENERAL_labels[4]); 
             dmx.setLabel(GENERAL_labels[5]);    
               led.setLabel(GENERAL_labels[12]); 
                //   dKeys.setItems(toggleList);
                   dKeys.setItems(keylist_general);
                 //    dModif.setVisible(false);
                   nT.show();
                 dModif.hide(); 
                 dKeys.show();
                     min.show(); 
                      min.setRange(0,127);
            max.show(); 
            led.hide(); 
                          dmx.show(); 
            midiC.hide(); 
            midiTypeOpt.hide();
            scale1.hide(); show_piano =0;
              dMode.show();
         
                 }
 
 

}

else {
    labell.hide();
      label_hint.hide();
      mp.hide();
      
      
      
                 nT.hide();
                 dModif.hide(); 
                 dKeys.hide();
                 min.hide(); 
                
            max.hide(); 
            led.hide(); 
            dmx.hide(); 
            midiC.hide(); 
            midiTypeOpt.hide();
            scale1.hide(); show_piano =0;
            dMode.hide();
}
 }
 


  
  
  
  public void sendMidiMessageON() { // 
  
    if (infoGraph < 65)
    
    if (page_selected == false && elementData.get(idElement).toggleMode <11 && elementData.get(idElement).toggleMode >0) 
  {  myBus.sendMessage(144+(elementData.get(idElement).indexMidiType)*16+elementData.get(idElement).midiChannel-1, 
  elementData.get(idElement).note[0], 
  PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue()) 
  
  );
       wave.setFrequency( frequenza2(elementData.get(idElement).note[0]) );
       if (soundstatus == false)  wave.setAmplitude( 0.5f );
       if (Monitor_OUT_button.isOn() )
       {
  buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(idElement).midiChannel;
  Note = elementData.get(idElement).note[0];
  optCC= typeMidiList[(indexMidiType)];
  Intensity = PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue());
   current_db2 = Intensity;
  raw1 = indexMidiType*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;}
  
  MIDI_OUT_LED = true;
  
}
    
    if (page_selected == true && elementData.get(idElement).toggleMode_2nd <11 && elementData.get(idElement).toggleMode_2nd >0) 
   { myBus.sendMessage(144+(elementData.get(idElement).indexMidiType_2nd)*16+elementData.get(idElement).midiChannel_2nd-1, 
   elementData.get(idElement).note[1], 
   PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue())
   );
      wave.setFrequency( frequenza2(elementData.get(idElement).note[0]) );
      if (soundstatus == false)  wave.setAmplitude( 0.5f );
       if (Monitor_OUT_button.isOn() )
       {
  buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(idElement).midiChannel_2nd;
  Note = elementData.get(idElement).note[1];
  optCC= typeMidiList[(indexMidiType_2nd)];
  Intensity = PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue());
   current_db2 = Intensity;
  raw1 = indexMidiType_2nd*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;
       }
  MIDI_OUT_LED = true;

 }
  }
  
  
  
  public void sendMidiMessageOFF() {
  /*  if ( noteCC == true) {
      myBus.sendMessage(0xB0, midiChannel-1, note, 0);
    } else if ( noteCC == false) {
      myBus.sendMessage(0x90, midiChannel-1, note, 0);
    }*/
    if (infoGraph < 65)
     if (page_selected == false && elementData.get(idElement).toggleMode <11 && elementData.get(idElement).toggleMode >0 && elementData.get(idElement).indexMidiType != 3) 
    { myBus.sendMessage(144+(elementData.get(idElement).indexMidiType)*16+elementData.get(idElement).midiChannel-1, elementData.get(idElement).note[0], 0);
  // saw.amp(0.0);
  wave.setAmplitude( 0.0f );
   if (Monitor_OUT_button.isOn() )
       {
   buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(idElement).midiChannel;
  //if ( elementData.get(idElement).indexMidiType == 5) {Note = 64;}  else    
  Note = elementData.get(idElement).note[0];
  
  optCC= typeMidiList[(indexMidiType)];
  Intensity = 0;
   current_db2 = Intensity;
  raw1 = indexMidiType*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;
  feedback_counter = 0;
       }
  MIDI_OUT_LED = true;
  
}
     
     if (page_selected == true && elementData.get(idElement).toggleMode_2nd <11 && elementData.get(idElement).toggleMode_2nd >0 
     && elementData.get(idElement).indexMidiType_2nd != 3) 
   {  myBus.sendMessage(144+(elementData.get(idElement).indexMidiType_2nd)*16+elementData.get(idElement).midiChannel_2nd-1, elementData.get(idElement).note[1], 0);
   // saw.amp(0.0);
     wave.setAmplitude( 0.0f );
      if (Monitor_OUT_button.isOn() )
       {
  buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(idElement).midiChannel_2nd;
  
// if ( elementData.get(idElement).indexMidiType_2nd == 5) {Note = 64;} else
 Note = elementData.get(idElement).note[1]; 
  optCC= typeMidiList[(indexMidiType_2nd)];
  Intensity = 0;
   current_db2 = Intensity;
  raw1 = indexMidiType*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;
  feedback_counter = 0;
       }
  MIDI_OUT_LED = true;
 }
  }
  
  
  public void loadTableRow(TableRow t) {
    
       hide = t.getInt("Hide");   
    if(hide == 0 )
   { toggleMode = t.getInt("Toggle");
    toggleMode_2nd = t.getInt("Toggle_2nd");
    if (toggleMode == 26 || toggleMode== 17)  { plugga_page();  }
   }
    else 
    { toggleMode = 0;
    toggleMode_2nd = 0;}
    
     if (toggleMode == 21 || toggleMode == 22) min.setRange(-100,100);
    /////////////
    
    posX = PApplet.parseInt ( (width/ ex_dimensionX   )* t.getInt("posX"));
    posY = PApplet.parseInt ( ( height / ex_dimensionY )* t.getInt("posY"));   
    shape = t.getInt("shape");
    radiusW = PApplet.parseInt (( width/ ex_dimensionX   )*t.getInt("dimensionX"));
    radiusH = PApplet.parseInt (( height / ex_dimensionY )*t.getInt("dimensionY"));
  
    // labeler();
    
   // labeler();
    //  plug_page();
     
    memoryPosition = t.getInt("MemoryPosition");
    label = t.getString("Name");    
    indexMidiType = t.getInt("midiType"); 
    indexMidiType_2nd = t.getInt("midiType_2nd");
    note[0]= t.getInt("Note");
    note[1]= t.getInt("Note_2nd");
    setExcursionControllMax= t.getInt("MaximumValue");
    setExcursionControllMax_2nd= t.getInt("MaximumValue_2nd");
    setExcursionControllMin =t.getInt("MinimumValue");
    setExcursionControllMin_2nd =t.getInt("MinimumValue_2nd");
    
 
    
    midiChannel = t.getInt("MidiChannel");
    midiChannel_2nd = t.getInt("MidiChannel_2nd"); 
    keyBoard = t.getInt("Keys");
    keyBoard_2nd = t.getInt("Keys_2nd");
    modifiers = t.getInt("Modifiers");
    modifiers_2nd = t.getInt("Modifiers_2nd");
    hint_message = t.getString("hint_message");    
    labeler_ = t.getInt("labeler");
    led_ = t.getInt("led");  
    addressDMX = t.getInt("DMXAddress");
    addressDMX_2nd = t.getInt("DMXAddress_2nd");
    togglePage = PApplet.parseBoolean(t.getInt("Page"));
    
  //    if (toggleMode == 21 || toggleMode == 22) min.setRange(-100,100);
    
        if (toggleMode == 21 || toggleMode == 22)  println(setExcursionControllMin);
   
    if (toggleMode_2nd == 21 || toggleMode_2nd == 22) { println( setExcursionControllMin_2nd);     println("---"); }
      
      
    
    mp.setValue(memoryPosition);   
    dMode.setValue(toggleMode);
    dKeys.setValue(keyBoard);    
    nT.setValue(note[0]);
    min.setValue(setExcursionControllMin);
    max.setValue(setExcursionControllMax);
    midiC.setValue(midiChannel);
    dmx.setValue(addressDMX);
    led.setValue(led_);
    
    if (toggleMode == 21 || toggleMode == 22)  println(setExcursionControllMin);
   
    if (toggleMode_2nd == 21 || toggleMode_2nd == 22) { println( setExcursionControllMin_2nd);  println("---"); }
   
;
  }
}
  
  
  
  
  
  
  
  
  
  
  
  
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////// fine myui
  
    CallbackListener send_ctrl = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {

  
  if (elementData.get(idElement).toggleMode >10  && elementData.get(idElement).toggleMode <16 && page_selected == false)
  if ( elementData.get(idElement).indexMidiType == 2 || elementData.get(idElement).indexMidiType == 5)
 
  {
 myBus.sendMessage(144+(elementData.get(idElement).indexMidiType)*16+elementData.get(idElement).midiChannel-1, 
 elementData.get(idElement).note[0], 
 PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue())      
 );
  wave.setFrequency( frequenza2( PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue())     ));
 if (soundstatus == false) wave.setAmplitude( 0.5f );
 feedback_counter = 0;
 if (Monitor_OUT_button.isOn() )
 {
  buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(idElement).midiChannel;
  if ( elementData.get(idElement).indexMidiType == 5) {Note = 64;}  else Note = elementData.get(idElement).note[0];
  optCC= typeMidiList[(elementData.get(idElement).indexMidiType)];
  Intensity = PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue());
  current_db2 = Intensity;
  raw1 = elementData.get(idElement).indexMidiType*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;
  }
  MIDI_OUT_LED = true;
  }
 
  if (
  elementData.get(idElement).toggleMode_2nd >10 && elementData.get(idElement).toggleMode_2nd <16 && page_selected == true && elementData.get(idElement).indexMidiType_2nd == 2 )
  
  {
 myBus.sendMessage(144+(elementData.get(idElement).indexMidiType_2nd)*16+elementData.get(idElement).midiChannel_2nd-1, 
 elementData.get(idElement).note[1], 
 PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue())      
 );
  wave.setFrequency( frequenza2( PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue())     ));
   if (soundstatus == false)  wave.setAmplitude( 0.5f );
 feedback_counter = 0;
 
 {
  buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;   /// sezione monitoraggio
  Channel = elementData.get(idElement).midiChannel_2nd;
   if ( elementData.get(idElement).indexMidiType_2nd == 5) {Note = 64;}  else Note = elementData.get(idElement).note[1];
  optCC= typeMidiList[(elementData.get(idElement).indexMidiType_2nd)];
  Intensity = PApplet.parseInt(cp5.get(Integer.toString(idElement+1)).getValue());
  current_db2 = Intensity;
  raw1 = elementData.get(idElement).indexMidiType_2nd*16+144+Channel-1;// (int)(message.getStatus() & 0xFF); 
  write_mon = true;
 }
  MIDI_OUT_LED = true;
  }
 
 
  }
};
  
  
  CallbackListener general_open = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
   if ( elementData.get(idElement).toggleMode == 26 )
  {general_send = 0;}
  }
};
  
  
  CallbackListener doppioni = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
   // theEvent.getController().bringToFront();
   // ((ScrollableList)theEvent.getController()).open();
   //------------labeler ();
  if (elementData.get(idElement).dMode.getValue() > 16 && elementData.get(idElement).dMode.getValue() != 0) 
{elementData.get(idElement).toggleMode = (int) elementData.get(idElement).dMode.getValue(); 
elementData.get(idElement).toggleMode_2nd = elementData.get(idElement).toggleMode;    // rendi il MODE uguale nelle due pagine - solo per le modalità speciali - non pot e button

 if (elementData.get(idElement).toggleMode == 17 || elementData.get(idElement).toggleMode == 26) {  elementData.get(idElement).plugga_page();
 } else {  // elementData.get(idElement).plug_page();
  // elementData.get(idElement).labeler(); 
 //elementData.get(idElement).plug_page();
 }
//elementData.get(idElement).midiC.plugTo( this, "midiChannel" ); elementData.get(idElement).midiC.plugTo( this, "midiChannel_2nd" ); 
// elementData.get(idElement).nT.plugTo( this, "note" ); elementData.get(idElement).nT.plugTo( this, "note_2nd" );
 //   elementData.get(idElement).midiTypeOpt.plugTo(this, "indexMidiType" ); elementData.get(idElement).midiTypeOpt.plugTo(this, "indexMidiType_2nd" );

};

    elementData.get(idElement).labeler();
    //  elementData.get(idElement).plug_page();
  }
  
  
  /* if (elementData.get(idElement).toggleMode == 2) 
  {  
    cp5.get(Integer.toString(idElement+1)).
  }
  */
  
 
};




  CallbackListener mp_doppioni = new CallbackListener() { // evita doppioni sulla memoryposition e ricolloca i modificatori su numero libero
  public void controlEvent(CallbackEvent theEvent) {

   // elementData.get(idElement).labeler();
   byte risultato = 0;
   byte numero_inutilizzato =0;
   for (int i=0; i<60; i++) {
     //  
    if (elementData.get(idElement).mp.getValue() != 0 && idElement != i
    && elementData.get(idElement).mp.getValue() == elementData.get(i).memoryPosition 
    //&& elementData.get(i).hide == 0 
    )  
  { 
      for (byte numero=1; numero<61; numero++)  // induviduo un numero non usato e lo carico su numero_inutilizzato;
      {
        risultato=0;
      for (byte quale=0; quale<60; quale++)
      {
        if (numero == elementData.get(quale).memoryPosition ) 
      risultato++; }
      if (risultato ==0) { numero_inutilizzato = numero; break;}
      
      }
      
    
   elementData.get(i).memoryPosition =numero_inutilizzato; 
   elementData.get(i).mp.setValue(numero_inutilizzato); 
    
   // elementData.get(i).memoryPosition=8; 
   // elementData.get(i).mp.setValue(8); 
  //  elementData.get(idElement).memoryPosition =0; 
 // elementData.get(idElement).mp.setValue(0); 
  // elementData.get(idElement).mp.setValueLabel("null");
}
//for (int i=0; i<60; i++) {
 
//}

     
      }
    
  }
};
  
  
  
  
CallbackListener toFront = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    theEvent.getController().bringToFront();
    ((ScrollableList)theEvent.getController()).open();
  }
};
CallbackListener toFrontBox = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    theEvent.getController().bringToFront();
    ((ListBox)theEvent.getController()).open();
  }
};
CallbackListener close = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    ((ScrollableList)theEvent.getController()).close();
  }
};

CallbackListener closenumberbox = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    ((Numberbox)theEvent.getController()).update();
  }
};



CallbackListener closeBox = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    ((ListBox)theEvent.getController()).close();
  }
};


CallbackListener open_scale = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
  //  if (theEvent.getController().getValue() == 2) scale1.show(); else scale1.hide();
 elementData.get(idElement).labeler();
  }
};



CallbackListener leave_item = new CallbackListener() {
  public void controlEvent(CallbackEvent theEvent) {
    if (theEvent.getController().getId() == idElement )
   // println('-');
   // println( theEvent.getController().getId() );
     
     // println(idElement);
     if (elementData.get(idElement).togglestate == false && page_selected == false) 
     {
     elementData.get(idElement).setStateButton(false); 
    // elementData.get(idElement).sendMidiMessageOFF();
    if (elementData.get(idElement).selected == true)
    myBus.sendMessage(144+(elementData.get(idElement).indexMidiType)*16+elementData.get(idElement).midiChannel-1, 
    elementData.get(idElement).note[0],
    0);
    
     wave.setAmplitude( 0.0f );
     }
     
      if (elementData.get(idElement).togglestate_2nd == false && page_selected == true) 
     {
     elementData.get(idElement).setStateButton(false); 
    if (elementData.get(idElement).selected == true)
    myBus.sendMessage(144+(elementData.get(idElement).indexMidiType_2nd)*16+elementData.get(idElement).midiChannel_2nd-1, 
    elementData.get(idElement).note[1],
    0);
    
     wave.setAmplitude( 0.0f );
     }
     
  }
};
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DART_EDITOR" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
