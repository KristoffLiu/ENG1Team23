package com.engoneassessment.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.engoneassessment.utils.RenderObject;

public class EngOneAssessment extends ApplicationAdapter {
	Array<RenderObject> VisibleThings = new Array<RenderObject>();
	SpriteBatch batch;
	MovingPicture demo;
	Character person;
	private OrthographicCamera camera;
	int i = 0;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,2560,1440);
		batch = new SpriteBatch();
		demo = new MovingPicture(new Texture("badlogic.jpg"));
		person = new Character(new Texture("badlogic.jpg"));
		VisibleThings.add(demo);
		VisibleThings.add(person);

		person.setX(0);
		person.setY(0);
	}

	@Override
	public void render () {
		camera.update();
		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		demo.setX(i);
		demo.setY(i);

		batch.begin();
		for(RenderObject visiblething: VisibleThings ){
			visiblething.Render(batch);
		}
		batch.end();
		i++;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for(RenderObject thing:VisibleThings){
			thing.dispose();
		}
	}
}
