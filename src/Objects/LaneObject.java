package Objects;

import java.awt.Rectangle;
//classe abstraite qui représente tous les objets faisant partie des LANES du jeu (autos et biots de bois)
public abstract class LaneObject extends MovableObject {

	protected int speed;
	
	public abstract int getPosX();
	public abstract int getPosY();

	public abstract Rectangle getBoundingBox(); 
}
