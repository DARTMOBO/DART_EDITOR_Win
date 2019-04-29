 Table table;
// *****************************************
// init TABLE to set data // 
//******************************************
void initTableOfElementData() {
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
void saveTableSettings(String s ) {
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
    r.setInt("Page", int (elementData.get(i).togglePage));
   
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












void loadTableSettings(String s) {
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







void fileToSave(File selection) {
  if (selection == null) {
  //  println("Window was closed or the user hit cancel.");
  } else {
   // println("User selected " + selection.getAbsolutePath());
    saveTableSettings(selection.getAbsolutePath());
  }
}
void fileToLoad(File selection) {
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
String[] listFileNames(String dir) {
  File file = new File(dir);
  if (file.isDirectory()) {
    String names[] = file.list();
    return names;
  } else {
    // If it's not a directory
    return null;
  }
}
