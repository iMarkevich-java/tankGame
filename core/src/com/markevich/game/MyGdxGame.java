package com.markevich.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Tank tank;
	private Bullet bullet;


	@Override
	public void create() {
		batch = new SpriteBatch();
		tank = new Tank(this);
		bullet = new Bullet();

	}

	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		ScreenUtils.clear(0, 0.6f, 0, 1);
		batch.begin();
		tank.render(batch);
		if (bullet.isActive()) {
			bullet.render(batch);
		}
		batch.end();
	}

	public void update(float dt) {
		tank.update(dt);
		if (bullet.isActive()) {
			bullet.update(dt);
		}
	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	public Tank getTank() {
		return tank;
	}

	public Bullet getBullet() {
		return bullet;
	}
}