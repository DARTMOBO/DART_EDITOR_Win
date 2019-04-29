// THE MIDI VARIABLES
MidiBus myBus; // The MidiBus
int Channel, Note, Intensity;
String optCC;
int DartIN, DartOUT;
int inD, outD;


  

// THE MIDI INIT SETTINGS //
void initMidi() {

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


void addMidiListener(MidiListener midiMessage) {
// write_mon = true;
}






void delay(int time) {
  int current = millis();
  while (millis () < current+time) Thread.yield();
}

///////////////////////-------------------------------------------------------------------
