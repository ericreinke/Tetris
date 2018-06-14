

package culminating.pkg2017;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Culminating2017 extends StateBasedGame{

    
  public static void main(String[] args) throws SlickException {
       
         AppGameContainer app = new AppGameContainer(new Culminating2017("Culminating"));
      app.setShowFPS(true);
   
      app.setTargetFrameRate(120);
      app.setDisplayMode(633, 385, false);
      app.setVSync(true);
      app.start();
    }

    public Culminating2017(String name) {
        super(name);
    }


    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
           
            addState (new Culminating(0));
            addState (new Settings(1));
            addState (new HighScores(2));
            addState (new Play(3));
            addState (new GameOver(4));
    }

}
