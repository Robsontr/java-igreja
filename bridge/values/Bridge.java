package bridge.values;

import java.util.ArrayList;
import java.util.List;

import entry.log.EntryLog;
import members.Members;
import out.log.OutLog;

public class Bridge {

	private List<EntryLog> el = new ArrayList<>();
	private List<OutLog> ol = new ArrayList<>();
	private List<Members> members = new ArrayList<>();

	public Bridge() {

	}

	public List<EntryLog> getEl() {
		return el;
	}

	public List<OutLog> getOl() {
		return ol;
	}

	public List<Members> getMembers() {
		return members;
	}

	// ADD LOG DE ENTRADA DE VALORES
	public void addLog(EntryLog entryLog) {
		el.add(entryLog);
	}

	// ADD LOG DE SAÍDA DE VALORES
	public void addLogOut(OutLog outLog) {
		ol.add(outLog);
	}

	// ADIÇÃO DE MEMBROS
	public void addMembers(Members members) {
		this.members.add(members);
	}

}
