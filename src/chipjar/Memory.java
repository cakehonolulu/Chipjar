package chipjar;

public class Memory {
	protected final short RAM_SIZE = 4096;
	
	protected byte m_ram[];

	public Memory() {
		this.m_ram = new byte[RAM_SIZE];
	}
}
