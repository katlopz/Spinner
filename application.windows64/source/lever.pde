class Lever{
  float x; 
  float y;
  boolean on = false;
  String type = "gold";
  
 Lever(Obstacle o){
   x = o.x;
   y = o.y + (o.Vsize/2);
   if(random(0, 100) > 70) {
     type = "blue";
   }
 }
 
 void redraw(){ 
   if (type.equals("blue")) {
     fill(0, 0, 200);
   }
   else if (type.equals("gold")){
     fill(255, 190, 0);
   }
   
   //line
   stroke(255);
   strokeWeight(4);
   if (!on) {       
     line(x, y, x-20, y+30);  
     //ball
     ellipse(x-20, y+30, 10, 10);
   }
   else {
     line(x, y, x+20, y+30);  
     //ball
     ellipse(x+20, y+30, 10, 10);
   }
   
   //base
   strokeWeight(0);
   ellipse(x, y, 30, 30); 
   
 }
 
 void check(){
  float distX = Math.abs(spinner.x - x); 
  float distY = Math.abs(spinner.y - y); 
  if(distY < spinner.size+50 && spinner.x >= x - 10 && spinner.x <= x + 10 && add > 0.1){
    on = true;
    if (type.equals("blue")){
      spinner.type = "blue";
      timeTilWhite = time + 1000;
    }
    else if(type.equals("gold")){
      score += 20;
    }
  }
 }
 
}