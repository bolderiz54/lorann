package model;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

/**
 * <h1>The class Player</h1>
 * This class describe the structure of the player
 * @author Vincent Linck
 * @version 1.0
 */
public class Player extends Being implements IPlayer {

	/**
	 * The direction of the player
	 */
	private Direction direction;
	
	/**
	 * The direction where the player is looking
	 */
	private Direction animationDirection;
	
	/**
	 * The images of the player
	 */
	private static String[] imagesPath = {"../sprite/lorann_u.png", "../sprite/lorann_ur.png", "../sprite/lorann_r.png", "../sprite/lorann_br.png",
			"../sprite/lorann_b.png", "../sprite/lorann_bl.png", "../sprite/lorann_l.png", "../sprite/lorann_ul.png"};
	
	/**
	 * Instantiate the player
	 * @param imagePath
	 * 			The path to the image
	 */
	public Player() {
		super(imagesPath[0], Permeability.PENETRABLE);
		this.setDirection(Direction.DIR_UP);
		this.animationDirection = Direction.DIR_UP;
	}
	
	@Override
	public void move(int x, int y) {
		this.setPosition(new Point(this.getX() + x, this.getY() + y));
		
		if(x > 0) {
			if(y > 0) {
				this.setDirection(Direction.DIR_DOWN_RIGHT);
				this.animationDirection = this.getDirection();
			}
			else if (y < 0) {
				this.setDirection(Direction.DIR_UP_RIGHT);
				this.animationDirection = this.getDirection();
			}
			else {
				this.setDirection(Direction.DIR_RIGHT);
				this.animationDirection = this.getDirection();
			}
		}
		else if (x < 0) {
			if(y > 0) {
				this.setDirection(Direction.DIR_DOWN_LEFT);
				this.animationDirection = this.getDirection();
			}
			else if (y < 0) {
				this.setDirection(Direction.DIR_UP_LEFT);
				this.animationDirection = this.getDirection();
			}
			else {
				this.setDirection(Direction.DIR_LEFT);
				this.animationDirection = this.getDirection();
			}
		}
		else {
			if(y > 0) {
				this.setDirection(Direction.DIR_DOWN);
				this.animationDirection = this.getDirection();
			}
			else if (y < 0) {
				this.setDirection(Direction.DIR_UP);
				this.animationDirection = this.getDirection();
			}
		}
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * This method allow the player to change its image according to its direction or its animation pattern
	 * @throws IOException
	 */
	private void setImage() throws IOException {
		switch (this.animationDirection) {
		case DIR_UP:
			this.getSprite().setImagePath(Player.imagesPath[0]);
			this.animationDirection = Direction.DIR_UP_RIGHT;
			break;
		case DIR_UP_RIGHT:
			this.getSprite().setImagePath(Player.imagesPath[1]);
			this.animationDirection = Direction.DIR_RIGHT;
			break;
		case DIR_RIGHT:
			this.getSprite().setImagePath(Player.imagesPath[2]);
			this.animationDirection = Direction.DIR_DOWN_RIGHT;
			break;
		case DIR_DOWN_RIGHT:
			this.getSprite().setImagePath(Player.imagesPath[3]);
			this.animationDirection = Direction.DIR_DOWN;
			break;
		case DIR_DOWN:
			this.getSprite().setImagePath(Player.imagesPath[4]);
			this.animationDirection = Direction.DIR_DOWN_LEFT;
			break;
		case DIR_DOWN_LEFT:
			this.getSprite().setImagePath(Player.imagesPath[5]);
			this.animationDirection = Direction.DIR_LEFT;
			break;
		case DIR_LEFT:
			this.getSprite().setImagePath(Player.imagesPath[6]);
			this.animationDirection = Direction.DIR_UP_LEFT;
			break;
		case DIR_UP_LEFT:
			this.getSprite().setImagePath(Player.imagesPath[7]);
			this.animationDirection = Direction.DIR_UP;
			break;
		default:
			break;
		}
		
		this.getSprite().loadImage();
	}
	
	@Override
	public Image getImage() {
		try {
			this.setImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.getSprite().getImage();
	}

}
