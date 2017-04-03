package interactionsObjetsGrenouille;


import java.awt.geom.Area;

import Objects.Frog;
import Objects.Lane;


public class detecteurCollision {
	
	public static boolean Collision(Frog frog, Lane[] items){
		
		for(Lane item : items){
			for(int i = 0; i< item.getLaneObj().length; i++){
				Area intersect = new Area();
				intersect = item.getLaneObj().length.getBoundingBox();
				intersect.intersect(new Area(frog.getBoundingBox()));
				//fuckyou for now
				if(!intersect.isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

}
