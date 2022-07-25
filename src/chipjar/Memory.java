package chipjar;

public class Memory {
	protected final short RAM_SIZE = 4096;
	
	protected byte m_memory[];

	public Memory() {
		this.m_memory = new byte[RAM_SIZE];
	}
}
