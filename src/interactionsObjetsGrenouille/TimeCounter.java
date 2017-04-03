package interactionsObjetsGrenouille;

import Objects.FroggerGame;

public class TimeCounter extends FroggerGame {
	protected Thread time;
	
	public TimeCounter() throws InterruptedException{
	time = new Thread();
	
	for (int i = 0; i < this.MAX_LIFE_TIME;i++){
		time.sleep(1000);
		this.startLifeTime = i;
		
	}
	
	}

}
