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

public class ScrScratchMenu implements Screen, InputProcessor {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////*Menu for the scratches*/////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Doesn't yet use sprites right.
    Button btnMenu, btnScratch1, btnScratch2, btnScratch3, btnScratch4, btnScratch6;
    GameMenu gamMenu;
    Texture txButtonP, txButtonT;
    OrthographicCamera oc;
    SpriteBatch batch;

    public ScrScratchMenu(GameMenu _gamMenu) {  //Referencing the main class.
        gamMenu = _gamMenu;
    }

    @Override
    public void show() {
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        batch = new SpriteBatch();
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        //This scratch involves gravity, movement and limiting the movement to a set ammount per "turn", there are currently no turns.
        btnScratch1 = new Button(100, 50, 0, Gdx.graphics.getHeight() - 800, "S1But.png ");
        //This Scratch is based on turns
        btnScratch2 = new Button(100, 50, 0, Gdx.graphics.getHeight() - 750, "TurnsBut.png ");
        //This scratch is based on creating basic shot types
        btnScratch3 = new Button(100, 50, 0, Gdx.graphics.getHeight() - 700, "S2But.png ");
        //Scratch for shots using gravity and angle
        btnScratch4 = new Button(100, 50, 0, Gdx.graphics.getHeight() - 650, "Shoot2But.png ");

        //This is a scratch for shooting with vectors using user input
        btnScratch6 = new Button(100, 50, 0, Gdx.graphics.getHeight() - 550, "S4But.png ");
        Gdx.input.setInputProcessor(this);
        
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setProjectionMatrix(oc.combined);
        btnMenu.draw(batch);
        btnScratch1.draw(batch);
        btnScratch2.draw(batch);
        btnScratch3.draw(batch);
        btnScratch4.draw(batch);
        btnScratch6.draw(batch);
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
            } else if (isHit(screenX, screenY, btnScratch1)) {
                System.out.println("Scratch 1");
                gamMenu.updateState(10);
            } else if (isHit(screenX, screenY, btnScratch2)) {
                System.out.println("Scratch 2");
                gamMenu.updateState(11);
            } else if (isHit(screenX, screenY, btnScratch3)) {
                System.out.println("Scratch 3");
                gamMenu.updateState(12);
            } else if (isHit(screenX, screenY, btnScratch4)) {
                System.out.println("Scratch 4");
                gamMenu.updateState(13);
            }  else if (isHit(screenX, screenY, btnScratch6)) {
                System.out.println("Scratch 6");
                gamMenu.updateState(15);
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
