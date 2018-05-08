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

public class ScrUserInput implements Screen, InputProcessor {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////*This needs to get the directions of the y-axis changed since this was originaly in a scratch elsewhere*////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Doesn't yet use sprites right.
    Button btnMenu;
    GameMenu gamMenu;
    OrthographicCamera oc;
    SpriteBatch batch;
    boolean bFire = false;
    int nCount = 0;
    Vector2 vPos = new Vector2(10, 600);
    Vector2 vVel = new Vector2(0, 0);
    Vector3 vTempTouch = new Vector3(10, 600, 0);
    Vector2 vTouch = new Vector2(0, 0);
    Vector2 vDir = new Vector2();
    float fSpeed = 4;
    Texture texture;
    Sprite sprShot;
    Sprite sprite;

    float speed = 100;

    public ScrUserInput(GameMenu _gamMenu) {  //Referencing the main class.
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
        texture = new Texture(Gdx.files.internal("S1But.png"));
        sprShot = new Sprite(texture);
        sprite = new Sprite(texture);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bFire = true;
        }
        if (bFire == true && sprShot.getY() <= 600) {
            update(Gdx.graphics.getDeltaTime());
            vVel.y -= 0.07;
        } else {
            bFire = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (fSpeed <= 10) {
                fSpeed += 0.1;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (fSpeed >= 1.01) {
                fSpeed -= 0.1;
            }
        }
        batch.begin();
        sprite.draw(batch);
        batch.setProjectionMatrix(oc.combined);
        btnMenu.draw(batch);
        batch.end();
    }

    public void update(float deltaTime) {
        vPos.add(vVel);
        sprShot.setX(vPos.x);
        sprShot.setY(vPos.y);
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (bFire == false) {
            sprShot.setY(599);
            vTempTouch = oc.unproject(new Vector3(screenX, screenY, 0));
            vTouch.set(vTempTouch.x, vTempTouch.y);
            vVel = vTouch.sub(vPos).nor().scl(fSpeed);
        }
        return true;
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
}
