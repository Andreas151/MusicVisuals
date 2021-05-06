package ie.tudublin;

import C19769669.FireWork;
import C19769669.Oceanwaves;
import C19769669.illuminati;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Oceanwaves());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}