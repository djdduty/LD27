package com.djdduty.LD27;

import java.text.DecimalFormat;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.geom.Vector3;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.gui.GuiManager;
import engine.JWolf2D.gui.Label;
import engine.JWolf2D.resource.Textures;

public class PlayScreen implements Screen {
	private GuiManager m_Gui;
	private MainScreen m_Owner;
	private ScreenManager m_Manager;
	private boolean Started = false, m_Lost, m_Won, m_InDanger;
	private float m_Tolerance, m_Speed, m_VerticalRandomness, m_HorrizontalRandomness, m_RotationRandomness;
	private Sprite m_Shape, m_PlayerShape;
	private Label m_TimeLabel, m_DangerLabel, m_LostLabel, m_WonLabel, m_SpaceLabel, m_DTimerLabel;
	private double m_Timer, m_SafeTimer, m_ChangeTimer;
	private DecimalFormat m_Df;
	private Vector2 m_ShapeVelocity;
	private Random m_RNG;
	private int m_RoundNum;
	
	public PlayScreen(float VR, float HR, float Speed, float Tolerance, float RR, int Round, MainScreen Owner) {
		m_VerticalRandomness = VR;
		m_HorrizontalRandomness = HR;
		m_RotationRandomness = RR * 1.1f;
		
		if(m_RotationRandomness > 360)
			m_RotationRandomness = 360;

		m_Speed = Speed * 1.2f;
		
		m_Tolerance = Tolerance-1;
		if(m_Tolerance < 10)
			m_Tolerance = 10;
		
		m_RoundNum = Round;
		
		//Load resources=========================================================================
		System.out.println("Loading Shapes...");
		Textures.get().add("Triangle", "res/images/triangle.png");
		Textures.get().add("Square", "res/images/square.png");
		//done with resources====================================================================
		
		m_Owner = Owner;
		m_Owner.setGameScreen(this);
	}
	
	public void init(ScreenManager manager) {
		m_Manager = manager;
		Started = true;
		
		//Sprite initialization==================================================================
		m_Shape = new Sprite(new Vector2(400,300), "Triangle");
		m_Shape.setColor(new Vector3(1,0,0));
		
		m_PlayerShape = new Sprite(new Vector2(400,300), "Triangle");
		m_PlayerShape.setColor(new Vector3(0,1,0));
		//done with sprites======================================================================
		
		//Gui====================================================================================
		m_Gui = new GuiManager("res/images/gui/fontBig.png");
		
		m_TimeLabel = new Label(m_Gui, "Time Left:" + m_Timer, new Vector2(16, 16), 18, 18, new Vector3(0,1,0));
		m_Gui.addLabel(m_TimeLabel);
		
		m_DangerLabel = new Label(m_Gui, "In Danger:NO", new Vector2(16, 40), 18, 18, new Vector3(0,1,0));
		m_Gui.addLabel(m_DangerLabel);
		
		m_Gui.addLabel(new Label(m_Gui, "Rounds Played:"+m_RoundNum, new Vector2(480, 18), 18, 18, new Vector3(0,1,0)));
		
		m_Df = new DecimalFormat("##.##");
		//done with gui==========================================================================
		
		//misc init==============================================================================
		
		//labels---------------------------------
		m_RNG = new Random();
		
		m_LostLabel = new Label(m_Gui.getFont(), "You LOST!Looser", new Vector2(400-((16*32)/2),300-16), 32, 32, new Vector3(1,0,0));//16 chars
		m_WonLabel = new Label(m_Gui.getFont(), "You WON!YAY!", new Vector2(400-((12*32)/2),300-16), 32, 32, new Vector3(0,1,0));//13 chars
		m_SpaceLabel = new Label(m_Gui.getFont(), "Press SPACE to continue.", new Vector2(400-((24*20)/2),300+20), 20, 20, new Vector3(1,1,1));//24 chars
		m_DTimerLabel = new Label(m_Gui.getFont(), "Time left to recover:", new Vector2(16, 64), 18, 18, new Vector3(1,0,0));//22 chars
		//bools----------------------------------
		m_Lost = false;
		m_Won = false; 
		m_InDanger = false;
		
		m_Shape.enableRotation(true);
		m_PlayerShape.enableRotation(true);
		//timers---------------------------------
		m_Timer = 10f;
		m_SafeTimer = 3f;
		m_ChangeTimer = 4f;
		//done with misc=========================================================================
		
		//Shape initial velocity=================================================================
		m_ShapeVelocity = new Vector2(0,0);
		m_ShapeVelocity.x = (float) ((m_RNG.nextDouble()-0.5) * m_HorrizontalRandomness * m_Speed);
		m_ShapeVelocity.y = (float) ((m_RNG.nextDouble()-0.5) * m_VerticalRandomness * m_Speed);
		//done with shape init velocity==========================================================
	}

