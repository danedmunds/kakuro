package ca.danedmunds.kakuro.model;


public class BlockBox extends BorderBox {
	
	public static BlockBox BLOCK_BOX = new BlockBox();

	@Override
	public Class<? extends Box> getType() {
		return BlockBox.class;
	}

	@Override
	public Box copy() {
		return BLOCK_BOX;
	}

}
