package C19769669;
public class FireWork extends Andyvisual {

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
 
    }

    float radius = 200;

    float smoothedBoxSize = 0;

    float rot = 0;

    public void drawcircles()
    {
        calculateAverageAmplitude();
      
        calculateFrequencyBands();
        background(0);
        noFill();
        stroke(255);
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, -500, 500, 0, 0, 0, 0, 1, 0);
        translate(0, 0, -250);

        rot += getAmplitude() / 0.5f;

        rotateY(rot);
        float[] bands = getSmoothedBands();
        for(int i = 0 ; i < bands.length ; i ++)
        {
            float theta = map(i, 0, bands.length, 0, TWO_PI);

            stroke(map(i, 0, bands.length, 0, 255), 255, 255);
            float x = sin(theta) * radius;
            float z = cos(theta) * radius;
            float h = bands[i];
            pushMatrix();
            translate(x, - h / 2 , z);
            rotateY(theta);
            ellipse(0, 0, radius, radius);
            popMatrix();
        }

    }
    float angle = 1;
public void render(){
    background(0);
    noStroke();
    lights();
    drawcircles();
}
}

