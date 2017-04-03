package collision;


import java.awt.geom.Area;

import Objects.Frog;
import Objects.Lane;


public class detecteurCollision {
	
	public static boolean collisionCheck(Frog frog, Lane[] items){
		
		for(Lane item : items){
			for(int i = 0; i< Lane.NB_OBJ_PER_LANE; i++){
				Area intersect = new Area(item.getLaneObj()[i].getBoundingBox());
				intersect.intersect(new Area(frog.getBoundingBox()));
				
				if(!intersect.isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

}
