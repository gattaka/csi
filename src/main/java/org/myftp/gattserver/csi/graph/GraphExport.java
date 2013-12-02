package org.myftp.gattserver.csi.graph;

import java.util.Map;
import java.util.Set;

import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Person;
import org.myftp.gattserver.csi.world.relations.IRelationType;

public class GraphExport {

	private static String createPersonLabel(Person p) {
		return "\"" + p.getFirstName() + " " + p.getSureName() + "\"";
	}

	public static String graphExport(Knowledge knowledge) {

		StringBuilder buffer = new StringBuilder();

		buffer.append("digraph World {");

		// osoby
		for (Person p : knowledge.getPersons()) {
			buffer.append(createPersonLabel(p) + ";");
		}

		for (IRelationType type : knowledge.getRelationsByRelation().keySet()) {
			Map<Person, Set<Person>> relations = knowledge.getRelationsByRelation().get(type);
			for (Person holdingPerson : relations.keySet()) {
				for (Person targetPerson : relations.get(holdingPerson)) {
					buffer.append(createPersonLabel(holdingPerson) + " -> " + createPersonLabel(targetPerson)
							+ " [label=\"" + type.getName() + "\"];");
				}
			}
		}

		buffer.append("}");
		
		return buffer.toString();

	}
}
