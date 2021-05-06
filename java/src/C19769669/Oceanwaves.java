package C19769669;

import processing.core.PVector;
public class Oceanwaves extends Andyvisual {

int rows;
int cols;
int scale = 20;
int w = 1800;
int h = 800;
int which = 0;
float x = 0;
PVector sunmove;

float waveSpeed = 0;
float [][] waves;
float sunSpeed = 0;


public void settings()
{
    size(800, 800, P3D);
    println("CWD: " + System.getProperty("user.dir"));
    fullScreen(P3D, SPAN);

}

public void setup(){
    colorMode(HSB);
	background(0);
    startMinim();
    //startListening();
    loadAudio("Beating Up Andreas2.mp3");
    cols = w / scale;
    rows = h / scale;
    waves = new float [cols][rows];
    sun = new PVector[total+1][total+1];
    sunmove = new PVector(100,100,0);
  
}

public void keyPressed()
{
    if (key == ' ')
    {
        getAudioPlayer().cue(0);
        getAudioPlayer().play();
    }
}

public void draw()
{
	drawWave();
    if (key == '1'){
        drawsun();
    }
 
    
}

public  void drawWave(){
  
 
    pushMatrix();
    background(0);
    calculateAverageAmplitude();
    fill(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
    stroke(255);
    strokeWeight(2);
    calculateFrequencyBands();
    translate(width/2, height/2 + 50, (200 * getSmoothedAmplitude()));
    rotateX(PI/3);
    translate(-width/2, -height/2);
    waveSpeed -= 0.05;
    if(key == 'b'){
         waveSpeed += 0.1;
    }
    float offsetY = waveSpeed;
    for (int y = 0; y < rows; y++){
        float offsetX = 0;
        for (int x = 0; x < cols; x++){
            waves[x][y] = map(noise(offsetX,offsetY), 0 ,1, -100, 100);
            offsetX += 0.2;
        }
        offsetY += getSmoothedAmplitude();
    }
    for (int y = 0; y < rows -1; y++){
        beginShape(TRIANGLE_STRIP);
        for (int x = 0 ; x < cols; x++){
            float rainbow2 = map(x, 0, 50, 0, 255*6);
            if(key == 'c'){
                fill(rainbow2  % 255, 255, 255);
            }
            
            vertex(x*scale, y*scale, waves[x][y]);
            vertex(x*scale, (y+1)*scale, waves[x][y+1]);
        }
        endShape();
    }
    popMatrix();
}

PVector[][] sun;
 int total = 50;
public void drawsun(){
      
    pushMatrix();
     background(0);
    noStroke();
    lights();
    translate(width/2, height/2, (500 * getSmoothedAmplitude()));
    float r = 100;
    rotateY(50 * getSmoothedAmplitude());
    
    for (int i = 0; i < total+1; i++) {
      float vertical = map(i, 0, total, 0, PI);
      for (int j = 0; j < total+1; j++) {
        float horizontal = map(j, 0, total, 0, TWO_PI);
        x = r * sin(vertical) * cos(horizontal);
        float y = r * sin(vertical) * sin(horizontal);
        float z = r * cos(vertical);
        sun[i][j] = new PVector(x, y, z);
      }
    }
    for (int i = 0; i < total+1; i++) {
        for (int j = 0; j < total+1; j++) {
          sun[i][j].add(sunmove);
        }
    }
    
        sunmove.x -= (getSmoothedAmplitude());
       // sunmove.y -= (20 * getSmoothedAmplitude());
    for (int i = 0; i < total; i++) {
      float rainbow = map(i, 0, total, 0, 255*6);
      fill(rainbow  % 255, 255, 255);
      beginShape(TRIANGLE_STRIP);
      for (int j = 0; j < total+1; j++) {
        PVector v1 = sun[i][j];
        vertex(v1.x, v1.y, v1.z);
        PVector v2 = sun[i+1][j];
        vertex(v2.x, v2.y, v2.z);
      } 
      endShape();
    }
    popMatrix();
    
    // Spawnsun sun_test = new Spawnsun(width, height, (500 * getSmoothedAmplitude()));
    // Spawnsun sun_test1 = new Spawnsun(width, height, (500 * getSmoothedAmplitude()));
    // sun_test.buildsun();
    // sun_test1.buildsun();
    }
    public void drawcircles(){
        
    }
 }

