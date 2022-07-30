package com.markevich.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tank {
	private MyGdxGame game;
	private Texture texture;
	private Texture textureTurret;
	private Vector2 position;
	private float speed;
	private float angleTurret;
	private float angle;

	public Tank(MyGdxGame game) {
		this.game = game;
		this.texture = new Texture("player_tank_base.png");
		this.textureTurret = new Texture("simple_weapon.png");
		this.position = new Vector2(100, 100);
		this.speed = 100.0f;
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, position.x - 20, position.y - 20, 20, 20, 40, 40, 1, 1, angle, 0, 0, 40, 40, false, false);
		batch.draw(textureTurret, position.x - 20, position.y - 20, 20, 20, 40, 40, 1, 1, angleTurret, 0, 0, 40, 40, false, false);
	}

	public void update(float dt) {
		checkMovement(dt);
		float mx = Gdx.input.getX();
		float my = Gdx.graphics.getHeight() - Gdx.input.getY();
		float angleTo = Utils.getAngle(position.x, position.y, mx, my);
		angleTurret = Utils.makeRotation(angleTurret, angleTo, 270.0f, dt);
		angleTurret = Utils.angleToFromNegPiToPosPi(angleTurret);
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			fire();
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

	public void fire() {
		if (!game.getBullet().isActive()) {
			float angleRad = (float) Math.toRadians(angleTurret);
			game.getBullet().activate(position.x, position.y, 320.0f * (float) Math.cos(angleRad), 320.0f * (float) Math.sin(angleRad));
		}
	}
}
