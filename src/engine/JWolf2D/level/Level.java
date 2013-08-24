package engine.JWolf2D.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.logic.GameObject;
import engine.JWolf2D.logic.Player;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

<<<<<<< HEAD
import com.djdduty.LD26.game.Tea;

=======
>>>>>>> Initial commit mk.2
public class Level {
	private ArrayList<Layer> layers;
	private String name;
	private Layer mainLayer;
	private Player player;
<<<<<<< HEAD
	public Tea tea;
=======
>>>>>>> Initial commit mk.2
	private Vector2 playerPos;
	
	public Level(String name) {
	this.name = name;	
	layers = new ArrayList<Layer>();
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Vector2 getPlayerPos() {
		return playerPos;
	}
	
	public void setPlayerPos(Vector2 playerPos) {
		this.playerPos = playerPos;
	}
	
	public void save() {
		Document doc = new Document();
		Element root = new Element("Level");
		for(Layer l : layers) {
			Element layer = new Element("layer");
				layer.setAttribute("name", l.getName());
				for(GameObject o : l.objects) {
					Element gameObject = new Element("gameObject");
					gameObject.setAttribute("texture", o.getTexture().getTextureName());
					gameObject.setAttribute("x", String.valueOf((int)(o.getPos().x)));
					gameObject.setAttribute("y", String.valueOf((int)(o.getPos().y)));
					layer.addContent(gameObject);
				}
			root.addContent(layer);
		}
		doc.setRootElement(root);
		XMLOutputter outputter = new XMLOutputter();

		try {
			outputter.output(doc, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load(String filePath) {
		SAXBuilder builder = new SAXBuilder();
		InputStream xmlFile = Level.class.getClassLoader().getResourceAsStream(filePath);

		  try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List layerList = rootNode.getChildren("layer");

			for (int i = 0; i < layerList.size(); i++) {
			   Element node = (Element) layerList.get(i);
			   Layer l = new Layer(String.valueOf(node.getAttributeValue("name")), this);
			   addLayer(l);
			   if(l.getName().equals("main")){setMainLayer(l); System.out.println("Found the main Layer");}
			   List objectList = node.getChildren("gameObject");
			       for (int o = 0; o < objectList.size(); o++) {
			    	   Element childNode = (Element) objectList.get(o);
			    	   GameObject object;
			    	   int x = Integer.valueOf(childNode.getAttributeValue("x"));
			    	   int y = Integer.valueOf(childNode.getAttributeValue("y"));
			    	   String texName = childNode.getAttributeValue("texture");
			    	   object = new GameObject(new Vector2(x, y), texName);
			    	   System.out.println(x + ", " + y + ", " + texName);
			    	   l.addObject(object);
			       }
			}
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
	}
	
	public String getName() {
		return name;
	}
	
	public void addLayer(Layer l) {
		layers.add(l);
		System.out.println("Added layer " + l.getName());
	}
	
	public void removeLayer(Layer l) {
		layers.remove(l);
	}
	
	public void update(long deltaTime) {
		for(Layer l : layers) {
			l.update(deltaTime);
		}
	}
	
	public void render(int xoff, int yoff) {
		for(Layer l : layers) {
			l.render(xoff, yoff);
		}
	}

	public Layer getLayer(String name) {
		for(Layer l : layers) {
			if(l.getName() == name) {
				return l;
			}
		}
		return null;
	}
	
	public void clear() {
		for(Layer l : layers) {
			layers.remove(l);
		}
	}
	
	public Layer getMainLayer() {
		return mainLayer;
	}
	
	public void setMainLayer(Layer l) {
		mainLayer = l;
	}
	
	public GameObject containsPoint(String layerName, Vector2 point) {
		return getLayer(layerName).containsPoint(point);
	}
}
