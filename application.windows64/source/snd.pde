// INTERFACE CONTROLL BUTTON
ControlP5 control,control2;
Button exit, save, load, sendFirstPage, sendSecondPage, sendOnPageOne, sendOnPageTwo, edit, sound, New, del_;
Textarea hints2;
//Button scale1;
Matrix scale1;
 Numberbox color_, filter_channel, filter_value;

//**************************** 
//*****    the control p5 init
//**************************** 

void init() {
  control = new ControlP5(this);
   control2 = new ControlP5(this);
  cp5 = new ControlP5(this);
  ellipseMode(CENTER);
  f = createFont("typeO.TTF", Betw2*0.4); 
  textFont(f, Betw2*0.4);
cp5.setFont(f, (int)(Betw2*0.6));
  control.setFont(f, int(Betw2*0.7));
  control2.setFont(f, int(Betw2*0.5));
 //  control.setColorBackground(color(90, 90, 90) );
}
// FUNCTION TO ACTIVATE PANEL BUTTON
void mousePressed() {
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
   }
  
  
  
 /* 
 if (sendOnPageOne.isPressed()) {
    myBus.sendMessage(241, 0, 0);
    // myBus.sendMessage(242, 0, 0); 
    delay(160);
    for (int i=0; i<elementData.size(); i++) {
      if (elementData.get(i).getStateDisplay())    {
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).note, elementData.get(i).memoryPosition);
        delay(4);
        if (  elementData.get(i).modifiers == 0) {
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).setExcursionControllMin);
        } else {
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).modifiers);
        }
        delay(4);
        myBus.sendMessage(elementData.get(i).led_+128, int(elementData.get(i).toggleMode), elementData.get(i).addressDMX);
        delay(4);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, int(elementData.get(i).keyBoard),int ( elementData.get(i).indexMidiType ));
        delay(160);
        break;
      }
    } 
    myBus.sendMessage(241, 0, 0);
  }
  */
  
  //*************
  //send selected to second page    // 
  //**************
  if (sendOnPageTwo.isPressed() && i_sender >= 59 ) {    // send THIS
  
//  setUIButtonsPosition();
  
  // SisButton.1007.fill(ControlP5.ORANGE);
     // sendOnPageTwo.update();
 //    sendOnPageTwo.setUpdate(true);
   //   sendOnPageTwo.update();
     //    sendOnPageTwo.setView(new SisButton());
  //  interfaceDisp();
    myBus.sendMessage(241, 0, 0);
    delay(260);
    for (int i=0; i<60; i++) {
      if (elementData.get(i).getStateDisplay()) 
      {
       if (page_selected == false  ){
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).note[0], elementData.get(i).memoryPosition-1);
        delay(delay_send);
       
        if (  elementData.get(i).keyBoard == 0) 
        {
          if (elementData.get(i).toggleMode == 21 || elementData.get(i).toggleMode == 22 || elementData.get(i).toggleMode == 19) 
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).setExcursionControllMin+32); // speed range -32 +32 viene inviata come 0-64
          else myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).setExcursionControllMin);
        } 
        
        else {
          myBus.sendMessage(176+elementData.get(i).midiChannel-1, elementData.get(i).setExcursionControllMax, elementData.get(i).modifiers);
        }
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, int(elementData.get(i).toggleMode), elementData.get(i).addressDMX);
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, int(keyboard_out(elementData.get(i).keyBoard)), int ( elementData.get(i).indexMidiType ));
         delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, int(elementData.get(i).led_), 0);
        delay(80);
        break;
      }
      else
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
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, int(elementData.get(i).toggleMode_2nd), elementData.get(i).addressDMX_2nd);
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, keyboard_out(int(elementData.get(i).keyBoard_2nd)), int ( elementData.get(i).indexMidiType_2nd ));
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, int(elementData.get(i).led_), 0);
        delay(80);
        break;
      }
      
      }
    } 
    myBus.sendMessage(241, 0, 0);
    
  }
  
  
  
}
// TO SET THE BUTTON POSITION
void setUIButtonsPosition () {
  
 // rect(gridCols[2],  gridRow[2], gridCols[15], gridRow[14]  ,7);
   hintbox();
   
  exit = control.addButton("EXIT")
    .setId(1000)
    .setValue(0)
    .setPosition(gridCols[1], gridRow[1])
    .setSize(int(Betw2*4), int(rowBetw*1.8))
    .setView(new SisButton())
    ;
  ;
  sendFirstPage = control.addButton("PAGE 1")
    .setId(1001)
    .setValue(0)
    .setPosition(gridCols[3]+Betw2*0.5, gridRow[1])
    .setSize(int(Betw2*4), int(rowBetw*1.8))
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
    .setSize(int(Betw2*4), int(rowBetw*1.8))
   // .setSize(int(Betw2*6), int(rowBetw*1.8))
    .setView(new SisButton_page())
  .setSwitch(true) 
    ;
  ;
 // sendSecondPage.isSwitch();
  


  save = control.addButton("SAVE")
    .setId(1004)
    .setValue(0)
    .setPosition(gridCols[10], gridRow[1])
    .setSize(int(Betw2*4), int(rowBetw*1.8))
    .setView(new SisButton())
     .setSwitch(false) 
    ;
  ;
  edit = control.addButton("EDIT")
    .setId(1006)
    .setValue(0)
    .setPosition(gridCols[12]+Betw2/2
    , gridRow[1])
    .setSize(int(Betw2*4), int(rowBetw*1.8))
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
    .setSize(int(Betw2*4), int(rowBetw*1.8))
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
    .setPosition(gridCols[8]-Betw2*0.5, gridRow[1])
    .setSize(int(Betw2*4), int(rowBetw*1.8))
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
    .setSize(int(Betw2*6.5), int(rowBetw*1.8))
    .setView(new SisButton())
    .setSwitch(false) 
    ;
  ;
  sendOnPageTwo = control.addButton("Send THIS")
    .setId(1007)
    .setValue(0)
    .setPosition(gridCols[21]-Betw2 *0.5
    , gridRow[1])
    .setSize(int(Betw2*6.5), int(rowBetw*1.8))
    .setView(new SisButton())
      .setSwitch(false) 
    ;
  ;
  
    //strokeWeight(0);
   //  noStroke();
    
 filter_channel = control2.addNumberbox("Filter Channel", (int) (gridCols[19]+Betw2), (int) (gridRow[19]+rowBetw*1.5
 ), (int)(Betw2*3), (int)(rowBetw*1.5)) //   
      .plugTo( this, "channel_filter" )
      .setId(2015)
      .setRange(0, 16)
  
       .setColorCaptionLabel(color(160))
   
     .setColorBackground(color(80)) 
   
      .setLabel("Channel f.")
      

     .setVisible(true);
    ;
    
    makeEditable (filter_channel);
    
    
    
     filter_value = control2.addNumberbox("Filter Value", (int) (gridCols[19]+Betw2), (int) (gridRow[21]+rowBetw*1.5
     ), (int)(Betw2*3), (int)(rowBetw*1.5)) //   
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


void edit_activate() {
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
       // elementData.get(i).led.setVisible(false);
   //     if (elementData.get(i).toggleMode == 26) {      // general mode 
    //      Button t = (Button)cp5.get(Integer.toString(i+1));
     //      t.hide();}
     
    }
  }
}

