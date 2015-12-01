package com.pluralsight.nullapointershooter;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Enemy {

    private static int ENEMY_SPEED = 250;

    private final Texture enemyTexture;
    private AnimatedSprite animatedSprite;
    private final ShotManager shotManager;

    public Enemy(Texture enemyTexture, ShotManager shotManager){
        this.enemyTexture = enemyTexture;
        this.shotManager = shotManager;
        spawn();
    }

    private void spawn(){
        Sprite enemySprite = new Sprite(enemyTexture);
        animatedSprite = new AnimatedSprite(enemySprite);
        int xPosition = createRandomPosition();
        animatedSprite.setPosition(xPosition, ShooterGame.SCREEN_HEIGHT - animatedSprite.getHeight());
        animatedSprite.setVelocity(new Vector2(ENEMY_SPEED, 0));
    }

    private int createRandomPosition(){
        Random random = new Random();
        int randomNumber = random.nextInt(ShooterGame.SCREEN_WIDTH - animatedSprite.getWidth() + 1);
        return randomNumber + animatedSprite.getWidth() /2;
    }

    public void draw (SpriteBatch batch){
        animatedSprite.draw(batch);
    }

    public void update(){
        if (shouldChangeDirection()){
            animatedSprite.changeDirection();
        }

        if (shouldFire()){
            shotManager.fireEnemyShot(animatedSprite.getX());
        }
        animatedSprite.move();
    }

    public boolean shouldChangeDirection(){
        Random random = new Random();
        return random.nextInt(21) == 0;
    }

    public boolean shouldFire(){
        Random random = new Random();
        return random.nextInt(51) == 0;
    }

}
