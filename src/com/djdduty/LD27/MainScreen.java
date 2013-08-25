package com.djdduty.LD27;

import org.lwjgl.input.Mouse;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.geom.Vector3;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.gui.Button;
import engine.JWolf2D.gui.GuiManager;
import engine.JWolf2D.gui.Label;
import engine.JWolf2D.resource.Textures;

public class MainScreen implements Screen {
	
	private boolean m_Started;
	private ScreenManager m_Manager;
	private GuiManager m_Gui;
	private Button m_PlayButton, m_AboutButton, m_ExitButton;
	private PlayScreen m_GameScreen;
	private Sprite m_BG;
	private Screen m_AboutScreen;
	
	public void init(ScreenManager manager) {
		m_Started = true;
		m_Manager = manager;
		m_Gui = new GuiManager("res/images/gui/fontBig.png");
		
		//Load resources
		System.out.println("Loading Resources...");
		Textures.get().add("Button", "res/images/gui/button.png");
		Textures.get().add("ButtonDown", "res/images/gui/buttonDown.png");
		Textures.get().add("BG", "res/images/menuBG.png");
		//done with resources
		
		//set up logo fade
		//done with logo
		
		//background
		m_BG = new Sprite(new Vector2(0,0), "BG");
		//done with bg
		
		//set up gui
		m_PlayButton = new Button(new Vector2(400-64,168), m_Gui, "Play", "Button", "ButtonDown");
		m_PlayButton.setLabelColor(new Vector3(0,1,0));
		
		m_AboutButton = new Button(new Vector2(400-64,234), m_Gui, "About", "Button", "ButtonDown");
		m_AboutButton.setLabelColor(new Vector3(0,1,0));
		
		m_ExitButton = new Button(new Vector2(400-64,300), m_Gui, "Exit", "Button", "ButtonDown");
		m_ExitButton.setLabelColor(new Vector3(0,1,0));
		
		m_Gui.addLabel(new Label(m_Gui, "10 seconds of Hell!", new Vector2(400-((19*42)/2), 100), 42, 32, new Vector3(1,0,0)));
		
		m_Gui.addButton(m_PlayButton);
		m_Gui.addButton(m_AboutButton);
		m_Gui.addButton(m_ExitButton);
		//done with gui
		
		m_GameScreen = new PlayScreen(0.2f, 1f, 1f, 32f, 20.f, 0, this);
		
		//misc
		m_AboutScreen = new AboutScreen(this);
		//done with misc
	}

	public void update(long deltaTime) {
		while(Mouse.next()) {
			m_Gui.update();
		}
		
		if(m_PlayButton.clicked()) {
			m_Manager.setScreen(m_GameScreen);
		}
		
		if(m_AboutButton.clicked()) {
			m_Manager.setScreen(m_AboutScreen);
		}
		
		if(m_ExitButton.clicked()) {
			m_Manager.getGame().requestClose();
		}
	}

	public void render() {
		m_BG.render(0,0);
		m_Gui.render();
	}

	public boolean getStarted() {
		return m_Started;
	}

	public void setGameScreen(PlayScreen GS) {
		m_GameScreen = GS;
	}
}
