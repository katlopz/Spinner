class Button{
 String text; 
 float x;
 float y; 
 float Hsize; 
 float Vsize;
 
 Button(String txt, float X, float Y, float HS, float VS){
  text = txt;  
  x = X;
  y = Y;
  Hsize = HS;
  Vsize = VS;
 }
 
 void redraw(){
   rectMode(CENTER);
   if(checkOn()){
    fill(100); 
   }
   else{
    fill(255); 
   }
   stroke(0);
   strokeWeight(6);
   rect(x, y, Hsize, Vsize);
   textSize(30);
   fill(0); 
   float xStart = x - ((text.length() * 15) /2 );
   text("Restart", xStart, y + 10);
   
 }
 boolean checkOn(){
   if(mouseX > x - Hsize/2 && mouseX < x + Hsize/2 && mouseY > y - Vsize/2 && mouseY < y + Vsize/2){
     return true;
   }
   else{
    return false; 
   }
 }
}