import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class A5 extends PApplet {

Spinner spinner = new Spinner();
ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
ArrayList<Laser> lasers = new ArrayList<Laser>();
Button restart = null;
ArrayList<Lever> levers = new ArrayList<Lever>();

float add = 0; 
float time = 0; 
float subtract = 3;
int score = 0;
int level = 1;
float timeTilWhite = 0;
float nextAdd = 100;

boolean gameEnd = false;
boolean onRestart = false;

boolean moveUp = false; 
boolean moveDown = false;
boolean moveRight = false;
boolean moveLeft = false; 
boolean spin = false;

boolean canUp = true; 
boolean canDown = true;
boolean canRight = true; 
boolean canLeft = true;
boolean canSpin = true;

public void setup() {
  
}

public void draw() {  

  if (!gameEnd) {
    run();
  } else {
    endScreen();
  }
}

public void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP && canUp) {
      moveUp = true;
    } else if (keyCode == DOWN && canDown) {
      moveDown = true;
    } else if (keyCode == RIGHT && canRight) {
      moveRight = true;
    } else if (keyCode == LEFT && canLeft) {
      moveLeft = true;
    }

  }
  
  if (key == ' ' && canSpin) {
      spin = true;
  }
}

public void keyReleased() {
  if (key == CODED) {
    if (keyCode == UP) {
      moveUp = false;
    } else if (keyCode == DOWN) {
      moveDown = false;
    } else if (keyCode == RIGHT) {
      moveRight = false;
    } else if (keyCode == LEFT) {
      moveLeft = false;
    }

  }
  
  if (key == ' ') {
      spin = false;
    }
}

public void mouseClicked() {
  if (gameEnd) {
    if (restart.checkOn()) {
      spinner = new Spinner();
      obstacles = new ArrayList<Obstacle>();
      lasers = new ArrayList<Laser>();
      levers = new ArrayList<Lever>();
      restart = null;

      add = 0; 
      time = 0; 
      subtract = 3;
      score = 0;
      level = 1;
      timeTilWhite = 0;

      gameEnd = false;
      onRestart = false;

      moveUp = false; 
      moveDown = false;
      moveRight = false;
      moveLeft = false; 
      spin = false;

      canUp = true; 
      canDown = true;
      canRight = true; 
      canLeft = true;
      canSpin = true;
    }
  }
}
class Obstacle {
  float Hsize = 50; 
  float Vsize; 
  float x;
  float y; 
  boolean top;
  
