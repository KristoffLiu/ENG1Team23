package com.engoneassessment.game.utils;
import com.badlogic.gdx.math.Rectangle;
import com.engoneassessment.game.actors.Character;

public interface ICollisionDetector {
    boolean checkCollision(Rectangle bounds, Character.FacingDirection facingDirection);

}
