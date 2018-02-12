package com.ip.itest.pattern.composite;

/**
 * https://javapapers.com/design-patterns/composite-design-pattern/
 * 
 * @author 999951
 *
 */
public class ImplementComposite {
	public static void main(String[] args) {
		// Initialize three blocks
		Block block1 = new Block(1, null, 15);
		Block block2 = new Block(2, null, 25);
		Block block3 = Block.builder().id(3).price(35).build();
		// Initialize three structure
		Structure structure = new Structure();
		Structure structure1 = new Structure();
		Structure structure2 = new Structure();

		Structure structure3 = new Structure();
		Structure structure4 = new Structure();

		Block block4 = new Block(4, null, 45);
		Block block5 = new Block(5, null, 55);
		Block blocks6 = new Block(6, null, 65);
		structure4.add(blocks6);

		structure3.add(block4);
		structure3.add(block5);
		structure3.add(structure4);
		structure2.add(structure3);
		// Composes the groups
		structure1.add(block1);
		structure1.add(block2);

		structure2.add(block3);

		structure.add(structure1);
		structure.add(structure2);

		structure2.assemble();
		System.out.println(structure2.getSum());
		structure.checkForLeaf();
		Structure structure5 = new Structure();
		structure.add(structure5);
		structure5.checkForLeaf();
	}
}