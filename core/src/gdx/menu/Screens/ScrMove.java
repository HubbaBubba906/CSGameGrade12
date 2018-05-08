package gdx.menu.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import gdx.menu.*;
import gdx.common.*;

public class ScrMove implements Screen, InputProcessor {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////*This is a scratch to create gravity and movement with gas*///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Doesn't yet use sprites right.
    Button btnMenu;
    Tank sprTank1;
    GameMenu gamMenu;
    OrthographicCamera oc;
    SpriteBatch batch;
    Texture txFloor, txBack, txGasmoney;
    int nTankX = 0, nTankY = 0;
    float fSpriteSpeed = 155f;
    double dSpeed = 0, dGravity = 0.1;
    int nGas;

    public ScrMove(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        batch = new SpriteBatch();
        txFloor = new Texture("floor.jpg");
        txBack = new Texture("back.jpg");
        txGasmoney = new Texture("green.png");
        nTankX = 500;
        nTankY = -50;
        dSpeed = 0;
        nGas = 500;
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        sprTank1 = new Tank(nTankX, nTankY, 100, 100, "Tanks.png ");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        if (nTankY <= 700) { //Gravity
            dSpeed -= dGravity;
        } else if (nTankY >= 700) {
            nTankY = 700;
        }
        if (nGas <= 400) {
            fSpriteSpeed = 0;
            nGas = 500;
        }
        nTankY -= dSpeed;
        batch.draw(txBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(txGasmoney, nTankX, nTankY - 10, nGas - 400, 10);
        batch.draw(sprTank1, nTankX, nTankY, 100, 100);
        if (nTankY >= 100) {
            if (Gdx.input.isKeyPressed(Input.Keys.A) && nTankX > 0) {
                nTankX -= Gdx.graphics.getDeltaTime() * fSpriteSpeed;
                if (fSpriteSpeed > 1) {
                    nGas -= 1;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) && nTankX < Gdx.graphics.getWidth()) {
                nTankX += Gdx.graphics.getDeltaTime() * fSpriteSpeed;
                if (fSpriteSpeed > 1) {
                    nGas -= 1;
                }
            }
        }
        batch.setProjectionMatrix(oc.combined);
        btnMenu.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
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
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
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
