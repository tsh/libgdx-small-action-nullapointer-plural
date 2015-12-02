package com.pluralsight.nullapointershooter;


public class CollisionManager {
    private final AnimatedSprite spaceshipAnimated;
    private final Enemy enemy;
    private final ShotManager shotManager;

    public CollisionManager(AnimatedSprite spaceshipAnimated, Enemy enemy, ShotManager shotManager){
        this.spaceshipAnimated = spaceshipAnimated;
        this.enemy = enemy;
        this.shotManager = shotManager;
    }

    public void handleCollisions(){
        handleEnemyShot();
    }

    public void handleEnemyShot(){
        if (shotManager.playerShotTouches(enemy.getBoundingBox())){
            enemy.hit();
        }
    }
}
