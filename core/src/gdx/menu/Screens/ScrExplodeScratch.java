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

public class ScrExplodeScratch implements Screen, InputProcessor {
/*=========================================================================================================================================================
    This scratch is based on making bullets explode on contact with the ground level.
 =========================================================================================================================================================*/
    Button btnMenu;
    Shot SprBsc;
    GameMenu gamMenu;
    OrthographicCamera oc;
    SpriteBatch batch;
    Texture floor, back;
    int SX = 0, SY = 0, SH = 25, SW = 25;
    float SpriteSpeed = 155f;
    double dSpeed = 0, dGravity = 0.1;

    public ScrExplodeScratch(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        batch = new SpriteBatch();
        floor = new Texture("floor.jpg");
        back = new Texture("back.jpg");
        SX = 500;
        SY = -50;
        dSpeed = 0;
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        SprBsc = new Shot(SX, SY, SH, SW, "BasicShot.png ");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if (SY <= 699) { //Gravity
            dSpeed -= dGravity;
        } else if (SY >= 705) {
            SY = 700;
            dSpeed=0;
            SH = 75;
            SW = 75;
            SX = SX -25;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            SH = 25;
            SW = 25;
            dSpeed = 0;
            SY = 0;
        }
        SY -= dSpeed;
        batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(SprBsc, SX, SY, SH, SW);
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

/* package gdx.menu.Screens;

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

public class ScrScratchShoot implements Screen, InputProcessor {

    /*
-------------------------------------------------------------------------------------------------------------------------------------------------------
This Scratch is based on creating movement, working gravity and limited movement. Limited to each turn, once out you cant move.
-------------------------------------------------------------------------------------------------------------------------------------------------------
    Button btnMenu;
    Tank SprTank1;
    Shot BasicS;
    GameMenu gamMenu;
    OrthographicCamera oc;
    SpriteBatch batch;
    Texture floor, back, gasmoney;
    int TankX = 0, TankY = 0, SX = 0, SY = 0, SAngl = 0;
    float SpriteSpeed = 155f, SPwr;
    double dSpeedT = 0, dGravity = 0.1, dSpeedS = 0;
    int dGas;
    boolean Fire = false, Explode = false;

    public ScrScratchShoot(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        batch = new SpriteBatch();
        floor = new Texture("floor.jpg");
        back = new Texture("back.jpg");
        gasmoney = new Texture("green.png");
        TankX = 500;
        TankY = -50;
        dSpeedT = 0;
        dGas = 500;
        btnMenu = new Button(100, 50, 540, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        SprTank1 = new Tank(TankX, TankY, 100, 100, "Tanks.png ");
        BasicS = new Shot(SX, SY, 10, 10, "BasicShot.png ");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if (TankY <= 100) { //Gravity
            dSpeedT -= dGravity;
        } else if (TankY >= 100) {
            TankY = 100;
        }
        if (dGas <= 400) {
            SpriteSpeed = 0;
            dGas = 500;
        }
        TankY -= dSpeedT;
        batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(gasmoney, TankX, TankY - 10, dGas - 400, 10);
        batch.draw(SprTank1, TankX, TankY, 100, 100);
        if (TankY >= 100) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                TankX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
                if (SpriteSpeed > 1) {
                    dGas -= 1;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                TankX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
                if (SpriteSpeed > 1) {
                    dGas -= 1;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
                if (SPwr >= 101) {
                    SPwr = 100;
                }
                SPwr += 0.1;
                System.out.println(SPwr);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
                if (SPwr <= -1) {
                    SPwr = 0;
                }
                SPwr -= 0.1;
                System.out.println(SPwr);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
                if (SAngl == 0) {
                    SAngl = 180;
                }
                SAngl -= 10;
                System.out.println(SAngl);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
                if (SAngl == 180) {
                    SAngl = 0;
                }
                SAngl += 10;
                System.out.println(SAngl);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                batch.draw(BasicS, SX, SY, 25, 25);
                SX = TankX;
                SY = TankY;
                Fire = true;
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
*/
