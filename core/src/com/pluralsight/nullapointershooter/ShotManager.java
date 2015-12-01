package com.pluralsight.nullapointershooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ShotManager {

    private static final int SHOT_Y_OFFSET = 90;
    private static final int ENEMY_SHOT_Y_OFFSET = 400;
    private static final int SHOT_SPEED = 300;
    private static final float MINIMUM_TIME_BETWEEN_SHOTS = .5f;

    private final Texture shotTexture;
    private final Texture enemyShotTexture;
    private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();
    private float timeSinceLastShot;
    private Sound laser = Gdx.audio.newSound(Gdx.files.internal("laser-bolt.mp3"));
    private List<AnimatedSprite> enemyShots = new ArrayList<AnimatedSprite>();

    public ShotManager(Texture shotTexture, Texture enemyShotTexture){
        this.shotTexture = shotTexture;
        this.enemyShotTexture = enemyShotTexture;
    }

    public void firePlayerShot(int shipCenterXLocation)
    {
        if(canFireShot())
        {
            Sprite newShot = new Sprite(shotTexture);
            AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
            newShotAnimated.setPosition(shipCenterXLocation, SHOT_Y_OFFSET);
            newShotAnimated.setVelocity(new Vector2(0, SHOT_SPEED));
            shots.add(newShotAnimated);
            timeSinceLastShot = 0f;
            laser.play();
        }

    }

    public void fireEnemyShot(int enemyCenterXLocation){
        Sprite newShot = new Sprite(enemyShotTexture);
        AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
        newShotAnimated.setPosition(enemyCenterXLocation, ENEMY_SHOT_Y_OFFSET);
        newShotAnimated.setVelocity(new Vector2(0, -SHOT_SPEED));
        enemyShots.add(newShotAnimated);
        timeSinceLastShot = 0f;
        laser.play();
    }

    private boolean canFireShot()
    {
        return timeSinceLastShot > MINIMUM_TIME_BETWEEN_SHOTS;
    }

    public void update()
    {
        Iterator<AnimatedSprite> i = shots.iterator();
        while(i.hasNext())
        {
            AnimatedSprite shot = i.next();
            shot.move();
            if (shot.getY() > ShooterGame.SCREEN_HEIGHT)
            {
                i.remove();
            }
        }

        Iterator<AnimatedSprite> j = enemyShots.iterator();
        while(j.hasNext())
        {
            AnimatedSprite shot = j.next();
            shot.move();
            if (shot.getY() < 0)
            {
                j.remove();
            }
        }

        timeSinceLastShot += Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch)
    {
        for(AnimatedSprite shot: shots)
        {
            shot.draw(batch);
        }

        for(AnimatedSprite shot: enemyShots)
        {
            shot.draw(batch);
        }
    }

}
