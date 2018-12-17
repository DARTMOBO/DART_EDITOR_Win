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
  int indexMidiType;
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
  int radiusW;
  int radiusH;
  // dimension of the numberbox
  int boxX=60;
  int boxY=24;
  boolean feedback_;

  /////////// instances of default class
  ControlP5 sett;
  //****************************** the button data

  boolean stateButton;

  //int [] typeMidiArray={144, 160, 176, 192, 208, 224};
  //******************************  
  //  for menus
  //****************************** 
  Numberbox mp, nT, min, max, midiC, dmx, led;
  ScrollableList dMode, midiTypeOpt,
 // dKeys, 
  dModif;
  
  ListBox dKeys;
  // TextField labell;
  Textfield labell, label_hint;
//  Textarea hintarea;
 // String labelC;



      
 
  
  
  void settingsBox() {
       // textFont(f, 20);
       
        //   min = sett.addNumberbox("setExcursionControllMin",  (int) gridCols[17]+Betw2, (int)gridRow[6]+rowBetw, boxX, boxY)
        label_hint =  sett.addTextfield("label_hint")
     .setPosition((int) (gridCols[20]+Betw2*0.8),(int)gridRow[12]+rowBetw)
   //  .setSize(Betw2*6,30)
     .setSize( Betw2*6, (int)(rowBetw*1.5))
     
     .plugTo(this, "label_hint" )
      .setFont(createFont("typeO.TTF", (Betw2/1.7)))
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
     .setPosition( (int) (gridCols[20]+Betw2*0.8), (int)gridRow[14]+rowBetw  // gridRow[10]+rowBetw
     ) 
      .setSize( Betw2*6, (int)(rowBetw*1.5)) 
     .plugTo(this, "label" )
      .setFont(createFont("typeO.TTF", (Betw2/1.7)))   
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
                    

    
    midiTypeOpt = sett.addScrollableList("midiTypeOpt")
      .plugTo(this, "indexMidiType" )
  .setPosition( (int)gridCols[20]+Betw2*0.8, gridRow[6]+rowBetw)
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
      
    nT = sett.addNumberbox("note",  (int) gridCols[17]+Betw2 , (int)gridRow[4]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5))
      .plugTo( this, "note" )
      .setId(2002)
      .setRange(0, 127)
     //  .onEnter(nT.setColorCaptionLabel(color(160)))
      .onLeave(closenumberbox)
      .setLabel("Value")
       .setColorCaptionLabel(color(160))
       // .setVisible(false);
        //.setHide();
      ;
    //  nT.hide();
     if (toggleMode == 17)  nT.plugTo( this, "note_2nd" );
     
    min = sett.addNumberbox("setExcursionControllMin",  (int) gridCols[17]+Betw2, (int)gridRow[6]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5))
      .plugTo( this, "setExcursionControllMin" )
      .setId(2003)
    //  .setColorBackground(ControlP5.BLACK)
      .setLabel("Minimum")
       .setColorCaptionLabel(color(160))
      // .setColorValueLabel(150) 
      .setRange(-127, 127)
      .onLeave(closenumberbox)
      ;
      
    max = sett.addNumberbox("setExcursionControllMax", (int) gridCols[17]+Betw2, (int)gridRow[8]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5))
      .plugTo( this, "setExcursionControllMax" )
      .setId(2004)
     // .setColorBackground(ControlP5.BLACK)
      .setValue(127)
      .setLabel("Maximum")
       .setColorCaptionLabel(color(160))
      .setRange(0, 127)
      .onLeave(closenumberbox)
      ;   
    midiC = sett.addNumberbox("midiChannel", (int) gridCols[17]+Betw2, (int)gridRow[10]+rowBetw,(int)(Betw2*3), (int)(rowBetw*1.5))
      .plugTo( this, "midiChannel" )
      .setId(2005)
     // .setColorBackground(ControlP5.BLACK)
      .setLabel("Midi Channel")
       .setColorCaptionLabel(color(160))
      .setRange(1, 16)
      .onLeave(closenumberbox)
      ;
    dmx = sett.addNumberbox("addressDMX", (int) gridCols[17]+Betw2, (int)gridRow[12]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5))
      .plugTo( this, "addressDMX" )
      .setId(2006)
     // .setColorBackground(ControlP5.BLACK)
      .setLabel("DMX Channel")
       .setColorCaptionLabel(color(160))
      .setRange(0, 127)
      .addCallback(open_scale) 
      .onLeave(closenumberbox)
      ;
      
      
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
    dMode =  sett.addScrollableList("toggleMode")
      
       .setPosition((int)gridCols[20]+Betw2*0.8, gridRow[4]+rowBetw)
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
      .setPosition( (int)gridCols[20]+Betw2*0.8, gridRow[8]+rowBetw)
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
     // .setLabel("Keys");
     .setLabel(keyboardList[keyBoard]);
     dKeys.getValueLabel().toUpperCase(false);
     
    dModif = sett.addScrollableList("Modifiers")
      .setPosition( (int)gridCols[20]+Betw2*0.8, gridRow[10]+rowBetw)
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
    mp = sett.addNumberbox("memorySlot",  (int) gridCols[17]+Betw2  , (int) gridRow[16]+rowBetw,  (int)(Betw2*3), (int)(rowBetw*1.5)) //  
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
   led = sett.addNumberbox("LED-OUT", (int) gridCols[17]+Betw2  , (int) gridRow[14]+rowBetw, (int)(Betw2*3), (int)(rowBetw*1.5)) //   
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
    posX = int(posx_);
    posY = int(posy_);
    radiusW = rW;
    radiusH = rH;

       sett = new ControlP5(applet);
    sett.setFont(f, (int)(Betw2*0.6));
    sett.setColorBackground(color(90, 90, 90) );
    
    
   
