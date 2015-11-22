package com.pluralsight.nullapointershooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShooterGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	private OrthographicCamera camera;
    private Sprite spaceshipSprite;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("space-background.png"));
        Texture spaceshipTexture = new Texture(Gdx.files.internal("spaceship.png"));
        spaceshipSprite = new Sprite(spaceshipTexture);
        spaceshipSprite.setPosition((800 / 2) - (spaceshipSprite.getWidth() / 2), 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
        spaceshipSprite.draw(batch);
		batch.end();
	}
}
