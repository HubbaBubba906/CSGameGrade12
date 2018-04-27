package gdx.vec;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {
    Vector2 position = new Vector2();
    Vector2 velocity = new Vector2();
    Vector2 movement = new Vector2();
    Vector2 touch = new Vector2();
    Vector2 dir = new Vector2();
    Vector3 temp = new Vector3();
    float speed = 100;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
    @Override
    public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("Ball.png"));

        sprite = new Sprite(texture);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown (int screenX, int screenY, int pointer, int button) {
                camera.unproject(temp.set(screenX, screenY, 0));
                touch.set(temp.x, temp.y);
                return true;
            }
        });
    }

    @Override
    public void render () {

        update(Gdx.graphics.getDeltaTime());
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();            
    }
    public void update (float deltaTime) {
        position.set(sprite.getX(), sprite.getY());
        dir.set(touch).sub(position).nor();
        velocity.set(dir).scl(speed);
        movement.set(velocity).scl(deltaTime);
        if (position.dst2(touch) > movement.len2()) {
            position.add(movement); 
        } else {
            position.set(touch);
        }               
        sprite.setX(position.x);
        sprite.setY(position.y);
    }
}