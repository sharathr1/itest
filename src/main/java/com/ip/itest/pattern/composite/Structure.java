package com.ip.itest.pattern.composite;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Black;

import com.ip.itest.pattern.composite.Block.BlockBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Structure implements Group {
	// Collection of child groups.
	private double sumnodes = 0;
	private List<Group> groups = new ArrayList();

	public void assemble() {
		for (Group group : groups) {
			group.assemble();
		}
	}

	public double getSum() {
		for (Group group : groups) {
			sumnodes = sumnodes + group.getSum();
		}
		return sumnodes;
	}

	// Adds the group to the structure.
	public void add(Group group) {
		groups.add(group);
	}

	// Removes the group from the structure.
	public void remove(Group group) {
		groups.remove(group);
	}

	// Is a Leaf ?
	@Override
	public void checkForLeaf() {
		if (groups.size() > 0) {
			System.err.println("Has Leaf ahead");
		}else{
			System.err.println("END");
		}
	}

	@Override
	public String toString() {
		return "Structure [sumnodes=" + sumnodes + ", groups=" + groups + "]";
	}

}