  Obstacle() {
    x = 800;
    int Y = PApplet.parseInt(random(0, 10));
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
  
  public void redraw() {
    rectMode(CENTER);
    strokeWeight(0);
    stroke(150); 
    fill(150); 
    rect(x, y, Hsize, Vsize); 
  }
  
  public void check(){
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
  
  public void redraw(){
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
 
 public void redraw(){
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
 public boolean checkOn(){
   if(mouseX > x - Hsize/2 && mouseX < x + Hsize/2 && mouseY > y - Vsize/2 && mouseY < y + Vsize/2){
     return true;
   }
   else{
    return false; 
   }
 }
}
public void endScreen(){
 //end screen
    rectMode(CENTER);
    fill(255);
    stroke(0);
    rect(width/2, height/2, 400, 300);
    fill(0);
    textSize(50);
    text("Score: " + score, 250, height/2-50); 

    //button interactions here
    restart = new Button("Restart", width/2, height/2, 150, 50);
    restart.redraw();

    //extra drawings 
    rectMode(CENTER); 
    strokeWeight(0);
    fill(220, 0, 0); 
    rect(15, height/2, 10, height); 
    fill(255); 
    rect(15, height/2, 3, height); 
}
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
  
  public void redraw() {
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
  
  public void check(){
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
 
 public void redraw(){ 
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
 
 public void check(){
  float distX = Math.abs(spinner.x - x); 
  float distY = Math.abs(spinner.y - y); 
  if(distY < spinner.size+50 && spinner.x >= x - 10 && spinner.x <= x + 10 && add > 0.1f){
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
public void run(){
 background(0);

    canUp = true;
    canDown = true; 
    canRight = true; 
    canLeft = true;
    canSpin = true;
    
    //draws obstacles and lasers
    if (time % nextAdd == 0) { //adds new obstacle
      if(level == 1) {
        obstacles.add(new Obstacle());
        if(obstacles.get(obstacles.size() - 1).top && random(0, 50) > 30){ //adds levers to newest obstacle
          levers.add(new Lever(obstacles.get(obstacles.size() - 1)));
        }
      }
      else {
        int chance = (int) random(0, 10);
        if (chance < 5) { //draws obstacle 
          obstacles.add(new Obstacle());
          if(obstacles.get(obstacles.size() - 1).top && random(0, 50) > 10){ //adds levers to newest obstacle
            levers.add(new Lever(obstacles.get(obstacles.size() - 1)));
          }
        }
        else {
          lasers.add(new Laser());
        } 
      }
    }
    
    
    if(!levers.isEmpty()){ //lever is present
      for(Lever l : levers) {
        l.x = l.x - subtract;
        if(!l.on) {
          l.check();
        }    
        l.redraw();
      } 
      if(levers.get(0).x < 0){
        levers.remove(0);
      }
    }
    
    for (Obstacle o : obstacles) { //draws and decreases x-value of obstacles
      o.redraw();
      o.x = o.x - subtract;
    }  
    if (!obstacles.isEmpty()) {
      if (obstacles.get(0).x <= 0-obstacles.get(0).Hsize) { //removes obstacles off-screen
        obstacles.remove(0);
      }
    }
    
    for (Laser l : lasers) { //draws and decreases x-value of laser
      l.redraw();
      l.x1 = l.x1 - subtract;
      l.x2 = l.x2 - subtract;
    }  
    if (!lasers.isEmpty()) {
      if (lasers.get(0).x1 < 0 && lasers.get(0).x2 < 0) { //removes laser off-screen
        lasers.remove(0);
      }
    }    
    
    //deals with time and level changing
    time++; 
    if (time % 1600 == 0){
      level++;
      if(level < 3) {
        subtract = 3; 
        nextAdd = 100;
      }
      else {
        subtract += 0.5f;
        nextAdd -= 10; 
        if (nextAdd < 30) {
         nextAdd = 30; 
        }
      }
    }

    for (Obstacle o : obstacles) {
      o.check();
    }
    for (Laser l : lasers) {
      l.check();
    }

    //keeping spinner in window 
    if (spinner.x - (spinner.size+20) < 0) {
      spinner.x = 0 + (spinner.size+20);
      canLeft = false;
    }
    if (spinner.x + (spinner.size+20) > width) {
      spinner.x = width - (spinner.size+20);
      canRight = false;
    }
    if (spinner.y - (spinner.size+20) < 30) {
      spinner.y = 30 + (spinner.size+20);
      canUp = false;
    }
    if (spinner.y + (spinner.size+20) > height-30) {
      spinner.y = height-30 - (spinner.size+20);
      canDown = false;
    }
    
    //spin control 
    if(add > 0){
      canSpin = false;
    }

    //spinner movement
    if (moveUp) {
      spinner.y = spinner.y - 5;
      spinner.angle = spinner.angle + 0.05f;
    }
    if (moveDown) {
      spinner.y = spinner.y + 5; 
      spinner.angle = spinner.angle + 0.05f;
    }
    if (moveRight) {
      spinner.x = spinner.x + 5; 
      spinner.angle = spinner.angle + 0.05f;
    }
    if (moveLeft) {
      spinner.x = spinner.x - 5;
      spinner.angle = spinner.angle + 0.05f;
    }
    if (spin) { //begins spin
      add = 0.25f;
      spinner.angle = spinner.angle + add;
    }
    
    //continues momentum
    spinner.angle = spinner.angle + add; 
    if (add > 0) {
      add -= 0.002f;
    } else if (add > 0.25f) {
      add = 0.25f;
    } else {
      add = 0;
    }
   
   
    spinner.redraw(); 

    //end game mechanic
    if (spinner.x - (spinner.size+15) < 20 && spinner.type.equals("white")) {
      gameEnd = true;
    }

    //extra drawings 
    rectMode(CORNER);
    stroke(150);
    fill(150);
    rect(0, 0, width, 30);
    rect(0, height-30, width, height);

    rectMode(CENTER); 
    stroke(220, 0, 0); 
    fill(220, 0, 0); 
    rect(random(14, 16), height/2, 10, height); 
    fill(255); 
    rect(random(14.5f, 15.5f), height/2, 5, height); 

    //bar duration 
    if(timeTilWhite > time) {
     fill(70, 70, 240);
     strokeWeight(0);
     rectMode(CORNERS);
     rect(30, height - 25, (timeTilWhite - time)*0.8f + 30, height-5); 
    }
    
    //score
    fill(255);
    if(time % 100 == 0) {
      score++;
    }
    stroke(255);
    strokeWeight(8);
    textSize(15);
    text("Score: " + score, width - 100, 20); 
    
    //level
    stroke(255);
    strokeWeight(8);
    textSize(15);
    text("Level: " + level, width - 200, 20); 
}
  public void settings() {  size(800, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "A5" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
