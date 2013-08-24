package engine.JWolf2D.core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import engine.JWolf2D.resource.Textures;

public class Game {
	private ScreenManager manager;
	private boolean isCloseRequested = false;
	
	static {
		if(System.getProperty("os.name").startsWith("Win")) {
			new Thread() {
				{
					setDaemon(true);
					start();
				}
				
				public void run() {
					while(true) {
						try {
							Thread.sleep(Long.MAX_VALUE);
						}
						catch(Exception exc) {}
					}
				}
			};
		}
	}
	
	public Game(Screen initialScreen) {
		manager = new ScreenManager(initialScreen);
		Display.setInitialBackground(0, 0, 0);
	}
	
	public void setTitle(String title) {
		Display.setTitle(title);
	}
	
	public void setDisplayMode(int width, int height) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width,height));
	}
	
	public int getWidth() {
		return Display.getWidth();
	}
	
	public int getHeight() {
		return Display.getHeight();
	}
	
	public ScreenManager getScreenManager() {
		return manager;
	}
	
	public void start() throws LWJGLException {
		Display.create();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_ALPHA_TEST);	
		//glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		try {
			Textures.get().add("nulltex", "res/images/nulltex.png");
			manager.init(this);
			
			long lastTime = System.currentTimeMillis();
			int frames = 0;
			long lastSecond = System.currentTimeMillis();
			
			while (!Display.isCloseRequested() && !isCloseRequested) {
				long now = System.currentTimeMillis();
				
				glClear(GL_COLOR_BUFFER_BIT);
				
				long deltaTime = System.currentTimeMillis()-lastTime;
				lastTime += System.currentTimeMillis()-lastTime;
					
				manager.update(deltaTime);
				//System.out.println(deltaTime);
				manager.render();
				
				Display.update();
				
				frames++;
				
				if(System.currentTimeMillis()-lastSecond >= 1e3) {
					System.out.println(frames);
					frames = 0;
					lastSecond += 1e3;
				}
				
				long sleepTime = (int)Math.round(1e3/60.0 - (System.currentTimeMillis() - now));
				if(sleepTime > 0) {
					long time = System.currentTimeMillis();
					long timePassed;
					while((timePassed = System.currentTimeMillis() - time) < sleepTime) {
						if(timePassed < sleepTime * 0.8)
							try{
								Thread.sleep(1);
							}
							catch(Exception exc) {}
						else
							Thread.yield();
					}
				}
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			Display.destroy();
		}
	}

	public void requestClose() {
		isCloseRequested = true;
	}
}


