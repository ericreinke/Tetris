/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating.pkg2017;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author ereinke7771
 */
public class Settings extends BasicGameState{
Image settings,checkbox,checkedbox;
private static StateBasedGame game;
public boolean click=false;
public String mouse="";
public int difficultyposy=145,playerposy=250,boxx=326,boxw=50;

    Settings(int i) throws SlickException {
       settings= new Image("Images\\Settings(1).jpg");
       checkbox=new Image("Images\\whitecheckbox.png");
       checkedbox=new Image("Images\\whitecheckedbox.png");
    }

    @Override
    public int getID() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        game=sbg;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        settings.draw(0,0);
         
        g.setColor(Color.white);
    g.drawString(mouse, 10, 50);
    //DIFFICULTY CHECKBOXES
    checkedbox.draw(357,difficultyposy,20,20);
    checkbox.draw(357,145,20,20);
    checkbox.draw(357,171,20,20);
    checkbox.draw(357,197,20,20);
    //PLAYER CHECKBOXES
    checkedbox.draw(357,playerposy,20,20);
    checkbox.draw(357,250,20,20);
    checkbox.draw(357,276,20,20);
    //SOUND ON/OFF BOXES
    checkbox.draw(boxx,85,boxw,25);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
 Input input = gc.getInput();
int xpos = input.getMouseX();
int ypos = input.getMouseY();
    mouse = "x: " + xpos + " y: " + ypos; 
    
    
    //BACK TO MENU
        if(input.isMouseButtonDown(0)&&input.getMouseX()>535&&input.getMouseX()<621&&input.getMouseY()>348&&input.getMouseY()<366&&click==false){
            game.enterState(0,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
        }
        //CHOOSING DIFFICULTY
         if(input.isMouseButtonDown(0)&&input.getMouseX()>357&&input.getMouseX()<377&&input.getMouseY()>145&&input.getMouseY()<165&&click==false){
            difficultyposy=145;
        }
          if(input.isMouseButtonDown(0)&&input.getMouseX()>357&&input.getMouseX()<377&&input.getMouseY()>171&&input.getMouseY()<191&&click==false){
            difficultyposy=171;
        }
           if(input.isMouseButtonDown(0)&&input.getMouseX()>357&&input.getMouseX()<377&&input.getMouseY()>197&&input.getMouseY()<217&&click==false){
            difficultyposy=197;
        }
           //CHOOSING PLAYERS
            if(input.isMouseButtonDown(0)&&input.getMouseX()>357&&input.getMouseX()<377&&input.getMouseY()>250&&input.getMouseY()<270&&click==false){
            playerposy=250;
        }
             if(input.isMouseButtonDown(0)&&input.getMouseX()>357&&input.getMouseX()<377&&input.getMouseY()>276&&input.getMouseY()<296&&click==false){
            playerposy=276;
        }
             //SOUND BOXES boxx=326,boxy=85,boxw=50,boxh=25;
              if(input.isMouseButtonDown(0)&&input.getMouseX()>326&&input.getMouseX()<376&&input.getMouseY()>85&&input.getMouseY()<105&&click==false){
                     boxx=326;
                     boxw=50;
              }
               if(input.isMouseButtonDown(0)&&input.getMouseX()>390&&input.getMouseX()<450&&input.getMouseY()>85&&input.getMouseY()<105&&click==false){
                  boxx=390;
                  boxw=60;
              }
              

               
               
               
               if(input.isMouseButtonDown(0)){
        click=true;
    }
    else{
        click=false;
    }
    }
    
}
