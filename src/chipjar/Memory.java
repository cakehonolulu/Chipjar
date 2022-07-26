package chipjar;

import java.util.Arrays;

public class Memory {
	protected final short RAM_SIZE = 4096;
	
	public byte m_ram[];

	public Memory() {
		this.m_ram = new byte[RAM_SIZE];
		
		Arrays.fill(m_ram, (byte) 0);
	}

	protected byte m_memory_read(short m_where) {
		return this.m_ram[m_where];
	}
	
}
