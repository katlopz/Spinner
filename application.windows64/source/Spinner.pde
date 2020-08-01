class Spinner{
  
  float angle = 0; 
  float x; 
  float y;
  float size = 20;
  String type = "white";
  
  Spinner(){
    x = 100; 
    y = height/2;
  }
  
  void redraw(){
    if (time > timeTilWhite) {
     type = "white"; 
    }
    
    if (type.equals("blue")){
      noFill();
      stroke(100, 100, 240);
      strokeWeight(4);
      float randDiam = random(spinner.size + 80, spinner.size + 85);
      ellipse(x, y, randDiam, randDiam ); 
    }
    
    pushMatrix();
    translate(x, y);
    
    if(type.equals("white")){
      fill(255);
      //fill(random(0,255), random(0,255), random(0,255));
    }
    else if (type.equals("blue")){
      fill(0, 0, 200);
    }
    stroke(0);
    
    //draws other disks
    pushMatrix();
    rotate(angle);
    for(int i = 0; i<3; i++) {
      strokeWeight(2); 
      rectMode(CENTER);
      rect(0, 0+15, size-4, size+6); //joint to middle
      strokeWeight(2);
      ellipse(0, 0+30, size+6, size+6); //border
      strokeWeight(5);
      ellipse(0, 0+30, size-8, size-8); //middle ring
      rotate((PI*2)/3);
    }
    popMatrix(); 
    
    //draws middle
    strokeWeight(2); 
    stroke(0);
    ellipse(0, 0, size, size);
    
    popMatrix();
  }
  
  //add a spin method?
}