// if (immagine_circuito == false)
    switch(setView_) {
    case 0:
      cp5_.addButton(label)
        .setId(id_)
        .setPosition(posX, posY)
        .setSize(radiusW, radiusH)
        .setView(new RectButton())
        ;
      break;
    case 1:
      cp5_.addButton(label)
        .setId(id_)
        .setPosition(posX, posY)
        .setSize(radiusW, radiusH)
        .setView(new Marrone())
        .bringToFront()
        ;
      break;
    case 2:
      cp5_.addButton(label)
        .setId(id_)
       // .setSwitch(true)
        .setPosition(posX, posY)
        .setSize(radiusW, radiusH)
        .setView(new Grigio())
        .bringToFront()
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
    
     settingsBox();
      memoryPosition=mem_pos;
  }
  
  
  boolean getStateButton () {
    return stateButton;
  }
  void setStateButton (boolean b) {
    stateButton = b;
  }
  ////////////// THE BOX SETTING DESIGN AND TOGGLE///////////////////////

  void setDisplay (boolean b) {
    sett.setVisible(b); // to set visibility of box settings on
    if (b) {sett.show(); }else sett.hide();
  }
  boolean getStateDisplay() {
    //println("sett is visible: " +  sett.isVisible());
    return sett.isVisible();
  }

 

  // THE LISTENER EVENT TO CAPTURE THE TOGGLE EVENT //

  void note(int v) {
    note[0] = v;
    //    println("Value of the Note: "+ v);
  }
    void note_2nd(int v) {
    note[1] = v;
    //    println("Value of the Note: "+ v);
  }
  
  void setExcursionControllMin(int v) {
    setExcursionControllMin = v;
    // println(" Minimum Value: "+ v);
  }
  
  void setExcursionControllMax(int v) {
    setExcursionControllMax= v;
    //println(" Maximum Value: "+ v);
  }
  void midiChannel(int v) {
    midiChannel = v;
    //println("Value of MIDI Channel: "+v);
  }
  void addressDMX(int v) {
    addressDMX = v;
    //println("Value of DMX Address: "+v);
  }
  void toggleMode(int f) {  
   // println("toggle: " + f);
    toggleMode = f;
  }
  void labell(String f) {  
  //  println("toggle: " + f);
    label = f;
    labell.setText(elementData.get(idElement).label);
  labell.setFocus(false);
  }
  void label_hint (String f) {  
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
  
  void modifiersBox(int f) {  
    //println("toggle", f);
    modifiers = f;
    //println(f);
  }
  void togglePage(boolean f) {
    //println("togglePage", f);
    //  togglePage = f;
  }
   void led(int f) {
    //println("togglePage", f);
    led_= f;
    //  togglePage = f;
  }
  
  
  void memoryPosition(int v) {
     memoryPosition = v;
     mem = memoryPosition;
   //  mp.setValue(memoryPosition);
    //   println("Value of Memory Position: "+v);
  }
  void displaySettingsUI() {

      fill(155);
    textSize(Betw2/1.6);
    // textSize(Betw2/1.7);
    text("MODIFIER "+label+" SETTiNGS", gridCols[17]+ Betw2*0.5
    , gridRow[3]+rowBetw*1.2); // box settings  label
  }
  
  
  //TO EDIT THE NUMBER OF SETTINGS IN NUMBERBOX
 /*  makeEditable( nT );
     
     
    makeEditable( min ); 
    makeEditable( max ); 
    makeEditable( midiC ); 
    makeEditable( dmx );*/
  void plug_page () 
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
  
  void plugga_page() {  // linka un modificatore sulla prima e seconda pagina
  midiC.plugTo( this, "midiChannel" ); midiC.plugTo( this, "midiChannel_2nd" ); 
nT.plugTo( this, "note" ); nT.plugTo( this, "note_2nd" );
min.plugTo( this, "setExcursionControllMin" ); min.plugTo( this, "setExcursionControllMin_2nd" );
max.plugTo( this, "setExcursionControllMax" ); max.plugTo( this, "setExcursionControllMax_2nd" );
midiC.plugTo( this, "midiChannel" ); midiC.plugTo( this, "midiChannel_2nd" );
dmx.plugTo( this, "addressDMX" ); dmx.plugTo( this, "addressDMX_2nd" );
midiTypeOpt.plugTo(this, "indexMidiType" ); midiTypeOpt.plugTo(this, "indexMidiType_2nd" );
dKeys.plugTo( this, "keyBoard" ); dKeys.plugTo( this, "keyBoard_2nd" );
  }
  
  
  
  void labeler () {
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
            scale1.hide();
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
            scale1.hide();
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
            scale1.hide();
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
            scale1.hide();
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
         scale1.hide();
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
         scale1.hide();
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
            scale1.hide();
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
            led.hide(); 
                          dmx.show(); 
            midiC.hide(); 
            midiTypeOpt.hide();
            scale1.hide();
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
            scale1.hide();
              dMode.show();
         
                 }
 
 

 
 }
 


  
  
  
  void sendMidiMessageON() {
   /* if ( noteCC == true) {
      myBus.sendMessage(0xB0, midiChannel-1, note, 127);
     // myBus.sendMessage(176+elementData.get(i).midiChannel_2nd-1, elementData.get(i).note_2nd, elementData.get(i).memoryPosition+64);
    } else if ( noteCC == false) {
      myBus.sendMessage(0x90, midiChannel-1, note, 127);
    }*/
    if (infoGraph < 65)
    if (page_selected == false && elementData.get(idElement).toggleMode <17 && elementData.get(idElement).toggleMode >0) 
  {  myBus.sendMessage(144+(elementData.get(idElement).indexMidiType)*16+elementData.get(idElement).midiChannel-1, elementData.get(idElement).note[0], 127);
 
 // saw.freq(frequenza2(elementData.get(idElement).note[0]));
 //  saw.amp(0.5);
  wave.setFrequency( frequenza2(elementData.get(idElement).note[0]) );
  wave.setAmplitude( 0.5 );
}
    
    if (page_selected == true && elementData.get(idElement).toggleMode_2nd <17 && elementData.get(idElement).toggleMode_2nd >0) 
   { myBus.sendMessage(144+(elementData.get(idElement).indexMidiType_2nd)*16+elementData.get(idElement).midiChannel_2nd-1, elementData.get(idElement).note[1], 127);
  
    // saw.freq(frequenza2(elementData.get(idElement).note[0]));
    //  saw.amp(0.5);
    
      wave.setFrequency( frequenza2(elementData.get(idElement).note[0]) );
  wave.setAmplitude( 0.5 );

 }
  }
  void sendMidiMessageOFF() {
  /*  if ( noteCC == true) {
      myBus.sendMessage(0xB0, midiChannel-1, note, 0);
    } else if ( noteCC == false) {
      myBus.sendMessage(0x90, midiChannel-1, note, 0);
    }*/
    if (infoGraph < 65)
     if (page_selected == false && elementData.get(idElement).toggleMode <17 && elementData.get(idElement).toggleMode >0) 
    { myBus.sendMessage(144+(elementData.get(idElement).indexMidiType)*16+elementData.get(idElement).midiChannel-1, elementData.get(idElement).note[0], 0);
  // saw.amp(0.0);
  wave.setAmplitude( 0.0 );
}
     
     if (page_selected == true && elementData.get(idElement).toggleMode_2nd <17  && elementData.get(idElement).toggleMode_2nd >0) 
   {  myBus.sendMessage(144+(elementData.get(idElement).indexMidiType_2nd)*16+elementData.get(idElement).midiChannel_2nd-1, elementData.get(idElement).note[1], 0);
   // saw.amp(0.0);
     wave.setAmplitude( 0.0 );
 }
  }
  
  
  void loadTableRow(TableRow t) {
    
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
    
    posX = int ( (width/ ex_dimensionX   )* t.getInt("posX"));
    posY = int ( ( height / ex_dimensionY )* t.getInt("posY"));   
    shape = t.getInt("shape");
    radiusW = int (( width/ ex_dimensionX   )*t.getInt("dimensionX"));
    radiusH = int (( height / ex_dimensionY )*t.getInt("dimensionY"));
  
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
    togglePage = boolean(t.getInt("Page"));
    
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
