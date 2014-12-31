package tk.wurst_client.fuck_cubik.preview.render;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.text.BadLocationException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import tk.wurst_client.fuck_cubik.Main;
import tk.wurst_client.fuck_cubik.dialogs.ErrorMessage;
import tk.wurst_client.fuck_cubik.files.FileManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Renderer
{
	public Renderer()
	{
		this.objectRenderer = new ObjectRenderer();
		this.guiRenderer = new GUIRenderer();
	}
	
	public ObjectRenderer objectRenderer;
	public GUIRenderer guiRenderer;
	public ArrayList<RenderObject> elementsList = new ArrayList<RenderObject>();
	public HashMap<String, File> textureLinkMap = new HashMap<String, File>();
	
	public float posX = 0F;
	public float posY = 0F;
	public float posZ = 0F;
	public float rotX = 0F;
	public float rotY = 0F;
	public float rotZ = 0F;
	public float zoom = 0F;
	
	public void reset()
	{
		this.posX = 0F;
		this.posY = -0.5F;
		this.posZ = 0F;
		this.rotX = 35F;
		this.rotY = 45F;
		this.rotZ = 0F;
		this.zoom = -3F;
	}
	
	public void init()
	{
		this.reset();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glClearColor(0.9F, 0.9F, 0.9F, 0F);
		GL11.glClearDepth(1.0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
		GLU.gluPerspective(45.0f, (float)Display.getDisplayMode().getWidth() / (float)Display.getDisplayMode().getHeight(), 0.1f, 100F);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		guiRenderer.init();
	}
	
	public void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glLoadIdentity();
		
		GL11.glTranslatef(0, 0, zoom);
		GL11.glRotatef(Math.min(Math.max(rotX, -90F), 90F), 1, 0, 0);
		GL11.glRotatef(rotY, 0, 1, 0);
		GL11.glRotatef(rotZ, 0, 0, 1);
		if(Main.frame.desktop.preview.showFocus)
			guiRenderer.renderFocus();
		GL11.glTranslatef((int)(posX * 16F) / 16F, (int)(posY * 16F) / 16F, (int)(posZ * 16F) / 16F);
		
		try
		{
			if(Main.frame.desktop.preview.showGrid)
				guiRenderer.renderGrid();
			if(Main.frame.desktop.preview.showCompass)
				guiRenderer.renderCompass();
			objectRenderer.checkTextureMap();
			for(RenderObject object : elementsList)
				objectRenderer.renderElement(object);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		GL11.glTranslatef(-(int)(posX * 16F) / 16F, -(int)(posY * 16F) / 16F, -(int)(posZ * 16F) / 16F);
	}
	
	public void refresh()
	{
		String code = "";
		elementsList.clear();
		textureLinkMap.clear();
		try
		{
			code = Main.frame.desktop.editor.textarea.getDocument().getText(0, Main.frame.desktop.editor.textarea.getDocument().getLength());
		}catch(BadLocationException e)
		{
			new ErrorMessage("reading code from the editor", e);
			return;
		}
		Gson gson = new Gson();
		JsonObject mainObject = new JsonParser().parse(code).getAsJsonObject();
		JsonObject texturesJSON = mainObject.get("textures").getAsJsonObject();
		Iterator<Entry<String, JsonElement>> texturesItr = texturesJSON.entrySet().iterator();
		while(texturesItr.hasNext())
		{
			Entry<String, JsonElement> textureEntry = (Entry<String, JsonElement>)texturesItr.next();
			File textureFile = new File(FileManager.TEXTURES_DIRECTORY, textureEntry.getValue().getAsString() + ".png");
			textureLinkMap.put(textureEntry.getKey(), textureFile);
		}
		JsonArray elementsJSON = mainObject.get("elements").getAsJsonArray();
		Iterator<JsonElement> elementsItr = elementsJSON.iterator();
		while(elementsItr.hasNext())
		{
			JsonObject element = elementsItr.next().getAsJsonObject();
			int[] from = gson.fromJson(element.get("from"), int[].class);
			int[] to = gson.fromJson(element.get("to"), int[].class);
			ArrayList<RenderObjectFace> facesList = new ArrayList<RenderObjectFace>();
			JsonObject facesJSON = element.get("faces").getAsJsonObject();
			Iterator<Entry<String, JsonElement>> facesItr = facesJSON.entrySet().iterator();
			while(facesItr.hasNext())
			{
				Entry<String, JsonElement> faceEntry = facesItr.next();
				JsonObject face = faceEntry.getValue().getAsJsonObject();
				Side side = Side.valueOf(faceEntry.getKey().toUpperCase());
				int[] uv = gson.fromJson(face.get("uv"), int[].class);
				String textureLink = face.get("texture").getAsString().substring(1);
				RenderObjectFace renderFace = new RenderObjectFace(side, uv, textureLink);
				facesList.add(renderFace);
			}
			RenderObjectFace[] faces = new RenderObjectFace[facesList.size()];
			faces = facesList.toArray(faces);
			RenderObject renderObject = new RenderObject(from, to, faces);
			elementsList.add(renderObject);
		}
		objectRenderer.clearTextureMap();
		Main.frame.desktop.textureViewer.options.reloadTextures();
	}
}
