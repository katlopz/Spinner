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

void setup() {
  size(800, 500);
}

void draw() {  

  if (!gameEnd) {
    run();
  } else {
    endScreen();
  }
}

void keyPressed() {
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

void keyReleased() {
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

void mouseClicked() {
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