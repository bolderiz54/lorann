package model;

import java.util.Observer;

/**
 * <h1>The Class ModelFacade provides a facade of the Model component.</h1>
 *
 * @author Hugo
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public final class ModelFacade implements IModel {
	
	/**
	 * The number of the level which will be loaded
	 */
	@SuppressWarnings("unused")
	private int levelNumber = 1;
	
	/**
	 * The level loaded from the database
	 */
	private String[][] loadedLevel;
	
	/**
	 * The map that the game use
	 */
	private LorannMap map;
	
	/**
	 * the width of the map
	 */
	private int width;
	
	/**
	 * the height of the map
	 */
	private int height;
	
	/**
	 * the score obtained
	 */
	private Score score;
	
	/**
	 * the factory that is use to generate entities
	 */
	private Factory factory;
	
    /**
     * Instantiates a new model facade.
     * 
     * @param map's width
     * @param map's height
     */
    public ModelFacade(final int width, final int height) {
        score = new Score();
        factory = new Factory();
        this.width = width;
        this.height = height;
        map = new LorannMap(this.width, this.height);
    }

    /**
	 * get the score
	 * @return the score
	 */
	@Override
	public int getScore() {
		return this.score.getScore();
	}

	/**
	 * calculate and add the score
	 * @param points
	 */
	@Override
	public void addScore(int points) {
		this.score.addScore(points);
		
	}

	/**
	 * set the score 
	 * @param points
	 */
	@Override
	public void setScore(int points) {
		this.score.setScore(points);
	}

	/**
	 * 
	 * @param level (int)
	 * @return true if the entity is alive
	 */
	@Override
	public boolean loadLevel(int level) {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.height; x++) {
				if (x == 0 || x == this.width - 1 || y == 0 || y == this.height - 1) {
					this.setOnMap(EntityType.ENT_BONE, x, y);
				}
				else {
					this.setOnMap(EntityType.ENT_GROUND, x, y);
				}
			}
		}
		
		return true;
	}

	/**
	 * Reset the level
	 */
	@Override
	public void resetLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch (this.loadedLevel[y][x]) {
				case "bone_v" :
					break;
				case "bone_h" :
					break;
				case "bone" :
					break;
				case "crystal" : 
					break;
				case "gate_c" : 
					break;
				case "gate_o" : 
					break;
				case "purse" :
					break;
				case "ground" :
					break;
				}
			}
		}	
	}

	/**
	 * Unload the level
     */
	@Override
	public void unloadLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				this.setOnMap(EntityType.ENT_GROUND, x, y);
			}
		}
	}

	@Override
	public LorannMap getLorannMap() {
		return this.map;
	}

	/**
	 * get the Width of the map
     * @return the size of the width (int)
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * set the width
	 * @param width
	 */
	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * get the height
	 * @return the size of the height (int)
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * set the height
	 * @param height
	 */
	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * get the player
	 */
	@Override
	public IPlayer getPlayer() {
		return this.factory.getPlayer();
	}

	/**
	 * This is to get a Monster
     * @param monsterNumber
     * @return the number of the monster
	 */
	@Override
	public IMonster getMonster(int monsterNumber) {
		return this.factory.getMonster(monsterNumber % 4);
	}

	/**
	 * The method check if the spell exist or not 
     * @return true if the spell exist
	 */
	@Override
	public boolean isSpellExist() {
		return this.factory.isSpellExist();
	}

	/**
	 * get the spell
	 */
	@Override
	public ISpell getSpell() {
		return this.factory.getSpell();
	}

	/**
	 * get an Entity
     * @param x  The movements of the Entity
     * @param y The movements of the Entity
     * @return  an entity at the right coordinates
	 */
	@Override
	public IEntity getOnMap(int x, int y) {
		return this.getLorannMap().getOnMap(x, y);
	}
	
	/**
	 * set an Entity on the map
	 * @param entity
	 * @param x
	 * @param y
	 */
	@Override
	public void setOnMap(EntityType entity, int x, int y) {
		this.getLorannMap().setOnMap(this.factory.getEntity(entity), x, y);
	}

	/**
	 * generate the spell
	 */
	@Override
	public void generateSpell() {
		this.factory.generateSpell();
	}
	/**
	 * destroy the spell
	 */
	@Override
	public void destroySpell() {
		this.factory.destroySpell();
	}

	/**
	 * remove a square from the map
	 */
	@Override
	public void removeSquare(int x, int y) {
		this.getLorannMap().setOnMap(this.factory.getEntity(EntityType.ENT_GROUND), x, y);
	}

	/**
	 * Add an observer to the map
	 */
	@Override
	public void addObserver(Observer observer) {
		this.getLorannMap().addObserver(observer);
	}

}
