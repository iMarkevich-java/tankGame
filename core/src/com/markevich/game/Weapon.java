package com.markevich.game;

import com.badlogic.gdx.graphics.Texture;

public class Weapon {
	private Texture texture;
	private float firePeriod;
	private int damage;

	public Weapon() {
		this.texture = new Texture("simple_weapon.png");
		this.firePeriod = 0.4f;
		this.damage = 1;
	}

	public Texture getTexture() {
		return texture;
	}

	public float getFirePeriod() {
		return firePeriod;
	}

	public int getDamage() {
		return damage;
	}
}
