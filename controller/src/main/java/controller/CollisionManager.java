package controller;

import java.awt.Point;

import javax.swing.text.html.parser.Entity;

import model.IBeing;
import model.IEntity;
import model.IModel;
import model.IPlayer;
import model.ISpell;
import model.Permeability;
import showboard.IPawn;
import view.IView;

public class CollisionManager {
	
	private IModel model;
	private IView view;
	
	public CollisionManager(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * This method allow us to create every case of wall collision
	 * With every order we're increasing or decreasing the value of x or y of our player in order to manage the wall collision
	 * @param player
	 * @param order
	 */
	public boolean wallCollision(IPlayer player, Order order) {
		Point position = player.getPosition();
		switch (order) {
		case ORD_M_UP:
			position.y -= 1;
			break;
		case ORD_M_UP_R:
			position.y -= 1;
			position.x += 1;
			break;
		case ORD_M_RIGHT:
			position.x += 1;
			break;
		case ORD_M_DOWN_R:
			position.y += 1;
			position.x += 1;
			break;
		case ORD_M_DOWN:
			position.y += 1;
			break;
		case ORD_M_DOWN_L:
			position.y +=1;
			position.x -=1;
			break;
		case ORD_M_LEFT:
			position.x -= 1;
			break;
		case ORD_M_UP_L:
			position.y -= 1;
			position.x -= 1;
			break;
		default:
			break;
		}
		
		IEntity entity = model.getOnMap(position.x, position.y);
		Permeability permeability = entity.getPermeability();
		switch (permeability) {
		case BLOCKING:
			return false;
		case PENETRABLE:
			return true;
		case COLLECTABLE:
			this.model.removeSquare(position.x, position.y);
			this.view.removeSquare(position.x, position.y);
			this.model.addScore(250);
			return true;
		case KILLING:
			player.die();
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * This method allow us to create every case of collision but this time for the spell
	 * Then we're doing a test in order to return if the spell can penetrate or not something
	 * @param spell
	 */
	public boolean wallCollision(ISpell spell) {
		Point position = spell.getPosition();
		switch (spell.getDirection()) {
		case DIR_UP:
			position.y -= 1;
			break;
		case DIR_UP_RIGHT:
			position.y -= 1;
			position.x += 1;
			break;
		case DIR_RIGHT:
			position.x += 1;
			break;
		case DIR_DOWN_RIGHT:
			position.y += 1;
			position.x += 1;
			break;
		case DIR_DOWN:
			position.y += 1;
			break;
		case DIR_DOWN_LEFT:
			position.y +=1;
			position.x -=1;
			break;
		case DIR_LEFT:
			position.x -= 1;
			break;
		case DIR_UP_LEFT:
			position.y -= 1;
			position.x -= 1;
			break;
		default:
			break;
		}
		
		IEntity entity = model.getOnMap(position.x, position.y);
		Permeability permeability = entity.getPermeability();
		if (permeability == Permeability.PENETRABLE) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method is used in order to know every case of collision with every entity (Monster, Spell, Player)
	 * Here we're comparing the position of an entity 1 with an entity 2
	 */
	public void crossCollision(IPawn entity1, IPawn entity2) {
		Point position1 = entity1.getPosition();
		Point position2 = entity2.getPosition();
		if (position1.x == position2.x && position1.y == position2.y) {
			switch (entity1.getClass().getName()) {
			case "model.Monster":
				switch (entity2.getClass().getName()) {
				case "model.Player":
					IBeing player = (IBeing) entity2;
					player.die();
					this.view.removePawn((IPawn) player);
					break;
				case "model.Spell":
					IBeing monster = (IBeing) entity1;
					monster.die();
					this.view.removePawn((IPawn) monster);
					this.view.removePawn((IPawn) entity2);
					model.destroySpell();
					this.model.addScore(200);
					break;
				}
				break;
				
			case "model.Player":
				switch (entity2.getClass().getName()) {
				case "model.Monster":
					IBeing player = (IBeing) entity1;
					player.die();
					this.view.removePawn((IPawn) player);
					break;
				case "model.Spell":
					this.view.removePawn((IPawn) entity2);
					model.destroySpell();
					break;
				}
				break;
				
			case "model.Spell":
				switch (entity2.getClass().getName()) {
				case "model.Monster":
					IBeing monster = (IBeing) entity2;
					monster.die();
					this.view.removePawn((IPawn) monster);
					this.view.removePawn((IPawn) entity1);
					model.destroySpell();
					this.model.addScore(200);
					break;
				case "model.Player":
					this.view.removePawn((IPawn) entity1);
					model.destroySpell();
					break;
				}
				break;
			}
		}
	}
}
