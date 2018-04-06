package gdx.Game;

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
import gdx.menu.Screens.*;

public class ScreenPlay implements Screen, InputProcessor {

    /*=========================================================================================================================================================
    This scratch is based on making turns betweeen the player and enemies.
 =========================================================================================================================================================*/
    Button btnMenu;
    GameMenu gamMenu;
    Texture txButtonP, txButtonT;
    OrthographicCamera oc;
    SpriteBatch batch;
    Shot SprBsc;
    Tank SprTank1;
    Texture floor, back, gasmoney;
    int TankX = 0, TankY = 0;
    float SpriteSpeed = 155f;
    double dSpeed = 0, dGravity = 0.1;
    int dGas;
    int SX = TankX, SY = 699, SH = 25, SW = 25;
    double dSpeedX = 7, dSpeedY = 7;
    boolean Fire = false;
    boolean InAir = false;

    public ScreenPlay(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        batch = new SpriteBatch();
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        floor = new Texture("floor.jpg");
        back = new Texture("back.jpg");
        gasmoney = new Texture("green.png");
        TankX = 500;
        TankY = -50;
        dSpeed = 0;
        dGas = 500;
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        SprTank1 = new Tank(TankX, TankY, 100, 100, "Tanks.png ");
        SprBsc = new Shot(SX, SY = 700, SH, SW, "BasicShot.png ");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1); //White background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (TankY <= 650) { //Gravity
            dSpeed -= dGravity;
        } else if (TankY >= 650) {
            TankY = 650;
        }
        if (dGas <= 400) {
            SpriteSpeed = 0;
            dGas = 500;
        }
        TankY -= dSpeed;
        /* batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(gasmoney, TankX, TankY - 10, dGas - 400, 10);
        batch.draw(SprTank1, TankX, TankY, 100, 100);*/
        if (TankY >= 100) {
            if (Gdx.input.isKeyPressed(Input.Keys.A) && TankX > 0) {
                TankX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
                if (SpriteSpeed > 1) {
                    dGas -= 1;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) && TankX < Gdx.graphics.getWidth()) {
                TankX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
                if (SpriteSpeed > 1) {
                    dGas -= 1;
                }
            }
        }
        if (SY >= 700) {
            SX = TankX + 30;
            SY = TankY + 40;
            dSpeedX = 0;
            dSpeedY = 0;
            Fire = false;
            InAir = false;
        }
        if (Fire = true) {
            dSpeedY -= dGravity;
            SY -= dSpeedY;
            SX += dSpeedX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && InAir != true) {
            dSpeedX = 7;
            dSpeedY = 7;
            SY = 699;
            Fire = true;
            InAir = true;
        }
        batch.begin();
        batch.setProjectionMatrix(oc.combined);
        batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnMenu.draw(batch);
        batch.draw(SprBsc, SX, SY, SH, SW);
        batch.draw(gasmoney, TankX, TankY - 10, dGas - 400, 10);
        batch.draw(SprTank1, TankX, TankY, 100, 100);
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
