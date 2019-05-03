class SisButton implements ControllerView<Button> { 
  public void display(PGraphics theApplet, Button theButton) {
    theApplet.pushMatrix();
    theApplet.textSize(Betw2/1.7);
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
    theApplet.textSize(Betw2/1.7);
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
    int y = theButton.getHeight()/2 - int(theButton.getCaptionLabel().getHeight()/1.8);
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
    theApplet.textSize(Betw2/1.7);
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
    theApplet.textSize(Betw2/1.7);
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
    theApplet.textSize(Betw2/1.7);
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
  +theButton.getWidth()*0.2//-(theButton.getWidth()/127)
  , theButton.getHeight(), 
  //theButton.getWidth()-
  (theButton.getValue()*theButton.getWidth()/210)+theButton.getWidth()*0.2, 
  theButton.getHeight());
  
  
  theApplet.strokeWeight(3);
  theApplet.stroke(150, 150, 150);
  
    // center the caption label 
    theApplet.textSize(Betw2/1.7);
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
    theApplet.textSize(Betw2/1.7);
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
    theApplet.textSize(Betw2/1.7);
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
    theApplet.rect(-theButton.getWidth()*0.24, theButton.getHeight()*0.22, theButton.getWidth()*1.5, theButton.getHeight()*0.6,7);
    
     noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 255, 255);
//   line(theButton.getWidth()*0.1, theButton.getHeight()*1, theButton.getWidth()*0.1, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
 
  line(0
  +theButton.getWidth()*0.2//-(theButton.getWidth()/127)
  , theButton.getHeight()*0.22, 
  (theButton.getValue()*theButton.getWidth()/210)+theButton.getWidth()*0.2, 
  theButton.getHeight()*0.22);
  
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
     
    
    rect( -theButton.getWidth()*0.8, theButton.getHeight()*0.35, theButton.getWidth()*2.6 , theButton.getHeight()*0.3 ,7);
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
          
    
          
    int x = int(theButton.getValue()*theButton.getWidth()/127   - theButton.getCaptionLabel().getWidth()/2);
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
  
    rect( theButton.getWidth()*0.35, -theButton.getHeight()*0.8, theButton.getWidth()*0.3 , theButton.getHeight()*2.6 ,7);
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
    int y = int(
    
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
    rect(theButton.getWidth()*0.1, -theButton.getHeight()*0.2, theButton.getWidth()*0.8, theButton.getHeight()*1.5,7);
    theApplet.strokeWeight(3);
    //stroke(255, 0, 0);
    //strokeWeight(2);
    //line(0, theButton.getHeight()/2, theButton.getWidth()/5, theButton.getHeight()/2);
    //line(theButton.getWidth()*4/5, theButton.getHeight()/2, theButton.getWidth(), theButton.getHeight()/2);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(theButton.getWidth()*0.1, -theButton.getHeight()*0.2, theButton.getWidth()*0.8, theButton.getHeight()*1.5,7);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
        theApplet.rect(theButton.getWidth()*0.1, -theButton.getHeight()*0.2, theButton.getWidth()*0.8, theButton.getHeight()*1.5,7);
      }
    } else { // the mouse is located outside the button area
      theApplet.fill(180, 150, 255);
    }
         
                    noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 255, 255);
  line(theButton.getWidth()*0.1, theButton.getHeight()*1, theButton.getWidth()*0.1, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
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
  rotate(frameCount / 100.0);
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
  rotate(frameCount / 100.0);
  polygon(0, 0, theButton.getHeight(), 5);  // Triangle
  popMatrix();
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
       // theApplet.rect(-theButton.getHeight()*0.2, theButton.getWidth()*0.2, theButton.getHeight()*1.5, theButton.getWidth()*0.6,7);
       pushMatrix();
  translate(theButton.getHeight()/2, theButton.getHeight()/2);
  //color(255,0,0);
  rotate(frameCount / 100.0);
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
    rect(theButton.getWidth()*0.1, -theButton.getHeight()*0.2, theButton.getWidth()*0.8, theButton.getHeight()*1.5,7);
    theApplet.strokeWeight(3);
    //stroke(255, 0, 0);
    //strokeWeight(2);
    //line(0, theButton.getHeight()/2, theButton.getWidth()/5, theButton.getHeight()/2);
    //line(theButton.getWidth()*4/5, theButton.getHeight()/2, theButton.getWidth(), theButton.getHeight()/2);
    if (theButton.isInside()) {
      if (theButton.isMousePressed()) { // button is pressed
        theApplet.fill(ControlP5.RED);
        theApplet.rect(theButton.getWidth()*0.1, -theButton.getHeight()*0.2, theButton.getWidth()*0.8, theButton.getHeight()*1.5,7);
        move_element ();
      } else { // mouse hovers the button
        theApplet.fill(ControlP5.YELLOW);
        theApplet.rect(theButton.getWidth()*0.1, -theButton.getHeight()*0.2, theButton.getWidth()*0.8, theButton.getHeight()*1.5,7);
      }
    } else { // the mouse is located outside the button area
      theApplet.fill(180, 150, 255);
    }
         
          
          noFill();
  theApplet.strokeWeight(5);
  theApplet.stroke(255, 128, 0);
  line(theButton.getWidth()*0.1, theButton.getHeight()*1, theButton.getWidth()*0.1, theButton.getHeight()*1-(theButton.getValue()*theButton.getHeight()/127));
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
  +theButton.getWidth()*0.2//-(theButton.getWidth()/127)
  , theButton.getHeight(), 
  //theButton.getWidth()-
  (theButton.getValue()*theButton.getWidth()/210)+theButton.getWidth()*0.2, 
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
  
  rotate(frameCount / 100.0);
  polygon(0, 0, theButton.getWidth()*1.5, 5);  // Triangle
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
      n.setValue( int( text ) );
      text = "";
    } else {
      n.getValueLabel().setText(""+n.getValue());
    }
  }
}

void move_element () 
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

void polygon(float x, float y, float radius, int npoints) {
  float angle = TWO_PI / npoints;
  beginShape();
  for (float a = 0; a < TWO_PI; a += angle) {
    float sx = x + cos(a) * radius;
    float sy = y + sin(a) * radius;
    vertex(sx, sy);
  }
  endShape(CLOSE);
}
