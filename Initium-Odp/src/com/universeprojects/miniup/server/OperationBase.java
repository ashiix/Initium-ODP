package com.universeprojects.miniup.server;

import com.universeprojects.miniup.server.model.GridCell;
import com.universeprojects.miniup.server.model.GridObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class OperationBase
{

	private List<Map<String, String>> htmlUpdates = new ArrayList<Map<String, String>>();
	private List<GridCell> gridCellUpdates = new ArrayList<>();
	private List<GridObject> gridObjectUpdates = new ArrayList<>();
	
	/**
	 * When the command returns to the browser, the page will then find the elements that match
	 * the given jquerySelector and the element's contents will be filled with the given htmlContents.
	 * 
	 * If you just want to replace the selected elements with html, then use the 
	 * updateHtml() method instead.
	 * 
	 * @param jquerySelector
	 * @param htmlContents
	 */
	public void updateHtmlContents(String jquerySelector, String htmlContents)
	{
		Map<String,String> htmlData = new HashMap<String,String>();
		
		htmlData.put("type", "0");
		htmlData.put("selector", jquerySelector);
		htmlData.put("html", htmlContents);
		
		htmlUpdates.add(htmlData);
	}
	
	/**
	 * When the command returns to the browser, the page will then find the elements that match
	 * the given jquerySelector and the element itself will be replaced with the given newHtml.
	 * 
	 * If you just want to update the contents of the element (ie. adding html inside of a div) then 
	 * use the updateHtmlContents() method instead.
	 * 
	 * @param jquerySelector
	 * @param newHtml
	 */
	public void updateHtml(String jquerySelector, String newHtml)
	{
		Map<String,String> htmlData = new HashMap<String,String>();
		
		htmlData.put("type", "1");
		htmlData.put("selector", jquerySelector);
		htmlData.put("html", newHtml);
		
		htmlUpdates.add(htmlData);
	}
	
	/**
	 * When the command returns to the browser, the script that has the given elementId 
	 * will be deleted and the then a new script tag will be added to the page with the given javascript.
	 * The new javascript <script> tag will also have the same id as the one deleted.
	 * 
	 *
	 */
	public void updateJavascript(String elementId, String newJavascript)
	{
		Map<String,String> htmlData = new HashMap<String,String>();
		
		htmlData.put("type", "4");
		htmlData.put("id", elementId);
		htmlData.put("js", newJavascript);
		
		htmlUpdates.add(htmlData);
	}
	
	/**
	 * When the command returns to the browser, the page will then find the first element 
	 * that matches the given jquerySelector and will insert the given htmlContents before.
	 * 
	 * @param jquerySelector
	 * @param htmlContents
	 */
	public void insertHtmlBefore(String jquerySelector, String htmlContents)
	{
		Map<String,String> htmlData = new HashMap<String,String>();
		
		htmlData.put("type", "2");
		htmlData.put("selector", jquerySelector);
		htmlData.put("html", htmlContents);
		
		htmlUpdates.add(htmlData);
	}
	
	/**
	 * When the command returns to the browser, the page will then find the last element 
	 * that matches the given jquerySelector and will insert the given htmlContents after.
	 * 
	 * @param jquerySelector
	 * @param htmlContents
	 */
	public void insertHtmlAfter(String jquerySelector, String htmlContents)
	{
		Map<String,String> htmlData = new HashMap<String,String>();
		
		htmlData.put("type", "3");
		htmlData.put("selector", jquerySelector);
		htmlData.put("html", htmlContents);
		
		htmlUpdates.add(htmlData);
	}
	
	public List<Map<String,String>> getHtmlUpdates()
	{
		return htmlUpdates;
	}

	public List<GridCell> getGridCellUpdates() {
		return gridCellUpdates;
	}

	public void setGridCellUpdates(List<GridCell> gridCellUpdates) {
		this.gridCellUpdates = gridCellUpdates;
	}

	public List<GridObject> getGridObjectUpdates() {
		return gridObjectUpdates;
	}

	public void setGridObjectUpdates(List<GridObject> gridObjectUpdates) {
		this.gridObjectUpdates = gridObjectUpdates;
	}
	
	public void addGridCellUpdate(GridCell gridCell) {
		this.gridCellUpdates.add(gridCell);
	}
	
	public void addGridObjectUpdate(GridObject gridObject) {
		this.gridObjectUpdates.add(gridObject);
	}
	
	public JSONObject getMapUpdateJSON() {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(this.gridCellUpdates);
		
		JSONArray jsonObjects = new JSONArray();
		jsonObjects.addAll(this.gridObjectUpdates);
		jsonObject.put("GridCells", jsonArray);
		jsonObject.put("GridObject", jsonObjects);
		return jsonObject;
	}
}
