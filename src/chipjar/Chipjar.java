package chipjar;

public class Chipjar {
	Cpu m_cpu;
	Memory m_memory;
	
	public Chipjar() {
		this.m_cpu = new Cpu();
		this.m_memory = new Memory();
	}
}
