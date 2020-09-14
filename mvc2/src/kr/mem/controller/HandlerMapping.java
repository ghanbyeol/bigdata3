package kr.mem.controller;

import java.util.HashMap;

import kr.mem.pojo.*;

public class HandlerMapping {
	// key°ª, value°ª
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		iniMap();
	}

		//¡Ú¡Ú¡Ú¡Ú¡Ú¡Ú¡Ú¡Ú¡Ú¡Ú¡Ú¡Ú
	private void iniMap() {
		// list.do ---> MemberListControlooer
		// insert.do ---> MemberInsertControlooer
		// insertForm.do ---> MemberInsertFormControlooer
		// delete.do ---> MemberDeleteControlooer
		try {
			mappings.put("/list.do", new MemberListController());
			mappings.put("/insert.do", new MemberInsertController());
			mappings.put("/insertForm.do", new MemberInsertFormController());
			mappings.put("/delete.do", new MemberDeleteController());
			mappings.put("/content.do", new MemberContentController());
			mappings.put("/update.do", new MemberUpdateController());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Controller getController(String key) {
		return mappings.get(key);
		
	}
}
