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
import gdx.menu.*;
import gdx.common.*;
import gdx.menu.*;

public class ScrPlay implements Screen, InputProcessor {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////*Play Screen for all scratches compiled*//////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Doesn't yet use sprites right.
    Button btnMenu;
    GameMenu gamMenu;
    Texture txButtonP, txButtonT;
    OrthographicCamera oc;
    SpriteBatch batch;
    Shot sprBsc;
    Tank sprTank1;
    Texture txFloor, txBack, txGasmoney;
    int nTankX = 0, nTankY = 0;
    float fSpriteSpeed = 155f;
    double dSpeed = 0, dGravity = 0.1;
    int nGas;
    int nSX = nTankX, nSY = 699, nSH = 25, nSW = 25;
    double dSpeedX = 4, dSpeedY = 4;
    boolean bFire = false;
    boolean bInAir = false;

    public ScrPlay(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        nTankX = 500;
        nTankY = -50;
        batch = new SpriteBatch();
        txFloor = new Texture("floor.jpg");
        txBack = new Texture("back.jpg");
        txGasmoney = new Texture("green.png");
        dSpeed = 0;
        nGas = 500;
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        sprTank1 = new Tank(nTankX, nTankY, 100, 100, "Tanks.png ");
        sprBsc = new Shot(nSX, nSY = 700, nSH, nSW, "BasicShot.png ");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1); //White background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (nTankY <= 650) { //Gravity
            dSpeed -= dGravity;
        } else if (nTankY >= 650) {
            sprTank1.setY(400);
            
        }
        if (nGas <= 400) {
            fSpriteSpeed = 0;
            nGas = 500;
        }
        nTankY -= dSpeed;
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
        if (nSY >= 700) {
            nSX = nTankX + 30;
            nSY = nTankY + 40;
            dSpeedX = 0;
            dSpeedY = 0;
            bFire = false;
            bInAir = false;
        }
        if (bFire = true) {
            dSpeedY -= dGravity;
            nSY -= dSpeedY;
            nSX += dSpeedX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && bInAir != true) {
            dSpeedX = 4;
            dSpeedY = 4;
            nSY = 699;
            bFire = true;
            bInAir = true;
        }
        batch.begin();
        batch.setProjectionMatrix(oc.combined);
        batch.draw(txBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnMenu.draw(batch);
        sprBsc.draw(batch);
        batch.draw(txGasmoney, nTankX, nTankY - 10, nGas - 400, 10);
        //batch.draw(SprTank1, nTankX, nTankY, 100, 100);
        sprTank1.draw(batch);
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
                System.out.println("Menu");
                gamMenu.updateState(0);
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
