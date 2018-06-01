package model;

import model.AIDesignPatter.*;

/**
 * The class Factory instantiate 
 * @author Hugo
 *
 */
public class Factory {

	private Player player;
	private Monster rook;
	private Monster bishop;
	private Monster wheel;
	private Monster stalker;
	private Spell spell;
	private Entity bone;
	private Entity ground;
	private Entity purse;
	private Entity gate_o;
	private Entity gate_c;
	private Entity crystal_ball;
	private Entity horizontal_bone;
	private Entity vertical_bone;
	/**
	 * instantiate some monsters, a player and the Entity
	 */
	public Factory() {
		player = new Player();
		
		rook = new Monster("C:\\Users\\pcmga\\git\\Lorann\\sprite\\rook.png", new Rook());
		bishop = new Monster("C:\\Users\\pcmga\\git\\Lorann\\sprite\\bishop.png", new Bishop());
		wheel = new Monster("C:\\Users\\pcmga\\git\\Lorann\\sprite\\wheel.png", new Wheel());
		stalker = new Monster("C:\\Users\\pcmga\\git\\Lorann\\sprite\\stalker.png", new Stalker());
		spell = null;
		bone = new Entity("C:\\Users\\pcmga\\git\\Lorann\\sprite\\bone.png", Permeability.BLOCKING);
		ground = new Entity("C:/Users/pcmga/git/Lorann/sprite/ground.png", Permeability.PENETRABLE);
		purse = new Entity("C:\\Users\\pcmga\\git\\Lorann\\sprite\\purse.png", Permeability.COLLECTABLE);
		gate_o = new Entity("C:\\Users\\pcmga\\git\\Lorann\\sprite\\gate_o.png", Permeability.PENETRABLE);
		gate_c = new Entity("C:\\Users\\pcmga\\git\\Lorann\\sprite\\gate_c.png", Permeability.KILLING);
		crystal_ball = new Entity("C:\\Users\\pcmga\\git\\Lorann\\sprite\\crystall_ball.png", Permeability.COLLECTABLE);
		horizontal_bone = new Entity("C:\\Users\\pcmga\\git\\Lorann\\sprite\\horizontal_bone.png", Permeability.BLOCKING);
		vertical_bone = new Entity ("C:\\Users\\pcmga\\git\\Lorann\\sprite\\vertical_bone.png", Permeability.BLOCKING);
		
		
	}
	/**
	 * get the type of the entity
	 * @param entityType
	 * @return the entity
	 */
	public IEntity getEntity(EntityType entityType) {
		switch (entityType) {
		
		case ENT_GROUND : return ground;
		
		case ENT_BONE_H : return horizontal_bone;
				
		case ENT_BONE_V : return vertical_bone;
				
		case ENT_BONE : return bone;
				
		case ENT_CRYSTAL : return crystal_ball;
				
		case ENT_GATE_O : return gate_o;
				
		case ENT_GATE_C : return gate_c;
				
		case ENT_PURSE : return purse;
		
		}
		return null;
	}
	
	/**
	 * get the monster according to the right monster
	 * @param monsterNumber
	 * @return the monster
	 */
	public IMonster getMonster(int monsterNumber) {
		switch (monsterNumber) {
		
		case 1 : return this.rook;
		
		case 2 : return this.bishop;
		
		case 3 : return this.wheel;
		
		case 4 : return this.stalker;
		
		default : return null;
		}
		
	}
	
	/**
	 * get the player 
	 * @return the player
	 */
	public IPlayer getPlayer() {
		return player;
	}
	
	/**
	 * generate the spell
	 */
	public void generateSpell() {
		if(!isSpellExist()) {
			spell = new Spell();
			spell.setPosition(player.getPosition());
		}
		
	}
	
	/**
	 * check if the spell exist
	 * @return true or false
	 */
	public boolean isSpellExist() {
		if(this.spell == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * get the spell
	 * @return the spell
	 */
	public ISpell getSpell() {
		return this.spell;
	}
	
	/**
	 * Destroy the spell
	 */
	public void destroySpell() {
		this.spell = null;
	}
	
}
