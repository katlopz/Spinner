class Obstacle {
  float Hsize = 50; 
  float Vsize; 
  float x;
  float y; 
  boolean top;
  
  Obstacle() {
    x = 800;
    int Y = int(random(0, 10));
    Vsize = random(250, 350);
    if (Y > 5) {
      y = Vsize/2;
      top = true;
    }
    else {
      y = 500 - (Vsize/2); 
      top = false;
    }
  }
  
  void redraw() {
    rectMode(CENTER);
    strokeWeight(0);
    stroke(150); 
    fill(150); 
    rect(x, y, Hsize, Vsize); 
  }
  
  void check(){
      float distx = Math.abs(spinner.x - x);
      float disty = Math.abs(spinner.y - y);

      if (spinner.x < x && distx < (Hsize/2)+(spinner.size+20)) { //before obstacle
        if (disty < (Vsize/2)+(spinner.size+20)) {
          spinner.x = x-(Hsize/2)-(spinner.size+20);
          canRight = false;
        }
      } else if (spinner.x > x && distx < (Hsize/2)+(spinner.size+20)) { //after obstacle
        if (disty < (Vsize/2)+(spinner.size+20)) {
          spinner.x = x+(Hsize/2)+(spinner.size+20);
          canLeft = false;
        }
      } 

      if (spinner.y < x && disty < (Vsize/2)+(spinner.size+20)) { //over obstacle
        if (distx < (Hsize/2)+(spinner.size+20)) {
          //canDown = false;
        }
      } else if (spinner.y > x && disty < (Vsize/2)+(spinner.size+20)) { //under obstacle
        if (distx < (Hsize/2)+(spinner.size+20)) {
          //canUp = false;
        }
      }
  }
}