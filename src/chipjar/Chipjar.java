package chipjar;

public class Chipjar {
	CPU m_cpu;
	Memory m_memory;
	
	public Chipjar() {
		this.m_cpu = new CPU();
		this.m_memory = new Memory();
	}
}
