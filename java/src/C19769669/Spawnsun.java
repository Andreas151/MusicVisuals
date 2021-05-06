package C19769669;

import processing.core.PVector;
public class Spawnsun extends Andyvisual {
    PVector[][] sun;
    int total = 50;
    float x= 0;
    float x1 = 0;
    float y2 = 0;
    float z2 = 0;
    PVector sunmove;
    public Spawnsun(float x1, float y2, float z2 ){
        this.sun = new PVector[total+1][total+1];
        this.x1 = x1;
        this.y2 = y2;
        
        sunmove = new PVector(100,100,0);
    }
    public void settings()
{
    size(800, 800, P3D);
    println("CWD: " + System.getProperty("user.dir"));
    //fullScreen(P3D, SPAN);

}

public void setup(){
    colorMode(HSB);
	  background(0);
    startMinim();
    //startListening();
    loadAudio("Beating Up Andreas2.mp3");
    sun = new PVector[total+1][total+1];
    sunmove = new PVector(100,100,0);
}
    public void buildsun(){
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
    }
    public void render(){
      background(0);
      noStroke();
      lights();
      translate(width/2, height/2,(200 * getSmoothedAmplitude()));
      buildsun();
    }
    
}
