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

public class ScrScratchShootV implements Screen, InputProcessor {

    /*
-------------------------------------------------------------------------------------------------------------------------------------------------------
This Scratch is based on creating movement, working gravity and limited movement. Limited to each turn, once out you cant move.
-------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    Button btnMenu;
    Shot SprBsc;
    GameMenu gamMenu;
    OrthographicCamera oc;
    SpriteBatch batch;
    Texture floor, back;
    int SX = 100, SY = 699, SH = 25, SW = 25, SPwr = 0, SAngl = 0;
    float SpriteSpeed = 155f;
    double dSpeedX = 10, dSpeedY = 10, dGravity = 0.3;
    boolean Fire = false;

    public ScrScratchShootV(GameMenu _gamMenu) {  //Referencing the main class.
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
        btnMenu = new Button(100, 50, 1500, Gdx.graphics.getHeight() - 50, "MenuBut.png ");
        SprBsc = new Shot(SX, SY, SH, SW, "BasicShot.png ");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //Yellow background.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            SPwr += 1;
            if (SPwr > 100) {
                SPwr = 100;
            }
            System.out.println(SPwr);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            SPwr -= 1;
            if (SPwr < 0) {
                SPwr = 0;
            }
            System.out.println(SPwr);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            SAngl += 1;
            if (SAngl > 180) {
                SAngl = 180;
            }
            System.out.println(SAngl);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            SAngl -= 1;
            if (SAngl < 0) {
                SAngl = 0;
            }
            System.out.println(SAngl);
        }

            batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.draw(SprBsc, SX, SY, SH, SW);
            batch.setProjectionMatrix(oc.combined);
            btnMenu.draw(batch);
            batch.end();
        }

        @Override
        public void resize
        (int width, int height
        
        
        ) {
    }

    @Override
        public void pause
        
        
        () {
    }

    @Override
        public void resume
        
        
        () {
    }

    @Override
        public void hide
        
        
        () {
    }

    @Override
        public void dispose
        
            () {
        batch.dispose();
        }

        @Override
        public boolean keyDown
        (int keycode
        
            ) {
        return false;
        }

        @Override
        public boolean keyUp
        (int keycode
        
            ) {
        return false;
        }

        @Override
        public boolean keyTyped
        (char character
        
            ) {
        return false;
        }

        @Override
        public boolean touchDown
        (int screenX, int screenY, int pointer, int button
        
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
        public boolean touchUp
        (int screenX, int screenY, int pointer, int button
        
            ) {
        return false;
        }

        @Override
        public boolean touchDragged
        (int screenX, int screenY, int pointer
        
            ) {
        return false;
        }

        @Override
        public boolean mouseMoved
        (int screenX, int screenY
        
            ) {
        return false;
        }

        @Override
        public boolean scrolled
        (int amount
        
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
