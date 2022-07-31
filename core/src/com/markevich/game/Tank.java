package com.markevich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tank {
	private TanksGame game;
	private Weapon weapon;
	private Texture texture;
	private Vector2 position;
	private float speed;
	private float angleTurret;
	private float fireTimer;
	private float angle;
	private int width;
	private int height;

	public Tank(TanksGame game) {
		this.game = game;
		this.weapon = new Weapon();
		this.texture = new Texture("player_tank_base.png");
		this.position = new Vector2(100, 100);
		this.speed = 100.0f;
		this.height = texture.getHeight();
		this.width = texture.getWidth();
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angle, 0, 0, 40, 40, false, false);
		batch.draw(weapon.getTexture(), position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angleTurret, 0, 0, 40, 40, false, false);
	}

	public void update(float dt) {
		checkMovement(dt);
		float mx = Gdx.input.getX();
		float my = Gdx.graphics.getHeight() - Gdx.input.getY();
		float angleTo = Utils.getAngle(position.x, position.y, mx, my);
		angleTurret = Utils.makeRotation(angleTurret, angleTo, 270.0f, dt);
		angleTurret = Utils.angleToFromNegPiToPosPi(angleTurret);
		if (Gdx.input.isTouched()) {
			fire(dt);
		}
	}

	public void checkMovement(float dt) {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			position.x -= speed * dt;
			angle = 180f;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			position.x += speed * dt;
			angle = 0f;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			position.y += speed * dt;
			angle = 90f;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			position.y -= speed * dt;
			angle = 270f;
		}
	}

	public void fire(float dt) {
		fireTimer += dt;
		if(fireTimer >= weapon.getFirePeriod()){
			fireTimer = 0.0f;
			float angleRad = (float) Math.toRadians(angleTurret);
			game.getBulletEmitter().activate(position.x, position.y, 320.0f * (float) Math.cos(angleRad), 320.0f * (float) Math.sin(angleRad), weapon.getDamage());
		}
	}
}
