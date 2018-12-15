void midiMessage(MidiMessage message//,long timestamp, String Busname
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
  buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1; // ho sempre una copia del messaggio vecchio
  Channel = midiSignal- typeMidiArray[indexTypeMidi]+1;
  Note = value_show;
  Intensity = (int)(message.getMessage()[2] & 0xFF);
  raw1 = (int)(message.getStatus() & 0xFF); 
  write_mon = true;
  
  if (raw1 < 160) {
    //saw.freq(frequenza2(Note)); 
  // saw.amp(int(boolean(Intensity)));
   wave.setFrequency( frequenza2(Note) );
    wave.setAmplitude( int(boolean(Intensity)));
  

 
}
    else //  if (raw1 < 176)
  { 
    // saw.freq(frequenza2(Intensity)); 
     wave.setFrequency( frequenza2(Intensity) );
  // saw.amp(0.5); 
    wave.setAmplitude( 0.5 );
feedback_counter = 0; }
   // monitor_flux6 [0] = raw1; monitor_flux6 [1] = Note ; monitor_flux6 [2] = Intensity;
}
} 
 else  // visualizza solo il canale Midi scelto.
 { int cosa = midiSignal- typeMidiArray[indexTypeMidi]+1; 
 if ( channel_filter == cosa) { 
   
   if (value_show == value_filter || value_filter == -1) {
   //  buffer_Channel = byte(Channel); 
     buffer_value = Note; buffer_intensity = Intensity; buffer_raw1 = raw1;
   write_mon = true;
     Channel = cosa;     
   
   Note = value_show;

  //if (indexTypeMidi == 3 || indexTypeMidi == 4) {} else 
  Intensity = (int)(message.getMessage()[2] & 0xFF);
    raw1 = (int)(message.getStatus() & 0xFF);
    
      if (raw1 < 160) {
          wave.setFrequency( frequenza2(Note) );
    wave.setAmplitude(int(boolean(Intensity)) );
        //saw.freq(frequenza2(Note)); 
      // saw.amp(int(boolean(Intensity)));
    } 
    else  //  if (raw1 < 176)
  { // saw.freq(frequenza2(Intensity)); saw.amp(0.5); 
          wave.setFrequency( frequenza2(Intensity) );
    wave.setAmplitude(0.5 );
  
feedback_counter = 0; }
   // monitor_flux6 [0] = raw1; monitor_flux6 [1] = Note ; monitor_flux6 [2] = Intensity;
}
  
  
 }
}

for (byte i =0;i < 60; i++) 
{
 // if (page_selected == false) 
  {
if (elementData.get(i).note[int(page_selected)] == value_show  ) 
if ( elementData.get(i).midiChannel ==  midiSignal- typeMidiArray[indexTypeMidi]+1 && page_selected == false && elementData.get(i).toggleMode > 0 || 
elementData.get(i).midiChannel_2nd ==  midiSignal- typeMidiArray[indexTypeMidi]+1 && page_selected == true && elementData.get(i).toggleMode_2nd > 0
) 
{ 
  //if (elementData.get(i).setExcursionControllMax[int(page_selected)] ==  (int)(message.getMessage()[2] & 0xFF) ) 
//{ elementData.get(i).feedback_ = true ; } else  { elementData.get(i).feedback_ = false ; }
elementData.get(i).feedback_ = true ;
feedback_counter = 0;
}  
  }
}


}
