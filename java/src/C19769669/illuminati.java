package C19769669;

import processing.core.PVector;
public class illuminati extends Andyvisual {
int w = 800;
int h = 800;
float x = 0;
PVector circlemove;



public void settings()
{
    size(800, 800, P3D);
    println("CWD: " + System.getProperty("user.dir"));
}

public void setup(){
    colorMode(HSB);
	background(0);
    startMinim();
    loadAudio("Beating Up Andreas2.mp3");
    circle = new PVector[total+1][total+1];
    circlemove = new PVector(100,100,0);
}

public void keyPressed()
{
    getAudioPlayer().cue(0);
    getAudioPlayer().play();
}

public void draw()
{
    translate(250,250);
    drawcircle();
    drawTriangle();;
    
}
PVector[][] circle;
int total = 20;
public void drawcircle(){
    pushMatrix();
    background(0);
    stroke(255);
    lights();
    
    float r = 50;
    rotateX(50 * getSmoothedAmplitude());
    for (int i = 0; i < total+1; i++) {
      float vertical = map(i, 0, total, 0, PI);
      for (int j = 0; j < total+1; j++) {
        float horizontal = map(j, 0, total, 0, TWO_PI);
        x = r * sin(vertical) * cos(horizontal);
        float y = r * sin(vertical) * sin(horizontal);
        float z = r * cos(vertical);
        circle[i][j] = new PVector(x, y, z);
      }
    }
    for (int i = 0; i < total+1; i++) {
        for (int j = 0; j < total+1; j++) {
          circle[i][j].add(circlemove);
          
        }
      }
      rotateX(circlemove.x += (500 * getSmoothedAmplitude()));
      circlemove.y += (500 * getSmoothedAmplitude());
    for (int i = 0; i < total; i++) {
     // float rainbow = map(i, 0, total, 0, 255*6);
      noFill();
      beginShape(TRIANGLE_STRIP);
      for (int j = 0; j < total+1; j++) {
        PVector v1 = circle[i][j];
        vertex(v1.x, v1.y, v1.z);
        PVector v2 = circle[i+1][j];
        vertex(v2.x, v2.y, v2.z);
      } 
      
      endShape();
    }
    popMatrix();
    }

  public void drawTriangle(){
    pushMatrix();
    background(0);
    stroke(255);
    lights();

    beginShape();
    vertex(-100, -100, -100);
    vertex( 100, -100, -100);
    vertex(   0,    0,  100);

    vertex( 100, -100, -100);
    vertex( 100,  100, -100);
    vertex(   0,    0,  100);

    vertex( 100, 100, -100);
    vertex(-100, 100, -100);
    vertex(   0,   0,  100);

    vertex(-100,  100, -100);
    vertex(-100, -100, -100);
    vertex(   0,    0,  100);
    endShape();
    popMatrix();
  }

 }

