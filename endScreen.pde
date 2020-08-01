void endScreen(){
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