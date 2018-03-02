package com.ip.itest.pattern.composite;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Block implements Group {
	private int id;
	private String name;
	private double price;

	
	public void assemble() {
		System.out.println("Block_ID "+this.getId());
	}

	@Override
	public double getSum() {
		System.out.println("Block_pricce "+this.getPrice());

		return this.getPrice();
	}

	@Override
	public void checkForLeaf() {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "Block [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}