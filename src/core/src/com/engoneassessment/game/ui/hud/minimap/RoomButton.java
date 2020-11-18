package com.engoneassessment.game.ui.hud.minimap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.engoneassessment.game.screens.RoomScreen;
import com.engoneassessment.game.ui.NonUIAnimationHelper;
import com.engoneassessment.game.ui.controls.Button;
import com.engoneassessment.game.ui.controls.ButtonClickListener;
import com.engoneassessment.game.ui.controls.ClickableUIElementClickListener;
import com.engoneassessment.game.ui.controls.labels.LabelStyles;

public class RoomButton extends Button {
    RoomScreen roomScreen;
    Label label;
    NonUIAnimationHelper labelNonUIAnimationHelper;
    /**
     * Constructor of a Button
     * the texture input will be its mapping texture.
     *
     * @param uiParent
     * @param normalTexture
     * @param hoveringTexture
     * @param pressedTexture
     */
    public RoomButton(Object uiParent, final MiniMap miniMap, String roomName,
                      TextureRegion normalTexture, TextureRegion hoveringTexture,
                      TextureRegion pressedTexture, TextureRegion notActivatedTexture) {
        super(uiParent, normalTexture, hoveringTexture, pressedTexture, notActivatedTexture);
        label = new Label(roomName, LabelStyles.usingImpactFontStyle(
                true,0.3f,0.75f,0.75f,0.75f,1f)
        );
        miniMap.addActor(label);
        label.setVisible(false);
        labelNonUIAnimationHelper = new NonUIAnimationHelper(label);
        setRoomScreen(roomScreen);
        setClickListener(new ButtonClickListener(){
            /** Called any time the mouse cursor or a finger touch is moved over an actor. On the desktop, this event occurs even when no
             * mouse buttons are pressed (pointer will be -1).
             * @param fromActor May be null.
             * @see ClickableUIElementClickListener */
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(miniMap.isOpen){
                    super.enter(event, x, y, pointer, fromActor);
                    label.setVisible(true);
                    label.setPosition(getX() + (getWidth() - label.getWidth()) / 2,getY()+ getHeight() + 10);
                    labelNonUIAnimationHelper.fadeIn(0.3f);
                }
            }

            /** Called any time the mouse cursor or a finger touch is moved out of an actor. On the desktop, this event occurs even when no
             * mouse buttons are pressed (pointer will be -1).
             * @param toActor May be null.
             * @see ClickableUIElementClickListener */
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if(miniMap.isOpen) {
                    super.exit(event, x, y, pointer, toActor);
                    labelNonUIAnimationHelper.fadeOut(0.3f);
                }
            }

            /** Called when a mouse button or a finger touch goes down on the actor. If true is returned, this listener will have
             * {@link Stage#addTouchFocus(EventListener, Actor, Actor, int, int) touch focus}, so it will receive all touchDragged and
             * touchUp events, even those not over this actor, until touchUp is received. Also when true is returned, the event is
             * {@link Event#handle() handled}.
             * @see ClickableUIElementClickListener */
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(miniMap.isOpen) {
                    return super.touchDown(event, x, y, pointer, button);
                }
                return false;
            }

            /** Called when a mouse button or a finger touch is moved anywhere, but only if touchDown previously returned true for the
             * mouse button or touch. The touchDragged event is always {@link Event#handle() handled}.
             * @see ClickableUIElementClickListener */
            @Override
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                if(miniMap.isOpen) {
                    super.touchDragged(event, x, y, pointer);
                }
            }

            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ClickableUIElementClickListener */
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if(miniMap.isOpen) {
                    super.clicked(event, x, y);
                }
            }
        });
    }

    public void setRoomScreen(RoomScreen roomScreen){
        this.roomScreen = roomScreen;
    }

    public void setPopUpText(){

    }
}
