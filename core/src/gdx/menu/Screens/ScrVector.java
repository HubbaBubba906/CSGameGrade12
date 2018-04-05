package gdx.menu.Screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import gdx.menu.*;
import gdx.common.*;

public class ScrVector implements Screen, InputProcessor {

    Button btnMenu;
    GameMenu gamMenu;
    OrthographicCamera oc;
    SpriteBatch batch;
    Vector2 position = new Vector2();
    Vector2 velocity = new Vector2();
    Vector2 movement = new Vector2();
    Vector2 vDir = new Vector2();
    Vector2 dir = new Vector2();
    Vector3 temp = new Vector3();
    Texture texture;
    Sprite sprite;

    float speed = 100;

    public ScrVector(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        batch = new SpriteBatch();
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("Ball.png"));
        
        sprite = new Sprite(texture);
        /*Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
        
        //This was for moving to the mouse x and y.
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                camera.unproject(temp.set(screenX, screenY, 0));
                vDir.set(temp.x, temp.y);
                return true;
            }
        });*/
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(Gdx.graphics.getDeltaTime());
        sprite.setScale(0.1f);
        vDir.set(temp.x, temp.y); // setting where the sprite goes
        temp.x += 1; // incressing vector x
        temp.y += 1; // incressing vector y
        batch.begin();
        sprite.draw(batch);
        batch.setProjectionMatrix(oc.combined);
        btnMenu.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height
    ) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode
    ) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode
    ) {
        return false;
    }

    @Override
    public boolean keyTyped(char character
    ) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button
    ) {
        if (button == Input.Buttons.LEFT) {
            if (isHit(screenX, screenY, btnMenu)) {
                System.out.println("Scratch Menu");
                gamMenu.updateState(1);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button
    ) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer
    ) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY
    ) {
        return false;
    }

    @Override
    public boolean scrolled(int amount
    ) {
        return false;
    }

    public boolean isHit(int nX, int nY, Sprite sprBtn) {
        if (nX > sprBtn.getX() && nX < sprBtn.getX() + sprBtn.getWidth() && nY > sprBtn.getY() && nY < sprBtn.getY() + sprBtn.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void update(float deltaTime) {
        position.set(sprite.getX(), sprite.getY());
        dir.set(vDir).sub(position).nor();
        velocity.set(dir.scl(speed));
        movement.set(velocity).scl(deltaTime);
        if (position.dst2(vDir) > movement.len2()) {
            position.add(movement);
        } else {
            position.set(vDir);
        }
        sprite.setX(position.x);
        sprite.setY(position.y);
        //this is the original code used,https://stackoverflow.com/questions/17694076/moving-a-point-vector-on-an-angle-libgdx
      /*dir.set(touch).sub(position).nor();
        velocity.set(dir).scl(speed);
        movement.set(velocity).scl(deltaTime);
        if (position.dst2(touch) > movement.len2()) {
            position.add(movement);
        } else {
            position.set(touch);
        }*/

    }
}
