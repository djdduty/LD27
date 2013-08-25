package com.djdduty.LD27;

import org.lwjgl.input.Keyboard;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.resource.Textures;

public class AboutScreen implements Screen {
	private boolean m_Started;
	private ScreenManager m_Manager;
	private Sprite m_BG;
	private Screen m_Owner;
	
	public AboutScreen(Screen Owner) {
		m_Owner = Owner;
	}
	
	public void init(ScreenManager manager) {
		m_Manager = manager;
		m_Started = true;
		
		//load resources
		Textures.get().add("AboutBG", "res/images/about.png");
		//
		
		m_BG = new Sprite(new Vector2(0,0), "AboutBG");
	}

	public void update(long deltaTime) {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			m_Manager.setScreen(m_Owner);
		}
	}

	public void render() {
		m_BG.render(0, 0);
	}

	public boolean getStarted() {
		return m_Started;
	}

}
