package com.pluralsight.nullapointershooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ShooterGame extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    SpriteBatch batch;
    Texture background;
    private OrthographicCamera camera;
    private AnimatedSprite spaceshipAnimated;
    private ShotManager shotManager;
    private Music gameMusic;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("space-background.png"));
        Texture spaceshipTexture = new Texture(Gdx.files.internal("spaceship-spritesheet.png"));
        Sprite spaceshipSprite = new Sprite(spaceshipTexture);
        spaceshipAnimated = new AnimatedSprite(spaceshipSprite);
        spaceshipAnimated.setPosition(800 / 2, 0);

        Texture shotTexture = new Texture(Gdx.files.internal("shot-spritesheet.png"));
        shotManager = new ShotManager(shotTexture);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game-music.mp3"));
        gameMusic.setVolume(.25f);
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        spaceshipAnimated.draw(batch);
        shotManager.draw(batch);
        batch.end();

        handleInput();
        spaceshipAnimated.move();
        shotManager.update();
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);

            if (touchPosition.x > spaceshipAnimated.getX()) {
                spaceshipAnimated.moveRight();
            } else {
                spaceshipAnimated.moveLeft();
            }

            shotManager.firePlayerShot(spaceshipAnimated.getX());
        }
    }
}
