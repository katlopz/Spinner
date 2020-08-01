void run(){
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
        subtract += 0.5;
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
      spinner.angle = spinner.angle + 0.05;
    }
    if (moveDown) {
      spinner.y = spinner.y + 5; 
      spinner.angle = spinner.angle + 0.05;
    }
    if (moveRight) {
      spinner.x = spinner.x + 5; 
      spinner.angle = spinner.angle + 0.05;
    }
    if (moveLeft) {
      spinner.x = spinner.x - 5;
      spinner.angle = spinner.angle + 0.05;
    }
    if (spin) { //begins spin
      add = 0.25;
      spinner.angle = spinner.angle + add;
    }
    
    //continues momentum
    spinner.angle = spinner.angle + add; 
    if (add > 0) {
      add -= 0.002;
    } else if (add > 0.25) {
      add = 0.25;
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
    rect(random(14.5, 15.5), height/2, 5, height); 

    //bar duration 
    if(timeTilWhite > time) {
     fill(70, 70, 240);
     strokeWeight(0);
     rectMode(CORNERS);
     rect(30, height - 25, (timeTilWhite - time)*0.8 + 30, height-5); 
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