void edit_deactivate() {
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

  void makeEditable( Numberbox n ) {
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
  
         void set_scale_view()
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
        if (elementData.get(idElement).midiTypeOpt.getValue() != 0 || elementData.get(idElement).dmx.getValue() != 2)  {scale1.hide(); 
        
         elementData.get(idElement).nT.show();
      }
        }
        
        
        
        
        
        
        
        void sender ()
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
       if (elementData.get(i).hide == 0)  myBus.sendMessage(176+elementData.get(i).midiChannel-1, int(elementData.get(i).toggleMode), elementData.get(i).addressDMX);
       else myBus.sendMessage(176+elementData.get(i).midiChannel-1, 0, elementData.get(i).addressDMX);
       
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, int(keyboard_out(elementData.get(i).keyBoard)), int ( elementData.get(i).indexMidiType ));
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel-1, int(elementData.get(i).led_), 0 );
        delay(80);
      //  break;
      }
     // else
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
        if (elementData.get(i).hide == 0)  myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, int(elementData.get(i).toggleMode_2nd), elementData.get(i).addressDMX_2nd);
        else myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, 0, elementData.get(i).addressDMX_2nd);
        
        delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, int(keyboard_out(elementData.get(i).keyBoard_2nd)), int ( elementData.get(i).indexMidiType_2nd ));
          delay(delay_send);
        myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, int(elementData.get(i).led_), 0 );
        delay(80);
     //   break;
      }
      
      }



  
    } 
    
    
   
    
  stroke(150);
  fill(0, 200, 0);

 rect(gridCols[17],  gridRow[0]+rowBetw, gridCols[1]+(Betw2/5)+(i_sender*2), gridRow[1] 
 ,4);
        
        }
