class Laser {
  float x1; 
  float y1; 
  float x2;
  float y2; 
  
  Laser() {
    int chance = (int) random(0, 30);
    if(chance < 10) { //vertical laser 
      x1 = width; 
      x2 = width; 
      
      y1 = random(30, height/2);
      if (y1 > 30 + spinner.size*2 + 50) { 
        y2 = random( (height/2) + 50, height-30 - 10); 
      } 
      else {
        y2 = random( (height/2) + 50, height-30 - (30 + spinner.size*2 + 50));
      }
    }
    else if(chance >= 10 && chance < 20){ //horizontal laser  
      x1 = width; 
      x2 = width + (random(150, 180));
      
      float randY = random(30 + (30 + spinner.size*2 + 50), height-30 - (30 + spinner.size*2 + 50));
      y1 = randY; 
      y2 = randY;     
    }
    else if(chance >= 20){ //diagonal laser
      if(chance > 25){ //top left -> bottom right
        x1 = width; 
        y1 = height/2 - 50; 
        x2 = width + 175;
        y2 = height/2 + 50;
      }
      else {
        x1 = width; 
        y1 = height/2 + 50; 
        x2 = width + 175;
        y2 = height/2 - 50;
      }
    }
  }
  
  void redraw() {
    stroke(220, 0, 0); 
    strokeWeight(10);
    line(x1, y1, x2, y2);
    stroke(255); 
    strokeWeight(5);
    line(x1, y1, x2, y2);
    
    rectMode(CENTER); 
    fill(100);
    ellipse(x1, y1, 12, 12);
    ellipse(x2, y2, 12, 12);
  }
  
  void check(){
    if(spinner.type.equals("blue")){
     fill(0); 
     strokeWeight(0);
     ellipse(spinner.x, spinner.y, spinner.size + 80, spinner.size + 80);
     return; 
    }
    
    float s = ((x2 - x1)*(spinner.x - x1) + (y2 - y1)*(spinner.y - y1)) / 
               ( (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) );
    float distX;
    float distY; 
    if (s >= 0 && s <= 1){ //checks if close to laser line
      float Px = ((1-s)*x1) + (s*x2);
      float Py = ((1-s)*y1) + (s*y2);
      distX = Math.abs(Px - spinner.x);
      distY = Math.abs(Py - spinner.y); 
      if (distX < spinner.size+20 && distY < spinner.size+20){
          gameEnd = true; 
        }
    }
    else { //checks if close to end points
      distX = Math.abs(x1 - spinner.x);
      distY = Math.abs(y1 - spinner.y);
      if (distX < spinner.size+20 && distY < spinner.size+20){
       gameEnd = true; 
      }
      else {
        distX = Math.abs(x2 - spinner.x);
        distY = Math.abs(y2 - spinner.y);
        if (distX < spinner.size+20 && distY < spinner.size+20){
          gameEnd = true; 
        }
      }
    }
    
    
  }
  
}