	public void update(long deltaTime) {
		if(!m_Lost && !m_Won) {//if the game is currently going
			//Timer update=======================================================================
			m_ChangeTimer -= (float)deltaTime/1000;
			m_Timer -= (float)deltaTime/1000;
			
			if(m_Timer <= 0.01 && !m_InDanger) {
				m_Timer = 0;
				m_Won = true;
			}
			
			m_TimeLabel.setContent("Time Left:" + m_Df.format(m_Timer));
			//====================================================================================
		
		
			//update shape=========================e==============================================
			
			//Translation----------------------------
			if(m_Shape.getPos().x + m_Shape.getWidth()/2 > 800 || m_Shape.getPos().x - m_Shape.getWidth()/2 < 0)
				m_ShapeVelocity.x *= -1;
		
			if(m_Shape.getPos().y + m_Shape.getHeight()/2 > 600 || m_Shape.getPos().y - m_Shape.getHeight()/2 < 0)
				m_ShapeVelocity.y *= -1;
		
			m_Shape.addPos(m_ShapeVelocity);
			//---------------------------------------
			
			//Rotation and Scale---------------------
			if(m_ChangeTimer <= 0.1) {
				m_Shape.setRotation(m_RNG.nextFloat() * m_RotationRandomness);
				
				m_Shape.setScale(m_RNG.nextFloat() * 2);
				if(m_Shape.getScale() < 0.6f)
					m_Shape.setScale(0.6f);
				
				m_ChangeTimer = 4;
			}
			//---------------------------------------
			//done with shape transformation======================================================
			
			
			//update player=======================================================================
			m_PlayerShape.setPos(new Vector2(Mouse.getX(), (-Mouse.getY()+600)));
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W) && m_PlayerShape.getScale() < 2.0)
				m_PlayerShape.addScale(0.05f);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S) && m_PlayerShape.getScale() > 0.2)
				m_PlayerShape.addScale(-0.05f);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
				m_PlayerShape.addRotation(5);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_A))
				m_PlayerShape.addRotation(-5);
			
			if(m_PlayerShape.getRotation() > 360 || m_PlayerShape.getRotation() < -360) 
				m_PlayerShape.setRotation(0);
			
			/*if(m_PlayerShape.getRotation() < 0) 
				m_PlayerShape.setRotation(360 + m_PlayerShape.getRotation());*/
			
			//done with player====================================================================
			
			
			//check if loosing====================================================================
			m_InDanger = false;
			
			if(m_PlayerShape.getPos().x < m_Shape.getPos().x - m_Tolerance || m_PlayerShape.getPos().x > m_Shape.getPos().x + m_Tolerance)
				m_InDanger = true;
			
			if(m_PlayerShape.getPos().y < m_Shape.getPos().y - m_Tolerance || m_PlayerShape.getPos().y > m_Shape.getPos().y + m_Tolerance)
				m_InDanger = true;
			
			if(m_PlayerShape.getRotation() > m_Shape.getRotation()+m_Tolerance)
				m_InDanger = true;
			
			if(m_PlayerShape.getRotation() < m_Shape.getRotation()-m_Tolerance)
				m_InDanger = true;
			
			if(m_PlayerShape.getScale() > m_Shape.getScale()+m_Tolerance/75)
				m_InDanger = true;
			
			if(m_PlayerShape.getScale() < m_Shape.getScale()-m_Tolerance/75)
				m_InDanger = true;
			//Update danger--------------------------
			if(m_InDanger) {
				m_DTimerLabel.setContent("Time left to recover:" + m_Df.format(m_SafeTimer));
				m_SafeTimer -= (float)deltaTime/1000;
				m_DangerLabel.setContent("In Danger:YES");
			} else {
				if(m_SafeTimer < 3)
					m_SafeTimer += (float)deltaTime/1000;
				
				m_DangerLabel.setContent("In Danger:NO");
			}
			//done w/ danger------------------------
			
			if(m_SafeTimer < 0.01)
				m_Lost = true;
			
			//done with loosing===================================================================
			
		} else if(m_Lost) {
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				this.init(m_Manager);
			
		}else if(m_Won) {
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				m_Manager.setScreen(new PlayScreen(m_VerticalRandomness, m_HorrizontalRandomness, m_Speed, m_Tolerance, m_RotationRandomness, m_RoundNum+1, m_Owner));
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			m_Manager.setScreen(m_Owner);
	}

	public void render() {
		m_Shape.render(0,0);
		m_PlayerShape.render(0,0);
		m_Gui.render();
		
		if(m_InDanger)
			m_DTimerLabel.render();
		
		if(m_Lost) {
			m_LostLabel.render();
			m_SpaceLabel.render();
		} else if(m_Won) {
			m_WonLabel.render();
			m_SpaceLabel.render();
		}
	}

	public boolean getStarted() {
		return Started;
	}

}
