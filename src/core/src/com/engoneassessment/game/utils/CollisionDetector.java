package com.engoneassessment.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.engoneassessment.game.actors.Character;

public class CollisionDetector implements ICollisionDetector {
    @Override
    public boolean checkCollision(Rectangle bounds, Character.FacingDirection facingDirection) {
        return false;
    }
}
