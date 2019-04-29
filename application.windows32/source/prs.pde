void firstPosition() {
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
void setupElement () {
  firstPosition();
}
