package collision;


import java.awt.geom.Area;

import Objects.Frog;
import Objects.Lane;


public class detecteurCollision {
	
	public static Area inter = new Area();
	
	public static boolean carCheck(Frog frog, Lane[] items){
		
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
	
	public static boolean logCheck(Frog frog, Lane[] items){
		boolean touchFlag = false;
		inter = new Area();
		if(frog.getCurrentLane()>=7 && frog.getCurrentLane()<=11)
		{
			for(Lane item : items){
				for(int i = 0; i< Lane.NB_OBJ_PER_LANE; i++){
					inter = new Area(item.getLaneObj()[i].getBoundingBox());
					inter.intersect(new Area(frog.getBoundingBox()));
						
				if(!inter.isEmpty() && inter.getBounds().getWidth()>20)
				{
					touchFlag = true;

					if(item.getDirection() == Lane.LEFT)
						frog.moveLeftOnLog(item.getSpeed());
					else	
						frog.moveRightOnLog(item.getSpeed());
					
					break;
				}
			}
			
		}
		}
		else
		{
			touchFlag = true;
		}

		return touchFlag;
		
	}

}
